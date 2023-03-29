/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.OngJpaController;
import cu.edu.unah.sigenuead.entity.Ong;
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
public class OngServices {

    private OngJpaController controllerOng;

    public OngJpaController getInstanceOfOng() {
        return (controllerOng == null) ? controllerOng = new OngJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerOng;
    }

    public Par addOng(String nombre, boolean cancel) {
        try {
            if (getInstanceOfOng().findOngByNombre(nombre) == null) {
                getInstanceOfOng().create(new Ong(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(OngServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editOng(String nombre, String nombreNew, boolean cancel) {
        try {
            Ong p = getInstanceOfOng().findOngByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getOngNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfOng().findOngByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setOngNombre(nombreNew);
            p.setOngCancelado(cancel);
            getInstanceOfOng().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(OngServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarOng(String nombre) {
//        try {
//            Ong p = getInstanceOfOng().findOngByNombre(nombre);
//            p.setOngCancelado(!p.getOngCancelado());
//            getInstanceOfOng().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(OngServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteOng(String nombre) {
//        try {
//            Ong p = getInstanceOfOng().findOngByNombre(nombre);
//            getInstanceOfOng().destroy(p.getOngId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(OngServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<Ong> findAllOng() {
        return getInstanceOfOng().findOngEntities();
    }
    public List<String> findOngAvailable() {
        return getInstanceOfOng().findOngAvailable();
    }
}
