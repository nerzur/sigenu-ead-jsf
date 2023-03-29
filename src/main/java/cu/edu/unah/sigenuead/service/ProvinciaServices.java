/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.PaisJpaController;
import cu.edu.unah.sigenuead.controller.ProvinciaJpaController;
import cu.edu.unah.sigenuead.entity.Pais;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Provincia;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudy
 */
public class ProvinciaServices {

    private ProvinciaJpaController controllerProvincia;

    public ProvinciaJpaController getInstanceOfProvincia() {
        return (controllerProvincia == null) ? controllerProvincia = new ProvinciaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerProvincia;
    }
    private PaisJpaController controllerPais;

    public PaisJpaController getInstanceOfPais() {
        return (controllerPais == null) ? controllerPais = new PaisJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerPais;
    }

    public Par addProvincia(String provincia, String pais, boolean cancel) {
        try {
            Pais c = getInstanceOfPais().findPaisByNombre(pais);
            if (getInstanceOfProvincia().findProvinciaByNombreAndPais(provincia, c.getIdpais()) == null) {
                getInstanceOfProvincia().create(new Provincia(provincia, cancel, c));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(ProvinciaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editProvincia(String provincia, String provinciaNew, boolean cancel, String pais, String paisNew) {
        try {
            Pais c = getInstanceOfPais().findPaisByNombreAvailable(pais);
            if (c == null) {
                return new Par(2, texts.getPaisNull());
            }
            Provincia p = getInstanceOfProvincia().findProvinciaByNombreAndPaisAvailable(provincia, c.getIdpais());
            if (p == null) {
                return new Par(2, texts.getProvinciaNull());
            }
            Pais a = getInstanceOfPais().findPaisByNombre(paisNew);
            if (a == null) {
                return new Par(2, texts.getPaisNull());
            }
            if (!provincia.equals(provinciaNew) && getInstanceOfProvincia().findProvinciaByNombreAndPaisAvailable(provinciaNew, a.getIdpais()) != null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setNombreprovincia(provinciaNew);
            p.setCanceladoprovincia(cancel);
            p.setPaisidpais(a);
            getInstanceOfProvincia().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(ProvinciaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarProvincia(String provincia, String pais) {
//        try {
//            Pais c = getInstanceOfPais().findPaisByNombre(pais);
//            Provincia p = getInstanceOfProvincia().findProvinciaByNombreAndPais(provincia, c.getIdpais());
//            p.setCanceladoprovincia(true);
//            getInstanceOfProvincia().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(ProvinciaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteProvincia(String provincia, String pais) {
//        try {
//            Pais c = getInstanceOfPais().findPaisByNombre(pais);
//            Provincia p = getInstanceOfProvincia().findProvinciaByNombreAndPais(provincia, c.getIdpais());
//            getInstanceOfProvincia().destroy(p.getIdprovincia());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(ProvinciaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<Provincia> findAllProvincia() {
        return getInstanceOfProvincia().findProvinciaList();
    }

    public List<String> findProvinciaAvailable(String pais) {
        Pais c = getInstanceOfPais().findPaisByNombre(pais);
        if (c != null) {
            return getInstanceOfProvincia().findProvinciaAvailable(c.getIdpais());
        } else {
            return new ArrayList<String>();
        }
    }
}
