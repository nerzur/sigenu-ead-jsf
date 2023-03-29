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
@Table(name = "carrera")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrera.findAll", query = "SELECT c FROM Carrera c"),
    @NamedQuery(name = "Carrera.findByIdcarrera", query = "SELECT c FROM Carrera c WHERE c.idcarrera = :idcarrera"),
    @NamedQuery(name = "Carrera.findByCanceladacarrera", query = "SELECT c FROM Carrera c WHERE c.canceladacarrera = :canceladacarrera")})
public class Carrera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcarrera")
    private Integer idcarrera;
    @Basic(optional = false)
    @Column(name = "canceladacarrera")
    private boolean canceladacarrera;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrera")
    private List<FacultadCumCarrera> facultadCumCarreraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carreraidcarrera")
    private List<Planestudio> planestudioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carrera")
    private List<CarreraCurso> carreraCursoList;
    @JoinColumn(name = "carreranacionalidcarreranacional", referencedColumnName = "idcarreranacional")
    @ManyToOne(optional = false)
    private Carreranacional carreranacionalidcarreranacional;
    @JoinColumn(name = "facultadcodigoarea", referencedColumnName = "codigoarea")
    @ManyToOne
    private Facultad facultadcodigoarea;

    public Carrera() {
    }

    public Carrera(Integer idcarrera) {
        this.idcarrera = idcarrera;
    }

    public Carrera(Integer idcarrera, boolean canceladacarrera) {
        this.idcarrera = idcarrera;
        this.canceladacarrera = canceladacarrera;
    }

    public Integer getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(Integer idcarrera) {
        this.idcarrera = idcarrera;
    }

    public boolean getCanceladacarrera() {
        return canceladacarrera;
    }

    public void setCanceladacarrera(boolean canceladacarrera) {
        this.canceladacarrera = canceladacarrera;
    }

    @XmlTransient
    public List<FacultadCumCarrera> getFacultadCumCarreraList() {
        return facultadCumCarreraList;
    }

    public void setFacultadCumCarreraList(List<FacultadCumCarrera> facultadCumCarreraList) {
        this.facultadCumCarreraList = facultadCumCarreraList;
    }

    @XmlTransient
    public List<Planestudio> getPlanestudioList() {
        return planestudioList;
    }

    public void setPlanestudioList(List<Planestudio> planestudioList) {
        this.planestudioList = planestudioList;
    }

    @XmlTransient
    public List<CarreraCurso> getCarreraCursoList() {
        return carreraCursoList;
    }

    public void setCarreraCursoList(List<CarreraCurso> carreraCursoList) {
        this.carreraCursoList = carreraCursoList;
    }

    public Carreranacional getCarreranacionalidcarreranacional() {
        return carreranacionalidcarreranacional;
    }

    public void setCarreranacionalidcarreranacional(Carreranacional carreranacionalidcarreranacional) {
        this.carreranacionalidcarreranacional = carreranacionalidcarreranacional;
    }

    public Facultad getFacultadcodigoarea() {
        return facultadcodigoarea;
    }

    public void setFacultadcodigoarea(Facultad facultadcodigoarea) {
        this.facultadcodigoarea = facultadcodigoarea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcarrera != null ? idcarrera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrera)) {
            return false;
        }
        Carrera other = (Carrera) object;
        if ((this.idcarrera == null && other.idcarrera != null) || (this.idcarrera != null && !this.idcarrera.equals(other.idcarrera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Carrera[ idcarrera=" + idcarrera + " ]";
    }

    public Carrera(boolean canceladacarrera, Carreranacional carreranacionalidcarreranacional, Facultad facultadcodigoarea) {
        this.canceladacarrera = canceladacarrera;
        this.carreranacionalidcarreranacional = carreranacionalidcarreranacional;
        this.facultadcodigoarea = facultadcodigoarea;
    }
}
