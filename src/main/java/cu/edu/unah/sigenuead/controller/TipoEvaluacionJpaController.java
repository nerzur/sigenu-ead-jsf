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
import cu.edu.unah.sigenuead.entity.Asignatura;
import cu.edu.unah.sigenuead.entity.TipoEvaluacion;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class TipoEvaluacionJpaController implements Serializable {

    public TipoEvaluacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoEvaluacion tipoEvaluacion) {
        if (tipoEvaluacion.getAsignaturaList() == null) {
            tipoEvaluacion.setAsignaturaList(new ArrayList<Asignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asignatura> attachedAsignaturaList = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListAsignaturaToAttach : tipoEvaluacion.getAsignaturaList()) {
                asignaturaListAsignaturaToAttach = em.getReference(asignaturaListAsignaturaToAttach.getClass(), asignaturaListAsignaturaToAttach.getAsignaturaId());
                attachedAsignaturaList.add(asignaturaListAsignaturaToAttach);
            }
            tipoEvaluacion.setAsignaturaList(attachedAsignaturaList);
            em.persist(tipoEvaluacion);
            for (Asignatura asignaturaListAsignatura : tipoEvaluacion.getAsignaturaList()) {
                TipoEvaluacion oldTipoEvaluaciontipoEvaluacionIdOfAsignaturaListAsignatura = asignaturaListAsignatura.getTipoEvaluaciontipoEvaluacionId();
                asignaturaListAsignatura.setTipoEvaluaciontipoEvaluacionId(tipoEvaluacion);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
                if (oldTipoEvaluaciontipoEvaluacionIdOfAsignaturaListAsignatura != null) {
                    oldTipoEvaluaciontipoEvaluacionIdOfAsignaturaListAsignatura.getAsignaturaList().remove(asignaturaListAsignatura);
                    oldTipoEvaluaciontipoEvaluacionIdOfAsignaturaListAsignatura = em.merge(oldTipoEvaluaciontipoEvaluacionIdOfAsignaturaListAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoEvaluacion tipoEvaluacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoEvaluacion persistentTipoEvaluacion = em.find(TipoEvaluacion.class, tipoEvaluacion.getTipoEvaluacionId());
            List<Asignatura> asignaturaListOld = persistentTipoEvaluacion.getAsignaturaList();
            List<Asignatura> asignaturaListNew = tipoEvaluacion.getAsignaturaList();
            List<String> illegalOrphanMessages = null;
            for (Asignatura asignaturaListOldAsignatura : asignaturaListOld) {
                if (!asignaturaListNew.contains(asignaturaListOldAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignatura " + asignaturaListOldAsignatura + " since its tipoEvaluaciontipoEvaluacionId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Asignatura> attachedAsignaturaListNew = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListNewAsignaturaToAttach : asignaturaListNew) {
                asignaturaListNewAsignaturaToAttach = em.getReference(asignaturaListNewAsignaturaToAttach.getClass(), asignaturaListNewAsignaturaToAttach.getAsignaturaId());
                attachedAsignaturaListNew.add(asignaturaListNewAsignaturaToAttach);
            }
            asignaturaListNew = attachedAsignaturaListNew;
            tipoEvaluacion.setAsignaturaList(asignaturaListNew);
            tipoEvaluacion = em.merge(tipoEvaluacion);
            for (Asignatura asignaturaListNewAsignatura : asignaturaListNew) {
                if (!asignaturaListOld.contains(asignaturaListNewAsignatura)) {
                    TipoEvaluacion oldTipoEvaluaciontipoEvaluacionIdOfAsignaturaListNewAsignatura = asignaturaListNewAsignatura.getTipoEvaluaciontipoEvaluacionId();
                    asignaturaListNewAsignatura.setTipoEvaluaciontipoEvaluacionId(tipoEvaluacion);
                    asignaturaListNewAsignatura = em.merge(asignaturaListNewAsignatura);
                    if (oldTipoEvaluaciontipoEvaluacionIdOfAsignaturaListNewAsignatura != null && !oldTipoEvaluaciontipoEvaluacionIdOfAsignaturaListNewAsignatura.equals(tipoEvaluacion)) {
                        oldTipoEvaluaciontipoEvaluacionIdOfAsignaturaListNewAsignatura.getAsignaturaList().remove(asignaturaListNewAsignatura);
                        oldTipoEvaluaciontipoEvaluacionIdOfAsignaturaListNewAsignatura = em.merge(oldTipoEvaluaciontipoEvaluacionIdOfAsignaturaListNewAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoEvaluacion.getTipoEvaluacionId();
                if (findTipoEvaluacion(id) == null) {
                    throw new NonexistentEntityException("The tipoEvaluacion with id " + id + " no longer exists.");
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
            TipoEvaluacion tipoEvaluacion;
            try {
                tipoEvaluacion = em.getReference(TipoEvaluacion.class, id);
                tipoEvaluacion.getTipoEvaluacionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoEvaluacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Asignatura> asignaturaListOrphanCheck = tipoEvaluacion.getAsignaturaList();
            for (Asignatura asignaturaListOrphanCheckAsignatura : asignaturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoEvaluacion (" + tipoEvaluacion + ") cannot be destroyed since the Asignatura " + asignaturaListOrphanCheckAsignatura + " in its asignaturaList field has a non-nullable tipoEvaluaciontipoEvaluacionId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoEvaluacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoEvaluacion> findTipoEvaluacionEntities() {
        return findTipoEvaluacionEntities(true, -1, -1);
    }

    public List<TipoEvaluacion> findTipoEvaluacionEntities(int maxResults, int firstResult) {
        return findTipoEvaluacionEntities(false, maxResults, firstResult);
    }

    private List<TipoEvaluacion> findTipoEvaluacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoEvaluacion.class));
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

    public TipoEvaluacion findTipoEvaluacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoEvaluacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoEvaluacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoEvaluacion> rt = cq.from(TipoEvaluacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public TipoEvaluacion findTipoEvaluacionByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT t "
                    + "FROM TipoEvaluacion t "
                    + "WHERE t.tipoEvaluacionNombre= :nombre");
            q.setParameter("nombre", nombre);
            TipoEvaluacion p = (TipoEvaluacion) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public TipoEvaluacion findTipoEvaluacionByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT t "
                    + "FROM TipoEvaluacion t "
                    + "WHERE t.tipoEavluacionCancelado=false AND "
                    + "t.tipoEvaluacionNombre= :nombre");
            q.setParameter("nombre", nombre);
            TipoEvaluacion p = (TipoEvaluacion) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findTipoEvaluacionAvailable() {
        EntityManager em = getEntityManager();
        try {
            List<String> p = em.createQuery("SELECT t.tipoEvaluacionNombre "
                    + "FROM TipoEvaluacion t "
                    + "WHERE t.tipoEavluacionCancelado=false "
                    + "ORDER BY t.tipoEvaluacionNombre").getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
