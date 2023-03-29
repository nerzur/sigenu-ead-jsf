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
import cu.edu.unah.sigenuead.entity.NivelEscolar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class NivelEscolarJpaController implements Serializable {

    public NivelEscolarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NivelEscolar nivelEscolar) {
        if (nivelEscolar.getTutorList() == null) {
            nivelEscolar.setTutorList(new ArrayList<Tutor>());
        }
        if (nivelEscolar.getEstudianteList() == null) {
            nivelEscolar.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tutor> attachedTutorList = new ArrayList<Tutor>();
            for (Tutor tutorListTutorToAttach : nivelEscolar.getTutorList()) {
                tutorListTutorToAttach = em.getReference(tutorListTutorToAttach.getClass(), tutorListTutorToAttach.getTutorId());
                attachedTutorList.add(tutorListTutorToAttach);
            }
            nivelEscolar.setTutorList(attachedTutorList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : nivelEscolar.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            nivelEscolar.setEstudianteList(attachedEstudianteList);
            em.persist(nivelEscolar);
            for (Tutor tutorListTutor : nivelEscolar.getTutorList()) {
                NivelEscolar oldNivelEscolarnivelEscolarIdOfTutorListTutor = tutorListTutor.getNivelEscolarnivelEscolarId();
                tutorListTutor.setNivelEscolarnivelEscolarId(nivelEscolar);
                tutorListTutor = em.merge(tutorListTutor);
                if (oldNivelEscolarnivelEscolarIdOfTutorListTutor != null) {
                    oldNivelEscolarnivelEscolarIdOfTutorListTutor.getTutorList().remove(tutorListTutor);
                    oldNivelEscolarnivelEscolarIdOfTutorListTutor = em.merge(oldNivelEscolarnivelEscolarIdOfTutorListTutor);
                }
            }
            for (Estudiante estudianteListEstudiante : nivelEscolar.getEstudianteList()) {
                NivelEscolar oldNivelEscolarnivelEscolarIdOfEstudianteListEstudiante = estudianteListEstudiante.getNivelEscolarnivelEscolarId();
                estudianteListEstudiante.setNivelEscolarnivelEscolarId(nivelEscolar);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldNivelEscolarnivelEscolarIdOfEstudianteListEstudiante != null) {
                    oldNivelEscolarnivelEscolarIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldNivelEscolarnivelEscolarIdOfEstudianteListEstudiante = em.merge(oldNivelEscolarnivelEscolarIdOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NivelEscolar nivelEscolar) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NivelEscolar persistentNivelEscolar = em.find(NivelEscolar.class, nivelEscolar.getNivelEscolarId());
            List<Tutor> tutorListOld = persistentNivelEscolar.getTutorList();
            List<Tutor> tutorListNew = nivelEscolar.getTutorList();
            List<Estudiante> estudianteListOld = persistentNivelEscolar.getEstudianteList();
            List<Estudiante> estudianteListNew = nivelEscolar.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Tutor tutorListOldTutor : tutorListOld) {
                if (!tutorListNew.contains(tutorListOldTutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tutor " + tutorListOldTutor + " since its nivelEscolarnivelEscolarId field is not nullable.");
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its nivelEscolarnivelEscolarId field is not nullable.");
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
            nivelEscolar.setTutorList(tutorListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            nivelEscolar.setEstudianteList(estudianteListNew);
            nivelEscolar = em.merge(nivelEscolar);
            for (Tutor tutorListNewTutor : tutorListNew) {
                if (!tutorListOld.contains(tutorListNewTutor)) {
                    NivelEscolar oldNivelEscolarnivelEscolarIdOfTutorListNewTutor = tutorListNewTutor.getNivelEscolarnivelEscolarId();
                    tutorListNewTutor.setNivelEscolarnivelEscolarId(nivelEscolar);
                    tutorListNewTutor = em.merge(tutorListNewTutor);
                    if (oldNivelEscolarnivelEscolarIdOfTutorListNewTutor != null && !oldNivelEscolarnivelEscolarIdOfTutorListNewTutor.equals(nivelEscolar)) {
                        oldNivelEscolarnivelEscolarIdOfTutorListNewTutor.getTutorList().remove(tutorListNewTutor);
                        oldNivelEscolarnivelEscolarIdOfTutorListNewTutor = em.merge(oldNivelEscolarnivelEscolarIdOfTutorListNewTutor);
                    }
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    NivelEscolar oldNivelEscolarnivelEscolarIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getNivelEscolarnivelEscolarId();
                    estudianteListNewEstudiante.setNivelEscolarnivelEscolarId(nivelEscolar);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldNivelEscolarnivelEscolarIdOfEstudianteListNewEstudiante != null && !oldNivelEscolarnivelEscolarIdOfEstudianteListNewEstudiante.equals(nivelEscolar)) {
                        oldNivelEscolarnivelEscolarIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldNivelEscolarnivelEscolarIdOfEstudianteListNewEstudiante = em.merge(oldNivelEscolarnivelEscolarIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nivelEscolar.getNivelEscolarId();
                if (findNivelEscolar(id) == null) {
                    throw new NonexistentEntityException("The nivelEscolar with id " + id + " no longer exists.");
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
            NivelEscolar nivelEscolar;
            try {
                nivelEscolar = em.getReference(NivelEscolar.class, id);
                nivelEscolar.getNivelEscolarId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nivelEscolar with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tutor> tutorListOrphanCheck = nivelEscolar.getTutorList();
            for (Tutor tutorListOrphanCheckTutor : tutorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NivelEscolar (" + nivelEscolar + ") cannot be destroyed since the Tutor " + tutorListOrphanCheckTutor + " in its tutorList field has a non-nullable nivelEscolarnivelEscolarId field.");
            }
            List<Estudiante> estudianteListOrphanCheck = nivelEscolar.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NivelEscolar (" + nivelEscolar + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable nivelEscolarnivelEscolarId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(nivelEscolar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NivelEscolar> findNivelEscolarEntities() {
        return findNivelEscolarEntities(true, -1, -1);
    }

    public List<NivelEscolar> findNivelEscolarEntities(int maxResults, int firstResult) {
        return findNivelEscolarEntities(false, maxResults, firstResult);
    }

    private List<NivelEscolar> findNivelEscolarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NivelEscolar.class));
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

    public NivelEscolar findNivelEscolar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NivelEscolar.class, id);
        } finally {
            em.close();
        }
    }

    public int getNivelEscolarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NivelEscolar> rt = cq.from(NivelEscolar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public NivelEscolar findNivelEscolarByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT n "
                    + "FROM NivelEscolar n "
                    + "WHERE n.nivelEscolarNombre= :nombre");
            q.setParameter("nombre", nombre);
            NivelEscolar p = (NivelEscolar) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public NivelEscolar findNivelEscolarByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT n "
                    + "FROM NivelEscolar n "
                    + "WHERE n.nivelEscolarCancelado=false AND "
                    + "n.nivelEscolarNombre= :nombre");
            q.setParameter("nombre", nombre);
            NivelEscolar p = (NivelEscolar) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findNivelEscolarAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT n.nivelEscolarNombre "
                    + "FROM NivelEscolar n "
                    + "WHERE n.nivelEscolarCancelado=false "
                    + "ORDER BY n.nivelEscolarNombre");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
