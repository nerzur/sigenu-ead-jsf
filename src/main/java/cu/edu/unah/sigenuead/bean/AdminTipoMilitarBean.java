package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.TipoMilitar;
import cu.edu.unah.sigenuead.service.TipoMilitarServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
@Data
public class AdminTipoMilitarBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<TipoMilitar> tipoMilitarList = new ArrayList<TipoMilitar>();
    private TipoMilitar tipoMilitarSelected;
    private TipoMilitarServices serv = new TipoMilitarServices();

    public void init() {
        tipoMilitarList.clear();
        tipoMilitarList = serv.findAllTipoMilitar();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_TipoMilitar(TipoMilitar tipomilitar) {
        this.setTipoMilitarSelected(tipomilitar);
        cleanVariables();
    }

    public void updateSelected_TipoMilitar_toDelete(TipoMilitar tipomilitar) {
        this.setTipoMilitarSelected(tipomilitar);
        nombre1 = tipomilitar.getNombreTipo();
    }

    public void updateSelected_TipoMilitar_toEdit(TipoMilitar tipomilitar) {
        tipoMilitarSelected = tipomilitar;
        nombre = tipomilitar.getNombreTipo();
        nombre1 = tipomilitar.getNombreTipo();
        cancelado = tipomilitar.getCanceladoTipoMilitar();
    }

    public void addTipoMilitar() {
        Par submit = serv.addTipoMilitar(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addTipoMilitarDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tipoMilitar");
    }

    public void editTipoMilitar() {
        Par submit = serv.editTipoMilitar(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editTipoMilitarDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tipoMilitar");
    }

    public void deleteTipoMilitar() {
//        Par submit = serv.deleteTipoMilitar(nombre1);
//        FacesContext context = FacesContext.getCurrentInstance();
//        init();
//        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }

    public String translateEstado(boolean estado) {
        if (estado) {
            return "Cancelado";
        }
        return "Activo";
    }

    public String translateBooleanToSeverity(boolean element) {
        if (!element) {
            return "badge badge-success";
        }
        return "badge badge-danger";
    }

    public boolean isCancelado() {
        return !cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = !cancelado;
    }

}
