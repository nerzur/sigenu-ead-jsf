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
@Table(name = "facultad_cum_carrera")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacultadCumCarrera.findAll", query = "SELECT f FROM FacultadCumCarrera f"),
    @NamedQuery(name = "FacultadCumCarrera.findByFacultadCumcumcodigocum", query = "SELECT f FROM FacultadCumCarrera f WHERE f.facultadCumCarreraPK.facultadCumcumcodigocum = :facultadCumcumcodigocum"),
    @NamedQuery(name = "FacultadCumCarrera.findByFacultadCumfacultadcodigoarea", query = "SELECT f FROM FacultadCumCarrera f WHERE f.facultadCumCarreraPK.facultadCumfacultadcodigoarea = :facultadCumfacultadcodigoarea"),
    @NamedQuery(name = "FacultadCumCarrera.findByCarreraidcarrera", query = "SELECT f FROM FacultadCumCarrera f WHERE f.facultadCumCarreraPK.carreraidcarrera = :carreraidcarrera"),
    @NamedQuery(name = "FacultadCumCarrera.findByCancelado", query = "SELECT f FROM FacultadCumCarrera f WHERE f.cancelado = :cancelado")})
public class FacultadCumCarrera implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FacultadCumCarreraPK facultadCumCarreraPK;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultadCumCarrera")
    private List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultadCumCarrera")
    private List<Matricula> matriculaList;
    @JoinColumn(name = "carreraidcarrera", referencedColumnName = "idcarrera", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Carrera carrera;
    @JoinColumns({
        @JoinColumn(name = "facultad_cumcumcodigocum", referencedColumnName = "cumcodigocum", insertable = false, updatable = false),
        @JoinColumn(name = "facultad_cumfacultadcodigoarea", referencedColumnName = "facultadcodigoarea", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private FacultadCum facultadCum;

    public FacultadCumCarrera() {
    }

    public FacultadCumCarrera(FacultadCumCarreraPK facultadCumCarreraPK) {
        this.facultadCumCarreraPK = facultadCumCarreraPK;
    }

    public FacultadCumCarrera(FacultadCumCarreraPK facultadCumCarreraPK, boolean cancelado) {
        this.facultadCumCarreraPK = facultadCumCarreraPK;
        this.cancelado = cancelado;
    }

    public FacultadCumCarrera(String facultadCumcumcodigocum, String facultadCumfacultadcodigoarea, int carreraidcarrera) {
        this.facultadCumCarreraPK = new FacultadCumCarreraPK(facultadCumcumcodigocum, facultadCumfacultadcodigoarea, carreraidcarrera);
    }

    public FacultadCumCarreraPK getFacultadCumCarreraPK() {
        return facultadCumCarreraPK;
    }

    public void setFacultadCumCarreraPK(FacultadCumCarreraPK facultadCumCarreraPK) {
        this.facultadCumCarreraPK = facultadCumCarreraPK;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    @XmlTransient
    public List<FacultadCumCarreraEstudiante> getFacultadCumCarreraEstudianteList() {
        return facultadCumCarreraEstudianteList;
    }

    public void setFacultadCumCarreraEstudianteList(List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteList) {
        this.facultadCumCarreraEstudianteList = facultadCumCarreraEstudianteList;
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public FacultadCum getFacultadCum() {
        return facultadCum;
    }

    public void setFacultadCum(FacultadCum facultadCum) {
        this.facultadCum = facultadCum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facultadCumCarreraPK != null ? facultadCumCarreraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacultadCumCarrera)) {
            return false;
        }
        FacultadCumCarrera other = (FacultadCumCarrera) object;
        if ((this.facultadCumCarreraPK == null && other.facultadCumCarreraPK != null) || (this.facultadCumCarreraPK != null && !this.facultadCumCarreraPK.equals(other.facultadCumCarreraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FacultadCumCarrera[ facultadCumCarreraPK=" + facultadCumCarreraPK + " ]";
    }
    
}
