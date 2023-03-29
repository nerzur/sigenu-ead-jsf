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
@Table(name = "provincia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p"),
    @NamedQuery(name = "Provincia.findByIdprovincia", query = "SELECT p FROM Provincia p WHERE p.idprovincia = :idprovincia"),
    @NamedQuery(name = "Provincia.findByNombreprovincia", query = "SELECT p FROM Provincia p WHERE p.nombreprovincia = :nombreprovincia"),
    @NamedQuery(name = "Provincia.findByCanceladoprovincia", query = "SELECT p FROM Provincia p WHERE p.canceladoprovincia = :canceladoprovincia")})
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprovincia")
    private Integer idprovincia;
    @Basic(optional = false)
    @Column(name = "nombreprovincia")
    private String nombreprovincia;
    @Basic(optional = false)
    @Column(name = "canceladoprovincia")
    private boolean canceladoprovincia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinciaidprovincia")
    private List<Municipio> municipioList;
    @OneToMany(mappedBy = "provinciaidprovincia")
    private List<Estudiante> estudianteList;
    @JoinColumn(name = "paisidpais", referencedColumnName = "idpais")
    @ManyToOne(optional = false)
    private Pais paisidpais;

    public Provincia() {
    }

    public Provincia(Integer idprovincia) {
        this.idprovincia = idprovincia;
    }

    public Provincia(Integer idprovincia, String nombreprovincia, boolean canceladoprovincia) {
        this.idprovincia = idprovincia;
        this.nombreprovincia = nombreprovincia;
        this.canceladoprovincia = canceladoprovincia;
    }

    public Integer getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(Integer idprovincia) {
        this.idprovincia = idprovincia;
    }

    public String getNombreprovincia() {
        return nombreprovincia;
    }

    public void setNombreprovincia(String nombreprovincia) {
        this.nombreprovincia = nombreprovincia;
    }

    public boolean getCanceladoprovincia() {
        return canceladoprovincia;
    }

    public void setCanceladoprovincia(boolean canceladoprovincia) {
        this.canceladoprovincia = canceladoprovincia;
    }

    @XmlTransient
    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    public Pais getPaisidpais() {
        return paisidpais;
    }

    public void setPaisidpais(Pais paisidpais) {
        this.paisidpais = paisidpais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprovincia != null ? idprovincia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
        if ((this.idprovincia == null && other.idprovincia != null) || (this.idprovincia != null && !this.idprovincia.equals(other.idprovincia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Provincia[ idprovincia=" + idprovincia + " ]";
    }

    public Provincia(String nombreprovincia, boolean canceladoprovincia, Pais paisidpais) {
        this.nombreprovincia = nombreprovincia;
        this.canceladoprovincia = canceladoprovincia;
        this.paisidpais = paisidpais;
    }
}
