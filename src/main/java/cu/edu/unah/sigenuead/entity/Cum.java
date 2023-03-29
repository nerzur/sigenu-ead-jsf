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
@Table(name = "cum")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cum.findAll", query = "SELECT c FROM Cum c"),
    @NamedQuery(name = "Cum.findByCodigocum", query = "SELECT c FROM Cum c WHERE c.codigocum = :codigocum"),
    @NamedQuery(name = "Cum.findByInicialescum", query = "SELECT c FROM Cum c WHERE c.inicialescum = :inicialescum"),
    @NamedQuery(name = "Cum.findByActividadcum", query = "SELECT c FROM Cum c WHERE c.actividadcum = :actividadcum"),
    @NamedQuery(name = "Cum.findByNombrecum", query = "SELECT c FROM Cum c WHERE c.nombrecum = :nombrecum"),
    @NamedQuery(name = "Cum.findByDireccioncum", query = "SELECT c FROM Cum c WHERE c.direccioncum = :direccioncum"),
    @NamedQuery(name = "Cum.findByTelefonocum", query = "SELECT c FROM Cum c WHERE c.telefonocum = :telefonocum"),
    @NamedQuery(name = "Cum.findByFaxcum", query = "SELECT c FROM Cum c WHERE c.faxcum = :faxcum"),
    @NamedQuery(name = "Cum.findByNombresecgralcum", query = "SELECT c FROM Cum c WHERE c.nombresecgralcum = :nombresecgralcum"),
    @NamedQuery(name = "Cum.findByNombredirectorcum", query = "SELECT c FROM Cum c WHERE c.nombredirectorcum = :nombredirectorcum"),
    @NamedQuery(name = "Cum.findByReglamentocum", query = "SELECT c FROM Cum c WHERE c.reglamentocum = :reglamentocum"),
    @NamedQuery(name = "Cum.findByCumcancelado", query = "SELECT c FROM Cum c WHERE c.cumcancelado = :cumcancelado")})
public class Cum implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigocum")
    private String codigocum;
    @Basic(optional = false)
    @Column(name = "inicialescum")
    private String inicialescum;
    @Column(name = "actividadcum")
    private String actividadcum;
    @Basic(optional = false)
    @Column(name = "nombrecum")
    private String nombrecum;
    @Basic(optional = false)
    @Column(name = "direccioncum")
    private String direccioncum;
    @Column(name = "telefonocum")
    private String telefonocum;
    @Column(name = "faxcum")
    private String faxcum;
    @Basic(optional = false)
    @Column(name = "nombresecgralcum")
    private String nombresecgralcum;
    @Basic(optional = false)
    @Column(name = "nombredirectorcum")
    private String nombredirectorcum;
    @Basic(optional = false)
    @Column(name = "reglamentocum")
    private String reglamentocum;
    @Basic(optional = false)
    @Column(name = "cumcancelado")
    private boolean cumcancelado;
    @JoinTable(name = "cum_authorities", joinColumns = {
        @JoinColumn(name = "cumcodigocum", referencedColumnName = "codigocum")}, inverseJoinColumns = {
        @JoinColumn(name = "authoritiesusername", referencedColumnName = "username"),
        @JoinColumn(name = "authoritiesauthority", referencedColumnName = "authority")})
    @ManyToMany
    private List<Authorities> authoritiesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cum")
    private List<FacultadCum> facultadCumList;
    @JoinColumn(name = "municipioidmunicipio", referencedColumnName = "idmunicipio")
    @ManyToOne(optional = false)
    private Municipio municipioidmunicipio;
    @JoinColumn(name = "universidadcodigouniversidad", referencedColumnName = "codigouniversidad")
    @ManyToOne(optional = false)
    private Universidad universidadcodigouniversidad;

    public Cum() {
    }

    public Cum(String codigocum) {
        this.codigocum = codigocum;
    }

    public Cum(String codigocum, String inicialescum, String nombrecum, String direccioncum, String nombresecgralcum, String nombredirectorcum, String reglamentocum, boolean cumcancelado) {
        this.codigocum = codigocum;
        this.inicialescum = inicialescum;
        this.nombrecum = nombrecum;
        this.direccioncum = direccioncum;
        this.nombresecgralcum = nombresecgralcum;
        this.nombredirectorcum = nombredirectorcum;
        this.reglamentocum = reglamentocum;
        this.cumcancelado = cumcancelado;
    }

    public String getCodigocum() {
        return codigocum;
    }

    public void setCodigocum(String codigocum) {
        this.codigocum = codigocum;
    }

    public String getInicialescum() {
        return inicialescum;
    }

    public void setInicialescum(String inicialescum) {
        this.inicialescum = inicialescum;
    }

    public String getActividadcum() {
        return actividadcum;
    }

    public void setActividadcum(String actividadcum) {
        this.actividadcum = actividadcum;
    }

    public String getNombrecum() {
        return nombrecum;
    }

    public void setNombrecum(String nombrecum) {
        this.nombrecum = nombrecum;
    }

    public String getDireccioncum() {
        return direccioncum;
    }

    public void setDireccioncum(String direccioncum) {
        this.direccioncum = direccioncum;
    }

    public String getTelefonocum() {
        return telefonocum;
    }

    public void setTelefonocum(String telefonocum) {
        this.telefonocum = telefonocum;
    }

    public String getFaxcum() {
        return faxcum;
    }

    public void setFaxcum(String faxcum) {
        this.faxcum = faxcum;
    }

    public String getNombresecgralcum() {
        return nombresecgralcum;
    }

    public void setNombresecgralcum(String nombresecgralcum) {
        this.nombresecgralcum = nombresecgralcum;
    }

    public String getNombredirectorcum() {
        return nombredirectorcum;
    }

    public void setNombredirectorcum(String nombredirectorcum) {
        this.nombredirectorcum = nombredirectorcum;
    }

    public String getReglamentocum() {
        return reglamentocum;
    }

    public void setReglamentocum(String reglamentocum) {
        this.reglamentocum = reglamentocum;
    }

    public boolean getCumcancelado() {
        return cumcancelado;
    }

    public void setCumcancelado(boolean cumcancelado) {
        this.cumcancelado = cumcancelado;
    }

    @XmlTransient
    public List<Authorities> getAuthoritiesList() {
        return authoritiesList;
    }

    public void setAuthoritiesList(List<Authorities> authoritiesList) {
        this.authoritiesList = authoritiesList;
    }

    @XmlTransient
    public List<FacultadCum> getFacultadCumList() {
        return facultadCumList;
    }

    public void setFacultadCumList(List<FacultadCum> facultadCumList) {
        this.facultadCumList = facultadCumList;
    }

    public Municipio getMunicipioidmunicipio() {
        return municipioidmunicipio;
    }

    public void setMunicipioidmunicipio(Municipio municipioidmunicipio) {
        this.municipioidmunicipio = municipioidmunicipio;
    }

    public Universidad getUniversidadcodigouniversidad() {
        return universidadcodigouniversidad;
    }

    public void setUniversidadcodigouniversidad(Universidad universidadcodigouniversidad) {
        this.universidadcodigouniversidad = universidadcodigouniversidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigocum != null ? codigocum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cum)) {
            return false;
        }
        Cum other = (Cum) object;
        if ((this.codigocum == null && other.codigocum != null) || (this.codigocum != null && !this.codigocum.equals(other.codigocum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Cum[ codigocum=" + codigocum + " ]";
    }

    public Cum(String codigocum, String inicialescum, String actividadcum, String nombrecum, boolean cancel, String direccioncum, String telefonocum, String faxcum, String nombresecgralcum, String nombredirectorcum, String reglamentocum, Municipio municipioidmunicipio, Universidad universidadcodigouniversidad) {
        this.codigocum = codigocum;
        this.inicialescum = inicialescum;
        this.actividadcum = actividadcum;
        this.nombrecum = nombrecum;
        this.direccioncum = direccioncum;
        this.telefonocum = telefonocum;
        this.faxcum = faxcum;
        this.nombresecgralcum = nombresecgralcum;
        this.nombredirectorcum = nombredirectorcum;
        this.reglamentocum = reglamentocum;
        this.cumcancelado = cancel;
        this.municipioidmunicipio = municipioidmunicipio;
        this.universidadcodigouniversidad = universidadcodigouniversidad;
    }
}
