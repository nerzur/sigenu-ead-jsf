/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.OrganismoJpaController;
import cu.edu.unah.sigenuead.entity.Organismo;
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
public class OrganismoServices {

    private OrganismoJpaController controllerOrganismo;

    public OrganismoJpaController getInstanceOfOrganismo() {
        return (controllerOrganismo == null) ? controllerOrganismo = new OrganismoJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerOrganismo;
    }

    public Par AddOrganismo(String nombre, boolean cancel) {
        try {
            if (getInstanceOfOrganismo().findOrganismoByNombre(nombre) == null) {
                getInstanceOfOrganismo().create(new Organismo(nombre, cancel));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(OrganismoServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par EditOrganismo(String nombre, String nombreNew, boolean cancel) {
        try {
            Organismo p = getInstanceOfOrganismo().findOrganismoByNombre(nombre);
            if (p==null) {
                return new Par(2, texts.getOrganismoNull());
            }
            if (!nombre.equals(nombreNew) && getInstanceOfOrganismo().findOrganismoByNombre(nombreNew)!=null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setNombreorganismo(nombreNew);
            p.setCanceladoorganismo(cancel);
            getInstanceOfOrganismo().edit(p);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(OrganismoServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par CancelOrganismo(String nombre) {
//        try {
//            Organismo p = getInstanceOfOrganismo().findOrganismoByNombre(nombre);
//            p.setCanceladoorganismo(!p.getCanceladoorganismo());
//            getInstanceOfOrganismo().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(OrganismoServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par DeleteOrganismo(String nombre) {
//        try {
//            Organismo p = getInstanceOfOrganismo().findOrganismoByNombre(nombre);
//            getInstanceOfOrganismo().destroy(p.getIdorganismo());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(OrganismoServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<Organismo> findAllOrganismo() {
        return getInstanceOfOrganismo().findOrganismoEntities();
    }
    public List<String> findOrganismoAvailable() {
        return getInstanceOfOrganismo().findOrganismoAvailable();
    }
}
