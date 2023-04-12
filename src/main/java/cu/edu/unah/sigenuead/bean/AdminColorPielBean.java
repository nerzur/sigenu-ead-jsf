package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.ColorPiel;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.ColorPielServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
@Data
public class AdminColorPielBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado =true;
    private List<ColorPiel> colorPielList = new ArrayList<ColorPiel>();
    private ColorPiel colorPielSelected;
    private ColorPielServices serv = new ColorPielServices();

    public void init() {
        colorPielList.clear();
        colorPielList = serv.findAllColorPiel();
        cleanVariables();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_ColorPiel(ColorPiel color_piel) {
        this.setColorPielSelected(color_piel);
        cleanVariables();
    }

    public void updateSelected_ColorPiel_toDelete(ColorPiel colorPiel) {
        this.setColorPielSelected(colorPiel);
        nombre1 = colorPiel.getColorPielNombre();
    }

    public void updateSelected_ColorPiel_toEdit(ColorPiel color_piel) {
        colorPielSelected = color_piel;
        nombre = color_piel.getColorPielNombre();
        nombre1 = color_piel.getColorPielNombre();
        cancelado = color_piel.getColorPielCancelado();
    }

    public void addColorPiel() {
        Par submit = serv.AddColorPiel(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addColorPielDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-colorPiel");
    }

    public void editColorPiel() {
        Par submit = serv.EditColorPiel(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editColorPielDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-colorPiel");
    }

    public void deleteColorPiel() {
//        Par submit = serv.DeleteColorPiel(nombre1);
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
