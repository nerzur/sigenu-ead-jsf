package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.service.CursoServices;
import cu.edu.unah.sigenuead.service.EstudianteServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import org.jfree.util.Log;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;

import java.io.Serializable;
import java.util.*;

@SessionScoped
@Named
@Data
public class IndexBean implements Serializable {

    private int estudiantesActivos = 0;
    private int estudiantesBaja = 0;
    private int estudiantesEgresados = 0;
    private String cursoActual;
    private int carrerasActivas = 0;
    private int totalEstudiantes = 0;

    private BarChartModel barModel;
    private PolarAreaChartModel polarAreaModel;
    private List<Estudiante> estudianteList;
    private Map<String, Integer> estudiantesByArea = new HashMap<>();
    private Map<String, Integer> estudiantesByCarrera = new HashMap<>();

    private final String[] BG_COLORS = {
            "rgba(255, 99, 132, 0.2)",
            "rgba(255, 159, 64, 0.2)",
            "rgba(255, 205, 86, 0.2)",
            "rgba(75, 192, 192, 0.2)",
            "rgba(54, 162, 235, 0.2)",
            "rgba(153, 102, 255, 0.2)",
            "rgba(201, 203, 207, 0.2)"
    };

    private final String[] BORDER_COLORS = {
            "rgb(255, 99, 132)",
            "rgb(255, 159, 64)",
            "rgb(255, 205, 86)",
            "rgb(75, 192, 192)",
            "rgb(54, 162, 235)",
            "rgb(153, 102, 255)",
            "rgb(201, 203, 207)"
    };


    private final EstudianteServices estudianteServices = new EstudianteServices();
    private final CursoServices cursoServices = new CursoServices();

    public void init() {
        estudianteList = estudianteServices.findEstudianteEntities();
        estudiantesActivos = 0;
        estudiantesBaja = 0;
        estudiantesEgresados = 0;
        carrerasActivas = 0;
        totalEstudiantes = 0;
        searchWorks();
        createBarModel();
        createPolarAreaModel();
    }

    private void searchWorks() {
        estudiantesByArea = new HashMap<>();
        estudiantesByCarrera = new HashMap<>();
        totalEstudiantes = estudianteList.size();
        cursoActual = cursoServices.findCursoActual().getIdcurso();
        estudianteList.forEach(estudiante -> {
            estudiante.getFacultadCumCarreraEstudianteList().forEach(facultadCumCarreraEstudiante -> {
                String area = !facultadCumCarreraEstudiante.getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum().equals("")
                        ? facultadCumCarreraEstudiante.getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum()
                        : facultadCumCarreraEstudiante.getFacultadCumCarrera().getFacultadCum().getFacultad().getNombrearea();
                String carrera = facultadCumCarreraEstudiante.getFacultadCumCarrera().getCarrera().getCarreranacionalidcarreranacional().getNombrecarreranacional();
                if (estudiantesByArea.get(area) == null)
                    estudiantesByArea.put(area, 1);
                else {
                    int cant = estudiantesByArea.get(area);
                    estudiantesByArea.replace(area, cant + 1);
                }
                if (estudiantesByCarrera.get(carrera) == null)
                    estudiantesByCarrera.put(carrera, 1);
                else {
                    int cant = estudiantesByCarrera.get(carrera);
                    estudiantesByCarrera.replace(carrera, cant + 1);
                }
                String estado = facultadCumCarreraEstudiante.getEstadoEstudianteestadoEstucianteId().getEstadoEstudianteNombre();
                switch (estado.toLowerCase()) {
                    case "baja" -> estudiantesBaja++;
                    case "egresado" -> estudiantesEgresados++;
                    case "activo" -> estudiantesActivos++;
                }
            });
        });
    }

    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Estudiantes Matriculados por Área");

        List<Number> values = new ArrayList<>(estudiantesByArea.values());
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>(Arrays.asList(BG_COLORS));
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>(Arrays.asList(BORDER_COLORS));
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>(estudiantesByArea.keySet());
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
//        title.setText("Bar Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
//        Animation animation = new Animation();
//        animation.setDuration(0);
//        options.setAnimation(animation);
//
        barModel.setOptions(options);
    }

    private void createPolarAreaModel() {
        polarAreaModel = new PolarAreaChartModel();
        ChartData data = new ChartData();

        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
        List<Number> values = new ArrayList<>(estudiantesByCarrera.values());
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>(Arrays.asList(BG_COLORS));
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>(estudiantesByCarrera.keySet());
        data.setLabels(labels);

        polarAreaModel.setData(data);
    }
}
