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
import cu.edu.unah.sigenuead.entity.Cum;
import cu.edu.unah.sigenuead.entity.Facultad;
import cu.edu.unah.sigenuead.entity.FacultadCum;
import cu.edu.unah.sigenuead.entity.FacultadCumCarrera;
import cu.edu.unah.sigenuead.entity.FacultadCumPK;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class FacultadCumJpaController implements Serializable {

    public FacultadCumJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FacultadCum facultadCum) throws PreexistingEntityException, Exception {
        if (facultadCum.getFacultadCumPK() == null) {
            facultadCum.setFacultadCumPK(new FacultadCumPK());
        }
        if (facultadCum.getFacultadCumCarreraList() == null) {
            facultadCum.setFacultadCumCarreraList(new ArrayList<FacultadCumCarrera>());
        }
        facultadCum.getFacultadCumPK().setCumcodigocum(facultadCum.getCum().getCodigocum());
        facultadCum.getFacultadCumPK().setFacultadcodigoarea(facultadCum.getFacultad().getCodigoarea());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cum cum = facultadCum.getCum();
            if (cum != null) {
                cum = em.getReference(cum.getClass(), cum.getCodigocum());
                facultadCum.setCum(cum);
            }
            Facultad facultad = facultadCum.getFacultad();
            if (facultad != null) {
                facultad = em.getReference(facultad.getClass(), facultad.getCodigoarea());
                facultadCum.setFacultad(facultad);
            }
            List<FacultadCumCarrera> attachedFacultadCumCarreraList = new ArrayList<FacultadCumCarrera>();
            for (FacultadCumCarrera facultadCumCarreraListFacultadCumCarreraToAttach : facultadCum.getFacultadCumCarreraList()) {
                facultadCumCarreraListFacultadCumCarreraToAttach = em.getReference(facultadCumCarreraListFacultadCumCarreraToAttach.getClass(), facultadCumCarreraListFacultadCumCarreraToAttach.getFacultadCumCarreraPK());
                attachedFacultadCumCarreraList.add(facultadCumCarreraListFacultadCumCarreraToAttach);
            }
            facultadCum.setFacultadCumCarreraList(attachedFacultadCumCarreraList);
            em.persist(facultadCum);
            if (cum != null) {
                cum.getFacultadCumList().add(facultadCum);
                cum = em.merge(cum);
            }
            if (facultad != null) {
                facultad.getFacultadCumList().add(facultadCum);
                facultad = em.merge(facultad);
            }
            for (FacultadCumCarrera facultadCumCarreraListFacultadCumCarrera : facultadCum.getFacultadCumCarreraList()) {
                FacultadCum oldFacultadCumOfFacultadCumCarreraListFacultadCumCarrera = facultadCumCarreraListFacultadCumCarrera.getFacultadCum();
                facultadCumCarreraListFacultadCumCarrera.setFacultadCum(facultadCum);
                facultadCumCarreraListFacultadCumCarrera = em.merge(facultadCumCarreraListFacultadCumCarrera);
                if (oldFacultadCumOfFacultadCumCarreraListFacultadCumCarrera != null) {
                    oldFacultadCumOfFacultadCumCarreraListFacultadCumCarrera.getFacultadCumCarreraList().remove(facultadCumCarreraListFacultadCumCarrera);
                    oldFacultadCumOfFacultadCumCarreraListFacultadCumCarrera = em.merge(oldFacultadCumOfFacultadCumCarreraListFacultadCumCarrera);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFacultadCum(facultadCum.getFacultadCumPK()) != null) {
                throw new PreexistingEntityException("FacultadCum " + facultadCum + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FacultadCum facultadCum) throws IllegalOrphanException, NonexistentEntityException, Exception {
        facultadCum.getFacultadCumPK().setCumcodigocum(facultadCum.getCum().getCodigocum());
        facultadCum.getFacultadCumPK().setFacultadcodigoarea(facultadCum.getFacultad().getCodigoarea());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacultadCum persistentFacultadCum = em.find(FacultadCum.class, facultadCum.getFacultadCumPK());
            Cum cumOld = persistentFacultadCum.getCum();
            Cum cumNew = facultadCum.getCum();
            Facultad facultadOld = persistentFacultadCum.getFacultad();
            Facultad facultadNew = facultadCum.getFacultad();
            List<FacultadCumCarrera> facultadCumCarreraListOld = persistentFacultadCum.getFacultadCumCarreraList();
            List<FacultadCumCarrera> facultadCumCarreraListNew = facultadCum.getFacultadCumCarreraList();
            List<String> illegalOrphanMessages = null;
            for (FacultadCumCarrera facultadCumCarreraListOldFacultadCumCarrera : facultadCumCarreraListOld) {
                if (!facultadCumCarreraListNew.contains(facultadCumCarreraListOldFacultadCumCarrera)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FacultadCumCarrera " + facultadCumCarreraListOldFacultadCumCarrera + " since its facultadCum field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cumNew != null) {
                cumNew = em.getReference(cumNew.getClass(), cumNew.getCodigocum());
                facultadCum.setCum(cumNew);
            }
            if (facultadNew != null) {
                facultadNew = em.getReference(facultadNew.getClass(), facultadNew.getCodigoarea());
                facultadCum.setFacultad(facultadNew);
            }
            List<FacultadCumCarrera> attachedFacultadCumCarreraListNew = new ArrayList<FacultadCumCarrera>();
            for (FacultadCumCarrera facultadCumCarreraListNewFacultadCumCarreraToAttach : facultadCumCarreraListNew) {
                facultadCumCarreraListNewFacultadCumCarreraToAttach = em.getReference(facultadCumCarreraListNewFacultadCumCarreraToAttach.getClass(), facultadCumCarreraListNewFacultadCumCarreraToAttach.getFacultadCumCarreraPK());
                attachedFacultadCumCarreraListNew.add(facultadCumCarreraListNewFacultadCumCarreraToAttach);
            }
            facultadCumCarreraListNew = attachedFacultadCumCarreraListNew;
            facultadCum.setFacultadCumCarreraList(facultadCumCarreraListNew);
            facultadCum = em.merge(facultadCum);
            if (cumOld != null && !cumOld.equals(cumNew)) {
                cumOld.getFacultadCumList().remove(facultadCum);
                cumOld = em.merge(cumOld);
            }
            if (cumNew != null && !cumNew.equals(cumOld)) {
                cumNew.getFacultadCumList().add(facultadCum);
                cumNew = em.merge(cumNew);
            }
            if (facultadOld != null && !facultadOld.equals(facultadNew)) {
                facultadOld.getFacultadCumList().remove(facultadCum);
                facultadOld = em.merge(facultadOld);
            }
            if (facultadNew != null && !facultadNew.equals(facultadOld)) {
                facultadNew.getFacultadCumList().add(facultadCum);
                facultadNew = em.merge(facultadNew);
            }
            for (FacultadCumCarrera facultadCumCarreraListNewFacultadCumCarrera : facultadCumCarreraListNew) {
                if (!facultadCumCarreraListOld.contains(facultadCumCarreraListNewFacultadCumCarrera)) {
                    FacultadCum oldFacultadCumOfFacultadCumCarreraListNewFacultadCumCarrera = facultadCumCarreraListNewFacultadCumCarrera.getFacultadCum();
                    facultadCumCarreraListNewFacultadCumCarrera.setFacultadCum(facultadCum);
                    facultadCumCarreraListNewFacultadCumCarrera = em.merge(facultadCumCarreraListNewFacultadCumCarrera);
                    if (oldFacultadCumOfFacultadCumCarreraListNewFacultadCumCarrera != null && !oldFacultadCumOfFacultadCumCarreraListNewFacultadCumCarrera.equals(facultadCum)) {
                        oldFacultadCumOfFacultadCumCarreraListNewFacultadCumCarrera.getFacultadCumCarreraList().remove(facultadCumCarreraListNewFacultadCumCarrera);
                        oldFacultadCumOfFacultadCumCarreraListNewFacultadCumCarrera = em.merge(oldFacultadCumOfFacultadCumCarreraListNewFacultadCumCarrera);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                FacultadCumPK id = facultadCum.getFacultadCumPK();
                if (findFacultadCum(id) == null) {
                    throw new NonexistentEntityException("The facultadCum with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(FacultadCumPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacultadCum facultadCum;
            try {
                facultadCum = em.getReference(FacultadCum.class, id);
                facultadCum.getFacultadCumPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facultadCum with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<FacultadCumCarrera> facultadCumCarreraListOrphanCheck = facultadCum.getFacultadCumCarreraList();
            for (FacultadCumCarrera facultadCumCarreraListOrphanCheckFacultadCumCarrera : facultadCumCarreraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FacultadCum (" + facultadCum + ") cannot be destroyed since the FacultadCumCarrera " + facultadCumCarreraListOrphanCheckFacultadCumCarrera + " in its facultadCumCarreraList field has a non-nullable facultadCum field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cum cum = facultadCum.getCum();
            if (cum != null) {
                cum.getFacultadCumList().remove(facultadCum);
                cum = em.merge(cum);
            }
            Facultad facultad = facultadCum.getFacultad();
            if (facultad != null) {
                facultad.getFacultadCumList().remove(facultadCum);
                facultad = em.merge(facultad);
            }
            em.remove(facultadCum);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FacultadCum> findFacultadCumEntities() {
        return findFacultadCumEntities(true, -1, -1);
    }

    public List<FacultadCum> findFacultadCumEntities(int maxResults, int firstResult) {
        return findFacultadCumEntities(false, maxResults, firstResult);
    }

    private List<FacultadCum> findFacultadCumEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FacultadCum.class));
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

    public FacultadCum findFacultadCum(FacultadCumPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FacultadCum.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacultadCumCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FacultadCum> rt = cq.from(FacultadCum.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
