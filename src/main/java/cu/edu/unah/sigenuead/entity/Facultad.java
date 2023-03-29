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
@Table(name = "facultad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f"),
    @NamedQuery(name = "Facultad.findByCodigoarea", query = "SELECT f FROM Facultad f WHERE f.codigoarea = :codigoarea"),
    @NamedQuery(name = "Facultad.findByNombrearea", query = "SELECT f FROM Facultad f WHERE f.nombrearea = :nombrearea"),
    @NamedQuery(name = "Facultad.findByNombresecdocarea", query = "SELECT f FROM Facultad f WHERE f.nombresecdocarea = :nombresecdocarea"),
    @NamedQuery(name = "Facultad.findByNombredecanoarea", query = "SELECT f FROM Facultad f WHERE f.nombredecanoarea = :nombredecanoarea"),
    @NamedQuery(name = "Facultad.findByTelefonoarea", query = "SELECT f FROM Facultad f WHERE f.telefonoarea = :telefonoarea"),
    @NamedQuery(name = "Facultad.findByDireccionarea", query = "SELECT f FROM Facultad f WHERE f.direccionarea = :direccionarea"),
    @NamedQuery(name = "Facultad.findByCanceladoarea", query = "SELECT f FROM Facultad f WHERE f.canceladoarea = :canceladoarea")})
public class Facultad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigoarea")
    private String codigoarea;
    @Basic(optional = false)
    @Column(name = "nombrearea")
    private String nombrearea;
    @Basic(optional = false)
    @Column(name = "nombresecdocarea")
    private String nombresecdocarea;
    @Basic(optional = false)
    @Column(name = "nombredecanoarea")
    private String nombredecanoarea;
    @Basic(optional = false)
    @Column(name = "telefonoarea")
    private String telefonoarea;
    @Basic(optional = false)
    @Column(name = "direccionarea")
    private String direccionarea;
    @Basic(optional = false)
    @Column(name = "canceladoarea")
    private boolean canceladoarea;
    @JoinTable(name = "facultad_authorities", joinColumns = {
        @JoinColumn(name = "facultadcodigoarea", referencedColumnName = "codigoarea")}, inverseJoinColumns = {
        @JoinColumn(name = "authoritiesusername", referencedColumnName = "username"),
        @JoinColumn(name = "authoritiesauthority", referencedColumnName = "authority")})
    @ManyToMany
    private List<Authorities> authoritiesList;
    @JoinColumn(name = "municipioidmunicipio", referencedColumnName = "idmunicipio")
    @ManyToOne(optional = false)
    private Municipio municipioidmunicipio;
    @JoinColumn(name = "universidadcodigouniversidad", referencedColumnName = "codigouniversidad")
    @ManyToOne(optional = false)
    private Universidad universidadcodigouniversidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultad")
    private List<FacultadCum> facultadCumList;
    @OneToMany(mappedBy = "facultadcodigoarea")
    private List<Carrera> carreraList;

    public Facultad() {
    }

    public Facultad(String codigoarea) {
        this.codigoarea = codigoarea;
    }

    public Facultad(String codigoarea, String nombrearea, String nombresecdocarea, String nombredecanoarea, String telefonoarea, String direccionarea, boolean canceladoarea) {
        this.codigoarea = codigoarea;
        this.nombrearea = nombrearea;
        this.nombresecdocarea = nombresecdocarea;
        this.nombredecanoarea = nombredecanoarea;
        this.telefonoarea = telefonoarea;
        this.direccionarea = direccionarea;
        this.canceladoarea = canceladoarea;
    }

    public String getCodigoarea() {
        return codigoarea;
    }

    public void setCodigoarea(String codigoarea) {
        this.codigoarea = codigoarea;
    }

    public String getNombrearea() {
        return nombrearea;
    }

    public void setNombrearea(String nombrearea) {
        this.nombrearea = nombrearea;
    }

    public String getNombresecdocarea() {
        return nombresecdocarea;
    }

    public void setNombresecdocarea(String nombresecdocarea) {
        this.nombresecdocarea = nombresecdocarea;
    }

    public String getNombredecanoarea() {
        return nombredecanoarea;
    }

    public void setNombredecanoarea(String nombredecanoarea) {
        this.nombredecanoarea = nombredecanoarea;
    }

    public String getTelefonoarea() {
        return telefonoarea;
    }

    public void setTelefonoarea(String telefonoarea) {
        this.telefonoarea = telefonoarea;
    }

    public String getDireccionarea() {
        return direccionarea;
    }

    public void setDireccionarea(String direccionarea) {
        this.direccionarea = direccionarea;
    }

    public boolean getCanceladoarea() {
        return canceladoarea;
    }

    public void setCanceladoarea(boolean canceladoarea) {
        this.canceladoarea = canceladoarea;
    }

    @XmlTransient
    public List<Authorities> getAuthoritiesList() {
        return authoritiesList;
    }

    public void setAuthoritiesList(List<Authorities> authoritiesList) {
        this.authoritiesList = authoritiesList;
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

    @XmlTransient
    public List<FacultadCum> getFacultadCumList() {
        return facultadCumList;
    }

    public void setFacultadCumList(List<FacultadCum> facultadCumList) {
        this.facultadCumList = facultadCumList;
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
        hash += (codigoarea != null ? codigoarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.codigoarea == null && other.codigoarea != null) || (this.codigoarea != null && !this.codigoarea.equals(other.codigoarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Facultad[ codigoarea=" + codigoarea + " ]";
    }

    public Facultad(String codigoarea, String nombrearea, String nombresecdocarea, String nombredecanoarea, String telefonoarea, String direccionarea, boolean canceladoarea, Municipio municipioidmunicipio, Universidad universidadcodigouniversidad) {
        this.codigoarea = codigoarea;
        this.nombrearea = nombrearea;
        this.nombresecdocarea = nombresecdocarea;
        this.nombredecanoarea = nombredecanoarea;
        this.telefonoarea = telefonoarea;
        this.direccionarea = direccionarea;
        this.canceladoarea = canceladoarea;
        this.municipioidmunicipio = municipioidmunicipio;
        this.universidadcodigouniversidad = universidadcodigouniversidad;
    }
}
