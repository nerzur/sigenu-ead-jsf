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
@Table(name = "facultad_cum")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacultadCum.findAll", query = "SELECT f FROM FacultadCum f"),
    @NamedQuery(name = "FacultadCum.findByCumcodigocum", query = "SELECT f FROM FacultadCum f WHERE f.facultadCumPK.cumcodigocum = :cumcodigocum"),
    @NamedQuery(name = "FacultadCum.findByFacultadcodigoarea", query = "SELECT f FROM FacultadCum f WHERE f.facultadCumPK.facultadcodigoarea = :facultadcodigoarea"),
    @NamedQuery(name = "FacultadCum.findByCancelado", query = "SELECT f FROM FacultadCum f WHERE f.cancelado = :cancelado")})
public class FacultadCum implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FacultadCumPK facultadCumPK;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    @JoinColumn(name = "cumcodigocum", referencedColumnName = "codigocum", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cum cum;
    @JoinColumn(name = "facultadcodigoarea", referencedColumnName = "codigoarea", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Facultad facultad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultadCum")
    private List<FacultadCumCarrera> facultadCumCarreraList;

    public FacultadCum() {
    }

    public FacultadCum(FacultadCumPK facultadCumPK) {
        this.facultadCumPK = facultadCumPK;
    }

    public FacultadCum(FacultadCumPK facultadCumPK, boolean cancelado) {
        this.facultadCumPK = facultadCumPK;
        this.cancelado = cancelado;
    }

    public FacultadCum(String cumcodigocum, String facultadcodigoarea) {
        this.facultadCumPK = new FacultadCumPK(cumcodigocum, facultadcodigoarea);
    }

    public FacultadCumPK getFacultadCumPK() {
        return facultadCumPK;
    }

    public void setFacultadCumPK(FacultadCumPK facultadCumPK) {
        this.facultadCumPK = facultadCumPK;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Cum getCum() {
        return cum;
    }

    public void setCum(Cum cum) {
        this.cum = cum;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    @XmlTransient
    public List<FacultadCumCarrera> getFacultadCumCarreraList() {
        return facultadCumCarreraList;
    }

    public void setFacultadCumCarreraList(List<FacultadCumCarrera> facultadCumCarreraList) {
        this.facultadCumCarreraList = facultadCumCarreraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facultadCumPK != null ? facultadCumPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacultadCum)) {
            return false;
        }
        FacultadCum other = (FacultadCum) object;
        if ((this.facultadCumPK == null && other.facultadCumPK != null) || (this.facultadCumPK != null && !this.facultadCumPK.equals(other.facultadCumPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FacultadCum[ facultadCumPK=" + facultadCumPK + " ]";
    }
    
}
