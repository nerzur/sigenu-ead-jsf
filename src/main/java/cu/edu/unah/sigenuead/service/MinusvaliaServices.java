/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.MinusvaliaJpaController;
import cu.edu.unah.sigenuead.entity.Minusvalia;
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
public class MinusvaliaServices {

    private MinusvaliaJpaController controllerMinusvalia;

    public MinusvaliaJpaController getInstanceOfMinusvalia() {
        return (controllerMinusvalia == null) ? controllerMinusvalia = new MinusvaliaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerMinusvalia;
    }

    public Par addMinusvalia(String nombre, boolean cancel) {
        try {
            if (getInstanceOfMinusvalia().findMinusvaliaByNombre(nombre) == null) {
                getInstanceOfMinusvalia().create(new Minusvalia(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(MinusvaliaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editMinusvalia(String nombre, String nombreNew, boolean cancel) {
        try {
            Minusvalia p = getInstanceOfMinusvalia().findMinusvaliaByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getMinusvaliaNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfMinusvalia().findMinusvaliaByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setMinusvaliaNombre(nombreNew);
            p.setMinusvaliaCancelado(cancel);
            getInstanceOfMinusvalia().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(MinusvaliaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarMinusvalia(String nombre) {
//        try {
//            Minusvalia p = getInstanceOfMinusvalia().findMinusvaliaByNombre(nombre);
//            p.setMinusvaliaCancelado(!p.getMinusvaliaCancelado());
//            getInstanceOfMinusvalia().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(MinusvaliaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteMinusvalia(String nombre) {
//        try {
//            Minusvalia p = getInstanceOfMinusvalia().findMinusvaliaByNombre(nombre);
//            getInstanceOfMinusvalia().destroy(p.getMinusvaliaId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(MinusvaliaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<Minusvalia> findAllMinusvalia() {
        return getInstanceOfMinusvalia().findMinusvaliaEntities();
    }

    public List<String> findMinusvaliaAvailable() {
        return getInstanceOfMinusvalia().findMinusvaliaAvailable();
    }
}
