/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.entity.EspecialidadMilitar;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Estudiante;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class EspecialidadMilitarJpaController implements Serializable {

    public EspecialidadMilitarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EspecialidadMilitar especialidadMilitar) {
        if (especialidadMilitar.getEstudianteList() == null) {
            especialidadMilitar.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : especialidadMilitar.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            especialidadMilitar.setEstudianteList(attachedEstudianteList);
            em.persist(especialidadMilitar);
            for (Estudiante estudianteListEstudiante : especialidadMilitar.getEstudianteList()) {
                EspecialidadMilitar oldEspecialidadMilitarespecialidadMilitarIdOfEstudianteListEstudiante = estudianteListEstudiante.getEspecialidadMilitarespecialidadMilitarId();
                estudianteListEstudiante.setEspecialidadMilitarespecialidadMilitarId(especialidadMilitar);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldEspecialidadMilitarespecialidadMilitarIdOfEstudianteListEstudiante != null) {
                    oldEspecialidadMilitarespecialidadMilitarIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldEspecialidadMilitarespecialidadMilitarIdOfEstudianteListEstudiante = em.merge(oldEspecialidadMilitarespecialidadMilitarIdOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EspecialidadMilitar especialidadMilitar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EspecialidadMilitar persistentEspecialidadMilitar = em.find(EspecialidadMilitar.class, especialidadMilitar.getEspecialidadMilitarId());
            List<Estudiante> estudianteListOld = persistentEspecialidadMilitar.getEstudianteList();
            List<Estudiante> estudianteListNew = especialidadMilitar.getEstudianteList();
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            especialidadMilitar.setEstudianteList(estudianteListNew);
            especialidadMilitar = em.merge(especialidadMilitar);
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    estudianteListOldEstudiante.setEspecialidadMilitarespecialidadMilitarId(null);
                    estudianteListOldEstudiante = em.merge(estudianteListOldEstudiante);
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    EspecialidadMilitar oldEspecialidadMilitarespecialidadMilitarIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getEspecialidadMilitarespecialidadMilitarId();
                    estudianteListNewEstudiante.setEspecialidadMilitarespecialidadMilitarId(especialidadMilitar);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldEspecialidadMilitarespecialidadMilitarIdOfEstudianteListNewEstudiante != null && !oldEspecialidadMilitarespecialidadMilitarIdOfEstudianteListNewEstudiante.equals(especialidadMilitar)) {
                        oldEspecialidadMilitarespecialidadMilitarIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldEspecialidadMilitarespecialidadMilitarIdOfEstudianteListNewEstudiante = em.merge(oldEspecialidadMilitarespecialidadMilitarIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = especialidadMilitar.getEspecialidadMilitarId();
                if (findEspecialidadMilitar(id) == null) {
                    throw new NonexistentEntityException("The especialidadMilitar with id " + id + " no longer exists.");
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
            EspecialidadMilitar especialidadMilitar;
            try {
                especialidadMilitar = em.getReference(EspecialidadMilitar.class, id);
                especialidadMilitar.getEspecialidadMilitarId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The especialidadMilitar with id " + id + " no longer exists.", enfe);
            }
            List<Estudiante> estudianteList = especialidadMilitar.getEstudianteList();
            for (Estudiante estudianteListEstudiante : estudianteList) {
                estudianteListEstudiante.setEspecialidadMilitarespecialidadMilitarId(null);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.remove(especialidadMilitar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EspecialidadMilitar> findEspecialidadMilitarEntities() {
        return findEspecialidadMilitarEntities(true, -1, -1);
    }

    public List<EspecialidadMilitar> findEspecialidadMilitarEntities(int maxResults, int firstResult) {
        return findEspecialidadMilitarEntities(false, maxResults, firstResult);
    }

    private List<EspecialidadMilitar> findEspecialidadMilitarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EspecialidadMilitar.class));
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

    public EspecialidadMilitar findEspecialidadMilitar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EspecialidadMilitar.class, id);
        } finally {
            em.close();
        }
    }

    public int getEspecialidadMilitarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EspecialidadMilitar> rt = cq.from(EspecialidadMilitar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public EspecialidadMilitar findEspecialidadMilitarByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e "
                    + "FROM EspecialidadMilitar e "
                    + "WHERE e.especialidadMilitarNombre= :nombre");
            q.setParameter("nombre", nombre);
            EspecialidadMilitar p = (EspecialidadMilitar) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public EspecialidadMilitar findEspecialidadMilitarByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e "
                    + "FROM EspecialidadMilitar e "
                    + "WHERE e.especialidadMilitarCancelado=false AND "
                    + "e.especialidadMilitarNombre= :nombre");
            q.setParameter("nombre", nombre);
            EspecialidadMilitar p = (EspecialidadMilitar) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findEspecialidadMilitarAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e.especialidadMilitarNombre "
                    + "FROM EspecialidadMilitar e "
                    + "WHERE e.especialidadMilitarCancelado=false "
                    + "ORDER BY e.especialidadMilitarNombre");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
