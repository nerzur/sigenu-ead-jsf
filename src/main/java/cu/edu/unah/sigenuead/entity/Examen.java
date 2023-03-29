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
@Table(name = "examen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examen.findAll", query = "SELECT e FROM Examen e"),
    @NamedQuery(name = "Examen.findByExamenId", query = "SELECT e FROM Examen e WHERE e.examenId = :examenId"),
    @NamedQuery(name = "Examen.findByExamenTipo", query = "SELECT e FROM Examen e WHERE e.examenTipo = :examenTipo"),
    @NamedQuery(name = "Examen.findByExamenCancelado", query = "SELECT e FROM Examen e WHERE e.examenCancelado = :examenCancelado")})
public class Examen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "examen_id")
    private Integer examenId;
    @Basic(optional = false)
    @Column(name = "examen_tipo")
    private String examenTipo;
    @Basic(optional = false)
    @Column(name = "examen_cancelado")
    private boolean examenCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examen")
    private List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> examenMatriculaFacultadCumCarreraEstudianteAsignaturaList;

    public Examen() {
    }

    public Examen(Integer examenId) {
        this.examenId = examenId;
    }

    public Examen(Integer examenId, String examenTipo, boolean examenCancelado) {
        this.examenId = examenId;
        this.examenTipo = examenTipo;
        this.examenCancelado = examenCancelado;
    }

    public Integer getExamenId() {
        return examenId;
    }

    public void setExamenId(Integer examenId) {
        this.examenId = examenId;
    }

    public String getExamenTipo() {
        return examenTipo;
    }

    public void setExamenTipo(String examenTipo) {
        this.examenTipo = examenTipo;
    }

    public boolean getExamenCancelado() {
        return examenCancelado;
    }

    public void setExamenCancelado(boolean examenCancelado) {
        this.examenCancelado = examenCancelado;
    }

    @XmlTransient
    public List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> getExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList() {
        return examenMatriculaFacultadCumCarreraEstudianteAsignaturaList;
    }

    public void setExamenMatriculaFacultadCumCarreraEstudianteAsignaturaList(List<ExamenMatriculaFacultadCumCarreraEstudianteAsignatura> examenMatriculaFacultadCumCarreraEstudianteAsignaturaList) {
        this.examenMatriculaFacultadCumCarreraEstudianteAsignaturaList = examenMatriculaFacultadCumCarreraEstudianteAsignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examenId != null ? examenId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examen)) {
            return false;
        }
        Examen other = (Examen) object;
        if ((this.examenId == null && other.examenId != null) || (this.examenId != null && !this.examenId.equals(other.examenId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Examen[ examenId=" + examenId + " ]";
    }

    public Examen(String examenTipo, boolean examenCancelado) {
        this.examenTipo = examenTipo;
        this.examenCancelado = examenCancelado;
    }
}
