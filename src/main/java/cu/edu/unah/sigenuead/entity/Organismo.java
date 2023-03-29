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
@Table(name = "organismo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organismo.findAll", query = "SELECT o FROM Organismo o"),
    @NamedQuery(name = "Organismo.findByIdorganismo", query = "SELECT o FROM Organismo o WHERE o.idorganismo = :idorganismo"),
    @NamedQuery(name = "Organismo.findByNombreorganismo", query = "SELECT o FROM Organismo o WHERE o.nombreorganismo = :nombreorganismo"),
    @NamedQuery(name = "Organismo.findByCanceladoorganismo", query = "SELECT o FROM Organismo o WHERE o.canceladoorganismo = :canceladoorganismo")})
public class Organismo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorganismo")
    private Integer idorganismo;
    @Basic(optional = false)
    @Column(name = "nombreorganismo")
    private String nombreorganismo;
    @Basic(optional = false)
    @Column(name = "canceladoorganismo")
    private boolean canceladoorganismo;
    @OneToMany(mappedBy = "organismoidorganismo")
    private List<Estudiante> estudianteList;

    public Organismo() {
    }

    public Organismo(Integer idorganismo) {
        this.idorganismo = idorganismo;
    }

    public Organismo(Integer idorganismo, String nombreorganismo, boolean canceladoorganismo) {
        this.idorganismo = idorganismo;
        this.nombreorganismo = nombreorganismo;
        this.canceladoorganismo = canceladoorganismo;
    }

    public Integer getIdorganismo() {
        return idorganismo;
    }

    public void setIdorganismo(Integer idorganismo) {
        this.idorganismo = idorganismo;
    }

    public String getNombreorganismo() {
        return nombreorganismo;
    }

    public void setNombreorganismo(String nombreorganismo) {
        this.nombreorganismo = nombreorganismo;
    }

    public boolean getCanceladoorganismo() {
        return canceladoorganismo;
    }

    public void setCanceladoorganismo(boolean canceladoorganismo) {
        this.canceladoorganismo = canceladoorganismo;
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
        hash += (idorganismo != null ? idorganismo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organismo)) {
            return false;
        }
        Organismo other = (Organismo) object;
        if ((this.idorganismo == null && other.idorganismo != null) || (this.idorganismo != null && !this.idorganismo.equals(other.idorganismo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Organismo[ idorganismo=" + idorganismo + " ]";
    }

    public Organismo(String nombreorganismo, boolean canceladoorganismo) {
        this.nombreorganismo = nombreorganismo;
        this.canceladoorganismo = canceladoorganismo;
    }
}
