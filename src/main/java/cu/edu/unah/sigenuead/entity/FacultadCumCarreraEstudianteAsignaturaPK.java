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
public class FacultadCumCarreraEstudianteAsignaturaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "codigocum")
    private String codigocum;
    @Basic(optional = false)
    @Column(name = "codigoarea")
    private String codigoarea;
    @Basic(optional = false)
    @Column(name = "idcarrera")
    private int idcarrera;
    @Basic(optional = false)
    @Column(name = "estudiante_id")
    private String estudianteId;
    @Basic(optional = false)
    @Column(name = "fecha_matricula")
    @Temporal(TemporalType.DATE)
    private Date fechaMatricula;
    @Basic(optional = false)
    @Column(name = "asignatura_id")
    private int asignaturaId;

    public FacultadCumCarreraEstudianteAsignaturaPK() {
    }

    public FacultadCumCarreraEstudianteAsignaturaPK(String codigocum, String codigoarea, int idcarrera, String estudianteId, Date fechaMatricula, int asignaturaId) {
        this.codigocum = codigocum;
        this.codigoarea = codigoarea;
        this.idcarrera = idcarrera;
        this.estudianteId = estudianteId;
        this.fechaMatricula = fechaMatricula;
        this.asignaturaId = asignaturaId;
    }

    public String getCodigocum() {
        return codigocum;
    }

    public void setCodigocum(String codigocum) {
        this.codigocum = codigocum;
    }

    public String getCodigoarea() {
        return codigoarea;
    }

    public void setCodigoarea(String codigoarea) {
        this.codigoarea = codigoarea;
    }

    public int getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(int idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(String estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public int getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(int asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigocum != null ? codigocum.hashCode() : 0);
        hash += (codigoarea != null ? codigoarea.hashCode() : 0);
        hash += (int) idcarrera;
        hash += (estudianteId != null ? estudianteId.hashCode() : 0);
        hash += (fechaMatricula != null ? fechaMatricula.hashCode() : 0);
        hash += (int) asignaturaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacultadCumCarreraEstudianteAsignaturaPK)) {
            return false;
        }
        FacultadCumCarreraEstudianteAsignaturaPK other = (FacultadCumCarreraEstudianteAsignaturaPK) object;
        if ((this.codigocum == null && other.codigocum != null) || (this.codigocum != null && !this.codigocum.equals(other.codigocum))) {
            return false;
        }
        if ((this.codigoarea == null && other.codigoarea != null) || (this.codigoarea != null && !this.codigoarea.equals(other.codigoarea))) {
            return false;
        }
        if (this.idcarrera != other.idcarrera) {
            return false;
        }
        if ((this.estudianteId == null && other.estudianteId != null) || (this.estudianteId != null && !this.estudianteId.equals(other.estudianteId))) {
            return false;
        }
        if ((this.fechaMatricula == null && other.fechaMatricula != null) || (this.fechaMatricula != null && !this.fechaMatricula.equals(other.fechaMatricula))) {
            return false;
        }
        if (this.asignaturaId != other.asignaturaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FacultadCumCarreraEstudianteAsignaturaPK[ codigocum=" + codigocum + ", codigoarea=" + codigoarea + ", idcarrera=" + idcarrera + ", estudianteId=" + estudianteId + ", fechaMatricula=" + fechaMatricula + ", asignaturaId=" + asignaturaId + " ]";
    }
    
}
