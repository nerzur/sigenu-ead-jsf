/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author claudy
 */
@Entity
@Table(name = "tutor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t"),
    @NamedQuery(name = "Tutor.findByTutorId", query = "SELECT t FROM Tutor t WHERE t.tutorId = :tutorId"),
    @NamedQuery(name = "Tutor.findByNombreTutor", query = "SELECT t FROM Tutor t WHERE t.nombreTutor = :nombreTutor"),
    @NamedQuery(name = "Tutor.findByApellidosTutor", query = "SELECT t FROM Tutor t WHERE t.apellidosTutor = :apellidosTutor"),
    @NamedQuery(name = "Tutor.findBySalarioTutor", query = "SELECT t FROM Tutor t WHERE t.salarioTutor = :salarioTutor"),
    @NamedQuery(name = "Tutor.findByFallecido", query = "SELECT t FROM Tutor t WHERE t.fallecido = :fallecido")})
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tutor_id")
    private Integer tutorId;
    @Basic(optional = false)
    @Column(name = "nombre_tutor")
    private String nombreTutor;
    @Basic(optional = false)
    @Column(name = "apellidos_tutor")
    private String apellidosTutor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario_tutor")
    private Float salarioTutor;
    @Basic(optional = false)
    @Column(name = "fallecido")
    private boolean fallecido;
    @JoinColumn(name = "estudianteestudiante_id", referencedColumnName = "estudiante_id")
    @ManyToOne(optional = false)
    private Estudiante estudianteestudianteId;
    @JoinColumn(name = "nivel_escolarnivel_escolar_id", referencedColumnName = "nivel_escolar_id")
    @ManyToOne(optional = false)
    private NivelEscolar nivelEscolarnivelEscolarId;
    @JoinColumn(name = "ocupacionocupacion_id", referencedColumnName = "ocupacion_id")
    @ManyToOne(optional = false)
    private Ocupacion ocupacionocupacionId;
    @JoinColumn(name = "organizacion_politicaorganizacion_politica_id", referencedColumnName = "organizacion_politica_id")
    @ManyToOne(optional = false)
    private OrganizacionPolitica organizacionPoliticaorganizacionPoliticaId;
    @JoinColumn(name = "sexosexo_id", referencedColumnName = "sexo_id")
    @ManyToOne(optional = false)
    private Sexo sexosexoId;

    public Tutor() {
    }

    public Tutor(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public Tutor(Integer tutorId, String nombreTutor, String apellidosTutor, boolean fallecido) {
        this.tutorId = tutorId;
        this.nombreTutor = nombreTutor;
        this.apellidosTutor = apellidosTutor;
        this.fallecido = fallecido;
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public String getApellidosTutor() {
        return apellidosTutor;
    }

    public void setApellidosTutor(String apellidosTutor) {
        this.apellidosTutor = apellidosTutor;
    }

    public Float getSalarioTutor() {
        return salarioTutor;
    }

    public void setSalarioTutor(Float salarioTutor) {
        this.salarioTutor = salarioTutor;
    }

    public boolean getFallecido() {
        return fallecido;
    }

    public void setFallecido(boolean fallecido) {
        this.fallecido = fallecido;
    }

    public Estudiante getEstudianteestudianteId() {
        return estudianteestudianteId;
    }

    public void setEstudianteestudianteId(Estudiante estudianteestudianteId) {
        this.estudianteestudianteId = estudianteestudianteId;
    }

    public NivelEscolar getNivelEscolarnivelEscolarId() {
        return nivelEscolarnivelEscolarId;
    }

    public void setNivelEscolarnivelEscolarId(NivelEscolar nivelEscolarnivelEscolarId) {
        this.nivelEscolarnivelEscolarId = nivelEscolarnivelEscolarId;
    }

    public Ocupacion getOcupacionocupacionId() {
        return ocupacionocupacionId;
    }

    public void setOcupacionocupacionId(Ocupacion ocupacionocupacionId) {
        this.ocupacionocupacionId = ocupacionocupacionId;
    }

    public OrganizacionPolitica getOrganizacionPoliticaorganizacionPoliticaId() {
        return organizacionPoliticaorganizacionPoliticaId;
    }

    public void setOrganizacionPoliticaorganizacionPoliticaId(OrganizacionPolitica organizacionPoliticaorganizacionPoliticaId) {
        this.organizacionPoliticaorganizacionPoliticaId = organizacionPoliticaorganizacionPoliticaId;
    }

    public Sexo getSexosexoId() {
        return sexosexoId;
    }

    public void setSexosexoId(Sexo sexosexoId) {
        this.sexosexoId = sexosexoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tutorId != null ? tutorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if ((this.tutorId == null && other.tutorId != null) || (this.tutorId != null && !this.tutorId.equals(other.tutorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tutor[ tutorId=" + tutorId + " ]";
    }

    public Tutor(String nombreTutor, String apellidosTutor, Float salarioTutor, boolean fallecido, Estudiante estudianteestudianteId, NivelEscolar nivelEscolarnivelEscolarId, Ocupacion ocupacionocupacionId, OrganizacionPolitica organizacionPoliticaorganizacionPoliticaId, Sexo sexosexoId) {
        this.nombreTutor = nombreTutor;
        this.apellidosTutor = apellidosTutor;
        this.salarioTutor = salarioTutor;
        this.fallecido = fallecido;
        this.estudianteestudianteId = estudianteestudianteId;
        this.nivelEscolarnivelEscolarId = nivelEscolarnivelEscolarId;
        this.ocupacionocupacionId = ocupacionocupacionId;
        this.organizacionPoliticaorganizacionPoliticaId = organizacionPoliticaorganizacionPoliticaId;
        this.sexosexoId = sexosexoId;
    }
}
