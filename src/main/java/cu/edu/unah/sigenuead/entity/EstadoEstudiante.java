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
@Table(name = "estado_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoEstudiante.findAll", query = "SELECT e FROM EstadoEstudiante e"),
    @NamedQuery(name = "EstadoEstudiante.findByEstadoEstucianteId", query = "SELECT e FROM EstadoEstudiante e WHERE e.estadoEstucianteId = :estadoEstucianteId"),
    @NamedQuery(name = "EstadoEstudiante.findByEstadoEstudianteNombre", query = "SELECT e FROM EstadoEstudiante e WHERE e.estadoEstudianteNombre = :estadoEstudianteNombre"),
    @NamedQuery(name = "EstadoEstudiante.findByEstadoEstudianteCancelado", query = "SELECT e FROM EstadoEstudiante e WHERE e.estadoEstudianteCancelado = :estadoEstudianteCancelado")})
public class EstadoEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "estado_estuciante_id")
    private Integer estadoEstucianteId;
    @Basic(optional = false)
    @Column(name = "estado_estudiante_nombre")
    private String estadoEstudianteNombre;
    @Basic(optional = false)
    @Column(name = "estado_estudiante_cancelado")
    private boolean estadoEstudianteCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoEstudianteestadoEstucianteId")
    private List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteList;

    public EstadoEstudiante() {
    }

    public EstadoEstudiante(Integer estadoEstucianteId) {
        this.estadoEstucianteId = estadoEstucianteId;
    }

    public EstadoEstudiante(Integer estadoEstucianteId, String estadoEstudianteNombre, boolean estadoEstudianteCancelado) {
        this.estadoEstucianteId = estadoEstucianteId;
        this.estadoEstudianteNombre = estadoEstudianteNombre;
        this.estadoEstudianteCancelado = estadoEstudianteCancelado;
    }

    public Integer getEstadoEstucianteId() {
        return estadoEstucianteId;
    }

    public void setEstadoEstucianteId(Integer estadoEstucianteId) {
        this.estadoEstucianteId = estadoEstucianteId;
    }

    public String getEstadoEstudianteNombre() {
        return estadoEstudianteNombre;
    }

    public void setEstadoEstudianteNombre(String estadoEstudianteNombre) {
        this.estadoEstudianteNombre = estadoEstudianteNombre;
    }

    public boolean getEstadoEstudianteCancelado() {
        return estadoEstudianteCancelado;
    }

    public void setEstadoEstudianteCancelado(boolean estadoEstudianteCancelado) {
        this.estadoEstudianteCancelado = estadoEstudianteCancelado;
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
        hash += (estadoEstucianteId != null ? estadoEstucianteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoEstudiante)) {
            return false;
        }
        EstadoEstudiante other = (EstadoEstudiante) object;
        if ((this.estadoEstucianteId == null && other.estadoEstucianteId != null) || (this.estadoEstucianteId != null && !this.estadoEstucianteId.equals(other.estadoEstucianteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EstadoEstudiante[ estadoEstucianteId=" + estadoEstucianteId + " ]";
    }

    public EstadoEstudiante(String estadoEstudianteNombre, boolean estadoEstudianteCancelado) {
        this.estadoEstudianteNombre = estadoEstudianteNombre;
        this.estadoEstudianteCancelado = estadoEstudianteCancelado;
    }
}
