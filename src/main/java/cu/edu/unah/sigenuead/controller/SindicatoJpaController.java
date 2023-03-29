/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.Sindicato;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class SindicatoJpaController implements Serializable {

    public SindicatoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sindicato sindicato) {
        if (sindicato.getEstudianteList() == null) {
            sindicato.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : sindicato.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            sindicato.setEstudianteList(attachedEstudianteList);
            em.persist(sindicato);
            for (Estudiante estudianteListEstudiante : sindicato.getEstudianteList()) {
                Sindicato oldSindicatoidsindicatoOfEstudianteListEstudiante = estudianteListEstudiante.getSindicatoidsindicato();
                estudianteListEstudiante.setSindicatoidsindicato(sindicato);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldSindicatoidsindicatoOfEstudianteListEstudiante != null) {
                    oldSindicatoidsindicatoOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldSindicatoidsindicatoOfEstudianteListEstudiante = em.merge(oldSindicatoidsindicatoOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sindicato sindicato) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sindicato persistentSindicato = em.find(Sindicato.class, sindicato.getIdsindicato());
            List<Estudiante> estudianteListOld = persistentSindicato.getEstudianteList();
            List<Estudiante> estudianteListNew = sindicato.getEstudianteList();
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            sindicato.setEstudianteList(estudianteListNew);
            sindicato = em.merge(sindicato);
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    estudianteListOldEstudiante.setSindicatoidsindicato(null);
                    estudianteListOldEstudiante = em.merge(estudianteListOldEstudiante);
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Sindicato oldSindicatoidsindicatoOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getSindicatoidsindicato();
                    estudianteListNewEstudiante.setSindicatoidsindicato(sindicato);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldSindicatoidsindicatoOfEstudianteListNewEstudiante != null && !oldSindicatoidsindicatoOfEstudianteListNewEstudiante.equals(sindicato)) {
                        oldSindicatoidsindicatoOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldSindicatoidsindicatoOfEstudianteListNewEstudiante = em.merge(oldSindicatoidsindicatoOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sindicato.getIdsindicato();
                if (findSindicato(id) == null) {
                    throw new NonexistentEntityException("The sindicato with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sindicato sindicato;
            try {
                sindicato = em.getReference(Sindicato.class, id);
                sindicato.getIdsindicato();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sindicato with id " + id + " no longer exists.", enfe);
            }
            List<Estudiante> estudianteList = sindicato.getEstudianteList();
            for (Estudiante estudianteListEstudiante : estudianteList) {
                estudianteListEstudiante.setSindicatoidsindicato(null);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.remove(sindicato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sindicato> findSindicatoEntities() {
        return findSindicatoEntities(true, -1, -1);
    }

    public List<Sindicato> findSindicatoEntities(int maxResults, int firstResult) {
        return findSindicatoEntities(false, maxResults, firstResult);
    }

    private List<Sindicato> findSindicatoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sindicato.class));
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

    public Sindicato findSindicato(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sindicato.class, id);
        } finally {
            em.close();
        }
    }

    public int getSindicatoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sindicato> rt = cq.from(Sindicato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Sindicato findSindicatoByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT s "
                    + "FROM Sindicato s "
                    + "WHERE s.nombresindicato= :nombre");
            q.setParameter("nombre", nombre);
            Sindicato p = (Sindicato) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Sindicato findSindicatoByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT s "
                    + "FROM Sindicato s "
                    + "WHERE s.canceladosindicato=false AND "
                    + "s.nombresindicato= :nombre");
            q.setParameter("nombre", nombre);
            Sindicato p = (Sindicato) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findSindicatoAvailable() {
        EntityManager em = getEntityManager();
        try {
            List<String> p = em.createQuery("SELECT s.nombresindicato "
                    + "FROM Sindicato s "
                    + "WHERE s.canceladosindicato=false "
                    + "ORDER BY s.nombresindicato").getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
