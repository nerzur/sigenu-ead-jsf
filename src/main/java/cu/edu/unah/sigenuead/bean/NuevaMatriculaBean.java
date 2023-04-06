package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.*;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.primefaces.event.FlowEvent;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SessionScoped
@Named
@Data
public class NuevaMatriculaBean implements Serializable {

    private String id_find;
    private String id;
    private Date FechaNacimiento;
    private int CantHijos;
    private String Nombre;
    private String Apellido1;
    private String Apellido2;
    private String Telefono;
    private String Direccion;
    private String Email;
    boolean DatosLaborales;
    float Salario;
    private String JefeInmediato;
    private String nombrecentrotrabajo;
    private String direccioncentrotrabajo;
    private String telefonocentrotrabajo;
    private String ProvinciaCentroTrabajo;
    private String MunicipioCentroTrabajo;
    private String organismoidorganismo;
    private String sindicatoidsindicato;
    private String ocupacionocupacionId;
    boolean DatosMilitares;
    private Date FechaLicenciamientoMilitar;
    private String gradoMilitar;
    private String especialidadMilitar;
    private String tipoMilitar;
    private String naturalde;
    private String sexo;
    private String colorPiel;
    private String organizacionPolitica;
    private String huerfano;
    private String estadoCivil;
    private String procedenciaEscolar;
    private String carrera;
    private String nivelEscolar;
    private String estadoEstudiante;
    private String fuenteIngreso;
    private String planestudio;
    private String centrotrabajo;
    Float notaMat;
    Float notaEsp;
    Float notaHist;
    Float PromedioIngreso;
    private Integer escalafonIngreso;
    private Date FechaMatricula;
    private Date FechaIngresoEs;
    private Date FechaIngresoCes;
    private Integer CarreraOpcion;
    boolean CarreraReoferta;
    private String facultad;
    private String cum;
    private String municipio;
    private String provincia;
    private String pais;
    private List<String> organizacionPopularList;
    private List<String> minusvaliaList;
    private List<String> ongList;
    private String nombreTutor1;
    private String apellidosTutor1;
    private Float salarioTutor1;
    private boolean fallecidoTutor1;
    private String nivelEscolarTutor1;
    private String ocupacionTutor1;
    private String organizacionPoliticaTutor1;
    private String sexoTutor1;
    private String nombreTutor2;
    private String apellidosTutor2;
    private Float salarioTutor2;
    private boolean fallecidoTutor2;
    private String nivelEscolarTutor2;
    private String ocupacionTutor2;
    private String organizacionPoliticaTutor2;
    private String sexoTutor2;

    private boolean skip = false;
    private boolean founded = false;
    private boolean state = false;
    private boolean pais_selected = false;

    private String[] selected_minusvalia;
    private String[] selected_org_popular;
    private String[] selected_ong;

    /**
     * List from DB
     */
    private List<String> sexo_list = new ArrayList<String>();
    private List<String> color_piel_list = new ArrayList<String>();
    private List<String> organizacion_politica_list = new ArrayList<String>();
    private List<String> huerfano_list = new ArrayList<String>();
    private List<String> estado_civil_list = new ArrayList<String>();
    private List<String> municipio_list = new ArrayList<String>();
    private List<String> provincia_list = new ArrayList<String>();
    private List<String> pais_list = new ArrayList<String>();
    private List<String> organizacion_popular_list = new ArrayList<String>();
    private List<String> minusvalia_list = new ArrayList<String>();
    private List<String> ONG_list = new ArrayList<String>();

    private List<String> nivel_escolar_list = new ArrayList<String>();
    private List<String> ocupacion_list = new ArrayList<String>();

    private List<String> procedencia_escolar_list = new ArrayList<String>();
    private List<String> fuente_ingreso_list = new ArrayList<String>();

    private List<String> facultad_list = new ArrayList<String>();
    private List<String> carrera_list = new ArrayList<String>();
    private List<String> cum_list = new ArrayList<String>();
    private List<String> estado_estudiante_list = new ArrayList<String>();
    private List<String> plan_estudio_list = new ArrayList<String>();

    private List<String> organismo_list = new ArrayList<String>();
    private List<String> municipio_org_list = new ArrayList<String>();
    private List<String> provincia_org_list = new ArrayList<String>();
    private List<String> sindicato_org_list = new ArrayList<String>();

    private List<String> especialidad_militar_list = new ArrayList<String>();
    private List<String> tipo_militar_list = new ArrayList<String>();
    private List<String> grado_militar_list = new ArrayList<String>();

    /**
     * Services Access
     *
     */
    final EstudianteServices serv_estudiante = new EstudianteServices();
    final SexoServices serv_sexo = new SexoServices();
    final ColorPielServices serv_color_piel = new ColorPielServices();
    final OrganizacionPoliticaServices ser_organizacion_politca = new OrganizacionPoliticaServices();
    final HuerfanoServices serv_huerfanos = new HuerfanoServices();
    final EstadoCivilServices serv_estado_civil = new EstadoCivilServices();
    final MunicipioServices serv_municipio = new MunicipioServices();
    final ProvinciaServices serv_provincia = new ProvinciaServices();
    final PaisServices serv_pais = new PaisServices();
    final OrganizacionPopularServices serv_organizacion_popular = new OrganizacionPopularServices();
    final MinusvaliaServices serv_minusvalia = new MinusvaliaServices();
    final OngServices serv_ong = new OngServices();

    final NivelEscolarServices serv_nivel_escolar = new NivelEscolarServices();
    final OcupacionServices serv_ocupacion = new OcupacionServices();

    final ProcedenciaEscolarServices serv_procedencia_escolar = new ProcedenciaEscolarServices();
    final FuenteIngresoServices serv_fuente_ingreso = new FuenteIngresoServices();

    final FacultadServices serv_facultad = new FacultadServices();
    final CarreraServices serv_carrera = new CarreraServices();
    final CumServices serv_cum = new CumServices();
    final EstadoEstudianteServices serv_estado_estudiante = new EstadoEstudianteServices();
    final PlanestudioServices serv_plan_estudio = new PlanestudioServices();

    final OrganismoServices serv_organismo = new OrganismoServices();
    final SindicatoServices serv_sindicato = new SindicatoServices();

    final EspecialidadMilitarServices serv_especialidad_militar = new EspecialidadMilitarServices();
    final TipoMilitarServices serv_tipo_militar = new TipoMilitarServices();
    final GradoMilitarServices serv_grado_militar = new GradoMilitarServices();

    /**
     * Matricular Estudiante
     *
     */
    public void init() {
        cleanVariables();
    }

    public void cleanVariables() {
        Date fecha = new Date(System.currentTimeMillis());
        Date fecha_actual = new Date(System.currentTimeMillis());
        fecha.setYear(fecha.getYear() - 18);

        founded = false;
        DatosLaborales = false;
        DatosMilitares = false;
        id = "";
        FechaNacimiento = fecha;
        CantHijos = 0;
        Nombre = "";
        Apellido1 = "";
        Apellido2 = "";
        Telefono = "";
        Direccion = "";
        Email = "";
        naturalde = "";
        sexo = "";
        colorPiel = "";
        organizacionPolitica = "";
        huerfano = "";
        estadoCivil = "";
        procedenciaEscolar = "";
        carrera = "";
        nivelEscolar = "";
        estadoEstudiante = "Nueva Matrícula";
        fuenteIngreso = "";
        planestudio = "";
        centrotrabajo = "";
        notaMat = (float) 0;
        notaEsp = (float) 0;
        notaHist = (float) 0;
        PromedioIngreso = (float) 0;
        escalafonIngreso = 0;
        FechaMatricula = fecha_actual;
        FechaIngresoEs = fecha_actual;
        FechaIngresoCes = fecha_actual;
        CarreraOpcion = 1;
        facultad = "";
        cum = "";
        municipio = "";
        provincia = "";
        pais = "";
        nombreTutor1 = "";
        apellidosTutor1 = "";
        salarioTutor1 = (float) 0;
        nivelEscolarTutor1 = "";
        ocupacionTutor1 = "";
        organizacionPoliticaTutor1 = "";
        sexoTutor1 = "";
        fallecidoTutor1 = false;
        nombreTutor2 = "";
        apellidosTutor2 = "";
        salarioTutor2 = (float) 0;
        nivelEscolarTutor2 = "";
        ocupacionTutor2 = "";
        organizacionPoliticaTutor2 = "";
        sexoTutor2 = "";
        fallecidoTutor2 = false;
        CarreraReoferta = false;
        Salario = 0;
        JefeInmediato = "";
        nombrecentrotrabajo = "";
        direccioncentrotrabajo = "";
        telefonocentrotrabajo = "";
        ProvinciaCentroTrabajo = "";
        MunicipioCentroTrabajo = "";
        organismoidorganismo = "";
        sindicatoidsindicato = "";
        ocupacionocupacionId = "";
        FechaLicenciamientoMilitar = fecha_actual;
        gradoMilitar = "";
        especialidadMilitar = "";
        tipoMilitar = "";
        state = false;
    }

    public void updatebirthday() {
        if (id!=null && id.length() == 11) {
            int year = Integer.parseInt("" + id.charAt(0) + "" + id.charAt(1));
            int month = Integer.parseInt("" + id.charAt(2) + "" + id.charAt(3)) - 1;
            int date = Integer.parseInt("" + id.charAt(4) + "" + id.charAt(5));
            FechaNacimiento = new Date(year, month, date);
        }
    }

    public void searchEstudiante() {
        Estudiante estudiante = serv_estudiante.findEstudianteById(id_find);
        if (estudiante == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe un estudiante en el sistema con esa ID", "Error"));
            founded = false;
            return;
        }
        founded = true;
        DatosLaborales = estudiante.getEstudianteDatosLaborales();
        DatosMilitares = estudiante.getEstudianteDatosMilitares();
        id = estudiante.getEstudianteId();
        updatebirthday();
        CantHijos = estudiante.getEstudianteCantHijos();
        Nombre = estudiante.getEstudianteNombre();
        Apellido1 = estudiante.getEstudianteApellido1();
        Apellido2 = estudiante.getEstudianteApellido2();
        if (estudiante.getEstudianteTelefono() != null) {
            Telefono = estudiante.getEstudianteTelefono();
        }
        Direccion = estudiante.getEstudianteDireccion();
        if (estudiante.getEstudianteEmail() != null) {
            Email = estudiante.getEstudianteEmail();
        }
        naturalde = estudiante.getNaturalde();
        sexo = estudiante.getSexosexoId().getSexoNombre();
        colorPiel = estudiante.getColorPielcolorPielId().getColorPielNombre();
        organizacionPolitica = estudiante.getOrganizacionPoliticaorganizacionPoliticaId().getOrganizacionPoliticaNombre();
        huerfano = estudiante.getHuerfanohuerfanoId().getHuerfanoNombre();
        estadoCivil = estudiante.getEstadoCivilestadoCivilId().getEstadoCivilNombre();
        procedenciaEscolar = estudiante.getProcedenciaEscolarprocedenciaEscolarId().getProcedenciaEscolarNombre();
        FacultadCumCarreraEstudiante f = serv_estudiante.findByEstudianteUltimaFecha(id);
        nivelEscolar = estudiante.getNivelEscolarnivelEscolarId().getNivelEscolarNombre();
        if (f != null) {
            carrera = f.getFacultadCumCarrera().getCarrera().getCarreranacionalidcarreranacional().getNombrecarreranacional();
            estadoEstudiante = f.getEstadoEstudianteestadoEstucianteId().getEstadoEstudianteNombre();

            fuenteIngreso = f.getFuenteIngresofuenteIngresoId().getFuenteIngresoNombre();
            planestudio = f.getPlanestudioidplanestudio().getTipoplanestudionombretipoplanestudio().getNombretipoplanestudio();
            notaMat = f.getNotaMat();
            notaEsp = f.getNotaEspanol();
            notaHist = f.getNotaHistoria();
            PromedioIngreso = f.getPromedioIngreso();
            escalafonIngreso = f.getEscalafon();
            FechaMatricula = f.getFacultadCumCarreraEstudiantePK().getFechaMatricula();
            CarreraOpcion = f.getCarreraOpcion();
            facultad = f.getFacultadCumCarrera().getFacultadCum().getFacultad().getNombrearea();
            CarreraReoferta = f.getCarreraReoferta();
            if (f.getFacultadCumCarrera() != null) {
                cum = f.getFacultadCumCarrera().getFacultadCum().getCum().getNombrecum();
            }
            if (estadoEstudiante.equals("Baja") || estadoEstudiante.equals("Egresado")) {
                estadoEstudiante = "Nueva Matrícula";
                carrera = "";
                fuenteIngreso = "";
                planestudio = "";
                notaMat = (float) 0;
                notaEsp = (float) 0;
                notaHist = (float) 0;
                PromedioIngreso = (float) 0;
                escalafonIngreso = 0;
                FechaMatricula = new Date(System.currentTimeMillis());
                CarreraOpcion = 1;
                facultad = "";
                CarreraReoferta = false;
                if (f.getFacultadCumCarrera() != null) {
                    cum = "";
                }
                procedenciaEscolar = "";
                nivelEscolar = "";
                state = false;
            }
        }
//        centrotrabajo = estudiante.getNombrecentrotrabajo(); //innecesario
        FechaIngresoEs = estudiante.getEstudianteFechaIngresoEs();
        FechaIngresoCes = estudiante.getEstudianteFechaIngresoCes();

        if (estudiante.getMunicipioidmunicipio() != null) {
            municipio = estudiante.getMunicipioidmunicipio().getNombremunicipio();
            provincia = estudiante.getMunicipioidmunicipio().getProvinciaidprovincia().getNombreprovincia();
        }
        pais = estudiante.getPaisidpais().getNombrepais();
        nombreTutor1 = estudiante.getTutorList().get(0).getNombreTutor();
        apellidosTutor1 = estudiante.getTutorList().get(0).getApellidosTutor();
        salarioTutor1 = estudiante.getTutorList().get(0).getSalarioTutor();
        nivelEscolarTutor1 = estudiante.getTutorList().get(0).getNivelEscolarnivelEscolarId().getNivelEscolarNombre();
        ocupacionTutor1 = estudiante.getTutorList().get(0).getOcupacionocupacionId().getOcupacionNombre();
        organizacionPoliticaTutor1 = estudiante.getTutorList().get(0).getOrganizacionPoliticaorganizacionPoliticaId().getOrganizacionPoliticaNombre();
        sexoTutor1 = "";
        fallecidoTutor1 = estudiante.getTutorList().get(0).getFallecido();
        nombreTutor2 = estudiante.getTutorList().get(1).getNombreTutor();
        apellidosTutor2 = estudiante.getTutorList().get(1).getApellidosTutor();
        salarioTutor2 = estudiante.getTutorList().get(1).getSalarioTutor();
        nivelEscolarTutor2 = estudiante.getTutorList().get(1).getNivelEscolarnivelEscolarId().getNivelEscolarNombre();
        ocupacionTutor2 = estudiante.getTutorList().get(1).getOcupacionocupacionId().getOcupacionNombre();
        organizacionPoliticaTutor2 = estudiante.getTutorList().get(1).getOrganizacionPoliticaorganizacionPoliticaId().getOrganizacionPoliticaNombre();
        sexoTutor2 = "";
        fallecidoTutor2 = estudiante.getTutorList().get(1).getFallecido();

        if (DatosLaborales) {
            Salario = estudiante.getEstudianteSalario();
            JefeInmediato = estudiante.getEstudianteJefeInmediato();
            nombrecentrotrabajo = estudiante.getNombrecentrotrabajo();
            direccioncentrotrabajo = estudiante.getDireccioncentrotrabajo();
            telefonocentrotrabajo = estudiante.getTelefonocentrotrabajo();
            ProvinciaCentroTrabajo = estudiante.getMunicipioidmunicipiocentrotrabajo().getProvinciaidprovincia().getNombreprovincia(); //no me queda claro
            MunicipioCentroTrabajo = estudiante.getMunicipioidmunicipiocentrotrabajo().getNombremunicipio();
            organismoidorganismo = estudiante.getOrganismoidorganismo().getNombreorganismo();
            sindicatoidsindicato = estudiante.getSindicatoidsindicato().getNombresindicato();
            ocupacionocupacionId = estudiante.getOcupacionocupacionId().getOcupacionNombre();

        }
        if (DatosMilitares) {
            FechaLicenciamientoMilitar = estudiante.getEstudianteFechaLicenciamientoMilitar();
            gradoMilitar = estudiante.getGradoMilitargradoMilitarId().getGradoMilitarNombre();
            especialidadMilitar = estudiante.getEspecialidadMilitarespecialidadMilitarId().getEspecialidadMilitarNombre();
            tipoMilitar = estudiante.getTipoMilitaridTipoMilitar().getNombreTipo();
        }

        selected_minusvalia = new String[estudiante.getMinusvaliaList().size()];
        selected_ong = new String[estudiante.getOngList().size()];
        selected_org_popular = new String[estudiante.getOrganizacionPopularList().size()];
        for (int i = 0; i < estudiante.getMinusvaliaList().size(); i++) {
            selected_minusvalia[i] = estudiante.getMinusvaliaList().get(i).getMinusvaliaNombre();
        }
        for (int i = 0; i < estudiante.getOngList().size(); i++) {
            selected_ong[i] = estudiante.getOngList().get(i).getOngNombre();
        }
        for (int i = 0; i < estudiante.getOrganizacionPopularList().size(); i++) {
            selected_org_popular[i] = estudiante.getOrganizacionPopularList().get(i).getOrganizacionPopularNombre();
        }
    }

    public void loadElementsStep1() {
        sexo_list = new ArrayList<String>();
        color_piel_list = new ArrayList<String>();
        organizacion_politica_list = new ArrayList<String>();
        huerfano_list = new ArrayList<String>();
        estado_civil_list = new ArrayList<String>();
        pais_list = new ArrayList<String>();
        organizacion_popular_list = new ArrayList<String>();
        minusvalia_list = new ArrayList<String>();
        ONG_list = new ArrayList<String>();
        nivel_escolar_list = new ArrayList<String>();
        ocupacion_list = new ArrayList<String>();
        procedencia_escolar_list = new ArrayList<String>();
        fuente_ingreso_list = new ArrayList<String>();

        sexo_list = serv_sexo.findSexoAvailable();
        color_piel_list = serv_color_piel.findColorPielAvailable();
        organizacion_politica_list = ser_organizacion_politca.findOrganizacionPoliticaAvailable();
        huerfano_list = serv_huerfanos.findHuerfanoAvailable();
        estado_civil_list = serv_estado_civil.findEstadoCivilAvailable();
        pais_list = serv_pais.findPaisAvailable();
        organizacion_popular_list = serv_organizacion_popular.findOrganizacionPopularAvailable();
        minusvalia_list = serv_minusvalia.findMinusvaliaAvailable();
        ONG_list = serv_ong.findOngAvailable();
        nivel_escolar_list = serv_nivel_escolar.findNivelEscolarAvailable();
        ocupacion_list = serv_ocupacion.findOcupacionAvailable();
        procedencia_escolar_list = serv_procedencia_escolar.findProcedenciaEscolarAvailable();
        fuente_ingreso_list = serv_fuente_ingreso.findFuenteIngresoAvailable();
        facultad_list = serv_facultad.findFacultadAvailables();
        estado_estudiante_list = serv_estado_estudiante.findEstadoEstudianteAvailable();
        organismo_list = serv_organismo.findOrganismoAvailable();
        sindicato_org_list = serv_sindicato.findSindicatoAvailable();
        especialidad_militar_list = serv_especialidad_militar.findEspecialidadMilitarAvailable();
        tipo_militar_list = serv_tipo_militar.findTipoMilitarAvailable();
        grado_militar_list = serv_grado_militar.findGradoMilitarAvailable();

        if (procedencia_escolar_list == null) {
            procedencia_escolar_list = new ArrayList<String>();
            procedencia_escolar_list.add("Procedencia Escolar");
        }
    }

    public void updateMunicipioStep1() {
        municipio_list = new ArrayList<String>();
        if (provincia != null && !provincia.equals("")) {
            municipio_list = serv_municipio.findMunicipioAvailable(provincia, pais);
        }
    }

    public void updateMunicipioStep5() {
        municipio_org_list = new ArrayList<String>();
        if (ProvinciaCentroTrabajo != null && !ProvinciaCentroTrabajo.equals("")) {
            municipio_org_list = serv_municipio.findMunicipioAvailable(ProvinciaCentroTrabajo, "Cuba");
        }
        if (municipio_org_list == null) {
            municipio_org_list = new ArrayList<String>();
        }

    }

    public void updateProvinciaStep1() {
        provincia_list = new ArrayList<String>();
        if (pais != null && !pais.equals("")) {
            provincia_list = serv_provincia.findProvinciaAvailable(pais);
        }
        if (!founded) {
            provincia = "";
            municipio = "";
        }
        updateMunicipioStep1();
        verifyCubaSelectedMunicipio();
    }

    public void updateProvinciaStep5() {
        provincia_org_list = new ArrayList<String>();
        provincia_org_list = serv_provincia.findProvinciaAvailable("Cuba");
        if (provincia_org_list == null) {
            provincia_org_list = new ArrayList<String>();
        }
    }

    public void updateCUMStep4() {
        cum_list = new ArrayList<String>();
        if (facultad != null && !facultad.equals("")) {
            cum_list = serv_cum.findCumAvailablesbyFacultad(facultad);
        }
        if (cum_list == null) { //Verificar esto que esta devolviendo null
            cum_list = new ArrayList<String>();
        }
    }

    public void updateCarreraStep4() {
        carrera_list = new ArrayList<String>();
        if (facultad != null && !facultad.equals("")) {
            if (cum == null) {
                cum = "";
            }
            carrera_list = serv_carrera.findCarreraAvailableByFacultadCum(facultad, cum);
        }
    }

    public void updatePlanEstudioStep4() {
        plan_estudio_list = new ArrayList<String>();
        if (facultad != null && !facultad.equals("") && carrera != null && !carrera.equals("") && FechaMatricula != null) {
            //plan_estudio_list = serv_plan_estudio.findPlanestudioAvailableByCarrera(facultad, carrera); //esta devolviendo 0
            plan_estudio_list = serv_plan_estudio.findPlanestudioAvailableByCarreraFecha(facultad, carrera, FechaMatricula);
        }
    }

    public void verifyCubaSelected() {
        if (pais != null && pais.equals("Cuba")) {
            pais_selected = true;
        } else {
            pais_selected = false;
        }
    }

    public void verifyCubaSelectedMunicipio() {
        if (pais != null && pais.equals("Cuba") && provincia != null && !provincia.equals("")) {
            pais_selected = true;
        } else {
            pais_selected = false;
        }
    }

    public String translateBoolean() {
        if (pais_selected == true) {
            return "false";
        }
        return "true";
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void matricularEstudiante() {
        FacesContext context = FacesContext.getCurrentInstance();
        Par submit = new Par(1, "");
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        if (!founded) {
            submit = serv_estudiante.MatricularEstudiante(id, FechaNacimiento, CantHijos, Nombre, Apellido1, Apellido2, Telefono, Direccion, Email, DatosLaborales, Salario, JefeInmediato, nombrecentrotrabajo, direccioncentrotrabajo, telefonocentrotrabajo, ProvinciaCentroTrabajo, MunicipioCentroTrabajo, organismoidorganismo, sindicatoidsindicato, ocupacionocupacionId, DatosMilitares, FechaLicenciamientoMilitar, gradoMilitar, especialidadMilitar, tipoMilitar, naturalde, sexo, colorPiel, organizacionPolitica, huerfano, estadoCivil, procedenciaEscolar, carrera, nivelEscolar, estadoEstudiante, fuenteIngreso, planestudio, notaMat, notaEsp, notaHist, PromedioIngreso, escalafonIngreso, FechaMatricula, FechaIngresoEs, FechaIngresoCes, CarreraOpcion, CarreraReoferta, facultad, cum, municipio, provincia, pais, selected_org_popular, selected_minusvalia, selected_ong, nombreTutor1, "", salarioTutor1, fallecidoTutor1, nivelEscolarTutor1, ocupacionTutor1, organizacionPoliticaTutor1, sexoTutor1, nombreTutor2, "", salarioTutor2, fallecidoTutor2, nivelEscolarTutor2, ocupacionTutor2, organizacionPoliticaTutor2, sexoTutor2);
        } else {
            submit = serv_estudiante.editEstudiante(id, FechaNacimiento, CantHijos, Nombre, Apellido1, Apellido2, Telefono, Direccion, Email, DatosLaborales, Salario, JefeInmediato, nombrecentrotrabajo, direccioncentrotrabajo, telefonocentrotrabajo, ProvinciaCentroTrabajo, MunicipioCentroTrabajo, organismoidorganismo, sindicatoidsindicato, ocupacionocupacionId, DatosMilitares, FechaLicenciamientoMilitar, gradoMilitar, especialidadMilitar, tipoMilitar, naturalde, sexo, colorPiel, organizacionPolitica, huerfano, estadoCivil, procedenciaEscolar, carrera, nivelEscolar, estadoEstudiante, fuenteIngreso, planestudio, notaMat, notaEsp, notaHist, PromedioIngreso, escalafonIngreso, FechaMatricula, FechaIngresoEs, FechaIngresoCes, CarreraOpcion, CarreraReoferta, facultad, cum, municipio, provincia, pais, selected_org_popular, selected_minusvalia, selected_ong, nombreTutor1, "", salarioTutor1, fallecidoTutor1, nivelEscolarTutor1, ocupacionTutor1, organizacionPoliticaTutor1, sexoTutor1, nombreTutor2, "", salarioTutor2, fallecidoTutor2, nivelEscolarTutor2, ocupacionTutor2, organizacionPoliticaTutor2, sexoTutor2);
        }
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
//        if (submit.getFacesType() == FacesMessage.SEVERITY_WARN || submit.getFacesType() == FacesMessage.SEVERITY_ERROR) {
//            try {
//                FacesContext.getCurrentInstance().getExternalContext().redirect(direccion + "/pages/matricula/NuevaMatricula.faces");
//            } catch (IOException ex) {
//                Logger.getLogger(NuevaMatricula.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return;
//        }
//
//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect(direccion + "/pages/index.faces");
//        } catch (IOException ex) {
//            Logger.getLogger(NuevaMatricula.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public String translateBoolean(boolean value) {
        if (value == true) {
            return "Sí";
        }
        return "No";
    }

    public String translateboolean(boolean b) {
        if (b) {
            return "true";
        } else {
            return "false";
        }
    }

    public String TranslateDate(Date date) {
        if (date != null) {
            return new SimpleDateFormat("dd'/'MM'/'yyyy", new Locale("es", "ES")).format(date);
        }
        return "No definido";
    }

    public String buttonvalue() {
        if (founded) {
            return "Actualizar datos";
        } else {
            return "Matricular";
        }
    }

    public void disable_pos() {
        if (founded && !(estadoEstudiante.equals("Baja") || estadoEstudiante.equals("Egresado") || estadoEstudiante.equals("Nueva Matrícula"))) {
            state = true;
        }
    }
}
