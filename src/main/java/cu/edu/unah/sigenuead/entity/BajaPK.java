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
public class BajaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "tipo_bajaid_tipo_baja")
    private int tipoBajaidTipoBaja;
    @Basic(optional = false)
    @Column(name = "motivo_bajaid_motivo_baja")
    private int motivoBajaidMotivoBaja;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "cursoidcurso")
    private String cursoidcurso;
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

    public BajaPK() {
    }

    public BajaPK(int tipoBajaidTipoBaja, int motivoBajaidMotivoBaja, Date fecha, String cursoidcurso, String codigocum, String codigoarea, int idcarrera, String estudianteId, Date fechaMatricula) {
        this.tipoBajaidTipoBaja = tipoBajaidTipoBaja;
        this.motivoBajaidMotivoBaja = motivoBajaidMotivoBaja;
        this.fecha = fecha;
        this.cursoidcurso = cursoidcurso;
        this.codigocum = codigocum;
        this.codigoarea = codigoarea;
        this.idcarrera = idcarrera;
        this.estudianteId = estudianteId;
        this.fechaMatricula = fechaMatricula;
    }

    public int getTipoBajaidTipoBaja() {
        return tipoBajaidTipoBaja;
    }

    public void setTipoBajaidTipoBaja(int tipoBajaidTipoBaja) {
        this.tipoBajaidTipoBaja = tipoBajaidTipoBaja;
    }

    public int getMotivoBajaidMotivoBaja() {
        return motivoBajaidMotivoBaja;
    }

    public void setMotivoBajaidMotivoBaja(int motivoBajaidMotivoBaja) {
        this.motivoBajaidMotivoBaja = motivoBajaidMotivoBaja;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCursoidcurso() {
        return cursoidcurso;
    }

    public void setCursoidcurso(String cursoidcurso) {
        this.cursoidcurso = cursoidcurso;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tipoBajaidTipoBaja;
        hash += (int) motivoBajaidMotivoBaja;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (cursoidcurso != null ? cursoidcurso.hashCode() : 0);
        hash += (codigocum != null ? codigocum.hashCode() : 0);
        hash += (codigoarea != null ? codigoarea.hashCode() : 0);
        hash += (int) idcarrera;
        hash += (estudianteId != null ? estudianteId.hashCode() : 0);
        hash += (fechaMatricula != null ? fechaMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BajaPK)) {
            return false;
        }
        BajaPK other = (BajaPK) object;
        if (this.tipoBajaidTipoBaja != other.tipoBajaidTipoBaja) {
            return false;
        }
        if (this.motivoBajaidMotivoBaja != other.motivoBajaidMotivoBaja) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if ((this.cursoidcurso == null && other.cursoidcurso != null) || (this.cursoidcurso != null && !this.cursoidcurso.equals(other.cursoidcurso))) {
            return false;
        }
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
        return true;
    }

    @Override
    public String toString() {
        return "entity.BajaPK[ tipoBajaidTipoBaja=" + tipoBajaidTipoBaja + ", motivoBajaidMotivoBaja=" + motivoBajaidMotivoBaja + ", fecha=" + fecha + ", cursoidcurso=" + cursoidcurso + ", codigocum=" + codigocum + ", codigoarea=" + codigoarea + ", idcarrera=" + idcarrera + ", estudianteId=" + estudianteId + ", fechaMatricula=" + fechaMatricula + " ]";
    }
    
}
