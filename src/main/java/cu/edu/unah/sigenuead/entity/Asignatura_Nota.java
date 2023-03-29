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
public class Asignatura_Nota {
    Asignatura a;
    int nota;
    Matricula m;

    public Asignatura_Nota(Asignatura a, int nota, Matricula m) {
        this.a = a;
        this.nota = nota;
        this.m = m;
    }

    public Asignatura getA() {
        return a;
    }

    public void setA(Asignatura a) {
        this.a = a;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Matricula getM() {
        return m;
    }

    public void setM(Matricula m) {
        this.m = m;
    }
    

   
}
