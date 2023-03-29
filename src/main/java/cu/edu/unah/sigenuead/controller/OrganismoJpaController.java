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
import cu.edu.unah.sigenuead.entity.Organismo;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class OrganismoJpaController implements Serializable {

    public OrganismoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Organismo organismo) {
        if (organismo.getEstudianteList() == null) {
            organismo.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : organismo.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            organismo.setEstudianteList(attachedEstudianteList);
            em.persist(organismo);
            for (Estudiante estudianteListEstudiante : organismo.getEstudianteList()) {
                Organismo oldOrganismoidorganismoOfEstudianteListEstudiante = estudianteListEstudiante.getOrganismoidorganismo();
                estudianteListEstudiante.setOrganismoidorganismo(organismo);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldOrganismoidorganismoOfEstudianteListEstudiante != null) {
                    oldOrganismoidorganismoOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldOrganismoidorganismoOfEstudianteListEstudiante = em.merge(oldOrganismoidorganismoOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Organismo organismo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Organismo persistentOrganismo = em.find(Organismo.class, organismo.getIdorganismo());
            List<Estudiante> estudianteListOld = persistentOrganismo.getEstudianteList();
            List<Estudiante> estudianteListNew = organismo.getEstudianteList();
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            organismo.setEstudianteList(estudianteListNew);
            organismo = em.merge(organismo);
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    estudianteListOldEstudiante.setOrganismoidorganismo(null);
                    estudianteListOldEstudiante = em.merge(estudianteListOldEstudiante);
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Organismo oldOrganismoidorganismoOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getOrganismoidorganismo();
                    estudianteListNewEstudiante.setOrganismoidorganismo(organismo);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldOrganismoidorganismoOfEstudianteListNewEstudiante != null && !oldOrganismoidorganismoOfEstudianteListNewEstudiante.equals(organismo)) {
                        oldOrganismoidorganismoOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldOrganismoidorganismoOfEstudianteListNewEstudiante = em.merge(oldOrganismoidorganismoOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = organismo.getIdorganismo();
                if (findOrganismo(id) == null) {
                    throw new NonexistentEntityException("The organismo with id " + id + " no longer exists.");
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
            Organismo organismo;
            try {
                organismo = em.getReference(Organismo.class, id);
                organismo.getIdorganismo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The organismo with id " + id + " no longer exists.", enfe);
            }
            List<Estudiante> estudianteList = organismo.getEstudianteList();
            for (Estudiante estudianteListEstudiante : estudianteList) {
                estudianteListEstudiante.setOrganismoidorganismo(null);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.remove(organismo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Organismo> findOrganismoEntities() {
        return findOrganismoEntities(true, -1, -1);
    }

    public List<Organismo> findOrganismoEntities(int maxResults, int firstResult) {
        return findOrganismoEntities(false, maxResults, firstResult);
    }

    private List<Organismo> findOrganismoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Organismo.class));
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

    public Organismo findOrganismo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Organismo.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrganismoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Organismo> rt = cq.from(Organismo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Organismo findOrganismoByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT o "
                    + "FROM Organismo o "
                    + "WHERE o.nombreorganismo= :nombre");
            q.setParameter("nombre", nombre);
            Organismo p = (Organismo) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Organismo findOrganismoByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT o "
                    + "FROM Organismo o "
                    + "WHERE o.canceladoorganismo=false AND "
                    + "o.nombreorganismo= :nombre");
            q.setParameter("nombre", nombre);
            Organismo p = (Organismo) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findOrganismoAvailable() {
        EntityManager em = getEntityManager();
        try {
            List<String> p = em.createQuery("SELECT o.nombreorganismo "
                    + "FROM Organismo o "
                    + "WHERE o.canceladoorganismo=false "
                    + "ORDER BY o.nombreorganismo").getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
