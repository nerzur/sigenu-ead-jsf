/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.EspecialidadJpaController;
import cu.edu.unah.sigenuead.entity.Especialidad;
import cu.edu.unah.sigenuead.entity.Par;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudia
 */
public class EspecialidadServices {

    private EspecialidadJpaController controllerEspecialidad;

    public EspecialidadJpaController getInstanceOfEspecialidad() {
        return (controllerEspecialidad == null) ? controllerEspecialidad = new EspecialidadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEspecialidad;
    }

    public Par addEspecialidad(String nombre, boolean cancel) {
        try {
            if (getInstanceOfEspecialidad().findEspecialidadByNombre(nombre) == null) {
                getInstanceOfEspecialidad().create(new Especialidad(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(EspecialidadServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editEspecialidad(String nombre, String nombreNew, boolean cancel) {
        try {
            Especialidad p = getInstanceOfEspecialidad().findEspecialidadByNombre(nombre);
            if (p != null) {
                if (nombre.equals(nombreNew) || getInstanceOfEspecialidad().findEspecialidadByNombre(nombreNew) == null) {
                    p.setNombreespecialidad(nombreNew);
                    p.setCanceladoespecialidad(cancel);
                    getInstanceOfEspecialidad().edit(p);
                    return new Par(1, texts.getSatisfactorio());
                }
                else{
                    return new Par(2, texts.getEditarExistente());
                }
            }
            else{
                return new Par(2, texts.getEspecialidadNull());
            }
        } catch (Exception ex) {
            Logger.getLogger(EspecialidadServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarEspecialidad(String nombre) {
//        try {
//            Especialidad p = getInstanceOfEspecialidad().findEspecialidadByNombre(nombre);
//            p.setCanceladoespecialidad(!p.getCanceladoespecialidad());
//            getInstanceOfEspecialidad().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(EspecialidadServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteEspecialidad(String nombre) {
//        try {
//            Especialidad p = getInstanceOfEspecialidad().findEspecialidadByNombre(nombre);
//            getInstanceOfEspecialidad().destroy(p.getIdespecialidad());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(EspecialidadServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<Especialidad> findAllEspecialidad() {
        return getInstanceOfEspecialidad().findEspecialidadEntities();
    }

    public List<String> findEspecialidadAvailable() {
        return getInstanceOfEspecialidad().findEspecialidadAvailable();
    }

}
