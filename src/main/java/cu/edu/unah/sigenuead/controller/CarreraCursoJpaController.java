/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Carrera;
import cu.edu.unah.sigenuead.entity.CarreraCurso;
import cu.edu.unah.sigenuead.entity.CarreraCursoPK;
import cu.edu.unah.sigenuead.entity.Curso;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class CarreraCursoJpaController implements Serializable {

    public CarreraCursoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CarreraCurso carreraCurso) throws PreexistingEntityException, Exception {
        if (carreraCurso.getCarreraCursoPK() == null) {
            carreraCurso.setCarreraCursoPK(new CarreraCursoPK());
        }
        carreraCurso.getCarreraCursoPK().setCarreraidcarrera(carreraCurso.getCarrera().getIdcarrera());
        carreraCurso.getCarreraCursoPK().setCursoidcurso(carreraCurso.getCurso().getIdcurso());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrera carrera = carreraCurso.getCarrera();
            if (carrera != null) {
                carrera = em.getReference(carrera.getClass(), carrera.getIdcarrera());
                carreraCurso.setCarrera(carrera);
            }
            Curso curso = carreraCurso.getCurso();
            if (curso != null) {
                curso = em.getReference(curso.getClass(), curso.getIdcurso());
                carreraCurso.setCurso(curso);
            }
            em.persist(carreraCurso);
            if (carrera != null) {
                carrera.getCarreraCursoList().add(carreraCurso);
                carrera = em.merge(carrera);
            }
            if (curso != null) {
                curso.getCarreraCursoList().add(carreraCurso);
                curso = em.merge(curso);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCarreraCurso(carreraCurso.getCarreraCursoPK()) != null) {
                throw new PreexistingEntityException("CarreraCurso " + carreraCurso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CarreraCurso carreraCurso) throws NonexistentEntityException, Exception {
        carreraCurso.getCarreraCursoPK().setCarreraidcarrera(carreraCurso.getCarrera().getIdcarrera());
        carreraCurso.getCarreraCursoPK().setCursoidcurso(carreraCurso.getCurso().getIdcurso());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CarreraCurso persistentCarreraCurso = em.find(CarreraCurso.class, carreraCurso.getCarreraCursoPK());
            Carrera carreraOld = persistentCarreraCurso.getCarrera();
            Carrera carreraNew = carreraCurso.getCarrera();
            Curso cursoOld = persistentCarreraCurso.getCurso();
            Curso cursoNew = carreraCurso.getCurso();
            if (carreraNew != null) {
                carreraNew = em.getReference(carreraNew.getClass(), carreraNew.getIdcarrera());
                carreraCurso.setCarrera(carreraNew);
            }
            if (cursoNew != null) {
                cursoNew = em.getReference(cursoNew.getClass(), cursoNew.getIdcurso());
                carreraCurso.setCurso(cursoNew);
            }
            carreraCurso = em.merge(carreraCurso);
            if (carreraOld != null && !carreraOld.equals(carreraNew)) {
                carreraOld.getCarreraCursoList().remove(carreraCurso);
                carreraOld = em.merge(carreraOld);
            }
            if (carreraNew != null && !carreraNew.equals(carreraOld)) {
                carreraNew.getCarreraCursoList().add(carreraCurso);
                carreraNew = em.merge(carreraNew);
            }
            if (cursoOld != null && !cursoOld.equals(cursoNew)) {
                cursoOld.getCarreraCursoList().remove(carreraCurso);
                cursoOld = em.merge(cursoOld);
            }
            if (cursoNew != null && !cursoNew.equals(cursoOld)) {
                cursoNew.getCarreraCursoList().add(carreraCurso);
                cursoNew = em.merge(cursoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CarreraCursoPK id = carreraCurso.getCarreraCursoPK();
                if (findCarreraCurso(id) == null) {
                    throw new NonexistentEntityException("The carreraCurso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CarreraCursoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CarreraCurso carreraCurso;
            try {
                carreraCurso = em.getReference(CarreraCurso.class, id);
                carreraCurso.getCarreraCursoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carreraCurso with id " + id + " no longer exists.", enfe);
            }
            Carrera carrera = carreraCurso.getCarrera();
            if (carrera != null) {
                carrera.getCarreraCursoList().remove(carreraCurso);
                carrera = em.merge(carrera);
            }
            Curso curso = carreraCurso.getCurso();
            if (curso != null) {
                curso.getCarreraCursoList().remove(carreraCurso);
                curso = em.merge(curso);
            }
            em.remove(carreraCurso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CarreraCurso> findCarreraCursoEntities() {
        return findCarreraCursoEntities(true, -1, -1);
    }

    public List<CarreraCurso> findCarreraCursoEntities(int maxResults, int firstResult) {
        return findCarreraCursoEntities(false, maxResults, firstResult);
    }

    private List<CarreraCurso> findCarreraCursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CarreraCurso.class));
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

    public CarreraCurso findCarreraCurso(CarreraCursoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CarreraCurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarreraCursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CarreraCurso> rt = cq.from(CarreraCurso.class);
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

    public List<String> findCarreraAvailableByFacultad(String fac) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c.carreranacionalidcarreranacional.nombrecarreranacional "
                    + "FROM Carrera c "
                    + "WHERE c.canceladacarrera=false AND "
                    + "c.facultadcodigoarea.codigoarea= :facultad "
                    + "ORDER BY c.carreranacionalidcarreranacional.nombrecarreranacional");
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
            Query q = em.createQuery("SELECT c.carreranacionalidcarreranacional.nombrecarreranacional "
                    + "FROM Carrera c "
                    + "JOIN FETCH c.facultadCumCarreraList fc "
                    + "WHERE c.canceladacarrera=false AND "
                    + "fc.facultadCum.cum.nombrecum= :facultad "
                    + "ORDER BY c.carreranacionalidcarreranacional.nombrecarreranacional");
            q.setParameter("facultad", fac);
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
