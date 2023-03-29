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
import cu.edu.unah.sigenuead.entity.MotivoBaja;
import java.util.ArrayList;
import java.util.List;
import cu.edu.unah.sigenuead.entity.Baja;
import cu.edu.unah.sigenuead.entity.TipoBaja;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class TipoBajaJpaController implements Serializable {

    public TipoBajaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoBaja tipoBaja) {
        if (tipoBaja.getMotivoBajaList() == null) {
            tipoBaja.setMotivoBajaList(new ArrayList<MotivoBaja>());
        }
        if (tipoBaja.getBajaList() == null) {
            tipoBaja.setBajaList(new ArrayList<Baja>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<MotivoBaja> attachedMotivoBajaList = new ArrayList<MotivoBaja>();
            for (MotivoBaja motivoBajaListMotivoBajaToAttach : tipoBaja.getMotivoBajaList()) {
                motivoBajaListMotivoBajaToAttach = em.getReference(motivoBajaListMotivoBajaToAttach.getClass(), motivoBajaListMotivoBajaToAttach.getIdMotivoBaja());
                attachedMotivoBajaList.add(motivoBajaListMotivoBajaToAttach);
            }
            tipoBaja.setMotivoBajaList(attachedMotivoBajaList);
            List<Baja> attachedBajaList = new ArrayList<Baja>();
            for (Baja bajaListBajaToAttach : tipoBaja.getBajaList()) {
                bajaListBajaToAttach = em.getReference(bajaListBajaToAttach.getClass(), bajaListBajaToAttach.getBajaPK());
                attachedBajaList.add(bajaListBajaToAttach);
            }
            tipoBaja.setBajaList(attachedBajaList);
            em.persist(tipoBaja);
            for (MotivoBaja motivoBajaListMotivoBaja : tipoBaja.getMotivoBajaList()) {
                TipoBaja oldTipoBajaidTipoBajaOfMotivoBajaListMotivoBaja = motivoBajaListMotivoBaja.getTipoBajaidTipoBaja();
                motivoBajaListMotivoBaja.setTipoBajaidTipoBaja(tipoBaja);
                motivoBajaListMotivoBaja = em.merge(motivoBajaListMotivoBaja);
                if (oldTipoBajaidTipoBajaOfMotivoBajaListMotivoBaja != null) {
                    oldTipoBajaidTipoBajaOfMotivoBajaListMotivoBaja.getMotivoBajaList().remove(motivoBajaListMotivoBaja);
                    oldTipoBajaidTipoBajaOfMotivoBajaListMotivoBaja = em.merge(oldTipoBajaidTipoBajaOfMotivoBajaListMotivoBaja);
                }
            }
            for (Baja bajaListBaja : tipoBaja.getBajaList()) {
                TipoBaja oldTipoBajaOfBajaListBaja = bajaListBaja.getTipoBaja();
                bajaListBaja.setTipoBaja(tipoBaja);
                bajaListBaja = em.merge(bajaListBaja);
                if (oldTipoBajaOfBajaListBaja != null) {
                    oldTipoBajaOfBajaListBaja.getBajaList().remove(bajaListBaja);
                    oldTipoBajaOfBajaListBaja = em.merge(oldTipoBajaOfBajaListBaja);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoBaja tipoBaja) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoBaja persistentTipoBaja = em.find(TipoBaja.class, tipoBaja.getIdTipoBaja());
            List<MotivoBaja> motivoBajaListOld = persistentTipoBaja.getMotivoBajaList();
            List<MotivoBaja> motivoBajaListNew = tipoBaja.getMotivoBajaList();
            List<Baja> bajaListOld = persistentTipoBaja.getBajaList();
            List<Baja> bajaListNew = tipoBaja.getBajaList();
            List<String> illegalOrphanMessages = null;
            for (MotivoBaja motivoBajaListOldMotivoBaja : motivoBajaListOld) {
                if (!motivoBajaListNew.contains(motivoBajaListOldMotivoBaja)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MotivoBaja " + motivoBajaListOldMotivoBaja + " since its tipoBajaidTipoBaja field is not nullable.");
                }
            }
            for (Baja bajaListOldBaja : bajaListOld) {
                if (!bajaListNew.contains(bajaListOldBaja)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Baja " + bajaListOldBaja + " since its tipoBaja field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<MotivoBaja> attachedMotivoBajaListNew = new ArrayList<MotivoBaja>();
            for (MotivoBaja motivoBajaListNewMotivoBajaToAttach : motivoBajaListNew) {
                motivoBajaListNewMotivoBajaToAttach = em.getReference(motivoBajaListNewMotivoBajaToAttach.getClass(), motivoBajaListNewMotivoBajaToAttach.getIdMotivoBaja());
                attachedMotivoBajaListNew.add(motivoBajaListNewMotivoBajaToAttach);
            }
            motivoBajaListNew = attachedMotivoBajaListNew;
            tipoBaja.setMotivoBajaList(motivoBajaListNew);
            List<Baja> attachedBajaListNew = new ArrayList<Baja>();
            for (Baja bajaListNewBajaToAttach : bajaListNew) {
                bajaListNewBajaToAttach = em.getReference(bajaListNewBajaToAttach.getClass(), bajaListNewBajaToAttach.getBajaPK());
                attachedBajaListNew.add(bajaListNewBajaToAttach);
            }
            bajaListNew = attachedBajaListNew;
            tipoBaja.setBajaList(bajaListNew);
            tipoBaja = em.merge(tipoBaja);
            for (MotivoBaja motivoBajaListNewMotivoBaja : motivoBajaListNew) {
                if (!motivoBajaListOld.contains(motivoBajaListNewMotivoBaja)) {
                    TipoBaja oldTipoBajaidTipoBajaOfMotivoBajaListNewMotivoBaja = motivoBajaListNewMotivoBaja.getTipoBajaidTipoBaja();
                    motivoBajaListNewMotivoBaja.setTipoBajaidTipoBaja(tipoBaja);
                    motivoBajaListNewMotivoBaja = em.merge(motivoBajaListNewMotivoBaja);
                    if (oldTipoBajaidTipoBajaOfMotivoBajaListNewMotivoBaja != null && !oldTipoBajaidTipoBajaOfMotivoBajaListNewMotivoBaja.equals(tipoBaja)) {
                        oldTipoBajaidTipoBajaOfMotivoBajaListNewMotivoBaja.getMotivoBajaList().remove(motivoBajaListNewMotivoBaja);
                        oldTipoBajaidTipoBajaOfMotivoBajaListNewMotivoBaja = em.merge(oldTipoBajaidTipoBajaOfMotivoBajaListNewMotivoBaja);
                    }
                }
            }
            for (Baja bajaListNewBaja : bajaListNew) {
                if (!bajaListOld.contains(bajaListNewBaja)) {
                    TipoBaja oldTipoBajaOfBajaListNewBaja = bajaListNewBaja.getTipoBaja();
                    bajaListNewBaja.setTipoBaja(tipoBaja);
                    bajaListNewBaja = em.merge(bajaListNewBaja);
                    if (oldTipoBajaOfBajaListNewBaja != null && !oldTipoBajaOfBajaListNewBaja.equals(tipoBaja)) {
                        oldTipoBajaOfBajaListNewBaja.getBajaList().remove(bajaListNewBaja);
                        oldTipoBajaOfBajaListNewBaja = em.merge(oldTipoBajaOfBajaListNewBaja);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoBaja.getIdTipoBaja();
                if (findTipoBaja(id) == null) {
                    throw new NonexistentEntityException("The tipoBaja with id " + id + " no longer exists.");
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
            TipoBaja tipoBaja;
            try {
                tipoBaja = em.getReference(TipoBaja.class, id);
                tipoBaja.getIdTipoBaja();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoBaja with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MotivoBaja> motivoBajaListOrphanCheck = tipoBaja.getMotivoBajaList();
            for (MotivoBaja motivoBajaListOrphanCheckMotivoBaja : motivoBajaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoBaja (" + tipoBaja + ") cannot be destroyed since the MotivoBaja " + motivoBajaListOrphanCheckMotivoBaja + " in its motivoBajaList field has a non-nullable tipoBajaidTipoBaja field.");
            }
            List<Baja> bajaListOrphanCheck = tipoBaja.getBajaList();
            for (Baja bajaListOrphanCheckBaja : bajaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoBaja (" + tipoBaja + ") cannot be destroyed since the Baja " + bajaListOrphanCheckBaja + " in its bajaList field has a non-nullable tipoBaja field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoBaja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoBaja> findTipoBajaEntities() {
        return findTipoBajaEntities(true, -1, -1);
    }

    public List<TipoBaja> findTipoBajaEntities(int maxResults, int firstResult) {
        return findTipoBajaEntities(false, maxResults, firstResult);
    }

    private List<TipoBaja> findTipoBajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoBaja.class));
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

    public TipoBaja findTipoBaja(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoBaja.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoBajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoBaja> rt = cq.from(TipoBaja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public TipoBaja findTipoBajaByNombre(String tipo) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT t FROM TipoBaja t WHERE t.nombreTipoBaja=:tipo");
            q.setParameter("tipo", tipo);
            TipoBaja p = (TipoBaja) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findTipoBajaAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT t.nombreTipoBaja FROM TipoBaja t WHERE t.cancelado=false");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
