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
import cu.edu.unah.sigenuead.entity.Ong;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class OngJpaController implements Serializable {

    public OngJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ong ong) {
        if (ong.getEstudianteList() == null) {
            ong.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : ong.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            ong.setEstudianteList(attachedEstudianteList);
            em.persist(ong);
            for (Estudiante estudianteListEstudiante : ong.getEstudianteList()) {
                estudianteListEstudiante.getOngList().add(ong);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ong ong) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ong persistentOng = em.find(Ong.class, ong.getOngId());
            List<Estudiante> estudianteListOld = persistentOng.getEstudianteList();
            List<Estudiante> estudianteListNew = ong.getEstudianteList();
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            ong.setEstudianteList(estudianteListNew);
            ong = em.merge(ong);
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    estudianteListOldEstudiante.getOngList().remove(ong);
                    estudianteListOldEstudiante = em.merge(estudianteListOldEstudiante);
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    estudianteListNewEstudiante.getOngList().add(ong);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ong.getOngId();
                if (findOng(id) == null) {
                    throw new NonexistentEntityException("The ong with id " + id + " no longer exists.");
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
            Ong ong;
            try {
                ong = em.getReference(Ong.class, id);
                ong.getOngId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ong with id " + id + " no longer exists.", enfe);
            }
            List<Estudiante> estudianteList = ong.getEstudianteList();
            for (Estudiante estudianteListEstudiante : estudianteList) {
                estudianteListEstudiante.getOngList().remove(ong);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.remove(ong);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ong> findOngEntities() {
        return findOngEntities(true, -1, -1);
    }

    public List<Ong> findOngEntities(int maxResults, int firstResult) {
        return findOngEntities(false, maxResults, firstResult);
    }

    private List<Ong> findOngEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ong.class));
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

    public Ong findOng(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ong.class, id);
        } finally {
            em.close();
        }
    }

    public int getOngCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ong> rt = cq.from(Ong.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Ong findOngByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT o FROM Ong o WHERE o.ongNombre=:nombre");
            q.setParameter("nombre", nombre);
            Ong p = (Ong) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Ong findOngByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT o "
                    + "FROM Ong o "
                    + "WHERE o.ongCancelado=false AND "
                    + "o.ongNombre= :nombre");
            q.setParameter("nombre", nombre);
            Ong p = (Ong) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findOngAvailable() {
        EntityManager em = getEntityManager();
        try {
            List<String> p = em.createQuery("SELECT o.ongNombre "
                    + "FROM Ong o "
                    + "WHERE o.ongCancelado=false "
                    + "ORDER BY o.ongNombre").getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
