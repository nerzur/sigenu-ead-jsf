/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.entity.EstadoEstudiante;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class EstadoEstudianteJpaController implements Serializable {

    public EstadoEstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoEstudiante estadoEstudiante) {
        if (estadoEstudiante.getFacultadCumCarreraEstudianteList() == null) {
            estadoEstudiante.setFacultadCumCarreraEstudianteList(new ArrayList<FacultadCumCarreraEstudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<FacultadCumCarreraEstudiante> attachedFacultadCumCarreraEstudianteList = new ArrayList<FacultadCumCarreraEstudiante>();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach : estadoEstudiante.getFacultadCumCarreraEstudianteList()) {
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach = em.getReference(facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach.getClass(), facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach.getFacultadCumCarreraEstudiantePK());
                attachedFacultadCumCarreraEstudianteList.add(facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach);
            }
            estadoEstudiante.setFacultadCumCarreraEstudianteList(attachedFacultadCumCarreraEstudianteList);
            em.persist(estadoEstudiante);
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante : estadoEstudiante.getFacultadCumCarreraEstudianteList()) {
                EstadoEstudiante oldEstadoEstudianteestadoEstucianteIdOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.getEstadoEstudianteestadoEstucianteId();
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.setEstadoEstudianteestadoEstucianteId(estadoEstudiante);
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                if (oldEstadoEstudianteestadoEstucianteIdOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante != null) {
                    oldEstadoEstudianteestadoEstucianteIdOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                    oldEstadoEstudianteestadoEstucianteIdOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = em.merge(oldEstadoEstudianteestadoEstucianteIdOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoEstudiante estadoEstudiante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoEstudiante persistentEstadoEstudiante = em.find(EstadoEstudiante.class, estadoEstudiante.getEstadoEstucianteId());
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListOld = persistentEstadoEstudiante.getFacultadCumCarreraEstudianteList();
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListNew = estadoEstudiante.getFacultadCumCarreraEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListOld) {
                if (!facultadCumCarreraEstudianteListNew.contains(facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FacultadCumCarreraEstudiante " + facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante + " since its estadoEstudianteestadoEstucianteId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<FacultadCumCarreraEstudiante> attachedFacultadCumCarreraEstudianteListNew = new ArrayList<FacultadCumCarreraEstudiante>();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach : facultadCumCarreraEstudianteListNew) {
                facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach = em.getReference(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach.getClass(), facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach.getFacultadCumCarreraEstudiantePK());
                attachedFacultadCumCarreraEstudianteListNew.add(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach);
            }
            facultadCumCarreraEstudianteListNew = attachedFacultadCumCarreraEstudianteListNew;
            estadoEstudiante.setFacultadCumCarreraEstudianteList(facultadCumCarreraEstudianteListNew);
            estadoEstudiante = em.merge(estadoEstudiante);
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListNew) {
                if (!facultadCumCarreraEstudianteListOld.contains(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante)) {
                    EstadoEstudiante oldEstadoEstudianteestadoEstucianteIdOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.getEstadoEstudianteestadoEstucianteId();
                    facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.setEstadoEstudianteestadoEstucianteId(estadoEstudiante);
                    facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                    if (oldEstadoEstudianteestadoEstucianteIdOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante != null && !oldEstadoEstudianteestadoEstucianteIdOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.equals(estadoEstudiante)) {
                        oldEstadoEstudianteestadoEstucianteIdOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                        oldEstadoEstudianteestadoEstucianteIdOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = em.merge(oldEstadoEstudianteestadoEstucianteIdOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadoEstudiante.getEstadoEstucianteId();
                if (findEstadoEstudiante(id) == null) {
                    throw new NonexistentEntityException("The estadoEstudiante with id " + id + " no longer exists.");
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
            EstadoEstudiante estadoEstudiante;
            try {
                estadoEstudiante = em.getReference(EstadoEstudiante.class, id);
                estadoEstudiante.getEstadoEstucianteId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoEstudiante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListOrphanCheck = estadoEstudiante.getFacultadCumCarreraEstudianteList();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListOrphanCheckFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadoEstudiante (" + estadoEstudiante + ") cannot be destroyed since the FacultadCumCarreraEstudiante " + facultadCumCarreraEstudianteListOrphanCheckFacultadCumCarreraEstudiante + " in its facultadCumCarreraEstudianteList field has a non-nullable estadoEstudianteestadoEstucianteId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estadoEstudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoEstudiante> findEstadoEstudianteEntities() {
        return findEstadoEstudianteEntities(true, -1, -1);
    }

    public List<EstadoEstudiante> findEstadoEstudianteEntities(int maxResults, int firstResult) {
        return findEstadoEstudianteEntities(false, maxResults, firstResult);
    }

    private List<EstadoEstudiante> findEstadoEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoEstudiante.class));
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

    public EstadoEstudiante findEstadoEstudiante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoEstudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoEstudiante> rt = cq.from(EstadoEstudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public EstadoEstudiante findEstadoEstudianteByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e "
                    + "FROM EstadoEstudiante e "
                    + "WHERE e.estadoEstudianteNombre= :nombre");
            q.setParameter("nombre", nombre);
            EstadoEstudiante p = (EstadoEstudiante) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public EstadoEstudiante findEstadoEstudianteByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e "
                    + "FROM EstadoEstudiante e "
                    + "WHERE e.estadoEstudianteCancelado=false AND "
                    + "e.estadoEstudianteNombre= :nombre");
            q.setParameter("nombre", nombre);
            EstadoEstudiante p = (EstadoEstudiante) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findEstadoEstudianteAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e.estadoEstudianteNombre "
                    + "FROM EstadoEstudiante e "
                    + "WHERE e.estadoEstudianteCancelado=false "
                    + "ORDER BY e.estadoEstudianteNombre");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
