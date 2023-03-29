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
import cu.edu.unah.sigenuead.entity.Tutor;
import java.util.ArrayList;
import java.util.List;
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.Ocupacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class OcupacionJpaController implements Serializable {

    public OcupacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ocupacion ocupacion) {
        if (ocupacion.getTutorList() == null) {
            ocupacion.setTutorList(new ArrayList<Tutor>());
        }
        if (ocupacion.getEstudianteList() == null) {
            ocupacion.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tutor> attachedTutorList = new ArrayList<Tutor>();
            for (Tutor tutorListTutorToAttach : ocupacion.getTutorList()) {
                tutorListTutorToAttach = em.getReference(tutorListTutorToAttach.getClass(), tutorListTutorToAttach.getTutorId());
                attachedTutorList.add(tutorListTutorToAttach);
            }
            ocupacion.setTutorList(attachedTutorList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : ocupacion.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            ocupacion.setEstudianteList(attachedEstudianteList);
            em.persist(ocupacion);
            for (Tutor tutorListTutor : ocupacion.getTutorList()) {
                Ocupacion oldOcupacionocupacionIdOfTutorListTutor = tutorListTutor.getOcupacionocupacionId();
                tutorListTutor.setOcupacionocupacionId(ocupacion);
                tutorListTutor = em.merge(tutorListTutor);
                if (oldOcupacionocupacionIdOfTutorListTutor != null) {
                    oldOcupacionocupacionIdOfTutorListTutor.getTutorList().remove(tutorListTutor);
                    oldOcupacionocupacionIdOfTutorListTutor = em.merge(oldOcupacionocupacionIdOfTutorListTutor);
                }
            }
            for (Estudiante estudianteListEstudiante : ocupacion.getEstudianteList()) {
                Ocupacion oldOcupacionocupacionIdOfEstudianteListEstudiante = estudianteListEstudiante.getOcupacionocupacionId();
                estudianteListEstudiante.setOcupacionocupacionId(ocupacion);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldOcupacionocupacionIdOfEstudianteListEstudiante != null) {
                    oldOcupacionocupacionIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldOcupacionocupacionIdOfEstudianteListEstudiante = em.merge(oldOcupacionocupacionIdOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ocupacion ocupacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ocupacion persistentOcupacion = em.find(Ocupacion.class, ocupacion.getOcupacionId());
            List<Tutor> tutorListOld = persistentOcupacion.getTutorList();
            List<Tutor> tutorListNew = ocupacion.getTutorList();
            List<Estudiante> estudianteListOld = persistentOcupacion.getEstudianteList();
            List<Estudiante> estudianteListNew = ocupacion.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Tutor tutorListOldTutor : tutorListOld) {
                if (!tutorListNew.contains(tutorListOldTutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tutor " + tutorListOldTutor + " since its ocupacionocupacionId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Tutor> attachedTutorListNew = new ArrayList<Tutor>();
            for (Tutor tutorListNewTutorToAttach : tutorListNew) {
                tutorListNewTutorToAttach = em.getReference(tutorListNewTutorToAttach.getClass(), tutorListNewTutorToAttach.getTutorId());
                attachedTutorListNew.add(tutorListNewTutorToAttach);
            }
            tutorListNew = attachedTutorListNew;
            ocupacion.setTutorList(tutorListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            ocupacion.setEstudianteList(estudianteListNew);
            ocupacion = em.merge(ocupacion);
            for (Tutor tutorListNewTutor : tutorListNew) {
                if (!tutorListOld.contains(tutorListNewTutor)) {
                    Ocupacion oldOcupacionocupacionIdOfTutorListNewTutor = tutorListNewTutor.getOcupacionocupacionId();
                    tutorListNewTutor.setOcupacionocupacionId(ocupacion);
                    tutorListNewTutor = em.merge(tutorListNewTutor);
                    if (oldOcupacionocupacionIdOfTutorListNewTutor != null && !oldOcupacionocupacionIdOfTutorListNewTutor.equals(ocupacion)) {
                        oldOcupacionocupacionIdOfTutorListNewTutor.getTutorList().remove(tutorListNewTutor);
                        oldOcupacionocupacionIdOfTutorListNewTutor = em.merge(oldOcupacionocupacionIdOfTutorListNewTutor);
                    }
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    estudianteListOldEstudiante.setOcupacionocupacionId(null);
                    estudianteListOldEstudiante = em.merge(estudianteListOldEstudiante);
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Ocupacion oldOcupacionocupacionIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getOcupacionocupacionId();
                    estudianteListNewEstudiante.setOcupacionocupacionId(ocupacion);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldOcupacionocupacionIdOfEstudianteListNewEstudiante != null && !oldOcupacionocupacionIdOfEstudianteListNewEstudiante.equals(ocupacion)) {
                        oldOcupacionocupacionIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldOcupacionocupacionIdOfEstudianteListNewEstudiante = em.merge(oldOcupacionocupacionIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ocupacion.getOcupacionId();
                if (findOcupacion(id) == null) {
                    throw new NonexistentEntityException("The ocupacion with id " + id + " no longer exists.");
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
            Ocupacion ocupacion;
            try {
                ocupacion = em.getReference(Ocupacion.class, id);
                ocupacion.getOcupacionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ocupacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tutor> tutorListOrphanCheck = ocupacion.getTutorList();
            for (Tutor tutorListOrphanCheckTutor : tutorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ocupacion (" + ocupacion + ") cannot be destroyed since the Tutor " + tutorListOrphanCheckTutor + " in its tutorList field has a non-nullable ocupacionocupacionId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Estudiante> estudianteList = ocupacion.getEstudianteList();
            for (Estudiante estudianteListEstudiante : estudianteList) {
                estudianteListEstudiante.setOcupacionocupacionId(null);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.remove(ocupacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ocupacion> findOcupacionEntities() {
        return findOcupacionEntities(true, -1, -1);
    }

    public List<Ocupacion> findOcupacionEntities(int maxResults, int firstResult) {
        return findOcupacionEntities(false, maxResults, firstResult);
    }

    private List<Ocupacion> findOcupacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ocupacion.class));
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

    public Ocupacion findOcupacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ocupacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getOcupacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ocupacion> rt = cq.from(Ocupacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Ocupacion findOcupacionByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT o "
                    + "FROM Ocupacion o "
                    + "WHERE o.ocupacionNombre= :nombre");
            q.setParameter("nombre", nombre);
            Ocupacion p = (Ocupacion) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Ocupacion findOcupacionByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT o "
                    + "FROM Ocupacion o "
                    + "WHERE o.ocupacionCancelado=false AND "
                    + "o.ocupacionNombre= :nombre");
            q.setParameter("nombre", nombre);
            Ocupacion p = (Ocupacion) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findOcupacionAvailable() {
        EntityManager em = getEntityManager();
        try {
            List<String> p = em.createQuery("SELECT o.ocupacionNombre "
                    + "FROM Ocupacion o "
                    + "WHERE o.ocupacionCancelado=false "
                    + "ORDER BY o.ocupacionNombre").getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
