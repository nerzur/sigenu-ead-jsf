/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.entity;

/**
 *
 * @author claudy
 */
public class PlanEstudio_Disciplina {
    String disciplina;
    String objetivo;
    String programa;
    boolean cancelado;

    public PlanEstudio_Disciplina(String disciplina, String objetivo, String programa, boolean cancelado) {
        this.disciplina = disciplina;
        this.objetivo = objetivo;
        this.programa = programa;
        this.cancelado = cancelado;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    } 
}
