/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Disciplina;
import cu.edu.unah.sigenuead.entity.Planestudio;
import cu.edu.unah.sigenuead.entity.Asignatura;
import cu.edu.unah.sigenuead.entity.DisciplinaPlanestudio;
import cu.edu.unah.sigenuead.entity.DisciplinaPlanestudioPK;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class DisciplinaPlanestudioJpaController implements Serializable {

    public DisciplinaPlanestudioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DisciplinaPlanestudio disciplinaPlanestudio) throws PreexistingEntityException, Exception {
        if (disciplinaPlanestudio.getDisciplinaPlanestudioPK() == null) {
            disciplinaPlanestudio.setDisciplinaPlanestudioPK(new DisciplinaPlanestudioPK());
        }
        if (disciplinaPlanestudio.getAsignaturaList() == null) {
            disciplinaPlanestudio.setAsignaturaList(new ArrayList<Asignatura>());
        }
        disciplinaPlanestudio.getDisciplinaPlanestudioPK().setDisciplinadisciplinaCodigo(disciplinaPlanestudio.getDisciplina().getDisciplinaCodigo());
        disciplinaPlanestudio.getDisciplinaPlanestudioPK().setPlanestudioidplanestudio(disciplinaPlanestudio.getPlanestudio().getIdplanestudio());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Disciplina disciplina = disciplinaPlanestudio.getDisciplina();
            if (disciplina != null) {
                disciplina = em.getReference(disciplina.getClass(), disciplina.getDisciplinaCodigo());
                disciplinaPlanestudio.setDisciplina(disciplina);
            }
            Planestudio planestudio = disciplinaPlanestudio.getPlanestudio();
            if (planestudio != null) {
                planestudio = em.getReference(planestudio.getClass(), planestudio.getIdplanestudio());
                disciplinaPlanestudio.setPlanestudio(planestudio);
            }
            List<Asignatura> attachedAsignaturaList = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListAsignaturaToAttach : disciplinaPlanestudio.getAsignaturaList()) {
                asignaturaListAsignaturaToAttach = em.getReference(asignaturaListAsignaturaToAttach.getClass(), asignaturaListAsignaturaToAttach.getAsignaturaId());
                attachedAsignaturaList.add(asignaturaListAsignaturaToAttach);
            }
            disciplinaPlanestudio.setAsignaturaList(attachedAsignaturaList);
            em.persist(disciplinaPlanestudio);
            if (disciplina != null) {
                disciplina.getDisciplinaPlanestudioList().add(disciplinaPlanestudio);
                disciplina = em.merge(disciplina);
            }
            if (planestudio != null) {
                planestudio.getDisciplinaPlanestudioList().add(disciplinaPlanestudio);
                planestudio = em.merge(planestudio);
            }
            for (Asignatura asignaturaListAsignatura : disciplinaPlanestudio.getAsignaturaList()) {
                DisciplinaPlanestudio oldDisciplinaPlanestudioOfAsignaturaListAsignatura = asignaturaListAsignatura.getDisciplinaPlanestudio();
                asignaturaListAsignatura.setDisciplinaPlanestudio(disciplinaPlanestudio);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
                if (oldDisciplinaPlanestudioOfAsignaturaListAsignatura != null) {
                    oldDisciplinaPlanestudioOfAsignaturaListAsignatura.getAsignaturaList().remove(asignaturaListAsignatura);
                    oldDisciplinaPlanestudioOfAsignaturaListAsignatura = em.merge(oldDisciplinaPlanestudioOfAsignaturaListAsignatura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDisciplinaPlanestudio(disciplinaPlanestudio.getDisciplinaPlanestudioPK()) != null) {
                throw new PreexistingEntityException("DisciplinaPlanestudio " + disciplinaPlanestudio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DisciplinaPlanestudio disciplinaPlanestudio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        disciplinaPlanestudio.getDisciplinaPlanestudioPK().setDisciplinadisciplinaCodigo(disciplinaPlanestudio.getDisciplina().getDisciplinaCodigo());
        disciplinaPlanestudio.getDisciplinaPlanestudioPK().setPlanestudioidplanestudio(disciplinaPlanestudio.getPlanestudio().getIdplanestudio());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DisciplinaPlanestudio persistentDisciplinaPlanestudio = em.find(DisciplinaPlanestudio.class, disciplinaPlanestudio.getDisciplinaPlanestudioPK());
            Disciplina disciplinaOld = persistentDisciplinaPlanestudio.getDisciplina();
            Disciplina disciplinaNew = disciplinaPlanestudio.getDisciplina();
            Planestudio planestudioOld = persistentDisciplinaPlanestudio.getPlanestudio();
            Planestudio planestudioNew = disciplinaPlanestudio.getPlanestudio();
            List<Asignatura> asignaturaListOld = persistentDisciplinaPlanestudio.getAsignaturaList();
            List<Asignatura> asignaturaListNew = disciplinaPlanestudio.getAsignaturaList();
            List<String> illegalOrphanMessages = null;
            for (Asignatura asignaturaListOldAsignatura : asignaturaListOld) {
                if (!asignaturaListNew.contains(asignaturaListOldAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignatura " + asignaturaListOldAsignatura + " since its disciplinaPlanestudio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (disciplinaNew != null) {
                disciplinaNew = em.getReference(disciplinaNew.getClass(), disciplinaNew.getDisciplinaCodigo());
                disciplinaPlanestudio.setDisciplina(disciplinaNew);
            }
            if (planestudioNew != null) {
                planestudioNew = em.getReference(planestudioNew.getClass(), planestudioNew.getIdplanestudio());
                disciplinaPlanestudio.setPlanestudio(planestudioNew);
            }
            List<Asignatura> attachedAsignaturaListNew = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListNewAsignaturaToAttach : asignaturaListNew) {
                asignaturaListNewAsignaturaToAttach = em.getReference(asignaturaListNewAsignaturaToAttach.getClass(), asignaturaListNewAsignaturaToAttach.getAsignaturaId());
                attachedAsignaturaListNew.add(asignaturaListNewAsignaturaToAttach);
            }
            asignaturaListNew = attachedAsignaturaListNew;
            disciplinaPlanestudio.setAsignaturaList(asignaturaListNew);
            disciplinaPlanestudio = em.merge(disciplinaPlanestudio);
            if (disciplinaOld != null && !disciplinaOld.equals(disciplinaNew)) {
                disciplinaOld.getDisciplinaPlanestudioList().remove(disciplinaPlanestudio);
                disciplinaOld = em.merge(disciplinaOld);
            }
            if (disciplinaNew != null && !disciplinaNew.equals(disciplinaOld)) {
                disciplinaNew.getDisciplinaPlanestudioList().add(disciplinaPlanestudio);
                disciplinaNew = em.merge(disciplinaNew);
            }
            if (planestudioOld != null && !planestudioOld.equals(planestudioNew)) {
                planestudioOld.getDisciplinaPlanestudioList().remove(disciplinaPlanestudio);
                planestudioOld = em.merge(planestudioOld);
            }
            if (planestudioNew != null && !planestudioNew.equals(planestudioOld)) {
                planestudioNew.getDisciplinaPlanestudioList().add(disciplinaPlanestudio);
                planestudioNew = em.merge(planestudioNew);
            }
            for (Asignatura asignaturaListNewAsignatura : asignaturaListNew) {
                if (!asignaturaListOld.contains(asignaturaListNewAsignatura)) {
                    DisciplinaPlanestudio oldDisciplinaPlanestudioOfAsignaturaListNewAsignatura = asignaturaListNewAsignatura.getDisciplinaPlanestudio();
                    asignaturaListNewAsignatura.setDisciplinaPlanestudio(disciplinaPlanestudio);
                    asignaturaListNewAsignatura = em.merge(asignaturaListNewAsignatura);
                    if (oldDisciplinaPlanestudioOfAsignaturaListNewAsignatura != null && !oldDisciplinaPlanestudioOfAsignaturaListNewAsignatura.equals(disciplinaPlanestudio)) {
                        oldDisciplinaPlanestudioOfAsignaturaListNewAsignatura.getAsignaturaList().remove(asignaturaListNewAsignatura);
                        oldDisciplinaPlanestudioOfAsignaturaListNewAsignatura = em.merge(oldDisciplinaPlanestudioOfAsignaturaListNewAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DisciplinaPlanestudioPK id = disciplinaPlanestudio.getDisciplinaPlanestudioPK();
                if (findDisciplinaPlanestudio(id) == null) {
                    throw new NonexistentEntityException("The disciplinaPlanestudio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DisciplinaPlanestudioPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DisciplinaPlanestudio disciplinaPlanestudio;
            try {
                disciplinaPlanestudio = em.getReference(DisciplinaPlanestudio.class, id);
                disciplinaPlanestudio.getDisciplinaPlanestudioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The disciplinaPlanestudio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Asignatura> asignaturaListOrphanCheck = disciplinaPlanestudio.getAsignaturaList();
            for (Asignatura asignaturaListOrphanCheckAsignatura : asignaturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DisciplinaPlanestudio (" + disciplinaPlanestudio + ") cannot be destroyed since the Asignatura " + asignaturaListOrphanCheckAsignatura + " in its asignaturaList field has a non-nullable disciplinaPlanestudio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Disciplina disciplina = disciplinaPlanestudio.getDisciplina();
            if (disciplina != null) {
                disciplina.getDisciplinaPlanestudioList().remove(disciplinaPlanestudio);
                disciplina = em.merge(disciplina);
            }
            Planestudio planestudio = disciplinaPlanestudio.getPlanestudio();
            if (planestudio != null) {
                planestudio.getDisciplinaPlanestudioList().remove(disciplinaPlanestudio);
                planestudio = em.merge(planestudio);
            }
            em.remove(disciplinaPlanestudio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DisciplinaPlanestudio> findDisciplinaPlanestudioEntities() {
        return findDisciplinaPlanestudioEntities(true, -1, -1);
    }

    public List<DisciplinaPlanestudio> findDisciplinaPlanestudioEntities(int maxResults, int firstResult) {
        return findDisciplinaPlanestudioEntities(false, maxResults, firstResult);
    }

    private List<DisciplinaPlanestudio> findDisciplinaPlanestudioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DisciplinaPlanestudio.class));
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

    public DisciplinaPlanestudio findDisciplinaPlanestudio(DisciplinaPlanestudioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DisciplinaPlanestudio.class, id);
        } finally {
            em.close();
        }
    }

    public int getDisciplinaPlanestudioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DisciplinaPlanestudio> rt = cq.from(DisciplinaPlanestudio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
