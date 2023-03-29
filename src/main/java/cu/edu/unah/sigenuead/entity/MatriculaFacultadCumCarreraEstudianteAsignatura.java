/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author claudy
 */
@Entity
@Table(name = "matricula_facultad_cum_carrera_estudiante_asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MatriculaFacultadCumCarreraEstudianteAsignatura.findAll", query = "SELECT m FROM MatriculaFacultadCumCarreraEstudianteAsignatura m"),
    @NamedQuery(name = "MatriculaFacultadCumCarreraEstudianteAsignatura.findByMatriculamatriculaId", query = "SELECT m FROM MatriculaFacultadCumCarreraEstudianteAsignatura m WHERE m.matriculaFacultadCumCarreraEstudianteAsignaturaPK.matriculamatriculaId = :matriculamatriculaId"),
    @NamedQuery(name = "MatriculaFacultadCumCarreraEstudianteAsignatura.findByCodigocum", query = "SELECT m FROM MatriculaFacultadCumCarreraEstudianteAsignatura m WHERE m.matriculaFacultadCumCarreraEstudianteAsignaturaPK.codigocum = :codigocum"),
    @NamedQuery(name = "MatriculaFacultadCumCarreraEstudianteAsignatura.findByCodigoarea", query = "SELECT m FROM MatriculaFacultadCumCarreraEstudianteAsignatura m WHERE m.matriculaFacultadCumCarreraEstudianteAsignaturaPK.codigoarea = :codigoarea"),
    @NamedQuery(name = "MatriculaFacultadCumCarreraEstudianteAsignatura.findByIdcarrera", query = "SELECT m FROM MatriculaFacultadCumCarreraEstudianteAsignatura m WHERE m.matriculaFacultadCumCarreraEstudianteAsignaturaPK.idcarrera = :idcarrera"),
    @NamedQuery(name = "MatriculaFacultadCumCarreraEstudianteAsignatura.findByEstudianteId", query = "SELECT m FROM MatriculaFacultadCumCarreraEstudianteAsignatura m WHERE m.matriculaFacultadCumCarreraEstudianteAsignaturaPK.estudianteId = :estudianteId"),
    @NamedQuery(name = "MatriculaFacultadCumCarreraEstudianteAsignatura.findByFechaMatricula", query = "SELECT m FROM MatriculaFacultadCumCarreraEstudianteAsignatura m WHERE m.matriculaFacultadCumCarreraEstudianteAsignaturaPK.fechaMatricula = :fechaMatricula"),
    @NamedQuery(name = "MatriculaFacultadCumCarreraEstudianteAsignatura.findByAsignaturaId", query = "SELECT m FROM MatriculaFacultadCumCarreraEstudianteAsignatura m WHERE m.matriculaFacultadCumCarreraEstudianteAsignaturaPK.asignaturaId = :asignaturaId"),
    @NamedQuery(name = "MatriculaFacultadCumCarreraEstudianteAsignatura.findByCancelada", query = "SELECT m FROM MatriculaFacultadCumCarreraEstudianteAsignatura m WHERE m.cancelada = :cancelada"),
    @NamedQuery(name = "MatriculaFacultadCumCarreraEstudianteAsignatura.findByActual", query = "SELECT m FROM MatriculaFacultadCumCarreraEstudianteAsignatura m WHERE m.actual = :actual")})
public class MatriculaFacultadCumCarreraEstudianteAsignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MatriculaFacultadCumCarreraEstudianteAsignaturaPK matriculaFacultadCumCarreraEstudianteAsignaturaPK;
    @Basic(optional = false)
    @Column(name = "cancelada")
    private boolean cancelada;
    @Basic(optional = false)
    @Column(name = "actual")
    private boolean actual;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matriculaFacultadCumCarreraEstudianteAsignatura")
    private List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> examenMatriculaFacultadCumCarreraEstudianteAsignaturaList;
    @JoinColumns({
        @JoinColumn(name = "codigocum", referencedColumnName = "codigocum", insertable = false, updatable = false),
        @JoinColumn(name = "codigoarea", referencedColumnName = "codigoarea", insertable = false, updatable = false),
        @JoinColumn(name = "idcarrera", referencedColumnName = "idcarrera", insertable = false, updatable = false),
        @JoinColumn(name = "estudiante_id", referencedColumnName = "estudiante_id", insertable = false, updatable = false),
        @JoinColumn(name = "fecha_matricula", referencedColumnName = "fecha_matricula", insertable = false, updatable = false),
        @JoinColumn(name = "asignatura_id", referencedColumnName = "asignatura_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignatura;
    @JoinColumn(name = "matriculamatricula_id", referencedColumnName = "matricula_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Matricula matricula;

    public MatriculaFacultadCumCarreraEstudianteAsignatura() {
    }

    public MatriculaFacultadCumCarreraEstudianteAsignatura(MatriculaFacultadCumCarreraEstudianteAsignaturaPK matriculaFacultadCumCarreraEstudianteAsignaturaPK) {
        this.matriculaFacultadCumCarreraEstudianteAsignaturaPK = matriculaFacultadCumCarreraEstudianteAsignaturaPK;
    }

    public MatriculaFacultadCumCarreraEstudianteAsignatura(MatriculaFacultadCumCarreraEstudianteAsignaturaPK matriculaFacultadCumCarreraEstudianteAsignaturaPK, boolean cancelada, boolean actual) {
        this.matriculaFacultadCumCarreraEstudianteAsignaturaPK = matriculaFacultadCumCarreraEstudianteAsignaturaPK;
        this.cancelada = cancelada;
        this.actual = actual;
    }

    public MatriculaFacultadCumCarreraEstudianteAsignatura(int matriculamatriculaId, String codigocum, String codigoarea, int idcarrera, String estudianteId, Date fechaMatricula, int asignaturaId) {
        this.matriculaFacultadCumCarreraEstudianteAsignaturaPK = new MatriculaFacultadCumCarreraEstudianteAsignaturaPK(matriculamatriculaId, codigocum, codigoarea, idcarrera, estudianteId, fechaMatricula, asignaturaId);
    }

    public MatriculaFacultadCumCarreraEstudianteAsignaturaPK getMatriculaFacultadCumCarreraEstudianteAsignaturaPK() {
        return matriculaFacultadCumCarreraEstudianteAsignaturaPK;
    }

    public void setMatriculaFacultadCumCarreraEstudianteAsignaturaPK(MatriculaFacultadCumCarreraEstudianteAsignaturaPK matriculaFacultadCumCarreraEstudianteAsignaturaPK) {
        this.matriculaFacultadCumCarreraEstudianteAsignaturaPK = matriculaFacultadCumCarreraEstudianteAsignaturaPK;
    }

    public boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public boolean getActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    @XmlTransient
    public List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList() {
        return examenMatriculaFacultadCumCarreraEstudianteAsignaturaList;
    }

    public void setExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList(List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> examenMatriculaFacultadCumCarreraEstudianteAsignaturaList) {
        this.examenMatriculaFacultadCumCarreraEstudianteAsignaturaList = examenMatriculaFacultadCumCarreraEstudianteAsignaturaList;
    }

    public FacultadCumCarreraEstudianteAsignatura getFacultadCumCarreraEstudianteAsignatura() {
        return facultadCumCarreraEstudianteAsignatura;
    }

    public void setFacultadCumCarreraEstudianteAsignatura(FacultadCumCarreraEstudianteAsignatura facultadCumCarreraEstudianteAsignatura) {
        this.facultadCumCarreraEstudianteAsignatura = facultadCumCarreraEstudianteAsignatura;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculaFacultadCumCarreraEstudianteAsignaturaPK != null ? matriculaFacultadCumCarreraEstudianteAsignaturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatriculaFacultadCumCarreraEstudianteAsignatura)) {
            return false;
        }
        MatriculaFacultadCumCarreraEstudianteAsignatura other = (MatriculaFacultadCumCarreraEstudianteAsignatura) object;
        if ((this.matriculaFacultadCumCarreraEstudianteAsignaturaPK == null && other.matriculaFacultadCumCarreraEstudianteAsignaturaPK != null) || (this.matriculaFacultadCumCarreraEstudianteAsignaturaPK != null && !this.matriculaFacultadCumCarreraEstudianteAsignaturaPK.equals(other.matriculaFacultadCumCarreraEstudianteAsignaturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MatriculaFacultadCumCarreraEstudianteAsignatura[ matriculaFacultadCumCarreraEstudianteAsignaturaPK=" + matriculaFacultadCumCarreraEstudianteAsignaturaPK + " ]";
    }
    
}
