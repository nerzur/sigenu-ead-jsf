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
import java.util.ArrayList;
import java.util.List;
import cu.edu.unah.sigenuead.entity.FacultadCum;
import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.Facultad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class FacultadJpaController implements Serializable {

    public FacultadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facultad facultad) throws PreexistingEntityException, Exception {
        if (facultad.getAuthoritiesList() == null) {
            facultad.setAuthoritiesList(new ArrayList<Authorities>());
        }
        if (facultad.getFacultadCumList() == null) {
            facultad.setFacultadCumList(new ArrayList<FacultadCum>());
        }
        if (facultad.getCarreraList() == null) {
            facultad.setCarreraList(new ArrayList<Carrera>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipio municipioidmunicipio = facultad.getMunicipioidmunicipio();
            if (municipioidmunicipio != null) {
                municipioidmunicipio = em.getReference(municipioidmunicipio.getClass(), municipioidmunicipio.getIdmunicipio());
                facultad.setMunicipioidmunicipio(municipioidmunicipio);
            }
            Universidad universidadcodigouniversidad = facultad.getUniversidadcodigouniversidad();
            if (universidadcodigouniversidad != null) {
                universidadcodigouniversidad = em.getReference(universidadcodigouniversidad.getClass(), universidadcodigouniversidad.getCodigouniversidad());
                facultad.setUniversidadcodigouniversidad(universidadcodigouniversidad);
            }
            List<Authorities> attachedAuthoritiesList = new ArrayList<Authorities>();
            for (Authorities authoritiesListAuthoritiesToAttach : facultad.getAuthoritiesList()) {
                authoritiesListAuthoritiesToAttach = em.getReference(authoritiesListAuthoritiesToAttach.getClass(), authoritiesListAuthoritiesToAttach.getAuthoritiesPK());
                attachedAuthoritiesList.add(authoritiesListAuthoritiesToAttach);
            }
            facultad.setAuthoritiesList(attachedAuthoritiesList);
            List<FacultadCum> attachedFacultadCumList = new ArrayList<FacultadCum>();
            for (FacultadCum facultadCumListFacultadCumToAttach : facultad.getFacultadCumList()) {
                facultadCumListFacultadCumToAttach = em.getReference(facultadCumListFacultadCumToAttach.getClass(), facultadCumListFacultadCumToAttach.getFacultadCumPK());
                attachedFacultadCumList.add(facultadCumListFacultadCumToAttach);
            }
            facultad.setFacultadCumList(attachedFacultadCumList);
            List<Carrera> attachedCarreraList = new ArrayList<Carrera>();
            for (Carrera carreraListCarreraToAttach : facultad.getCarreraList()) {
                carreraListCarreraToAttach = em.getReference(carreraListCarreraToAttach.getClass(), carreraListCarreraToAttach.getIdcarrera());
                attachedCarreraList.add(carreraListCarreraToAttach);
            }
            facultad.setCarreraList(attachedCarreraList);
            em.persist(facultad);
            if (municipioidmunicipio != null) {
                municipioidmunicipio.getFacultadList().add(facultad);
                municipioidmunicipio = em.merge(municipioidmunicipio);
            }
            if (universidadcodigouniversidad != null) {
                universidadcodigouniversidad.getFacultadList().add(facultad);
                universidadcodigouniversidad = em.merge(universidadcodigouniversidad);
            }
            for (Authorities authoritiesListAuthorities : facultad.getAuthoritiesList()) {
                authoritiesListAuthorities.getFacultadList().add(facultad);
                authoritiesListAuthorities = em.merge(authoritiesListAuthorities);
            }
            for (FacultadCum facultadCumListFacultadCum : facultad.getFacultadCumList()) {
                Facultad oldFacultadOfFacultadCumListFacultadCum = facultadCumListFacultadCum.getFacultad();
                facultadCumListFacultadCum.setFacultad(facultad);
                facultadCumListFacultadCum = em.merge(facultadCumListFacultadCum);
                if (oldFacultadOfFacultadCumListFacultadCum != null) {
                    oldFacultadOfFacultadCumListFacultadCum.getFacultadCumList().remove(facultadCumListFacultadCum);
                    oldFacultadOfFacultadCumListFacultadCum = em.merge(oldFacultadOfFacultadCumListFacultadCum);
                }
            }
            for (Carrera carreraListCarrera : facultad.getCarreraList()) {
                Facultad oldFacultadcodigoareaOfCarreraListCarrera = carreraListCarrera.getFacultadcodigoarea();
                carreraListCarrera.setFacultadcodigoarea(facultad);
                carreraListCarrera = em.merge(carreraListCarrera);
                if (oldFacultadcodigoareaOfCarreraListCarrera != null) {
                    oldFacultadcodigoareaOfCarreraListCarrera.getCarreraList().remove(carreraListCarrera);
                    oldFacultadcodigoareaOfCarreraListCarrera = em.merge(oldFacultadcodigoareaOfCarreraListCarrera);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFacultad(facultad.getCodigoarea()) != null) {
                throw new PreexistingEntityException("Facultad " + facultad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facultad facultad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facultad persistentFacultad = em.find(Facultad.class, facultad.getCodigoarea());
            Municipio municipioidmunicipioOld = persistentFacultad.getMunicipioidmunicipio();
            Municipio municipioidmunicipioNew = facultad.getMunicipioidmunicipio();
            Universidad universidadcodigouniversidadOld = persistentFacultad.getUniversidadcodigouniversidad();
            Universidad universidadcodigouniversidadNew = facultad.getUniversidadcodigouniversidad();
            List<Authorities> authoritiesListOld = persistentFacultad.getAuthoritiesList();
            List<Authorities> authoritiesListNew = facultad.getAuthoritiesList();
            List<FacultadCum> facultadCumListOld = persistentFacultad.getFacultadCumList();
            List<FacultadCum> facultadCumListNew = facultad.getFacultadCumList();
            List<Carrera> carreraListOld = persistentFacultad.getCarreraList();
            List<Carrera> carreraListNew = facultad.getCarreraList();
            List<String> illegalOrphanMessages = null;
            for (FacultadCum facultadCumListOldFacultadCum : facultadCumListOld) {
                if (!facultadCumListNew.contains(facultadCumListOldFacultadCum)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FacultadCum " + facultadCumListOldFacultadCum + " since its facultad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (municipioidmunicipioNew != null) {
                municipioidmunicipioNew = em.getReference(municipioidmunicipioNew.getClass(), municipioidmunicipioNew.getIdmunicipio());
                facultad.setMunicipioidmunicipio(municipioidmunicipioNew);
            }
            if (universidadcodigouniversidadNew != null) {
                universidadcodigouniversidadNew = em.getReference(universidadcodigouniversidadNew.getClass(), universidadcodigouniversidadNew.getCodigouniversidad());
                facultad.setUniversidadcodigouniversidad(universidadcodigouniversidadNew);
            }
            List<Authorities> attachedAuthoritiesListNew = new ArrayList<Authorities>();
            for (Authorities authoritiesListNewAuthoritiesToAttach : authoritiesListNew) {
                authoritiesListNewAuthoritiesToAttach = em.getReference(authoritiesListNewAuthoritiesToAttach.getClass(), authoritiesListNewAuthoritiesToAttach.getAuthoritiesPK());
                attachedAuthoritiesListNew.add(authoritiesListNewAuthoritiesToAttach);
            }
            authoritiesListNew = attachedAuthoritiesListNew;
            facultad.setAuthoritiesList(authoritiesListNew);
            List<FacultadCum> attachedFacultadCumListNew = new ArrayList<FacultadCum>();
            for (FacultadCum facultadCumListNewFacultadCumToAttach : facultadCumListNew) {
                facultadCumListNewFacultadCumToAttach = em.getReference(facultadCumListNewFacultadCumToAttach.getClass(), facultadCumListNewFacultadCumToAttach.getFacultadCumPK());
                attachedFacultadCumListNew.add(facultadCumListNewFacultadCumToAttach);
            }
            facultadCumListNew = attachedFacultadCumListNew;
            facultad.setFacultadCumList(facultadCumListNew);
            List<Carrera> attachedCarreraListNew = new ArrayList<Carrera>();
            for (Carrera carreraListNewCarreraToAttach : carreraListNew) {
                carreraListNewCarreraToAttach = em.getReference(carreraListNewCarreraToAttach.getClass(), carreraListNewCarreraToAttach.getIdcarrera());
                attachedCarreraListNew.add(carreraListNewCarreraToAttach);
            }
            carreraListNew = attachedCarreraListNew;
            facultad.setCarreraList(carreraListNew);
            facultad = em.merge(facultad);
            if (municipioidmunicipioOld != null && !municipioidmunicipioOld.equals(municipioidmunicipioNew)) {
                municipioidmunicipioOld.getFacultadList().remove(facultad);
                municipioidmunicipioOld = em.merge(municipioidmunicipioOld);
            }
            if (municipioidmunicipioNew != null && !municipioidmunicipioNew.equals(municipioidmunicipioOld)) {
                municipioidmunicipioNew.getFacultadList().add(facultad);
                municipioidmunicipioNew = em.merge(municipioidmunicipioNew);
            }
            if (universidadcodigouniversidadOld != null && !universidadcodigouniversidadOld.equals(universidadcodigouniversidadNew)) {
                universidadcodigouniversidadOld.getFacultadList().remove(facultad);
                universidadcodigouniversidadOld = em.merge(universidadcodigouniversidadOld);
            }
            if (universidadcodigouniversidadNew != null && !universidadcodigouniversidadNew.equals(universidadcodigouniversidadOld)) {
                universidadcodigouniversidadNew.getFacultadList().add(facultad);
                universidadcodigouniversidadNew = em.merge(universidadcodigouniversidadNew);
            }
            for (Authorities authoritiesListOldAuthorities : authoritiesListOld) {
                if (!authoritiesListNew.contains(authoritiesListOldAuthorities)) {
                    authoritiesListOldAuthorities.getFacultadList().remove(facultad);
                    authoritiesListOldAuthorities = em.merge(authoritiesListOldAuthorities);
                }
            }
            for (Authorities authoritiesListNewAuthorities : authoritiesListNew) {
                if (!authoritiesListOld.contains(authoritiesListNewAuthorities)) {
                    authoritiesListNewAuthorities.getFacultadList().add(facultad);
                    authoritiesListNewAuthorities = em.merge(authoritiesListNewAuthorities);
                }
            }
            for (FacultadCum facultadCumListNewFacultadCum : facultadCumListNew) {
                if (!facultadCumListOld.contains(facultadCumListNewFacultadCum)) {
                    Facultad oldFacultadOfFacultadCumListNewFacultadCum = facultadCumListNewFacultadCum.getFacultad();
                    facultadCumListNewFacultadCum.setFacultad(facultad);
                    facultadCumListNewFacultadCum = em.merge(facultadCumListNewFacultadCum);
                    if (oldFacultadOfFacultadCumListNewFacultadCum != null && !oldFacultadOfFacultadCumListNewFacultadCum.equals(facultad)) {
                        oldFacultadOfFacultadCumListNewFacultadCum.getFacultadCumList().remove(facultadCumListNewFacultadCum);
                        oldFacultadOfFacultadCumListNewFacultadCum = em.merge(oldFacultadOfFacultadCumListNewFacultadCum);
                    }
                }
            }
            for (Carrera carreraListOldCarrera : carreraListOld) {
                if (!carreraListNew.contains(carreraListOldCarrera)) {
                    carreraListOldCarrera.setFacultadcodigoarea(null);
                    carreraListOldCarrera = em.merge(carreraListOldCarrera);
                }
            }
            for (Carrera carreraListNewCarrera : carreraListNew) {
                if (!carreraListOld.contains(carreraListNewCarrera)) {
                    Facultad oldFacultadcodigoareaOfCarreraListNewCarrera = carreraListNewCarrera.getFacultadcodigoarea();
                    carreraListNewCarrera.setFacultadcodigoarea(facultad);
                    carreraListNewCarrera = em.merge(carreraListNewCarrera);
                    if (oldFacultadcodigoareaOfCarreraListNewCarrera != null && !oldFacultadcodigoareaOfCarreraListNewCarrera.equals(facultad)) {
                        oldFacultadcodigoareaOfCarreraListNewCarrera.getCarreraList().remove(carreraListNewCarrera);
                        oldFacultadcodigoareaOfCarreraListNewCarrera = em.merge(oldFacultadcodigoareaOfCarreraListNewCarrera);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = facultad.getCodigoarea();
                if (findFacultad(id) == null) {
                    throw new NonexistentEntityException("The facultad with id " + id + " no longer exists.");
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
            Facultad facultad;
            try {
                facultad = em.getReference(Facultad.class, id);
                facultad.getCodigoarea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facultad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<FacultadCum> facultadCumListOrphanCheck = facultad.getFacultadCumList();
            for (FacultadCum facultadCumListOrphanCheckFacultadCum : facultadCumListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Facultad (" + facultad + ") cannot be destroyed since the FacultadCum " + facultadCumListOrphanCheckFacultadCum + " in its facultadCumList field has a non-nullable facultad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Municipio municipioidmunicipio = facultad.getMunicipioidmunicipio();
            if (municipioidmunicipio != null) {
                municipioidmunicipio.getFacultadList().remove(facultad);
                municipioidmunicipio = em.merge(municipioidmunicipio);
            }
            Universidad universidadcodigouniversidad = facultad.getUniversidadcodigouniversidad();
            if (universidadcodigouniversidad != null) {
                universidadcodigouniversidad.getFacultadList().remove(facultad);
                universidadcodigouniversidad = em.merge(universidadcodigouniversidad);
            }
            List<Authorities> authoritiesList = facultad.getAuthoritiesList();
            for (Authorities authoritiesListAuthorities : authoritiesList) {
                authoritiesListAuthorities.getFacultadList().remove(facultad);
                authoritiesListAuthorities = em.merge(authoritiesListAuthorities);
            }
            List<Carrera> carreraList = facultad.getCarreraList();
            for (Carrera carreraListCarrera : carreraList) {
                carreraListCarrera.setFacultadcodigoarea(null);
                carreraListCarrera = em.merge(carreraListCarrera);
            }
            em.remove(facultad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facultad> findFacultadEntities() {
        return findFacultadEntities(true, -1, -1);
    }

    public List<Facultad> findFacultadEntities(int maxResults, int firstResult) {
        return findFacultadEntities(false, maxResults, firstResult);
    }

    private List<Facultad> findFacultadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facultad.class));
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

    public Facultad findFacultad(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facultad.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacultadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facultad> rt = cq.from(Facultad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<String> findFacultadAvailable() {
        EntityManager em = getEntityManager();
        try {
            List<String> p = em.createQuery("SELECT f.nombrearea "
                    + "FROM Facultad f "
                    + "WHERE f.canceladoarea=false "
                    + "ORDER BY f.nombrearea").getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Facultad findFacultadByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT f "
                    + "FROM Facultad f "
                    + "WHERE f.nombrearea= :nombre");
            q.setParameter("nombre", nombre);
            Facultad p = (Facultad) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Facultad findFacultadByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT f "
                    + "FROM Facultad f "
                    + "WHERE f.canceladoarea=false AND "
                    + "f.nombrearea= :nombre");
            q.setParameter("nombre", nombre);
            Facultad p = (Facultad) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Facultad findFacultadByCumCarrera(String cum, int carrera) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT f.facultadCum.facultad FROM FacultadCumCarrera f WHERE f.cancelado=false AND f.facultadCumCarreraPK.carreraidcarrera=:carrera AND f.facultadCumCarreraPK.facultadCumcumcodigocum=:cum");
            q.setParameter("carrera", carrera);
            q.setParameter("cum", cum);
            Facultad p = (Facultad) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
