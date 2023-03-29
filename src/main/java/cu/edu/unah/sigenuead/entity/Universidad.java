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
@Table(name = "universidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Universidad.findAll", query = "SELECT u FROM Universidad u"),
    @NamedQuery(name = "Universidad.findByCodigouniversidad", query = "SELECT u FROM Universidad u WHERE u.codigouniversidad = :codigouniversidad"),
    @NamedQuery(name = "Universidad.findByNombreuniversidad", query = "SELECT u FROM Universidad u WHERE u.nombreuniversidad = :nombreuniversidad"),
    @NamedQuery(name = "Universidad.findByInicialesuniversidad", query = "SELECT u FROM Universidad u WHERE u.inicialesuniversidad = :inicialesuniversidad"),
    @NamedQuery(name = "Universidad.findByActividaduniversidad", query = "SELECT u FROM Universidad u WHERE u.actividaduniversidad = :actividaduniversidad"),
    @NamedQuery(name = "Universidad.findByTelefonouniversidad", query = "SELECT u FROM Universidad u WHERE u.telefonouniversidad = :telefonouniversidad"),
    @NamedQuery(name = "Universidad.findByReglamentouniversidad", query = "SELECT u FROM Universidad u WHERE u.reglamentouniversidad = :reglamentouniversidad"),
    @NamedQuery(name = "Universidad.findByFaxuniversidad", query = "SELECT u FROM Universidad u WHERE u.faxuniversidad = :faxuniversidad"),
    @NamedQuery(name = "Universidad.findByNombrerectoruniversidad", query = "SELECT u FROM Universidad u WHERE u.nombrerectoruniversidad = :nombrerectoruniversidad"),
    @NamedQuery(name = "Universidad.findByNombresecgraluniversidad", query = "SELECT u FROM Universidad u WHERE u.nombresecgraluniversidad = :nombresecgraluniversidad"),
    @NamedQuery(name = "Universidad.findByDireccionuniversidad", query = "SELECT u FROM Universidad u WHERE u.direccionuniversidad = :direccionuniversidad"),
    @NamedQuery(name = "Universidad.findByCanceladouniversidad", query = "SELECT u FROM Universidad u WHERE u.canceladouniversidad = :canceladouniversidad")})
public class Universidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigouniversidad")
    private String codigouniversidad;
    @Basic(optional = false)
    @Column(name = "nombreuniversidad")
    private String nombreuniversidad;
    @Basic(optional = false)
    @Column(name = "inicialesuniversidad")
    private String inicialesuniversidad;
    @Column(name = "actividaduniversidad")
    private String actividaduniversidad;
    @Column(name = "telefonouniversidad")
    private String telefonouniversidad;
    @Basic(optional = false)
    @Column(name = "reglamentouniversidad")
    private String reglamentouniversidad;
    @Column(name = "faxuniversidad")
    private String faxuniversidad;
    @Basic(optional = false)
    @Column(name = "nombrerectoruniversidad")
    private String nombrerectoruniversidad;
    @Basic(optional = false)
    @Column(name = "nombresecgraluniversidad")
    private String nombresecgraluniversidad;
    @Basic(optional = false)
    @Column(name = "direccionuniversidad")
    private String direccionuniversidad;
    @Basic(optional = false)
    @Column(name = "canceladouniversidad")
    private boolean canceladouniversidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "universidadcodigouniversidad")
    private List<Facultad> facultadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "universidadcodigouniversidad")
    private List<Curso> cursoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "universidadcodigouniversidad")
    private List<Cum> cumList;
    @JoinColumn(name = "municipioidmunicipio", referencedColumnName = "idmunicipio")
    @ManyToOne(optional = false)
    private Municipio municipioidmunicipio;

    public Universidad() {
    }

    public Universidad(String codigouniversidad) {
        this.codigouniversidad = codigouniversidad;
    }

    public Universidad(String codigouniversidad, String nombreuniversidad, String inicialesuniversidad, String reglamentouniversidad, String nombrerectoruniversidad, String nombresecgraluniversidad, String direccionuniversidad, boolean canceladouniversidad) {
        this.codigouniversidad = codigouniversidad;
        this.nombreuniversidad = nombreuniversidad;
        this.inicialesuniversidad = inicialesuniversidad;
        this.reglamentouniversidad = reglamentouniversidad;
        this.nombrerectoruniversidad = nombrerectoruniversidad;
        this.nombresecgraluniversidad = nombresecgraluniversidad;
        this.direccionuniversidad = direccionuniversidad;
        this.canceladouniversidad = canceladouniversidad;
    }

    public String getCodigouniversidad() {
        return codigouniversidad;
    }

    public void setCodigouniversidad(String codigouniversidad) {
        this.codigouniversidad = codigouniversidad;
    }

    public String getNombreuniversidad() {
        return nombreuniversidad;
    }

    public void setNombreuniversidad(String nombreuniversidad) {
        this.nombreuniversidad = nombreuniversidad;
    }

    public String getInicialesuniversidad() {
        return inicialesuniversidad;
    }

    public void setInicialesuniversidad(String inicialesuniversidad) {
        this.inicialesuniversidad = inicialesuniversidad;
    }

    public String getActividaduniversidad() {
        return actividaduniversidad;
    }

    public void setActividaduniversidad(String actividaduniversidad) {
        this.actividaduniversidad = actividaduniversidad;
    }

    public String getTelefonouniversidad() {
        return telefonouniversidad;
    }

    public void setTelefonouniversidad(String telefonouniversidad) {
        this.telefonouniversidad = telefonouniversidad;
    }

    public String getReglamentouniversidad() {
        return reglamentouniversidad;
    }

    public void setReglamentouniversidad(String reglamentouniversidad) {
        this.reglamentouniversidad = reglamentouniversidad;
    }

    public String getFaxuniversidad() {
        return faxuniversidad;
    }

    public void setFaxuniversidad(String faxuniversidad) {
        this.faxuniversidad = faxuniversidad;
    }

    public String getNombrerectoruniversidad() {
        return nombrerectoruniversidad;
    }

    public void setNombrerectoruniversidad(String nombrerectoruniversidad) {
        this.nombrerectoruniversidad = nombrerectoruniversidad;
    }

    public String getNombresecgraluniversidad() {
        return nombresecgraluniversidad;
    }

    public void setNombresecgraluniversidad(String nombresecgraluniversidad) {
        this.nombresecgraluniversidad = nombresecgraluniversidad;
    }

    public String getDireccionuniversidad() {
        return direccionuniversidad;
    }

    public void setDireccionuniversidad(String direccionuniversidad) {
        this.direccionuniversidad = direccionuniversidad;
    }

    public boolean getCanceladouniversidad() {
        return canceladouniversidad;
    }

    public void setCanceladouniversidad(boolean canceladouniversidad) {
        this.canceladouniversidad = canceladouniversidad;
    }

    @XmlTransient
    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    @XmlTransient
    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    @XmlTransient
    public List<Cum> getCumList() {
        return cumList;
    }

    public void setCumList(List<Cum> cumList) {
        this.cumList = cumList;
    }

    public Municipio getMunicipioidmunicipio() {
        return municipioidmunicipio;
    }

    public void setMunicipioidmunicipio(Municipio municipioidmunicipio) {
        this.municipioidmunicipio = municipioidmunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigouniversidad != null ? codigouniversidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Universidad)) {
            return false;
        }
        Universidad other = (Universidad) object;
        if ((this.codigouniversidad == null && other.codigouniversidad != null) || (this.codigouniversidad != null && !this.codigouniversidad.equals(other.codigouniversidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Universidad[ codigouniversidad=" + codigouniversidad + " ]";
    }

    public Universidad(String codigouniversidad, String nombreuniversidad, String inicialesuniversidad, String actividaduniversidad, String telefonouniversidad, String reglamentouniversidad, String faxuniversidad, String nombrerectoruniversidad, String nombresecgraluniversidad, String direccionuniversidad, boolean canceladouniversidad, Municipio municipioidmunicipio) {
        this.codigouniversidad = codigouniversidad;
        this.nombreuniversidad = nombreuniversidad;
        this.inicialesuniversidad = inicialesuniversidad;
        this.actividaduniversidad = actividaduniversidad;
        this.telefonouniversidad = telefonouniversidad;
        this.reglamentouniversidad = reglamentouniversidad;
        this.faxuniversidad = faxuniversidad;
        this.nombrerectoruniversidad = nombrerectoruniversidad;
        this.nombresecgraluniversidad = nombresecgraluniversidad;
        this.direccionuniversidad = direccionuniversidad;
        this.canceladouniversidad = canceladouniversidad;
        this.municipioidmunicipio = municipioidmunicipio;
    }
}
