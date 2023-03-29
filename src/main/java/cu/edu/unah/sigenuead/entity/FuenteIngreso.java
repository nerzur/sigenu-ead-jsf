/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author claudy
 */
@Entity
@Table(name = "fuente_ingreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FuenteIngreso.findAll", query = "SELECT f FROM FuenteIngreso f"),
    @NamedQuery(name = "FuenteIngreso.findByFuenteIngresoId", query = "SELECT f FROM FuenteIngreso f WHERE f.fuenteIngresoId = :fuenteIngresoId"),
    @NamedQuery(name = "FuenteIngreso.findByFuenteIngresoNombre", query = "SELECT f FROM FuenteIngreso f WHERE f.fuenteIngresoNombre = :fuenteIngresoNombre"),
    @NamedQuery(name = "FuenteIngreso.findByFuenteIngresoCancelado", query = "SELECT f FROM FuenteIngreso f WHERE f.fuenteIngresoCancelado = :fuenteIngresoCancelado")})
public class FuenteIngreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fuente_ingreso_id")
    private Integer fuenteIngresoId;
    @Basic(optional = false)
    @Column(name = "fuente_ingreso_nombre")
    private String fuenteIngresoNombre;
    @Basic(optional = false)
    @Column(name = "fuente_ingreso_cancelado")
    private boolean fuenteIngresoCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuenteIngresofuenteIngresoId")
    private List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteList;

    public FuenteIngreso() {
    }

    public FuenteIngreso(Integer fuenteIngresoId) {
        this.fuenteIngresoId = fuenteIngresoId;
    }

    public FuenteIngreso(Integer fuenteIngresoId, String fuenteIngresoNombre, boolean fuenteIngresoCancelado) {
        this.fuenteIngresoId = fuenteIngresoId;
        this.fuenteIngresoNombre = fuenteIngresoNombre;
        this.fuenteIngresoCancelado = fuenteIngresoCancelado;
    }

    public Integer getFuenteIngresoId() {
        return fuenteIngresoId;
    }

    public void setFuenteIngresoId(Integer fuenteIngresoId) {
        this.fuenteIngresoId = fuenteIngresoId;
    }

    public String getFuenteIngresoNombre() {
        return fuenteIngresoNombre;
    }

    public void setFuenteIngresoNombre(String fuenteIngresoNombre) {
        this.fuenteIngresoNombre = fuenteIngresoNombre;
    }

    public boolean getFuenteIngresoCancelado() {
        return fuenteIngresoCancelado;
    }

    public void setFuenteIngresoCancelado(boolean fuenteIngresoCancelado) {
        this.fuenteIngresoCancelado = fuenteIngresoCancelado;
    }

    @XmlTransient
    public List<FacultadCumCarreraEstudiante> getFacultadCumCarreraEstudianteList() {
        return facultadCumCarreraEstudianteList;
    }

    public void setFacultadCumCarreraEstudianteList(List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteList) {
        this.facultadCumCarreraEstudianteList = facultadCumCarreraEstudianteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fuenteIngresoId != null ? fuenteIngresoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuenteIngreso)) {
            return false;
        }
        FuenteIngreso other = (FuenteIngreso) object;
        if ((this.fuenteIngresoId == null && other.fuenteIngresoId != null) || (this.fuenteIngresoId != null && !this.fuenteIngresoId.equals(other.fuenteIngresoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FuenteIngreso[ fuenteIngresoId=" + fuenteIngresoId + " ]";
    }

    public FuenteIngreso(String fuenteIngresoNombre, boolean fuenteIngresoCancelado) {
        this.fuenteIngresoNombre = fuenteIngresoNombre;
        this.fuenteIngresoCancelado = fuenteIngresoCancelado;
    }
}
