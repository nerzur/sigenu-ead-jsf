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
import cu.edu.unah.sigenuead.entity.TipoBaja;
import cu.edu.unah.sigenuead.entity.Baja;
import cu.edu.unah.sigenuead.entity.MotivoBaja;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class MotivoBajaJpaController implements Serializable {

    public MotivoBajaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MotivoBaja motivoBaja) {
        if (motivoBaja.getBajaList() == null) {
            motivoBaja.setBajaList(new ArrayList<Baja>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoBaja tipoBajaidTipoBaja = motivoBaja.getTipoBajaidTipoBaja();
            if (tipoBajaidTipoBaja != null) {
                tipoBajaidTipoBaja = em.getReference(tipoBajaidTipoBaja.getClass(), tipoBajaidTipoBaja.getIdTipoBaja());
                motivoBaja.setTipoBajaidTipoBaja(tipoBajaidTipoBaja);
            }
            List<Baja> attachedBajaList = new ArrayList<Baja>();
            for (Baja bajaListBajaToAttach : motivoBaja.getBajaList()) {
                bajaListBajaToAttach = em.getReference(bajaListBajaToAttach.getClass(), bajaListBajaToAttach.getBajaPK());
                attachedBajaList.add(bajaListBajaToAttach);
            }
            motivoBaja.setBajaList(attachedBajaList);
            em.persist(motivoBaja);
            if (tipoBajaidTipoBaja != null) {
                tipoBajaidTipoBaja.getMotivoBajaList().add(motivoBaja);
                tipoBajaidTipoBaja = em.merge(tipoBajaidTipoBaja);
            }
            for (Baja bajaListBaja : motivoBaja.getBajaList()) {
                MotivoBaja oldMotivoBajaOfBajaListBaja = bajaListBaja.getMotivoBaja();
                bajaListBaja.setMotivoBaja(motivoBaja);
                bajaListBaja = em.merge(bajaListBaja);
                if (oldMotivoBajaOfBajaListBaja != null) {
                    oldMotivoBajaOfBajaListBaja.getBajaList().remove(bajaListBaja);
                    oldMotivoBajaOfBajaListBaja = em.merge(oldMotivoBajaOfBajaListBaja);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MotivoBaja motivoBaja) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MotivoBaja persistentMotivoBaja = em.find(MotivoBaja.class, motivoBaja.getIdMotivoBaja());
            TipoBaja tipoBajaidTipoBajaOld = persistentMotivoBaja.getTipoBajaidTipoBaja();
            TipoBaja tipoBajaidTipoBajaNew = motivoBaja.getTipoBajaidTipoBaja();
            List<Baja> bajaListOld = persistentMotivoBaja.getBajaList();
            List<Baja> bajaListNew = motivoBaja.getBajaList();
            List<String> illegalOrphanMessages = null;
            for (Baja bajaListOldBaja : bajaListOld) {
                if (!bajaListNew.contains(bajaListOldBaja)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Baja " + bajaListOldBaja + " since its motivoBaja field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tipoBajaidTipoBajaNew != null) {
                tipoBajaidTipoBajaNew = em.getReference(tipoBajaidTipoBajaNew.getClass(), tipoBajaidTipoBajaNew.getIdTipoBaja());
                motivoBaja.setTipoBajaidTipoBaja(tipoBajaidTipoBajaNew);
            }
            List<Baja> attachedBajaListNew = new ArrayList<Baja>();
            for (Baja bajaListNewBajaToAttach : bajaListNew) {
                bajaListNewBajaToAttach = em.getReference(bajaListNewBajaToAttach.getClass(), bajaListNewBajaToAttach.getBajaPK());
                attachedBajaListNew.add(bajaListNewBajaToAttach);
            }
            bajaListNew = attachedBajaListNew;
            motivoBaja.setBajaList(bajaListNew);
            motivoBaja = em.merge(motivoBaja);
            if (tipoBajaidTipoBajaOld != null && !tipoBajaidTipoBajaOld.equals(tipoBajaidTipoBajaNew)) {
                tipoBajaidTipoBajaOld.getMotivoBajaList().remove(motivoBaja);
                tipoBajaidTipoBajaOld = em.merge(tipoBajaidTipoBajaOld);
            }
            if (tipoBajaidTipoBajaNew != null && !tipoBajaidTipoBajaNew.equals(tipoBajaidTipoBajaOld)) {
                tipoBajaidTipoBajaNew.getMotivoBajaList().add(motivoBaja);
                tipoBajaidTipoBajaNew = em.merge(tipoBajaidTipoBajaNew);
            }
            for (Baja bajaListNewBaja : bajaListNew) {
                if (!bajaListOld.contains(bajaListNewBaja)) {
                    MotivoBaja oldMotivoBajaOfBajaListNewBaja = bajaListNewBaja.getMotivoBaja();
                    bajaListNewBaja.setMotivoBaja(motivoBaja);
                    bajaListNewBaja = em.merge(bajaListNewBaja);
                    if (oldMotivoBajaOfBajaListNewBaja != null && !oldMotivoBajaOfBajaListNewBaja.equals(motivoBaja)) {
                        oldMotivoBajaOfBajaListNewBaja.getBajaList().remove(bajaListNewBaja);
                        oldMotivoBajaOfBajaListNewBaja = em.merge(oldMotivoBajaOfBajaListNewBaja);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = motivoBaja.getIdMotivoBaja();
                if (findMotivoBaja(id) == null) {
                    throw new NonexistentEntityException("The motivoBaja with id " + id + " no longer exists.");
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
            MotivoBaja motivoBaja;
            try {
                motivoBaja = em.getReference(MotivoBaja.class, id);
                motivoBaja.getIdMotivoBaja();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The motivoBaja with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Baja> bajaListOrphanCheck = motivoBaja.getBajaList();
            for (Baja bajaListOrphanCheckBaja : bajaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MotivoBaja (" + motivoBaja + ") cannot be destroyed since the Baja " + bajaListOrphanCheckBaja + " in its bajaList field has a non-nullable motivoBaja field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoBaja tipoBajaidTipoBaja = motivoBaja.getTipoBajaidTipoBaja();
            if (tipoBajaidTipoBaja != null) {
                tipoBajaidTipoBaja.getMotivoBajaList().remove(motivoBaja);
                tipoBajaidTipoBaja = em.merge(tipoBajaidTipoBaja);
            }
            em.remove(motivoBaja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MotivoBaja> findMotivoBajaEntities() {
        return findMotivoBajaEntities(true, -1, -1);
    }

    public List<MotivoBaja> findMotivoBajaEntities(int maxResults, int firstResult) {
        return findMotivoBajaEntities(false, maxResults, firstResult);
    }

    private List<MotivoBaja> findMotivoBajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MotivoBaja.class));
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

    public MotivoBaja findMotivoBaja(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MotivoBaja.class, id);
        } finally {
            em.close();
        }
    }

    public int getMotivoBajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MotivoBaja> rt = cq.from(MotivoBaja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public MotivoBaja findMotivoBajaByNombre(String motivo) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m FROM MotivoBaja m WHERE m.nombreMotivoBaja=:tipo");
            q.setParameter("tipo", motivo);
            MotivoBaja p = (MotivoBaja) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findMotivoBajaAvailable(String tipo) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT t.nombreMotivoBaja FROM MotivoBaja t WHERE t.cancelado=false AND t.tipoBajaidTipoBaja.nombreTipoBaja=:tipo");
            q.setParameter("tipo", tipo);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
