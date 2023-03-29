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
@Table(name = "procedencia_escolar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcedenciaEscolar.findAll", query = "SELECT p FROM ProcedenciaEscolar p"),
    @NamedQuery(name = "ProcedenciaEscolar.findByProcedenciaEscolarId", query = "SELECT p FROM ProcedenciaEscolar p WHERE p.procedenciaEscolarId = :procedenciaEscolarId"),
    @NamedQuery(name = "ProcedenciaEscolar.findByProcedenciaEscolarNombre", query = "SELECT p FROM ProcedenciaEscolar p WHERE p.procedenciaEscolarNombre = :procedenciaEscolarNombre"),
    @NamedQuery(name = "ProcedenciaEscolar.findByProcedenciaEscolarCancelado", query = "SELECT p FROM ProcedenciaEscolar p WHERE p.procedenciaEscolarCancelado = :procedenciaEscolarCancelado")})
public class ProcedenciaEscolar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "procedencia_escolar_id")
    private Integer procedenciaEscolarId;
    @Basic(optional = false)
    @Column(name = "procedencia_escolar_nombre")
    private String procedenciaEscolarNombre;
    @Basic(optional = false)
    @Column(name = "procedencia_escolar_cancelado")
    private boolean procedenciaEscolarCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procedenciaEscolarprocedenciaEscolarId")
    private List<Estudiante> estudianteList;

    public ProcedenciaEscolar() {
    }

    public ProcedenciaEscolar(Integer procedenciaEscolarId) {
        this.procedenciaEscolarId = procedenciaEscolarId;
    }

    public ProcedenciaEscolar(Integer procedenciaEscolarId, String procedenciaEscolarNombre, boolean procedenciaEscolarCancelado) {
        this.procedenciaEscolarId = procedenciaEscolarId;
        this.procedenciaEscolarNombre = procedenciaEscolarNombre;
        this.procedenciaEscolarCancelado = procedenciaEscolarCancelado;
    }

    public Integer getProcedenciaEscolarId() {
        return procedenciaEscolarId;
    }

    public void setProcedenciaEscolarId(Integer procedenciaEscolarId) {
        this.procedenciaEscolarId = procedenciaEscolarId;
    }

    public String getProcedenciaEscolarNombre() {
        return procedenciaEscolarNombre;
    }

    public void setProcedenciaEscolarNombre(String procedenciaEscolarNombre) {
        this.procedenciaEscolarNombre = procedenciaEscolarNombre;
    }

    public boolean getProcedenciaEscolarCancelado() {
        return procedenciaEscolarCancelado;
    }

    public void setProcedenciaEscolarCancelado(boolean procedenciaEscolarCancelado) {
        this.procedenciaEscolarCancelado = procedenciaEscolarCancelado;
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
        hash += (procedenciaEscolarId != null ? procedenciaEscolarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcedenciaEscolar)) {
            return false;
        }
        ProcedenciaEscolar other = (ProcedenciaEscolar) object;
        if ((this.procedenciaEscolarId == null && other.procedenciaEscolarId != null) || (this.procedenciaEscolarId != null && !this.procedenciaEscolarId.equals(other.procedenciaEscolarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProcedenciaEscolar[ procedenciaEscolarId=" + procedenciaEscolarId + " ]";
    }

    public ProcedenciaEscolar(String procedenciaEscolarNombre, boolean procedenciaEscolarCancelado) {
        this.procedenciaEscolarNombre = procedenciaEscolarNombre;
        this.procedenciaEscolarCancelado = procedenciaEscolarCancelado;
    }
}
