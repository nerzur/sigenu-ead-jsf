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
import cu.edu.unah.sigenuead.entity.TipoAsignatura;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class TipoAsignaturaJpaController implements Serializable {

    public TipoAsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoAsignatura tipoAsignatura) {
        if (tipoAsignatura.getAsignaturaList() == null) {
            tipoAsignatura.setAsignaturaList(new ArrayList<Asignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asignatura> attachedAsignaturaList = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListAsignaturaToAttach : tipoAsignatura.getAsignaturaList()) {
                asignaturaListAsignaturaToAttach = em.getReference(asignaturaListAsignaturaToAttach.getClass(), asignaturaListAsignaturaToAttach.getAsignaturaId());
                attachedAsignaturaList.add(asignaturaListAsignaturaToAttach);
            }
            tipoAsignatura.setAsignaturaList(attachedAsignaturaList);
            em.persist(tipoAsignatura);
            for (Asignatura asignaturaListAsignatura : tipoAsignatura.getAsignaturaList()) {
                TipoAsignatura oldTipoAsignaturatipoAsignaturaIdOfAsignaturaListAsignatura = asignaturaListAsignatura.getTipoAsignaturatipoAsignaturaId();
                asignaturaListAsignatura.setTipoAsignaturatipoAsignaturaId(tipoAsignatura);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
                if (oldTipoAsignaturatipoAsignaturaIdOfAsignaturaListAsignatura != null) {
                    oldTipoAsignaturatipoAsignaturaIdOfAsignaturaListAsignatura.getAsignaturaList().remove(asignaturaListAsignatura);
                    oldTipoAsignaturatipoAsignaturaIdOfAsignaturaListAsignatura = em.merge(oldTipoAsignaturatipoAsignaturaIdOfAsignaturaListAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoAsignatura tipoAsignatura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAsignatura persistentTipoAsignatura = em.find(TipoAsignatura.class, tipoAsignatura.getTipoAsignaturaId());
            List<Asignatura> asignaturaListOld = persistentTipoAsignatura.getAsignaturaList();
            List<Asignatura> asignaturaListNew = tipoAsignatura.getAsignaturaList();
            List<String> illegalOrphanMessages = null;
            for (Asignatura asignaturaListOldAsignatura : asignaturaListOld) {
                if (!asignaturaListNew.contains(asignaturaListOldAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignatura " + asignaturaListOldAsignatura + " since its tipoAsignaturatipoAsignaturaId field is not nullable.");
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
            tipoAsignatura.setAsignaturaList(asignaturaListNew);
            tipoAsignatura = em.merge(tipoAsignatura);
            for (Asignatura asignaturaListNewAsignatura : asignaturaListNew) {
                if (!asignaturaListOld.contains(asignaturaListNewAsignatura)) {
                    TipoAsignatura oldTipoAsignaturatipoAsignaturaIdOfAsignaturaListNewAsignatura = asignaturaListNewAsignatura.getTipoAsignaturatipoAsignaturaId();
                    asignaturaListNewAsignatura.setTipoAsignaturatipoAsignaturaId(tipoAsignatura);
                    asignaturaListNewAsignatura = em.merge(asignaturaListNewAsignatura);
                    if (oldTipoAsignaturatipoAsignaturaIdOfAsignaturaListNewAsignatura != null && !oldTipoAsignaturatipoAsignaturaIdOfAsignaturaListNewAsignatura.equals(tipoAsignatura)) {
                        oldTipoAsignaturatipoAsignaturaIdOfAsignaturaListNewAsignatura.getAsignaturaList().remove(asignaturaListNewAsignatura);
                        oldTipoAsignaturatipoAsignaturaIdOfAsignaturaListNewAsignatura = em.merge(oldTipoAsignaturatipoAsignaturaIdOfAsignaturaListNewAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoAsignatura.getTipoAsignaturaId();
                if (findTipoAsignatura(id) == null) {
                    throw new NonexistentEntityException("The tipoAsignatura with id " + id + " no longer exists.");
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
            TipoAsignatura tipoAsignatura;
            try {
                tipoAsignatura = em.getReference(TipoAsignatura.class, id);
                tipoAsignatura.getTipoAsignaturaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoAsignatura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Asignatura> asignaturaListOrphanCheck = tipoAsignatura.getAsignaturaList();
            for (Asignatura asignaturaListOrphanCheckAsignatura : asignaturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoAsignatura (" + tipoAsignatura + ") cannot be destroyed since the Asignatura " + asignaturaListOrphanCheckAsignatura + " in its asignaturaList field has a non-nullable tipoAsignaturatipoAsignaturaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoAsignatura> findTipoAsignaturaEntities() {
        return findTipoAsignaturaEntities(true, -1, -1);
    }

    public List<TipoAsignatura> findTipoAsignaturaEntities(int maxResults, int firstResult) {
        return findTipoAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<TipoAsignatura> findTipoAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoAsignatura.class));
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

    public TipoAsignatura findTipoAsignatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoAsignatura> rt = cq.from(TipoAsignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public TipoAsignatura findTipoAsignaturaByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT t "
                    + "FROM TipoAsignatura t "
                    + "WHERE t.tipoAsignaturaNombre= :nombre");
            q.setParameter("nombre", nombre);
            TipoAsignatura p = (TipoAsignatura) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public TipoAsignatura findTipoAsignaturaByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT t "
                    + "FROM TipoAsignatura t "
                    + "WHERE t.tipoAsignaturaCancelado=false AND "
                    + "t.tipoAsignaturaNombre= :nombre");
            q.setParameter("nombre", nombre);
            TipoAsignatura p = (TipoAsignatura) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findTipoAsignaturaAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT t.tipoAsignaturaNombre "
                    + "FROM TipoAsignatura t "
                    + "WHERE t.tipoAsignaturaCancelado=false "
                    + "ORDER BY t.tipoAsignaturaNombre");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
