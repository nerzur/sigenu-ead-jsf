/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Planestudio;
import cu.edu.unah.sigenuead.entity.Tipoplanestudio;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class TipoplanestudioJpaController implements Serializable {

    public TipoplanestudioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoplanestudio tipoplanestudio) throws PreexistingEntityException, Exception {
        if (tipoplanestudio.getPlanestudioList() == null) {
            tipoplanestudio.setPlanestudioList(new ArrayList<Planestudio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Planestudio> attachedPlanestudioList = new ArrayList<Planestudio>();
            for (Planestudio planestudioListPlanestudioToAttach : tipoplanestudio.getPlanestudioList()) {
                planestudioListPlanestudioToAttach = em.getReference(planestudioListPlanestudioToAttach.getClass(), planestudioListPlanestudioToAttach.getIdplanestudio());
                attachedPlanestudioList.add(planestudioListPlanestudioToAttach);
            }
            tipoplanestudio.setPlanestudioList(attachedPlanestudioList);
            em.persist(tipoplanestudio);
            for (Planestudio planestudioListPlanestudio : tipoplanestudio.getPlanestudioList()) {
                Tipoplanestudio oldTipoplanestudionombretipoplanestudioOfPlanestudioListPlanestudio = planestudioListPlanestudio.getTipoplanestudionombretipoplanestudio();
                planestudioListPlanestudio.setTipoplanestudionombretipoplanestudio(tipoplanestudio);
                planestudioListPlanestudio = em.merge(planestudioListPlanestudio);
                if (oldTipoplanestudionombretipoplanestudioOfPlanestudioListPlanestudio != null) {
                    oldTipoplanestudionombretipoplanestudioOfPlanestudioListPlanestudio.getPlanestudioList().remove(planestudioListPlanestudio);
                    oldTipoplanestudionombretipoplanestudioOfPlanestudioListPlanestudio = em.merge(oldTipoplanestudionombretipoplanestudioOfPlanestudioListPlanestudio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoplanestudio(tipoplanestudio.getNombretipoplanestudio()) != null) {
                throw new PreexistingEntityException("Tipoplanestudio " + tipoplanestudio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoplanestudio tipoplanestudio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoplanestudio persistentTipoplanestudio = em.find(Tipoplanestudio.class, tipoplanestudio.getNombretipoplanestudio());
            List<Planestudio> planestudioListOld = persistentTipoplanestudio.getPlanestudioList();
            List<Planestudio> planestudioListNew = tipoplanestudio.getPlanestudioList();
            List<String> illegalOrphanMessages = null;
            for (Planestudio planestudioListOldPlanestudio : planestudioListOld) {
                if (!planestudioListNew.contains(planestudioListOldPlanestudio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Planestudio " + planestudioListOldPlanestudio + " since its tipoplanestudionombretipoplanestudio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Planestudio> attachedPlanestudioListNew = new ArrayList<Planestudio>();
            for (Planestudio planestudioListNewPlanestudioToAttach : planestudioListNew) {
                planestudioListNewPlanestudioToAttach = em.getReference(planestudioListNewPlanestudioToAttach.getClass(), planestudioListNewPlanestudioToAttach.getIdplanestudio());
                attachedPlanestudioListNew.add(planestudioListNewPlanestudioToAttach);
            }
            planestudioListNew = attachedPlanestudioListNew;
            tipoplanestudio.setPlanestudioList(planestudioListNew);
            tipoplanestudio = em.merge(tipoplanestudio);
            for (Planestudio planestudioListNewPlanestudio : planestudioListNew) {
                if (!planestudioListOld.contains(planestudioListNewPlanestudio)) {
                    Tipoplanestudio oldTipoplanestudionombretipoplanestudioOfPlanestudioListNewPlanestudio = planestudioListNewPlanestudio.getTipoplanestudionombretipoplanestudio();
                    planestudioListNewPlanestudio.setTipoplanestudionombretipoplanestudio(tipoplanestudio);
                    planestudioListNewPlanestudio = em.merge(planestudioListNewPlanestudio);
                    if (oldTipoplanestudionombretipoplanestudioOfPlanestudioListNewPlanestudio != null && !oldTipoplanestudionombretipoplanestudioOfPlanestudioListNewPlanestudio.equals(tipoplanestudio)) {
                        oldTipoplanestudionombretipoplanestudioOfPlanestudioListNewPlanestudio.getPlanestudioList().remove(planestudioListNewPlanestudio);
                        oldTipoplanestudionombretipoplanestudioOfPlanestudioListNewPlanestudio = em.merge(oldTipoplanestudionombretipoplanestudioOfPlanestudioListNewPlanestudio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoplanestudio.getNombretipoplanestudio();
                if (findTipoplanestudio(id) == null) {
                    throw new NonexistentEntityException("The tipoplanestudio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoplanestudio tipoplanestudio;
            try {
                tipoplanestudio = em.getReference(Tipoplanestudio.class, id);
                tipoplanestudio.getNombretipoplanestudio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoplanestudio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Planestudio> planestudioListOrphanCheck = tipoplanestudio.getPlanestudioList();
            for (Planestudio planestudioListOrphanCheckPlanestudio : planestudioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipoplanestudio (" + tipoplanestudio + ") cannot be destroyed since the Planestudio " + planestudioListOrphanCheckPlanestudio + " in its planestudioList field has a non-nullable tipoplanestudionombretipoplanestudio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoplanestudio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoplanestudio> findTipoplanestudioEntities() {
        return findTipoplanestudioEntities(true, -1, -1);
    }

    public List<Tipoplanestudio> findTipoplanestudioEntities(int maxResults, int firstResult) {
        return findTipoplanestudioEntities(false, maxResults, firstResult);
    }

    private List<Tipoplanestudio> findTipoplanestudioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoplanestudio.class));
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

    public Tipoplanestudio findTipoplanestudio(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoplanestudio.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoplanestudioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoplanestudio> rt = cq.from(Tipoplanestudio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<String> findTipoPlanEstudioAvailable() {
        EntityManager em = getEntityManager();
        try {
            List<String> p = em.createQuery("SELECT t.nombretipoplanestudio "
                    + "FROM Tipoplanestudio t "
                    + "WHERE t.tipoplanestudiocancelado=false "
                    + "ORDER BY t.nombretipoplanestudio").getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Tipoplanestudio findTipoPlanEstudioByNombreAvailable(String tipo) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT t FROM Tipoplanestudio t WHERE t.nombretipoplanestudio= :nombre and t.tipoplanestudiocancelado=false");
            q.setParameter("nombre", tipo);
            return (Tipoplanestudio) q.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
