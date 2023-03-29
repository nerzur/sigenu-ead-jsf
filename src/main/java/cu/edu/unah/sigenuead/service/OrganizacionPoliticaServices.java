/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.OrganizacionPoliticaJpaController;
import cu.edu.unah.sigenuead.entity.OrganizacionPolitica;
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
public class OrganizacionPoliticaServices {

    private OrganizacionPoliticaJpaController controllerOrganizacionPolitica;

    public OrganizacionPoliticaJpaController getInstanceOfOrganizacionPolitica() {
        return (controllerOrganizacionPolitica == null) ? controllerOrganizacionPolitica = new OrganizacionPoliticaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerOrganizacionPolitica;
    }

    public Par addOrganizacionPolitica(String nombre, boolean cancel) {
        try {
            if (getInstanceOfOrganizacionPolitica().findOrganizacionPoliticaByNombre(nombre) == null) {
                getInstanceOfOrganizacionPolitica().create(new OrganizacionPolitica(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(OrganizacionPoliticaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editOrganizacionPolitica(String nombre, String nombreNew, boolean cancel) {
        try {
            OrganizacionPolitica p = getInstanceOfOrganizacionPolitica().findOrganizacionPoliticaByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getOrganizacionPoliticaNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfOrganizacionPolitica().findOrganizacionPoliticaByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setOrganizacionPoliticaNombre(nombreNew);
            p.setOrganizacionPoliticaCancelado(cancel);
            getInstanceOfOrganizacionPolitica().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(OrganizacionPoliticaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarOrganizacionPolitica(String nombre) {
//        try {
//            OrganizacionPolitica p = getInstanceOfOrganizacionPolitica().findOrganizacionPoliticaByNombre(nombre);
//            p.setOrganizacionPoliticaCancelado(!p.getOrganizacionPoliticaCancelado());
//            getInstanceOfOrganizacionPolitica().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(OrganizacionPoliticaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteOrganizacionPolitica(String nombre) {
//        try {
//            OrganizacionPolitica p = getInstanceOfOrganizacionPolitica().findOrganizacionPoliticaByNombre(nombre);
//            getInstanceOfOrganizacionPolitica().destroy(p.getOrganizacionPoliticaId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(OrganizacionPoliticaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<OrganizacionPolitica> findAllOrganizacionPolitica() {
        return getInstanceOfOrganizacionPolitica().findOrganizacionPoliticaEntities();
    }
    public List<String> findOrganizacionPoliticaAvailable() {
        return getInstanceOfOrganizacionPolitica().findOrganizacionPoliticaAvailable();
    }
}
