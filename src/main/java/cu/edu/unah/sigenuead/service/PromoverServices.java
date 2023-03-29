/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.CumJpaController;
import cu.edu.unah.sigenuead.controller.EstadoEstudianteJpaController;
import cu.edu.unah.sigenuead.controller.EstudianteJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumCarreraEstudianteJpaController;
import cu.edu.unah.sigenuead.controller.FacultadJpaController;
import cu.edu.unah.sigenuead.entity.Cum;
import cu.edu.unah.sigenuead.entity.EstadoEstudiante;
import cu.edu.unah.sigenuead.entity.Estudiante_promover;
import cu.edu.unah.sigenuead.entity.Facultad;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.Par;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;
import cu.edu.unah.sigenuead.util.userLoging;

/**
 *
 * @author claudy
 */
public class PromoverServices {

    private FacultadJpaController controllerFacultad;

    public FacultadJpaController getInstanceOfFacultad() {
        return (controllerFacultad == null) ? controllerFacultad = new FacultadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultad;
    }
    private EstadoEstudianteJpaController controllerEstadoEstudiante;

    public EstadoEstudianteJpaController getInstanceOfEstadoEstudiante() {
        return (controllerEstadoEstudiante == null) ? controllerEstadoEstudiante = new EstadoEstudianteJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEstadoEstudiante;
    }
    private CumJpaController controllerCum;

    public CumJpaController getInstanceOfCum() {
        return (controllerCum == null) ? controllerCum = new CumJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCum;
    }
    private EstudianteJpaController controllerEstudiante;

    public EstudianteJpaController getInstanceOfEstudiante() {
        return (controllerEstudiante == null) ? controllerEstudiante = new EstudianteJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEstudiante;
    }
    private FacultadCumCarreraEstudianteJpaController controllerFacultadCumCarreraEstudiante;

    public FacultadCumCarreraEstudianteJpaController getInstanceOfFacultadCumCarreraEstudiante() {
        return (controllerFacultadCumCarreraEstudiante == null) ? controllerFacultadCumCarreraEstudiante = new FacultadCumCarreraEstudianteJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultadCumCarreraEstudiante;
    }
    EstudianteServices es = new EstudianteServices();

    public Par promoverEstudiantes(List<Estudiante_promover> est) {
        try {
            EstadoEstudiante ee = getInstanceOfEstadoEstudiante().findEstadoEstudianteByNombreAvailable("Egresado");
            for (Estudiante_promover fcce : est) {
                if (fcce.isPromover()) {
                    fcce.getFcce().setEstadoEstudianteestadoEstucianteId(ee);
                    getInstanceOfFacultadCumCarreraEstudiante().edit(fcce.getFcce());
                }
            }
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(MatriculaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public List<Estudiante_promover> promoverEstudiantes(String area, String carrera) {
        try {
            List<FacultadCumCarreraEstudiante> est = new ArrayList<FacultadCumCarreraEstudiante>();
            Facultad f = getInstanceOfFacultad().findFacultadByNombreAvailable(area);
            EstadoEstudiante ee = getInstanceOfEstadoEstudiante().findEstadoEstudianteByNombreAvailable("Egresado");
            if (f == null) {
                Cum c = getInstanceOfCum().findCumByNombreAvailable(area);
                if (c != null) {
                    est = getInstanceOfEstudiante().findEstudiantesActivosByCum(area, carrera);
                    est.removeAll(getInstanceOfEstudiante().findEstudiantesActivosConAsignaturasPendientesByCum(area, carrera));
                }
            } else {
                est = getInstanceOfEstudiante().findEstudiantesActivosByFacultad(area, carrera);
                est.removeAll(getInstanceOfEstudiante().findEstudiantesActivosConAsignaturasPendientesByFacultad(area, carrera));
            }
            List<Estudiante_promover> ep = new ArrayList<Estudiante_promover>();
            for (FacultadCumCarreraEstudiante fcce : est) {
                ep.add(new Estudiante_promover(fcce, false));
            }
            return ep;
        } catch (Exception ex) {
            Logger.getLogger(MatriculaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Estudiante_promover>();
        }
    }

    public Par revocarPromover(String idEstudiante) {
        try {
            FacultadCumCarreraEstudiante fcce = getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteUltimaFecha(idEstudiante);
            if (fcce == null) {
                return new Par(2, "Este estudiante no se le puede revocar promocion");
            }
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
    
    public FacultadCumCarreraEstudiante findPromoverForRevocar(String idestudiante) {
        userLoging u=new userLoging();
        FacultadCumCarreraEstudiante fcce=getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteEgresadoCum(idestudiante, u.getUsername());
        if (fcce==null) {
            fcce=getInstanceOfFacultadCumCarreraEstudiante().FindFacultadCumCarreraEstudianteByEstudianteEgresadoFacultad(idestudiante, u.getUsername());
        }
        return fcce;
    }

}
