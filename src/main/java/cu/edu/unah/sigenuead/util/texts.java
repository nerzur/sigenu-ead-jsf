/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.util;

/**
 *
 * @author claudy
 */
public class texts {

    static String satisfactorio = "La operacion se ha realizado satisfactoriamente";
    static String informacion = "El elemento ya existe";
    static String error = "La operacion no se ha podido realizar";
    static String noedit = "El elemento no puede ser editado";
    static String univMunic = "En ese municipio ya existe una universidad";
    static String cumMunic = "En ese municipio ya existe un CUM";
    static String asignaturaNull = "Esa asignatura no existe";
    static String authoritiesPKNull = "Ese usuario no tiene ese rol";
    static String CarreraNull = "Esa carrera no existe";
    static String CarreraCursoNull = "En ese curso esa carrera no existe";
    static String CarreraNacionalNull = "Esa carrera nacional no existe";
    static String ColorPielNull = "Ese color de piel no existe";
    static String CumNull = "Ese cum no existe";
    static String CursoNull = "Ese curso no existe";
    static String DisciplinaNull = "Esa disciplina no existe";
    static String DisciplinaPlanEstudioNull = "Ese plan de estudio no posee esa disciplina";
    static String EspecialidadNull = "Esa especialidad no existe";
    static String EspeciladadMilitarNull = "Esa especialidad militar no existe";
    static String EstadoCivilNull = "Ese estado civil no existe";
    static String EstadoEstudianteNull = "Ese estado del estudiante no existe";
    static String EstudianteNull = "Ese estudiante no existe";
    static String EstudianteActivoNull = "Ese estudiante no esta activo";
    static String ExamenNull = "Ese examen no existe";
    static String FacultadNull = "Esa facultad no existe";
    static String FacultadCumNull = "Esa facultad no tiene carreras en ese cum";
    static String FuenteIngresoNull = "Esa fuente de ingreso no existe";
    static String GradoMilitarNull = "Ese grado militar no existe";
    static String HuerfanoNull = "Ese tipo de orfandad no existe";
    static String MatriculaNull = "Esa matricula no existe";
    static String MatriculaEstudianteAsignaturaNull = "Ese estudiante no ha matriculado esa asignatura en ese momento";
    static String MatriculaEstudianteAsignaturaExamenNull = "Ese estudiante no ha realizado ese examen para esa asignatura en ese momento";
    static String MinusvaliaNull = "Ese tipo de minusvalia no existe";
    static String MunicipioNull = "Ese municipio no existe";
    static String NivelEscolarNull = "Ese nivel escolar no existe";
    static String OcupacionNull = "Esa ocupacion no existe";
    static String OngNull = "Esa ONG no existe";
    static String OrganismoNull = "Ese organismo no existe";
    static String OrganizacionPopularNull = "Esa organizacion popular no existe";
    static String OrganizacionPoliticaNull = "Esa organizacion politica no existe";
    static String PaisNull = "Ese pais no existe";
    static String PlanEstudioNull = "Ese plan de estudio no existe";
    static String PlanEstudioExistente = "Ese plan de estudio ya existe";
    static String ProcedenciaEscolarNull = "Esa procedencia escolar no existe";
    static String ProvinciaNull = "Esa provincia no existe";
    static String SexoNull = "Ese sexo no existe";
    static String SindicatoNull = "Ese sindicato no existe";
    static String TipoAsignaturaNull = "Ese tipo de asignatura no existe";
    static String TipoEvaluacionNull = "Ese tipo de evaluacion no existe";
    static String TipoMilitarNull = "Ese tipo militar no existe";
    static String TipoPlanEstudioNull = "Ese tipo de plan de estudio no existe";
    static String UniversidadNull = "Esa universidad no existe";
    static String UsuarioNull = "Ese usuario no existe";
    static String CodigoExistente = "Ese codigo ya existe";
    static String editarExistente = "No puede editar este elemento porque los datos corresponden a otro ya existente";
    static String UniversidadActiva = "Debe haber una universidad activa";
    static String fechasIncorrectas = "Las fechas no cumplen con las reglas establecidas";
    static String userIdentificaion = "Ya existe un usuario con esa identificacion";
    static String disciplinaPlanEstudioError = "No se pudo añadir la disciplina ";
    static String disciplinaPlanEstudioErroraux = "No se pudo añadir la disciplina ";
    static String asignaturadisciplinaPlanEstudioError = "No se pudo añadir la asignatura ";
    static String asignaturadisciplinaPlanEstudioErroraux = "No se pudo añadir la asignatura ";

    public static String getPlanEstudioExistente() {
        return PlanEstudioExistente;
    }

    public static void setPlanEstudioExistente(String PlanEstudioExistente) {
        texts.PlanEstudioExistente = PlanEstudioExistente;
    }

    
    public static String getAsignaturadisciplinaPlanEstudioError() {
        return asignaturadisciplinaPlanEstudioErroraux;
    }

    public static void setAsignaturadisciplinaPlanEstudioError(String asignaturadisciplinaPlanEstudioError) {
        texts.asignaturadisciplinaPlanEstudioErroraux = texts.asignaturadisciplinaPlanEstudioError+asignaturadisciplinaPlanEstudioError;
    }

    
    public static String getDisciplinaPlanEstudioError() {
        return disciplinaPlanEstudioErroraux;
    }

    public static void setDisciplinaPlanEstudioError(String disciplinaPlanEstudioError) {
        texts.disciplinaPlanEstudioErroraux = texts.disciplinaPlanEstudioError + disciplinaPlanEstudioError;
    }

    public static String getUserIdentificaion() {
        return userIdentificaion;
    }

    public static void setUserIdentificaion(String userIdentificaion) {
        texts.userIdentificaion = userIdentificaion;
    }

    public static String getEstudianteActivoNull() {
        return EstudianteActivoNull;
    }

    public static void setEstudianteActivoNull(String EstudianteActivoNull) {
        texts.EstudianteActivoNull = EstudianteActivoNull;
    }

    public static String getFechasIncorrectas() {
        return fechasIncorrectas;
    }

    public static void setFechasIncorrectas(String fechasIncorrectas) {
        texts.fechasIncorrectas = fechasIncorrectas;
    }

    public static String getUniversidadActiva() {
        return UniversidadActiva;
    }

    public static void setUniversidadActiva(String UniversidadActiva) {
        texts.UniversidadActiva = UniversidadActiva;
    }

    public static String getEditarExistente() {
        return editarExistente;
    }

    public static void setEditarExistente(String editarExistente) {
        texts.editarExistente = editarExistente;
    }

    public static String getCumMunic() {
        return cumMunic;
    }

    public static void setCumMunic(String cumMunic) {
        texts.cumMunic = cumMunic;
    }

    public static String getCodigoExistente() {
        return CodigoExistente;
    }

    public static void setCodigoExistente(String CodigoExistente) {
        texts.CodigoExistente = CodigoExistente;
    }

    public static String getUnivMunic() {
        return univMunic;
    }

    public static void setUnivMunic(String univMunic) {
        texts.univMunic = univMunic;
    }

    public static String getSatisfactorio() {
        return satisfactorio;
    }

    public static String getNoedit() {
        return noedit;
    }

    public static void setNoedit(String noedit) {
        texts.noedit = noedit;
    }

    public static void setSatisfactorio(String satisfactorio) {
        satisfactorio = satisfactorio;
    }

    public static String getInformacion() {
        return informacion;
    }

    public static void setInformacion(String informacion) {
        informacion = informacion;
    }

    public static String getError() {
        return error;
    }

    public static void setError(String error) {
        error = error;
    }

    public static String getAsignaturaNull() {
        return asignaturaNull;
    }

    public static void setAsignaturaNull(String asignaturaNull) {
        texts.asignaturaNull = asignaturaNull;
    }

    public static String getAuthoritiesPKNull() {
        return authoritiesPKNull;
    }

    public static void setAuthoritiesPKNull(String authoritiesPKNull) {
        texts.authoritiesPKNull = authoritiesPKNull;
    }

    public static String getCarreraNull() {
        return CarreraNull;
    }

    public static void setCarreraNull(String CarreraNull) {
        texts.CarreraNull = CarreraNull;
    }

    public static String getCarreraCursoNull() {
        return CarreraCursoNull;
    }

    public static void setCarreraCursoNull(String CarreraCursoNull) {
        texts.CarreraCursoNull = CarreraCursoNull;
    }

    public static String getCarreraNacionalNull() {
        return CarreraNacionalNull;
    }

    public static void setCarreraNacionalNull(String CarreraNacionalNull) {
        texts.CarreraNacionalNull = CarreraNacionalNull;
    }

    public static String getColorPielNull() {
        return ColorPielNull;
    }

    public static void setColorPielNull(String ColorPielNull) {
        texts.ColorPielNull = ColorPielNull;
    }

    public static String getCumNull() {
        return CumNull;
    }

    public static void setCumNull(String CumNull) {
        texts.CumNull = CumNull;
    }

    public static String getCursoNull() {
        return CursoNull;
    }

    public static void setCursoNull(String CursoNull) {
        texts.CursoNull = CursoNull;
    }

    public static String getDisciplinaNull() {
        return DisciplinaNull;
    }

    public static void setDisciplinaNull(String DisciplinaNull) {
        texts.DisciplinaNull = DisciplinaNull;
    }

    public static String getDisciplinaPlanEstudioNull() {
        return DisciplinaPlanEstudioNull;
    }

    public static void setDisciplinaPlanEstudioNull(String DisciplinaPlanEstudioNull) {
        texts.DisciplinaPlanEstudioNull = DisciplinaPlanEstudioNull;
    }

    public static String getEspecialidadNull() {
        return EspecialidadNull;
    }

    public static void setEspecialidadNull(String EspecialidadNull) {
        texts.EspecialidadNull = EspecialidadNull;
    }

    public static String getEspeciladadMilitarNull() {
        return EspeciladadMilitarNull;
    }

    public static void setEspeciladadMilitarNull(String EspeciladadMilitarNull) {
        texts.EspeciladadMilitarNull = EspeciladadMilitarNull;
    }

    public static String getEstadoCivilNull() {
        return EstadoCivilNull;
    }

    public static void setEstadoCivilNull(String EstadoCivilNull) {
        texts.EstadoCivilNull = EstadoCivilNull;
    }

    public static String getEstadoEstudianteNull() {
        return EstadoEstudianteNull;
    }

    public static void setEstadoEstudianteNull(String EstadoEstudianteNull) {
        texts.EstadoEstudianteNull = EstadoEstudianteNull;
    }

    public static String getEstudianteNull() {
        return EstudianteNull;
    }

    public static void setEstudianteNull(String EstudianteNull) {
        texts.EstudianteNull = EstudianteNull;
    }

    public static String getExamenNull() {
        return ExamenNull;
    }

    public static void setExamenNull(String ExamenNull) {
        texts.ExamenNull = ExamenNull;
    }

    public static String getFacultadNull() {
        return FacultadNull;
    }

    public static void setFacultadNull(String FacultadNull) {
        texts.FacultadNull = FacultadNull;
    }

    public static String getFacultadCumNull() {
        return FacultadCumNull;
    }

    public static void setFacultadCumNull(String FacultadCumNull) {
        texts.FacultadCumNull = FacultadCumNull;
    }

    public static String getFuenteIngresoNull() {
        return FuenteIngresoNull;
    }

    public static void setFuenteIngresoNull(String FuenteIngresoNull) {
        texts.FuenteIngresoNull = FuenteIngresoNull;
    }

    public static String getGradoMilitarNull() {
        return GradoMilitarNull;
    }

    public static void setGradoMilitarNull(String GradoMilitarNull) {
        texts.GradoMilitarNull = GradoMilitarNull;
    }

    public static String getHuerfanoNull() {
        return HuerfanoNull;
    }

    public static void setHuerfanoNull(String HuerfanoNull) {
        texts.HuerfanoNull = HuerfanoNull;
    }

    public static String getMatriculaNull() {
        return MatriculaNull;
    }

    public static void setMatriculaNull(String MatriculaNull) {
        texts.MatriculaNull = MatriculaNull;
    }

    public static String getMatriculaEstudianteAsignaturaNull() {
        return MatriculaEstudianteAsignaturaNull;
    }

    public static void setMatriculaEstudianteAsignaturaNull(String MatriculaEstudianteAsignaturaNull) {
        texts.MatriculaEstudianteAsignaturaNull = MatriculaEstudianteAsignaturaNull;
    }

    public static String getMatriculaEstudianteAsignaturaExamenNull() {
        return MatriculaEstudianteAsignaturaExamenNull;
    }

    public static void setMatriculaEstudianteAsignaturaExamenNull(String MatriculaEstudianteAsignaturaExamenNull) {
        texts.MatriculaEstudianteAsignaturaExamenNull = MatriculaEstudianteAsignaturaExamenNull;
    }

    public static String getMinusvaliaNull() {
        return MinusvaliaNull;
    }

    public static void setMinusvaliaNull(String MinusvaliaNull) {
        texts.MinusvaliaNull = MinusvaliaNull;
    }

    public static String getMunicipioNull() {
        return MunicipioNull;
    }

    public static void setMunicipioNull(String MunicipioNull) {
        texts.MunicipioNull = MunicipioNull;
    }

    public static String getNivelEscolarNull() {
        return NivelEscolarNull;
    }

    public static void setNivelEscolarNull(String NivelEscolarNull) {
        texts.NivelEscolarNull = NivelEscolarNull;
    }

    public static String getOcupacionNull() {
        return OcupacionNull;
    }

    public static void setOcupacionNull(String OcupacionNull) {
        texts.OcupacionNull = OcupacionNull;
    }

    public static String getOngNull() {
        return OngNull;
    }

    public static void setOngNull(String OngNull) {
        texts.OngNull = OngNull;
    }

    public static String getOrganismoNull() {
        return OrganismoNull;
    }

    public static void setOrganismoNull(String OrganismoNull) {
        texts.OrganismoNull = OrganismoNull;
    }

    public static String getOrganizacionPopularNull() {
        return OrganizacionPopularNull;
    }

    public static void setOrganizacionPopularNull(String OrganizacionPopularNull) {
        texts.OrganizacionPopularNull = OrganizacionPopularNull;
    }

    public static String getOrganizacionPoliticaNull() {
        return OrganizacionPoliticaNull;
    }

    public static void setOrganizacionPoliticaNull(String OrganizacionPoliticaNull) {
        texts.OrganizacionPoliticaNull = OrganizacionPoliticaNull;
    }

    public static String getPaisNull() {
        return PaisNull;
    }

    public static void setPaisNull(String PaisNull) {
        texts.PaisNull = PaisNull;
    }

    public static String getPlanEstudioNull() {
        return PlanEstudioNull;
    }

    public static void setPlanEstudioNull(String PlanEstudioNull) {
        texts.PlanEstudioNull = PlanEstudioNull;
    }

    public static String getProcedenciaEscolarNull() {
        return ProcedenciaEscolarNull;
    }

    public static void setProcedenciaEscolarNull(String ProcedenciaEscolarNull) {
        texts.ProcedenciaEscolarNull = ProcedenciaEscolarNull;
    }

    public static String getProvinciaNull() {
        return ProvinciaNull;
    }

    public static void setProvinciaNull(String ProvinciaNull) {
        texts.ProvinciaNull = ProvinciaNull;
    }

    public static String getSexoNull() {
        return SexoNull;
    }

    public static void setSexoNull(String SexoNull) {
        texts.SexoNull = SexoNull;
    }

    public static String getSindicatoNull() {
        return SindicatoNull;
    }

    public static void setSindicatoNull(String SindicatoNull) {
        texts.SindicatoNull = SindicatoNull;
    }

    public static String getTipoAsignaturaNull() {
        return TipoAsignaturaNull;
    }

    public static void setTipoAsignaturaNull(String TipoAsignaturaNull) {
        texts.TipoAsignaturaNull = TipoAsignaturaNull;
    }

    public static String getTipoEvaluacionNull() {
        return TipoEvaluacionNull;
    }

    public static void setTipoEvaluacionNull(String TipoEvaluacionNull) {
        texts.TipoEvaluacionNull = TipoEvaluacionNull;
    }

    public static String getTipoMilitarNull() {
        return TipoMilitarNull;
    }

    public static void setTipoMilitarNull(String TipoMilitarNull) {
        texts.TipoMilitarNull = TipoMilitarNull;
    }

    public static String getTipoPlanEstudioNull() {
        return TipoPlanEstudioNull;
    }

    public static void setTipoPlanEstudioNull(String TipoPlanEstudioNull) {
        texts.TipoPlanEstudioNull = TipoPlanEstudioNull;
    }

    public static String getUniversidadNull() {
        return UniversidadNull;
    }

    public static void setUniversidadNull(String UniversidadNull) {
        texts.UniversidadNull = UniversidadNull;
    }

    public static String getUsuarioNull() {
        return UsuarioNull;
    }

    public static void setUsuarioNull(String UsuarioNull) {
        texts.UsuarioNull = UsuarioNull;
    }

}
