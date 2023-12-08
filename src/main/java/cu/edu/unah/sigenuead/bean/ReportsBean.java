package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Asignatura;
import cu.edu.unah.sigenuead.service.AsignaturaServices;
import cu.edu.unah.sigenuead.service.CarreraServices;
import cu.edu.unah.sigenuead.service.FacultadServices;
import cu.edu.unah.sigenuead.service.PlanestudioServices;
import cu.edu.unah.sigenuead.util.FileType;
import cu.edu.unah.sigenuead.util.ReportUtil;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.StreamedContent;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionScoped
@Named
@Data
public class ReportsBean implements Serializable {

    private ReportUtil reportUtil;
    private final String URL = "/resources/reports/";
    private Object response;
    //    private String asignatura_Ms13d;
//    private String estudiante_Ms19d;
//    private String estudiante_Rs4b_tipo2;
    private Asignatura asignatura;

    private String idEstudiante;
    private String area;
    private String carrera;
    private String asignaturaNombre;
    private String plan_estudio;
    private StreamedContent file;

    private List<String> list_area = new ArrayList<String>();
    private List<String> list_carrera = new ArrayList<String>();
    private List<String> list_asignatura = new ArrayList<String>();
    private List<String> list_plan_estudio = new ArrayList<String>();

    private PlanestudioServices serv_plan = new PlanestudioServices();
    private FacultadServices serv_facultad = new FacultadServices();
    private CarreraServices serv_carrera = new CarreraServices();
    private AsignaturaServices serv_asignatura = new AsignaturaServices();

    @PostConstruct
    public void postConstruct() {
        reportUtil = new ReportUtil();
    }

    public void init() {
        this.asignatura = new Asignatura();
        this.asignaturaNombre = "";
        this.area = "";
        this.carrera = "";
        this.plan_estudio = "";
    }

    public Asignatura getAsignaturaByName() {
        return serv_asignatura.findAsignaturaByNombreAvailable(asignaturaNombre, area, carrera, plan_estudio);
    }

    public void updateArea() {
        list_area.clear();
        list_area = serv_facultad.findAreasAvailables();
    }

    public void updateCarrera() {
        list_carrera = new ArrayList<>();
        list_carrera = serv_carrera.findCarreraAvailableByArea(area);
    }

    public void updatePlanEstudio() {
        list_plan_estudio = new ArrayList<>();
        list_plan_estudio = serv_plan.findPlanestudioAvailableByCarrera(area, carrera);
    }

    public void updateAsignatura() {
        list_asignatura.clear();
        serv_asignatura.findAsignaturaByPlanEstudio(area, carrera, plan_estudio).forEach(asig -> {
            list_asignatura.add(asig.getAsignaturaNombre());
        });
    }

    public void printRegistroCalifAsignatura(String type) {
        Map<String, Object> params = new HashMap<>();
        asignatura = getAsignaturaByName();
        params.put("nombreasignatura", asignatura.getAsignaturaId());
        String fileName = "Registro de Calificaciones de Asignatura - " + asignaturaNombre + "." + type.toLowerCase();
        String jasperPath = URL + "rs4btipo1.jasper";
        FileType fileType = FileType.valueOf(type);
        try {
            file = reportUtil.processReport(params, jasperPath, fileName, fileType);
        } catch (JRException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printRegistroCalifEstudiante(String type){
        Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("estudiante", idEstudiante);
        String fileName = "Registro de Calificaciones de Estudiante - " + idEstudiante + "." + type.toLowerCase();
        String jasperPath = URL + "rs4btipo2.jasper";
        FileType fileType = FileType.valueOf(type);
        try {
            file = reportUtil.processReport(params, jasperPath, fileName, fileType);
        } catch (JRException | IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Ha ocurrido un error al procesar el reporte.", "Error"));
            e.printStackTrace();
        }
    }

    public void printCertCalifCursoAc(String type){
        Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("id", idEstudiante);
        String fileName = "Certificado de Calificaciones de Curso Académico - " + idEstudiante + "." + type.toLowerCase();
        String jasperPath = URL + "ms19d.jasper";
        FileType fileType = FileType.valueOf(type);
        try {
            file = reportUtil.processReport(params, jasperPath, fileName, fileType);
        } catch (JRException | IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Ha ocurrido un error al procesar el reporte.", "Error"));
            e.printStackTrace();
        }
    }

    public void printActaExamen(String type) {
        Map<String, Object> params = new HashMap<>();
        asignatura = getAsignaturaByName();
        params.put("nombre", asignatura.getAsignaturaId());
        String fileName = "Acta de Examen - " + asignaturaNombre + "." + type.toLowerCase();
        String jasperPath = URL + "ms13d.jasper";
        FileType fileType = FileType.valueOf(type);
        try {
            file = reportUtil.processReport(params, jasperPath, fileName, fileType);
        } catch (JRException | IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Ha ocurrido un error al procesar el reporte.", "Error"));
            e.printStackTrace();
        }
    }
}
