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
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.FuenteIngreso;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class FuenteIngresoJpaController implements Serializable {

    public FuenteIngresoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FuenteIngreso fuenteIngreso) {
        if (fuenteIngreso.getFacultadCumCarreraEstudianteList() == null) {
            fuenteIngreso.setFacultadCumCarreraEstudianteList(new ArrayList<FacultadCumCarreraEstudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<FacultadCumCarreraEstudiante> attachedFacultadCumCarreraEstudianteList = new ArrayList<FacultadCumCarreraEstudiante>();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach : fuenteIngreso.getFacultadCumCarreraEstudianteList()) {
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach = em.getReference(facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach.getClass(), facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach.getFacultadCumCarreraEstudiantePK());
                attachedFacultadCumCarreraEstudianteList.add(facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach);
            }
            fuenteIngreso.setFacultadCumCarreraEstudianteList(attachedFacultadCumCarreraEstudianteList);
            em.persist(fuenteIngreso);
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante : fuenteIngreso.getFacultadCumCarreraEstudianteList()) {
                FuenteIngreso oldFuenteIngresofuenteIngresoIdOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.getFuenteIngresofuenteIngresoId();
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.setFuenteIngresofuenteIngresoId(fuenteIngreso);
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                if (oldFuenteIngresofuenteIngresoIdOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante != null) {
                    oldFuenteIngresofuenteIngresoIdOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                    oldFuenteIngresofuenteIngresoIdOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = em.merge(oldFuenteIngresofuenteIngresoIdOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FuenteIngreso fuenteIngreso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FuenteIngreso persistentFuenteIngreso = em.find(FuenteIngreso.class, fuenteIngreso.getFuenteIngresoId());
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListOld = persistentFuenteIngreso.getFacultadCumCarreraEstudianteList();
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListNew = fuenteIngreso.getFacultadCumCarreraEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListOld) {
                if (!facultadCumCarreraEstudianteListNew.contains(facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FacultadCumCarreraEstudiante " + facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante + " since its fuenteIngresofuenteIngresoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<FacultadCumCarreraEstudiante> attachedFacultadCumCarreraEstudianteListNew = new ArrayList<FacultadCumCarreraEstudiante>();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach : facultadCumCarreraEstudianteListNew) {
                facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach = em.getReference(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach.getClass(), facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach.getFacultadCumCarreraEstudiantePK());
                attachedFacultadCumCarreraEstudianteListNew.add(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach);
            }
            facultadCumCarreraEstudianteListNew = attachedFacultadCumCarreraEstudianteListNew;
            fuenteIngreso.setFacultadCumCarreraEstudianteList(facultadCumCarreraEstudianteListNew);
            fuenteIngreso = em.merge(fuenteIngreso);
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListNew) {
                if (!facultadCumCarreraEstudianteListOld.contains(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante)) {
                    FuenteIngreso oldFuenteIngresofuenteIngresoIdOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.getFuenteIngresofuenteIngresoId();
                    facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.setFuenteIngresofuenteIngresoId(fuenteIngreso);
                    facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                    if (oldFuenteIngresofuenteIngresoIdOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante != null && !oldFuenteIngresofuenteIngresoIdOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.equals(fuenteIngreso)) {
                        oldFuenteIngresofuenteIngresoIdOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                        oldFuenteIngresofuenteIngresoIdOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = em.merge(oldFuenteIngresofuenteIngresoIdOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fuenteIngreso.getFuenteIngresoId();
                if (findFuenteIngreso(id) == null) {
                    throw new NonexistentEntityException("The fuenteIngreso with id " + id + " no longer exists.");
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
            FuenteIngreso fuenteIngreso;
            try {
                fuenteIngreso = em.getReference(FuenteIngreso.class, id);
                fuenteIngreso.getFuenteIngresoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fuenteIngreso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListOrphanCheck = fuenteIngreso.getFacultadCumCarreraEstudianteList();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListOrphanCheckFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FuenteIngreso (" + fuenteIngreso + ") cannot be destroyed since the FacultadCumCarreraEstudiante " + facultadCumCarreraEstudianteListOrphanCheckFacultadCumCarreraEstudiante + " in its facultadCumCarreraEstudianteList field has a non-nullable fuenteIngresofuenteIngresoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(fuenteIngreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FuenteIngreso> findFuenteIngresoEntities() {
        return findFuenteIngresoEntities(true, -1, -1);
    }

    public List<FuenteIngreso> findFuenteIngresoEntities(int maxResults, int firstResult) {
        return findFuenteIngresoEntities(false, maxResults, firstResult);
    }

    private List<FuenteIngreso> findFuenteIngresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FuenteIngreso.class));
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

    public FuenteIngreso findFuenteIngreso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FuenteIngreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuenteIngresoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FuenteIngreso> rt = cq.from(FuenteIngreso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public FuenteIngreso findFuenteIngresoByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT f "
                    + "FROM FuenteIngreso f "
                    + "WHERE f.fuenteIngresoNombre= :nombre");
            q.setParameter("nombre", nombre);
            FuenteIngreso p = (FuenteIngreso) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public FuenteIngreso findFuenteIngresoByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT f "
                    + "FROM FuenteIngreso f "
                    + "WHERE f.fuenteIngresoCancelado=false AND "
                    + "f.fuenteIngresoNombre= :nombre");
            q.setParameter("nombre", nombre);
            FuenteIngreso p = (FuenteIngreso) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findFuenteIngresoAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT f.fuenteIngresoNombre "
                    + "FROM FuenteIngreso f "
                    + "WHERE f.fuenteIngresoCancelado=false "
                    + "ORDER BY f.fuenteIngresoNombre");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
