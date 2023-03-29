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
@Table(name = "ocupacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ocupacion.findAll", query = "SELECT o FROM Ocupacion o"),
    @NamedQuery(name = "Ocupacion.findByOcupacionId", query = "SELECT o FROM Ocupacion o WHERE o.ocupacionId = :ocupacionId"),
    @NamedQuery(name = "Ocupacion.findByOcupacionNombre", query = "SELECT o FROM Ocupacion o WHERE o.ocupacionNombre = :ocupacionNombre"),
    @NamedQuery(name = "Ocupacion.findByOcupacionCancelado", query = "SELECT o FROM Ocupacion o WHERE o.ocupacionCancelado = :ocupacionCancelado")})
public class Ocupacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ocupacion_id")
    private Integer ocupacionId;
    @Basic(optional = false)
    @Column(name = "ocupacion_nombre")
    private String ocupacionNombre;
    @Basic(optional = false)
    @Column(name = "ocupacion_cancelado")
    private boolean ocupacionCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ocupacionocupacionId")
    private List<Tutor> tutorList;
    @OneToMany(mappedBy = "ocupacionocupacionId")
    private List<Estudiante> estudianteList;

    public Ocupacion() {
    }

    public Ocupacion(Integer ocupacionId) {
        this.ocupacionId = ocupacionId;
    }

    public Ocupacion(Integer ocupacionId, String ocupacionNombre, boolean ocupacionCancelado) {
        this.ocupacionId = ocupacionId;
        this.ocupacionNombre = ocupacionNombre;
        this.ocupacionCancelado = ocupacionCancelado;
    }

    public Integer getOcupacionId() {
        return ocupacionId;
    }

    public void setOcupacionId(Integer ocupacionId) {
        this.ocupacionId = ocupacionId;
    }

    public String getOcupacionNombre() {
        return ocupacionNombre;
    }

    public void setOcupacionNombre(String ocupacionNombre) {
        this.ocupacionNombre = ocupacionNombre;
    }

    public boolean getOcupacionCancelado() {
        return ocupacionCancelado;
    }

    public void setOcupacionCancelado(boolean ocupacionCancelado) {
        this.ocupacionCancelado = ocupacionCancelado;
    }

    @XmlTransient
    public List<Tutor> getTutorList() {
        return tutorList;
    }

    public void setTutorList(List<Tutor> tutorList) {
        this.tutorList = tutorList;
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
        hash += (ocupacionId != null ? ocupacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocupacion)) {
            return false;
        }
        Ocupacion other = (Ocupacion) object;
        if ((this.ocupacionId == null && other.ocupacionId != null) || (this.ocupacionId != null && !this.ocupacionId.equals(other.ocupacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ocupacion[ ocupacionId=" + ocupacionId + " ]";
    }

    public Ocupacion(String ocupacionNombre, boolean ocupacionCancelado) {
        this.ocupacionNombre = ocupacionNombre;
        this.ocupacionCancelado = ocupacionCancelado;
    }
}
