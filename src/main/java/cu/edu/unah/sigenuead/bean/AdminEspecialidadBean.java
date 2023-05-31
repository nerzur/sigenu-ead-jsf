/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Especialidad;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.EspecialidadServices;
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
public class AdminEspecialidadBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private  List<Especialidad> lista_especialidades = new ArrayList<Especialidad>();
    private Especialidad especialidad_selected;
    private EspecialidadServices serv = new EspecialidadServices();

    public void init() {
        lista_especialidades.clear();
        lista_especialidades = serv.findAllEspecialidad();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_Especialidad(Especialidad especialidad) {
        this.setEspecialidad_selected(especialidad);
        cleanVariables();
    }

    public void updateSelected_Especialidad_toDelete(Especialidad especialidad) {
        this.setEspecialidad_selected(especialidad);
        nombre1 = especialidad.getNombreespecialidad();
    }

    public void updateSelected_Especialidad_toEdit(Especialidad especialidad) {
        especialidad_selected = especialidad;
        nombre = especialidad.getNombreespecialidad();
        nombre1 = especialidad.getNombreespecialidad();
        cancelado = especialidad.getCanceladoespecialidad();
    }

    public void addEspecialidad() {
        Par submit = serv.addEspecialidad(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addEspecialidadDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-especialidad");
    }

    public void editEspecialidad() {
        Par submit = serv.editEspecialidad(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editEspecialidadDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-especialidad");
    }

    public void deleteEspecialidad() {
//        Par submit = serv.deleteEspecialidad(nombre1);
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
