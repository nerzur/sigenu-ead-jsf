/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 *
 * @author claudy
 */
@Embeddable
public class AsignaturaAsignaturaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "asignatura_precedente")
    private int asignaturaPrecedente;
    @Basic(optional = false)
    @Column(name = "asignatura_dependiente")
    private int asignaturaDependiente;

    public AsignaturaAsignaturaPK() {
    }

    public AsignaturaAsignaturaPK(int asignaturaPrecedente, int asignaturaDependiente) {
        this.asignaturaPrecedente = asignaturaPrecedente;
        this.asignaturaDependiente = asignaturaDependiente;
    }

    public int getAsignaturaPrecedente() {
        return asignaturaPrecedente;
    }

    public void setAsignaturaPrecedente(int asignaturaPrecedente) {
        this.asignaturaPrecedente = asignaturaPrecedente;
    }

    public int getAsignaturaDependiente() {
        return asignaturaDependiente;
    }

    public void setAsignaturaDependiente(int asignaturaDependiente) {
        this.asignaturaDependiente = asignaturaDependiente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) asignaturaPrecedente;
        hash += (int) asignaturaDependiente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignaturaAsignaturaPK)) {
            return false;
        }
        AsignaturaAsignaturaPK other = (AsignaturaAsignaturaPK) object;
        if (this.asignaturaPrecedente != other.asignaturaPrecedente) {
            return false;
        }
        if (this.asignaturaDependiente != other.asignaturaDependiente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AsignaturaAsignaturaPK[ asignaturaPrecedente=" + asignaturaPrecedente + ", asignaturaDependiente=" + asignaturaDependiente + " ]";
    }
    
}
