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
@Table(name = "tipo_militar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMilitar.findAll", query = "SELECT t FROM TipoMilitar t"),
    @NamedQuery(name = "TipoMilitar.findByIdTipoMilitar", query = "SELECT t FROM TipoMilitar t WHERE t.idTipoMilitar = :idTipoMilitar"),
    @NamedQuery(name = "TipoMilitar.findByNombreTipo", query = "SELECT t FROM TipoMilitar t WHERE t.nombreTipo = :nombreTipo"),
    @NamedQuery(name = "TipoMilitar.findByCanceladoTipoMilitar", query = "SELECT t FROM TipoMilitar t WHERE t.canceladoTipoMilitar = :canceladoTipoMilitar")})
public class TipoMilitar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_militar")
    private Integer idTipoMilitar;
    @Basic(optional = false)
    @Column(name = "nombre_tipo")
    private String nombreTipo;
    @Basic(optional = false)
    @Column(name = "cancelado_tipo_militar")
    private boolean canceladoTipoMilitar;
    @OneToMany(mappedBy = "tipoMilitaridTipoMilitar")
    private List<Estudiante> estudianteList;

    public TipoMilitar() {
    }

    public TipoMilitar(Integer idTipoMilitar) {
        this.idTipoMilitar = idTipoMilitar;
    }

    public TipoMilitar(Integer idTipoMilitar, String nombreTipo, boolean canceladoTipoMilitar) {
        this.idTipoMilitar = idTipoMilitar;
        this.nombreTipo = nombreTipo;
        this.canceladoTipoMilitar = canceladoTipoMilitar;
    }

    public Integer getIdTipoMilitar() {
        return idTipoMilitar;
    }

    public void setIdTipoMilitar(Integer idTipoMilitar) {
        this.idTipoMilitar = idTipoMilitar;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public boolean getCanceladoTipoMilitar() {
        return canceladoTipoMilitar;
    }

    public void setCanceladoTipoMilitar(boolean canceladoTipoMilitar) {
        this.canceladoTipoMilitar = canceladoTipoMilitar;
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
        hash += (idTipoMilitar != null ? idTipoMilitar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMilitar)) {
            return false;
        }
        TipoMilitar other = (TipoMilitar) object;
        if ((this.idTipoMilitar == null && other.idTipoMilitar != null) || (this.idTipoMilitar != null && !this.idTipoMilitar.equals(other.idTipoMilitar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TipoMilitar[ idTipoMilitar=" + idTipoMilitar + " ]";
    }

    public TipoMilitar(String nombreTipo, boolean canceladoTipoMilitar) {
        this.nombreTipo = nombreTipo;
        this.canceladoTipoMilitar = canceladoTipoMilitar;
    }
}
