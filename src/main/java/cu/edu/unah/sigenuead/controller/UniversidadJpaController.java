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
import cu.edu.unah.sigenuead.entity.Municipio;
import cu.edu.unah.sigenuead.entity.Facultad;
import java.util.ArrayList;
import java.util.List;
import cu.edu.unah.sigenuead.entity.Curso;
import cu.edu.unah.sigenuead.entity.Cum;
import cu.edu.unah.sigenuead.entity.Universidad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class UniversidadJpaController implements Serializable {

    public UniversidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Universidad universidad) throws PreexistingEntityException, Exception {
        if (universidad.getFacultadList() == null) {
            universidad.setFacultadList(new ArrayList<Facultad>());
        }
        if (universidad.getCursoList() == null) {
            universidad.setCursoList(new ArrayList<Curso>());
        }
        if (universidad.getCumList() == null) {
            universidad.setCumList(new ArrayList<Cum>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipio municipioidmunicipio = universidad.getMunicipioidmunicipio();
            if (municipioidmunicipio != null) {
                municipioidmunicipio = em.getReference(municipioidmunicipio.getClass(), municipioidmunicipio.getIdmunicipio());
                universidad.setMunicipioidmunicipio(municipioidmunicipio);
            }
            List<Facultad> attachedFacultadList = new ArrayList<Facultad>();
            for (Facultad facultadListFacultadToAttach : universidad.getFacultadList()) {
                facultadListFacultadToAttach = em.getReference(facultadListFacultadToAttach.getClass(), facultadListFacultadToAttach.getCodigoarea());
                attachedFacultadList.add(facultadListFacultadToAttach);
            }
            universidad.setFacultadList(attachedFacultadList);
            List<Curso> attachedCursoList = new ArrayList<Curso>();
            for (Curso cursoListCursoToAttach : universidad.getCursoList()) {
                cursoListCursoToAttach = em.getReference(cursoListCursoToAttach.getClass(), cursoListCursoToAttach.getIdcurso());
                attachedCursoList.add(cursoListCursoToAttach);
            }
            universidad.setCursoList(attachedCursoList);
            List<Cum> attachedCumList = new ArrayList<Cum>();
            for (Cum cumListCumToAttach : universidad.getCumList()) {
                cumListCumToAttach = em.getReference(cumListCumToAttach.getClass(), cumListCumToAttach.getCodigocum());
                attachedCumList.add(cumListCumToAttach);
            }
            universidad.setCumList(attachedCumList);
            em.persist(universidad);
            if (municipioidmunicipio != null) {
                municipioidmunicipio.getUniversidadList().add(universidad);
                municipioidmunicipio = em.merge(municipioidmunicipio);
            }
            for (Facultad facultadListFacultad : universidad.getFacultadList()) {
                Universidad oldUniversidadcodigouniversidadOfFacultadListFacultad = facultadListFacultad.getUniversidadcodigouniversidad();
                facultadListFacultad.setUniversidadcodigouniversidad(universidad);
                facultadListFacultad = em.merge(facultadListFacultad);
                if (oldUniversidadcodigouniversidadOfFacultadListFacultad != null) {
                    oldUniversidadcodigouniversidadOfFacultadListFacultad.getFacultadList().remove(facultadListFacultad);
                    oldUniversidadcodigouniversidadOfFacultadListFacultad = em.merge(oldUniversidadcodigouniversidadOfFacultadListFacultad);
                }
            }
            for (Curso cursoListCurso : universidad.getCursoList()) {
                Universidad oldUniversidadcodigouniversidadOfCursoListCurso = cursoListCurso.getUniversidadcodigouniversidad();
                cursoListCurso.setUniversidadcodigouniversidad(universidad);
                cursoListCurso = em.merge(cursoListCurso);
                if (oldUniversidadcodigouniversidadOfCursoListCurso != null) {
                    oldUniversidadcodigouniversidadOfCursoListCurso.getCursoList().remove(cursoListCurso);
                    oldUniversidadcodigouniversidadOfCursoListCurso = em.merge(oldUniversidadcodigouniversidadOfCursoListCurso);
                }
            }
            for (Cum cumListCum : universidad.getCumList()) {
                Universidad oldUniversidadcodigouniversidadOfCumListCum = cumListCum.getUniversidadcodigouniversidad();
                cumListCum.setUniversidadcodigouniversidad(universidad);
                cumListCum = em.merge(cumListCum);
                if (oldUniversidadcodigouniversidadOfCumListCum != null) {
                    oldUniversidadcodigouniversidadOfCumListCum.getCumList().remove(cumListCum);
                    oldUniversidadcodigouniversidadOfCumListCum = em.merge(oldUniversidadcodigouniversidadOfCumListCum);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUniversidad(universidad.getCodigouniversidad()) != null) {
                throw new PreexistingEntityException("Universidad " + universidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Universidad universidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Universidad persistentUniversidad = em.find(Universidad.class, universidad.getCodigouniversidad());
            Municipio municipioidmunicipioOld = persistentUniversidad.getMunicipioidmunicipio();
            Municipio municipioidmunicipioNew = universidad.getMunicipioidmunicipio();
            List<Facultad> facultadListOld = persistentUniversidad.getFacultadList();
            List<Facultad> facultadListNew = universidad.getFacultadList();
            List<Curso> cursoListOld = persistentUniversidad.getCursoList();
            List<Curso> cursoListNew = universidad.getCursoList();
            List<Cum> cumListOld = persistentUniversidad.getCumList();
            List<Cum> cumListNew = universidad.getCumList();
            List<String> illegalOrphanMessages = null;
            for (Facultad facultadListOldFacultad : facultadListOld) {
                if (!facultadListNew.contains(facultadListOldFacultad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Facultad " + facultadListOldFacultad + " since its universidadcodigouniversidad field is not nullable.");
                }
            }
            for (Curso cursoListOldCurso : cursoListOld) {
                if (!cursoListNew.contains(cursoListOldCurso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Curso " + cursoListOldCurso + " since its universidadcodigouniversidad field is not nullable.");
                }
            }
            for (Cum cumListOldCum : cumListOld) {
                if (!cumListNew.contains(cumListOldCum)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cum " + cumListOldCum + " since its universidadcodigouniversidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (municipioidmunicipioNew != null) {
                municipioidmunicipioNew = em.getReference(municipioidmunicipioNew.getClass(), municipioidmunicipioNew.getIdmunicipio());
                universidad.setMunicipioidmunicipio(municipioidmunicipioNew);
            }
            List<Facultad> attachedFacultadListNew = new ArrayList<Facultad>();
            for (Facultad facultadListNewFacultadToAttach : facultadListNew) {
                facultadListNewFacultadToAttach = em.getReference(facultadListNewFacultadToAttach.getClass(), facultadListNewFacultadToAttach.getCodigoarea());
                attachedFacultadListNew.add(facultadListNewFacultadToAttach);
            }
            facultadListNew = attachedFacultadListNew;
            universidad.setFacultadList(facultadListNew);
            List<Curso> attachedCursoListNew = new ArrayList<Curso>();
            for (Curso cursoListNewCursoToAttach : cursoListNew) {
                cursoListNewCursoToAttach = em.getReference(cursoListNewCursoToAttach.getClass(), cursoListNewCursoToAttach.getIdcurso());
                attachedCursoListNew.add(cursoListNewCursoToAttach);
            }
            cursoListNew = attachedCursoListNew;
            universidad.setCursoList(cursoListNew);
            List<Cum> attachedCumListNew = new ArrayList<Cum>();
            for (Cum cumListNewCumToAttach : cumListNew) {
                cumListNewCumToAttach = em.getReference(cumListNewCumToAttach.getClass(), cumListNewCumToAttach.getCodigocum());
                attachedCumListNew.add(cumListNewCumToAttach);
            }
            cumListNew = attachedCumListNew;
            universidad.setCumList(cumListNew);
            universidad = em.merge(universidad);
            if (municipioidmunicipioOld != null && !municipioidmunicipioOld.equals(municipioidmunicipioNew)) {
                municipioidmunicipioOld.getUniversidadList().remove(universidad);
                municipioidmunicipioOld = em.merge(municipioidmunicipioOld);
            }
            if (municipioidmunicipioNew != null && !municipioidmunicipioNew.equals(municipioidmunicipioOld)) {
                municipioidmunicipioNew.getUniversidadList().add(universidad);
                municipioidmunicipioNew = em.merge(municipioidmunicipioNew);
            }
            for (Facultad facultadListNewFacultad : facultadListNew) {
                if (!facultadListOld.contains(facultadListNewFacultad)) {
                    Universidad oldUniversidadcodigouniversidadOfFacultadListNewFacultad = facultadListNewFacultad.getUniversidadcodigouniversidad();
                    facultadListNewFacultad.setUniversidadcodigouniversidad(universidad);
                    facultadListNewFacultad = em.merge(facultadListNewFacultad);
                    if (oldUniversidadcodigouniversidadOfFacultadListNewFacultad != null && !oldUniversidadcodigouniversidadOfFacultadListNewFacultad.equals(universidad)) {
                        oldUniversidadcodigouniversidadOfFacultadListNewFacultad.getFacultadList().remove(facultadListNewFacultad);
                        oldUniversidadcodigouniversidadOfFacultadListNewFacultad = em.merge(oldUniversidadcodigouniversidadOfFacultadListNewFacultad);
                    }
                }
            }
            for (Curso cursoListNewCurso : cursoListNew) {
                if (!cursoListOld.contains(cursoListNewCurso)) {
                    Universidad oldUniversidadcodigouniversidadOfCursoListNewCurso = cursoListNewCurso.getUniversidadcodigouniversidad();
                    cursoListNewCurso.setUniversidadcodigouniversidad(universidad);
                    cursoListNewCurso = em.merge(cursoListNewCurso);
                    if (oldUniversidadcodigouniversidadOfCursoListNewCurso != null && !oldUniversidadcodigouniversidadOfCursoListNewCurso.equals(universidad)) {
                        oldUniversidadcodigouniversidadOfCursoListNewCurso.getCursoList().remove(cursoListNewCurso);
                        oldUniversidadcodigouniversidadOfCursoListNewCurso = em.merge(oldUniversidadcodigouniversidadOfCursoListNewCurso);
                    }
                }
            }
            for (Cum cumListNewCum : cumListNew) {
                if (!cumListOld.contains(cumListNewCum)) {
                    Universidad oldUniversidadcodigouniversidadOfCumListNewCum = cumListNewCum.getUniversidadcodigouniversidad();
                    cumListNewCum.setUniversidadcodigouniversidad(universidad);
                    cumListNewCum = em.merge(cumListNewCum);
                    if (oldUniversidadcodigouniversidadOfCumListNewCum != null && !oldUniversidadcodigouniversidadOfCumListNewCum.equals(universidad)) {
                        oldUniversidadcodigouniversidadOfCumListNewCum.getCumList().remove(cumListNewCum);
                        oldUniversidadcodigouniversidadOfCumListNewCum = em.merge(oldUniversidadcodigouniversidadOfCumListNewCum);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = universidad.getCodigouniversidad();
                if (findUniversidad(id) == null) {
                    throw new NonexistentEntityException("The universidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Universidad universidad;
            try {
                universidad = em.getReference(Universidad.class, id);
                universidad.getCodigouniversidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The universidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Facultad> facultadListOrphanCheck = universidad.getFacultadList();
            for (Facultad facultadListOrphanCheckFacultad : facultadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Universidad (" + universidad + ") cannot be destroyed since the Facultad " + facultadListOrphanCheckFacultad + " in its facultadList field has a non-nullable universidadcodigouniversidad field.");
            }
            List<Curso> cursoListOrphanCheck = universidad.getCursoList();
            for (Curso cursoListOrphanCheckCurso : cursoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Universidad (" + universidad + ") cannot be destroyed since the Curso " + cursoListOrphanCheckCurso + " in its cursoList field has a non-nullable universidadcodigouniversidad field.");
            }
            List<Cum> cumListOrphanCheck = universidad.getCumList();
            for (Cum cumListOrphanCheckCum : cumListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Universidad (" + universidad + ") cannot be destroyed since the Cum " + cumListOrphanCheckCum + " in its cumList field has a non-nullable universidadcodigouniversidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Municipio municipioidmunicipio = universidad.getMunicipioidmunicipio();
            if (municipioidmunicipio != null) {
                municipioidmunicipio.getUniversidadList().remove(universidad);
                municipioidmunicipio = em.merge(municipioidmunicipio);
            }
            em.remove(universidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Universidad> findUniversidadEntities() {
        return findUniversidadEntities(true, -1, -1);
    }

    public List<Universidad> findUniversidadEntities(int maxResults, int firstResult) {
        return findUniversidadEntities(false, maxResults, firstResult);
    }

    private List<Universidad> findUniversidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Universidad.class));
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

    public Universidad findUniversidad(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Universidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getUniversidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Universidad> rt = cq.from(Universidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Universidad findUniversidadByNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT u "
                    + "FROM Universidad u "
                    + "WHERE u.nombreuniversidad= :nombre");
            q.setParameter("nombre", nombre);
            Universidad p = (Universidad) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Universidad findUniversidadAvailable() {
        EntityManager em = getEntityManager();
        try {
            Universidad p = (Universidad) em.createQuery("SELECT u "
                    + "FROM Universidad u "
                    + "WHERE u.canceladouniversidad=false AND "
                    + "u.codigouniversidad NOT LIKE ''").getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Universidad> findUniversidadList() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT u "
                    + "FROM Universidad u "
                    + "WHERE u.codigouniversidad NOT LIKE ''");
            List<Universidad> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
