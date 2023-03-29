/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.Minusvalia;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class MinusvaliaJpaController implements Serializable {

    public MinusvaliaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Minusvalia minusvalia) {
        if (minusvalia.getEstudianteList() == null) {
            minusvalia.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : minusvalia.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            minusvalia.setEstudianteList(attachedEstudianteList);
            em.persist(minusvalia);
            for (Estudiante estudianteListEstudiante : minusvalia.getEstudianteList()) {
                estudianteListEstudiante.getMinusvaliaList().add(minusvalia);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Minusvalia minusvalia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Minusvalia persistentMinusvalia = em.find(Minusvalia.class, minusvalia.getMinusvaliaId());
            List<Estudiante> estudianteListOld = persistentMinusvalia.getEstudianteList();
            List<Estudiante> estudianteListNew = minusvalia.getEstudianteList();
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            minusvalia.setEstudianteList(estudianteListNew);
            minusvalia = em.merge(minusvalia);
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    estudianteListOldEstudiante.getMinusvaliaList().remove(minusvalia);
                    estudianteListOldEstudiante = em.merge(estudianteListOldEstudiante);
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    estudianteListNewEstudiante.getMinusvaliaList().add(minusvalia);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = minusvalia.getMinusvaliaId();
                if (findMinusvalia(id) == null) {
                    throw new NonexistentEntityException("The minusvalia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Minusvalia minusvalia;
            try {
                minusvalia = em.getReference(Minusvalia.class, id);
                minusvalia.getMinusvaliaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The minusvalia with id " + id + " no longer exists.", enfe);
            }
            List<Estudiante> estudianteList = minusvalia.getEstudianteList();
            for (Estudiante estudianteListEstudiante : estudianteList) {
                estudianteListEstudiante.getMinusvaliaList().remove(minusvalia);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.remove(minusvalia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Minusvalia> findMinusvaliaEntities() {
        return findMinusvaliaEntities(true, -1, -1);
    }

    public List<Minusvalia> findMinusvaliaEntities(int maxResults, int firstResult) {
        return findMinusvaliaEntities(false, maxResults, firstResult);
    }

    private List<Minusvalia> findMinusvaliaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Minusvalia.class));
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

    public Minusvalia findMinusvalia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Minusvalia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMinusvaliaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Minusvalia> rt = cq.from(Minusvalia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Minusvalia findMinusvaliaByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m "
                    + "FROM Minusvalia m "
                    + "WHERE m.minusvaliaNombre= :nombre");
            q.setParameter("nombre", nombre);
            Minusvalia p = (Minusvalia) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Minusvalia findMinusvaliaByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m "
                    + "FROM Minusvalia m "
                    + "WHERE m.minusvaliaCancelado=false AND "
                    + "m.minusvaliaNombre= :nombre");
            q.setParameter("nombre", nombre);
            Minusvalia p = (Minusvalia) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findMinusvaliaAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m.minusvaliaNombre "
                    + "FROM Minusvalia m "
                    + "WHERE m.minusvaliaCancelado=false "
                    + "ORDER BY m.minusvaliaNombre");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
