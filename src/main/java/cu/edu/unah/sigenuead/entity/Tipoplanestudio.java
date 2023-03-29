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
@Table(name = "tipoplanestudio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoplanestudio.findAll", query = "SELECT t FROM Tipoplanestudio t"),
    @NamedQuery(name = "Tipoplanestudio.findByNombretipoplanestudio", query = "SELECT t FROM Tipoplanestudio t WHERE t.nombretipoplanestudio = :nombretipoplanestudio"),
    @NamedQuery(name = "Tipoplanestudio.findByTipoplanestudiocancelado", query = "SELECT t FROM Tipoplanestudio t WHERE t.tipoplanestudiocancelado = :tipoplanestudiocancelado"),
    @NamedQuery(name = "Tipoplanestudio.findByCancelado", query = "SELECT t FROM Tipoplanestudio t WHERE t.cancelado = :cancelado")})
public class Tipoplanestudio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombretipoplanestudio")
    private String nombretipoplanestudio;
    @Basic(optional = false)
    @Column(name = "tipoplanestudiocancelado")
    private boolean tipoplanestudiocancelado;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoplanestudionombretipoplanestudio")
    private List<Planestudio> planestudioList;

    public Tipoplanestudio() {
    }

    public Tipoplanestudio(String nombretipoplanestudio) {
        this.nombretipoplanestudio = nombretipoplanestudio;
    }

    public Tipoplanestudio(String nombretipoplanestudio, boolean tipoplanestudiocancelado, boolean cancelado) {
        this.nombretipoplanestudio = nombretipoplanestudio;
        this.tipoplanestudiocancelado = tipoplanestudiocancelado;
        this.cancelado = cancelado;
    }

    public String getNombretipoplanestudio() {
        return nombretipoplanestudio;
    }

    public void setNombretipoplanestudio(String nombretipoplanestudio) {
        this.nombretipoplanestudio = nombretipoplanestudio;
    }

    public boolean getTipoplanestudiocancelado() {
        return tipoplanestudiocancelado;
    }

    public void setTipoplanestudiocancelado(boolean tipoplanestudiocancelado) {
        this.tipoplanestudiocancelado = tipoplanestudiocancelado;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    @XmlTransient
    public List<Planestudio> getPlanestudioList() {
        return planestudioList;
    }

    public void setPlanestudioList(List<Planestudio> planestudioList) {
        this.planestudioList = planestudioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombretipoplanestudio != null ? nombretipoplanestudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoplanestudio)) {
            return false;
        }
        Tipoplanestudio other = (Tipoplanestudio) object;
        if ((this.nombretipoplanestudio == null && other.nombretipoplanestudio != null) || (this.nombretipoplanestudio != null && !this.nombretipoplanestudio.equals(other.nombretipoplanestudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tipoplanestudio[ nombretipoplanestudio=" + nombretipoplanestudio + " ]";
    }
    
}
