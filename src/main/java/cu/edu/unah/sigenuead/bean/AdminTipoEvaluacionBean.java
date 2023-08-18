package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.TipoEvaluacion;
import cu.edu.unah.sigenuead.service.TipoEvaluacionServices;
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
public class AdminTipoEvaluacionBean implements Serializable {

    private String nombre;
    private static String nombre1;
    private boolean cancelado = true;
    private List<TipoEvaluacion> tipoEvaluacionList = new ArrayList<TipoEvaluacion>();
    private TipoEvaluacion tipoEvaluacionSelected;
    private TipoEvaluacionServices serv = new TipoEvaluacionServices();

    public void init() {
        tipoEvaluacionList.clear();
        tipoEvaluacionList = serv.findAllTipoEvaluacion();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_TipoEvaluacion(TipoEvaluacion tipoevaluacion) {
        this.setTipoEvaluacionSelected(tipoevaluacion);
        cleanVariables();
    }

    public void updateSelected_TipoEvaluacion_toDelete(TipoEvaluacion tipoevaluacion) {
        this.setTipoEvaluacionSelected(tipoevaluacion);
        nombre1 = tipoevaluacion.getTipoEvaluacionNombre();
    }

    public void updateSelected_TipoEvaluacion_toEdit(TipoEvaluacion tipoevaluacion) {
        tipoEvaluacionSelected = tipoevaluacion;
        nombre = tipoevaluacion.getTipoEvaluacionNombre();
        nombre1 = tipoevaluacion.getTipoEvaluacionNombre();
        cancelado = tipoevaluacion.getTipoEavluacionCancelado();
    }

    public void addTipoEvaluacion() {
        Par submit = serv.addTipoEvaluacion(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addTipoEvaluacionDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tipoEvaluacion");    }

    public void editTipoEvaluacion() {
        Par submit = serv.editTipoEvaluacion(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editTipoEvaluacionDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tipoEvaluacion");
    }

    public void deleteTipoEvaluacion() {
//        Par submit = serv.deleteTipoEvaluacion(nombre1);
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
