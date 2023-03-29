/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author claudy
 */
@Embeddable
public class DisciplinaPlanestudioPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "disciplinadisciplina_codigo")
    private String disciplinadisciplinaCodigo;
    @Basic(optional = false)
    @Column(name = "planestudioidplanestudio")
    private int planestudioidplanestudio;

    public DisciplinaPlanestudioPK() {
    }

    public DisciplinaPlanestudioPK(String disciplinadisciplinaCodigo, int planestudioidplanestudio) {
        this.disciplinadisciplinaCodigo = disciplinadisciplinaCodigo;
        this.planestudioidplanestudio = planestudioidplanestudio;
    }

    public String getDisciplinadisciplinaCodigo() {
        return disciplinadisciplinaCodigo;
    }

    public void setDisciplinadisciplinaCodigo(String disciplinadisciplinaCodigo) {
        this.disciplinadisciplinaCodigo = disciplinadisciplinaCodigo;
    }

    public int getPlanestudioidplanestudio() {
        return planestudioidplanestudio;
    }

    public void setPlanestudioidplanestudio(int planestudioidplanestudio) {
        this.planestudioidplanestudio = planestudioidplanestudio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (disciplinadisciplinaCodigo != null ? disciplinadisciplinaCodigo.hashCode() : 0);
        hash += (int) planestudioidplanestudio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DisciplinaPlanestudioPK)) {
            return false;
        }
        DisciplinaPlanestudioPK other = (DisciplinaPlanestudioPK) object;
        if ((this.disciplinadisciplinaCodigo == null && other.disciplinadisciplinaCodigo != null) || (this.disciplinadisciplinaCodigo != null && !this.disciplinadisciplinaCodigo.equals(other.disciplinadisciplinaCodigo))) {
            return false;
        }
        if (this.planestudioidplanestudio != other.planestudioidplanestudio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DisciplinaPlanestudioPK[ disciplinadisciplinaCodigo=" + disciplinadisciplinaCodigo + ", planestudioidplanestudio=" + planestudioidplanestudio + " ]";
    }
    
}
