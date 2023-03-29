/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.EstadoEstudiante;
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.FacultadCumCarrera;
import cu.edu.unah.sigenuead.entity.FuenteIngreso;
import cu.edu.unah.sigenuead.entity.Planestudio;
import cu.edu.unah.sigenuead.entity.Baja;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import java.util.ArrayList;
import java.util.List;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiantePK;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class FacultadCumCarreraEstudianteJpaController implements Serializable {

    public FacultadCumCarreraEstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FacultadCumCarreraEstudiante facultadCumCarreraEstudiante) throws PreexistingEntityException, Exception {
        if (facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK() == null) {
            facultadCumCarreraEstudiante.setFacultadCumCarreraEstudiantePK(new FacultadCumCarreraEstudiantePK());
        }
        if (facultadCumCarreraEstudiante.getBajaList() == null) {
            facultadCumCarreraEstudiante.setBajaList(new ArrayList<Baja>());
        }
        if (facultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteAsignaturaList() == null) {
            facultadCumCarreraEstudiante.setFacultadCumCarreraEstudianteAsignaturaList(new ArrayList<FacultadCumCarreraEstudianteAsignatura>());
        }
        facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK().setEstudianteestudianteId(facultadCumCarreraEstudiante.getEstudiante().getEstudianteId());
        facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK().setFacultadCumCarreracarreraidcarrera(facultadCumCarreraEstudiante.getFacultadCumCarrera().getFacultadCumCarreraPK().getCarreraidcarrera());
        facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK().setFacultadCumCarrerafacultadCumfacultadcodigoarea(facultadCumCarreraEstudiante.getFacultadCumCarrera().getFacultadCumCarreraPK().getFacultadCumfacultadcodigoarea());
        facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK().setFacultadCumCarrerafacultadCumcumcodigocum(facultadCumCarreraEstudiante.getFacultadCumCarrera().getFacultadCumCarreraPK().getFacultadCumcumcodigocum());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoEstudiante estadoEstudianteestadoEstucianteId = facultadCumCarreraEstudiante.getEstadoEstudianteestadoEstucianteId();
            if (estadoEstudianteestadoEstucianteId != null) {
                estadoEstudianteestadoEstucianteId = em.getReference(estadoEstudianteestadoEstucianteId.getClass(), estadoEstudianteestadoEstucianteId.getEstadoEstucianteId());
                facultadCumCarreraEstudiante.setEstadoEstudianteestadoEstucianteId(estadoEstudianteestadoEstucianteId);
            }
            Estudiante estudiante = facultadCumCarreraEstudiante.getEstudiante();
            if (estudiante != null) {
                estudiante = em.getReference(estudiante.getClass(), estudiante.getEstudianteId());
                facultadCumCarreraEstudiante.setEstudiante(estudiante);
            }
            FacultadCumCarrera facultadCumCarrera = facultadCumCarreraEstudiante.getFacultadCumCarrera();
            if (facultadCumCarrera != null) {
                facultadCumCarrera = em.getReference(facultadCumCarrera.getClass(), facultadCumCarrera.getFacultadCumCarreraPK());
                facultadCumCarreraEstudiante.setFacultadCumCarrera(facultadCumCarrera);
            }
            FuenteIngreso fuenteIngresofuenteIngresoId = facultadCumCarreraEstudiante.getFuenteIngresofuenteIngresoId();
            if (fuenteIngresofuenteIngresoId != null) {
                fuenteIngresofuenteIngresoId = em.getReference(fuenteIngresofuenteIngresoId.getClass(), fuenteIngresofuenteIngresoId.getFuenteIngresoId());
                facultadCumCarreraEstudiante.setFuenteIngresofuenteIngresoId(fuenteIngresofuenteIngresoId);
            }
            Planestudio planestudioidplanestudio = facultadCumCarreraEstudiante.getPlanestudioidplanestudio();
            if (planestudioidplanestudio != null) {
                planestudioidplanestudio = em.getReference(planestudioidplanestudio.getClass(), planestudioidplanestudio.getIdplanestudio());
                facultadCumCarreraEstudiante.setPlanestudioidplanestudio(planestudioidplanestudio);
            }
            List<Baja> attachedBajaList = new ArrayList<Baja>();
            for (Baja bajaListBajaToAttach : facultadCumCarreraEstudiante.getBajaList()) {
                bajaListBajaToAttach = em.getReference(bajaListBajaToAttach.getClass(), bajaListBajaToAttach.getBajaPK());
                attachedBajaList.add(bajaListBajaToAttach);
            }
            facultadCumCarreraEstudiante.setBajaList(attachedBajaList);
            List<FacultadCumCarreraEstudianteAsignatura> attachedFacultadCumCarreraEstudianteAsignaturaList = new ArrayList<FacultadCumCarreraEstudianteAsignatura>();
            for (FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignaturaToAttach : facultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteAsignaturaList()) {
                facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignaturaToAttach = em.getReference(facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignaturaToAttach.getClass(), facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignaturaToAttach.getFacultadCumCarreraEstudianteAsignaturaPK());
                attachedFacultadCumCarreraEstudianteAsignaturaList.add(facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignaturaToAttach);
            }
            facultadCumCarreraEstudiante.setFacultadCumCarreraEstudianteAsignaturaList(attachedFacultadCumCarreraEstudianteAsignaturaList);
            em.persist(facultadCumCarreraEstudiante);
            if (estadoEstudianteestadoEstucianteId != null) {
                estadoEstudianteestadoEstucianteId.getFacultadCumCarreraEstudianteList().add(facultadCumCarreraEstudiante);
                estadoEstudianteestadoEstucianteId = em.merge(estadoEstudianteestadoEstucianteId);
            }
            if (estudiante != null) {
                estudiante.getFacultadCumCarreraEstudianteList().add(facultadCumCarreraEstudiante);
                estudiante = em.merge(estudiante);
            }
            if (facultadCumCarrera != null) {
                facultadCumCarrera.getFacultadCumCarreraEstudianteList().add(facultadCumCarreraEstudiante);
                facultadCumCarrera = em.merge(facultadCumCarrera);
            }
            if (fuenteIngresofuenteIngresoId != null) {
                fuenteIngresofuenteIngresoId.getFacultadCumCarreraEstudianteList().add(facultadCumCarreraEstudiante);
                fuenteIngresofuenteIngresoId = em.merge(fuenteIngresofuenteIngresoId);
            }
            if (planestudioidplanestudio != null) {
                planestudioidplanestudio.getFacultadCumCarreraEstudianteList().add(facultadCumCarreraEstudiante);
                planestudioidplanestudio = em.merge(planestudioidplanestudio);
            }
            for (Baja bajaListBaja : facultadCumCarreraEstudiante.getBajaList()) {
                FacultadCumCarreraEstudiante oldFacultadCumCarreraEstudianteOfBajaListBaja = bajaListBaja.getFacultadCumCarreraEstudiante();
                bajaListBaja.setFacultadCumCarreraEstudiante(facultadCumCarreraEstudiante);
                bajaListBaja = em.merge(bajaListBaja);
                if (oldFacultadCumCarreraEstudianteOfBajaListBaja != null) {
                    oldFacultadCumCarreraEstudianteOfBajaListBaja.getBajaList().remove(bajaListBaja);
                    oldFacultadCumCarreraEstudianteOfBajaListBaja = em.merge(oldFacultadCumCarreraEstudianteOfBajaListBaja);
                }
            }
            for (FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura : facultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteAsignaturaList()) {
                FacultadCumCarreraEstudiante oldFacultadCumCarreraEstudianteOfFacultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura = facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante();
                facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura.setFacultadCumCarreraEstudiante(facultadCumCarreraEstudiante);
                facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura = em.merge(facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura);
                if (oldFacultadCumCarreraEstudianteOfFacultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura != null) {
                    oldFacultadCumCarreraEstudianteOfFacultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaList().remove(facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura);
                    oldFacultadCumCarreraEstudianteOfFacultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura = em.merge(oldFacultadCumCarreraEstudianteOfFacultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFacultadCumCarreraEstudiante(facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK()) != null) {
                throw new PreexistingEntityException("FacultadCumCarreraEstudiante " + facultadCumCarreraEstudiante + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FacultadCumCarreraEstudiante facultadCumCarreraEstudiante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK().setEstudianteestudianteId(facultadCumCarreraEstudiante.getEstudiante().getEstudianteId());
        facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK().setFacultadCumCarreracarreraidcarrera(facultadCumCarreraEstudiante.getFacultadCumCarrera().getFacultadCumCarreraPK().getCarreraidcarrera());
        facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK().setFacultadCumCarrerafacultadCumfacultadcodigoarea(facultadCumCarreraEstudiante.getFacultadCumCarrera().getFacultadCumCarreraPK().getFacultadCumfacultadcodigoarea());
        facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK().setFacultadCumCarrerafacultadCumcumcodigocum(facultadCumCarreraEstudiante.getFacultadCumCarrera().getFacultadCumCarreraPK().getFacultadCumcumcodigocum());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacultadCumCarreraEstudiante persistentFacultadCumCarreraEstudiante = em.find(FacultadCumCarreraEstudiante.class, facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK());
            EstadoEstudiante estadoEstudianteestadoEstucianteIdOld = persistentFacultadCumCarreraEstudiante.getEstadoEstudianteestadoEstucianteId();
            EstadoEstudiante estadoEstudianteestadoEstucianteIdNew = facultadCumCarreraEstudiante.getEstadoEstudianteestadoEstucianteId();
            Estudiante estudianteOld = persistentFacultadCumCarreraEstudiante.getEstudiante();
            Estudiante estudianteNew = facultadCumCarreraEstudiante.getEstudiante();
            FacultadCumCarrera facultadCumCarreraOld = persistentFacultadCumCarreraEstudiante.getFacultadCumCarrera();
            FacultadCumCarrera facultadCumCarreraNew = facultadCumCarreraEstudiante.getFacultadCumCarrera();
            FuenteIngreso fuenteIngresofuenteIngresoIdOld = persistentFacultadCumCarreraEstudiante.getFuenteIngresofuenteIngresoId();
            FuenteIngreso fuenteIngresofuenteIngresoIdNew = facultadCumCarreraEstudiante.getFuenteIngresofuenteIngresoId();
            Planestudio planestudioidplanestudioOld = persistentFacultadCumCarreraEstudiante.getPlanestudioidplanestudio();
            Planestudio planestudioidplanestudioNew = facultadCumCarreraEstudiante.getPlanestudioidplanestudio();
            List<Baja> bajaListOld = persistentFacultadCumCarreraEstudiante.getBajaList();
            List<Baja> bajaListNew = facultadCumCarreraEstudiante.getBajaList();
            List<FacultadCumCarreraEstudianteAsignatura> facultadCumCarreraEstudianteAsignaturaListOld = persistentFacultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteAsignaturaList();
            List<FacultadCumCarreraEstudianteAsignatura> facultadCumCarreraEstudianteAsignaturaListNew = facultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteAsignaturaList();
            List<String> illegalOrphanMessages = null;
            for (Baja bajaListOldBaja : bajaListOld) {
                if (!bajaListNew.contains(bajaListOldBaja)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Baja " + bajaListOldBaja + " since its facultadCumCarreraEstudiante field is not nullable.");
                }
            }
            for (FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaListOldFacultadCumCarreraEstudianteAsignatura : facultadCumCarreraEstudianteAsignaturaListOld) {
                if (!facultadCumCarreraEstudianteAsignaturaListNew.contains(facultadCumCarreraEstudianteAsignaturaListOldFacultadCumCarreraEstudianteAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FacultadCumCarreraEstudianteAsignatura " + facultadCumCarreraEstudianteAsignaturaListOldFacultadCumCarreraEstudianteAsignatura + " since its facultadCumCarreraEstudiante field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoEstudianteestadoEstucianteIdNew != null) {
                estadoEstudianteestadoEstucianteIdNew = em.getReference(estadoEstudianteestadoEstucianteIdNew.getClass(), estadoEstudianteestadoEstucianteIdNew.getEstadoEstucianteId());
                facultadCumCarreraEstudiante.setEstadoEstudianteestadoEstucianteId(estadoEstudianteestadoEstucianteIdNew);
            }
            if (estudianteNew != null) {
                estudianteNew = em.getReference(estudianteNew.getClass(), estudianteNew.getEstudianteId());
                facultadCumCarreraEstudiante.setEstudiante(estudianteNew);
            }
            if (facultadCumCarreraNew != null) {
                facultadCumCarreraNew = em.getReference(facultadCumCarreraNew.getClass(), facultadCumCarreraNew.getFacultadCumCarreraPK());
                facultadCumCarreraEstudiante.setFacultadCumCarrera(facultadCumCarreraNew);
            }
            if (fuenteIngresofuenteIngresoIdNew != null) {
                fuenteIngresofuenteIngresoIdNew = em.getReference(fuenteIngresofuenteIngresoIdNew.getClass(), fuenteIngresofuenteIngresoIdNew.getFuenteIngresoId());
                facultadCumCarreraEstudiante.setFuenteIngresofuenteIngresoId(fuenteIngresofuenteIngresoIdNew);
            }
            if (planestudioidplanestudioNew != null) {
                planestudioidplanestudioNew = em.getReference(planestudioidplanestudioNew.getClass(), planestudioidplanestudioNew.getIdplanestudio());
                facultadCumCarreraEstudiante.setPlanestudioidplanestudio(planestudioidplanestudioNew);
            }
            List<Baja> attachedBajaListNew = new ArrayList<Baja>();
            for (Baja bajaListNewBajaToAttach : bajaListNew) {
                bajaListNewBajaToAttach = em.getReference(bajaListNewBajaToAttach.getClass(), bajaListNewBajaToAttach.getBajaPK());
                attachedBajaListNew.add(bajaListNewBajaToAttach);
            }
            bajaListNew = attachedBajaListNew;
            facultadCumCarreraEstudiante.setBajaList(bajaListNew);
            List<FacultadCumCarreraEstudianteAsignatura> attachedFacultadCumCarreraEstudianteAsignaturaListNew = new ArrayList<FacultadCumCarreraEstudianteAsignatura>();
            for (FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignaturaToAttach : facultadCumCarreraEstudianteAsignaturaListNew) {
                facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignaturaToAttach = em.getReference(facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignaturaToAttach.getClass(), facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignaturaToAttach.getFacultadCumCarreraEstudianteAsignaturaPK());
                attachedFacultadCumCarreraEstudianteAsignaturaListNew.add(facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignaturaToAttach);
            }
            facultadCumCarreraEstudianteAsignaturaListNew = attachedFacultadCumCarreraEstudianteAsignaturaListNew;
            facultadCumCarreraEstudiante.setFacultadCumCarreraEstudianteAsignaturaList(facultadCumCarreraEstudianteAsignaturaListNew);
            facultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudiante);
            if (estadoEstudianteestadoEstucianteIdOld != null && !estadoEstudianteestadoEstucianteIdOld.equals(estadoEstudianteestadoEstucianteIdNew)) {
                estadoEstudianteestadoEstucianteIdOld.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudiante);
                estadoEstudianteestadoEstucianteIdOld = em.merge(estadoEstudianteestadoEstucianteIdOld);
            }
            if (estadoEstudianteestadoEstucianteIdNew != null && !estadoEstudianteestadoEstucianteIdNew.equals(estadoEstudianteestadoEstucianteIdOld)) {
                estadoEstudianteestadoEstucianteIdNew.getFacultadCumCarreraEstudianteList().add(facultadCumCarreraEstudiante);
                estadoEstudianteestadoEstucianteIdNew = em.merge(estadoEstudianteestadoEstucianteIdNew);
            }
            if (estudianteOld != null && !estudianteOld.equals(estudianteNew)) {
                estudianteOld.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudiante);
                estudianteOld = em.merge(estudianteOld);
            }
            if (estudianteNew != null && !estudianteNew.equals(estudianteOld)) {
                estudianteNew.getFacultadCumCarreraEstudianteList().add(facultadCumCarreraEstudiante);
                estudianteNew = em.merge(estudianteNew);
            }
            if (facultadCumCarreraOld != null && !facultadCumCarreraOld.equals(facultadCumCarreraNew)) {
                facultadCumCarreraOld.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudiante);
                facultadCumCarreraOld = em.merge(facultadCumCarreraOld);
            }
            if (facultadCumCarreraNew != null && !facultadCumCarreraNew.equals(facultadCumCarreraOld)) {
                facultadCumCarreraNew.getFacultadCumCarreraEstudianteList().add(facultadCumCarreraEstudiante);
                facultadCumCarreraNew = em.merge(facultadCumCarreraNew);
            }
            if (fuenteIngresofuenteIngresoIdOld != null && !fuenteIngresofuenteIngresoIdOld.equals(fuenteIngresofuenteIngresoIdNew)) {
                fuenteIngresofuenteIngresoIdOld.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudiante);
                fuenteIngresofuenteIngresoIdOld = em.merge(fuenteIngresofuenteIngresoIdOld);
            }
            if (fuenteIngresofuenteIngresoIdNew != null && !fuenteIngresofuenteIngresoIdNew.equals(fuenteIngresofuenteIngresoIdOld)) {
                fuenteIngresofuenteIngresoIdNew.getFacultadCumCarreraEstudianteList().add(facultadCumCarreraEstudiante);
                fuenteIngresofuenteIngresoIdNew = em.merge(fuenteIngresofuenteIngresoIdNew);
            }
            if (planestudioidplanestudioOld != null && !planestudioidplanestudioOld.equals(planestudioidplanestudioNew)) {
                planestudioidplanestudioOld.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudiante);
                planestudioidplanestudioOld = em.merge(planestudioidplanestudioOld);
            }
            if (planestudioidplanestudioNew != null && !planestudioidplanestudioNew.equals(planestudioidplanestudioOld)) {
                planestudioidplanestudioNew.getFacultadCumCarreraEstudianteList().add(facultadCumCarreraEstudiante);
                planestudioidplanestudioNew = em.merge(planestudioidplanestudioNew);
            }
            for (Baja bajaListNewBaja : bajaListNew) {
                if (!bajaListOld.contains(bajaListNewBaja)) {
                    FacultadCumCarreraEstudiante oldFacultadCumCarreraEstudianteOfBajaListNewBaja = bajaListNewBaja.getFacultadCumCarreraEstudiante();
                    bajaListNewBaja.setFacultadCumCarreraEstudiante(facultadCumCarreraEstudiante);
                    bajaListNewBaja = em.merge(bajaListNewBaja);
                    if (oldFacultadCumCarreraEstudianteOfBajaListNewBaja != null && !oldFacultadCumCarreraEstudianteOfBajaListNewBaja.equals(facultadCumCarreraEstudiante)) {
                        oldFacultadCumCarreraEstudianteOfBajaListNewBaja.getBajaList().remove(bajaListNewBaja);
                        oldFacultadCumCarreraEstudianteOfBajaListNewBaja = em.merge(oldFacultadCumCarreraEstudianteOfBajaListNewBaja);
                    }
                }
            }
            for (FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura : facultadCumCarreraEstudianteAsignaturaListNew) {
                if (!facultadCumCarreraEstudianteAsignaturaListOld.contains(facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura)) {
                    FacultadCumCarreraEstudiante oldFacultadCumCarreraEstudianteOfFacultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura = facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante();
                    facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura.setFacultadCumCarreraEstudiante(facultadCumCarreraEstudiante);
                    facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura = em.merge(facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura);
                    if (oldFacultadCumCarreraEstudianteOfFacultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura != null && !oldFacultadCumCarreraEstudianteOfFacultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura.equals(facultadCumCarreraEstudiante)) {
                        oldFacultadCumCarreraEstudianteOfFacultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaList().remove(facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura);
                        oldFacultadCumCarreraEstudianteOfFacultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura = em.merge(oldFacultadCumCarreraEstudianteOfFacultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                FacultadCumCarreraEstudiantePK id = facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK();
                if (findFacultadCumCarreraEstudiante(id) == null) {
                    throw new NonexistentEntityException("The facultadCumCarreraEstudiante with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(FacultadCumCarreraEstudiantePK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacultadCumCarreraEstudiante facultadCumCarreraEstudiante;
            try {
                facultadCumCarreraEstudiante = em.getReference(FacultadCumCarreraEstudiante.class, id);
                facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facultadCumCarreraEstudiante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Baja> bajaListOrphanCheck = facultadCumCarreraEstudiante.getBajaList();
            for (Baja bajaListOrphanCheckBaja : bajaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FacultadCumCarreraEstudiante (" + facultadCumCarreraEstudiante + ") cannot be destroyed since the Baja " + bajaListOrphanCheckBaja + " in its bajaList field has a non-nullable facultadCumCarreraEstudiante field.");
            }
            List<FacultadCumCarreraEstudianteAsignatura> facultadCumCarreraEstudianteAsignaturaListOrphanCheck = facultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteAsignaturaList();
            for (FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaListOrphanCheckFacultadCumCarreraEstudianteAsignatura : facultadCumCarreraEstudianteAsignaturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FacultadCumCarreraEstudiante (" + facultadCumCarreraEstudiante + ") cannot be destroyed since the FacultadCumCarreraEstudianteAsignatura " + facultadCumCarreraEstudianteAsignaturaListOrphanCheckFacultadCumCarreraEstudianteAsignatura + " in its facultadCumCarreraEstudianteAsignaturaList field has a non-nullable facultadCumCarreraEstudiante field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            EstadoEstudiante estadoEstudianteestadoEstucianteId = facultadCumCarreraEstudiante.getEstadoEstudianteestadoEstucianteId();
            if (estadoEstudianteestadoEstucianteId != null) {
                estadoEstudianteestadoEstucianteId.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudiante);
                estadoEstudianteestadoEstucianteId = em.merge(estadoEstudianteestadoEstucianteId);
            }
            Estudiante estudiante = facultadCumCarreraEstudiante.getEstudiante();
            if (estudiante != null) {
                estudiante.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudiante);
                estudiante = em.merge(estudiante);
            }
            FacultadCumCarrera facultadCumCarrera = facultadCumCarreraEstudiante.getFacultadCumCarrera();
            if (facultadCumCarrera != null) {
                facultadCumCarrera.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudiante);
                facultadCumCarrera = em.merge(facultadCumCarrera);
            }
            FuenteIngreso fuenteIngresofuenteIngresoId = facultadCumCarreraEstudiante.getFuenteIngresofuenteIngresoId();
            if (fuenteIngresofuenteIngresoId != null) {
                fuenteIngresofuenteIngresoId.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudiante);
                fuenteIngresofuenteIngresoId = em.merge(fuenteIngresofuenteIngresoId);
            }
            Planestudio planestudioidplanestudio = facultadCumCarreraEstudiante.getPlanestudioidplanestudio();
            if (planestudioidplanestudio != null) {
                planestudioidplanestudio.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudiante);
                planestudioidplanestudio = em.merge(planestudioidplanestudio);
            }
            em.remove(facultadCumCarreraEstudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FacultadCumCarreraEstudiante> findFacultadCumCarreraEstudianteEntities() {
        return findFacultadCumCarreraEstudianteEntities(true, -1, -1);
    }

    public List<FacultadCumCarreraEstudiante> findFacultadCumCarreraEstudianteEntities(int maxResults, int firstResult) {
        return findFacultadCumCarreraEstudianteEntities(false, maxResults, firstResult);
    }

    private List<FacultadCumCarreraEstudiante> findFacultadCumCarreraEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FacultadCumCarreraEstudiante.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public FacultadCumCarreraEstudiante findFacultadCumCarreraEstudiante(FacultadCumCarreraEstudiantePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FacultadCumCarreraEstudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacultadCumCarreraEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FacultadCumCarreraEstudiante> rt = cq.from(FacultadCumCarreraEstudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteActivo(String idestudiante) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce WHERE fcce.estudiante.estudianteId= :idestudiante and fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre= 'Activo'");
            q.setParameter("idestudiante", idestudiante);
            FacultadCumCarreraEstudiante p = (FacultadCumCarreraEstudiante) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteActivoCum(String idestudiante, String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarrera.facultadCum.cum.authoritiesList cal WHERE cal.authoritiesPK.username=:user AND fcce.estudiante.estudianteId= :idestudiante and fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre= 'Activo'");
            q.setParameter("idestudiante", idestudiante);
            q.setParameter("user", user);
            FacultadCumCarreraEstudiante p = (FacultadCumCarreraEstudiante) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteActivoFacultad(String idestudiante, String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarrera.facultadCum.facultad.authoritiesList fal WHERE fal.authoritiesPK.username=:user AND fcce.estudiante.estudianteId= :idestudiante and fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre= 'Activo'");
            q.setParameter("idestudiante", idestudiante);
            q.setParameter("user", user);
            FacultadCumCarreraEstudiante p = (FacultadCumCarreraEstudiante) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteNuevaMatricula(String idestudiante) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce WHERE fcce.estudiante.estudianteId= :idestudiante and fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre= 'Nueva Matrícula'");
            q.setParameter("idestudiante", idestudiante);
            FacultadCumCarreraEstudiante p = (FacultadCumCarreraEstudiante) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteNuevaMatriculaCum(String idestudiante, String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarrera.facultadCum.cum.authoritiesList cal WHERE cal.authoritiesPK.username=:user AND fcce.estudiante.estudianteId= :idestudiante and fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre= 'Nueva Matrícula'");
            q.setParameter("idestudiante", idestudiante);
            q.setParameter("user", user);
            FacultadCumCarreraEstudiante p = (FacultadCumCarreraEstudiante) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteNuevaMatriculaFacultad(String idestudiante, String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarrera.facultadCum.facultad.authoritiesList cal WHERE cal.authoritiesPK.username=:user AND fcce.estudiante.estudianteId= :idestudiante and fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre= 'Nueva Matrícula'");
            q.setParameter("idestudiante", idestudiante);
            q.setParameter("user", user);
            FacultadCumCarreraEstudiante p = (FacultadCumCarreraEstudiante) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteEgresadoCum(String idestudiante, String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarrera.facultadCum.cum.authoritiesList cal WHERE cal.authoritiesPK.username=:user AND fcce.estudiante.estudianteId= :idestudiante and fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre= 'Egresado'");
            q.setParameter("idestudiante", idestudiante);
            q.setParameter("user", user);
            FacultadCumCarreraEstudiante p = (FacultadCumCarreraEstudiante) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteEgresadoFacultad(String idestudiante, String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarrera.facultadCum.facultad.authoritiesList cal WHERE cal.authoritiesPK.username=:user AND fcce.estudiante.estudianteId= :idestudiante and fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre= 'Egresado'");
            q.setParameter("idestudiante", idestudiante);
            q.setParameter("user", user);
            FacultadCumCarreraEstudiante p = (FacultadCumCarreraEstudiante) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteUltimaFecha(String idEstudiante) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce WHERE fcce.estudiante.estudianteId= :idestudiante ORDER BY fcce.facultadCumCarreraEstudiantePK.fechaMatricula DESC");
            q.setParameter("idestudiante", idEstudiante);
            List<FacultadCumCarreraEstudiante> p = q.getResultList();
            if (p.size() == 0) {
                return null;
            }
            return p.get(0);
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteUltimaFechaCum(String idEstudiante, String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarrera.facultadCum.cum.authoritiesList cal WHERE cal.authoritiesPK.username=:user AND fcce.estudiante.estudianteId= :idestudiante ORDER BY fcce.facultadCumCarreraEstudiantePK.fechaMatricula DESC");
            q.setParameter("idestudiante", idEstudiante);
            q.setParameter("user", user);
            List<FacultadCumCarreraEstudiante> p = q.getResultList();
            if (p.size() == 0) {
                return null;
            }
            return p.get(0);
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteUltimaFechaFacultad(String idEstudiante, String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarrera.facultadCum.facultad.authoritiesList cal WHERE cal.authoritiesPK.username=:user AND fcce.estudiante.estudianteId= :idestudiante ORDER BY fcce.facultadCumCarreraEstudiantePK.fechaMatricula DESC");
            q.setParameter("idestudiante", idEstudiante);
            q.setParameter("user", user);
            List<FacultadCumCarreraEstudiante> p = q.getResultList();
            if (p.size() == 0) {
                return null;
            }
            return p.get(0);
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<FacultadCumCarreraEstudiante> findFacultadCumCarreraEstudianteByCarrera(String facultad, String carrera, String planestudio) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce WHERE fcce.facultadCumCarrera.carrera.carreranacionalidcarreranacional.nombrecarreranacional =:carrera and fcce.facultadCumCarreraEstudiantePK.facultadCumCarrerafacultadCumfacultadcodigoarea=:facultad and fcce.planestudioidplanestudio.tipoplanestudionombretipoplanestudio.nombretipoplanestudio=:planestudio");
            q.setParameter("facultad", facultad);
            q.setParameter("carrera", carrera);
            q.setParameter("planestudio", planestudio);
            List<FacultadCumCarreraEstudiante> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteMatriculaPasiva(String idEstudiante) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce WHERE fcce.estudiante.estudianteId= :idestudiante and fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre= 'Matrícula Pasiva'");
            q.setParameter("idestudiante", idEstudiante);
            FacultadCumCarreraEstudiante p = (FacultadCumCarreraEstudiante) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteMatriculaPasivaCum(String idEstudiante, String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarrera.facultadCum.cum.authoritiesList cal WHERE cal.authoritiesPK.username=:user AND fcce.estudiante.estudianteId= :idestudiante and fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre= 'Matrícula Pasiva'");
            q.setParameter("idestudiante", idEstudiante);
            q.setParameter("user", user);
            FacultadCumCarreraEstudiante p = (FacultadCumCarreraEstudiante) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public FacultadCumCarreraEstudiante FindFacultadCumCarreraEstudianteByEstudianteMatriculaPasivaFacultad(String idEstudiante, String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarrera.facultadCum.facultad.authoritiesList cal WHERE cal.authoritiesPK.username=:user AND fcce.estudiante.estudianteId= :idestudiante and fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre= 'Matrícula Pasiva'");
            q.setParameter("idestudiante", idEstudiante);
            q.setParameter("user", user);
            FacultadCumCarreraEstudiante p = (FacultadCumCarreraEstudiante) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
