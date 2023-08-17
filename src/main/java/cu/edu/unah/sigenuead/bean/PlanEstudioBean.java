package cu.edu.unah.sigenuead.bean;

import cu.edu.unah.sigenuead.entity.*;
import cu.edu.unah.sigenuead.service.*;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SessionScoped
@Named
@Data
public class PlanEstudioBean implements Serializable {

    /**
     * Search elements
     */
    String area_find;
    String carrera_find;
    String plan_estudio_find;

    List<String> area_list = new ArrayList<String>();
    List<String> carrera_list = new ArrayList<String>();
    List<String> plan_estudio_list = new ArrayList<String>();

    /**
     * Wizard elements first Step
     */
    String curso_activacion;
    String facultad;
    String carrera;
    String tipo_plan_estudio;
    int cant_oportunidades;
    String comentario;
    boolean listo = true;
    boolean cancelado = false;
    String copy_carrera_value;

    List<String> facultad_list_wizzard = new ArrayList<String>();
    List<String> carrera_list_wizzard = new ArrayList<String>();
    List<String> tipo_plan_estudio_list_wizzard = new ArrayList<String>();
    List<String> curso_activacion_list = new ArrayList<String>();

    /**
     * wizard elements Second Step
     */
    String disciplina;
    String objetivo;
    String programa;
    boolean disciplina_cancelado;

    List<PlanEstudio_Disciplina> disciplina_plan_estudio_list = new ArrayList<PlanEstudio_Disciplina>();
    List<PlanEstudio_Disciplina> disciplina_plan_estudio_list1 = new ArrayList<PlanEstudio_Disciplina>();
    List<String> disciplina_list = new ArrayList<String>();
    PlanEstudio_Disciplina plan_estudio_disciplina_selected;

    /**
     * wizard elements Third Step
     */
    private String codigo_asignatura;
    private String nombre_asignatura;
    private String abreviatura_asignatura;
    private String tipo_evaluacion_asignatura;
    private String tipo_asignatura;
    private String disciplina_asignatura;
    private List<String> precedentes_asignatura = new ArrayList<String>();
    private boolean cancelado_asignatura;
    private boolean promediable_asignatura;
    private boolean certificable_asignatura;
    private PlanEstudio_Disciplina_Asignatura planEstudio_Disciplina_Asignatura_selected;

    List<String> list_asignaturas = new ArrayList<String>();
    List<PlanEstudio_Disciplina_Asignatura> asignatura_list = new ArrayList<PlanEstudio_Disciplina_Asignatura>();
    List<PlanEstudio_Disciplina_Asignatura> asignatura_list1 = new ArrayList<PlanEstudio_Disciplina_Asignatura>();
    private List<String> lista_tipo_asignatura = new ArrayList<String>();
    private List<String> lista_tipo_evaluacion = new ArrayList<String>();
    private List<String> disciplina_asignatura_list = new ArrayList<String>();

    /**
     * wizard elements fourth step
     */
    private List<Pard> confirmacion_list_asignatura = new ArrayList<Pard>();

    final FacultadServices serv_facultad = new FacultadServices();
    final CarreraServices serv_carrera = new CarreraServices();
    final PlanestudioServices serv_plan_estudio = new PlanestudioServices();
    final CursoServices serv_curso = new CursoServices();
    final TipoplanestudioServices serv_tipo_plan_estudio = new TipoplanestudioServices();
    final DisciplinaServices serv_disciplina = new DisciplinaServices();
    final AsignaturaServices serv_asignatura = new AsignaturaServices();
    private final TipoAsignaturaServices serv_tipo_asignatura = new TipoAsignaturaServices();
    private final TipoEvaluacionServices serv_tipo_evaluacion = new TipoEvaluacionServices();
    private final VicedecanoServices serv_vice_decano = new VicedecanoServices();
    private boolean founded;
    private boolean encontrado;
    private boolean duplicate;

    public void cleanVar1() {
        area_find = "";
        carrera_find = "";
        plan_estudio_find = "";
        encontrado = false;
        founded = false;
        duplicate = false;
        cleanVariables();
    }

    public void cleanVariables() {
        curso_activacion = "";
        facultad = "";
        carrera = "";
        tipo_plan_estudio = "";
        cant_oportunidades = 1;
        comentario = "";
        listo = true;
        cancelado = false;
        disciplina_plan_estudio_list.clear();
        asignatura_list.clear();
        encontrado = false;

    }

    public void cleanVariablesModal1() {
        disciplina = "";
        objetivo = "";
        programa = "";
    }

    public void cleanVariablesModal2() {
        codigo_asignatura = "";
        nombre_asignatura = "";
        abreviatura_asignatura = "";
        tipo_evaluacion_asignatura = "";
        tipo_asignatura = "";
        disciplina_asignatura = "";
        cancelado_asignatura = false;
        promediable_asignatura = false;
        certificable_asignatura = false;
    }

    public void init() {
        cleanVar1();
        cleanVariables();
        cleanVariablesModal1();
        cleanVariablesModal2();
        area_list.clear();
        area_list = serv_facultad.findFacultadAvailables();
        facultad_list_wizzard.clear();
        facultad_list_wizzard = serv_facultad.findFacultadAvailables();
        curso_activacion_list.clear();
        curso_activacion_list = serv_curso.findAllCursosCodigo();
        tipo_plan_estudio_list_wizzard.clear();
        tipo_plan_estudio_list_wizzard = serv_tipo_plan_estudio.findTipoplanestudioAvailable();
        disciplina_list.clear();
        disciplina_list = serv_disciplina.findDisciplinaAvailable();
        disciplina_plan_estudio_list.clear();
        lista_tipo_asignatura.clear();
        lista_tipo_asignatura = serv_tipo_asignatura.findTipoAsignaturaAvailable();
        lista_tipo_evaluacion.clear();
        lista_tipo_evaluacion = serv_tipo_evaluacion.findTipoEvaluacionAvailable();
        asignatura_list.clear();
    }

    public void updtate_carrera() {
        carrera_list = new ArrayList<String>();
        if (area_find != null && !area_find.equals("")) {
            carrera_list = serv_carrera.findCarreraAvailable(area_find);
        }
    }

    public void update_plan_estudio() {
        plan_estudio_list = new ArrayList<String>();
        if (area_find != null && !area_find.equals("") && carrera_find != null && !carrera_find.equals("")) {
            plan_estudio_list = serv_plan_estudio.findPlanestudioAvailableByCarrera(area_find, carrera_find);
        }
    }

    public void updtate_carrera1() {
        carrera_list_wizzard = new ArrayList<String>();
        if (facultad != null && !facultad.equals("")) {
            carrera_list_wizzard = serv_carrera.findCarreraAvailable(facultad);
        }
    }

    public void addDisciplina_tolist() {
        disciplina_plan_estudio_list.add(new PlanEstudio_Disciplina(disciplina, objetivo, programa, disciplina_cancelado));
        disciplina_list.remove(disciplina);
        cleanVariablesModal1();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha adicionado correctamente el elemento", ""));
        PrimeFaces.current().executeScript("PF('addDisciplinaDialog').hide()");
        PrimeFaces.current().ajax().update("wizzard_form:messages", "wizzard_form:dt-disciplina");
    }

    public void editDisciplina_fromlist() {
        int x = 0;
        for (int i = 0; i < disciplina_plan_estudio_list.size(); i++) {
            if (disciplina_plan_estudio_list.get(i).getDisciplina().equals(plan_estudio_disciplina_selected.getDisciplina())) {
                x = i;
                break;
            }
        }
        disciplina_plan_estudio_list.remove(x);
        disciplina_plan_estudio_list.add(new PlanEstudio_Disciplina(plan_estudio_disciplina_selected.getDisciplina(), objetivo, programa, disciplina_cancelado));
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha editado correctamente el elemento", ""));
        PrimeFaces.current().executeScript("PF('editDisciplinaDialog').hide()");
        PrimeFaces.current().ajax().update("wizzard_form:messages", "wizzard_form:dt-disciplina");
    }

    public void deleteDisciplina_fromlist() {
        int x = 0;
        for (int i = 0; i < disciplina_plan_estudio_list.size(); i++) {
            if (disciplina_plan_estudio_list.get(i).getDisciplina().equals(plan_estudio_disciplina_selected.getDisciplina())) {
                x = i;
                break;
            }
        }
        if (!asignatura_list.isEmpty()) {
            for (int i = 0; i < asignatura_list.size(); i++) {
                if (asignatura_list.get(i).getDisciplina().equals(disciplina_plan_estudio_list.get(x).getDisciplina())) {
                    asignatura_list.remove(i);
                    i--;
                }
            }
        }
        disciplina_list.add(disciplina_plan_estudio_list.get(x).getDisciplina());
        disciplina_plan_estudio_list.remove(x);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamente el elemento", ""));
    }

    public void updateSelected_PlanEstudio_Disciplina(PlanEstudio_Disciplina plan_estudio_disciplina) {
        plan_estudio_disciplina_selected = plan_estudio_disciplina;
        disciplina = plan_estudio_disciplina.getDisciplina();
        objetivo = plan_estudio_disciplina.getObjetivo();
        programa = plan_estudio_disciplina.getPrograma();
        disciplina_cancelado = plan_estudio_disciplina.isCancelado();

    }

    public void updateSelectedDisciplinaPlanEstudio_toDelete(PlanEstudio_Disciplina plan_estudio_disciplina) {
        plan_estudio_disciplina_selected = plan_estudio_disciplina;
    }

    public void updateDisciplinaAsignatura() {
        disciplina_asignatura_list.clear();
        for (PlanEstudio_Disciplina disciplina_plan_estudio_list1 : disciplina_plan_estudio_list) {
            disciplina_asignatura_list.add(disciplina_plan_estudio_list1.getDisciplina());
        }
    }

    public void addAsignatura() {
        PlanEstudio_Disciplina_Asignatura as = new PlanEstudio_Disciplina_Asignatura(disciplina_asignatura, codigo_asignatura, nombre_asignatura, abreviatura_asignatura, cancelado_asignatura, promediable_asignatura, certificable_asignatura, precedentes_asignatura, tipo_asignatura, tipo_evaluacion_asignatura);
        for (PlanEstudio_Disciplina_Asignatura asignatura_list1 : asignatura_list) {
            if (asignatura_list1.getAsignaturaCodigo().equals(as.getAsignaturaCodigo())) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya existe una Asignatura con los mismos datos", ""));
                return;
            }
        }
        asignatura_list.add(as);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha adicionado correctamente el elemento", ""));
        PrimeFaces.current().executeScript("PF('addAsignaturaDialog').hide()");
        PrimeFaces.current().ajax().update("wizzard_form:messages", "wizzard_form:dt-asignatura");
    }

    public void editAsignatura() {
        for (int i = 0; i < asignatura_list.size(); i++) {
            if (asignatura_list.get(i).getAsignaturaCodigo().equals(planEstudio_Disciplina_Asignatura_selected.getAsignaturaCodigo())) {
                asignatura_list.remove(i);
                break;
            }
        }
        asignatura_list.add(new PlanEstudio_Disciplina_Asignatura(disciplina_asignatura, planEstudio_Disciplina_Asignatura_selected.getAsignaturaCodigo(), nombre_asignatura, abreviatura_asignatura, cancelado_asignatura, promediable_asignatura, certificable_asignatura, precedentes_asignatura, tipo_asignatura, tipo_evaluacion_asignatura));
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha editado correctamente el elemento", ""));
        PrimeFaces.current().executeScript("PF('editAsignaturaDialog').hide()");
        PrimeFaces.current().ajax().update("wizzard_form:messages", "wizzard_form:dt-asignatura");
    }

    public void deleteAsignatura() {
        for (int i = 0; i < asignatura_list.size(); i++) {
            if (asignatura_list.get(i).getAsignaturaCodigo().equals(planEstudio_Disciplina_Asignatura_selected.getAsignaturaCodigo())) {
                asignatura_list.remove(i);
                for (PlanEstudio_Disciplina_Asignatura a : asignatura_list) {
                    a.getAsignaturaList().remove(planEstudio_Disciplina_Asignatura_selected.getAsignaturaNombre());
                }
                break;
            }
        }

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamente el elemento", ""));
    }

    public void updateSelectedPlanEstudio_Disciplina_Asignatura_toEdit(PlanEstudio_Disciplina_Asignatura planEstudio_Disciplina_Asignatura) {
        planEstudio_Disciplina_Asignatura_selected = planEstudio_Disciplina_Asignatura;
        codigo_asignatura = planEstudio_Disciplina_Asignatura.getAsignaturaCodigo();
        nombre_asignatura = planEstudio_Disciplina_Asignatura.getAsignaturaNombre();
        abreviatura_asignatura = planEstudio_Disciplina_Asignatura.getAsignaturaAbreviatura();
        tipo_evaluacion_asignatura = planEstudio_Disciplina_Asignatura.getTipoEvaluaciontipoEvaluacionId();
        tipo_asignatura = planEstudio_Disciplina_Asignatura.getTipoAsignaturatipoAsignaturaId();
        disciplina_asignatura = planEstudio_Disciplina_Asignatura.getDisciplina();
        cancelado_asignatura = planEstudio_Disciplina_Asignatura.isAsignaturaCancelada();
        promediable_asignatura = planEstudio_Disciplina_Asignatura.isAsignaturaPromediable();
        certificable_asignatura = planEstudio_Disciplina_Asignatura.isAsignaturaCertificable();
        precedentes_asignatura = new ArrayList<String>();
        for (String a : planEstudio_Disciplina_Asignatura.getAsignaturaList()) {
            precedentes_asignatura.add(a);
        }
    }

    public void updateConfirmacionStep() {
        confirmacion_list_asignatura.clear();
        String[] asign = new String[disciplina_plan_estudio_list.size()];
        for (int i = 0; i < disciplina_plan_estudio_list.size(); i++) {
            confirmacion_list_asignatura.add(new Pard(disciplina_plan_estudio_list.get(i), new ArrayList<PlanEstudio_Disciplina_Asignatura>()));
            asign[i] = disciplina_plan_estudio_list.get(i).getDisciplina();
        }
        Arrays.sort(asign);
        Collections.sort(confirmacion_list_asignatura);
        for (int i = 0; i < asignatura_list.size(); i++) {
            int pos = Arrays.binarySearch(asign, asignatura_list.get(i).getDisciplina());
            List<PlanEstudio_Disciplina_Asignatura> temp = confirmacion_list_asignatura.get(pos).list_asignatura;
            PlanEstudio_Disciplina temp_disc = confirmacion_list_asignatura.get(pos).disciplina;
            temp.add(asignatura_list.get(i));
            confirmacion_list_asignatura.set(pos, new Pard(temp_disc, temp));
        }

    }

    public void updateAsignaturasList() {
        list_asignaturas.clear();
        if (facultad != null && !facultad.equals("") && carrera != null && !carrera.equals("") && tipo_plan_estudio != null && !tipo_plan_estudio.equals("") && disciplina_asignatura != null && !disciplina_asignatura.equals("")) {
            for (PlanEstudio_Disciplina_Asignatura p : asignatura_list) {
                if (p.getDisciplina().equals(disciplina_asignatura)) {
                    list_asignaturas.add(p.getAsignaturaNombre());
                }
            }
        }
    }

    public void updateAsignaturasListEdit() {
        list_asignaturas = new ArrayList<String>();
//        precedentes_asignatura.clear();
        if (facultad != null && !facultad.equals("") && carrera != null && !carrera.equals("") && tipo_plan_estudio != null && !tipo_plan_estudio.equals("") && disciplina_asignatura != null && !disciplina_asignatura.equals("")) {
            for (PlanEstudio_Disciplina_Asignatura p : asignatura_list) {
                if (p.getDisciplina().equals(disciplina_asignatura) && nombre_asignatura != null && !nombre_asignatura.equals(p.getAsignaturaNombre())) {
                    list_asignaturas.add(p.getAsignaturaNombre());
                }
            }
            for (String a : planEstudio_Disciplina_Asignatura_selected.getAsignaturaList()) {
                if (list_asignaturas.contains(a)) {
                    precedentes_asignatura.add(a);
                }
            }
        }

    }

    public void addplanEstudio() {
        FacesContext context = FacesContext.getCurrentInstance();
        Par submit = new Par(1, "");
        if (!founded) {
            submit = serv_vice_decano.AddPlanEstudioDisciplinaAsignatura(comentario, listo, cant_oportunidades, cancelado, curso_activacion, facultad, carrera, tipo_plan_estudio, disciplina_plan_estudio_list, asignatura_list);
        } else {
            submit = serv_vice_decano.EditPlanEstudioDisciplinaAsignatura(comentario, listo, cant_oportunidades, cancelado, curso_activacion, facultad, carrera, tipo_plan_estudio, disciplina_plan_estudio_list, asignatura_list);
            //submit = serv_vice_decano.EditPlanEstudioDisciplinaAsignatura(comentario, listo, cant_oportunidades, cancelado, curso_activacion, facultad, carrera, tipo_plan_estudio, disciplina_plan_estudio_list, asignatura_list, disciplina_plan_estudio_list1, asignatura_list1);
        }
        context.addMessage(null, new FacesMessage(submit.getFacesType(), submit.getResult(), ""));
    }

    public void findPlanEstudioExiste() {
        if (!duplicate && facultad != null && !facultad.equals("") && copy_carrera_value != null && !copy_carrera_value.equals("") && tipo_plan_estudio != null && !tipo_plan_estudio.equals("")) {
            if (!founded) {
                Planestudio p = serv_plan_estudio.findPlanEstudioByCarrera(facultad, copy_carrera_value, tipo_plan_estudio);
                encontrado = p != null;
                if (encontrado) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existe ya un Plan de Estudio con esos datos, se ha inhabilitado la posibilidad de añadir y modificar datos", "Error"));
                }
            }
        }
    }

    public void copyvalueCarrera() {
        copy_carrera_value = carrera;
    }

    public void search() {
        cleanVariables();
        duplicate = false;
        Planestudio p = serv_plan_estudio.findPlanEstudioByCarrera(area_find, carrera_find, plan_estudio_find);
        if (p == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe un Plan de Estudio en el sistema con esos Datos", "Error"));
            founded = false;
            return;
        }
        founded = true;
        curso_activacion = p.getCursoactivacion().getIdcurso();
        facultad = p.getCarreraidcarrera().getFacultadcodigoarea().getNombrearea();
        carrera = p.getCarreraidcarrera().getCarreranacionalidcarreranacional().getNombrecarreranacional();
        tipo_plan_estudio = p.getTipoplanestudionombretipoplanestudio().getNombretipoplanestudio();
        cant_oportunidades = p.getCantoportunidades();
        comentario = p.getPlanEstudioComentario();
        listo = p.getPlanEstudioListo();
        cancelado = p.getPlanEstudioCancelado();

        List<DisciplinaPlanestudio> dp = p.getDisciplinaPlanestudioList();
        for (DisciplinaPlanestudio dp1 : dp) {
            disciplina_plan_estudio_list.add(new PlanEstudio_Disciplina(dp1.getDisciplina().getDisciplinaNombre(), dp1.getObjetivos(), dp1.getPrograma(), dp1.getCancelado()));
            disciplina_plan_estudio_list1.add(new PlanEstudio_Disciplina(dp1.getDisciplina().getDisciplinaNombre(), dp1.getObjetivos(), dp1.getPrograma(), dp1.getCancelado()));
            for (Asignatura a : dp1.getAsignaturaList()) {
                //List<String> x = serv_asignatura.findAsignaturaPrecedentes(facultad, carrera, tipo_plan_estudio, disciplina, a.getAsignaturaNombre());
                List<String> x = serv_asignatura.findAsignaturaPrecedentesByCodigo(a.getAsignaturaCodigo());
                asignatura_list.add(new PlanEstudio_Disciplina_Asignatura(dp1.getDisciplina().getDisciplinaNombre(), a.getAsignaturaCodigo(), a.getAsignaturaNombre(), a.getAsignaturaAbreviatura(), a.getAsignaturaCancelada(), a.getAsignaturaPromediable(), a.getAsignaturaCertificable(), x, a.getTipoAsignaturatipoAsignaturaId().getTipoAsignaturaNombre(), a.getTipoEvaluaciontipoEvaluacionId().getTipoEvaluacionNombre()));
                asignatura_list1.add(new PlanEstudio_Disciplina_Asignatura(dp1.getDisciplina().getDisciplinaNombre(), a.getAsignaturaCodigo(), a.getAsignaturaNombre(), a.getAsignaturaAbreviatura(), a.getAsignaturaCancelada(), a.getAsignaturaPromediable(), a.getAsignaturaCertificable(), x, a.getTipoAsignaturatipoAsignaturaId().getTipoAsignaturaNombre(), a.getTipoEvaluaciontipoEvaluacionId().getTipoEvaluacionNombre()));
            }
        }

        for (PlanEstudio_Disciplina dp1 : disciplina_plan_estudio_list) {
            disciplina_list.remove(dp1.getDisciplina());
        }

    }

    public void duplicate(){
        founded = false;
        duplicate = true;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos duplicados", "INFO"));

    }

    public String updateButtom() {
        if (founded) {
            return "Actualizar";
        }
        return "Adicionar";
    }

    public boolean isCancelado() {
        return !cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = !cancelado;
    }

    public boolean isDisciplina_cancelado() {
        return !disciplina_cancelado;
    }

    public void setDisciplina_cancelado(boolean disciplina_cancelado) {
        this.disciplina_cancelado = !disciplina_cancelado;
    }

    public boolean isCancelado_asignatura() {
        return !cancelado_asignatura;
    }

    public void setCancelado_asignatura(boolean cancelado_asignatura) {
        this.cancelado_asignatura = !cancelado_asignatura;
    }

    public String translate_estado(boolean estado) {
        if (!estado) {
            return "Cancelado";
        }
        return "Activo";
    }

    public String translate_estado1(boolean estado) {
        if (estado) {
            return "Cancelado";
        }
        return "Activo";
    }

    public String translateBooleanToSeverity(boolean element) {
        if (element) {
            return "badge badge-success";
        }
        return "badge badge-danger";
    }

    public String translateBooleanToSeverity1(boolean element) {
        if (element) {
            return "badge badge-success";
        }
        return "badge badge-danger";
    }

    public String translatePromediable(boolean estado) {
        if (!estado) {
            return "No promediable";
        }
        return "Promediable";
    }

    public String translateCertificable(boolean estado) {
        if (!estado) {
            return "No Certificable";
        }
        return "Certificable";
    }

    public String translate_listo(boolean estado) {
        if (!estado) {
            return "Pendiente";
        }
        return "Listo";
    }
}
