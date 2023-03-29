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
@Table(name = "facultad_cum_carrera_estudiante_asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacultadCumCarreraEstudianteAsignatura.findAll", query = "SELECT f FROM FacultadCumCarreraEstudianteAsignatura f"),
    @NamedQuery(name = "FacultadCumCarreraEstudianteAsignatura.findByCodigocum", query = "SELECT f FROM FacultadCumCarreraEstudianteAsignatura f WHERE f.facultadCumCarreraEstudianteAsignaturaPK.codigocum = :codigocum"),
    @NamedQuery(name = "FacultadCumCarreraEstudianteAsignatura.findByCodigoarea", query = "SELECT f FROM FacultadCumCarreraEstudianteAsignatura f WHERE f.facultadCumCarreraEstudianteAsignaturaPK.codigoarea = :codigoarea"),
    @NamedQuery(name = "FacultadCumCarreraEstudianteAsignatura.findByIdcarrera", query = "SELECT f FROM FacultadCumCarreraEstudianteAsignatura f WHERE f.facultadCumCarreraEstudianteAsignaturaPK.idcarrera = :idcarrera"),
    @NamedQuery(name = "FacultadCumCarreraEstudianteAsignatura.findByEstudianteId", query = "SELECT f FROM FacultadCumCarreraEstudianteAsignatura f WHERE f.facultadCumCarreraEstudianteAsignaturaPK.estudianteId = :estudianteId"),
    @NamedQuery(name = "FacultadCumCarreraEstudianteAsignatura.findByFechaMatricula", query = "SELECT f FROM FacultadCumCarreraEstudianteAsignatura f WHERE f.facultadCumCarreraEstudianteAsignaturaPK.fechaMatricula = :fechaMatricula"),
    @NamedQuery(name = "FacultadCumCarreraEstudianteAsignatura.findByAsignaturaId", query = "SELECT f FROM FacultadCumCarreraEstudianteAsignatura f WHERE f.facultadCumCarreraEstudianteAsignaturaPK.asignaturaId = :asignaturaId"),
    @NamedQuery(name = "FacultadCumCarreraEstudianteAsignatura.findByAprobada", query = "SELECT f FROM FacultadCumCarreraEstudianteAsignatura f WHERE f.aprobada = :aprobada"),
    @NamedQuery(name = "FacultadCumCarreraEstudianteAsignatura.findByCancelada", query = "SELECT f FROM FacultadCumCarreraEstudianteAsignatura f WHERE f.cancelada = :cancelada")})
public class FacultadCumCarreraEstudianteAsignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FacultadCumCarreraEstudianteAsignaturaPK facultadCumCarreraEstudianteAsignaturaPK;
    @Basic(optional = false)
    @Column(name = "aprobada")
    private boolean aprobada;
    @Basic(optional = false)
    @Column(name = "cancelada")
    private boolean cancelada;
    @JoinColumn(name = "asignatura_id", referencedColumnName = "asignatura_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura;
    @JoinColumns({
        @JoinColumn(name = "codigocum", referencedColumnName = "facultad_cum_carrerafacultad_cumcumcodigocum", insertable = false, updatable = false),
        @JoinColumn(name = "codigoarea", referencedColumnName = "facultad_cum_carrerafacultad_cumfacultadcodigoarea", insertable = false, updatable = false),
        @JoinColumn(name = "idcarrera", referencedColumnName = "facultad_cum_carreracarreraidcarrera", insertable = false, updatable = false),
        @JoinColumn(name = "estudiante_id", referencedColumnName = "estudianteestudiante_id", insertable = false, updatable = false),
        @JoinColumn(name = "fecha_matricula", referencedColumnName = "fecha_matricula", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private FacultadCumCarreraEstudiante facultadCumCarreraEstudiante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultadCumCarreraEstudianteAsignatura")
    private List<MatriculaFacultadCumCarreraEstudianteAsignatura> matriculaFacultadCumCarreraEstudianteAsignaturaList;

    public FacultadCumCarreraEstudianteAsignatura() {
    }

    public FacultadCumCarreraEstudianteAsignatura(FacultadCumCarreraEstudianteAsignaturaPK facultadCumCarreraEstudianteAsignaturaPK) {
        this.facultadCumCarreraEstudianteAsignaturaPK = facultadCumCarreraEstudianteAsignaturaPK;
    }

    public FacultadCumCarreraEstudianteAsignatura(FacultadCumCarreraEstudianteAsignaturaPK facultadCumCarreraEstudianteAsignaturaPK, boolean aprobada, boolean cancelada) {
        this.facultadCumCarreraEstudianteAsignaturaPK = facultadCumCarreraEstudianteAsignaturaPK;
        this.aprobada = aprobada;
        this.cancelada = cancelada;
    }

    public FacultadCumCarreraEstudianteAsignatura(String codigocum, String codigoarea, int idcarrera, String estudianteId, Date fechaMatricula, int asignaturaId) {
        this.facultadCumCarreraEstudianteAsignaturaPK = new FacultadCumCarreraEstudianteAsignaturaPK(codigocum, codigoarea, idcarrera, estudianteId, fechaMatricula, asignaturaId);
    }

    public FacultadCumCarreraEstudianteAsignaturaPK getFacultadCumCarreraEstudianteAsignaturaPK() {
        return facultadCumCarreraEstudianteAsignaturaPK;
    }

    public void setFacultadCumCarreraEstudianteAsignaturaPK(FacultadCumCarreraEstudianteAsignaturaPK facultadCumCarreraEstudianteAsignaturaPK) {
        this.facultadCumCarreraEstudianteAsignaturaPK = facultadCumCarreraEstudianteAsignaturaPK;
    }

    public boolean getAprobada() {
        return aprobada;
    }

    public void setAprobada(boolean aprobada) {
        this.aprobada = aprobada;
    }

    public boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public FacultadCumCarreraEstudiante getFacultadCumCarreraEstudiante() {
        return facultadCumCarreraEstudiante;
    }

    public void setFacultadCumCarreraEstudiante(FacultadCumCarreraEstudiante facultadCumCarreraEstudiante) {
        this.facultadCumCarreraEstudiante = facultadCumCarreraEstudiante;
    }

    @XmlTransient
    public List<MatriculaFacultadCumCarreraEstudianteAsignatura> getMatriculaFacultadCumCarreraEstudianteAsignaturaList() {
        return matriculaFacultadCumCarreraEstudianteAsignaturaList;
    }

    public void setMatriculaFacultadCumCarreraEstudianteAsignaturaList(List<MatriculaFacultadCumCarreraEstudianteAsignatura> matriculaFacultadCumCarreraEstudianteAsignaturaList) {
        this.matriculaFacultadCumCarreraEstudianteAsignaturaList = matriculaFacultadCumCarreraEstudianteAsignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facultadCumCarreraEstudianteAsignaturaPK != null ? facultadCumCarreraEstudianteAsignaturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacultadCumCarreraEstudianteAsignatura)) {
            return false;
        }
        FacultadCumCarreraEstudianteAsignatura other = (FacultadCumCarreraEstudianteAsignatura) object;
        if ((this.facultadCumCarreraEstudianteAsignaturaPK == null && other.facultadCumCarreraEstudianteAsignaturaPK != null) || (this.facultadCumCarreraEstudianteAsignaturaPK != null && !this.facultadCumCarreraEstudianteAsignaturaPK.equals(other.facultadCumCarreraEstudianteAsignaturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FacultadCumCarreraEstudianteAsignatura[ facultadCumCarreraEstudianteAsignaturaPK=" + facultadCumCarreraEstudianteAsignaturaPK + " ]";
    }
    
}
