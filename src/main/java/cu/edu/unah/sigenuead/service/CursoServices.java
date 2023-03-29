/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.CarreraCursoJpaController;
import cu.edu.unah.sigenuead.controller.CarreraJpaController;
import cu.edu.unah.sigenuead.controller.CursoJpaController;
import cu.edu.unah.sigenuead.controller.UniversidadJpaController;
import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.CarreraCurso;
import cu.edu.unah.sigenuead.entity.CarreraCursoPK;
import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Universidad;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudia
 */
public class CursoServices {

    private CursoJpaController controllerCurso;

    public CursoJpaController getInstanceOfCurso() {
        return (controllerCurso == null) ? controllerCurso = new CursoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCurso;
    }

    private CarreraCursoJpaController controllerCarreraCurso;

    public CarreraCursoJpaController getInstanceOfCarreraCurso() {
        return (controllerCarreraCurso == null) ? controllerCarreraCurso = new CarreraCursoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCarreraCurso;
    }

    private UniversidadJpaController controllerUniversidad;

    public UniversidadJpaController getInstanceOfUniversidad() {
        return (controllerUniversidad == null) ? controllerUniversidad = new UniversidadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerUniversidad;
    }

    private CarreraJpaController controllerCarrera;

    public CarreraJpaController getInstanceOfCarrera() {
        return (controllerCarrera == null) ? controllerCarrera = new CarreraJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCarrera;
    }

    public Par addCurso(String id, Date fechainiciomatriculacurso, Date fechafinmatricula, Date fechainiciorematricula, Date fechafinrematricula, Date fechagraduacion, boolean cursoactual) {
        try {
            String[] codigo = id.split("-");
            int year1 = Integer.parseInt(codigo[0]);
            int year2 = Integer.parseInt(codigo[1]);
            if ((fechafinmatricula.getYear() + 1900 == year1 || fechafinmatricula.getYear() + 1900 == year2) && (fechafinrematricula.getYear() + 1900 == year1 || fechafinrematricula.getYear() + 1900 == year2) && (fechagraduacion.getYear() + 1900 == year2) && (fechainiciomatriculacurso.getYear() + 1900 == year1 || fechainiciomatriculacurso.getYear() + 1900 == year2) && (fechainiciorematricula.getYear() + 1900 == year1 || fechainiciorematricula.getYear() + 1900 == year2) && fechainiciomatriculacurso.before(fechafinmatricula) && fechainiciorematricula.before(fechafinrematricula) && fechainiciomatriculacurso.before(fechainiciorematricula)) {
                Universidad u = getInstanceOfUniversidad().findUniversidadAvailable();
                Curso ca = getInstanceOfCurso().findCursoActual();
                if (u != null) {
                    if (cursoactual) {
                        if (ca != null) {
                            ca.setCursoactual(false);
                            getInstanceOfCurso().edit(ca);
                        }
                    } else {
                        if (ca == null) {
                            return new Par(2, "Es necesario que este curso sea selecionado como el actual");
                        }
                    }
                    Curso c = new Curso(id, fechainiciomatriculacurso, fechafinmatricula, fechainiciorematricula, fechafinrematricula, fechagraduacion, cursoactual, u);
                    getInstanceOfCurso().create(c);
                    if (ca != null) {
                        List<CarreraCurso> cclist = getInstanceOfCarrera().findCarreraInCurso(ca.getIdcurso());
                        for (CarreraCurso cc : cclist) {
                            CarreraCurso ccnew = new CarreraCurso(cc.getCarrera().getIdcarrera(), c.getIdcurso());
                            ccnew.setCancelado(cc.getCancelado());
                            ccnew.setCantmatriculas(cc.getCantmatriculas());
                            ccnew.setCarrera(cc.getCarrera());
                            ccnew.setCurso(c);
                            getInstanceOfCarreraCurso().create(ccnew);
                        }
                    }
                    return new Par(1, texts.getSatisfactorio());
                } else {
                    return new Par(2, texts.getUniversidadActiva());
                }
            } else {
                return new Par(2, texts.getFechasIncorrectas());
            }
        } catch (Exception ex) {
            Logger.getLogger(CursoServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editCurso(String id, Date fechainiciomatriculacurso, Date fechafinmatricula, Date fechainiciorematricula, Date fechafinrematricula, Date fechagraduacion, boolean cursoactual) {
        try {
            String[] codigo = id.split("-");
            int year1 = Integer.parseInt(codigo[0]);
            int year2 = Integer.parseInt(codigo[1]);
            if ((fechafinmatricula.getYear() + 1900 == year1 || fechafinmatricula.getYear() + 1900 == year2) && (fechafinrematricula.getYear() + 1900 == year1 || fechafinrematricula.getYear() + 1900 == year2) && (fechagraduacion.getYear() + 1900 == year2) && (fechainiciomatriculacurso.getYear() + 1900 == year1 || fechainiciomatriculacurso.getYear() + 1900 == year2) && (fechainiciorematricula.getYear() + 1900 == year1 || fechainiciorematricula.getYear() + 1900 == year2) && fechainiciomatriculacurso.before(fechafinmatricula) && fechainiciorematricula.before(fechafinrematricula) && fechainiciomatriculacurso.before(fechainiciorematricula)) {
                Universidad u = getInstanceOfUniversidad().findUniversidadAvailable();
                Curso cn = getInstanceOfCurso().findCursoActual();
                Curso p = getInstanceOfCurso().findCurso(id);
                if (u != null) {
                    if (p != null) {
                        if (cursoactual) {
                            if (cn != null) {
                                cn.setCursoactual(false);
                                getInstanceOfCurso().edit(cn);
                                List<CarreraCurso> cclist = getInstanceOfCarrera().findCarreraInCurso(cn.getIdcurso());
                                for (CarreraCurso cc : cclist) {
                                    CarreraCurso ccnew = getInstanceOfCarreraCurso().findCarreraCurso(new CarreraCursoPK(cc.getCarrera().getIdcarrera(), p.getIdcurso()));
                                    if (ccnew == null) {
                                        ccnew = new CarreraCurso(cc.getCarrera().getIdcarrera(), p.getIdcurso());
                                        ccnew.setCarrera(cc.getCarrera());
                                        ccnew.setCurso(p);
                                        ccnew.setCancelado(cc.getCancelado());
                                        ccnew.setCantmatriculas(cc.getCantmatriculas());
                                        getInstanceOfCarreraCurso().create(ccnew);
                                    } else {
                                        ccnew.setCancelado(cc.getCancelado());
                                        ccnew.setCantmatriculas(cc.getCantmatriculas());
                                        getInstanceOfCarreraCurso().edit(ccnew);
                                    }
                                }
                            }
                        } else {
                            if (cn != null && cn.equals(p)) {
                                return new Par(2, "No se puede desactivar el curso debido a que se dejaria al sistema sin curso actual");
                            }
                        }
                        p.setCursoactual(cursoactual);
                        p.setFechafinmatricula(fechafinmatricula);
                        p.setFechafinrematricula(fechafinrematricula);
                        p.setFechagraduacion(fechagraduacion);
                        p.setFechainiciomatriculacurso(fechainiciomatriculacurso);
                        p.setFechainiciorematricula(fechainiciorematricula);
                        p.setUniversidadcodigouniversidad(u);
                        getInstanceOfCurso().edit(p);
                        return new Par(1, texts.getSatisfactorio());
                    } else {
                        return new Par(2, texts.getCursoNull());
                    }
                } else {
                    return new Par(2, texts.getUniversidadActiva());
                }
            } else {
                return new Par(2, texts.getFechasIncorrectas());
            }
        } catch (Exception ex) {
            Logger.getLogger(CursoServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarCurso(String id) {
//        try {
//            Curso p = getInstanceOfCurso().findCurso(id);
//            p.setCursoactual(false);
//            getInstanceOfCurso().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(CursoServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteCurso(String id) {
//        try {
//            getInstanceOfCurso().destroy(id);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(CursoServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
    public List<String> findAllCursosCodigo() {
        return getInstanceOfCurso().findAllCursoCodigo();
    }

    public List<Curso> findAllCursos() {
        return getInstanceOfCurso().findCursoEntities();
    }

    public Curso findCursoActual() {
        return getInstanceOfCurso().findCursoActual();
    }

    public String generateCode() {
        Integer x = getInstanceOfCurso().findCursoMayor();
        if (x == null) {
            Date a = new Date(System.currentTimeMillis());
            x = a.getYear() + 1900;
        }
        return x + "-" + (x + 1);
    }
}
