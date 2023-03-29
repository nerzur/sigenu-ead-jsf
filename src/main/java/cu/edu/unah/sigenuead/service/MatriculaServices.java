/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.AsignaturaJpaController;
import cu.edu.unah.sigenuead.controller.CarreraCursoJpaController;
import cu.edu.unah.sigenuead.controller.CarreraJpaController;
import cu.edu.unah.sigenuead.controller.CumJpaController;
import cu.edu.unah.sigenuead.controller.CursoJpaController;
import cu.edu.unah.sigenuead.controller.EstadoEstudianteJpaController;
import cu.edu.unah.sigenuead.controller.EstudianteJpaController;
import cu.edu.unah.sigenuead.controller.ExamenJpaController;
import cu.edu.unah.sigenuead.controller.ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumCarreraEstudianteAsignaturaJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumCarreraEstudianteJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumCarreraJpaController;
import cu.edu.unah.sigenuead.controller.FacultadJpaController;
import cu.edu.unah.sigenuead.controller.MatriculaFacultadCumCarreraEstudianteAsignaturaJpaController;
import cu.edu.unah.sigenuead.controller.MatriculaJpaController;
import cu.edu.unah.sigenuead.entity.Asignatura;
import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.CarreraCursoPK;
import cu.edu.unah.sigenuead.entity.Cum;
import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.EstadoEstudiante;
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.ExamenMatriculaFacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK;
import cu.edu.unah.sigenuead.entity.Facultad;
import cu.edu.unah.sigenuead.entity.FacultadCumCarrera;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudianteAsignaturaPK;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraPK;
import cu.edu.unah.sigenuead.entity.Matricula;
import cu.edu.unah.sigenuead.entity.MatriculaFacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.MatriculaFacultadCumCarreraEstudianteAsignaturaPK;
import cu.edu.unah.sigenuead.entity.Par;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudy
 */
public class MatriculaServices {

    private MatriculaJpaController controllerMatricula;

    public MatriculaJpaController getInstanceOfMatricula() {
        return (controllerMatricula == null) ? controllerMatricula = new MatriculaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerMatricula;
    }
    private FacultadJpaController controllerFacultad;

    public FacultadJpaController getInstanceOfFacultad() {
        return (controllerFacultad == null) ? controllerFacultad = new FacultadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultad;
    }
    private CursoJpaController controllerCurso;

    public CursoJpaController getInstanceOfCurso() {
        return (controllerCurso == null) ? controllerCurso = new CursoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCurso;
    }

    private CarreraCursoJpaController controllerCarreraCurso;

    public CarreraCursoJpaController getInstanceOfCarreraCurso() {
        return (controllerCarreraCurso == null) ? controllerCarreraCurso = new CarreraCursoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCarreraCurso;
    }
    private CarreraJpaController controllerCarrera;

    public CarreraJpaController getInstanceOfCarrera() {
        return (controllerCarrera == null) ? controllerCarrera = new CarreraJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCarrera;
    }

    private EstudianteJpaController controllerEstudiante;

    public EstudianteJpaController getInstanceOfEstudiante() {
        return (controllerEstudiante == null) ? controllerEstudiante = new EstudianteJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEstudiante;
    }

    private AsignaturaJpaController controllerAsignatura;

    public AsignaturaJpaController getInstanceOfAsignatura() {
        return (controllerAsignatura == null) ? controllerAsignatura = new AsignaturaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerAsignatura;
    }
    private CumJpaController controllerCum;

    public CumJpaController getInstanceOfCum() {
        return (controllerCum == null) ? controllerCum = new CumJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCum;
    }
    private EstadoEstudianteJpaController controllerEstadoEstudiante;

    public EstadoEstudianteJpaController getInstanceOfEstadoEstudiante() {
        return (controllerEstadoEstudiante == null) ? controllerEstadoEstudiante = new EstadoEstudianteJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEstadoEstudiante;
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
    private MatriculaFacultadCumCarreraEstudianteAsignaturaJpaController controllerMatriculaFacultadCumCarreraEstudianteAsignatura;

    public MatriculaFacultadCumCarreraEstudianteAsignaturaJpaController getInstanceOfMatriculaFacultadCumCarreraEstudianteAsignatura() {
        return (controllerMatriculaFacultadCumCarreraEstudianteAsignatura == null) ? controllerMatriculaFacultadCumCarreraEstudianteAsignatura = new MatriculaFacultadCumCarreraEstudianteAsignaturaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerMatriculaFacultadCumCarreraEstudianteAsignatura;
    }
    private ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaJpaController controllerExamenMatriculaFacultadCumCarreraEstudianteAsignatura;

    public ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaJpaController getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura() {
        return (controllerExamenMatriculaFacultadCumCarreraEstudianteAsignatura == null) ? controllerExamenMatriculaFacultadCumCarreraEstudianteAsignatura = new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerExamenMatriculaFacultadCumCarreraEstudianteAsignatura;
    }
    private ExamenJpaController controllerExamen;

    public ExamenJpaController getInstanceOfExamen() {
        return (controllerExamen == null) ? controllerExamen = new ExamenJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerExamen;
    }

    public Par addMatricula(Date inicio, Date fin, boolean cancel, boolean cerrada, String curso, String facultad, String carrera) {
        try {
            if (inicio.after(fin)) {
                return new Par(2, texts.getFechasIncorrectas());
            }
            Carrera c = getInstanceOfCarrera().findCarreraByCarreranacionalAvailable(carrera);
            if (c == null) {
                return new Par(2, texts.getCarreraNull());
            }
            Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
            Cum cu;
            if (f == null) {
                cu = getInstanceOfCum().findCumByNombreAvailable(facultad);
                if (cu == null) {
                    return new Par(2, "Esa area no es valida");
                }
                f = getInstanceOfFacultad().findFacultadByCumCarrera(cu.getCodigocum(), c.getIdcarrera());
            } else {
                cu = cu = getInstanceOfCum().findCumByNombreAvailable("");
            }
            Curso cs = getInstanceOfCurso().findCurso(curso);
            if (cs == null) {
                return new Par(2, texts.getCursoNull());
            }
            String[] years = curso.split("-");
            int year1 = Integer.parseInt(years[0]);
            int year2 = Integer.parseInt(years[1]);
            if ((fin.getYear() + 1900 == year1 || fin.getYear() + 1900 == year2) && (inicio.getYear() + 1900 == year1 || inicio.getYear() + 1900 == year2) && inicio.before(fin)) {
                if (getInstanceOfCarreraCurso().findCarreraCurso(new CarreraCursoPK(c.getIdcarrera(), curso)) != null) {
                    Matricula m = getInstanceOfMatricula().finMatriculaByFechaInicioCarrera(inicio, c.getIdcarrera(), curso, f.getCodigoarea(), cu.getCodigocum());
                    if (m == null) {
                        FacultadCumCarrera fcc = getInstanceOfFacultadCumCarrera().findFacultadCumCarrera(new FacultadCumCarreraPK(cu.getCodigocum(), f.getCodigoarea(), c.getIdcarrera()));
                        List<Date> list = getInstanceOfMatricula().findFechaFinMatriculaAvailableByCarrera(f.getCodigoarea(), c.getIdcarrera(), cu.getCodigocum());
                        if (!list.isEmpty() && inicio.before(list.get(0))) {
                            return new Par(2, "No puede iniciar una matricula en esa fecha porque existe otra matricula que finaliza después");
                        }
                        if (!cancel) {
                            Matricula m2 = getInstanceOfMatricula().findMatriculaAvailableByCarreraCurso(fcc, curso);
                            if (m2 != null) {
                                m2.setMatriculaCancelada(true);
                                m2.setCerrada(true);
                                getInstanceOfMatricula().edit(m2);
                            }
                        }
                        m = new Matricula(inicio, fin, cancel, cerrada, cs, fcc);
                        getInstanceOfMatricula().create(m);
                        return new Par(1, texts.getSatisfactorio());
                    } else {
                        return new Par(3, texts.getInformacion());
                    }
                } else {
                    return new Par(2, texts.getCarreraCursoNull());
                }
            } else {
                return new Par(2, texts.getFechasIncorrectas());
            }
        } catch (Exception ex) {
            Logger.getLogger(MatriculaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editMatricula(Date inicio, Date inicioNew, Date fin, boolean cancel, boolean cerrada, String curso, String facultad, String carrera) {
        try {
            if (inicioNew.after(fin)) {
                return new Par(2, texts.getFechasIncorrectas());
            }
            Carrera c = getInstanceOfCarrera().findCarreraByCarreranacionalAvailable(carrera);
            if (c == null) {
                return new Par(2, texts.getCarreraNull());
            }
            Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
            Cum cu;
            if (f == null) {
                cu = getInstanceOfCum().findCumByNombreAvailable(facultad);
                if (cu == null) {
                    return new Par(2, "esa area no es valida");
                }
                f = getInstanceOfFacultad().findFacultadByCumCarrera(cu.getCodigocum(), c.getIdcarrera());
            } else {
                cu = cu = getInstanceOfCum().findCumByNombreAvailable("");
            }
            Curso cs = getInstanceOfCurso().findCurso(curso);
            if (cs == null) {
                return new Par(2, texts.getCursoNull());
            }
            String[] years = curso.split("-");
            int year1 = Integer.parseInt(years[0]);
            int year2 = Integer.parseInt(years[1]);
            if ((fin.getYear() + 1900 == year1 || fin.getYear() + 1900 == year2) && (inicioNew.getYear() + 1900 == year1 || inicioNew.getYear() + 1900 == year2) && inicioNew.before(fin)) {
                if (getInstanceOfCarreraCurso().findCarreraCurso(new CarreraCursoPK(c.getIdcarrera(), curso)) != null) {
                    Matricula m = getInstanceOfMatricula().finMatriculaByFechaInicioCarrera(inicio, c.getIdcarrera(), curso, f.getCodigoarea(), cu.getCodigocum());
                    if (m != null) {
                        m.setMatriculaCancelada(cancel);
                        m.setCerrada(cerrada);
                        m.setMatriculaFechaFin(fin);
                        getInstanceOfMatricula().edit(m);
                        return new Par(1, texts.getSatisfactorio());
                    } else {
                        return new Par(3, texts.getMatriculaNull());
                    }
                } else {
                    return new Par(2, texts.getCarreraCursoNull());
                }
            } else {
                return new Par(2, texts.getFechasIncorrectas());
            }
        } catch (Exception ex) {
            Logger.getLogger(MatriculaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarMatricula(Date inicio, String curso, String facultad, String carrera) {
//        try {
//            Facultad f = getInstanceOfFacultad().findFacultadByNombre(facultad);
//            Carrera c = getInstanceOfCarrera().findCarreraByFacultadCarreranacional(facultad, carrera);
//            Curso cs = getInstanceOfCurso().findCurso(curso);
//            Matricula m = getInstanceOfMatricula().finMatriculaByFechaInicioCarrera(inicio, c.getIdcarrera(), curso);
//            if (m != null) {
//                m.setMatriculaCancelada(!m.getMatriculaCancelada());
//                getInstanceOfMatricula().edit(m);
//                return new Par(1, texts.getSatisfactorio());
//            } else {
//                return new Par(3, "El elemento no existe");
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(MatriculaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteMatricula(Date inicio, String curso, String facultad, String carrera) {
//        try {
//            Facultad f = getInstanceOfFacultad().findFacultadByNombre(facultad);
//            Carrera c = getInstanceOfCarrera().findCarreraByFacultadCarreranacional(facultad, carrera);
//            Curso cs = getInstanceOfCurso().findCurso(curso);
//            Matricula m = getInstanceOfMatricula().finMatriculaByFechaInicioCarrera(inicio, c.getIdcarrera(), curso);
//            if (m != null) {
//                getInstanceOfMatricula().destroy(m.getMatriculaId());
//                return new Par(1, texts.getSatisfactorio());
//            } else {
//                return new Par(3, "El elemento no existe");
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(MatriculaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
    public Par addMatriculaResponsable(Estudiante estudiante, List<Asignatura> asignatura, List<Asignatura> asignaturanew) {
        try {
            Estudiante e = estudiante;
            if (e == null) {
                return new Par(2, texts.getEstudianteActivoNull());
            }
            FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteActivo(e.getEstudianteId());
            Facultad f = fcce.getFacultadCumCarrera().getFacultadCum().getFacultad();
            if (f == null) {
                return new Par(2, texts.getFacultadNull());
            }
            Carrera c = fcce.getFacultadCumCarrera().getCarrera();
            if (c == null) {
                return new Par(2, texts.getCarreraNull());
            }
            Curso cs = getInstanceOfCurso().findCursoActual();
            if (cs == null) {
                return new Par(2, texts.getCursoNull());
            }
            Matricula m = getInstanceOfMatricula().findMatriculaAvailableByCarreraCurso(fcce.getFacultadCumCarrera(), cs.getIdcurso());
            if (m == null) {
                return new Par(2, texts.getMatriculaNull());
            }
            for (int i = 0; i < asignatura.size(); i++) {
                Asignatura a = getInstanceOfAsignatura().findAsignaturaByNombreAvailable(asignatura.get(i).getAsignaturaNombre(), f.getNombrearea(), c.getCarreranacionalidcarreranacional().getNombrecarreranacional(), fcce.getPlanestudioidplanestudio().getTipoplanestudionombretipoplanestudio().getNombretipoplanestudio());
                if (a != null) {
                    FacultadCumCarreraEstudianteAsignatura fccea = getInstanceOfFacultadCumCarreraEstudianteAsignatura().findFacultadCumCarreraEstudianteAsignatura(new FacultadCumCarreraEstudianteAsignaturaPK(fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), fcce.getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId(), fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), a.getAsignaturaId()));
                    if (fccea != null) {
                        if (!asignaturanew.contains(asignatura.get(i))) {
                            if (!a.getTipoAsignaturatipoAsignaturaId().getTipoAsignaturaNombre().equals("Básica")) {
                                fccea.setCancelada(true);
                                getInstanceOfFacultadCumCarreraEstudianteAsignatura().edit(fccea);
                                MatriculaFacultadCumCarreraEstudianteAsignatura mfccea = getInstanceOfMatriculaFacultadCumCarreraEstudianteAsignatura().findMatriculaFacultadCumCarreraEstudianteAsignatura(new MatriculaFacultadCumCarreraEstudianteAsignaturaPK(m.getMatriculaId(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), fcce.getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId(), fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), a.getAsignaturaId()));
                                if (mfccea != null) {
                                    if (!mfccea.getCancelada()) {
                                        mfccea.setCancelada(true);
                                        getInstanceOfMatriculaFacultadCumCarreraEstudianteAsignatura().edit(mfccea);
                                    }
                                }
                            }
                            else{
                                return new Par(2, "Debe matricular todas las asignaturas Básicas");
                            }
                        }
                    }
                } else {
                    return new Par(2, texts.getAsignaturaNull());
                }
            }
            for (int i = 0; i < asignaturanew.size(); i++) {
                Asignatura a = getInstanceOfAsignatura().findAsignaturaByCodigoAvailable(asignaturanew.get(i).getAsignaturaCodigo());
                if (a != null) {
                    FacultadCumCarreraEstudianteAsignatura fccea = getInstanceOfFacultadCumCarreraEstudianteAsignatura().findFacultadCumCarreraEstudianteAsignatura(new FacultadCumCarreraEstudianteAsignaturaPK(fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), fcce.getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId(), fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), a.getAsignaturaId()));
                    List<String> listprec = getInstanceOfAsignatura().findAsignaturaPrecedenteByCodigo(a.getAsignaturaCodigo());
                    for (String s : listprec) {
                        Asignatura aux = getInstanceOfAsignatura().findAsignaturaByCodigoAvailable(s);
                        FacultadCumCarreraEstudianteAsignatura fcceaAux = getInstanceOfFacultadCumCarreraEstudianteAsignatura().findFacultadCumCarreraEstudianteAsignatura(new FacultadCumCarreraEstudianteAsignaturaPK(fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), fcce.getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId(), fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), aux.getAsignaturaId()));
                        if (!fcceaAux.getAprobada()) {
                            return new Par(2, "El estudiante no puede matricular la asignatura " + a.getAsignaturaCodigo() + "-" + a.getAsignaturaNombre() + " porque antes debe aprobar " + aux.getAsignaturaCodigo() + "-" + aux.getAsignaturaNombre() + " que es su precedente");
                        }
                    }
                    if (fccea != null) {
                        List<MatriculaFacultadCumCarreraEstudianteAsignatura> list = getInstanceOfFacultadCumCarreraEstudianteAsignatura().findFacultadCumCarreraEstudianteAsignaturaCantMatriculasAvailables(fccea);
                        if (list.size() >= 4) {
                            return new Par(2, "Ya ha consumido todas las oportunidades de matricular la asignatura " + a.getAsignaturaCodigo() + "-" + a.getAsignaturaNombre());
                        }
                        if (fccea.getCancelada()) {
                            fccea.setCancelada(false);
                            getInstanceOfFacultadCumCarreraEstudianteAsignatura().edit(fccea);

                        }
                    } else {
                        fccea = new FacultadCumCarreraEstudianteAsignatura();
                        fccea.setAprobada(false);
                        fccea.setAsignatura(a);
                        fccea.setCancelada(false);
                        fccea.setFacultadCumCarreraEstudiante(fcce);
                        getInstanceOfFacultadCumCarreraEstudianteAsignatura().create(fccea);
                    }
                    MatriculaFacultadCumCarreraEstudianteAsignatura mfccea = getInstanceOfMatriculaFacultadCumCarreraEstudianteAsignatura().findMatriculaFacultadCumCarreraEstudianteAsignatura(new MatriculaFacultadCumCarreraEstudianteAsignaturaPK(m.getMatriculaId(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), fcce.getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId(), fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), a.getAsignaturaId()));
                    if (mfccea != null) {
                        if (mfccea.getCancelada()) {
                            mfccea.setCancelada(false);
                            mfccea.setActual(true);
                            getInstanceOfMatriculaFacultadCumCarreraEstudianteAsignatura().edit(mfccea);
                            ExamenMatriculaFacultadCumCarreraEstudianteAsignatura emfccea1 = getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 1").getExamenId(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getMatriculamatriculaId(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getCodigocum(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getCodigoarea(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getIdcarrera(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getEstudianteId(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getFechaMatricula(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getAsignaturaId()));
                            ExamenMatriculaFacultadCumCarreraEstudianteAsignatura emfccea2 = getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 1").getExamenId(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getMatriculamatriculaId(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getCodigocum(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getCodigoarea(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getIdcarrera(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getEstudianteId(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getFechaMatricula(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getAsignaturaId()));
                            ExamenMatriculaFacultadCumCarreraEstudianteAsignatura emfccea3 = getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 1").getExamenId(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getMatriculamatriculaId(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getCodigocum(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getCodigoarea(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getIdcarrera(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getEstudianteId(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getFechaMatricula(), mfccea.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getAsignaturaId()));
                            if (emfccea1 == null) {
                                emfccea1 = new ExamenMatriculaFacultadCumCarreraEstudianteAsignatura();
                                emfccea1.setCancelado(false);
                                emfccea1.setExamen(getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 1"));
                                emfccea1.setMatriculaFacultadCumCarreraEstudianteAsignatura(mfccea);
                                emfccea1.setNota(0);
                                getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().create(emfccea1);
                            } else {
                                if (emfccea1.getCancelado()) {
                                    emfccea1.setCancelado(false);
                                    getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().edit(emfccea1);
                                }
                            }
                            if (emfccea2 == null) {
                                emfccea2 = new ExamenMatriculaFacultadCumCarreraEstudianteAsignatura();
                                emfccea2.setCancelado(true);
                                emfccea2.setExamen(getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 2"));
                                emfccea2.setMatriculaFacultadCumCarreraEstudianteAsignatura(mfccea);
                                emfccea2.setNota(0);
                                getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().create(emfccea2);
                            }
                            if (emfccea3 == null) {
                                emfccea3 = new ExamenMatriculaFacultadCumCarreraEstudianteAsignatura();
                                emfccea3.setCancelado(true);
                                emfccea3.setExamen(getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 3"));
                                emfccea3.setMatriculaFacultadCumCarreraEstudianteAsignatura(mfccea);
                                emfccea3.setNota(0);
                                getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().create(emfccea3);
                            }
                        }
                    } else {
                        mfccea = new MatriculaFacultadCumCarreraEstudianteAsignatura();
                        mfccea.setCancelada(false);
                        mfccea.setFacultadCumCarreraEstudianteAsignatura(fccea);
                        mfccea.setMatricula(m);
                        mfccea.setActual(true);
                        getInstanceOfMatriculaFacultadCumCarreraEstudianteAsignatura().create(mfccea);
                        ExamenMatriculaFacultadCumCarreraEstudianteAsignatura emfccea = new ExamenMatriculaFacultadCumCarreraEstudianteAsignatura();
                        emfccea.setCancelado(false);
                        emfccea.setExamen(getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 1"));
                        emfccea.setMatriculaFacultadCumCarreraEstudianteAsignatura(mfccea);
                        emfccea.setNota(0);
                        getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().create(emfccea);
                        emfccea = new ExamenMatriculaFacultadCumCarreraEstudianteAsignatura();
                        emfccea.setCancelado(true);
                        emfccea.setExamen(getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 2"));
                        emfccea.setMatriculaFacultadCumCarreraEstudianteAsignatura(mfccea);
                        emfccea.setNota(0);
                        getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().create(emfccea);
                        emfccea = new ExamenMatriculaFacultadCumCarreraEstudianteAsignatura();
                        emfccea.setCancelado(true);
                        emfccea.setExamen(getInstanceOfExamen().findExamenByNombreAvailable("Convocatoria 3"));
                        emfccea.setMatriculaFacultadCumCarreraEstudianteAsignatura(mfccea);
                        emfccea.setNota(0);
                        getInstanceOfExamenMatriculaFacultadCumCarreraEstudianteAsignatura().create(emfccea);
                    }
                } else {
                    return new Par(2, texts.getAsignaturaNull());
                }
            }
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(MatriculaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par CerrarMatricula(String area, String carrera) {
        try {
            List<FacultadCumCarreraEstudiante> est = new ArrayList<FacultadCumCarreraEstudiante>();
            Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(area);
            EstadoEstudiante ee = getInstanceOfEstadoEstudiante().findEstadoEstudianteByNombreAvailable("Activo");
            if (f == null) {
                Cum c = getInstanceOfCum().findCumByNombreAvailable(area);
                if (c != null) {
                    est = getInstanceOfEstudiante().findEstudiantesNuevaMatriculaByCum(area, carrera);
                }
            } else {
                est = getInstanceOfEstudiante().findEstudiantesNuevaMatriculaByFacultad(area, carrera);
            }
            for (FacultadCumCarreraEstudiante fcce : est) {
                fcce.setEstadoEstudianteestadoEstucianteId(ee);
                getInstanceOfFacultadCumCarreraEstudiante().edit(fcce);
            }
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(MatriculaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par relacionarAsignaturasEstudiantes(String facultad, String carrera, String planestudio, String estudiante) {
        try {
            List<Asignatura> list = getInstanceOfAsignatura().findAsignaturaByPlanEstudio(facultad, carrera, planestudio);
            FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteActivo(estudiante);
            if (fcce == null) {
                fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteNuevaMatricula(estudiante);
                if (fcce == null) {
                    return new Par(2, "A este estudiante no es posible asignarle asignaturas");
                }
            }
            for (Asignatura a : list) {
                if (getInstanceOfFacultadCumCarreraEstudianteAsignatura().findFacultadCumCarreraEstudianteAsignatura(new FacultadCumCarreraEstudianteAsignaturaPK(fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), estudiante, fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), a.getAsignaturaId())) == null) {
                    FacultadCumCarreraEstudianteAsignatura fccea = new FacultadCumCarreraEstudianteAsignatura();
                    fccea.setAprobada(false);
                    fccea.setAsignatura(a);
                    fccea.setCancelada(true);
                    fccea.setFacultadCumCarreraEstudiante(fcce);
                    getInstanceOfFacultadCumCarreraEstudianteAsignatura().create(fccea);
                }
            }
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(MatriculaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }

    }

    public Par relacionarAsignaturasEstudiantes(String facultad, String carrera, String planestudio) {
        try {
            List<Asignatura> list = getInstanceOfAsignatura().findAsignaturaByPlanEstudio(facultad, carrera, planestudio);
            List<FacultadCumCarreraEstudiante> fccel = getInstanceOfFacultadCumCarreraEstudiante().findFacultadCumCarreraEstudianteByCarrera(facultad, carrera, planestudio);
            for (Asignatura a : list) {
                for (FacultadCumCarreraEstudiante fcce : fccel) {
                    if (getInstanceOfFacultadCumCarreraEstudianteAsignatura().findFacultadCumCarreraEstudianteAsignatura(new FacultadCumCarreraEstudianteAsignaturaPK(fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), fcce.getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId(), fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), a.getAsignaturaId())) == null) {
                        FacultadCumCarreraEstudianteAsignatura fccea = new FacultadCumCarreraEstudianteAsignatura();
                        fccea.setAprobada(false);
                        fccea.setAsignatura(a);
                        fccea.setCancelada(false);
                        fccea.setFacultadCumCarreraEstudiante(fcce);
                        getInstanceOfFacultadCumCarreraEstudianteAsignatura().create(fccea);
                    }
                }
            }
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(MatriculaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }

    }

    public List<Matricula> findAllMatricula() {
        return getInstanceOfMatricula().findMatriculaEntities();
    }

    public List<Date> findMatriculaAvailableByCarrera(String facultad, String carrera, String curso) {
        Carrera c = getInstanceOfCarrera().findCarreraByCarreranacionalAvailable(carrera);
        if (c == null) {
            return new ArrayList<Date>();
        }
        Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
        Cum cu;
        if (f == null) {
            cu = getInstanceOfCum().findCumByNombreAvailable(facultad);
            if (cu == null) {
                return new ArrayList<Date>();
            }
            f = getInstanceOfFacultad().findFacultadByCumCarrera(cu.getCodigocum(), c.getIdcarrera());
        } else {
            cu = cu = getInstanceOfCum().findCumByNombreAvailable("");
        }
        return getInstanceOfMatricula().findMatriculaAvailableByCarrera(f.getCodigoarea(), c.getIdcarrera(), curso, cu.getCodigocum());
    }
}
