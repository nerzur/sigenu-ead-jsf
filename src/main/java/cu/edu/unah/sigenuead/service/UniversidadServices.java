/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.MunicipioJpaController;
import cu.edu.unah.sigenuead.controller.PaisJpaController;
import cu.edu.unah.sigenuead.controller.ProvinciaJpaController;
import cu.edu.unah.sigenuead.controller.UniversidadJpaController;
import cu.edu.unah.sigenuead.entity.Municipio;
import cu.edu.unah.sigenuead.entity.Pais;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Provincia;
import cu.edu.unah.sigenuead.entity.Universidad;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudy
 */
public class UniversidadServices {

    private UniversidadJpaController controllerUniversidad;

    public UniversidadJpaController getInstanceOfUniversidad() {
        return (controllerUniversidad == null) ? controllerUniversidad = new UniversidadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerUniversidad;
    }
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

    public Par addUniversidad(String codigouniversidad, String nombreuniversidad, String inicialesuniversidad, String actividaduniversidad, String telefonouniversidad, String reglamentouniversidad, String faxuniversidad, String nombrerectoruniversidad, String nombresecgraluniversidad, String direccionuniversidad, boolean canceladouniversidad, String municipio, String provincia) {
        try {
            if (getInstanceOfUniversidad().findUniversidad(codigouniversidad) == null) {
                if (getInstanceOfUniversidad().findUniversidadByNombre(nombreuniversidad) == null) {
                    Pais c = getInstanceOfPais().findPaisByNombre("Cuba");
                    if (c == null) {
                        return new Par(2, texts.getPaisNull());
                    }
                    Provincia prov = getInstanceOfProvincia().findProvinciaByNombreAndPaisAvailable(provincia, c.getIdpais());
                    if (prov == null) {
                        return new Par(2, texts.getProvinciaNull());
                    }
                    Municipio p = getInstanceOfMunicipio().findMunicipioByNombreAndPaisAvailable(municipio, prov.getIdprovincia(), c.getIdpais());
                    if (p == null) {
                        return new Par(2, texts.getMunicipioNull());
                    }
                    if (p.getUniversidadList().size() > 0) {
                        return new Par(2, texts.getUnivMunic());
                    } else {
                        getInstanceOfUniversidad().create(new Universidad(codigouniversidad, nombreuniversidad, inicialesuniversidad, actividaduniversidad, telefonouniversidad, reglamentouniversidad, faxuniversidad, nombrerectoruniversidad, nombresecgraluniversidad, direccionuniversidad, canceladouniversidad, p));
                        return new Par(1, texts.getSatisfactorio());
                    }
                } else {
                    return new Par(3, texts.getInformacion());
                }
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(UniversidadServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editUniversidad(String codigouniversidad, String nombreuniversidad, String inicialesuniversidad, String actividaduniversidad, String telefonouniversidad, String reglamentouniversidad, String faxuniversidad, String nombrerectoruniversidad, String nombresecgraluniversidad, String direccionuniversidad, boolean canceladouniversidad, String municipio, String provincia) {
        try {
            Universidad p = getInstanceOfUniversidad().findUniversidad(codigouniversidad);
            if (p == null) {
                return new Par(2, texts.getUniversidadNull());
            }
            p.setActividaduniversidad(actividaduniversidad);
            p.setCanceladouniversidad(canceladouniversidad);
            p.setDireccionuniversidad(direccionuniversidad);
            p.setFaxuniversidad(faxuniversidad);
            p.setInicialesuniversidad(inicialesuniversidad);
            if (!p.getNombreuniversidad().equals(nombreuniversidad) && getInstanceOfUniversidad().findUniversidadByNombre(nombreuniversidad) != null) {
                return new Par(2, texts.getEditarExistente());
            }
            p.setNombreuniversidad(nombreuniversidad);
            Pais c = getInstanceOfPais().findPaisByNombre("Cuba");
            if (c == null) {
                return new Par(2, texts.getPaisNull());
            }
            Provincia prov = getInstanceOfProvincia().findProvinciaByNombreAndPaisAvailable(provincia, c.getIdpais());
            if (prov == null) {
                return new Par(2, texts.getProvinciaNull());
            }
            Municipio u = getInstanceOfMunicipio().findMunicipioByNombreAndPaisAvailable(municipio, prov.getIdprovincia(), c.getIdpais());
            if (u == null) {
                return new Par(2, texts.getMunicipioNull());
            }
            if (p.getMunicipioidmunicipio().getIdmunicipio()!=u.getIdmunicipio() && u.getUniversidadList().size() > 0) {
                return new Par(2, texts.getUnivMunic());
            } else {
                p.setMunicipioidmunicipio(u);
                p.setNombrerectoruniversidad(nombrerectoruniversidad);
                p.setNombresecgraluniversidad(nombresecgraluniversidad);
                p.setReglamentouniversidad(reglamentouniversidad);
                p.setTelefonouniversidad(telefonouniversidad);
                getInstanceOfUniversidad().edit(p);
                return new Par(1, texts.getSatisfactorio());
            }
        } catch (Exception ex) {
            Logger.getLogger(UniversidadServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }
//
//    public Par cancelarUniversidad(String codigo) {
//        try {
//            Universidad p = getInstanceOfUniversidad().findUniversidad(codigo);
//            p.setCanceladouniversidad(true);
//            getInstanceOfUniversidad().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(UniversidadServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteUniversidad(String codigo) {
//        try {
//            getInstanceOfUniversidad().destroy(codigo);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(UniversidadServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<Universidad> findAllUniversidad() {
        return getInstanceOfUniversidad().findUniversidadList();
    }

    public String findUniversidadAvailable() {
        return getInstanceOfUniversidad().findUniversidadAvailable().getNombreuniversidad();
    }
}
