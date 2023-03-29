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
@Table(name = "municipio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m"),
    @NamedQuery(name = "Municipio.findByIdmunicipio", query = "SELECT m FROM Municipio m WHERE m.idmunicipio = :idmunicipio"),
    @NamedQuery(name = "Municipio.findByNombremunicipio", query = "SELECT m FROM Municipio m WHERE m.nombremunicipio = :nombremunicipio"),
    @NamedQuery(name = "Municipio.findByCanceladomunicipio", query = "SELECT m FROM Municipio m WHERE m.canceladomunicipio = :canceladomunicipio")})
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmunicipio")
    private Integer idmunicipio;
    @Basic(optional = false)
    @Column(name = "nombremunicipio")
    private String nombremunicipio;
    @Basic(optional = false)
    @Column(name = "canceladomunicipio")
    private boolean canceladomunicipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipioidmunicipio")
    private List<Facultad> facultadList;
    @JoinColumn(name = "provinciaidprovincia", referencedColumnName = "idprovincia")
    @ManyToOne(optional = false)
    private Provincia provinciaidprovincia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipioidmunicipio")
    private List<Cum> cumList;
    @OneToMany(mappedBy = "municipioidmunicipio")
    private List<Estudiante> estudianteList;
    @OneToMany(mappedBy = "municipioidmunicipiocentrotrabajo")
    private List<Estudiante> estudianteList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipioidmunicipio")
    private List<Universidad> universidadList;

    public Municipio() {
    }

    public Municipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public Municipio(Integer idmunicipio, String nombremunicipio, boolean canceladomunicipio) {
        this.idmunicipio = idmunicipio;
        this.nombremunicipio = nombremunicipio;
        this.canceladomunicipio = canceladomunicipio;
    }

    public Integer getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public String getNombremunicipio() {
        return nombremunicipio;
    }

    public void setNombremunicipio(String nombremunicipio) {
        this.nombremunicipio = nombremunicipio;
    }

    public boolean getCanceladomunicipio() {
        return canceladomunicipio;
    }

    public void setCanceladomunicipio(boolean canceladomunicipio) {
        this.canceladomunicipio = canceladomunicipio;
    }

    @XmlTransient
    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    public Provincia getProvinciaidprovincia() {
        return provinciaidprovincia;
    }

    public void setProvinciaidprovincia(Provincia provinciaidprovincia) {
        this.provinciaidprovincia = provinciaidprovincia;
    }

    @XmlTransient
    public List<Cum> getCumList() {
        return cumList;
    }

    public void setCumList(List<Cum> cumList) {
        this.cumList = cumList;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList1() {
        return estudianteList1;
    }

    public void setEstudianteList1(List<Estudiante> estudianteList1) {
        this.estudianteList1 = estudianteList1;
    }

    @XmlTransient
    public List<Universidad> getUniversidadList() {
        return universidadList;
    }

    public void setUniversidadList(List<Universidad> universidadList) {
        this.universidadList = universidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmunicipio != null ? idmunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.idmunicipio == null && other.idmunicipio != null) || (this.idmunicipio != null && !this.idmunicipio.equals(other.idmunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Municipio[ idmunicipio=" + idmunicipio + " ]";
    }

    public Municipio(String nombremunicipio, boolean canceladomunicipio, Provincia provinciaidprovincia) {
        this.nombremunicipio = nombremunicipio;
        this.canceladomunicipio = canceladomunicipio;
        this.provinciaidprovincia = provinciaidprovincia;
    }
}
