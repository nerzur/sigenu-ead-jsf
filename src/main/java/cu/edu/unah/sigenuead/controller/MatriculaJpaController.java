/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.ExamenMatriculaFacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.FacultadCumCarrera;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.Matricula;
import cu.edu.unah.sigenuead.entity.MatriculaFacultadCumCarreraEstudianteAsignatura;
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
public class MatriculaJpaController implements Serializable {

    public MatriculaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Matricula matricula) {
        if (matricula.getMatriculaFacultadCumCarreraEstudianteAsignaturaList() == null) {
            matricula.setMatriculaFacultadCumCarreraEstudianteAsignaturaList(new ArrayList<MatriculaFacultadCumCarreraEstudianteAsignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso cursoidcurso = matricula.getCursoidcurso();
            if (cursoidcurso != null) {
                cursoidcurso = em.getReference(cursoidcurso.getClass(), cursoidcurso.getIdcurso());
                matricula.setCursoidcurso(cursoidcurso);
            }
            FacultadCumCarrera facultadCumCarrera = matricula.getFacultadCumCarrera();
            if (facultadCumCarrera != null) {
                facultadCumCarrera = em.getReference(facultadCumCarrera.getClass(), facultadCumCarrera.getFacultadCumCarreraPK());
                matricula.setFacultadCumCarrera(facultadCumCarrera);
            }
            List<MatriculaFacultadCumCarreraEstudianteAsignatura> attachedMatriculaFacultadCumCarreraEstudianteAsignaturaList = new ArrayList<MatriculaFacultadCumCarreraEstudianteAsignatura>();
            for (MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach : matricula.getMatriculaFacultadCumCarreraEstudianteAsignaturaList()) {
                matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach = em.getReference(matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getClass(), matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK());
                attachedMatriculaFacultadCumCarreraEstudianteAsignaturaList.add(matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach);
            }
            matricula.setMatriculaFacultadCumCarreraEstudianteAsignaturaList(attachedMatriculaFacultadCumCarreraEstudianteAsignaturaList);
            em.persist(matricula);
            if (cursoidcurso != null) {
                cursoidcurso.getMatriculaList().add(matricula);
                cursoidcurso = em.merge(cursoidcurso);
            }
            if (facultadCumCarrera != null) {
                facultadCumCarrera.getMatriculaList().add(matricula);
                facultadCumCarrera = em.merge(facultadCumCarrera);
            }
            for (MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura : matricula.getMatriculaFacultadCumCarreraEstudianteAsignaturaList()) {
                Matricula oldMatriculaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura = matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura.getMatricula();
                matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura.setMatricula(matricula);
                matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura);
                if (oldMatriculaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura != null) {
                    oldMatriculaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura);
                    oldMatriculaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(oldMatriculaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Matricula matricula) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Matricula persistentMatricula = em.find(Matricula.class, matricula.getMatriculaId());
            Curso cursoidcursoOld = persistentMatricula.getCursoidcurso();
            Curso cursoidcursoNew = matricula.getCursoidcurso();
            FacultadCumCarrera facultadCumCarreraOld = persistentMatricula.getFacultadCumCarrera();
            FacultadCumCarrera facultadCumCarreraNew = matricula.getFacultadCumCarrera();
            List<MatriculaFacultadCumCarreraEstudianteAsignatura> matriculaFacultadCumCarreraEstudianteAsignaturaListOld = persistentMatricula.getMatriculaFacultadCumCarreraEstudianteAsignaturaList();
            List<MatriculaFacultadCumCarreraEstudianteAsignatura> matriculaFacultadCumCarreraEstudianteAsignaturaListNew = matricula.getMatriculaFacultadCumCarreraEstudianteAsignaturaList();
            List<String> illegalOrphanMessages = null;
            for (MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaListOldMatriculaFacultadCumCarreraEstudianteAsignatura : matriculaFacultadCumCarreraEstudianteAsignaturaListOld) {
                if (!matriculaFacultadCumCarreraEstudianteAsignaturaListNew.contains(matriculaFacultadCumCarreraEstudianteAsignaturaListOldMatriculaFacultadCumCarreraEstudianteAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MatriculaFacultadCumCarreraEstudianteAsignatura " + matriculaFacultadCumCarreraEstudianteAsignaturaListOldMatriculaFacultadCumCarreraEstudianteAsignatura + " since its matricula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cursoidcursoNew != null) {
                cursoidcursoNew = em.getReference(cursoidcursoNew.getClass(), cursoidcursoNew.getIdcurso());
                matricula.setCursoidcurso(cursoidcursoNew);
            }
            if (facultadCumCarreraNew != null) {
                facultadCumCarreraNew = em.getReference(facultadCumCarreraNew.getClass(), facultadCumCarreraNew.getFacultadCumCarreraPK());
                matricula.setFacultadCumCarrera(facultadCumCarreraNew);
            }
            List<MatriculaFacultadCumCarreraEstudianteAsignatura> attachedMatriculaFacultadCumCarreraEstudianteAsignaturaListNew = new ArrayList<MatriculaFacultadCumCarreraEstudianteAsignatura>();
            for (MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach : matriculaFacultadCumCarreraEstudianteAsignaturaListNew) {
                matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach = em.getReference(matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getClass(), matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK());
                attachedMatriculaFacultadCumCarreraEstudianteAsignaturaListNew.add(matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach);
            }
            matriculaFacultadCumCarreraEstudianteAsignaturaListNew = attachedMatriculaFacultadCumCarreraEstudianteAsignaturaListNew;
            matricula.setMatriculaFacultadCumCarreraEstudianteAsignaturaList(matriculaFacultadCumCarreraEstudianteAsignaturaListNew);
            matricula = em.merge(matricula);
            if (cursoidcursoOld != null && !cursoidcursoOld.equals(cursoidcursoNew)) {
                cursoidcursoOld.getMatriculaList().remove(matricula);
                cursoidcursoOld = em.merge(cursoidcursoOld);
            }
            if (cursoidcursoNew != null && !cursoidcursoNew.equals(cursoidcursoOld)) {
                cursoidcursoNew.getMatriculaList().add(matricula);
                cursoidcursoNew = em.merge(cursoidcursoNew);
            }
            if (facultadCumCarreraOld != null && !facultadCumCarreraOld.equals(facultadCumCarreraNew)) {
                facultadCumCarreraOld.getMatriculaList().remove(matricula);
                facultadCumCarreraOld = em.merge(facultadCumCarreraOld);
            }
            if (facultadCumCarreraNew != null && !facultadCumCarreraNew.equals(facultadCumCarreraOld)) {
                facultadCumCarreraNew.getMatriculaList().add(matricula);
                facultadCumCarreraNew = em.merge(facultadCumCarreraNew);
            }
            for (MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura : matriculaFacultadCumCarreraEstudianteAsignaturaListNew) {
                if (!matriculaFacultadCumCarreraEstudianteAsignaturaListOld.contains(matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura)) {
                    Matricula oldMatriculaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura = matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura.getMatricula();
                    matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura.setMatricula(matricula);
                    matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura);
                    if (oldMatriculaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura != null && !oldMatriculaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura.equals(matricula)) {
                        oldMatriculaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura);
                        oldMatriculaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(oldMatriculaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = matricula.getMatriculaId();
                if (findMatricula(id) == null) {
                    throw new NonexistentEntityException("The matricula with id " + id + " no longer exists.");
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
            Matricula matricula;
            try {
                matricula = em.getReference(Matricula.class, id);
                matricula.getMatriculaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The matricula with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MatriculaFacultadCumCarreraEstudianteAsignatura> matriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheck = matricula.getMatriculaFacultadCumCarreraEstudianteAsignaturaList();
            for (MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheckMatriculaFacultadCumCarreraEstudianteAsignatura : matriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Matricula (" + matricula + ") cannot be destroyed since the MatriculaFacultadCumCarreraEstudianteAsignatura " + matriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheckMatriculaFacultadCumCarreraEstudianteAsignatura + " in its matriculaFacultadCumCarreraEstudianteAsignaturaList field has a non-nullable matricula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Curso cursoidcurso = matricula.getCursoidcurso();
            if (cursoidcurso != null) {
                cursoidcurso.getMatriculaList().remove(matricula);
                cursoidcurso = em.merge(cursoidcurso);
            }
            FacultadCumCarrera facultadCumCarrera = matricula.getFacultadCumCarrera();
            if (facultadCumCarrera != null) {
                facultadCumCarrera.getMatriculaList().remove(matricula);
                facultadCumCarrera = em.merge(facultadCumCarrera);
            }
            em.remove(matricula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Matricula> findMatriculaEntities() {
        return findMatriculaEntities(true, -1, -1);
    }

    public List<Matricula> findMatriculaEntities(int maxResults, int firstResult) {
        return findMatriculaEntities(false, maxResults, firstResult);
    }

    private List<Matricula> findMatriculaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Matricula.class));
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

    public Matricula findMatricula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Matricula.class, id);
        } finally {
            em.close();
        }
    }

    public int getMatriculaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Matricula> rt = cq.from(Matricula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Matricula finMatriculaByFechaInicioCarrera(Date inicio, int idcarrera, String curso, String facultad, String cum) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m FROM Matricula m WHERE m.facultadCumCarrera.facultadCumCarreraPK.carreraidcarrera =:idcarrera AND m.facultadCumCarrera.facultadCumCarreraPK.facultadCumfacultadcodigoarea=:facultad AND m.facultadCumCarrera.facultadCumCarreraPK.facultadCumcumcodigocum=:cum AND m.cursoidcurso.idcurso =:curso AND m.matriculaFechaInicio= :inicio");
            q.setParameter("idcarrera", idcarrera);
            q.setParameter("cum", cum);
            q.setParameter("facultad", facultad);
            q.setParameter("inicio", inicio);
            q.setParameter("curso", curso);
            Matricula p = (Matricula) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Matricula finMatriculaByFechaInicioCarreraAvailable(Date inicio, int idcarrera, String curso, String facultad, String cum) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m FROM Matricula m WHERE m.matriculaCancelada=false AND m.facultadCumCarrera.facultadCumCarreraPK.carreraidcarrera =:idcarrera AND m.facultadCumCarrera.facultadCumCarreraPK.facultadCumfacultadcodigoarea=:facultad AND m.facultadCumCarrera.facultadCumCarreraPK.facultadCumcumcodigocum=:cum AND m.cursoidcurso=:curso AND m.matriculaFechaInicio=:inicio");
            q.setParameter("idcarrera", idcarrera);
            q.setParameter("cum", cum);
            q.setParameter("facultad", facultad);
            q.setParameter("inicio", inicio);
            q.setParameter("curso", curso);
            Matricula p = (Matricula) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Matricula finMatriculaByFechaInicioCarreraAvailableCerrada(Date inicio, int idcarrera, String curso, String facultad, String cum) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m FROM Matricula m WHERE m.matriculaCancelada=false AND m.cerrada=FALSE AND m.facultadCumCarrera.facultadCumCarreraPK.carreraidcarrera =:idcarrera AND m.facultadCumCarrera.facultadCumCarreraPK.facultadCumfacultadcodigoarea=:facultad AND m.facultadCumCarrera.facultadCumCarreraPK.facultadCumcumcodigocum=:cum AND m.cursoidcurso=:curso AND m.matriculaFechaInicio :inicio");
            q.setParameter("idcarrera", idcarrera);
            q.setParameter("cum", cum);
            q.setParameter("facultad", facultad);
            q.setParameter("inicio", inicio);
            q.setParameter("curso", curso);
            Matricula p = (Matricula) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Date> findMatriculaAvailableByCarrera(String facultad, int carrera, String curso, String cum) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m.matriculaFechaInicio FROM Matricula m WHERE m.matriculaCancelada=false AND m.facultadCumCarrera.facultadCumCarreraPK.carreraidcarrera =:carrera AND m.facultadCumCarrera.facultadCumCarreraPK.facultadCumfacultadcodigoarea=:facultad AND m.facultadCumCarrera.facultadCumCarreraPK.facultadCumcumcodigocum=:cum AND m.cursoidcurso.idcurso =:curso ORDER BY m.matriculaFechaInicio");
            q.setParameter("carrera", carrera);
            q.setParameter("facultad", facultad);
            q.setParameter("cum", cum);
            q.setParameter("curso", curso);
            List<Date> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
//    public List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> findMatriculaAvailableByCarreraForNotas(Date inicio, String facultad, int carrera, String cum, int asignatura, int examen) {
//        EntityManager em = getEntityManager();
//        try {
//            Query q = em.createQuery("SELECT e FROM ExamenMatriculaFacultadCumCarreraEstudianteAsignatura e WHERE e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.asignaturaId=:asignatura AND e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.codigoarea=:area AND e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.codigocum=:cum AND e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.examenexamenId=:examen AND e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.fechaMatricula=:fechamatricula AND e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.idcarrera=:carrera AND e.matriculaFacultadCumCarreraEstudianteAsignatura.facultadCumCarreraEstudianteAsignatura.facultadCumCarreraEstudiante.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre='Activo' AND e.matriculaFacultadCumCarreraEstudianteAsignatura.actual=true AND e.matriculaFacultadCumCarreraEstudianteAsignatura.cancelada=false");
////            Query q = em.createQuery("SELECT e FROM MatriculaFacultadCumCarreraEstudianteAsignatura m JOIN FETCH m.examenMatriculaFacultadCumCarreraEstudianteAsignaturaList e WHERE e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.examenexamenId=:examen AND m.matriculaFacultadCumCarreraEstudianteAsignaturaPK.asignaturaId=:asignatura AND m.matricula.matriculaCancelada=false AND m.actual=true AND m.cancelada=false AND m.matricula.matriculaFechaInicio=:inicio AND m.matricula.facultadCumCarrera.facultadCumCarreraPK.carreraidcarrera =:carrera AND m.matricula.facultadCumCarrera.facultadCumCarreraPK.facultadCumfacultadcodigoarea=:facultad AND m.matricula.facultadCumCarrera.facultadCumCarreraPK.facultadCumcumcodigocum=:cum");
//            q.setParameter("carrera", carrera);
//            q.setParameter("area", facultad);
//            q.setParameter("cum", cum);
//            q.setParameter("fechamatricula", inicio);
//            q.setParameter("asignatura", asignatura);
//            q.setParameter("examen", examen);
//            List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> p = q.getResultList();
//            return p;
//        } catch (Exception e) {
//            return null;
//        } finally {
//            em.close();
//        }
//    }

//    public Matricula findMatriculaAvailableByCarreraCurso(String facultad, String carrera, String curso) {
//        EntityManager em = getEntityManager();
//        try {
//            Query q = em.createQuery("SELECT m "
//                    + "FROM Matricula m "
//                    + "WHERE m.facultadCumCarrera.facultadCumCarreraPK.carreraidcarrera =:carrera AND "
//                    + "m.carreraidcarrera.facultadcodigoarea.nombrearea=:facultad AND "
//                    + "m.cursoidcurso.idcurso=:curso AND "
//                    + "m.matriculaCancelada=false "
//                    + "ORDER BY m.matriculaFechaInicio");
//            q.setParameter("carrera", carrera);
//            q.setParameter("facultad", facultad);
//            q.setParameter("curso", curso);
//            Matricula p = (Matricula) q.getSingleResult();
//            return p;
//        } catch (Exception e) {
//            return null;
//        } finally {
//            em.close();
//        }
//    }
    public Matricula findMatriculaAvailableByCarreraCurso(FacultadCumCarrera facultadCumCarrera, String idcurso) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m FROM Matricula m WHERE m.facultadCumCarrera.carrera.carreranacionalidcarreranacional.nombrecarreranacional= :carrera AND m.facultadCumCarrera.facultadCum.facultad.codigoarea =:facultad AND m.facultadCumCarrera.facultadCum.cum.codigocum= :cum AND m.cursoidcurso.idcurso =:curso AND m.matriculaCancelada=false ORDER BY m.matriculaFechaInicio");
            q.setParameter("carrera", facultadCumCarrera.getCarrera().getCarreranacionalidcarreranacional().getNombrecarreranacional());
            q.setParameter("facultad", facultadCumCarrera.getFacultadCum().getFacultad().getCodigoarea());
            q.setParameter("cum", facultadCumCarrera.getFacultadCum().getCum().getCodigocum());
            q.setParameter("curso", idcurso);
            Matricula p = (Matricula) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<MatriculaFacultadCumCarreraEstudianteAsignatura> findMatriculasAvailablesByEstudiante(FacultadCumCarreraEstudiante fcce) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m FROM MatriculaFacultadCumCarreraEstudianteAsignatura m WHERE m.actual=false AND m.cancelada=false and m.facultadCumCarreraEstudianteAsignatura.facultadCumCarreraEstudiante=:fcce");
            q.setParameter("fcce", fcce);
            List<MatriculaFacultadCumCarreraEstudianteAsignatura> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<MatriculaFacultadCumCarreraEstudianteAsignatura> findMatriculasAvailablesByEstudianteNoActual(FacultadCumCarreraEstudiante fcce) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m FROM MatriculaFacultadCumCarreraEstudianteAsignatura m WHERE m.cancelada=false and m.facultadCumCarreraEstudianteAsignatura.facultadCumCarreraEstudiante=:fcce");
            q.setParameter("fcce", fcce);
            List<MatriculaFacultadCumCarreraEstudianteAsignatura> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Date> findFechaFinMatriculaAvailableByCarrera(String codigoarea, Integer idcarrera, String codigocum) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m.matriculaFechaFin FROM Matricula m WHERE m.matriculaCancelada=false AND m.facultadCumCarrera.facultadCumCarreraPK.carreraidcarrera=:carrera AND m.facultadCumCarrera.facultadCumCarreraPK.facultadCumcumcodigocum=:cum AND m.facultadCumCarrera.facultadCumCarreraPK.facultadCumfacultadcodigoarea=:facultad ORDER BY m.matriculaFechaFin DESC");
            q.setParameter("facultad",codigoarea);
            q.setParameter("cum", codigocum);
            q.setParameter("carrera",idcarrera);
            List<Date> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
