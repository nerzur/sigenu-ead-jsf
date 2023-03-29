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
@Table(name = "grado_militar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GradoMilitar.findAll", query = "SELECT g FROM GradoMilitar g"),
    @NamedQuery(name = "GradoMilitar.findByGradoMilitarId", query = "SELECT g FROM GradoMilitar g WHERE g.gradoMilitarId = :gradoMilitarId"),
    @NamedQuery(name = "GradoMilitar.findByGradoMilitarNombre", query = "SELECT g FROM GradoMilitar g WHERE g.gradoMilitarNombre = :gradoMilitarNombre"),
    @NamedQuery(name = "GradoMilitar.findByGradoMilitarCancelado", query = "SELECT g FROM GradoMilitar g WHERE g.gradoMilitarCancelado = :gradoMilitarCancelado")})
public class GradoMilitar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "grado_militar_id")
    private Integer gradoMilitarId;
    @Basic(optional = false)
    @Column(name = "grado_militar_nombre")
    private String gradoMilitarNombre;
    @Basic(optional = false)
    @Column(name = "grado_militar_cancelado")
    private boolean gradoMilitarCancelado;
    @OneToMany(mappedBy = "gradoMilitargradoMilitarId")
    private List<Estudiante> estudianteList;

    public GradoMilitar() {
    }

    public GradoMilitar(Integer gradoMilitarId) {
        this.gradoMilitarId = gradoMilitarId;
    }

    public GradoMilitar(Integer gradoMilitarId, String gradoMilitarNombre, boolean gradoMilitarCancelado) {
        this.gradoMilitarId = gradoMilitarId;
        this.gradoMilitarNombre = gradoMilitarNombre;
        this.gradoMilitarCancelado = gradoMilitarCancelado;
    }

    public Integer getGradoMilitarId() {
        return gradoMilitarId;
    }

    public void setGradoMilitarId(Integer gradoMilitarId) {
        this.gradoMilitarId = gradoMilitarId;
    }

    public String getGradoMilitarNombre() {
        return gradoMilitarNombre;
    }

    public void setGradoMilitarNombre(String gradoMilitarNombre) {
        this.gradoMilitarNombre = gradoMilitarNombre;
    }

    public boolean getGradoMilitarCancelado() {
        return gradoMilitarCancelado;
    }

    public void setGradoMilitarCancelado(boolean gradoMilitarCancelado) {
        this.gradoMilitarCancelado = gradoMilitarCancelado;
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
        hash += (gradoMilitarId != null ? gradoMilitarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GradoMilitar)) {
            return false;
        }
        GradoMilitar other = (GradoMilitar) object;
        if ((this.gradoMilitarId == null && other.gradoMilitarId != null) || (this.gradoMilitarId != null && !this.gradoMilitarId.equals(other.gradoMilitarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GradoMilitar[ gradoMilitarId=" + gradoMilitarId + " ]";
    }

    public GradoMilitar(String gradoMilitarNombre, boolean gradoMilitarCancelado) {
        this.gradoMilitarNombre = gradoMilitarNombre;
        this.gradoMilitarCancelado = gradoMilitarCancelado;
    }
}
