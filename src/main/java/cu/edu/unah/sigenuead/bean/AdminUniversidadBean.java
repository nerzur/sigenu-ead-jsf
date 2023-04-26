package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Universidad;
import cu.edu.unah.sigenuead.service.MunicipioServices;
import cu.edu.unah.sigenuead.service.ProvinciaServices;
import cu.edu.unah.sigenuead.service.UniversidadServices;
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
public class AdminUniversidadBean implements Serializable {

    private String nombre;
    private String nombre1;
    private String iniciales;
    private String telefono;
    private String nombreRector;
    private String nombreSecGen;
    private String codigo;
    private String direccion;
    private String municipio;
    private String provincia;
    private String reglamento;
    private String actividad;
    private String fax;
    private boolean cancelado = true;
    private String codigo1;
    private List<Universidad> lista_universidades = new ArrayList<Universidad>();
    private List<String> provincias = new ArrayList<String>();
    private List<String> municipios = new ArrayList<String>();
    private Universidad universidadSelected;
    final private UniversidadServices serv = new UniversidadServices();
    final private ProvinciaServices serv_provincia = new ProvinciaServices();
    final private MunicipioServices serv_municipios = new MunicipioServices();
    private String DisableAdd = "";

    public void init() {
        lista_universidades.clear();
        lista_universidades = serv.findAllUniversidad();
        updateDisableAdd();
        cleanVariables();
    }

    public void updateDisableAdd() {
        boolean t = false;
        for (int i = 0; i < lista_universidades.size(); i++) {
            if (!lista_universidades.get(i).getCanceladouniversidad()) {
                t = true;
            }
        }
        if (!t) {
            DisableAdd = "";
        } else {
            DisableAdd = "true";
        }
    }

    public void cleanVariables() {
        this.setCodigo("");
        this.setNombre("");
        this.setIniciales("");
        this.setTelefono("");
        this.setNombreRector("");
        this.setNombreSecGen("");
        this.setDireccion("");
        this.setMunicipio("");
        this.setProvincia("");
        this.setCancelado(true);
    }

    public void updateSelected_Universidad(Universidad universidad) {
        this.setUniversidadSelected(universidad);
        cleanVariables();
    }

    public void updateSelected_Universidad_toDelete(Universidad universidad) {
        this.setUniversidadSelected(universidadSelected);
        nombre1 = universidad.getNombreuniversidad();
    }

    public void updateSelected_Universidad_toEdit(Universidad universidad) {
        universidadSelected = universidad;
        nombre = universidad.getNombreuniversidad();
        nombre1 = universidad.getNombreuniversidad();
        iniciales = universidad.getInicialesuniversidad();
        codigo = universidad.getCodigouniversidad();
        telefono = universidad.getTelefonouniversidad();
        fax = universidad.getFaxuniversidad();
        nombreRector = universidad.getNombrerectoruniversidad();
        nombreSecGen = universidad.getNombresecgraluniversidad();
        direccion = universidad.getDireccionuniversidad();
        updateProvincias();
        provincia = universidad.getMunicipioidmunicipio().getProvinciaidprovincia().getNombreprovincia();
        updateMunicipios();
        municipio = universidad.getMunicipioidmunicipio().getNombremunicipio();
        cancelado = universidad.getCanceladouniversidad();
        reglamento = universidad.getReglamentouniversidad();
        actividad = universidad.getActividaduniversidad();
        codigo1 = universidad.getCodigouniversidad();

    }

    public void addUniversidad() {
        Par submit = serv.addUniversidad(codigo, nombre, iniciales, actividad, telefono, reglamento, fax, nombreRector, nombreSecGen, direccion, cancelado, municipio, provincia);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(((Par) submit).getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addUniversidadDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-universidad");
    }

    public void editUniversidad() {
        Par submit = serv.editUniversidad(codigo1, nombre, iniciales, actividad, telefono, reglamento, fax, nombreRector, nombreSecGen, direccion, cancelado, municipio, provincia);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editUniversidadDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-universidad");
    }

    public void deleteUniversidad() {
//        Par submit = serv.deleteUniversidad(nombre1);
//        FacesContext context = FacesContext.getCurrentInstance();
//        init();
//        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }

    public void updateProvincias() {
        provincia = "";
        provincias.clear();
        provincias = serv_provincia.findProvinciaAvailable("Cuba");
    }

    public void updateMunicipios() {
        municipios.clear();
        if (provincia != null && !provincia.equals("")) {
            municipios = serv_municipios.findMunicipioAvailable(provincia, "Cuba");
        }
    }

    public String translateEstado(boolean estado) {
        if (estado) {
            return "Inactivo";
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
