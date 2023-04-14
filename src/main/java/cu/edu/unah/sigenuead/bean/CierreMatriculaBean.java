package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.CarreraServices;
import cu.edu.unah.sigenuead.service.FacultadServices;
import cu.edu.unah.sigenuead.service.MatriculaServices;
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
public class CierreMatriculaBean implements Serializable {

    private String area;
    private String carrera;

    private List<String> list_area = new ArrayList<String>();
    private List<String> list_carrera = new ArrayList<String>();

    private final FacultadServices serv_facultad = new FacultadServices();
    private final CarreraServices serv_carrera = new CarreraServices();
    private final MatriculaServices serv_matricula = new MatriculaServices();

    public void init() {
        area = "";
        carrera = "";
        list_area.clear();
        list_carrera.clear();
        list_area = serv_facultad.findAreasAvailables();
    }

    public void update_Carreras() {
        list_carrera.clear();
        list_carrera = serv_carrera.findCarreraAvailableByArea(area);
    }

    public void cierreMatricula() {
        Par submit = serv_matricula.CerrarMatricula(area, carrera);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }

}
