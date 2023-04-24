package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.PromoverServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;

@SessionScoped
@Named
@Data
public class RevocarPromocionBean implements Serializable {

    private String id;
    private String nombre;
    private String area;
    private String carrera;
    private FacultadCumCarreraEstudiante faq;
    private boolean founded;

    final PromoverServices serv_prom = new PromoverServices();

    public void init() {
        id = "";
        nombre = "";
        area = "";
        carrera = "";
        founded = false;
    }

    public void search() {
        FacultadCumCarreraEstudiante f = serv_prom.findPromoverForRevocar(id);
        if (f == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe un estudiante en el sistema con esa ID o el estudiante aún no ha promovido", "Error"));
            founded = false;
            return;
        }
        faq = f;
        nombre = f.getEstudiante().getEstudianteNombre() + " " + f.getEstudiante().getEstudianteApellido1() + " " + f.getEstudiante().getEstudianteApellido2();
        if (!f.getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum().equals("")) {
            area = f.getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum();
        } else {
            area = f.getFacultadCumCarrera().getFacultadCum().getFacultad().getNombrearea();
        }
        carrera = f.getFacultadCumCarrera().getCarrera().getCarreranacionalidcarreranacional().getNombrecarreranacional();
        founded = true;
    }

    public void submit() {
        Par submit = serv_prom.revocarPromover(id);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }
}
