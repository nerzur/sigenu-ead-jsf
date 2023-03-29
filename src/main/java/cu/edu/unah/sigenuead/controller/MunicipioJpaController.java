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
import cu.edu.unah.sigenuead.entity.Provincia;
import cu.edu.unah.sigenuead.entity.Facultad;
import java.util.ArrayList;
import java.util.List;
import cu.edu.unah.sigenuead.entity.Cum;
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.Municipio;
import cu.edu.unah.sigenuead.entity.Universidad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 *
 * @author claudy
 */
public class MunicipioJpaController implements Serializable {

    public MunicipioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Municipio municipio) {
        if (municipio.getFacultadList() == null) {
            municipio.setFacultadList(new ArrayList<Facultad>());
        }
        if (municipio.getCumList() == null) {
            municipio.setCumList(new ArrayList<Cum>());
        }
        if (municipio.getEstudianteList() == null) {
            municipio.setEstudianteList(new ArrayList<Estudiante>());
        }
        if (municipio.getEstudianteList1() == null) {
            municipio.setEstudianteList1(new ArrayList<Estudiante>());
        }
        if (municipio.getUniversidadList() == null) {
            municipio.setUniversidadList(new ArrayList<Universidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia provinciaidprovincia = municipio.getProvinciaidprovincia();
            if (provinciaidprovincia != null) {
                provinciaidprovincia = em.getReference(provinciaidprovincia.getClass(), provinciaidprovincia.getIdprovincia());
                municipio.setProvinciaidprovincia(provinciaidprovincia);
            }
            List<Facultad> attachedFacultadList = new ArrayList<Facultad>();
            for (Facultad facultadListFacultadToAttach : municipio.getFacultadList()) {
                facultadListFacultadToAttach = em.getReference(facultadListFacultadToAttach.getClass(), facultadListFacultadToAttach.getCodigoarea());
                attachedFacultadList.add(facultadListFacultadToAttach);
            }
            municipio.setFacultadList(attachedFacultadList);
            List<Cum> attachedCumList = new ArrayList<Cum>();
            for (Cum cumListCumToAttach : municipio.getCumList()) {
                cumListCumToAttach = em.getReference(cumListCumToAttach.getClass(), cumListCumToAttach.getCodigocum());
                attachedCumList.add(cumListCumToAttach);
            }
            municipio.setCumList(attachedCumList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : municipio.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getEstudianteId());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            municipio.setEstudianteList(attachedEstudianteList);
            List<Estudiante> attachedEstudianteList1 = new ArrayList<Estudiante>();
            for (Estudiante estudianteList1EstudianteToAttach : municipio.getEstudianteList1()) {
                estudianteList1EstudianteToAttach = em.getReference(estudianteList1EstudianteToAttach.getClass(), estudianteList1EstudianteToAttach.getEstudianteId());
                attachedEstudianteList1.add(estudianteList1EstudianteToAttach);
            }
            municipio.setEstudianteList1(attachedEstudianteList1);
            List<Universidad> attachedUniversidadList = new ArrayList<Universidad>();
            for (Universidad universidadListUniversidadToAttach : municipio.getUniversidadList()) {
                universidadListUniversidadToAttach = em.getReference(universidadListUniversidadToAttach.getClass(), universidadListUniversidadToAttach.getCodigouniversidad());
                attachedUniversidadList.add(universidadListUniversidadToAttach);
            }
            municipio.setUniversidadList(attachedUniversidadList);
            em.persist(municipio);
            if (provinciaidprovincia != null) {
                provinciaidprovincia.getMunicipioList().add(municipio);
                provinciaidprovincia = em.merge(provinciaidprovincia);
            }
            for (Facultad facultadListFacultad : municipio.getFacultadList()) {
                Municipio oldMunicipioidmunicipioOfFacultadListFacultad = facultadListFacultad.getMunicipioidmunicipio();
                facultadListFacultad.setMunicipioidmunicipio(municipio);
                facultadListFacultad = em.merge(facultadListFacultad);
                if (oldMunicipioidmunicipioOfFacultadListFacultad != null) {
                    oldMunicipioidmunicipioOfFacultadListFacultad.getFacultadList().remove(facultadListFacultad);
                    oldMunicipioidmunicipioOfFacultadListFacultad = em.merge(oldMunicipioidmunicipioOfFacultadListFacultad);
                }
            }
            for (Cum cumListCum : municipio.getCumList()) {
                Municipio oldMunicipioidmunicipioOfCumListCum = cumListCum.getMunicipioidmunicipio();
                cumListCum.setMunicipioidmunicipio(municipio);
                cumListCum = em.merge(cumListCum);
                if (oldMunicipioidmunicipioOfCumListCum != null) {
                    oldMunicipioidmunicipioOfCumListCum.getCumList().remove(cumListCum);
                    oldMunicipioidmunicipioOfCumListCum = em.merge(oldMunicipioidmunicipioOfCumListCum);
                }
            }
            for (Estudiante estudianteListEstudiante : municipio.getEstudianteList()) {
                Municipio oldMunicipioidmunicipioOfEstudianteListEstudiante = estudianteListEstudiante.getMunicipioidmunicipio();
                estudianteListEstudiante.setMunicipioidmunicipio(municipio);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldMunicipioidmunicipioOfEstudianteListEstudiante != null) {
                    oldMunicipioidmunicipioOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldMunicipioidmunicipioOfEstudianteListEstudiante = em.merge(oldMunicipioidmunicipioOfEstudianteListEstudiante);
                }
            }
            for (Estudiante estudianteList1Estudiante : municipio.getEstudianteList1()) {
                Municipio oldMunicipioidmunicipiocentrotrabajoOfEstudianteList1Estudiante = estudianteList1Estudiante.getMunicipioidmunicipiocentrotrabajo();
                estudianteList1Estudiante.setMunicipioidmunicipiocentrotrabajo(municipio);
                estudianteList1Estudiante = em.merge(estudianteList1Estudiante);
                if (oldMunicipioidmunicipiocentrotrabajoOfEstudianteList1Estudiante != null) {
                    oldMunicipioidmunicipiocentrotrabajoOfEstudianteList1Estudiante.getEstudianteList1().remove(estudianteList1Estudiante);
                    oldMunicipioidmunicipiocentrotrabajoOfEstudianteList1Estudiante = em.merge(oldMunicipioidmunicipiocentrotrabajoOfEstudianteList1Estudiante);
                }
            }
            for (Universidad universidadListUniversidad : municipio.getUniversidadList()) {
                Municipio oldMunicipioidmunicipioOfUniversidadListUniversidad = universidadListUniversidad.getMunicipioidmunicipio();
                universidadListUniversidad.setMunicipioidmunicipio(municipio);
                universidadListUniversidad = em.merge(universidadListUniversidad);
                if (oldMunicipioidmunicipioOfUniversidadListUniversidad != null) {
                    oldMunicipioidmunicipioOfUniversidadListUniversidad.getUniversidadList().remove(universidadListUniversidad);
                    oldMunicipioidmunicipioOfUniversidadListUniversidad = em.merge(oldMunicipioidmunicipioOfUniversidadListUniversidad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Municipio municipio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipio persistentMunicipio = em.find(Municipio.class, municipio.getIdmunicipio());
            Provincia provinciaidprovinciaOld = persistentMunicipio.getProvinciaidprovincia();
            Provincia provinciaidprovinciaNew = municipio.getProvinciaidprovincia();
            List<Facultad> facultadListOld = persistentMunicipio.getFacultadList();
            List<Facultad> facultadListNew = municipio.getFacultadList();
            List<Cum> cumListOld = persistentMunicipio.getCumList();
            List<Cum> cumListNew = municipio.getCumList();
            List<Estudiante> estudianteListOld = persistentMunicipio.getEstudianteList();
            List<Estudiante> estudianteListNew = municipio.getEstudianteList();
            List<Estudiante> estudianteList1Old = persistentMunicipio.getEstudianteList1();
            List<Estudiante> estudianteList1New = municipio.getEstudianteList1();
            List<Universidad> universidadListOld = persistentMunicipio.getUniversidadList();
            List<Universidad> universidadListNew = municipio.getUniversidadList();
            List<String> illegalOrphanMessages = null;
            for (Facultad facultadListOldFacultad : facultadListOld) {
                if (!facultadListNew.contains(facultadListOldFacultad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Facultad " + facultadListOldFacultad + " since its municipioidmunicipio field is not nullable.");
                }
            }
            for (Cum cumListOldCum : cumListOld) {
                if (!cumListNew.contains(cumListOldCum)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cum " + cumListOldCum + " since its municipioidmunicipio field is not nullable.");
                }
            }
            for (Universidad universidadListOldUniversidad : universidadListOld) {
                if (!universidadListNew.contains(universidadListOldUniversidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Universidad " + universidadListOldUniversidad + " since its municipioidmunicipio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (provinciaidprovinciaNew != null) {
                provinciaidprovinciaNew = em.getReference(provinciaidprovinciaNew.getClass(), provinciaidprovinciaNew.getIdprovincia());
                municipio.setProvinciaidprovincia(provinciaidprovinciaNew);
            }
            List<Facultad> attachedFacultadListNew = new ArrayList<Facultad>();
            for (Facultad facultadListNewFacultadToAttach : facultadListNew) {
                facultadListNewFacultadToAttach = em.getReference(facultadListNewFacultadToAttach.getClass(), facultadListNewFacultadToAttach.getCodigoarea());
                attachedFacultadListNew.add(facultadListNewFacultadToAttach);
            }
            facultadListNew = attachedFacultadListNew;
            municipio.setFacultadList(facultadListNew);
            List<Cum> attachedCumListNew = new ArrayList<Cum>();
            for (Cum cumListNewCumToAttach : cumListNew) {
                cumListNewCumToAttach = em.getReference(cumListNewCumToAttach.getClass(), cumListNewCumToAttach.getCodigocum());
                attachedCumListNew.add(cumListNewCumToAttach);
            }
            cumListNew = attachedCumListNew;
            municipio.setCumList(cumListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getEstudianteId());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            municipio.setEstudianteList(estudianteListNew);
            List<Estudiante> attachedEstudianteList1New = new ArrayList<Estudiante>();
            for (Estudiante estudianteList1NewEstudianteToAttach : estudianteList1New) {
                estudianteList1NewEstudianteToAttach = em.getReference(estudianteList1NewEstudianteToAttach.getClass(), estudianteList1NewEstudianteToAttach.getEstudianteId());
                attachedEstudianteList1New.add(estudianteList1NewEstudianteToAttach);
            }
            estudianteList1New = attachedEstudianteList1New;
            municipio.setEstudianteList1(estudianteList1New);
            List<Universidad> attachedUniversidadListNew = new ArrayList<Universidad>();
            for (Universidad universidadListNewUniversidadToAttach : universidadListNew) {
                universidadListNewUniversidadToAttach = em.getReference(universidadListNewUniversidadToAttach.getClass(), universidadListNewUniversidadToAttach.getCodigouniversidad());
                attachedUniversidadListNew.add(universidadListNewUniversidadToAttach);
            }
            universidadListNew = attachedUniversidadListNew;
            municipio.setUniversidadList(universidadListNew);
            municipio = em.merge(municipio);
            if (provinciaidprovinciaOld != null && !provinciaidprovinciaOld.equals(provinciaidprovinciaNew)) {
                provinciaidprovinciaOld.getMunicipioList().remove(municipio);
                provinciaidprovinciaOld = em.merge(provinciaidprovinciaOld);
            }
            if (provinciaidprovinciaNew != null && !provinciaidprovinciaNew.equals(provinciaidprovinciaOld)) {
                provinciaidprovinciaNew.getMunicipioList().add(municipio);
                provinciaidprovinciaNew = em.merge(provinciaidprovinciaNew);
            }
            for (Facultad facultadListNewFacultad : facultadListNew) {
                if (!facultadListOld.contains(facultadListNewFacultad)) {
                    Municipio oldMunicipioidmunicipioOfFacultadListNewFacultad = facultadListNewFacultad.getMunicipioidmunicipio();
                    facultadListNewFacultad.setMunicipioidmunicipio(municipio);
                    facultadListNewFacultad = em.merge(facultadListNewFacultad);
                    if (oldMunicipioidmunicipioOfFacultadListNewFacultad != null && !oldMunicipioidmunicipioOfFacultadListNewFacultad.equals(municipio)) {
                        oldMunicipioidmunicipioOfFacultadListNewFacultad.getFacultadList().remove(facultadListNewFacultad);
                        oldMunicipioidmunicipioOfFacultadListNewFacultad = em.merge(oldMunicipioidmunicipioOfFacultadListNewFacultad);
                    }
                }
            }
            for (Cum cumListNewCum : cumListNew) {
                if (!cumListOld.contains(cumListNewCum)) {
                    Municipio oldMunicipioidmunicipioOfCumListNewCum = cumListNewCum.getMunicipioidmunicipio();
                    cumListNewCum.setMunicipioidmunicipio(municipio);
                    cumListNewCum = em.merge(cumListNewCum);
                    if (oldMunicipioidmunicipioOfCumListNewCum != null && !oldMunicipioidmunicipioOfCumListNewCum.equals(municipio)) {
                        oldMunicipioidmunicipioOfCumListNewCum.getCumList().remove(cumListNewCum);
                        oldMunicipioidmunicipioOfCumListNewCum = em.merge(oldMunicipioidmunicipioOfCumListNewCum);
                    }
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    estudianteListOldEstudiante.setMunicipioidmunicipio(null);
                    estudianteListOldEstudiante = em.merge(estudianteListOldEstudiante);
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Municipio oldMunicipioidmunicipioOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getMunicipioidmunicipio();
                    estudianteListNewEstudiante.setMunicipioidmunicipio(municipio);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldMunicipioidmunicipioOfEstudianteListNewEstudiante != null && !oldMunicipioidmunicipioOfEstudianteListNewEstudiante.equals(municipio)) {
                        oldMunicipioidmunicipioOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldMunicipioidmunicipioOfEstudianteListNewEstudiante = em.merge(oldMunicipioidmunicipioOfEstudianteListNewEstudiante);
                    }
                }
            }
            for (Estudiante estudianteList1OldEstudiante : estudianteList1Old) {
                if (!estudianteList1New.contains(estudianteList1OldEstudiante)) {
                    estudianteList1OldEstudiante.setMunicipioidmunicipiocentrotrabajo(null);
                    estudianteList1OldEstudiante = em.merge(estudianteList1OldEstudiante);
                }
            }
            for (Estudiante estudianteList1NewEstudiante : estudianteList1New) {
                if (!estudianteList1Old.contains(estudianteList1NewEstudiante)) {
                    Municipio oldMunicipioidmunicipiocentrotrabajoOfEstudianteList1NewEstudiante = estudianteList1NewEstudiante.getMunicipioidmunicipiocentrotrabajo();
                    estudianteList1NewEstudiante.setMunicipioidmunicipiocentrotrabajo(municipio);
                    estudianteList1NewEstudiante = em.merge(estudianteList1NewEstudiante);
                    if (oldMunicipioidmunicipiocentrotrabajoOfEstudianteList1NewEstudiante != null && !oldMunicipioidmunicipiocentrotrabajoOfEstudianteList1NewEstudiante.equals(municipio)) {
                        oldMunicipioidmunicipiocentrotrabajoOfEstudianteList1NewEstudiante.getEstudianteList1().remove(estudianteList1NewEstudiante);
                        oldMunicipioidmunicipiocentrotrabajoOfEstudianteList1NewEstudiante = em.merge(oldMunicipioidmunicipiocentrotrabajoOfEstudianteList1NewEstudiante);
                    }
                }
            }
            for (Universidad universidadListNewUniversidad : universidadListNew) {
                if (!universidadListOld.contains(universidadListNewUniversidad)) {
                    Municipio oldMunicipioidmunicipioOfUniversidadListNewUniversidad = universidadListNewUniversidad.getMunicipioidmunicipio();
                    universidadListNewUniversidad.setMunicipioidmunicipio(municipio);
                    universidadListNewUniversidad = em.merge(universidadListNewUniversidad);
                    if (oldMunicipioidmunicipioOfUniversidadListNewUniversidad != null && !oldMunicipioidmunicipioOfUniversidadListNewUniversidad.equals(municipio)) {
                        oldMunicipioidmunicipioOfUniversidadListNewUniversidad.getUniversidadList().remove(universidadListNewUniversidad);
                        oldMunicipioidmunicipioOfUniversidadListNewUniversidad = em.merge(oldMunicipioidmunicipioOfUniversidadListNewUniversidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = municipio.getIdmunicipio();
                if (findMunicipio(id) == null) {
                    throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.");
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
            Municipio municipio;
            try {
                municipio = em.getReference(Municipio.class, id);
                municipio.getIdmunicipio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Facultad> facultadListOrphanCheck = municipio.getFacultadList();
            for (Facultad facultadListOrphanCheckFacultad : facultadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Municipio (" + municipio + ") cannot be destroyed since the Facultad " + facultadListOrphanCheckFacultad + " in its facultadList field has a non-nullable municipioidmunicipio field.");
            }
            List<Cum> cumListOrphanCheck = municipio.getCumList();
            for (Cum cumListOrphanCheckCum : cumListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Municipio (" + municipio + ") cannot be destroyed since the Cum " + cumListOrphanCheckCum + " in its cumList field has a non-nullable municipioidmunicipio field.");
            }
            List<Universidad> universidadListOrphanCheck = municipio.getUniversidadList();
            for (Universidad universidadListOrphanCheckUniversidad : universidadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Municipio (" + municipio + ") cannot be destroyed since the Universidad " + universidadListOrphanCheckUniversidad + " in its universidadList field has a non-nullable municipioidmunicipio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Provincia provinciaidprovincia = municipio.getProvinciaidprovincia();
            if (provinciaidprovincia != null) {
                provinciaidprovincia.getMunicipioList().remove(municipio);
                provinciaidprovincia = em.merge(provinciaidprovincia);
            }
            List<Estudiante> estudianteList = municipio.getEstudianteList();
            for (Estudiante estudianteListEstudiante : estudianteList) {
                estudianteListEstudiante.setMunicipioidmunicipio(null);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
            }
            List<Estudiante> estudianteList1 = municipio.getEstudianteList1();
            for (Estudiante estudianteList1Estudiante : estudianteList1) {
                estudianteList1Estudiante.setMunicipioidmunicipiocentrotrabajo(null);
                estudianteList1Estudiante = em.merge(estudianteList1Estudiante);
            }
            em.remove(municipio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Municipio> findMunicipioEntities() {
        return findMunicipioEntities(true, -1, -1);
    }

    public List<Municipio> findMunicipioEntities(int maxResults, int firstResult) {
        return findMunicipioEntities(false, maxResults, firstResult);
    }

    private List<Municipio> findMunicipioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Municipio.class));
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

    public Municipio findMunicipio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Municipio.class, id);
        } finally {
            em.close();
        }
    }

    public int getMunicipioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Municipio> rt = cq.from(Municipio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Municipio findMunicipioByNombreAndPais(String municipio, Integer idprovincia, Integer idpais) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p "
                    + "FROM Municipio p "
                    + "WHERE p.nombremunicipio= :municipio AND "
                    + "p.provinciaidprovincia.idprovincia= :provincia AND "
                    + "p.provinciaidprovincia.paisidpais.idpais= :idpais");
            q.setParameter("municipio", municipio);
            q.setParameter("provincia", idprovincia);
            q.setParameter("idpais", idpais);
            Municipio p = (Municipio) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Municipio findMunicipioByNombreAndPaisAvailable(String municipio, Integer idprovincia, Integer idpais) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p "
                    + "FROM Municipio p "
                    + "WHERE p.canceladomunicipio=false AND "
                    + "p.nombremunicipio= :municipio "
                    + "AND p.provinciaidprovincia.idprovincia= :provincia "
                    + "AND p.provinciaidprovincia.paisidpais.idpais= :pais");
            q.setParameter("municipio", municipio);
            q.setParameter("provincia", idprovincia);
            q.setParameter("pais", idpais);
            Municipio p = (Municipio) q.getSingleResult();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<String> findMunicipioAvailable(Integer idprovincia, Integer idpais) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p.nombremunicipio "
                    + "FROM Municipio p "
                    + "WHERE p.canceladomunicipio=false AND "
                    + "p.provinciaidprovincia.idprovincia= :provincia AND "
                    + "p.provinciaidprovincia.paisidpais.idpais= :pais AND "
                    + "p.idmunicipio>=0 "
                    + "ORDER BY p.nombremunicipio");
            q.setParameter("provincia", idprovincia);
            q.setParameter("pais", idpais);
            List<String> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Municipio> findMunicipioList() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT p "
                    + "FROM Municipio p "
                    + "WHERE p.idmunicipio>=0 "
                    + "ORDER BY p.nombremunicipio");
            List<Municipio> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
