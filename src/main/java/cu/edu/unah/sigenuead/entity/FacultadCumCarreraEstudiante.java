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
@Table(name = "facultad_cum_carrera_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacultadCumCarreraEstudiante.findAll", query = "SELECT f FROM FacultadCumCarreraEstudiante f"),
    @NamedQuery(name = "FacultadCumCarreraEstudiante.findByFacultadCumCarrerafacultadCumcumcodigocum", query = "SELECT f FROM FacultadCumCarreraEstudiante f WHERE f.facultadCumCarreraEstudiantePK.facultadCumCarrerafacultadCumcumcodigocum = :facultadCumCarrerafacultadCumcumcodigocum"),
    @NamedQuery(name = "FacultadCumCarreraEstudiante.findByFacultadCumCarrerafacultadCumfacultadcodigoarea", query = "SELECT f FROM FacultadCumCarreraEstudiante f WHERE f.facultadCumCarreraEstudiantePK.facultadCumCarrerafacultadCumfacultadcodigoarea = :facultadCumCarrerafacultadCumfacultadcodigoarea"),
    @NamedQuery(name = "FacultadCumCarreraEstudiante.findByFacultadCumCarreracarreraidcarrera", query = "SELECT f FROM FacultadCumCarreraEstudiante f WHERE f.facultadCumCarreraEstudiantePK.facultadCumCarreracarreraidcarrera = :facultadCumCarreracarreraidcarrera"),
    @NamedQuery(name = "FacultadCumCarreraEstudiante.findByEstudianteestudianteId", query = "SELECT f FROM FacultadCumCarreraEstudiante f WHERE f.facultadCumCarreraEstudiantePK.estudianteestudianteId = :estudianteestudianteId"),
    @NamedQuery(name = "FacultadCumCarreraEstudiante.findByFechaMatricula", query = "SELECT f FROM FacultadCumCarreraEstudiante f WHERE f.facultadCumCarreraEstudiantePK.fechaMatricula = :fechaMatricula"),
    @NamedQuery(name = "FacultadCumCarreraEstudiante.findByCarreraReoferta", query = "SELECT f FROM FacultadCumCarreraEstudiante f WHERE f.carreraReoferta = :carreraReoferta"),
    @NamedQuery(name = "FacultadCumCarreraEstudiante.findByCarreraOpcion", query = "SELECT f FROM FacultadCumCarreraEstudiante f WHERE f.carreraOpcion = :carreraOpcion"),
    @NamedQuery(name = "FacultadCumCarreraEstudiante.findByNotaMat", query = "SELECT f FROM FacultadCumCarreraEstudiante f WHERE f.notaMat = :notaMat"),
    @NamedQuery(name = "FacultadCumCarreraEstudiante.findByNotaEspanol", query = "SELECT f FROM FacultadCumCarreraEstudiante f WHERE f.notaEspanol = :notaEspanol"),
    @NamedQuery(name = "FacultadCumCarreraEstudiante.findByNotaHistoria", query = "SELECT f FROM FacultadCumCarreraEstudiante f WHERE f.notaHistoria = :notaHistoria"),
    @NamedQuery(name = "FacultadCumCarreraEstudiante.findByPromedioIngreso", query = "SELECT f FROM FacultadCumCarreraEstudiante f WHERE f.promedioIngreso = :promedioIngreso"),
    @NamedQuery(name = "FacultadCumCarreraEstudiante.findByEscalafon", query = "SELECT f FROM FacultadCumCarreraEstudiante f WHERE f.escalafon = :escalafon")})
public class FacultadCumCarreraEstudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FacultadCumCarreraEstudiantePK facultadCumCarreraEstudiantePK;
    @Basic(optional = false)
    @Column(name = "carrera_reoferta")
    private boolean carreraReoferta;
    @Column(name = "carrera_opcion")
    private Integer carreraOpcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nota_mat")
    private Float notaMat;
    @Column(name = "nota_espanol")
    private Float notaEspanol;
    @Column(name = "nota_historia")
    private Float notaHistoria;
    @Column(name = "promedio_ingreso")
    private Float promedioIngreso;
    @Column(name = "escalafon")
    private Integer escalafon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultadCumCarreraEstudiante")
    private List<Baja> bajaList;
    @JoinColumn(name = "estado_estudianteestado_estuciante_id", referencedColumnName = "estado_estuciante_id")
    @ManyToOne(optional = false)
    private EstadoEstudiante estadoEstudianteestadoEstucianteId;
    @JoinColumn(name = "estudianteestudiante_id", referencedColumnName = "estudiante_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estudiante estudiante;
    @JoinColumns({
        @JoinColumn(name = "facultad_cum_carrerafacultad_cumcumcodigocum", referencedColumnName = "facultad_cumcumcodigocum", insertable = false, updatable = false),
        @JoinColumn(name = "facultad_cum_carrerafacultad_cumfacultadcodigoarea", referencedColumnName = "facultad_cumfacultadcodigoarea", insertable = false, updatable = false),
        @JoinColumn(name = "facultad_cum_carreracarreraidcarrera", referencedColumnName = "carreraidcarrera", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private FacultadCumCarrera facultadCumCarrera;
    @JoinColumn(name = "fuente_ingresofuente_ingreso_id", referencedColumnName = "fuente_ingreso_id")
    @ManyToOne(optional = false)
    private FuenteIngreso fuenteIngresofuenteIngresoId;
    @JoinColumn(name = "planestudioidplanestudio", referencedColumnName = "idplanestudio")
    @ManyToOne(optional = false)
    private Planestudio planestudioidplanestudio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultadCumCarreraEstudiante")
    private List<FacultadCumCarreraEstudianteAsignatura> facultadCumCarreraEstudianteAsignaturaList;

    public FacultadCumCarreraEstudiante() {
    }

    public FacultadCumCarreraEstudiante(FacultadCumCarreraEstudiantePK facultadCumCarreraEstudiantePK) {
        this.facultadCumCarreraEstudiantePK = facultadCumCarreraEstudiantePK;
    }

    public FacultadCumCarreraEstudiante(FacultadCumCarreraEstudiantePK facultadCumCarreraEstudiantePK, boolean carreraReoferta) {
        this.facultadCumCarreraEstudiantePK = facultadCumCarreraEstudiantePK;
        this.carreraReoferta = carreraReoferta;
    }

    public FacultadCumCarreraEstudiante(String facultadCumCarrerafacultadCumcumcodigocum, String facultadCumCarrerafacultadCumfacultadcodigoarea, int facultadCumCarreracarreraidcarrera, String estudianteestudianteId, Date fechaMatricula) {
        this.facultadCumCarreraEstudiantePK = new FacultadCumCarreraEstudiantePK(facultadCumCarrerafacultadCumcumcodigocum, facultadCumCarrerafacultadCumfacultadcodigoarea, facultadCumCarreracarreraidcarrera, estudianteestudianteId, fechaMatricula);
    }

    public FacultadCumCarreraEstudiantePK getFacultadCumCarreraEstudiantePK() {
        return facultadCumCarreraEstudiantePK;
    }

    public void setFacultadCumCarreraEstudiantePK(FacultadCumCarreraEstudiantePK facultadCumCarreraEstudiantePK) {
        this.facultadCumCarreraEstudiantePK = facultadCumCarreraEstudiantePK;
    }

    public boolean getCarreraReoferta() {
        return carreraReoferta;
    }

    public void setCarreraReoferta(boolean carreraReoferta) {
        this.carreraReoferta = carreraReoferta;
    }

    public Integer getCarreraOpcion() {
        return carreraOpcion;
    }

    public void setCarreraOpcion(Integer carreraOpcion) {
        this.carreraOpcion = carreraOpcion;
    }

    public Float getNotaMat() {
        return notaMat;
    }

    public void setNotaMat(Float notaMat) {
        this.notaMat = notaMat;
    }

    public Float getNotaEspanol() {
        return notaEspanol;
    }

    public void setNotaEspanol(Float notaEspanol) {
        this.notaEspanol = notaEspanol;
    }

    public Float getNotaHistoria() {
        return notaHistoria;
    }

    public void setNotaHistoria(Float notaHistoria) {
        this.notaHistoria = notaHistoria;
    }

    public Float getPromedioIngreso() {
        return promedioIngreso;
    }

    public void setPromedioIngreso(Float promedioIngreso) {
        this.promedioIngreso = promedioIngreso;
    }

    public Integer getEscalafon() {
        return escalafon;
    }

    public void setEscalafon(Integer escalafon) {
        this.escalafon = escalafon;
    }

    @XmlTransient
    public List<Baja> getBajaList() {
        return bajaList;
    }

    public void setBajaList(List<Baja> bajaList) {
        this.bajaList = bajaList;
    }

    public EstadoEstudiante getEstadoEstudianteestadoEstucianteId() {
        return estadoEstudianteestadoEstucianteId;
    }

    public void setEstadoEstudianteestadoEstucianteId(EstadoEstudiante estadoEstudianteestadoEstucianteId) {
        this.estadoEstudianteestadoEstucianteId = estadoEstudianteestadoEstucianteId;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public FacultadCumCarrera getFacultadCumCarrera() {
        return facultadCumCarrera;
    }

    public void setFacultadCumCarrera(FacultadCumCarrera facultadCumCarrera) {
        this.facultadCumCarrera = facultadCumCarrera;
    }

    public FuenteIngreso getFuenteIngresofuenteIngresoId() {
        return fuenteIngresofuenteIngresoId;
    }

    public void setFuenteIngresofuenteIngresoId(FuenteIngreso fuenteIngresofuenteIngresoId) {
        this.fuenteIngresofuenteIngresoId = fuenteIngresofuenteIngresoId;
    }

    public Planestudio getPlanestudioidplanestudio() {
        return planestudioidplanestudio;
    }

    public void setPlanestudioidplanestudio(Planestudio planestudioidplanestudio) {
        this.planestudioidplanestudio = planestudioidplanestudio;
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
        hash += (facultadCumCarreraEstudiantePK != null ? facultadCumCarreraEstudiantePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacultadCumCarreraEstudiante)) {
            return false;
        }
        FacultadCumCarreraEstudiante other = (FacultadCumCarreraEstudiante) object;
        if ((this.facultadCumCarreraEstudiantePK == null && other.facultadCumCarreraEstudiantePK != null) || (this.facultadCumCarreraEstudiantePK != null && !this.facultadCumCarreraEstudiantePK.equals(other.facultadCumCarreraEstudiantePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FacultadCumCarreraEstudiante[ facultadCumCarreraEstudiantePK=" + facultadCumCarreraEstudiantePK + " ]";
    }
    
}
