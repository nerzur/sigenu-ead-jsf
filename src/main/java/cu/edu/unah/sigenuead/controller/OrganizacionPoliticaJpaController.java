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
import cu.edu.unah.sigenuead.entity.OrganizacionPolitica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class OrganizacionPoliticaJpaController implements Serializable {

    public OrganizacionPoliticaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrganizacionPolitica organizacionPolitica) {
        if (organizacionPolitica.getTutorList() == null) {
            organizacionPolitica.setTutorList(new ArrayList<Tutor>());
        }
        if (organizacionPolitica.getEstudianteList() == null) {
            organizacionPolitica.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tutor> attachedTutorList = new ArrayList<Tutor>();
            for (Tutor tutorListTutorToAttach : organizacionPolitica.getTutorList()) {
                tutorListTutorToAttach = em.getReference(tutorListTutorToAttach.getClass(), tutorListTutorToAttach.getTutorId());
                attachedTutorList.add(tutorListTutorToAttach);
            }
            organizacionPolitica.setTutorList(attachedTutorList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : organizacionPolitica.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            organizacionPolitica.setEstudianteList(attachedEstudianteList);
            em.persist(organizacionPolitica);
            for (Tutor tutorListTutor : organizacionPolitica.getTutorList()) {
                OrganizacionPolitica oldOrganizacionPoliticaorganizacionPoliticaIdOfTutorListTutor = tutorListTutor.getOrganizacionPoliticaorganizacionPoliticaId();
                tutorListTutor.setOrganizacionPoliticaorganizacionPoliticaId(organizacionPolitica);
                tutorListTutor = em.merge(tutorListTutor);
                if (oldOrganizacionPoliticaorganizacionPoliticaIdOfTutorListTutor != null) {
                    oldOrganizacionPoliticaorganizacionPoliticaIdOfTutorListTutor.getTutorList().remove(tutorListTutor);
                    oldOrganizacionPoliticaorganizacionPoliticaIdOfTutorListTutor = em.merge(oldOrganizacionPoliticaorganizacionPoliticaIdOfTutorListTutor);
                }
            }
            for (Estudiante estudianteListEstudiante : organizacionPolitica.getEstudianteList()) {
                OrganizacionPolitica oldOrganizacionPoliticaorganizacionPoliticaIdOfEstudianteListEstudiante = estudianteListEstudiante.getOrganizacionPoliticaorganizacionPoliticaId();
                estudianteListEstudiante.setOrganizacionPoliticaorganizacionPoliticaId(organizacionPolitica);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldOrganizacionPoliticaorganizacionPoliticaIdOfEstudianteListEstudiante != null) {
                    oldOrganizacionPoliticaorganizacionPoliticaIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldOrganizacionPoliticaorganizacionPoliticaIdOfEstudianteListEstudiante = em.merge(oldOrganizacionPoliticaorganizacionPoliticaIdOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrganizacionPolitica organizacionPolitica) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrganizacionPolitica persistentOrganizacionPolitica = em.find(OrganizacionPolitica.class, organizacionPolitica.getOrganizacionPoliticaId());
            List<Tutor> tutorListOld = persistentOrganizacionPolitica.getTutorList();
            List<Tutor> tutorListNew = organizacionPolitica.getTutorList();
            List<Estudiante> estudianteListOld = persistentOrganizacionPolitica.getEstudianteList();
            List<Estudiante> estudianteListNew = organizacionPolitica.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Tutor tutorListOldTutor : tutorListOld) {
                if (!tutorListNew.contains(tutorListOldTutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tutor " + tutorListOldTutor + " since its organizacionPoliticaorganizacionPoliticaId field is not nullable.");
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its organizacionPoliticaorganizacionPoliticaId field is not nullable.");
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
            organizacionPolitica.setTutorList(tutorListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            organizacionPolitica.setEstudianteList(estudianteListNew);
            organizacionPolitica = em.merge(organizacionPolitica);
            for (Tutor tutorListNewTutor : tutorListNew) {
                if (!tutorListOld.contains(tutorListNewTutor)) {
                    OrganizacionPolitica oldOrganizacionPoliticaorganizacionPoliticaIdOfTutorListNewTutor = tutorListNewTutor.getOrganizacionPoliticaorganizacionPoliticaId();
                    tutorListNewTutor.setOrganizacionPoliticaorganizacionPoliticaId(organizacionPolitica);
                    tutorListNewTutor = em.merge(tutorListNewTutor);
                    if (oldOrganizacionPoliticaorganizacionPoliticaIdOfTutorListNewTutor != null && !oldOrganizacionPoliticaorganizacionPoliticaIdOfTutorListNewTutor.equals(organizacionPolitica)) {
                        oldOrganizacionPoliticaorganizacionPoliticaIdOfTutorListNewTutor.getTutorList().remove(tutorListNewTutor);
                        oldOrganizacionPoliticaorganizacionPoliticaIdOfTutorListNewTutor = em.merge(oldOrganizacionPoliticaorganizacionPoliticaIdOfTutorListNewTutor);
                    }
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    OrganizacionPolitica oldOrganizacionPoliticaorganizacionPoliticaIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getOrganizacionPoliticaorganizacionPoliticaId();
                    estudianteListNewEstudiante.setOrganizacionPoliticaorganizacionPoliticaId(organizacionPolitica);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldOrganizacionPoliticaorganizacionPoliticaIdOfEstudianteListNewEstudiante != null && !oldOrganizacionPoliticaorganizacionPoliticaIdOfEstudianteListNewEstudiante.equals(organizacionPolitica)) {
                        oldOrganizacionPoliticaorganizacionPoliticaIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldOrganizacionPoliticaorganizacionPoliticaIdOfEstudianteListNewEstudiante = em.merge(oldOrganizacionPoliticaorganizacionPoliticaIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = organizacionPolitica.getOrganizacionPoliticaId();
                if (findOrganizacionPolitica(id) == null) {
                    throw new NonexistentEntityException("The organizacionPolitica with id " + id + " no longer exists.");
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
            OrganizacionPolitica organizacionPolitica;
            try {
                organizacionPolitica = em.getReference(OrganizacionPolitica.class, id);
                organizacionPolitica.getOrganizacionPoliticaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The organizacionPolitica with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tutor> tutorListOrphanCheck = organizacionPolitica.getTutorList();
            for (Tutor tutorListOrphanCheckTutor : tutorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OrganizacionPolitica (" + organizacionPolitica + ") cannot be destroyed since the Tutor " + tutorListOrphanCheckTutor + " in its tutorList field has a non-nullable organizacionPoliticaorganizacionPoliticaId field.");
            }
            List<Estudiante> estudianteListOrphanCheck = organizacionPolitica.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OrganizacionPolitica (" + organizacionPolitica + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable organizacionPoliticaorganizacionPoliticaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(organizacionPolitica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrganizacionPolitica> findOrganizacionPoliticaEntities() {
        return findOrganizacionPoliticaEntities(true, -1, -1);
    }

    public List<OrganizacionPolitica> findOrganizacionPoliticaEntities(int maxResults, int firstResult) {
        return findOrganizacionPoliticaEntities(false, maxResults, firstResult);
    }

    private List<OrganizacionPolitica> findOrganizacionPoliticaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrganizacionPolitica.class));
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

    public OrganizacionPolitica findOrganizacionPolitica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrganizacionPolitica.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrganizacionPoliticaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrganizacionPolitica> rt = cq.from(OrganizacionPolitica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public OrganizacionPolitica findOrganizacionPoliticaByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT o "
                    + "FROM OrganizacionPolitica o "
                    + "WHERE o.organizacionPoliticaNombre= :nombre");
            q.setParameter("nombre", nombre);
            OrganizacionPolitica p = (OrganizacionPolitica) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public OrganizacionPolitica findOrganizacionPoliticaByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT o "
                    + "FROM OrganizacionPolitica o "
                    + "WHERE o.organizacionPoliticaCancelado=false AND "
                    + "o.organizacionPoliticaNombre= :nombre");
            q.setParameter("nombre", nombre);
            OrganizacionPolitica p = (OrganizacionPolitica) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findOrganizacionPoliticaAvailable() {
        EntityManager em = getEntityManager();
        try {
            List<String> p = em.createQuery("SELECT o.organizacionPoliticaNombre "
                    + "FROM OrganizacionPolitica o "
                    + "WHERE o.organizacionPoliticaCancelado=false "
                    + "ORDER BY o.organizacionPoliticaNombre").getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
