/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.TipoMilitar;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class TipoMilitarJpaController implements Serializable {

    public TipoMilitarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoMilitar tipoMilitar) {
        if (tipoMilitar.getEstudianteList() == null) {
            tipoMilitar.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : tipoMilitar.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            tipoMilitar.setEstudianteList(attachedEstudianteList);
            em.persist(tipoMilitar);
            for (Estudiante estudianteListEstudiante : tipoMilitar.getEstudianteList()) {
                TipoMilitar oldTipoMilitaridTipoMilitarOfEstudianteListEstudiante = estudianteListEstudiante.getTipoMilitaridTipoMilitar();
                estudianteListEstudiante.setTipoMilitaridTipoMilitar(tipoMilitar);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldTipoMilitaridTipoMilitarOfEstudianteListEstudiante != null) {
                    oldTipoMilitaridTipoMilitarOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldTipoMilitaridTipoMilitarOfEstudianteListEstudiante = em.merge(oldTipoMilitaridTipoMilitarOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoMilitar tipoMilitar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoMilitar persistentTipoMilitar = em.find(TipoMilitar.class, tipoMilitar.getIdTipoMilitar());
            List<Estudiante> estudianteListOld = persistentTipoMilitar.getEstudianteList();
            List<Estudiante> estudianteListNew = tipoMilitar.getEstudianteList();
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            tipoMilitar.setEstudianteList(estudianteListNew);
            tipoMilitar = em.merge(tipoMilitar);
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    estudianteListOldEstudiante.setTipoMilitaridTipoMilitar(null);
                    estudianteListOldEstudiante = em.merge(estudianteListOldEstudiante);
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    TipoMilitar oldTipoMilitaridTipoMilitarOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getTipoMilitaridTipoMilitar();
                    estudianteListNewEstudiante.setTipoMilitaridTipoMilitar(tipoMilitar);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldTipoMilitaridTipoMilitarOfEstudianteListNewEstudiante != null && !oldTipoMilitaridTipoMilitarOfEstudianteListNewEstudiante.equals(tipoMilitar)) {
                        oldTipoMilitaridTipoMilitarOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldTipoMilitaridTipoMilitarOfEstudianteListNewEstudiante = em.merge(oldTipoMilitaridTipoMilitarOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoMilitar.getIdTipoMilitar();
                if (findTipoMilitar(id) == null) {
                    throw new NonexistentEntityException("The tipoMilitar with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoMilitar tipoMilitar;
            try {
                tipoMilitar = em.getReference(TipoMilitar.class, id);
                tipoMilitar.getIdTipoMilitar();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoMilitar with id " + id + " no longer exists.", enfe);
            }
            List<Estudiante> estudianteList = tipoMilitar.getEstudianteList();
            for (Estudiante estudianteListEstudiante : estudianteList) {
                estudianteListEstudiante.setTipoMilitaridTipoMilitar(null);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            em.remove(tipoMilitar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoMilitar> findTipoMilitarEntities() {
        return findTipoMilitarEntities(true, -1, -1);
    }

    public List<TipoMilitar> findTipoMilitarEntities(int maxResults, int firstResult) {
        return findTipoMilitarEntities(false, maxResults, firstResult);
    }

    private List<TipoMilitar> findTipoMilitarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoMilitar.class));
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

    public TipoMilitar findTipoMilitar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoMilitar.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoMilitarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoMilitar> rt = cq.from(TipoMilitar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public TipoMilitar findTipoMilitarByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT t "
                    + "FROM TipoMilitar t "
                    + "WHERE t.nombreTipo= :nombre");
            q.setParameter("nombre", nombre);
            TipoMilitar p = (TipoMilitar) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public TipoMilitar findTipoMilitarByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT t "
                    + "FROM TipoMilitar t "
                    + "WHERE t.canceladoTipoMilitar=false AND "
                    + "t.nombreTipo= :nombre");
            q.setParameter("nombre", nombre);
            TipoMilitar p = (TipoMilitar) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findTipoMilitarAvailable() {
        EntityManager em = getEntityManager();
        try {
            List<String> p = em.createQuery("SELECT t.nombreTipo "
                    + "FROM TipoMilitar t "
                    + "WHERE t.canceladoTipoMilitar=false "
                    + "ORDER BY t.nombreTipo").getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
