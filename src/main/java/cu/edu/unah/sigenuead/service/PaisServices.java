/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.PaisJpaController;
import cu.edu.unah.sigenuead.entity.Pais;
import cu.edu.unah.sigenuead.entity.Par;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudia
 */
public class PaisServices {

    private PaisJpaController controllerPais;

    public PaisJpaController getInstanceOfPais() {
        return (controllerPais == null) ? controllerPais = new PaisJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerPais;
    }

    public Par addPais(String nombre, boolean cancel) {
        try {
            if (getInstanceOfPais().findPaisByNombre(nombre) == null) {
                getInstanceOfPais().create(new Pais(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(PaisServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editPais(String nombre, String nombreNew, boolean cancel) {
        try {
            Pais p = getInstanceOfPais().findPaisByNombre(nombre);
            if (p == null) {
                return new Par(2, texts.getPaisNull());
            }
            if (nombre.equals("Cuba")) {
                return new Par(2, texts.getNoedit());
            } else {
                if (!nombre.equals(nombreNew) && getInstanceOfPais().findPaisByNombre(nombreNew) != null) {
                    return new Par(2, texts.getEditarExistente());
                }
                p.setNombrepais(nombreNew);
                p.setCanceladopais(cancel);
                getInstanceOfPais().edit(p);
                return new Par(1, texts.getSatisfactorio());
            }
        } catch (Exception ex) {
            Logger.getLogger(PaisServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarPais(String nombre) {
//        try {
//            Pais p = getInstanceOfPais().findPaisByNombre(nombre);
//            p.setCanceladopais(true);
//            getInstanceOfPais().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(PaisServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deletePais(String nombre) {
//        try {
//            Pais p = getInstanceOfPais().findPaisByNombre(nombre);
//            getInstanceOfPais().destroy(p.getIdpais());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(PaisServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<Pais> findAllPais() {
        return getInstanceOfPais().findPaisList();
    }

    public List<String> findPaisAvailable() {
        return getInstanceOfPais().findPaisAvailable();
    }
}
