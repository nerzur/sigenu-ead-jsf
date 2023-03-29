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
import cu.edu.unah.sigenuead.entity.Municipio;
import cu.edu.unah.sigenuead.entity.Universidad;
import cu.edu.unah.sigenuead.entity.Authorities;
import cu.edu.unah.sigenuead.entity.Cum;
import java.util.ArrayList;
import java.util.List;
import cu.edu.unah.sigenuead.entity.FacultadCum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class CumJpaController implements Serializable {

    public CumJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cum cum) throws PreexistingEntityException, Exception {
        if (cum.getAuthoritiesList() == null) {
            cum.setAuthoritiesList(new ArrayList<Authorities>());
        }
        if (cum.getFacultadCumList() == null) {
            cum.setFacultadCumList(new ArrayList<FacultadCum>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipio municipioidmunicipio = cum.getMunicipioidmunicipio();
            if (municipioidmunicipio != null) {
                municipioidmunicipio = em.getReference(municipioidmunicipio.getClass(), municipioidmunicipio.getIdmunicipio());
                cum.setMunicipioidmunicipio(municipioidmunicipio);
            }
            Universidad universidadcodigouniversidad = cum.getUniversidadcodigouniversidad();
            if (universidadcodigouniversidad != null) {
                universidadcodigouniversidad = em.getReference(universidadcodigouniversidad.getClass(), universidadcodigouniversidad.getCodigouniversidad());
                cum.setUniversidadcodigouniversidad(universidadcodigouniversidad);
            }
            List<Authorities> attachedAuthoritiesList = new ArrayList<Authorities>();
            for (Authorities authoritiesListAuthoritiesToAttach : cum.getAuthoritiesList()) {
                authoritiesListAuthoritiesToAttach = em.getReference(authoritiesListAuthoritiesToAttach.getClass(), authoritiesListAuthoritiesToAttach.getAuthoritiesPK());
                attachedAuthoritiesList.add(authoritiesListAuthoritiesToAttach);
            }
            cum.setAuthoritiesList(attachedAuthoritiesList);
            List<FacultadCum> attachedFacultadCumList = new ArrayList<FacultadCum>();
            for (FacultadCum facultadCumListFacultadCumToAttach : cum.getFacultadCumList()) {
                facultadCumListFacultadCumToAttach = em.getReference(facultadCumListFacultadCumToAttach.getClass(), facultadCumListFacultadCumToAttach.getFacultadCumPK());
                attachedFacultadCumList.add(facultadCumListFacultadCumToAttach);
            }
            cum.setFacultadCumList(attachedFacultadCumList);
            em.persist(cum);
            if (municipioidmunicipio != null) {
                municipioidmunicipio.getCumList().add(cum);
                municipioidmunicipio = em.merge(municipioidmunicipio);
            }
            if (universidadcodigouniversidad != null) {
                universidadcodigouniversidad.getCumList().add(cum);
                universidadcodigouniversidad = em.merge(universidadcodigouniversidad);
            }
            for (Authorities authoritiesListAuthorities : cum.getAuthoritiesList()) {
                authoritiesListAuthorities.getCumList().add(cum);
                authoritiesListAuthorities = em.merge(authoritiesListAuthorities);
            }
            for (FacultadCum facultadCumListFacultadCum : cum.getFacultadCumList()) {
                Cum oldCumOfFacultadCumListFacultadCum = facultadCumListFacultadCum.getCum();
                facultadCumListFacultadCum.setCum(cum);
                facultadCumListFacultadCum = em.merge(facultadCumListFacultadCum);
                if (oldCumOfFacultadCumListFacultadCum != null) {
                    oldCumOfFacultadCumListFacultadCum.getFacultadCumList().remove(facultadCumListFacultadCum);
                    oldCumOfFacultadCumListFacultadCum = em.merge(oldCumOfFacultadCumListFacultadCum);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCum(cum.getCodigocum()) != null) {
                throw new PreexistingEntityException("Cum " + cum + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cum cum) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cum persistentCum = em.find(Cum.class, cum.getCodigocum());
            Municipio municipioidmunicipioOld = persistentCum.getMunicipioidmunicipio();
            Municipio municipioidmunicipioNew = cum.getMunicipioidmunicipio();
            Universidad universidadcodigouniversidadOld = persistentCum.getUniversidadcodigouniversidad();
            Universidad universidadcodigouniversidadNew = cum.getUniversidadcodigouniversidad();
            List<Authorities> authoritiesListOld = persistentCum.getAuthoritiesList();
            List<Authorities> authoritiesListNew = cum.getAuthoritiesList();
            List<FacultadCum> facultadCumListOld = persistentCum.getFacultadCumList();
            List<FacultadCum> facultadCumListNew = cum.getFacultadCumList();
            List<String> illegalOrphanMessages = null;
            for (FacultadCum facultadCumListOldFacultadCum : facultadCumListOld) {
                if (!facultadCumListNew.contains(facultadCumListOldFacultadCum)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FacultadCum " + facultadCumListOldFacultadCum + " since its cum field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (municipioidmunicipioNew != null) {
                municipioidmunicipioNew = em.getReference(municipioidmunicipioNew.getClass(), municipioidmunicipioNew.getIdmunicipio());
                cum.setMunicipioidmunicipio(municipioidmunicipioNew);
            }
            if (universidadcodigouniversidadNew != null) {
                universidadcodigouniversidadNew = em.getReference(universidadcodigouniversidadNew.getClass(), universidadcodigouniversidadNew.getCodigouniversidad());
                cum.setUniversidadcodigouniversidad(universidadcodigouniversidadNew);
            }
            List<Authorities> attachedAuthoritiesListNew = new ArrayList<Authorities>();
            for (Authorities authoritiesListNewAuthoritiesToAttach : authoritiesListNew) {
                authoritiesListNewAuthoritiesToAttach = em.getReference(authoritiesListNewAuthoritiesToAttach.getClass(), authoritiesListNewAuthoritiesToAttach.getAuthoritiesPK());
                attachedAuthoritiesListNew.add(authoritiesListNewAuthoritiesToAttach);
            }
            authoritiesListNew = attachedAuthoritiesListNew;
            cum.setAuthoritiesList(authoritiesListNew);
            List<FacultadCum> attachedFacultadCumListNew = new ArrayList<FacultadCum>();
            for (FacultadCum facultadCumListNewFacultadCumToAttach : facultadCumListNew) {
                facultadCumListNewFacultadCumToAttach = em.getReference(facultadCumListNewFacultadCumToAttach.getClass(), facultadCumListNewFacultadCumToAttach.getFacultadCumPK());
                attachedFacultadCumListNew.add(facultadCumListNewFacultadCumToAttach);
            }
            facultadCumListNew = attachedFacultadCumListNew;
            cum.setFacultadCumList(facultadCumListNew);
            cum = em.merge(cum);
            if (municipioidmunicipioOld != null && !municipioidmunicipioOld.equals(municipioidmunicipioNew)) {
                municipioidmunicipioOld.getCumList().remove(cum);
                municipioidmunicipioOld = em.merge(municipioidmunicipioOld);
            }
            if (municipioidmunicipioNew != null && !municipioidmunicipioNew.equals(municipioidmunicipioOld)) {
                municipioidmunicipioNew.getCumList().add(cum);
                municipioidmunicipioNew = em.merge(municipioidmunicipioNew);
            }
            if (universidadcodigouniversidadOld != null && !universidadcodigouniversidadOld.equals(universidadcodigouniversidadNew)) {
                universidadcodigouniversidadOld.getCumList().remove(cum);
                universidadcodigouniversidadOld = em.merge(universidadcodigouniversidadOld);
            }
            if (universidadcodigouniversidadNew != null && !universidadcodigouniversidadNew.equals(universidadcodigouniversidadOld)) {
                universidadcodigouniversidadNew.getCumList().add(cum);
                universidadcodigouniversidadNew = em.merge(universidadcodigouniversidadNew);
            }
            for (Authorities authoritiesListOldAuthorities : authoritiesListOld) {
                if (!authoritiesListNew.contains(authoritiesListOldAuthorities)) {
                    authoritiesListOldAuthorities.getCumList().remove(cum);
                    authoritiesListOldAuthorities = em.merge(authoritiesListOldAuthorities);
                }
            }
            for (Authorities authoritiesListNewAuthorities : authoritiesListNew) {
                if (!authoritiesListOld.contains(authoritiesListNewAuthorities)) {
                    authoritiesListNewAuthorities.getCumList().add(cum);
                    authoritiesListNewAuthorities = em.merge(authoritiesListNewAuthorities);
                }
            }
            for (FacultadCum facultadCumListNewFacultadCum : facultadCumListNew) {
                if (!facultadCumListOld.contains(facultadCumListNewFacultadCum)) {
                    Cum oldCumOfFacultadCumListNewFacultadCum = facultadCumListNewFacultadCum.getCum();
                    facultadCumListNewFacultadCum.setCum(cum);
                    facultadCumListNewFacultadCum = em.merge(facultadCumListNewFacultadCum);
                    if (oldCumOfFacultadCumListNewFacultadCum != null && !oldCumOfFacultadCumListNewFacultadCum.equals(cum)) {
                        oldCumOfFacultadCumListNewFacultadCum.getFacultadCumList().remove(facultadCumListNewFacultadCum);
                        oldCumOfFacultadCumListNewFacultadCum = em.merge(oldCumOfFacultadCumListNewFacultadCum);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cum.getCodigocum();
                if (findCum(id) == null) {
                    throw new NonexistentEntityException("The cum with id " + id + " no longer exists.");
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
            Cum cum;
            try {
                cum = em.getReference(Cum.class, id);
                cum.getCodigocum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cum with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<FacultadCum> facultadCumListOrphanCheck = cum.getFacultadCumList();
            for (FacultadCum facultadCumListOrphanCheckFacultadCum : facultadCumListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cum (" + cum + ") cannot be destroyed since the FacultadCum " + facultadCumListOrphanCheckFacultadCum + " in its facultadCumList field has a non-nullable cum field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Municipio municipioidmunicipio = cum.getMunicipioidmunicipio();
            if (municipioidmunicipio != null) {
                municipioidmunicipio.getCumList().remove(cum);
                municipioidmunicipio = em.merge(municipioidmunicipio);
            }
            Universidad universidadcodigouniversidad = cum.getUniversidadcodigouniversidad();
            if (universidadcodigouniversidad != null) {
                universidadcodigouniversidad.getCumList().remove(cum);
                universidadcodigouniversidad = em.merge(universidadcodigouniversidad);
            }
            List<Authorities> authoritiesList = cum.getAuthoritiesList();
            for (Authorities authoritiesListAuthorities : authoritiesList) {
                authoritiesListAuthorities.getCumList().remove(cum);
                authoritiesListAuthorities = em.merge(authoritiesListAuthorities);
            }
            em.remove(cum);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cum> findCumEntities() {
        return findCumEntities(true, -1, -1);
    }

    public List<Cum> findCumEntities(int maxResults, int firstResult) {
        return findCumEntities(false, maxResults, firstResult);
    }

    private List<Cum> findCumEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cum.class));
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

    public Cum findCum(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cum.class, id);
        } finally {
            em.close();
        }
    }

    public int getCumCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cum> rt = cq.from(Cum.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Cum findCumByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Cum c "
                    + "WHERE c.nombrecum= :nombre");
            q.setParameter("nombre", nombre);
            Cum p = (Cum) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Cum findCumByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Cum c "
                    + "WHERE c.cumcancelado=false AND "
                    + "c.nombrecum= :nombre");
            q.setParameter("nombre", nombre);
            Cum p = (Cum) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Cum> findCumByFacultad(String facultad) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT c "
                    + "FROM Cum c "
                    + "JOIN FETCH c.facultadCumList fc "
                    + "WHERE fc.facultad.nombrearea= :facultad AND "
                    + "c.codigocum NOT LIKE ''");
            q.setParameter("facultad", facultad);
            List<Cum> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

//    public List<String> findCumAvailableByFacultad(String f) {
//        EntityManager em = getEntityManager();
//        try {
//            Query q = em.createQuery("SELECT DISTINCT c.nombrecum FROM Cum c JOIN FETCH c.facultadCumList fc JOIN FETCH fc.facultadCumCarreraList fcc WHERE c.cumcancelado=false AND fc.cancelado=false AND fc.facultad.nombrearea= :facultad AND c.codigocum NOT LIKE ''");
//            q.setParameter("facultad", f);
//            List<String> p = q.getResultList();
//            return p;
//        } catch (Exception e) {
//            return null;
//        } finally {
//            em.close();
//        }
//    }
    public List<String> findCumAvailableByFacultad(String f, String user) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT c.nombrecum FROM Cum c JOIN FETCH c.facultadCumList fc JOIN FETCH fc.facultadCumCarreraList fcc JOIN FETCH c.authoritiesList al WHERE c.cumcancelado=false AND fc.cancelado=false AND fc.facultad.nombrearea= :facultad AND c.codigocum NOT LIKE '' AND al.authoritiesPK.username=:user");
            q.setParameter("facultad", f);
            q.setParameter("user", user);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findCumAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c.nombrecum "
                    + "FROM Cum c "
                    + "WHERE c.cumcancelado=false AND "
                    + "c.codigocum NOT LIKE '' "
                    + "ORDER BY c.nombrecum");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findCumAvailableByFacultadCarrera(String fac, String carrera) {
        EntityManager em = getEntityManager();
        try {
            Query tq = em.createQuery("SELECT DISTINCT c.nombrecum FROM Cum c JOIN FETCH c.facultadCumList fc JOIN FETCH fc.facultadCumCarreraList fcc WHERE c.cumcancelado=false AND c.codigocum NOT LIKE '' AND fcc.cancelado=false AND fc.facultad.nombrearea= :facultad AND fcc.carrera.carreranacionalidcarreranacional.nombrecarreranacional= :carrera");
            tq.setParameter("facultad", fac);
            tq.setParameter("carrera", carrera);
            List<String> p = tq.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Cum> findCumList() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Cum c "
                    + "WHERE c.codigocum NOT LIKE ''"
                    + "ORDER BY c.nombrecum");
            List<Cum> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
