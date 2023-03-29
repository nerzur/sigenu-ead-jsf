/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.SexoJpaController;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Sexo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudy
 */
public class SexoServices {

    private SexoJpaController controllerSexo;

    public SexoJpaController getInstanceOfSexo() {
        return (controllerSexo == null) ? controllerSexo = new SexoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerSexo;
    }

    public Par addSexo(String nombre, boolean cancel) {
        try {
            if (getInstanceOfSexo().findSexoByNombre(nombre) == null) {
                getInstanceOfSexo().create(new Sexo(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(SexoServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getSatisfactorio());
        }
    }

    public Par editSexo(String nombre, String nombreNew, boolean cancel) {
        try {
            if (nombre.equals("Femenino") || nombre.equals("Masculino")) {
                return new Par(2, texts.getNoedit());
            } else {
                Sexo p = getInstanceOfSexo().findSexoByNombre(nombre);
                if (p == null) {
                    return new Par(2, texts.getSexoNull());
                }
                if (!nombre.equals(nombreNew) && getInstanceOfSexo().findSexoByNombre(nombreNew) != null) {
                    return new Par(2, texts.getEditarExistente());
                }
                p.setSexoNombre(nombreNew);
                p.setSexoCancelado(cancel);
                getInstanceOfSexo().edit(p);
                return new Par(1, texts.getSatisfactorio());
            }
        } catch (Exception ex) {
            Logger.getLogger(SexoServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getSatisfactorio());
        }
    }

//    public Par cancelarSexo(String nombre) {
//        try {
//            Sexo p = getInstanceOfSexo().findSexoByNombre(nombre);
//            p.setSexoCancelado(true);
//            getInstanceOfSexo().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(SexoServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getSatisfactorio());
//        }
//    }
//
//    public Par deleteSexo(String nombre) {
//        try {
//            Sexo p = getInstanceOfSexo().findSexoByNombre(nombre);
//            getInstanceOfSexo().destroy(p.getSexoId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(SexoServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getSatisfactorio());
//        }
//    }
    public List<Sexo> findAllSexo() {
        return getInstanceOfSexo().findSexoEntities();
    }

    public List<String> findSexoAvailable() {
        return getInstanceOfSexo().findSexoAvailable();
    }
}
