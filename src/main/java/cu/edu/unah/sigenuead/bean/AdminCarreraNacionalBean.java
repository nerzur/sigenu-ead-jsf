package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Carreranacional;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.CarreraNacionalServices;
import cu.edu.unah.sigenuead.service.EspecialidadServices;
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
public class AdminCarreraNacionalBean implements Serializable {

    private String codigo;
    private String codigo1;
    private String nombre;
    private String diploma;
    private String especialidad;
    private String especialidad1;
    private String nombre1;
    private boolean cancelado = true;
    private List<Carreranacional> lista_carreranacionales = new ArrayList<Carreranacional>();
    private List<String> lista_especialidades = new ArrayList<String>();
    private Carreranacional carreranacional_selected;
    final private CarreraNacionalServices serv = new CarreraNacionalServices();
    final private EspecialidadServices serv_esp = new EspecialidadServices();

    public void init() {
        cleanVariables();
        lista_carreranacionales.clear();
        lista_especialidades = serv_esp.findEspecialidadAvailable();
        lista_carreranacionales = serv.findAllCarreranacional();
    }

    public void cleanVariables() {
        this.setCodigo("");
        this.setNombre("");
        this.setDiploma("");
        this.setEspecialidad("");
        this.setCancelado(true);
    }

    public void updateSelected_Carreranacional(Carreranacional carreranacional) {
        this.setCarreranacional_selected(carreranacional);
        cleanVariables();
    }

    public void updateSelected_Carreranacional_toDelete(Carreranacional carreranacional) {
        this.setCarreranacional_selected(carreranacional_selected);
        nombre1 = carreranacional.getNombrecarreranacional();
    }

    public void updateSelected_Carreranacional_toEdit(Carreranacional carreranacional) {
        lista_especialidades = serv_esp.findEspecialidadAvailable();
        carreranacional_selected = carreranacional;
        codigo = carreranacional.getCodigocarreranacional();
        codigo1 = carreranacional.getCodigocarreranacional();
        nombre = carreranacional.getNombrecarreranacional();
        diploma = carreranacional.getDiplomacarreranacional();
        especialidad = carreranacional.getEspecialidadidespecialidad().getNombreespecialidad();
        especialidad1 = carreranacional.getEspecialidadidespecialidad().getNombreespecialidad();
        nombre1 = carreranacional.getNombrecarreranacional();
        cancelado = carreranacional.getCanceladocarreranacional();
    }

    public void addCarreranacional() {
        Par submit = serv.addCarreranacional(nombre, codigo, diploma, especialidad, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addCarreraNacionalDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-carreraNacional");
    }

    public void editCarreranacional() {
        Par submit = serv.editCarreranacional(nombre1, nombre, codigo1, diploma, especialidad1, especialidad, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editCarreraNacionalDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-carreraNacional");
    }

    public void deleteCarreranacional() {
//        Par submit = serv.deleteCarreraNacional(nombre, especialidad);
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
