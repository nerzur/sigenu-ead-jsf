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
import cu.edu.unah.sigenuead.entity.Pais;
import cu.edu.unah.sigenuead.entity.Municipio;
import java.util.ArrayList;
import java.util.List;
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.Provincia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class ProvinciaJpaController implements Serializable {

    public ProvinciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Provincia provincia) {
        if (provincia.getMunicipioList() == null) {
            provincia.setMunicipioList(new ArrayList<Municipio>());
        }
        if (provincia.getEstudianteList() == null) {
            provincia.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais paisidpais = provincia.getPaisidpais();
            if (paisidpais != null) {
                paisidpais = em.getReference(paisidpais.getClass(), paisidpais.getIdpais());
                provincia.setPaisidpais(paisidpais);
            }
            List<Municipio> attachedMunicipioList = new ArrayList<Municipio>();
            for (Municipio municipioListMunicipioToAttach : provincia.getMunicipioList()) {
                municipioListMunicipioToAttach = em.getReference(municipioListMunicipioToAttach.getClass(), municipioListMunicipioToAttach.getIdmunicipio());
                attachedMunicipioList.add(municipioListMunicipioToAttach);
            }
            provincia.setMunicipioList(attachedMunicipioList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : provincia.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            provincia.setEstudianteList(attachedEstudianteList);
            em.persist(provincia);
            if (paisidpais != null) {
                paisidpais.getProvinciaList().add(provincia);
                paisidpais = em.merge(paisidpais);
            }
            for (Municipio municipioListMunicipio : provincia.getMunicipioList()) {
                Provincia oldProvinciaidprovinciaOfMunicipioListMunicipio = municipioListMunicipio.getProvinciaidprovincia();
                municipioListMunicipio.setProvinciaidprovincia(provincia);
                municipioListMunicipio = em.merge(municipioListMunicipio);
                if (oldProvinciaidprovinciaOfMunicipioListMunicipio != null) {
                    oldProvinciaidprovinciaOfMunicipioListMunicipio.getMunicipioList().remove(municipioListMunicipio);
                    oldProvinciaidprovinciaOfMunicipioListMunicipio = em.merge(oldProvinciaidprovinciaOfMunicipioListMunicipio);
                }
            }
            for (Estudiante estudianteListEstudiante : provincia.getEstudianteList()) {
                Provincia oldProvinciaidprovinciaOfEstudianteListEstudiante = estudianteListEstudiante.getProvinciaidprovincia();
                estudianteListEstudiante.setProvinciaidprovincia(provincia);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldProvinciaidprovinciaOfEstudianteListEstudiante != null) {
                    oldProvinciaidprovinciaOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldProvinciaidprovinciaOfEstudianteListEstudiante = em.merge(oldProvinciaidprovinciaOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Provincia provincia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia persistentProvincia = em.find(Provincia.class, provincia.getIdprovincia());
            Pais paisidpaisOld = persistentProvincia.getPaisidpais();
            Pais paisidpaisNew = provincia.getPaisidpais();
            List<Municipio> municipioListOld = persistentProvincia.getMunicipioList();
            List<Municipio> municipioListNew = provincia.getMunicipioList();
            List<Estudiante> estudianteListOld = persistentProvincia.getEstudianteList();
            List<Estudiante> estudianteListNew = provincia.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Municipio municipioListOldMunicipio : municipioListOld) {
                if (!municipioListNew.contains(municipioListOldMunicipio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Municipio " + municipioListOldMunicipio + " since its provinciaidprovincia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (paisidpaisNew != null) {
                paisidpaisNew = em.getReference(paisidpaisNew.getClass(), paisidpaisNew.getIdpais());
                provincia.setPaisidpais(paisidpaisNew);
            }
            List<Municipio> attachedMunicipioListNew = new ArrayList<Municipio>();
            for (Municipio municipioListNewMunicipioToAttach : municipioListNew) {
                municipioListNewMunicipioToAttach = em.getReference(municipioListNewMunicipioToAttach.getClass(), municipioListNewMunicipioToAttach.getIdmunicipio());
                attachedMunicipioListNew.add(municipioListNewMunicipioToAttach);
            }
            municipioListNew = attachedMunicipioListNew;
            provincia.setMunicipioList(municipioListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            provincia.setEstudianteList(estudianteListNew);
            provincia = em.merge(provincia);
            if (paisidpaisOld != null && !paisidpaisOld.equals(paisidpaisNew)) {
                paisidpaisOld.getProvinciaList().remove(provincia);
                paisidpaisOld = em.merge(paisidpaisOld);
            }
            if (paisidpaisNew != null && !paisidpaisNew.equals(paisidpaisOld)) {
                paisidpaisNew.getProvinciaList().add(provincia);
                paisidpaisNew = em.merge(paisidpaisNew);
            }
            for (Municipio municipioListNewMunicipio : municipioListNew) {
                if (!municipioListOld.contains(municipioListNewMunicipio)) {
                    Provincia oldProvinciaidprovinciaOfMunicipioListNewMunicipio = municipioListNewMunicipio.getProvinciaidprovincia();
                    municipioListNewMunicipio.setProvinciaidprovincia(provincia);
                    municipioListNewMunicipio = em.merge(municipioListNewMunicipio);
                    if (oldProvinciaidprovinciaOfMunicipioListNewMunicipio != null && !oldProvinciaidprovinciaOfMunicipioListNewMunicipio.equals(provincia)) {
                        oldProvinciaidprovinciaOfMunicipioListNewMunicipio.getMunicipioList().remove(municipioListNewMunicipio);
                        oldProvinciaidprovinciaOfMunicipioListNewMunicipio = em.merge(oldProvinciaidprovinciaOfMunicipioListNewMunicipio);
                    }
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    estudianteListOldEstudiante.setProvinciaidprovincia(null);
                    estudianteListOldEstudiante = em.merge(estudianteListOldEstudiante);
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Provincia oldProvinciaidprovinciaOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getProvinciaidprovincia();
                    estudianteListNewEstudiante.setProvinciaidprovincia(provincia);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldProvinciaidprovinciaOfEstudianteListNewEstudiante != null && !oldProvinciaidprovinciaOfEstudianteListNewEstudiante.equals(provincia)) {
                        oldProvinciaidprovinciaOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldProvinciaidprovinciaOfEstudianteListNewEstudiante = em.merge(oldProvinciaidprovinciaOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = provincia.getIdprovincia();
                if (findProvincia(id) == null) {
                    throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.");
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
            Provincia provincia;
            try {
                provincia = em.getReference(Provincia.class, id);
                provincia.getIdprovincia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Municipio> municipioListOrphanCheck = provincia.getMunicipioList();
            for (Municipio municipioListOrphanCheckMunicipio : municipioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Provincia (" + provincia + ") cannot be destroyed since the Municipio " + municipioListOrphanCheckMunicipio + " in its municipioList field has a non-nullable provinciaidprovincia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pais paisidpais = provincia.getPaisidpais();
            if (paisidpais != null) {
                paisidpais.getProvinciaList().remove(provincia);
                paisidpais = em.merge(paisidpais);
            }
            List<Estudiante> estudianteList = provincia.getEstudianteList();
            for (Estudiante estudianteListEstudiante : estudianteList) {
                estudianteListEstudiante.setProvinciaidprovincia(null);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.remove(provincia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Provincia> findProvinciaEntities() {
        return findProvinciaEntities(true, -1, -1);
    }

    public List<Provincia> findProvinciaEntities(int maxResults, int firstResult) {
        return findProvinciaEntities(false, maxResults, firstResult);
    }

    private List<Provincia> findProvinciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Provincia.class));
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

    public Provincia findProvincia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Provincia.class, id);
        } finally {
            em.close();
        }
    }

    public int getProvinciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Provincia> rt = cq.from(Provincia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Provincia findProvinciaByNombreAndPais(String provincia, int pais) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p "
                    + "FROM Provincia p "
                    + "WHERE p.nombreprovincia= :provincia "
                    + "AND p.paisidpais.idpais= :pais");
            q.setParameter("provincia", provincia);
            q.setParameter("pais", pais);
            Provincia p = (Provincia) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Provincia findProvinciaByNombreAndPaisAvailable(String provincia, int pais) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p "
                    + "FROM Provincia p "
                    + "WHERE p.canceladoprovincia=false AND "
                    + "p.nombreprovincia= :provincia AND "
                    + "p.paisidpais.idpais= :pais");
            q.setParameter("provincia", provincia);
            q.setParameter("pais", pais);
            Provincia p = (Provincia) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findProvinciaAvailable(int pais) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p.nombreprovincia "
                    + "FROM Provincia p "
                    + "WHERE p.canceladoprovincia=false AND "
                    + "p.paisidpais.idpais= :pais AND "
                    + "p.idprovincia>=0 "
                    + "ORDER BY p.nombreprovincia");
            q.setParameter("pais", pais);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Provincia> findProvinciaList() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p FROM Provincia p WHERE  p.idprovincia>=0 ORDER BY p.nombreprovincia");
            List<Provincia> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
