/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.entity.ColorPiel;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import cu.edu.unah.sigenuead.entity.Estudiante;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class ColorPielJpaController implements Serializable {

    public ColorPielJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ColorPiel colorPiel) {
        if (colorPiel.getEstudianteList() == null) {
            colorPiel.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : colorPiel.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            colorPiel.setEstudianteList(attachedEstudianteList);
            em.persist(colorPiel);
            for (Estudiante estudianteListEstudiante : colorPiel.getEstudianteList()) {
                ColorPiel oldColorPielcolorPielIdOfEstudianteListEstudiante = estudianteListEstudiante.getColorPielcolorPielId();
                estudianteListEstudiante.setColorPielcolorPielId(colorPiel);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldColorPielcolorPielIdOfEstudianteListEstudiante != null) {
                    oldColorPielcolorPielIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldColorPielcolorPielIdOfEstudianteListEstudiante = em.merge(oldColorPielcolorPielIdOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ColorPiel colorPiel) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ColorPiel persistentColorPiel = em.find(ColorPiel.class, colorPiel.getColorPielId());
            List<Estudiante> estudianteListOld = persistentColorPiel.getEstudianteList();
            List<Estudiante> estudianteListNew = colorPiel.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its colorPielcolorPielId field is not nullable.");
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
            colorPiel.setEstudianteList(estudianteListNew);
            colorPiel = em.merge(colorPiel);
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    ColorPiel oldColorPielcolorPielIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getColorPielcolorPielId();
                    estudianteListNewEstudiante.setColorPielcolorPielId(colorPiel);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldColorPielcolorPielIdOfEstudianteListNewEstudiante != null && !oldColorPielcolorPielIdOfEstudianteListNewEstudiante.equals(colorPiel)) {
                        oldColorPielcolorPielIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldColorPielcolorPielIdOfEstudianteListNewEstudiante = em.merge(oldColorPielcolorPielIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = colorPiel.getColorPielId();
                if (findColorPiel(id) == null) {
                    throw new NonexistentEntityException("The colorPiel with id " + id + " no longer exists.");
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
            ColorPiel colorPiel;
            try {
                colorPiel = em.getReference(ColorPiel.class, id);
                colorPiel.getColorPielId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The colorPiel with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estudiante> estudianteListOrphanCheck = colorPiel.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ColorPiel (" + colorPiel + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable colorPielcolorPielId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(colorPiel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ColorPiel> findColorPielEntities() {
        return findColorPielEntities(true, -1, -1);
    }

    public List<ColorPiel> findColorPielEntities(int maxResults, int firstResult) {
        return findColorPielEntities(false, maxResults, firstResult);
    }

    private List<ColorPiel> findColorPielEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ColorPiel.class));
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

    public ColorPiel findColorPiel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ColorPiel.class, id);
        } finally {
            em.close();
        }
    }

    public int getColorPielCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ColorPiel> rt = cq.from(ColorPiel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public ColorPiel findColorPielByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM ColorPiel c "
                    + "WHERE c.colorPielNombre= :colorpiel");
            q.setParameter("colorpiel", nombre);
            ColorPiel p = (ColorPiel) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public ColorPiel findColorPielByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c "
                    + "FROM ColorPiel c "
                    + "WHERE c.colorPielCancelado=false AND "
                    + "c.colorPielNombre= :nombre");
            q.setParameter("nombre", nombre);
            ColorPiel p = (ColorPiel) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findColorPielAvailable() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT c.colorPielNombre "
                    + "FROM ColorPiel c "
                    + "WHERE c.colorPielCancelado=false "
                    + "ORDER BY c.colorPielNombre");
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
