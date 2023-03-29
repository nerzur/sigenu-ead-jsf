/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.OrganizacionPopularJpaController;
import cu.edu.unah.sigenuead.entity.OrganizacionPopular;
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
public class OrganizacionPopularServices {

    private OrganizacionPopularJpaController controllerOrganizacionPopular;

    public OrganizacionPopularJpaController getInstanceOfOrganizacionPopular() {
        return (controllerOrganizacionPopular == null) ? controllerOrganizacionPopular = new OrganizacionPopularJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerOrganizacionPopular;
    }

    public Par addOrganizacionPopular(String nombre, boolean cancel) {
        try {
            if (getInstanceOfOrganizacionPopular().findOrganizacionPopularByNombre(nombre) == null) {
                getInstanceOfOrganizacionPopular().create(new OrganizacionPopular(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
               return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(OrganizacionPopularServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editOrganizacionPopular(String nombre, String nombreNew, boolean cancel) {
        try {
            OrganizacionPopular p = getInstanceOfOrganizacionPopular().findOrganizacionPopularByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getOrganizacionPopularNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfOrganizacionPopular().findOrganizacionPopularByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setOrganizacionPopularNombre(nombreNew);
            p.setOrganizacionPopularCancelado(cancel);
            getInstanceOfOrganizacionPopular().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(OrganizacionPopularServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarOrganizacionPopular(String nombre) {
//        try {
//            OrganizacionPopular p = getInstanceOfOrganizacionPopular().findOrganizacionPopularByNombre(nombre);
//            p.setOrganizacionPopularCancelado(true);
//            getInstanceOfOrganizacionPopular().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(OrganizacionPopularServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteOrganizacionPopular(String nombre) {
//        try {
//            OrganizacionPopular p = getInstanceOfOrganizacionPopular().findOrganizacionPopularByNombre(nombre);
//            getInstanceOfOrganizacionPopular().destroy(p.getOrganizacionPopularId());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(OrganizacionPopularServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<OrganizacionPopular> findAllOrganizacionPopular() {
        return getInstanceOfOrganizacionPopular().findOrganizacionPopularEntities();
    }
    public List<String> findOrganizacionPopularAvailable() {
        return getInstanceOfOrganizacionPopular().findOrganizacionPopularAvailable();
    }
}
