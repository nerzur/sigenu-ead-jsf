/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.entity.EstadoCivil;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Estudiante;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class EstadoCivilJpaController implements Serializable {

    public EstadoCivilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoCivil estadoCivil) {
        if (estadoCivil.getEstudianteList() == null) {
            estadoCivil.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : estadoCivil.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            estadoCivil.setEstudianteList(attachedEstudianteList);
            em.persist(estadoCivil);
            for (Estudiante estudianteListEstudiante : estadoCivil.getEstudianteList()) {
                EstadoCivil oldEstadoCivilestadoCivilIdOfEstudianteListEstudiante = estudianteListEstudiante.getEstadoCivilestadoCivilId();
                estudianteListEstudiante.setEstadoCivilestadoCivilId(estadoCivil);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldEstadoCivilestadoCivilIdOfEstudianteListEstudiante != null) {
                    oldEstadoCivilestadoCivilIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldEstadoCivilestadoCivilIdOfEstudianteListEstudiante = em.merge(oldEstadoCivilestadoCivilIdOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoCivil estadoCivil) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoCivil persistentEstadoCivil = em.find(EstadoCivil.class, estadoCivil.getEstadoCivilId());
            List<Estudiante> estudianteListOld = persistentEstadoCivil.getEstudianteList();
            List<Estudiante> estudianteListNew = estadoCivil.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its estadoCivilestadoCivilId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            estadoCivil.setEstudianteList(estudianteListNew);
            estadoCivil = em.merge(estadoCivil);
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    EstadoCivil oldEstadoCivilestadoCivilIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getEstadoCivilestadoCivilId();
                    estudianteListNewEstudiante.setEstadoCivilestadoCivilId(estadoCivil);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldEstadoCivilestadoCivilIdOfEstudianteListNewEstudiante != null && !oldEstadoCivilestadoCivilIdOfEstudianteListNewEstudiante.equals(estadoCivil)) {
                        oldEstadoCivilestadoCivilIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldEstadoCivilestadoCivilIdOfEstudianteListNewEstudiante = em.merge(oldEstadoCivilestadoCivilIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadoCivil.getEstadoCivilId();
                if (findEstadoCivil(id) == null) {
                    throw new NonexistentEntityException("The estadoCivil with id " + id + " no longer exists.");
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
            EstadoCivil estadoCivil;
            try {
                estadoCivil = em.getReference(EstadoCivil.class, id);
                estadoCivil.getEstadoCivilId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoCivil with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estudiante> estudianteListOrphanCheck = estadoCivil.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadoCivil (" + estadoCivil + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable estadoCivilestadoCivilId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estadoCivil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoCivil> findEstadoCivilEntities() {
        return findEstadoCivilEntities(true, -1, -1);
    }

    public List<EstadoCivil> findEstadoCivilEntities(int maxResults, int firstResult) {
        return findEstadoCivilEntities(false, maxResults, firstResult);
    }

    private List<EstadoCivil> findEstadoCivilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoCivil.class));
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

    public EstadoCivil findEstadoCivil(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoCivil.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoCivilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoCivil> rt = cq.from(EstadoCivil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public EstadoCivil findEstadoCivilByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e "
                    + "FROM EstadoCivil e "
                    + "WHERE e.estadoCivilNombre= :nombre");
            q.setParameter("nombre", nombre);
            EstadoCivil p = (EstadoCivil) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public EstadoCivil findEstadoCivilByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e FROM EstadoCivil e WHERE e.estadoCivilCancelado=false and e.estadoCivilNombre= :nombre");
            q.setParameter("nombre", nombre);
            EstadoCivil p = (EstadoCivil) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findEstadoCivilAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e.estadoCivilNombre "
                    + "FROM EstadoCivil e "
                    + "WHERE e.estadoCivilCancelado=false "
                    + "ORDER BY e.estadoCivilNombre");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
