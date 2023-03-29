/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.controller.exceptions.PreexistingEntityException;
import cu.edu.unah.sigenuead.entity.Disciplina;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.DisciplinaPlanestudio;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class DisciplinaJpaController implements Serializable {

    public DisciplinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Disciplina disciplina) throws PreexistingEntityException, Exception {
        if (disciplina.getDisciplinaPlanestudioList() == null) {
            disciplina.setDisciplinaPlanestudioList(new ArrayList<DisciplinaPlanestudio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DisciplinaPlanestudio> attachedDisciplinaPlanestudioList = new ArrayList<DisciplinaPlanestudio>();
            for (DisciplinaPlanestudio disciplinaPlanestudioListDisciplinaPlanestudioToAttach : disciplina.getDisciplinaPlanestudioList()) {
                disciplinaPlanestudioListDisciplinaPlanestudioToAttach = em.getReference(disciplinaPlanestudioListDisciplinaPlanestudioToAttach.getClass(), disciplinaPlanestudioListDisciplinaPlanestudioToAttach.getDisciplinaPlanestudioPK());
                attachedDisciplinaPlanestudioList.add(disciplinaPlanestudioListDisciplinaPlanestudioToAttach);
            }
            disciplina.setDisciplinaPlanestudioList(attachedDisciplinaPlanestudioList);
            em.persist(disciplina);
            for (DisciplinaPlanestudio disciplinaPlanestudioListDisciplinaPlanestudio : disciplina.getDisciplinaPlanestudioList()) {
                Disciplina oldDisciplinaOfDisciplinaPlanestudioListDisciplinaPlanestudio = disciplinaPlanestudioListDisciplinaPlanestudio.getDisciplina();
                disciplinaPlanestudioListDisciplinaPlanestudio.setDisciplina(disciplina);
                disciplinaPlanestudioListDisciplinaPlanestudio = em.merge(disciplinaPlanestudioListDisciplinaPlanestudio);
                if (oldDisciplinaOfDisciplinaPlanestudioListDisciplinaPlanestudio != null) {
                    oldDisciplinaOfDisciplinaPlanestudioListDisciplinaPlanestudio.getDisciplinaPlanestudioList().remove(disciplinaPlanestudioListDisciplinaPlanestudio);
                    oldDisciplinaOfDisciplinaPlanestudioListDisciplinaPlanestudio = em.merge(oldDisciplinaOfDisciplinaPlanestudioListDisciplinaPlanestudio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDisciplina(disciplina.getDisciplinaCodigo()) != null) {
                throw new PreexistingEntityException("Disciplina " + disciplina + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Disciplina disciplina) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Disciplina persistentDisciplina = em.find(Disciplina.class, disciplina.getDisciplinaCodigo());
            List<DisciplinaPlanestudio> disciplinaPlanestudioListOld = persistentDisciplina.getDisciplinaPlanestudioList();
            List<DisciplinaPlanestudio> disciplinaPlanestudioListNew = disciplina.getDisciplinaPlanestudioList();
            List<String> illegalOrphanMessages = null;
            for (DisciplinaPlanestudio disciplinaPlanestudioListOldDisciplinaPlanestudio : disciplinaPlanestudioListOld) {
                if (!disciplinaPlanestudioListNew.contains(disciplinaPlanestudioListOldDisciplinaPlanestudio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DisciplinaPlanestudio " + disciplinaPlanestudioListOldDisciplinaPlanestudio + " since its disciplina field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<DisciplinaPlanestudio> attachedDisciplinaPlanestudioListNew = new ArrayList<DisciplinaPlanestudio>();
            for (DisciplinaPlanestudio disciplinaPlanestudioListNewDisciplinaPlanestudioToAttach : disciplinaPlanestudioListNew) {
                disciplinaPlanestudioListNewDisciplinaPlanestudioToAttach = em.getReference(disciplinaPlanestudioListNewDisciplinaPlanestudioToAttach.getClass(), disciplinaPlanestudioListNewDisciplinaPlanestudioToAttach.getDisciplinaPlanestudioPK());
                attachedDisciplinaPlanestudioListNew.add(disciplinaPlanestudioListNewDisciplinaPlanestudioToAttach);
            }
            disciplinaPlanestudioListNew = attachedDisciplinaPlanestudioListNew;
            disciplina.setDisciplinaPlanestudioList(disciplinaPlanestudioListNew);
            disciplina = em.merge(disciplina);
            for (DisciplinaPlanestudio disciplinaPlanestudioListNewDisciplinaPlanestudio : disciplinaPlanestudioListNew) {
                if (!disciplinaPlanestudioListOld.contains(disciplinaPlanestudioListNewDisciplinaPlanestudio)) {
                    Disciplina oldDisciplinaOfDisciplinaPlanestudioListNewDisciplinaPlanestudio = disciplinaPlanestudioListNewDisciplinaPlanestudio.getDisciplina();
                    disciplinaPlanestudioListNewDisciplinaPlanestudio.setDisciplina(disciplina);
                    disciplinaPlanestudioListNewDisciplinaPlanestudio = em.merge(disciplinaPlanestudioListNewDisciplinaPlanestudio);
                    if (oldDisciplinaOfDisciplinaPlanestudioListNewDisciplinaPlanestudio != null && !oldDisciplinaOfDisciplinaPlanestudioListNewDisciplinaPlanestudio.equals(disciplina)) {
                        oldDisciplinaOfDisciplinaPlanestudioListNewDisciplinaPlanestudio.getDisciplinaPlanestudioList().remove(disciplinaPlanestudioListNewDisciplinaPlanestudio);
                        oldDisciplinaOfDisciplinaPlanestudioListNewDisciplinaPlanestudio = em.merge(oldDisciplinaOfDisciplinaPlanestudioListNewDisciplinaPlanestudio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = disciplina.getDisciplinaCodigo();
                if (findDisciplina(id) == null) {
                    throw new NonexistentEntityException("The disciplina with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Disciplina disciplina;
            try {
                disciplina = em.getReference(Disciplina.class, id);
                disciplina.getDisciplinaCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The disciplina with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DisciplinaPlanestudio> disciplinaPlanestudioListOrphanCheck = disciplina.getDisciplinaPlanestudioList();
            for (DisciplinaPlanestudio disciplinaPlanestudioListOrphanCheckDisciplinaPlanestudio : disciplinaPlanestudioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Disciplina (" + disciplina + ") cannot be destroyed since the DisciplinaPlanestudio " + disciplinaPlanestudioListOrphanCheckDisciplinaPlanestudio + " in its disciplinaPlanestudioList field has a non-nullable disciplina field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(disciplina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Disciplina> findDisciplinaEntities() {
        return findDisciplinaEntities(true, -1, -1);
    }

    public List<Disciplina> findDisciplinaEntities(int maxResults, int firstResult) {
        return findDisciplinaEntities(false, maxResults, firstResult);
    }

    private List<Disciplina> findDisciplinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Disciplina.class));
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

    public Disciplina findDisciplina(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Disciplina.class, id);
        } finally {
            em.close();
        }
    }

    public int getDisciplinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Disciplina> rt = cq.from(Disciplina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Disciplina findDisciplinaByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Disciplina c "
                    + "WHERE c.disciplinaNombre= :nombre");
            q.setParameter("nombre", nombre);
            Disciplina p = (Disciplina) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Disciplina findDisciplinaByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Disciplina c "
                    + "WHERE c.disciplinaCancelada=false AND "
                    + "c.disciplinaNombre= :nombre");
            q.setParameter("nombre", nombre);
            Disciplina p = (Disciplina) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findDisciplinaAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c.disciplinaNombre "
                    + "FROM Disciplina c "
                    + "WHERE c.disciplinaCancelada=false "
                    + "ORDER BY c.disciplinaNombre");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findDisciplinaAvailableByPlanEstudio(String carrera, String facultad, String tipoplan) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c.disciplinaNombre "
                    + "FROM Disciplina c "
                    + "WHERE c.disciplinaCancelada=false AND "
                    + "c.disciplinaPlanestudioList.planestudio.tipoplanestudionombretipoplanestudio.nombretipoplanestudio=: planestudio AND "
                    + "c.disciplinaPlanestudioList.planestudio.carreraidcarrera.facultadcodigoarea.codigoarea=: codigofac AND "
                    + "c.disciplinaPlanestudioList.planestudio.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera "
                    + "ORDER BY c.disciplinaNombre");
            q.setParameter("planestudio", tipoplan);
            q.setParameter("codigofac", facultad);
            q.setParameter("carrera", carrera);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Disciplina> findDisciplinaByPlanEstudio(String carrera, String facultad, String tipoplan) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Disciplina c "
                    + "WHERE c.disciplinaPlanestudioList.planestudio.tipoplanestudionombretipoplanestudio.nombretipoplanestudio=: planestudio AND "
                    + "c.disciplinaPlanestudioList.planestudio.carreraidcarrera.facultadcodigoarea.codigoarea=: codigofac AND "
                    + "c.disciplinaPlanestudioList.planestudio.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera "
                    + "ORDER BY c.disciplinaNombre");
            q.setParameter("planestudio", tipoplan);
            q.setParameter("codigofac", facultad);
            q.setParameter("carrera", carrera);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
