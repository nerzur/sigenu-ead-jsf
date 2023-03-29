/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author claudy
 */
@Entity
@Table(name = "examen_matricula_facultad_cum_carrera_estudiante_asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.findAll", query = "SELECT e FROM ExamenMatriculaFacultadCumCarreraEstudianteAsignatura e"),
    @NamedQuery(name = "ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.findByExamenexamenId", query = "SELECT e FROM ExamenMatriculaFacultadCumCarreraEstudianteAsignatura e WHERE e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.examenexamenId = :examenexamenId"),
    @NamedQuery(name = "ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.findByMatriculaId", query = "SELECT e FROM ExamenMatriculaFacultadCumCarreraEstudianteAsignatura e WHERE e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.matriculaId = :matriculaId"),
    @NamedQuery(name = "ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.findByCodigocum", query = "SELECT e FROM ExamenMatriculaFacultadCumCarreraEstudianteAsignatura e WHERE e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.codigocum = :codigocum"),
    @NamedQuery(name = "ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.findByCodigoarea", query = "SELECT e FROM ExamenMatriculaFacultadCumCarreraEstudianteAsignatura e WHERE e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.codigoarea = :codigoarea"),
    @NamedQuery(name = "ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.findByIdcarrera", query = "SELECT e FROM ExamenMatriculaFacultadCumCarreraEstudianteAsignatura e WHERE e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.idcarrera = :idcarrera"),
    @NamedQuery(name = "ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.findByEstudianteId", query = "SELECT e FROM ExamenMatriculaFacultadCumCarreraEstudianteAsignatura e WHERE e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.estudianteId = :estudianteId"),
    @NamedQuery(name = "ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.findByFechaMatricula", query = "SELECT e FROM ExamenMatriculaFacultadCumCarreraEstudianteAsignatura e WHERE e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.fechaMatricula = :fechaMatricula"),
    @NamedQuery(name = "ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.findByAsignaturaId", query = "SELECT e FROM ExamenMatriculaFacultadCumCarreraEstudianteAsignatura e WHERE e.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.asignaturaId = :asignaturaId"),
    @NamedQuery(name = "ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.findByCancelado", query = "SELECT e FROM ExamenMatriculaFacultadCumCarreraEstudianteAsignatura e WHERE e.cancelado = :cancelado"),
    @NamedQuery(name = "ExamenMatriculaFacultadCumCarreraEstudianteAsignatura.findByNota", query = "SELECT e FROM ExamenMatriculaFacultadCumCarreraEstudianteAsignatura e WHERE e.nota = :nota")})
public class ExamenMatriculaFacultadCumCarreraEstudianteAsignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    @Basic(optional = false)
    @Column(name = "nota")
    private int nota;
    @JoinColumn(name = "examenexamen_id", referencedColumnName = "examen_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Examen examen;
    @JoinColumns({
        @JoinColumn(name = "matricula_id", referencedColumnName = "matriculamatricula_id", insertable = false, updatable = false),
        @JoinColumn(name = "codigocum", referencedColumnName = "codigocum", insertable = false, updatable = false),
        @JoinColumn(name = "codigoarea", referencedColumnName = "codigoarea", insertable = false, updatable = false),
        @JoinColumn(name = "idcarrera", referencedColumnName = "idcarrera", insertable = false, updatable = false),
        @JoinColumn(name = "estudiante_id", referencedColumnName = "estudiante_id", insertable = false, updatable = false),
        @JoinColumn(name = "fecha_matricula", referencedColumnName = "fecha_matricula", insertable = false, updatable = false),
        @JoinColumn(name = "asignatura_id", referencedColumnName = "asignatura_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignatura;

    public ExamenMatriculaFacultadCumCarreraEstudianteAsignatura() {
    }

    public ExamenMatriculaFacultadCumCarreraEstudianteAsignatura(ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK) {
        this.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK = examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK;
    }

    public ExamenMatriculaFacultadCumCarreraEstudianteAsignatura(ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK, boolean cancelado, int nota) {
        this.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK = examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK;
        this.cancelado = cancelado;
        this.nota = nota;
    }

    public ExamenMatriculaFacultadCumCarreraEstudianteAsignatura(int examenexamenId, int matriculaId, String codigocum, String codigoarea, int idcarrera, String estudianteId, Date fechaMatricula, int asignaturaId) {
        this.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK = new ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(examenexamenId, matriculaId, codigocum, codigoarea, idcarrera, estudianteId, fechaMatricula, asignaturaId);
    }

    public ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK() {
        return examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK;
    }

    public void setExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK(ExamenMatriculaFacultadCumCarreraEstudianteAsignaturaPK examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK) {
        this.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK = examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public MatriculaFacultadCumCarreraEstudianteAsignatura getMatriculaFacultadCumCarreraEstudianteAsignatura() {
        return matriculaFacultadCumCarreraEstudianteAsignatura;
    }

    public void setMatriculaFacultadCumCarreraEstudianteAsignatura(MatriculaFacultadCumCarreraEstudianteAsignatura matriculaFacultadCumCarreraEstudianteAsignatura) {
        this.matriculaFacultadCumCarreraEstudianteAsignatura = matriculaFacultadCumCarreraEstudianteAsignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK != null ? examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenMatriculaFacultadCumCarreraEstudianteAsignatura)) {
            return false;
        }
        ExamenMatriculaFacultadCumCarreraEstudianteAsignatura other = (ExamenMatriculaFacultadCumCarreraEstudianteAsignatura) object;
        if ((this.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK == null && other.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK != null) || (this.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK != null && !this.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK.equals(other.examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ExamenMatriculaFacultadCumCarreraEstudianteAsignatura[ examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK=" + examenMatriculaFacultadCumCarreraEstudianteAsignaturaPK + " ]";
    }
    
}
