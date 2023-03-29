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
public class FacultadCumCarreraPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "facultad_cumcumcodigocum")
    private String facultadCumcumcodigocum;
    @Basic(optional = false)
    @Column(name = "facultad_cumfacultadcodigoarea")
    private String facultadCumfacultadcodigoarea;
    @Basic(optional = false)
    @Column(name = "carreraidcarrera")
    private int carreraidcarrera;

    public FacultadCumCarreraPK() {
    }

    public FacultadCumCarreraPK(String facultadCumcumcodigocum, String facultadCumfacultadcodigoarea, int carreraidcarrera) {
        this.facultadCumcumcodigocum = facultadCumcumcodigocum;
        this.facultadCumfacultadcodigoarea = facultadCumfacultadcodigoarea;
        this.carreraidcarrera = carreraidcarrera;
    }

    public String getFacultadCumcumcodigocum() {
        return facultadCumcumcodigocum;
    }

    public void setFacultadCumcumcodigocum(String facultadCumcumcodigocum) {
        this.facultadCumcumcodigocum = facultadCumcumcodigocum;
    }

    public String getFacultadCumfacultadcodigoarea() {
        return facultadCumfacultadcodigoarea;
    }

    public void setFacultadCumfacultadcodigoarea(String facultadCumfacultadcodigoarea) {
        this.facultadCumfacultadcodigoarea = facultadCumfacultadcodigoarea;
    }

    public int getCarreraidcarrera() {
        return carreraidcarrera;
    }

    public void setCarreraidcarrera(int carreraidcarrera) {
        this.carreraidcarrera = carreraidcarrera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facultadCumcumcodigocum != null ? facultadCumcumcodigocum.hashCode() : 0);
        hash += (facultadCumfacultadcodigoarea != null ? facultadCumfacultadcodigoarea.hashCode() : 0);
        hash += (int) carreraidcarrera;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacultadCumCarreraPK)) {
            return false;
        }
        FacultadCumCarreraPK other = (FacultadCumCarreraPK) object;
        if ((this.facultadCumcumcodigocum == null && other.facultadCumcumcodigocum != null) || (this.facultadCumcumcodigocum != null && !this.facultadCumcumcodigocum.equals(other.facultadCumcumcodigocum))) {
            return false;
        }
        if ((this.facultadCumfacultadcodigoarea == null && other.facultadCumfacultadcodigoarea != null) || (this.facultadCumfacultadcodigoarea != null && !this.facultadCumfacultadcodigoarea.equals(other.facultadCumfacultadcodigoarea))) {
            return false;
        }
        if (this.carreraidcarrera != other.carreraidcarrera) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FacultadCumCarreraPK[ facultadCumcumcodigocum=" + facultadCumcumcodigocum + ", facultadCumfacultadcodigoarea=" + facultadCumfacultadcodigoarea + ", carreraidcarrera=" + carreraidcarrera + " ]";
    }
    
}
