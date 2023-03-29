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
@Table(name = "disciplina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disciplina.findAll", query = "SELECT d FROM Disciplina d"),
    @NamedQuery(name = "Disciplina.findByDisciplinaCodigo", query = "SELECT d FROM Disciplina d WHERE d.disciplinaCodigo = :disciplinaCodigo"),
    @NamedQuery(name = "Disciplina.findByDisciplinaNombre", query = "SELECT d FROM Disciplina d WHERE d.disciplinaNombre = :disciplinaNombre"),
    @NamedQuery(name = "Disciplina.findByDisciplinaCancelada", query = "SELECT d FROM Disciplina d WHERE d.disciplinaCancelada = :disciplinaCancelada")})
public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "disciplina_codigo")
    private String disciplinaCodigo;
    @Basic(optional = false)
    @Column(name = "disciplina_nombre")
    private String disciplinaNombre;
    @Basic(optional = false)
    @Column(name = "disciplina_cancelada")
    private boolean disciplinaCancelada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disciplina")
    private List<DisciplinaPlanestudio> disciplinaPlanestudioList;

    public Disciplina() {
    }

    public Disciplina(String disciplinaCodigo) {
        this.disciplinaCodigo = disciplinaCodigo;
    }

    public Disciplina(String disciplinaCodigo, String disciplinaNombre, boolean disciplinaCancelada) {
        this.disciplinaCodigo = disciplinaCodigo;
        this.disciplinaNombre = disciplinaNombre;
        this.disciplinaCancelada = disciplinaCancelada;
    }

    public String getDisciplinaCodigo() {
        return disciplinaCodigo;
    }

    public void setDisciplinaCodigo(String disciplinaCodigo) {
        this.disciplinaCodigo = disciplinaCodigo;
    }

    public String getDisciplinaNombre() {
        return disciplinaNombre;
    }

    public void setDisciplinaNombre(String disciplinaNombre) {
        this.disciplinaNombre = disciplinaNombre;
    }

    public boolean getDisciplinaCancelada() {
        return disciplinaCancelada;
    }

    public void setDisciplinaCancelada(boolean disciplinaCancelada) {
        this.disciplinaCancelada = disciplinaCancelada;
    }

    @XmlTransient
    public List<DisciplinaPlanestudio> getDisciplinaPlanestudioList() {
        return disciplinaPlanestudioList;
    }

    public void setDisciplinaPlanestudioList(List<DisciplinaPlanestudio> disciplinaPlanestudioList) {
        this.disciplinaPlanestudioList = disciplinaPlanestudioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (disciplinaCodigo != null ? disciplinaCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disciplina)) {
            return false;
        }
        Disciplina other = (Disciplina) object;
        if ((this.disciplinaCodigo == null && other.disciplinaCodigo != null) || (this.disciplinaCodigo != null && !this.disciplinaCodigo.equals(other.disciplinaCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Disciplina[ disciplinaCodigo=" + disciplinaCodigo + " ]";
    }
    
}
