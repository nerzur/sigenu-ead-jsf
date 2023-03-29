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
@Table(name = "pais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p"),
    @NamedQuery(name = "Pais.findByIdpais", query = "SELECT p FROM Pais p WHERE p.idpais = :idpais"),
    @NamedQuery(name = "Pais.findByNombrepais", query = "SELECT p FROM Pais p WHERE p.nombrepais = :nombrepais"),
    @NamedQuery(name = "Pais.findByCanceladopais", query = "SELECT p FROM Pais p WHERE p.canceladopais = :canceladopais")})
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpais")
    private Integer idpais;
    @Basic(optional = false)
    @Column(name = "nombrepais")
    private String nombrepais;
    @Basic(optional = false)
    @Column(name = "canceladopais")
    private boolean canceladopais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paisidpais")
    private List<Estudiante> estudianteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paisidpais")
    private List<Provincia> provinciaList;

    public Pais() {
    }

    public Pais(Integer idpais) {
        this.idpais = idpais;
    }

    public Pais(Integer idpais, String nombrepais, boolean canceladopais) {
        this.idpais = idpais;
        this.nombrepais = nombrepais;
        this.canceladopais = canceladopais;
    }

    public Integer getIdpais() {
        return idpais;
    }

    public void setIdpais(Integer idpais) {
        this.idpais = idpais;
    }

    public String getNombrepais() {
        return nombrepais;
    }

    public void setNombrepais(String nombrepais) {
        this.nombrepais = nombrepais;
    }

    public boolean getCanceladopais() {
        return canceladopais;
    }

    public void setCanceladopais(boolean canceladopais) {
        this.canceladopais = canceladopais;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @XmlTransient
    public List<Provincia> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(List<Provincia> provinciaList) {
        this.provinciaList = provinciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpais != null ? idpais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.idpais == null && other.idpais != null) || (this.idpais != null && !this.idpais.equals(other.idpais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pais[ idpais=" + idpais + " ]";
    }

    public Pais(String nombrepais, boolean canceladopais) {
        this.nombrepais = nombrepais;
        this.canceladopais = canceladopais;
    }
}
