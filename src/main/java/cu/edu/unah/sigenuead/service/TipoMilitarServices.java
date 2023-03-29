/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.TipoMilitarJpaController;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.TipoMilitar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudy
 */
public class TipoMilitarServices {

    private TipoMilitarJpaController controllerTipoMilitar;

    public TipoMilitarJpaController getInstanceOfTipoMilitar() {
        return (controllerTipoMilitar == null) ? controllerTipoMilitar = new TipoMilitarJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerTipoMilitar;
    }

    public Par addTipoMilitar(String nombre, boolean cancel) {
        try {
            if (getInstanceOfTipoMilitar().findTipoMilitarByNombre(nombre) == null) {
                getInstanceOfTipoMilitar().create(new TipoMilitar(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(TipoMilitarServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editTipoMilitar(String nombre, String nombreNew, boolean cancel) {
        try {
            TipoMilitar p = getInstanceOfTipoMilitar().findTipoMilitarByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getTipoMilitarNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfTipoMilitar().findTipoMilitarByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setNombreTipo(nombreNew);
            p.setCanceladoTipoMilitar(cancel);
            getInstanceOfTipoMilitar().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(TipoMilitarServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarTipoMilitar(String nombre) {
//        try {
//            TipoMilitar p = getInstanceOfTipoMilitar().findTipoMilitarByNombre(nombre);
//            p.setCanceladoTipoMilitar(!p.getCanceladoTipoMilitar());
//            getInstanceOfTipoMilitar().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(TipoMilitarServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteTipoMilitar(String nombre) {
//        try {
//            TipoMilitar p = getInstanceOfTipoMilitar().findTipoMilitarByNombre(nombre);
//            getInstanceOfTipoMilitar().destroy(p.getIdTipoMilitar());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(TipoMilitarServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<TipoMilitar> findAllTipoMilitar() {
        return getInstanceOfTipoMilitar().findTipoMilitarEntities();
    }

    public List<String> findTipoMilitarAvailable() {
        return getInstanceOfTipoMilitar().findTipoMilitarAvailable();
    }
}
