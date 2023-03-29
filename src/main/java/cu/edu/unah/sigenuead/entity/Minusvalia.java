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
@Table(name = "minusvalia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Minusvalia.findAll", query = "SELECT m FROM Minusvalia m"),
    @NamedQuery(name = "Minusvalia.findByMinusvaliaId", query = "SELECT m FROM Minusvalia m WHERE m.minusvaliaId = :minusvaliaId"),
    @NamedQuery(name = "Minusvalia.findByMinusvaliaNombre", query = "SELECT m FROM Minusvalia m WHERE m.minusvaliaNombre = :minusvaliaNombre"),
    @NamedQuery(name = "Minusvalia.findByMinusvaliaCancelado", query = "SELECT m FROM Minusvalia m WHERE m.minusvaliaCancelado = :minusvaliaCancelado")})
public class Minusvalia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "minusvalia_id")
    private Integer minusvaliaId;
    @Basic(optional = false)
    @Column(name = "minusvalia_nombre")
    private String minusvaliaNombre;
    @Basic(optional = false)
    @Column(name = "minusvalia_cancelado")
    private boolean minusvaliaCancelado;
    @ManyToMany(mappedBy = "minusvaliaList")
    private List<Estudiante> estudianteList;

    public Minusvalia() {
    }

    public Minusvalia(Integer minusvaliaId) {
        this.minusvaliaId = minusvaliaId;
    }

    public Minusvalia(Integer minusvaliaId, String minusvaliaNombre, boolean minusvaliaCancelado) {
        this.minusvaliaId = minusvaliaId;
        this.minusvaliaNombre = minusvaliaNombre;
        this.minusvaliaCancelado = minusvaliaCancelado;
    }

    public Integer getMinusvaliaId() {
        return minusvaliaId;
    }

    public void setMinusvaliaId(Integer minusvaliaId) {
        this.minusvaliaId = minusvaliaId;
    }

    public String getMinusvaliaNombre() {
        return minusvaliaNombre;
    }

    public void setMinusvaliaNombre(String minusvaliaNombre) {
        this.minusvaliaNombre = minusvaliaNombre;
    }

    public boolean getMinusvaliaCancelado() {
        return minusvaliaCancelado;
    }

    public void setMinusvaliaCancelado(boolean minusvaliaCancelado) {
        this.minusvaliaCancelado = minusvaliaCancelado;
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
        hash += (minusvaliaId != null ? minusvaliaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Minusvalia)) {
            return false;
        }
        Minusvalia other = (Minusvalia) object;
        if ((this.minusvaliaId == null && other.minusvaliaId != null) || (this.minusvaliaId != null && !this.minusvaliaId.equals(other.minusvaliaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Minusvalia[ minusvaliaId=" + minusvaliaId + " ]";
    }

    public Minusvalia(String minusvaliaNombre, boolean minusvaliaCancelado) {
        this.minusvaliaNombre = minusvaliaNombre;
        this.minusvaliaCancelado = minusvaliaCancelado;
    }
}
