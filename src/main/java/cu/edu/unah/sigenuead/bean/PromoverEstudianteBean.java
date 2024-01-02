package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Estudiante_promover;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.CarreraServices;
import cu.edu.unah.sigenuead.service.FacultadServices;
import cu.edu.unah.sigenuead.service.PromoverServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
@Data
public class PromoverEstudianteBean implements Serializable {

    private String area;
    private String carrera;
    private boolean founded;

    private List<String> list_area = new ArrayList<String>();
    private List<String> list_carrera = new ArrayList<String>();

    private List<Estudiante_promover> list_estudiante_promover = new ArrayList<Estudiante_promover>();

    private final FacultadServices serv_facultad = new FacultadServices();
    private final CarreraServices serv_carrera = new CarreraServices();
    private final PromoverServices serv_promover = new PromoverServices();

    public void init() {
        area = "";
        carrera = "";
        founded = false;
        list_area.clear();
        list_carrera.clear();
        list_area = serv_facultad.findAreasAvailables();
        list_estudiante_promover.clear();
    }

    public void update_Carreras() {
        if (area != null && !area.equals("")) {
            list_carrera.clear();
            list_carrera = serv_carrera.findCarreraAvailableByArea(area);
        }
    }

    public void search() {
        if (area != null && !area.equals("") && carrera != null && !carrera.equals("")) {
            list_estudiante_promover = new ArrayList<Estudiante_promover>();
            list_estudiante_promover = serv_promover.promoverEstudiantes(area, carrera);
        }
        if (!list_estudiante_promover.isEmpty()) {
            founded = true;
        } else {
            founded = false;
        }
    }

    public void actualizar() {
        Par submit = serv_promover.promoverEstudiantes(list_estudiante_promover);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        search();
    }
}
