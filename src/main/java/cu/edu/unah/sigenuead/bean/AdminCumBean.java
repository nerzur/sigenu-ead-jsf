package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Cum;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.CumServices;
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
public class AdminCumBean implements Serializable {

    private String nombre;
    private static String nombre1;
    private String iniciales;
    private String telefono;
    private String nombredirector;
    private String nombresecgen;
    private String codigo;
    private String direccion;
    private String municipio;
    private String provincia;
    private String reglamento;
    private String actividad;
    private String fax;
    private static String codigo1;
    private boolean cancelado = true;
    private List<Cum> cumList = new ArrayList<Cum>();
    private List<String> provincias = new ArrayList<String>();
    private List<String> municipios = new ArrayList<String>();
    private Cum cumSelected;
    final private CumServices serv = new CumServices();
    final private ProvinciaServices serv_provincia = new ProvinciaServices();
    final private MunicipioServices serv_municipios = new MunicipioServices();

    public void init() {
        cumList.clear();
        cumList = serv.findAllCum();
    }

    public void cleanVariables() {
        this.setCodigo("");
        this.setNombre("");
        this.setIniciales("");
        this.setTelefono("");
        this.setNombredirector("");
        this.setNombresecgen("");
        this.setDireccion("");
        this.setMunicipio("");
        this.setProvincia("");
        this.setCancelado(true);
        codigo1 = "";
    }

    public void updateSelected_Cum(Cum cum) {
        this.setCumSelected(cum);
        cleanVariables();
    }

    public void updateSelected_Cum_toDelete(Cum cum) {
        this.setCumSelected(cumSelected);
        nombre1 = cum.getCodigocum();
    }

    public void updateSelected_Cum_toEdit(Cum cum) {
        cumSelected = cum;
        nombre = cum.getNombrecum();
        nombre1 = cum.getNombrecum();
        iniciales = cum.getInicialescum();
        codigo = cum.getCodigocum();
        codigo1 = cum.getCodigocum();
        telefono = cum.getTelefonocum();
        fax = cum.getFaxcum();
        nombredirector = cum.getNombredirectorcum();
        nombresecgen = cum.getNombresecgralcum();
        direccion = cum.getDireccioncum();
        municipio = cum.getMunicipioidmunicipio().getNombremunicipio();
        provincia = cum.getMunicipioidmunicipio().getProvinciaidprovincia().getNombreprovincia();
        cancelado = cum.getCumcancelado();
        reglamento = cum.getReglamentocum();
        actividad = cum.getActividadcum();
    }

    public void addCum() {
        Par submit = serv.addCum(codigo, nombre, iniciales, actividad, cancelado, telefono, reglamento, fax, nombredirector, nombresecgen, direccion, municipio, provincia);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addCumDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-cum");
    }

    public void editCum() {
        Par submit = serv.editCum(codigo1, nombre, iniciales, actividad, cancelado, telefono, reglamento, fax, nombredirector, nombresecgen, direccion, municipio, provincia);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editCumDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-cum");
    }

    public void deleteCum() {
//        Par submit = serv.deleteCum(codigo);
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
        if (provincia != null && !provincia.equals("")) {
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
