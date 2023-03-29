/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author claudy
 */
@Entity
@Table(name = "asignatura_asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignaturaAsignatura.findAll", query = "SELECT a FROM AsignaturaAsignatura a"),
    @NamedQuery(name = "AsignaturaAsignatura.findByAsignaturaPrecedente", query = "SELECT a FROM AsignaturaAsignatura a WHERE a.asignaturaAsignaturaPK.asignaturaPrecedente = :asignaturaPrecedente"),
    @NamedQuery(name = "AsignaturaAsignatura.findByAsignaturaDependiente", query = "SELECT a FROM AsignaturaAsignatura a WHERE a.asignaturaAsignaturaPK.asignaturaDependiente = :asignaturaDependiente"),
    @NamedQuery(name = "AsignaturaAsignatura.findByCancelado", query = "SELECT a FROM AsignaturaAsignatura a WHERE a.cancelado = :cancelado")})
public class AsignaturaAsignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsignaturaAsignaturaPK asignaturaAsignaturaPK;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    @JoinColumn(name = "asignatura_precedente", referencedColumnName = "asignatura_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura;
    @JoinColumn(name = "asignatura_dependiente", referencedColumnName = "asignatura_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura1;

    public AsignaturaAsignatura() {
    }

    public AsignaturaAsignatura(AsignaturaAsignaturaPK asignaturaAsignaturaPK) {
        this.asignaturaAsignaturaPK = asignaturaAsignaturaPK;
    }

    public AsignaturaAsignatura(AsignaturaAsignaturaPK asignaturaAsignaturaPK, boolean cancelado) {
        this.asignaturaAsignaturaPK = asignaturaAsignaturaPK;
        this.cancelado = cancelado;
    }

    public AsignaturaAsignatura(int asignaturaPrecedente, int asignaturaDependiente) {
        this.asignaturaAsignaturaPK = new AsignaturaAsignaturaPK(asignaturaPrecedente, asignaturaDependiente);
    }

    public AsignaturaAsignaturaPK getAsignaturaAsignaturaPK() {
        return asignaturaAsignaturaPK;
    }

    public void setAsignaturaAsignaturaPK(AsignaturaAsignaturaPK asignaturaAsignaturaPK) {
        this.asignaturaAsignaturaPK = asignaturaAsignaturaPK;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Asignatura getAsignatura1() {
        return asignatura1;
    }

    public void setAsignatura1(Asignatura asignatura1) {
        this.asignatura1 = asignatura1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asignaturaAsignaturaPK != null ? asignaturaAsignaturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignaturaAsignatura)) {
            return false;
        }
        AsignaturaAsignatura other = (AsignaturaAsignatura) object;
        if ((this.asignaturaAsignaturaPK == null && other.asignaturaAsignaturaPK != null) || (this.asignaturaAsignaturaPK != null && !this.asignaturaAsignaturaPK.equals(other.asignaturaAsignaturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AsignaturaAsignatura[ asignaturaAsignaturaPK=" + asignaturaAsignaturaPK + " ]";
    }
    
}
