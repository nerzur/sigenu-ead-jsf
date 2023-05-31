/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.EspecialidadMilitar;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.EspecialidadMilitarServices;
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
public class AdminEspecialidadMilitarBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<EspecialidadMilitar> lista_especialidadmilitares = new ArrayList<EspecialidadMilitar>();
    private EspecialidadMilitar especialidadmilitar_selected;
    private EspecialidadMilitarServices serv = new EspecialidadMilitarServices();

    public void init() {
        lista_especialidadmilitares.clear();
        lista_especialidadmilitares = serv.findAllEspecialidadMilitar();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_EspecialidadMilitar(EspecialidadMilitar especialidadmilitar) {
        this.setEspecialidadmilitar_selected(especialidadmilitar);
        cleanVariables();
    }

    public void updateSelected_EspecialidadMilitar_toDelete(EspecialidadMilitar especialidadmilitar) {
        this.setEspecialidadmilitar_selected(especialidadmilitar_selected);
        nombre1 = especialidadmilitar.getEspecialidadMilitarNombre();
    }

    public void updateSelected_EspecialidadMilitar_toEdit(EspecialidadMilitar especialidadmilitar) {
        especialidadmilitar_selected = especialidadmilitar;
        nombre = especialidadmilitar.getEspecialidadMilitarNombre();
        nombre1 = especialidadmilitar.getEspecialidadMilitarNombre();
        cancelado = especialidadmilitar.getEspecialidadMilitarCancelado();
    }

    public void addEspecialidadMilitar() {
        Par submit = serv.addEspecialidadMilitar(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addEspecialidadMilitarDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-especialidadMilitar");
    }

    public void editEspecialidadMilitar() {
        Par submit = serv.editEspecialidadMilitar(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editEspecialidadMilitarDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-especialidadMilitar");
    }

    public void deleteEspecialidadMilitar() {
//        Par submit = serv.deletePais(nombre1);
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
