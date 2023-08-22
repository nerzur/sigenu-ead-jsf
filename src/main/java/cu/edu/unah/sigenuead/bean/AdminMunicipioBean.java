package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Municipio;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.MunicipioServices;
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
public class AdminMunicipioBean implements Serializable {

    private String nombre;
    private String nombreprovincia;
    private String nombrepais;
    private String nombre1;
    private String nombreprovincia1;
    private String nombrepais1;
    private boolean cancelado = true;
    private boolean isCuba;
    private List<Municipio> municipioList = new ArrayList<Municipio>();
    private Municipio municipioSelected;
    final private MunicipioServices serv = new MunicipioServices();
    final private ProvinciaServices serv_provincia = new ProvinciaServices();
    final private PaisServices serv_pais = new PaisServices();
    private List<String> paisList = new ArrayList<String>();
    private List<String> provinciaList = new ArrayList<String>();

    public void init() {
        municipioList.clear();
        paisList.clear();
        municipioList = serv.findAllMunicipio();
        paisList = serv_pais.findPaisAvailable();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setNombrepais("Cuba");
        this.setCancelado(true);

    }

    public void updateSelected_Municipio(Municipio municipio) {
        this.setMunicipioSelected(municipio);
        cleanVariables();
    }

    public void updateSelected_Municipio_toDelete(Municipio municipio) {
        this.setMunicipioSelected(municipioSelected);
        nombre1 = municipio.getNombremunicipio();
        nombreprovincia1 = municipio.getProvinciaidprovincia().getNombreprovincia();
        nombrepais1 = municipio.getProvinciaidprovincia().getPaisidpais().getNombrepais();
    }

    public void updateSelected_Municipio_toEdit(Municipio municipio) {
        municipioSelected = municipio;
        nombre = municipio.getNombremunicipio();
        nombre1 = municipio.getNombremunicipio();
        nombrepais = municipio.getProvinciaidprovincia().getPaisidpais().getNombrepais();
        nombrepais1 = municipio.getProvinciaidprovincia().getPaisidpais().getNombrepais();
        nombreprovincia = municipio.getProvinciaidprovincia().getNombreprovincia();
        nombreprovincia1 = municipio.getProvinciaidprovincia().getNombreprovincia();
        cancelado = municipio.getCanceladomunicipio();
    }

    public void addMunicipio() {
        Par submit = serv.addMunicipio(nombre, nombreprovincia, nombrepais, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addMunicipioDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-municipio");
    }

    public void editMunicipio() {
        Par submit = serv.editMunicipio(nombre1, nombre, nombreprovincia1, nombreprovincia, nombrepais1, nombrepais, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editMunicipioDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-municipio");
    }

    public void deleteMunicipio() {
//        Par submit = serv.deleteMunicipio(nombre1, nombreprovincia1, nombrepais1);
//        FacesContext context = FacesContext.getCurrentInstance();
//        init();
//        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }

    public void updateProvincias() {
        if (nombrepais != null && !nombrepais.equals("")) {
            provinciaList.clear();
            provinciaList = serv_provincia.findProvinciaAvailable(nombrepais);
            isCuba = nombrepais.equals("Cuba");
        }
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
