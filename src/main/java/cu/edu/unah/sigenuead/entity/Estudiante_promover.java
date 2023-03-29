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
public class Estudiante_promover {
    FacultadCumCarreraEstudiante fcce;
    boolean promover;

    public Estudiante_promover(FacultadCumCarreraEstudiante fcce, boolean promover) {
        this.fcce = fcce;
        this.promover = promover;
    }

    
    public FacultadCumCarreraEstudiante getFcce() {
        return fcce;
    }

    public void setFcce(FacultadCumCarreraEstudiante fcce) {
        this.fcce = fcce;
    }

    public boolean isPromover() {
        return promover;
    }

    public void setPromover(boolean promover) {
        this.promover = promover;
    }
    
    
}
