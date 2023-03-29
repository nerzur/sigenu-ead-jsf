/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.controller.exceptions.PreexistingEntityException;
import cu.edu.unah.sigenuead.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author claudy
 */
public class AuthoritiesJpaController implements Serializable {

    public AuthoritiesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Authorities authorities) throws PreexistingEntityException, Exception {
        if (authorities.getAuthoritiesPK() == null) {
            authorities.setAuthoritiesPK(new AuthoritiesPK());
        }
        if (authorities.getFacultadList() == null) {
            authorities.setFacultadList(new ArrayList<Facultad>());
        }
        if (authorities.getCumList() == null) {
            authorities.setCumList(new ArrayList<Cum>());
        }
        authorities.getAuthoritiesPK().setUsername(authorities.getUsers().getUsername());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users = authorities.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getUsername());
                authorities.setUsers(users);
            }
            List<Facultad> attachedFacultadList = new ArrayList<Facultad>();
            for (Facultad facultadListFacultadToAttach : authorities.getFacultadList()) {
                facultadListFacultadToAttach = em.getReference(facultadListFacultadToAttach.getClass(), facultadListFacultadToAttach.getCodigoarea());
                attachedFacultadList.add(facultadListFacultadToAttach);
            }
            authorities.setFacultadList(attachedFacultadList);
            List<Cum> attachedCumList = new ArrayList<Cum>();
            for (Cum cumListCumToAttach : authorities.getCumList()) {
                cumListCumToAttach = em.getReference(cumListCumToAttach.getClass(), cumListCumToAttach.getCodigocum());
                attachedCumList.add(cumListCumToAttach);
            }
            authorities.setCumList(attachedCumList);
            em.persist(authorities);
            if (users != null) {
                users.getAuthoritiesList().add(authorities);
                users = em.merge(users);
            }
            for (Facultad facultadListFacultad : authorities.getFacultadList()) {
                facultadListFacultad.getAuthoritiesList().add(authorities);
                facultadListFacultad = em.merge(facultadListFacultad);
            }
            for (Cum cumListCum : authorities.getCumList()) {
                cumListCum.getAuthoritiesList().add(authorities);
                cumListCum = em.merge(cumListCum);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAuthorities(authorities.getAuthoritiesPK()) != null) {
                throw new PreexistingEntityException("Authorities " + authorities + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Authorities authorities) throws NonexistentEntityException, Exception {
        authorities.getAuthoritiesPK().setUsername(authorities.getUsers().getUsername());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Authorities persistentAuthorities = em.find(Authorities.class, authorities.getAuthoritiesPK());
            Users usersOld = persistentAuthorities.getUsers();
            Users usersNew = authorities.getUsers();
            List<Facultad> facultadListOld = persistentAuthorities.getFacultadList();
            List<Facultad> facultadListNew = authorities.getFacultadList();
            List<Cum> cumListOld = persistentAuthorities.getCumList();
            List<Cum> cumListNew = authorities.getCumList();
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getUsername());
                authorities.setUsers(usersNew);
            }
            List<Facultad> attachedFacultadListNew = new ArrayList<Facultad>();
            for (Facultad facultadListNewFacultadToAttach : facultadListNew) {
                facultadListNewFacultadToAttach = em.getReference(facultadListNewFacultadToAttach.getClass(), facultadListNewFacultadToAttach.getCodigoarea());
                attachedFacultadListNew.add(facultadListNewFacultadToAttach);
            }
            facultadListNew = attachedFacultadListNew;
            authorities.setFacultadList(facultadListNew);
            List<Cum> attachedCumListNew = new ArrayList<Cum>();
            for (Cum cumListNewCumToAttach : cumListNew) {
                cumListNewCumToAttach = em.getReference(cumListNewCumToAttach.getClass(), cumListNewCumToAttach.getCodigocum());
                attachedCumListNew.add(cumListNewCumToAttach);
            }
            cumListNew = attachedCumListNew;
            authorities.setCumList(cumListNew);
            authorities = em.merge(authorities);
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getAuthoritiesList().remove(authorities);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getAuthoritiesList().add(authorities);
                usersNew = em.merge(usersNew);
            }
            for (Facultad facultadListOldFacultad : facultadListOld) {
                if (!facultadListNew.contains(facultadListOldFacultad)) {
                    facultadListOldFacultad.getAuthoritiesList().remove(authorities);
                    facultadListOldFacultad = em.merge(facultadListOldFacultad);
                }
            }
            for (Facultad facultadListNewFacultad : facultadListNew) {
                if (!facultadListOld.contains(facultadListNewFacultad)) {
                    facultadListNewFacultad.getAuthoritiesList().add(authorities);
                    facultadListNewFacultad = em.merge(facultadListNewFacultad);
                }
            }
            for (Cum cumListOldCum : cumListOld) {
                if (!cumListNew.contains(cumListOldCum)) {
                    cumListOldCum.getAuthoritiesList().remove(authorities);
                    cumListOldCum = em.merge(cumListOldCum);
                }
            }
            for (Cum cumListNewCum : cumListNew) {
                if (!cumListOld.contains(cumListNewCum)) {
                    cumListNewCum.getAuthoritiesList().add(authorities);
                    cumListNewCum = em.merge(cumListNewCum);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AuthoritiesPK id = authorities.getAuthoritiesPK();
                if (findAuthorities(id) == null) {
                    throw new NonexistentEntityException("The authorities with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AuthoritiesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Authorities authorities;
            try {
                authorities = em.getReference(Authorities.class, id);
                authorities.getAuthoritiesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The authorities with id " + id + " no longer exists.", enfe);
            }
            Users users = authorities.getUsers();
            if (users != null) {
                users.getAuthoritiesList().remove(authorities);
                users = em.merge(users);
            }
            List<Facultad> facultadList = authorities.getFacultadList();
            for (Facultad facultadListFacultad : facultadList) {
                facultadListFacultad.getAuthoritiesList().remove(authorities);
                facultadListFacultad = em.merge(facultadListFacultad);
            }
            List<Cum> cumList = authorities.getCumList();
            for (Cum cumListCum : cumList) {
                cumListCum.getAuthoritiesList().remove(authorities);
                cumListCum = em.merge(cumListCum);
            }
            em.remove(authorities);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Authorities> findAuthoritiesEntities() {
        return findAuthoritiesEntities(true, -1, -1);
    }

    public List<Authorities> findAuthoritiesEntities(int maxResults, int firstResult) {
        return findAuthoritiesEntities(false, maxResults, firstResult);
    }

    private List<Authorities> findAuthoritiesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Authorities.class));
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

    public Authorities findAuthorities(AuthoritiesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Authorities.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuthoritiesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Authorities> rt = cq.from(Authorities.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<String> findUserRoles(String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT a.authoritiesPK.authority "
                    + "FROM Authorities a "
                    + "WHERE a.authoritiesPK.username= :username "
                    + "ORDER BY a.authoritiesPK.authority");
            q.setParameter("username", user);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<String> findUserPermisosCum(String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT cl.nombrecum FROM Authorities a JOIN FETCH a.cumList cl WHERE a.authoritiesPK.username= :username ORDER BY a.authoritiesPK.authority");
            q.setParameter("username", user);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<String> findUserPermisosFac(String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT cl.nombrearea FROM Authorities a JOIN FETCH a.facultadList cl WHERE a.authoritiesPK.username= :username ORDER BY a.authoritiesPK.authority");
            q.setParameter("username", user);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
