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
@Table(name = "organizacion_popular")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrganizacionPopular.findAll", query = "SELECT o FROM OrganizacionPopular o"),
    @NamedQuery(name = "OrganizacionPopular.findByOrganizacionPopularId", query = "SELECT o FROM OrganizacionPopular o WHERE o.organizacionPopularId = :organizacionPopularId"),
    @NamedQuery(name = "OrganizacionPopular.findByOrganizacionPopularNombre", query = "SELECT o FROM OrganizacionPopular o WHERE o.organizacionPopularNombre = :organizacionPopularNombre"),
    @NamedQuery(name = "OrganizacionPopular.findByOrganizacionPopularCancelado", query = "SELECT o FROM OrganizacionPopular o WHERE o.organizacionPopularCancelado = :organizacionPopularCancelado")})
public class OrganizacionPopular implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organizacion_popular_id")
    private Integer organizacionPopularId;
    @Basic(optional = false)
    @Column(name = "organizacion_popular_nombre")
    private String organizacionPopularNombre;
    @Basic(optional = false)
    @Column(name = "organizacion_popular_cancelado")
    private boolean organizacionPopularCancelado;
    @ManyToMany(mappedBy = "organizacionPopularList")
    private List<Estudiante> estudianteList;

    public OrganizacionPopular() {
    }

    public OrganizacionPopular(Integer organizacionPopularId) {
        this.organizacionPopularId = organizacionPopularId;
    }

    public OrganizacionPopular(Integer organizacionPopularId, String organizacionPopularNombre, boolean organizacionPopularCancelado) {
        this.organizacionPopularId = organizacionPopularId;
        this.organizacionPopularNombre = organizacionPopularNombre;
        this.organizacionPopularCancelado = organizacionPopularCancelado;
    }

    public Integer getOrganizacionPopularId() {
        return organizacionPopularId;
    }

    public void setOrganizacionPopularId(Integer organizacionPopularId) {
        this.organizacionPopularId = organizacionPopularId;
    }

    public String getOrganizacionPopularNombre() {
        return organizacionPopularNombre;
    }

    public void setOrganizacionPopularNombre(String organizacionPopularNombre) {
        this.organizacionPopularNombre = organizacionPopularNombre;
    }

    public boolean getOrganizacionPopularCancelado() {
        return organizacionPopularCancelado;
    }

    public void setOrganizacionPopularCancelado(boolean organizacionPopularCancelado) {
        this.organizacionPopularCancelado = organizacionPopularCancelado;
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
        hash += (organizacionPopularId != null ? organizacionPopularId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizacionPopular)) {
            return false;
        }
        OrganizacionPopular other = (OrganizacionPopular) object;
        if ((this.organizacionPopularId == null && other.organizacionPopularId != null) || (this.organizacionPopularId != null && !this.organizacionPopularId.equals(other.organizacionPopularId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrganizacionPopular[ organizacionPopularId=" + organizacionPopularId + " ]";
    }

    public OrganizacionPopular(String organizacionPopularNombre, boolean organizacionPopularCancelado) {
        this.organizacionPopularNombre = organizacionPopularNombre;
        this.organizacionPopularCancelado = organizacionPopularCancelado;
    }
}
