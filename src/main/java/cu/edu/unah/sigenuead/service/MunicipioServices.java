/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.MunicipioJpaController;
import cu.edu.unah.sigenuead.controller.PaisJpaController;
import cu.edu.unah.sigenuead.controller.ProvinciaJpaController;
import cu.edu.unah.sigenuead.entity.Municipio;
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
public class MunicipioServices {

    private MunicipioJpaController controllerMunicipio;

    public MunicipioJpaController getInstanceOfMunicipio() {
        return (controllerMunicipio == null) ? controllerMunicipio = new MunicipioJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerMunicipio;
    }
    private ProvinciaJpaController controllerProvincia;

    public ProvinciaJpaController getInstanceOfProvincia() {
        return (controllerProvincia == null) ? controllerProvincia = new ProvinciaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerProvincia;
    }
    private PaisJpaController controllerPais;

    public PaisJpaController getInstanceOfPais() {
        return (controllerPais == null) ? controllerPais = new PaisJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerPais;
    }

    public Par addMunicipio(String municipio, String provincia, String pais, boolean cancel) {
        try {
            Pais c = getInstanceOfPais().findPaisByNombreAvailable(pais);
            if (c == null) {
                return new Par(2, texts.getPaisNull());
            }
            Provincia prov = getInstanceOfProvincia().findProvinciaByNombreAndPaisAvailable(provincia, c.getIdpais());
            if (prov == null) {
                return new Par(2, texts.getProvinciaNull());
            }
            if (getInstanceOfMunicipio().findMunicipioByNombreAndPais(municipio, prov.getIdprovincia(), c.getIdpais()) == null) {
                getInstanceOfMunicipio().create(new Municipio(municipio, cancel, prov));
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(MunicipioServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editMunicipio(String municipio, String municipioNew, String provincia, String provinciaNew, String pais, String paisNew, boolean cancel) {
        try {
            Pais c = getInstanceOfPais().findPaisByNombreAvailable(pais);
            if (c == null) {
                return new Par(2, texts.getPaisNull());
            }
            Provincia prov = getInstanceOfProvincia().findProvinciaByNombreAndPaisAvailable(provincia, c.getIdpais());
            if (prov == null) {
                return new Par(2, texts.getProvinciaNull());
            }
            Municipio p = getInstanceOfMunicipio().findMunicipioByNombreAndPais(municipio, prov.getIdprovincia(), c.getIdpais());
            if (p == null) {
                return new Par(2, texts.getMunicipioNull());
            }
            Pais cn = getInstanceOfPais().findPaisByNombreAvailable(paisNew);
            if (c == null) {
                return new Par(2, texts.getPaisNull());
            }
            Provincia provn = getInstanceOfProvincia().findProvinciaByNombreAndPaisAvailable(provinciaNew, cn.getIdpais());
            if (prov == null) {
                return new Par(2, texts.getProvinciaNull());
            }
            Municipio pn = getInstanceOfMunicipio().findMunicipioByNombreAndPais(municipioNew, provn.getIdprovincia(), cn.getIdpais());
            if (municipio.equals(municipioNew) || pn == null) {
                p.setCanceladomunicipio(cancel);
                p.setNombremunicipio(municipioNew);
                p.setProvinciaidprovincia(provn);
                getInstanceOfMunicipio().edit(p);
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(2, texts.getEditarExistente());
            }
        } catch (Exception ex) {
            Logger.getLogger(MunicipioServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarMunicipio(String municipio, String provincia, String pais) {
//        try {
//            Pais c = getInstanceOfPais().findPaisByNombre(pais);
//            Provincia prov = getInstanceOfProvincia().findProvinciaByNombreAndPais(provincia, c.getIdpais());
//            Municipio p = getInstanceOfMunicipio().findMunicipioByNombreAndPais(municipio, prov.getIdprovincia(), c.getIdpais());
//            p.setCanceladomunicipio(!p.getCanceladomunicipio());
//            getInstanceOfMunicipio().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(MunicipioServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteMunicipio(String municipio, String provincia, String pais) {
//        try {
//            Pais c = getInstanceOfPais().findPaisByNombre(pais);
//            Provincia prov = getInstanceOfProvincia().findProvinciaByNombreAndPais(provincia, c.getIdpais());
//            Municipio p = getInstanceOfMunicipio().findMunicipioByNombreAndPais(municipio, prov.getIdprovincia(), c.getIdpais());
//            getInstanceOfMunicipio().destroy(p.getIdmunicipio());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(MunicipioServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
    public List<Municipio> findAllMunicipio() {
        return getInstanceOfMunicipio().findMunicipioList();
    }

    public List<String> findMunicipioAvailable(String provincia, String pais) {
        Pais c = getInstanceOfPais().findPaisByNombre(pais);
        if (c==null) {
            return new ArrayList<String>();
        }
        Provincia prov = getInstanceOfProvincia().findProvinciaByNombreAndPais(provincia, c.getIdpais());
        if (prov==null) {
            return new ArrayList<String>();
        }
        return getInstanceOfMunicipio().findMunicipioAvailable(prov.getIdprovincia(), c.getIdpais());
    }
}
