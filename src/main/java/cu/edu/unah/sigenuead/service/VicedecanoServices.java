/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.service;

import cu.edu.unah.sigenuead.controller.DisciplinaPlanestudioJpaController;
import cu.edu.unah.sigenuead.entity.Asignatura;
import cu.edu.unah.sigenuead.entity.Disciplina;
import cu.edu.unah.sigenuead.entity.DisciplinaPlanestudio;
import cu.edu.unah.sigenuead.entity.DisciplinaPlanestudioPK;
import cu.edu.unah.sigenuead.entity.Par;
import cu.edu.unah.sigenuead.entity.PlanEstudio_Disciplina;
import cu.edu.unah.sigenuead.entity.PlanEstudio_Disciplina_Asignatura;
import cu.edu.unah.sigenuead.entity.Planestudio;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import jakarta.persistence.Persistence;
import cu.edu.unah.sigenuead.util.texts;

/**
 *
 * @author claudy
 */
public class VicedecanoServices {

    PlanestudioServices pes = new PlanestudioServices();
    DisciplinaServices ds = new DisciplinaServices();
    AsignaturaServices as = new AsignaturaServices();
    MatriculaServices ms = new MatriculaServices();
    private DisciplinaPlanestudioJpaController controllerDisciplinaPlanestudio;

    public DisciplinaPlanestudioJpaController getInstanceOfDisciplinaPlanestudio() {
        return (controllerDisciplinaPlanestudio == null) ? controllerDisciplinaPlanestudio = new DisciplinaPlanestudioJpaController(Persistence.createEntityManagerFactory("dist_educ_finalPU")) : controllerDisciplinaPlanestudio;
    }

    public Par AddPlanEstudioDisciplinaAsignatura(String comentario, boolean listo, int cantoportunidades, boolean cancel, String curso, String facultad, String carrera, String tipoplan, List<PlanEstudio_Disciplina> pd, List<PlanEstudio_Disciplina_Asignatura> pda) {
        Planestudio p = pes.findPlanEstudioByCarrera(facultad, carrera, tipoplan);
        if (p == null) {
            Par pe = pes.addPlanestudio(comentario, listo, cantoportunidades, cancel, curso, facultad, carrera, tipoplan);
            if (pe.getFacesType().equals(FacesMessage.SEVERITY_ERROR)) {
                return pe;
            }
            for (int i = 0; i < pd.size(); i++) {
                Par pdis = pes.addDisciplinastoPlanEstudio(facultad, carrera, tipoplan, pd.get(i).getDisciplina(), pd.get(i).getObjetivo(), pd.get(i).getPrograma(), false);
                if (pdis.getFacesType().equals(FacesMessage.SEVERITY_ERROR)) {
                    texts.setDisciplinaPlanEstudioError(pd.get(i).getDisciplina());
                    return new Par(2, texts.getDisciplinaPlanEstudioError());
                }
            }
            for (int i = 0; i < pda.size(); i++) {
                PlanEstudio_Disciplina_Asignatura a = pda.get(i);
                Par pad = as.AddAsignatura(a.getAsignaturaCodigo(), a.getAsignaturaNombre(), a.getAsignaturaAbreviatura(), facultad, carrera, a.isAsignaturaCancelada(), a.getTipoEvaluaciontipoEvaluacionId(), a.getTipoAsignaturatipoAsignaturaId(), a.isAsignaturaPromediable(), a.isAsignaturaCertificable(), a.getDisciplina(), tipoplan, a.getAsignaturaList());
                if (pad.getFacesType().equals(FacesMessage.SEVERITY_ERROR)) {
                    texts.setDisciplinaPlanEstudioError(pd.get(i).getDisciplina());
                    return new Par(2, texts.getDisciplinaPlanEstudioError());
                }
            }
            for (int i = 0; i < pda.size(); i++) {
                PlanEstudio_Disciplina_Asignatura a = pda.get(i);
                Asignatura aux = as.findAsignaturaByCodigo(a.getAsignaturaCodigo(), facultad, carrera, tipoplan);
                Par pad = as.EditAsignatura(aux.getAsignaturaCodigo(), aux.getAsignaturaNombre(), aux.getAsignaturaAbreviatura(), facultad, carrera, aux.getAsignaturaCancelada(), aux.getTipoEvaluaciontipoEvaluacionId().getTipoEvaluacionNombre(), aux.getTipoAsignaturatipoAsignaturaId().getTipoAsignaturaNombre(), aux.getAsignaturaPromediable(), aux.getAsignaturaCertificable(), a.getDisciplina(), tipoplan, as.findAsignaturaPrecedentesByCodigo(a.getAsignaturaNombre()), a.getAsignaturaList());
                if (pad.getFacesType().equals(FacesMessage.SEVERITY_ERROR)) {
                    return new Par(2, "No se han podido establecer las relaciones de precedencia para la asignatura: " + aux.getAsignaturaCodigo() + "-" + aux.getAsignaturaNombre());
                }
            }
//            ms.relacionarAsignaturasEstudiantes(facultad, carrera, tipoplan);
            return new Par(1, texts.getSatisfactorio());
        } else {
            return new Par(2, texts.getPlanEstudioExistente());
        }
    }

    public Par EditPlanEstudioDisciplinaAsignatura(String comentario, boolean listo, int cantoportunidades, boolean cancel, String curso, String facultad, String carrera, String tipoplan, List<PlanEstudio_Disciplina> pd, List<PlanEstudio_Disciplina_Asignatura> pda) {
        Planestudio p = pes.findPlanEstudioByCarrera(facultad, carrera, tipoplan);
        if (p != null) {
            Par pe = pes.editPlanestudio(comentario, listo, cantoportunidades, cancel, curso, facultad, carrera, tipoplan);
            if (pe.getFacesType().equals(FacesMessage.SEVERITY_ERROR)) {
                return pe;
            }
            for (PlanEstudio_Disciplina pdi : pd) {
                Disciplina d = ds.findDisciplinaByNombreAvailable(pdi.getDisciplina());
                if (d == null) {
                    return new Par(2, texts.getDisciplinaNull());
                }
                DisciplinaPlanestudio discplan = getInstanceOfDisciplinaPlanestudio().findDisciplinaPlanestudio(new DisciplinaPlanestudioPK(d.getDisciplinaCodigo(), p.getIdplanestudio()));
                Par pdis = new Par(2, "Esa disciplina que intenta cancelar no existe en el plan de estudio");
                if (discplan != null) {
                    pdis = pes.editDisciplinasToPlanEstudio(facultad, carrera, tipoplan, pdi.getDisciplina(), pdi.getObjetivo(), pdi.getPrograma(), pdi.isCancelado());
                } else {
                    pdis = pes.addDisciplinastoPlanEstudio(facultad, carrera, tipoplan, pdi.getDisciplina(), pdi.getObjetivo(), pdi.getPrograma(), pdi.isCancelado());
                }
                if (pdis.getFacesType().equals(FacesMessage.SEVERITY_ERROR)) {
                    texts.setDisciplinaPlanEstudioError(pdi.getDisciplina());
                    return new Par(2, texts.getDisciplinaPlanEstudioError());
                }
                //cancela todas las asignaturas de esa disciplina en ese plan de estudio
                if (pdi.isCancelado()) {
                    List<Asignatura> asigList = as.findAsignaturaEntityByDisciplina(facultad, carrera, tipoplan, pdi.getDisciplina());
                    for (Asignatura aux : asigList) {
                        aux.setAsignaturaCancelada(true);
                        if (as.EditAsignatura(aux).getFacesType().equals(FacesMessage.SEVERITY_ERROR)) {
                            return new Par(2, "No se pudo editar la asignatura");
                        }
                    }
                }
            }
            for (PlanEstudio_Disciplina_Asignatura pdai : pda) {
                Asignatura aa = as.findAsignaturaByCodigo(pdai.getAsignaturaCodigo(), facultad, carrera, tipoplan);
                Par pad;
                if (aa == null) {
                    pad = as.AddAsignatura(pdai.getAsignaturaCodigo(), pdai.getAsignaturaNombre(), pdai.getAsignaturaAbreviatura(), facultad, carrera, pdai.isAsignaturaCancelada(), pdai.getTipoEvaluaciontipoEvaluacionId(), pdai.getTipoAsignaturatipoAsignaturaId(), pdai.isAsignaturaPromediable(), pdai.isAsignaturaCertificable(), pdai.getDisciplina(), tipoplan, pdai.getAsignaturaList());
                } else {
                    pad = as.EditAsignatura(aa.getAsignaturaCodigo(), pdai.getAsignaturaNombre(), pdai.getAsignaturaAbreviatura(), facultad, carrera, pdai.isAsignaturaCancelada(), pdai.getTipoEvaluaciontipoEvaluacionId(), pdai.getTipoAsignaturatipoAsignaturaId(), pdai.isAsignaturaPromediable(), pdai.isAsignaturaCertificable(), pdai.getDisciplina(), tipoplan, as.findAsignaturaPrecedentesByCodigo(aa.getAsignaturaCodigo()), pdai.getAsignaturaList());
                }
                if (pad.getFacesType().equals(FacesMessage.SEVERITY_ERROR)) {
                    texts.setAsignaturadisciplinaPlanEstudioError(pdai.getDisciplina());
                    return new Par(2, texts.getAsignaturadisciplinaPlanEstudioError());
                }
            }
            for (int i = 0; i < pda.size(); i++) {
                PlanEstudio_Disciplina_Asignatura a = pda.get(i);
                Asignatura aux = as.findAsignaturaByCodigo(a.getAsignaturaCodigo(), facultad, carrera, tipoplan);
                as.EditAsignatura(aux.getAsignaturaCodigo(), aux.getAsignaturaNombre(), aux.getAsignaturaAbreviatura(), facultad, carrera, aux.getAsignaturaCancelada(), aux.getTipoEvaluaciontipoEvaluacionId().getTipoEvaluacionNombre(), aux.getTipoAsignaturatipoAsignaturaId().getTipoAsignaturaNombre(), aux.getAsignaturaPromediable(), aux.getAsignaturaCertificable(), a.getDisciplina(), tipoplan, as.findAsignaturaPrecedentesByCodigo(a.getAsignaturaNombre()), a.getAsignaturaList());
            }
//            ms.relacionarAsignaturasEstudiantes(facultad, carrera, tipoplan);
            return new Par(1, texts.getSatisfactorio());
        } else {
            return new Par(2, texts.getPlanEstudioExistente());
        }
    }
}
