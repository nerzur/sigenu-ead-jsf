package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Facultad;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.FacultadServices;
import cu.edu.unah.sigenuead.service.MunicipioServices;
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
public class AdminFacultadBean implements Serializable {

    private String nombre;
    private String nombre1;
    private String telefono;
    private String nombredecano;
    private String nombresecgen;
    private String codigo;
    private String direccion;
    private String municipio;
    private String provincia;
    private String codigo1;
    private boolean cancelado = true;
    private List<Facultad> facultadList = new ArrayList<Facultad>();
    private List<String> provincias = new ArrayList<String>();
    private List<String> municipios = new ArrayList<String>();
    private Facultad facultadSelected;
    final private FacultadServices serv = new FacultadServices();
    final private ProvinciaServices serv_provincia = new ProvinciaServices();
    final private MunicipioServices serv_municipios = new MunicipioServices();

    public void init() {
        facultadList.clear();
        facultadList = serv.findAllFacultad();
    }

    public void cleanVariables() {
        this.setCodigo("");
        this.setNombre("");
        this.setTelefono("");
        this.setNombresecgen("");
        this.setDireccion("");
        this.setMunicipio("");
        this.setProvincia("");
        this.setCancelado(true);
        codigo1 = "";
    }

    public void updateSelected_Facultad(Facultad facultad) {
        this.setFacultadSelected(facultad);
        cleanVariables();
    }

    public void updateSelected_Facultad_toDelete(Facultad facultad) {
        this.setFacultadSelected(facultadSelected);
        nombre1 = facultad.getCodigoarea();
    }

    public void updateSelected_Facultad_toEdit(Facultad facultad) {
        facultadSelected = facultad;
        nombre = facultad.getNombrearea();
        nombre1 = facultad.getNombrearea();
        codigo = facultad.getCodigoarea();
        codigo1 = facultad.getCodigoarea();
        telefono = facultad.getTelefonoarea();
        nombredecano = facultad.getNombredecanoarea();
        nombresecgen = facultad.getNombresecdocarea();
        direccion = facultad.getDireccionarea();
        municipio = facultad.getMunicipioidmunicipio().getNombremunicipio();
        provincia = facultad.getMunicipioidmunicipio().getProvinciaidprovincia().getNombreprovincia();
        cancelado = facultad.getCanceladoarea();
    }

    public void addFacultad() {
        Par submit = serv.addFacultad(codigo, nombre, nombresecgen, nombredecano, telefono, direccion, municipio, provincia, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addFacultadDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-facultad");
    }

    public void editFacultad() {
        Par submit = serv.editFacultad(codigo1, nombre, nombresecgen, nombredecano, telefono, direccion, municipio, provincia, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editFacultadDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-facultad");
    }

    public void deleteFacultad() {
//        Par submit = serv.deleteFacultad(codigo);
//        FacesContext context = FacesContext.getCurrentInstance();
//        init();
//        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }

    public void updateProvincias() {
        provincias.clear();
        provincias = serv_provincia.findProvinciaAvailable("Cuba");
        if (provincias == null) {
            provincias = new ArrayList<String>();
        }

    }

    public void updateMunicipios() {
        municipios.clear();
        if (provincia != null && !provincia.isEmpty()) {
            municipios = serv_municipios.findMunicipioAvailable(provincia, "Cuba");
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
