package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.*;
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
public class AdminCarreraBean implements Serializable {

    private String nombre;
    private static String nombre1;

    private String carreraNacional;
    private String Facultad;
    private String Especialidad;

    private String carreraNacional1;
    private String Facultad1;
    private String Especialidad1;

    private boolean cancelado = true;
    private List<Carrera> lista_carrera = new ArrayList<Carrera>();
    private List<String> lista_carrera_nacional = new ArrayList<String>();
    private List<String> lista_facultad = new ArrayList<String>();
    private List<String> lista_especialidad = new ArrayList<String>();

    private Carrera carrera_selected;

    final private CarreraServices serv = new CarreraServices();
    final private CarreraNacionalServices serv_carrera_nacional = new CarreraNacionalServices();
    final private FacultadServices serv_facultad = new FacultadServices();
    final private EspecialidadServices serv_especialidad = new EspecialidadServices();

    public void init() {
        lista_carrera.clear();
        lista_carrera = serv.findAllCarrera();
        cleanVariables();
    }

    public void cleanVariables() {
        this.setCarreraNacional("");
        this.setEspecialidad("");
        this.setFacultad("");
        this.setCancelado(true);
    }

    public void updateSelected_Carrera(Carrera carrera) {
        this.setCarrera_selected(carrera);
        cleanVariables();
    }

    public void updateSelected_Carrera_toDelete(Carrera carrera) {
        this.setCarrera_selected(carrera_selected);
        //nombre1 = carrera.getIdcarrera();
    }

    public void updateSelected_Carrera_toEdit(Carrera carrera) {
        carrera_selected = carrera;
        Especialidad = carrera.getCarreranacionalidcarreranacional().getEspecialidadidespecialidad().getNombreespecialidad();
        Facultad = carrera.getFacultadcodigoarea().getNombrearea();
        carreraNacional = carrera.getCarreranacionalidcarreranacional().getNombrecarreranacional();

        Especialidad1 = carrera.getCarreranacionalidcarreranacional().getEspecialidadidespecialidad().getNombreespecialidad();
        Facultad1 = carrera.getFacultadcodigoarea().getNombrearea();
        carreraNacional1 = carrera.getCarreranacionalidcarreranacional().getNombrecarreranacional();

        cancelado = carrera.getCanceladacarrera();

    }

    public void updateData() {
        lista_especialidad.clear();
        lista_facultad.clear();
        lista_especialidad = serv_especialidad.findEspecialidadAvailable();
        lista_facultad = serv_facultad.findFacultadAvailables();
    }

    public void updateListCarreraNacional() {
        lista_carrera_nacional.clear();
        lista_carrera_nacional = serv_carrera_nacional.findCarreranacionalAvailableByEspecialidad(Especialidad);
        if (lista_carrera_nacional == null) {
            lista_carrera_nacional = new ArrayList<String>();
        }
    }

    public void addCarrera() {
        Par submit = serv.addCarrera(carreraNacional, Facultad, Especialidad, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addCarreraDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-carrera");
    }

    public void editCarrera() {
        Par submit = serv.editCarrera(carreraNacional1, Facultad1, Especialidad1, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editCarreraDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-carrera");
    }

    public void deleteCarrera() {
//        Par submit = serv.deleteCarreraNacional(carreraNacional, Facultad, Especialidad);
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
