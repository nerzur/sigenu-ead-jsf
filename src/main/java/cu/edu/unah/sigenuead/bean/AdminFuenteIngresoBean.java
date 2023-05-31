/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.FuenteIngreso;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.FuenteIngresoServices;
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
public class AdminFuenteIngresoBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<FuenteIngreso> lista_fuenteingresoes = new ArrayList<FuenteIngreso>();
    private FuenteIngreso fuenteingreso_selected;
    private FuenteIngresoServices serv = new FuenteIngresoServices();

    public void init() {
        lista_fuenteingresoes.clear();
        lista_fuenteingresoes = serv.findAllFuenteIngreso();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_FuenteIngreso(FuenteIngreso fuenteingreso) {
        this.setFuenteingreso_selected(fuenteingreso);
        cleanVariables();
    }

    public void updateSelected_FuenteIngreso_toDelete(FuenteIngreso fuenteingreso) {
        this.setFuenteingreso_selected(fuenteingreso);
        nombre1 = fuenteingreso.getFuenteIngresoNombre();
    }

    public void updateSelected_FuenteIngreso_toEdit(FuenteIngreso fuenteingreso) {
        fuenteingreso_selected = fuenteingreso;
        nombre = fuenteingreso.getFuenteIngresoNombre();
        nombre1 = fuenteingreso.getFuenteIngresoNombre();
        cancelado = fuenteingreso.getFuenteIngresoCancelado();
    }

    public void addFuenteIngreso() {
        Par submit = serv.addFuenteIngreso(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addFuenteIngresoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-fuentesDeIngreso");
    }

    public void editFuenteIngreso() {
        Par submit = serv.editFuenteIngreso(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));

        PrimeFaces.current().executeScript("PF('editFuenteIngresoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-fuentesDeIngreso");
    }

    public void deleteFuenteIngreso() {
//        Par submit = serv.deleteFuenteIngreso(nombre1);
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
