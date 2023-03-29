/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.AsignaturaAsignaturaJpaController;
import cu.edu.unah.sigenuead.controller.AsignaturaJpaController;
import cu.edu.unah.sigenuead.controller.CarreraJpaController;
import cu.edu.unah.sigenuead.controller.CarreranacionalJpaController;
import cu.edu.unah.sigenuead.controller.CumJpaController;
import cu.edu.unah.sigenuead.controller.CursoJpaController;
import cu.edu.unah.sigenuead.controller.DisciplinaJpaController;
import cu.edu.unah.sigenuead.controller.DisciplinaPlanestudioJpaController;
import cu.edu.unah.sigenuead.controller.EstudianteJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumCarreraEstudianteAsignaturaJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumCarreraEstudianteJpaController;
import cu.edu.unah.sigenuead.controller.FacultadJpaController;
import cu.edu.unah.sigenuead.controller.MatriculaJpaController;
import cu.edu.unah.sigenuead.controller.PlanestudioJpaController;
import cu.edu.unah.sigenuead.controller.TipoAsignaturaJpaController;
import cu.edu.unah.sigenuead.controller.TipoEvaluacionJpaController;
import cu.edu.unah.sigenuead.entity.Asignatura;
import cu.edu.unah.sigenuead.entity.AsignaturaAsignatura;
import cu.edu.unah.sigenuead.entity.AsignaturaAsignaturaPK;
import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.Cum;
import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.Disciplina;
import cu.edu.unah.sigenuead.entity.DisciplinaPlanestudio;
import cu.edu.unah.sigenuead.entity.DisciplinaPlanestudioPK;
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.Facultad;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.Matricula;
import cu.edu.unah.sigenuead.entity.MatriculaFacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Planestudio;
import cu.edu.unah.sigenuead.entity.TipoAsignatura;
import cu.edu.unah.sigenuead.entity.TipoEvaluacion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 * findAllAsignatura
 *
 * @author claudy
 */
public class AsignaturaServices {

    private AsignaturaJpaController controllerAsignatura;

    public AsignaturaJpaController getInstanceOfAsignatura() {
        return (controllerAsignatura == null) ? controllerAsignatura = new AsignaturaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerAsignatura;
    }
    private AsignaturaAsignaturaJpaController controllerAsignaturaAsignatura;

    public AsignaturaAsignaturaJpaController getInstanceOfAsignaturaAsignatura() {
        return (controllerAsignaturaAsignatura == null) ? controllerAsignaturaAsignatura = new AsignaturaAsignaturaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerAsignaturaAsignatura;
    }

    private TipoEvaluacionJpaController controllerTipoEvaluacion;

    public TipoEvaluacionJpaController getInstanceOfTipoEvaluacion() {
        return (controllerTipoEvaluacion == null) ? controllerTipoEvaluacion = new TipoEvaluacionJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerTipoEvaluacion;
    }

    private TipoAsignaturaJpaController controllerTipoAsignatura;

    public TipoAsignaturaJpaController getInstanceOfTipoAsignatura() {
        return (controllerTipoAsignatura == null) ? controllerTipoAsignatura = new TipoAsignaturaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerTipoAsignatura;
    }

    private PlanestudioJpaController controllerPlanestudio;

    public PlanestudioJpaController getInstanceOfPlanestudio() {
        return (controllerPlanestudio == null) ? controllerPlanestudio = new PlanestudioJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerPlanestudio;
    }
    private DisciplinaJpaController controllerDisciplina;

    public DisciplinaJpaController getInstanceOfDisciplina() {
        return (controllerDisciplina == null) ? controllerDisciplina = new DisciplinaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerDisciplina;
    }
    private DisciplinaPlanestudioJpaController controllerDisciplinaPlanestudio;

    public DisciplinaPlanestudioJpaController getInstanceOfDisciplinaPlanestudio() {
        return (controllerDisciplinaPlanestudio == null) ? controllerDisciplinaPlanestudio = new DisciplinaPlanestudioJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerDisciplinaPlanestudio;
    }
    private FacultadJpaController controllerFacultad;

    public FacultadJpaController getInstanceOfFacultad() {
        return (controllerFacultad == null) ? controllerFacultad = new FacultadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultad;
    }

    private EstudianteJpaController controllerEstudiante;

    public EstudianteJpaController getInstanceOfEstudiante() {
        return (controllerEstudiante == null) ? controllerEstudiante = new EstudianteJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEstudiante;
    }
    private CarreraJpaController controllerCarrera;

    public CarreraJpaController getInstanceOfCarrera() {
        return (controllerCarrera == null) ? controllerCarrera = new CarreraJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCarrera;
    }
    private CarreranacionalJpaController controllerCarreraNacional;

    public CarreranacionalJpaController getInstanceOfCarreraNacional() {
        return (controllerCarreraNacional == null) ? controllerCarreraNacional = new CarreranacionalJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCarreraNacional;
    }
    private MatriculaJpaController controllerMatricula;

    public MatriculaJpaController getInstanceOfMatricula() {
        return (controllerMatricula == null) ? controllerMatricula = new MatriculaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerMatricula;
    }
    private CursoJpaController controllerCurso;

    public CursoJpaController getInstanceOfCurso() {
        return (controllerCurso == null) ? controllerCurso = new CursoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCurso;
    }
    private FacultadCumCarreraEstudianteAsignaturaJpaController controllerFacultadCumCarreraEstudianteAsignatura;

    public FacultadCumCarreraEstudianteAsignaturaJpaController getInstanceOfFacultadCumCarreraEstudianteAsignatura() {
        return (controllerFacultadCumCarreraEstudianteAsignatura == null) ? controllerFacultadCumCarreraEstudianteAsignatura = new FacultadCumCarreraEstudianteAsignaturaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultadCumCarreraEstudianteAsignatura;
    }
    private FacultadCumCarreraEstudianteJpaController controllerFacultadCumCarreraEstudiante;

    public FacultadCumCarreraEstudianteJpaController getInstanceOfFacultadCumCarreraEstudiante() {
        return (controllerFacultadCumCarreraEstudiante == null) ? controllerFacultadCumCarreraEstudiante = new FacultadCumCarreraEstudianteJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultadCumCarreraEstudiante;
    }
    private CumJpaController controllerCum;

    public CumJpaController getInstanceOfCum() {
        return (controllerCum == null) ? controllerCum = new CumJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCum;
    }

    public Par AddAsignatura(String codigo, String nombre, String abreviatura, String facultad, String carrera, boolean cancel, String tipoEvaluacion, String tipoAsignatura, boolean promediable, boolean certificable, String disciplina, String planEstudio, List<String> precedentes) {
        try {
            if (getInstanceOfAsignatura().findAsignaturaByCodigo(codigo) == null) {
                Asignatura a = new Asignatura();
                a.setAsignaturaAbreviatura(abreviatura);
                a.setAsignaturaCancelada(cancel);
                a.setAsignaturaCertificable(certificable);
                a.setAsignaturaCodigo(codigo);
                a.setAsignaturaNombre(nombre);
                a.setAsignaturaPromediable(promediable);
                TipoEvaluacion te = getInstanceOfTipoEvaluacion().findTipoEvaluacionByNombreAvailable(tipoEvaluacion);
                if (te == null) {
                    return new Par(2, texts.getTipoEvaluacionNull());
                }
                a.setTipoEvaluaciontipoEvaluacionId(te);
                TipoAsignatura ta = getInstanceOfTipoAsignatura().findTipoAsignaturaByNombreAvailable(tipoAsignatura);
                if (te == null) {
                    return new Par(2, texts.getTipoAsignaturaNull());
                }
                a.setTipoAsignaturatipoAsignaturaId(ta);
                Disciplina d = getInstanceOfDisciplina().findDisciplinaByNombreAvailable(disciplina);
                if (d == null) {
                    return new Par(2, texts.getDisciplinaNull());
                }
                Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
                if (f == null) {
                    return new Par(2, texts.getFacultadNull());
                }
                Planestudio p = getInstanceOfPlanestudio().findPlanEstudioByCarreraAvailable(f.getCodigoarea(), carrera, planEstudio);
                if (p == null) {
                    return new Par(2, texts.getPlanEstudioNull());
                }
                DisciplinaPlanestudio dp = getInstanceOfDisciplinaPlanestudio().findDisciplinaPlanestudio(new DisciplinaPlanestudioPK(d.getDisciplinaCodigo(), p.getIdplanestudio()));
                if (dp == null) {
                    return new Par(2, texts.getDisciplinaPlanEstudioNull());
                }
                a.setDisciplinaPlanestudio(dp);
                if (getInstanceOfAsignatura().findAsignaturaByNombre(nombre, facultad, carrera, planEstudio) == null) {
                    getInstanceOfAsignatura().create(a);
                    List<FacultadCumCarreraEstudiante> est = getInstanceOfEstudiante().findFacultadCumCarreraEstudiantesByPlanEstudio(f.getCodigoarea(), carrera, planEstudio);
                    for (FacultadCumCarreraEstudiante e : est) {
                        if (est == null) {
                            return new Par(2, "Este estudiante no esta activo en esa carrera");
                        }
                        FacultadCumCarreraEstudianteAsignatura ae = new FacultadCumCarreraEstudianteAsignatura();
                        ae.setAprobada(false);
                        ae.setAsignatura(a);
                        ae.setCancelada(true);
                        ae.setFacultadCumCarreraEstudiante(e);
                        getInstanceOfFacultadCumCarreraEstudianteAsignatura().create(ae);
                    }
                    return new Par(1, texts.getSatisfactorio());
                } else {
                    return new Par(3, texts.getInformacion());
                }
            } else {
                return new Par(3, texts.getCodigoExistente());
            }
        } catch (Exception ex) {
            Logger.getLogger(AsignaturaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par EditAsignatura(Asignatura a) {
        try {
            getInstanceOfAsignatura().edit(a);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(AsignaturaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, "No se pudo editar la asignatura");
        }
    }

    public Par EditAsignatura(String codigo, String nombre, String abreviatura, String facultad, String carrera, boolean cancel, String tipoEvaluacion, String tipoAsignatura, boolean promediable, boolean certificable, String disciplina, String planEstudio, List<String> precedentes, List<String> precedentesnew) {
        try {
            Asignatura a = getInstanceOfAsignatura().findAsignaturaByCodigo(codigo);
            if (a != null) {
                a.setAsignaturaAbreviatura(abreviatura);
                a.setAsignaturaCancelada(cancel);
                a.setAsignaturaCertificable(certificable);
                a.setAsignaturaCodigo(codigo);
                a.setAsignaturaNombre(nombre);
                a.setAsignaturaPromediable(promediable);
                TipoEvaluacion te = getInstanceOfTipoEvaluacion().findTipoEvaluacionByNombreAvailable(tipoEvaluacion);
                if (te == null) {
                    return new Par(2, texts.getTipoEvaluacionNull());
                }
                a.setTipoEvaluaciontipoEvaluacionId(te);
                TipoAsignatura ta = getInstanceOfTipoAsignatura().findTipoAsignaturaByNombreAvailable(tipoAsignatura);
                if (te == null) {
                    return new Par(2, texts.getTipoAsignaturaNull());
                }
                a.setTipoAsignaturatipoAsignaturaId(ta);
                Disciplina d = getInstanceOfDisciplina().findDisciplinaByNombreAvailable(disciplina);
                if (d == null) {
                    return new Par(2, texts.getDisciplinaNull());
                }
                Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
                if (f == null) {
                    return new Par(2, texts.getFacultadNull());
                }
                Planestudio p = getInstanceOfPlanestudio().findPlanEstudioByCarreraAvailable(f.getCodigoarea(), carrera, planEstudio);
                if (p == null) {
                    return new Par(2, texts.getPlanEstudioNull());
                }
                DisciplinaPlanestudio dp = getInstanceOfDisciplinaPlanestudio().findDisciplinaPlanestudio(new DisciplinaPlanestudioPK(d.getDisciplinaCodigo(), p.getIdplanestudio()));
                if (dp == null) {
                    return new Par(2, texts.getDisciplinaPlanEstudioNull());
                }
                a.setDisciplinaPlanestudio(dp);
                getInstanceOfAsignatura().edit(a);
                for (String s : precedentes) {
                    if (!precedentesnew.contains(s)) {
                        Asignatura aux = getInstanceOfAsignatura().findAsignaturaByNombreAvailable(s, facultad, carrera, planEstudio);
                        if (aux != null) {
                            AsignaturaAsignatura aa = getInstanceOfAsignaturaAsignatura().findAsignaturaAsignatura(new AsignaturaAsignaturaPK(aux.getAsignaturaId(), a.getAsignaturaId()));
                            if (aa != null) {
                                aa.setCancelado(true);
                                getInstanceOfAsignaturaAsignatura().edit(aa);
                            }

                        }
                    }
                }
                for (String s : precedentesnew) {
                    Asignatura aux = getInstanceOfAsignatura().findAsignaturaByNombreAvailable(s, facultad, carrera, planEstudio);
                    if (aux != null) {
                        AsignaturaAsignatura aa = getInstanceOfAsignaturaAsignatura().findAsignaturaAsignatura(new AsignaturaAsignaturaPK(aux.getAsignaturaId(), a.getAsignaturaId()));
                        if (aa == null) {
                            aa = new AsignaturaAsignatura(aux.getAsignaturaId(), a.getAsignaturaId());
                            aa.setAsignatura(aux);
                            aa.setAsignatura1(a);
                            aa.setCancelado(cancel);
                            getInstanceOfAsignaturaAsignatura().create(aa);
                        } else {
                            aa.setCancelado(cancel);
                            getInstanceOfAsignaturaAsignatura().edit(aa);
                        }

                    }
                }
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getEditarExistente());
            }
        } catch (Exception ex) {
            Logger.getLogger(AsignaturaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par CancelarAsignatura(String codigo) {
//        try {
//            Asignatura a = getInstanceOfAsignatura().findAsignaturaByCodigo(codigo);
//            if (a != null) {
//                a.setAsignaturaCancelada(!a.getAsignaturaCancelada());
//                getInstanceOfAsignatura().edit(a);
//                return new Par(1, texts.getSatisfactorio());
//            } else {
//                return new Par(3, texts.getAsignaturaNull());
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(AsignaturaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par DeleteAsignatura(String codigo) {
//        try {
//            Asignatura a = getInstanceOfAsignatura().findAsignaturaByCodigo(codigo);
//            if (a != null) {
//                a.setAsignaturaCancelada(!a.getAsignaturaCancelada());
//                getInstanceOfAsignatura().destroy(a.getAsignaturaId());
//                return new Par(1, texts.getSatisfactorio());
//            } else {
//                return new Par(3, texts.getAsignaturaNull());
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(AsignaturaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
    public List<Asignatura> findAllAsignatura() {
        return getInstanceOfAsignatura().findAsignaturaEntities();
    }

    public List<Asignatura> findAsignaturaMatriculadasByEstudiante(String estudiante) {
        FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteActivo(estudiante);
        FacesContext context = FacesContext.getCurrentInstance();
        if (fcce == null) {
            Par p = new Par(2, "Ese estudiante no se encuentra activo en ninguna carrera");
            context.addMessage(null, new FacesMessage(p.getFacesType(), p.getResult(), ""));
            return null;
        }
        if (fcce.getFacultadCumCarreraEstudianteAsignaturaList().isEmpty()) {
            return new ArrayList<Asignatura>();
        }
        Curso curso = getInstanceOfCurso().findCursoActual();
        if (curso == null) {
            Par p = new Par(2, texts.getCursoNull());
            context.addMessage(null, new FacesMessage(p.getFacesType(), p.getResult(), ""));
            return null;
        }
        Matricula m = getInstanceOfMatricula().findMatriculaAvailableByCarreraCurso(fcce.getFacultadCumCarrera(), curso.getIdcurso());
        if (m == null) {
            Par p = new Par(2, texts.getMatriculaNull());
            context.addMessage(null, new FacesMessage(p.getFacesType(), p.getResult(), ""));
            return null;
        }
        List<MatriculaFacultadCumCarreraEstudianteAsignatura> cantmatriculas = getInstanceOfMatricula().findMatriculasAvailablesByEstudianteNoActual(fcce);
        List<Asignatura> asig = new ArrayList<Asignatura>();
        if (cantmatriculas.isEmpty()) {
            asig.addAll(getInstanceOfAsignatura().findAsignaturaBasicasFaltantesByEstudiante(fcce));
        } else {
            asig.addAll(getInstanceOfAsignatura().findAsignaturaMatriculadasByEstudiante(estudiante, fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), m.getMatriculaId(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum()));
        }
        return asig;
    }

    public List<String> findAsignaturaByDisciplina(String facultad, String carrera, String planEstudio, String disciplina) {
        return getInstanceOfAsignatura().findAsignaturaAvailableByDisciplina(facultad, carrera, planEstudio, disciplina);
    }

//    public List<String> findAsignaturaPrecedentes(String facultad, String carrera, String planEstudio, String disciplina, String asignatura) {
//        return getInstanceOfAsignatura().findAsignaturaPrecedente(facultad, carrera, planEstudio, disciplina, asignatura);
//    }
    public List<String> findAsignaturaPrecedentesByCodigo(String asignaturaCodigo) {
        return getInstanceOfAsignatura().findAsignaturaPrecedenteByCodigo(asignaturaCodigo);
    }

    public List<String> findAsignaturaAvailable() {
        return getInstanceOfAsignatura().findAsignaturaAvailable();
    }

    public Asignatura findAsignaturaByCodigo(String codigo, String facultad, String carrera, String planEstudio) {
        return getInstanceOfAsignatura().findAsignaturaByCodigo(codigo);
    }

    public Asignatura findAsignaturaByNombreAvailable(String asignatura, String facultad, String carrera, String planEstudio) {
        return getInstanceOfAsignatura().findAsignaturaByNombreAvailable(asignatura, facultad, carrera, planEstudio);
    }

    public List<Asignatura> findAsignaturasParaMatricular(String idEstudiante) {
        FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteActivo(idEstudiante);
        FacesContext context = FacesContext.getCurrentInstance();
        if (fcce == null) {
            Par p = new Par(2, "Ese estudiante no se encuentra activo en ninguna carrera");
            context.addMessage(null, new FacesMessage(p.getFacesType(), p.getResult(), ""));
            return null;
        }
        if (fcce.getFacultadCumCarreraEstudianteAsignaturaList().isEmpty()) {
            return getInstanceOfAsignatura().findAsignaturaByPlanEstudio(fcce.getFacultadCumCarrera().getFacultadCum().getFacultad().getNombrearea(), fcce.getFacultadCumCarrera().getCarrera().getCarreranacionalidcarreranacional().getNombrecarreranacional(), fcce.getPlanestudioidplanestudio().getTipoplanestudionombretipoplanestudio().getNombretipoplanestudio());
        }
        List<MatriculaFacultadCumCarreraEstudianteAsignatura> cantmatriculas = getInstanceOfMatricula().findMatriculasAvailablesByEstudiante(fcce);
        if (!cantmatriculas.isEmpty()) {
            List<Asignatura> basicas = getInstanceOfAsignatura().findAsignaturaBasicasFaltantesByEstudiante(fcce);
            if (!basicas.isEmpty()) {
                return basicas;
            }
        }
        Curso cs = getInstanceOfCurso().findCursoActual();
        Matricula m = getInstanceOfMatricula().findMatriculaAvailableByCarreraCurso(fcce.getFacultadCumCarrera(), cs.getIdcurso());
        List<Asignatura> res = getInstanceOfAsignatura().findAsignaturaNoAprobadas(fcce, m.getMatriculaId());
        List<Asignatura> matriculadas = getInstanceOfAsignatura().findAsignaturaMatriculadasByEstudiante(idEstudiante, fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), m.getMatriculaId(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum());
        res.removeAll(matriculadas);
        if (cantmatriculas.isEmpty()) {
            res.removeAll(getInstanceOfAsignatura().findAsignaturaBasicasFaltantesByEstudiante(fcce));
        }
        return res;
    }

//    public List<Asignatura> findAsignaturasBasicasParaMatricular(String idEstudiante) {
//        FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteActivo(idEstudiante);
//        FacesContext context = FacesContext.getCurrentInstance();
//        if (fcce == null) {
//            Par p = new Par(2, "Ese estudiante no se encuentra activo en ninguna carrera");
//            context.addMessage(null, new FacesMessage(p.getFacesType(), p.getResult(), ""));
//            return null;
//        }
//        if (fcce.getFacultadCumCarreraEstudianteAsignaturaList().isEmpty()) {
//            return getInstanceOfAsignatura().findAsignaturaByPlanEstudio(fcce.getFacultadCumCarrera().getFacultadCum().getFacultad().getNombrearea(), fcce.getFacultadCumCarrera().getCarrera().getCarreranacionalidcarreranacional().getNombrecarreranacional(), fcce.getPlanestudioidplanestudio().getTipoplanestudionombretipoplanestudio().getNombretipoplanestudio());
//        }
//        Curso cs = getInstanceOfCurso().findCursoActual();
//        Matricula m = getInstanceOfMatricula().findMatriculaAvailableByCarreraCurso(fcce.getFacultadCumCarrera(), cs.getIdcurso());
//        List<Asignatura> res = getInstanceOfAsignatura().findAsignaturaBasicaNoMatriculadasByEstudiante(idEstudiante, fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), m.getMatriculaId(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum());
//        List<Asignatura> matriculadas = getInstanceOfAsignatura().findAsignaturaBasicaMatriculadasByEstudiante(idEstudiante, fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), m.getMatriculaId(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum());
//        res.removeAll(matriculadas);
//        return res;
//    }
//    public List<Asignatura> findAsignaturaMatriculadasByMatricula(String facultad, String carrera, String planEstudio, String Matricula) {
//        Facultad f = getInstanceOfFacultad().findFacultadByNombre(facultad);
//        Carreranacional cn = getInstanceOfCarreraNacional().findCarreraNacionalByNombre(carrera);
//        Carrera c = getInstanceOfCarrera().findCarreraByCarreraNacional(cn.getIdcarreranacional(), carrera);
//        Planestudio pe = getInstanceOfPlanestudio().findPlanEstudioByCarrera(facultad, carrera, planEstudio);
//
//        return getInstanceOfAsignatura().findAsignaturaMatriculadasByEstudiante(estudiante);
//    }
    List<Asignatura> findAsignaturaEntityByDisciplina(String facultad, String carrera, String planEstudio, String disciplina) {
        return getInstanceOfAsignatura().findAsignaturaEntityByDisciplina(facultad, carrera, planEstudio, disciplina);
    }

    public List<Asignatura> findAsignaturaByPlanEstudio(String area, String carrera, String planEstudio) {
        Carrera c = getInstanceOfCarrera().findCarreraByCarreranacionalAvailable(carrera);
        if (c == null) {
            return new ArrayList<Asignatura>();
        }
        Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(area);
        Cum cu;
        if (f == null) {
            cu = getInstanceOfCum().findCumByNombreAvailable(area);
            if (cu == null) {
                return new ArrayList<Asignatura>();
            }
            f = getInstanceOfFacultad().findFacultadByCumCarrera(cu.getCodigocum(), c.getIdcarrera());
        } else {
            cu = cu = getInstanceOfCum().findCumByNombreAvailable("");
        }
        return getInstanceOfAsignatura().findAsignaturaByPlanEstudio(f.getNombrearea(), carrera, planEstudio);
    }
}
