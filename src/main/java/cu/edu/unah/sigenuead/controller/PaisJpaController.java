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
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.Pais;
import java.util.ArrayList;
import java.util.List;
import cu.edu.unah.sigenuead.entity.Provincia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class PaisJpaController implements Serializable {

    public PaisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pais pais) {
        if (pais.getEstudianteList() == null) {
            pais.setEstudianteList(new ArrayList<Estudiante>());
        }
        if (pais.getProvinciaList() == null) {
            pais.setProvinciaList(new ArrayList<Provincia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : pais.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            pais.setEstudianteList(attachedEstudianteList);
            List<Provincia> attachedProvinciaList = new ArrayList<Provincia>();
            for (Provincia provinciaListProvinciaToAttach : pais.getProvinciaList()) {
                provinciaListProvinciaToAttach = em.getReference(provinciaListProvinciaToAttach.getClass(), provinciaListProvinciaToAttach.getIdprovincia());
                attachedProvinciaList.add(provinciaListProvinciaToAttach);
            }
            pais.setProvinciaList(attachedProvinciaList);
            em.persist(pais);
            for (Estudiante estudianteListEstudiante : pais.getEstudianteList()) {
                Pais oldPaisidpaisOfEstudianteListEstudiante = estudianteListEstudiante.getPaisidpais();
                estudianteListEstudiante.setPaisidpais(pais);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldPaisidpaisOfEstudianteListEstudiante != null) {
                    oldPaisidpaisOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldPaisidpaisOfEstudianteListEstudiante = em.merge(oldPaisidpaisOfEstudianteListEstudiante);
                }
            }
            for (Provincia provinciaListProvincia : pais.getProvinciaList()) {
                Pais oldPaisidpaisOfProvinciaListProvincia = provinciaListProvincia.getPaisidpais();
                provinciaListProvincia.setPaisidpais(pais);
                provinciaListProvincia = em.merge(provinciaListProvincia);
                if (oldPaisidpaisOfProvinciaListProvincia != null) {
                    oldPaisidpaisOfProvinciaListProvincia.getProvinciaList().remove(provinciaListProvincia);
                    oldPaisidpaisOfProvinciaListProvincia = em.merge(oldPaisidpaisOfProvinciaListProvincia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pais pais) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais persistentPais = em.find(Pais.class, pais.getIdpais());
            List<Estudiante> estudianteListOld = persistentPais.getEstudianteList();
            List<Estudiante> estudianteListNew = pais.getEstudianteList();
            List<Provincia> provinciaListOld = persistentPais.getProvinciaList();
            List<Provincia> provinciaListNew = pais.getProvinciaList();
            List<String> illegalOrphanMessages = null;
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its paisidpais field is not nullable.");
                }
            }
            for (Provincia provinciaListOldProvincia : provinciaListOld) {
                if (!provinciaListNew.contains(provinciaListOldProvincia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Provincia " + provinciaListOldProvincia + " since its paisidpais field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            pais.setEstudianteList(estudianteListNew);
            List<Provincia> attachedProvinciaListNew = new ArrayList<Provincia>();
            for (Provincia provinciaListNewProvinciaToAttach : provinciaListNew) {
                provinciaListNewProvinciaToAttach = em.getReference(provinciaListNewProvinciaToAttach.getClass(), provinciaListNewProvinciaToAttach.getIdprovincia());
                attachedProvinciaListNew.add(provinciaListNewProvinciaToAttach);
            }
            provinciaListNew = attachedProvinciaListNew;
            pais.setProvinciaList(provinciaListNew);
            pais = em.merge(pais);
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Pais oldPaisidpaisOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getPaisidpais();
                    estudianteListNewEstudiante.setPaisidpais(pais);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldPaisidpaisOfEstudianteListNewEstudiante != null && !oldPaisidpaisOfEstudianteListNewEstudiante.equals(pais)) {
                        oldPaisidpaisOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldPaisidpaisOfEstudianteListNewEstudiante = em.merge(oldPaisidpaisOfEstudianteListNewEstudiante);
                    }
                }
            }
            for (Provincia provinciaListNewProvincia : provinciaListNew) {
                if (!provinciaListOld.contains(provinciaListNewProvincia)) {
                    Pais oldPaisidpaisOfProvinciaListNewProvincia = provinciaListNewProvincia.getPaisidpais();
                    provinciaListNewProvincia.setPaisidpais(pais);
                    provinciaListNewProvincia = em.merge(provinciaListNewProvincia);
                    if (oldPaisidpaisOfProvinciaListNewProvincia != null && !oldPaisidpaisOfProvinciaListNewProvincia.equals(pais)) {
                        oldPaisidpaisOfProvinciaListNewProvincia.getProvinciaList().remove(provinciaListNewProvincia);
                        oldPaisidpaisOfProvinciaListNewProvincia = em.merge(oldPaisidpaisOfProvinciaListNewProvincia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pais.getIdpais();
                if (findPais(id) == null) {
                    throw new NonexistentEntityException("The pais with id " + id + " no longer exists.");
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
            Pais pais;
            try {
                pais = em.getReference(Pais.class, id);
                pais.getIdpais();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pais with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estudiante> estudianteListOrphanCheck = pais.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pais (" + pais + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable paisidpais field.");
            }
            List<Provincia> provinciaListOrphanCheck = pais.getProvinciaList();
            for (Provincia provinciaListOrphanCheckProvincia : provinciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pais (" + pais + ") cannot be destroyed since the Provincia " + provinciaListOrphanCheckProvincia + " in its provinciaList field has a non-nullable paisidpais field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pais> findPaisEntities() {
        return findPaisEntities(true, -1, -1);
    }

    public List<Pais> findPaisEntities(int maxResults, int firstResult) {
        return findPaisEntities(false, maxResults, firstResult);
    }

    private List<Pais> findPaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pais.class));
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

    public Pais findPais(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pais.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pais> rt = cq.from(Pais.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Pais findPaisByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p "
                    + "FROM Pais p "
                    + "WHERE p.nombrepais= :nombre");
            q.setParameter("nombre", nombre);
            Pais p = (Pais) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Pais findPaisByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p FROM Pais p WHERE p.canceladopais=false and p.nombrepais= :nombre");
            q.setParameter("nombre", nombre);
            Pais p = (Pais) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findPaisAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p.nombrepais FROM Pais p WHERE p.canceladopais=false AND p.idpais>=0 ORDER BY p.nombrepais");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Pais> findPaisList() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p FROM Pais p WHERE p.idpais>=0 ORDER BY p.nombrepais");
            List<Pais> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
