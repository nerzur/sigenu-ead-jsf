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
import cu.edu.unah.sigenuead.entity.Authorities;
import cu.edu.unah.sigenuead.entity.Users;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) throws PreexistingEntityException, Exception {
        if (users.getAuthoritiesList() == null) {
            users.setAuthoritiesList(new ArrayList<Authorities>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Authorities> attachedAuthoritiesList = new ArrayList<Authorities>();
            for (Authorities authoritiesListAuthoritiesToAttach : users.getAuthoritiesList()) {
                authoritiesListAuthoritiesToAttach = em.getReference(authoritiesListAuthoritiesToAttach.getClass(), authoritiesListAuthoritiesToAttach.getAuthoritiesPK());
                attachedAuthoritiesList.add(authoritiesListAuthoritiesToAttach);
            }
            users.setAuthoritiesList(attachedAuthoritiesList);
            em.persist(users);
            for (Authorities authoritiesListAuthorities : users.getAuthoritiesList()) {
                Users oldUsersOfAuthoritiesListAuthorities = authoritiesListAuthorities.getUsers();
                authoritiesListAuthorities.setUsers(users);
                authoritiesListAuthorities = em.merge(authoritiesListAuthorities);
                if (oldUsersOfAuthoritiesListAuthorities != null) {
                    oldUsersOfAuthoritiesListAuthorities.getAuthoritiesList().remove(authoritiesListAuthorities);
                    oldUsersOfAuthoritiesListAuthorities = em.merge(oldUsersOfAuthoritiesListAuthorities);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsers(users.getUsername()) != null) {
                throw new PreexistingEntityException("Users " + users + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getUsername());
            List<Authorities> authoritiesListOld = persistentUsers.getAuthoritiesList();
            List<Authorities> authoritiesListNew = users.getAuthoritiesList();
            List<String> illegalOrphanMessages = null;
            for (Authorities authoritiesListOldAuthorities : authoritiesListOld) {
                if (!authoritiesListNew.contains(authoritiesListOldAuthorities)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Authorities " + authoritiesListOldAuthorities + " since its users field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Authorities> attachedAuthoritiesListNew = new ArrayList<Authorities>();
            for (Authorities authoritiesListNewAuthoritiesToAttach : authoritiesListNew) {
                authoritiesListNewAuthoritiesToAttach = em.getReference(authoritiesListNewAuthoritiesToAttach.getClass(), authoritiesListNewAuthoritiesToAttach.getAuthoritiesPK());
                attachedAuthoritiesListNew.add(authoritiesListNewAuthoritiesToAttach);
            }
            authoritiesListNew = attachedAuthoritiesListNew;
            users.setAuthoritiesList(authoritiesListNew);
            users = em.merge(users);
            for (Authorities authoritiesListNewAuthorities : authoritiesListNew) {
                if (!authoritiesListOld.contains(authoritiesListNewAuthorities)) {
                    Users oldUsersOfAuthoritiesListNewAuthorities = authoritiesListNewAuthorities.getUsers();
                    authoritiesListNewAuthorities.setUsers(users);
                    authoritiesListNewAuthorities = em.merge(authoritiesListNewAuthorities);
                    if (oldUsersOfAuthoritiesListNewAuthorities != null && !oldUsersOfAuthoritiesListNewAuthorities.equals(users)) {
                        oldUsersOfAuthoritiesListNewAuthorities.getAuthoritiesList().remove(authoritiesListNewAuthorities);
                        oldUsersOfAuthoritiesListNewAuthorities = em.merge(oldUsersOfAuthoritiesListNewAuthorities);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = users.getUsername();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getUsername();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Authorities> authoritiesListOrphanCheck = users.getAuthoritiesList();
            for (Authorities authoritiesListOrphanCheckAuthorities : authoritiesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Authorities " + authoritiesListOrphanCheckAuthorities + " in its authoritiesList field has a non-nullable users field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Users findUsersByUsernameAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT s "
                    + "FROM Users s "
                    + "WHERE s.enabled=true AND "
                    + "s.username= :nombre");
            q.setParameter("nombre", nombre);
            Users p = (Users) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Users findUsersByIdentificacion(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT s FROM Users s WHERE s.identificacion= :nombre");
            q.setParameter("nombre", nombre);
            Users p = (Users) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<String> findFacultadAutorities(String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fl.nombrearea FROM Authorities a JOIN FETCH a.facultadList fl WHERE a.authoritiesPK.username=:user AND fl.canceladoarea=FALSE");
            q.setParameter("user", user);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<String> findCumAutorities(String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fl.nombrecum FROM Authorities a JOIN FETCH a.cumList fl WHERE a.authoritiesPK.username=:user AND fl.cumcancelado=FALSE");
            q.setParameter("user", user);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
