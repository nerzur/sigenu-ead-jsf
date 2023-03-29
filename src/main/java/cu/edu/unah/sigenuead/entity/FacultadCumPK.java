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
public class FacultadCumPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "cumcodigocum")
    private String cumcodigocum;
    @Basic(optional = false)
    @Column(name = "facultadcodigoarea")
    private String facultadcodigoarea;

    public FacultadCumPK() {
    }

    public FacultadCumPK(String cumcodigocum, String facultadcodigoarea) {
        this.cumcodigocum = cumcodigocum;
        this.facultadcodigoarea = facultadcodigoarea;
    }

    public String getCumcodigocum() {
        return cumcodigocum;
    }

    public void setCumcodigocum(String cumcodigocum) {
        this.cumcodigocum = cumcodigocum;
    }

    public String getFacultadcodigoarea() {
        return facultadcodigoarea;
    }

    public void setFacultadcodigoarea(String facultadcodigoarea) {
        this.facultadcodigoarea = facultadcodigoarea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cumcodigocum != null ? cumcodigocum.hashCode() : 0);
        hash += (facultadcodigoarea != null ? facultadcodigoarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacultadCumPK)) {
            return false;
        }
        FacultadCumPK other = (FacultadCumPK) object;
        if ((this.cumcodigocum == null && other.cumcodigocum != null) || (this.cumcodigocum != null && !this.cumcodigocum.equals(other.cumcodigocum))) {
            return false;
        }
        if ((this.facultadcodigoarea == null && other.facultadcodigoarea != null) || (this.facultadcodigoarea != null && !this.facultadcodigoarea.equals(other.facultadcodigoarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FacultadCumPK[ cumcodigocum=" + cumcodigocum + ", facultadcodigoarea=" + facultadcodigoarea + " ]";
    }
    
}
