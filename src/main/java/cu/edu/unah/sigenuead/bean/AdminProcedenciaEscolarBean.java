package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.ProcedenciaEscolar;
import cu.edu.unah.sigenuead.service.ProcedenciaEscolarServices;
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
public class AdminProcedenciaEscolarBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<ProcedenciaEscolar> procedenciaEscolarList = new ArrayList<ProcedenciaEscolar>();
    private ProcedenciaEscolar procedenciaEscolarSelected;
    private ProcedenciaEscolarServices serv = new ProcedenciaEscolarServices();

    public void init() {
        procedenciaEscolarList.clear();
        procedenciaEscolarList = serv.findAllProcedenciaEscolar();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_ProcedenciaEscolar(ProcedenciaEscolar procedenciaescolar) {
        this.setProcedenciaEscolarSelected(procedenciaescolar);
        cleanVariables();
    }

    public void updateSelected_ProcedenciaEscolar_toDelete(ProcedenciaEscolar procedenciaescolar) {
        this.setProcedenciaEscolarSelected(procedenciaescolar);
        nombre1 = procedenciaescolar.getProcedenciaEscolarNombre();
    }

    public void updateSelected_ProcedenciaEscolar_toEdit(ProcedenciaEscolar procedenciaescolar) {
        procedenciaEscolarSelected = procedenciaescolar;
        nombre = procedenciaescolar.getProcedenciaEscolarNombre();
        nombre1 = procedenciaescolar.getProcedenciaEscolarNombre();
        cancelado = procedenciaescolar.getProcedenciaEscolarCancelado();
    }

    public void addProcedenciaEscolar() {
        Par submit = serv.addProcedenciaEscolar(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addProcedenciaEscolarDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-procedenciaEscolar");
    }

    public void editProcedenciaEscolar() {
        Par submit = serv.editProcedenciaEscolar(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editProcedenciaEscolarDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-procedenciaEscolar");
    }

    public void deleteProcedenciaEscolar() {
//        Par submit = serv.deleteProcedenciaEscolar(nombre1);
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
