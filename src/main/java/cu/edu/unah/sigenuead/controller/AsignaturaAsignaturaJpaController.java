/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.controller;


import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.controller.exceptions.PreexistingEntityException;
import cu.edu.unah.sigenuead.entity.Asignatura;
import cu.edu.unah.sigenuead.entity.AsignaturaAsignaturaPK;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import cu.edu.unah.sigenuead.entity.AsignaturaAsignatura;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author claudy
 */
public class AsignaturaAsignaturaJpaController implements Serializable {

    public AsignaturaAsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignaturaAsignatura asignaturaAsignatura) throws PreexistingEntityException, Exception {
        if (asignaturaAsignatura.getAsignaturaAsignaturaPK() == null) {
            asignaturaAsignatura.setAsignaturaAsignaturaPK(new AsignaturaAsignaturaPK());
        }
        asignaturaAsignatura.getAsignaturaAsignaturaPK().setAsignaturaDependiente(asignaturaAsignatura.getAsignatura1().getAsignaturaId());
        asignaturaAsignatura.getAsignaturaAsignaturaPK().setAsignaturaPrecedente(asignaturaAsignatura.getAsignatura().getAsignaturaId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura asignatura = asignaturaAsignatura.getAsignatura();
            if (asignatura != null) {
                asignatura = em.getReference(asignatura.getClass(), asignatura.getAsignaturaId());
                asignaturaAsignatura.setAsignatura(asignatura);
            }
            Asignatura asignatura1 = asignaturaAsignatura.getAsignatura1();
            if (asignatura1 != null) {
                asignatura1 = em.getReference(asignatura1.getClass(), asignatura1.getAsignaturaId());
                asignaturaAsignatura.setAsignatura1(asignatura1);
            }
            em.persist(asignaturaAsignatura);
            if (asignatura != null) {
                asignatura.getAsignaturaAsignaturaList().add(asignaturaAsignatura);
                asignatura = em.merge(asignatura);
            }
            if (asignatura1 != null) {
                asignatura1.getAsignaturaAsignaturaList().add(asignaturaAsignatura);
                asignatura1 = em.merge(asignatura1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsignaturaAsignatura(asignaturaAsignatura.getAsignaturaAsignaturaPK()) != null) {
                throw new PreexistingEntityException("AsignaturaAsignatura " + asignaturaAsignatura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignaturaAsignatura asignaturaAsignatura) throws NonexistentEntityException, Exception {
        asignaturaAsignatura.getAsignaturaAsignaturaPK().setAsignaturaDependiente(asignaturaAsignatura.getAsignatura1().getAsignaturaId());
        asignaturaAsignatura.getAsignaturaAsignaturaPK().setAsignaturaPrecedente(asignaturaAsignatura.getAsignatura().getAsignaturaId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignaturaAsignatura persistentAsignaturaAsignatura = em.find(AsignaturaAsignatura.class, asignaturaAsignatura.getAsignaturaAsignaturaPK());
            Asignatura asignaturaOld = persistentAsignaturaAsignatura.getAsignatura();
            Asignatura asignaturaNew = asignaturaAsignatura.getAsignatura();
            Asignatura asignatura1Old = persistentAsignaturaAsignatura.getAsignatura1();
            Asignatura asignatura1New = asignaturaAsignatura.getAsignatura1();
            if (asignaturaNew != null) {
                asignaturaNew = em.getReference(asignaturaNew.getClass(), asignaturaNew.getAsignaturaId());
                asignaturaAsignatura.setAsignatura(asignaturaNew);
            }
            if (asignatura1New != null) {
                asignatura1New = em.getReference(asignatura1New.getClass(), asignatura1New.getAsignaturaId());
                asignaturaAsignatura.setAsignatura1(asignatura1New);
            }
            asignaturaAsignatura = em.merge(asignaturaAsignatura);
            if (asignaturaOld != null && !asignaturaOld.equals(asignaturaNew)) {
                asignaturaOld.getAsignaturaAsignaturaList().remove(asignaturaAsignatura);
                asignaturaOld = em.merge(asignaturaOld);
            }
            if (asignaturaNew != null && !asignaturaNew.equals(asignaturaOld)) {
                asignaturaNew.getAsignaturaAsignaturaList().add(asignaturaAsignatura);
                asignaturaNew = em.merge(asignaturaNew);
            }
            if (asignatura1Old != null && !asignatura1Old.equals(asignatura1New)) {
                asignatura1Old.getAsignaturaAsignaturaList().remove(asignaturaAsignatura);
                asignatura1Old = em.merge(asignatura1Old);
            }
            if (asignatura1New != null && !asignatura1New.equals(asignatura1Old)) {
                asignatura1New.getAsignaturaAsignaturaList().add(asignaturaAsignatura);
                asignatura1New = em.merge(asignatura1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AsignaturaAsignaturaPK id = asignaturaAsignatura.getAsignaturaAsignaturaPK();
                if (findAsignaturaAsignatura(id) == null) {
                    throw new NonexistentEntityException("The asignaturaAsignatura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AsignaturaAsignaturaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignaturaAsignatura asignaturaAsignatura;
            try {
                asignaturaAsignatura = em.getReference(AsignaturaAsignatura.class, id);
                asignaturaAsignatura.getAsignaturaAsignaturaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignaturaAsignatura with id " + id + " no longer exists.", enfe);
            }
            Asignatura asignatura = asignaturaAsignatura.getAsignatura();
            if (asignatura != null) {
                asignatura.getAsignaturaAsignaturaList().remove(asignaturaAsignatura);
                asignatura = em.merge(asignatura);
            }
            Asignatura asignatura1 = asignaturaAsignatura.getAsignatura1();
            if (asignatura1 != null) {
                asignatura1.getAsignaturaAsignaturaList().remove(asignaturaAsignatura);
                asignatura1 = em.merge(asignatura1);
            }
            em.remove(asignaturaAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignaturaAsignatura> findAsignaturaAsignaturaEntities() {
        return findAsignaturaAsignaturaEntities(true, -1, -1);
    }

    public List<AsignaturaAsignatura> findAsignaturaAsignaturaEntities(int maxResults, int firstResult) {
        return findAsignaturaAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<AsignaturaAsignatura> findAsignaturaAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsignaturaAsignatura.class));
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

    public AsignaturaAsignatura findAsignaturaAsignatura(AsignaturaAsignaturaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignaturaAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturaAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsignaturaAsignatura> rt = cq.from(AsignaturaAsignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
