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
@Table(name = "carrera_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarreraCurso.findAll", query = "SELECT c FROM CarreraCurso c"),
    @NamedQuery(name = "CarreraCurso.findByCarreraidcarrera", query = "SELECT c FROM CarreraCurso c WHERE c.carreraCursoPK.carreraidcarrera = :carreraidcarrera"),
    @NamedQuery(name = "CarreraCurso.findByCursoidcurso", query = "SELECT c FROM CarreraCurso c WHERE c.carreraCursoPK.cursoidcurso = :cursoidcurso"),
    @NamedQuery(name = "CarreraCurso.findByCantmatriculas", query = "SELECT c FROM CarreraCurso c WHERE c.cantmatriculas = :cantmatriculas"),
    @NamedQuery(name = "CarreraCurso.findByCancelado", query = "SELECT c FROM CarreraCurso c WHERE c.cancelado = :cancelado")})
public class CarreraCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CarreraCursoPK carreraCursoPK;
    @Basic(optional = false)
    @Column(name = "cantmatriculas")
    private int cantmatriculas;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    @JoinColumn(name = "carreraidcarrera", referencedColumnName = "idcarrera", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Carrera carrera;
    @JoinColumn(name = "cursoidcurso", referencedColumnName = "idcurso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Curso curso;

    public CarreraCurso() {
    }

    public CarreraCurso(CarreraCursoPK carreraCursoPK) {
        this.carreraCursoPK = carreraCursoPK;
    }

    public CarreraCurso(CarreraCursoPK carreraCursoPK, int cantmatriculas, boolean cancelado) {
        this.carreraCursoPK = carreraCursoPK;
        this.cantmatriculas = cantmatriculas;
        this.cancelado = cancelado;
    }

    public CarreraCurso(int carreraidcarrera, String cursoidcurso) {
        this.carreraCursoPK = new CarreraCursoPK(carreraidcarrera, cursoidcurso);
    }

    public CarreraCursoPK getCarreraCursoPK() {
        return carreraCursoPK;
    }

    public void setCarreraCursoPK(CarreraCursoPK carreraCursoPK) {
        this.carreraCursoPK = carreraCursoPK;
    }

    public int getCantmatriculas() {
        return cantmatriculas;
    }

    public void setCantmatriculas(int cantmatriculas) {
        this.cantmatriculas = cantmatriculas;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carreraCursoPK != null ? carreraCursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarreraCurso)) {
            return false;
        }
        CarreraCurso other = (CarreraCurso) object;
        if ((this.carreraCursoPK == null && other.carreraCursoPK != null) || (this.carreraCursoPK != null && !this.carreraCursoPK.equals(other.carreraCursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CarreraCurso[ carreraCursoPK=" + carreraCursoPK + " ]";
    }
    
}
