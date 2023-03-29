/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.SindicatoJpaController;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Sindicato;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudia
 */
public class SindicatoServices {

    private SindicatoJpaController controllerSindicato;

    public SindicatoJpaController getInstanceOfSindicato() {
        return (controllerSindicato == null) ? controllerSindicato = new SindicatoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerSindicato;
    }

    public Par AddSindicato(String nombre, boolean cancel) {
        try {
            if (getInstanceOfSindicato().findSindicatoByNombre(nombre) == null) {
                getInstanceOfSindicato().create(new Sindicato(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(SindicatoServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par EditSindicato(String nombre, String nombreNew, boolean cancel) {
        try {
            Sindicato p = getInstanceOfSindicato().findSindicatoByNombre(nombre);
            if (p == null) {
                return new Par(2, texts.getSindicatoNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfSindicato().findSindicatoByNombre(nombreNew) != null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setNombresindicato(nombreNew);
            p.setCanceladosindicato(cancel);
            getInstanceOfSindicato().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(SindicatoServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par CancelSindicato(String nombre) {
//        try {
//            Sindicato p = getInstanceOfSindicato().findSindicatoByNombre(nombre);
//            p.setCanceladosindicato(true);
//            getInstanceOfSindicato().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(SindicatoServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par DeleteSindicato(String nombre) {
//        try {
//            Sindicato p = getInstanceOfSindicato().findSindicatoByNombre(nombre);
//            getInstanceOfSindicato().destroy(p.getIdsindicato());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(SindicatoServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
    public List<Sindicato> findAllSindicato() {
        return getInstanceOfSindicato().findSindicatoEntities();
    }

    public List<String> findSindicatoAvailable() {
        return getInstanceOfSindicato().findSindicatoAvailable();
    }
}
