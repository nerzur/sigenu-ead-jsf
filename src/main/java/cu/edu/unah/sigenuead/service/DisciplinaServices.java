/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.DisciplinaJpaController;
import cu.edu.unah.sigenuead.controller.FacultadJpaController;
import cu.edu.unah.sigenuead.entity.Disciplina;
import cu.edu.unah.sigenuead.entity.Facultad;
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
public class DisciplinaServices {

    private DisciplinaJpaController controllerDisciplina;

    public DisciplinaJpaController getInstanceOfDisciplina() {
        return (controllerDisciplina == null) ? controllerDisciplina = new DisciplinaJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerDisciplina;
    }
    private FacultadJpaController controllerFacultad;

    public FacultadJpaController getInstanceOfFacultad() {
        return (controllerFacultad == null) ? controllerFacultad = new FacultadJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerFacultad;
    }

    public Par AddDisciplina(String codigo, String nombre, boolean cancel) {
        try {
            if (getInstanceOfDisciplina().findDisciplina(codigo) == null) {
                if (getInstanceOfDisciplina().findDisciplinaByNombre(nombre) == null) {
                    getInstanceOfDisciplina().create(new Disciplina(codigo, nombre, cancel));
                    return new Par(1, texts.getSatisfactorio());
                } else {
                    return new Par(2, texts.getInformacion());
                }
            } else {
                return new Par(2, texts.getInformacion());
            }

        } catch (Exception ex) {
            Logger.getLogger(DisciplinaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

    public Par EditDisciplina(String nombre, String nombreNew, boolean cancel) {
        try {
            Disciplina p = getInstanceOfDisciplina().findDisciplinaByNombre(nombre);
            if (p != null) {
                if (nombre.equals(nombreNew) || getInstanceOfDisciplina().findDisciplinaByNombre(nombreNew) == null) {
                    p.setDisciplinaNombre(nombreNew);
                    p.setDisciplinaCancelada(cancel);
                    getInstanceOfDisciplina().edit(p);
                    return new Par(1, texts.getSatisfactorio());
                } else {
                    return new Par(2, texts.getEditarExistente());
                }
            } else {
                return new Par(2, texts.getDisciplinaNull());
            }
        } catch (Exception ex) {
            Logger.getLogger(DisciplinaServices.class.getName()).log(Level.SEVERE, null, ex);
            return new Par(2, texts.getError());
        }
    }

//    public Par CancelDisciplina(String nombre) {
//        try {
//            Disciplina p = getInstanceOfDisciplina().findDisciplinaByNombre(nombre);
//            p.setDisciplinaCancelada(!p.getDisciplinaCancelada());
//            getInstanceOfDisciplina().edit(p);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(DisciplinaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
//
//    public Par DeleteDisciplina(String codigo) {
//        try {;
//            getInstanceOfDisciplina().destroy(codigo);
//            return new Par(1, texts.getSatisfactorio());
//        } catch (Exception ex) {
//            Logger.getLogger(DisciplinaServices.class.getName()).log(Level.SEVERE, null, ex);
//            return new Par(2, texts.getError());
//        }
//    }
    public List<Disciplina> findAllDisciplina() {
        return getInstanceOfDisciplina().findDisciplinaEntities();
    }

    public List<String> findDisciplinaAvailable() {
        return getInstanceOfDisciplina().findDisciplinaAvailable();
    }

    public List<String> findDisciplinaAvailableByPlanEstudio(String carrera, String facultad, String tipoplan) {
        Facultad f = getInstanceOfFacultad().findFacultadByNombre(facultad);
        if (f != null) {
            return getInstanceOfDisciplina().findDisciplinaAvailableByPlanEstudio(carrera, f.getCodigoarea(), tipoplan);
        }
        return new ArrayList<String>();
    }

    public List<Disciplina> findDisciplinaByPlanEstudio(String carrera, String facultad, String tipoplan) {
        Facultad f = getInstanceOfFacultad().findFacultadByNombre(facultad);
        if (f != null) {
            return getInstanceOfDisciplina().findDisciplinaByPlanEstudio(carrera, f.getCodigoarea(), tipoplan);
        }
        return new ArrayList<Disciplina>();
    }
    
    public Disciplina findDisciplinaByNombreAvailable(String nombre) {
        return getInstanceOfDisciplina().findDisciplinaByNombreAvailable(nombre);
    }
}
