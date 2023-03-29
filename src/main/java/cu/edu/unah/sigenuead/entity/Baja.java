/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author claudy
 */
@Entity
@Table(name = "baja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Baja.findAll", query = "SELECT b FROM Baja b"),
    @NamedQuery(name = "Baja.findByTipoBajaidTipoBaja", query = "SELECT b FROM Baja b WHERE b.bajaPK.tipoBajaidTipoBaja = :tipoBajaidTipoBaja"),
    @NamedQuery(name = "Baja.findByMotivoBajaidMotivoBaja", query = "SELECT b FROM Baja b WHERE b.bajaPK.motivoBajaidMotivoBaja = :motivoBajaidMotivoBaja"),
    @NamedQuery(name = "Baja.findByFecha", query = "SELECT b FROM Baja b WHERE b.bajaPK.fecha = :fecha"),
    @NamedQuery(name = "Baja.findByCursoidcurso", query = "SELECT b FROM Baja b WHERE b.bajaPK.cursoidcurso = :cursoidcurso"),
    @NamedQuery(name = "Baja.findByCodigocum", query = "SELECT b FROM Baja b WHERE b.bajaPK.codigocum = :codigocum"),
    @NamedQuery(name = "Baja.findByCodigoarea", query = "SELECT b FROM Baja b WHERE b.bajaPK.codigoarea = :codigoarea"),
    @NamedQuery(name = "Baja.findByIdcarrera", query = "SELECT b FROM Baja b WHERE b.bajaPK.idcarrera = :idcarrera"),
    @NamedQuery(name = "Baja.findByEstudianteId", query = "SELECT b FROM Baja b WHERE b.bajaPK.estudianteId = :estudianteId"),
    @NamedQuery(name = "Baja.findByFechaMatricula", query = "SELECT b FROM Baja b WHERE b.bajaPK.fechaMatricula = :fechaMatricula"),
    @NamedQuery(name = "Baja.findByBajaCancelada", query = "SELECT b FROM Baja b WHERE b.bajaCancelada = :bajaCancelada")})
public class Baja implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BajaPK bajaPK;
    @Basic(optional = false)
    @Column(name = "baja_cancelada")
    private boolean bajaCancelada;
    @JoinColumn(name = "cursoidcurso", referencedColumnName = "idcurso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Curso curso;
    @JoinColumns({
        @JoinColumn(name = "codigocum", referencedColumnName = "facultad_cum_carrerafacultad_cumcumcodigocum", insertable = false, updatable = false),
        @JoinColumn(name = "codigoarea", referencedColumnName = "facultad_cum_carrerafacultad_cumfacultadcodigoarea", insertable = false, updatable = false),
        @JoinColumn(name = "idcarrera", referencedColumnName = "facultad_cum_carreracarreraidcarrera", insertable = false, updatable = false),
        @JoinColumn(name = "estudiante_id", referencedColumnName = "estudianteestudiante_id", insertable = false, updatable = false),
        @JoinColumn(name = "fecha_matricula", referencedColumnName = "fecha_matricula", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private FacultadCumCarreraEstudiante facultadCumCarreraEstudiante;
    @JoinColumn(name = "motivo_bajaid_motivo_baja", referencedColumnName = "id_motivo_baja", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MotivoBaja motivoBaja;
    @JoinColumn(name = "tipo_bajaid_tipo_baja", referencedColumnName = "id_tipo_baja", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoBaja tipoBaja;

    public Baja() {
    }

    public Baja(BajaPK bajaPK) {
        this.bajaPK = bajaPK;
    }

    public Baja(BajaPK bajaPK, boolean bajaCancelada) {
        this.bajaPK = bajaPK;
        this.bajaCancelada = bajaCancelada;
    }

    public Baja(int tipoBajaidTipoBaja, int motivoBajaidMotivoBaja, Date fecha, String cursoidcurso, String codigocum, String codigoarea, int idcarrera, String estudianteId, Date fechaMatricula) {
        this.bajaPK = new BajaPK(tipoBajaidTipoBaja, motivoBajaidMotivoBaja, fecha, cursoidcurso, codigocum, codigoarea, idcarrera, estudianteId, fechaMatricula);
    }

    public BajaPK getBajaPK() {
        return bajaPK;
    }

    public void setBajaPK(BajaPK bajaPK) {
        this.bajaPK = bajaPK;
    }

    public boolean getBajaCancelada() {
        return bajaCancelada;
    }

    public void setBajaCancelada(boolean bajaCancelada) {
        this.bajaCancelada = bajaCancelada;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public FacultadCumCarreraEstudiante getFacultadCumCarreraEstudiante() {
        return facultadCumCarreraEstudiante;
    }

    public void setFacultadCumCarreraEstudiante(FacultadCumCarreraEstudiante facultadCumCarreraEstudiante) {
        this.facultadCumCarreraEstudiante = facultadCumCarreraEstudiante;
    }

    public MotivoBaja getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(MotivoBaja motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public TipoBaja getTipoBaja() {
        return tipoBaja;
    }

    public void setTipoBaja(TipoBaja tipoBaja) {
        this.tipoBaja = tipoBaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bajaPK != null ? bajaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Baja)) {
            return false;
        }
        Baja other = (Baja) object;
        if ((this.bajaPK == null && other.bajaPK != null) || (this.bajaPK != null && !this.bajaPK.equals(other.bajaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Baja[ bajaPK=" + bajaPK + " ]";
    }
}
