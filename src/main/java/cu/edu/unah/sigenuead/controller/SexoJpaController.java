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
import cu.edu.unah.sigenuead.entity.Sexo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class SexoJpaController implements Serializable {

    public SexoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sexo sexo) {
        if (sexo.getTutorList() == null) {
            sexo.setTutorList(new ArrayList<Tutor>());
        }
        if (sexo.getEstudianteList() == null) {
            sexo.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tutor> attachedTutorList = new ArrayList<Tutor>();
            for (Tutor tutorListTutorToAttach : sexo.getTutorList()) {
                tutorListTutorToAttach = em.getReference(tutorListTutorToAttach.getClass(), tutorListTutorToAttach.getTutorId());
                attachedTutorList.add(tutorListTutorToAttach);
            }
            sexo.setTutorList(attachedTutorList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : sexo.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            sexo.setEstudianteList(attachedEstudianteList);
            em.persist(sexo);
            for (Tutor tutorListTutor : sexo.getTutorList()) {
                Sexo oldSexosexoIdOfTutorListTutor = tutorListTutor.getSexosexoId();
                tutorListTutor.setSexosexoId(sexo);
                tutorListTutor = em.merge(tutorListTutor);
                if (oldSexosexoIdOfTutorListTutor != null) {
                    oldSexosexoIdOfTutorListTutor.getTutorList().remove(tutorListTutor);
                    oldSexosexoIdOfTutorListTutor = em.merge(oldSexosexoIdOfTutorListTutor);
                }
            }
            for (Estudiante estudianteListEstudiante : sexo.getEstudianteList()) {
                Sexo oldSexosexoIdOfEstudianteListEstudiante = estudianteListEstudiante.getSexosexoId();
                estudianteListEstudiante.setSexosexoId(sexo);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldSexosexoIdOfEstudianteListEstudiante != null) {
                    oldSexosexoIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldSexosexoIdOfEstudianteListEstudiante = em.merge(oldSexosexoIdOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sexo sexo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sexo persistentSexo = em.find(Sexo.class, sexo.getSexoId());
            List<Tutor> tutorListOld = persistentSexo.getTutorList();
            List<Tutor> tutorListNew = sexo.getTutorList();
            List<Estudiante> estudianteListOld = persistentSexo.getEstudianteList();
            List<Estudiante> estudianteListNew = sexo.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Tutor tutorListOldTutor : tutorListOld) {
                if (!tutorListNew.contains(tutorListOldTutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tutor " + tutorListOldTutor + " since its sexosexoId field is not nullable.");
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its sexosexoId field is not nullable.");
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
            sexo.setTutorList(tutorListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            sexo.setEstudianteList(estudianteListNew);
            sexo = em.merge(sexo);
            for (Tutor tutorListNewTutor : tutorListNew) {
                if (!tutorListOld.contains(tutorListNewTutor)) {
                    Sexo oldSexosexoIdOfTutorListNewTutor = tutorListNewTutor.getSexosexoId();
                    tutorListNewTutor.setSexosexoId(sexo);
                    tutorListNewTutor = em.merge(tutorListNewTutor);
                    if (oldSexosexoIdOfTutorListNewTutor != null && !oldSexosexoIdOfTutorListNewTutor.equals(sexo)) {
                        oldSexosexoIdOfTutorListNewTutor.getTutorList().remove(tutorListNewTutor);
                        oldSexosexoIdOfTutorListNewTutor = em.merge(oldSexosexoIdOfTutorListNewTutor);
                    }
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Sexo oldSexosexoIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getSexosexoId();
                    estudianteListNewEstudiante.setSexosexoId(sexo);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldSexosexoIdOfEstudianteListNewEstudiante != null && !oldSexosexoIdOfEstudianteListNewEstudiante.equals(sexo)) {
                        oldSexosexoIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldSexosexoIdOfEstudianteListNewEstudiante = em.merge(oldSexosexoIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sexo.getSexoId();
                if (findSexo(id) == null) {
                    throw new NonexistentEntityException("The sexo with id " + id + " no longer exists.");
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
            Sexo sexo;
            try {
                sexo = em.getReference(Sexo.class, id);
                sexo.getSexoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sexo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tutor> tutorListOrphanCheck = sexo.getTutorList();
            for (Tutor tutorListOrphanCheckTutor : tutorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sexo (" + sexo + ") cannot be destroyed since the Tutor " + tutorListOrphanCheckTutor + " in its tutorList field has a non-nullable sexosexoId field.");
            }
            List<Estudiante> estudianteListOrphanCheck = sexo.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sexo (" + sexo + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable sexosexoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sexo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sexo> findSexoEntities() {
        return findSexoEntities(true, -1, -1);
    }

    public List<Sexo> findSexoEntities(int maxResults, int firstResult) {
        return findSexoEntities(false, maxResults, firstResult);
    }

    private List<Sexo> findSexoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sexo.class));
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

    public Sexo findSexo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sexo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSexoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sexo> rt = cq.from(Sexo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Sexo findSexoByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT s "
                    + "FROM Sexo s "
                    + "WHERE s.sexoNombre= :nombre");
            q.setParameter("nombre", nombre);
            Sexo p = (Sexo) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Sexo findSexoByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT s "
                    + "FROM Sexo s "
                    + "WHERE s.sexoCancelado=false AND "
                    + "s.sexoNombre= :nombre");
            q.setParameter("nombre", nombre);
            Sexo p = (Sexo) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findSexoAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT s.sexoNombre "
                    + "FROM Sexo s "
                    + "WHERE s.sexoCancelado=false "
                    + "ORDER BY s.sexoNombre");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
