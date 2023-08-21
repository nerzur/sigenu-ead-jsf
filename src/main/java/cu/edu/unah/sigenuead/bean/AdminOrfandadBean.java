package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Huerfano;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.HuerfanoServices;
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
public class AdminOrfandadBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<Huerfano> huerfanoList = new ArrayList<Huerfano>();
    private Huerfano huerfanoSelected;
    private HuerfanoServices serv = new HuerfanoServices();

    public void init() {
        huerfanoList.clear();
        huerfanoList = serv.findAllHuerfano();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_Huerfano(Huerfano huerfano) {
        this.setHuerfanoSelected(huerfano);
        cleanVariables();
    }

    public void updateSelected_Huerfano_toDelete(Huerfano huerfano) {
        this.setHuerfanoSelected(huerfano);
        nombre1 = huerfano.getHuerfanoNombre();
    }

    public void updateSelected_Huerfano_toEdit(Huerfano huerfano) {
        huerfanoSelected = huerfano;
        nombre = huerfano.getHuerfanoNombre();
        nombre1 = huerfano.getHuerfanoNombre();
        cancelado = huerfano.getHuerfanoCancelado();
    }

    public void addHuerfano() {
        Par submit = serv.addHuerfano(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addOrfandadDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-orfandad");
    }

    public void editHuerfano() {
        Par submit = serv.editHuerfano(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editOrfandadDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-orfandad");
    }

    public void deleteHuerfano() {
//        Par submit = serv.deleteHuerfano(nombre1);
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
