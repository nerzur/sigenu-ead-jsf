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
@Table(name = "organizacion_politica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrganizacionPolitica.findAll", query = "SELECT o FROM OrganizacionPolitica o"),
    @NamedQuery(name = "OrganizacionPolitica.findByOrganizacionPoliticaId", query = "SELECT o FROM OrganizacionPolitica o WHERE o.organizacionPoliticaId = :organizacionPoliticaId"),
    @NamedQuery(name = "OrganizacionPolitica.findByOrganizacionPoliticaNombre", query = "SELECT o FROM OrganizacionPolitica o WHERE o.organizacionPoliticaNombre = :organizacionPoliticaNombre"),
    @NamedQuery(name = "OrganizacionPolitica.findByOrganizacionPoliticaCancelado", query = "SELECT o FROM OrganizacionPolitica o WHERE o.organizacionPoliticaCancelado = :organizacionPoliticaCancelado")})
public class OrganizacionPolitica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organizacion_politica_id")
    private Integer organizacionPoliticaId;
    @Basic(optional = false)
    @Column(name = "organizacion_politica_nombre")
    private String organizacionPoliticaNombre;
    @Basic(optional = false)
    @Column(name = "organizacion_politica_cancelado")
    private boolean organizacionPoliticaCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizacionPoliticaorganizacionPoliticaId")
    private List<Tutor> tutorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizacionPoliticaorganizacionPoliticaId")
    private List<Estudiante> estudianteList;

    public OrganizacionPolitica() {
    }

    public OrganizacionPolitica(Integer organizacionPoliticaId) {
        this.organizacionPoliticaId = organizacionPoliticaId;
    }

    public OrganizacionPolitica(Integer organizacionPoliticaId, String organizacionPoliticaNombre, boolean organizacionPoliticaCancelado) {
        this.organizacionPoliticaId = organizacionPoliticaId;
        this.organizacionPoliticaNombre = organizacionPoliticaNombre;
        this.organizacionPoliticaCancelado = organizacionPoliticaCancelado;
    }

    public Integer getOrganizacionPoliticaId() {
        return organizacionPoliticaId;
    }

    public void setOrganizacionPoliticaId(Integer organizacionPoliticaId) {
        this.organizacionPoliticaId = organizacionPoliticaId;
    }

    public String getOrganizacionPoliticaNombre() {
        return organizacionPoliticaNombre;
    }

    public void setOrganizacionPoliticaNombre(String organizacionPoliticaNombre) {
        this.organizacionPoliticaNombre = organizacionPoliticaNombre;
    }

    public boolean getOrganizacionPoliticaCancelado() {
        return organizacionPoliticaCancelado;
    }

    public void setOrganizacionPoliticaCancelado(boolean organizacionPoliticaCancelado) {
        this.organizacionPoliticaCancelado = organizacionPoliticaCancelado;
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
        hash += (organizacionPoliticaId != null ? organizacionPoliticaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizacionPolitica)) {
            return false;
        }
        OrganizacionPolitica other = (OrganizacionPolitica) object;
        if ((this.organizacionPoliticaId == null && other.organizacionPoliticaId != null) || (this.organizacionPoliticaId != null && !this.organizacionPoliticaId.equals(other.organizacionPoliticaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrganizacionPolitica[ organizacionPoliticaId=" + organizacionPoliticaId + " ]";
    }

    public OrganizacionPolitica(String organizacionPoliticaNombre, boolean organizacionPoliticaCancelado) {
        this.organizacionPoliticaNombre = organizacionPoliticaNombre;
        this.organizacionPoliticaCancelado = organizacionPoliticaCancelado;
    }
}
