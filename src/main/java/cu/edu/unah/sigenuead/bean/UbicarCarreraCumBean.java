package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.CarreraServices;
import cu.edu.unah.sigenuead.service.CumServices;
import cu.edu.unah.sigenuead.service.FacultadServices;
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
public class UbicarCarreraCumBean implements Serializable {

    private String facultad;
    private String facultad1;
    private String cum;
    private String carrera;
    private String carrera1;
    private boolean do_search;
    private String[] selectedCarCum = new String[0];
    private String[] selectedCarCum1 = new String[0];

    private List<String> list_facultad = new ArrayList<String>();
    private List<String> list_cum = new ArrayList<String>();
    private List<String> list_carrera = new ArrayList<String>();
    private List<String> list_carrera_cum = new ArrayList<String>();

    final FacultadServices serv_facultad = new FacultadServices();
    final CumServices serv_cum = new CumServices();
    final CarreraServices serv_carrera = new CarreraServices();

    public void init() {
        facultad = "";
        facultad1 = "";
        carrera = "";
        carrera1 = "";
        list_facultad.clear();
        list_facultad = serv_facultad.findFacultadAvailables();
        list_cum.clear();
        list_cum = serv_cum.findCumAvailables();
        do_search = false;
    }

    public void updateCarrera() {
        if (facultad != null && !facultad.equals("")) {
            list_carrera.clear();
            list_carrera = serv_carrera.findCarreraAvailable(facultad);
        }
    }

    public void search() {
        facultad1 = facultad;
        carrera1 = carrera;
        if (facultad1 != null && !facultad1.equals("") && carrera1 != null && !carrera1.equals("")) {
            list_carrera_cum = new ArrayList<String>();
            list_carrera_cum = serv_cum.findCumAvailablesbyFacultad(facultad1,carrera1);
            do_search = true;
            actualizar_arraySelected();
        }
    }

    private void actualizar_arraySelected() {
        if (list_carrera_cum != null) {
            selectedCarCum = new String[list_carrera_cum.size()];
            selectedCarCum1 = new String[list_carrera_cum.size()];
            for (int i = 0; i < list_carrera_cum.size(); i++) {
                selectedCarCum[i] = list_carrera_cum.get(i);
                selectedCarCum1[i] = list_carrera_cum.get(i);
            }
        } else {
            selectedCarCum = new String[0];
        }
    }

    public void updateList() {
        Par submit = new Par(1, "");
        FacesContext context = FacesContext.getCurrentInstance();
        submit = serv_facultad.addCarreraToCum(facultad1, carrera1, selectedCarCum);
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        list_carrera_cum = new ArrayList<String>();
        list_carrera_cum = serv_cum.findCumAvailablesbyFacultad(facultad1, carrera1);
        actualizar_arraySelected();
    }

}
