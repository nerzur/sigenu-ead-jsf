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
@Table(name = "motivo_baja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotivoBaja.findAll", query = "SELECT m FROM MotivoBaja m"),
    @NamedQuery(name = "MotivoBaja.findByIdMotivoBaja", query = "SELECT m FROM MotivoBaja m WHERE m.idMotivoBaja = :idMotivoBaja"),
    @NamedQuery(name = "MotivoBaja.findByNombreMotivoBaja", query = "SELECT m FROM MotivoBaja m WHERE m.nombreMotivoBaja = :nombreMotivoBaja"),
    @NamedQuery(name = "MotivoBaja.findByCancelado", query = "SELECT m FROM MotivoBaja m WHERE m.cancelado = :cancelado")})
public class MotivoBaja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_motivo_baja")
    private Integer idMotivoBaja;
    @Basic(optional = false)
    @Column(name = "nombre_motivo_baja")
    private String nombreMotivoBaja;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    @JoinColumn(name = "tipo_bajaid_tipo_baja", referencedColumnName = "id_tipo_baja")
    @ManyToOne(optional = false)
    private TipoBaja tipoBajaidTipoBaja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "motivoBaja")
    private List<Baja> bajaList;

    public MotivoBaja() {
    }

    public MotivoBaja(Integer idMotivoBaja) {
        this.idMotivoBaja = idMotivoBaja;
    }

    public MotivoBaja(Integer idMotivoBaja, String nombreMotivoBaja, boolean cancelado) {
        this.idMotivoBaja = idMotivoBaja;
        this.nombreMotivoBaja = nombreMotivoBaja;
        this.cancelado = cancelado;
    }

    public Integer getIdMotivoBaja() {
        return idMotivoBaja;
    }

    public void setIdMotivoBaja(Integer idMotivoBaja) {
        this.idMotivoBaja = idMotivoBaja;
    }

    public String getNombreMotivoBaja() {
        return nombreMotivoBaja;
    }

    public void setNombreMotivoBaja(String nombreMotivoBaja) {
        this.nombreMotivoBaja = nombreMotivoBaja;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public TipoBaja getTipoBajaidTipoBaja() {
        return tipoBajaidTipoBaja;
    }

    public void setTipoBajaidTipoBaja(TipoBaja tipoBajaidTipoBaja) {
        this.tipoBajaidTipoBaja = tipoBajaidTipoBaja;
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
        hash += (idMotivoBaja != null ? idMotivoBaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotivoBaja)) {
            return false;
        }
        MotivoBaja other = (MotivoBaja) object;
        if ((this.idMotivoBaja == null && other.idMotivoBaja != null) || (this.idMotivoBaja != null && !this.idMotivoBaja.equals(other.idMotivoBaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MotivoBaja[ idMotivoBaja=" + idMotivoBaja + " ]";
    }
    
}
