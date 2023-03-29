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
import cu.edu.unah.sigenuead.entity.Especialidad;
import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.Carreranacional;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class CarreranacionalJpaController implements Serializable {

    public CarreranacionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carreranacional carreranacional) {
        if (carreranacional.getCarreraList() == null) {
            carreranacional.setCarreraList(new ArrayList<Carrera>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especialidad especialidadidespecialidad = carreranacional.getEspecialidadidespecialidad();
            if (especialidadidespecialidad != null) {
                especialidadidespecialidad = em.getReference(especialidadidespecialidad.getClass(), especialidadidespecialidad.getIdespecialidad());
                carreranacional.setEspecialidadidespecialidad(especialidadidespecialidad);
            }
            List<Carrera> attachedCarreraList = new ArrayList<Carrera>();
            for (Carrera carreraListCarreraToAttach : carreranacional.getCarreraList()) {
                carreraListCarreraToAttach = em.getReference(carreraListCarreraToAttach.getClass(), carreraListCarreraToAttach.getIdcarrera());
                attachedCarreraList.add(carreraListCarreraToAttach);
            }
            carreranacional.setCarreraList(attachedCarreraList);
            em.persist(carreranacional);
            if (especialidadidespecialidad != null) {
                especialidadidespecialidad.getCarreranacionalList().add(carreranacional);
                especialidadidespecialidad = em.merge(especialidadidespecialidad);
            }
            for (Carrera carreraListCarrera : carreranacional.getCarreraList()) {
                Carreranacional oldCarreranacionalidcarreranacionalOfCarreraListCarrera = carreraListCarrera.getCarreranacionalidcarreranacional();
                carreraListCarrera.setCarreranacionalidcarreranacional(carreranacional);
                carreraListCarrera = em.merge(carreraListCarrera);
                if (oldCarreranacionalidcarreranacionalOfCarreraListCarrera != null) {
                    oldCarreranacionalidcarreranacionalOfCarreraListCarrera.getCarreraList().remove(carreraListCarrera);
                    oldCarreranacionalidcarreranacionalOfCarreraListCarrera = em.merge(oldCarreranacionalidcarreranacionalOfCarreraListCarrera);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Carreranacional carreranacional) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carreranacional persistentCarreranacional = em.find(Carreranacional.class, carreranacional.getIdcarreranacional());
            Especialidad especialidadidespecialidadOld = persistentCarreranacional.getEspecialidadidespecialidad();
            Especialidad especialidadidespecialidadNew = carreranacional.getEspecialidadidespecialidad();
            List<Carrera> carreraListOld = persistentCarreranacional.getCarreraList();
            List<Carrera> carreraListNew = carreranacional.getCarreraList();
            List<String> illegalOrphanMessages = null;
            for (Carrera carreraListOldCarrera : carreraListOld) {
                if (!carreraListNew.contains(carreraListOldCarrera)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Carrera " + carreraListOldCarrera + " since its carreranacionalidcarreranacional field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (especialidadidespecialidadNew != null) {
                especialidadidespecialidadNew = em.getReference(especialidadidespecialidadNew.getClass(), especialidadidespecialidadNew.getIdespecialidad());
                carreranacional.setEspecialidadidespecialidad(especialidadidespecialidadNew);
            }
            List<Carrera> attachedCarreraListNew = new ArrayList<Carrera>();
            for (Carrera carreraListNewCarreraToAttach : carreraListNew) {
                carreraListNewCarreraToAttach = em.getReference(carreraListNewCarreraToAttach.getClass(), carreraListNewCarreraToAttach.getIdcarrera());
                attachedCarreraListNew.add(carreraListNewCarreraToAttach);
            }
            carreraListNew = attachedCarreraListNew;
            carreranacional.setCarreraList(carreraListNew);
            carreranacional = em.merge(carreranacional);
            if (especialidadidespecialidadOld != null && !especialidadidespecialidadOld.equals(especialidadidespecialidadNew)) {
                especialidadidespecialidadOld.getCarreranacionalList().remove(carreranacional);
                especialidadidespecialidadOld = em.merge(especialidadidespecialidadOld);
            }
            if (especialidadidespecialidadNew != null && !especialidadidespecialidadNew.equals(especialidadidespecialidadOld)) {
                especialidadidespecialidadNew.getCarreranacionalList().add(carreranacional);
                especialidadidespecialidadNew = em.merge(especialidadidespecialidadNew);
            }
            for (Carrera carreraListNewCarrera : carreraListNew) {
                if (!carreraListOld.contains(carreraListNewCarrera)) {
                    Carreranacional oldCarreranacionalidcarreranacionalOfCarreraListNewCarrera = carreraListNewCarrera.getCarreranacionalidcarreranacional();
                    carreraListNewCarrera.setCarreranacionalidcarreranacional(carreranacional);
                    carreraListNewCarrera = em.merge(carreraListNewCarrera);
                    if (oldCarreranacionalidcarreranacionalOfCarreraListNewCarrera != null && !oldCarreranacionalidcarreranacionalOfCarreraListNewCarrera.equals(carreranacional)) {
                        oldCarreranacionalidcarreranacionalOfCarreraListNewCarrera.getCarreraList().remove(carreraListNewCarrera);
                        oldCarreranacionalidcarreranacionalOfCarreraListNewCarrera = em.merge(oldCarreranacionalidcarreranacionalOfCarreraListNewCarrera);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = carreranacional.getIdcarreranacional();
                if (findCarreranacional(id) == null) {
                    throw new NonexistentEntityException("The carreranacional with id " + id + " no longer exists.");
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
            Carreranacional carreranacional;
            try {
                carreranacional = em.getReference(Carreranacional.class, id);
                carreranacional.getIdcarreranacional();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carreranacional with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Carrera> carreraListOrphanCheck = carreranacional.getCarreraList();
            for (Carrera carreraListOrphanCheckCarrera : carreraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Carreranacional (" + carreranacional + ") cannot be destroyed since the Carrera " + carreraListOrphanCheckCarrera + " in its carreraList field has a non-nullable carreranacionalidcarreranacional field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Especialidad especialidadidespecialidad = carreranacional.getEspecialidadidespecialidad();
            if (especialidadidespecialidad != null) {
                especialidadidespecialidad.getCarreranacionalList().remove(carreranacional);
                especialidadidespecialidad = em.merge(especialidadidespecialidad);
            }
            em.remove(carreranacional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Carreranacional> findCarreranacionalEntities() {
        return findCarreranacionalEntities(true, -1, -1);
    }

    public List<Carreranacional> findCarreranacionalEntities(int maxResults, int firstResult) {
        return findCarreranacionalEntities(false, maxResults, firstResult);
    }

    private List<Carreranacional> findCarreranacionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carreranacional.class));
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

    public Carreranacional findCarreranacional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carreranacional.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarreranacionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carreranacional> rt = cq.from(Carreranacional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Carreranacional findCarreraNacionalByNombre(String nombre, int especialidad) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Carreranacional c "
                    + "WHERE c.especialidadidespecialidad.idespecialidad= :especialidad AND "
                    + "c.nombrecarreranacional= :nombre");
            q.setParameter("especialidad", especialidad);
            q.setParameter("nombre", nombre);
            Carreranacional p = (Carreranacional) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Carreranacional findCarreraNacionalByCodigo(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Carreranacional c "
                    + "WHERE c.codigocarreranacional= :nombre");
            q.setParameter("nombre", nombre);
            Carreranacional p = (Carreranacional) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Carreranacional findCarreraNacionalByNombreAvailable(String nombre, int especialidad) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Carreranacional c "
                    + "WHERE c.canceladocarreranacional=false AND "
                    + "c.especialidadidespecialidad.idespecialidad= :especialidad AND "
                    + "c.nombrecarreranacional= :nombre");
            q.setParameter("especialidad", especialidad);
            q.setParameter("nombre", nombre);
            Carreranacional p = (Carreranacional) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findCarreranacionalAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c.nombrecarreranacional "
                    + "FROM Carreranacional c "
                    + "WHERE c.canceladocarreranacional=false "
                    + "ORDER BY c.nombrecarreranacional");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findCarreranacionalAvailableByEspecialidad(int especialidadId) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c.nombrecarreranacional "
                    + "FROM Carreranacional c "
                    + "WHERE c.especialidadidespecialidad.idespecialidad= :especialidad AND "
                    + "c.canceladocarreranacional=false "
                    + "ORDER BY c.nombrecarreranacional");
            q.setParameter("especialidad", especialidadId);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Carreranacional findCarreraNacionalByNombre(String carrera) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Carreranacional c "
                    + "WHERE c.canceladocarreranacional=false AND "
                    + "c.nombrecarreranacional= :carrera");
            q.setParameter("carrera", carrera);
            Carreranacional p = (Carreranacional) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
