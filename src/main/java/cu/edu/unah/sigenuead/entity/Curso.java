/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author claudy
 */
@Entity
@Table(name = "curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByIdcurso", query = "SELECT c FROM Curso c WHERE c.idcurso = :idcurso"),
    @NamedQuery(name = "Curso.findByFechainiciomatriculacurso", query = "SELECT c FROM Curso c WHERE c.fechainiciomatriculacurso = :fechainiciomatriculacurso"),
    @NamedQuery(name = "Curso.findByFechafinmatricula", query = "SELECT c FROM Curso c WHERE c.fechafinmatricula = :fechafinmatricula"),
    @NamedQuery(name = "Curso.findByFechainiciorematricula", query = "SELECT c FROM Curso c WHERE c.fechainiciorematricula = :fechainiciorematricula"),
    @NamedQuery(name = "Curso.findByFechafinrematricula", query = "SELECT c FROM Curso c WHERE c.fechafinrematricula = :fechafinrematricula"),
    @NamedQuery(name = "Curso.findByFechagraduacion", query = "SELECT c FROM Curso c WHERE c.fechagraduacion = :fechagraduacion"),
    @NamedQuery(name = "Curso.findByCursoactual", query = "SELECT c FROM Curso c WHERE c.cursoactual = :cursoactual"),
    @NamedQuery(name = "Curso.findByCancelado", query = "SELECT c FROM Curso c WHERE c.cancelado = :cancelado")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcurso")
    private String idcurso;
    @Basic(optional = false)
    @Column(name = "fechainiciomatriculacurso")
    @Temporal(TemporalType.DATE)
    private Date fechainiciomatriculacurso;
    @Basic(optional = false)
    @Column(name = "fechafinmatricula")
    @Temporal(TemporalType.DATE)
    private Date fechafinmatricula;
    @Basic(optional = false)
    @Column(name = "fechainiciorematricula")
    @Temporal(TemporalType.DATE)
    private Date fechainiciorematricula;
    @Basic(optional = false)
    @Column(name = "fechafinrematricula")
    @Temporal(TemporalType.DATE)
    private Date fechafinrematricula;
    @Basic(optional = false)
    @Column(name = "fechagraduacion")
    @Temporal(TemporalType.DATE)
    private Date fechagraduacion;
    @Basic(optional = false)
    @Column(name = "cursoactual")
    private boolean cursoactual;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<Baja> bajaList;
    @JoinColumn(name = "universidadcodigouniversidad", referencedColumnName = "codigouniversidad")
    @ManyToOne(optional = false)
    private Universidad universidadcodigouniversidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoidcurso")
    private List<Matricula> matriculaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoactivacion")
    private List<Planestudio> planestudioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<CarreraCurso> carreraCursoList;

    public Curso() {
    }

    public Curso(String idcurso) {
        this.idcurso = idcurso;
    }

    public Curso(String idcurso, Date fechainiciomatriculacurso, Date fechafinmatricula, Date fechainiciorematricula, Date fechafinrematricula, Date fechagraduacion, boolean cursoactual, boolean cancelado) {
        this.idcurso = idcurso;
        this.fechainiciomatriculacurso = fechainiciomatriculacurso;
        this.fechafinmatricula = fechafinmatricula;
        this.fechainiciorematricula = fechainiciorematricula;
        this.fechafinrematricula = fechafinrematricula;
        this.fechagraduacion = fechagraduacion;
        this.cursoactual = cursoactual;
        this.cancelado = cancelado;
    }

    public String getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(String idcurso) {
        this.idcurso = idcurso;
    }

    public Date getFechainiciomatriculacurso() {
        return fechainiciomatriculacurso;
    }

    public void setFechainiciomatriculacurso(Date fechainiciomatriculacurso) {
        this.fechainiciomatriculacurso = fechainiciomatriculacurso;
    }

    public Date getFechafinmatricula() {
        return fechafinmatricula;
    }

    public void setFechafinmatricula(Date fechafinmatricula) {
        this.fechafinmatricula = fechafinmatricula;
    }

    public Date getFechainiciorematricula() {
        return fechainiciorematricula;
    }

    public void setFechainiciorematricula(Date fechainiciorematricula) {
        this.fechainiciorematricula = fechainiciorematricula;
    }

    public Date getFechafinrematricula() {
        return fechafinrematricula;
    }

    public void setFechafinrematricula(Date fechafinrematricula) {
        this.fechafinrematricula = fechafinrematricula;
    }

    public Date getFechagraduacion() {
        return fechagraduacion;
    }

    public void setFechagraduacion(Date fechagraduacion) {
        this.fechagraduacion = fechagraduacion;
    }

    public boolean getCursoactual() {
        return cursoactual;
    }

    public void setCursoactual(boolean cursoactual) {
        this.cursoactual = cursoactual;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    @XmlTransient
    public List<Baja> getBajaList() {
        return bajaList;
    }

    public void setBajaList(List<Baja> bajaList) {
        this.bajaList = bajaList;
    }

    public Universidad getUniversidadcodigouniversidad() {
        return universidadcodigouniversidad;
    }

    public void setUniversidadcodigouniversidad(Universidad universidadcodigouniversidad) {
        this.universidadcodigouniversidad = universidadcodigouniversidad;
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    @XmlTransient
    public List<Planestudio> getPlanestudioList() {
        return planestudioList;
    }

    public void setPlanestudioList(List<Planestudio> planestudioList) {
        this.planestudioList = planestudioList;
    }

    @XmlTransient
    public List<CarreraCurso> getCarreraCursoList() {
        return carreraCursoList;
    }

    public void setCarreraCursoList(List<CarreraCurso> carreraCursoList) {
        this.carreraCursoList = carreraCursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcurso != null ? idcurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idcurso == null && other.idcurso != null) || (this.idcurso != null && !this.idcurso.equals(other.idcurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Curso[ idcurso=" + idcurso + " ]";
    }

    public Curso(String idcurso, Date fechainiciomatriculacurso, Date fechafinmatricula, Date fechainiciorematricula, Date fechafinrematricula, Date fechagraduacion, boolean cursoactual, Universidad universidadcodigouniversidad) {
        this.idcurso = idcurso;
        this.fechainiciomatriculacurso = fechainiciomatriculacurso;
        this.fechafinmatricula = fechafinmatricula;
        this.fechainiciorematricula = fechainiciorematricula;
        this.fechafinrematricula = fechafinrematricula;
        this.fechagraduacion = fechagraduacion;
        this.cursoactual = cursoactual;
        this.universidadcodigouniversidad = universidadcodigouniversidad;
    }
}
