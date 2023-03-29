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
import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.FacultadCum;
import cu.edu.unah.sigenuead.entity.FacultadCumCarrera;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraPK;
import java.util.ArrayList;
import java.util.List;
import cu.edu.unah.sigenuead.entity.Matricula;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class FacultadCumCarreraJpaController implements Serializable {

    public FacultadCumCarreraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FacultadCumCarrera facultadCumCarrera) throws PreexistingEntityException, Exception {
        if (facultadCumCarrera.getFacultadCumCarreraPK() == null) {
            facultadCumCarrera.setFacultadCumCarreraPK(new FacultadCumCarreraPK());
        }
        if (facultadCumCarrera.getFacultadCumCarreraEstudianteList() == null) {
            facultadCumCarrera.setFacultadCumCarreraEstudianteList(new ArrayList<FacultadCumCarreraEstudiante>());
        }
        if (facultadCumCarrera.getMatriculaList() == null) {
            facultadCumCarrera.setMatriculaList(new ArrayList<Matricula>());
        }
        facultadCumCarrera.getFacultadCumCarreraPK().setFacultadCumcumcodigocum(facultadCumCarrera.getFacultadCum().getFacultadCumPK().getCumcodigocum());
        facultadCumCarrera.getFacultadCumCarreraPK().setCarreraidcarrera(facultadCumCarrera.getCarrera().getIdcarrera());
        facultadCumCarrera.getFacultadCumCarreraPK().setFacultadCumfacultadcodigoarea(facultadCumCarrera.getFacultadCum().getFacultadCumPK().getFacultadcodigoarea());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrera carrera = facultadCumCarrera.getCarrera();
            if (carrera != null) {
                carrera = em.getReference(carrera.getClass(), carrera.getIdcarrera());
                facultadCumCarrera.setCarrera(carrera);
            }
            FacultadCum facultadCum = facultadCumCarrera.getFacultadCum();
            if (facultadCum != null) {
                facultadCum = em.getReference(facultadCum.getClass(), facultadCum.getFacultadCumPK());
                facultadCumCarrera.setFacultadCum(facultadCum);
            }
            List<FacultadCumCarreraEstudiante> attachedFacultadCumCarreraEstudianteList = new ArrayList<FacultadCumCarreraEstudiante>();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach : facultadCumCarrera.getFacultadCumCarreraEstudianteList()) {
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach = em.getReference(facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach.getClass(), facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach.getFacultadCumCarreraEstudiantePK());
                attachedFacultadCumCarreraEstudianteList.add(facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach);
            }
            facultadCumCarrera.setFacultadCumCarreraEstudianteList(attachedFacultadCumCarreraEstudianteList);
            List<Matricula> attachedMatriculaList = new ArrayList<Matricula>();
            for (Matricula matriculaListMatriculaToAttach : facultadCumCarrera.getMatriculaList()) {
                matriculaListMatriculaToAttach = em.getReference(matriculaListMatriculaToAttach.getClass(), matriculaListMatriculaToAttach.getMatriculaId());
                attachedMatriculaList.add(matriculaListMatriculaToAttach);
            }
            facultadCumCarrera.setMatriculaList(attachedMatriculaList);
            em.persist(facultadCumCarrera);
            if (carrera != null) {
                carrera.getFacultadCumCarreraList().add(facultadCumCarrera);
                carrera = em.merge(carrera);
            }
            if (facultadCum != null) {
                facultadCum.getFacultadCumCarreraList().add(facultadCumCarrera);
                facultadCum = em.merge(facultadCum);
            }
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante : facultadCumCarrera.getFacultadCumCarreraEstudianteList()) {
                FacultadCumCarrera oldFacultadCumCarreraOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.getFacultadCumCarrera();
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.setFacultadCumCarrera(facultadCumCarrera);
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                if (oldFacultadCumCarreraOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante != null) {
                    oldFacultadCumCarreraOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                    oldFacultadCumCarreraOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = em.merge(oldFacultadCumCarreraOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                }
            }
            for (Matricula matriculaListMatricula : facultadCumCarrera.getMatriculaList()) {
                FacultadCumCarrera oldFacultadCumCarreraOfMatriculaListMatricula = matriculaListMatricula.getFacultadCumCarrera();
                matriculaListMatricula.setFacultadCumCarrera(facultadCumCarrera);
                matriculaListMatricula = em.merge(matriculaListMatricula);
                if (oldFacultadCumCarreraOfMatriculaListMatricula != null) {
                    oldFacultadCumCarreraOfMatriculaListMatricula.getMatriculaList().remove(matriculaListMatricula);
                    oldFacultadCumCarreraOfMatriculaListMatricula = em.merge(oldFacultadCumCarreraOfMatriculaListMatricula);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFacultadCumCarrera(facultadCumCarrera.getFacultadCumCarreraPK()) != null) {
                throw new PreexistingEntityException("FacultadCumCarrera " + facultadCumCarrera + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FacultadCumCarrera facultadCumCarrera) throws IllegalOrphanException, NonexistentEntityException, Exception {
        facultadCumCarrera.getFacultadCumCarreraPK().setFacultadCumcumcodigocum(facultadCumCarrera.getFacultadCum().getFacultadCumPK().getCumcodigocum());
        facultadCumCarrera.getFacultadCumCarreraPK().setCarreraidcarrera(facultadCumCarrera.getCarrera().getIdcarrera());
        facultadCumCarrera.getFacultadCumCarreraPK().setFacultadCumfacultadcodigoarea(facultadCumCarrera.getFacultadCum().getFacultadCumPK().getFacultadcodigoarea());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacultadCumCarrera persistentFacultadCumCarrera = em.find(FacultadCumCarrera.class, facultadCumCarrera.getFacultadCumCarreraPK());
            Carrera carreraOld = persistentFacultadCumCarrera.getCarrera();
            Carrera carreraNew = facultadCumCarrera.getCarrera();
            FacultadCum facultadCumOld = persistentFacultadCumCarrera.getFacultadCum();
            FacultadCum facultadCumNew = facultadCumCarrera.getFacultadCum();
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListOld = persistentFacultadCumCarrera.getFacultadCumCarreraEstudianteList();
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListNew = facultadCumCarrera.getFacultadCumCarreraEstudianteList();
            List<Matricula> matriculaListOld = persistentFacultadCumCarrera.getMatriculaList();
            List<Matricula> matriculaListNew = facultadCumCarrera.getMatriculaList();
            List<String> illegalOrphanMessages = null;
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListOld) {
                if (!facultadCumCarreraEstudianteListNew.contains(facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FacultadCumCarreraEstudiante " + facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante + " since its facultadCumCarrera field is not nullable.");
                }
            }
            for (Matricula matriculaListOldMatricula : matriculaListOld) {
                if (!matriculaListNew.contains(matriculaListOldMatricula)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Matricula " + matriculaListOldMatricula + " since its facultadCumCarrera field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (carreraNew != null) {
                carreraNew = em.getReference(carreraNew.getClass(), carreraNew.getIdcarrera());
                facultadCumCarrera.setCarrera(carreraNew);
            }
            if (facultadCumNew != null) {
                facultadCumNew = em.getReference(facultadCumNew.getClass(), facultadCumNew.getFacultadCumPK());
                facultadCumCarrera.setFacultadCum(facultadCumNew);
            }
            List<FacultadCumCarreraEstudiante> attachedFacultadCumCarreraEstudianteListNew = new ArrayList<FacultadCumCarreraEstudiante>();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach : facultadCumCarreraEstudianteListNew) {
                facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach = em.getReference(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach.getClass(), facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach.getFacultadCumCarreraEstudiantePK());
                attachedFacultadCumCarreraEstudianteListNew.add(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach);
            }
            facultadCumCarreraEstudianteListNew = attachedFacultadCumCarreraEstudianteListNew;
            facultadCumCarrera.setFacultadCumCarreraEstudianteList(facultadCumCarreraEstudianteListNew);
            List<Matricula> attachedMatriculaListNew = new ArrayList<Matricula>();
            for (Matricula matriculaListNewMatriculaToAttach : matriculaListNew) {
                matriculaListNewMatriculaToAttach = em.getReference(matriculaListNewMatriculaToAttach.getClass(), matriculaListNewMatriculaToAttach.getMatriculaId());
                attachedMatriculaListNew.add(matriculaListNewMatriculaToAttach);
            }
            matriculaListNew = attachedMatriculaListNew;
            facultadCumCarrera.setMatriculaList(matriculaListNew);
            facultadCumCarrera = em.merge(facultadCumCarrera);
            if (carreraOld != null && !carreraOld.equals(carreraNew)) {
                carreraOld.getFacultadCumCarreraList().remove(facultadCumCarrera);
                carreraOld = em.merge(carreraOld);
            }
            if (carreraNew != null && !carreraNew.equals(carreraOld)) {
                carreraNew.getFacultadCumCarreraList().add(facultadCumCarrera);
                carreraNew = em.merge(carreraNew);
            }
            if (facultadCumOld != null && !facultadCumOld.equals(facultadCumNew)) {
                facultadCumOld.getFacultadCumCarreraList().remove(facultadCumCarrera);
                facultadCumOld = em.merge(facultadCumOld);
            }
            if (facultadCumNew != null && !facultadCumNew.equals(facultadCumOld)) {
                facultadCumNew.getFacultadCumCarreraList().add(facultadCumCarrera);
                facultadCumNew = em.merge(facultadCumNew);
            }
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListNew) {
                if (!facultadCumCarreraEstudianteListOld.contains(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante)) {
                    FacultadCumCarrera oldFacultadCumCarreraOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.getFacultadCumCarrera();
                    facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.setFacultadCumCarrera(facultadCumCarrera);
                    facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                    if (oldFacultadCumCarreraOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante != null && !oldFacultadCumCarreraOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.equals(facultadCumCarrera)) {
                        oldFacultadCumCarreraOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                        oldFacultadCumCarreraOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = em.merge(oldFacultadCumCarreraOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                    }
                }
            }
            for (Matricula matriculaListNewMatricula : matriculaListNew) {
                if (!matriculaListOld.contains(matriculaListNewMatricula)) {
                    FacultadCumCarrera oldFacultadCumCarreraOfMatriculaListNewMatricula = matriculaListNewMatricula.getFacultadCumCarrera();
                    matriculaListNewMatricula.setFacultadCumCarrera(facultadCumCarrera);
                    matriculaListNewMatricula = em.merge(matriculaListNewMatricula);
                    if (oldFacultadCumCarreraOfMatriculaListNewMatricula != null && !oldFacultadCumCarreraOfMatriculaListNewMatricula.equals(facultadCumCarrera)) {
                        oldFacultadCumCarreraOfMatriculaListNewMatricula.getMatriculaList().remove(matriculaListNewMatricula);
                        oldFacultadCumCarreraOfMatriculaListNewMatricula = em.merge(oldFacultadCumCarreraOfMatriculaListNewMatricula);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                FacultadCumCarreraPK id = facultadCumCarrera.getFacultadCumCarreraPK();
                if (findFacultadCumCarrera(id) == null) {
                    throw new NonexistentEntityException("The facultadCumCarrera with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(FacultadCumCarreraPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacultadCumCarrera facultadCumCarrera;
            try {
                facultadCumCarrera = em.getReference(FacultadCumCarrera.class, id);
                facultadCumCarrera.getFacultadCumCarreraPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facultadCumCarrera with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListOrphanCheck = facultadCumCarrera.getFacultadCumCarreraEstudianteList();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListOrphanCheckFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FacultadCumCarrera (" + facultadCumCarrera + ") cannot be destroyed since the FacultadCumCarreraEstudiante " + facultadCumCarreraEstudianteListOrphanCheckFacultadCumCarreraEstudiante + " in its facultadCumCarreraEstudianteList field has a non-nullable facultadCumCarrera field.");
            }
            List<Matricula> matriculaListOrphanCheck = facultadCumCarrera.getMatriculaList();
            for (Matricula matriculaListOrphanCheckMatricula : matriculaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FacultadCumCarrera (" + facultadCumCarrera + ") cannot be destroyed since the Matricula " + matriculaListOrphanCheckMatricula + " in its matriculaList field has a non-nullable facultadCumCarrera field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Carrera carrera = facultadCumCarrera.getCarrera();
            if (carrera != null) {
                carrera.getFacultadCumCarreraList().remove(facultadCumCarrera);
                carrera = em.merge(carrera);
            }
            FacultadCum facultadCum = facultadCumCarrera.getFacultadCum();
            if (facultadCum != null) {
                facultadCum.getFacultadCumCarreraList().remove(facultadCumCarrera);
                facultadCum = em.merge(facultadCum);
            }
            em.remove(facultadCumCarrera);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FacultadCumCarrera> findFacultadCumCarreraEntities() {
        return findFacultadCumCarreraEntities(true, -1, -1);
    }

    public List<FacultadCumCarrera> findFacultadCumCarreraEntities(int maxResults, int firstResult) {
        return findFacultadCumCarreraEntities(false, maxResults, firstResult);
    }

    private List<FacultadCumCarrera> findFacultadCumCarreraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FacultadCumCarrera.class));
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

    public FacultadCumCarrera findFacultadCumCarrera(FacultadCumCarreraPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FacultadCumCarrera.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacultadCumCarreraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FacultadCumCarrera> rt = cq.from(FacultadCumCarrera.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<String> findCarreraByFacultadCum(String facultad, int carrera) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT fcc.facultadCum.cum.nombrecum FROM FacultadCumCarrera fcc WHERE fcc.carrera.idcarrera=:carrera AND fcc.facultadCum.facultad.codigoarea=:facultad AND fcc.facultadCum.cum.codigocum NOT LIKE '' ORDER BY fcc.facultadCum.cum.nombrecum");
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
}
