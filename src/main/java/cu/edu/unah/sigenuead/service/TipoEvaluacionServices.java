/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.TipoEvaluacionJpaController;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.TipoEvaluacion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudy
 */
public class TipoEvaluacionServices {

    private TipoEvaluacionJpaController controllerTipoEvaluacion;

    public TipoEvaluacionJpaController getInstanceOfTipoEvaluacion() {
        return (controllerTipoEvaluacion == null) ? controllerTipoEvaluacion = new TipoEvaluacionJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerTipoEvaluacion;
    }

    public Par addTipoEvaluacion(String nombre, boolean cancel) {
        try {
            if (getInstanceOfTipoEvaluacion().findTipoEvaluacionByNombre(nombre) == null) {
                getInstanceOfTipoEvaluacion().create(new TipoEvaluacion(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(TipoEvaluacionServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editTipoEvaluacion(String nombre, String nombreNew, boolean cancel) {
        try {
            TipoEvaluacion p = getInstanceOfTipoEvaluacion().findTipoEvaluacionByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getTipoEvaluacionNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfTipoEvaluacion().findTipoEvaluacionByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setTipoEvaluacionNombre(nombreNew);
            p.setTipoEavluacionCancelado(cancel);
            getInstanceOfTipoEvaluacion().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(TipoEvaluacionServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarTipoEvaluacion(String nombre) {
//        try {
//            TipoEvaluacion p = getInstanceOfTipoEvaluacion().findTipoEvaluacionByNombre(nombre);
//            p.setTipoEavluacionCancelado(true);
//            getInstanceOfTipoEvaluacion().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(TipoEvaluacionServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteTipoEvaluacion(String nombre) {
//        try {
//            TipoEvaluacion p = getInstanceOfTipoEvaluacion().findTipoEvaluacionByNombre(nombre);
//            getInstanceOfTipoEvaluacion().destroy(p.getTipoEvaluacionId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(TipoEvaluacionServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<TipoEvaluacion> findAllTipoEvaluacion() {
        return getInstanceOfTipoEvaluacion().findTipoEvaluacionEntities();
    }
    public List<String> findTipoEvaluacionAvailable() {
        return getInstanceOfTipoEvaluacion().findTipoEvaluacionAvailable();
    }
}
