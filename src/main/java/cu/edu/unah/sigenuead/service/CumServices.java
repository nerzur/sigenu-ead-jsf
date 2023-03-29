/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.CarreraJpaController;
import cu.edu.unah.sigenuead.controller.CumJpaController;
import cu.edu.unah.sigenuead.controller.FacultadJpaController;
import cu.edu.unah.sigenuead.controller.MunicipioJpaController;
import cu.edu.unah.sigenuead.controller.PaisJpaController;
import cu.edu.unah.sigenuead.controller.ProvinciaJpaController;
import cu.edu.unah.sigenuead.controller.UniversidadJpaController;
import cu.edu.unah.sigenuead.controller.UsersJpaController;
import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.Cum;
import cu.edu.unah.sigenuead.entity.Facultad;
import cu.edu.unah.sigenuead.entity.Municipio;
import cu.edu.unah.sigenuead.entity.Pais;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Provincia;
import cu.edu.unah.sigenuead.entity.Universidad;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;
import cu.edu.unah.sigenuead.util.userLoging;

/**
 *
 * @author claudy
 */
public class CumServices {

    private UniversidadJpaController controllerUniversidad;

    public UniversidadJpaController getInstanceOfUniversidad() {
        return (controllerUniversidad == null) ? controllerUniversidad = new UniversidadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerUniversidad;
    }

    private CumJpaController controllerCum;

    public CumJpaController getInstanceOfCum() {
        return (controllerCum == null) ? controllerCum = new CumJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCum;
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

    private FacultadJpaController controllerFacultad;

    public FacultadJpaController getInstanceOfFacultad() {
        return (controllerFacultad == null) ? controllerFacultad = new FacultadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultad;
    }
    private CarreraJpaController controllerCarrera;

    public CarreraJpaController getInstanceOfCarrera() {
        return (controllerCarrera == null) ? controllerCarrera = new CarreraJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCarrera;
    }

    private UsersJpaController controllerUsers;

    public UsersJpaController getInstanceOfUsers() {
        return (controllerUsers == null) ? controllerUsers = new UsersJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerUsers;
    }

    public Par addCum(String codigo, String nombre, String iniciales, String actividad, boolean cancel, String telefono, String reglamento, String fax, String nombredirector, String nombresec, String direccion, String municipio, String provincia) {
        try {
            if (getInstanceOfCum().findCum(codigo) == null) {
                if (getInstanceOfCum().findCum(nombre) == null) {
                    Cum cum = new Cum(codigo);
                    cum.setActividadcum(actividad);
                    cum.setCumcancelado(cancel);
                    cum.setDireccioncum(direccion);
                    cum.setFaxcum(fax);
                    cum.setInicialescum(iniciales);
                    cum.setNombrecum(nombre);
                    cum.setNombredirectorcum(nombredirector);
                    cum.setNombresecgralcum(nombresec);
                    cum.setReglamentocum(reglamento);
                    cum.setTelefonocum(telefono);
                    Pais c = getInstanceOfPais().findPaisByNombre("Cuba");
                    if (c == null) {
                        return new Par(2, texts.getPaisNull());
                    }
                    Provincia prov = getInstanceOfProvincia().findProvinciaByNombreAndPais(provincia, c.getIdpais());
                    if (prov == null) {
                        return new Par(2, texts.getProvinciaNull());
                    }
                    Municipio p = getInstanceOfMunicipio().findMunicipioByNombreAndPais(municipio, prov.getIdprovincia(), c.getIdpais());
                    if (c == null) {
                        return new Par(2, texts.getMunicipioNull());
                    }
                    if (p.getCumList().size() > 0) {
                        return new Par(2, texts.getCumMunic());
                    }
                    cum.setMunicipioidmunicipio(p);
                    Universidad u = getInstanceOfUniversidad().findUniversidadAvailable();
                    if (u != null) {
                        cum.setUniversidadcodigouniversidad(u);
                        getInstanceOfCum().create(cum);
                        return new Par(1, texts.getSatisfactorio());
                    } else {
                        return new Par(2, texts.getUniversidadActiva());
                    }
                } else {
                    return new Par(3, texts.getInformacion());
                }
            } else {
                return new Par(3, texts.getInformacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(CumServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editCum(String codigo, String nombre, String iniciales, String actividad, boolean cancel, String telefono, String reglamento, String fax, String nombredirector, String nombresec, String direccion, String municipio, String provincia) {
        try {
            Cum cum = getInstanceOfCum().findCum(codigo);
            if (cum != null) {
                cum.setActividadcum(actividad);
                cum.setCumcancelado(cancel);
                cum.setDireccioncum(direccion);
                cum.setFaxcum(fax);
                cum.setInicialescum(iniciales);
                if (!cum.getNombrecum().equals(nombre) && getInstanceOfCum().findCumByNombre(nombre) != null) {
                    return new Par(2, texts.getInformacion());
                }
                cum.setNombrecum(nombre);
                cum.setNombredirectorcum(nombredirector);
                cum.setNombresecgralcum(nombresec);
                cum.setReglamentocum(reglamento);
                cum.setTelefonocum(telefono);
                Pais c = getInstanceOfPais().findPaisByNombre("Cuba");
                if (c == null) {
                    return new Par(2, texts.getPaisNull());
                }
                Provincia prov = getInstanceOfProvincia().findProvinciaByNombreAndPais(provincia, c.getIdpais());
                if (prov == null) {
                    return new Par(2, texts.getProvinciaNull());
                }
                Municipio p = getInstanceOfMunicipio().findMunicipioByNombreAndPais(municipio, prov.getIdprovincia(), c.getIdpais());
                if (p == null) {
                    return new Par(2, texts.getMunicipioNull());
                }
                if (!cum.getMunicipioidmunicipio().getNombremunicipio().equals(p.getNombremunicipio()) && p.getCumList().size() > 0) {
                    return new Par(2, texts.getCumMunic());
                }
                cum.setMunicipioidmunicipio(p);
                getInstanceOfCum().edit(cum);
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(3, texts.getCumNull());
            }
        } catch (Exception ex) {
            Logger.getLogger(CumServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par cancelarCum(String codigo) {
//        try {
//            Cum p = getInstanceOfCum().findCum(codigo);
//            p.setCumcancelado(!p.getCumcancelado());
//            getInstanceOfCum().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(CumServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteCum(String codigo) {
//        try {
//            getInstanceOfCum().destroy(codigo);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(CumServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
    public List<Cum> findAllCum() {
        return getInstanceOfCum().findCumList();
    }

//    public List<String> findCumAvailables() {
//        return getInstanceOfCum().findCumAvailable();
//    }
     public List<String> findCumAvailables() {
        userLoging u=new userLoging();
        return getInstanceOfUsers().findCumAutorities(u.getUsername());
    }

    public List<String> findCumAvailablesbyFacultad(String fac) {
        Facultad f = getInstanceOfFacultad().findFacultadByNombre(fac);
        if (f != null) {
            userLoging u=new userLoging();
            return getInstanceOfCum().findCumAvailableByFacultad(fac, u.getUsername());
        }
        return new ArrayList<String>();
    }

    public List<String> findCumAvailablesbyFacultad(String fac, String carrera) {
        Facultad f = getInstanceOfFacultad().findFacultadByNombre(fac);
        Carrera c = getInstanceOfCarrera().findCarreraByFacultadCarreranacionalAvailable(fac, carrera);
        if (f != null && c != null) {
            return getInstanceOfCum().findCumAvailableByFacultadCarrera(fac, carrera);
        }
        return new ArrayList<String>();
    }

//    public List<Cum> findCumbyFacultad(String fac) {
//        Facultad f = getInstanceOfFacultad().findFacultadByNombre(fac);
//        if (f != null) {
//            return getInstanceOfCum().findCumByFacultad(f.getCodigoarea());
//        }
//        return new ArrayList<Cum>();
//    }
}
