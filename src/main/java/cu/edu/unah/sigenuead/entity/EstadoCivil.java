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
@Table(name = "estado_civil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCivil.findAll", query = "SELECT e FROM EstadoCivil e"),
    @NamedQuery(name = "EstadoCivil.findByEstadoCivilId", query = "SELECT e FROM EstadoCivil e WHERE e.estadoCivilId = :estadoCivilId"),
    @NamedQuery(name = "EstadoCivil.findByEstadoCivilNombre", query = "SELECT e FROM EstadoCivil e WHERE e.estadoCivilNombre = :estadoCivilNombre"),
    @NamedQuery(name = "EstadoCivil.findByEstadoCivilCancelado", query = "SELECT e FROM EstadoCivil e WHERE e.estadoCivilCancelado = :estadoCivilCancelado")})
public class EstadoCivil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "estado_civil_id")
    private Integer estadoCivilId;
    @Basic(optional = false)
    @Column(name = "estado_civil_nombre")
    private String estadoCivilNombre;
    @Basic(optional = false)
    @Column(name = "estado_civil_cancelado")
    private boolean estadoCivilCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoCivilestadoCivilId")
    private List<Estudiante> estudianteList;

    public EstadoCivil() {
    }

    public EstadoCivil(Integer estadoCivilId) {
        this.estadoCivilId = estadoCivilId;
    }

    public EstadoCivil(Integer estadoCivilId, String estadoCivilNombre, boolean estadoCivilCancelado) {
        this.estadoCivilId = estadoCivilId;
        this.estadoCivilNombre = estadoCivilNombre;
        this.estadoCivilCancelado = estadoCivilCancelado;
    }

    public Integer getEstadoCivilId() {
        return estadoCivilId;
    }

    public void setEstadoCivilId(Integer estadoCivilId) {
        this.estadoCivilId = estadoCivilId;
    }

    public String getEstadoCivilNombre() {
        return estadoCivilNombre;
    }

    public void setEstadoCivilNombre(String estadoCivilNombre) {
        this.estadoCivilNombre = estadoCivilNombre;
    }

    public boolean getEstadoCivilCancelado() {
        return estadoCivilCancelado;
    }

    public void setEstadoCivilCancelado(boolean estadoCivilCancelado) {
        this.estadoCivilCancelado = estadoCivilCancelado;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoCivilId != null ? estadoCivilId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCivil)) {
            return false;
        }
        EstadoCivil other = (EstadoCivil) object;
        if ((this.estadoCivilId == null && other.estadoCivilId != null) || (this.estadoCivilId != null && !this.estadoCivilId.equals(other.estadoCivilId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EstadoCivil[ estadoCivilId=" + estadoCivilId + " ]";
    }

    public EstadoCivil(String estadoCivilNombre, boolean estadoCivilCancelado) {
        this.estadoCivilNombre = estadoCivilNombre;
        this.estadoCivilCancelado = estadoCivilCancelado;
    }
}
