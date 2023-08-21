package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Ong;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.OngServices;
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
public class AdminOngBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<Ong> ongList = new ArrayList<Ong>();
    private Ong ongSelected;
    private OngServices serv = new OngServices();

    public void init() {
        ongList.clear();
        ongList = serv.findAllOng();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_Ong(Ong ong) {
        this.setOngSelected(ong);
        cleanVariables();
    }

    public void updateSelected_Ong_toDelete(Ong ong) {
        this.setOngSelected(ong);
        nombre1 = ong.getOngNombre();
    }

    public void updateSelected_Ong_toEdit(Ong ong) {
        ongSelected = ong;
        nombre = ong.getOngNombre();
        nombre1 = ong.getOngNombre();
        cancelado = ong.getOngCancelado();
    }

    public void addOng() {
        Par submit = serv.addOng(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addOngDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-ong");
    }

    public void editOng() {
        Par submit = serv.editOng(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editOngDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-ong");
    }

    public void deleteOng() {
//        Par submit = serv.deleteOng(nombre1);
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
