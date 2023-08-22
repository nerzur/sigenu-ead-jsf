package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.TipoAsignatura;
import cu.edu.unah.sigenuead.service.TipoAsignaturaServices;
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
public class AdminTipoAsignaturaBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<TipoAsignatura> tipoAsignaturaList = new ArrayList<TipoAsignatura>();
    private TipoAsignatura tipoAsignaturaSelected;
    private TipoAsignaturaServices serv = new TipoAsignaturaServices();

    public void init() {
        tipoAsignaturaList.clear();
        tipoAsignaturaList = serv.findAllTipoAsignatura();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_TipoAsignatura(TipoAsignatura tipoasignatura) {
        this.setTipoAsignaturaSelected(tipoasignatura);
        cleanVariables();
    }

    public void updateSelected_TipoAsignatura_toDelete(TipoAsignatura tipoasignatura) {
        this.setTipoAsignaturaSelected(tipoasignatura);
        nombre1 = tipoasignatura.getTipoAsignaturaNombre();
    }

    public void updateSelected_TipoAsignatura_toEdit(TipoAsignatura tipoasignatura) {
        tipoAsignaturaSelected = tipoasignatura;
        nombre = tipoasignatura.getTipoAsignaturaNombre();
        nombre1 = tipoasignatura.getTipoAsignaturaNombre();
        cancelado = tipoasignatura.getTipoAsignaturaCancelado();
    }

    public void addTipoAsignatura() {
        Par submit = serv.addTipoAsignatura(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addTipoAsignaturaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tipoAsignatura");
    }

    public void editTipoAsignatura() {
        Par submit = serv.editTipoAsignatura(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editTipoAsignaturaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tipoAsignatura");
    }

    public void deleteTipoAsignatura() {
//        Par submit = serv.deleteTipoAsignatura(nombre1);
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
