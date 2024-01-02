package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Asignatura_Nota;
import cu.edu.unah.sigenuead.entity.ExamenMatriculaFacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.EstudianteServices;
import cu.edu.unah.sigenuead.service.ExamenServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SessionScoped
@Named
@Data
public class EvaluarAsignaturaEstudianteBean implements Serializable {

    private String convocatoria;
    private String id;
    private List<String> list_convocatorias = new ArrayList<String>();
    private List<Asignatura_Nota> lista_asignatura_notas = new ArrayList<Asignatura_Nota>();

    private final ExamenServices serv_examen = new ExamenServices();
    private final EstudianteServices serv_estudiante = new EstudianteServices();
    private boolean founded;

    Asignatura_Nota l = new Asignatura_Nota(null, 4, null);

    public void init() {
        convocatoria = "";
        id = "";
        list_convocatorias.clear();
        list_convocatorias = serv_examen.findExamenAvailable();
        if (list_convocatorias == null) {
            list_convocatorias = new ArrayList<String>();
        }
        founded = false;
    }

    public void search() {
        List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> list = serv_estudiante.findAsignaturasForNota(id, convocatoria);
        lista_asignatura_notas.clear();
        if (list != null) {
            for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura l : list) {
                lista_asignatura_notas.add(new Asignatura_Nota(l.getMatriculaFacultadCumCarreraEstudianteAsignatura().getFacultadCumCarreraEstudianteAsignatura().getAsignatura(), l.getNota(), l.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatricula()));
            }
            founded = true;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe un estudiante en el sistema con esa ID o no tiene asignaturas matriculadas", "Error"));
            founded = false;
            return;
        }

    }

    public void refresh() {
        Par submit = serv_estudiante.addNota(convocatoria, lista_asignatura_notas, id);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }

    public String TranslateDate(Date date) {
        if (date != null) {
            return new SimpleDateFormat("dd'/'MM'/'yyyy", new Locale("es", "ES")).format(date);
        }
        return "No definido";
    }
    
}
