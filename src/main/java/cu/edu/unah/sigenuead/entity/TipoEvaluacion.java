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
@Table(name = "tipo_evaluacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEvaluacion.findAll", query = "SELECT t FROM TipoEvaluacion t"),
    @NamedQuery(name = "TipoEvaluacion.findByTipoEvaluacionId", query = "SELECT t FROM TipoEvaluacion t WHERE t.tipoEvaluacionId = :tipoEvaluacionId"),
    @NamedQuery(name = "TipoEvaluacion.findByTipoEvaluacionNombre", query = "SELECT t FROM TipoEvaluacion t WHERE t.tipoEvaluacionNombre = :tipoEvaluacionNombre"),
    @NamedQuery(name = "TipoEvaluacion.findByTipoEavluacionCancelado", query = "SELECT t FROM TipoEvaluacion t WHERE t.tipoEavluacionCancelado = :tipoEavluacionCancelado")})
public class TipoEvaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_evaluacion_id")
    private Integer tipoEvaluacionId;
    @Basic(optional = false)
    @Column(name = "tipo_evaluacion_nombre")
    private String tipoEvaluacionNombre;
    @Basic(optional = false)
    @Column(name = "tipo_eavluacion_cancelado")
    private boolean tipoEavluacionCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoEvaluaciontipoEvaluacionId")
    private List<Asignatura> asignaturaList;

    public TipoEvaluacion() {
    }

    public TipoEvaluacion(Integer tipoEvaluacionId) {
        this.tipoEvaluacionId = tipoEvaluacionId;
    }

    public TipoEvaluacion(Integer tipoEvaluacionId, String tipoEvaluacionNombre, boolean tipoEavluacionCancelado) {
        this.tipoEvaluacionId = tipoEvaluacionId;
        this.tipoEvaluacionNombre = tipoEvaluacionNombre;
        this.tipoEavluacionCancelado = tipoEavluacionCancelado;
    }

    public Integer getTipoEvaluacionId() {
        return tipoEvaluacionId;
    }

    public void setTipoEvaluacionId(Integer tipoEvaluacionId) {
        this.tipoEvaluacionId = tipoEvaluacionId;
    }

    public String getTipoEvaluacionNombre() {
        return tipoEvaluacionNombre;
    }

    public void setTipoEvaluacionNombre(String tipoEvaluacionNombre) {
        this.tipoEvaluacionNombre = tipoEvaluacionNombre;
    }

    public boolean getTipoEavluacionCancelado() {
        return tipoEavluacionCancelado;
    }

    public void setTipoEavluacionCancelado(boolean tipoEavluacionCancelado) {
        this.tipoEavluacionCancelado = tipoEavluacionCancelado;
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
        hash += (tipoEvaluacionId != null ? tipoEvaluacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEvaluacion)) {
            return false;
        }
        TipoEvaluacion other = (TipoEvaluacion) object;
        if ((this.tipoEvaluacionId == null && other.tipoEvaluacionId != null) || (this.tipoEvaluacionId != null && !this.tipoEvaluacionId.equals(other.tipoEvaluacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TipoEvaluacion[ tipoEvaluacionId=" + tipoEvaluacionId + " ]";
    }

    public TipoEvaluacion(String tipoEvaluacionNombre, boolean tipoEavluacionCancelado) {
        this.tipoEvaluacionNombre = tipoEvaluacionNombre;
        this.tipoEavluacionCancelado = tipoEavluacionCancelado;
    }
}
