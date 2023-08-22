package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.NivelEscolar;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.NivelEscolarServices;
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
public class AdminNivelEscolarBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<NivelEscolar> nivelEscolarList = new ArrayList<NivelEscolar>();
    private NivelEscolar nivelEscolarSelected;
    private NivelEscolarServices serv = new NivelEscolarServices();

    public void init() {
        nivelEscolarList.clear();
        nivelEscolarList = serv.findAllNivelEscolar();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(false);
    }

    public void updateSelected_NivelEscolar(NivelEscolar nivelescolar) {
        this.setNivelEscolarSelected(nivelescolar);
        cleanVariables();
    }

    public void updateSelected_NivelEscolar_toDelete(NivelEscolar nivelescolar) {
        this.setNivelEscolarSelected(nivelescolar);
        nombre1 = nivelescolar.getNivelEscolarNombre();
    }

    public void updateSelected_NivelEscolar_toEdit(NivelEscolar nivelescolar) {
        nivelEscolarSelected = nivelescolar;
        nombre = nivelescolar.getNivelEscolarNombre();
        nombre1 = nivelescolar.getNivelEscolarNombre();
        cancelado = nivelescolar.getNivelEscolarCancelado();
    }

    public void addNivelEscolar() {
        Par submit = serv.addNivelEscolar(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addNivelEscolarDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-nivelEscolar");
    }

    public void editNivelEscolar() {
        Par submit = serv.editNivelEscolar(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editNivelEscolarDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-nivelEscolar");
    }

    public void deleteNivelEscolar() {
//        Par submit = serv.deleteNivelEscolar(nombre1);
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
