/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.edu.unah.sigenuead.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
/**
 *
 * @author claudy
 */
@Entity
@Table(name = "estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByEstudianteId", query = "SELECT e FROM Estudiante e WHERE e.estudianteId = :estudianteId"),
    @NamedQuery(name = "Estudiante.findByEstudianteFechaNacimiento", query = "SELECT e FROM Estudiante e WHERE e.estudianteFechaNacimiento = :estudianteFechaNacimiento"),
    @NamedQuery(name = "Estudiante.findByEstudianteCantHijos", query = "SELECT e FROM Estudiante e WHERE e.estudianteCantHijos = :estudianteCantHijos"),
    @NamedQuery(name = "Estudiante.findByEstudianteNombre", query = "SELECT e FROM Estudiante e WHERE e.estudianteNombre = :estudianteNombre"),
    @NamedQuery(name = "Estudiante.findByEstudianteApellido1", query = "SELECT e FROM Estudiante e WHERE e.estudianteApellido1 = :estudianteApellido1"),
    @NamedQuery(name = "Estudiante.findByEstudianteApellido2", query = "SELECT e FROM Estudiante e WHERE e.estudianteApellido2 = :estudianteApellido2"),
    @NamedQuery(name = "Estudiante.findByEstudianteTelefono", query = "SELECT e FROM Estudiante e WHERE e.estudianteTelefono = :estudianteTelefono"),
    @NamedQuery(name = "Estudiante.findByEstudianteDireccion", query = "SELECT e FROM Estudiante e WHERE e.estudianteDireccion = :estudianteDireccion"),
    @NamedQuery(name = "Estudiante.findByEstudianteEmail", query = "SELECT e FROM Estudiante e WHERE e.estudianteEmail = :estudianteEmail"),
    @NamedQuery(name = "Estudiante.findByEstudianteDatosLaborales", query = "SELECT e FROM Estudiante e WHERE e.estudianteDatosLaborales = :estudianteDatosLaborales"),
    @NamedQuery(name = "Estudiante.findByEstudianteSalario", query = "SELECT e FROM Estudiante e WHERE e.estudianteSalario = :estudianteSalario"),
    @NamedQuery(name = "Estudiante.findByEstudianteJefeInmediato", query = "SELECT e FROM Estudiante e WHERE e.estudianteJefeInmediato = :estudianteJefeInmediato"),
    @NamedQuery(name = "Estudiante.findByNombrecentrotrabajo", query = "SELECT e FROM Estudiante e WHERE e.nombrecentrotrabajo = :nombrecentrotrabajo"),
    @NamedQuery(name = "Estudiante.findByDireccioncentrotrabajo", query = "SELECT e FROM Estudiante e WHERE e.direccioncentrotrabajo = :direccioncentrotrabajo"),
    @NamedQuery(name = "Estudiante.findByTelefonocentrotrabajo", query = "SELECT e FROM Estudiante e WHERE e.telefonocentrotrabajo = :telefonocentrotrabajo"),
    @NamedQuery(name = "Estudiante.findByEstudianteDatosMilitares", query = "SELECT e FROM Estudiante e WHERE e.estudianteDatosMilitares = :estudianteDatosMilitares"),
    @NamedQuery(name = "Estudiante.findByEstudianteFechaLicenciamientoMilitar", query = "SELECT e FROM Estudiante e WHERE e.estudianteFechaLicenciamientoMilitar = :estudianteFechaLicenciamientoMilitar"),
    @NamedQuery(name = "Estudiante.findByNaturalde", query = "SELECT e FROM Estudiante e WHERE e.naturalde = :naturalde"),
    @NamedQuery(name = "Estudiante.findByEstudianteFechaIngresoEs", query = "SELECT e FROM Estudiante e WHERE e.estudianteFechaIngresoEs = :estudianteFechaIngresoEs"),
    @NamedQuery(name = "Estudiante.findByEstudianteFechaIngresoCes", query = "SELECT e FROM Estudiante e WHERE e.estudianteFechaIngresoCes = :estudianteFechaIngresoCes")})
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "estudiante_id")
    private String estudianteId;
    @Basic(optional = false)
    @Column(name = "estudiante_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date estudianteFechaNacimiento;
    @Basic(optional = false)
    @Column(name = "estudiante_cant_hijos")
    private int estudianteCantHijos;
    @Basic(optional = false)
    @Column(name = "estudiante_nombre")
    private String estudianteNombre;
    @Basic(optional = false)
    @Column(name = "estudiante_apellido1")
    private String estudianteApellido1;
    @Column(name = "estudiante_apellido2")
    private String estudianteApellido2;
    @Column(name = "estudiante_telefono")
    private String estudianteTelefono;
    @Basic(optional = false)
    @Column(name = "estudiante_direccion")
    private String estudianteDireccion;
    @Column(name = "estudiante_email")
    private String estudianteEmail;
    @Basic(optional = false)
    @Column(name = "estudiante_datos_laborales")
    private boolean estudianteDatosLaborales;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "estudiante_salario")
    private Float estudianteSalario;
    @Column(name = "estudiante_jefe_inmediato")
    private String estudianteJefeInmediato;
    @Column(name = "nombrecentrotrabajo")
    private String nombrecentrotrabajo;
    @Column(name = "direccioncentrotrabajo")
    private String direccioncentrotrabajo;
    @Column(name = "telefonocentrotrabajo")
    private String telefonocentrotrabajo;
    @Basic(optional = false)
    @Column(name = "estudiante_datos_militares")
    private boolean estudianteDatosMilitares;
    @Column(name = "estudiante_fecha_licenciamiento_militar")
    @Temporal(TemporalType.DATE)
    private Date estudianteFechaLicenciamientoMilitar;
    @Basic(optional = false)
    @Column(name = "naturalde")
    private String naturalde;
    @Basic(optional = false)
    @Column(name = "estudiante_fecha_ingreso_es")
    @Temporal(TemporalType.DATE)
    private Date estudianteFechaIngresoEs;
    @Basic(optional = false)
    @Column(name = "estudiante_fecha_ingreso_ces")
    @Temporal(TemporalType.DATE)
    private Date estudianteFechaIngresoCes;
    @JoinTable(name = "organizacion_popular_estudiante", joinColumns = {
        @JoinColumn(name = "estudianteestudiante_id", referencedColumnName = "estudiante_id")}, inverseJoinColumns = {
        @JoinColumn(name = "organizacion_popularorganizacion_popular_id", referencedColumnName = "organizacion_popular_id")})
    @ManyToMany
    private List<OrganizacionPopular> organizacionPopularList;
    @JoinTable(name = "minusvalia_estudiante", joinColumns = {
        @JoinColumn(name = "estudianteestudiante_id", referencedColumnName = "estudiante_id")}, inverseJoinColumns = {
        @JoinColumn(name = "minusvaliaminusvalia_id", referencedColumnName = "minusvalia_id")})
    @ManyToMany
    private List<Minusvalia> minusvaliaList;
    @ManyToMany(mappedBy = "estudianteList")
    private List<Ong> ongList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiante")
    private List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteestudianteId")
    private List<Tutor> tutorList;
    @JoinColumn(name = "color_pielcolor_piel_id", referencedColumnName = "color_piel_id")
    @ManyToOne(optional = false)
    private ColorPiel colorPielcolorPielId;
    @JoinColumn(name = "especialidad_militarespecialidad_militar_id", referencedColumnName = "especialidad_militar_id")
    @ManyToOne
    private EspecialidadMilitar especialidadMilitarespecialidadMilitarId;
    @JoinColumn(name = "estado_civilestado_civil_id", referencedColumnName = "estado_civil_id")
    @ManyToOne(optional = false)
    private EstadoCivil estadoCivilestadoCivilId;
    @JoinColumn(name = "grado_militargrado_militar_id", referencedColumnName = "grado_militar_id")
    @ManyToOne
    private GradoMilitar gradoMilitargradoMilitarId;
    @JoinColumn(name = "procedencia_escolarprocedencia_escolar_id", referencedColumnName = "procedencia_escolar_id")
    @ManyToOne(optional = false)
    private ProcedenciaEscolar procedenciaEscolarprocedenciaEscolarId;
    @JoinColumn(name = "tipo_militarid_tipo_militar", referencedColumnName = "id_tipo_militar")
    @ManyToOne
    private TipoMilitar tipoMilitaridTipoMilitar;
    @JoinColumn(name = "huerfanohuerfano_id", referencedColumnName = "huerfano_id")
    @ManyToOne(optional = false)
    private Huerfano huerfanohuerfanoId;
    @JoinColumn(name = "municipioidmunicipio", referencedColumnName = "idmunicipio")
    @ManyToOne
    private Municipio municipioidmunicipio;
    @JoinColumn(name = "municipioidmunicipiocentrotrabajo", referencedColumnName = "idmunicipio")
    @ManyToOne
    private Municipio municipioidmunicipiocentrotrabajo;
    @JoinColumn(name = "paisidpais", referencedColumnName = "idpais")
    @ManyToOne(optional = false)
    private Pais paisidpais;
    @JoinColumn(name = "provinciaidprovincia", referencedColumnName = "idprovincia")
    @ManyToOne
    private Provincia provinciaidprovincia;
    @JoinColumn(name = "sexosexo_id", referencedColumnName = "sexo_id")
    @ManyToOne(optional = false)
    private Sexo sexosexoId;
    @JoinColumn(name = "sindicatoidsindicato", referencedColumnName = "idsindicato")
    @ManyToOne
    private Sindicato sindicatoidsindicato;
    @JoinColumn(name = "nivel_escolarnivel_escolar_id", referencedColumnName = "nivel_escolar_id")
    @ManyToOne(optional = false)
    private NivelEscolar nivelEscolarnivelEscolarId;
    @JoinColumn(name = "ocupacionocupacion_id", referencedColumnName = "ocupacion_id")
    @ManyToOne
    private Ocupacion ocupacionocupacionId;
    @JoinColumn(name = "organismoidorganismo", referencedColumnName = "idorganismo")
    @ManyToOne
    private Organismo organismoidorganismo;
    @JoinColumn(name = "organizacion_politicaorganizacion_politica_id", referencedColumnName = "organizacion_politica_id")
    @ManyToOne(optional = false)
    private OrganizacionPolitica organizacionPoliticaorganizacionPoliticaId;

    public Estudiante() {
    }

    public Estudiante(String estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Estudiante(String estudianteId, Date estudianteFechaNacimiento, int estudianteCantHijos, String estudianteNombre, String estudianteApellido1, String estudianteDireccion, boolean estudianteDatosLaborales, boolean estudianteDatosMilitares, String naturalde, Date estudianteFechaIngresoEs, Date estudianteFechaIngresoCes) {
        this.estudianteId = estudianteId;
        this.estudianteFechaNacimiento = estudianteFechaNacimiento;
        this.estudianteCantHijos = estudianteCantHijos;
        this.estudianteNombre = estudianteNombre;
        this.estudianteApellido1 = estudianteApellido1;
        this.estudianteDireccion = estudianteDireccion;
        this.estudianteDatosLaborales = estudianteDatosLaborales;
        this.estudianteDatosMilitares = estudianteDatosMilitares;
        this.naturalde = naturalde;
        this.estudianteFechaIngresoEs = estudianteFechaIngresoEs;
        this.estudianteFechaIngresoCes = estudianteFechaIngresoCes;
    }

    public String getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(String estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Date getEstudianteFechaNacimiento() {
        return estudianteFechaNacimiento;
    }

    public void setEstudianteFechaNacimiento(Date estudianteFechaNacimiento) {
        this.estudianteFechaNacimiento = estudianteFechaNacimiento;
    }

    public int getEstudianteCantHijos() {
        return estudianteCantHijos;
    }

    public void setEstudianteCantHijos(int estudianteCantHijos) {
        this.estudianteCantHijos = estudianteCantHijos;
    }

    public String getEstudianteNombre() {
        return estudianteNombre;
    }

    public void setEstudianteNombre(String estudianteNombre) {
        this.estudianteNombre = estudianteNombre;
    }

    public String getEstudianteApellido1() {
        return estudianteApellido1;
    }

    public void setEstudianteApellido1(String estudianteApellido1) {
        this.estudianteApellido1 = estudianteApellido1;
    }

    public String getEstudianteApellido2() {
        return estudianteApellido2;
    }

    public void setEstudianteApellido2(String estudianteApellido2) {
        this.estudianteApellido2 = estudianteApellido2;
    }

    public String getEstudianteTelefono() {
        return estudianteTelefono;
    }

    public void setEstudianteTelefono(String estudianteTelefono) {
        this.estudianteTelefono = estudianteTelefono;
    }

    public String getEstudianteDireccion() {
        return estudianteDireccion;
    }

    public void setEstudianteDireccion(String estudianteDireccion) {
        this.estudianteDireccion = estudianteDireccion;
    }

    public String getEstudianteEmail() {
        return estudianteEmail;
    }

    public void setEstudianteEmail(String estudianteEmail) {
        this.estudianteEmail = estudianteEmail;
    }

    public boolean getEstudianteDatosLaborales() {
        return estudianteDatosLaborales;
    }

    public void setEstudianteDatosLaborales(boolean estudianteDatosLaborales) {
        this.estudianteDatosLaborales = estudianteDatosLaborales;
    }

    public Float getEstudianteSalario() {
        return estudianteSalario;
    }

    public void setEstudianteSalario(Float estudianteSalario) {
        this.estudianteSalario = estudianteSalario;
    }

    public String getEstudianteJefeInmediato() {
        return estudianteJefeInmediato;
    }

    public void setEstudianteJefeInmediato(String estudianteJefeInmediato) {
        this.estudianteJefeInmediato = estudianteJefeInmediato;
    }

    public String getNombrecentrotrabajo() {
        return nombrecentrotrabajo;
    }

    public void setNombrecentrotrabajo(String nombrecentrotrabajo) {
        this.nombrecentrotrabajo = nombrecentrotrabajo;
    }

    public String getDireccioncentrotrabajo() {
        return direccioncentrotrabajo;
    }

    public void setDireccioncentrotrabajo(String direccioncentrotrabajo) {
        this.direccioncentrotrabajo = direccioncentrotrabajo;
    }

    public String getTelefonocentrotrabajo() {
        return telefonocentrotrabajo;
    }

    public void setTelefonocentrotrabajo(String telefonocentrotrabajo) {
        this.telefonocentrotrabajo = telefonocentrotrabajo;
    }

    public boolean getEstudianteDatosMilitares() {
        return estudianteDatosMilitares;
    }

    public void setEstudianteDatosMilitares(boolean estudianteDatosMilitares) {
        this.estudianteDatosMilitares = estudianteDatosMilitares;
    }

    public Date getEstudianteFechaLicenciamientoMilitar() {
        return estudianteFechaLicenciamientoMilitar;
    }

    public void setEstudianteFechaLicenciamientoMilitar(Date estudianteFechaLicenciamientoMilitar) {
        this.estudianteFechaLicenciamientoMilitar = estudianteFechaLicenciamientoMilitar;
    }

    public String getNaturalde() {
        return naturalde;
    }

    public void setNaturalde(String naturalde) {
        this.naturalde = naturalde;
    }

    public Date getEstudianteFechaIngresoEs() {
        return estudianteFechaIngresoEs;
    }

    public void setEstudianteFechaIngresoEs(Date estudianteFechaIngresoEs) {
        this.estudianteFechaIngresoEs = estudianteFechaIngresoEs;
    }

    public Date getEstudianteFechaIngresoCes() {
        return estudianteFechaIngresoCes;
    }

    public void setEstudianteFechaIngresoCes(Date estudianteFechaIngresoCes) {
        this.estudianteFechaIngresoCes = estudianteFechaIngresoCes;
    }

    @XmlTransient
    public List<OrganizacionPopular> getOrganizacionPopularList() {
        return organizacionPopularList;
    }

    public void setOrganizacionPopularList(List<OrganizacionPopular> organizacionPopularList) {
        this.organizacionPopularList = organizacionPopularList;
    }

    @XmlTransient
    public List<Minusvalia> getMinusvaliaList() {
        return minusvaliaList;
    }

    public void setMinusvaliaList(List<Minusvalia> minusvaliaList) {
        this.minusvaliaList = minusvaliaList;
    }

    @XmlTransient
    public List<Ong> getOngList() {
        return ongList;
    }

    public void setOngList(List<Ong> ongList) {
        this.ongList = ongList;
    }

    @XmlTransient
    public List<FacultadCumCarreraEstudiante> getFacultadCumCarreraEstudianteList() {
        return facultadCumCarreraEstudianteList;
    }

    public void setFacultadCumCarreraEstudianteList(List<FacultadCumCarreraEstudiante> facultadCumCarreraEstudianteList) {
        this.facultadCumCarreraEstudianteList = facultadCumCarreraEstudianteList;
    }

    @XmlTransient
    public List<Tutor> getTutorList() {
        return tutorList;
    }

    public void setTutorList(List<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    public ColorPiel getColorPielcolorPielId() {
        return colorPielcolorPielId;
    }

    public void setColorPielcolorPielId(ColorPiel colorPielcolorPielId) {
        this.colorPielcolorPielId = colorPielcolorPielId;
    }

    public EspecialidadMilitar getEspecialidadMilitarespecialidadMilitarId() {
        return especialidadMilitarespecialidadMilitarId;
    }

    public void setEspecialidadMilitarespecialidadMilitarId(EspecialidadMilitar especialidadMilitarespecialidadMilitarId) {
        this.especialidadMilitarespecialidadMilitarId = especialidadMilitarespecialidadMilitarId;
    }

    public EstadoCivil getEstadoCivilestadoCivilId() {
        return estadoCivilestadoCivilId;
    }

    public void setEstadoCivilestadoCivilId(EstadoCivil estadoCivilestadoCivilId) {
        this.estadoCivilestadoCivilId = estadoCivilestadoCivilId;
    }

    public GradoMilitar getGradoMilitargradoMilitarId() {
        return gradoMilitargradoMilitarId;
    }

    public void setGradoMilitargradoMilitarId(GradoMilitar gradoMilitargradoMilitarId) {
        this.gradoMilitargradoMilitarId = gradoMilitargradoMilitarId;
    }

    public ProcedenciaEscolar getProcedenciaEscolarprocedenciaEscolarId() {
        return procedenciaEscolarprocedenciaEscolarId;
    }

    public void setProcedenciaEscolarprocedenciaEscolarId(ProcedenciaEscolar procedenciaEscolarprocedenciaEscolarId) {
        this.procedenciaEscolarprocedenciaEscolarId = procedenciaEscolarprocedenciaEscolarId;
    }

    public TipoMilitar getTipoMilitaridTipoMilitar() {
        return tipoMilitaridTipoMilitar;
    }

    public void setTipoMilitaridTipoMilitar(TipoMilitar tipoMilitaridTipoMilitar) {
        this.tipoMilitaridTipoMilitar = tipoMilitaridTipoMilitar;
    }

    public Huerfano getHuerfanohuerfanoId() {
        return huerfanohuerfanoId;
    }

    public void setHuerfanohuerfanoId(Huerfano huerfanohuerfanoId) {
        this.huerfanohuerfanoId = huerfanohuerfanoId;
    }

    public Municipio getMunicipioidmunicipio() {
        return municipioidmunicipio;
    }

    public void setMunicipioidmunicipio(Municipio municipioidmunicipio) {
        this.municipioidmunicipio = municipioidmunicipio;
    }

    public Municipio getMunicipioidmunicipiocentrotrabajo() {
        return municipioidmunicipiocentrotrabajo;
    }

    public void setMunicipioidmunicipiocentrotrabajo(Municipio municipioidmunicipiocentrotrabajo) {
        this.municipioidmunicipiocentrotrabajo = municipioidmunicipiocentrotrabajo;
    }

    public Pais getPaisidpais() {
        return paisidpais;
    }

    public void setPaisidpais(Pais paisidpais) {
        this.paisidpais = paisidpais;
    }

    public Provincia getProvinciaidprovincia() {
        return provinciaidprovincia;
    }

    public void setProvinciaidprovincia(Provincia provinciaidprovincia) {
        this.provinciaidprovincia = provinciaidprovincia;
    }

    public Sexo getSexosexoId() {
        return sexosexoId;
    }

    public void setSexosexoId(Sexo sexosexoId) {
        this.sexosexoId = sexosexoId;
    }

    public Sindicato getSindicatoidsindicato() {
        return sindicatoidsindicato;
    }

    public void setSindicatoidsindicato(Sindicato sindicatoidsindicato) {
        this.sindicatoidsindicato = sindicatoidsindicato;
    }

    public NivelEscolar getNivelEscolarnivelEscolarId() {
        return nivelEscolarnivelEscolarId;
    }

    public void setNivelEscolarnivelEscolarId(NivelEscolar nivelEscolarnivelEscolarId) {
        this.nivelEscolarnivelEscolarId = nivelEscolarnivelEscolarId;
    }

    public Ocupacion getOcupacionocupacionId() {
        return ocupacionocupacionId;
    }

    public void setOcupacionocupacionId(Ocupacion ocupacionocupacionId) {
        this.ocupacionocupacionId = ocupacionocupacionId;
    }

    public Organismo getOrganismoidorganismo() {
        return organismoidorganismo;
    }

    public void setOrganismoidorganismo(Organismo organismoidorganismo) {
        this.organismoidorganismo = organismoidorganismo;
    }

    public OrganizacionPolitica getOrganizacionPoliticaorganizacionPoliticaId() {
        return organizacionPoliticaorganizacionPoliticaId;
    }

    public void setOrganizacionPoliticaorganizacionPoliticaId(OrganizacionPolitica organizacionPoliticaorganizacionPoliticaId) {
        this.organizacionPoliticaorganizacionPoliticaId = organizacionPoliticaorganizacionPoliticaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estudianteId != null ? estudianteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.estudianteId == null && other.estudianteId != null) || (this.estudianteId != null && !this.estudianteId.equals(other.estudianteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Estudiante[ estudianteId=" + estudianteId + " ]";
    }

    public Estudiante(String estudianteId, Date estudianteFechaNacimiento, int estudianteCantHijos, String estudianteNombre, String estudianteApellido1, String estudianteApellido2, String estudianteTelefono, String estudianteDireccion, String estudianteEmail, boolean estudianteDatosLaborales, float estudianteSalario, String estudianteJefeInmediato, String nombrecentrotrabajo, String direccioncentrotrabajo, String telefonocentrotrabajo, boolean estudianteDatosMilitares, Date estudianteFechaLicenciamientoMilitar, String naturalde, Date estudianteFechaIngresoEs, Date estudianteFechaIngresoCes, List<OrganizacionPopular> organizacionPopularList, List<Minusvalia> minusvaliaList, List<Ong> ongList, EstadoCivil estadoCivilestadoCivilId, ColorPiel colorPielcolorPielId, EspecialidadMilitar especialidadMilitarespecialidadMilitarId, TipoMilitar tipoMilitaridTipoMilitar, Provincia provinciaidprovincia, Organismo organismoidorganismo, GradoMilitar gradoMilitargradoMilitarId, Huerfano huerfanohuerfanoId, NivelEscolar nivelEscolarnivelEscolarId, ProcedenciaEscolar procedenciaEscolarprocedenciaEscolarId, Municipio municipioidmunicipiocentrotrabajo, Sindicato sindicatoidsindicato, Pais paisidpais, Municipio municipioidmunicipio, Ocupacion ocupacionocupacionId, OrganizacionPolitica organizacionPoliticaorganizacionPoliticaId, Sexo sexosexoId) {
        this.estudianteId = estudianteId;
        this.estudianteFechaNacimiento = estudianteFechaNacimiento;
        this.estudianteCantHijos = estudianteCantHijos;
        this.estudianteNombre = estudianteNombre;
        this.estudianteApellido1 = estudianteApellido1;
        this.estudianteApellido2 = estudianteApellido2;
        this.estudianteTelefono = estudianteTelefono;
        this.estudianteDireccion = estudianteDireccion;
        this.estudianteEmail = estudianteEmail;
        this.estudianteDatosLaborales = estudianteDatosLaborales;
        this.estudianteSalario = estudianteSalario;
        this.estudianteJefeInmediato = estudianteJefeInmediato;
        this.nombrecentrotrabajo = nombrecentrotrabajo;
        this.direccioncentrotrabajo = direccioncentrotrabajo;
        this.telefonocentrotrabajo = telefonocentrotrabajo;
        this.estudianteDatosMilitares = estudianteDatosMilitares;
        this.estudianteFechaLicenciamientoMilitar = estudianteFechaLicenciamientoMilitar;
        this.naturalde = naturalde;
        this.estudianteFechaIngresoEs = estudianteFechaIngresoEs;
        this.estudianteFechaIngresoCes = estudianteFechaIngresoCes;
        this.organizacionPopularList = organizacionPopularList;
        this.minusvaliaList = minusvaliaList;
        this.ongList = ongList;
        this.estadoCivilestadoCivilId = estadoCivilestadoCivilId;
        this.colorPielcolorPielId = colorPielcolorPielId;
        this.especialidadMilitarespecialidadMilitarId = especialidadMilitarespecialidadMilitarId;
        this.tipoMilitaridTipoMilitar = tipoMilitaridTipoMilitar;
        this.provinciaidprovincia = provinciaidprovincia;
        this.organismoidorganismo = organismoidorganismo;
        this.gradoMilitargradoMilitarId = gradoMilitargradoMilitarId;
        this.huerfanohuerfanoId = huerfanohuerfanoId;
        this.nivelEscolarnivelEscolarId = nivelEscolarnivelEscolarId;
        this.procedenciaEscolarprocedenciaEscolarId = procedenciaEscolarprocedenciaEscolarId;
        this.municipioidmunicipiocentrotrabajo = municipioidmunicipiocentrotrabajo;
        this.sindicatoidsindicato = sindicatoidsindicato;
        this.paisidpais = paisidpais;
        this.municipioidmunicipio = municipioidmunicipio;
        this.ocupacionocupacionId = ocupacionocupacionId;
        this.organizacionPoliticaorganizacionPoliticaId = organizacionPoliticaorganizacionPoliticaId;
        this.sexosexoId = sexosexoId;
    }
}
