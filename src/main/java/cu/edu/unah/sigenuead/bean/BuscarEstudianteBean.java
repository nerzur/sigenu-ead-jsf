package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.*;
import cu.edu.unah.sigenuead.service.*;
import cu.edu.unah.sigenuead.util.EstudianteSearch;
import cu.edu.unah.sigenuead.util.texts;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SessionScoped
@Named
@Data
public class BuscarEstudianteBean implements Serializable {

    private List<Estudiante> buscados = new ArrayList<>();
    private String id_search;
    private String nombre_search;
    private String primer_apllido_search;
    private String segundo_apellido_search;
    private String area_search;
    private String carrera_search;
    private String estado_search;
    private String fuente_ingreso_search;

    private boolean founded = false;

    private List<String> list_area = new ArrayList<>();
    private List<String> list_carrera = new ArrayList<>();
    private List<String> list_estado = new ArrayList<>();
    List<EstudianteSearch> listEstudiantesSearched = new ArrayList<>();

    private final FacultadServices serv_facultad = new FacultadServices();
    private final CarreraServices serv_carrera = new CarreraServices();
    private final EstadoEstudianteServices serv_estado = new EstadoEstudianteServices();
    private final EstudianteServices serv_estudiante = new EstudianteServices();

    public void init() {
        id_search = "";
        nombre_search = "";
        primer_apllido_search = "";
        segundo_apellido_search = "";
        area_search = "";
        carrera_search = "";
        estado_search = "";
        fuente_ingreso_search = "";
        list_area.clear();
        list_carrera.clear();
        list_area = serv_facultad.findAreasAvailables();
        list_estado = serv_estado.findEstadoEstudianteAvailable();
        founded = false;
        listEstudiantesSearched.clear();
    }

    public void update_Carreras() {
        if (area_search != null && !area_search.equals("")) {
            list_carrera.clear();
            list_carrera = serv_carrera.findCarreraAvailableByArea(area_search);
        }
    }

    public void search() {
        List<Estudiante> listAllEstudiantes = serv_estudiante.findEstudianteEntities();
        listEstudiantesSearched.clear();
        for (Estudiante estudiante : listAllEstudiantes) {
            int searched = 0;
            if (estudiante.getEstudianteId().equals(id_search))
                searched++;
            if (estudiante.getEstudianteNombre().equals(nombre_search))
                searched++;
            if (estudiante.getEstudianteApellido1().equals(primer_apllido_search))
                searched++;
            if (estudiante.getEstudianteApellido2().equals(segundo_apellido_search))
                searched++;

            FacultadCumCarreraEstudiante matricula = null;
            if ((area_search != null && !area_search.equals("")) || (carrera_search != null && !carrera_search.equals(""))) {
                matricula = serv_estudiante.findByEstudianteUltimaFecha(estudiante.getEstudianteId());
                if (matricula != null) {
                    if (matricula.getFacultadCumCarrera().getCarrera().getCarreranacionalidcarreranacional().getNombrecarreranacional().equalsIgnoreCase(carrera_search)) {
                        searched++;
                    }
                    if (matricula.getFacultadCumCarrera().getFacultadCum().getFacultad().getNombrearea().equalsIgnoreCase(area_search)) {
                        searched++;
                    }
                    if (matricula.getFacultadCumCarrera() != null) {
                        if (matricula.getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum().equalsIgnoreCase(area_search)) {
                            searched++;
                        }
                    }
                    if (matricula.getEstadoEstudianteestadoEstucianteId().getEstadoEstudianteNombre().equalsIgnoreCase(estado_search)) {
                        searched++;
                    }
//                    if (matricula.getFuenteIngresofuenteIngresoId().getFuenteIngresoNombre().equalsIgnoreCase(fuente_ingreso_search)) {
//                        searched++;
//                    }
                }
            }
            if (searched > 0) {
                if (matricula == null)
                    matricula = serv_estudiante.findByEstudianteUltimaFecha(estudiante.getEstudianteId());
                EstudianteSearch estudianteSearch = EstudianteSearch.builder()
                        .searchTimes(searched)
                        .estudiante(estudiante)
                        .facultadCumCarreraEstudiante(matricula)
                        .build();
                listEstudiantesSearched.add(estudianteSearch);
            }
        }
        FacesContext context = FacesContext.getCurrentInstance();
        if (!listEstudiantesSearched.isEmpty()) {
            Collections.sort(listEstudiantesSearched);
            Collections.reverse(listEstudiantesSearched);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, texts.getSearchResults() + listEstudiantesSearched.size() + " estudiantes que coinciden con los criterios de búsqueda.", ""));
            founded = true;
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, texts.getNoSearchResults(), ""));
            founded = false;
        }
    }

    public String translateAreaEstudiante(EstudianteSearch estudianteSearch) {
        if (estudianteSearch.getFacultadCumCarreraEstudiante().getFacultadCumCarrera().getFacultadCum().getCum() != null)
            return estudianteSearch.getFacultadCumCarreraEstudiante().getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum();
        else
            return estudianteSearch.getFacultadCumCarreraEstudiante().getFacultadCumCarrera().getFacultadCum().getFacultad().getNombrearea();


    }

    public String translateCarreraEstudiante(EstudianteSearch estudianteSearch) {
        return estudianteSearch.getFacultadCumCarreraEstudiante().getFacultadCumCarrera().getCarrera().getCarreranacionalidcarreranacional().getNombrecarreranacional();
    }

    public String translateEstadoEstudiante(EstudianteSearch estudianteSearch) {
        return estudianteSearch.getFacultadCumCarreraEstudiante().getEstadoEstudianteestadoEstucianteId().getEstadoEstudianteNombre();
    }

    public String translateEstadoEstudianteToColor(EstudianteSearch estudianteSearch) {
        int type = estudianteSearch.getFacultadCumCarreraEstudiante().getEstadoEstudianteestadoEstucianteId().getEstadoEstucianteId();
        String[] colors = {
                "#C0392B",
                "#9B59B6",
                "#2980B9",
                "#1ABC9C",
                "#27AE60",
                "#F1C40F",
                "#E67E22",
                "#34495E"
        };

        return (type <= colors.length) ? colors[type] : "#FFFFFF";
    }

}
