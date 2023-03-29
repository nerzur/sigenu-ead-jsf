/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.ColorPielJpaController;
import cu.edu.unah.sigenuead.entity.ColorPiel;
import cu.edu.unah.sigenuead.entity.Par;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudia
 */
public class ColorPielServices {

    private ColorPielJpaController controllerColorPiel;

    public ColorPielJpaController getInstanceOfColorPiel() {
        return (controllerColorPiel == null) ? controllerColorPiel = new ColorPielJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerColorPiel;
    }

    public Par AddColorPiel(String nombre, boolean cancel) {
        try {
            if (getInstanceOfColorPiel().findColorPielByNombre(nombre) == null) {
                getInstanceOfColorPiel().create(new ColorPiel(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(ColorPielServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par EditColorPiel(String nombre, String nombreNew, boolean cancel) {
        try {
            ColorPiel p = getInstanceOfColorPiel().findColorPielByNombre(nombre);
            if (p != null) {
                if (nombre.equals(nombreNew) || getInstanceOfColorPiel().findColorPielByNombre(nombreNew) == null) {
                    p.setColorPielNombre(nombreNew);
                    p.setColorPielCancelado(cancel);
                    getInstanceOfColorPiel().edit(p);
                    return new Par(1, texts.getSatisfactorio());
                } else {
                    return new Par(2, texts.getEditarExistente());
                }
            } else {
                return new Par(2, texts.getColorPielNull());
            }
        } catch (Exception ex) {
            Logger.getLogger(ColorPielServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par CancelColorPiel(String nombre) {
//        try {
//            ColorPiel p = getInstanceOfColorPiel().findColorPielByNombre(nombre);
//            p.setColorPielCancelado(!p.getColorPielCancelado());
//            getInstanceOfColorPiel().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(ColorPielServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par DeleteColorPiel(String nombre) {
//        try {
//            ColorPiel p = getInstanceOfColorPiel().findColorPielByNombre(nombre);
//            getInstanceOfColorPiel().destroy(p.getColorPielId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(ColorPielServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
    
    public List<ColorPiel> findAllColorPiel() {
        return getInstanceOfColorPiel().findColorPielEntities();
    }

    public List<String> findColorPielAvailable() {
        return getInstanceOfColorPiel().findColorPielAvailable();
    }
}
