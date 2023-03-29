/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.DisciplinaPlanestudio;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.Planestudio;
import cu.edu.unah.sigenuead.entity.Tipoplanestudio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

/**
 *
 * @author claudy
 */
public class PlanestudioJpaController implements Serializable {

    public PlanestudioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planestudio planestudio) {
        if (planestudio.getFacultadCumCarreraEstudianteList() == null) {
            planestudio.setFacultadCumCarreraEstudianteList(new ArrayList<FacultadCumCarreraEstudiante>());
        }
        if (planestudio.getDisciplinaPlanestudioList() == null) {
            planestudio.setDisciplinaPlanestudioList(new ArrayList<DisciplinaPlanestudio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrera carreraidcarrera = planestudio.getCarreraidcarrera();
            if (carreraidcarrera != null) {
                carreraidcarrera = em.getReference(carreraidcarrera.getClass(), carreraidcarrera.getIdcarrera());
                planestudio.setCarreraidcarrera(carreraidcarrera);
            }
            Curso cursoactivacion = planestudio.getCursoactivacion();
            if (cursoactivacion != null) {
                cursoactivacion = em.getReference(cursoactivacion.getClass(), cursoactivacion.getIdcurso());
                planestudio.setCursoactivacion(cursoactivacion);
            }
            Tipoplanestudio tipoplanestudionombretipoplanestudio = planestudio.getTipoplanestudionombretipoplanestudio();
            if (tipoplanestudionombretipoplanestudio != null) {
                tipoplanestudionombretipoplanestudio = em.getReference(tipoplanestudionombretipoplanestudio.getClass(), tipoplanestudionombretipoplanestudio.getNombretipoplanestudio());
                planestudio.setTipoplanestudionombretipoplanestudio(tipoplanestudionombretipoplanestudio);
            }
            List<FacultadCumCarreraEstudiante> attachedFacultadCumCarreraEstudianteList = new ArrayList<FacultadCumCarreraEstudiante>();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach : planestudio.getFacultadCumCarreraEstudianteList()) {
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach = em.getReference(facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach.getClass(), facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach.getFacultadCumCarreraEstudiantePK());
                attachedFacultadCumCarreraEstudianteList.add(facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach);
            }
            planestudio.setFacultadCumCarreraEstudianteList(attachedFacultadCumCarreraEstudianteList);
            List<DisciplinaPlanestudio> attachedDisciplinaPlanestudioList = new ArrayList<DisciplinaPlanestudio>();
            for (DisciplinaPlanestudio disciplinaPlanestudioListDisciplinaPlanestudioToAttach : planestudio.getDisciplinaPlanestudioList()) {
                disciplinaPlanestudioListDisciplinaPlanestudioToAttach = em.getReference(disciplinaPlanestudioListDisciplinaPlanestudioToAttach.getClass(), disciplinaPlanestudioListDisciplinaPlanestudioToAttach.getDisciplinaPlanestudioPK());
                attachedDisciplinaPlanestudioList.add(disciplinaPlanestudioListDisciplinaPlanestudioToAttach);
            }
            planestudio.setDisciplinaPlanestudioList(attachedDisciplinaPlanestudioList);
            em.persist(planestudio);
            if (carreraidcarrera != null) {
                carreraidcarrera.getPlanestudioList().add(planestudio);
                carreraidcarrera = em.merge(carreraidcarrera);
            }
            if (cursoactivacion != null) {
                cursoactivacion.getPlanestudioList().add(planestudio);
                cursoactivacion = em.merge(cursoactivacion);
            }
            if (tipoplanestudionombretipoplanestudio != null) {
                tipoplanestudionombretipoplanestudio.getPlanestudioList().add(planestudio);
                tipoplanestudionombretipoplanestudio = em.merge(tipoplanestudionombretipoplanestudio);
            }
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante : planestudio.getFacultadCumCarreraEstudianteList()) {
                Planestudio oldPlanestudioidplanestudioOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.getPlanestudioidplanestudio();
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.setPlanestudioidplanestudio(planestudio);
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                if (oldPlanestudioidplanestudioOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante != null) {
                    oldPlanestudioidplanestudioOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                    oldPlanestudioidplanestudioOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = em.merge(oldPlanestudioidplanestudioOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                }
            }
            for (DisciplinaPlanestudio disciplinaPlanestudioListDisciplinaPlanestudio : planestudio.getDisciplinaPlanestudioList()) {
                Planestudio oldPlanestudioOfDisciplinaPlanestudioListDisciplinaPlanestudio = disciplinaPlanestudioListDisciplinaPlanestudio.getPlanestudio();
                disciplinaPlanestudioListDisciplinaPlanestudio.setPlanestudio(planestudio);
                disciplinaPlanestudioListDisciplinaPlanestudio = em.merge(disciplinaPlanestudioListDisciplinaPlanestudio);
                if (oldPlanestudioOfDisciplinaPlanestudioListDisciplinaPlanestudio != null) {
                    oldPlanestudioOfDisciplinaPlanestudioListDisciplinaPlanestudio.getDisciplinaPlanestudioList().remove(disciplinaPlanestudioListDisciplinaPlanestudio);
                    oldPlanestudioOfDisciplinaPlanestudioListDisciplinaPlanestudio = em.merge(oldPlanestudioOfDisciplinaPlanestudioListDisciplinaPlanestudio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planestudio planestudio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planestudio persistentPlanestudio = em.find(Planestudio.class, planestudio.getIdplanestudio());
            Carrera carreraidcarreraOld = persistentPlanestudio.getCarreraidcarrera();
            Carrera carreraidcarreraNew = planestudio.getCarreraidcarrera();
            Curso cursoactivacionOld = persistentPlanestudio.getCursoactivacion();
            Curso cursoactivacionNew = planestudio.getCursoactivacion();
            Tipoplanestudio tipoplanestudionombretipoplanestudioOld = persistentPlanestudio.getTipoplanestudionombretipoplanestudio();
            Tipoplanestudio tipoplanestudionombretipoplanestudioNew = planestudio.getTipoplanestudionombretipoplanestudio();
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListOld = persistentPlanestudio.getFacultadCumCarreraEstudianteList();
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListNew = planestudio.getFacultadCumCarreraEstudianteList();
            List<DisciplinaPlanestudio> disciplinaPlanestudioListOld = persistentPlanestudio.getDisciplinaPlanestudioList();
            List<DisciplinaPlanestudio> disciplinaPlanestudioListNew = planestudio.getDisciplinaPlanestudioList();
            List<String> illegalOrphanMessages = null;
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListOld) {
                if (!facultadCumCarreraEstudianteListNew.contains(facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FacultadCumCarreraEstudiante " + facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante + " since its planestudioidplanestudio field is not nullable.");
                }
            }
            for (DisciplinaPlanestudio disciplinaPlanestudioListOldDisciplinaPlanestudio : disciplinaPlanestudioListOld) {
                if (!disciplinaPlanestudioListNew.contains(disciplinaPlanestudioListOldDisciplinaPlanestudio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DisciplinaPlanestudio " + disciplinaPlanestudioListOldDisciplinaPlanestudio + " since its planestudio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (carreraidcarreraNew != null) {
                carreraidcarreraNew = em.getReference(carreraidcarreraNew.getClass(), carreraidcarreraNew.getIdcarrera());
                planestudio.setCarreraidcarrera(carreraidcarreraNew);
            }
            if (cursoactivacionNew != null) {
                cursoactivacionNew = em.getReference(cursoactivacionNew.getClass(), cursoactivacionNew.getIdcurso());
                planestudio.setCursoactivacion(cursoactivacionNew);
            }
            if (tipoplanestudionombretipoplanestudioNew != null) {
                tipoplanestudionombretipoplanestudioNew = em.getReference(tipoplanestudionombretipoplanestudioNew.getClass(), tipoplanestudionombretipoplanestudioNew.getNombretipoplanestudio());
                planestudio.setTipoplanestudionombretipoplanestudio(tipoplanestudionombretipoplanestudioNew);
            }
            List<FacultadCumCarreraEstudiante> attachedFacultadCumCarreraEstudianteListNew = new ArrayList<FacultadCumCarreraEstudiante>();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach : facultadCumCarreraEstudianteListNew) {
                facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach = em.getReference(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach.getClass(), facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach.getFacultadCumCarreraEstudiantePK());
                attachedFacultadCumCarreraEstudianteListNew.add(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach);
            }
            facultadCumCarreraEstudianteListNew = attachedFacultadCumCarreraEstudianteListNew;
            planestudio.setFacultadCumCarreraEstudianteList(facultadCumCarreraEstudianteListNew);
            List<DisciplinaPlanestudio> attachedDisciplinaPlanestudioListNew = new ArrayList<DisciplinaPlanestudio>();
            for (DisciplinaPlanestudio disciplinaPlanestudioListNewDisciplinaPlanestudioToAttach : disciplinaPlanestudioListNew) {
                disciplinaPlanestudioListNewDisciplinaPlanestudioToAttach = em.getReference(disciplinaPlanestudioListNewDisciplinaPlanestudioToAttach.getClass(), disciplinaPlanestudioListNewDisciplinaPlanestudioToAttach.getDisciplinaPlanestudioPK());
                attachedDisciplinaPlanestudioListNew.add(disciplinaPlanestudioListNewDisciplinaPlanestudioToAttach);
            }
            disciplinaPlanestudioListNew = attachedDisciplinaPlanestudioListNew;
            planestudio.setDisciplinaPlanestudioList(disciplinaPlanestudioListNew);
            planestudio = em.merge(planestudio);
            if (carreraidcarreraOld != null && !carreraidcarreraOld.equals(carreraidcarreraNew)) {
                carreraidcarreraOld.getPlanestudioList().remove(planestudio);
                carreraidcarreraOld = em.merge(carreraidcarreraOld);
            }
            if (carreraidcarreraNew != null && !carreraidcarreraNew.equals(carreraidcarreraOld)) {
                carreraidcarreraNew.getPlanestudioList().add(planestudio);
                carreraidcarreraNew = em.merge(carreraidcarreraNew);
            }
            if (cursoactivacionOld != null && !cursoactivacionOld.equals(cursoactivacionNew)) {
                cursoactivacionOld.getPlanestudioList().remove(planestudio);
                cursoactivacionOld = em.merge(cursoactivacionOld);
            }
            if (cursoactivacionNew != null && !cursoactivacionNew.equals(cursoactivacionOld)) {
                cursoactivacionNew.getPlanestudioList().add(planestudio);
                cursoactivacionNew = em.merge(cursoactivacionNew);
            }
            if (tipoplanestudionombretipoplanestudioOld != null && !tipoplanestudionombretipoplanestudioOld.equals(tipoplanestudionombretipoplanestudioNew)) {
                tipoplanestudionombretipoplanestudioOld.getPlanestudioList().remove(planestudio);
                tipoplanestudionombretipoplanestudioOld = em.merge(tipoplanestudionombretipoplanestudioOld);
            }
            if (tipoplanestudionombretipoplanestudioNew != null && !tipoplanestudionombretipoplanestudioNew.equals(tipoplanestudionombretipoplanestudioOld)) {
                tipoplanestudionombretipoplanestudioNew.getPlanestudioList().add(planestudio);
                tipoplanestudionombretipoplanestudioNew = em.merge(tipoplanestudionombretipoplanestudioNew);
            }
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListNew) {
                if (!facultadCumCarreraEstudianteListOld.contains(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante)) {
                    Planestudio oldPlanestudioidplanestudioOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.getPlanestudioidplanestudio();
                    facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.setPlanestudioidplanestudio(planestudio);
                    facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                    if (oldPlanestudioidplanestudioOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante != null && !oldPlanestudioidplanestudioOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.equals(planestudio)) {
                        oldPlanestudioidplanestudioOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                        oldPlanestudioidplanestudioOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = em.merge(oldPlanestudioidplanestudioOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                    }
                }
            }
            for (DisciplinaPlanestudio disciplinaPlanestudioListNewDisciplinaPlanestudio : disciplinaPlanestudioListNew) {
                if (!disciplinaPlanestudioListOld.contains(disciplinaPlanestudioListNewDisciplinaPlanestudio)) {
                    Planestudio oldPlanestudioOfDisciplinaPlanestudioListNewDisciplinaPlanestudio = disciplinaPlanestudioListNewDisciplinaPlanestudio.getPlanestudio();
                    disciplinaPlanestudioListNewDisciplinaPlanestudio.setPlanestudio(planestudio);
                    disciplinaPlanestudioListNewDisciplinaPlanestudio = em.merge(disciplinaPlanestudioListNewDisciplinaPlanestudio);
                    if (oldPlanestudioOfDisciplinaPlanestudioListNewDisciplinaPlanestudio != null && !oldPlanestudioOfDisciplinaPlanestudioListNewDisciplinaPlanestudio.equals(planestudio)) {
                        oldPlanestudioOfDisciplinaPlanestudioListNewDisciplinaPlanestudio.getDisciplinaPlanestudioList().remove(disciplinaPlanestudioListNewDisciplinaPlanestudio);
                        oldPlanestudioOfDisciplinaPlanestudioListNewDisciplinaPlanestudio = em.merge(oldPlanestudioOfDisciplinaPlanestudioListNewDisciplinaPlanestudio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planestudio.getIdplanestudio();
                if (findPlanestudio(id) == null) {
                    throw new NonexistentEntityException("The planestudio with id " + id + " no longer exists.");
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
            Planestudio planestudio;
            try {
                planestudio = em.getReference(Planestudio.class, id);
                planestudio.getIdplanestudio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planestudio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListOrphanCheck = planestudio.getFacultadCumCarreraEstudianteList();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListOrphanCheckFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planestudio (" + planestudio + ") cannot be destroyed since the FacultadCumCarreraEstudiante " + facultadCumCarreraEstudianteListOrphanCheckFacultadCumCarreraEstudiante + " in its facultadCumCarreraEstudianteList field has a non-nullable planestudioidplanestudio field.");
            }
            List<DisciplinaPlanestudio> disciplinaPlanestudioListOrphanCheck = planestudio.getDisciplinaPlanestudioList();
            for (DisciplinaPlanestudio disciplinaPlanestudioListOrphanCheckDisciplinaPlanestudio : disciplinaPlanestudioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planestudio (" + planestudio + ") cannot be destroyed since the DisciplinaPlanestudio " + disciplinaPlanestudioListOrphanCheckDisciplinaPlanestudio + " in its disciplinaPlanestudioList field has a non-nullable planestudio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Carrera carreraidcarrera = planestudio.getCarreraidcarrera();
            if (carreraidcarrera != null) {
                carreraidcarrera.getPlanestudioList().remove(planestudio);
                carreraidcarrera = em.merge(carreraidcarrera);
            }
            Curso cursoactivacion = planestudio.getCursoactivacion();
            if (cursoactivacion != null) {
                cursoactivacion.getPlanestudioList().remove(planestudio);
                cursoactivacion = em.merge(cursoactivacion);
            }
            Tipoplanestudio tipoplanestudionombretipoplanestudio = planestudio.getTipoplanestudionombretipoplanestudio();
            if (tipoplanestudionombretipoplanestudio != null) {
                tipoplanestudionombretipoplanestudio.getPlanestudioList().remove(planestudio);
                tipoplanestudionombretipoplanestudio = em.merge(tipoplanestudionombretipoplanestudio);
            }
            em.remove(planestudio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planestudio> findPlanestudioEntities() {
        return findPlanestudioEntities(true, -1, -1);
    }

    public List<Planestudio> findPlanestudioEntities(int maxResults, int firstResult) {
        return findPlanestudioEntities(false, maxResults, firstResult);
    }

    private List<Planestudio> findPlanestudioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planestudio.class));
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

    public Planestudio findPlanestudio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planestudio.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanestudioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planestudio> rt = cq.from(Planestudio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Planestudio findPlanEstudioByCarrera(String facultad, String carrera, String tipoplan) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p FROM Planestudio p WHERE p.carreraidcarrera.facultadcodigoarea.nombrearea= :facultad AND p.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera AND p.tipoplanestudionombretipoplanestudio.nombretipoplanestudio= :plan");
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("plan", tipoplan);
            Planestudio p = (Planestudio) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Planestudio findPlanEstudioByCarreraAvailable(String facultad, String carrera, String tipoplan) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p "
                    + "FROM Planestudio p "
                    + "WHERE p.planEstudioCancelado=false AND "
                    + "p.carreraidcarrera.facultadcodigoarea.codigoarea=:facultad AND "
                    + "p.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera AND "
                    + "p.tipoplanestudionombretipoplanestudio.nombretipoplanestudio=:plan");
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("plan", tipoplan);
            Planestudio p = (Planestudio) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Planestudio findPlanEstudioByCarreraAvailableCursoActivado(String facultad, String carrera, String tipoplan, Date matricula) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p FROM Planestudio p WHERE p.planEstudioCancelado=false AND p.carreraidcarrera.facultadcodigoarea.codigoarea=:facultad AND p.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera AND p.tipoplanestudionombretipoplanestudio.nombretipoplanestudio=:plan AND p.cursoactivacion.fechainiciomatriculacurso<= :fecha");
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("plan", tipoplan);
            q.setParameter("fecha", matricula);
            Planestudio p = (Planestudio) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public int findAsignaturasByPlanEstudio(String facultad, String carrera, String tipoplan) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT COUNT( DISTINCT a) FROM Planestudio p JOIN FETCH p.disciplinaPlanestudioList.asignaturaList a WHERE p.planEstudioCancelado=false AND p.carreraidcarrera.facultadcodigoarea.codigoarea=:facultad AND p.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera AND p.tipoplanestudionombretipoplanestudio.nombretipoplanestudio=:plan AND a.asignaturaCancelada=FALSE");
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("plan", tipoplan);
            Integer p = (Integer) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return 0;
        } finally {
            em.close();
        }
    }

    public List<String> findPlanestudioAvailableByCarrera(String facultad, String carrera) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p.tipoplanestudionombretipoplanestudio.nombretipoplanestudio "
                    + "FROM Planestudio p "
                    + "WHERE p.carreraidcarrera.facultadcodigoarea.nombrearea= :facultad AND "
                    + "p.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera AND "
                    + "p.planEstudioCancelado=false AND "
                    + "p.planEstudioListo=true "
                    + "ORDER BY p.tipoplanestudionombretipoplanestudio.nombretipoplanestudio");
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findPlanestudioAvailableByCarreraFecha(String facultad, String carrera, Date fecha) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p.tipoplanestudionombretipoplanestudio.nombretipoplanestudio FROM Planestudio p WHERE p.planEstudioCancelado=false AND p.carreraidcarrera.facultadcodigoarea.nombrearea =:facultad AND p.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera AND p.cursoactivacion.fechainiciomatriculacurso<= :fecha");
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("fecha", fecha);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
