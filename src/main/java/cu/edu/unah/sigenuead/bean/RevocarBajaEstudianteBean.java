package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Baja;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.BajaServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SessionScoped
@Named
@Data
public class RevocarBajaEstudianteBean implements Serializable {

    private String id;
    private String nombre;
    private String area;
    private String carrera;
    private Date fecha;
    private String tipo;
    private String motivo;
    private Baja baja;
    private boolean founded;

    final BajaServices serv_baja = new BajaServices();

    public void init() {
        id = "";
        nombre = "";
        area = "";
        carrera = "";
        fecha = new Date(System.currentTimeMillis());
        tipo = "";
        motivo = "";
        baja = new Baja();
        founded = false;
    }

    public void search() {
        Baja b = serv_baja.findBajaForRevocar(id);
        if (b == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe un estudiante en el sistema con esa ID o el estudiante aun no es baja", "Error"));
            founded = false;
            return;
        }
        baja = b;
        nombre = b.getFacultadCumCarreraEstudiante().getEstudiante().getEstudianteNombre() + " " + b.getFacultadCumCarreraEstudiante().getEstudiante().getEstudianteApellido1() + " " + b.getFacultadCumCarreraEstudiante().getEstudiante().getEstudianteApellido2();
        if (!b.getFacultadCumCarreraEstudiante().getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum().equals("")) {
            area = b.getFacultadCumCarreraEstudiante().getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum();
        } else {
            area = b.getFacultadCumCarreraEstudiante().getFacultadCumCarrera().getFacultadCum().getFacultad().getNombrearea();
        }
        carrera = b.getFacultadCumCarreraEstudiante().getFacultadCumCarrera().getCarrera().getCarreranacionalidcarreranacional().getNombrecarreranacional();
        fecha = b.getBajaPK().getFecha();
        tipo = b.getTipoBaja().getNombreTipoBaja();
        motivo = b.getMotivoBaja().getNombreMotivoBaja();
        founded = true;
    }

    public void submit() {
        Par submit = serv_baja.revocarBaja(id, baja);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }

    public String translateDate(Date date) {
        if (date != null) {
            return new SimpleDateFormat("dd'/'MM'/'yyyy", new Locale("es", "ES")).format(date);
        }
        return "No definido";
    }
}
