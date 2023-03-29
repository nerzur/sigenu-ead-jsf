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
import cu.edu.unah.sigenuead.entity.ProcedenciaEscolar;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class ProcedenciaEscolarJpaController implements Serializable {

    public ProcedenciaEscolarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProcedenciaEscolar procedenciaEscolar) {
        if (procedenciaEscolar.getEstudianteList() == null) {
            procedenciaEscolar.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : procedenciaEscolar.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            procedenciaEscolar.setEstudianteList(attachedEstudianteList);
            em.persist(procedenciaEscolar);
            for (Estudiante estudianteListEstudiante : procedenciaEscolar.getEstudianteList()) {
                ProcedenciaEscolar oldProcedenciaEscolarprocedenciaEscolarIdOfEstudianteListEstudiante = estudianteListEstudiante.getProcedenciaEscolarprocedenciaEscolarId();
                estudianteListEstudiante.setProcedenciaEscolarprocedenciaEscolarId(procedenciaEscolar);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldProcedenciaEscolarprocedenciaEscolarIdOfEstudianteListEstudiante != null) {
                    oldProcedenciaEscolarprocedenciaEscolarIdOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldProcedenciaEscolarprocedenciaEscolarIdOfEstudianteListEstudiante = em.merge(oldProcedenciaEscolarprocedenciaEscolarIdOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProcedenciaEscolar procedenciaEscolar) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProcedenciaEscolar persistentProcedenciaEscolar = em.find(ProcedenciaEscolar.class, procedenciaEscolar.getProcedenciaEscolarId());
            List<Estudiante> estudianteListOld = persistentProcedenciaEscolar.getEstudianteList();
            List<Estudiante> estudianteListNew = procedenciaEscolar.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its procedenciaEscolarprocedenciaEscolarId field is not nullable.");
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
            procedenciaEscolar.setEstudianteList(estudianteListNew);
            procedenciaEscolar = em.merge(procedenciaEscolar);
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    ProcedenciaEscolar oldProcedenciaEscolarprocedenciaEscolarIdOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getProcedenciaEscolarprocedenciaEscolarId();
                    estudianteListNewEstudiante.setProcedenciaEscolarprocedenciaEscolarId(procedenciaEscolar);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldProcedenciaEscolarprocedenciaEscolarIdOfEstudianteListNewEstudiante != null && !oldProcedenciaEscolarprocedenciaEscolarIdOfEstudianteListNewEstudiante.equals(procedenciaEscolar)) {
                        oldProcedenciaEscolarprocedenciaEscolarIdOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldProcedenciaEscolarprocedenciaEscolarIdOfEstudianteListNewEstudiante = em.merge(oldProcedenciaEscolarprocedenciaEscolarIdOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = procedenciaEscolar.getProcedenciaEscolarId();
                if (findProcedenciaEscolar(id) == null) {
                    throw new NonexistentEntityException("The procedenciaEscolar with id " + id + " no longer exists.");
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
            ProcedenciaEscolar procedenciaEscolar;
            try {
                procedenciaEscolar = em.getReference(ProcedenciaEscolar.class, id);
                procedenciaEscolar.getProcedenciaEscolarId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procedenciaEscolar with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estudiante> estudianteListOrphanCheck = procedenciaEscolar.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProcedenciaEscolar (" + procedenciaEscolar + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable procedenciaEscolarprocedenciaEscolarId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(procedenciaEscolar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProcedenciaEscolar> findProcedenciaEscolarEntities() {
        return findProcedenciaEscolarEntities(true, -1, -1);
    }

    public List<ProcedenciaEscolar> findProcedenciaEscolarEntities(int maxResults, int firstResult) {
        return findProcedenciaEscolarEntities(false, maxResults, firstResult);
    }

    private List<ProcedenciaEscolar> findProcedenciaEscolarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProcedenciaEscolar.class));
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

    public ProcedenciaEscolar findProcedenciaEscolar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProcedenciaEscolar.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcedenciaEscolarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProcedenciaEscolar> rt = cq.from(ProcedenciaEscolar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public ProcedenciaEscolar findProcedenciaEscolarByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p "
                    + "FROM ProcedenciaEscolar p "
                    + "WHERE p.procedenciaEscolarNombre= :nombre");
            q.setParameter("nombre", nombre);
            ProcedenciaEscolar p = (ProcedenciaEscolar) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public ProcedenciaEscolar findProcedenciaEscolarByNombreAvailable(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p "
                    + "FROM ProcedenciaEscolar p "
                    + "WHERE p.procedenciaEscolarCancelado=false AND "
                    + "p.procedenciaEscolarNombre= :nombre");
            q.setParameter("nombre", nombre);
            ProcedenciaEscolar p = (ProcedenciaEscolar) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findProcedenciaEscolarAvailable() {
        EntityManager em = getEntityManager();
        try {
            List<String> p = em.createQuery("SELECT p.procedenciaEscolarNombre "
                    + "FROM ProcedenciaEscolar p "
                    + "WHERE p.procedenciaEscolarCancelado=false "
                    + "ORDER BY p.procedenciaEscolarNombre").getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
