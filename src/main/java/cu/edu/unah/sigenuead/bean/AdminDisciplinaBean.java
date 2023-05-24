/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.Disciplina;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.service.DisciplinaServices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
@Data
public class AdminDisciplinaBean implements Serializable {

    private String codigo;
    private String nombre;
    private static String nombre1;
    private boolean cancelado = true;
    private  List<Disciplina> lista_disciplinaes = new ArrayList<Disciplina>();
    private Disciplina disciplina_selected;
    private final DisciplinaServices serv = new DisciplinaServices();

    public void init() {
        lista_disciplinaes.clear();
        lista_disciplinaes = serv.findAllDisciplina();
    }

    public void cleanVariables() {
        this.setNombre("");
        this.setCodigo("");
        this.setCancelado(true);
    }

    public void updateSelected_Disciplina(Disciplina disciplina) {
        this.setDisciplina_selected(disciplina);
        cleanVariables();
    }

    public void updateSelected_Disciplina_toDelete(Disciplina disciplina) {
        this.setDisciplina_selected(disciplina);
        nombre1 = disciplina.getDisciplinaCodigo();
    }

    public void updateSelected_Disciplina_toEdit(Disciplina disciplina) {
        disciplina_selected = disciplina;
        codigo = disciplina.getDisciplinaCodigo();
        nombre = disciplina.getDisciplinaNombre();
        nombre1 = disciplina.getDisciplinaNombre();
        cancelado = disciplina.getDisciplinaCancelada();
    }

    public void addDisciplina() {
        Par submit = serv.AddDisciplina(codigo, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('addDisciplinaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-disciplina");
    }

    public void editDisciplina() {
        Par submit = serv.EditDisciplina(nombre1, nombre, cancelado);
        FacesContext context = FacesContext.getCurrentInstance();
        init();
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
        PrimeFaces.current().executeScript("PF('editDisciplinaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-disciplina");
    }

    public void deleteDisciplina() {
//        Par submit = serv.DeleteDisciplina(nombre1);
//        FacesContext context = FacesContext.getCurrentInstance();
//        init();
//        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }

    public String translateEstado(boolean estado) {
        if (estado) {
            return "Cancelado";
        }
        return "Activo";
    }

    public String translateBooleanToSeverity(boolean element) {
        if (!element) {
            return "badge badge-success";
        }
        return "badge badge-danger";
    }




    public boolean isCancelado() {
        return !cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = !cancelado;
    }


}
