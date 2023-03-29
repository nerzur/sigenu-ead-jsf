/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.AsignaturaJpaController;
import cu.edu.unah.sigenuead.controller.CarreraJpaController;
import cu.edu.unah.sigenuead.controller.CursoJpaController;
import cu.edu.unah.sigenuead.controller.DisciplinaJpaController;
import cu.edu.unah.sigenuead.controller.DisciplinaPlanestudioJpaController;
import cu.edu.unah.sigenuead.controller.FacultadJpaController;
import cu.edu.unah.sigenuead.controller.PlanestudioJpaController;
import cu.edu.unah.sigenuead.controller.TipoplanestudioJpaController;
import cu.edu.unah.sigenuead.entity.Asignatura;
import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.Disciplina;
import cu.edu.unah.sigenuead.entity.DisciplinaPlanestudio;
import cu.edu.unah.sigenuead.entity.DisciplinaPlanestudioPK;
import cu.edu.unah.sigenuead.entity.Facultad;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Planestudio;
import cu.edu.unah.sigenuead.entity.Tipoplanestudio;
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
public class PlanestudioServices {

    private PlanestudioJpaController controllerPlanestudio;

    public PlanestudioJpaController getInstanceOfPlanestudio() {
        return (controllerPlanestudio == null) ? controllerPlanestudio = new PlanestudioJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerPlanestudio;
    }
    private FacultadJpaController controllerFacultad;

    public FacultadJpaController getInstanceOfFacultad() {
        return (controllerFacultad == null) ? controllerFacultad = new FacultadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultad;
    }
    private CursoJpaController controllerCurso;

    public CursoJpaController getInstanceOfCurso() {
        return (controllerCurso == null) ? controllerCurso = new CursoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCurso;
    }
    private CarreraJpaController controllerCarrera;

    public CarreraJpaController getInstanceOfCarrera() {
        return (controllerCarrera == null) ? controllerCarrera = new CarreraJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCarrera;
    }
    private TipoplanestudioJpaController controllertipoplan;

    public TipoplanestudioJpaController getInstanceOfTipoPlanEstudio() {
        return (controllertipoplan == null) ? controllertipoplan = new TipoplanestudioJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllertipoplan;
    }
    private DisciplinaJpaController controllerDisciplina;

    public DisciplinaJpaController getInstanceOfDisciplina() {
        return (controllerDisciplina == null) ? controllerDisciplina = new DisciplinaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerDisciplina;
    }
    private DisciplinaPlanestudioJpaController controllerDisciplinaPlanestudio;

    public DisciplinaPlanestudioJpaController getInstanceOfDisciplinaPlanestudio() {
        return (controllerDisciplinaPlanestudio == null) ? controllerDisciplinaPlanestudio = new DisciplinaPlanestudioJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerDisciplinaPlanestudio;
    }
    private AsignaturaJpaController controllerAsignatura;

    public AsignaturaJpaController getInstanceOfAsignatura() {
        return (controllerAsignatura == null) ? controllerAsignatura = new AsignaturaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerAsignatura;
    }

    AsignaturaServices as = new AsignaturaServices();

    public Par addPlanestudio(String comentario, boolean listo, int cantoportunidades, boolean cancel, String curso, String facultad, String carrera, String tipoplan) {
        try {
            Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
            if (f == null) {
                return new Par(2, texts.getFacultadNull());
            }
            Carrera c = getInstanceOfCarrera().findCarreraByFacultadCarreranacionalAvailable(facultad, carrera);
            if (c == null) {
                return new Par(2, texts.getCarreraNull());
            }
            Curso cs = getInstanceOfCurso().findCurso(curso);
            if (cs == null) {
                return new Par(2, texts.getCursoNull());
            }
            Tipoplanestudio t = getInstanceOfTipoPlanEstudio().findTipoPlanEstudioByNombreAvailable(tipoplan);
            if (t == null) {
                return new Par(2, texts.getTipoPlanEstudioNull());
            }
            Planestudio m = getInstanceOfPlanestudio().findPlanEstudioByCarreraAvailable(f.getCodigoarea(), carrera, tipoplan);
            if (m == null) {
                m = new Planestudio(comentario, listo, cancel, cantoportunidades, c, cs, t);
                getInstanceOfPlanestudio().create(m);
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(PlanestudioServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editPlanestudio(String comentario, boolean listo, int cantoportunidades, boolean cancel, String curso, String facultad, String carrera, String tipoplan) {
        try {
            Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
            if (f == null) {
                return new Par(2, texts.getFacultadNull());
            }
            Carrera c = getInstanceOfCarrera().findCarreraByFacultadCarreranacionalAvailable(facultad, carrera);
            if (c == null) {
                return new Par(2, texts.getCarreraNull());
            }
            Curso cs = getInstanceOfCurso().findCurso(curso);
            if (cs == null) {
                return new Par(2, texts.getCursoNull());
            }
            Tipoplanestudio t = getInstanceOfTipoPlanEstudio().findTipoPlanEstudioByNombreAvailable(tipoplan);
            if (t == null) {
                return new Par(2, texts.getTipoPlanEstudioNull());
            }
            Planestudio m = getInstanceOfPlanestudio().findPlanEstudioByCarreraAvailable(f.getCodigoarea(), carrera, tipoplan);
            if (m != null) {
                m.setCantoportunidades(cantoportunidades);
                m.setCursoactivacion(cs);
                m.setPlanEstudioCancelado(cancel);
                m.setPlanEstudioComentario(comentario);
                m.setPlanEstudioListo(listo);
                getInstanceOfPlanestudio().edit(m);
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(2, texts.getPlanEstudioNull());
            }
        } catch (Exception ex) {
            Logger.getLogger(PlanestudioServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarPlanestudio(String facultad, String carrera, String tipoplan) {
//        try {
//            Facultad f = getInstanceOfFacultad().findFacultadByNombre(facultad);
//            Carrera c = getInstanceOfCarrera().findCarreraByFacultadCarreranacional(f.getCodigoarea(), carrera);
//            Tipoplanestudio t = getInstanceOfTipoPlanEstudio().findTipoplanestudio(tipoplan);
//            Planestudio m = getInstanceOfPlanestudio().findPlanEstudioByCarrera(f.getCodigoarea(), carrera, tipoplan);
//            if (m != null) {
//                m.setPlanEstudioCancelado(!m.getPlanEstudioCancelado());
//                getInstanceOfPlanestudio().edit(m);
//                return new Par(1, texts.getSatisfactorio());
//            } else {
//                return new Par(3, "El elemento no existe");
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(PlanestudioServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deletePlanestudio(String facultad, String carrera, String tipoplan) {
//        try {
//            Facultad f = getInstanceOfFacultad().findFacultadByNombre(facultad);
//            Carrera c = getInstanceOfCarrera().findCarreraByFacultadCarreranacional(f.getCodigoarea(), carrera);
//            Tipoplanestudio t = getInstanceOfTipoPlanEstudio().findTipoplanestudio(tipoplan);
//            Planestudio m = getInstanceOfPlanestudio().findPlanEstudioByCarrera(f.getCodigoarea(), carrera, tipoplan);
//            if (m != null) {
//                getInstanceOfPlanestudio().destroy(m.getIdplanestudio());
//                return new Par(1, texts.getSatisfactorio());
//            } else {
//                return new Par(3, "El elemento no existe");
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(PlanestudioServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
    public Par addDisciplinastoPlanEstudio(String facultad, String carrera, String tipoplan, String disciplina, String objetivos, String programa, boolean cancel) {
        try {
            Facultad f = getInstanceOfFacultad().findFacultadByNombre(facultad);
            Carrera c = getInstanceOfCarrera().findCarreraByFacultadCarreranacional(f.getCodigoarea(), carrera);
            Tipoplanestudio t = getInstanceOfTipoPlanEstudio().findTipoplanestudio(tipoplan);
            Planestudio m = getInstanceOfPlanestudio().findPlanEstudioByCarrera(facultad, carrera, tipoplan);
            if (m != null) {
                Disciplina d = getInstanceOfDisciplina().findDisciplinaByNombre(disciplina);
                DisciplinaPlanestudio dp = getInstanceOfDisciplinaPlanestudio().findDisciplinaPlanestudio(new DisciplinaPlanestudioPK(d.getDisciplinaCodigo(), m.getIdplanestudio()));
                if (dp == null) {
                    dp = new DisciplinaPlanestudio(new DisciplinaPlanestudioPK(d.getDisciplinaCodigo(), m.getIdplanestudio()));
                    dp.setObjetivos(objetivos);
                    dp.setPrograma(programa);
                    dp.setCancelado(cancel);
                    dp.setDisciplina(d);
                    dp.setPlanestudio(m);
                    getInstanceOfDisciplinaPlanestudio().create(dp);
                    return new Par(1, texts.getSatisfactorio());
                } else {
                    return new Par(2, texts.getError());
                }
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(PlanestudioServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editDisciplinasToPlanEstudio(String facultad, String carrera, String tipoplan, String disciplina, String objetivos, String programa, boolean cancel) {
        try {
            Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
            Carrera c = getInstanceOfCarrera().findCarreraByFacultadCarreranacionalAvailable(facultad, carrera);
            Tipoplanestudio t = getInstanceOfTipoPlanEstudio().findTipoPlanEstudioByNombreAvailable(tipoplan);
            Planestudio m = getInstanceOfPlanestudio().findPlanEstudioByCarrera(facultad, carrera, tipoplan);
            if (m != null) {
                Disciplina d = getInstanceOfDisciplina().findDisciplinaByNombreAvailable(disciplina);
                if (d == null) {
                    return new Par(2, texts.getDisciplinaNull());
                }
                DisciplinaPlanestudio dp = getInstanceOfDisciplinaPlanestudio().findDisciplinaPlanestudio(new DisciplinaPlanestudioPK(d.getDisciplinaCodigo(), m.getIdplanestudio()));
                if (dp != null) {
                    dp.setCancelado(cancel);
                    dp.setObjetivos(objetivos);
                    dp.setPrograma(programa);
                    getInstanceOfDisciplinaPlanestudio().edit(dp);
                    return new Par(1, texts.getSatisfactorio());
                } else {
                    return new Par(2, texts.getDisciplinaPlanEstudioNull());
                }
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(PlanestudioServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public List<Planestudio> findAllPlanestudio() {
        return getInstanceOfPlanestudio().findPlanestudioEntities();
    }

    public List<String> findPlanestudioAvailableByCarrera(String facultad, String carrera) {
        return getInstanceOfPlanestudio().findPlanestudioAvailableByCarrera(facultad, carrera);
    }
    public List<String> findPlanestudioAvailableByCarreraFecha(String facultad, String carrera, Date fecha) {
        return getInstanceOfPlanestudio().findPlanestudioAvailableByCarreraFecha(facultad, carrera, fecha);
    }

    public Planestudio findPlanEstudioByCarrera(String facultad, String carrera, String tipoplan) {
        if (!facultad.equals("") && !carrera.equals("") && !tipoplan.equals("")) {
            return getInstanceOfPlanestudio().findPlanEstudioByCarrera(facultad, carrera, tipoplan);
        }
        return null;
    }
}
