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
@Table(name = "nivel_escolar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelEscolar.findAll", query = "SELECT n FROM NivelEscolar n"),
    @NamedQuery(name = "NivelEscolar.findByNivelEscolarId", query = "SELECT n FROM NivelEscolar n WHERE n.nivelEscolarId = :nivelEscolarId"),
    @NamedQuery(name = "NivelEscolar.findByNivelEscolarNombre", query = "SELECT n FROM NivelEscolar n WHERE n.nivelEscolarNombre = :nivelEscolarNombre"),
    @NamedQuery(name = "NivelEscolar.findByNivelEscolarCancelado", query = "SELECT n FROM NivelEscolar n WHERE n.nivelEscolarCancelado = :nivelEscolarCancelado")})
public class NivelEscolar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nivel_escolar_id")
    private Integer nivelEscolarId;
    @Basic(optional = false)
    @Column(name = "nivel_escolar_nombre")
    private String nivelEscolarNombre;
    @Basic(optional = false)
    @Column(name = "nivel_escolar_cancelado")
    private boolean nivelEscolarCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nivelEscolarnivelEscolarId")
    private List<Tutor> tutorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nivelEscolarnivelEscolarId")
    private List<Estudiante> estudianteList;

    public NivelEscolar() {
    }

    public NivelEscolar(Integer nivelEscolarId) {
        this.nivelEscolarId = nivelEscolarId;
    }

    public NivelEscolar(Integer nivelEscolarId, String nivelEscolarNombre, boolean nivelEscolarCancelado) {
        this.nivelEscolarId = nivelEscolarId;
        this.nivelEscolarNombre = nivelEscolarNombre;
        this.nivelEscolarCancelado = nivelEscolarCancelado;
    }

    public Integer getNivelEscolarId() {
        return nivelEscolarId;
    }

    public void setNivelEscolarId(Integer nivelEscolarId) {
        this.nivelEscolarId = nivelEscolarId;
    }

    public String getNivelEscolarNombre() {
        return nivelEscolarNombre;
    }

    public void setNivelEscolarNombre(String nivelEscolarNombre) {
        this.nivelEscolarNombre = nivelEscolarNombre;
    }

    public boolean getNivelEscolarCancelado() {
        return nivelEscolarCancelado;
    }

    public void setNivelEscolarCancelado(boolean nivelEscolarCancelado) {
        this.nivelEscolarCancelado = nivelEscolarCancelado;
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
        hash += (nivelEscolarId != null ? nivelEscolarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelEscolar)) {
            return false;
        }
        NivelEscolar other = (NivelEscolar) object;
        if ((this.nivelEscolarId == null && other.nivelEscolarId != null) || (this.nivelEscolarId != null && !this.nivelEscolarId.equals(other.nivelEscolarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.NivelEscolar[ nivelEscolarId=" + nivelEscolarId + " ]";
    }

    public NivelEscolar(String nivelEscolarNombre, boolean nivelEscolarCancelado) {
        this.nivelEscolarNombre = nivelEscolarNombre;
        this.nivelEscolarCancelado = nivelEscolarCancelado;
    }
}
