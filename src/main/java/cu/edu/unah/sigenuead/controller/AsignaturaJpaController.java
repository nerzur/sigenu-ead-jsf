/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;


import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author claudy
 */
public class AsignaturaJpaController implements Serializable {

    public AsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignatura asignatura) {
        if (asignatura.getAsignaturaAsignaturaList() == null) {
            asignatura.setAsignaturaAsignaturaList(new ArrayList<AsignaturaAsignatura>());
        }
        if (asignatura.getAsignaturaAsignaturaList1() == null) {
            asignatura.setAsignaturaAsignaturaList1(new ArrayList<AsignaturaAsignatura>());
        }
        if (asignatura.getFacultadCumCarreraEstudianteAsignaturaList() == null) {
            asignatura.setFacultadCumCarreraEstudianteAsignaturaList(new ArrayList<FacultadCumCarreraEstudianteAsignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DisciplinaPlanestudio disciplinaPlanestudio = asignatura.getDisciplinaPlanestudio();
            if (disciplinaPlanestudio != null) {
                disciplinaPlanestudio = em.getReference(disciplinaPlanestudio.getClass(), disciplinaPlanestudio.getDisciplinaPlanestudioPK());
                asignatura.setDisciplinaPlanestudio(disciplinaPlanestudio);
            }
            TipoAsignatura tipoAsignaturatipoAsignaturaId = asignatura.getTipoAsignaturatipoAsignaturaId();
            if (tipoAsignaturatipoAsignaturaId != null) {
                tipoAsignaturatipoAsignaturaId = em.getReference(tipoAsignaturatipoAsignaturaId.getClass(), tipoAsignaturatipoAsignaturaId.getTipoAsignaturaId());
                asignatura.setTipoAsignaturatipoAsignaturaId(tipoAsignaturatipoAsignaturaId);
            }
            TipoEvaluacion tipoEvaluaciontipoEvaluacionId = asignatura.getTipoEvaluaciontipoEvaluacionId();
            if (tipoEvaluaciontipoEvaluacionId != null) {
                tipoEvaluaciontipoEvaluacionId = em.getReference(tipoEvaluaciontipoEvaluacionId.getClass(), tipoEvaluaciontipoEvaluacionId.getTipoEvaluacionId());
                asignatura.setTipoEvaluaciontipoEvaluacionId(tipoEvaluaciontipoEvaluacionId);
            }
            List<AsignaturaAsignatura> attachedAsignaturaAsignaturaList = new ArrayList<AsignaturaAsignatura>();
            for (AsignaturaAsignatura asignaturaAsignaturaListAsignaturaAsignaturaToAttach : asignatura.getAsignaturaAsignaturaList()) {
                asignaturaAsignaturaListAsignaturaAsignaturaToAttach = em.getReference(asignaturaAsignaturaListAsignaturaAsignaturaToAttach.getClass(), asignaturaAsignaturaListAsignaturaAsignaturaToAttach.getAsignaturaAsignaturaPK());
                attachedAsignaturaAsignaturaList.add(asignaturaAsignaturaListAsignaturaAsignaturaToAttach);
            }
            asignatura.setAsignaturaAsignaturaList(attachedAsignaturaAsignaturaList);
            List<AsignaturaAsignatura> attachedAsignaturaAsignaturaList1 = new ArrayList<AsignaturaAsignatura>();
            for (AsignaturaAsignatura asignaturaAsignaturaList1AsignaturaAsignaturaToAttach : asignatura.getAsignaturaAsignaturaList1()) {
                asignaturaAsignaturaList1AsignaturaAsignaturaToAttach = em.getReference(asignaturaAsignaturaList1AsignaturaAsignaturaToAttach.getClass(), asignaturaAsignaturaList1AsignaturaAsignaturaToAttach.getAsignaturaAsignaturaPK());
                attachedAsignaturaAsignaturaList1.add(asignaturaAsignaturaList1AsignaturaAsignaturaToAttach);
            }
            asignatura.setAsignaturaAsignaturaList1(attachedAsignaturaAsignaturaList1);
            List<FacultadCumCarreraEstudianteAsignatura> attachedFacultadCumCarreraEstudianteAsignaturaList = new ArrayList<FacultadCumCarreraEstudianteAsignatura>();
            for (FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignaturaToAttach : asignatura.getFacultadCumCarreraEstudianteAsignaturaList()) {
                facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignaturaToAttach = em.getReference(facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignaturaToAttach.getClass(), facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignaturaToAttach.getFacultadCumCarreraEstudianteAsignaturaPK());
                attachedFacultadCumCarreraEstudianteAsignaturaList.add(facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignaturaToAttach);
            }
            asignatura.setFacultadCumCarreraEstudianteAsignaturaList(attachedFacultadCumCarreraEstudianteAsignaturaList);
            em.persist(asignatura);
            if (disciplinaPlanestudio != null) {
                disciplinaPlanestudio.getAsignaturaList().add(asignatura);
                disciplinaPlanestudio = em.merge(disciplinaPlanestudio);
            }
            if (tipoAsignaturatipoAsignaturaId != null) {
                tipoAsignaturatipoAsignaturaId.getAsignaturaList().add(asignatura);
                tipoAsignaturatipoAsignaturaId = em.merge(tipoAsignaturatipoAsignaturaId);
            }
            if (tipoEvaluaciontipoEvaluacionId != null) {
                tipoEvaluaciontipoEvaluacionId.getAsignaturaList().add(asignatura);
                tipoEvaluaciontipoEvaluacionId = em.merge(tipoEvaluaciontipoEvaluacionId);
            }
            for (AsignaturaAsignatura asignaturaAsignaturaListAsignaturaAsignatura : asignatura.getAsignaturaAsignaturaList()) {
                Asignatura oldAsignaturaOfAsignaturaAsignaturaListAsignaturaAsignatura = asignaturaAsignaturaListAsignaturaAsignatura.getAsignatura();
                asignaturaAsignaturaListAsignaturaAsignatura.setAsignatura(asignatura);
                asignaturaAsignaturaListAsignaturaAsignatura = em.merge(asignaturaAsignaturaListAsignaturaAsignatura);
                if (oldAsignaturaOfAsignaturaAsignaturaListAsignaturaAsignatura != null) {
                    oldAsignaturaOfAsignaturaAsignaturaListAsignaturaAsignatura.getAsignaturaAsignaturaList().remove(asignaturaAsignaturaListAsignaturaAsignatura);
                    oldAsignaturaOfAsignaturaAsignaturaListAsignaturaAsignatura = em.merge(oldAsignaturaOfAsignaturaAsignaturaListAsignaturaAsignatura);
                }
            }
            for (AsignaturaAsignatura asignaturaAsignaturaList1AsignaturaAsignatura : asignatura.getAsignaturaAsignaturaList1()) {
                Asignatura oldAsignatura1OfAsignaturaAsignaturaList1AsignaturaAsignatura = asignaturaAsignaturaList1AsignaturaAsignatura.getAsignatura1();
                asignaturaAsignaturaList1AsignaturaAsignatura.setAsignatura1(asignatura);
                asignaturaAsignaturaList1AsignaturaAsignatura = em.merge(asignaturaAsignaturaList1AsignaturaAsignatura);
                if (oldAsignatura1OfAsignaturaAsignaturaList1AsignaturaAsignatura != null) {
                    oldAsignatura1OfAsignaturaAsignaturaList1AsignaturaAsignatura.getAsignaturaAsignaturaList1().remove(asignaturaAsignaturaList1AsignaturaAsignatura);
                    oldAsignatura1OfAsignaturaAsignaturaList1AsignaturaAsignatura = em.merge(oldAsignatura1OfAsignaturaAsignaturaList1AsignaturaAsignatura);
                }
            }
            for (FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura : asignatura.getFacultadCumCarreraEstudianteAsignaturaList()) {
                Asignatura oldAsignaturaOfFacultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura = facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura.getAsignatura();
                facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura.setAsignatura(asignatura);
                facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura = em.merge(facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura);
                if (oldAsignaturaOfFacultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura != null) {
                    oldAsignaturaOfFacultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaList().remove(facultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura);
                    oldAsignaturaOfFacultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura = em.merge(oldAsignaturaOfFacultadCumCarreraEstudianteAsignaturaListFacultadCumCarreraEstudianteAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignatura asignatura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura persistentAsignatura = em.find(Asignatura.class, asignatura.getAsignaturaId());
            DisciplinaPlanestudio disciplinaPlanestudioOld = persistentAsignatura.getDisciplinaPlanestudio();
            DisciplinaPlanestudio disciplinaPlanestudioNew = asignatura.getDisciplinaPlanestudio();
            TipoAsignatura tipoAsignaturatipoAsignaturaIdOld = persistentAsignatura.getTipoAsignaturatipoAsignaturaId();
            TipoAsignatura tipoAsignaturatipoAsignaturaIdNew = asignatura.getTipoAsignaturatipoAsignaturaId();
            TipoEvaluacion tipoEvaluaciontipoEvaluacionIdOld = persistentAsignatura.getTipoEvaluaciontipoEvaluacionId();
            TipoEvaluacion tipoEvaluaciontipoEvaluacionIdNew = asignatura.getTipoEvaluaciontipoEvaluacionId();
            List<AsignaturaAsignatura> asignaturaAsignaturaListOld = persistentAsignatura.getAsignaturaAsignaturaList();
            List<AsignaturaAsignatura> asignaturaAsignaturaListNew = asignatura.getAsignaturaAsignaturaList();
            List<AsignaturaAsignatura> asignaturaAsignaturaList1Old = persistentAsignatura.getAsignaturaAsignaturaList1();
            List<AsignaturaAsignatura> asignaturaAsignaturaList1New = asignatura.getAsignaturaAsignaturaList1();
            List<FacultadCumCarreraEstudianteAsignatura> facultadCumCarreraEstudianteAsignaturaListOld = persistentAsignatura.getFacultadCumCarreraEstudianteAsignaturaList();
            List<FacultadCumCarreraEstudianteAsignatura> facultadCumCarreraEstudianteAsignaturaListNew = asignatura.getFacultadCumCarreraEstudianteAsignaturaList();
            List<String> illegalOrphanMessages = null;
            for (AsignaturaAsignatura asignaturaAsignaturaListOldAsignaturaAsignatura : asignaturaAsignaturaListOld) {
                if (!asignaturaAsignaturaListNew.contains(asignaturaAsignaturaListOldAsignaturaAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AsignaturaAsignatura " + asignaturaAsignaturaListOldAsignaturaAsignatura + " since its asignatura field is not nullable.");
                }
            }
            for (AsignaturaAsignatura asignaturaAsignaturaList1OldAsignaturaAsignatura : asignaturaAsignaturaList1Old) {
                if (!asignaturaAsignaturaList1New.contains(asignaturaAsignaturaList1OldAsignaturaAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AsignaturaAsignatura " + asignaturaAsignaturaList1OldAsignaturaAsignatura + " since its asignatura1 field is not nullable.");
                }
            }
            for (FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaListOldFacultadCumCarreraEstudianteAsignatura : facultadCumCarreraEstudianteAsignaturaListOld) {
                if (!facultadCumCarreraEstudianteAsignaturaListNew.contains(facultadCumCarreraEstudianteAsignaturaListOldFacultadCumCarreraEstudianteAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FacultadCumCarreraEstudianteAsignatura " + facultadCumCarreraEstudianteAsignaturaListOldFacultadCumCarreraEstudianteAsignatura + " since its asignatura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (disciplinaPlanestudioNew != null) {
                disciplinaPlanestudioNew = em.getReference(disciplinaPlanestudioNew.getClass(), disciplinaPlanestudioNew.getDisciplinaPlanestudioPK());
                asignatura.setDisciplinaPlanestudio(disciplinaPlanestudioNew);
            }
            if (tipoAsignaturatipoAsignaturaIdNew != null) {
                tipoAsignaturatipoAsignaturaIdNew = em.getReference(tipoAsignaturatipoAsignaturaIdNew.getClass(), tipoAsignaturatipoAsignaturaIdNew.getTipoAsignaturaId());
                asignatura.setTipoAsignaturatipoAsignaturaId(tipoAsignaturatipoAsignaturaIdNew);
            }
            if (tipoEvaluaciontipoEvaluacionIdNew != null) {
                tipoEvaluaciontipoEvaluacionIdNew = em.getReference(tipoEvaluaciontipoEvaluacionIdNew.getClass(), tipoEvaluaciontipoEvaluacionIdNew.getTipoEvaluacionId());
                asignatura.setTipoEvaluaciontipoEvaluacionId(tipoEvaluaciontipoEvaluacionIdNew);
            }
            List<AsignaturaAsignatura> attachedAsignaturaAsignaturaListNew = new ArrayList<AsignaturaAsignatura>();
            for (AsignaturaAsignatura asignaturaAsignaturaListNewAsignaturaAsignaturaToAttach : asignaturaAsignaturaListNew) {
                asignaturaAsignaturaListNewAsignaturaAsignaturaToAttach = em.getReference(asignaturaAsignaturaListNewAsignaturaAsignaturaToAttach.getClass(), asignaturaAsignaturaListNewAsignaturaAsignaturaToAttach.getAsignaturaAsignaturaPK());
                attachedAsignaturaAsignaturaListNew.add(asignaturaAsignaturaListNewAsignaturaAsignaturaToAttach);
            }
            asignaturaAsignaturaListNew = attachedAsignaturaAsignaturaListNew;
            asignatura.setAsignaturaAsignaturaList(asignaturaAsignaturaListNew);
            List<AsignaturaAsignatura> attachedAsignaturaAsignaturaList1New = new ArrayList<AsignaturaAsignatura>();
            for (AsignaturaAsignatura asignaturaAsignaturaList1NewAsignaturaAsignaturaToAttach : asignaturaAsignaturaList1New) {
                asignaturaAsignaturaList1NewAsignaturaAsignaturaToAttach = em.getReference(asignaturaAsignaturaList1NewAsignaturaAsignaturaToAttach.getClass(), asignaturaAsignaturaList1NewAsignaturaAsignaturaToAttach.getAsignaturaAsignaturaPK());
                attachedAsignaturaAsignaturaList1New.add(asignaturaAsignaturaList1NewAsignaturaAsignaturaToAttach);
            }
            asignaturaAsignaturaList1New = attachedAsignaturaAsignaturaList1New;
            asignatura.setAsignaturaAsignaturaList1(asignaturaAsignaturaList1New);
            List<FacultadCumCarreraEstudianteAsignatura> attachedFacultadCumCarreraEstudianteAsignaturaListNew = new ArrayList<FacultadCumCarreraEstudianteAsignatura>();
            for (FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignaturaToAttach : facultadCumCarreraEstudianteAsignaturaListNew) {
                facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignaturaToAttach = em.getReference(facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignaturaToAttach.getClass(), facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignaturaToAttach.getFacultadCumCarreraEstudianteAsignaturaPK());
                attachedFacultadCumCarreraEstudianteAsignaturaListNew.add(facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignaturaToAttach);
            }
            facultadCumCarreraEstudianteAsignaturaListNew = attachedFacultadCumCarreraEstudianteAsignaturaListNew;
            asignatura.setFacultadCumCarreraEstudianteAsignaturaList(facultadCumCarreraEstudianteAsignaturaListNew);
            asignatura = em.merge(asignatura);
            if (disciplinaPlanestudioOld != null && !disciplinaPlanestudioOld.equals(disciplinaPlanestudioNew)) {
                disciplinaPlanestudioOld.getAsignaturaList().remove(asignatura);
                disciplinaPlanestudioOld = em.merge(disciplinaPlanestudioOld);
            }
            if (disciplinaPlanestudioNew != null && !disciplinaPlanestudioNew.equals(disciplinaPlanestudioOld)) {
                disciplinaPlanestudioNew.getAsignaturaList().add(asignatura);
                disciplinaPlanestudioNew = em.merge(disciplinaPlanestudioNew);
            }
            if (tipoAsignaturatipoAsignaturaIdOld != null && !tipoAsignaturatipoAsignaturaIdOld.equals(tipoAsignaturatipoAsignaturaIdNew)) {
                tipoAsignaturatipoAsignaturaIdOld.getAsignaturaList().remove(asignatura);
                tipoAsignaturatipoAsignaturaIdOld = em.merge(tipoAsignaturatipoAsignaturaIdOld);
            }
            if (tipoAsignaturatipoAsignaturaIdNew != null && !tipoAsignaturatipoAsignaturaIdNew.equals(tipoAsignaturatipoAsignaturaIdOld)) {
                tipoAsignaturatipoAsignaturaIdNew.getAsignaturaList().add(asignatura);
                tipoAsignaturatipoAsignaturaIdNew = em.merge(tipoAsignaturatipoAsignaturaIdNew);
            }
            if (tipoEvaluaciontipoEvaluacionIdOld != null && !tipoEvaluaciontipoEvaluacionIdOld.equals(tipoEvaluaciontipoEvaluacionIdNew)) {
                tipoEvaluaciontipoEvaluacionIdOld.getAsignaturaList().remove(asignatura);
                tipoEvaluaciontipoEvaluacionIdOld = em.merge(tipoEvaluaciontipoEvaluacionIdOld);
            }
            if (tipoEvaluaciontipoEvaluacionIdNew != null && !tipoEvaluaciontipoEvaluacionIdNew.equals(tipoEvaluaciontipoEvaluacionIdOld)) {
                tipoEvaluaciontipoEvaluacionIdNew.getAsignaturaList().add(asignatura);
                tipoEvaluaciontipoEvaluacionIdNew = em.merge(tipoEvaluaciontipoEvaluacionIdNew);
            }
            for (AsignaturaAsignatura asignaturaAsignaturaListNewAsignaturaAsignatura : asignaturaAsignaturaListNew) {
                if (!asignaturaAsignaturaListOld.contains(asignaturaAsignaturaListNewAsignaturaAsignatura)) {
                    Asignatura oldAsignaturaOfAsignaturaAsignaturaListNewAsignaturaAsignatura = asignaturaAsignaturaListNewAsignaturaAsignatura.getAsignatura();
                    asignaturaAsignaturaListNewAsignaturaAsignatura.setAsignatura(asignatura);
                    asignaturaAsignaturaListNewAsignaturaAsignatura = em.merge(asignaturaAsignaturaListNewAsignaturaAsignatura);
                    if (oldAsignaturaOfAsignaturaAsignaturaListNewAsignaturaAsignatura != null && !oldAsignaturaOfAsignaturaAsignaturaListNewAsignaturaAsignatura.equals(asignatura)) {
                        oldAsignaturaOfAsignaturaAsignaturaListNewAsignaturaAsignatura.getAsignaturaAsignaturaList().remove(asignaturaAsignaturaListNewAsignaturaAsignatura);
                        oldAsignaturaOfAsignaturaAsignaturaListNewAsignaturaAsignatura = em.merge(oldAsignaturaOfAsignaturaAsignaturaListNewAsignaturaAsignatura);
                    }
                }
            }
            for (AsignaturaAsignatura asignaturaAsignaturaList1NewAsignaturaAsignatura : asignaturaAsignaturaList1New) {
                if (!asignaturaAsignaturaList1Old.contains(asignaturaAsignaturaList1NewAsignaturaAsignatura)) {
                    Asignatura oldAsignatura1OfAsignaturaAsignaturaList1NewAsignaturaAsignatura = asignaturaAsignaturaList1NewAsignaturaAsignatura.getAsignatura1();
                    asignaturaAsignaturaList1NewAsignaturaAsignatura.setAsignatura1(asignatura);
                    asignaturaAsignaturaList1NewAsignaturaAsignatura = em.merge(asignaturaAsignaturaList1NewAsignaturaAsignatura);
                    if (oldAsignatura1OfAsignaturaAsignaturaList1NewAsignaturaAsignatura != null && !oldAsignatura1OfAsignaturaAsignaturaList1NewAsignaturaAsignatura.equals(asignatura)) {
                        oldAsignatura1OfAsignaturaAsignaturaList1NewAsignaturaAsignatura.getAsignaturaAsignaturaList1().remove(asignaturaAsignaturaList1NewAsignaturaAsignatura);
                        oldAsignatura1OfAsignaturaAsignaturaList1NewAsignaturaAsignatura = em.merge(oldAsignatura1OfAsignaturaAsignaturaList1NewAsignaturaAsignatura);
                    }
                }
            }
            for (FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura : facultadCumCarreraEstudianteAsignaturaListNew) {
                if (!facultadCumCarreraEstudianteAsignaturaListOld.contains(facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura)) {
                    Asignatura oldAsignaturaOfFacultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura = facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura.getAsignatura();
                    facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura.setAsignatura(asignatura);
                    facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura = em.merge(facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura);
                    if (oldAsignaturaOfFacultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura != null && !oldAsignaturaOfFacultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura.equals(asignatura)) {
                        oldAsignaturaOfFacultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaList().remove(facultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura);
                        oldAsignaturaOfFacultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura = em.merge(oldAsignaturaOfFacultadCumCarreraEstudianteAsignaturaListNewFacultadCumCarreraEstudianteAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignatura.getAsignaturaId();
                if (findAsignatura(id) == null) {
                    throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura asignatura;
            try {
                asignatura = em.getReference(Asignatura.class, id);
                asignatura.getAsignaturaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AsignaturaAsignatura> asignaturaAsignaturaListOrphanCheck = asignatura.getAsignaturaAsignaturaList();
            for (AsignaturaAsignatura asignaturaAsignaturaListOrphanCheckAsignaturaAsignatura : asignaturaAsignaturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asignatura (" + asignatura + ") cannot be destroyed since the AsignaturaAsignatura " + asignaturaAsignaturaListOrphanCheckAsignaturaAsignatura + " in its asignaturaAsignaturaList field has a non-nullable asignatura field.");
            }
            List<AsignaturaAsignatura> asignaturaAsignaturaList1OrphanCheck = asignatura.getAsignaturaAsignaturaList1();
            for (AsignaturaAsignatura asignaturaAsignaturaList1OrphanCheckAsignaturaAsignatura : asignaturaAsignaturaList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asignatura (" + asignatura + ") cannot be destroyed since the AsignaturaAsignatura " + asignaturaAsignaturaList1OrphanCheckAsignaturaAsignatura + " in its asignaturaAsignaturaList1 field has a non-nullable asignatura1 field.");
            }
            List<FacultadCumCarreraEstudianteAsignatura> facultadCumCarreraEstudianteAsignaturaListOrphanCheck = asignatura.getFacultadCumCarreraEstudianteAsignaturaList();
            for (FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaListOrphanCheckFacultadCumCarreraEstudianteAsignatura : facultadCumCarreraEstudianteAsignaturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asignatura (" + asignatura + ") cannot be destroyed since the FacultadCumCarreraEstudianteAsignatura " + facultadCumCarreraEstudianteAsignaturaListOrphanCheckFacultadCumCarreraEstudianteAsignatura + " in its facultadCumCarreraEstudianteAsignaturaList field has a non-nullable asignatura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            DisciplinaPlanestudio disciplinaPlanestudio = asignatura.getDisciplinaPlanestudio();
            if (disciplinaPlanestudio != null) {
                disciplinaPlanestudio.getAsignaturaList().remove(asignatura);
                disciplinaPlanestudio = em.merge(disciplinaPlanestudio);
            }
            TipoAsignatura tipoAsignaturatipoAsignaturaId = asignatura.getTipoAsignaturatipoAsignaturaId();
            if (tipoAsignaturatipoAsignaturaId != null) {
                tipoAsignaturatipoAsignaturaId.getAsignaturaList().remove(asignatura);
                tipoAsignaturatipoAsignaturaId = em.merge(tipoAsignaturatipoAsignaturaId);
            }
            TipoEvaluacion tipoEvaluaciontipoEvaluacionId = asignatura.getTipoEvaluaciontipoEvaluacionId();
            if (tipoEvaluaciontipoEvaluacionId != null) {
                tipoEvaluaciontipoEvaluacionId.getAsignaturaList().remove(asignatura);
                tipoEvaluaciontipoEvaluacionId = em.merge(tipoEvaluaciontipoEvaluacionId);
            }
            em.remove(asignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignatura> findAsignaturaEntities() {
        return findAsignaturaEntities(true, -1, -1);
    }

    public List<Asignatura> findAsignaturaEntities(int maxResults, int firstResult) {
        return findAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<Asignatura> findAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asignatura.class));
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

    public Asignatura findAsignatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asignatura> rt = cq.from(Asignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Asignatura findAsignaturaByCodigo(String codigo) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a FROM Asignatura a WHERE a.asignaturaCodigo= :codigo");
            q.setParameter("codigo", codigo);
            Asignatura p = (Asignatura) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findAsignaturaAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a.asignaturaNombre FROM Asignatura a WHERE a.asignaturaCancelada=false ORDER BY a.asignaturaNombre");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findAsignaturaAvailableByDisciplina(String facultad, String carrera, String planEstudio, String disciplina) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a.asignaturaNombre FROM Asignatura a WHERE a.asignaturaCancelada=false AND a.disciplinaPlanestudio.cancelado=false AND a.disciplinaPlanestudio.disciplina.disciplinaNombre= :disciplina AND a.disciplinaPlanestudio.planestudio.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional= :carrera AND a.disciplinaPlanestudio.planestudio.carreraidcarrera.facultadcodigoarea.nombrearea= :facultad AND a.disciplinaPlanestudio.planestudio.tipoplanestudionombretipoplanestudio.nombretipoplanestudio= :planestudio ORDER BY a.asignaturaNombre");
            q.setParameter("disciplina", disciplina);
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("planestudio", planEstudio);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Asignatura findAsignaturaByNombre(String asignatura, String facultad, String carrera, String planEstudio) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a "
                    + "FROM Asignatura a "
                    + "WHERE a.asignaturaNombre= :nombre AND "
                    + "a.disciplinaPlanestudio.planestudio.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional= :carrera AND "
                    + "a.disciplinaPlanestudio.planestudio.carreraidcarrera.facultadcodigoarea.nombrearea= :facultad AND "
                    + "a.disciplinaPlanestudio.planestudio.tipoplanestudionombretipoplanestudio.nombretipoplanestudio= :planestudio");
            q.setParameter("nombre", asignatura);
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("planestudio", planEstudio);
            Asignatura p = (Asignatura) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Asignatura findAsignaturaByNombreAvailable(String asignatura, String facultad, String carrera, String planEstudio) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a FROM Asignatura a WHERE a.asignaturaCancelada=false AND a.disciplinaPlanestudio.cancelado=false AND a.asignaturaNombre=:nombre AND a.disciplinaPlanestudio.planestudio.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera AND a.disciplinaPlanestudio.planestudio.carreraidcarrera.facultadcodigoarea.nombrearea=:facultad AND a.disciplinaPlanestudio.planestudio.tipoplanestudionombretipoplanestudio.nombretipoplanestudio=:planEstudio");
            q.setParameter("nombre", asignatura);
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("planEstudio", planEstudio);
            Asignatura p = (Asignatura) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Asignatura> findAsignaturaByPlanEstudio(String facultad, String carrera, String planEstudio) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a FROM Asignatura a WHERE a.asignaturaCancelada=false AND a.disciplinaPlanestudio.cancelado=false AND a.disciplinaPlanestudio.planestudio.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional= :carrera AND a.disciplinaPlanestudio.planestudio.carreraidcarrera.facultadcodigoarea.nombrearea= :facultad AND a.disciplinaPlanestudio.planestudio.tipoplanestudionombretipoplanestudio.nombretipoplanestudio= :planEstudio");
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("planEstudio", planEstudio);
            List<Asignatura> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    //*************OJO********************
    public List<Asignatura> findAsignaturaNoAprobadas(String estudiante, String facultad, int carrera, String planEstudio) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fccea.asignatura FROM Estudiante e JOIN FETCH e.facultadCumCarreraEstudianteList fcce JOIN FETCH fcce.facultadCumCarreraEstudianteAsignaturaList fccea WHERE fccea.aprobada=FALSE");
            List<Asignatura> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
//no se que hace pero funciona

    public List<Asignatura> findAsignaturaPorMatricular(String estudiante, String facultad, int carrera, String planEstudio) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT a FROM Asignatura a JOIN FETCH a.facultadCumCarreraEstudianteAsignaturaList ae WHERE a.disciplinaPlanestudio.planestudio.carreraidcarrera.idcarrera= :carrera AND a.disciplinaPlanestudio.planestudio.tipoplanestudionombretipoplanestudio.nombretipoplanestudio= :tipo AND  ae.facultadCumCarreraEstudiante.facultadCumCarreraEstudiantePK.estudianteestudianteId NOT LIKE :id");
            q.setParameter("id", estudiante);
            q.setParameter("carrera", carrera);
            q.setParameter("tipo", planEstudio);
            List<Asignatura> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

//    //*************OJO********************
    public long findAsignaturaPorMatricularCount(String estudiante, String facultad, int carrera, String cum, Date fecha) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT COUNT (a) FROM Asignatura a JOIN FETCH a.facultadCumCarreraEstudianteAsignaturaList fccea WHERE fccea.asignatura.asignaturaCancelada=false AND fccea.asignatura.disciplinaPlanestudio.cancelado=false AND fccea.facultadCumCarreraEstudiante.facultadCumCarreraEstudiantePK.fechaMatricula=:fecha AND fccea.facultadCumCarreraEstudianteAsignaturaPK.idcarrera=:carrera and fccea.facultadCumCarreraEstudianteAsignaturaPK.codigoarea =:facultad and fccea.facultadCumCarreraEstudianteAsignaturaPK.codigocum =:cum and fccea.facultadCumCarreraEstudianteAsignaturaPK.estudianteId=:estudiante and fccea.aprobada=FALSE");
            q.setParameter("estudiante", estudiante);
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("cum", cum);
            q.setParameter("fecha", fecha);
            long p = (Long) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return 0;
        } finally {
            em.close();
        }
    }

    public long findAsignaturaMatriculadas(String estudiante, String facultad, int carrera, String cum, Date fecha) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT COUNT (a) FROM Asignatura a JOIN FETCH a.facultadCumCarreraEstudianteAsignaturaList fccea WHERE fccea.asignatura.asignaturaCancelada=false AND fccea.facultadCumCarreraEstudiante.facultadCumCarreraEstudiantePK.fechaMatricula=:fecha AND fccea.asignatura.disciplinaPlanestudio.cancelado=false AND fccea.facultadCumCarreraEstudianteAsignaturaPK.idcarrera=:carrera and fccea.facultadCumCarreraEstudianteAsignaturaPK.codigoarea =:facultad and fccea.facultadCumCarreraEstudianteAsignaturaPK.codigocum =:cum and fccea.facultadCumCarreraEstudianteAsignaturaPK.estudianteId=:estudiante and fccea.aprobada=TRUE");
            q.setParameter("estudiante", estudiante);
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("cum", cum);
            q.setParameter("fecha", fecha);
            long p = (Long) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return 0;
        } finally {
            em.close();
        }
    }
//
//    //*************OJO********************

//    public int findAsignaturaMatriculadas(String estudiante, String facultad, int carrera, String planEstudio) {
//        EntityManager em = getEntityManager();
//        try {
//            Query q = em.createQuery("SELECT COUNT (DISTINCT a) FROM Asignatura a JOIN FETCH a.facultadCumCarreraEstudianteAsignaturaList fccea WHERE fccea.asignatura.asignaturaCancelada=false AND fccea.asignatura.disciplinaPlanestudio.cancelado=false AND fccea.facultadCumCarreraEstudianteAsignaturaPK.idcarrera=:carrera and fccea.facultadCumCarreraEstudianteAsignaturaPK.codigoarea =:facultad and fccea.facultadCumCarreraEstudiante.planestudioidplanestudio.tipoplanestudionombretipoplanestudio.nombretipoplanestudio = :planEstudio and fccea.aprobada=TRUE");
//            q.setParameter("estudiante", estudiante);
//            q.setParameter("carrera", carrera);
//            q.setParameter("facultad", facultad);
//            q.setParameter("planEstudio", planEstudio);
//            Integer p = (Integer) q.getSingleResult();
//            return p;
//        } catch (Exception e) {
//            return 0;
//        } finally {
//            em.close();
//        }
//    }
    public List<Asignatura> findAsignaturaMatriculadasByEstudiante(String estudiante, String facultad, int carrera, int matricula, String cum) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT a FROM Asignatura a JOIN FETCH a.facultadCumCarreraEstudianteAsignaturaList fccea JOIN FETCH fccea.matriculaFacultadCumCarreraEstudianteAsignaturaList mfccea WHERE a.asignaturaCancelada=false and a.disciplinaPlanestudio.cancelado=false and mfccea.cancelada=false and mfccea.matriculaFacultadCumCarreraEstudianteAsignaturaPK.codigoarea=:facultad and mfccea.matriculaFacultadCumCarreraEstudianteAsignaturaPK.codigocum=:cum and mfccea.matriculaFacultadCumCarreraEstudianteAsignaturaPK.estudianteId=:estudiante and mfccea.matriculaFacultadCumCarreraEstudianteAsignaturaPK.idcarrera=:carrera AND mfccea.matriculaFacultadCumCarreraEstudianteAsignaturaPK.matriculamatriculaId=:matricula ORDER BY a.asignaturaNombre");
            q.setParameter("estudiante", estudiante);
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("cum", cum);
            q.setParameter("matricula", matricula);
            List<Asignatura> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
//    public List<Asignatura> findAsignaturaBasicaMatriculadasByEstudiante(String estudiante, String facultad, int carrera, int matricula, String cum) {
//        EntityManager em = getEntityManager();
//        try {
//            Query q = em.createQuery("SELECT DISTINCT fccea.asignatura FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarreraEstudianteAsignaturaList fccea WHERE fccea.aprobada=FALSE AND fccea.asignatura.asignaturaCancelada=false And fccea.asignatura.disciplinaPlanestudio.cancelado=false AND fcce.facultadCumCarreraEstudiantePK.facultadCumCarreracarreraidcarrera =:carrera AND fcce.facultadCumCarreraEstudiantePK.facultadCumCarrerafacultadCumfacultadcodigoarea =:facultad AND fcce.facultadCumCarreraEstudiantePK.facultadCumCarrerafacultadCumcumcodigocum =:cum AND fcce.facultadCumCarreraEstudiantePK.estudianteestudianteId =:estudiante AND fcce.facultadCumCarreraEstudiantePK.fechaMatricula=:fecha");
//            q.setParameter("estudiante", estudiante);
//            q.setParameter("carrera", carrera);
//            q.setParameter("facultad", facultad);
//            q.setParameter("cum", cum);
//            q.setParameter("matricula", matricula);
//            List<Asignatura> p = q.getResultList();
//            return p;
//        } catch (Exception e) {
//            return null;
//        } finally {
//            em.close();
//        }
//    }
//    public List<Asignatura> findAsignaturaBasicaMatriculadasByEstudiante(String estudiante, String facultad, int carrera, int matricula, String cum) {
//        EntityManager em = getEntityManager();
//        try {
//            Query q = em.createQuery("SELECT DISTINCT a FROM Asignatura a JOIN FETCH a.facultadCumCarreraEstudianteAsignaturaList fccea JOIN FETCH fccea.matriculaFacultadCumCarreraEstudianteAsignaturaList mfccea WHERE a.asignaturaCancelada=false and a.disciplinaPlanestudio.cancelado=false and mfccea.cancelada=false and mfccea.matriculaFacultadCumCarreraEstudianteAsignaturaPK.codigoarea=:facultad and mfccea.matriculaFacultadCumCarreraEstudianteAsignaturaPK.codigocum=:cum and mfccea.matriculaFacultadCumCarreraEstudianteAsignaturaPK.estudianteId=:estudiante and mfccea.matriculaFacultadCumCarreraEstudianteAsignaturaPK.idcarrera=:carrera AND mfccea.matriculaFacultadCumCarreraEstudianteAsignaturaPK.matriculamatriculaId=:matricula ORDER BY a.asignaturaNombre");
//            q.setParameter("estudiante", estudiante);
//            q.setParameter("carrera", carrera);
//            q.setParameter("facultad", facultad);
//            q.setParameter("cum", cum);
//            q.setParameter("matricula", matricula);
//            List<Asignatura> p = q.getResultList();
//            return p;
//        } catch (Exception e) {
//            return null;
//        } finally {
//            em.close();
//        }
//    }
//
//    //*************OJO********************

    public List<String> findAsignaturaPrecedenteByCodigo(String codigo) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a.asignaturaCodigo FROM Asignatura a JOIN FETCH a.asignaturaAsignaturaList prec WHERE prec.asignatura1.asignaturaCodigo= :asignatura AND prec.cancelado=false ORDER BY a.asignaturaNombre");
            q.setParameter("asignatura", codigo);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
//    public List<String> findAsignaturaPrecedente(String facultad, String carrera, String planEstudio, String disciplina, String codigo) {
//        EntityManager em = getEntityManager();
//        try {
//            Query q = em.createQuery("SELECT a.asignaturaNombre FROM Asignatura a JOIN FETCH a.asignaturaAsignaturaList prec WHERE prec.asignatura1.asignaturaNombre= :asignatura AND prec.cancelado=false ORDER BY a.asignaturaNombre");
//            q.setParameter("asignatura", codigo);
//            List<String> p = q.getResultList();
//            return p;
//        } catch (Exception e) {
//            return null;
//        } finally {
//            em.close();
//        }
//    }

    public List<Asignatura> findAsignaturaBasicasFaltantesByEstudiante(String facultad, String carrera, String planEstudio, String estudiante) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT a FROM Asignatura a JOIN FETCH a.asignaturaEstudianteList prec WHERE a.tipoAsignaturatipoAsignaturaId.tipoAsignaturaNombre='Basica' AND a.disciplinaPlanestudio.planestudio.carreraidcarrera.facultadcodigoarea.nombrearea=:facultad AND a.disciplinaPlanestudio.planestudio.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera AND a.disciplinaPlanestudio.planestudio.tipoplanestudionombretipoplanestudio.tipoplanestudiocancelado=:planestudio AND (prec.estudiante.estudianteId NOT LIKE :estudiante OR (prec.estudiante.estudianteId NOT LIKE :estudiante AND prec.aprobada=false)");
            q.setParameter("facultad", facultad);
            q.setParameter("carrera", carrera);
            q.setParameter("planestudio", planEstudio);
            q.setParameter("estudiante", estudiante);
            List<Asignatura> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Asignatura> findAsignaturaEntityByDisciplina(String facultad, String carrera, String planEstudio, String disciplina) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a "
                    + "FROM Asignatura a "
                    + "WHERE a.disciplinaPlanestudio.disciplina.disciplinaNombre= :disciplina AND "
                    + "a.disciplinaPlanestudio.planestudio.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional= :carrera AND "
                    + "a.disciplinaPlanestudio.planestudio.carreraidcarrera.facultadcodigoarea.nombrearea= :facultad AND "
                    + "a.disciplinaPlanestudio.planestudio.tipoplanestudionombretipoplanestudio.nombretipoplanestudio= :planestudio "
                    + "ORDER BY a.asignaturaNombre");
            q.setParameter("disciplina", disciplina);
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("planestudio", planEstudio);
            List<Asignatura> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Asignatura> findAsignaturaNoAprobadas(FacultadCumCarreraEstudiante fcce, int matricula) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fccea.asignatura FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarreraEstudianteAsignaturaList fccea WHERE fccea.aprobada=FALSE AND fccea.asignatura.asignaturaCancelada=false And fccea.asignatura.disciplinaPlanestudio.cancelado=false AND fcce.facultadCumCarreraEstudiantePK.facultadCumCarreracarreraidcarrera =:carrera AND fcce.facultadCumCarreraEstudiantePK.facultadCumCarrerafacultadCumfacultadcodigoarea =:facultad AND fcce.facultadCumCarreraEstudiantePK.facultadCumCarrerafacultadCumcumcodigocum =:cum AND fcce.facultadCumCarreraEstudiantePK.estudianteestudianteId =:estudiante AND fcce.facultadCumCarreraEstudiantePK.fechaMatricula=:fecha");
            q.setParameter("carrera", fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera());
            q.setParameter("facultad", fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea());
            q.setParameter("cum", fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum());
            q.setParameter("estudiante", fcce.getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId());
            q.setParameter("fecha", fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula());
            List<Asignatura> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Asignatura findAsignaturaByCodigoAvailable(String codigo) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a FROM Asignatura a WHERE a.asignaturaCodigo= :codigo AND a.asignaturaCancelada=false AND a.disciplinaPlanestudio.cancelado=false");
            q.setParameter("codigo", codigo);
            Asignatura p = (Asignatura) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Asignatura> findAsignaturaBasicasFaltantesByEstudiante(FacultadCumCarreraEstudiante fcce) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT a FROM Asignatura a JOIN FETCH a.facultadCumCarreraEstudianteAsignaturaList fccea WHERE a.tipoAsignaturatipoAsignaturaId.tipoAsignaturaNombre='Básica' AND fccea.aprobada=FALSE AND fccea.facultadCumCarreraEstudiante=:estudiante");
            q.setParameter("estudiante", fcce);
            List<Asignatura> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}