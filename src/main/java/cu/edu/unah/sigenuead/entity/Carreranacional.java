/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author claudy
 */
@Entity
@Table(name = "carreranacional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carreranacional.findAll", query = "SELECT c FROM Carreranacional c"),
    @NamedQuery(name = "Carreranacional.findByIdcarreranacional", query = "SELECT c FROM Carreranacional c WHERE c.idcarreranacional = :idcarreranacional"),
    @NamedQuery(name = "Carreranacional.findByCodigocarreranacional", query = "SELECT c FROM Carreranacional c WHERE c.codigocarreranacional = :codigocarreranacional"),
    @NamedQuery(name = "Carreranacional.findByNombrecarreranacional", query = "SELECT c FROM Carreranacional c WHERE c.nombrecarreranacional = :nombrecarreranacional"),
    @NamedQuery(name = "Carreranacional.findByDiplomacarreranacional", query = "SELECT c FROM Carreranacional c WHERE c.diplomacarreranacional = :diplomacarreranacional"),
    @NamedQuery(name = "Carreranacional.findByCanceladocarreranacional", query = "SELECT c FROM Carreranacional c WHERE c.canceladocarreranacional = :canceladocarreranacional")})
public class Carreranacional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcarreranacional")
    private Integer idcarreranacional;
    @Basic(optional = false)
    @Column(name = "codigocarreranacional")
    private String codigocarreranacional;
    @Basic(optional = false)
    @Column(name = "nombrecarreranacional")
    private String nombrecarreranacional;
    @Column(name = "diplomacarreranacional")
    private String diplomacarreranacional;
    @Basic(optional = false)
    @Column(name = "canceladocarreranacional")
    private boolean canceladocarreranacional;
    @JoinColumn(name = "especialidadidespecialidad", referencedColumnName = "idespecialidad")
    @ManyToOne(optional = false)
    private Especialidad especialidadidespecialidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carreranacionalidcarreranacional")
    private List<Carrera> carreraList;

    public Carreranacional() {
    }

    public Carreranacional(Integer idcarreranacional) {
        this.idcarreranacional = idcarreranacional;
    }

    public Carreranacional(Integer idcarreranacional, String codigocarreranacional, String nombrecarreranacional, boolean canceladocarreranacional) {
        this.idcarreranacional = idcarreranacional;
        this.codigocarreranacional = codigocarreranacional;
        this.nombrecarreranacional = nombrecarreranacional;
        this.canceladocarreranacional = canceladocarreranacional;
    }

    public Integer getIdcarreranacional() {
        return idcarreranacional;
    }

    public void setIdcarreranacional(Integer idcarreranacional) {
        this.idcarreranacional = idcarreranacional;
    }

    public String getCodigocarreranacional() {
        return codigocarreranacional;
    }

    public void setCodigocarreranacional(String codigocarreranacional) {
        this.codigocarreranacional = codigocarreranacional;
    }

    public String getNombrecarreranacional() {
        return nombrecarreranacional;
    }

    public void setNombrecarreranacional(String nombrecarreranacional) {
        this.nombrecarreranacional = nombrecarreranacional;
    }

    public String getDiplomacarreranacional() {
        return diplomacarreranacional;
    }

    public void setDiplomacarreranacional(String diplomacarreranacional) {
        this.diplomacarreranacional = diplomacarreranacional;
    }

    public boolean getCanceladocarreranacional() {
        return canceladocarreranacional;
    }

    public void setCanceladocarreranacional(boolean canceladocarreranacional) {
        this.canceladocarreranacional = canceladocarreranacional;
    }

    public Especialidad getEspecialidadidespecialidad() {
        return especialidadidespecialidad;
    }

    public void setEspecialidadidespecialidad(Especialidad especialidadidespecialidad) {
        this.especialidadidespecialidad = especialidadidespecialidad;
    }

    @XmlTransient
    public List<Carrera> getCarreraList() {
        return carreraList;
    }

    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcarreranacional != null ? idcarreranacional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carreranacional)) {
            return false;
        }
        Carreranacional other = (Carreranacional) object;
        if ((this.idcarreranacional == null && other.idcarreranacional != null) || (this.idcarreranacional != null && !this.idcarreranacional.equals(other.idcarreranacional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Carreranacional[ idcarreranacional=" + idcarreranacional + " ]";
    }

    public Carreranacional(String codigocarreranacional, String nombrecarreranacional, String diplomacarreranacional, boolean canceladocarreranacional, Especialidad especialidadidespecialidad) {
        this.codigocarreranacional = codigocarreranacional;
        this.nombrecarreranacional = nombrecarreranacional;
        this.diplomacarreranacional = diplomacarreranacional;
        this.canceladocarreranacional = canceladocarreranacional;
        this.especialidadidespecialidad = especialidadidespecialidad;
    }
}
