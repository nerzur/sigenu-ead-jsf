/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author claudy
 */
@Embeddable
public class FacultadCumCarreraEstudiantePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "facultad_cum_carrerafacultad_cumcumcodigocum")
    private String facultadCumCarrerafacultadCumcumcodigocum;
    @Basic(optional = false)
    @Column(name = "facultad_cum_carrerafacultad_cumfacultadcodigoarea")
    private String facultadCumCarrerafacultadCumfacultadcodigoarea;
    @Basic(optional = false)
    @Column(name = "facultad_cum_carreracarreraidcarrera")
    private int facultadCumCarreracarreraidcarrera;
    @Basic(optional = false)
    @Column(name = "estudianteestudiante_id")
    private String estudianteestudianteId;
    @Basic(optional = false)
    @Column(name = "fecha_matricula")
    @Temporal(TemporalType.DATE)
    private Date fechaMatricula;

    public FacultadCumCarreraEstudiantePK() {
    }

    public FacultadCumCarreraEstudiantePK(String facultadCumCarrerafacultadCumcumcodigocum, String facultadCumCarrerafacultadCumfacultadcodigoarea, int facultadCumCarreracarreraidcarrera, String estudianteestudianteId, Date fechaMatricula) {
        this.facultadCumCarrerafacultadCumcumcodigocum = facultadCumCarrerafacultadCumcumcodigocum;
        this.facultadCumCarrerafacultadCumfacultadcodigoarea = facultadCumCarrerafacultadCumfacultadcodigoarea;
        this.facultadCumCarreracarreraidcarrera = facultadCumCarreracarreraidcarrera;
        this.estudianteestudianteId = estudianteestudianteId;
        this.fechaMatricula = fechaMatricula;
    }

    public String getFacultadCumCarrerafacultadCumcumcodigocum() {
        return facultadCumCarrerafacultadCumcumcodigocum;
    }

    public void setFacultadCumCarrerafacultadCumcumcodigocum(String facultadCumCarrerafacultadCumcumcodigocum) {
        this.facultadCumCarrerafacultadCumcumcodigocum = facultadCumCarrerafacultadCumcumcodigocum;
    }

    public String getFacultadCumCarrerafacultadCumfacultadcodigoarea() {
        return facultadCumCarrerafacultadCumfacultadcodigoarea;
    }

    public void setFacultadCumCarrerafacultadCumfacultadcodigoarea(String facultadCumCarrerafacultadCumfacultadcodigoarea) {
        this.facultadCumCarrerafacultadCumfacultadcodigoarea = facultadCumCarrerafacultadCumfacultadcodigoarea;
    }

    public int getFacultadCumCarreracarreraidcarrera() {
        return facultadCumCarreracarreraidcarrera;
    }

    public void setFacultadCumCarreracarreraidcarrera(int facultadCumCarreracarreraidcarrera) {
        this.facultadCumCarreracarreraidcarrera = facultadCumCarreracarreraidcarrera;
    }

    public String getEstudianteestudianteId() {
        return estudianteestudianteId;
    }

    public void setEstudianteestudianteId(String estudianteestudianteId) {
        this.estudianteestudianteId = estudianteestudianteId;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facultadCumCarrerafacultadCumcumcodigocum != null ? facultadCumCarrerafacultadCumcumcodigocum.hashCode() : 0);
        hash += (facultadCumCarrerafacultadCumfacultadcodigoarea != null ? facultadCumCarrerafacultadCumfacultadcodigoarea.hashCode() : 0);
        hash += (int) facultadCumCarreracarreraidcarrera;
        hash += (estudianteestudianteId != null ? estudianteestudianteId.hashCode() : 0);
        hash += (fechaMatricula != null ? fechaMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacultadCumCarreraEstudiantePK)) {
            return false;
        }
        FacultadCumCarreraEstudiantePK other = (FacultadCumCarreraEstudiantePK) object;
        if ((this.facultadCumCarrerafacultadCumcumcodigocum == null && other.facultadCumCarrerafacultadCumcumcodigocum != null) || (this.facultadCumCarrerafacultadCumcumcodigocum != null && !this.facultadCumCarrerafacultadCumcumcodigocum.equals(other.facultadCumCarrerafacultadCumcumcodigocum))) {
            return false;
        }
        if ((this.facultadCumCarrerafacultadCumfacultadcodigoarea == null && other.facultadCumCarrerafacultadCumfacultadcodigoarea != null) || (this.facultadCumCarrerafacultadCumfacultadcodigoarea != null && !this.facultadCumCarrerafacultadCumfacultadcodigoarea.equals(other.facultadCumCarrerafacultadCumfacultadcodigoarea))) {
            return false;
        }
        if (this.facultadCumCarreracarreraidcarrera != other.facultadCumCarreracarreraidcarrera) {
            return false;
        }
        if ((this.estudianteestudianteId == null && other.estudianteestudianteId != null) || (this.estudianteestudianteId != null && !this.estudianteestudianteId.equals(other.estudianteestudianteId))) {
            return false;
        }
        if ((this.fechaMatricula == null && other.fechaMatricula != null) || (this.fechaMatricula != null && !this.fechaMatricula.equals(other.fechaMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FacultadCumCarreraEstudiantePK[ facultadCumCarrerafacultadCumcumcodigocum=" + facultadCumCarrerafacultadCumcumcodigocum + ", facultadCumCarrerafacultadCumfacultadcodigoarea=" + facultadCumCarrerafacultadCumfacultadcodigoarea + ", facultadCumCarreracarreraidcarrera=" + facultadCumCarreracarreraidcarrera + ", estudianteestudianteId=" + estudianteestudianteId + ", fechaMatricula=" + fechaMatricula + " ]";
    }
    
}
