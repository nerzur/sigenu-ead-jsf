/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.TipoplanestudioJpaController;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Tipoplanestudio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudy
 */
public class TipoplanestudioServices {
    private TipoplanestudioJpaController controllerTipoplanestudio;

    public TipoplanestudioJpaController getInstanceOfTipoplanestudio() {
        return (controllerTipoplanestudio == null) ? controllerTipoplanestudio = new TipoplanestudioJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerTipoplanestudio;
    }

    public Par addTipoplanestudio(String nombre, boolean cancel) {
        try {
            if (getInstanceOfTipoplanestudio().findTipoplanestudio(nombre) == null) {
                getInstanceOfTipoplanestudio().create(new Tipoplanestudio(nombre, cancel, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(TipoplanestudioServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editTipoplanestudio(String nombre, String nombreNew, boolean cancel) {
        try {
            Tipoplanestudio p = getInstanceOfTipoplanestudio().findTipoplanestudio(nombre);
            if (p==null) {
                return new Par(2, texts.getTipoPlanEstudioNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfTipoplanestudio().findTipoplanestudio(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setNombretipoplanestudio(nombreNew);
            p.setTipoplanestudiocancelado(cancel);
            getInstanceOfTipoplanestudio().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(TipoplanestudioServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarTipoplanestudio(String nombre) {
//        try {
//            Tipoplanestudio p = getInstanceOfTipoplanestudio().findTipoplanestudio(nombre);
//            p.setTipoplanestudiocancelado(!p.getTipoplanestudiocancelado());
//            getInstanceOfTipoplanestudio().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(TipoplanestudioServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteTipoplanestudio(String nombre) {
//        try {
//            getInstanceOfTipoplanestudio().destroy(nombre);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(TipoplanestudioServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<Tipoplanestudio> findAllTipoplanestudio() {
        return getInstanceOfTipoplanestudio().findTipoplanestudioEntities();
    }
    public List<String> findTipoplanestudioAvailable() {
        return getInstanceOfTipoplanestudio().findTipoPlanEstudioAvailable();
    }
}
