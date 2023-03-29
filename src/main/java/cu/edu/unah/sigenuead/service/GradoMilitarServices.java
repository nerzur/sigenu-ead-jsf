/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.GradoMilitarJpaController;
import cu.edu.unah.sigenuead.entity.GradoMilitar;
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
public class GradoMilitarServices {

    private GradoMilitarJpaController controllerGradoMilitar;

    public GradoMilitarJpaController getInstanceOfGradoMilitar() {
        return (controllerGradoMilitar == null) ? controllerGradoMilitar = new GradoMilitarJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerGradoMilitar;
    }

    public Par addGradoMilitar(String nombre, boolean cancel) {
        try {
            if (getInstanceOfGradoMilitar().findGradoMilitarByNombre(nombre) == null) {
                getInstanceOfGradoMilitar().create(new GradoMilitar(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(GradoMilitarServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editGradoMilitar(String nombre, String nombreNew, boolean cancel) {
        try {
            GradoMilitar p = getInstanceOfGradoMilitar().findGradoMilitarByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getGradoMilitarNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfGradoMilitar().findGradoMilitarByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setGradoMilitarNombre(nombreNew);
            p.setGradoMilitarCancelado(cancel);
            getInstanceOfGradoMilitar().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(GradoMilitarServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarGradoMilitar(String nombre) {
//        try {
//            GradoMilitar p = getInstanceOfGradoMilitar().findGradoMilitarByNombre(nombre);
//            p.setGradoMilitarCancelado(!p.getGradoMilitarCancelado());
//            getInstanceOfGradoMilitar().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(GradoMilitarServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteGradoMilitar(String nombre) {
//        try {
//            GradoMilitar p = getInstanceOfGradoMilitar().findGradoMilitarByNombre(nombre);
//            getInstanceOfGradoMilitar().destroy(p.getGradoMilitarId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(GradoMilitarServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<GradoMilitar> findAllGradoMilitar() {
        return getInstanceOfGradoMilitar().findGradoMilitarEntities();
    }
    public List<String> findGradoMilitarAvailable() {
        return getInstanceOfGradoMilitar().findGradoMilitarAvailable();
    }

}
