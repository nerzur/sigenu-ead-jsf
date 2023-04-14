package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.CursoServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Named
@SessionScoped
@Data
public class AdminCursoBean implements Serializable {

    private String idCurso;
    private Date inicioMatricula;
    private Date finMatricula;
    private Date inicioRematricula;
    private Date finRematricula;
    private Date fechaGraduacion;
    private Date minDate;
    private Date maxDate;
    private boolean cursoActual = true;

    private Curso selectedCurso;
    private List<Curso> lista_curso = new ArrayList<Curso>();
    final private CursoServices serv = new CursoServices();

    public void init() {
        lista_curso.clear();
        lista_curso = serv.findAllCursos();
        cleanVariables();
    }

    public void cleanVariables() {
        long now = System.currentTimeMillis();
        idCurso = "";
        inicioMatricula = new Date(now);
        finMatricula = new Date(now);
        inicioRematricula = new Date(now);
        finRematricula = new Date(now);
        fechaGraduacion = new Date(now);
        cursoActual = true;
    }

    public void updateSelected_Curso(Curso curso) {
        this.setSelectedCurso(curso);
        cleanVariables();
    }

    public void updateSelected_Curso_toDelete(Curso curso) {
        this.setSelectedCurso(selectedCurso);
    }

    public void updateSelected_Curso_toEdit(Curso curso) {
        selectedCurso = curso;
        idCurso = curso.getIdcurso();
        inicioMatricula = curso.getFechainiciomatriculacurso();
        finMatricula = curso.getFechafinmatricula();
        inicioRematricula = curso.getFechainiciorematricula();
        finRematricula = curso.getFechafinrematricula();
        fechaGraduacion = curso.getFechagraduacion();
        cursoActual = curso.getCursoactual();
        setMinMaxDate();
    }

    public void generateId() {
        idCurso = serv.generateCode();
        setMinMaxDate();
        inicioMatricula = (Date) minDate.clone();
        finMatricula = (Date) minDate.clone();
        inicioRematricula = (Date) minDate.clone();
        finRematricula = (Date) minDate.clone();
        fechaGraduacion = ((Date) minDate.clone());
        fechaGraduacion.setYear(fechaGraduacion.getYear()-1);
    }

    public void setMinMaxDate(){
        int year1 = Integer.parseInt(idCurso.split("-")[0]);
        int year2 = Integer.parseInt(idCurso.split("-")[1]);

        SimpleDateFormat simpleDateFormatCurrentDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            minDate = simpleDateFormatCurrentDate.parse(year1+"-01-01");
            maxDate = simpleDateFormatCurrentDate.parse(year2+"-12-31");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCurso() {
        Par submit = serv.addCurso(idCurso, inicioMatricula, finMatricula, inicioRematricula, finRematricula, fechaGraduacion, cursoActual);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addCursoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-cursos");
    }

    public void editCurso() {
        Par submit = serv.editCurso(idCurso, inicioMatricula, finMatricula, inicioRematricula, finRematricula, fechaGraduacion, cursoActual);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editCursoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-cursos");
    }

    public void deleteCurso() {
//        Par submit = serv.deleteCurso(idcurso);
//        FacesContext context = FacesContext.getCurrentInstance();
//        init();
//        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }

    public String translateEstado(boolean estado) {
        if (!estado) {
            return "Antiguo";
        }
        return "Actual";
    }

    public String translateBooleanToSeverity(boolean element) {
        if (element) {
            return "badge badge-success";
        }
        return "badge badge-danger";
    }

    public String translateDate(Date date) {
        if (date != null) {
            return new SimpleDateFormat("dd'/'MM'/'yyyy", new Locale("es", "ES")).format(date);
        }
        return "No definido";
    }
}
