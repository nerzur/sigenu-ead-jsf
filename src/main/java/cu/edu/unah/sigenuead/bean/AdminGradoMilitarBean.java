/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.GradoMilitar;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.GradoMilitarServices;
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
public class AdminGradoMilitarBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<GradoMilitar> lista_gradomilitares = new ArrayList<GradoMilitar>();
    private GradoMilitar gradomilitar_selected;
    private GradoMilitarServices serv = new GradoMilitarServices();

    public void init() {
        lista_gradomilitares.clear();
        lista_gradomilitares = serv.findAllGradoMilitar();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_GradoMilitar(GradoMilitar gradomilitar) {
        this.setGradomilitar_selected(gradomilitar);
        cleanVariables();
    }

    public void updateSelected_GradoMilitar_toDelete(GradoMilitar gradomilitar) {
        this.setGradomilitar_selected(gradomilitar);
        nombre1 = gradomilitar.getGradoMilitarNombre();
    }

    public void updateSelected_GradoMilitar_toEdit(GradoMilitar gradomilitar) {
        gradomilitar_selected = gradomilitar;
        nombre = gradomilitar.getGradoMilitarNombre();
        nombre1 = gradomilitar.getGradoMilitarNombre();
        cancelado = gradomilitar.getGradoMilitarCancelado();
    }

    public void addGradoMilitar() {
        Par submit = serv.addGradoMilitar(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addGradoMilitarDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-gradoMilitar");
    }

    public void editGradoMilitar() {
        Par submit = serv.editGradoMilitar(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editGradoMilitarDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-gradoMilitar");
    }

    public void deleteGradoMilitar() {
//        Par submit = serv.deleteGradoMilitar(nombre1);
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
