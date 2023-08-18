package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.OrganizacionPopular;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.OrganizacionPopularServices;
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
public class AdminOrganizacionPopularBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<OrganizacionPopular> organizacionPopularList = new ArrayList<OrganizacionPopular>();
    private OrganizacionPopular organizacionPopularSelected;
    private OrganizacionPopularServices serv = new OrganizacionPopularServices();

    public void init() {
        organizacionPopularList.clear();
        organizacionPopularList = serv.findAllOrganizacionPopular();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_OrganizacionPopular(OrganizacionPopular organizacionpopular) {
        this.setOrganizacionPopularSelected(organizacionpopular);
        cleanVariables();
    }

    public void updateSelected_OrganizacionPopular_toDelete(OrganizacionPopular organizacionpopular) {
        this.setOrganizacionPopularSelected(organizacionpopular);
        nombre1 = organizacionpopular.getOrganizacionPopularNombre();
    }

    public void updateSelected_OrganizacionPopular_toEdit(OrganizacionPopular organizacionpopular) {
        organizacionPopularSelected = organizacionpopular;
        nombre = organizacionpopular.getOrganizacionPopularNombre();
        nombre1 = organizacionpopular.getOrganizacionPopularNombre();
        cancelado = organizacionpopular.getOrganizacionPopularCancelado();
    }

    public void addOrganizacionPopular() {
        Par submit = serv.addOrganizacionPopular(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addOrganizacionPopularDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-organizacionPopular");
    }

    public void editOrganizacionPopular() {
        Par submit = serv.editOrganizacionPopular(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editOrganizacionPopularDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-organizacionPopular");
    }

    public void deleteOrganizacionPopular() {
//        Par submit = serv.deleteOrganizacionPopular(nombre1);
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
