/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Carreranacional;
import cu.edu.unah.sigenuead.entity.Especialidad;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class EspecialidadJpaController implements Serializable {

    public EspecialidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Especialidad especialidad) {
        if (especialidad.getCarreranacionalList() == null) {
            especialidad.setCarreranacionalList(new ArrayList<Carreranacional>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Carreranacional> attachedCarreranacionalList = new ArrayList<Carreranacional>();
            for (Carreranacional carreranacionalListCarreranacionalToAttach : especialidad.getCarreranacionalList()) {
                carreranacionalListCarreranacionalToAttach = em.getReference(carreranacionalListCarreranacionalToAttach.getClass(), carreranacionalListCarreranacionalToAttach.getIdcarreranacional());
                attachedCarreranacionalList.add(carreranacionalListCarreranacionalToAttach);
            }
            especialidad.setCarreranacionalList(attachedCarreranacionalList);
            em.persist(especialidad);
            for (Carreranacional carreranacionalListCarreranacional : especialidad.getCarreranacionalList()) {
                Especialidad oldEspecialidadidespecialidadOfCarreranacionalListCarreranacional = carreranacionalListCarreranacional.getEspecialidadidespecialidad();
                carreranacionalListCarreranacional.setEspecialidadidespecialidad(especialidad);
                carreranacionalListCarreranacional = em.merge(carreranacionalListCarreranacional);
                if (oldEspecialidadidespecialidadOfCarreranacionalListCarreranacional != null) {
                    oldEspecialidadidespecialidadOfCarreranacionalListCarreranacional.getCarreranacionalList().remove(carreranacionalListCarreranacional);
                    oldEspecialidadidespecialidadOfCarreranacionalListCarreranacional = em.merge(oldEspecialidadidespecialidadOfCarreranacionalListCarreranacional);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Especialidad especialidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especialidad persistentEspecialidad = em.find(Especialidad.class, especialidad.getIdespecialidad());
            List<Carreranacional> carreranacionalListOld = persistentEspecialidad.getCarreranacionalList();
            List<Carreranacional> carreranacionalListNew = especialidad.getCarreranacionalList();
            List<String> illegalOrphanMessages = null;
            for (Carreranacional carreranacionalListOldCarreranacional : carreranacionalListOld) {
                if (!carreranacionalListNew.contains(carreranacionalListOldCarreranacional)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Carreranacional " + carreranacionalListOldCarreranacional + " since its especialidadidespecialidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Carreranacional> attachedCarreranacionalListNew = new ArrayList<Carreranacional>();
            for (Carreranacional carreranacionalListNewCarreranacionalToAttach : carreranacionalListNew) {
                carreranacionalListNewCarreranacionalToAttach = em.getReference(carreranacionalListNewCarreranacionalToAttach.getClass(), carreranacionalListNewCarreranacionalToAttach.getIdcarreranacional());
                attachedCarreranacionalListNew.add(carreranacionalListNewCarreranacionalToAttach);
            }
            carreranacionalListNew = attachedCarreranacionalListNew;
            especialidad.setCarreranacionalList(carreranacionalListNew);
            especialidad = em.merge(especialidad);
            for (Carreranacional carreranacionalListNewCarreranacional : carreranacionalListNew) {
                if (!carreranacionalListOld.contains(carreranacionalListNewCarreranacional)) {
                    Especialidad oldEspecialidadidespecialidadOfCarreranacionalListNewCarreranacional = carreranacionalListNewCarreranacional.getEspecialidadidespecialidad();
                    carreranacionalListNewCarreranacional.setEspecialidadidespecialidad(especialidad);
                    carreranacionalListNewCarreranacional = em.merge(carreranacionalListNewCarreranacional);
                    if (oldEspecialidadidespecialidadOfCarreranacionalListNewCarreranacional != null && !oldEspecialidadidespecialidadOfCarreranacionalListNewCarreranacional.equals(especialidad)) {
                        oldEspecialidadidespecialidadOfCarreranacionalListNewCarreranacional.getCarreranacionalList().remove(carreranacionalListNewCarreranacional);
                        oldEspecialidadidespecialidadOfCarreranacionalListNewCarreranacional = em.merge(oldEspecialidadidespecialidadOfCarreranacionalListNewCarreranacional);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = especialidad.getIdespecialidad();
                if (findEspecialidad(id) == null) {
                    throw new NonexistentEntityException("The especialidad with id " + id + " no longer exists.");
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
            Especialidad especialidad;
            try {
                especialidad = em.getReference(Especialidad.class, id);
                especialidad.getIdespecialidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The especialidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Carreranacional> carreranacionalListOrphanCheck = especialidad.getCarreranacionalList();
            for (Carreranacional carreranacionalListOrphanCheckCarreranacional : carreranacionalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Especialidad (" + especialidad + ") cannot be destroyed since the Carreranacional " + carreranacionalListOrphanCheckCarreranacional + " in its carreranacionalList field has a non-nullable especialidadidespecialidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(especialidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Especialidad> findEspecialidadEntities() {
        return findEspecialidadEntities(true, -1, -1);
    }

    public List<Especialidad> findEspecialidadEntities(int maxResults, int firstResult) {
        return findEspecialidadEntities(false, maxResults, firstResult);
    }

    private List<Especialidad> findEspecialidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Especialidad.class));
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

    public Especialidad findEspecialidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Especialidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEspecialidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Especialidad> rt = cq.from(Especialidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Especialidad findEspecialidadByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e FROM Especialidad e WHERE e.nombreespecialidad= :nombre");
            q.setParameter("nombre", nombre);
            Especialidad p = (Especialidad) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Especialidad findEspecialidadByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e "
                    + "FROM Especialidad e "
                    + "WHERE e.canceladoespecialidad=false AND "
                    + "e.nombreespecialidad= :nombre");
            q.setParameter("nombre", nombre);
            Especialidad p = (Especialidad) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findEspecialidadAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e.nombreespecialidad "
                    + "FROM Especialidad e "
                    + "WHERE e.canceladoespecialidad=false "
                    + "ORDER BY e.nombreespecialidad");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
