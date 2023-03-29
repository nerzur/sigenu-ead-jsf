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
@Table(name = "especialidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Especialidad.findAll", query = "SELECT e FROM Especialidad e"),
    @NamedQuery(name = "Especialidad.findByIdespecialidad", query = "SELECT e FROM Especialidad e WHERE e.idespecialidad = :idespecialidad"),
    @NamedQuery(name = "Especialidad.findByNombreespecialidad", query = "SELECT e FROM Especialidad e WHERE e.nombreespecialidad = :nombreespecialidad"),
    @NamedQuery(name = "Especialidad.findByCanceladoespecialidad", query = "SELECT e FROM Especialidad e WHERE e.canceladoespecialidad = :canceladoespecialidad")})
public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idespecialidad")
    private Integer idespecialidad;
    @Basic(optional = false)
    @Column(name = "nombreespecialidad")
    private String nombreespecialidad;
    @Basic(optional = false)
    @Column(name = "canceladoespecialidad")
    private boolean canceladoespecialidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "especialidadidespecialidad")
    private List<Carreranacional> carreranacionalList;

    public Especialidad() {
    }

    public Especialidad(Integer idespecialidad) {
        this.idespecialidad = idespecialidad;
    }

    public Especialidad(Integer idespecialidad, String nombreespecialidad, boolean canceladoespecialidad) {
        this.idespecialidad = idespecialidad;
        this.nombreespecialidad = nombreespecialidad;
        this.canceladoespecialidad = canceladoespecialidad;
    }

    public Integer getIdespecialidad() {
        return idespecialidad;
    }

    public void setIdespecialidad(Integer idespecialidad) {
        this.idespecialidad = idespecialidad;
    }

    public String getNombreespecialidad() {
        return nombreespecialidad;
    }

    public void setNombreespecialidad(String nombreespecialidad) {
        this.nombreespecialidad = nombreespecialidad;
    }

    public boolean getCanceladoespecialidad() {
        return canceladoespecialidad;
    }

    public void setCanceladoespecialidad(boolean canceladoespecialidad) {
        this.canceladoespecialidad = canceladoespecialidad;
    }

    @XmlTransient
    public List<Carreranacional> getCarreranacionalList() {
        return carreranacionalList;
    }

    public void setCarreranacionalList(List<Carreranacional> carreranacionalList) {
        this.carreranacionalList = carreranacionalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idespecialidad != null ? idespecialidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especialidad)) {
            return false;
        }
        Especialidad other = (Especialidad) object;
        if ((this.idespecialidad == null && other.idespecialidad != null) || (this.idespecialidad != null && !this.idespecialidad.equals(other.idespecialidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Especialidad[ idespecialidad=" + idespecialidad + " ]";
    }

    public Especialidad(String nombreespecialidad, boolean canceladoespecialidad) {
        this.nombreespecialidad = nombreespecialidad;
        this.canceladoespecialidad = canceladoespecialidad;
    }
}
