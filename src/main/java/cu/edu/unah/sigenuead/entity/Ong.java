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
@Table(name = "ong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ong.findAll", query = "SELECT o FROM Ong o"),
    @NamedQuery(name = "Ong.findByOngId", query = "SELECT o FROM Ong o WHERE o.ongId = :ongId"),
    @NamedQuery(name = "Ong.findByOngNombre", query = "SELECT o FROM Ong o WHERE o.ongNombre = :ongNombre"),
    @NamedQuery(name = "Ong.findByOngCancelado", query = "SELECT o FROM Ong o WHERE o.ongCancelado = :ongCancelado")})
public class Ong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ong_id")
    private Integer ongId;
    @Basic(optional = false)
    @Column(name = "ong_nombre")
    private String ongNombre;
    @Basic(optional = false)
    @Column(name = "ong_cancelado")
    private boolean ongCancelado;
    @JoinTable(name = "ong_estudiante", joinColumns = {
        @JoinColumn(name = "ongong_id", referencedColumnName = "ong_id")}, inverseJoinColumns = {
        @JoinColumn(name = "estudianteestudiante_id", referencedColumnName = "estudiante_id")})
    @ManyToMany
    private List<Estudiante> estudianteList;

    public Ong() {
    }

    public Ong(Integer ongId) {
        this.ongId = ongId;
    }

    public Ong(Integer ongId, String ongNombre, boolean ongCancelado) {
        this.ongId = ongId;
        this.ongNombre = ongNombre;
        this.ongCancelado = ongCancelado;
    }

    public Integer getOngId() {
        return ongId;
    }

    public void setOngId(Integer ongId) {
        this.ongId = ongId;
    }

    public String getOngNombre() {
        return ongNombre;
    }

    public void setOngNombre(String ongNombre) {
        this.ongNombre = ongNombre;
    }

    public boolean getOngCancelado() {
        return ongCancelado;
    }

    public void setOngCancelado(boolean ongCancelado) {
        this.ongCancelado = ongCancelado;
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
        hash += (ongId != null ? ongId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ong)) {
            return false;
        }
        Ong other = (Ong) object;
        if ((this.ongId == null && other.ongId != null) || (this.ongId != null && !this.ongId.equals(other.ongId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ong[ ongId=" + ongId + " ]";
    }

    public Ong(String ongNombre, boolean ongCancelado) {
        this.ongNombre = ongNombre;
        this.ongCancelado = ongCancelado;
    }
}
