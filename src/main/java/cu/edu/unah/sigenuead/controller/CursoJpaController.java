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
import cu.edu.unah.sigenuead.entity.Universidad;
import cu.edu.unah.sigenuead.entity.Baja;
import java.util.ArrayList;
import java.util.List;
import cu.edu.unah.sigenuead.entity.Matricula;
import cu.edu.unah.sigenuead.entity.Planestudio;
import cu.edu.unah.sigenuead.entity.CarreraCurso;
import cu.edu.unah.sigenuead.entity.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class CursoJpaController implements Serializable {

    public CursoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Curso curso) throws PreexistingEntityException, Exception {
        if (curso.getBajaList() == null) {
            curso.setBajaList(new ArrayList<Baja>());
        }
        if (curso.getMatriculaList() == null) {
            curso.setMatriculaList(new ArrayList<Matricula>());
        }
        if (curso.getPlanestudioList() == null) {
            curso.setPlanestudioList(new ArrayList<Planestudio>());
        }
        if (curso.getCarreraCursoList() == null) {
            curso.setCarreraCursoList(new ArrayList<CarreraCurso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Universidad universidadcodigouniversidad = curso.getUniversidadcodigouniversidad();
            if (universidadcodigouniversidad != null) {
                universidadcodigouniversidad = em.getReference(universidadcodigouniversidad.getClass(), universidadcodigouniversidad.getCodigouniversidad());
                curso.setUniversidadcodigouniversidad(universidadcodigouniversidad);
            }
            List<Baja> attachedBajaList = new ArrayList<Baja>();
            for (Baja bajaListBajaToAttach : curso.getBajaList()) {
                bajaListBajaToAttach = em.getReference(bajaListBajaToAttach.getClass(), bajaListBajaToAttach.getBajaPK());
                attachedBajaList.add(bajaListBajaToAttach);
            }
            curso.setBajaList(attachedBajaList);
            List<Matricula> attachedMatriculaList = new ArrayList<Matricula>();
            for (Matricula matriculaListMatriculaToAttach : curso.getMatriculaList()) {
                matriculaListMatriculaToAttach = em.getReference(matriculaListMatriculaToAttach.getClass(), matriculaListMatriculaToAttach.getMatriculaId());
                attachedMatriculaList.add(matriculaListMatriculaToAttach);
            }
            curso.setMatriculaList(attachedMatriculaList);
            List<Planestudio> attachedPlanestudioList = new ArrayList<Planestudio>();
            for (Planestudio planestudioListPlanestudioToAttach : curso.getPlanestudioList()) {
                planestudioListPlanestudioToAttach = em.getReference(planestudioListPlanestudioToAttach.getClass(), planestudioListPlanestudioToAttach.getIdplanestudio());
                attachedPlanestudioList.add(planestudioListPlanestudioToAttach);
            }
            curso.setPlanestudioList(attachedPlanestudioList);
            List<CarreraCurso> attachedCarreraCursoList = new ArrayList<CarreraCurso>();
            for (CarreraCurso carreraCursoListCarreraCursoToAttach : curso.getCarreraCursoList()) {
                carreraCursoListCarreraCursoToAttach = em.getReference(carreraCursoListCarreraCursoToAttach.getClass(), carreraCursoListCarreraCursoToAttach.getCarreraCursoPK());
                attachedCarreraCursoList.add(carreraCursoListCarreraCursoToAttach);
            }
            curso.setCarreraCursoList(attachedCarreraCursoList);
            em.persist(curso);
            if (universidadcodigouniversidad != null) {
                universidadcodigouniversidad.getCursoList().add(curso);
                universidadcodigouniversidad = em.merge(universidadcodigouniversidad);
            }
            for (Baja bajaListBaja : curso.getBajaList()) {
                Curso oldCursoOfBajaListBaja = bajaListBaja.getCurso();
                bajaListBaja.setCurso(curso);
                bajaListBaja = em.merge(bajaListBaja);
                if (oldCursoOfBajaListBaja != null) {
                    oldCursoOfBajaListBaja.getBajaList().remove(bajaListBaja);
                    oldCursoOfBajaListBaja = em.merge(oldCursoOfBajaListBaja);
                }
            }
            for (Matricula matriculaListMatricula : curso.getMatriculaList()) {
                Curso oldCursoidcursoOfMatriculaListMatricula = matriculaListMatricula.getCursoidcurso();
                matriculaListMatricula.setCursoidcurso(curso);
                matriculaListMatricula = em.merge(matriculaListMatricula);
                if (oldCursoidcursoOfMatriculaListMatricula != null) {
                    oldCursoidcursoOfMatriculaListMatricula.getMatriculaList().remove(matriculaListMatricula);
                    oldCursoidcursoOfMatriculaListMatricula = em.merge(oldCursoidcursoOfMatriculaListMatricula);
                }
            }
            for (Planestudio planestudioListPlanestudio : curso.getPlanestudioList()) {
                Curso oldCursoactivacionOfPlanestudioListPlanestudio = planestudioListPlanestudio.getCursoactivacion();
                planestudioListPlanestudio.setCursoactivacion(curso);
                planestudioListPlanestudio = em.merge(planestudioListPlanestudio);
                if (oldCursoactivacionOfPlanestudioListPlanestudio != null) {
                    oldCursoactivacionOfPlanestudioListPlanestudio.getPlanestudioList().remove(planestudioListPlanestudio);
                    oldCursoactivacionOfPlanestudioListPlanestudio = em.merge(oldCursoactivacionOfPlanestudioListPlanestudio);
                }
            }
            for (CarreraCurso carreraCursoListCarreraCurso : curso.getCarreraCursoList()) {
                Curso oldCursoOfCarreraCursoListCarreraCurso = carreraCursoListCarreraCurso.getCurso();
                carreraCursoListCarreraCurso.setCurso(curso);
                carreraCursoListCarreraCurso = em.merge(carreraCursoListCarreraCurso);
                if (oldCursoOfCarreraCursoListCarreraCurso != null) {
                    oldCursoOfCarreraCursoListCarreraCurso.getCarreraCursoList().remove(carreraCursoListCarreraCurso);
                    oldCursoOfCarreraCursoListCarreraCurso = em.merge(oldCursoOfCarreraCursoListCarreraCurso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCurso(curso.getIdcurso()) != null) {
                throw new PreexistingEntityException("Curso " + curso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Curso curso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso persistentCurso = em.find(Curso.class, curso.getIdcurso());
            Universidad universidadcodigouniversidadOld = persistentCurso.getUniversidadcodigouniversidad();
            Universidad universidadcodigouniversidadNew = curso.getUniversidadcodigouniversidad();
            List<Baja> bajaListOld = persistentCurso.getBajaList();
            List<Baja> bajaListNew = curso.getBajaList();
            List<Matricula> matriculaListOld = persistentCurso.getMatriculaList();
            List<Matricula> matriculaListNew = curso.getMatriculaList();
            List<Planestudio> planestudioListOld = persistentCurso.getPlanestudioList();
            List<Planestudio> planestudioListNew = curso.getPlanestudioList();
            List<CarreraCurso> carreraCursoListOld = persistentCurso.getCarreraCursoList();
            List<CarreraCurso> carreraCursoListNew = curso.getCarreraCursoList();
            List<String> illegalOrphanMessages = null;
            for (Baja bajaListOldBaja : bajaListOld) {
                if (!bajaListNew.contains(bajaListOldBaja)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Baja " + bajaListOldBaja + " since its curso field is not nullable.");
                }
            }
            for (Matricula matriculaListOldMatricula : matriculaListOld) {
                if (!matriculaListNew.contains(matriculaListOldMatricula)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Matricula " + matriculaListOldMatricula + " since its cursoidcurso field is not nullable.");
                }
            }
            for (Planestudio planestudioListOldPlanestudio : planestudioListOld) {
                if (!planestudioListNew.contains(planestudioListOldPlanestudio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Planestudio " + planestudioListOldPlanestudio + " since its cursoactivacion field is not nullable.");
                }
            }
            for (CarreraCurso carreraCursoListOldCarreraCurso : carreraCursoListOld) {
                if (!carreraCursoListNew.contains(carreraCursoListOldCarreraCurso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CarreraCurso " + carreraCursoListOldCarreraCurso + " since its curso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (universidadcodigouniversidadNew != null) {
                universidadcodigouniversidadNew = em.getReference(universidadcodigouniversidadNew.getClass(), universidadcodigouniversidadNew.getCodigouniversidad());
                curso.setUniversidadcodigouniversidad(universidadcodigouniversidadNew);
            }
            List<Baja> attachedBajaListNew = new ArrayList<Baja>();
            for (Baja bajaListNewBajaToAttach : bajaListNew) {
                bajaListNewBajaToAttach = em.getReference(bajaListNewBajaToAttach.getClass(), bajaListNewBajaToAttach.getBajaPK());
                attachedBajaListNew.add(bajaListNewBajaToAttach);
            }
            bajaListNew = attachedBajaListNew;
            curso.setBajaList(bajaListNew);
            List<Matricula> attachedMatriculaListNew = new ArrayList<Matricula>();
            for (Matricula matriculaListNewMatriculaToAttach : matriculaListNew) {
                matriculaListNewMatriculaToAttach = em.getReference(matriculaListNewMatriculaToAttach.getClass(), matriculaListNewMatriculaToAttach.getMatriculaId());
                attachedMatriculaListNew.add(matriculaListNewMatriculaToAttach);
            }
            matriculaListNew = attachedMatriculaListNew;
            curso.setMatriculaList(matriculaListNew);
            List<Planestudio> attachedPlanestudioListNew = new ArrayList<Planestudio>();
            for (Planestudio planestudioListNewPlanestudioToAttach : planestudioListNew) {
                planestudioListNewPlanestudioToAttach = em.getReference(planestudioListNewPlanestudioToAttach.getClass(), planestudioListNewPlanestudioToAttach.getIdplanestudio());
                attachedPlanestudioListNew.add(planestudioListNewPlanestudioToAttach);
            }
            planestudioListNew = attachedPlanestudioListNew;
            curso.setPlanestudioList(planestudioListNew);
            List<CarreraCurso> attachedCarreraCursoListNew = new ArrayList<CarreraCurso>();
            for (CarreraCurso carreraCursoListNewCarreraCursoToAttach : carreraCursoListNew) {
                carreraCursoListNewCarreraCursoToAttach = em.getReference(carreraCursoListNewCarreraCursoToAttach.getClass(), carreraCursoListNewCarreraCursoToAttach.getCarreraCursoPK());
                attachedCarreraCursoListNew.add(carreraCursoListNewCarreraCursoToAttach);
            }
            carreraCursoListNew = attachedCarreraCursoListNew;
            curso.setCarreraCursoList(carreraCursoListNew);
            curso = em.merge(curso);
            if (universidadcodigouniversidadOld != null && !universidadcodigouniversidadOld.equals(universidadcodigouniversidadNew)) {
                universidadcodigouniversidadOld.getCursoList().remove(curso);
                universidadcodigouniversidadOld = em.merge(universidadcodigouniversidadOld);
            }
            if (universidadcodigouniversidadNew != null && !universidadcodigouniversidadNew.equals(universidadcodigouniversidadOld)) {
                universidadcodigouniversidadNew.getCursoList().add(curso);
                universidadcodigouniversidadNew = em.merge(universidadcodigouniversidadNew);
            }
            for (Baja bajaListNewBaja : bajaListNew) {
                if (!bajaListOld.contains(bajaListNewBaja)) {
                    Curso oldCursoOfBajaListNewBaja = bajaListNewBaja.getCurso();
                    bajaListNewBaja.setCurso(curso);
                    bajaListNewBaja = em.merge(bajaListNewBaja);
                    if (oldCursoOfBajaListNewBaja != null && !oldCursoOfBajaListNewBaja.equals(curso)) {
                        oldCursoOfBajaListNewBaja.getBajaList().remove(bajaListNewBaja);
                        oldCursoOfBajaListNewBaja = em.merge(oldCursoOfBajaListNewBaja);
                    }
                }
            }
            for (Matricula matriculaListNewMatricula : matriculaListNew) {
                if (!matriculaListOld.contains(matriculaListNewMatricula)) {
                    Curso oldCursoidcursoOfMatriculaListNewMatricula = matriculaListNewMatricula.getCursoidcurso();
                    matriculaListNewMatricula.setCursoidcurso(curso);
                    matriculaListNewMatricula = em.merge(matriculaListNewMatricula);
                    if (oldCursoidcursoOfMatriculaListNewMatricula != null && !oldCursoidcursoOfMatriculaListNewMatricula.equals(curso)) {
                        oldCursoidcursoOfMatriculaListNewMatricula.getMatriculaList().remove(matriculaListNewMatricula);
                        oldCursoidcursoOfMatriculaListNewMatricula = em.merge(oldCursoidcursoOfMatriculaListNewMatricula);
                    }
                }
            }
            for (Planestudio planestudioListNewPlanestudio : planestudioListNew) {
                if (!planestudioListOld.contains(planestudioListNewPlanestudio)) {
                    Curso oldCursoactivacionOfPlanestudioListNewPlanestudio = planestudioListNewPlanestudio.getCursoactivacion();
                    planestudioListNewPlanestudio.setCursoactivacion(curso);
                    planestudioListNewPlanestudio = em.merge(planestudioListNewPlanestudio);
                    if (oldCursoactivacionOfPlanestudioListNewPlanestudio != null && !oldCursoactivacionOfPlanestudioListNewPlanestudio.equals(curso)) {
                        oldCursoactivacionOfPlanestudioListNewPlanestudio.getPlanestudioList().remove(planestudioListNewPlanestudio);
                        oldCursoactivacionOfPlanestudioListNewPlanestudio = em.merge(oldCursoactivacionOfPlanestudioListNewPlanestudio);
                    }
                }
            }
            for (CarreraCurso carreraCursoListNewCarreraCurso : carreraCursoListNew) {
                if (!carreraCursoListOld.contains(carreraCursoListNewCarreraCurso)) {
                    Curso oldCursoOfCarreraCursoListNewCarreraCurso = carreraCursoListNewCarreraCurso.getCurso();
                    carreraCursoListNewCarreraCurso.setCurso(curso);
                    carreraCursoListNewCarreraCurso = em.merge(carreraCursoListNewCarreraCurso);
                    if (oldCursoOfCarreraCursoListNewCarreraCurso != null && !oldCursoOfCarreraCursoListNewCarreraCurso.equals(curso)) {
                        oldCursoOfCarreraCursoListNewCarreraCurso.getCarreraCursoList().remove(carreraCursoListNewCarreraCurso);
                        oldCursoOfCarreraCursoListNewCarreraCurso = em.merge(oldCursoOfCarreraCursoListNewCarreraCurso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = curso.getIdcurso();
                if (findCurso(id) == null) {
                    throw new NonexistentEntityException("The curso with id " + id + " no longer exists.");
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
            Curso curso;
            try {
                curso = em.getReference(Curso.class, id);
                curso.getIdcurso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The curso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Baja> bajaListOrphanCheck = curso.getBajaList();
            for (Baja bajaListOrphanCheckBaja : bajaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Curso (" + curso + ") cannot be destroyed since the Baja " + bajaListOrphanCheckBaja + " in its bajaList field has a non-nullable curso field.");
            }
            List<Matricula> matriculaListOrphanCheck = curso.getMatriculaList();
            for (Matricula matriculaListOrphanCheckMatricula : matriculaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Curso (" + curso + ") cannot be destroyed since the Matricula " + matriculaListOrphanCheckMatricula + " in its matriculaList field has a non-nullable cursoidcurso field.");
            }
            List<Planestudio> planestudioListOrphanCheck = curso.getPlanestudioList();
            for (Planestudio planestudioListOrphanCheckPlanestudio : planestudioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Curso (" + curso + ") cannot be destroyed since the Planestudio " + planestudioListOrphanCheckPlanestudio + " in its planestudioList field has a non-nullable cursoactivacion field.");
            }
            List<CarreraCurso> carreraCursoListOrphanCheck = curso.getCarreraCursoList();
            for (CarreraCurso carreraCursoListOrphanCheckCarreraCurso : carreraCursoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Curso (" + curso + ") cannot be destroyed since the CarreraCurso " + carreraCursoListOrphanCheckCarreraCurso + " in its carreraCursoList field has a non-nullable curso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Universidad universidadcodigouniversidad = curso.getUniversidadcodigouniversidad();
            if (universidadcodigouniversidad != null) {
                universidadcodigouniversidad.getCursoList().remove(curso);
                universidadcodigouniversidad = em.merge(universidadcodigouniversidad);
            }
            em.remove(curso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Curso> findCursoEntities() {
        return findCursoEntities(true, -1, -1);
    }

    public List<Curso> findCursoEntities(int maxResults, int firstResult) {
        return findCursoEntities(false, maxResults, firstResult);
    }

    private List<Curso> findCursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Curso.class));
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

    public Curso findCurso(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Curso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Curso> rt = cq.from(Curso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Curso findCursoActual() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Curso c "
                    + "WHERE c.cursoactual=true");
            Curso p = (Curso) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findAllCursoCodigo() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c.idcurso "
                    + "FROM Curso c "
                    + "ORDER BY c.idcurso");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Integer findCursoMayor() {
        EntityManager em = getEntityManager();
        try {
            Integer p = (Integer) em.createQuery("SELECT MAX(CAST(SUBSTRING(c.idcurso,6,4) AS INT)) FROM Curso c").getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
