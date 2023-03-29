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
import cu.edu.unah.sigenuead.entity.OrganizacionPopular;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class OrganizacionPopularJpaController implements Serializable {

    public OrganizacionPopularJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrganizacionPopular organizacionPopular) {
        if (organizacionPopular.getEstudianteList() == null) {
            organizacionPopular.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : organizacionPopular.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            organizacionPopular.setEstudianteList(attachedEstudianteList);
            em.persist(organizacionPopular);
            for (Estudiante estudianteListEstudiante : organizacionPopular.getEstudianteList()) {
                estudianteListEstudiante.getOrganizacionPopularList().add(organizacionPopular);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrganizacionPopular organizacionPopular) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrganizacionPopular persistentOrganizacionPopular = em.find(OrganizacionPopular.class, organizacionPopular.getOrganizacionPopularId());
            List<Estudiante> estudianteListOld = persistentOrganizacionPopular.getEstudianteList();
            List<Estudiante> estudianteListNew = organizacionPopular.getEstudianteList();
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            organizacionPopular.setEstudianteList(estudianteListNew);
            organizacionPopular = em.merge(organizacionPopular);
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    estudianteListOldEstudiante.getOrganizacionPopularList().remove(organizacionPopular);
                    estudianteListOldEstudiante = em.merge(estudianteListOldEstudiante);
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    estudianteListNewEstudiante.getOrganizacionPopularList().add(organizacionPopular);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = organizacionPopular.getOrganizacionPopularId();
                if (findOrganizacionPopular(id) == null) {
                    throw new NonexistentEntityException("The organizacionPopular with id " + id + " no longer exists.");
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
            OrganizacionPopular organizacionPopular;
            try {
                organizacionPopular = em.getReference(OrganizacionPopular.class, id);
                organizacionPopular.getOrganizacionPopularId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The organizacionPopular with id " + id + " no longer exists.", enfe);
            }
            List<Estudiante> estudianteList = organizacionPopular.getEstudianteList();
            for (Estudiante estudianteListEstudiante : estudianteList) {
                estudianteListEstudiante.getOrganizacionPopularList().remove(organizacionPopular);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.remove(organizacionPopular);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrganizacionPopular> findOrganizacionPopularEntities() {
        return findOrganizacionPopularEntities(true, -1, -1);
    }

    public List<OrganizacionPopular> findOrganizacionPopularEntities(int maxResults, int firstResult) {
        return findOrganizacionPopularEntities(false, maxResults, firstResult);
    }

    private List<OrganizacionPopular> findOrganizacionPopularEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrganizacionPopular.class));
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

    public OrganizacionPopular findOrganizacionPopular(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrganizacionPopular.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrganizacionPopularCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrganizacionPopular> rt = cq.from(OrganizacionPopular.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public OrganizacionPopular findOrganizacionPopularByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT o "
                    + "FROM OrganizacionPopular o "
                    + "WHERE o.organizacionPopularNombre= :nombre");
            q.setParameter("nombre", nombre);
            OrganizacionPopular p = (OrganizacionPopular) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public OrganizacionPopular findOrganizacionPopularByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT o "
                    + "FROM OrganizacionPopular o "
                    + "WHERE o.organizacionPopularCancelado=false AND "
                    + "o.organizacionPopularNombre= :nombre");
            q.setParameter("nombre", nombre);
            OrganizacionPopular p = (OrganizacionPopular) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findOrganizacionPopularAvailable() {
        EntityManager em = getEntityManager();
        try {
            List<String> p = em.createQuery("SELECT o.organizacionPopularNombre "
                    + "FROM OrganizacionPopular o "
                    + "WHERE o.organizacionPopularCancelado=false "
                    + "ORDER BY o.organizacionPopularNombre").getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
