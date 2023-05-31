/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.EstadoCivil;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.EstadoCivilServices;
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
public class AdminEstadoCivilBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<EstadoCivil> lista_estadociviles = new ArrayList<EstadoCivil>();
    private EstadoCivil estadocivil_selected;
    private EstadoCivilServices serv = new EstadoCivilServices();

    public void init() {
        lista_estadociviles.clear();
        lista_estadociviles = serv.findAllEstadoCivil();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_EstadoCivil(EstadoCivil estadocivil) {
        this.setEstadocivil_selected(estadocivil);
        cleanVariables();
    }

    public void updateSelected_EstadoCivil_toDelete(EstadoCivil estadocivil) {
        this.setEstadocivil_selected(estadocivil);
        nombre1 = estadocivil.getEstadoCivilNombre();
    }

    public void updateSelected_EstadoCivil_toEdit(EstadoCivil estadocivil) {
        estadocivil_selected = estadocivil;
        nombre = estadocivil.getEstadoCivilNombre();
        nombre1 = estadocivil.getEstadoCivilNombre();
        cancelado = estadocivil.getEstadoCivilCancelado();
    }

    public void addEstadoCivil() {
        Par submit = serv.addEstadoCivil(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addEstadoCivilDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-estadoCivil");
    }

    public void editEstadoCivil() {
        Par submit = serv.editEstadoCivil(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editEstadoCivilDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-estadoCivil");
    }

    public void deleteEstadoCivil() {
//        Par submit = serv.deleteEstadoCivil(nombre1);
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
