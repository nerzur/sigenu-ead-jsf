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
@Table(name = "tipo_asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAsignatura.findAll", query = "SELECT t FROM TipoAsignatura t"),
    @NamedQuery(name = "TipoAsignatura.findByTipoAsignaturaId", query = "SELECT t FROM TipoAsignatura t WHERE t.tipoAsignaturaId = :tipoAsignaturaId"),
    @NamedQuery(name = "TipoAsignatura.findByTipoAsignaturaNombre", query = "SELECT t FROM TipoAsignatura t WHERE t.tipoAsignaturaNombre = :tipoAsignaturaNombre"),
    @NamedQuery(name = "TipoAsignatura.findByTipoAsignaturaCancelado", query = "SELECT t FROM TipoAsignatura t WHERE t.tipoAsignaturaCancelado = :tipoAsignaturaCancelado")})
public class TipoAsignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_asignatura_id")
    private Integer tipoAsignaturaId;
    @Basic(optional = false)
    @Column(name = "tipo_asignatura_nombre")
    private String tipoAsignaturaNombre;
    @Basic(optional = false)
    @Column(name = "tipo_asignatura_cancelado")
    private boolean tipoAsignaturaCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAsignaturatipoAsignaturaId")
    private List<Asignatura> asignaturaList;

    public TipoAsignatura() {
    }

    public TipoAsignatura(Integer tipoAsignaturaId) {
        this.tipoAsignaturaId = tipoAsignaturaId;
    }

    public TipoAsignatura(Integer tipoAsignaturaId, String tipoAsignaturaNombre, boolean tipoAsignaturaCancelado) {
        this.tipoAsignaturaId = tipoAsignaturaId;
        this.tipoAsignaturaNombre = tipoAsignaturaNombre;
        this.tipoAsignaturaCancelado = tipoAsignaturaCancelado;
    }

    public Integer getTipoAsignaturaId() {
        return tipoAsignaturaId;
    }

    public void setTipoAsignaturaId(Integer tipoAsignaturaId) {
        this.tipoAsignaturaId = tipoAsignaturaId;
    }

    public String getTipoAsignaturaNombre() {
        return tipoAsignaturaNombre;
    }

    public void setTipoAsignaturaNombre(String tipoAsignaturaNombre) {
        this.tipoAsignaturaNombre = tipoAsignaturaNombre;
    }

    public boolean getTipoAsignaturaCancelado() {
        return tipoAsignaturaCancelado;
    }

    public void setTipoAsignaturaCancelado(boolean tipoAsignaturaCancelado) {
        this.tipoAsignaturaCancelado = tipoAsignaturaCancelado;
    }

    @XmlTransient
    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoAsignaturaId != null ? tipoAsignaturaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAsignatura)) {
            return false;
        }
        TipoAsignatura other = (TipoAsignatura) object;
        if ((this.tipoAsignaturaId == null && other.tipoAsignaturaId != null) || (this.tipoAsignaturaId != null && !this.tipoAsignaturaId.equals(other.tipoAsignaturaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TipoAsignatura[ tipoAsignaturaId=" + tipoAsignaturaId + " ]";
    }

    public TipoAsignatura(String tipoAsignaturaNombre, boolean tipoAsignaturaCancelado) {
        this.tipoAsignaturaNombre = tipoAsignaturaNombre;
        this.tipoAsignaturaCancelado = tipoAsignaturaCancelado;
    }
}
