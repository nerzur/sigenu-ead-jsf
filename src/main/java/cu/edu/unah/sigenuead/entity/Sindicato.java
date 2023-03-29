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
@Table(name = "sindicato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sindicato.findAll", query = "SELECT s FROM Sindicato s"),
    @NamedQuery(name = "Sindicato.findByIdsindicato", query = "SELECT s FROM Sindicato s WHERE s.idsindicato = :idsindicato"),
    @NamedQuery(name = "Sindicato.findByNombresindicato", query = "SELECT s FROM Sindicato s WHERE s.nombresindicato = :nombresindicato"),
    @NamedQuery(name = "Sindicato.findByCanceladosindicato", query = "SELECT s FROM Sindicato s WHERE s.canceladosindicato = :canceladosindicato")})
public class Sindicato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsindicato")
    private Integer idsindicato;
    @Basic(optional = false)
    @Column(name = "nombresindicato")
    private String nombresindicato;
    @Basic(optional = false)
    @Column(name = "canceladosindicato")
    private boolean canceladosindicato;
    @OneToMany(mappedBy = "sindicatoidsindicato")
    private List<Estudiante> estudianteList;

    public Sindicato() {
    }

    public Sindicato(Integer idsindicato) {
        this.idsindicato = idsindicato;
    }

    public Sindicato(Integer idsindicato, String nombresindicato, boolean canceladosindicato) {
        this.idsindicato = idsindicato;
        this.nombresindicato = nombresindicato;
        this.canceladosindicato = canceladosindicato;
    }

    public Integer getIdsindicato() {
        return idsindicato;
    }

    public void setIdsindicato(Integer idsindicato) {
        this.idsindicato = idsindicato;
    }

    public String getNombresindicato() {
        return nombresindicato;
    }

    public void setNombresindicato(String nombresindicato) {
        this.nombresindicato = nombresindicato;
    }

    public boolean getCanceladosindicato() {
        return canceladosindicato;
    }

    public void setCanceladosindicato(boolean canceladosindicato) {
        this.canceladosindicato = canceladosindicato;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsindicato != null ? idsindicato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sindicato)) {
            return false;
        }
        Sindicato other = (Sindicato) object;
        if ((this.idsindicato == null && other.idsindicato != null) || (this.idsindicato != null && !this.idsindicato.equals(other.idsindicato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Sindicato[ idsindicato=" + idsindicato + " ]";
    }

    public Sindicato(String nombresindicato, boolean canceladosindicato) {
        this.nombresindicato = nombresindicato;
        this.canceladosindicato = canceladosindicato;
    }
}
