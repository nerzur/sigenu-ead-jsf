package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Sindicato;
import cu.edu.unah.sigenuead.service.SindicatoServices;
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
public class AdminSindicatoBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<Sindicato> sindicatoList = new ArrayList<Sindicato>();
    private Sindicato sindicatoSelected;
    private SindicatoServices serv = new SindicatoServices();

    public void init() {
        sindicatoList.clear();
        sindicatoList = serv.findAllSindicato();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_Sindicato(Sindicato sindicato) {
        this.setSindicatoSelected(sindicato);
        cleanVariables();
    }

    public void updateSelected_Sindicato_toDelete(Sindicato sindicato) {
        this.setSindicatoSelected(sindicato);
        nombre1 = sindicato.getNombresindicato();
    }

    public void updateSelected_Sindicato_toEdit(Sindicato sindicato) {
        sindicatoSelected = sindicato;
        nombre = sindicato.getNombresindicato();
        nombre1 = sindicato.getNombresindicato();
        cancelado = sindicato.getCanceladosindicato();
    }

    public void addSindicato() {
        Par submit = serv.AddSindicato(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addSindicatoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-sindicato");
    }

    public void editSindicato() {
        Par submit = serv.EditSindicato(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editSindicatoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-sindicato");
    }

    public void deleteSindicato() {
//        Par submit = serv.DeleteSindicato(nombre1);
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
