/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Examen;
import cu.edu.unah.sigenuead.entity.ExamenMatriculaFacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK;
import cu.edu.unah.sigenuead.entity.MatriculaFacultadCumCarreraEstudianteAsignatura;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaJpaController implements Serializable {

    public ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignatura) throws PreexistingEntityException, Exception {
        if (examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK() == null) {
            examenMatriculaFacultadCumCarreraEstudianteAsignatura.setExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK());
        }
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setIdcarrera(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getIdcarrera());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setMatriculaId(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getMatriculamatriculaId());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setExamenexamenId(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamen().getExamenId());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setCodigocum(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getCodigocum());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setFechaMatricula(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getFechaMatricula());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setCodigoarea(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getCodigoarea());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setEstudianteId(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getEstudianteId());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setAsignaturaId(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getAsignaturaId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Examen examen = examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamen();
            if (examen != null) {
                examen = em.getReference(examen.getClass(), examen.getExamenId());
                examenMatriculaFacultadCumCarreraEstudianteAsignatura.setExamen(examen);
            }
            MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignatura = examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura();
            if (matriculaFacultadCumCarreraEstudianteAsignatura != null) {
                matriculaFacultadCumCarreraEstudianteAsignatura = em.getReference(matriculaFacultadCumCarreraEstudianteAsignatura.getClass(), matriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK());
                examenMatriculaFacultadCumCarreraEstudianteAsignatura.setMatriculaFacultadCumCarreraEstudianteAsignatura(matriculaFacultadCumCarreraEstudianteAsignatura);
            }
            em.persist(examenMatriculaFacultadCumCarreraEstudianteAsignatura);
            if (examen != null) {
                examen.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList().add(examenMatriculaFacultadCumCarreraEstudianteAsignatura);
                examen = em.merge(examen);
            }
            if (matriculaFacultadCumCarreraEstudianteAsignatura != null) {
                matriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList().add(examenMatriculaFacultadCumCarreraEstudianteAsignatura);
                matriculaFacultadCumCarreraEstudianteAsignatura = em.merge(matriculaFacultadCumCarreraEstudianteAsignatura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK()) != null) {
                throw new PreexistingEntityException("ExamenMatriculaFacultadCumCarreraEstudianteAsignatura " + examenMatriculaFacultadCumCarreraEstudianteAsignatura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignatura) throws NonexistentEntityException, Exception {
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setIdcarrera(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getIdcarrera());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setMatriculaId(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getMatriculamatriculaId());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setExamenexamenId(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamen().getExamenId());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setCodigocum(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getCodigocum());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setFechaMatricula(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getFechaMatricula());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setCodigoarea(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getCodigoarea());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setEstudianteId(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getEstudianteId());
        examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK().setAsignaturaId(examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura().getMatriculaFacultadCumCarreraEstudianteAsignaturaPK().getAsignaturaId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExamenMatriculaFacultadCumCarreraEstudianteAsignatura persistentExamenMatriculaFacultadCumCarreraEstudianteAsignatura = em.find(ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.class, examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK());
            Examen examenOld = persistentExamenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamen();
            Examen examenNew = examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamen();
            MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaOld = persistentExamenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura();
            MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaNew = examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura();
            if (examenNew != null) {
                examenNew = em.getReference(examenNew.getClass(), examenNew.getExamenId());
                examenMatriculaFacultadCumCarreraEstudianteAsignatura.setExamen(examenNew);
            }
            if (matriculaFacultadCumCarreraEstudianteAsignaturaNew != null) {
                matriculaFacultadCumCarreraEstudianteAsignaturaNew = em.getReference(matriculaFacultadCumCarreraEstudianteAsignaturaNew.getClass(), matriculaFacultadCumCarreraEstudianteAsignaturaNew.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK());
                examenMatriculaFacultadCumCarreraEstudianteAsignatura.setMatriculaFacultadCumCarreraEstudianteAsignatura(matriculaFacultadCumCarreraEstudianteAsignaturaNew);
            }
            examenMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(examenMatriculaFacultadCumCarreraEstudianteAsignatura);
            if (examenOld != null && !examenOld.equals(examenNew)) {
                examenOld.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(examenMatriculaFacultadCumCarreraEstudianteAsignatura);
                examenOld = em.merge(examenOld);
            }
            if (examenNew != null && !examenNew.equals(examenOld)) {
                examenNew.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList().add(examenMatriculaFacultadCumCarreraEstudianteAsignatura);
                examenNew = em.merge(examenNew);
            }
            if (matriculaFacultadCumCarreraEstudianteAsignaturaOld != null && !matriculaFacultadCumCarreraEstudianteAsignaturaOld.equals(matriculaFacultadCumCarreraEstudianteAsignaturaNew)) {
                matriculaFacultadCumCarreraEstudianteAsignaturaOld.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(examenMatriculaFacultadCumCarreraEstudianteAsignatura);
                matriculaFacultadCumCarreraEstudianteAsignaturaOld = em.merge(matriculaFacultadCumCarreraEstudianteAsignaturaOld);
            }
            if (matriculaFacultadCumCarreraEstudianteAsignaturaNew != null && !matriculaFacultadCumCarreraEstudianteAsignaturaNew.equals(matriculaFacultadCumCarreraEstudianteAsignaturaOld)) {
                matriculaFacultadCumCarreraEstudianteAsignaturaNew.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList().add(examenMatriculaFacultadCumCarreraEstudianteAsignatura);
                matriculaFacultadCumCarreraEstudianteAsignaturaNew = em.merge(matriculaFacultadCumCarreraEstudianteAsignaturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK id = examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK();
                if (findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(id) == null) {
                    throw new NonexistentEntityException("The examenMatriculaFacultadCumCarreraEstudianteAsignatura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignatura;
            try {
                examenMatriculaFacultadCumCarreraEstudianteAsignatura = em.getReference(ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.class, id);
                examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examenMatriculaFacultadCumCarreraEstudianteAsignatura with id " + id + " no longer exists.", enfe);
            }
            Examen examen = examenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamen();
            if (examen != null) {
                examen.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(examenMatriculaFacultadCumCarreraEstudianteAsignatura);
                examen = em.merge(examen);
            }
            MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignatura = examenMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignatura();
            if (matriculaFacultadCumCarreraEstudianteAsignatura != null) {
                matriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(examenMatriculaFacultadCumCarreraEstudianteAsignatura);
                matriculaFacultadCumCarreraEstudianteAsignatura = em.merge(matriculaFacultadCumCarreraEstudianteAsignatura);
            }
            em.remove(examenMatriculaFacultadCumCarreraEstudianteAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> findExamenMatriculaFacultadCumCarreraEstudianteAsignaturaEntities() {
        return findExamenMatriculaFacultadCumCarreraEstudianteAsignaturaEntities(true, -1, -1);
    }

    public List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> findExamenMatriculaFacultadCumCarreraEstudianteAsignaturaEntities(int maxResults, int firstResult) {
        return findExamenMatriculaFacultadCumCarreraEstudianteAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> findExamenMatriculaFacultadCumCarreraEstudianteAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.class));
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

    public ExamenMatriculaFacultadCumCarreraEstudianteAsignatura findExamenMatriculaFacultadCumCarreraEstudianteAsignatura(ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> rt = cq.from(ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
