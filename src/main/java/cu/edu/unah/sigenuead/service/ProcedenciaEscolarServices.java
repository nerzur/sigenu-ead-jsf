/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.ProcedenciaEscolarJpaController;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.ProcedenciaEscolar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudy
 */
public class ProcedenciaEscolarServices {

    private ProcedenciaEscolarJpaController controllerProcedenciaEscolar;

    public ProcedenciaEscolarJpaController getInstanceOfProcedenciaEscolar() {
        return (controllerProcedenciaEscolar == null) ? controllerProcedenciaEscolar = new ProcedenciaEscolarJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerProcedenciaEscolar;
    }

    public Par addProcedenciaEscolar(String nombre, boolean cancel) {
        try {
            if (getInstanceOfProcedenciaEscolar().findProcedenciaEscolarByNombre(nombre) == null) {
                getInstanceOfProcedenciaEscolar().create(new ProcedenciaEscolar(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(ProcedenciaEscolarServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editProcedenciaEscolar(String nombre, String nombreNew, boolean cancel) {
        try {
            ProcedenciaEscolar p = getInstanceOfProcedenciaEscolar().findProcedenciaEscolarByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getProcedenciaEscolarNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfProcedenciaEscolar().findProcedenciaEscolarByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setProcedenciaEscolarNombre(nombreNew);
            p.setProcedenciaEscolarCancelado(cancel);
            getInstanceOfProcedenciaEscolar().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(ProcedenciaEscolarServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarProcedenciaEscolar(String nombre) {
//        try {
//            ProcedenciaEscolar p = getInstanceOfProcedenciaEscolar().findProcedenciaEscolarByNombre(nombre);
//            p.setProcedenciaEscolarCancelado(true);
//            getInstanceOfProcedenciaEscolar().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(ProcedenciaEscolarServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteProcedenciaEscolar(String nombre) {
//        try {
//            ProcedenciaEscolar p = getInstanceOfProcedenciaEscolar().findProcedenciaEscolarByNombre(nombre);
//            getInstanceOfProcedenciaEscolar().destroy(p.getProcedenciaEscolarId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(ProcedenciaEscolarServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<ProcedenciaEscolar> findAllProcedenciaEscolar() {
        return getInstanceOfProcedenciaEscolar().findProcedenciaEscolarEntities();
    }
    public List<String> findProcedenciaEscolarAvailable() {
        return getInstanceOfProcedenciaEscolar().findProcedenciaEscolarAvailable();
    }
}
