package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Minusvalia;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.MinusvaliaServices;
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
public class AdminMinusvaliaBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<Minusvalia> minusvaliaList = new ArrayList<Minusvalia>();
    private Minusvalia minusvaliaSelected;
    private MinusvaliaServices serv = new MinusvaliaServices();

    public void init() {
        minusvaliaList.clear();
        minusvaliaList = serv.findAllMinusvalia();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_Minusvalia(Minusvalia minusvalia) {
        this.setMinusvaliaSelected(minusvalia);
        cleanVariables();
    }

    public void updateSelected_Minusvalia_toDelete(Minusvalia minusvalia) {
        this.setMinusvaliaSelected(minusvalia);
        nombre1 = minusvalia.getMinusvaliaNombre();
    }

    public void updateSelected_Minusvalia_toEdit(Minusvalia minusvalia) {
        minusvaliaSelected = minusvalia;
        nombre = minusvalia.getMinusvaliaNombre();
        nombre1 = minusvalia.getMinusvaliaNombre();
        cancelado = minusvalia.getMinusvaliaCancelado();
    }

    public void addMinusvalia() {
        Par submit = serv.addMinusvalia(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addMinusvaliaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-minusvalia");
    }

    public void editMinusvalia() {
        Par submit = serv.editMinusvalia(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editMinusvaliaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-minusvalia");
    }

    public void deleteMinusvalia() {
//        Par submit = serv.deleteMinusvalia(nombre1);
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
