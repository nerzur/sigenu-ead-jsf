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
@Table(name = "planestudio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planestudio.findAll", query = "SELECT p FROM Planestudio p"),
    @NamedQuery(name = "Planestudio.findByIdplanestudio", query = "SELECT p FROM Planestudio p WHERE p.idplanestudio = :idplanestudio"),
    @NamedQuery(name = "Planestudio.findByPlanEstudioComentario", query = "SELECT p FROM Planestudio p WHERE p.planEstudioComentario = :planEstudioComentario"),
    @NamedQuery(name = "Planestudio.findByPlanEstudioListo", query = "SELECT p FROM Planestudio p WHERE p.planEstudioListo = :planEstudioListo"),
    @NamedQuery(name = "Planestudio.findByPlanEstudioCancelado", query = "SELECT p FROM Planestudio p WHERE p.planEstudioCancelado = :planEstudioCancelado"),
    @NamedQuery(name = "Planestudio.findByCantoportunidades", query = "SELECT p FROM Planestudio p WHERE p.cantoportunidades = :cantoportunidades")})
public class Planestudio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplanestudio")
    private Integer idplanestudio;
    @Basic(optional = false)
    @Column(name = "plan_estudio_comentario")
    private String planEstudioComentario;
    @Basic(optional = false)
    @Column(name = "plan_estudio_listo")
    private boolean planEstudioListo;
    @Basic(optional = false)
    @Column(name = "plan_estudio_cancelado")
    private boolean planEstudioCancelado;
    @Basic(optional = false)
    @Column(name = "cantoportunidades")
    private int cantoportunidades;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planestudioidplanestudio")
    private List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteList;
    @JoinColumn(name = "carreraidcarrera", referencedColumnName = "idcarrera")
    @ManyToOne(optional = false)
    private Carrera carreraidcarrera;
    @JoinColumn(name = "cursoactivacion", referencedColumnName = "idcurso")
    @ManyToOne(optional = false)
    private Curso cursoactivacion;
    @JoinColumn(name = "tipoplanestudionombretipoplanestudio", referencedColumnName = "nombretipoplanestudio")
    @ManyToOne(optional = false)
    private Tipoplanestudio tipoplanestudionombretipoplanestudio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planestudio")
    private List<DisciplinaPlanestudio> disciplinaPlanestudioList;

    public Planestudio() {
    }

    public Planestudio(Integer idplanestudio) {
        this.idplanestudio = idplanestudio;
    }

    public Planestudio(Integer idplanestudio, String planEstudioComentario, boolean planEstudioListo, boolean planEstudioCancelado, int cantoportunidades) {
        this.idplanestudio = idplanestudio;
        this.planEstudioComentario = planEstudioComentario;
        this.planEstudioListo = planEstudioListo;
        this.planEstudioCancelado = planEstudioCancelado;
        this.cantoportunidades = cantoportunidades;
    }

    public Integer getIdplanestudio() {
        return idplanestudio;
    }

    public void setIdplanestudio(Integer idplanestudio) {
        this.idplanestudio = idplanestudio;
    }

    public String getPlanEstudioComentario() {
        return planEstudioComentario;
    }

    public void setPlanEstudioComentario(String planEstudioComentario) {
        this.planEstudioComentario = planEstudioComentario;
    }

    public boolean getPlanEstudioListo() {
        return planEstudioListo;
    }

    public void setPlanEstudioListo(boolean planEstudioListo) {
        this.planEstudioListo = planEstudioListo;
    }

    public boolean getPlanEstudioCancelado() {
        return planEstudioCancelado;
    }

    public void setPlanEstudioCancelado(boolean planEstudioCancelado) {
        this.planEstudioCancelado = planEstudioCancelado;
    }

    public int getCantoportunidades() {
        return cantoportunidades;
    }

    public void setCantoportunidades(int cantoportunidades) {
        this.cantoportunidades = cantoportunidades;
    }

    @XmlTransient
    public List<FacultadCumCarreraEstudiante> getFacultadCumCarreraEstudianteList() {
        return facultadCumCarreraEstudianteList;
    }

    public void setFacultadCumCarreraEstudianteList(List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteList) {
        this.facultadCumCarreraEstudianteList = facultadCumCarreraEstudianteList;
    }

    public Carrera getCarreraidcarrera() {
        return carreraidcarrera;
    }

    public void setCarreraidcarrera(Carrera carreraidcarrera) {
        this.carreraidcarrera = carreraidcarrera;
    }

    public Curso getCursoactivacion() {
        return cursoactivacion;
    }

    public void setCursoactivacion(Curso cursoactivacion) {
        this.cursoactivacion = cursoactivacion;
    }

    public Tipoplanestudio getTipoplanestudionombretipoplanestudio() {
        return tipoplanestudionombretipoplanestudio;
    }

    public void setTipoplanestudionombretipoplanestudio(Tipoplanestudio tipoplanestudionombretipoplanestudio) {
        this.tipoplanestudionombretipoplanestudio = tipoplanestudionombretipoplanestudio;
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
        hash += (idplanestudio != null ? idplanestudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planestudio)) {
            return false;
        }
        Planestudio other = (Planestudio) object;
        if ((this.idplanestudio == null && other.idplanestudio != null) || (this.idplanestudio != null && !this.idplanestudio.equals(other.idplanestudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Planestudio[ idplanestudio=" + idplanestudio + " ]";
    }

    public Planestudio(String planEstudioComentario, boolean planEstudioListo, boolean planEstudioCancelado, int cantoportunidades, Carrera carreraidcarrera, Curso cursoactivacion, Tipoplanestudio tipoplanestudionombretipoplanestudio) {
        this.planEstudioComentario = planEstudioComentario;
        this.planEstudioListo = planEstudioListo;
        this.planEstudioCancelado = planEstudioCancelado;
        this.cantoportunidades = cantoportunidades;
        this.carreraidcarrera = carreraidcarrera;
        this.cursoactivacion = cursoactivacion;
        this.tipoplanestudionombretipoplanestudio = tipoplanestudionombretipoplanestudio;
    }
}
