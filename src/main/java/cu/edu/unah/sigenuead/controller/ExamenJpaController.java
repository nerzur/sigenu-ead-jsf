/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.entity.Examen;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.ExamenMatriculaFacultadCumCarreraEstudianteAsignatura;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class ExamenJpaController implements Serializable {

    public ExamenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Examen examen) {
        if (examen.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList() == null) {
            examen.setExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList(new ArrayList<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> attachedExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList = new ArrayList<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura>();
            for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach : examen.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList()) {
                examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach = em.getReference(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getClass(), examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK());
                attachedExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList.add(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach);
            }
            examen.setExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList(attachedExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList);
            em.persist(examen);
            for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura : examen.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList()) {
                Examen oldExamenOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura = examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamen();
                examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura.setExamen(examen);
                examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura);
                if (oldExamenOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura != null) {
                    oldExamenOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura);
                    oldExamenOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(oldExamenOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListExamenMatriculaFacultadCumCarreraEstudianteAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Examen examen) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Examen persistentExamen = em.find(Examen.class, examen.getExamenId());
            List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOld = persistentExamen.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList();
            List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew = examen.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList();
            List<String> illegalOrphanMessages = null;
            for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOldExamenMatriculaFacultadCumCarreraEstudianteAsignatura : examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOld) {
                if (!examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew.contains(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOldExamenMatriculaFacultadCumCarreraEstudianteAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ExamenMatriculaFacultadCumCarreraEstudianteAsignatura " + examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOldExamenMatriculaFacultadCumCarreraEstudianteAsignatura + " since its examen field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> attachedExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew = new ArrayList<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura>();
            for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach : examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew) {
                examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach = em.getReference(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getClass(), examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK());
                attachedExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew.add(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach);
            }
            examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew = attachedExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew;
            examen.setExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew);
            examen = em.merge(examen);
            for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura : examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNew) {
                if (!examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOld.contains(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura)) {
                    Examen oldExamenOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura = examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamen();
                    examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura.setExamen(examen);
                    examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura);
                    if (oldExamenOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura != null && !oldExamenOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura.equals(examen)) {
                        oldExamenOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(examenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura);
                        oldExamenOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(oldExamenOfExamenMatriculaFacultadCumCarreraEstudianteAsignaturaListNewExamenMatriculaFacultadCumCarreraEstudianteAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = examen.getExamenId();
                if (findExamen(id) == null) {
                    throw new NonexistentEntityException("The examen with id " + id + " no longer exists.");
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
            Examen examen;
            try {
                examen = em.getReference(Examen.class, id);
                examen.getExamenId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examen with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheck = examen.getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList();
            for (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheckExamenMatriculaFacultadCumCarreraEstudianteAsignatura : examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Examen (" + examen + ") cannot be destroyed since the ExamenMatriculaFacultadCumCarreraEstudianteAsignatura " + examenMatriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheckExamenMatriculaFacultadCumCarreraEstudianteAsignatura + " in its examenMatriculaFacultadCumCarreraEstudianteAsignaturaList field has a non-nullable examen field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(examen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Examen> findExamenEntities() {
        return findExamenEntities(true, -1, -1);
    }

    public List<Examen> findExamenEntities(int maxResults, int firstResult) {
        return findExamenEntities(false, maxResults, firstResult);
    }

    private List<Examen> findExamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Examen.class));
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

    public Examen findExamen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Examen.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Examen> rt = cq.from(Examen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Examen findExamenByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e FROM Examen e WHERE e.examenTipo= :nombre");
            q.setParameter("nombre", nombre);
            Examen p = (Examen) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Examen findExamenByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e FROM Examen e WHERE e.examenCancelado=false and e.examenTipo= :nombre");
            q.setParameter("nombre", nombre);
            Examen p = (Examen) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findExamenAvailable() {
        EntityManager em = getEntityManager();
        try {
            List<String> p = em.createQuery("SELECT e.examenTipo "
                    + "FROM Examen e "
                    + "WHERE e.examenCancelado=false "
                    + "ORDER BY e.examenTipo").getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
