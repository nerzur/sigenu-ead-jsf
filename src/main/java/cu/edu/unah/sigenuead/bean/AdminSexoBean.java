package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Sexo;
import cu.edu.unah.sigenuead.service.SexoServices;
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
public class AdminSexoBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<Sexo> sexoList = new ArrayList<Sexo>();
    private Sexo sexoSelected;
    private SexoServices serv = new SexoServices();

    public void init() {
        sexoList.clear();
        sexoList = serv.findAllSexo();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_Sexo(Sexo sexo) {
        this.setSexoSelected(sexo);
        cleanVariables();
    }

    public void updateSelected_Sexo_toDelete(Sexo sexo) {
        this.setSexoSelected(sexo);
        nombre1 = sexo.getSexoNombre();
    }

    public void updateSelected_Sexo_toEdit(Sexo sexo) {
        sexoSelected = sexo;
        nombre = sexo.getSexoNombre();
        nombre1 = sexo.getSexoNombre();
        cancelado = sexo.getSexoCancelado();
    }

    public void addSexo() {
        Par submit = serv.addSexo(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addSexoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-sexo");
    }

    public void editSexo() {
        Par submit = serv.editSexo(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editSexoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-sexo");
    }

    public void deleteSexo() {
//        Par submit = serv.deleteSexo(nombre1);
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
