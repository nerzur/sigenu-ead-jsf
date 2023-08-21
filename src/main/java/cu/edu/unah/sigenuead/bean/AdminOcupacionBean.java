package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Ocupacion;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.OcupacionServices;
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
public class AdminOcupacionBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<Ocupacion> ocupacionList = new ArrayList<Ocupacion>();
    private Ocupacion ocupacionSelected;
    private OcupacionServices serv = new OcupacionServices();

    public void init() {
        ocupacionList.clear();
        ocupacionList = serv.findAllOcupacion();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_Ocupacion(Ocupacion ocupacion) {
        this.setOcupacionSelected(ocupacion);
        cleanVariables();
    }

    public void updateSelected_Ocupacion_toDelete(Ocupacion ocupacion) {
        this.setOcupacionSelected(ocupacion);
        nombre1 = ocupacion.getOcupacionNombre();
    }

    public void updateSelected_Ocupacion_toEdit(Ocupacion ocupacion) {
        ocupacionSelected = ocupacion;
        nombre = ocupacion.getOcupacionNombre();
        nombre1 = ocupacion.getOcupacionNombre();
        cancelado = ocupacion.getOcupacionCancelado();
    }

    public void addOcupacion() {
        Par submit = serv.addOcupacion(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addOcupacionDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-ocupacion");
    }

    public void editOcupacion() {
        Par submit = serv.editOcupacion(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editOcupacionDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-ocupacion");
    }

    public void deleteOcupacion() {
//        Par submit = serv.deleteOcupacion(nombre1);
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
