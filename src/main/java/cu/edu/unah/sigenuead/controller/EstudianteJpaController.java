/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.controller;

import cu.edu.unah.sigenuead.controller.exceptions.IllegalOrphanException;
import cu.edu.unah.sigenuead.controller.exceptions.NonexistentEntityException;
import cu.edu.unah.sigenuead.controller.exceptions.PreexistingEntityException;
import cu.edu.unah.sigenuead.entity.ColorPiel;
import cu.edu.unah.sigenuead.entity.EspecialidadMilitar;
import cu.edu.unah.sigenuead.entity.EstadoCivil;
import cu.edu.unah.sigenuead.entity.Estudiante;
import cu.edu.unah.sigenuead.entity.ExamenMatriculaFacultadCumCarreraEstudianteAsignatura;
import cu.edu.unah.sigenuead.entity.FacultadCumCarreraEstudiante;
import cu.edu.unah.sigenuead.entity.GradoMilitar;
import cu.edu.unah.sigenuead.entity.Huerfano;
import cu.edu.unah.sigenuead.entity.Minusvalia;
import cu.edu.unah.sigenuead.entity.Municipio;
import cu.edu.unah.sigenuead.entity.NivelEscolar;
import cu.edu.unah.sigenuead.entity.Ocupacion;
import cu.edu.unah.sigenuead.entity.Ong;
import cu.edu.unah.sigenuead.entity.Organismo;
import cu.edu.unah.sigenuead.entity.OrganizacionPolitica;
import cu.edu.unah.sigenuead.entity.OrganizacionPopular;
import cu.edu.unah.sigenuead.entity.Pais;
import cu.edu.unah.sigenuead.entity.ProcedenciaEscolar;
import cu.edu.unah.sigenuead.entity.Provincia;
import cu.edu.unah.sigenuead.entity.Sexo;
import cu.edu.unah.sigenuead.entity.Sindicato;
import cu.edu.unah.sigenuead.entity.TipoMilitar;
import cu.edu.unah.sigenuead.entity.Tutor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EstudianteJpaController implements Serializable {

    public EstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estudiante estudiante) throws PreexistingEntityException, Exception {
        if (estudiante.getOrganizacionPopularList() == null) {
            estudiante.setOrganizacionPopularList(new ArrayList<OrganizacionPopular>());
        }
        if (estudiante.getMinusvaliaList() == null) {
            estudiante.setMinusvaliaList(new ArrayList<Minusvalia>());
        }
        if (estudiante.getOngList() == null) {
            estudiante.setOngList(new ArrayList<Ong>());
        }
        if (estudiante.getFacultadCumCarreraEstudianteList() == null) {
            estudiante.setFacultadCumCarreraEstudianteList(new ArrayList<FacultadCumCarreraEstudiante>());
        }
        if (estudiante.getTutorList() == null) {
            estudiante.setTutorList(new ArrayList<Tutor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ColorPiel colorPielcolorPielId = estudiante.getColorPielcolorPielId();
            if (colorPielcolorPielId != null) {
                colorPielcolorPielId = em.getReference(colorPielcolorPielId.getClass(), colorPielcolorPielId.getColorPielId());
                estudiante.setColorPielcolorPielId(colorPielcolorPielId);
            }
            EspecialidadMilitar especialidadMilitarespecialidadMilitarId = estudiante.getEspecialidadMilitarespecialidadMilitarId();
            if (especialidadMilitarespecialidadMilitarId != null) {
                especialidadMilitarespecialidadMilitarId = em.getReference(especialidadMilitarespecialidadMilitarId.getClass(), especialidadMilitarespecialidadMilitarId.getEspecialidadMilitarId());
                estudiante.setEspecialidadMilitarespecialidadMilitarId(especialidadMilitarespecialidadMilitarId);
            }
            EstadoCivil estadoCivilestadoCivilId = estudiante.getEstadoCivilestadoCivilId();
            if (estadoCivilestadoCivilId != null) {
                estadoCivilestadoCivilId = em.getReference(estadoCivilestadoCivilId.getClass(), estadoCivilestadoCivilId.getEstadoCivilId());
                estudiante.setEstadoCivilestadoCivilId(estadoCivilestadoCivilId);
            }
            GradoMilitar gradoMilitargradoMilitarId = estudiante.getGradoMilitargradoMilitarId();
            if (gradoMilitargradoMilitarId != null) {
                gradoMilitargradoMilitarId = em.getReference(gradoMilitargradoMilitarId.getClass(), gradoMilitargradoMilitarId.getGradoMilitarId());
                estudiante.setGradoMilitargradoMilitarId(gradoMilitargradoMilitarId);
            }
            ProcedenciaEscolar procedenciaEscolarprocedenciaEscolarId = estudiante.getProcedenciaEscolarprocedenciaEscolarId();
            if (procedenciaEscolarprocedenciaEscolarId != null) {
                procedenciaEscolarprocedenciaEscolarId = em.getReference(procedenciaEscolarprocedenciaEscolarId.getClass(), procedenciaEscolarprocedenciaEscolarId.getProcedenciaEscolarId());
                estudiante.setProcedenciaEscolarprocedenciaEscolarId(procedenciaEscolarprocedenciaEscolarId);
            }
            TipoMilitar tipoMilitaridTipoMilitar = estudiante.getTipoMilitaridTipoMilitar();
            if (tipoMilitaridTipoMilitar != null) {
                tipoMilitaridTipoMilitar = em.getReference(tipoMilitaridTipoMilitar.getClass(), tipoMilitaridTipoMilitar.getIdTipoMilitar());
                estudiante.setTipoMilitaridTipoMilitar(tipoMilitaridTipoMilitar);
            }
            Huerfano huerfanohuerfanoId = estudiante.getHuerfanohuerfanoId();
            if (huerfanohuerfanoId != null) {
                huerfanohuerfanoId = em.getReference(huerfanohuerfanoId.getClass(), huerfanohuerfanoId.getHuerfanoId());
                estudiante.setHuerfanohuerfanoId(huerfanohuerfanoId);
            }
            Municipio municipioidmunicipio = estudiante.getMunicipioidmunicipio();
            if (municipioidmunicipio != null) {
                municipioidmunicipio = em.getReference(municipioidmunicipio.getClass(), municipioidmunicipio.getIdmunicipio());
                estudiante.setMunicipioidmunicipio(municipioidmunicipio);
            }
            Municipio municipioidmunicipiocentrotrabajo = estudiante.getMunicipioidmunicipiocentrotrabajo();
            if (municipioidmunicipiocentrotrabajo != null) {
                municipioidmunicipiocentrotrabajo = em.getReference(municipioidmunicipiocentrotrabajo.getClass(), municipioidmunicipiocentrotrabajo.getIdmunicipio());
                estudiante.setMunicipioidmunicipiocentrotrabajo(municipioidmunicipiocentrotrabajo);
            }
            Pais paisidpais = estudiante.getPaisidpais();
            if (paisidpais != null) {
                paisidpais = em.getReference(paisidpais.getClass(), paisidpais.getIdpais());
                estudiante.setPaisidpais(paisidpais);
            }
            Provincia provinciaidprovincia = estudiante.getProvinciaidprovincia();
            if (provinciaidprovincia != null) {
                provinciaidprovincia = em.getReference(provinciaidprovincia.getClass(), provinciaidprovincia.getIdprovincia());
                estudiante.setProvinciaidprovincia(provinciaidprovincia);
            }
            Sexo sexosexoId = estudiante.getSexosexoId();
            if (sexosexoId != null) {
                sexosexoId = em.getReference(sexosexoId.getClass(), sexosexoId.getSexoId());
                estudiante.setSexosexoId(sexosexoId);
            }
            Sindicato sindicatoidsindicato = estudiante.getSindicatoidsindicato();
            if (sindicatoidsindicato != null) {
                sindicatoidsindicato = em.getReference(sindicatoidsindicato.getClass(), sindicatoidsindicato.getIdsindicato());
                estudiante.setSindicatoidsindicato(sindicatoidsindicato);
            }
            NivelEscolar nivelEscolarnivelEscolarId = estudiante.getNivelEscolarnivelEscolarId();
            if (nivelEscolarnivelEscolarId != null) {
                nivelEscolarnivelEscolarId = em.getReference(nivelEscolarnivelEscolarId.getClass(), nivelEscolarnivelEscolarId.getNivelEscolarId());
                estudiante.setNivelEscolarnivelEscolarId(nivelEscolarnivelEscolarId);
            }
            Ocupacion ocupacionocupacionId = estudiante.getOcupacionocupacionId();
            if (ocupacionocupacionId != null) {
                ocupacionocupacionId = em.getReference(ocupacionocupacionId.getClass(), ocupacionocupacionId.getOcupacionId());
                estudiante.setOcupacionocupacionId(ocupacionocupacionId);
            }
            Organismo organismoidorganismo = estudiante.getOrganismoidorganismo();
            if (organismoidorganismo != null) {
                organismoidorganismo = em.getReference(organismoidorganismo.getClass(), organismoidorganismo.getIdorganismo());
                estudiante.setOrganismoidorganismo(organismoidorganismo);
            }
            OrganizacionPolitica organizacionPoliticaorganizacionPoliticaId = estudiante.getOrganizacionPoliticaorganizacionPoliticaId();
            if (organizacionPoliticaorganizacionPoliticaId != null) {
                organizacionPoliticaorganizacionPoliticaId = em.getReference(organizacionPoliticaorganizacionPoliticaId.getClass(), organizacionPoliticaorganizacionPoliticaId.getOrganizacionPoliticaId());
                estudiante.setOrganizacionPoliticaorganizacionPoliticaId(organizacionPoliticaorganizacionPoliticaId);
            }
            List<OrganizacionPopular> attachedOrganizacionPopularList = new ArrayList<OrganizacionPopular>();
            for (OrganizacionPopular organizacionPopularListOrganizacionPopularToAttach : estudiante.getOrganizacionPopularList()) {
                organizacionPopularListOrganizacionPopularToAttach = em.getReference(organizacionPopularListOrganizacionPopularToAttach.getClass(), organizacionPopularListOrganizacionPopularToAttach.getOrganizacionPopularId());
                attachedOrganizacionPopularList.add(organizacionPopularListOrganizacionPopularToAttach);
            }
            estudiante.setOrganizacionPopularList(attachedOrganizacionPopularList);
            List<Minusvalia> attachedMinusvaliaList = new ArrayList<Minusvalia>();
            for (Minusvalia minusvaliaListMinusvaliaToAttach : estudiante.getMinusvaliaList()) {
                minusvaliaListMinusvaliaToAttach = em.getReference(minusvaliaListMinusvaliaToAttach.getClass(), minusvaliaListMinusvaliaToAttach.getMinusvaliaId());
                attachedMinusvaliaList.add(minusvaliaListMinusvaliaToAttach);
            }
            estudiante.setMinusvaliaList(attachedMinusvaliaList);
            List<Ong> attachedOngList = new ArrayList<Ong>();
            for (Ong ongListOngToAttach : estudiante.getOngList()) {
                ongListOngToAttach = em.getReference(ongListOngToAttach.getClass(), ongListOngToAttach.getOngId());
                attachedOngList.add(ongListOngToAttach);
            }
            estudiante.setOngList(attachedOngList);
            List<FacultadCumCarreraEstudiante> attachedFacultadCumCarreraEstudianteList = new ArrayList<FacultadCumCarreraEstudiante>();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach : estudiante.getFacultadCumCarreraEstudianteList()) {
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach = em.getReference(facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach.getClass(), facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach.getFacultadCumCarreraEstudiantePK());
                attachedFacultadCumCarreraEstudianteList.add(facultadCumCarreraEstudianteListFacultadCumCarreraEstudianteToAttach);
            }
            estudiante.setFacultadCumCarreraEstudianteList(attachedFacultadCumCarreraEstudianteList);
            List<Tutor> attachedTutorList = new ArrayList<Tutor>();
            for (Tutor tutorListTutorToAttach : estudiante.getTutorList()) {
                tutorListTutorToAttach = em.getReference(tutorListTutorToAttach.getClass(), tutorListTutorToAttach.getTutorId());
                attachedTutorList.add(tutorListTutorToAttach);
            }
            estudiante.setTutorList(attachedTutorList);
            em.persist(estudiante);
            if (colorPielcolorPielId != null) {
                colorPielcolorPielId.getEstudianteList().add(estudiante);
                colorPielcolorPielId = em.merge(colorPielcolorPielId);
            }
            if (especialidadMilitarespecialidadMilitarId != null) {
                especialidadMilitarespecialidadMilitarId.getEstudianteList().add(estudiante);
                especialidadMilitarespecialidadMilitarId = em.merge(especialidadMilitarespecialidadMilitarId);
            }
            if (estadoCivilestadoCivilId != null) {
                estadoCivilestadoCivilId.getEstudianteList().add(estudiante);
                estadoCivilestadoCivilId = em.merge(estadoCivilestadoCivilId);
            }
            if (gradoMilitargradoMilitarId != null) {
                gradoMilitargradoMilitarId.getEstudianteList().add(estudiante);
                gradoMilitargradoMilitarId = em.merge(gradoMilitargradoMilitarId);
            }
            if (procedenciaEscolarprocedenciaEscolarId != null) {
                procedenciaEscolarprocedenciaEscolarId.getEstudianteList().add(estudiante);
                procedenciaEscolarprocedenciaEscolarId = em.merge(procedenciaEscolarprocedenciaEscolarId);
            }
            if (tipoMilitaridTipoMilitar != null) {
                tipoMilitaridTipoMilitar.getEstudianteList().add(estudiante);
                tipoMilitaridTipoMilitar = em.merge(tipoMilitaridTipoMilitar);
            }
            if (huerfanohuerfanoId != null) {
                huerfanohuerfanoId.getEstudianteList().add(estudiante);
                huerfanohuerfanoId = em.merge(huerfanohuerfanoId);
            }
            if (municipioidmunicipio != null) {
                municipioidmunicipio.getEstudianteList().add(estudiante);
                municipioidmunicipio = em.merge(municipioidmunicipio);
            }
            if (municipioidmunicipiocentrotrabajo != null) {
                municipioidmunicipiocentrotrabajo.getEstudianteList().add(estudiante);
                municipioidmunicipiocentrotrabajo = em.merge(municipioidmunicipiocentrotrabajo);
            }
            if (paisidpais != null) {
                paisidpais.getEstudianteList().add(estudiante);
                paisidpais = em.merge(paisidpais);
            }
            if (provinciaidprovincia != null) {
                provinciaidprovincia.getEstudianteList().add(estudiante);
                provinciaidprovincia = em.merge(provinciaidprovincia);
            }
            if (sexosexoId != null) {
                sexosexoId.getEstudianteList().add(estudiante);
                sexosexoId = em.merge(sexosexoId);
            }
            if (sindicatoidsindicato != null) {
                sindicatoidsindicato.getEstudianteList().add(estudiante);
                sindicatoidsindicato = em.merge(sindicatoidsindicato);
            }
            if (nivelEscolarnivelEscolarId != null) {
                nivelEscolarnivelEscolarId.getEstudianteList().add(estudiante);
                nivelEscolarnivelEscolarId = em.merge(nivelEscolarnivelEscolarId);
            }
            if (ocupacionocupacionId != null) {
                ocupacionocupacionId.getEstudianteList().add(estudiante);
                ocupacionocupacionId = em.merge(ocupacionocupacionId);
            }
            if (organismoidorganismo != null) {
                organismoidorganismo.getEstudianteList().add(estudiante);
                organismoidorganismo = em.merge(organismoidorganismo);
            }
            if (organizacionPoliticaorganizacionPoliticaId != null) {
                organizacionPoliticaorganizacionPoliticaId.getEstudianteList().add(estudiante);
                organizacionPoliticaorganizacionPoliticaId = em.merge(organizacionPoliticaorganizacionPoliticaId);
            }
            for (OrganizacionPopular organizacionPopularListOrganizacionPopular : estudiante.getOrganizacionPopularList()) {
                organizacionPopularListOrganizacionPopular.getEstudianteList().add(estudiante);
                organizacionPopularListOrganizacionPopular = em.merge(organizacionPopularListOrganizacionPopular);
            }
            for (Minusvalia minusvaliaListMinusvalia : estudiante.getMinusvaliaList()) {
                minusvaliaListMinusvalia.getEstudianteList().add(estudiante);
                minusvaliaListMinusvalia = em.merge(minusvaliaListMinusvalia);
            }
            for (Ong ongListOng : estudiante.getOngList()) {
                ongListOng.getEstudianteList().add(estudiante);
                ongListOng = em.merge(ongListOng);
            }
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante : estudiante.getFacultadCumCarreraEstudianteList()) {
                Estudiante oldEstudianteOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.getEstudiante();
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.setEstudiante(estudiante);
                facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                if (oldEstudianteOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante != null) {
                    oldEstudianteOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                    oldEstudianteOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante = em.merge(oldEstudianteOfFacultadCumCarreraEstudianteListFacultadCumCarreraEstudiante);
                }
            }
            for (Tutor tutorListTutor : estudiante.getTutorList()) {
                Estudiante oldEstudianteestudianteIdOfTutorListTutor = tutorListTutor.getEstudianteestudianteId();
                tutorListTutor.setEstudianteestudianteId(estudiante);
                tutorListTutor = em.merge(tutorListTutor);
                if (oldEstudianteestudianteIdOfTutorListTutor != null) {
                    oldEstudianteestudianteIdOfTutorListTutor.getTutorList().remove(tutorListTutor);
                    oldEstudianteestudianteIdOfTutorListTutor = em.merge(oldEstudianteestudianteIdOfTutorListTutor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstudiante(estudiante.getEstudianteId()) != null) {
                throw new PreexistingEntityException("Estudiante " + estudiante + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudiante estudiante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante persistentEstudiante = em.find(Estudiante.class, estudiante.getEstudianteId());
            ColorPiel colorPielcolorPielIdOld = persistentEstudiante.getColorPielcolorPielId();
            ColorPiel colorPielcolorPielIdNew = estudiante.getColorPielcolorPielId();
            EspecialidadMilitar especialidadMilitarespecialidadMilitarIdOld = persistentEstudiante.getEspecialidadMilitarespecialidadMilitarId();
            EspecialidadMilitar especialidadMilitarespecialidadMilitarIdNew = estudiante.getEspecialidadMilitarespecialidadMilitarId();
            EstadoCivil estadoCivilestadoCivilIdOld = persistentEstudiante.getEstadoCivilestadoCivilId();
            EstadoCivil estadoCivilestadoCivilIdNew = estudiante.getEstadoCivilestadoCivilId();
            GradoMilitar gradoMilitargradoMilitarIdOld = persistentEstudiante.getGradoMilitargradoMilitarId();
            GradoMilitar gradoMilitargradoMilitarIdNew = estudiante.getGradoMilitargradoMilitarId();
            ProcedenciaEscolar procedenciaEscolarprocedenciaEscolarIdOld = persistentEstudiante.getProcedenciaEscolarprocedenciaEscolarId();
            ProcedenciaEscolar procedenciaEscolarprocedenciaEscolarIdNew = estudiante.getProcedenciaEscolarprocedenciaEscolarId();
            TipoMilitar tipoMilitaridTipoMilitarOld = persistentEstudiante.getTipoMilitaridTipoMilitar();
            TipoMilitar tipoMilitaridTipoMilitarNew = estudiante.getTipoMilitaridTipoMilitar();
            Huerfano huerfanohuerfanoIdOld = persistentEstudiante.getHuerfanohuerfanoId();
            Huerfano huerfanohuerfanoIdNew = estudiante.getHuerfanohuerfanoId();
            Municipio municipioidmunicipioOld = persistentEstudiante.getMunicipioidmunicipio();
            Municipio municipioidmunicipioNew = estudiante.getMunicipioidmunicipio();
            Municipio municipioidmunicipiocentrotrabajoOld = persistentEstudiante.getMunicipioidmunicipiocentrotrabajo();
            Municipio municipioidmunicipiocentrotrabajoNew = estudiante.getMunicipioidmunicipiocentrotrabajo();
            Pais paisidpaisOld = persistentEstudiante.getPaisidpais();
            Pais paisidpaisNew = estudiante.getPaisidpais();
            Provincia provinciaidprovinciaOld = persistentEstudiante.getProvinciaidprovincia();
            Provincia provinciaidprovinciaNew = estudiante.getProvinciaidprovincia();
            Sexo sexosexoIdOld = persistentEstudiante.getSexosexoId();
            Sexo sexosexoIdNew = estudiante.getSexosexoId();
            Sindicato sindicatoidsindicatoOld = persistentEstudiante.getSindicatoidsindicato();
            Sindicato sindicatoidsindicatoNew = estudiante.getSindicatoidsindicato();
            NivelEscolar nivelEscolarnivelEscolarIdOld = persistentEstudiante.getNivelEscolarnivelEscolarId();
            NivelEscolar nivelEscolarnivelEscolarIdNew = estudiante.getNivelEscolarnivelEscolarId();
            Ocupacion ocupacionocupacionIdOld = persistentEstudiante.getOcupacionocupacionId();
            Ocupacion ocupacionocupacionIdNew = estudiante.getOcupacionocupacionId();
            Organismo organismoidorganismoOld = persistentEstudiante.getOrganismoidorganismo();
            Organismo organismoidorganismoNew = estudiante.getOrganismoidorganismo();
            OrganizacionPolitica organizacionPoliticaorganizacionPoliticaIdOld = persistentEstudiante.getOrganizacionPoliticaorganizacionPoliticaId();
            OrganizacionPolitica organizacionPoliticaorganizacionPoliticaIdNew = estudiante.getOrganizacionPoliticaorganizacionPoliticaId();
            List<OrganizacionPopular> organizacionPopularListOld = persistentEstudiante.getOrganizacionPopularList();
            List<OrganizacionPopular> organizacionPopularListNew = estudiante.getOrganizacionPopularList();
            List<Minusvalia> minusvaliaListOld = persistentEstudiante.getMinusvaliaList();
            List<Minusvalia> minusvaliaListNew = estudiante.getMinusvaliaList();
            List<Ong> ongListOld = persistentEstudiante.getOngList();
            List<Ong> ongListNew = estudiante.getOngList();
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListOld = persistentEstudiante.getFacultadCumCarreraEstudianteList();
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListNew = estudiante.getFacultadCumCarreraEstudianteList();
            List<Tutor> tutorListOld = persistentEstudiante.getTutorList();
            List<Tutor> tutorListNew = estudiante.getTutorList();
            List<String> illegalOrphanMessages = null;
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListOld) {
                if (!facultadCumCarreraEstudianteListNew.contains(facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FacultadCumCarreraEstudiante " + facultadCumCarreraEstudianteListOldFacultadCumCarreraEstudiante + " since its estudiante field is not nullable.");
                }
            }
            for (Tutor tutorListOldTutor : tutorListOld) {
                if (!tutorListNew.contains(tutorListOldTutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tutor " + tutorListOldTutor + " since its estudianteestudianteId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (colorPielcolorPielIdNew != null) {
                colorPielcolorPielIdNew = em.getReference(colorPielcolorPielIdNew.getClass(), colorPielcolorPielIdNew.getColorPielId());
                estudiante.setColorPielcolorPielId(colorPielcolorPielIdNew);
            }
            if (especialidadMilitarespecialidadMilitarIdNew != null) {
                especialidadMilitarespecialidadMilitarIdNew = em.getReference(especialidadMilitarespecialidadMilitarIdNew.getClass(), especialidadMilitarespecialidadMilitarIdNew.getEspecialidadMilitarId());
                estudiante.setEspecialidadMilitarespecialidadMilitarId(especialidadMilitarespecialidadMilitarIdNew);
            }
            if (estadoCivilestadoCivilIdNew != null) {
                estadoCivilestadoCivilIdNew = em.getReference(estadoCivilestadoCivilIdNew.getClass(), estadoCivilestadoCivilIdNew.getEstadoCivilId());
                estudiante.setEstadoCivilestadoCivilId(estadoCivilestadoCivilIdNew);
            }
            if (gradoMilitargradoMilitarIdNew != null) {
                gradoMilitargradoMilitarIdNew = em.getReference(gradoMilitargradoMilitarIdNew.getClass(), gradoMilitargradoMilitarIdNew.getGradoMilitarId());
                estudiante.setGradoMilitargradoMilitarId(gradoMilitargradoMilitarIdNew);
            }
            if (procedenciaEscolarprocedenciaEscolarIdNew != null) {
                procedenciaEscolarprocedenciaEscolarIdNew = em.getReference(procedenciaEscolarprocedenciaEscolarIdNew.getClass(), procedenciaEscolarprocedenciaEscolarIdNew.getProcedenciaEscolarId());
                estudiante.setProcedenciaEscolarprocedenciaEscolarId(procedenciaEscolarprocedenciaEscolarIdNew);
            }
            if (tipoMilitaridTipoMilitarNew != null) {
                tipoMilitaridTipoMilitarNew = em.getReference(tipoMilitaridTipoMilitarNew.getClass(), tipoMilitaridTipoMilitarNew.getIdTipoMilitar());
                estudiante.setTipoMilitaridTipoMilitar(tipoMilitaridTipoMilitarNew);
            }
            if (huerfanohuerfanoIdNew != null) {
                huerfanohuerfanoIdNew = em.getReference(huerfanohuerfanoIdNew.getClass(), huerfanohuerfanoIdNew.getHuerfanoId());
                estudiante.setHuerfanohuerfanoId(huerfanohuerfanoIdNew);
            }
            if (municipioidmunicipioNew != null) {
                municipioidmunicipioNew = em.getReference(municipioidmunicipioNew.getClass(), municipioidmunicipioNew.getIdmunicipio());
                estudiante.setMunicipioidmunicipio(municipioidmunicipioNew);
            }
            if (municipioidmunicipiocentrotrabajoNew != null) {
                municipioidmunicipiocentrotrabajoNew = em.getReference(municipioidmunicipiocentrotrabajoNew.getClass(), municipioidmunicipiocentrotrabajoNew.getIdmunicipio());
                estudiante.setMunicipioidmunicipiocentrotrabajo(municipioidmunicipiocentrotrabajoNew);
            }
            if (paisidpaisNew != null) {
                paisidpaisNew = em.getReference(paisidpaisNew.getClass(), paisidpaisNew.getIdpais());
                estudiante.setPaisidpais(paisidpaisNew);
            }
            if (provinciaidprovinciaNew != null) {
                provinciaidprovinciaNew = em.getReference(provinciaidprovinciaNew.getClass(), provinciaidprovinciaNew.getIdprovincia());
                estudiante.setProvinciaidprovincia(provinciaidprovinciaNew);
            }
            if (sexosexoIdNew != null) {
                sexosexoIdNew = em.getReference(sexosexoIdNew.getClass(), sexosexoIdNew.getSexoId());
                estudiante.setSexosexoId(sexosexoIdNew);
            }
            if (sindicatoidsindicatoNew != null) {
                sindicatoidsindicatoNew = em.getReference(sindicatoidsindicatoNew.getClass(), sindicatoidsindicatoNew.getIdsindicato());
                estudiante.setSindicatoidsindicato(sindicatoidsindicatoNew);
            }
            if (nivelEscolarnivelEscolarIdNew != null) {
                nivelEscolarnivelEscolarIdNew = em.getReference(nivelEscolarnivelEscolarIdNew.getClass(), nivelEscolarnivelEscolarIdNew.getNivelEscolarId());
                estudiante.setNivelEscolarnivelEscolarId(nivelEscolarnivelEscolarIdNew);
            }
            if (ocupacionocupacionIdNew != null) {
                ocupacionocupacionIdNew = em.getReference(ocupacionocupacionIdNew.getClass(), ocupacionocupacionIdNew.getOcupacionId());
                estudiante.setOcupacionocupacionId(ocupacionocupacionIdNew);
            }
            if (organismoidorganismoNew != null) {
                organismoidorganismoNew = em.getReference(organismoidorganismoNew.getClass(), organismoidorganismoNew.getIdorganismo());
                estudiante.setOrganismoidorganismo(organismoidorganismoNew);
            }
            if (organizacionPoliticaorganizacionPoliticaIdNew != null) {
                organizacionPoliticaorganizacionPoliticaIdNew = em.getReference(organizacionPoliticaorganizacionPoliticaIdNew.getClass(), organizacionPoliticaorganizacionPoliticaIdNew.getOrganizacionPoliticaId());
                estudiante.setOrganizacionPoliticaorganizacionPoliticaId(organizacionPoliticaorganizacionPoliticaIdNew);
            }
            List<OrganizacionPopular> attachedOrganizacionPopularListNew = new ArrayList<OrganizacionPopular>();
            for (OrganizacionPopular organizacionPopularListNewOrganizacionPopularToAttach : organizacionPopularListNew) {
                organizacionPopularListNewOrganizacionPopularToAttach = em.getReference(organizacionPopularListNewOrganizacionPopularToAttach.getClass(), organizacionPopularListNewOrganizacionPopularToAttach.getOrganizacionPopularId());
                attachedOrganizacionPopularListNew.add(organizacionPopularListNewOrganizacionPopularToAttach);
            }
            organizacionPopularListNew = attachedOrganizacionPopularListNew;
            estudiante.setOrganizacionPopularList(organizacionPopularListNew);
            List<Minusvalia> attachedMinusvaliaListNew = new ArrayList<Minusvalia>();
            for (Minusvalia minusvaliaListNewMinusvaliaToAttach : minusvaliaListNew) {
                minusvaliaListNewMinusvaliaToAttach = em.getReference(minusvaliaListNewMinusvaliaToAttach.getClass(), minusvaliaListNewMinusvaliaToAttach.getMinusvaliaId());
                attachedMinusvaliaListNew.add(minusvaliaListNewMinusvaliaToAttach);
            }
            minusvaliaListNew = attachedMinusvaliaListNew;
            estudiante.setMinusvaliaList(minusvaliaListNew);
            List<Ong> attachedOngListNew = new ArrayList<Ong>();
            for (Ong ongListNewOngToAttach : ongListNew) {
                ongListNewOngToAttach = em.getReference(ongListNewOngToAttach.getClass(), ongListNewOngToAttach.getOngId());
                attachedOngListNew.add(ongListNewOngToAttach);
            }
            ongListNew = attachedOngListNew;
            estudiante.setOngList(ongListNew);
            List<FacultadCumCarreraEstudiante> attachedFacultadCumCarreraEstudianteListNew = new ArrayList<FacultadCumCarreraEstudiante>();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach : facultadCumCarreraEstudianteListNew) {
                facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach = em.getReference(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach.getClass(), facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach.getFacultadCumCarreraEstudiantePK());
                attachedFacultadCumCarreraEstudianteListNew.add(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudianteToAttach);
            }
            facultadCumCarreraEstudianteListNew = attachedFacultadCumCarreraEstudianteListNew;
            estudiante.setFacultadCumCarreraEstudianteList(facultadCumCarreraEstudianteListNew);
            List<Tutor> attachedTutorListNew = new ArrayList<Tutor>();
            for (Tutor tutorListNewTutorToAttach : tutorListNew) {
                tutorListNewTutorToAttach = em.getReference(tutorListNewTutorToAttach.getClass(), tutorListNewTutorToAttach.getTutorId());
                attachedTutorListNew.add(tutorListNewTutorToAttach);
            }
            tutorListNew = attachedTutorListNew;
            estudiante.setTutorList(tutorListNew);
            estudiante = em.merge(estudiante);
            if (colorPielcolorPielIdOld != null && !colorPielcolorPielIdOld.equals(colorPielcolorPielIdNew)) {
                colorPielcolorPielIdOld.getEstudianteList().remove(estudiante);
                colorPielcolorPielIdOld = em.merge(colorPielcolorPielIdOld);
            }
            if (colorPielcolorPielIdNew != null && !colorPielcolorPielIdNew.equals(colorPielcolorPielIdOld)) {
                colorPielcolorPielIdNew.getEstudianteList().add(estudiante);
                colorPielcolorPielIdNew = em.merge(colorPielcolorPielIdNew);
            }
            if (especialidadMilitarespecialidadMilitarIdOld != null && !especialidadMilitarespecialidadMilitarIdOld.equals(especialidadMilitarespecialidadMilitarIdNew)) {
                especialidadMilitarespecialidadMilitarIdOld.getEstudianteList().remove(estudiante);
                especialidadMilitarespecialidadMilitarIdOld = em.merge(especialidadMilitarespecialidadMilitarIdOld);
            }
            if (especialidadMilitarespecialidadMilitarIdNew != null && !especialidadMilitarespecialidadMilitarIdNew.equals(especialidadMilitarespecialidadMilitarIdOld)) {
                especialidadMilitarespecialidadMilitarIdNew.getEstudianteList().add(estudiante);
                especialidadMilitarespecialidadMilitarIdNew = em.merge(especialidadMilitarespecialidadMilitarIdNew);
            }
            if (estadoCivilestadoCivilIdOld != null && !estadoCivilestadoCivilIdOld.equals(estadoCivilestadoCivilIdNew)) {
                estadoCivilestadoCivilIdOld.getEstudianteList().remove(estudiante);
                estadoCivilestadoCivilIdOld = em.merge(estadoCivilestadoCivilIdOld);
            }
            if (estadoCivilestadoCivilIdNew != null && !estadoCivilestadoCivilIdNew.equals(estadoCivilestadoCivilIdOld)) {
                estadoCivilestadoCivilIdNew.getEstudianteList().add(estudiante);
                estadoCivilestadoCivilIdNew = em.merge(estadoCivilestadoCivilIdNew);
            }
            if (gradoMilitargradoMilitarIdOld != null && !gradoMilitargradoMilitarIdOld.equals(gradoMilitargradoMilitarIdNew)) {
                gradoMilitargradoMilitarIdOld.getEstudianteList().remove(estudiante);
                gradoMilitargradoMilitarIdOld = em.merge(gradoMilitargradoMilitarIdOld);
            }
            if (gradoMilitargradoMilitarIdNew != null && !gradoMilitargradoMilitarIdNew.equals(gradoMilitargradoMilitarIdOld)) {
                gradoMilitargradoMilitarIdNew.getEstudianteList().add(estudiante);
                gradoMilitargradoMilitarIdNew = em.merge(gradoMilitargradoMilitarIdNew);
            }
            if (procedenciaEscolarprocedenciaEscolarIdOld != null && !procedenciaEscolarprocedenciaEscolarIdOld.equals(procedenciaEscolarprocedenciaEscolarIdNew)) {
                procedenciaEscolarprocedenciaEscolarIdOld.getEstudianteList().remove(estudiante);
                procedenciaEscolarprocedenciaEscolarIdOld = em.merge(procedenciaEscolarprocedenciaEscolarIdOld);
            }
            if (procedenciaEscolarprocedenciaEscolarIdNew != null && !procedenciaEscolarprocedenciaEscolarIdNew.equals(procedenciaEscolarprocedenciaEscolarIdOld)) {
                procedenciaEscolarprocedenciaEscolarIdNew.getEstudianteList().add(estudiante);
                procedenciaEscolarprocedenciaEscolarIdNew = em.merge(procedenciaEscolarprocedenciaEscolarIdNew);
            }
            if (tipoMilitaridTipoMilitarOld != null && !tipoMilitaridTipoMilitarOld.equals(tipoMilitaridTipoMilitarNew)) {
                tipoMilitaridTipoMilitarOld.getEstudianteList().remove(estudiante);
                tipoMilitaridTipoMilitarOld = em.merge(tipoMilitaridTipoMilitarOld);
            }
            if (tipoMilitaridTipoMilitarNew != null && !tipoMilitaridTipoMilitarNew.equals(tipoMilitaridTipoMilitarOld)) {
                tipoMilitaridTipoMilitarNew.getEstudianteList().add(estudiante);
                tipoMilitaridTipoMilitarNew = em.merge(tipoMilitaridTipoMilitarNew);
            }
            if (huerfanohuerfanoIdOld != null && !huerfanohuerfanoIdOld.equals(huerfanohuerfanoIdNew)) {
                huerfanohuerfanoIdOld.getEstudianteList().remove(estudiante);
                huerfanohuerfanoIdOld = em.merge(huerfanohuerfanoIdOld);
            }
            if (huerfanohuerfanoIdNew != null && !huerfanohuerfanoIdNew.equals(huerfanohuerfanoIdOld)) {
                huerfanohuerfanoIdNew.getEstudianteList().add(estudiante);
                huerfanohuerfanoIdNew = em.merge(huerfanohuerfanoIdNew);
            }
            if (municipioidmunicipioOld != null && !municipioidmunicipioOld.equals(municipioidmunicipioNew)) {
                municipioidmunicipioOld.getEstudianteList().remove(estudiante);
                municipioidmunicipioOld = em.merge(municipioidmunicipioOld);
            }
            if (municipioidmunicipioNew != null && !municipioidmunicipioNew.equals(municipioidmunicipioOld)) {
                municipioidmunicipioNew.getEstudianteList().add(estudiante);
                municipioidmunicipioNew = em.merge(municipioidmunicipioNew);
            }
            if (municipioidmunicipiocentrotrabajoOld != null && !municipioidmunicipiocentrotrabajoOld.equals(municipioidmunicipiocentrotrabajoNew)) {
                municipioidmunicipiocentrotrabajoOld.getEstudianteList().remove(estudiante);
                municipioidmunicipiocentrotrabajoOld = em.merge(municipioidmunicipiocentrotrabajoOld);
            }
            if (municipioidmunicipiocentrotrabajoNew != null && !municipioidmunicipiocentrotrabajoNew.equals(municipioidmunicipiocentrotrabajoOld)) {
                municipioidmunicipiocentrotrabajoNew.getEstudianteList().add(estudiante);
                municipioidmunicipiocentrotrabajoNew = em.merge(municipioidmunicipiocentrotrabajoNew);
            }
            if (paisidpaisOld != null && !paisidpaisOld.equals(paisidpaisNew)) {
                paisidpaisOld.getEstudianteList().remove(estudiante);
                paisidpaisOld = em.merge(paisidpaisOld);
            }
            if (paisidpaisNew != null && !paisidpaisNew.equals(paisidpaisOld)) {
                paisidpaisNew.getEstudianteList().add(estudiante);
                paisidpaisNew = em.merge(paisidpaisNew);
            }
            if (provinciaidprovinciaOld != null && !provinciaidprovinciaOld.equals(provinciaidprovinciaNew)) {
                provinciaidprovinciaOld.getEstudianteList().remove(estudiante);
                provinciaidprovinciaOld = em.merge(provinciaidprovinciaOld);
            }
            if (provinciaidprovinciaNew != null && !provinciaidprovinciaNew.equals(provinciaidprovinciaOld)) {
                provinciaidprovinciaNew.getEstudianteList().add(estudiante);
                provinciaidprovinciaNew = em.merge(provinciaidprovinciaNew);
            }
            if (sexosexoIdOld != null && !sexosexoIdOld.equals(sexosexoIdNew)) {
                sexosexoIdOld.getEstudianteList().remove(estudiante);
                sexosexoIdOld = em.merge(sexosexoIdOld);
            }
            if (sexosexoIdNew != null && !sexosexoIdNew.equals(sexosexoIdOld)) {
                sexosexoIdNew.getEstudianteList().add(estudiante);
                sexosexoIdNew = em.merge(sexosexoIdNew);
            }
            if (sindicatoidsindicatoOld != null && !sindicatoidsindicatoOld.equals(sindicatoidsindicatoNew)) {
                sindicatoidsindicatoOld.getEstudianteList().remove(estudiante);
                sindicatoidsindicatoOld = em.merge(sindicatoidsindicatoOld);
            }
            if (sindicatoidsindicatoNew != null && !sindicatoidsindicatoNew.equals(sindicatoidsindicatoOld)) {
                sindicatoidsindicatoNew.getEstudianteList().add(estudiante);
                sindicatoidsindicatoNew = em.merge(sindicatoidsindicatoNew);
            }
            if (nivelEscolarnivelEscolarIdOld != null && !nivelEscolarnivelEscolarIdOld.equals(nivelEscolarnivelEscolarIdNew)) {
                nivelEscolarnivelEscolarIdOld.getEstudianteList().remove(estudiante);
                nivelEscolarnivelEscolarIdOld = em.merge(nivelEscolarnivelEscolarIdOld);
            }
            if (nivelEscolarnivelEscolarIdNew != null && !nivelEscolarnivelEscolarIdNew.equals(nivelEscolarnivelEscolarIdOld)) {
                nivelEscolarnivelEscolarIdNew.getEstudianteList().add(estudiante);
                nivelEscolarnivelEscolarIdNew = em.merge(nivelEscolarnivelEscolarIdNew);
            }
            if (ocupacionocupacionIdOld != null && !ocupacionocupacionIdOld.equals(ocupacionocupacionIdNew)) {
                ocupacionocupacionIdOld.getEstudianteList().remove(estudiante);
                ocupacionocupacionIdOld = em.merge(ocupacionocupacionIdOld);
            }
            if (ocupacionocupacionIdNew != null && !ocupacionocupacionIdNew.equals(ocupacionocupacionIdOld)) {
                ocupacionocupacionIdNew.getEstudianteList().add(estudiante);
                ocupacionocupacionIdNew = em.merge(ocupacionocupacionIdNew);
            }
            if (organismoidorganismoOld != null && !organismoidorganismoOld.equals(organismoidorganismoNew)) {
                organismoidorganismoOld.getEstudianteList().remove(estudiante);
                organismoidorganismoOld = em.merge(organismoidorganismoOld);
            }
            if (organismoidorganismoNew != null && !organismoidorganismoNew.equals(organismoidorganismoOld)) {
                organismoidorganismoNew.getEstudianteList().add(estudiante);
                organismoidorganismoNew = em.merge(organismoidorganismoNew);
            }
            if (organizacionPoliticaorganizacionPoliticaIdOld != null && !organizacionPoliticaorganizacionPoliticaIdOld.equals(organizacionPoliticaorganizacionPoliticaIdNew)) {
                organizacionPoliticaorganizacionPoliticaIdOld.getEstudianteList().remove(estudiante);
                organizacionPoliticaorganizacionPoliticaIdOld = em.merge(organizacionPoliticaorganizacionPoliticaIdOld);
            }
            if (organizacionPoliticaorganizacionPoliticaIdNew != null && !organizacionPoliticaorganizacionPoliticaIdNew.equals(organizacionPoliticaorganizacionPoliticaIdOld)) {
                organizacionPoliticaorganizacionPoliticaIdNew.getEstudianteList().add(estudiante);
                organizacionPoliticaorganizacionPoliticaIdNew = em.merge(organizacionPoliticaorganizacionPoliticaIdNew);
            }
            for (OrganizacionPopular organizacionPopularListOldOrganizacionPopular : organizacionPopularListOld) {
                if (!organizacionPopularListNew.contains(organizacionPopularListOldOrganizacionPopular)) {
                    organizacionPopularListOldOrganizacionPopular.getEstudianteList().remove(estudiante);
                    organizacionPopularListOldOrganizacionPopular = em.merge(organizacionPopularListOldOrganizacionPopular);
                }
            }
            for (OrganizacionPopular organizacionPopularListNewOrganizacionPopular : organizacionPopularListNew) {
                if (!organizacionPopularListOld.contains(organizacionPopularListNewOrganizacionPopular)) {
                    organizacionPopularListNewOrganizacionPopular.getEstudianteList().add(estudiante);
                    organizacionPopularListNewOrganizacionPopular = em.merge(organizacionPopularListNewOrganizacionPopular);
                }
            }
            for (Minusvalia minusvaliaListOldMinusvalia : minusvaliaListOld) {
                if (!minusvaliaListNew.contains(minusvaliaListOldMinusvalia)) {
                    minusvaliaListOldMinusvalia.getEstudianteList().remove(estudiante);
                    minusvaliaListOldMinusvalia = em.merge(minusvaliaListOldMinusvalia);
                }
            }
            for (Minusvalia minusvaliaListNewMinusvalia : minusvaliaListNew) {
                if (!minusvaliaListOld.contains(minusvaliaListNewMinusvalia)) {
                    minusvaliaListNewMinusvalia.getEstudianteList().add(estudiante);
                    minusvaliaListNewMinusvalia = em.merge(minusvaliaListNewMinusvalia);
                }
            }
            for (Ong ongListOldOng : ongListOld) {
                if (!ongListNew.contains(ongListOldOng)) {
                    ongListOldOng.getEstudianteList().remove(estudiante);
                    ongListOldOng = em.merge(ongListOldOng);
                }
            }
            for (Ong ongListNewOng : ongListNew) {
                if (!ongListOld.contains(ongListNewOng)) {
                    ongListNewOng.getEstudianteList().add(estudiante);
                    ongListNewOng = em.merge(ongListNewOng);
                }
            }
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListNew) {
                if (!facultadCumCarreraEstudianteListOld.contains(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante)) {
                    Estudiante oldEstudianteOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.getEstudiante();
                    facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.setEstudiante(estudiante);
                    facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = em.merge(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                    if (oldEstudianteOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante != null && !oldEstudianteOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.equals(estudiante)) {
                        oldEstudianteOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante.getFacultadCumCarreraEstudianteList().remove(facultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                        oldEstudianteOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante = em.merge(oldEstudianteOfFacultadCumCarreraEstudianteListNewFacultadCumCarreraEstudiante);
                    }
                }
            }
            for (Tutor tutorListNewTutor : tutorListNew) {
                if (!tutorListOld.contains(tutorListNewTutor)) {
                    Estudiante oldEstudianteestudianteIdOfTutorListNewTutor = tutorListNewTutor.getEstudianteestudianteId();
                    tutorListNewTutor.setEstudianteestudianteId(estudiante);
                    tutorListNewTutor = em.merge(tutorListNewTutor);
                    if (oldEstudianteestudianteIdOfTutorListNewTutor != null && !oldEstudianteestudianteIdOfTutorListNewTutor.equals(estudiante)) {
                        oldEstudianteestudianteIdOfTutorListNewTutor.getTutorList().remove(tutorListNewTutor);
                        oldEstudianteestudianteIdOfTutorListNewTutor = em.merge(oldEstudianteestudianteIdOfTutorListNewTutor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = estudiante.getEstudianteId();
                if (findEstudiante(id) == null) {
                    throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.");
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
            Estudiante estudiante;
            try {
                estudiante = em.getReference(Estudiante.class, id);
                estudiante.getEstudianteId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteListOrphanCheck = estudiante.getFacultadCumCarreraEstudianteList();
            for (FacultadCumCarreraEstudiante facultadCumCarreraEstudianteListOrphanCheckFacultadCumCarreraEstudiante : facultadCumCarreraEstudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the FacultadCumCarreraEstudiante " + facultadCumCarreraEstudianteListOrphanCheckFacultadCumCarreraEstudiante + " in its facultadCumCarreraEstudianteList field has a non-nullable estudiante field.");
            }
            List<Tutor> tutorListOrphanCheck = estudiante.getTutorList();
            for (Tutor tutorListOrphanCheckTutor : tutorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the Tutor " + tutorListOrphanCheckTutor + " in its tutorList field has a non-nullable estudianteestudianteId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ColorPiel colorPielcolorPielId = estudiante.getColorPielcolorPielId();
            if (colorPielcolorPielId != null) {
                colorPielcolorPielId.getEstudianteList().remove(estudiante);
                colorPielcolorPielId = em.merge(colorPielcolorPielId);
            }
            EspecialidadMilitar especialidadMilitarespecialidadMilitarId = estudiante.getEspecialidadMilitarespecialidadMilitarId();
            if (especialidadMilitarespecialidadMilitarId != null) {
                especialidadMilitarespecialidadMilitarId.getEstudianteList().remove(estudiante);
                especialidadMilitarespecialidadMilitarId = em.merge(especialidadMilitarespecialidadMilitarId);
            }
            EstadoCivil estadoCivilestadoCivilId = estudiante.getEstadoCivilestadoCivilId();
            if (estadoCivilestadoCivilId != null) {
                estadoCivilestadoCivilId.getEstudianteList().remove(estudiante);
                estadoCivilestadoCivilId = em.merge(estadoCivilestadoCivilId);
            }
            GradoMilitar gradoMilitargradoMilitarId = estudiante.getGradoMilitargradoMilitarId();
            if (gradoMilitargradoMilitarId != null) {
                gradoMilitargradoMilitarId.getEstudianteList().remove(estudiante);
                gradoMilitargradoMilitarId = em.merge(gradoMilitargradoMilitarId);
            }
            ProcedenciaEscolar procedenciaEscolarprocedenciaEscolarId = estudiante.getProcedenciaEscolarprocedenciaEscolarId();
            if (procedenciaEscolarprocedenciaEscolarId != null) {
                procedenciaEscolarprocedenciaEscolarId.getEstudianteList().remove(estudiante);
                procedenciaEscolarprocedenciaEscolarId = em.merge(procedenciaEscolarprocedenciaEscolarId);
            }
            TipoMilitar tipoMilitaridTipoMilitar = estudiante.getTipoMilitaridTipoMilitar();
            if (tipoMilitaridTipoMilitar != null) {
                tipoMilitaridTipoMilitar.getEstudianteList().remove(estudiante);
                tipoMilitaridTipoMilitar = em.merge(tipoMilitaridTipoMilitar);
            }
            Huerfano huerfanohuerfanoId = estudiante.getHuerfanohuerfanoId();
            if (huerfanohuerfanoId != null) {
                huerfanohuerfanoId.getEstudianteList().remove(estudiante);
                huerfanohuerfanoId = em.merge(huerfanohuerfanoId);
            }
            Municipio municipioidmunicipio = estudiante.getMunicipioidmunicipio();
            if (municipioidmunicipio != null) {
                municipioidmunicipio.getEstudianteList().remove(estudiante);
                municipioidmunicipio = em.merge(municipioidmunicipio);
            }
            Municipio municipioidmunicipiocentrotrabajo = estudiante.getMunicipioidmunicipiocentrotrabajo();
            if (municipioidmunicipiocentrotrabajo != null) {
                municipioidmunicipiocentrotrabajo.getEstudianteList().remove(estudiante);
                municipioidmunicipiocentrotrabajo = em.merge(municipioidmunicipiocentrotrabajo);
            }
            Pais paisidpais = estudiante.getPaisidpais();
            if (paisidpais != null) {
                paisidpais.getEstudianteList().remove(estudiante);
                paisidpais = em.merge(paisidpais);
            }
            Provincia provinciaidprovincia = estudiante.getProvinciaidprovincia();
            if (provinciaidprovincia != null) {
                provinciaidprovincia.getEstudianteList().remove(estudiante);
                provinciaidprovincia = em.merge(provinciaidprovincia);
            }
            Sexo sexosexoId = estudiante.getSexosexoId();
            if (sexosexoId != null) {
                sexosexoId.getEstudianteList().remove(estudiante);
                sexosexoId = em.merge(sexosexoId);
            }
            Sindicato sindicatoidsindicato = estudiante.getSindicatoidsindicato();
            if (sindicatoidsindicato != null) {
                sindicatoidsindicato.getEstudianteList().remove(estudiante);
                sindicatoidsindicato = em.merge(sindicatoidsindicato);
            }
            NivelEscolar nivelEscolarnivelEscolarId = estudiante.getNivelEscolarnivelEscolarId();
            if (nivelEscolarnivelEscolarId != null) {
                nivelEscolarnivelEscolarId.getEstudianteList().remove(estudiante);
                nivelEscolarnivelEscolarId = em.merge(nivelEscolarnivelEscolarId);
            }
            Ocupacion ocupacionocupacionId = estudiante.getOcupacionocupacionId();
            if (ocupacionocupacionId != null) {
                ocupacionocupacionId.getEstudianteList().remove(estudiante);
                ocupacionocupacionId = em.merge(ocupacionocupacionId);
            }
            Organismo organismoidorganismo = estudiante.getOrganismoidorganismo();
            if (organismoidorganismo != null) {
                organismoidorganismo.getEstudianteList().remove(estudiante);
                organismoidorganismo = em.merge(organismoidorganismo);
            }
            OrganizacionPolitica organizacionPoliticaorganizacionPoliticaId = estudiante.getOrganizacionPoliticaorganizacionPoliticaId();
            if (organizacionPoliticaorganizacionPoliticaId != null) {
                organizacionPoliticaorganizacionPoliticaId.getEstudianteList().remove(estudiante);
                organizacionPoliticaorganizacionPoliticaId = em.merge(organizacionPoliticaorganizacionPoliticaId);
            }
            List<OrganizacionPopular> organizacionPopularList = estudiante.getOrganizacionPopularList();
            for (OrganizacionPopular organizacionPopularListOrganizacionPopular : organizacionPopularList) {
                organizacionPopularListOrganizacionPopular.getEstudianteList().remove(estudiante);
                organizacionPopularListOrganizacionPopular = em.merge(organizacionPopularListOrganizacionPopular);
            }
            List<Minusvalia> minusvaliaList = estudiante.getMinusvaliaList();
            for (Minusvalia minusvaliaListMinusvalia : minusvaliaList) {
                minusvaliaListMinusvalia.getEstudianteList().remove(estudiante);
                minusvaliaListMinusvalia = em.merge(minusvaliaListMinusvalia);
            }
            List<Ong> ongList = estudiante.getOngList();
            for (Ong ongListOng : ongList) {
                ongListOng.getEstudianteList().remove(estudiante);
                ongListOng = em.merge(ongListOng);
            }
            em.remove(estudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudiante> findEstudianteEntities() {
        return findEstudianteEntities(true, -1, -1);
    }

    public List<Estudiante> findEstudianteEntities(int maxResults, int firstResult) {
        return findEstudianteEntities(false, maxResults, firstResult);
    }

    private List<Estudiante> findEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudiante.class));
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

    public Estudiante findEstudiante(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudiante> rt = cq.from(Estudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    //**********revisar*******************
//    public Estudiante findEstudianteActivo(String id) {
//        EntityManager em = getEntityManager();
//        try {
//            Query q = em.createQuery("SELECT e "
//                    + "FROM Estudiante e "
//                    + "WHERE e.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre='Activo' AND "
//                    + "e.estudianteId= :id");
//            q.setParameter("id", id);
//            Estudiante p = (Estudiante) q.getSingleResult();
//            return p;
//        } catch (Exception e) {
//            return null;
//        } finally {
//            em.close();
//        }
//    }

    public List<FacultadCumCarreraEstudiante> findEstudiantesNuevaMatriculaByCum(String cum, String carrera) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT fcc FROM Estudiante e JOIN FETCH e.facultadCumCarreraEstudianteList fcc WHERE fcc.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre='Nueva Matrícula' AND fcc.facultadCumCarrera.facultadCum.cum.nombrecum = :cum  AND fcc.facultadCumCarrera.carrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera");
            q.setParameter("cum", cum);
            q.setParameter("carrera", carrera);
            List<FacultadCumCarreraEstudiante> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<FacultadCumCarreraEstudiante> findEstudiantesNuevaMatriculaByFacultad(String fac, String carrera) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT fcc FROM Estudiante e JOIN FETCH e.facultadCumCarreraEstudianteList fcc WHERE fcc.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre='Nueva Matrícula' AND fcc.facultadCumCarrera.facultadCum.cum.nombrecum = :cum AND fcc.facultadCumCarrera.facultadCum.facultad.nombrearea = :facultad AND fcc.facultadCumCarrera.carrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera");
            q.setParameter("facultad", fac);
            q.setParameter("carrera", carrera);
            q.setParameter("cum", "");
            List<FacultadCumCarreraEstudiante> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

//    public Estudiante findEstudianteByAsignaturaForNota(String facultad, String carrera, String planEstudio, Date matricula, String asignatura, String convocatoria) {
//        EntityManager em = getEntityManager();
//        try {
//            Estudiante p = (Estudiante) em.createQuery("SELECT e.estudianteNombre+' '+e.estudianteApellido1+' '+e.estudianteApellido2 AS fullname, meae.matriculaEstudianteAsignaturaExamenNota FROM Estudiante e, MatriculaEstudianteAsignaturaExamen meae WHERE e.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre='Activo' and e.estudianteId like '" + id + "'").getSingleResult();
//            return p;
//        } catch (Exception e) {
//            return null;
//        } finally {
//            em.close();
//        }
//    }
    public List<FacultadCumCarreraEstudiante> findFacultadCumCarreraEstudiantesByPlanEstudio(String fac, String carrera, String planestudio) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT e FROM FacultadCumCarreraEstudiante e WHERE e.facultadCumCarreraEstudiantePK.facultadCumCarrerafacultadCumfacultadcodigoarea = :facultad AND e.facultadCumCarrera.carrera.carreranacionalidcarreranacional.nombrecarreranacional= :carrera AND e.planestudioidplanestudio.tipoplanestudionombretipoplanestudio.nombretipoplanestudio =:planestudio AND e.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre='Activo'");
            q.setParameter("facultad", fac);
            q.setParameter("carrera", carrera);
            q.setParameter("planestudio", planestudio);
            List<FacultadCumCarreraEstudiante> p = q.getResultList();
            return p;
        } catch (Exception e) {
            Logger.getLogger(EstudianteJpaController.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            em.close();
        }
    }

    public List<Estudiante> findEstudiantesByPlanEstudio(String fac, String carrera, String planestudio) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e.estudiante FROM FacultadCumCarreraEstudiante e WHERE e.facultadcodigo.nombrearea= :facultad AND e.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional= :carrera AND e.planestudioidplanestudio.tipoplanestudionombretipoplanestudio.nombretipoplanestudio=:planestudio AND e.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre='Activo'");
            q.setParameter("facultad", fac);
            q.setParameter("carrera", carrera);
            q.setParameter("planestudio", planestudio);
            List<Estudiante> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

//    public List<Estudiante> findEstudiantesParaPromover(String fac, String carrera) {
//        EntityManager em = getEntityManager();
//        try {
//            Query q = em.createQuery("SELECT DISTINCT e FROM Estudiante e JOIN FETCH e.asignaturaEstudianteList ae WHERE e.facultadcodigo.nombrearea= :facultad AND e.carreraidcarrera.carreranacionalidcarreranacional.nombrecarreranacional= :carrera AND ae.cancelada=FALSE AND ae.aprobada=true");
//            q.setParameter("facultad", fac);
//            q.setParameter("carrera", carrera);
//            List<Estudiante> p = q.getResultList();
//            return p;
//        } catch (Exception e) {
//            return null;
//        } finally {
//            em.close();
//        }
//    }

    public List<FacultadCumCarreraEstudiante> findEstudiantesActivosConAsignaturasPendientesByCum(String area, String carrera) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarreraEstudianteAsignaturaList fccea WHERE fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre='Activo' AND fcce.facultadCumCarrera.facultadCum.cum.nombrecum = :cum  AND fcce.facultadCumCarrera.carrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera AND fccea.aprobada=false AND fccea.cancelada=false");
            q.setParameter("cum", area);
            q.setParameter("carrera", carrera);
            List<FacultadCumCarreraEstudiante> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<FacultadCumCarreraEstudiante> findEstudiantesActivosByCum(String area, String carrera) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce WHERE fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre='Activo' AND fcce.facultadCumCarrera.facultadCum.cum.nombrecum = :cum  AND fcce.facultadCumCarrera.carrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera");
            q.setParameter("cum", area);
            q.setParameter("carrera", carrera);
            List<FacultadCumCarreraEstudiante> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<FacultadCumCarreraEstudiante> findEstudiantesActivosConAsignaturasPendientesByFacultad(String area, String carrera) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce JOIN FETCH fcce.facultadCumCarreraEstudianteAsignaturaList fccea WHERE fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre='Activo' AND fcce.facultadCumCarrera.facultadCum.facultad.nombrearea = :facultad AND fcce.facultadCumCarrera.facultadCum.cum.nombrecum=''  AND fcce.facultadCumCarrera.carrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera AND fccea.aprobada=false AND fccea.cancelada=false");
            q.setParameter("facultad", area);
            q.setParameter("carrera", carrera);
            List<FacultadCumCarreraEstudiante> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public List<FacultadCumCarreraEstudiante> findEstudiantesActivosByFacultad(String area, String carrera) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT DISTINCT fcce FROM FacultadCumCarreraEstudiante fcce WHERE fcce.estadoEstudianteestadoEstucianteId.estadoEstudianteNombre='Activo' AND fcce.facultadCumCarrera.facultadCum.facultad.nombrearea = :facultad AND fcce.facultadCumCarrera.facultadCum.cum.nombrecum=''  AND fcce.facultadCumCarrera.carrera.carreranacionalidcarreranacional.nombrecarreranacional=:carrera");
            q.setParameter("facultad", area);
            q.setParameter("carrera", carrera);
            List<FacultadCumCarreraEstudiante> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> findAsignaturasForNotasByExamen(FacultadCumCarreraEstudiante fcce, Integer examenId) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT e from ExamenMatriculaFacultadCumCarreraEstudianteAsignatura e WHERE e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.codigoarea=:facultad AND e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.codigocum=:cum AND e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.estudianteId=:estudiante AND e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.examenexamenId=:examen AND e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.fechaMatricula=:matricula AND e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.idcarrera=:carrera AND e.matriculaFacultadCumCarreraEstudianteAsignatura.actual=true AND e.matriculaFacultadCumCarreraEstudianteAsignatura.cancelada=false AND e.cancelado=false");
            q.setParameter("facultad", fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumfacultadcodigoarea());
            q.setParameter("cum", fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarrerafacultadCumcumcodigocum());
            q.setParameter("estudiante", fcce.getFacultadCumCarreraEstudiantePK().getEstudianteestudianteId());
            q.setParameter("examen", examenId);
            q.setParameter("matricula", fcce.getFacultadCumCarreraEstudiantePK().getFechaMatricula());
            q.setParameter("carrera", fcce.getFacultadCumCarreraEstudiantePK().getFacultadCumCarreracarreraidcarrera());
            List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> p = q.getResultList();
            return p;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
