/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.CarreraJpaController;
import cu.edu.unah.sigenuead.controller.CumJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumCarreraJpaController;
import cu.edu.unah.sigenuead.controller.FacultadCumJpaController;
import cu.edu.unah.sigenuead.controller.FacultadJpaController;
import cu.edu.unah.sigenuead.controller.MunicipioJpaController;
import cu.edu.unah.sigenuead.controller.PaisJpaController;
import cu.edu.unah.sigenuead.controller.ProvinciaJpaController;
import cu.edu.unah.sigenuead.controller.UniversidadJpaController;
import cu.edu.unah.sigenuead.controller.UsersJpaController;
import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.Cum;
import cu.edu.unah.sigenuead.entity.Facultad;
import cu.edu.unah.sigenuead.entity.FacultadCum;
import cu.edu.unah.sigenuead.entity.FacultadCumCarrera;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraPK;
import cu.edu.unah.sigenuead.entity.FacultadCumPK;
import cu.edu.unah.sigenuead.entity.Municipio;
import cu.edu.unah.sigenuead.entity.Pais;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.Provincia;
import cu.edu.unah.sigenuead.entity.Universidad;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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
public class FacultadServices {

    private UniversidadJpaController controllerUniversidad;

    public UniversidadJpaController getInstanceOfUniversidad() {
        return (controllerUniversidad == null) ? controllerUniversidad = new UniversidadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerUniversidad;
    }
    private CumJpaController controllerCum;

    public CumJpaController getInstanceOfCum() {
        return (controllerCum == null) ? controllerCum = new CumJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCum;
    }
    private FacultadCumJpaController controllerFacultadCum;

    public FacultadCumJpaController getInstanceOfFacultadCum() {
        return (controllerFacultadCum == null) ? controllerFacultadCum = new FacultadCumJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultadCum;
    }
    private FacultadCumCarreraJpaController controllerFacultadCumCarrera;

    public FacultadCumCarreraJpaController getInstanceOfFacultadCumCarrera() {
        return (controllerFacultadCumCarrera == null) ? controllerFacultadCumCarrera = new FacultadCumCarreraJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultadCumCarrera;
    }
    private FacultadJpaController controllerFacultad;

    public FacultadJpaController getInstanceOfFacultad() {
        return (controllerFacultad == null) ? controllerFacultad = new FacultadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultad;
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
    private CarreraJpaController controllerCarrera;

    public CarreraJpaController getInstanceOfCarrera() {
        return (controllerCarrera == null) ? controllerCarrera = new CarreraJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCarrera;
    }
    private UsersJpaController controllerUsers;

    public UsersJpaController getInstanceOfUsers() {
        return (controllerUsers == null) ? controllerUsers = new UsersJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerUsers;
    }

    public Par addFacultad(String codigo, String nombre, String nombreSecGral, String nombreDecano, String telefono, String direccion, String municipio, String provincia, boolean cancel) {
        try {
            if (getInstanceOfFacultad().findFacultad(codigo) != null || getInstanceOfFacultad().findFacultadByNombre(nombre) != null) {
                return new Par(2, texts.getInformacion());
            }
            Facultad f = new Facultad(codigo);
            Universidad u = getInstanceOfUniversidad().findUniversidadAvailable();
            if (u == null) {
                return new Par(2, texts.getUniversidadActiva());
            }
            f.setUniversidadcodigouniversidad(u);
            f.setCanceladoarea(cancel);
            f.setDireccionarea(direccion);
            f.setNombrearea(nombre);
            f.setNombredecanoarea(nombreDecano);
            f.setNombresecdocarea(nombreSecGral);
            f.setTelefonoarea(telefono);
            Pais c = getInstanceOfPais().findPaisByNombreAvailable("Cuba");
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
            f.setMunicipioidmunicipio(p);
            getInstanceOfFacultad().create(f);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(FacultadServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editFacultad(String codigo, String nombre, String nombreSecGral, String nombreDecano, String telefono, String direccion, String municipio, String provincia, boolean cancel) {
        try {
            Facultad f = getInstanceOfFacultad().findFacultad(codigo);
            if (f == null) {
                return new Par(2, texts.getFacultadNull());
            }
            f.setCanceladoarea(cancel);
            f.setDireccionarea(direccion);
            if (!f.getNombrearea().equals(nombre) && getInstanceOfFacultad().findFacultadByNombre(nombre) == null) {
                return new Par(2, texts.getEditarExistente());
            }
            f.setNombrearea(nombre);
            f.setNombredecanoarea(nombreDecano);
            f.setNombresecdocarea(nombreSecGral);
            f.setTelefonoarea(telefono);
            Pais c = getInstanceOfPais().findPaisByNombreAvailable("Cuba");
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
            f.setMunicipioidmunicipio(p);
            getInstanceOfFacultad().edit(f);
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(FacultadServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }
//
//    public Par cancelarFacultad(String codigo) {
//        try {
//            Facultad p = getInstanceOfFacultad().findFacultad(codigo);
//            p.setCanceladoarea(!p.getCanceladoarea());
//            getInstanceOfFacultad().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//
//        } catch (Exception ex) {
//            Logger.getLogger(FacultadServices.class
//                    .getName()).log(Level.SEVERE, null, ex);
//            return new Par(
//                    2, texts.getError());
//        }
//    }
//
//    public Par deleteFacultad(String codigo) {
//        try {
//            getInstanceOfFacultad().destroy(codigo);
//            return new Par(1, texts.getSatisfactorio());
//
//        } catch (Exception ex) {
//            Logger.getLogger(FacultadServices.class
//                    .getName()).log(Level.SEVERE, null, ex);
//            return new Par(
//                    2, texts.getError());
//        }
//    }

    public Par addCarreraToCum(String facultad, String carrera, String[] cums) {
        try {
            Facultad fac = getInstanceOfFacultad().findFacultadByNombreAvailable(facultad);
            if (fac == null) {
                return new Par(2, texts.getFacultadNull());
            }
            Carrera carr = getInstanceOfCarrera().findCarreraByFacultadCarreranacionalAvailable(facultad, carrera);
            if (carr == null) {
                return new Par(2, texts.getCarreraNull());
            }
            List<String> cumListnew = Arrays.asList(cums);
            List<String> cumList = getInstanceOfFacultadCumCarrera().findCarreraByFacultadCum(fac.getCodigoarea(), carr.getIdcarrera());
            for (int i = 0; i < cumList.size(); i++) {
                if (!cumListnew.contains(cumList.get(i))) {
                    Cum c = getInstanceOfCum().findCumByNombre(cumList.get(i));
                    FacultadCumCarrera fcc = getInstanceOfFacultadCumCarrera().findFacultadCumCarrera(new FacultadCumCarreraPK(c.getCodigocum(), fac.getCodigoarea(), carr.getIdcarrera()));
                    if (fcc != null) {
                        fcc.setCancelado(true);
                        getInstanceOfFacultadCumCarrera().edit(fcc);
                    }

                    List<String> car=getInstanceOfCarrera().findCarreraAvailableByFacultadCum(fac.getCodigoarea(), c.getCodigocum());
                    if (car.isEmpty()) {
                        FacultadCum fc=getInstanceOfFacultadCum().findFacultadCum(new FacultadCumPK(c.getCodigocum(), fac.getCodigoarea()));
                        fc.setCancelado(true);
                        getInstanceOfFacultadCum().edit(fc);
                    }
                }
            }
            for (int i = 0; i < cumListnew.size(); i++) {
                Cum c = getInstanceOfCum().findCumByNombreAvailable(cumListnew.get(i));
                if (c == null) {
                    return new Par(2, texts.getCumNull());
                }
                FacultadCum fc = getInstanceOfFacultadCum().findFacultadCum(new FacultadCumPK(c.getCodigocum(), fac.getCodigoarea()));
                if (fc == null) {
                    fc = new FacultadCum(c.getCodigocum(), fac.getCodigoarea());
                    fc.setCancelado(false);
                    fc.setCum(c);
                    fc.setFacultad(fac);
                    getInstanceOfFacultadCum().create(fc);
                } else {
                    if (fc.getCancelado()) {
                        fc.setCancelado(false);
                        getInstanceOfFacultadCum().edit(fc);
                    }
                }
                FacultadCumCarrera fcc = getInstanceOfFacultadCumCarrera().findFacultadCumCarrera(new FacultadCumCarreraPK(c.getCodigocum(), fac.getCodigoarea(), carr.getIdcarrera()));
                if (fcc == null) {
                    fcc = new FacultadCumCarrera(c.getCodigocum(), fac.getCodigoarea(), carr.getIdcarrera());
                    fcc.setCarrera(carr);
                    fcc.setFacultadCum(fc);
                    fcc.setCancelado(false);
                    getInstanceOfFacultadCumCarrera().create(fcc);
                } else {
                    fcc.setCancelado(false);
                    getInstanceOfFacultadCumCarrera().edit(fcc);
                }
            }
            return new Par(1, texts.getSatisfactorio());
        } catch (Exception ex) {
            Logger.getLogger(FacultadServices.class
                    .getName()).log(Level.SEVERE, null, ex);
            return new Par(
                    2, texts.getError());
        }
    }

    public List<Facultad> findAllFacultad() {
        return getInstanceOfFacultad().findFacultadEntities();
    }

//    public List<String> findAreasAvailables() {
//        List<String> res = new ArrayList<String>();
//        List<String> fac = getInstanceOfFacultad().findFacultadAvailable();
//        List<String> cum = getInstanceOfCum().findCumAvailable();
//        if (fac != null) {
//            res.addAll(fac);
//        }
//        if (cum != null) {
//            res.addAll(cum);
//        }
//        Collections.sort(res);
//        return res;
//    }
    public List<String> findAreasAvailables() {
        userLoging u=new userLoging();
        List<String> res = new ArrayList<String>();
        List<String> fac = getInstanceOfUsers().findFacultadAutorities(u.getUsername());
        List<String> cum = getInstanceOfUsers().findCumAutorities(u.getUsername());
        if (fac != null) {
            res.addAll(fac);
        }
        if (cum != null) {
            res.addAll(cum);
        }
        Collections.sort(res);
        return res;
    }
    public List<String> findAreasAvailablesForRoles() {
        userLoging u=new userLoging();
        List<String> res = new ArrayList<String>();
        List<String> fac = getInstanceOfFacultad().findFacultadAvailable();
        List<String> cum = getInstanceOfCum().findCumAvailable();
        if (fac != null) {
            res.addAll(fac);
        }
        if (cum != null) {
            res.addAll(cum);
        }
        Collections.sort(res);
        return res;
    }

//    public List<String> findFacultadAvailables() {
//        return getInstanceOfFacultad().findFacultadAvailable();
//    }
    public List<String> findFacultadAvailables() {
        userLoging u=new userLoging();
        return getInstanceOfUsers().findFacultadAutorities(u.getUsername());
    }
}
