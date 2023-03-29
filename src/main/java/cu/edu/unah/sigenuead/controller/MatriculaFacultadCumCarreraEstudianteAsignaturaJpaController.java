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
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.Matricula;
import cu.edu.unah.sigenuead.entity.ExamenMatriculaFacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.MatriculaFacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.MatriculaFacultadCumCarreraEstudianteAsignaturaPK;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class MatriculaFacultadCumCarreraEstudianteAsignaturaJpaController implements Serializable {

    public MatriculaFacultadCumCarreraEstudianteAsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignatura) throws PreexistingEntityException, Exception {
        if (matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK() == null) {
            matriculaFacultadCumCarreraEstudianteAsignatura.setMatriculaFacultadCumCarreraEstudianteAsignaturaPK(new MatriculaFacultadCumCarreraEstudianteAsignaturaPK());
        }
        if (matriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList() == null) {
            matriculaFacultadCumCarreraEstudianteAsignatura.setExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList(new ArrayList<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura>());
        }
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setCodigoarea(matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura().getFacultadCumCarreraEstudianteAsignaturaPK().getCodigoarea());
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setAsignaturaId(matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura().getFacultadCumCarreraEstudianteAsignaturaPK().getAsignaturaId());
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setEstudianteId(matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura().getFacultadCumCarreraEstudianteAsignaturaPK().getEstudianteId());
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setFechaMatricula(matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura().getFacultadCumCarreraEstudianteAsignaturaPK().getFechaMatricula());
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setMatriculamatriculaId(matriculaFacultadCumCarreraEstudianteAsignatura.getMatricula().getMatriculaId());
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setCodigocum(matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura().getFacultadCumCarreraEstudianteAsignaturaPK().getCodigocum());
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setIdcarrera(matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura().getFacultadCumCarreraEstudianteAsignaturaPK().getIdcarrera());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignatura = matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura();
            if (facultadCumCarreraEstudianteAsignatura != null) {
                facultadCumCarreraEstudianteAsignatura = em.getReference(facultadCumCarreraEstudianteAsignatura.getClass(), facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK());
                matriculaFacultadCumCarreraEstudianteAsignatura.setFacultadCumCarreraEstudianteAsignatura(facultadCumCarreraEstudianteAsignatura);
            }
            Matricula matricula = matriculaFacultadCumCarreraEstudianteAsignatura.getMatricula();
            if (matricula != null) {
                matricula = em.getReference(matricula.getClass(), matricula.getMatriculaId());
                matriculaFacultadCumCarreraEstudianteAsignatura.setMatricula(matricula);
            }
            List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> attachedExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList = new ArrayList<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura>();
            for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach : matriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList()) {
                examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach = em.getReference(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getClass(), examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK());
                attachedExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList.add(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach);
            }
            matriculaFacultadCumCarreraEstudianteAsignatura.setExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList(attachedExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList);
            em.persist(matriculaFacultadCumCarreraEstudianteAsignatura);
            if (facultadCumCarreraEstudianteAsignatura != null) {
                facultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaList().add(matriculaFacultadCumCarreraEstudianteAsignatura);
                facultadCumCarreraEstudianteAsignatura = em.merge(facultadCumCarreraEstudianteAsignatura);
            }
            if (matricula != null) {
                matricula.getMatriculaFacultadCumCarreraEstudianteAsignaturaList().add(matriculaFacultadCumCarreraEstudianteAsignatura);
                matricula = em.merge(matricula);
            }
            for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura : matriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList()) {
                MatriculaFacultadCumCarreraEstudianteAsignatura oldMatriculaFacultadCumCarreraEstudianteAsignaturaOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura = examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura();
                examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura.setMatriculaFacultadCumCarreraEstudianteAsignatura(matriculaFacultadCumCarreraEstudianteAsignatura);
                examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura);
                if (oldMatriculaFacultadCumCarreraEstudianteAsignaturaOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura != null) {
                    oldMatriculaFacultadCumCarreraEstudianteAsignaturaOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura);
                    oldMatriculaFacultadCumCarreraEstudianteAsignaturaOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(oldMatriculaFacultadCumCarreraEstudianteAsignaturaOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMatriculaFacultadCumCarreraEstudianteAsignatura(matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK()) != null) {
                throw new PreexistingEntityException("MatriculaFacultadCumCarreraEstudianteAsignatura " + matriculaFacultadCumCarreraEstudianteAsignatura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignatura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setCodigoarea(matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura().getFacultadCumCarreraEstudianteAsignaturaPK().getCodigoarea());
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setAsignaturaId(matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura().getFacultadCumCarreraEstudianteAsignaturaPK().getAsignaturaId());
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setEstudianteId(matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura().getFacultadCumCarreraEstudianteAsignaturaPK().getEstudianteId());
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setFechaMatricula(matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura().getFacultadCumCarreraEstudianteAsignaturaPK().getFechaMatricula());
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setMatriculamatriculaId(matriculaFacultadCumCarreraEstudianteAsignatura.getMatricula().getMatriculaId());
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setCodigocum(matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura().getFacultadCumCarreraEstudianteAsignaturaPK().getCodigocum());
        matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setIdcarrera(matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura().getFacultadCumCarreraEstudianteAsignaturaPK().getIdcarrera());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MatriculaFacultadCumCarreraEstudianteAsignatura persistentMatriculaFacultadCumCarreraEstudianteAsignatura = em.find(MatriculaFacultadCumCarreraEstudianteAsignatura.class, matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK());
            FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaOld = persistentMatriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura();
            FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignaturaNew = matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura();
            Matricula matriculaOld = persistentMatriculaFacultadCumCarreraEstudianteAsignatura.getMatricula();
            Matricula matriculaNew = matriculaFacultadCumCarreraEstudianteAsignatura.getMatricula();
            List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOld = persistentMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList();
            List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew = matriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList();
            List<String> illegalOrphanMessages = null;
            for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOldExamenMatriculaFacultadCumCarreraEstudianteAsignatura : examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOld) {
                if (!examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew.contains(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOldExamenMatriculaFacultadCumCarreraEstudianteAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ExamenMatriculaFacultadCumCarreraEstudianteAsignatura " + examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOldExamenMatriculaFacultadCumCarreraEstudianteAsignatura + " since its matriculaFacultadCumCarreraEstudianteAsignatura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (facultadCumCarreraEstudianteAsignaturaNew != null) {
                facultadCumCarreraEstudianteAsignaturaNew = em.getReference(facultadCumCarreraEstudianteAsignaturaNew.getClass(), facultadCumCarreraEstudianteAsignaturaNew.getFacultadCumCarreraEstudianteAsignaturaPK());
                matriculaFacultadCumCarreraEstudianteAsignatura.setFacultadCumCarreraEstudianteAsignatura(facultadCumCarreraEstudianteAsignaturaNew);
            }
            if (matriculaNew != null) {
                matriculaNew = em.getReference(matriculaNew.getClass(), matriculaNew.getMatriculaId());
                matriculaFacultadCumCarreraEstudianteAsignatura.setMatricula(matriculaNew);
            }
            List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> attachedExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew = new ArrayList<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura>();
            for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach : examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew) {
                examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach = em.getReference(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getClass(), examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK());
                attachedExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew.add(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach);
            }
            examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew = attachedExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew;
            matriculaFacultadCumCarreraEstudianteAsignatura.setExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew);
            matriculaFacultadCumCarreraEstudianteAsignatura = em.merge(matriculaFacultadCumCarreraEstudianteAsignatura);
            if (facultadCumCarreraEstudianteAsignaturaOld != null && !facultadCumCarreraEstudianteAsignaturaOld.equals(facultadCumCarreraEstudianteAsignaturaNew)) {
                facultadCumCarreraEstudianteAsignaturaOld.getMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(matriculaFacultadCumCarreraEstudianteAsignatura);
                facultadCumCarreraEstudianteAsignaturaOld = em.merge(facultadCumCarreraEstudianteAsignaturaOld);
            }
            if (facultadCumCarreraEstudianteAsignaturaNew != null && !facultadCumCarreraEstudianteAsignaturaNew.equals(facultadCumCarreraEstudianteAsignaturaOld)) {
                facultadCumCarreraEstudianteAsignaturaNew.getMatriculaFacultadCumCarreraEstudianteAsignaturaList().add(matriculaFacultadCumCarreraEstudianteAsignatura);
                facultadCumCarreraEstudianteAsignaturaNew = em.merge(facultadCumCarreraEstudianteAsignaturaNew);
            }
            if (matriculaOld != null && !matriculaOld.equals(matriculaNew)) {
                matriculaOld.getMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(matriculaFacultadCumCarreraEstudianteAsignatura);
                matriculaOld = em.merge(matriculaOld);
            }
            if (matriculaNew != null && !matriculaNew.equals(matriculaOld)) {
                matriculaNew.getMatriculaFacultadCumCarreraEstudianteAsignaturaList().add(matriculaFacultadCumCarreraEstudianteAsignatura);
                matriculaNew = em.merge(matriculaNew);
            }
            for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura : examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew) {
                if (!examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOld.contains(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura)) {
                    MatriculaFacultadCumCarreraEstudianteAsignatura oldMatriculaFacultadCumCarreraEstudianteAsignaturaOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura = examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura();
                    examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura.setMatriculaFacultadCumCarreraEstudianteAsignatura(matriculaFacultadCumCarreraEstudianteAsignatura);
                    examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura);
                    if (oldMatriculaFacultadCumCarreraEstudianteAsignaturaOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura != null && !oldMatriculaFacultadCumCarreraEstudianteAsignaturaOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura.equals(matriculaFacultadCumCarreraEstudianteAsignatura)) {
                        oldMatriculaFacultadCumCarreraEstudianteAsignaturaOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura);
                        oldMatriculaFacultadCumCarreraEstudianteAsignaturaOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(oldMatriculaFacultadCumCarreraEstudianteAsignaturaOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MatriculaFacultadCumCarreraEstudianteAsignaturaPK id = matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK();
                if (findMatriculaFacultadCumCarreraEstudianteAsignatura(id) == null) {
                    throw new NonexistentEntityException("The matriculaFacultadCumCarreraEstudianteAsignatura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MatriculaFacultadCumCarreraEstudianteAsignaturaPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignatura;
            try {
                matriculaFacultadCumCarreraEstudianteAsignatura = em.getReference(MatriculaFacultadCumCarreraEstudianteAsignatura.class, id);
                matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The matriculaFacultadCumCarreraEstudianteAsignatura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheck = matriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList();
            for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheckExamenMatriculaFacultadCumCarreraEstudianteAsignatura : examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MatriculaFacultadCumCarreraEstudianteAsignatura (" + matriculaFacultadCumCarreraEstudianteAsignatura + ") cannot be destroyed since the ExamenMatriculaFacultadCumCarreraEstudianteAsignatura " + examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheckExamenMatriculaFacultadCumCarreraEstudianteAsignatura + " in its examenMatriculaFacultadCumCarreraEstudianteAsignaturaList field has a non-nullable matriculaFacultadCumCarreraEstudianteAsignatura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignatura = matriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura();
            if (facultadCumCarreraEstudianteAsignatura != null) {
                facultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(matriculaFacultadCumCarreraEstudianteAsignatura);
                facultadCumCarreraEstudianteAsignatura = em.merge(facultadCumCarreraEstudianteAsignatura);
            }
            Matricula matricula = matriculaFacultadCumCarreraEstudianteAsignatura.getMatricula();
            if (matricula != null) {
                matricula.getMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(matriculaFacultadCumCarreraEstudianteAsignatura);
                matricula = em.merge(matricula);
            }
            em.remove(matriculaFacultadCumCarreraEstudianteAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MatriculaFacultadCumCarreraEstudianteAsignatura> findMatriculaFacultadCumCarreraEstudianteAsignaturaEntities() {
        return findMatriculaFacultadCumCarreraEstudianteAsignaturaEntities(true, -1, -1);
    }

    public List<MatriculaFacultadCumCarreraEstudianteAsignatura> findMatriculaFacultadCumCarreraEstudianteAsignaturaEntities(int maxResults, int firstResult) {
        return findMatriculaFacultadCumCarreraEstudianteAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<MatriculaFacultadCumCarreraEstudianteAsignatura> findMatriculaFacultadCumCarreraEstudianteAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MatriculaFacultadCumCarreraEstudianteAsignatura.class));
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

    public MatriculaFacultadCumCarreraEstudianteAsignatura findMatriculaFacultadCumCarreraEstudianteAsignatura(MatriculaFacultadCumCarreraEstudianteAsignaturaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MatriculaFacultadCumCarreraEstudianteAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getMatriculaFacultadCumCarreraEstudianteAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MatriculaFacultadCumCarreraEstudianteAsignatura> rt = cq.from(MatriculaFacultadCumCarreraEstudianteAsignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
