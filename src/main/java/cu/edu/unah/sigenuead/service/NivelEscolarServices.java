/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.NivelEscolarJpaController;
import cu.edu.unah.sigenuead.entity.NivelEscolar;
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
public class NivelEscolarServices {

    private NivelEscolarJpaController controllerNivelEscolar;

    public NivelEscolarJpaController getInstanceOfNivelEscolar() {
        return (controllerNivelEscolar == null) ? controllerNivelEscolar = new NivelEscolarJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerNivelEscolar;
    }

    public Par addNivelEscolar(String nombre, boolean cancel) {
        try {
            if (getInstanceOfNivelEscolar().findNivelEscolarByNombre(nombre) == null) {
                getInstanceOfNivelEscolar().create(new NivelEscolar(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(NivelEscolarServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editNivelEscolar(String nombre, String nombreNew, boolean cancel) {
        try {
            NivelEscolar p = getInstanceOfNivelEscolar().findNivelEscolarByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getNivelEscolarNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfNivelEscolar().findNivelEscolarByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setNivelEscolarNombre(nombreNew);
            p.setNivelEscolarCancelado(cancel);
            getInstanceOfNivelEscolar().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(NivelEscolarServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarNivelEscolar(String nombre) {
//        try {
//            NivelEscolar p = getInstanceOfNivelEscolar().findNivelEscolarByNombre(nombre);
//            p.setNivelEscolarCancelado(!p.getNivelEscolarCancelado());
//            getInstanceOfNivelEscolar().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(NivelEscolarServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteNivelEscolar(String nombre) {
//        try {
//            NivelEscolar p = getInstanceOfNivelEscolar().findNivelEscolarByNombre(nombre);
//            getInstanceOfNivelEscolar().destroy(p.getNivelEscolarId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(NivelEscolarServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<NivelEscolar> findAllNivelEscolar() {
        return getInstanceOfNivelEscolar().findNivelEscolarEntities();
    }
    public List<String> findNivelEscolarAvailable() {
        return getInstanceOfNivelEscolar().findNivelEscolarAvailable();
    }
}
