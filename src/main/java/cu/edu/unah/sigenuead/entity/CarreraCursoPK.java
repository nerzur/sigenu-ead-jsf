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
public class CarreraCursoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "carreraidcarrera")
    private int carreraidcarrera;
    @Basic(optional = false)
    @Column(name = "cursoidcurso")
    private String cursoidcurso;

    public CarreraCursoPK() {
    }

    public CarreraCursoPK(int carreraidcarrera, String cursoidcurso) {
        this.carreraidcarrera = carreraidcarrera;
        this.cursoidcurso = cursoidcurso;
    }

    public int getCarreraidcarrera() {
        return carreraidcarrera;
    }

    public void setCarreraidcarrera(int carreraidcarrera) {
        this.carreraidcarrera = carreraidcarrera;
    }

    public String getCursoidcurso() {
        return cursoidcurso;
    }

    public void setCursoidcurso(String cursoidcurso) {
        this.cursoidcurso = cursoidcurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) carreraidcarrera;
        hash += (cursoidcurso != null ? cursoidcurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarreraCursoPK)) {
            return false;
        }
        CarreraCursoPK other = (CarreraCursoPK) object;
        if (this.carreraidcarrera != other.carreraidcarrera) {
            return false;
        }
        if ((this.cursoidcurso == null && other.cursoidcurso != null) || (this.cursoidcurso != null && !this.cursoidcurso.equals(other.cursoidcurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CarreraCursoPK[ carreraidcarrera=" + carreraidcarrera + ", cursoidcurso=" + cursoidcurso + " ]";
    }
    
}
