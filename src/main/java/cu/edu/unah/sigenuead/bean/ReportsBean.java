package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Asignatura;
import cu.edu.unah.sigenuead.service.AsignaturaServices;
import cu.edu.unah.sigenuead.service.CarreraServices;
import cu.edu.unah.sigenuead.service.FacultadServices;
import cu.edu.unah.sigenuead.service.PlanestudioServices;
import cu.edu.unah.sigenuead.util.ReportUtil;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import net.sf.jasperreports.engine.JRException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
@Data
public class ReportsBean implements Serializable {

    private ReportUtil reportUtil;
    private final String URL = ((HttpServletRequest) ((FacesContext) FacesContext.getCurrentInstance())
            .getExternalContext().getRequest()).getContextPath()
            + "/resources/reports/";
    private Object response;
    private String asignatura_Ms13d;
    private String estudiante_Ms19d;
    private String estudiante_Rs4b_tipo2;
    private Asignatura asignatura_Rs4b_tipo1;

    private String area;
    private String carrera;
    private String asignatura;
    private String plan_estudio;

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
        this.asignatura_Ms13d = "";
        this.estudiante_Ms19d = "";
        this.estudiante_Rs4b_tipo2 = "";
        this.asignatura_Rs4b_tipo1 = new Asignatura();
        this.asignatura = "";
        this.area = "";
        this.carrera = "";
        this.plan_estudio = "";
    }

    public void update_Area() {
        list_area.clear();
        list_area = serv_facultad.findAreasAvailables();
    }

    public void updtate_carrera() {
        list_carrera = new ArrayList<String>();
        list_carrera = serv_carrera.findCarreraAvailableByArea(area);
    }

    public void update_plan_estudio() {
        list_plan_estudio = new ArrayList<String>();
        list_plan_estudio = serv_plan.findPlanestudioAvailableByCarrera(area, carrera);
    }

    public void update_Asignaturas() {
        list_asignatura.clear();
        serv_asignatura.findAsignaturaByPlanEstudio(area, carrera, plan_estudio).forEach(asig->{
            list_asignatura.add(asig.getAsignaturaNombre());
        });
    }

    public void print_ms13d_PDF() throws JRException, IOException {
        java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("nombre", asignatura_Ms13d);
        String filename = "Modelo Ms-13-d";
        String jasperpath = URL + "ms13d.jasper";
        reportUtil.PDF(params, jasperpath, filename);

    }

    public void print_ms19d_PDF() throws JRException, IOException {
        java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("id", estudiante_Ms19d);
        String filename = "Modelo Ms-19-d";
        String jasperpath = URL + "ms19d.jasper";
        reportUtil.PDF(params, jasperpath, filename);
    }

    public void print_rs4b_tipo1_PDF() throws JRException, IOException {
        java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("nombreasignatura", asignatura_Rs4b_tipo1.getAsignaturaId());
        String filename = "Modelo Rs-4-b-1";
        String jasperpath = URL + "rs4btipo1.jasper";
        reportUtil.PDF(params, jasperpath, filename);
    }

    public void print_rs4b_tipo2_PDF() throws JRException, IOException {
        java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("estudiante", estudiante_Rs4b_tipo2);
        String filename = "Modelo Rs-4B-2";
        String jasperpath = URL + "rs4btipo2.jasper";
        reportUtil.PDF(params, jasperpath, filename);

    }

    public void print_ms13d_RTF() throws JRException, IOException {
        java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("nombre", asignatura_Ms13d);
        String filename = "Modelo Ms-13-d";
        String jasperpath = URL + "ms13d.jasper";
        reportUtil.RTF(params, jasperpath, filename);

    }

    public void print_ms19d_RTF() throws JRException, IOException {
        java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("id", estudiante_Ms19d);
        String filename = "Modelo Ms-19-d";
        String jasperpath = URL + "ms19d.jasper";
        reportUtil.RTF(params, jasperpath, filename);

    }

    public void print_rs4b_tipo1_RTF() throws JRException, IOException {
        java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("nombreasignatura", asignatura_Rs4b_tipo1.getAsignaturaId());
        String filename = "Modelo Rs-4-b-1";
        String jasperpath = URL + "rs4btipo1.jasper";
        reportUtil.RTF(params, jasperpath, filename);
    }

    public void print_rs4b_tipo2_RTF() throws JRException, IOException {
        java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("estudiante", estudiante_Rs4b_tipo2);
        String filename = "Modelo Rs-4B-2";
        String jasperpath = URL + "rs4btipo2.jasper";
        reportUtil.RTF(params, jasperpath, filename);

    }

    public void print_ms13d_DOC() throws JRException, IOException {
        java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("nombre", asignatura_Ms13d);
        String filename = "Modelo Ms-13-d";
        String jasperpath = URL + "ms13d.jasper";
        reportUtil.DOC(params, jasperpath, filename);

    }

    public void print_ms19d_DOC() throws JRException, IOException {
        java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("id", estudiante_Ms19d);
        String filename = "Modelo Ms-19-d";
        String jasperpath = URL + "ms19d.jasper";
        reportUtil.DOC(params, jasperpath, filename);

    }

    public void print_rs4b_tipo1_DOC() throws JRException, IOException {
        System.out.println(asignatura_Rs4b_tipo1.getAsignaturaId());
        java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("nombreasignatura", asignatura_Rs4b_tipo1.getAsignaturaId());
        String filename = "Modelo Rs-4-b-1";
        String jasperpath = URL + "rs4btipo1.jasper";
        reportUtil.DOC(params, jasperpath, filename);
    }

    public void print_rs4b_tipo2_DOC() throws JRException, IOException {
        java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
        params.put("estudiante", estudiante_Rs4b_tipo2);
        String filename = "Modelo Rs-4B-2";
        String jasperpath = URL + "rs4btipo2.jasper";
        reportUtil.DOC(params, jasperpath, filename);
    }

    public void print_ms2d() {
        try {
            String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/reportes/ms2d.doc");
            File file = new File(relativeWebPath);
            String filename = file.getPath();
            System.out.println(file);
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print_ms18() {
        try {
            String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(URL + "ms18.doc");
            File file = new File(relativeWebPath);
            String filename = file.getPath();
            System.out.println(file);
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
