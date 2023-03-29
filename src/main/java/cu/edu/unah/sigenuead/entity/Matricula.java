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
@Table(name = "matricula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m"),
    @NamedQuery(name = "Matricula.findByMatriculaId", query = "SELECT m FROM Matricula m WHERE m.matriculaId = :matriculaId"),
    @NamedQuery(name = "Matricula.findByMatriculaFechaInicio", query = "SELECT m FROM Matricula m WHERE m.matriculaFechaInicio = :matriculaFechaInicio"),
    @NamedQuery(name = "Matricula.findByMatriculaFechaFin", query = "SELECT m FROM Matricula m WHERE m.matriculaFechaFin = :matriculaFechaFin"),
    @NamedQuery(name = "Matricula.findByMatriculaCancelada", query = "SELECT m FROM Matricula m WHERE m.matriculaCancelada = :matriculaCancelada"),
    @NamedQuery(name = "Matricula.findByCerrada", query = "SELECT m FROM Matricula m WHERE m.cerrada = :cerrada")})
public class Matricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "matricula_id")
    private Integer matriculaId;
    @Basic(optional = false)
    @Column(name = "matricula_fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date matriculaFechaInicio;
    @Basic(optional = false)
    @Column(name = "matricula_fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date matriculaFechaFin;
    @Basic(optional = false)
    @Column(name = "matricula_cancelada")
    private boolean matriculaCancelada;
    @Basic(optional = false)
    @Column(name = "cerrada")
    private boolean cerrada;
    @JoinColumn(name = "cursoidcurso", referencedColumnName = "idcurso")
    @ManyToOne(optional = false)
    private Curso cursoidcurso;
    @JoinColumns({
        @JoinColumn(name = "facultad_cum_carrerafacultad_cumcumcodigocum", referencedColumnName = "facultad_cumcumcodigocum"),
        @JoinColumn(name = "facultad_cum_carrerafacultad_cumfacultadcodigoarea", referencedColumnName = "facultad_cumfacultadcodigoarea"),
        @JoinColumn(name = "facultad_cum_carreracarreraidcarrera", referencedColumnName = "carreraidcarrera")})
    @ManyToOne(optional = false)
    private FacultadCumCarrera facultadCumCarrera;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matricula")
    private List<MatriculaFacultadCumCarreraEstudianteAsignatura> matriculaFacultadCumCarreraEstudianteAsignaturaList;

    public Matricula() {
    }

    public Matricula(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Matricula(Integer matriculaId, Date matriculaFechaInicio, Date matriculaFechaFin, boolean matriculaCancelada, boolean cerrada) {
        this.matriculaId = matriculaId;
        this.matriculaFechaInicio = matriculaFechaInicio;
        this.matriculaFechaFin = matriculaFechaFin;
        this.matriculaCancelada = matriculaCancelada;
        this.cerrada = cerrada;
    }

    public Integer getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Date getMatriculaFechaInicio() {
        return matriculaFechaInicio;
    }

    public void setMatriculaFechaInicio(Date matriculaFechaInicio) {
        this.matriculaFechaInicio = matriculaFechaInicio;
    }

    public Date getMatriculaFechaFin() {
        return matriculaFechaFin;
    }

    public void setMatriculaFechaFin(Date matriculaFechaFin) {
        this.matriculaFechaFin = matriculaFechaFin;
    }

    public boolean getMatriculaCancelada() {
        return matriculaCancelada;
    }

    public void setMatriculaCancelada(boolean matriculaCancelada) {
        this.matriculaCancelada = matriculaCancelada;
    }

    public boolean getCerrada() {
        return cerrada;
    }

    public void setCerrada(boolean cerrada) {
        this.cerrada = cerrada;
    }

    public Curso getCursoidcurso() {
        return cursoidcurso;
    }

    public void setCursoidcurso(Curso cursoidcurso) {
        this.cursoidcurso = cursoidcurso;
    }

    public FacultadCumCarrera getFacultadCumCarrera() {
        return facultadCumCarrera;
    }

    public void setFacultadCumCarrera(FacultadCumCarrera facultadCumCarrera) {
        this.facultadCumCarrera = facultadCumCarrera;
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
        hash += (matriculaId != null ? matriculaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.matriculaId == null && other.matriculaId != null) || (this.matriculaId != null && !this.matriculaId.equals(other.matriculaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Matricula[ matriculaId=" + matriculaId + " ]";
    }

    public Matricula(Date matriculaFechaInicio, Date matriculaFechaFin, boolean matriculaCancelada, boolean cerrada, Curso cursoidcurso, FacultadCumCarrera facultadCumCarrera) {
        this.matriculaFechaInicio = matriculaFechaInicio;
        this.matriculaFechaFin = matriculaFechaFin;
        this.matriculaCancelada = matriculaCancelada;
        this.cerrada = cerrada;
        this.cursoidcurso = cursoidcurso;
        this.facultadCumCarrera = facultadCumCarrera;
    }
}
