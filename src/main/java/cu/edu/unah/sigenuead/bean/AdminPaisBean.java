package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Pais;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.PaisServices;
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
public class AdminPaisBean implements Serializable {

    private String nombre;
    private static String nombre1;
    private boolean cancelado = true;
    private List<Pais> paisList = new ArrayList<Pais>();
    private Pais paisSelected;
    private PaisServices serv = new PaisServices();

    public void init() {
        paisList.clear();
        paisList = serv.findAllPais();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(false);
    }

    public void updateSelected_Pais(Pais pais) {
        this.setPaisSelected(pais);
        cleanVariables();
    }

    public void updateSelected_Pais_toDelete(Pais pais) {
        this.setPaisSelected(paisSelected);
        nombre1 = pais.getNombrepais();
    }

    public void updateSelected_Pais_toEdit(Pais pais) {
        paisSelected = pais;
        nombre = pais.getNombrepais();
        nombre1 = pais.getNombrepais();
        cancelado = pais.getCanceladopais();
    }

    public void addPais() {
        Par submit = serv.addPais(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addPaisDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-pais");
    }

    public void editPais() {
        Par submit = serv.editPais(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editPaisDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-pais");
    }

    public void deletePais() {
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
