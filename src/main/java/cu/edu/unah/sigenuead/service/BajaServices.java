/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.BajaJpaController;
import cu.edu.unah.sigenuead.controller.EstadoEstudianteJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumCarreraEstudianteJpaController;
import cu.edu.unah.sigenuead.controller.MotivoBajaJpaController;
import cu.edu.unah.sigenuead.controller.TipoBajaJpaController;
import cu.edu.unah.sigenuead.entity.Baja;
import cu.edu.unah.sigenuead.entity.BajaPK;
import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.EstadoEstudiante;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.MotivoBaja;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.TipoBaja;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;
import cu.edu.unah.sigenuead.util.userLoging;

/**
 *
 * @author claudy
 */
public class BajaServices {

    private EstadoEstudianteJpaController controllerEstadoEstudiante;

    public EstadoEstudianteJpaController getInstanceOfEstadoEstudiante() {
        return (controllerEstadoEstudiante == null) ? controllerEstadoEstudiante = new EstadoEstudianteJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEstadoEstudiante;
    }
    private BajaJpaController controllerBaja;

    public BajaJpaController getInstanceOfBaja() {
        return (controllerBaja == null) ? controllerBaja = new BajaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerBaja;
    }
    private TipoBajaJpaController controllerTipoBaja;

    public TipoBajaJpaController getInstanceOfTipoBaja() {
        return (controllerTipoBaja == null) ? controllerTipoBaja = new TipoBajaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerTipoBaja;
    }
    private MotivoBajaJpaController controllerMotivoBaja;

    public MotivoBajaJpaController getInstanceOfMotivoBaja() {
        return (controllerMotivoBaja == null) ? controllerMotivoBaja = new MotivoBajaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerMotivoBaja;
    }
    private FacultadCumCarreraEstudianteJpaController controllerFacultadCumCarreraEstudiante;

    public FacultadCumCarreraEstudianteJpaController getInstanceOfFacultadCumCarreraEstudiante() {
        return (controllerFacultadCumCarreraEstudiante == null) ? controllerFacultadCumCarreraEstudiante = new FacultadCumCarreraEstudianteJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultadCumCarreraEstudiante;
    }
    EstudianteServices es = new EstudianteServices();

    public Par darBaja(String idEstudiante, String tipo, String motivo, String curso, Date fecha) {
        try {
            FacultadCumCarreraEstudiante fcce = es.findByEstudianteActivo(idEstudiante);
            TipoBaja tp = getInstanceOfTipoBaja().findTipoBajaByNombre(tipo);
            MotivoBaja mp = getInstanceOfMotivoBaja().findMotivoBajaByNombre(motivo);
            Curso cs = es.getInstanceOfCurso().findCurso(curso);
            if (fcce == null) {
                fcce = es.findByEstudianteNuevaMtricula(idEstudiante);
                if (fcce == null) {
                    fcce = es.findByEstudianteMatriculaPasiva(idEstudiante);
                    if (fcce == null) {
                        return new Par(2, "Este estudiante no se le puede dar baja");
                    }
                }
            }
            Baja b = getInstanceOfBaja().findBaja(idEstudiante, curso, fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula(), fecha, mp.getIdMotivoBaja(), tp.getIdTipoBaja());
            if (b != null) {
                return new Par(2, "Ya esta baja ha sido otorgada para el estudiante con CI: " + idEstudiante);
            }
            b = new Baja();
            b.setBajaCancelada(false);
            b.setCurso(cs);
            b.setFacultadCumCarreraEstudiante(fcce);
            b.setMotivoBaja(mp);
            b.setTipoBaja(tp);
            b.setBajaPK(new BajaPK(tp.getIdTipoBaja(), mp.getIdMotivoBaja(), fecha, cs.getIdcurso(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea(), fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera(), fcce.getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId(), fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula()));
            getInstanceOfBaja().create(b);
            EstadoEstudiante ee = getInstanceOfEstadoEstudiante().findEstadoEstudianteByNombre("Baja");
            fcce = es.findByEstudianteActivo(idEstudiante);
            if (fcce == null) {
                fcce = es.findByEstudianteNuevaMtricula(idEstudiante);
                if (fcce == null) {
                    fcce = es.findByEstudianteMatriculaPasiva(idEstudiante);
                }
            }
            fcce.setEstadoEstudianteestadoEstucianteId(ee);
            getInstanceOfFacultadCumCarreraEstudiante().edit(fcce);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception e) {
            return new Par(2, texts.getError());
        }
    }

    public Par revocarBaja(String idEstudiante, Baja baja) {
        try {
            FacultadCumCarreraEstudiante fcce = baja.getFacultadCumCarreraEstudiante();
            if (fcce == null) {
                return new Par(2, "Este estudiante no se le puede revocar baja");
            }
            baja.setBajaCancelada(true);
            getInstanceOfBaja().edit(baja);
            EstadoEstudiante ee = getInstanceOfEstadoEstudiante().findEstadoEstudianteByNombreAvailable("Activo");
            if (ee == null) {
                return new Par(2, "No se puede establecer el estado Activo para este estudiante");
            }
            fcce.setEstadoEstudianteestadoEstucianteId(ee);
            getInstanceOfFacultadCumCarreraEstudiante().edit(fcce);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception e) {
            return new Par(2, texts.getError());
        }
    }

    public Baja findBajaForRevocar(String idestudiante) {
        userLoging u = new userLoging();
        FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteUltimaFechaCum(idestudiante, u.getUsername());
        if (fcce == null) {
            fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteUltimaFechaFacultad(idestudiante, u.getUsername());
        }
        if (fcce == null) {
            return null;
        }
        Baja b = getInstanceOfBaja().findBajaByUltimaFecha(fcce);
        return b;
    }

    public List<String> findTipoBajaAvailable() {
        return getInstanceOfTipoBaja().findTipoBajaAvailable();
    }

    public List<String> findMotivoBajaAvailableByTipo(String tipo) {
        return getInstanceOfMotivoBaja().findMotivoBajaAvailable(tipo);
    }
}
