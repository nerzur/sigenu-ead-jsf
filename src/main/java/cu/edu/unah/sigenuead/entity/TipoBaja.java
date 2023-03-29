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
@Table(name = "tipo_baja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoBaja.findAll", query = "SELECT t FROM TipoBaja t"),
    @NamedQuery(name = "TipoBaja.findByIdTipoBaja", query = "SELECT t FROM TipoBaja t WHERE t.idTipoBaja = :idTipoBaja"),
    @NamedQuery(name = "TipoBaja.findByNombreTipoBaja", query = "SELECT t FROM TipoBaja t WHERE t.nombreTipoBaja = :nombreTipoBaja"),
    @NamedQuery(name = "TipoBaja.findByCancelado", query = "SELECT t FROM TipoBaja t WHERE t.cancelado = :cancelado")})
public class TipoBaja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_baja")
    private Integer idTipoBaja;
    @Basic(optional = false)
    @Column(name = "nombre_tipo_baja")
    private String nombreTipoBaja;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoBajaidTipoBaja")
    private List<MotivoBaja> motivoBajaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoBaja")
    private List<Baja> bajaList;

    public TipoBaja() {
    }

    public TipoBaja(Integer idTipoBaja) {
        this.idTipoBaja = idTipoBaja;
    }

    public TipoBaja(Integer idTipoBaja, String nombreTipoBaja, boolean cancelado) {
        this.idTipoBaja = idTipoBaja;
        this.nombreTipoBaja = nombreTipoBaja;
        this.cancelado = cancelado;
    }

    public Integer getIdTipoBaja() {
        return idTipoBaja;
    }

    public void setIdTipoBaja(Integer idTipoBaja) {
        this.idTipoBaja = idTipoBaja;
    }

    public String getNombreTipoBaja() {
        return nombreTipoBaja;
    }

    public void setNombreTipoBaja(String nombreTipoBaja) {
        this.nombreTipoBaja = nombreTipoBaja;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    @XmlTransient
    public List<MotivoBaja> getMotivoBajaList() {
        return motivoBajaList;
    }

    public void setMotivoBajaList(List<MotivoBaja> motivoBajaList) {
        this.motivoBajaList = motivoBajaList;
    }

    @XmlTransient
    public List<Baja> getBajaList() {
        return bajaList;
    }

    public void setBajaList(List<Baja> bajaList) {
        this.bajaList = bajaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoBaja != null ? idTipoBaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoBaja)) {
            return false;
        }
        TipoBaja other = (TipoBaja) object;
        if ((this.idTipoBaja == null && other.idTipoBaja != null) || (this.idTipoBaja != null && !this.idTipoBaja.equals(other.idTipoBaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TipoBaja[ idTipoBaja=" + idTipoBaja + " ]";
    }
    
}
