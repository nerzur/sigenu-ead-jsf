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
public class Pard implements Comparable<Pard> {

    public PlanEstudio_Disciplina disciplina;
    public List<PlanEstudio_Disciplina_Asignatura> list_asignatura;

    public Pard(PlanEstudio_Disciplina disciplina, List<PlanEstudio_Disciplina_Asignatura> list_asignatura) {
        this.disciplina = disciplina;
        this.list_asignatura = list_asignatura;
    }

    public PlanEstudio_Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(PlanEstudio_Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<PlanEstudio_Disciplina_Asignatura> getList_asignatura() {
        return list_asignatura;
    }

    public void setList_asignatura(List<PlanEstudio_Disciplina_Asignatura> list_asignatura) {
        this.list_asignatura = list_asignatura;
    }

    @Override
    public int compareTo(Pard o) {
        return this.disciplina.getDisciplina().compareTo(o.disciplina.getDisciplina());
    }
}
