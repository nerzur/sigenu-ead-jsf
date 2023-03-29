/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.entity;


import jakarta.faces.application.FacesMessage;

/**
 *
 * @author claudia
 */
public class Par {
    //flag=1 operacion exitosa
    //flag=2 error
    //flag=3 elementos que ya esisten
    
    FacesMessage.Severity FacesType;
    String result;

    public Par(int flag, String result) {
        switch (flag){
            case 1:
                this.FacesType=FacesMessage.SEVERITY_INFO;
                break;
            case 2:
                this.FacesType=FacesMessage.SEVERITY_ERROR;
                break;
            case 3:
                this.FacesType=FacesMessage.SEVERITY_WARN;
                break;
        }
        this.result = result;
    }

    public FacesMessage.Severity getFacesType() {
        return FacesType;
    }

    public void setFacesType(FacesMessage.Severity FacesType) {
        this.FacesType = FacesType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    
}
