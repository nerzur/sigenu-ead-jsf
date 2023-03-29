/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.HuerfanoJpaController;
import cu.edu.unah.sigenuead.entity.Huerfano;
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
public class HuerfanoServices {

    private HuerfanoJpaController controllerHuerfano;

    public HuerfanoJpaController getInstanceOfHuerfano() {
        return (controllerHuerfano == null) ? controllerHuerfano = new HuerfanoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerHuerfano;
    }

    public Par addHuerfano(String nombre, boolean cancel) {
        try {
            if (getInstanceOfHuerfano().findHuerfanoByNombre(nombre) == null) {
                getInstanceOfHuerfano().create(new Huerfano(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(HuerfanoServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editHuerfano(String nombre, String nombreNew, boolean cancel) {
        try {
            Huerfano p = getInstanceOfHuerfano().findHuerfanoByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getHuerfanoNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfHuerfano().findHuerfanoByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setHuerfanoNombre(nombreNew);
            p.setHuerfanoCancelado(cancel);
            getInstanceOfHuerfano().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(HuerfanoServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarHuerfano(String nombre) {
//        try {
//            Huerfano p = getInstanceOfHuerfano().findHuerfanoByNombre(nombre);
//            p.setHuerfanoCancelado(!p.getHuerfanoCancelado());
//            getInstanceOfHuerfano().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(HuerfanoServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteHuerfano(String nombre) {
//        try {
//            Huerfano p = getInstanceOfHuerfano().findHuerfanoByNombre(nombre);
//            getInstanceOfHuerfano().destroy(p.getHuerfanoId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(HuerfanoServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<Huerfano> findAllHuerfano() {
        return getInstanceOfHuerfano().findHuerfanoEntities();
    }
    public List<String> findHuerfanoAvailable() {
        return getInstanceOfHuerfano().findHuerfanoAvailable();
    }
}
