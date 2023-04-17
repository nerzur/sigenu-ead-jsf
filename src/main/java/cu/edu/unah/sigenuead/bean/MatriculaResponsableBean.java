package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.*;
import cu.edu.unah.sigenuead.service.AsignaturaServices;
import cu.edu.unah.sigenuead.service.EstudianteServices;
import cu.edu.unah.sigenuead.service.MatriculaServices;
import cu.edu.unah.sigenuead.service.TipoAsignaturaServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.primefaces.model.DualListModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
@Data
public class MatriculaResponsableBean implements Serializable {

    private DualListModel<Asignatura> list_asignaturas;
    private List<Asignatura> list_asignaturasSource = new ArrayList<Asignatura>();
    private List<Asignatura> list_asignaturasTarget = new ArrayList<Asignatura>();
    private List<Asignatura> list_asignaturasTarget1 = new ArrayList<Asignatura>();
    private List<TipoAsignatura> listTipoAsignatura = new ArrayList<TipoAsignatura>();
    private PieChartModel pieModel;
    private boolean do_search;

    private final EstudianteServices serv_estudiante = new EstudianteServices();
    private final AsignaturaServices serv_asignatura = new AsignaturaServices();
    private final MatriculaServices serv_matricula = new MatriculaServices();
    private final TipoAsignaturaServices servTipoAsignatura = new TipoAsignaturaServices();

    private String id;
    private String nombre_estudiante;
    private Estudiante estudiante;

    public void clear() {
        id = "";
        nombre_estudiante = "";
        estudiante = null;
        do_search = false;
    }

    public void init() {
        list_asignaturasSource = new ArrayList<Asignatura>();
        list_asignaturasTarget = new ArrayList<Asignatura>();
        listTipoAsignatura = servTipoAsignatura.findAllTipoAsignatura();
        if (id != null && !id.equals("")) {
            list_asignaturasSource = serv_asignatura.findAsignaturasParaMatricular(id);
            if (list_asignaturasSource != null) {
                list_asignaturasTarget = serv_asignatura.findAsignaturaMatriculadasByEstudiante(id);
                if (list_asignaturasTarget != null) {
                    list_asignaturasTarget1 = serv_asignatura.findAsignaturaMatriculadasByEstudiante(id);
                } else {
                    do_search = false;
                }
            } else {
                do_search = false;
            }

            if (list_asignaturasSource == null) {
                list_asignaturasSource = new ArrayList<Asignatura>();
            }
            if (list_asignaturasTarget == null) {
                list_asignaturasTarget = new ArrayList<Asignatura>();
            }
        } else
            do_search = false;
        list_asignaturas = new DualListModel<Asignatura>(list_asignaturasSource, list_asignaturasTarget);

        initChart();
    }

    public void initChart() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(serv_estudiante.findAsignaturasMatriculadas(id));
        values.add(serv_estudiante.findAsignaturasPorMatricular(id));
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Aprobadas");
        labels.add("Por Aprobar");

        data.setLabels(labels);
        pieModel.setData(data);
    }

    public void search() {
        estudiante = serv_estudiante.findEstudianteById(id);
        FacultadCumCarreraEstudiante f = serv_estudiante.findByEstudianteUltimaFecha(id);
        if (f == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ud. no cuenta con los privilegios suficientes para ver los datos de ese estudiante", "Error"));
            return;
        }
        if (estudiante == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe un estudiante en el sistema con esa ID", "Error"));
            do_search = false;
            return;
        }
        nombre_estudiante = estudiante.getEstudianteNombre() + " " + estudiante.getEstudianteApellido1() + " " + estudiante.getEstudianteApellido2();
        do_search = true;
        init();
    }

    public void update() {
        Par submit = serv_matricula.addMatriculaResponsable(estudiante, list_asignaturasTarget1, list_asignaturas.getTarget());
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }

    public String translateAsignaturaTypeToColor(int type) {
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
