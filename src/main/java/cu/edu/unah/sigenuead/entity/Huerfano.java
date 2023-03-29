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
@Table(name = "huerfano")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Huerfano.findAll", query = "SELECT h FROM Huerfano h"),
    @NamedQuery(name = "Huerfano.findByHuerfanoId", query = "SELECT h FROM Huerfano h WHERE h.huerfanoId = :huerfanoId"),
    @NamedQuery(name = "Huerfano.findByHuerfanoNombre", query = "SELECT h FROM Huerfano h WHERE h.huerfanoNombre = :huerfanoNombre"),
    @NamedQuery(name = "Huerfano.findByHuerfanoCancelado", query = "SELECT h FROM Huerfano h WHERE h.huerfanoCancelado = :huerfanoCancelado")})
public class Huerfano implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "huerfano_id")
    private Integer huerfanoId;
    @Basic(optional = false)
    @Column(name = "huerfano_nombre")
    private String huerfanoNombre;
    @Basic(optional = false)
    @Column(name = "huerfano_cancelado")
    private boolean huerfanoCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "huerfanohuerfanoId")
    private List<Estudiante> estudianteList;

    public Huerfano() {
    }

    public Huerfano(Integer huerfanoId) {
        this.huerfanoId = huerfanoId;
    }

    public Huerfano(Integer huerfanoId, String huerfanoNombre, boolean huerfanoCancelado) {
        this.huerfanoId = huerfanoId;
        this.huerfanoNombre = huerfanoNombre;
        this.huerfanoCancelado = huerfanoCancelado;
    }

    public Integer getHuerfanoId() {
        return huerfanoId;
    }

    public void setHuerfanoId(Integer huerfanoId) {
        this.huerfanoId = huerfanoId;
    }

    public String getHuerfanoNombre() {
        return huerfanoNombre;
    }

    public void setHuerfanoNombre(String huerfanoNombre) {
        this.huerfanoNombre = huerfanoNombre;
    }

    public boolean getHuerfanoCancelado() {
        return huerfanoCancelado;
    }

    public void setHuerfanoCancelado(boolean huerfanoCancelado) {
        this.huerfanoCancelado = huerfanoCancelado;
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
        hash += (huerfanoId != null ? huerfanoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Huerfano)) {
            return false;
        }
        Huerfano other = (Huerfano) object;
        if ((this.huerfanoId == null && other.huerfanoId != null) || (this.huerfanoId != null && !this.huerfanoId.equals(other.huerfanoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Huerfano[ huerfanoId=" + huerfanoId + " ]";
    }

    public Huerfano(String huerfanoNombre, boolean huerfanoCancelado) {
        this.huerfanoNombre = huerfanoNombre;
        this.huerfanoCancelado = huerfanoCancelado;
    }
}
