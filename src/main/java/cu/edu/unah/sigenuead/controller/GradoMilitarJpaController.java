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
import cu.edu.unah.sigenuead.entity.GradoMilitar;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class GradoMilitarJpaController implements Serializable {

    public GradoMilitarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GradoMilitar gradoMilitar) {
        if (gradoMilitar.getEstudianteList() == null) {
            gradoMilitar.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : gradoMilitar.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            gradoMilitar.setEstudianteList(attachedEstudianteList);
            em.persist(gradoMilitar);
            for (Estudiante estudianteListEstudiante : gradoMilitar.getEstudianteList()) {
                GradoMilitar oldGradoMilitargradoMilitarIdOfEstudianteListEstudiante = estudianteListEstudiante.getGradoMilitargradoMilitarId();
                estudianteListEstudiante.setGradoMilitargradoMilitarId(gradoMilitar);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldGradoMilitargradoMilitarIdOfEstudianteListEstudiante != null) {
                    oldGradoMilitargradoMilitarIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldGradoMilitargradoMilitarIdOfEstudianteListEstudiante = em.merge(oldGradoMilitargradoMilitarIdOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GradoMilitar gradoMilitar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GradoMilitar persistentGradoMilitar = em.find(GradoMilitar.class, gradoMilitar.getGradoMilitarId());
            List<Estudiante> estudianteListOld = persistentGradoMilitar.getEstudianteList();
            List<Estudiante> estudianteListNew = gradoMilitar.getEstudianteList();
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            gradoMilitar.setEstudianteList(estudianteListNew);
            gradoMilitar = em.merge(gradoMilitar);
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    estudianteListOldEstudiante.setGradoMilitargradoMilitarId(null);
                    estudianteListOldEstudiante = em.merge(estudianteListOldEstudiante);
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    GradoMilitar oldGradoMilitargradoMilitarIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getGradoMilitargradoMilitarId();
                    estudianteListNewEstudiante.setGradoMilitargradoMilitarId(gradoMilitar);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldGradoMilitargradoMilitarIdOfEstudianteListNewEstudiante != null && !oldGradoMilitargradoMilitarIdOfEstudianteListNewEstudiante.equals(gradoMilitar)) {
                        oldGradoMilitargradoMilitarIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldGradoMilitargradoMilitarIdOfEstudianteListNewEstudiante = em.merge(oldGradoMilitargradoMilitarIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = gradoMilitar.getGradoMilitarId();
                if (findGradoMilitar(id) == null) {
                    throw new NonexistentEntityException("The gradoMilitar with id " + id + " no longer exists.");
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
            GradoMilitar gradoMilitar;
            try {
                gradoMilitar = em.getReference(GradoMilitar.class, id);
                gradoMilitar.getGradoMilitarId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gradoMilitar with id " + id + " no longer exists.", enfe);
            }
            List<Estudiante> estudianteList = gradoMilitar.getEstudianteList();
            for (Estudiante estudianteListEstudiante : estudianteList) {
                estudianteListEstudiante.setGradoMilitargradoMilitarId(null);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.remove(gradoMilitar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GradoMilitar> findGradoMilitarEntities() {
        return findGradoMilitarEntities(true, -1, -1);
    }

    public List<GradoMilitar> findGradoMilitarEntities(int maxResults, int firstResult) {
        return findGradoMilitarEntities(false, maxResults, firstResult);
    }

    private List<GradoMilitar> findGradoMilitarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GradoMilitar.class));
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

    public GradoMilitar findGradoMilitar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GradoMilitar.class, id);
        } finally {
            em.close();
        }
    }

    public int getGradoMilitarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GradoMilitar> rt = cq.from(GradoMilitar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public GradoMilitar findGradoMilitarByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT g "
                    + "FROM GradoMilitar g "
                    + "WHERE g.gradoMilitarNombre= :nombre");
            q.setParameter("nombre", nombre);
            GradoMilitar p = (GradoMilitar) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public GradoMilitar findGradoMilitarByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT g "
                    + "FROM GradoMilitar g "
                    + "WHERE g.gradoMilitarCancelado=false AND "
                    + "g.gradoMilitarNombre= :nombre");
            q.setParameter("nombre", nombre);
            GradoMilitar p = (GradoMilitar) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findGradoMilitarAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT g.gradoMilitarNombre "
                    + "FROM GradoMilitar g "
                    + "WHERE g.gradoMilitarCancelado=false "
                    + "ORDER BY g.gradoMilitarNombre");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
