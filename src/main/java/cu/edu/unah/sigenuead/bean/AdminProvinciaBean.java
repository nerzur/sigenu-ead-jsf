package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Pais;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Provincia;
import cu.edu.unah.sigenuead.service.PaisServices;
import cu.edu.unah.sigenuead.service.ProvinciaServices;
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
public class AdminProvinciaBean implements Serializable {

    private String nombre;
    private String nombrepais;
    private String nombre1;
    private String nombrepais1;
    private boolean cancelado = true;
    
    private List<Provincia> provinciaList = new ArrayList<Provincia>();
    private List<Pais> paisList = new ArrayList<Pais>();

    private Provincia provinciaSelected;
    
    final private ProvinciaServices serv = new ProvinciaServices();
    final private PaisServices serv_pais = new PaisServices();
    

    public void init() {
        provinciaList.clear();
        paisList.clear();
        provinciaList = serv.findAllProvincia();
        paisList = serv_pais.findAllPais();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setNombrepais("");
        this.setCancelado(true);
    }

    public void updateSelected_Provincia(Provincia provincia) {
        this.setProvinciaSelected(provincia);
        cleanVariables();
    }

    public void updateSelected_Provincia_toDelete(Provincia provincia) {
        this.setProvinciaSelected(provinciaSelected);
        nombre1 = provincia.getNombreprovincia();
        nombrepais1 = provincia.getPaisidpais().getNombrepais();
    }

    public void updateSelected_Provincia_toEdit(Provincia provincia) {
        provinciaSelected = provincia;
        nombre = provincia.getNombreprovincia();
        nombrepais = provincia.getPaisidpais().getNombrepais();
        nombrepais1 = provincia.getPaisidpais().getNombrepais();
        nombre1 = provincia.getNombreprovincia();
        cancelado = provincia.getCanceladoprovincia();
    }

    public void addProvincia() {
        Par submit = serv.addProvincia(nombre, nombrepais, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addProvinciaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-Provincia");
    }

    public void editProvincia() {
        Par submit = serv.editProvincia(nombre1, nombre, cancelado, nombrepais1, nombrepais);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editProvinciaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-Provincia");
    }

    public void deleteProvincia() {
//        Par submit = serv.deleteProvincia(nombre1, nombrepais1);
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
