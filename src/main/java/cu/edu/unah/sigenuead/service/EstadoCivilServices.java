/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.EstadoCivilJpaController;
import cu.edu.unah.sigenuead.entity.EstadoCivil;
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
public class EstadoCivilServices {

    private EstadoCivilJpaController controllerEstadoCivil;

    public EstadoCivilJpaController getInstanceOfEstadoCivil() {
        return (controllerEstadoCivil == null) ? controllerEstadoCivil = new EstadoCivilJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEstadoCivil;
    }

    public Par addEstadoCivil(String nombre, boolean cancel) {
        try {
            if (getInstanceOfEstadoCivil().findEstadoCivilByNombre(nombre) == null) {
                getInstanceOfEstadoCivil().create(new EstadoCivil(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(2, texts.getError());
            }
        } catch (Exception ex) {
            Logger.getLogger(EstadoCivilServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editEstadoCivil(String nombre, String nombreNew, boolean cancel) {
        try {
            EstadoCivil p = getInstanceOfEstadoCivil().findEstadoCivilByNombre(nombre);
            if (p != null) {
                if (nombre.equals(nombreNew) || getInstanceOfEstadoCivil().findEstadoCivilByNombre(nombreNew) == null) {
                    p.setEstadoCivilNombre(nombreNew);
                    p.setEstadoCivilCancelado(cancel);
                    getInstanceOfEstadoCivil().edit(p);
                    return new Par(1, texts.getSatisfactorio());
                } else {
                    return new Par(2, texts.getEditarExistente());
                }
            } else {
                return new Par(2, texts.getEstadoCivilNull());
            }
        } catch (Exception ex) {
            Logger.getLogger(EstadoCivilServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarEstadoCivil(String nombre) {
//        try {
//            EstadoCivil p = getInstanceOfEstadoCivil().findEstadoCivilByNombre(nombre);
//            p.setEstadoCivilCancelado(!p.getEstadoCivilCancelado());
//            getInstanceOfEstadoCivil().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(EstadoCivilServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteEstadoCivil(String nombre) {
//        try {
//            EstadoCivil p = getInstanceOfEstadoCivil().findEstadoCivilByNombre(nombre);
//            getInstanceOfEstadoCivil().destroy(p.getEstadoCivilId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(EstadoCivilServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<EstadoCivil> findAllEstadoCivil() {
        return getInstanceOfEstadoCivil().findEstadoCivilEntities();
    }

    public List<String> findEstadoCivilAvailable() {
        return getInstanceOfEstadoCivil().findEstadoCivilAvailable();
    }
}
