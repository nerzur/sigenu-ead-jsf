/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.AsignaturaJpaController;
import cu.edu.unah.sigenuead.controller.BajaJpaController;
import cu.edu.unah.sigenuead.controller.CarreraJpaController;
import cu.edu.unah.sigenuead.controller.CarreranacionalJpaController;
import cu.edu.unah.sigenuead.controller.ColorPielJpaController;
import cu.edu.unah.sigenuead.controller.CumJpaController;
import cu.edu.unah.sigenuead.controller.CursoJpaController;
import cu.edu.unah.sigenuead.controller.EspecialidadJpaController;
import cu.edu.unah.sigenuead.controller.EspecialidadMilitarJpaController;
import cu.edu.unah.sigenuead.controller.EstadoCivilJpaController;
import cu.edu.unah.sigenuead.controller.EstadoEstudianteJpaController;
import cu.edu.unah.sigenuead.controller.EstudianteJpaController;
import cu.edu.unah.sigenuead.controller.ExamenJpaController;
import cu.edu.unah.sigenuead.controller.ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumCarreraEstudianteAsignaturaJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumCarreraEstudianteJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumCarreraJpaController;
import cu.edu.unah.sigenuead.controller.FacultadJpaController;
import cu.edu.unah.sigenuead.controller.FuenteIngresoJpaController;
import cu.edu.unah.sigenuead.controller.GradoMilitarJpaController;
import cu.edu.unah.sigenuead.controller.HuerfanoJpaController;
import cu.edu.unah.sigenuead.controller.MatriculaFacultadCumCarreraEstudianteAsignaturaJpaController;
import cu.edu.unah.sigenuead.controller.MatriculaJpaController;
import cu.edu.unah.sigenuead.controller.MinusvaliaJpaController;
import cu.edu.unah.sigenuead.controller.MotivoBajaJpaController;
import cu.edu.unah.sigenuead.controller.MunicipioJpaController;
import cu.edu.unah.sigenuead.controller.NivelEscolarJpaController;
import cu.edu.unah.sigenuead.controller.OcupacionJpaController;
import cu.edu.unah.sigenuead.controller.OngJpaController;
import cu.edu.unah.sigenuead.controller.OrganismoJpaController;
import cu.edu.unah.sigenuead.controller.OrganizacionPoliticaJpaController;
import cu.edu.unah.sigenuead.controller.OrganizacionPopularJpaController;
import cu.edu.unah.sigenuead.controller.PaisJpaController;
import cu.edu.unah.sigenuead.controller.PlanestudioJpaController;
import cu.edu.unah.sigenuead.controller.ProcedenciaEscolarJpaController;
import cu.edu.unah.sigenuead.controller.ProvinciaJpaController;
import cu.edu.unah.sigenuead.controller.SexoJpaController;
import cu.edu.unah.sigenuead.controller.SindicatoJpaController;
import cu.edu.unah.sigenuead.controller.TipoBajaJpaController;
import cu.edu.unah.sigenuead.controller.TipoMilitarJpaController;
import cu.edu.unah.sigenuead.controller.TutorJpaController;
import cu.edu.unah.sigenuead.controller.UniversidadJpaController;
import cu.edu.unah.sigenuead.entity.Asignatura;
import cu.edu.unah.sigenuead.entity.Asignatura_Nota;
import cu.edu.unah.sigenuead.entity.Baja;
import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.ColorPiel;
import cu.edu.unah.sigenuead.entity.Cum;
import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.EspecialidadMilitar;
import cu.edu.unah.sigenuead.entity.EstadoCivil;
import cu.edu.unah.sigenuead.entity.EstadoEstudiante;
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.Examen;
import cu.edu.unah.sigenuead.entity.ExamenMatriculaFacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK;
import cu.edu.unah.sigenuead.entity.Facultad;
import cu.edu.unah.sigenuead.entity.FacultadCumCarrera;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudianteAsignaturaPK;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiantePK;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraPK;
import cu.edu.unah.sigenuead.entity.FuenteIngreso;
import cu.edu.unah.sigenuead.entity.GradoMilitar;
import cu.edu.unah.sigenuead.entity.Huerfano;
import cu.edu.unah.sigenuead.entity.Matricula;
import cu.edu.unah.sigenuead.entity.MatriculaFacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.MatriculaFacultadCumCarreraEstudianteAsignaturaPK;
import cu.edu.unah.sigenuead.entity.Minusvalia;
import cu.edu.unah.sigenuead.entity.MotivoBaja;
import cu.edu.unah.sigenuead.entity.Municipio;
import cu.edu.unah.sigenuead.entity.NivelEscolar;
import cu.edu.unah.sigenuead.entity.Ocupacion;
import cu.edu.unah.sigenuead.entity.Ong;
import cu.edu.unah.sigenuead.entity.Organismo;
import cu.edu.unah.sigenuead.entity.OrganizacionPolitica;
import cu.edu.unah.sigenuead.entity.OrganizacionPopular;
import cu.edu.unah.sigenuead.entity.Pais;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Planestudio;
import cu.edu.unah.sigenuead.entity.ProcedenciaEscolar;
import cu.edu.unah.sigenuead.entity.Provincia;
import cu.edu.unah.sigenuead.entity.Sexo;
import cu.edu.unah.sigenuead.entity.Sindicato;
import cu.edu.unah.sigenuead.entity.TipoBaja;
import cu.edu.unah.sigenuead.entity.TipoMilitar;
import cu.edu.unah.sigenuead.entity.Tutor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;
import cu.edu.unah.sigenuead.util.userLoging;
import cu.edu.unah.sigenuead.service.MatriculaServices;

/**
 *
 * @author claudy
 */
public class EstudianteServices {

    private EstudianteJpaController controllerEstudiante;

    public EstudianteJpaController getInstanceOfEstudiante() {
        return (controllerEstudiante == null) ? controllerEstudiante = new EstudianteJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEstudiante;
    }
    private CarreraJpaController controllerCarrera;

    public CarreraJpaController getInstanceOfCarrera() {
        return (controllerCarrera == null) ? controllerCarrera = new CarreraJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCarrera;
    }

    private CarreranacionalJpaController controllerCarreranacional;

    public CarreranacionalJpaController getInstanceOfCarreranacional() {
        return (controllerCarreranacional == null) ? controllerCarreranacional = new CarreranacionalJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCarreranacional;
    }
    private EspecialidadJpaController controllerEspecialidad;

    public EspecialidadJpaController getInstanceOfEspecialidad() {
        return (controllerEspecialidad == null) ? controllerEspecialidad = new EspecialidadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEspecialidad;
    }

    private FacultadJpaController controllerFacultad;

    public FacultadJpaController getInstanceOfFacultad() {
        return (controllerFacultad == null) ? controllerFacultad = new FacultadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultad;
    }

    private MunicipioJpaController controllerMunicipio;

    public MunicipioJpaController getInstanceOfMunicipio() {
        return (controllerMunicipio == null) ? controllerMunicipio = new MunicipioJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerMunicipio;
    }
    private ProvinciaJpaController controllerProvincia;

    public ProvinciaJpaController getInstanceOfProvincia() {
        return (controllerProvincia == null) ? controllerProvincia = new ProvinciaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerProvincia;
    }
    private PaisJpaController controllerPais;

    public PaisJpaController getInstanceOfPais() {
        return (controllerPais == null) ? controllerPais = new PaisJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerPais;
    }
    private OrganismoJpaController controllerOrganismo;

    public OrganismoJpaController getInstanceOfOrganismo() {
        return (controllerOrganismo == null) ? controllerOrganismo = new OrganismoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerOrganismo;
    }
    private SindicatoJpaController controllerSindicato;

    public SindicatoJpaController getInstanceOfSindicato() {
        return (controllerSindicato == null) ? controllerSindicato = new SindicatoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerSindicato;
    }
    private OcupacionJpaController controllerOcupacion;

    public OcupacionJpaController getInstanceOfOcupacion() {
        return (controllerOcupacion == null) ? controllerOcupacion = new OcupacionJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerOcupacion;
    }
    private GradoMilitarJpaController controllerGradoMilitar;

    public GradoMilitarJpaController getInstanceOfGradoMilitar() {
        return (controllerGradoMilitar == null) ? controllerGradoMilitar = new GradoMilitarJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerGradoMilitar;
    }
    private EspecialidadMilitarJpaController controllerEspecialidadMilitar;

    public EspecialidadMilitarJpaController getInstanceOfEspecialidadMilitar() {
        return (controllerEspecialidadMilitar == null) ? controllerEspecialidadMilitar = new EspecialidadMilitarJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEspecialidadMilitar;
    }
    private TipoMilitarJpaController controllerTipoMilitar;

    public TipoMilitarJpaController getInstanceOfTipoMilitar() {
        return (controllerTipoMilitar == null) ? controllerTipoMilitar = new TipoMilitarJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerTipoMilitar;
    }

    private SexoJpaController controllerSexo;

    public SexoJpaController getInstanceOfSexo() {
        return (controllerSexo == null) ? controllerSexo = new SexoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerSexo;
    }
    private ColorPielJpaController controllerColorPiel;

    public ColorPielJpaController getInstanceOfColorPiel() {
        return (controllerColorPiel == null) ? controllerColorPiel = new ColorPielJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerColorPiel;
    }
    private OrganizacionPoliticaJpaController controllerOrganizacionPolitica;

    public OrganizacionPoliticaJpaController getInstanceOfOrganizacionPolitica() {
        return (controllerOrganizacionPolitica == null) ? controllerOrganizacionPolitica = new OrganizacionPoliticaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerOrganizacionPolitica;
    }

    private HuerfanoJpaController controllerHuerfano;

    public HuerfanoJpaController getInstanceOfHuerfano() {
        return (controllerHuerfano == null) ? controllerHuerfano = new HuerfanoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerHuerfano;
    }
    private EstadoCivilJpaController controllerEstadoCivil;

    public EstadoCivilJpaController getInstanceOfEstadoCivil() {
        return (controllerEstadoCivil == null) ? controllerEstadoCivil = new EstadoCivilJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEstadoCivil;
    }
    private ProcedenciaEscolarJpaController controllerProcedenciaEscolar;

    public ProcedenciaEscolarJpaController getInstanceOfProcedenciaEscolar() {
        return (controllerProcedenciaEscolar == null) ? controllerProcedenciaEscolar = new ProcedenciaEscolarJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerProcedenciaEscolar;
    }

    private UniversidadJpaController controllerUniversidad;

    public UniversidadJpaController getInstanceOfUniversidad() {
        return (controllerUniversidad == null) ? controllerUniversidad = new UniversidadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerUniversidad;
    }

    private CumJpaController controllerCum;

    public CumJpaController getInstanceOfCum() {
        return (controllerCum == null) ? controllerCum = new CumJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCum;
    }

    private FacultadCumCarreraJpaController controllerFacultadCumCarrera;

    public FacultadCumCarreraJpaController getInstanceOfFacultadCumCarrera() {
        return (controllerFacultadCumCarrera == null) ? controllerFacultadCumCarrera = new FacultadCumCarreraJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultadCumCarrera;
    }
    private FacultadCumCarreraEstudianteJpaController controllerFacultadCumCarreraEstudiante;

    public FacultadCumCarreraEstudianteJpaController getInstanceOfFacultadCumCarreraEstudiante() {
        return (controllerFacultadCumCarreraEstudiante == null) ? controllerFacultadCumCarreraEstudiante = new FacultadCumCarreraEstudianteJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultadCumCarreraEstudiante;
    }
    private FacultadCumCarreraEstudianteAsignaturaJpaController controllerFacultadCumCarreraEstudianteAsignatura;

    public FacultadCumCarreraEstudianteAsignaturaJpaController getInstanceOfFacultadCumCarreraEstudianteAsignatura() {
        return (controllerFacultadCumCarreraEstudianteAsignatura == null) ? controllerFacultadCumCarreraEstudianteAsignatura = new FacultadCumCarreraEstudianteAsignaturaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultadCumCarreraEstudianteAsignatura;
    }
    private NivelEscolarJpaController controllerNivelEscolar;

    public NivelEscolarJpaController getInstanceOfNivelEscolar() {
        return (controllerNivelEscolar == null) ? controllerNivelEscolar = new NivelEscolarJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerNivelEscolar;
    }

    private EstadoEstudianteJpaController controllerEstadoEstudiante;

    public EstadoEstudianteJpaController getInstanceOfEstadoEstudiante() {
        return (controllerEstadoEstudiante == null) ? controllerEstadoEstudiante = new EstadoEstudianteJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEstadoEstudiante;
    }

    private FuenteIngresoJpaController controllerFuenteIngreso;

    public FuenteIngresoJpaController getInstanceOfFuenteIngreso() {
        return (controllerFuenteIngreso == null) ? controllerFuenteIngreso = new FuenteIngresoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFuenteIngreso;
    }

    private PlanestudioJpaController controllerPlanestudio;

    public PlanestudioJpaController getInstanceOfPlanestudio() {
        return (controllerPlanestudio == null) ? controllerPlanestudio = new PlanestudioJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerPlanestudio;
    }

    private OrganizacionPopularJpaController controllerOrganizacionPopular;

    public OrganizacionPopularJpaController getInstanceOfOrganizacionPopular() {
        return (controllerOrganizacionPopular == null) ? controllerOrganizacionPopular = new OrganizacionPopularJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerOrganizacionPopular;
    }

    private MinusvaliaJpaController controllerMinusvalia;

    public MinusvaliaJpaController getInstanceOfMinusvalia() {
        return (controllerMinusvalia == null) ? controllerMinusvalia = new MinusvaliaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerMinusvalia;
    }

    private OngJpaController controllerOng;

    public OngJpaController getInstanceOfOng() {
        return (controllerOng == null) ? controllerOng = new OngJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerOng;
    }

    private TutorJpaController controllerTutor;

    public TutorJpaController getInstanceOfTutor() {
        return (controllerTutor == null) ? controllerTutor = new TutorJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerTutor;
    }

    private AsignaturaJpaController controllerAsignatura;

    public AsignaturaJpaController getInstanceOfAsignatura() {
        return (controllerAsignatura == null) ? controllerAsignatura = new AsignaturaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerAsignatura;
    }

    private CursoJpaController controllerCurso;

    public CursoJpaController getInstanceOfCurso() {
        return (controllerCurso == null) ? controllerCurso = new CursoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCurso;
    }

    private MatriculaJpaController controllerMatricula;

    public MatriculaJpaController getInstanceOfMatricula() {
        return (controllerMatricula == null) ? controllerMatricula = new MatriculaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerMatricula;
    }

    private ExamenJpaController controllerExamen;

    public ExamenJpaController getInstanceOfExamen() {
        return (controllerExamen == null) ? controllerExamen = new ExamenJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerExamen;
    }
    private MatriculaFacultadCumCarreraEstudianteAsignaturaJpaController controllerMatriculaFacultadCumCarreraEstudianteAsignatura;

    public MatriculaFacultadCumCarreraEstudianteAsignaturaJpaController getInstanceOfMatriculaFacultadCumCarreraEstudianteAsignatura() {
        return (controllerMatriculaFacultadCumCarreraEstudianteAsignatura == null) ? controllerMatriculaFacultadCumCarreraEstudianteAsignatura = new MatriculaFacultadCumCarreraEstudianteAsignaturaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerMatriculaFacultadCumCarreraEstudianteAsignatura;
    }
    private ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaJpaController controllerExamenMatriculaFacultadCumCarreraEstudianteAsignatura;

    public ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaJpaController getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura() {
        return (controllerExamenMatriculaFacultadCumCarreraEstudianteAsignatura == null) ? controllerExamenMatriculaFacultadCumCarreraEstudianteAsignatura = new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerExamenMatriculaFacultadCumCarreraEstudianteAsignatura;
    }

    MatriculaServices ms = new MatriculaServices();

    public Par MatricularEstudiante(
            String id,
            Date FechaNacimiento,
            int CantHijos,
            String Nombre,
            String Apellido1,
            String Apellido2,
            String Telefono,
            String Direccion,
            String Email,
            boolean DatosLaborales,
            float Salario,
            String JefeInmediato,
            String nombrecentrotrabajo,
            String direccioncentrotrabajo,
            String telefonocentrotrabajo,
            String ProvinciaCentroTrabajo,
            String MunicipioCentroTrabajo,
            String organismoidorganismo,
            String sindicatoidsindicato,
            String ocupacionocupacionId,
            boolean DatosMilitares,
            Date FechaLicenciamientoMilitar,
            String gradoMilitar,
            String especialidadMilitar,
            String tipoMilitar,
            String naturalde,
            String sexo,
            String colorPiel,
            String organizacionPolitica,
            String huerfano,
            String estadoCivil,
            String procedenciaEscolar,
            String carrera,
            String nivelEscolar,
            String estadoEstudiante,
            String fuenteIngreso,
            String planestudio,
            Float notaMat,
            Float notaEsp,
            Float notaHist,
            Float PromedioIngreso,
            int escalafonIngreso,
            Date FechaMatricula,
            Date FechaIngresoEs,
            Date FechaIngresoCes,
            Integer CarreraOpcion,
            boolean CarreraReoferta,
            String facultad,
            String cum,
            String municipio,
            String provincia,
            String pais,
            String[] organizacionPopularList,
            String[] minusvaliaList,
            String[] ongList,
            String nombreTutor1,
            String apellidosTutor1,
            Float salarioTutor1,
            boolean fallecidoTutor1,
            String nivelEscolarTutor1,
            String ocupacionTutor1,
            String organizacionPoliticaTutor1,
            String sexoTutor1,
            String nombreTutor2,
            String apellidosTutor2,
            Float salarioTutor2,
            boolean fallecidoTutor2,
            String nivelEscolarTutor2,
            String ocupacionTutor2,
            String organizacionPoliticaTutor2,
            String sexoTutor2
    ) {
        sexoTutor1 = "Femenino";
        sexoTutor2 = "Masculino";
        Estudiante e = getInstanceOfEstudiante().findEstudiante(id);
        try {
            if (e == null) {
                e = new Estudiante(id);
                e.setEstudianteFechaNacimiento(FechaNacimiento);
                e.setEstudianteCantHijos(CantHijos);
                e.setEstudianteNombre(Nombre);
                e.setEstudianteApellido1(Apellido1);
                e.setEstudianteApellido2(Apellido2);
                e.setEstudianteTelefono(Telefono);
                e.setEstudianteDireccion(Direccion);
                e.setEstudianteEmail(Email);
                e.setEstudianteDatosLaborales(DatosLaborales);
                if (DatosLaborales) {
                    e.setEstudianteJefeInmediato(JefeInmediato);
                    e.setEstudianteSalario(Salario);
                    e.setNombrecentrotrabajo(nombrecentrotrabajo);
                    e.setDireccioncentrotrabajo(direccioncentrotrabajo);
                    e.setTelefonocentrotrabajo(telefonocentrotrabajo);
                    Pais p = getInstanceOfPais().findPaisByNombre("Cuba");
                    if (p == null) {
                        return new Par(2, texts.getPaisNull());
                    }
                    Provincia prov = getInstanceOfProvincia().findProvinciaByNombreAndPais(ProvinciaCentroTrabajo, p.getIdpais());
                    if (prov == null) {
                        return new Par(2, texts.getProvinciaNull());
                    }
                    Municipio mun = getInstanceOfMunicipio().findMunicipioByNombreAndPais(MunicipioCentroTrabajo, prov.getIdprovincia(), p.getIdpais());
                    if (mun == null) {
                        return new Par(2, texts.getMunicipioNull());
                    }
                    e.setMunicipioidmunicipiocentrotrabajo(mun);
                    Organismo organismo = getInstanceOfOrganismo().findOrganismoByNombreAvailable(organismoidorganismo);
                    if (organismo == null) {
                        return new Par(2, texts.getOrganismoNull());
                    }
                    e.setOrganismoidorganismo(organismo);
                    Sindicato sindicato = getInstanceOfSindicato().findSindicatoByNombreAvailable(sindicatoidsindicato);
                    if (sindicato == null) {
                        return new Par(2, texts.getSindicatoNull());
                    }
                    e.setSindicatoidsindicato(sindicato);
                    Ocupacion ocupacion = getInstanceOfOcupacion().findOcupacionByNombreAvailable(ocupacionocupacionId);
                    if (ocupacion == null) {
                        return new Par(2, texts.getOcupacionNull());
                    }
                    e.setOcupacionocupacionId(ocupacion);
                }
                e.setEstudianteDatosMilitares(DatosMilitares);
                if (DatosMilitares) {
                    e.setEstudianteFechaLicenciamientoMilitar(FechaLicenciamientoMilitar);
                    GradoMilitar gm = getInstanceOfGradoMilitar().findGradoMilitarByNombreAvailable(gradoMilitar);
                    if (gm == null) {
                        return new Par(2, texts.getGradoMilitarNull());
                    }
                    e.setGradoMilitargradoMilitarId(gm);
                    EspecialidadMilitar em = getInstanceOfEspecialidadMilitar().findEspecialidadMilitarByNombreAvailable(especialidadMilitar);
                    if (em == null) {
                        return new Par(2, texts.getEspeciladadMilitarNull());
                    }
                    e.setEspecialidadMilitarespecialidadMilitarId(em);
                    TipoMilitar tm = getInstanceOfTipoMilitar().findTipoMilitarByNombreAvailable(tipoMilitar);
                    if (tm == null) {
                        return new Par(2, texts.getTipoMilitarNull());
                    }
                    e.setTipoMilitaridTipoMilitar(tm);
                }
                e.setNaturalde(naturalde);
                Sexo sx = getInstanceOfSexo().findSexoByNombreAvailable(sexo);
                if (sx == null) {
                    return new Par(2, texts.getSexoNull());
                }
                e.setSexosexoId(sx);
                ColorPiel cpiel = getInstanceOfColorPiel().findColorPielByNombreAvailable(colorPiel);
                if (cpiel == null) {
                    return new Par(2, texts.getColorPielNull());
                }
                e.setColorPielcolorPielId(cpiel);
                OrganizacionPolitica opolitica = getInstanceOfOrganizacionPolitica().findOrganizacionPoliticaByNombreAvailable(organizacionPolitica);
                if (opolitica == null) {
                    return new Par(2, texts.getOrganizacionPoliticaNull());
                }
                e.setOrganizacionPoliticaorganizacionPoliticaId(opolitica);
                Huerfano h = getInstanceOfHuerfano().findHuerfanoByNombreAvailable(huerfano);
                if (h == null) {
                    return new Par(2, texts.getHuerfanoNull());
                }
                e.setHuerfanohuerfanoId(h);
                EstadoCivil ecivil = getInstanceOfEstadoCivil().findEstadoCivilByNombreAvailable(estadoCivil);
                if (ecivil == null) {
                    return new Par(2, texts.getEstadoCivilNull());
                }
                e.setEstadoCivilestadoCivilId(ecivil);
                ProcedenciaEscolar pescolar = getInstanceOfProcedenciaEscolar().findProcedenciaEscolarByNombreAvailable(procedenciaEscolar);
                if (pescolar == null) {
                    return new Par(2, texts.getProcedenciaEscolarNull());
                }
                e.setProcedenciaEscolarprocedenciaEscolarId(pescolar);
                Facultad fac = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
                if (fac == null) {
                    return new Par(2, texts.getFacultadNull());
                }
                Carrera carr = getInstanceOfCarrera().findCarreraByFacultadCarreranacionalAvailable(facultad, carrera);
                if (carr == null) {
                    return new Par(2, texts.getCarreraNull());
                }
                Cum cu = getInstanceOfCum().findCumByNombreAvailable(cum);
                if (cu == null) {
                    return new Par(2, texts.getCumNull());
                }
                FacultadCumCarrera fcc;
                if (cu == null) {
                    fcc = getInstanceOfFacultadCumCarrera().findFacultadCumCarrera(new FacultadCumCarreraPK(null, fac.getCodigoarea(), carr.getIdcarrera()));
                } else {
                    fcc = getInstanceOfFacultadCumCarrera().findFacultadCumCarrera(new FacultadCumCarreraPK(cu.getCodigocum(), fac.getCodigoarea(), carr.getIdcarrera()));
                }
                if (fcc == null || fcc.getCancelado()) {
                    return new Par(2, texts.getFacultadCumNull());
                }
                NivelEscolar nescolar = getInstanceOfNivelEscolar().findNivelEscolarByNombreAvailable(nivelEscolar);
                if (nescolar == null) {
                    return new Par(2, texts.getNivelEscolarNull());
                }
                e.setNivelEscolarnivelEscolarId(nescolar);
                e.setEstudianteFechaIngresoEs(FechaIngresoEs);
                e.setEstudianteFechaIngresoCes(FechaIngresoCes);
                Pais paisd = getInstanceOfPais().findPaisByNombre(pais);
                if (paisd == null) {
                    return new Par(2, texts.getPaisNull());
                }
                e.setPaisidpais(paisd);
                if (pais.equals("Cuba")) {
                    Provincia provd = getInstanceOfProvincia().findProvinciaByNombreAndPaisAvailable(provincia, paisd.getIdpais());
                    if (provd == null) {
                        return new Par(2, texts.getProvinciaNull());
                    }
                    e.setProvinciaidprovincia(provd);
                    Municipio mund = getInstanceOfMunicipio().findMunicipioByNombreAndPaisAvailable(municipio, provd.getIdprovincia(), paisd.getIdpais());
                    if (mund == null) {
                        return new Par(2, texts.getMunicipioNull());
                    }
                    e.setMunicipioidmunicipio(mund);
                }
                List<OrganizacionPopular> oplist = new ArrayList<OrganizacionPopular>();
                for (int i = 0; i < organizacionPopularList.length; i++) {
                    OrganizacionPopular op = getInstanceOfOrganizacionPopular().findOrganizacionPopularByNombreAvailable(organizacionPopularList[i]);
                    if (op != null) {
                        oplist.add(op);
                    }
                }
                e.setOrganizacionPopularList(oplist);
                List<Minusvalia> minuslist = new ArrayList<Minusvalia>();
                for (int i = 0; i < minusvaliaList.length; i++) {
                    Minusvalia min = getInstanceOfMinusvalia().findMinusvaliaByNombreAvailable(minusvaliaList[i]);
                    if (min != null) {
                        minuslist.add(min);
                    }
                }
                e.setMinusvaliaList(minuslist);
                List<Ong> ongl = new ArrayList<Ong>();
                for (int i = 0; i < ongList.length; i++) {
                    Ong o = getInstanceOfOng().findOngByNombreAvailable(ongList[i]);
                    if (o != null) {
                        ongl.add(o);
                    }
                }
                e.setOngList(ongl);
                getInstanceOfEstudiante().create(e);
                Tutor tutor1 = new Tutor();
                tutor1.setNombreTutor(nombreTutor1);
                tutor1.setApellidosTutor(apellidosTutor1);
                tutor1.setSalarioTutor(salarioTutor1);
                OrganizacionPolitica opt1 = getInstanceOfOrganizacionPolitica().findOrganizacionPoliticaByNombreAvailable(organizacionPoliticaTutor1);
                if (opt1 == null) {
                    return new Par(2, texts.getOrganizacionPoliticaNull());
                }
                tutor1.setOrganizacionPoliticaorganizacionPoliticaId(opt1);
                NivelEscolar net1 = getInstanceOfNivelEscolar().findNivelEscolarByNombreAvailable(nivelEscolarTutor1);
                if (net1 == null) {
                    return new Par(2, texts.getNivelEscolarNull());
                }
                tutor1.setNivelEscolarnivelEscolarId(net1);
                Ocupacion ot1 = getInstanceOfOcupacion().findOcupacionByNombreAvailable(ocupacionTutor1);
                if (ot1 == null) {
                    return new Par(2, texts.getOcupacionNull());
                }
                tutor1.setOcupacionocupacionId(ot1);
                tutor1.setFallecido(fallecidoTutor1);
                Sexo sxt1 = getInstanceOfSexo().findSexoByNombreAvailable(sexoTutor1);
                if (sxt1 == null) {
                    return new Par(2, texts.getSexoNull());
                }
                tutor1.setSexosexoId(sxt1);
                tutor1.setEstudianteestudianteId(e);
                getInstanceOfTutor().create(tutor1);
                Tutor tutor2 = new Tutor();
                tutor2.setNombreTutor(nombreTutor2);
                tutor2.setApellidosTutor(apellidosTutor2);
                tutor2.setSalarioTutor(salarioTutor2);
                OrganizacionPolitica opt2 = getInstanceOfOrganizacionPolitica().findOrganizacionPoliticaByNombreAvailable(organizacionPoliticaTutor2);
                if (opt2 == null) {
                    return new Par(2, texts.getOrganizacionPoliticaNull());
                }
                tutor2.setOrganizacionPoliticaorganizacionPoliticaId(opt2);
                NivelEscolar net2 = getInstanceOfNivelEscolar().findNivelEscolarByNombreAvailable(nivelEscolarTutor2);
                if (net2 == null) {
                    return new Par(2, texts.getNivelEscolarNull());
                }
                tutor2.setNivelEscolarnivelEscolarId(net2);
                Ocupacion ot2 = getInstanceOfOcupacion().findOcupacionByNombreAvailable(ocupacionTutor2);
                if (ot2 == null) {
                    return new Par(2, texts.getOcupacionNull());
                }
                tutor2.setOcupacionocupacionId(ot2);
                tutor2.setFallecido(fallecidoTutor2);
                Sexo sxt2 = getInstanceOfSexo().findSexoByNombreAvailable(sexoTutor2);
                if (sxt2 == null) {
                    return new Par(2, texts.getSexoNull());
                }
                tutor2.setSexosexoId(sxt2);
                tutor2.setEstudianteestudianteId(e);
                getInstanceOfTutor().create(tutor2);
                FacultadCumCarreraEstudiante fcce = new FacultadCumCarreraEstudiante(cu.getCodigocum(), fac.getCodigoarea(), carr.getIdcarrera(), e.getEstudianteId(), FechaMatricula);
                fcce.setCarreraOpcion(CarreraOpcion);
                fcce.setCarreraReoferta(CarreraReoferta);
                fcce.setEscalafon(escalafonIngreso);
                EstadoEstudiante eest = getInstanceOfEstadoEstudiante().findEstadoEstudianteByNombreAvailable(estadoEstudiante);
                if (eest == null) {
                    return new Par(2, texts.getEstadoEstudianteNull());
                }
                fcce.setEstadoEstudianteestadoEstucianteId(eest);
                fcce.setEstudiante(e);
                fcce.setFacultadCumCarrera(fcc);
                fcce.setNotaEspanol(notaEsp);
                fcce.setNotaHistoria(notaHist);
                fcce.setNotaMat(notaMat);
                Planestudio pestudio = getInstanceOfPlanestudio().findPlanEstudioByCarreraAvailableCursoActivado(fac.getCodigoarea(), carrera, planestudio, FechaMatricula);
                if (pestudio == null) {
                    return new Par(2, texts.getPlanEstudioNull());
                }
                fcce.setPlanestudioidplanestudio(pestudio);
                fcce.setPromedioIngreso(PromedioIngreso);
                FuenteIngreso fing = getInstanceOfFuenteIngreso().findFuenteIngresoByNombreAvailable(fuenteIngreso);
                if (fing == null) {
                    return new Par(2, texts.getFuenteIngresoNull());
                }
                fcce.setFuenteIngresofuenteIngresoId(fing);
                getInstanceOfFacultadCumCarreraEstudiante().create(fcce);
                ms.relacionarAsignaturasEstudiantes(facultad, carrera, planestudio, id);
                return new Par(1, texts.getSatisfactorio());
            }
            return new Par(3, texts.getInformacion());
        } catch (Exception ex) {
            Logger.getLogger(EstudianteServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editEstudiante(
            String id,
            Date FechaNacimiento,
            int CantHijos,
            String Nombre,
            String Apellido1,
            String Apellido2,
            String Telefono,
            String Direccion,
            String Email,
            boolean DatosLaborales,
            float Salario,
            String JefeInmediato,
            String nombrecentrotrabajo,
            String direccioncentrotrabajo,
            String telefonocentrotrabajo,
            String ProvinciaCentroTrabajo,
            String MunicipioCentroTrabajo,
            String organismoidorganismo,
            String sindicatoidsindicato,
            String ocupacionocupacionId,
            boolean DatosMilitares,
            Date FechaLicenciamientoMilitar,
            String gradoMilitar,
            String especialidadMilitar,
            String tipoMilitar,
            String naturalde,
            String sexo,
            String colorPiel,
            String organizacionPolitica,
            String huerfano,
            String estadoCivil,
            String procedenciaEscolar,
            String carrera,
            String nivelEscolar,
            String estadoEstudiante,
            String fuenteIngreso,
            String planestudio,
            Float notaMat,
            Float notaEsp,
            Float notaHist,
            Float PromedioIngreso,
            int escalafonIngreso,
            Date FechaMatricula,
            Date FechaIngresoEs,
            Date FechaIngresoCes,
            Integer CarreraOpcion,
            boolean CarreraReoferta,
            String facultad,
            String cum,
            String municipio,
            String provincia,
            String pais,
            String[] organizacionPopularList,
            String[] minusvaliaList,
            String[] ongList,
            String nombreTutor1,
            String apellidosTutor1,
            Float salarioTutor1,
            boolean fallecidoTutor1,
            String nivelEscolarTutor1,
            String ocupacionTutor1,
            String organizacionPoliticaTutor1,
            String sexoTutor1,
            String nombreTutor2,
            String apellidosTutor2,
            Float salarioTutor2,
            boolean fallecidoTutor2,
            String nivelEscolarTutor2,
            String ocupacionTutor2,
            String organizacionPoliticaTutor2,
            String sexoTutor2
    ) {
        Estudiante e = getInstanceOfEstudiante().findEstudiante(id);
        try {
            if (e != null) {
                sexoTutor1 = "Femenino";
                sexoTutor2 = "Masculino";
                e.setEstudianteFechaNacimiento(FechaNacimiento);
                e.setEstudianteCantHijos(CantHijos);
                e.setEstudianteNombre(Nombre);
                e.setEstudianteApellido1(Apellido1);
                e.setEstudianteApellido2(Apellido2);
                e.setEstudianteTelefono(Telefono);
                e.setEstudianteDireccion(Direccion);
                e.setEstudianteEmail(Email);
                e.setEstudianteDatosLaborales(DatosLaborales);
                if (DatosLaborales) {
                    e.setEstudianteJefeInmediato(JefeInmediato);
                    e.setEstudianteSalario(Salario);
                    e.setNombrecentrotrabajo(nombrecentrotrabajo);
                    e.setDireccioncentrotrabajo(direccioncentrotrabajo);
                    e.setTelefonocentrotrabajo(telefonocentrotrabajo);
                    Pais p = getInstanceOfPais().findPaisByNombre("Cuba");
                    if (p == null) {
                        return new Par(2, texts.getPaisNull());
                    }
                    Provincia prov = getInstanceOfProvincia().findProvinciaByNombreAndPais(ProvinciaCentroTrabajo, p.getIdpais());
                    if (prov == null) {
                        return new Par(2, texts.getProvinciaNull());
                    }
                    Municipio mun = getInstanceOfMunicipio().findMunicipioByNombreAndPais(MunicipioCentroTrabajo, prov.getIdprovincia(), p.getIdpais());
                    if (mun == null) {
                        return new Par(2, texts.getMunicipioNull());
                    }
                    e.setMunicipioidmunicipiocentrotrabajo(mun);
                    Organismo organismo = getInstanceOfOrganismo().findOrganismoByNombreAvailable(organismoidorganismo);
                    if (organismo == null) {
                        return new Par(2, texts.getOrganismoNull());
                    }
                    e.setOrganismoidorganismo(organismo);
                    Sindicato sindicato = getInstanceOfSindicato().findSindicatoByNombreAvailable(sindicatoidsindicato);
                    if (sindicato == null) {
                        return new Par(2, texts.getSindicatoNull());
                    }
                    e.setSindicatoidsindicato(sindicato);
                    Ocupacion ocupacion = getInstanceOfOcupacion().findOcupacionByNombreAvailable(ocupacionocupacionId);
                    if (ocupacion == null) {
                        return new Par(2, texts.getOcupacionNull());
                    }
                    e.setOcupacionocupacionId(ocupacion);
                } else {
                    e.setEstudianteJefeInmediato(null);
                    e.setEstudianteSalario(null);
                    e.setNombrecentrotrabajo(null);
                    e.setDireccioncentrotrabajo(null);
                    e.setTelefonocentrotrabajo(null);
                    e.setMunicipioidmunicipiocentrotrabajo(null);
                    e.setOrganismoidorganismo(null);
                    e.setSindicatoidsindicato(null);
                    e.setOcupacionocupacionId(null);
                }
                e.setEstudianteDatosMilitares(DatosMilitares);
                if (DatosMilitares) {
                    e.setEstudianteFechaLicenciamientoMilitar(FechaLicenciamientoMilitar);
                    GradoMilitar gm = getInstanceOfGradoMilitar().findGradoMilitarByNombreAvailable(gradoMilitar);
                    if (gm == null) {
                        return new Par(2, texts.getGradoMilitarNull());
                    }
                    e.setGradoMilitargradoMilitarId(gm);
                    EspecialidadMilitar em = getInstanceOfEspecialidadMilitar().findEspecialidadMilitarByNombreAvailable(especialidadMilitar);
                    if (em == null) {
                        return new Par(2, texts.getEspeciladadMilitarNull());
                    }
                    e.setEspecialidadMilitarespecialidadMilitarId(em);
                    TipoMilitar tm = getInstanceOfTipoMilitar().findTipoMilitarByNombreAvailable(tipoMilitar);
                    if (tm == null) {
                        return new Par(2, texts.getTipoMilitarNull());
                    }
                    e.setTipoMilitaridTipoMilitar(tm);
                } else {
                    e.setEstudianteFechaLicenciamientoMilitar(null);
                    e.setGradoMilitargradoMilitarId(null);
                    e.setEspecialidadMilitarespecialidadMilitarId(null);
                    e.setTipoMilitaridTipoMilitar(null);
                }
                e.setNaturalde(naturalde);
                Sexo sx = getInstanceOfSexo().findSexoByNombreAvailable(sexo);
                if (sx == null) {
                    return new Par(2, texts.getSexoNull());
                }
                e.setSexosexoId(sx);
                ColorPiel cpiel = getInstanceOfColorPiel().findColorPielByNombreAvailable(colorPiel);
                if (cpiel == null) {
                    return new Par(2, texts.getColorPielNull());
                }
                e.setColorPielcolorPielId(cpiel);
                OrganizacionPolitica opolitica = getInstanceOfOrganizacionPolitica().findOrganizacionPoliticaByNombreAvailable(organizacionPolitica);
                if (opolitica == null) {
                    return new Par(2, texts.getOrganizacionPoliticaNull());
                }
                e.setOrganizacionPoliticaorganizacionPoliticaId(opolitica);
                Huerfano h = getInstanceOfHuerfano().findHuerfanoByNombreAvailable(huerfano);
                if (h == null) {
                    return new Par(2, texts.getHuerfanoNull());
                }
                e.setHuerfanohuerfanoId(h);
                EstadoCivil ecivil = getInstanceOfEstadoCivil().findEstadoCivilByNombreAvailable(estadoCivil);
                if (ecivil == null) {
                    return new Par(2, texts.getEstadoCivilNull());
                }
                e.setEstadoCivilestadoCivilId(ecivil);
                ProcedenciaEscolar pescolar = getInstanceOfProcedenciaEscolar().findProcedenciaEscolarByNombreAvailable(procedenciaEscolar);
                if (pescolar == null) {
                    return new Par(2, texts.getProcedenciaEscolarNull());
                }
                e.setProcedenciaEscolarprocedenciaEscolarId(pescolar);
                Facultad fac = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
                if (fac == null) {
                    return new Par(2, texts.getFacultadNull());
                }
                Carrera carr = getInstanceOfCarrera().findCarreraByFacultadCarreranacionalAvailable(facultad, carrera);
                if (carr == null) {
                    return new Par(2, texts.getCarreraNull());
                }
                Cum cu = getInstanceOfCum().findCumByNombreAvailable(cum);
                if (!cum.equals("") && cu == null) {
                    return new Par(2, texts.getCumNull());
                }
                FacultadCumCarrera fcc;
                fcc = getInstanceOfFacultadCumCarrera().findFacultadCumCarrera(new FacultadCumCarreraPK(cu.getCodigocum(), fac.getCodigoarea(), carr.getIdcarrera()));
                if (fcc == null) {
                    return new Par(2, texts.getFacultadCumNull());
                }

                NivelEscolar nescolar = getInstanceOfNivelEscolar().findNivelEscolarByNombreAvailable(nivelEscolar);
                if (nescolar == null) {
                    return new Par(2, texts.getNivelEscolarNull());
                }
                e.setNivelEscolarnivelEscolarId(nescolar);
                e.setEstudianteFechaIngresoEs(FechaIngresoEs);
                e.setEstudianteFechaIngresoCes(FechaIngresoCes);
                Pais paisd = getInstanceOfPais().findPaisByNombre(pais);
                if (paisd == null) {
                    return new Par(2, texts.getPaisNull());
                }
                e.setPaisidpais(paisd);
                if (pais.equals("Cuba")) {
                    Provincia provd = getInstanceOfProvincia().findProvinciaByNombreAndPaisAvailable(provincia, paisd.getIdpais());
                    if (provd == null) {
                        return new Par(2, texts.getProvinciaNull());
                    }
                    e.setProvinciaidprovincia(provd);
                    Municipio mund = getInstanceOfMunicipio().findMunicipioByNombreAndPaisAvailable(municipio, provd.getIdprovincia(), paisd.getIdpais());
                    if (mund == null) {
                        return new Par(2, texts.getMunicipioNull());
                    }
                    e.setMunicipioidmunicipio(mund);
                }
                List<OrganizacionPopular> oplist = new ArrayList<OrganizacionPopular>();
                for (int i = 0; i < organizacionPopularList.length; i++) {
                    OrganizacionPopular op = getInstanceOfOrganizacionPopular().findOrganizacionPopularByNombreAvailable(organizacionPopularList[i]);
                    if (op != null) {
                        oplist.add(op);
                    }
                }
                e.setOrganizacionPopularList(oplist);
                List<Minusvalia> minuslist = new ArrayList<Minusvalia>();
                for (int i = 0; i < minusvaliaList.length; i++) {
                    Minusvalia min = getInstanceOfMinusvalia().findMinusvaliaByNombreAvailable(minusvaliaList[i]);
                    if (min != null) {
                        minuslist.add(min);
                    }
                }
                e.setMinusvaliaList(minuslist);
                List<Ong> ongl = new ArrayList<Ong>();
                for (int i = 0; i < ongList.length; i++) {
                    Ong o = getInstanceOfOng().findOngByNombreAvailable(ongList[i]);
                    if (o != null) {
                        ongl.add(o);
                    }
                }
                e.setOngList(ongl);
                getInstanceOfEstudiante().edit(e);
                FacultadCumCarreraEstudiante fcce;
                fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteActivo(id);
                if (fcce == null) {
                    fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteNuevaMatricula(id);
                    if (fcce == null) {
                        fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteMatriculaPasiva(id);
                    }
                }
                boolean flag = false;
                if (fcce == null) {
                    fcce = new FacultadCumCarreraEstudiante();
                    flag = true;
                }
                fcce.setCarreraOpcion(CarreraOpcion);
                fcce.setCarreraReoferta(CarreraReoferta);
                fcce.setEscalafon(escalafonIngreso);
                EstadoEstudiante eest = getInstanceOfEstadoEstudiante().findEstadoEstudianteByNombreAvailable(estadoEstudiante);
                if (eest == null) {
                    return new Par(2, texts.getEstadoEstudianteNull());
                }
                fcce.setEstadoEstudianteestadoEstucianteId(eest);
                fcce.setEstudiante(e);
                fcce.setFacultadCumCarrera(fcc);
                fcce.setNotaEspanol(notaEsp);
                fcce.setNotaHistoria(notaHist);
                fcce.setNotaMat(notaMat);
                Planestudio pestudio = getInstanceOfPlanestudio().findPlanEstudioByCarreraAvailableCursoActivado(fac.getCodigoarea(), carrera, planestudio, FechaMatricula);
                if (pestudio == null) {
                    return new Par(2, texts.getPlanEstudioNull());
                }
                fcce.setPlanestudioidplanestudio(pestudio);
                fcce.setPromedioIngreso(PromedioIngreso);
                FuenteIngreso fing = getInstanceOfFuenteIngreso().findFuenteIngresoByNombreAvailable(fuenteIngreso);
                if (fing == null) {
                    return new Par(2, texts.getFuenteIngresoNull());
                }
                fcce.setFuenteIngresofuenteIngresoId(fing);
                getInstanceOfFacultadCumCarreraEstudiante().edit(fcce);
                if (flag) {
                    ms.relacionarAsignaturasEstudiantes(facultad, carrera, planestudio, id);
                }
                if (!e.getTutorList().isEmpty()) {
                    Tutor tutor1 = getInstanceOfTutor().findTutor(e.getTutorList().get(0).getTutorId());
                    tutor1.setNombreTutor(nombreTutor1);
                    tutor1.setApellidosTutor(apellidosTutor1);
                    tutor1.setSalarioTutor(salarioTutor1);
                    OrganizacionPolitica opt1 = getInstanceOfOrganizacionPolitica().findOrganizacionPoliticaByNombreAvailable(organizacionPoliticaTutor1);
                    if (opt1 == null) {
                        return new Par(2, texts.getOrganizacionPoliticaNull());
                    }
                    tutor1.setOrganizacionPoliticaorganizacionPoliticaId(opt1);
                    NivelEscolar net1 = getInstanceOfNivelEscolar().findNivelEscolarByNombreAvailable(nivelEscolarTutor1);
                    if (net1 == null) {
                        return new Par(2, texts.getNivelEscolarNull());
                    }
                    tutor1.setNivelEscolarnivelEscolarId(net1);
                    Ocupacion ot1 = getInstanceOfOcupacion().findOcupacionByNombreAvailable(ocupacionTutor1);
                    if (ot1 == null) {
                        return new Par(2, texts.getOcupacionNull());
                    }
                    tutor1.setOcupacionocupacionId(ot1);
                    tutor1.setFallecido(fallecidoTutor1);
                    Sexo sxt1 = getInstanceOfSexo().findSexoByNombreAvailable(sexoTutor1);
                    if (sxt1 == null) {
                        return new Par(2, texts.getSexoNull());
                    }
                    tutor1.setSexosexoId(sxt1);
                    tutor1.setEstudianteestudianteId(e);
                    getInstanceOfTutor().edit(tutor1);
                    Tutor tutor2 = getInstanceOfTutor().findTutor(e.getTutorList().get(1).getTutorId());
                    tutor2.setNombreTutor(nombreTutor2);
                    tutor2.setApellidosTutor(apellidosTutor2);
                    tutor2.setSalarioTutor(salarioTutor2);
                    OrganizacionPolitica opt2 = getInstanceOfOrganizacionPolitica().findOrganizacionPoliticaByNombreAvailable(organizacionPoliticaTutor2);
                    if (opt2 == null) {
                        return new Par(2, texts.getOrganizacionPoliticaNull());
                    }
                    tutor2.setOrganizacionPoliticaorganizacionPoliticaId(opt2);
                    NivelEscolar net2 = getInstanceOfNivelEscolar().findNivelEscolarByNombreAvailable(nivelEscolarTutor2);
                    if (net2 == null) {
                        return new Par(2, texts.getNivelEscolarNull());
                    }
                    tutor2.setNivelEscolarnivelEscolarId(net2);
                    Ocupacion ot2 = getInstanceOfOcupacion().findOcupacionByNombreAvailable(ocupacionTutor2);
                    if (ot2 == null) {
                        return new Par(2, texts.getOcupacionNull());
                    }
                    tutor2.setOcupacionocupacionId(ot2);
                    tutor2.setFallecido(fallecidoTutor2);
                    Sexo sxt2 = getInstanceOfSexo().findSexoByNombreAvailable(sexoTutor2);
                    if (sxt2 == null) {
                        return new Par(2, texts.getSexoNull());
                    }
                    tutor2.setSexosexoId(sxt2);
                    tutor2.setEstudianteestudianteId(e);
                    getInstanceOfTutor().edit(tutor2);
                }
                return new Par(1, texts.getSatisfactorio());
            }
            return new Par(3, texts.getInformacion());
        } catch (Exception ex) {
            Logger.getLogger(EstudianteServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par addNota(String examen, List<Asignatura_Nota> anl, String estudianteid) {
        try {
            FacultadCumCarreraEstudiante e = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteActivo(estudianteid);
            if (e == null) {
                return new Par(2, texts.getEstudianteActivoNull());
            }
            Examen ex = getInstanceOfExamen().findExamenByNombreAvailable(examen);
            if (ex == null) {
                return new Par(2, texts.getExamenNull());
            }
            for (Asignatura_Nota an : anl) {
                ExamenMatriculaFacultadCumCarreraEstudianteAsignatura emfccea = getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(ex.getExamenId(), an.getM().getMatriculaId(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), estudianteid, e.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), an.getA().getAsignaturaId()));
                if (emfccea.getCancelado()) {
                    return new Par(2, "No puede poner nota a la asignatura " + an.getA().getAsignaturaNombre() + " en esta convocatoria porque convocatoria anterior no tiene nota");
                }
                emfccea.setNota(an.getNota());
                getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().edit(emfccea);
                if (an.getNota() >= 2) {
                    if (examen.equals("Convocatoria 1")) {
                        Examen ex1 = getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 2");
                        emfccea = getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(ex1.getExamenId(), an.getM().getMatriculaId(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), estudianteid, e.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), an.getA().getAsignaturaId()));
                        if (emfccea.getCancelado()) {
                            emfccea.setCancelado(false);
                            getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().edit(emfccea);
                        }
                    } else {
                        if (examen.equals("Convocatoria 2")) {
                            Examen ex1 = getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 3");
                            emfccea = getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(ex1.getExamenId(), an.getM().getMatriculaId(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), estudianteid, e.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), an.getA().getAsignaturaId()));
                            if (emfccea.getCancelado()) {
                                emfccea.setCancelado(false);
                                getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().edit(emfccea);
                            }
                        }
                    }
                } else {
                    if (examen.equals("Convocatoria 1")) {
                        Examen ex1 = getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 2");
                        emfccea = getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(ex1.getExamenId(), an.getM().getMatriculaId(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), estudianteid, e.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), an.getA().getAsignaturaId()));
                        if (!emfccea.getCancelado()) {
                            emfccea.setCancelado(true);
                            getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().edit(emfccea);
                        }
                        Examen ex2 = getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 3");
                        emfccea = getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(ex2.getExamenId(), an.getM().getMatriculaId(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), estudianteid, e.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), an.getA().getAsignaturaId()));
                        if (!emfccea.getCancelado()) {
                            emfccea.setCancelado(true);
                            getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().edit(emfccea);
                        }
                    } else {
                        if (examen.equals("Convocatoria 2")) {
                            Examen ex1 = getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 3");
                            emfccea = getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(ex1.getExamenId(), an.getM().getMatriculaId(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), estudianteid, e.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), an.getA().getAsignaturaId()));
                            if (!emfccea.getCancelado()) {
                                emfccea.setCancelado(true);
                                getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().edit(emfccea);
                            }
                        }
                    }
                }
                FacultadCumCarreraEstudianteAsignatura fccea = getInstanceOfFacultadCumCarreraEstudianteAsignatura().findFacultadCumCarreraEstudianteAsignatura(new FacultadCumCarreraEstudianteAsignaturaPK(e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), e.getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId(), e.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), an.getA().getAsignaturaId()));
                MatriculaFacultadCumCarreraEstudianteAsignatura mfccea = emfccea.getMatriculaFacultadCumCarreraEstudianteAsignatura();
                int[] notas = new int[3];
                for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura n : mfccea.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList()) {
                    if (!n.getCancelado()) {
                        notas[Integer.parseInt((n.getExamen().getExamenTipo().charAt(n.getExamen().getExamenTipo().length() - 1)) + "") - 1] = n.getNota();
                    }
                }
                boolean flag = false;
                for (int i = 0; i < 3; i++) {
                    if (notas[i] < 2) {
                        break;
                    }
                    if (notas[i] > 2) {
                        flag = true;
                    }
                }
                fccea.setAprobada(flag);
                getInstanceOfFacultadCumCarreraEstudianteAsignatura().edit(fccea);

//                if (an.getNota() > -1) {
//                    if (examen.equals("Convocatoria 1")) {
//                        Examen ex1 = getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 2");
//                        emfccea = getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(ex1.getExamenId(), an.getM().getMatriculaId(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), estudianteid, e.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), an.getA().getAsignaturaId()));
//                        if (emfccea.getCancelado()) {
//                            emfccea.setCancelado(false);
//                            getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().edit(emfccea);
//                        }
//                    } else {
//                        if (examen.equals("Convocatoria 2")) {
//                            Examen ex1 = getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 3");
//                            emfccea = getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(ex1.getExamenId(), an.getM().getMatriculaId(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), e.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), estudianteid, e.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), an.getA().getAsignaturaId()));
//                            if (emfccea.getCancelado()) {
//                                emfccea.setCancelado(false);
//                                getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().edit(emfccea);
//                            }
//                        }
//                    }
//                }
            }
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(MatriculaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Estudiante findEstudianteById(String id) {
        return getInstanceOfEstudiante().findEstudiante(id);
    }

    public long findAsignaturasPorMatricular(String idEstudiante) {
        FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteActivo(idEstudiante);
        if (fcce == null) {
            return 0;
        }
        return getInstanceOfAsignatura().findAsignaturaPorMatricularCount(idEstudiante, fcce.getFacultadCumCarrera().getFacultadCum().getFacultad().getCodigoarea(), fcce.getFacultadCumCarrera().getCarrera().getIdcarrera(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula());
    }

    public long findAsignaturasMatriculadas(String idEstudiante) {
        FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteActivo(idEstudiante);
        if (fcce == null) {
            return 0;
        }
        return getInstanceOfAsignatura().findAsignaturaMatriculadas(idEstudiante, fcce.getFacultadCumCarrera().getFacultadCum().getFacultad().getCodigoarea(), fcce.getFacultadCumCarrera().getCarrera().getIdcarrera(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula());
    }

//    public List<Estudiante> findEstudiantesByCum(String cum, String carrera) {
//        return getInstanceOfEstudiante().findEstudiantesByCum(cum, carrera);
//    }
//    public List<Estudiante> findEstudiantesByFacultad(String fac, String carrera) {
//        return getInstanceOfEstudiante().findEstudiantesByFacultad(fac, carrera);
//    }
    public List<Estudiante> findEstudiantesByPlanEstudio(String fac, String carrera, String planestudio) {
        return getInstanceOfEstudiante().findEstudiantesByPlanEstudio(fac, carrera, planestudio);
    }

//    public List<Estudiante> findEstudiantesParaPromover(String fac, String carrera) {
//        return getInstanceOfEstudiante().findEstudiantesParaPromover(fac, carrera);
//    }
//    public FacultadCumCarreraEstudiante findByEstudianteActivo(String idEstudiante) {
//        return getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteActivo(idEstudiante);
//    }
    public FacultadCumCarreraEstudiante findByEstudianteActivo(String idEstudiante) {
        userLoging u = new userLoging();
        FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteActivoCum(idEstudiante, u.getUsername());
        if (fcce == null) {
            fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteActivoFacultad(idEstudiante, u.getUsername());
        }
        return fcce;
    }

//    public FacultadCumCarreraEstudiante findByEstudianteNuevaMtricula(String idEstudiante) {
//        return getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteNuevaMatricula(idEstudiante);
//    }
    public FacultadCumCarreraEstudiante findByEstudianteNuevaMtricula(String idEstudiante) {
        userLoging u = new userLoging();
        FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteNuevaMatriculaCum(idEstudiante, u.getUsername());
        if (fcce == null) {
            fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteNuevaMatriculaFacultad(idEstudiante, u.getUsername());
        }
        return fcce;
    }

//    public FacultadCumCarreraEstudiante findByEstudianteMatriculaPasiva(String idEstudiante) {
//        return getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteMatriculaPasiva(idEstudiante);
//    }
    public FacultadCumCarreraEstudiante findByEstudianteMatriculaPasiva(String idEstudiante) {
        userLoging u = new userLoging();
        FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteMatriculaPasivaCum(idEstudiante, u.getUsername());
        if (fcce == null) {
            fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteMatriculaPasivaFacultad(idEstudiante, u.getUsername());
        }
        return fcce;
    }

//    public FacultadCumCarreraEstudiante findByEstudianteUltimaFecha(String idEstudiante) {
//        return getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteUltimaFecha(idEstudiante);
//    }
    public FacultadCumCarreraEstudiante findByEstudianteUltimaFecha(String idEstudiante) {
        userLoging u = new userLoging();
        FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteUltimaFechaCum(idEstudiante, u.getUsername());
        if (fcce == null) {
            fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteUltimaFechaFacultad(idEstudiante, u.getUsername());
        }
        return fcce;
    }
//    public List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> findEstudiantesByAsignaturaMatriculaConvocatoriaForNotas(String area, String carrera, Date inicio, String Asignatura, String convocatoria) {
//        Carrera c = getInstanceOfCarrera().findCarreraByCarreranacionalAvailable(carrera);
//        if (c == null) {
//            return new ArrayList<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura>();
//        }
//        Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(area);
//        Cum cu;
//        if (f == null) {
//            cu = getInstanceOfCum().findCumByNombreAvailable(area);
//            if (cu == null) {
//                return new ArrayList<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura>();
//            }
//            f = getInstanceOfFacultad().findFacultadByCumCarrera(cu.getCodigocum(), c.getIdcarrera());
//        } else {
//            cu = cu = getInstanceOfCum().findCumByNombreAvailable("");
//        }
//        Asignatura a=getInstanceOfAsignatura().findAsignaturaByCodigoAvailable(Asignatura);
//        if (a==null) {
//            return new ArrayList<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura>();
//        }
//        Examen e=getInstanceOfExamen().findExamenByNombreAvailable(convocatoria);
//        if (e==null) {
//            return new ArrayList<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura>();
//        }
//        return getInstanceOfMatricula().findMatriculaAvailableByCarreraForNotas(inicio, f.getCodigoarea(), c.getIdcarrera(), cu.getCodigocum(), a.getAsignaturaId(), e.getExamenId());
//    }
//
//    public Estudiante findEstudianteByAsignaturaForNota(String facultad, String carrera, String planEstudio, Date matricula, String asignatura, String convocatoria) {
//        return getInstanceOfEstudiante().findEstudianteByAsignaturaForNota(facultad, carrera, planEstudio, matricula, asignatura, convocatoria);
//    }

    public List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> findAsignaturasForNota(String estudiante, String convocatoria) {
        FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteActivo(estudiante);
        if (fcce == null) {
            return null;
        }
        Examen e = getInstanceOfExamen().findExamenByNombreAvailable(convocatoria);
        if (e == null) {
            return null;
        }
        return getInstanceOfEstudiante().findAsignaturasForNotasByExamen(fcce, e.getExamenId());
    }

    public List<Estudiante> findEstudianteEntities() {
        return getInstanceOfEstudiante().findEstudianteEntities();
    }
}
