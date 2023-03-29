/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.FuenteIngresoJpaController;
import cu.edu.unah.sigenuead.entity.FuenteIngreso;
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
public class FuenteIngresoServices {

    private FuenteIngresoJpaController controllerFuenteIngreso;

    public FuenteIngresoJpaController getInstanceOfFuenteIngreso() {
        return (controllerFuenteIngreso == null) ? controllerFuenteIngreso = new FuenteIngresoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFuenteIngreso;
    }

    public Par addFuenteIngreso(String nombre, boolean cancel) {
        try {
            if (getInstanceOfFuenteIngreso().findFuenteIngresoByNombre(nombre) == null) {
                getInstanceOfFuenteIngreso().create(new FuenteIngreso(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(FuenteIngresoServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editFuenteIngreso(String nombre, String nombreNew, boolean cancel) {
        try {
            FuenteIngreso p = getInstanceOfFuenteIngreso().findFuenteIngresoByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getFuenteIngresoNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfFuenteIngreso().findFuenteIngresoByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setFuenteIngresoNombre(nombreNew);
            p.setFuenteIngresoCancelado(cancel);
            getInstanceOfFuenteIngreso().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(FuenteIngresoServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarFuenteIngreso(String nombre) {
//        try {
//            FuenteIngreso p = getInstanceOfFuenteIngreso().findFuenteIngresoByNombre(nombre);
//            p.setFuenteIngresoCancelado(!p.getFuenteIngresoCancelado());
//            getInstanceOfFuenteIngreso().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(FuenteIngresoServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteFuenteIngreso(String nombre) {
//        try {
//            FuenteIngreso p = getInstanceOfFuenteIngreso().findFuenteIngresoByNombre(nombre);
//            getInstanceOfFuenteIngreso().destroy(p.getFuenteIngresoId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(FuenteIngresoServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<FuenteIngreso> findAllFuenteIngreso() {
        return getInstanceOfFuenteIngreso().findFuenteIngresoEntities();
    }
    public List<String> findFuenteIngresoAvailable() {
        return getInstanceOfFuenteIngreso().findFuenteIngresoAvailable();
    }
}
