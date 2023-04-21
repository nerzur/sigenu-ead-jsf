package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.BajaServices;
import cu.edu.unah.sigenuead.service.CursoServices;
import cu.edu.unah.sigenuead.service.EstudianteServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SessionScoped
@Named
@Data
public class DarBajaEstudianteBean implements Serializable {

    private String id;
    private String nombre;
    private String area;
    private String carrera;
    private String tipo;
    private String motivo;
    private String estado;
    private Date fecha = new Date(System.currentTimeMillis());
    private boolean founded;

    private final BajaServices serv_baja = new BajaServices();
    private final CursoServices serv_curso = new CursoServices();
    private final EstudianteServices serv_estudiante = new EstudianteServices();

    private List<String> list_tipo_baja = new ArrayList<String>();
    private List<String> list_motivo_baja = new ArrayList<String>();

    public void init() {
        id = "";
        nombre = "";
        area = "";
        carrera = "";
        tipo = "";
        motivo = "";
        estado = "";
        fecha = new Date(System.currentTimeMillis());
        founded = false;
    }

    public void search() {
        FacultadCumCarreraEstudiante fcce = serv_estudiante.findByEstudianteActivo(id);
        if (fcce == null) {
            fcce = serv_estudiante.findByEstudianteNuevaMtricula(id);
            if (fcce == null) {
                fcce = serv_estudiante.findByEstudianteMatriculaPasiva(id);
                if (fcce == null) {
                    Par submit = new Par(2, "No es posible darle baja al estudiante seleccionado.");
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
                    founded = false;
                    return;
                }
            }
        }
        nombre = fcce.getEstudiante().getEstudianteNombre() + " " + fcce.getEstudiante().getEstudianteApellido1() + " " + fcce.getEstudiante().getEstudianteApellido2();
        String c = fcce.getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum();
        if (!c.equals("")) {
            area = c;
        } else {
            area = fcce.getFacultadCumCarrera().getFacultadCum().getFacultad().getNombrearea();
        }
        carrera = fcce.getFacultadCumCarrera().getCarrera().getCarreranacionalidcarreranacional().getNombrecarreranacional();
        estado = fcce.getEstadoEstudianteestadoEstucianteId().getEstadoEstudianteNombre();
        list_tipo_baja = serv_baja.findTipoBajaAvailable();
        founded = true;
    }

    public void baja() {
        Par submit = serv_baja.darBaja(id, tipo, motivo, serv_curso.findCursoActual().getIdcurso(), fecha);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        init();
    }

    public void update_motivo_baja() {
        if (tipo != null && !tipo.equals("")) {
            list_motivo_baja = serv_baja.findMotivoBajaAvailableByTipo(tipo);
        }
    }
}
