package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.OrganizacionPolitica;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.OrganizacionPoliticaServices;
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
public class AdminOrganizacionPoliticaBean implements Serializable {

    private String nombre;
    private String nombre1;
    private boolean cancelado = true;
    private List<OrganizacionPolitica> organizacionPoliticaList = new ArrayList<OrganizacionPolitica>();
    private OrganizacionPolitica organizacionPoliticaSelected;
    private OrganizacionPoliticaServices serv = new OrganizacionPoliticaServices();

    public void init() {
        organizacionPoliticaList.clear();
        organizacionPoliticaList = serv.findAllOrganizacionPolitica();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCancelado(true);
    }

    public void updateSelected_OrganizacionPolitica(OrganizacionPolitica organizacionpolitica) {
        this.setOrganizacionPoliticaSelected(organizacionpolitica);
        cleanVariables();
    }

    public void updateSelected_OrganizacionPolitica_toDelete(OrganizacionPolitica organizacionpolitica) {
        this.setOrganizacionPoliticaSelected(organizacionpolitica);
        nombre1 = organizacionpolitica.getOrganizacionPoliticaNombre();
    }

    public void updateSelected_OrganizacionPolitica_toEdit(OrganizacionPolitica organizacionpolitica) {
        organizacionPoliticaSelected = organizacionpolitica;
        nombre = organizacionpolitica.getOrganizacionPoliticaNombre();
        nombre1 = organizacionpolitica.getOrganizacionPoliticaNombre();
        cancelado = organizacionpolitica.getOrganizacionPoliticaCancelado();
    }

    public void addOrganizacionPolitica() {
        Par submit = serv.addOrganizacionPolitica(nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addOrganizacionPoliticaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-organizacionPolitica");
    }

    public void editOrganizacionPolitica() {
        Par submit = serv.editOrganizacionPolitica(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editOrganizacionPoliticaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-organizacionPolitica");
    }

    public void deleteOrganizacionPolitica() {
//        Par submit = serv.deleteOrganizacionPolitica(nombre1);
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
