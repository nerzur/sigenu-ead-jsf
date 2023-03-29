/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.TipoAsignaturaJpaController;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.TipoAsignatura;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudy
 */
public class TipoAsignaturaServices {

    private TipoAsignaturaJpaController controllerTipoAsignatura;

    public TipoAsignaturaJpaController getInstanceOfTipoAsignatura() {
        return (controllerTipoAsignatura == null) ? controllerTipoAsignatura = new TipoAsignaturaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerTipoAsignatura;
    }

    public Par addTipoAsignatura(String nombre, boolean cancel) {
        try {
            if (getInstanceOfTipoAsignatura().findTipoAsignaturaByNombre(nombre) == null) {
                getInstanceOfTipoAsignatura().create(new TipoAsignatura(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(TipoAsignaturaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editTipoAsignatura(String nombre, String nombreNew, boolean cancel) {
        try {
            TipoAsignatura p = getInstanceOfTipoAsignatura().findTipoAsignaturaByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getTipoAsignaturaNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfTipoAsignatura().findTipoAsignaturaByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setTipoAsignaturaNombre(nombreNew);
            p.setTipoAsignaturaCancelado(cancel);
            getInstanceOfTipoAsignatura().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(TipoAsignaturaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarTipoAsignatura(String nombre) {
//        try {
//            TipoAsignatura p = getInstanceOfTipoAsignatura().findTipoAsignaturaByNombre(nombre);
//            p.setTipoAsignaturaCancelado(true);
//            getInstanceOfTipoAsignatura().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(TipoAsignaturaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteTipoAsignatura(String nombre) {
//        try {
//            TipoAsignatura p = getInstanceOfTipoAsignatura().findTipoAsignaturaByNombre(nombre);
//            getInstanceOfTipoAsignatura().destroy(p.getTipoAsignaturaId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(TipoAsignaturaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<TipoAsignatura> findAllTipoAsignatura() {
        return getInstanceOfTipoAsignatura().findTipoAsignaturaEntities();
    }
    public List<String> findTipoAsignaturaAvailable() {
        return getInstanceOfTipoAsignatura().findTipoAsignaturaAvailable();
    }
}
