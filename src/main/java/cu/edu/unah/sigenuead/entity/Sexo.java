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
@Table(name = "sexo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sexo.findAll", query = "SELECT s FROM Sexo s"),
    @NamedQuery(name = "Sexo.findBySexoId", query = "SELECT s FROM Sexo s WHERE s.sexoId = :sexoId"),
    @NamedQuery(name = "Sexo.findBySexoNombre", query = "SELECT s FROM Sexo s WHERE s.sexoNombre = :sexoNombre"),
    @NamedQuery(name = "Sexo.findBySexoCancelado", query = "SELECT s FROM Sexo s WHERE s.sexoCancelado = :sexoCancelado")})
public class Sexo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sexo_id")
    private Integer sexoId;
    @Basic(optional = false)
    @Column(name = "sexo_nombre")
    private String sexoNombre;
    @Basic(optional = false)
    @Column(name = "sexo_cancelado")
    private boolean sexoCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sexosexoId")
    private List<Tutor> tutorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sexosexoId")
    private List<Estudiante> estudianteList;

    public Sexo() {
    }

    public Sexo(Integer sexoId) {
        this.sexoId = sexoId;
    }

    public Sexo(Integer sexoId, String sexoNombre, boolean sexoCancelado) {
        this.sexoId = sexoId;
        this.sexoNombre = sexoNombre;
        this.sexoCancelado = sexoCancelado;
    }

    public Integer getSexoId() {
        return sexoId;
    }

    public void setSexoId(Integer sexoId) {
        this.sexoId = sexoId;
    }

    public String getSexoNombre() {
        return sexoNombre;
    }

    public void setSexoNombre(String sexoNombre) {
        this.sexoNombre = sexoNombre;
    }

    public boolean getSexoCancelado() {
        return sexoCancelado;
    }

    public void setSexoCancelado(boolean sexoCancelado) {
        this.sexoCancelado = sexoCancelado;
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
        hash += (sexoId != null ? sexoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sexo)) {
            return false;
        }
        Sexo other = (Sexo) object;
        if ((this.sexoId == null && other.sexoId != null) || (this.sexoId != null && !this.sexoId.equals(other.sexoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Sexo[ sexoId=" + sexoId + " ]";
    }

    public Sexo(String sexoNombre, boolean sexoCancelado) {
        this.sexoNombre = sexoNombre;
        this.sexoCancelado = sexoCancelado;
    }
}
