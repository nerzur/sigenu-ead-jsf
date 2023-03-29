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
@Table(name = "disciplina_planestudio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DisciplinaPlanestudio.findAll", query = "SELECT d FROM DisciplinaPlanestudio d"),
    @NamedQuery(name = "DisciplinaPlanestudio.findByDisciplinadisciplinaCodigo", query = "SELECT d FROM DisciplinaPlanestudio d WHERE d.disciplinaPlanestudioPK.disciplinadisciplinaCodigo = :disciplinadisciplinaCodigo"),
    @NamedQuery(name = "DisciplinaPlanestudio.findByPlanestudioidplanestudio", query = "SELECT d FROM DisciplinaPlanestudio d WHERE d.disciplinaPlanestudioPK.planestudioidplanestudio = :planestudioidplanestudio"),
    @NamedQuery(name = "DisciplinaPlanestudio.findByObjetivos", query = "SELECT d FROM DisciplinaPlanestudio d WHERE d.objetivos = :objetivos"),
    @NamedQuery(name = "DisciplinaPlanestudio.findByPrograma", query = "SELECT d FROM DisciplinaPlanestudio d WHERE d.programa = :programa"),
    @NamedQuery(name = "DisciplinaPlanestudio.findByCancelado", query = "SELECT d FROM DisciplinaPlanestudio d WHERE d.cancelado = :cancelado")})
public class DisciplinaPlanestudio implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DisciplinaPlanestudioPK disciplinaPlanestudioPK;
    @Column(name = "objetivos")
    private String objetivos;
    @Column(name = "programa")
    private String programa;
    @Basic(optional = false)
    @Column(name = "cancelado")
    private boolean cancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disciplinaPlanestudio")
    private List<Asignatura> asignaturaList;
    @JoinColumn(name = "disciplinadisciplina_codigo", referencedColumnName = "disciplina_codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Disciplina disciplina;
    @JoinColumn(name = "planestudioidplanestudio", referencedColumnName = "idplanestudio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Planestudio planestudio;

    public DisciplinaPlanestudio() {
    }

    public DisciplinaPlanestudio(DisciplinaPlanestudioPK disciplinaPlanestudioPK) {
        this.disciplinaPlanestudioPK = disciplinaPlanestudioPK;
    }

    public DisciplinaPlanestudio(DisciplinaPlanestudioPK disciplinaPlanestudioPK, boolean cancelado) {
        this.disciplinaPlanestudioPK = disciplinaPlanestudioPK;
        this.cancelado = cancelado;
    }

    public DisciplinaPlanestudio(String disciplinadisciplinaCodigo, int planestudioidplanestudio) {
        this.disciplinaPlanestudioPK = new DisciplinaPlanestudioPK(disciplinadisciplinaCodigo, planestudioidplanestudio);
    }

    public DisciplinaPlanestudioPK getDisciplinaPlanestudioPK() {
        return disciplinaPlanestudioPK;
    }

    public void setDisciplinaPlanestudioPK(DisciplinaPlanestudioPK disciplinaPlanestudioPK) {
        this.disciplinaPlanestudioPK = disciplinaPlanestudioPK;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    @XmlTransient
    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Planestudio getPlanestudio() {
        return planestudio;
    }

    public void setPlanestudio(Planestudio planestudio) {
        this.planestudio = planestudio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (disciplinaPlanestudioPK != null ? disciplinaPlanestudioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DisciplinaPlanestudio)) {
            return false;
        }
        DisciplinaPlanestudio other = (DisciplinaPlanestudio) object;
        if ((this.disciplinaPlanestudioPK == null && other.disciplinaPlanestudioPK != null) || (this.disciplinaPlanestudioPK != null && !this.disciplinaPlanestudioPK.equals(other.disciplinaPlanestudioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DisciplinaPlanestudio[ disciplinaPlanestudioPK=" + disciplinaPlanestudioPK + " ]";
    }
    
}
