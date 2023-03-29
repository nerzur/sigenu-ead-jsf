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
import cu.edu.unah.sigenuead.entity.Asignatura;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudianteAsignaturaPK;
import cu.edu.unah.sigenuead.entity.MatriculaFacultadCumCarreraEstudianteAsignatura;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class FacultadCumCarreraEstudianteAsignaturaJpaController implements Serializable {

    public FacultadCumCarreraEstudianteAsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignatura) throws PreexistingEntityException, Exception {
        if (facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK() == null) {
            facultadCumCarreraEstudianteAsignatura.setFacultadCumCarreraEstudianteAsignaturaPK(new FacultadCumCarreraEstudianteAsignaturaPK());
        }
        if (facultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaList() == null) {
            facultadCumCarreraEstudianteAsignatura.setMatriculaFacultadCumCarreraEstudianteAsignaturaList(new ArrayList<MatriculaFacultadCumCarreraEstudianteAsignatura>());
        }
        facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK().setEstudianteId(facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId());
        facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK().setCodigoarea(facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea());
        facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK().setFechaMatricula(facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFechaMatricula());
        facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK().setAsignaturaId(facultadCumCarreraEstudianteAsignatura.getAsignatura().getAsignaturaId());
        facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK().setIdcarrera(facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera());
        facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK().setCodigocum(facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura asignatura = facultadCumCarreraEstudianteAsignatura.getAsignatura();
            if (asignatura != null) {
                asignatura = em.getReference(asignatura.getClass(), asignatura.getAsignaturaId());
                facultadCumCarreraEstudianteAsignatura.setAsignatura(asignatura);
            }
            FacultadCumCarreraEstudiante facultadCumCarreraEstudiante = facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante();
            if (facultadCumCarreraEstudiante != null) {
                facultadCumCarreraEstudiante = em.getReference(facultadCumCarreraEstudiante.getClass(), facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK());
                facultadCumCarreraEstudianteAsignatura.setFacultadCumCarreraEstudiante(facultadCumCarreraEstudiante);
            }
            List<MatriculaFacultadCumCarreraEstudianteAsignatura> attachedMatriculaFacultadCumCarreraEstudianteAsignaturaList = new ArrayList<MatriculaFacultadCumCarreraEstudianteAsignatura>();
            for (MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach : facultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaList()) {
                matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach = em.getReference(matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getClass(), matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK());
                attachedMatriculaFacultadCumCarreraEstudianteAsignaturaList.add(matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach);
            }
            facultadCumCarreraEstudianteAsignatura.setMatriculaFacultadCumCarreraEstudianteAsignaturaList(attachedMatriculaFacultadCumCarreraEstudianteAsignaturaList);
            em.persist(facultadCumCarreraEstudianteAsignatura);
            if (asignatura != null) {
                asignatura.getFacultadCumCarreraEstudianteAsignaturaList().add(facultadCumCarreraEstudianteAsignatura);
                asignatura = em.merge(asignatura);
            }
            if (facultadCumCarreraEstudiante != null) {
                facultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteAsignaturaList().add(facultadCumCarreraEstudianteAsignatura);
                facultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudiante);
            }
            for (MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura : facultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaList()) {
                FacultadCumCarreraEstudianteAsignatura oldFacultadCumCarreraEstudianteAsignaturaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura = matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura();
                matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura.setFacultadCumCarreraEstudianteAsignatura(facultadCumCarreraEstudianteAsignatura);
                matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura);
                if (oldFacultadCumCarreraEstudianteAsignaturaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura != null) {
                    oldFacultadCumCarreraEstudianteAsignaturaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(matriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura);
                    oldFacultadCumCarreraEstudianteAsignaturaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(oldFacultadCumCarreraEstudianteAsignaturaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListMatriculaFacultadCumCarreraEstudianteAsignatura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFacultadCumCarreraEstudianteAsignatura(facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK()) != null) {
                throw new PreexistingEntityException("FacultadCumCarreraEstudianteAsignatura " + facultadCumCarreraEstudianteAsignatura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignatura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK().setEstudianteId(facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId());
        facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK().setCodigoarea(facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea());
        facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK().setFechaMatricula(facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFechaMatricula());
        facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK().setAsignaturaId(facultadCumCarreraEstudianteAsignatura.getAsignatura().getAsignaturaId());
        facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK().setIdcarrera(facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera());
        facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK().setCodigocum(facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacultadCumCarreraEstudianteAsignatura persistentFacultadCumCarreraEstudianteAsignatura = em.find(FacultadCumCarreraEstudianteAsignatura.class, facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK());
            Asignatura asignaturaOld = persistentFacultadCumCarreraEstudianteAsignatura.getAsignatura();
            Asignatura asignaturaNew = facultadCumCarreraEstudianteAsignatura.getAsignatura();
            FacultadCumCarreraEstudiante facultadCumCarreraEstudianteOld = persistentFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante();
            FacultadCumCarreraEstudiante facultadCumCarreraEstudianteNew = facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante();
            List<MatriculaFacultadCumCarreraEstudianteAsignatura> matriculaFacultadCumCarreraEstudianteAsignaturaListOld = persistentFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaList();
            List<MatriculaFacultadCumCarreraEstudianteAsignatura> matriculaFacultadCumCarreraEstudianteAsignaturaListNew = facultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaList();
            List<String> illegalOrphanMessages = null;
            for (MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaListOldMatriculaFacultadCumCarreraEstudianteAsignatura : matriculaFacultadCumCarreraEstudianteAsignaturaListOld) {
                if (!matriculaFacultadCumCarreraEstudianteAsignaturaListNew.contains(matriculaFacultadCumCarreraEstudianteAsignaturaListOldMatriculaFacultadCumCarreraEstudianteAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MatriculaFacultadCumCarreraEstudianteAsignatura " + matriculaFacultadCumCarreraEstudianteAsignaturaListOldMatriculaFacultadCumCarreraEstudianteAsignatura + " since its facultadCumCarreraEstudianteAsignatura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (asignaturaNew != null) {
                asignaturaNew = em.getReference(asignaturaNew.getClass(), asignaturaNew.getAsignaturaId());
                facultadCumCarreraEstudianteAsignatura.setAsignatura(asignaturaNew);
            }
            if (facultadCumCarreraEstudianteNew != null) {
                facultadCumCarreraEstudianteNew = em.getReference(facultadCumCarreraEstudianteNew.getClass(), facultadCumCarreraEstudianteNew.getFacultadCumCarreraEstudiantePK());
                facultadCumCarreraEstudianteAsignatura.setFacultadCumCarreraEstudiante(facultadCumCarreraEstudianteNew);
            }
            List<MatriculaFacultadCumCarreraEstudianteAsignatura> attachedMatriculaFacultadCumCarreraEstudianteAsignaturaListNew = new ArrayList<MatriculaFacultadCumCarreraEstudianteAsignatura>();
            for (MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach : matriculaFacultadCumCarreraEstudianteAsignaturaListNew) {
                matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach = em.getReference(matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getClass(), matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach.getMatriculaFacultadCumCarreraEstudianteAsignaturaPK());
                attachedMatriculaFacultadCumCarreraEstudianteAsignaturaListNew.add(matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignaturaToAttach);
            }
            matriculaFacultadCumCarreraEstudianteAsignaturaListNew = attachedMatriculaFacultadCumCarreraEstudianteAsignaturaListNew;
            facultadCumCarreraEstudianteAsignatura.setMatriculaFacultadCumCarreraEstudianteAsignaturaList(matriculaFacultadCumCarreraEstudianteAsignaturaListNew);
            facultadCumCarreraEstudianteAsignatura = em.merge(facultadCumCarreraEstudianteAsignatura);
            if (asignaturaOld != null && !asignaturaOld.equals(asignaturaNew)) {
                asignaturaOld.getFacultadCumCarreraEstudianteAsignaturaList().remove(facultadCumCarreraEstudianteAsignatura);
                asignaturaOld = em.merge(asignaturaOld);
            }
            if (asignaturaNew != null && !asignaturaNew.equals(asignaturaOld)) {
                asignaturaNew.getFacultadCumCarreraEstudianteAsignaturaList().add(facultadCumCarreraEstudianteAsignatura);
                asignaturaNew = em.merge(asignaturaNew);
            }
            if (facultadCumCarreraEstudianteOld != null && !facultadCumCarreraEstudianteOld.equals(facultadCumCarreraEstudianteNew)) {
                facultadCumCarreraEstudianteOld.getFacultadCumCarreraEstudianteAsignaturaList().remove(facultadCumCarreraEstudianteAsignatura);
                facultadCumCarreraEstudianteOld = em.merge(facultadCumCarreraEstudianteOld);
            }
            if (facultadCumCarreraEstudianteNew != null && !facultadCumCarreraEstudianteNew.equals(facultadCumCarreraEstudianteOld)) {
                facultadCumCarreraEstudianteNew.getFacultadCumCarreraEstudianteAsignaturaList().add(facultadCumCarreraEstudianteAsignatura);
                facultadCumCarreraEstudianteNew = em.merge(facultadCumCarreraEstudianteNew);
            }
            for (MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura : matriculaFacultadCumCarreraEstudianteAsignaturaListNew) {
                if (!matriculaFacultadCumCarreraEstudianteAsignaturaListOld.contains(matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura)) {
                    FacultadCumCarreraEstudianteAsignatura oldFacultadCumCarreraEstudianteAsignaturaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura = matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignatura();
                    matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura.setFacultadCumCarreraEstudianteAsignatura(facultadCumCarreraEstudianteAsignatura);
                    matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura);
                    if (oldFacultadCumCarreraEstudianteAsignaturaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura != null && !oldFacultadCumCarreraEstudianteAsignaturaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura.equals(facultadCumCarreraEstudianteAsignatura)) {
                        oldFacultadCumCarreraEstudianteAsignaturaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaList().remove(matriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura);
                        oldFacultadCumCarreraEstudianteAsignaturaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura = em.merge(oldFacultadCumCarreraEstudianteAsignaturaOfMatriculaFacultadCumCarreraEstudianteAsignaturaListNewMatriculaFacultadCumCarreraEstudianteAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                FacultadCumCarreraEstudianteAsignaturaPK id = facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK();
                if (findFacultadCumCarreraEstudianteAsignatura(id) == null) {
                    throw new NonexistentEntityException("The facultadCumCarreraEstudianteAsignatura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(FacultadCumCarreraEstudianteAsignaturaPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignatura;
            try {
                facultadCumCarreraEstudianteAsignatura = em.getReference(FacultadCumCarreraEstudianteAsignatura.class, id);
                facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudianteAsignaturaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facultadCumCarreraEstudianteAsignatura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MatriculaFacultadCumCarreraEstudianteAsignatura> matriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheck = facultadCumCarreraEstudianteAsignatura.getMatriculaFacultadCumCarreraEstudianteAsignaturaList();
            for (MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheckMatriculaFacultadCumCarreraEstudianteAsignatura : matriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FacultadCumCarreraEstudianteAsignatura (" + facultadCumCarreraEstudianteAsignatura + ") cannot be destroyed since the MatriculaFacultadCumCarreraEstudianteAsignatura " + matriculaFacultadCumCarreraEstudianteAsignaturaListOrphanCheckMatriculaFacultadCumCarreraEstudianteAsignatura + " in its matriculaFacultadCumCarreraEstudianteAsignaturaList field has a non-nullable facultadCumCarreraEstudianteAsignatura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Asignatura asignatura = facultadCumCarreraEstudianteAsignatura.getAsignatura();
            if (asignatura != null) {
                asignatura.getFacultadCumCarreraEstudianteAsignaturaList().remove(facultadCumCarreraEstudianteAsignatura);
                asignatura = em.merge(asignatura);
            }
            FacultadCumCarreraEstudiante facultadCumCarreraEstudiante = facultadCumCarreraEstudianteAsignatura.getFacultadCumCarreraEstudiante();
            if (facultadCumCarreraEstudiante != null) {
                facultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteAsignaturaList().remove(facultadCumCarreraEstudianteAsignatura);
                facultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudiante);
            }
            em.remove(facultadCumCarreraEstudianteAsignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FacultadCumCarreraEstudianteAsignatura> findFacultadCumCarreraEstudianteAsignaturaEntities() {
        return findFacultadCumCarreraEstudianteAsignaturaEntities(true, -1, -1);
    }

    public List<FacultadCumCarreraEstudianteAsignatura> findFacultadCumCarreraEstudianteAsignaturaEntities(int maxResults, int firstResult) {
        return findFacultadCumCarreraEstudianteAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<FacultadCumCarreraEstudianteAsignatura> findFacultadCumCarreraEstudianteAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FacultadCumCarreraEstudianteAsignatura.class));
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

    public FacultadCumCarreraEstudianteAsignatura findFacultadCumCarreraEstudianteAsignatura(FacultadCumCarreraEstudianteAsignaturaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FacultadCumCarreraEstudianteAsignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacultadCumCarreraEstudianteAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FacultadCumCarreraEstudianteAsignatura> rt = cq.from(FacultadCumCarreraEstudianteAsignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<MatriculaFacultadCumCarreraEstudianteAsignatura> findFacultadCumCarreraEstudianteAsignaturaCantMatriculasAvailables(FacultadCumCarreraEstudianteAsignatura fccea) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT m FROM MatriculaFacultadCumCarreraEstudianteAsignatura m WHERE m.cancelada=false AND m.facultadCumCarreraEstudianteAsignatura.facultadCumCarreraEstudianteAsignaturaPK.idcarrera =:carrera AND m.matriculaFacultadCumCarreraEstudianteAsignaturaPK.codigocum =:cum AND m.matriculaFacultadCumCarreraEstudianteAsignaturaPK.codigoarea =:facultad AND m.matriculaFacultadCumCarreraEstudianteAsignaturaPK.asignaturaId=:asignatura AND m.matriculaFacultadCumCarreraEstudianteAsignaturaPK.estudianteId=:estudiante");
            q.setParameter("facultad",fccea.getFacultadCumCarreraEstudianteAsignaturaPK().getCodigoarea());
            q.setParameter("cum", fccea.getFacultadCumCarreraEstudianteAsignaturaPK().getCodigocum());
            q.setParameter("carrera",fccea.getFacultadCumCarreraEstudianteAsignaturaPK().getIdcarrera());
            q.setParameter("asignatura", fccea.getFacultadCumCarreraEstudianteAsignaturaPK().getAsignaturaId());
            q.setParameter("estudiante",fccea.getFacultadCumCarreraEstudianteAsignaturaPK().getEstudianteId());
            List<MatriculaFacultadCumCarreraEstudianteAsignatura> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
