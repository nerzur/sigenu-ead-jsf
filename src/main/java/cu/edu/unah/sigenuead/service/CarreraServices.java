/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.CarreraCursoJpaController;
import cu.edu.unah.sigenuead.controller.CarreraJpaController;
import cu.edu.unah.sigenuead.controller.CarreranacionalJpaController;
import cu.edu.unah.sigenuead.controller.CumJpaController;
import cu.edu.unah.sigenuead.controller.CursoJpaController;
import cu.edu.unah.sigenuead.controller.EspecialidadJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumCarreraJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumJpaController;
import cu.edu.unah.sigenuead.controller.FacultadJpaController;
import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.CarreraCurso;
import cu.edu.unah.sigenuead.entity.CarreraCursoPK;
import cu.edu.unah.sigenuead.entity.Carreranacional;
import cu.edu.unah.sigenuead.entity.Cum;
import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.Especialidad;
import cu.edu.unah.sigenuead.entity.Facultad;
import cu.edu.unah.sigenuead.entity.FacultadCum;
import cu.edu.unah.sigenuead.entity.FacultadCumCarrera;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraPK;
import cu.edu.unah.sigenuead.entity.FacultadCumPK;
import cu.edu.unah.sigenuead.entity.Par;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudy
 */
public class CarreraServices {

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
    private FacultadCumCarreraJpaController controllerFacultadCumCarrera;

    public FacultadCumCarreraJpaController getInstanceOfFacultadCumCarrera() {
        return (controllerFacultadCumCarrera == null) ? controllerFacultadCumCarrera = new FacultadCumCarreraJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultadCumCarrera;
    }
    private FacultadCumJpaController controllerFacultadCum;

    public FacultadCumJpaController getInstanceOfFacultadCum() {
        return (controllerFacultadCum == null) ? controllerFacultadCum = new FacultadCumJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultadCum;
    }

    private CursoJpaController controllerCurso;

    public CursoJpaController getInstanceOfCurso() {
        return (controllerCurso == null) ? controllerCurso = new CursoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCurso;
    }
    private CarreraCursoJpaController controllerCarreraCurso;

    public CarreraCursoJpaController getInstanceOfCarreraCurso() {
        return (controllerCarreraCurso == null) ? controllerCarreraCurso = new CarreraCursoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCarreraCurso;
    }
    private CumJpaController controllerCum;

    public CumJpaController getInstanceOfCum() {
        return (controllerCum == null) ? controllerCum = new CumJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCum;
    }

    public Par addCarrera(String carreraNacional, String facultad, String especialidad, boolean cancel) {
        try {
            Carrera c = new Carrera();
            Especialidad e = getInstanceOfEspecialidad().findEspecialidadByNombreAvailable(especialidad);
            if (e == null) {
                return new Par(2, texts.getEspecialidadNull());
            }
            Carreranacional cn = getInstanceOfCarreranacional().findCarreraNacionalByNombreAvailable(carreraNacional, e.getIdespecialidad());
            if (cn == null) {
                return new Par(2, texts.getCarreraNacionalNull());
            }
            c.setCarreranacionalidcarreranacional(cn);
            Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
            if (f == null) {
                return new Par(2, texts.getFacultadNull());
            }
            c.setFacultadcodigoarea(f);
            if (getInstanceOfCarrera().findCarreraByCarreraNacional(cn.getIdcarreranacional(), f.getCodigoarea()) == null) {
                getInstanceOfCarrera().create(c);
                FacultadCum fc = getInstanceOfFacultadCum().findFacultadCum(new FacultadCumPK("", f.getCodigoarea()));
                if (fc == null) {
                    fc = new FacultadCum();
                    fc.setCancelado(false);
                    fc.setCum(getInstanceOfCum().findCum(""));
                    fc.setFacultad(f);
                    getInstanceOfFacultadCum().create(fc);
                } else {
                    if (fc.getCancelado()) {
                        fc.setCancelado(false);
                        getInstanceOfFacultadCum().edit(fc);
                    }
                }
                FacultadCumCarrera fcc = new FacultadCumCarrera();
                fcc.setCancelado(cancel);
                fcc.setCarrera(c);
                fcc.setFacultadCum(fc);
                getInstanceOfFacultadCumCarrera().create(fcc);
                Curso curso = getInstanceOfCurso().findCursoActual();
                if (curso == null) {
                    return new Par(2, texts.getCursoNull());
                }
                CarreraCurso cc = new CarreraCurso(c.getIdcarrera(), curso.getIdcurso());
                cc.setCancelado(cancel);
                cc.setCantmatriculas(3);
                cc.setCarrera(c);
                cc.setCurso(curso);
                getInstanceOfCarreraCurso().create(cc);
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(CarreraNacionalServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editCarrera(String carreraNacional, String facultad, String especialidad, boolean cancel) {
        try {
            Especialidad e = getInstanceOfEspecialidad().findEspecialidadByNombreAvailable(especialidad);
            if (e == null) {
                return new Par(2, texts.getEspecialidadNull());
            }
            Carreranacional cn = getInstanceOfCarreranacional().findCarreraNacionalByNombreAvailable(carreraNacional, e.getIdespecialidad());
            if (cn == null) {
                return new Par(2, texts.getCarreraNacionalNull());
            }
            Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
            if (f == null) {
                return new Par(2, texts.getFacultadNull());
            }
            Carrera c = getInstanceOfCarrera().findCarreraByCarreraNacional(cn.getIdcarreranacional(), f.getCodigoarea());
            if (c != null) {
                c.setCanceladacarrera(cancel);
                getInstanceOfCarrera().edit(c);
                Curso curso = getInstanceOfCurso().findCursoActual();
                CarreraCurso cc = getInstanceOfCarreraCurso().findCarreraCurso(new CarreraCursoPK(c.getIdcarrera(), curso.getIdcurso()));
                cc.setCancelado(cancel);
                getInstanceOfCarreraCurso().edit(cc);
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getCarreraNull());
            }
        } catch (Exception ex) {
            Logger.getLogger(CarreraNacionalServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelCarrera(String carreraNacional, String facultad, String especialidad) {
//        try {
//            Especialidad e = getInstanceOfEspecialidad().findEspecialidadByNombre(especialidad);
//            Carreranacional cn = getInstanceOfCarreranacional().findCarreraNacionalByNombre(carreraNacional, e.getIdespecialidad());
//            Facultad f = getInstanceOfFacultad().findFacultadByNombre(facultad);
//            Carrera c = getInstanceOfCarrera().findCarreraByCarreraNacional(cn.getIdcarreranacional(), f.getCodigoarea());
//            if (c != null) {
//                c.setCanceladacarrera(!c.getCanceladacarrera());
//                return new Par(1, texts.getSatisfactorio());
//            } else {
//                return new Par(3, texts.getInformacion());
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(CarreraNacionalServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteCarreraNacional(String carreraNacional, String facultad, String especialidad) {
//        try {
//            Especialidad e = getInstanceOfEspecialidad().findEspecialidadByNombre(especialidad);
//            Carreranacional cn = getInstanceOfCarreranacional().findCarreraNacionalByNombre(carreraNacional, e.getIdespecialidad());
//            Facultad f = getInstanceOfFacultad().findFacultadByNombre(facultad);
//            Carrera c = getInstanceOfCarrera().findCarreraByCarreraNacional(cn.getIdcarreranacional(), f.getCodigoarea());
//            getInstanceOfCarrera().destroy(c.getIdcarrera());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(CarreraNacionalServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
    public List<Carrera> findAllCarrera() {
        return getInstanceOfCarrera().findCarreraEntities();
    }

    public List<CarreraCurso> findCarreraInCurso(String curso) {
        return getInstanceOfCarrera().findCarreraInCurso(curso);
    }

    public List<String> findCarreraAvailable(String facultad) {
        Facultad f = getInstanceOfFacultad().findFacultadByNombre(facultad);
        if (f != null) {
            return getInstanceOfCarrera().findCarreraAvailableByFacultad(f.getCodigoarea());
        }
        return new ArrayList<String>();
    }

    public List<String> findCarreraAvailableByArea(String area) {
        Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(area);
        if (f == null) {
            Cum c = getInstanceOfCum().findCumByNombreAvailable(area);
            if (c == null) {
                return new ArrayList<String>();
            }
            return getInstanceOfCarrera().findCarreraAvailableByCum(area);
        }
        return getInstanceOfCarrera().findCarreraAvailableByFacultad(f.getCodigoarea());
    }

    public List<String> findCarreraAvailableByFacultadCum(String facultad, String cum) {
        Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
        if (f == null) {
            return new ArrayList<String>();
        }
        Cum c = getInstanceOfCum().findCumByNombreAvailable(cum);
        if (c == null) {
            return new ArrayList<String>();
        }
        return getInstanceOfCarrera().findCarreraAvailableByFacultadCum(f.getCodigoarea(), c.getCodigocum());
    }
}
