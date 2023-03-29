/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.OcupacionJpaController;
import cu.edu.unah.sigenuead.entity.Ocupacion;
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
public class OcupacionServices {

    private OcupacionJpaController controllerOcupacion;

    public OcupacionJpaController getInstanceOfOcupacion() {
        return (controllerOcupacion == null) ? controllerOcupacion = new OcupacionJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerOcupacion;
    }

    public Par addOcupacion(String nombre, boolean cancel) {
        try {
            if (getInstanceOfOcupacion().findOcupacionByNombre(nombre) == null) {
                getInstanceOfOcupacion().create(new Ocupacion(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(OcupacionServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editOcupacion(String nombre, String nombreNew, boolean cancel) {
        try {
            Ocupacion p = getInstanceOfOcupacion().findOcupacionByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getOcupacionNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfOcupacion().findOcupacionByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setOcupacionNombre(nombreNew);
            p.setOcupacionCancelado(cancel);
            getInstanceOfOcupacion().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(OcupacionServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarOcupacion(String nombre) {
//        try {
//            Ocupacion p = getInstanceOfOcupacion().findOcupacionByNombre(nombre);
//            p.setOcupacionCancelado(!p.getOcupacionCancelado());
//            getInstanceOfOcupacion().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(OcupacionServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteOcupacion(String nombre) {
//        try {
//            Ocupacion p = getInstanceOfOcupacion().findOcupacionByNombre(nombre);
//            getInstanceOfOcupacion().destroy(p.getOcupacionId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(OcupacionServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<Ocupacion> findAllOcupacion() {
        return getInstanceOfOcupacion().findOcupacionEntities();
    }

    public List<String> findOcupacionAvailable() {
        return getInstanceOfOcupacion().findOcupacionAvailable();
    }
}
