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
@Table(name = "color_piel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ColorPiel.findAll", query = "SELECT c FROM ColorPiel c"),
    @NamedQuery(name = "ColorPiel.findByColorPielId", query = "SELECT c FROM ColorPiel c WHERE c.colorPielId = :colorPielId"),
    @NamedQuery(name = "ColorPiel.findByColorPielNombre", query = "SELECT c FROM ColorPiel c WHERE c.colorPielNombre = :colorPielNombre"),
    @NamedQuery(name = "ColorPiel.findByColorPielCancelado", query = "SELECT c FROM ColorPiel c WHERE c.colorPielCancelado = :colorPielCancelado")})
public class ColorPiel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "color_piel_id")
    private Integer colorPielId;
    @Basic(optional = false)
    @Column(name = "color_piel_nombre")
    private String colorPielNombre;
    @Basic(optional = false)
    @Column(name = "color_piel_cancelado")
    private boolean colorPielCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colorPielcolorPielId")
    private List<Estudiante> estudianteList;

    public ColorPiel() {
    }

    public ColorPiel(Integer colorPielId) {
        this.colorPielId = colorPielId;
    }

    public ColorPiel(Integer colorPielId, String colorPielNombre, boolean colorPielCancelado) {
        this.colorPielId = colorPielId;
        this.colorPielNombre = colorPielNombre;
        this.colorPielCancelado = colorPielCancelado;
    }

    public Integer getColorPielId() {
        return colorPielId;
    }

    public void setColorPielId(Integer colorPielId) {
        this.colorPielId = colorPielId;
    }

    public String getColorPielNombre() {
        return colorPielNombre;
    }

    public void setColorPielNombre(String colorPielNombre) {
        this.colorPielNombre = colorPielNombre;
    }

    public boolean getColorPielCancelado() {
        return colorPielCancelado;
    }

    public void setColorPielCancelado(boolean colorPielCancelado) {
        this.colorPielCancelado = colorPielCancelado;
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
        hash += (colorPielId != null ? colorPielId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ColorPiel)) {
            return false;
        }
        ColorPiel other = (ColorPiel) object;
        if ((this.colorPielId == null && other.colorPielId != null) || (this.colorPielId != null && !this.colorPielId.equals(other.colorPielId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ColorPiel[ colorPielId=" + colorPielId + " ]";
    }

    public ColorPiel(String colorPielNombre, boolean colorPielCancelado) {
        this.colorPielNombre = colorPielNombre;
        this.colorPielCancelado = colorPielCancelado;
    }
}
