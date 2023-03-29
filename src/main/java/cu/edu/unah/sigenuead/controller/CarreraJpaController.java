/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.entity.Carrera;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Carreranacional;
import cu.edu.unah.sigenuead.entity.Facultad;
import cu.edu.unah.sigenuead.entity.FacultadCumCarrera;
import java.util.ArrayList;
import java.util.List;
import cu.edu.unah.sigenuead.entity.Planestudio;
import cu.edu.unah.sigenuead.entity.CarreraCurso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class CarreraJpaController implements Serializable {

    public CarreraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carrera carrera) {
        if (carrera.getFacultadCumCarreraList() == null) {
            carrera.setFacultadCumCarreraList(new ArrayList<FacultadCumCarrera>());
        }
        if (carrera.getPlanestudioList() == null) {
            carrera.setPlanestudioList(new ArrayList<Planestudio>());
        }
        if (carrera.getCarreraCursoList() == null) {
            carrera.setCarreraCursoList(new ArrayList<CarreraCurso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carreranacional carreranacionalidcarreranacional = carrera.getCarreranacionalidcarreranacional();
            if (carreranacionalidcarreranacional != null) {
                carreranacionalidcarreranacional = em.getReference(carreranacionalidcarreranacional.getClass(), carreranacionalidcarreranacional.getIdcarreranacional());
                carrera.setCarreranacionalidcarreranacional(carreranacionalidcarreranacional);
            }
            Facultad facultadcodigoarea = carrera.getFacultadcodigoarea();
            if (facultadcodigoarea != null) {
                facultadcodigoarea = em.getReference(facultadcodigoarea.getClass(), facultadcodigoarea.getCodigoarea());
                carrera.setFacultadcodigoarea(facultadcodigoarea);
            }
            List<FacultadCumCarrera> attachedFacultadCumCarreraList = new ArrayList<FacultadCumCarrera>();
            for (FacultadCumCarrera facultadCumCarreraListFacultadCumCarreraToAttach : carrera.getFacultadCumCarreraList()) {
                facultadCumCarreraListFacultadCumCarreraToAttach = em.getReference(facultadCumCarreraListFacultadCumCarreraToAttach.getClass(), facultadCumCarreraListFacultadCumCarreraToAttach.getFacultadCumCarreraPK());
                attachedFacultadCumCarreraList.add(facultadCumCarreraListFacultadCumCarreraToAttach);
            }
            carrera.setFacultadCumCarreraList(attachedFacultadCumCarreraList);
            List<Planestudio> attachedPlanestudioList = new ArrayList<Planestudio>();
            for (Planestudio planestudioListPlanestudioToAttach : carrera.getPlanestudioList()) {
                planestudioListPlanestudioToAttach = em.getReference(planestudioListPlanestudioToAttach.getClass(), planestudioListPlanestudioToAttach.getIdplanestudio());
                attachedPlanestudioList.add(planestudioListPlanestudioToAttach);
            }
            carrera.setPlanestudioList(attachedPlanestudioList);
            List<CarreraCurso> attachedCarreraCursoList = new ArrayList<CarreraCurso>();
            for (CarreraCurso carreraCursoListCarreraCursoToAttach : carrera.getCarreraCursoList()) {
                carreraCursoListCarreraCursoToAttach = em.getReference(carreraCursoListCarreraCursoToAttach.getClass(), carreraCursoListCarreraCursoToAttach.getCarreraCursoPK());
                attachedCarreraCursoList.add(carreraCursoListCarreraCursoToAttach);
            }
            carrera.setCarreraCursoList(attachedCarreraCursoList);
            em.persist(carrera);
            if (carreranacionalidcarreranacional != null) {
                carreranacionalidcarreranacional.getCarreraList().add(carrera);
                carreranacionalidcarreranacional = em.merge(carreranacionalidcarreranacional);
            }
            if (facultadcodigoarea != null) {
                facultadcodigoarea.getCarreraList().add(carrera);
                facultadcodigoarea = em.merge(facultadcodigoarea);
            }
            for (FacultadCumCarrera facultadCumCarreraListFacultadCumCarrera : carrera.getFacultadCumCarreraList()) {
                Carrera oldCarreraOfFacultadCumCarreraListFacultadCumCarrera = facultadCumCarreraListFacultadCumCarrera.getCarrera();
                facultadCumCarreraListFacultadCumCarrera.setCarrera(carrera);
                facultadCumCarreraListFacultadCumCarrera = em.merge(facultadCumCarreraListFacultadCumCarrera);
                if (oldCarreraOfFacultadCumCarreraListFacultadCumCarrera != null) {
                    oldCarreraOfFacultadCumCarreraListFacultadCumCarrera.getFacultadCumCarreraList().remove(facultadCumCarreraListFacultadCumCarrera);
                    oldCarreraOfFacultadCumCarreraListFacultadCumCarrera = em.merge(oldCarreraOfFacultadCumCarreraListFacultadCumCarrera);
                }
            }
            for (Planestudio planestudioListPlanestudio : carrera.getPlanestudioList()) {
                Carrera oldCarreraidcarreraOfPlanestudioListPlanestudio = planestudioListPlanestudio.getCarreraidcarrera();
                planestudioListPlanestudio.setCarreraidcarrera(carrera);
                planestudioListPlanestudio = em.merge(planestudioListPlanestudio);
                if (oldCarreraidcarreraOfPlanestudioListPlanestudio != null) {
                    oldCarreraidcarreraOfPlanestudioListPlanestudio.getPlanestudioList().remove(planestudioListPlanestudio);
                    oldCarreraidcarreraOfPlanestudioListPlanestudio = em.merge(oldCarreraidcarreraOfPlanestudioListPlanestudio);
                }
            }
            for (CarreraCurso carreraCursoListCarreraCurso : carrera.getCarreraCursoList()) {
                Carrera oldCarreraOfCarreraCursoListCarreraCurso = carreraCursoListCarreraCurso.getCarrera();
                carreraCursoListCarreraCurso.setCarrera(carrera);
                carreraCursoListCarreraCurso = em.merge(carreraCursoListCarreraCurso);
                if (oldCarreraOfCarreraCursoListCarreraCurso != null) {
                    oldCarreraOfCarreraCursoListCarreraCurso.getCarreraCursoList().remove(carreraCursoListCarreraCurso);
                    oldCarreraOfCarreraCursoListCarreraCurso = em.merge(oldCarreraOfCarreraCursoListCarreraCurso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Carrera carrera) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrera persistentCarrera = em.find(Carrera.class, carrera.getIdcarrera());
            Carreranacional carreranacionalidcarreranacionalOld = persistentCarrera.getCarreranacionalidcarreranacional();
            Carreranacional carreranacionalidcarreranacionalNew = carrera.getCarreranacionalidcarreranacional();
            Facultad facultadcodigoareaOld = persistentCarrera.getFacultadcodigoarea();
            Facultad facultadcodigoareaNew = carrera.getFacultadcodigoarea();
            List<FacultadCumCarrera> facultadCumCarreraListOld = persistentCarrera.getFacultadCumCarreraList();
            List<FacultadCumCarrera> facultadCumCarreraListNew = carrera.getFacultadCumCarreraList();
            List<Planestudio> planestudioListOld = persistentCarrera.getPlanestudioList();
            List<Planestudio> planestudioListNew = carrera.getPlanestudioList();
            List<CarreraCurso> carreraCursoListOld = persistentCarrera.getCarreraCursoList();
            List<CarreraCurso> carreraCursoListNew = carrera.getCarreraCursoList();
            List<String> illegalOrphanMessages = null;
            for (FacultadCumCarrera facultadCumCarreraListOldFacultadCumCarrera : facultadCumCarreraListOld) {
                if (!facultadCumCarreraListNew.contains(facultadCumCarreraListOldFacultadCumCarrera)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FacultadCumCarrera " + facultadCumCarreraListOldFacultadCumCarrera + " since its carrera field is not nullable.");
                }
            }
            for (Planestudio planestudioListOldPlanestudio : planestudioListOld) {
                if (!planestudioListNew.contains(planestudioListOldPlanestudio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Planestudio " + planestudioListOldPlanestudio + " since its carreraidcarrera field is not nullable.");
                }
            }
            for (CarreraCurso carreraCursoListOldCarreraCurso : carreraCursoListOld) {
                if (!carreraCursoListNew.contains(carreraCursoListOldCarreraCurso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CarreraCurso " + carreraCursoListOldCarreraCurso + " since its carrera field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (carreranacionalidcarreranacionalNew != null) {
                carreranacionalidcarreranacionalNew = em.getReference(carreranacionalidcarreranacionalNew.getClass(), carreranacionalidcarreranacionalNew.getIdcarreranacional());
                carrera.setCarreranacionalidcarreranacional(carreranacionalidcarreranacionalNew);
            }
            if (facultadcodigoareaNew != null) {
                facultadcodigoareaNew = em.getReference(facultadcodigoareaNew.getClass(), facultadcodigoareaNew.getCodigoarea());
                carrera.setFacultadcodigoarea(facultadcodigoareaNew);
            }
            List<FacultadCumCarrera> attachedFacultadCumCarreraListNew = new ArrayList<FacultadCumCarrera>();
            for (FacultadCumCarrera facultadCumCarreraListNewFacultadCumCarreraToAttach : facultadCumCarreraListNew) {
                facultadCumCarreraListNewFacultadCumCarreraToAttach = em.getReference(facultadCumCarreraListNewFacultadCumCarreraToAttach.getClass(), facultadCumCarreraListNewFacultadCumCarreraToAttach.getFacultadCumCarreraPK());
                attachedFacultadCumCarreraListNew.add(facultadCumCarreraListNewFacultadCumCarreraToAttach);
            }
            facultadCumCarreraListNew = attachedFacultadCumCarreraListNew;
            carrera.setFacultadCumCarreraList(facultadCumCarreraListNew);
            List<Planestudio> attachedPlanestudioListNew = new ArrayList<Planestudio>();
            for (Planestudio planestudioListNewPlanestudioToAttach : planestudioListNew) {
                planestudioListNewPlanestudioToAttach = em.getReference(planestudioListNewPlanestudioToAttach.getClass(), planestudioListNewPlanestudioToAttach.getIdplanestudio());
                attachedPlanestudioListNew.add(planestudioListNewPlanestudioToAttach);
            }
            planestudioListNew = attachedPlanestudioListNew;
            carrera.setPlanestudioList(planestudioListNew);
            List<CarreraCurso> attachedCarreraCursoListNew = new ArrayList<CarreraCurso>();
            for (CarreraCurso carreraCursoListNewCarreraCursoToAttach : carreraCursoListNew) {
                carreraCursoListNewCarreraCursoToAttach = em.getReference(carreraCursoListNewCarreraCursoToAttach.getClass(), carreraCursoListNewCarreraCursoToAttach.getCarreraCursoPK());
                attachedCarreraCursoListNew.add(carreraCursoListNewCarreraCursoToAttach);
            }
            carreraCursoListNew = attachedCarreraCursoListNew;
            carrera.setCarreraCursoList(carreraCursoListNew);
            carrera = em.merge(carrera);
            if (carreranacionalidcarreranacionalOld != null && !carreranacionalidcarreranacionalOld.equals(carreranacionalidcarreranacionalNew)) {
                carreranacionalidcarreranacionalOld.getCarreraList().remove(carrera);
                carreranacionalidcarreranacionalOld = em.merge(carreranacionalidcarreranacionalOld);
            }
            if (carreranacionalidcarreranacionalNew != null && !carreranacionalidcarreranacionalNew.equals(carreranacionalidcarreranacionalOld)) {
                carreranacionalidcarreranacionalNew.getCarreraList().add(carrera);
                carreranacionalidcarreranacionalNew = em.merge(carreranacionalidcarreranacionalNew);
            }
            if (facultadcodigoareaOld != null && !facultadcodigoareaOld.equals(facultadcodigoareaNew)) {
                facultadcodigoareaOld.getCarreraList().remove(carrera);
                facultadcodigoareaOld = em.merge(facultadcodigoareaOld);
            }
            if (facultadcodigoareaNew != null && !facultadcodigoareaNew.equals(facultadcodigoareaOld)) {
                facultadcodigoareaNew.getCarreraList().add(carrera);
                facultadcodigoareaNew = em.merge(facultadcodigoareaNew);
            }
            for (FacultadCumCarrera facultadCumCarreraListNewFacultadCumCarrera : facultadCumCarreraListNew) {
                if (!facultadCumCarreraListOld.contains(facultadCumCarreraListNewFacultadCumCarrera)) {
                    Carrera oldCarreraOfFacultadCumCarreraListNewFacultadCumCarrera = facultadCumCarreraListNewFacultadCumCarrera.getCarrera();
                    facultadCumCarreraListNewFacultadCumCarrera.setCarrera(carrera);
                    facultadCumCarreraListNewFacultadCumCarrera = em.merge(facultadCumCarreraListNewFacultadCumCarrera);
                    if (oldCarreraOfFacultadCumCarreraListNewFacultadCumCarrera != null && !oldCarreraOfFacultadCumCarreraListNewFacultadCumCarrera.equals(carrera)) {
                        oldCarreraOfFacultadCumCarreraListNewFacultadCumCarrera.getFacultadCumCarreraList().remove(facultadCumCarreraListNewFacultadCumCarrera);
                        oldCarreraOfFacultadCumCarreraListNewFacultadCumCarrera = em.merge(oldCarreraOfFacultadCumCarreraListNewFacultadCumCarrera);
                    }
                }
            }
            for (Planestudio planestudioListNewPlanestudio : planestudioListNew) {
                if (!planestudioListOld.contains(planestudioListNewPlanestudio)) {
                    Carrera oldCarreraidcarreraOfPlanestudioListNewPlanestudio = planestudioListNewPlanestudio.getCarreraidcarrera();
                    planestudioListNewPlanestudio.setCarreraidcarrera(carrera);
                    planestudioListNewPlanestudio = em.merge(planestudioListNewPlanestudio);
                    if (oldCarreraidcarreraOfPlanestudioListNewPlanestudio != null && !oldCarreraidcarreraOfPlanestudioListNewPlanestudio.equals(carrera)) {
                        oldCarreraidcarreraOfPlanestudioListNewPlanestudio.getPlanestudioList().remove(planestudioListNewPlanestudio);
                        oldCarreraidcarreraOfPlanestudioListNewPlanestudio = em.merge(oldCarreraidcarreraOfPlanestudioListNewPlanestudio);
                    }
                }
            }
            for (CarreraCurso carreraCursoListNewCarreraCurso : carreraCursoListNew) {
                if (!carreraCursoListOld.contains(carreraCursoListNewCarreraCurso)) {
                    Carrera oldCarreraOfCarreraCursoListNewCarreraCurso = carreraCursoListNewCarreraCurso.getCarrera();
                    carreraCursoListNewCarreraCurso.setCarrera(carrera);
                    carreraCursoListNewCarreraCurso = em.merge(carreraCursoListNewCarreraCurso);
                    if (oldCarreraOfCarreraCursoListNewCarreraCurso != null && !oldCarreraOfCarreraCursoListNewCarreraCurso.equals(carrera)) {
                        oldCarreraOfCarreraCursoListNewCarreraCurso.getCarreraCursoList().remove(carreraCursoListNewCarreraCurso);
                        oldCarreraOfCarreraCursoListNewCarreraCurso = em.merge(oldCarreraOfCarreraCursoListNewCarreraCurso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = carrera.getIdcarrera();
                if (findCarrera(id) == null) {
                    throw new NonexistentEntityException("The carrera with id " + id + " no longer exists.");
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
            Carrera carrera;
            try {
                carrera = em.getReference(Carrera.class, id);
                carrera.getIdcarrera();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carrera with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<FacultadCumCarrera> facultadCumCarreraListOrphanCheck = carrera.getFacultadCumCarreraList();
            for (FacultadCumCarrera facultadCumCarreraListOrphanCheckFacultadCumCarrera : facultadCumCarreraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Carrera (" + carrera + ") cannot be destroyed since the FacultadCumCarrera " + facultadCumCarreraListOrphanCheckFacultadCumCarrera + " in its facultadCumCarreraList field has a non-nullable carrera field.");
            }
            List<Planestudio> planestudioListOrphanCheck = carrera.getPlanestudioList();
            for (Planestudio planestudioListOrphanCheckPlanestudio : planestudioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Carrera (" + carrera + ") cannot be destroyed since the Planestudio " + planestudioListOrphanCheckPlanestudio + " in its planestudioList field has a non-nullable carreraidcarrera field.");
            }
            List<CarreraCurso> carreraCursoListOrphanCheck = carrera.getCarreraCursoList();
            for (CarreraCurso carreraCursoListOrphanCheckCarreraCurso : carreraCursoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Carrera (" + carrera + ") cannot be destroyed since the CarreraCurso " + carreraCursoListOrphanCheckCarreraCurso + " in its carreraCursoList field has a non-nullable carrera field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Carreranacional carreranacionalidcarreranacional = carrera.getCarreranacionalidcarreranacional();
            if (carreranacionalidcarreranacional != null) {
                carreranacionalidcarreranacional.getCarreraList().remove(carrera);
                carreranacionalidcarreranacional = em.merge(carreranacionalidcarreranacional);
            }
            Facultad facultadcodigoarea = carrera.getFacultadcodigoarea();
            if (facultadcodigoarea != null) {
                facultadcodigoarea.getCarreraList().remove(carrera);
                facultadcodigoarea = em.merge(facultadcodigoarea);
            }
            em.remove(carrera);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Carrera> findCarreraEntities() {
        return findCarreraEntities(true, -1, -1);
    }

    public List<Carrera> findCarreraEntities(int maxResults, int firstResult) {
        return findCarreraEntities(false, maxResults, firstResult);
    }

    private List<Carrera> findCarreraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carrera.class));
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

    public Carrera findCarrera(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carrera.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarreraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carrera> rt = cq.from(Carrera.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Carrera findCarreraByCarreraNacional(int cn, String fac) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Carrera c "
                    + "WHERE c.carreranacionalidcarreranacional.idcarreranacional= :carreranacional AND "
                    + "c.facultadcodigoarea.codigoarea= :facultad");
            q.setParameter("carreranacional", cn);
            q.setParameter("facultad", fac);
            Carrera p = (Carrera) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findCarreraAvailableByFacultadCum(String fac, String cum) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT fcc.carrera.carreranacionalidcarreranacional.nombrecarreranacional FROM FacultadCumCarrera fcc WHERE fcc.cancelado=false AND fcc.facultadCum.cancelado=false AND fcc.facultadCum.facultad.canceladoarea=false AND fcc.facultadCum.cum.cumcancelado=false AND fcc.carrera.canceladacarrera=false AND fcc.facultadCumCarreraPK.facultadCumfacultadcodigoarea = :facultad AND fcc.facultadCumCarreraPK.facultadCumcumcodigocum=:cum ORDER BY fcc.carrera.carreranacionalidcarreranacional.nombrecarreranacional");
            q.setParameter("facultad", fac);
            q.setParameter("cum", cum);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findCarreraAvailableByFacultad(String fac) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c.carreranacionalidcarreranacional.nombrecarreranacional FROM Carrera c JOIN FETCH c.facultadCumCarreraList fcc WHERE c.canceladacarrera=false AND c.facultadcodigoarea.codigoarea= :facultad AND fcc.facultadCumCarreraPK.facultadCumcumcodigocum='' ORDER BY c.carreranacionalidcarreranacional.nombrecarreranacional");
            q.setParameter("facultad", fac);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findCarreraAvailableByCum(String fac) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c.carreranacionalidcarreranacional.nombrecarreranacional FROM Carrera c JOIN FETCH c.facultadCumCarreraList fc WHERE c.canceladacarrera=false AND fc.facultadCum.cum.nombrecum= :cum AND fc.facultadCumCarreraPK.facultadCumfacultadcodigoarea='' ORDER BY c.carreranacionalidcarreranacional.nombrecarreranacional");
            q.setParameter("cum", fac);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Carrera> findCarreraAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Carrera c "
                    + "WHERE c.canceladacarrera=false "
                    + "ORDER BY c.carreranacionalidcarreranacional.nombrecarreranacional");
            List<Carrera> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Carrera findCarreraByFacultadCarreranacional(String fac, String cn) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Carrera c "
                    + "WHERE c.carreranacionalidcarreranacional.nombrecarreranacional= :carreranacional AND "
                    + "c.facultadcodigoarea.codigoarea= :facultad");
            q.setParameter("carreranacional", cn);
            q.setParameter("facultad", fac);
            Carrera p = (Carrera) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Carrera findCarreraByFacultadCarreranacionalAvailable(String fac, String cn) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Carrera c "
                    + "WHERE c.canceladacarrera=false AND "
                    + "c.carreranacionalidcarreranacional.nombrecarreranacional= :carreranacional AND "
                    + "c.facultadcodigoarea.nombrearea= :facultad");
            q.setParameter("carreranacional", cn);
            q.setParameter("facultad", fac);
            Carrera p = (Carrera) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Carrera findCarreraByCarreranacionalAvailable(String cn) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM Carrera c "
                    + "WHERE c.canceladacarrera=false AND "
                    + "c.carreranacionalidcarreranacional.nombrecarreranacional= :carreranacional");
            q.setParameter("carreranacional", cn);
            Carrera p = (Carrera) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<CarreraCurso> findCarreraInCurso(String curso) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM CarreraCurso c "
                    + "WHERE c.curso.idcurso=:curso");
            q.setParameter("curso", curso);
            List<CarreraCurso> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
