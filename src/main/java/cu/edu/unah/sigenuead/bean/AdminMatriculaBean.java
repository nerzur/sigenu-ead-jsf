package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.Matricula;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.CarreraServices;
import cu.edu.unah.sigenuead.service.CursoServices;
import cu.edu.unah.sigenuead.service.FacultadServices;
import cu.edu.unah.sigenuead.service.MatriculaServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SessionScoped
@Named
@Data
public class AdminMatriculaBean implements Serializable {
    
    private Date iniciomatricula;
    private Date iniciomatricula1;
    private Date finmatricula;
    private String curso;
    private String facultad;
    private String carrera;
    private boolean matriculcerrada = false;
    private boolean estado = false;
    private Matricula matricula_selected;
    private List<Matricula> lista_matricula = new ArrayList<Matricula>();
    private List<String> lista_facultad = new ArrayList<String>();
    private List<String> lista_carrera = new ArrayList<String>();

    private final FacultadServices serv_facultad = new FacultadServices();
    private final CursoServices serv_curso = new CursoServices();
    private final CarreraServices serv_carrera = new CarreraServices();
    private final MatriculaServices serv = new MatriculaServices();

    public void init() {
        lista_matricula.clear();
        lista_matricula = serv.findAllMatricula();
    }

    public void cleanVariables() {
        long now = System.currentTimeMillis();
        iniciomatricula = new Date(now);
        finmatricula = new Date(now);
        curso = "";
        facultad = "";
        carrera = "";
        matriculcerrada = false;
    }

    public void updateSelected_Matricula(Matricula matricula) {
        this.setMatricula_selected(matricula);
        cleanVariables();
    }

    public void updateSelected_Matricula_toDelete(Matricula matricula) {
        this.setMatricula_selected(matricula_selected);
    }

    public void updateSelected_Matricula_toEdit(Matricula matricula) {
        updateData();
        matricula_selected = matricula;
        iniciomatricula1 = matricula.getMatriculaFechaInicio();
        iniciomatricula = matricula.getMatriculaFechaInicio();
        finmatricula = matricula.getMatriculaFechaFin();
        curso = matricula.getCursoidcurso().getIdcurso();
        estado = matricula.getMatriculaCancelada();
        matriculcerrada = matricula.getCerrada();

        //facultad = matricula.getFacultadCumCarrera().getCarrera().getFacultadcodigoarea().getNombrearea(); //mal
        if (!matricula.getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum().equals("")) {
            facultad = matricula.getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum();
        } else {
            facultad = matricula.getFacultadCumCarrera().getFacultadCum().getFacultad().getNombrearea();
        }
        carrera = matricula.getFacultadCumCarrera().getCarrera().getCarreranacionalidcarreranacional().getNombrecarreranacional();

    }

    public void addMatricula() {
        FacesContext context = FacesContext.getCurrentInstance();

        Curso cursog = serv_curso.findCursoActual();
        String curso = "";
        if (cursog != null) {
            curso = cursog.getIdcurso();
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: No existe un Curso activo en el sistema", ""));
            return;
        }
        Par submit = serv.addMatricula(iniciomatricula, finmatricula, false, matriculcerrada, curso, facultad, carrera);
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addMatriculaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-matricula");
    }

    public void editMatricula() {
        Par submit = serv.editMatricula(iniciomatricula1, iniciomatricula1, finmatricula, matricula_selected.getMatriculaCancelada(), matriculcerrada, matricula_selected.getCursoidcurso().getIdcurso(), facultad, carrera);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editMatriculaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-matricula");
    }

    public void deleteMatricula() {
//        Par submit = serv.deleteMatricula(iniciomatricula, curso, facultad, carrera);
//        FacesContext context = FacesContext.getCurrentInstance();
//        init();
//        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }

    public String translateEstado(boolean estado) {
        if (estado) {
            return "Cerrada";
        }
        return "Abierta";
    }

    public String translateBooleanToSeverity(boolean element) {
        if (!element) {
            return "badge badge-success";
        }
        return "badge badge-danger";
    }

    public String TranslateDate(Date date) {
        if (date != null) {
            return new SimpleDateFormat("dd'/'MM'/'yyyy", new Locale("es", "ES")).format(date);
        }
        return "No definido";
    }

    public void updateData() {
        cleanVariables();
        lista_facultad.clear();
        lista_facultad = serv_facultad.findAreasAvailables();
    }

    public void updateListCarreras() {
        lista_carrera.clear();
        if (facultad != null && !facultad.equals("")) {
            lista_carrera = serv_carrera.findCarreraAvailableByArea(facultad);
        }
        if (lista_carrera == null) {
            lista_carrera = new ArrayList<String>();
        }
    }
    public String translatearea(Matricula matricula_selected1) {
        if (matricula_selected1 == null) {
            return "";
        }
        if (!matricula_selected1.getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum().equals("")) {
            return matricula_selected1.getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum();
        }
        return matricula_selected1.getFacultadCumCarrera().getFacultadCum().getFacultad().getNombrearea();
    }

    public boolean isMatriculcerrada() {
        return !matriculcerrada;
    }

    public void setMatriculcerrada(boolean matriculcerrada) {
        this.matriculcerrada = !matriculcerrada;
    }
}
