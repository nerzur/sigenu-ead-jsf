package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Organismo;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.OrganismoServices;
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
public class AdminOrganismoBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<Organismo> organismoList = new ArrayList<Organismo>();
    private Organismo organismoSelected;
    private OrganismoServices serv = new OrganismoServices();

    public void init() {
        organismoList.clear();
        organismoList = serv.findAllOrganismo();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_Organismo(Organismo organismo) {
        this.setOrganismoSelected(organismo);
        cleanVariables();
    }

    public void updateSelected_Organismo_toDelete(Organismo organismo) {
        this.setOrganismoSelected(organismo);
        nombre1 = organismo.getNombreorganismo();
    }

    public void updateSelected_Organismo_toEdit(Organismo organismo) {
        organismoSelected = organismo;
        nombre = organismo.getNombreorganismo();
        nombre1 = organismo.getNombreorganismo();
        cancelado = organismo.getCanceladoorganismo();
    }

    public void addOrganismo() {
        Par submit = serv.AddOrganismo(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addOrganismoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-organismo");
    }

    public void editOrganismo() {
        Par submit = serv.EditOrganismo(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editOrganismoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-organismo");
    }

    public void deleteOrganismo() {
//        Par submit = serv.DeleteOrganismo(nombre1);
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
