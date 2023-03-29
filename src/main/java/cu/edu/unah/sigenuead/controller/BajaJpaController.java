/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.controller.exceptions.PreexistingEntityException;
import cu.edu.unah.sigenuead.entity.Baja;
import cu.edu.unah.sigenuead.entity.BajaPK;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.MotivoBaja;
import cu.edu.unah.sigenuead.entity.TipoBaja;
import java.util.Date;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class BajaJpaController implements Serializable {

    public BajaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Baja baja) throws PreexistingEntityException, Exception {
        if (baja.getBajaPK() == null) {
            baja.setBajaPK(new BajaPK());
        }
        baja.getBajaPK().setTipoBajaidTipoBaja(baja.getTipoBaja().getIdTipoBaja());
        baja.getBajaPK().setMotivoBajaidMotivoBaja(baja.getMotivoBaja().getIdMotivoBaja());
        baja.getBajaPK().setCodigocum(baja.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum());
        baja.getBajaPK().setIdcarrera(baja.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera());
        baja.getBajaPK().setFechaMatricula(baja.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFechaMatricula());
        baja.getBajaPK().setCursoidcurso(baja.getCurso().getIdcurso());
        baja.getBajaPK().setCodigoarea(baja.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea());
        baja.getBajaPK().setEstudianteId(baja.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso curso = baja.getCurso();
            if (curso != null) {
                curso = em.getReference(curso.getClass(), curso.getIdcurso());
                baja.setCurso(curso);
            }
            FacultadCumCarreraEstudiante facultadCumCarreraEstudiante = baja.getFacultadCumCarreraEstudiante();
            if (facultadCumCarreraEstudiante != null) {
                facultadCumCarreraEstudiante = em.getReference(facultadCumCarreraEstudiante.getClass(), facultadCumCarreraEstudiante.getFacultadCumCarreraEstudiantePK());
                baja.setFacultadCumCarreraEstudiante(facultadCumCarreraEstudiante);
            }
            MotivoBaja motivoBaja = baja.getMotivoBaja();
            if (motivoBaja != null) {
                motivoBaja = em.getReference(motivoBaja.getClass(), motivoBaja.getIdMotivoBaja());
                baja.setMotivoBaja(motivoBaja);
            }
            TipoBaja tipoBaja = baja.getTipoBaja();
            if (tipoBaja != null) {
                tipoBaja = em.getReference(tipoBaja.getClass(), tipoBaja.getIdTipoBaja());
                baja.setTipoBaja(tipoBaja);
            }
            em.persist(baja);
            if (curso != null) {
                curso.getBajaList().add(baja);
                curso = em.merge(curso);
            }
            if (facultadCumCarreraEstudiante != null) {
                facultadCumCarreraEstudiante.getBajaList().add(baja);
                facultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudiante);
            }
            if (motivoBaja != null) {
                motivoBaja.getBajaList().add(baja);
                motivoBaja = em.merge(motivoBaja);
            }
            if (tipoBaja != null) {
                tipoBaja.getBajaList().add(baja);
                tipoBaja = em.merge(tipoBaja);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBaja(baja.getBajaPK()) != null) {
                throw new PreexistingEntityException("Baja " + baja + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Baja baja) throws NonexistentEntityException, Exception {
        baja.getBajaPK().setTipoBajaidTipoBaja(baja.getTipoBaja().getIdTipoBaja());
        baja.getBajaPK().setMotivoBajaidMotivoBaja(baja.getMotivoBaja().getIdMotivoBaja());
        baja.getBajaPK().setCodigocum(baja.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum());
        baja.getBajaPK().setIdcarrera(baja.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera());
        baja.getBajaPK().setFechaMatricula(baja.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFechaMatricula());
        baja.getBajaPK().setCursoidcurso(baja.getCurso().getIdcurso());
        baja.getBajaPK().setCodigoarea(baja.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea());
        baja.getBajaPK().setEstudianteId(baja.getFacultadCumCarreraEstudiante().getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Baja persistentBaja = em.find(Baja.class, baja.getBajaPK());
            Curso cursoOld = persistentBaja.getCurso();
            Curso cursoNew = baja.getCurso();
            FacultadCumCarreraEstudiante facultadCumCarreraEstudianteOld = persistentBaja.getFacultadCumCarreraEstudiante();
            FacultadCumCarreraEstudiante facultadCumCarreraEstudianteNew = baja.getFacultadCumCarreraEstudiante();
            MotivoBaja motivoBajaOld = persistentBaja.getMotivoBaja();
            MotivoBaja motivoBajaNew = baja.getMotivoBaja();
            TipoBaja tipoBajaOld = persistentBaja.getTipoBaja();
            TipoBaja tipoBajaNew = baja.getTipoBaja();
            if (cursoNew != null) {
                cursoNew = em.getReference(cursoNew.getClass(), cursoNew.getIdcurso());
                baja.setCurso(cursoNew);
            }
            if (facultadCumCarreraEstudianteNew != null) {
                facultadCumCarreraEstudianteNew = em.getReference(facultadCumCarreraEstudianteNew.getClass(), facultadCumCarreraEstudianteNew.getFacultadCumCarreraEstudiantePK());
                baja.setFacultadCumCarreraEstudiante(facultadCumCarreraEstudianteNew);
            }
            if (motivoBajaNew != null) {
                motivoBajaNew = em.getReference(motivoBajaNew.getClass(), motivoBajaNew.getIdMotivoBaja());
                baja.setMotivoBaja(motivoBajaNew);
            }
            if (tipoBajaNew != null) {
                tipoBajaNew = em.getReference(tipoBajaNew.getClass(), tipoBajaNew.getIdTipoBaja());
                baja.setTipoBaja(tipoBajaNew);
            }
            baja = em.merge(baja);
            if (cursoOld != null && !cursoOld.equals(cursoNew)) {
                cursoOld.getBajaList().remove(baja);
                cursoOld = em.merge(cursoOld);
            }
            if (cursoNew != null && !cursoNew.equals(cursoOld)) {
                cursoNew.getBajaList().add(baja);
                cursoNew = em.merge(cursoNew);
            }
            if (facultadCumCarreraEstudianteOld != null && !facultadCumCarreraEstudianteOld.equals(facultadCumCarreraEstudianteNew)) {
                facultadCumCarreraEstudianteOld.getBajaList().remove(baja);
                facultadCumCarreraEstudianteOld = em.merge(facultadCumCarreraEstudianteOld);
            }
            if (facultadCumCarreraEstudianteNew != null && !facultadCumCarreraEstudianteNew.equals(facultadCumCarreraEstudianteOld)) {
                facultadCumCarreraEstudianteNew.getBajaList().add(baja);
                facultadCumCarreraEstudianteNew = em.merge(facultadCumCarreraEstudianteNew);
            }
            if (motivoBajaOld != null && !motivoBajaOld.equals(motivoBajaNew)) {
                motivoBajaOld.getBajaList().remove(baja);
                motivoBajaOld = em.merge(motivoBajaOld);
            }
            if (motivoBajaNew != null && !motivoBajaNew.equals(motivoBajaOld)) {
                motivoBajaNew.getBajaList().add(baja);
                motivoBajaNew = em.merge(motivoBajaNew);
            }
            if (tipoBajaOld != null && !tipoBajaOld.equals(tipoBajaNew)) {
                tipoBajaOld.getBajaList().remove(baja);
                tipoBajaOld = em.merge(tipoBajaOld);
            }
            if (tipoBajaNew != null && !tipoBajaNew.equals(tipoBajaOld)) {
                tipoBajaNew.getBajaList().add(baja);
                tipoBajaNew = em.merge(tipoBajaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BajaPK id = baja.getBajaPK();
                if (findBaja(id) == null) {
                    throw new NonexistentEntityException("The baja with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BajaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Baja baja;
            try {
                baja = em.getReference(Baja.class, id);
                baja.getBajaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baja with id " + id + " no longer exists.", enfe);
            }
            Curso curso = baja.getCurso();
            if (curso != null) {
                curso.getBajaList().remove(baja);
                curso = em.merge(curso);
            }
            FacultadCumCarreraEstudiante facultadCumCarreraEstudiante = baja.getFacultadCumCarreraEstudiante();
            if (facultadCumCarreraEstudiante != null) {
                facultadCumCarreraEstudiante.getBajaList().remove(baja);
                facultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudiante);
            }
            MotivoBaja motivoBaja = baja.getMotivoBaja();
            if (motivoBaja != null) {
                motivoBaja.getBajaList().remove(baja);
                motivoBaja = em.merge(motivoBaja);
            }
            TipoBaja tipoBaja = baja.getTipoBaja();
            if (tipoBaja != null) {
                tipoBaja.getBajaList().remove(baja);
                tipoBaja = em.merge(tipoBaja);
            }
            em.remove(baja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Baja> findBajaEntities() {
        return findBajaEntities(true, -1, -1);
    }

    public List<Baja> findBajaEntities(int maxResults, int firstResult) {
        return findBajaEntities(false, maxResults, firstResult);
    }

    private List<Baja> findBajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Baja.class));
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

    public Baja findBaja(BajaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Baja.class, id);
        } finally {
            em.close();
        }
    }

    public int getBajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Baja> rt = cq.from(Baja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Baja findBaja(String idEstudiante, String curso, String facultadCumCarrerafacultadCumcumcodigocum, int facultadCumCarreracarreraidcarrera, String facultadCumCarrerafacultadCumfacultadcodigoarea, Date fechaMatricula, Date fecha, int motivo, int tipo) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT b FROM Baja b WHERE b.bajaPK.codigoarea=:facultad AND b.bajaPK.codigocum=:cum AND b.bajaPK.cursoidcurso=:curso AND b.bajaPK.estudianteId=:estudiante AND b.bajaPK.fecha=:fecha AND b.bajaPK.fechaMatricula=:fechamatricula AND b.bajaPK.idcarrera=:carrera AND b.bajaPK.motivoBajaidMotivoBaja=:motivo AND b.bajaPK.tipoBajaidTipoBaja=:tipo");
            q.setParameter("facultad", facultadCumCarrerafacultadCumfacultadcodigoarea);
            q.setParameter("cum", facultadCumCarrerafacultadCumcumcodigocum);
            q.setParameter("carrera", facultadCumCarreracarreraidcarrera);
            q.setParameter("curso", curso);
            q.setParameter("estudiante", idEstudiante);
            q.setParameter("fecha", fecha);
            q.setParameter("fechamatricula", fechaMatricula);
            q.setParameter("motivo", motivo);
            q.setParameter("tipo", tipo);
            Baja p = (Baja) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Baja findBajaByUltimaFecha(FacultadCumCarreraEstudiante fcce) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT b FROM Baja b WHERE b.bajaPK.codigoarea=:facultad AND b.bajaPK.codigocum=:cum AND b.bajaPK.estudianteId=:estudiante AND b.bajaPK.fechaMatricula=:fecha AND b.bajaPK.idcarrera=:carrera AND b.bajaCancelada=false ORDER BY b.bajaPK.fecha DESC");
            q.setParameter("facultad", fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea());
            q.setParameter("cum", fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum());
            q.setParameter("estudiante", fcce.getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId());
            q.setParameter("fecha", fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula());
            q.setParameter("carrera", fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera());
            List<Baja> p = q.getResultList();
            if (p.size() == 0) {
                return null;
            }
            return p.get(0);
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

}
