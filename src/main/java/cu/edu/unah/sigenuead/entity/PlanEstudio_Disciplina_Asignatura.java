/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.entity;

import java.util.List;

/**
 *
 * @author claudy
 */
public class PlanEstudio_Disciplina_Asignatura {
    String disciplina;
    String asignaturaCodigo;
    String asignaturaNombre;
    String asignaturaAbreviatura;
    boolean asignaturaCancelada;
    boolean asignaturaPromediable;
    boolean asignaturaCertificable;
    private List<String> asignaturaList;
    String tipoAsignaturatipoAsignaturaId;
    String tipoEvaluaciontipoEvaluacionId;

    public PlanEstudio_Disciplina_Asignatura(String disciplina, String asignaturaCodigo, String asignaturaNombre, String asignaturaAbreviatura, boolean asignaturaCancelada, boolean asignaturaPromediable, boolean asignaturaCertificable, List<String> asignaturaList, String tipoAsignaturatipoAsignaturaId, String tipoEvaluaciontipoEvaluacionId) {
        this.disciplina = disciplina;
        this.asignaturaCodigo = asignaturaCodigo;
        this.asignaturaNombre = asignaturaNombre;
        this.asignaturaAbreviatura = asignaturaAbreviatura;
        this.asignaturaCancelada = asignaturaCancelada;
        this.asignaturaPromediable = asignaturaPromediable;
        this.asignaturaCertificable = asignaturaCertificable;
        this.asignaturaList = asignaturaList;
        this.tipoAsignaturatipoAsignaturaId = tipoAsignaturatipoAsignaturaId;
        this.tipoEvaluaciontipoEvaluacionId = tipoEvaluaciontipoEvaluacionId;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getAsignaturaCodigo() {
        return asignaturaCodigo;
    }

    public void setAsignaturaCodigo(String asignaturaCodigo) {
        this.asignaturaCodigo = asignaturaCodigo;
    }

    public String getAsignaturaNombre() {
        return asignaturaNombre;
    }

    public void setAsignaturaNombre(String asignaturaNombre) {
        this.asignaturaNombre = asignaturaNombre;
    }

    public String getAsignaturaAbreviatura() {
        return asignaturaAbreviatura;
    }

    public void setAsignaturaAbreviatura(String asignaturaAbreviatura) {
        this.asignaturaAbreviatura = asignaturaAbreviatura;
    }

    public boolean isAsignaturaCancelada() {
        return asignaturaCancelada;
    }

    public void setAsignaturaCancelada(boolean asignaturaCancelada) {
        this.asignaturaCancelada = asignaturaCancelada;
    }

    public boolean isAsignaturaPromediable() {
        return asignaturaPromediable;
    }

    public void setAsignaturaPromediable(boolean asignaturaPromediable) {
        this.asignaturaPromediable = asignaturaPromediable;
    }

    public boolean isAsignaturaCertificable() {
        return asignaturaCertificable;
    }

    public void setAsignaturaCertificable(boolean asignaturaCertificable) {
        this.asignaturaCertificable = asignaturaCertificable;
    }

    public List<String> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<String> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    public String getTipoAsignaturatipoAsignaturaId() {
        return tipoAsignaturatipoAsignaturaId;
    }

    public void setTipoAsignaturatipoAsignaturaId(String tipoAsignaturatipoAsignaturaId) {
        this.tipoAsignaturatipoAsignaturaId = tipoAsignaturatipoAsignaturaId;
    }

    public String getTipoEvaluaciontipoEvaluacionId() {
        return tipoEvaluaciontipoEvaluacionId;
    }

    public void setTipoEvaluaciontipoEvaluacionId(String tipoEvaluaciontipoEvaluacionId) {
        this.tipoEvaluaciontipoEvaluacionId = tipoEvaluaciontipoEvaluacionId;
    }
    
    
}
