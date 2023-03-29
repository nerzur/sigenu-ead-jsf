/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.Huerfano;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class HuerfanoJpaController implements Serializable {

    public HuerfanoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Huerfano huerfano) {
        if (huerfano.getEstudianteList() == null) {
            huerfano.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : huerfano.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            huerfano.setEstudianteList(attachedEstudianteList);
            em.persist(huerfano);
            for (Estudiante estudianteListEstudiante : huerfano.getEstudianteList()) {
                Huerfano oldHuerfanohuerfanoIdOfEstudianteListEstudiante = estudianteListEstudiante.getHuerfanohuerfanoId();
                estudianteListEstudiante.setHuerfanohuerfanoId(huerfano);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldHuerfanohuerfanoIdOfEstudianteListEstudiante != null) {
                    oldHuerfanohuerfanoIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldHuerfanohuerfanoIdOfEstudianteListEstudiante = em.merge(oldHuerfanohuerfanoIdOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Huerfano huerfano) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Huerfano persistentHuerfano = em.find(Huerfano.class, huerfano.getHuerfanoId());
            List<Estudiante> estudianteListOld = persistentHuerfano.getEstudianteList();
            List<Estudiante> estudianteListNew = huerfano.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its huerfanohuerfanoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            huerfano.setEstudianteList(estudianteListNew);
            huerfano = em.merge(huerfano);
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Huerfano oldHuerfanohuerfanoIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getHuerfanohuerfanoId();
                    estudianteListNewEstudiante.setHuerfanohuerfanoId(huerfano);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldHuerfanohuerfanoIdOfEstudianteListNewEstudiante != null && !oldHuerfanohuerfanoIdOfEstudianteListNewEstudiante.equals(huerfano)) {
                        oldHuerfanohuerfanoIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldHuerfanohuerfanoIdOfEstudianteListNewEstudiante = em.merge(oldHuerfanohuerfanoIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = huerfano.getHuerfanoId();
                if (findHuerfano(id) == null) {
                    throw new NonexistentEntityException("The huerfano with id " + id + " no longer exists.");
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
            Huerfano huerfano;
            try {
                huerfano = em.getReference(Huerfano.class, id);
                huerfano.getHuerfanoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The huerfano with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estudiante> estudianteListOrphanCheck = huerfano.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Huerfano (" + huerfano + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable huerfanohuerfanoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(huerfano);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Huerfano> findHuerfanoEntities() {
        return findHuerfanoEntities(true, -1, -1);
    }

    public List<Huerfano> findHuerfanoEntities(int maxResults, int firstResult) {
        return findHuerfanoEntities(false, maxResults, firstResult);
    }

    private List<Huerfano> findHuerfanoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Huerfano.class));
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

    public Huerfano findHuerfano(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Huerfano.class, id);
        } finally {
            em.close();
        }
    }

    public int getHuerfanoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Huerfano> rt = cq.from(Huerfano.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Huerfano findHuerfanoByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT h "
                    + "FROM Huerfano h "
                    + "WHERE h.huerfanoNombre= :nombre");
            q.setParameter("nombre", nombre);
            Huerfano p = (Huerfano) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Huerfano findHuerfanoByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT h "
                    + "FROM Huerfano h "
                    + "WHERE h.huerfanoCancelado=false AND "
                    + "h.huerfanoNombre= :nombre");
            q.setParameter("nombre", nombre);
            Huerfano p = (Huerfano) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findHuerfanoAvailable() {
        EntityManager em = getEntityManager();
        try {
            List<String> p = em.createQuery("SELECT h.huerfanoNombre "
                    + "FROM Huerfano h "
                    + "WHERE h.huerfanoCancelado=false "
                    + "ORDER BY h.huerfanoNombre").getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
