/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.EstadoEstudianteJpaController;
import cu.edu.unah.sigenuead.entity.EstadoEstudiante;
import cu.edu.unah.sigenuead.entity.Par;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudy
 */
public class EstadoEstudianteServices {

    private EstadoEstudianteJpaController controllerEstadoEstudiante;

    public EstadoEstudianteJpaController getInstanceOfEstadoEstudiante() {
        return (controllerEstadoEstudiante == null) ? controllerEstadoEstudiante = new EstadoEstudianteJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEstadoEstudiante;
    }

    public Par addEstadoEstudiante(String nombre, boolean cancel) {
        try {
            if (getInstanceOfEstadoEstudiante().findEstadoEstudianteByNombre(nombre) == null) {
                getInstanceOfEstadoEstudiante().create(new EstadoEstudiante(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(EstadoEstudianteServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editEstadoEstudiante(String nombre, String nombreNew, boolean cancel) {
        try {
            if (nombre.equals("Activo")) {
                return new Par(2, texts.getNoedit());
            }
            EstadoEstudiante p = getInstanceOfEstadoEstudiante().findEstadoEstudianteByNombre(nombre);
            if (p != null) {
                if (nombre.equals(nombreNew) || getInstanceOfEstadoEstudiante().findEstadoEstudianteByNombre(nombreNew) == null) {
                    p.setEstadoEstudianteNombre(nombreNew);
                    p.setEstadoEstudianteCancelado(cancel);
                    getInstanceOfEstadoEstudiante().edit(p);
                    return new Par(1, texts.getSatisfactorio());
                } else {
                    return new Par(2, texts.getEditarExistente());
                }
            } else {
                return new Par(2, texts.getEstadoEstudianteNull());
            }
        } catch (Exception ex) {
            Logger.getLogger(EstadoEstudianteServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarEstadoEstudiante(String nombre) {
//        try {
//            EstadoEstudiante p = getInstanceOfEstadoEstudiante().findEstadoEstudianteByNombre(nombre);
//            p.setEstadoEstudianteCancelado(!p.getEstadoEstudianteCancelado());
//            getInstanceOfEstadoEstudiante().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(EstadoEstudianteServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteEstadoEstudiante(String nombre) {
//        try {
//            EstadoEstudiante p = getInstanceOfEstadoEstudiante().findEstadoEstudianteByNombre(nombre);
//            getInstanceOfEstadoEstudiante().destroy(p.getEstadoEstucianteId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(EstadoEstudianteServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
    public List<EstadoEstudiante> findAllEstadoEstudiante() {
        return getInstanceOfEstadoEstudiante().findEstadoEstudianteEntities();
    }

    public List<String> findEstadoEstudianteAvailable() {
        return getInstanceOfEstadoEstudiante().findEstadoEstudianteAvailable();
    }
}
