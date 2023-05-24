/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Tipoplanestudio;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.TipoplanestudioServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nerzur
 */
@Named
@SessionScoped
@Data
public class AdminTipoPlanEstudioBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<Tipoplanestudio> lista_tipoplanestudioes = new ArrayList<Tipoplanestudio>();
    private Tipoplanestudio tipoplanestudio_selected;
    private final TipoplanestudioServices serv = new TipoplanestudioServices();

    public void init() {
        lista_tipoplanestudioes.clear();
        lista_tipoplanestudioes = serv.findAllTipoplanestudio();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_Tipoplanestudio(Tipoplanestudio tipoplanestudio) {
        this.setTipoplanestudio_selected(tipoplanestudio);
        cleanVariables();
    }

    public void updateSelected_Tipoplanestudio_toDelete(Tipoplanestudio tipoplanestudio) {
        this.setTipoplanestudio_selected(tipoplanestudio);
        nombre1 = tipoplanestudio.getNombretipoplanestudio();
    }

    public void updateSelected_Tipoplanestudio_toEdit(Tipoplanestudio tipoplanestudio) {
        tipoplanestudio_selected = tipoplanestudio;
        nombre = tipoplanestudio.getNombretipoplanestudio();
        nombre1 = tipoplanestudio.getNombretipoplanestudio();
        cancelado = tipoplanestudio.getTipoplanestudiocancelado();
    }

    public void addTipoplanestudio() {
        Par submit = serv.addTipoplanestudio(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addTipoPlanEstudioDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tipoPlanEstudio");
    }

    public void editTipoplanestudio() {
        Par submit = serv.editTipoplanestudio(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editTipoPlanEstudioDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-tipoPlanEstudio");
    }

    public void deleteTipoplanestudio() {
//        Par submit = serv.deleteTipoplanestudio(nombre1);
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
