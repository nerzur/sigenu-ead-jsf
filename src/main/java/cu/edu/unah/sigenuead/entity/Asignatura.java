/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author claudy
 */
@Entity
@Table(name = "asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a"),
    @NamedQuery(name = "Asignatura.findByAsignaturaId", query = "SELECT a FROM Asignatura a WHERE a.asignaturaId = :asignaturaId"),
    @NamedQuery(name = "Asignatura.findByAsignaturaCodigo", query = "SELECT a FROM Asignatura a WHERE a.asignaturaCodigo = :asignaturaCodigo"),
    @NamedQuery(name = "Asignatura.findByAsignaturaNombre", query = "SELECT a FROM Asignatura a WHERE a.asignaturaNombre = :asignaturaNombre"),
    @NamedQuery(name = "Asignatura.findByAsignaturaAbreviatura", query = "SELECT a FROM Asignatura a WHERE a.asignaturaAbreviatura = :asignaturaAbreviatura"),
    @NamedQuery(name = "Asignatura.findByAsignaturaCancelada", query = "SELECT a FROM Asignatura a WHERE a.asignaturaCancelada = :asignaturaCancelada"),
    @NamedQuery(name = "Asignatura.findByAsignaturaPromediable", query = "SELECT a FROM Asignatura a WHERE a.asignaturaPromediable = :asignaturaPromediable"),
    @NamedQuery(name = "Asignatura.findByAsignaturaCertificable", query = "SELECT a FROM Asignatura a WHERE a.asignaturaCertificable = :asignaturaCertificable")})
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "asignatura_id")
    private Integer asignaturaId;
    @Basic(optional = false)
    @Column(name = "asignatura_codigo")
    private String asignaturaCodigo;
    @Basic(optional = false)
    @Column(name = "asignatura_nombre")
    private String asignaturaNombre;
    @Basic(optional = false)
    @Column(name = "asignatura_abreviatura")
    private String asignaturaAbreviatura;
    @Basic(optional = false)
    @Column(name = "asignatura_cancelada")
    private boolean asignaturaCancelada;
    @Basic(optional = false)
    @Column(name = "asignatura_promediable")
    private boolean asignaturaPromediable;
    @Basic(optional = false)
    @Column(name = "asignatura_certificable")
    private boolean asignaturaCertificable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura")
    private List<AsignaturaAsignatura> asignaturaAsignaturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura1")
    private List<AsignaturaAsignatura> asignaturaAsignaturaList1;
    @JoinColumns({
        @JoinColumn(name = "disciplina_codigo", referencedColumnName = "disciplinadisciplina_codigo"),
        @JoinColumn(name = "disciplina_idplanestudio", referencedColumnName = "planestudioidplanestudio")})
    @ManyToOne(optional = false)
    private DisciplinaPlanestudio disciplinaPlanestudio;
    @JoinColumn(name = "tipo_asignaturatipo_asignatura_id", referencedColumnName = "tipo_asignatura_id")
    @ManyToOne(optional = false)
    private TipoAsignatura tipoAsignaturatipoAsignaturaId;
    @JoinColumn(name = "tipo_evaluaciontipo_evaluacion_id", referencedColumnName = "tipo_evaluacion_id")
    @ManyToOne(optional = false)
    private TipoEvaluacion tipoEvaluaciontipoEvaluacionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura")
    private List<FacultadCumCarreraEstudianteAsignatura> facultadCumCarreraEstudianteAsignaturaList;

    public Asignatura() {
    }

    public Asignatura(Integer asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public Asignatura(Integer asignaturaId, String asignaturaCodigo, String asignaturaNombre, String asignaturaAbreviatura, boolean asignaturaCancelada, boolean asignaturaPromediable, boolean asignaturaCertificable) {
        this.asignaturaId = asignaturaId;
        this.asignaturaCodigo = asignaturaCodigo;
        this.asignaturaNombre = asignaturaNombre;
        this.asignaturaAbreviatura = asignaturaAbreviatura;
        this.asignaturaCancelada = asignaturaCancelada;
        this.asignaturaPromediable = asignaturaPromediable;
        this.asignaturaCertificable = asignaturaCertificable;
    }

    public Integer getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(Integer asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public String getAsignaturaCodigo() {
        return asignaturaCodigo;
    }

    public void setAsignaturaCodigo(String asignaturaCodigo) {
        this.asignaturaCodigo = asignaturaCodigo;
    }

    public String getAsignaturaNombre() {
        return asignaturaNombre;
    }

    public void setAsignaturaNombre(String asignaturaNombre) {
        this.asignaturaNombre = asignaturaNombre;
    }

    public String getAsignaturaAbreviatura() {
        return asignaturaAbreviatura;
    }

    public void setAsignaturaAbreviatura(String asignaturaAbreviatura) {
        this.asignaturaAbreviatura = asignaturaAbreviatura;
    }

    public boolean getAsignaturaCancelada() {
        return asignaturaCancelada;
    }

    public void setAsignaturaCancelada(boolean asignaturaCancelada) {
        this.asignaturaCancelada = asignaturaCancelada;
    }

    public boolean getAsignaturaPromediable() {
        return asignaturaPromediable;
    }

    public void setAsignaturaPromediable(boolean asignaturaPromediable) {
        this.asignaturaPromediable = asignaturaPromediable;
    }

    public boolean getAsignaturaCertificable() {
        return asignaturaCertificable;
    }

    public void setAsignaturaCertificable(boolean asignaturaCertificable) {
        this.asignaturaCertificable = asignaturaCertificable;
    }

    @XmlTransient
    public List<AsignaturaAsignatura> getAsignaturaAsignaturaList() {
        return asignaturaAsignaturaList;
    }

    public void setAsignaturaAsignaturaList(List<AsignaturaAsignatura> asignaturaAsignaturaList) {
        this.asignaturaAsignaturaList = asignaturaAsignaturaList;
    }

    @XmlTransient
    public List<AsignaturaAsignatura> getAsignaturaAsignaturaList1() {
        return asignaturaAsignaturaList1;
    }

    public void setAsignaturaAsignaturaList1(List<AsignaturaAsignatura> asignaturaAsignaturaList1) {
        this.asignaturaAsignaturaList1 = asignaturaAsignaturaList1;
    }

    public DisciplinaPlanestudio getDisciplinaPlanestudio() {
        return disciplinaPlanestudio;
    }

    public void setDisciplinaPlanestudio(DisciplinaPlanestudio disciplinaPlanestudio) {
        this.disciplinaPlanestudio = disciplinaPlanestudio;
    }

    public TipoAsignatura getTipoAsignaturatipoAsignaturaId() {
        return tipoAsignaturatipoAsignaturaId;
    }

    public void setTipoAsignaturatipoAsignaturaId(TipoAsignatura tipoAsignaturatipoAsignaturaId) {
        this.tipoAsignaturatipoAsignaturaId = tipoAsignaturatipoAsignaturaId;
    }

    public TipoEvaluacion getTipoEvaluaciontipoEvaluacionId() {
        return tipoEvaluaciontipoEvaluacionId;
    }

    public void setTipoEvaluaciontipoEvaluacionId(TipoEvaluacion tipoEvaluaciontipoEvaluacionId) {
        this.tipoEvaluaciontipoEvaluacionId = tipoEvaluaciontipoEvaluacionId;
    }

    @XmlTransient
    public List<FacultadCumCarreraEstudianteAsignatura> getFacultadCumCarreraEstudianteAsignaturaList() {
        return facultadCumCarreraEstudianteAsignaturaList;
    }

    public void setFacultadCumCarreraEstudianteAsignaturaList(List<FacultadCumCarreraEstudianteAsignatura> facultadCumCarreraEstudianteAsignaturaList) {
        this.facultadCumCarreraEstudianteAsignaturaList = facultadCumCarreraEstudianteAsignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asignaturaId != null ? asignaturaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.asignaturaId == null && other.asignaturaId != null) || (this.asignaturaId != null && !this.asignaturaId.equals(other.asignaturaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Asignatura[ asignaturaId=" + asignaturaId + " ]";
    }

    public Asignatura(String asignaturaCodigo, String asignaturaNombre, String asignaturaAbreviatura, boolean asignaturaCancelada, boolean asignaturaPromediable, boolean asignaturaCertificable, List<Asignatura> asignaturaList, DisciplinaPlanestudio disciplinaPlanestudio, TipoAsignatura tipoAsignaturatipoAsignaturaId, TipoEvaluacion tipoEvaluaciontipoEvaluacionId) {
        this.asignaturaCodigo = asignaturaCodigo;
        this.asignaturaNombre = asignaturaNombre;
        this.asignaturaAbreviatura = asignaturaAbreviatura;
        this.asignaturaCancelada = asignaturaCancelada;
        this.asignaturaPromediable = asignaturaPromediable;
        this.asignaturaCertificable = asignaturaCertificable;
        this.disciplinaPlanestudio = disciplinaPlanestudio;
        this.tipoAsignaturatipoAsignaturaId = tipoAsignaturatipoAsignaturaId;
        this.tipoEvaluaciontipoEvaluacionId = tipoEvaluaciontipoEvaluacionId;
    }
}
