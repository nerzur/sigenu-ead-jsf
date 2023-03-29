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
@Table(name = "especialidad_militar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EspecialidadMilitar.findAll", query = "SELECT e FROM EspecialidadMilitar e"),
    @NamedQuery(name = "EspecialidadMilitar.findByEspecialidadMilitarId", query = "SELECT e FROM EspecialidadMilitar e WHERE e.especialidadMilitarId = :especialidadMilitarId"),
    @NamedQuery(name = "EspecialidadMilitar.findByEspecialidadMilitarNombre", query = "SELECT e FROM EspecialidadMilitar e WHERE e.especialidadMilitarNombre = :especialidadMilitarNombre"),
    @NamedQuery(name = "EspecialidadMilitar.findByEspecialidadMilitarCancelado", query = "SELECT e FROM EspecialidadMilitar e WHERE e.especialidadMilitarCancelado = :especialidadMilitarCancelado")})
public class EspecialidadMilitar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "especialidad_militar_id")
    private Integer especialidadMilitarId;
    @Basic(optional = false)
    @Column(name = "especialidad_militar_nombre")
    private String especialidadMilitarNombre;
    @Basic(optional = false)
    @Column(name = "especialidad_militar_cancelado")
    private boolean especialidadMilitarCancelado;
    @OneToMany(mappedBy = "especialidadMilitarespecialidadMilitarId")
    private List<Estudiante> estudianteList;

    public EspecialidadMilitar() {
    }

    public EspecialidadMilitar(Integer especialidadMilitarId) {
        this.especialidadMilitarId = especialidadMilitarId;
    }

    public EspecialidadMilitar(Integer especialidadMilitarId, String especialidadMilitarNombre, boolean especialidadMilitarCancelado) {
        this.especialidadMilitarId = especialidadMilitarId;
        this.especialidadMilitarNombre = especialidadMilitarNombre;
        this.especialidadMilitarCancelado = especialidadMilitarCancelado;
    }

    public Integer getEspecialidadMilitarId() {
        return especialidadMilitarId;
    }

    public void setEspecialidadMilitarId(Integer especialidadMilitarId) {
        this.especialidadMilitarId = especialidadMilitarId;
    }

    public String getEspecialidadMilitarNombre() {
        return especialidadMilitarNombre;
    }

    public void setEspecialidadMilitarNombre(String especialidadMilitarNombre) {
        this.especialidadMilitarNombre = especialidadMilitarNombre;
    }

    public boolean getEspecialidadMilitarCancelado() {
        return especialidadMilitarCancelado;
    }

    public void setEspecialidadMilitarCancelado(boolean especialidadMilitarCancelado) {
        this.especialidadMilitarCancelado = especialidadMilitarCancelado;
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
        hash += (especialidadMilitarId != null ? especialidadMilitarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspecialidadMilitar)) {
            return false;
        }
        EspecialidadMilitar other = (EspecialidadMilitar) object;
        if ((this.especialidadMilitarId == null && other.especialidadMilitarId != null) || (this.especialidadMilitarId != null && !this.especialidadMilitarId.equals(other.especialidadMilitarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EspecialidadMilitar[ especialidadMilitarId=" + especialidadMilitarId + " ]";
    }

    public EspecialidadMilitar(String especialidadMilitarNombre, boolean especialidadMilitarCancelado) {
        this.especialidadMilitarNombre = especialidadMilitarNombre;
        this.especialidadMilitarCancelado = especialidadMilitarCancelado;
    }
}
