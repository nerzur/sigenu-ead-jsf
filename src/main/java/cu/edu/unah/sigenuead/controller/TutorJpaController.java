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
import cu.edu.unah.sigenuead.entity.NivelEscolar;
import cu.edu.unah.sigenuead.entity.Ocupacion;
import cu.edu.unah.sigenuead.entity.OrganizacionPolitica;
import cu.edu.unah.sigenuead.entity.Sexo;
import cu.edu.unah.sigenuead.entity.Tutor;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class TutorJpaController implements Serializable {

    public TutorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tutor tutor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante estudianteestudianteId = tutor.getEstudianteestudianteId();
            if (estudianteestudianteId != null) {
                estudianteestudianteId = em.getReference(estudianteestudianteId.getClass(), estudianteestudianteId.getEstudianteId());
                tutor.setEstudianteestudianteId(estudianteestudianteId);
            }
            NivelEscolar nivelEscolarnivelEscolarId = tutor.getNivelEscolarnivelEscolarId();
            if (nivelEscolarnivelEscolarId != null) {
                nivelEscolarnivelEscolarId = em.getReference(nivelEscolarnivelEscolarId.getClass(), nivelEscolarnivelEscolarId.getNivelEscolarId());
                tutor.setNivelEscolarnivelEscolarId(nivelEscolarnivelEscolarId);
            }
            Ocupacion ocupacionocupacionId = tutor.getOcupacionocupacionId();
            if (ocupacionocupacionId != null) {
                ocupacionocupacionId = em.getReference(ocupacionocupacionId.getClass(), ocupacionocupacionId.getOcupacionId());
                tutor.setOcupacionocupacionId(ocupacionocupacionId);
            }
            OrganizacionPolitica organizacionPoliticaorganizacionPoliticaId = tutor.getOrganizacionPoliticaorganizacionPoliticaId();
            if (organizacionPoliticaorganizacionPoliticaId != null) {
                organizacionPoliticaorganizacionPoliticaId = em.getReference(organizacionPoliticaorganizacionPoliticaId.getClass(), organizacionPoliticaorganizacionPoliticaId.getOrganizacionPoliticaId());
                tutor.setOrganizacionPoliticaorganizacionPoliticaId(organizacionPoliticaorganizacionPoliticaId);
            }
            Sexo sexosexoId = tutor.getSexosexoId();
            if (sexosexoId != null) {
                sexosexoId = em.getReference(sexosexoId.getClass(), sexosexoId.getSexoId());
                tutor.setSexosexoId(sexosexoId);
            }
            em.persist(tutor);
            if (estudianteestudianteId != null) {
                estudianteestudianteId.getTutorList().add(tutor);
                estudianteestudianteId = em.merge(estudianteestudianteId);
            }
            if (nivelEscolarnivelEscolarId != null) {
                nivelEscolarnivelEscolarId.getTutorList().add(tutor);
                nivelEscolarnivelEscolarId = em.merge(nivelEscolarnivelEscolarId);
            }
            if (ocupacionocupacionId != null) {
                ocupacionocupacionId.getTutorList().add(tutor);
                ocupacionocupacionId = em.merge(ocupacionocupacionId);
            }
            if (organizacionPoliticaorganizacionPoliticaId != null) {
                organizacionPoliticaorganizacionPoliticaId.getTutorList().add(tutor);
                organizacionPoliticaorganizacionPoliticaId = em.merge(organizacionPoliticaorganizacionPoliticaId);
            }
            if (sexosexoId != null) {
                sexosexoId.getTutorList().add(tutor);
                sexosexoId = em.merge(sexosexoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tutor tutor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutor persistentTutor = em.find(Tutor.class, tutor.getTutorId());
            Estudiante estudianteestudianteIdOld = persistentTutor.getEstudianteestudianteId();
            Estudiante estudianteestudianteIdNew = tutor.getEstudianteestudianteId();
            NivelEscolar nivelEscolarnivelEscolarIdOld = persistentTutor.getNivelEscolarnivelEscolarId();
            NivelEscolar nivelEscolarnivelEscolarIdNew = tutor.getNivelEscolarnivelEscolarId();
            Ocupacion ocupacionocupacionIdOld = persistentTutor.getOcupacionocupacionId();
            Ocupacion ocupacionocupacionIdNew = tutor.getOcupacionocupacionId();
            OrganizacionPolitica organizacionPoliticaorganizacionPoliticaIdOld = persistentTutor.getOrganizacionPoliticaorganizacionPoliticaId();
            OrganizacionPolitica organizacionPoliticaorganizacionPoliticaIdNew = tutor.getOrganizacionPoliticaorganizacionPoliticaId();
            Sexo sexosexoIdOld = persistentTutor.getSexosexoId();
            Sexo sexosexoIdNew = tutor.getSexosexoId();
            if (estudianteestudianteIdNew != null) {
                estudianteestudianteIdNew = em.getReference(estudianteestudianteIdNew.getClass(), estudianteestudianteIdNew.getEstudianteId());
                tutor.setEstudianteestudianteId(estudianteestudianteIdNew);
            }
            if (nivelEscolarnivelEscolarIdNew != null) {
                nivelEscolarnivelEscolarIdNew = em.getReference(nivelEscolarnivelEscolarIdNew.getClass(), nivelEscolarnivelEscolarIdNew.getNivelEscolarId());
                tutor.setNivelEscolarnivelEscolarId(nivelEscolarnivelEscolarIdNew);
            }
            if (ocupacionocupacionIdNew != null) {
                ocupacionocupacionIdNew = em.getReference(ocupacionocupacionIdNew.getClass(), ocupacionocupacionIdNew.getOcupacionId());
                tutor.setOcupacionocupacionId(ocupacionocupacionIdNew);
            }
            if (organizacionPoliticaorganizacionPoliticaIdNew != null) {
                organizacionPoliticaorganizacionPoliticaIdNew = em.getReference(organizacionPoliticaorganizacionPoliticaIdNew.getClass(), organizacionPoliticaorganizacionPoliticaIdNew.getOrganizacionPoliticaId());
                tutor.setOrganizacionPoliticaorganizacionPoliticaId(organizacionPoliticaorganizacionPoliticaIdNew);
            }
            if (sexosexoIdNew != null) {
                sexosexoIdNew = em.getReference(sexosexoIdNew.getClass(), sexosexoIdNew.getSexoId());
                tutor.setSexosexoId(sexosexoIdNew);
            }
            tutor = em.merge(tutor);
            if (estudianteestudianteIdOld != null && !estudianteestudianteIdOld.equals(estudianteestudianteIdNew)) {
                estudianteestudianteIdOld.getTutorList().remove(tutor);
                estudianteestudianteIdOld = em.merge(estudianteestudianteIdOld);
            }
            if (estudianteestudianteIdNew != null && !estudianteestudianteIdNew.equals(estudianteestudianteIdOld)) {
                estudianteestudianteIdNew.getTutorList().add(tutor);
                estudianteestudianteIdNew = em.merge(estudianteestudianteIdNew);
            }
            if (nivelEscolarnivelEscolarIdOld != null && !nivelEscolarnivelEscolarIdOld.equals(nivelEscolarnivelEscolarIdNew)) {
                nivelEscolarnivelEscolarIdOld.getTutorList().remove(tutor);
                nivelEscolarnivelEscolarIdOld = em.merge(nivelEscolarnivelEscolarIdOld);
            }
            if (nivelEscolarnivelEscolarIdNew != null && !nivelEscolarnivelEscolarIdNew.equals(nivelEscolarnivelEscolarIdOld)) {
                nivelEscolarnivelEscolarIdNew.getTutorList().add(tutor);
                nivelEscolarnivelEscolarIdNew = em.merge(nivelEscolarnivelEscolarIdNew);
            }
            if (ocupacionocupacionIdOld != null && !ocupacionocupacionIdOld.equals(ocupacionocupacionIdNew)) {
                ocupacionocupacionIdOld.getTutorList().remove(tutor);
                ocupacionocupacionIdOld = em.merge(ocupacionocupacionIdOld);
            }
            if (ocupacionocupacionIdNew != null && !ocupacionocupacionIdNew.equals(ocupacionocupacionIdOld)) {
                ocupacionocupacionIdNew.getTutorList().add(tutor);
                ocupacionocupacionIdNew = em.merge(ocupacionocupacionIdNew);
            }
            if (organizacionPoliticaorganizacionPoliticaIdOld != null && !organizacionPoliticaorganizacionPoliticaIdOld.equals(organizacionPoliticaorganizacionPoliticaIdNew)) {
                organizacionPoliticaorganizacionPoliticaIdOld.getTutorList().remove(tutor);
                organizacionPoliticaorganizacionPoliticaIdOld = em.merge(organizacionPoliticaorganizacionPoliticaIdOld);
            }
            if (organizacionPoliticaorganizacionPoliticaIdNew != null && !organizacionPoliticaorganizacionPoliticaIdNew.equals(organizacionPoliticaorganizacionPoliticaIdOld)) {
                organizacionPoliticaorganizacionPoliticaIdNew.getTutorList().add(tutor);
                organizacionPoliticaorganizacionPoliticaIdNew = em.merge(organizacionPoliticaorganizacionPoliticaIdNew);
            }
            if (sexosexoIdOld != null && !sexosexoIdOld.equals(sexosexoIdNew)) {
                sexosexoIdOld.getTutorList().remove(tutor);
                sexosexoIdOld = em.merge(sexosexoIdOld);
            }
            if (sexosexoIdNew != null && !sexosexoIdNew.equals(sexosexoIdOld)) {
                sexosexoIdNew.getTutorList().add(tutor);
                sexosexoIdNew = em.merge(sexosexoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tutor.getTutorId();
                if (findTutor(id) == null) {
                    throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.");
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
            Tutor tutor;
            try {
                tutor = em.getReference(Tutor.class, id);
                tutor.getTutorId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.", enfe);
            }
            Estudiante estudianteestudianteId = tutor.getEstudianteestudianteId();
            if (estudianteestudianteId != null) {
                estudianteestudianteId.getTutorList().remove(tutor);
                estudianteestudianteId = em.merge(estudianteestudianteId);
            }
            NivelEscolar nivelEscolarnivelEscolarId = tutor.getNivelEscolarnivelEscolarId();
            if (nivelEscolarnivelEscolarId != null) {
                nivelEscolarnivelEscolarId.getTutorList().remove(tutor);
                nivelEscolarnivelEscolarId = em.merge(nivelEscolarnivelEscolarId);
            }
            Ocupacion ocupacionocupacionId = tutor.getOcupacionocupacionId();
            if (ocupacionocupacionId != null) {
                ocupacionocupacionId.getTutorList().remove(tutor);
                ocupacionocupacionId = em.merge(ocupacionocupacionId);
            }
            OrganizacionPolitica organizacionPoliticaorganizacionPoliticaId = tutor.getOrganizacionPoliticaorganizacionPoliticaId();
            if (organizacionPoliticaorganizacionPoliticaId != null) {
                organizacionPoliticaorganizacionPoliticaId.getTutorList().remove(tutor);
                organizacionPoliticaorganizacionPoliticaId = em.merge(organizacionPoliticaorganizacionPoliticaId);
            }
            Sexo sexosexoId = tutor.getSexosexoId();
            if (sexosexoId != null) {
                sexosexoId.getTutorList().remove(tutor);
                sexosexoId = em.merge(sexosexoId);
            }
            em.remove(tutor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tutor> findTutorEntities() {
        return findTutorEntities(true, -1, -1);
    }

    public List<Tutor> findTutorEntities(int maxResults, int firstResult) {
        return findTutorEntities(false, maxResults, firstResult);
    }

    private List<Tutor> findTutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tutor.class));
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

    public Tutor findTutor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tutor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTutorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tutor> rt = cq.from(Tutor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
