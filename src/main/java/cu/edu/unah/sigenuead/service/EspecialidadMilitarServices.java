/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.EspecialidadMilitarJpaController;
import cu.edu.unah.sigenuead.entity.EspecialidadMilitar;
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
public class EspecialidadMilitarServices {

    private EspecialidadMilitarJpaController controllerEspecialidadMilitar;

    public EspecialidadMilitarJpaController getInstanceOfEspecialidadMilitar() {
        return (controllerEspecialidadMilitar == null) ? controllerEspecialidadMilitar = new EspecialidadMilitarJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEspecialidadMilitar;
    }

    public Par addEspecialidadMilitar(String nombre, boolean cancel) {
        try {
            if (getInstanceOfEspecialidadMilitar().findEspecialidadMilitarByNombre(nombre) == null) {
                getInstanceOfEspecialidadMilitar().create(new EspecialidadMilitar(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(EspecialidadMilitarServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editEspecialidadMilitar(String nombre, String nombreNew, boolean cancel) {
        try {
            EspecialidadMilitar p = getInstanceOfEspecialidadMilitar().findEspecialidadMilitarByNombre(nombre);
            if (p != null) {
                if (nombre.equals(nombreNew) || getInstanceOfEspecialidadMilitar().findEspecialidadMilitarByNombre(nombreNew) == null) {
                    p.setEspecialidadMilitarNombre(nombreNew);
                    p.setEspecialidadMilitarCancelado(cancel);
                    getInstanceOfEspecialidadMilitar().edit(p);
                    return new Par(1, texts.getSatisfactorio());
                } else {
                    return new Par(2, texts.getEditarExistente());
                }
            } else {
                return new Par(2, texts.getEspeciladadMilitarNull());
            }
        } catch (Exception ex) {
            Logger.getLogger(EspecialidadMilitarServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarEspecialidadMilitar(String nombre) {
//        try {
//            EspecialidadMilitar p = getInstanceOfEspecialidadMilitar().findEspecialidadMilitarByNombre(nombre);
//            p.setEspecialidadMilitarCancelado(!p.getEspecialidadMilitarCancelado());
//            getInstanceOfEspecialidadMilitar().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(EspecialidadMilitarServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deletePais(String nombre) {
//        try {
//            EspecialidadMilitar p = getInstanceOfEspecialidadMilitar().findEspecialidadMilitarByNombre(nombre);
//            getInstanceOfEspecialidadMilitar().destroy(p.getEspecialidadMilitarId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(EspecialidadMilitarServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
    public List<EspecialidadMilitar> findAllEspecialidadMilitar() {
        return getInstanceOfEspecialidadMilitar().findEspecialidadMilitarEntities();
    }

    public List<String> findEspecialidadMilitarAvailable() {
        return getInstanceOfEspecialidadMilitar().findEspecialidadMilitarAvailable();
    }
}
