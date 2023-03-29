/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.CarreranacionalJpaController;
import cu.edu.unah.sigenuead.controller.EspecialidadJpaController;
import cu.edu.unah.sigenuead.entity.Carreranacional;
import cu.edu.unah.sigenuead.entity.Especialidad;
import cu.edu.unah.sigenuead.entity.Par;
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
public class CarreraNacionalServices {

    private CarreranacionalJpaController controllerCarreranacional;

    public CarreranacionalJpaController getInstanceOfCarreranacional() {
        return (controllerCarreranacional == null) ? controllerCarreranacional = new CarreranacionalJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerCarreranacional;
    }

    private EspecialidadJpaController controllerEspecialidad;

    public EspecialidadJpaController getInstanceOfEspecialidad() {
        return (controllerEspecialidad == null) ? controllerEspecialidad = new EspecialidadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerEspecialidad;
    }

    public Par addCarreranacional(String nombre, String codigo, String diploma, String especialidad, boolean cancel) {
        try {
            if (getInstanceOfCarreranacional().findCarreraNacionalByCodigo(codigo) == null) {
                Carreranacional cn = new Carreranacional();
                cn.setCanceladocarreranacional(cancel);
                cn.setCodigocarreranacional(codigo);
                cn.setDiplomacarreranacional(diploma);
                Especialidad e = getInstanceOfEspecialidad().findEspecialidadByNombre(especialidad);
                if (e == null) {
                    return new Par(2, texts.getEspecialidadNull());
                }
                cn.setEspecialidadidespecialidad(e);
                cn.setNombrecarreranacional(nombre);
                if (getInstanceOfCarreranacional().findCarreraNacionalByNombre(nombre, e.getIdespecialidad()) == null) {
                    getInstanceOfCarreranacional().create(cn);
                    return new Par(1, texts.getSatisfactorio());
                } else {
                    return new Par(3, texts.getInformacion());
                }
            } else {
                return new Par(2, texts.getCodigoExistente());
            }
        } catch (Exception ex) {
            Logger.getLogger(CarreraNacionalServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par editCarreranacional(String nombre, String nombreNew, String codigo, String diploma, String especialidad, String especialidadNew, boolean cancel) {
        try {
            Especialidad e = getInstanceOfEspecialidad().findEspecialidadByNombre(especialidad);
            if (e == null) {
                return new Par(2, texts.getEspecialidadNull());
            }
            Carreranacional p = getInstanceOfCarreranacional().findCarreraNacionalByNombre(nombre, e.getIdespecialidad());
            if (p != null) {
                if (!nombre.equals(nombreNew) && getInstanceOfCarreranacional().findCarreraNacionalByNombre(nombreNew, e.getIdespecialidad())!=null) {
                    return new Par(2, texts.getEditarExistente());
                }
                p.setNombrecarreranacional(nombreNew);
                p.setCanceladocarreranacional(cancel);
                if (!codigo.equals(p.getCodigocarreranacional()) && getInstanceOfCarreranacional().findCarreraNacionalByCodigo(codigo)!=null) {
                    return new Par(2, texts.getEditarExistente());
                }
                p.setCodigocarreranacional(codigo);
                p.setDiplomacarreranacional(diploma);
                Especialidad f = getInstanceOfEspecialidad().findEspecialidadByNombre(especialidadNew);
                if (f == null) {
                    return new Par(2, texts.getEspecialidadNull());
                }
                p.setEspecialidadidespecialidad(f);
                getInstanceOfCarreranacional().edit(p);
                return new Par(1, texts.getSatisfactorio());
            } else {
                return new Par(2, texts.getCarreraNacionalNull());
            }

        } catch (Exception ex) {
            Logger.getLogger(CarreraNacionalServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }
//
//    public Par cancelarCarreranacional(String nombre) {
//        try {
//            Carreranacional p = getInstanceOfCarreranacional().findCarreranacionalByNombre(nombre);
//            p.setCarreranacionalCancelado(!p.getCarreranacionalCancelado());
//            getInstanceOfCarreranacional().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(CarreraNacionalServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par deleteCarreraNacional(String nombre, String especialidad) {
//        try {
//            Especialidad e = getInstanceOfEspecialidad().findEspecialidadByNombre(especialidad);
//            Carreranacional p = getInstanceOfCarreranacional().findCarreraNacionalByNombre(nombre, e.getIdespecialidad());
//            getInstanceOfCarreranacional().destroy(p.getIdcarreranacional());
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(CarreraNacionalServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }

    public List<Carreranacional> findAllCarreranacional() {
        return getInstanceOfCarreranacional().findCarreranacionalEntities();
    }

    public List<String> findCarreranacionalAvailable() {
        return getInstanceOfCarreranacional().findCarreranacionalAvailable();
    }

    public List<String> findCarreranacionalAvailableByEspecialidad(String especialidad) {
        Especialidad e = getInstanceOfEspecialidad().findEspecialidadByNombre(especialidad);
        if (e != null) {
            return getInstanceOfCarreranacional().findCarreranacionalAvailableByEspecialidad(e.getIdespecialidad());
        }
        return new ArrayList<String>();
    }
}
