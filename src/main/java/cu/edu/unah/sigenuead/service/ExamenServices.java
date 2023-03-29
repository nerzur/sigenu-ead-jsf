/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.ExamenJpaController;
import cu.edu.unah.sigenuead.entity.Examen;
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
public class ExamenServices {

    private ExamenJpaController controllerExamen;

    public ExamenJpaController getInstanceOfExamen() {
        return (controllerExamen == null) ? controllerExamen = new ExamenJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerExamen;
    }

    public Par addExamen(String nombre, boolean cancel) {
        try {
            if (getInstanceOfExamen().findExamenByNombre(nombre) == null) {
                getInstanceOfExamen().create(new Examen(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(ExamenServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getSatisfactorio());
        }
    }

    public Par editExamen(String nombre, String nombreNew, boolean cancel) {
        try {
            Examen p = getInstanceOfExamen().findExamenByNombre(nombre);
            if (p==null) {
                return new Par(2,texts.getExamenNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfExamen().findExamenByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setExamenTipo(nombreNew);
            p.setExamenCancelado(cancel);
            getInstanceOfExamen().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(ExamenServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getSatisfactorio());
        }
    }

//    public Par cancelarExamen(String nombre) {
//        try {
//            Examen p = getInstanceOfExamen().findExamenByNombre(nombre);
//            p.setExamenCancelado(!p.getExamenCancelado());
//            getInstanceOfExamen().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(ExamenServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getSatisfactorio());
//        }
//    }
//
//    public Par deleteExamen(String nombre) {
//        try {
//            Examen p = getInstanceOfExamen().findExamenByNombre(nombre);
//            getInstanceOfExamen().destroy(p.getExamenId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(ExamenServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getSatisfactorio());
//        }
//    }

    public List<Examen> findAllExamen() {
        return getInstanceOfExamen().findExamenEntities();
    }
    public List<String> findExamenAvailable() {
        return getInstanceOfExamen().findExamenAvailable();
    }
}
