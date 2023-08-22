--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2 (Ubuntu 15.2-1.pgdg22.04+1)
-- Dumped by pg_dump version 15.2

-- Started on 2023-08-22 13:08:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 33568)
-- Name: asignatura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.asignatura (
    asignatura_id integer NOT NULL,
    asignatura_codigo character varying(255) NOT NULL,
    asignatura_nombre character varying(255) NOT NULL,
    asignatura_abreviatura character varying(255) NOT NULL,
    asignatura_cancelada boolean NOT NULL,
    tipo_evaluaciontipo_evaluacion_id integer NOT NULL,
    tipo_asignaturatipo_asignatura_id integer NOT NULL,
    asignatura_promediable boolean NOT NULL,
    asignatura_certificable boolean NOT NULL,
    disciplina_codigo character varying(255) NOT NULL,
    disciplina_idplanestudio integer NOT NULL
);


ALTER TABLE public.asignatura OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 33571)
-- Name: asignatura_asignatura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.asignatura_asignatura (
    asignatura_precedente integer NOT NULL,
    asignatura_dependiente integer NOT NULL,
    cancelado boolean NOT NULL
);


ALTER TABLE public.asignatura_asignatura OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 33574)
-- Name: asignatura_asignatura_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.asignatura_asignatura_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.asignatura_asignatura_id_seq OWNER TO postgres;

--
-- TOC entry 3706 (class 0 OID 0)
-- Dependencies: 216
-- Name: asignatura_asignatura_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.asignatura_asignatura_id_seq OWNED BY public.asignatura.asignatura_id;


--
-- TOC entry 217 (class 1259 OID 33575)
-- Name: authorities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authorities (
    username character varying(50) NOT NULL,
    authority character varying(255) NOT NULL
);


ALTER TABLE public.authorities OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 33578)
-- Name: baja; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.baja (
    tipo_bajaid_tipo_baja integer NOT NULL,
    motivo_bajaid_motivo_baja integer NOT NULL,
    fecha date NOT NULL,
    cursoidcurso character varying(255) NOT NULL,
    codigocum character varying(255) NOT NULL,
    codigoarea character varying(255) NOT NULL,
    idcarrera integer NOT NULL,
    estudiante_id character varying(255) NOT NULL,
    fecha_matricula date NOT NULL,
    baja_cancelada boolean NOT NULL
);


ALTER TABLE public.baja OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 33581)
-- Name: carrera; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.carrera (
    idcarrera integer NOT NULL,
    canceladacarrera boolean NOT NULL,
    carreranacionalidcarreranacional integer NOT NULL,
    facultadcodigoarea character varying(255)
);


ALTER TABLE public.carrera OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 33584)
-- Name: carrera_curso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.carrera_curso (
    carreraidcarrera integer NOT NULL,
    cursoidcurso character varying(255) NOT NULL,
    cantmatriculas integer NOT NULL,
    cancelado boolean NOT NULL
);


ALTER TABLE public.carrera_curso OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 33587)
-- Name: carrera_idcarrera_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.carrera_idcarrera_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.carrera_idcarrera_seq OWNER TO postgres;

--
-- TOC entry 3707 (class 0 OID 0)
-- Dependencies: 221
-- Name: carrera_idcarrera_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.carrera_idcarrera_seq OWNED BY public.carrera.idcarrera;


--
-- TOC entry 222 (class 1259 OID 33588)
-- Name: carreranacional; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.carreranacional (
    idcarreranacional integer NOT NULL,
    codigocarreranacional character varying(255) NOT NULL,
    nombrecarreranacional character varying(255) NOT NULL,
    diplomacarreranacional character varying(255),
    canceladocarreranacional boolean NOT NULL,
    especialidadidespecialidad integer NOT NULL
);


ALTER TABLE public.carreranacional OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 33591)
-- Name: carreranacional_idcarreranacional_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.carreranacional_idcarreranacional_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.carreranacional_idcarreranacional_seq OWNER TO postgres;

--
-- TOC entry 3708 (class 0 OID 0)
-- Dependencies: 223
-- Name: carreranacional_idcarreranacional_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.carreranacional_idcarreranacional_seq OWNED BY public.carreranacional.idcarreranacional;


--
-- TOC entry 224 (class 1259 OID 33592)
-- Name: color_piel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.color_piel (
    color_piel_id integer NOT NULL,
    color_piel_nombre character varying(255) NOT NULL,
    color_piel_cancelado boolean NOT NULL
);


ALTER TABLE public.color_piel OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 33595)
-- Name: color_piel_color_piel_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.color_piel_color_piel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.color_piel_color_piel_id_seq OWNER TO postgres;

--
-- TOC entry 3709 (class 0 OID 0)
-- Dependencies: 225
-- Name: color_piel_color_piel_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.color_piel_color_piel_id_seq OWNED BY public.color_piel.color_piel_id;


--
-- TOC entry 226 (class 1259 OID 33596)
-- Name: cum; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cum (
    codigocum character varying(255) NOT NULL,
    inicialescum character varying(255) NOT NULL,
    actividadcum character varying(255),
    nombrecum character varying(255) NOT NULL,
    direccioncum character varying(255) NOT NULL,
    telefonocum character varying(255),
    faxcum character varying(255),
    nombresecgralcum character varying(255) NOT NULL,
    nombredirectorcum character varying(255) NOT NULL,
    reglamentocum character varying(255) NOT NULL,
    cumcancelado boolean NOT NULL,
    universidadcodigouniversidad character varying(255) NOT NULL,
    municipioidmunicipio integer NOT NULL
);


ALTER TABLE public.cum OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 33601)
-- Name: cum_authorities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cum_authorities (
    cumcodigocum character varying(255) NOT NULL,
    authoritiesusername character varying(50) NOT NULL,
    authoritiesauthority character varying(255) NOT NULL
);


ALTER TABLE public.cum_authorities OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 33604)
-- Name: curso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.curso (
    idcurso character varying(255) NOT NULL,
    fechainiciomatriculacurso date NOT NULL,
    fechafinmatricula date NOT NULL,
    fechainiciorematricula date NOT NULL,
    fechafinrematricula date NOT NULL,
    fechagraduacion date NOT NULL,
    cursoactual boolean NOT NULL,
    universidadcodigouniversidad character varying(255) NOT NULL,
    cancelado boolean NOT NULL
);


ALTER TABLE public.curso OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 33607)
-- Name: disciplina; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.disciplina (
    disciplina_codigo character varying(255) NOT NULL,
    disciplina_nombre character varying(255) NOT NULL,
    disciplina_cancelada boolean NOT NULL
);


ALTER TABLE public.disciplina OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 33610)
-- Name: disciplina_planestudio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.disciplina_planestudio (
    disciplinadisciplina_codigo character varying(255) NOT NULL,
    planestudioidplanestudio integer NOT NULL,
    objetivos character varying(1500),
    programa character varying(1500),
    cancelado boolean NOT NULL
);


ALTER TABLE public.disciplina_planestudio OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 33615)
-- Name: especialidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.especialidad (
    idespecialidad integer NOT NULL,
    nombreespecialidad character varying(255) NOT NULL,
    canceladoespecialidad boolean NOT NULL
);


ALTER TABLE public.especialidad OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 33618)
-- Name: especialidad_idespecialidad_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.especialidad_idespecialidad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.especialidad_idespecialidad_seq OWNER TO postgres;

--
-- TOC entry 3710 (class 0 OID 0)
-- Dependencies: 232
-- Name: especialidad_idespecialidad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.especialidad_idespecialidad_seq OWNED BY public.especialidad.idespecialidad;


--
-- TOC entry 233 (class 1259 OID 33619)
-- Name: especialidad_militar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.especialidad_militar (
    especialidad_militar_id integer NOT NULL,
    especialidad_militar_nombre character varying(255) NOT NULL,
    especialidad_militar_cancelado boolean NOT NULL
);


ALTER TABLE public.especialidad_militar OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 33622)
-- Name: especialidad_militar_especialidad_militar_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.especialidad_militar_especialidad_militar_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.especialidad_militar_especialidad_militar_id_seq OWNER TO postgres;

--
-- TOC entry 3711 (class 0 OID 0)
-- Dependencies: 234
-- Name: especialidad_militar_especialidad_militar_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.especialidad_militar_especialidad_militar_id_seq OWNED BY public.especialidad_militar.especialidad_militar_id;


--
-- TOC entry 235 (class 1259 OID 33623)
-- Name: estado_civil; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estado_civil (
    estado_civil_id integer NOT NULL,
    estado_civil_nombre character varying(255) NOT NULL,
    estado_civil_cancelado boolean NOT NULL
);


ALTER TABLE public.estado_civil OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 33626)
-- Name: estado_civil_estado_civil_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estado_civil_estado_civil_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estado_civil_estado_civil_id_seq OWNER TO postgres;

--
-- TOC entry 3712 (class 0 OID 0)
-- Dependencies: 236
-- Name: estado_civil_estado_civil_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estado_civil_estado_civil_id_seq OWNED BY public.estado_civil.estado_civil_id;


--
-- TOC entry 237 (class 1259 OID 33627)
-- Name: estado_estudiante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estado_estudiante (
    estado_estuciante_id integer NOT NULL,
    estado_estudiante_nombre character varying(255) NOT NULL,
    estado_estudiante_cancelado boolean NOT NULL
);


ALTER TABLE public.estado_estudiante OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 33630)
-- Name: estado_estudiante_estado_estuciante_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estado_estudiante_estado_estuciante_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estado_estudiante_estado_estuciante_id_seq OWNER TO postgres;

--
-- TOC entry 3713 (class 0 OID 0)
-- Dependencies: 238
-- Name: estado_estudiante_estado_estuciante_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estado_estudiante_estado_estuciante_id_seq OWNED BY public.estado_estudiante.estado_estuciante_id;


--
-- TOC entry 239 (class 1259 OID 33631)
-- Name: estudiante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estudiante (
    estudiante_id character varying(255) NOT NULL,
    estudiante_fecha_nacimiento date NOT NULL,
    estudiante_cant_hijos integer DEFAULT 0 NOT NULL,
    estudiante_nombre character varying(255) NOT NULL,
    estudiante_apellido1 character varying(255) NOT NULL,
    estudiante_apellido2 character varying(255),
    estudiante_telefono character varying(255),
    estudiante_direccion character varying(255) NOT NULL,
    estudiante_email character varying(255),
    estudiante_datos_laborales boolean NOT NULL,
    estudiante_salario real,
    estudiante_jefe_inmediato character varying(255),
    nombrecentrotrabajo character varying(255),
    direccioncentrotrabajo character varying(255),
    telefonocentrotrabajo character varying(255),
    municipioidmunicipiocentrotrabajo integer,
    organismoidorganismo integer,
    sindicatoidsindicato integer,
    ocupacionocupacion_id integer,
    estudiante_datos_militares boolean NOT NULL,
    estudiante_fecha_licenciamiento_militar date,
    grado_militargrado_militar_id integer,
    especialidad_militarespecialidad_militar_id integer,
    tipo_militarid_tipo_militar integer,
    naturalde character varying(255) NOT NULL,
    sexosexo_id integer NOT NULL,
    color_pielcolor_piel_id integer NOT NULL,
    organizacion_politicaorganizacion_politica_id integer NOT NULL,
    huerfanohuerfano_id integer NOT NULL,
    estado_civilestado_civil_id integer NOT NULL,
    procedencia_escolarprocedencia_escolar_id integer NOT NULL,
    nivel_escolarnivel_escolar_id integer NOT NULL,
    estudiante_fecha_ingreso_es date NOT NULL,
    estudiante_fecha_ingreso_ces date NOT NULL,
    municipioidmunicipio integer,
    provinciaidprovincia integer,
    paisidpais integer NOT NULL
);


ALTER TABLE public.estudiante OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 33637)
-- Name: examen; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.examen (
    examen_id integer NOT NULL,
    examen_tipo character varying(255) NOT NULL,
    examen_cancelado boolean NOT NULL
);


ALTER TABLE public.examen OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 33640)
-- Name: examen_examen_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.examen_examen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.examen_examen_id_seq OWNER TO postgres;

--
-- TOC entry 3714 (class 0 OID 0)
-- Dependencies: 241
-- Name: examen_examen_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.examen_examen_id_seq OWNED BY public.examen.examen_id;


--
-- TOC entry 242 (class 1259 OID 33641)
-- Name: examen_matricula_facultad_cum_carrera_estudiante_asignatura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.examen_matricula_facultad_cum_carrera_estudiante_asignatura (
    examenexamen_id integer NOT NULL,
    matricula_id integer NOT NULL,
    codigocum character varying(255) NOT NULL,
    codigoarea character varying(255) NOT NULL,
    idcarrera integer NOT NULL,
    estudiante_id character varying(255) NOT NULL,
    fecha_matricula date NOT NULL,
    asignatura_id integer NOT NULL,
    cancelado boolean NOT NULL,
    nota integer NOT NULL
);


ALTER TABLE public.examen_matricula_facultad_cum_carrera_estudiante_asignatura OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 33644)
-- Name: facultad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.facultad (
    codigoarea character varying(255) NOT NULL,
    nombrearea character varying(255) NOT NULL,
    nombresecdocarea character varying(255) NOT NULL,
    nombredecanoarea character varying(255) NOT NULL,
    telefonoarea character varying(255) NOT NULL,
    direccionarea character varying(255) NOT NULL,
    canceladoarea boolean NOT NULL,
    municipioidmunicipio integer NOT NULL,
    universidadcodigouniversidad character varying(255) NOT NULL
);


ALTER TABLE public.facultad OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 33647)
-- Name: facultad_authorities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.facultad_authorities (
    facultadcodigoarea character varying(255) NOT NULL,
    authoritiesusername character varying(50) NOT NULL,
    authoritiesauthority character varying(255) NOT NULL
);


ALTER TABLE public.facultad_authorities OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 33650)
-- Name: facultad_cum; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.facultad_cum (
    cumcodigocum character varying(255) NOT NULL,
    facultadcodigoarea character varying(255) NOT NULL,
    cancelado boolean NOT NULL
);


ALTER TABLE public.facultad_cum OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 33653)
-- Name: facultad_cum_carrera; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.facultad_cum_carrera (
    facultad_cumcumcodigocum character varying(255) NOT NULL,
    facultad_cumfacultadcodigoarea character varying(255) NOT NULL,
    carreraidcarrera integer NOT NULL,
    cancelado boolean NOT NULL
);


ALTER TABLE public.facultad_cum_carrera OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 33656)
-- Name: facultad_cum_carrera_estudiante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.facultad_cum_carrera_estudiante (
    facultad_cum_carrerafacultad_cumcumcodigocum character varying(255) NOT NULL,
    facultad_cum_carrerafacultad_cumfacultadcodigoarea character varying(255) NOT NULL,
    facultad_cum_carreracarreraidcarrera integer NOT NULL,
    estudianteestudiante_id character varying(255) NOT NULL,
    fecha_matricula date NOT NULL,
    carrera_reoferta boolean NOT NULL,
    carrera_opcion integer,
    nota_mat real,
    nota_espanol real,
    nota_historia real,
    promedio_ingreso real,
    escalafon integer,
    estado_estudianteestado_estuciante_id integer NOT NULL,
    planestudioidplanestudio integer NOT NULL,
    fuente_ingresofuente_ingreso_id integer NOT NULL
);


ALTER TABLE public.facultad_cum_carrera_estudiante OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 33659)
-- Name: facultad_cum_carrera_estudiante_asignatura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.facultad_cum_carrera_estudiante_asignatura (
    codigocum character varying(255) NOT NULL,
    codigoarea character varying(255) NOT NULL,
    idcarrera integer NOT NULL,
    estudiante_id character varying(255) NOT NULL,
    fecha_matricula date NOT NULL,
    asignatura_id integer NOT NULL,
    aprobada boolean NOT NULL,
    cancelada boolean NOT NULL
);


ALTER TABLE public.facultad_cum_carrera_estudiante_asignatura OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 33662)
-- Name: fuente_ingreso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fuente_ingreso (
    fuente_ingreso_id integer NOT NULL,
    fuente_ingreso_nombre character varying(255) NOT NULL,
    fuente_ingreso_cancelado boolean NOT NULL
);


ALTER TABLE public.fuente_ingreso OWNER TO postgres;

--
-- TOC entry 250 (class 1259 OID 33665)
-- Name: fuente_ingreso_fuente_ingreso_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fuente_ingreso_fuente_ingreso_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fuente_ingreso_fuente_ingreso_id_seq OWNER TO postgres;

--
-- TOC entry 3715 (class 0 OID 0)
-- Dependencies: 250
-- Name: fuente_ingreso_fuente_ingreso_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fuente_ingreso_fuente_ingreso_id_seq OWNED BY public.fuente_ingreso.fuente_ingreso_id;


--
-- TOC entry 251 (class 1259 OID 33666)
-- Name: grado_militar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grado_militar (
    grado_militar_id integer NOT NULL,
    grado_militar_nombre character varying(255) NOT NULL,
    grado_militar_cancelado boolean NOT NULL
);


ALTER TABLE public.grado_militar OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 33669)
-- Name: grado_militar_grado_militar_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.grado_militar_grado_militar_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grado_militar_grado_militar_id_seq OWNER TO postgres;

--
-- TOC entry 3716 (class 0 OID 0)
-- Dependencies: 252
-- Name: grado_militar_grado_militar_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.grado_militar_grado_militar_id_seq OWNED BY public.grado_militar.grado_militar_id;


--
-- TOC entry 253 (class 1259 OID 33670)
-- Name: huerfano; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.huerfano (
    huerfano_id integer NOT NULL,
    huerfano_nombre character varying(255) NOT NULL,
    huerfano_cancelado boolean NOT NULL
);


ALTER TABLE public.huerfano OWNER TO postgres;

--
-- TOC entry 254 (class 1259 OID 33673)
-- Name: huerfano_huerfano_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.huerfano_huerfano_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.huerfano_huerfano_id_seq OWNER TO postgres;

--
-- TOC entry 3717 (class 0 OID 0)
-- Dependencies: 254
-- Name: huerfano_huerfano_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.huerfano_huerfano_id_seq OWNED BY public.huerfano.huerfano_id;


--
-- TOC entry 255 (class 1259 OID 33674)
-- Name: matricula; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.matricula (
    matricula_id integer NOT NULL,
    matricula_fecha_inicio date NOT NULL,
    matricula_fecha_fin date NOT NULL,
    matricula_cancelada boolean NOT NULL,
    cerrada boolean NOT NULL,
    cursoidcurso character varying(255) NOT NULL,
    facultad_cum_carrerafacultad_cumcumcodigocum character varying(255) NOT NULL,
    facultad_cum_carrerafacultad_cumfacultadcodigoarea character varying(255) NOT NULL,
    facultad_cum_carreracarreraidcarrera integer NOT NULL
);


ALTER TABLE public.matricula OWNER TO postgres;

--
-- TOC entry 256 (class 1259 OID 33677)
-- Name: matricula_facultad_cum_carrera_estudiante_asignatura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.matricula_facultad_cum_carrera_estudiante_asignatura (
    matriculamatricula_id integer NOT NULL,
    codigocum character varying(255) NOT NULL,
    codigoarea character varying(255) NOT NULL,
    idcarrera integer NOT NULL,
    estudiante_id character varying(255) NOT NULL,
    fecha_matricula date NOT NULL,
    asignatura_id integer NOT NULL,
    cancelada boolean NOT NULL,
    actual boolean NOT NULL
);


ALTER TABLE public.matricula_facultad_cum_carrera_estudiante_asignatura OWNER TO postgres;

--
-- TOC entry 257 (class 1259 OID 33680)
-- Name: matricula_matricula_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.matricula_matricula_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.matricula_matricula_id_seq OWNER TO postgres;

--
-- TOC entry 3718 (class 0 OID 0)
-- Dependencies: 257
-- Name: matricula_matricula_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.matricula_matricula_id_seq OWNED BY public.matricula.matricula_id;


--
-- TOC entry 258 (class 1259 OID 33681)
-- Name: minusvalia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.minusvalia (
    minusvalia_id integer NOT NULL,
    minusvalia_nombre character varying(255) NOT NULL,
    minusvalia_cancelado boolean NOT NULL
);


ALTER TABLE public.minusvalia OWNER TO postgres;

--
-- TOC entry 259 (class 1259 OID 33684)
-- Name: minusvalia_estudiante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.minusvalia_estudiante (
    minusvaliaminusvalia_id integer NOT NULL,
    estudianteestudiante_id character varying(255) NOT NULL
);


ALTER TABLE public.minusvalia_estudiante OWNER TO postgres;

--
-- TOC entry 260 (class 1259 OID 33687)
-- Name: minusvalia_minusvalia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.minusvalia_minusvalia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.minusvalia_minusvalia_id_seq OWNER TO postgres;

--
-- TOC entry 3719 (class 0 OID 0)
-- Dependencies: 260
-- Name: minusvalia_minusvalia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.minusvalia_minusvalia_id_seq OWNED BY public.minusvalia.minusvalia_id;


--
-- TOC entry 261 (class 1259 OID 33688)
-- Name: motivo_baja; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.motivo_baja (
    id_motivo_baja integer NOT NULL,
    nombre_motivo_baja character varying(500) NOT NULL,
    tipo_bajaid_tipo_baja integer NOT NULL,
    cancelado boolean NOT NULL
);


ALTER TABLE public.motivo_baja OWNER TO postgres;

--
-- TOC entry 262 (class 1259 OID 33691)
-- Name: motivo_baja_id_motivo_baja_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.motivo_baja_id_motivo_baja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.motivo_baja_id_motivo_baja_seq OWNER TO postgres;

--
-- TOC entry 3720 (class 0 OID 0)
-- Dependencies: 262
-- Name: motivo_baja_id_motivo_baja_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.motivo_baja_id_motivo_baja_seq OWNED BY public.motivo_baja.id_motivo_baja;


--
-- TOC entry 263 (class 1259 OID 33692)
-- Name: municipio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.municipio (
    idmunicipio integer NOT NULL,
    provinciaidprovincia integer NOT NULL,
    nombremunicipio character varying(255) NOT NULL,
    canceladomunicipio boolean NOT NULL
);


ALTER TABLE public.municipio OWNER TO postgres;

--
-- TOC entry 264 (class 1259 OID 33695)
-- Name: municipio_idmunicipio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.municipio_idmunicipio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.municipio_idmunicipio_seq OWNER TO postgres;

--
-- TOC entry 3721 (class 0 OID 0)
-- Dependencies: 264
-- Name: municipio_idmunicipio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.municipio_idmunicipio_seq OWNED BY public.municipio.idmunicipio;


--
-- TOC entry 265 (class 1259 OID 33696)
-- Name: nivel_escolar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nivel_escolar (
    nivel_escolar_id integer NOT NULL,
    nivel_escolar_nombre character varying(255) NOT NULL,
    nivel_escolar_cancelado boolean NOT NULL
);


ALTER TABLE public.nivel_escolar OWNER TO postgres;

--
-- TOC entry 266 (class 1259 OID 33699)
-- Name: nivel_escolar_nivel_escolar_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nivel_escolar_nivel_escolar_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nivel_escolar_nivel_escolar_id_seq OWNER TO postgres;

--
-- TOC entry 3722 (class 0 OID 0)
-- Dependencies: 266
-- Name: nivel_escolar_nivel_escolar_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.nivel_escolar_nivel_escolar_id_seq OWNED BY public.nivel_escolar.nivel_escolar_id;


--
-- TOC entry 267 (class 1259 OID 33700)
-- Name: ocupacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ocupacion (
    ocupacion_id integer NOT NULL,
    ocupacion_nombre character varying(255) NOT NULL,
    ocupacion_cancelado boolean NOT NULL
);


ALTER TABLE public.ocupacion OWNER TO postgres;

--
-- TOC entry 268 (class 1259 OID 33703)
-- Name: ocupacion_ocupacion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ocupacion_ocupacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ocupacion_ocupacion_id_seq OWNER TO postgres;

--
-- TOC entry 3723 (class 0 OID 0)
-- Dependencies: 268
-- Name: ocupacion_ocupacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ocupacion_ocupacion_id_seq OWNED BY public.ocupacion.ocupacion_id;


--
-- TOC entry 269 (class 1259 OID 33704)
-- Name: ong; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ong (
    ong_id integer NOT NULL,
    ong_nombre character varying(255) NOT NULL,
    ong_cancelado boolean NOT NULL
);


ALTER TABLE public.ong OWNER TO postgres;

--
-- TOC entry 270 (class 1259 OID 33707)
-- Name: ong_estudiante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ong_estudiante (
    ongong_id integer NOT NULL,
    estudianteestudiante_id character varying(255) NOT NULL
);


ALTER TABLE public.ong_estudiante OWNER TO postgres;

--
-- TOC entry 271 (class 1259 OID 33710)
-- Name: ong_ong_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ong_ong_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ong_ong_id_seq OWNER TO postgres;

--
-- TOC entry 3724 (class 0 OID 0)
-- Dependencies: 271
-- Name: ong_ong_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ong_ong_id_seq OWNED BY public.ong.ong_id;


--
-- TOC entry 272 (class 1259 OID 33711)
-- Name: organismo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organismo (
    idorganismo integer NOT NULL,
    nombreorganismo character varying(255) NOT NULL,
    canceladoorganismo boolean NOT NULL
);


ALTER TABLE public.organismo OWNER TO postgres;

--
-- TOC entry 273 (class 1259 OID 33714)
-- Name: organismo_idorganismo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.organismo_idorganismo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organismo_idorganismo_seq OWNER TO postgres;

--
-- TOC entry 3725 (class 0 OID 0)
-- Dependencies: 273
-- Name: organismo_idorganismo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.organismo_idorganismo_seq OWNED BY public.organismo.idorganismo;


--
-- TOC entry 274 (class 1259 OID 33715)
-- Name: organizacion_politica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organizacion_politica (
    organizacion_politica_id integer NOT NULL,
    organizacion_politica_nombre character varying(255) NOT NULL,
    organizacion_politica_cancelado boolean NOT NULL
);


ALTER TABLE public.organizacion_politica OWNER TO postgres;

--
-- TOC entry 275 (class 1259 OID 33718)
-- Name: organizacion_politica_organizacion_politica_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.organizacion_politica_organizacion_politica_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organizacion_politica_organizacion_politica_id_seq OWNER TO postgres;

--
-- TOC entry 3726 (class 0 OID 0)
-- Dependencies: 275
-- Name: organizacion_politica_organizacion_politica_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.organizacion_politica_organizacion_politica_id_seq OWNED BY public.organizacion_politica.organizacion_politica_id;


--
-- TOC entry 276 (class 1259 OID 33719)
-- Name: organizacion_popular; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organizacion_popular (
    organizacion_popular_id integer NOT NULL,
    organizacion_popular_nombre character varying(255) NOT NULL,
    organizacion_popular_cancelado boolean NOT NULL
);


ALTER TABLE public.organizacion_popular OWNER TO postgres;

--
-- TOC entry 277 (class 1259 OID 33722)
-- Name: organizacion_popular_estudiante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organizacion_popular_estudiante (
    organizacion_popularorganizacion_popular_id integer NOT NULL,
    estudianteestudiante_id character varying(255) NOT NULL
);


ALTER TABLE public.organizacion_popular_estudiante OWNER TO postgres;

--
-- TOC entry 278 (class 1259 OID 33725)
-- Name: organizacion_popular_organizacion_popular_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.organizacion_popular_organizacion_popular_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organizacion_popular_organizacion_popular_id_seq OWNER TO postgres;

--
-- TOC entry 3727 (class 0 OID 0)
-- Dependencies: 278
-- Name: organizacion_popular_organizacion_popular_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.organizacion_popular_organizacion_popular_id_seq OWNED BY public.organizacion_popular.organizacion_popular_id;


--
-- TOC entry 279 (class 1259 OID 33726)
-- Name: pais; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pais (
    idpais integer NOT NULL,
    nombrepais character varying(255) NOT NULL,
    canceladopais boolean NOT NULL
);


ALTER TABLE public.pais OWNER TO postgres;

--
-- TOC entry 280 (class 1259 OID 33729)
-- Name: pais_idpais_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pais_idpais_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pais_idpais_seq OWNER TO postgres;

--
-- TOC entry 3728 (class 0 OID 0)
-- Dependencies: 280
-- Name: pais_idpais_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pais_idpais_seq OWNED BY public.pais.idpais;


--
-- TOC entry 281 (class 1259 OID 33730)
-- Name: planestudio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.planestudio (
    idplanestudio integer NOT NULL,
    plan_estudio_comentario character varying(1500) NOT NULL,
    plan_estudio_listo boolean NOT NULL,
    plan_estudio_cancelado boolean NOT NULL,
    cantoportunidades integer NOT NULL,
    carreraidcarrera integer NOT NULL,
    cursoactivacion character varying(255) NOT NULL,
    tipoplanestudionombretipoplanestudio character varying(255) NOT NULL
);


ALTER TABLE public.planestudio OWNER TO postgres;

--
-- TOC entry 282 (class 1259 OID 33735)
-- Name: planestudio_idplanestudio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.planestudio_idplanestudio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.planestudio_idplanestudio_seq OWNER TO postgres;

--
-- TOC entry 3729 (class 0 OID 0)
-- Dependencies: 282
-- Name: planestudio_idplanestudio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.planestudio_idplanestudio_seq OWNED BY public.planestudio.idplanestudio;


--
-- TOC entry 283 (class 1259 OID 33736)
-- Name: procedencia_escolar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.procedencia_escolar (
    procedencia_escolar_id integer NOT NULL,
    procedencia_escolar_nombre character varying(255) NOT NULL,
    procedencia_escolar_cancelado boolean NOT NULL
);


ALTER TABLE public.procedencia_escolar OWNER TO postgres;

--
-- TOC entry 284 (class 1259 OID 33739)
-- Name: procedencia_escolar_procedencia_escolar_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.procedencia_escolar_procedencia_escolar_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.procedencia_escolar_procedencia_escolar_id_seq OWNER TO postgres;

--
-- TOC entry 3730 (class 0 OID 0)
-- Dependencies: 284
-- Name: procedencia_escolar_procedencia_escolar_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.procedencia_escolar_procedencia_escolar_id_seq OWNED BY public.procedencia_escolar.procedencia_escolar_id;


--
-- TOC entry 285 (class 1259 OID 33740)
-- Name: provincia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.provincia (
    idprovincia integer NOT NULL,
    paisidpais integer NOT NULL,
    nombreprovincia character varying(255) NOT NULL,
    canceladoprovincia boolean NOT NULL
);


ALTER TABLE public.provincia OWNER TO postgres;

--
-- TOC entry 286 (class 1259 OID 33743)
-- Name: provincia_idprovincia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.provincia_idprovincia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.provincia_idprovincia_seq OWNER TO postgres;

--
-- TOC entry 3731 (class 0 OID 0)
-- Dependencies: 286
-- Name: provincia_idprovincia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.provincia_idprovincia_seq OWNED BY public.provincia.idprovincia;


--
-- TOC entry 287 (class 1259 OID 33744)
-- Name: sexo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sexo (
    sexo_id integer NOT NULL,
    sexo_nombre character varying(255) NOT NULL,
    sexo_cancelado boolean NOT NULL
);


ALTER TABLE public.sexo OWNER TO postgres;

--
-- TOC entry 288 (class 1259 OID 33747)
-- Name: sexo_sexo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sexo_sexo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sexo_sexo_id_seq OWNER TO postgres;

--
-- TOC entry 3732 (class 0 OID 0)
-- Dependencies: 288
-- Name: sexo_sexo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sexo_sexo_id_seq OWNED BY public.sexo.sexo_id;


--
-- TOC entry 289 (class 1259 OID 33748)
-- Name: sindicato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sindicato (
    idsindicato integer NOT NULL,
    nombresindicato character varying(255) NOT NULL,
    canceladosindicato boolean NOT NULL
);


ALTER TABLE public.sindicato OWNER TO postgres;

--
-- TOC entry 290 (class 1259 OID 33751)
-- Name: sindicato_idsindicato_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sindicato_idsindicato_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sindicato_idsindicato_seq OWNER TO postgres;

--
-- TOC entry 3733 (class 0 OID 0)
-- Dependencies: 290
-- Name: sindicato_idsindicato_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sindicato_idsindicato_seq OWNED BY public.sindicato.idsindicato;


--
-- TOC entry 291 (class 1259 OID 33752)
-- Name: tipo_asignatura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_asignatura (
    tipo_asignatura_id integer NOT NULL,
    tipo_asignatura_nombre character varying(255) NOT NULL,
    tipo_asignatura_cancelado boolean NOT NULL
);


ALTER TABLE public.tipo_asignatura OWNER TO postgres;

--
-- TOC entry 292 (class 1259 OID 33755)
-- Name: tipo_asignatura_tipo_asignatura_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_asignatura_tipo_asignatura_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_asignatura_tipo_asignatura_id_seq OWNER TO postgres;

--
-- TOC entry 3734 (class 0 OID 0)
-- Dependencies: 292
-- Name: tipo_asignatura_tipo_asignatura_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_asignatura_tipo_asignatura_id_seq OWNED BY public.tipo_asignatura.tipo_asignatura_id;


--
-- TOC entry 293 (class 1259 OID 33756)
-- Name: tipo_baja; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_baja (
    id_tipo_baja integer NOT NULL,
    nombre_tipo_baja character varying(255) NOT NULL,
    cancelado boolean NOT NULL
);


ALTER TABLE public.tipo_baja OWNER TO postgres;

--
-- TOC entry 294 (class 1259 OID 33759)
-- Name: tipo_baja_id_tipo_baja_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_baja_id_tipo_baja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_baja_id_tipo_baja_seq OWNER TO postgres;

--
-- TOC entry 3735 (class 0 OID 0)
-- Dependencies: 294
-- Name: tipo_baja_id_tipo_baja_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_baja_id_tipo_baja_seq OWNED BY public.tipo_baja.id_tipo_baja;


--
-- TOC entry 295 (class 1259 OID 33760)
-- Name: tipo_evaluacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_evaluacion (
    tipo_evaluacion_id integer NOT NULL,
    tipo_evaluacion_nombre character varying(255) NOT NULL,
    tipo_eavluacion_cancelado boolean NOT NULL
);


ALTER TABLE public.tipo_evaluacion OWNER TO postgres;

--
-- TOC entry 296 (class 1259 OID 33763)
-- Name: tipo_evaluacion_tipo_evaluacion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_evaluacion_tipo_evaluacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_evaluacion_tipo_evaluacion_id_seq OWNER TO postgres;

--
-- TOC entry 3736 (class 0 OID 0)
-- Dependencies: 296
-- Name: tipo_evaluacion_tipo_evaluacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_evaluacion_tipo_evaluacion_id_seq OWNED BY public.tipo_evaluacion.tipo_evaluacion_id;


--
-- TOC entry 297 (class 1259 OID 33764)
-- Name: tipo_militar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_militar (
    id_tipo_militar integer NOT NULL,
    nombre_tipo character varying(255) NOT NULL,
    cancelado_tipo_militar boolean NOT NULL
);


ALTER TABLE public.tipo_militar OWNER TO postgres;

--
-- TOC entry 298 (class 1259 OID 33767)
-- Name: tipo_militar_id_tipo_militar_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_militar_id_tipo_militar_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_militar_id_tipo_militar_seq OWNER TO postgres;

--
-- TOC entry 3737 (class 0 OID 0)
-- Dependencies: 298
-- Name: tipo_militar_id_tipo_militar_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_militar_id_tipo_militar_seq OWNED BY public.tipo_militar.id_tipo_militar;


--
-- TOC entry 299 (class 1259 OID 33768)
-- Name: tipoplanestudio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipoplanestudio (
    nombretipoplanestudio character varying(255) NOT NULL,
    tipoplanestudiocancelado boolean NOT NULL,
    cancelado boolean NOT NULL
);


ALTER TABLE public.tipoplanestudio OWNER TO postgres;

--
-- TOC entry 300 (class 1259 OID 33771)
-- Name: tutor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tutor (
    tutor_id integer NOT NULL,
    nombre_tutor character varying(255) NOT NULL,
    apellidos_tutor character varying(255) NOT NULL,
    salario_tutor real,
    organizacion_politicaorganizacion_politica_id integer NOT NULL,
    nivel_escolarnivel_escolar_id integer NOT NULL,
    ocupacionocupacion_id integer NOT NULL,
    fallecido boolean NOT NULL,
    sexosexo_id integer NOT NULL,
    estudianteestudiante_id character varying(255) NOT NULL
);


ALTER TABLE public.tutor OWNER TO postgres;

--
-- TOC entry 301 (class 1259 OID 33774)
-- Name: tutor_tutor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tutor_tutor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tutor_tutor_id_seq OWNER TO postgres;

--
-- TOC entry 3738 (class 0 OID 0)
-- Dependencies: 301
-- Name: tutor_tutor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tutor_tutor_id_seq OWNED BY public.tutor.tutor_id;


--
-- TOC entry 302 (class 1259 OID 33775)
-- Name: universidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.universidad (
    codigouniversidad character varying(255) NOT NULL,
    nombreuniversidad character varying(255) NOT NULL,
    inicialesuniversidad character varying(255) NOT NULL,
    actividaduniversidad character varying(255),
    telefonouniversidad character varying(255),
    reglamentouniversidad character varying(255) NOT NULL,
    faxuniversidad character varying(255),
    nombrerectoruniversidad character varying(255) NOT NULL,
    nombresecgraluniversidad character varying(255) NOT NULL,
    direccionuniversidad character varying(255) NOT NULL,
    canceladouniversidad boolean NOT NULL,
    municipioidmunicipio integer NOT NULL
);


ALTER TABLE public.universidad OWNER TO postgres;

--
-- TOC entry 303 (class 1259 OID 33780)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    username character varying(50) NOT NULL,
    identificacion character varying(255) NOT NULL,
    nombre character varying(255) NOT NULL,
    apellido1 character varying(255) NOT NULL,
    apellido2 character varying(255),
    email character varying(255) NOT NULL,
    password character varying(80) NOT NULL,
    enabled boolean NOT NULL,
    descripcion character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 3434 (class 2604 OID 33783)
-- Name: asignatura asignatura_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asignatura ALTER COLUMN asignatura_id SET DEFAULT nextval('public.asignatura_asignatura_id_seq'::regclass);


--
-- TOC entry 3435 (class 2604 OID 33784)
-- Name: carrera idcarrera; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carrera ALTER COLUMN idcarrera SET DEFAULT nextval('public.carrera_idcarrera_seq'::regclass);


--
-- TOC entry 3436 (class 2604 OID 33785)
-- Name: carreranacional idcarreranacional; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carreranacional ALTER COLUMN idcarreranacional SET DEFAULT nextval('public.carreranacional_idcarreranacional_seq'::regclass);


--
-- TOC entry 3437 (class 2604 OID 33786)
-- Name: color_piel color_piel_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.color_piel ALTER COLUMN color_piel_id SET DEFAULT nextval('public.color_piel_color_piel_id_seq'::regclass);


--
-- TOC entry 3438 (class 2604 OID 33787)
-- Name: especialidad idespecialidad; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.especialidad ALTER COLUMN idespecialidad SET DEFAULT nextval('public.especialidad_idespecialidad_seq'::regclass);


--
-- TOC entry 3439 (class 2604 OID 33788)
-- Name: especialidad_militar especialidad_militar_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.especialidad_militar ALTER COLUMN especialidad_militar_id SET DEFAULT nextval('public.especialidad_militar_especialidad_militar_id_seq'::regclass);


--
-- TOC entry 3440 (class 2604 OID 33789)
-- Name: estado_civil estado_civil_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_civil ALTER COLUMN estado_civil_id SET DEFAULT nextval('public.estado_civil_estado_civil_id_seq'::regclass);


--
-- TOC entry 3441 (class 2604 OID 33790)
-- Name: estado_estudiante estado_estuciante_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_estudiante ALTER COLUMN estado_estuciante_id SET DEFAULT nextval('public.estado_estudiante_estado_estuciante_id_seq'::regclass);


--
-- TOC entry 3443 (class 2604 OID 33791)
-- Name: examen examen_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.examen ALTER COLUMN examen_id SET DEFAULT nextval('public.examen_examen_id_seq'::regclass);


--
-- TOC entry 3444 (class 2604 OID 33792)
-- Name: fuente_ingreso fuente_ingreso_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fuente_ingreso ALTER COLUMN fuente_ingreso_id SET DEFAULT nextval('public.fuente_ingreso_fuente_ingreso_id_seq'::regclass);


--
-- TOC entry 3445 (class 2604 OID 33793)
-- Name: grado_militar grado_militar_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grado_militar ALTER COLUMN grado_militar_id SET DEFAULT nextval('public.grado_militar_grado_militar_id_seq'::regclass);


--
-- TOC entry 3446 (class 2604 OID 33794)
-- Name: huerfano huerfano_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.huerfano ALTER COLUMN huerfano_id SET DEFAULT nextval('public.huerfano_huerfano_id_seq'::regclass);


--
-- TOC entry 3447 (class 2604 OID 33795)
-- Name: matricula matricula_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matricula ALTER COLUMN matricula_id SET DEFAULT nextval('public.matricula_matricula_id_seq'::regclass);


--
-- TOC entry 3448 (class 2604 OID 33796)
-- Name: minusvalia minusvalia_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.minusvalia ALTER COLUMN minusvalia_id SET DEFAULT nextval('public.minusvalia_minusvalia_id_seq'::regclass);


--
-- TOC entry 3449 (class 2604 OID 33797)
-- Name: motivo_baja id_motivo_baja; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motivo_baja ALTER COLUMN id_motivo_baja SET DEFAULT nextval('public.motivo_baja_id_motivo_baja_seq'::regclass);


--
-- TOC entry 3450 (class 2604 OID 33798)
-- Name: municipio idmunicipio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.municipio ALTER COLUMN idmunicipio SET DEFAULT nextval('public.municipio_idmunicipio_seq'::regclass);


--
-- TOC entry 3451 (class 2604 OID 33799)
-- Name: nivel_escolar nivel_escolar_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nivel_escolar ALTER COLUMN nivel_escolar_id SET DEFAULT nextval('public.nivel_escolar_nivel_escolar_id_seq'::regclass);


--
-- TOC entry 3452 (class 2604 OID 33800)
-- Name: ocupacion ocupacion_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ocupacion ALTER COLUMN ocupacion_id SET DEFAULT nextval('public.ocupacion_ocupacion_id_seq'::regclass);


--
-- TOC entry 3453 (class 2604 OID 33801)
-- Name: ong ong_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ong ALTER COLUMN ong_id SET DEFAULT nextval('public.ong_ong_id_seq'::regclass);


--
-- TOC entry 3454 (class 2604 OID 33802)
-- Name: organismo idorganismo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organismo ALTER COLUMN idorganismo SET DEFAULT nextval('public.organismo_idorganismo_seq'::regclass);


--
-- TOC entry 3455 (class 2604 OID 33803)
-- Name: organizacion_politica organizacion_politica_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizacion_politica ALTER COLUMN organizacion_politica_id SET DEFAULT nextval('public.organizacion_politica_organizacion_politica_id_seq'::regclass);


--
-- TOC entry 3456 (class 2604 OID 33804)
-- Name: organizacion_popular organizacion_popular_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizacion_popular ALTER COLUMN organizacion_popular_id SET DEFAULT nextval('public.organizacion_popular_organizacion_popular_id_seq'::regclass);


--
-- TOC entry 3457 (class 2604 OID 33805)
-- Name: pais idpais; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pais ALTER COLUMN idpais SET DEFAULT nextval('public.pais_idpais_seq'::regclass);


--
-- TOC entry 3458 (class 2604 OID 33806)
-- Name: planestudio idplanestudio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planestudio ALTER COLUMN idplanestudio SET DEFAULT nextval('public.planestudio_idplanestudio_seq'::regclass);


--
-- TOC entry 3459 (class 2604 OID 33807)
-- Name: procedencia_escolar procedencia_escolar_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.procedencia_escolar ALTER COLUMN procedencia_escolar_id SET DEFAULT nextval('public.procedencia_escolar_procedencia_escolar_id_seq'::regclass);


--
-- TOC entry 3460 (class 2604 OID 33808)
-- Name: provincia idprovincia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.provincia ALTER COLUMN idprovincia SET DEFAULT nextval('public.provincia_idprovincia_seq'::regclass);


--
-- TOC entry 3461 (class 2604 OID 33809)
-- Name: sexo sexo_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sexo ALTER COLUMN sexo_id SET DEFAULT nextval('public.sexo_sexo_id_seq'::regclass);


--
-- TOC entry 3462 (class 2604 OID 33810)
-- Name: sindicato idsindicato; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sindicato ALTER COLUMN idsindicato SET DEFAULT nextval('public.sindicato_idsindicato_seq'::regclass);


--
-- TOC entry 3463 (class 2604 OID 33811)
-- Name: tipo_asignatura tipo_asignatura_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_asignatura ALTER COLUMN tipo_asignatura_id SET DEFAULT nextval('public.tipo_asignatura_tipo_asignatura_id_seq'::regclass);


--
-- TOC entry 3464 (class 2604 OID 33812)
-- Name: tipo_baja id_tipo_baja; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_baja ALTER COLUMN id_tipo_baja SET DEFAULT nextval('public.tipo_baja_id_tipo_baja_seq'::regclass);


--
-- TOC entry 3465 (class 2604 OID 33813)
-- Name: tipo_evaluacion tipo_evaluacion_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_evaluacion ALTER COLUMN tipo_evaluacion_id SET DEFAULT nextval('public.tipo_evaluacion_tipo_evaluacion_id_seq'::regclass);


--
-- TOC entry 3466 (class 2604 OID 33814)
-- Name: tipo_militar id_tipo_militar; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_militar ALTER COLUMN id_tipo_militar SET DEFAULT nextval('public.tipo_militar_id_tipo_militar_seq'::regclass);


--
-- TOC entry 3467 (class 2604 OID 33815)
-- Name: tutor tutor_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tutor ALTER COLUMN tutor_id SET DEFAULT nextval('public.tutor_tutor_id_seq'::regclass);


--
-- TOC entry 3610 (class 0 OID 33568)
-- Dependencies: 214
-- Data for Name: asignatura; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3611 (class 0 OID 33571)
-- Dependencies: 215
-- Data for Name: asignatura_asignatura; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3613 (class 0 OID 33575)
-- Dependencies: 217
-- Data for Name: authorities; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.authorities VALUES ('ead', 'ROLE_ADMIN');


--
-- TOC entry 3614 (class 0 OID 33578)
-- Dependencies: 218
-- Data for Name: baja; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3615 (class 0 OID 33581)
-- Dependencies: 219
-- Data for Name: carrera; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3616 (class 0 OID 33584)
-- Dependencies: 220
-- Data for Name: carrera_curso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3618 (class 0 OID 33588)
-- Dependencies: 222
-- Data for Name: carreranacional; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.carreranacional VALUES (1, '041600', 'Ingenier�a Forestal', NULL, false, 4);
INSERT INTO public.carreranacional VALUES (2, '011600', 'Ingenier�a Biom�dica', NULL, false, 1);
INSERT INTO public.carreranacional VALUES (3, '063700', 'Lengua Inglesa (Con segunda Lengua Extranjera)', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (4, '023001', 'F�sica N�clear Aplicada', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (5, '022100', 'Matem�tica', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (6, '063501', 'Periodismo', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (7, '100500', 'Comunicaci�n Audiovisual', NULL, false, 9);
INSERT INTO public.carreranacional VALUES (8, '064000', 'Letras', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (9, '030100', 'Medicina', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (10, '064801', 'Lengua Inglesa (Preparatoria)', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (11, '064900', 'Relaciones Internacionales', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (12, '022200', 'F�sica', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (13, '022000', 'Ciencias de la Computaci�n', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (14, '022300', 'Qu�mica', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (15, '051800', 'Econom�a', NULL, false, 5);
INSERT INTO public.carreranacional VALUES (16, '010200', 'Ingenier�a en Minas', NULL, false, 1);
INSERT INTO public.carreranacional VALUES (17, '011401', 'Dise�o Industrial', NULL, false, 1);
INSERT INTO public.carreranacional VALUES (18, '022400', 'Geograf�a', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (19, '063100', 'Historia', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (20, '030300', 'Enfermer�a', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (21, '100400', 'Arte Teatral', NULL, false, 9);
INSERT INTO public.carreranacional VALUES (22, '064600', 'Lengua de se�as', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (23, '100200', 'Artes pl�sticas', NULL, false, 9);
INSERT INTO public.carreranacional VALUES (24, '042000', 'Ingenier�a Agr�cola', NULL, false, 4);
INSERT INTO public.carreranacional VALUES (25, '064700', 'Preservaci�n y Gesti�n del Patrimonio Hist�rico Cultural', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (26, '064200', 'Sociolog�a', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (27, '063800', 'Lengua Alemana (Con segunda Lengua Extranjera)', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (28, '041502', 'Ingenier�a Agropecuaria', NULL, false, 4);
INSERT INTO public.carreranacional VALUES (29, '100100', 'M�sica', NULL, false, 9);
INSERT INTO public.carreranacional VALUES (30, '022600', 'Biolog�a', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (31, '063900', 'Lengua Francesa (Con segunda Lengua Extranjera)', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (32, '011700', 'Ingenier�a F�sica', NULL, false, 1);
INSERT INTO public.carreranacional VALUES (33, '041500', 'Agronom�a', NULL, false, 4);
INSERT INTO public.carreranacional VALUES (34, '010400', 'Ingenier�a Mec�nica', 'Ingeniero Mec�nico', false, 1);
INSERT INTO public.carreranacional VALUES (35, '011000', 'Ingenier�a Industrial', 'Ingeniero Industrial', false, 1);
INSERT INTO public.carreranacional VALUES (36, '010500', 'Ingenier�a El�ctrica', 'Ingeniero Electricista', false, 1);
INSERT INTO public.carreranacional VALUES (37, '010700', 'Ingenier�a en Telecomunicaciones y Electr�nica', 'Ingeniero en Telecomunicaciones y Electr�nica', false, 1);
INSERT INTO public.carreranacional VALUES (38, '010900', 'Ingenier�a Qu�mica', 'Ingeniero Qu�mico', false, 1);
INSERT INTO public.carreranacional VALUES (39, '010800', 'Ingenier�a Inform�tica', 'Ingeniero Inform�tico', false, 1);
INSERT INTO public.carreranacional VALUES (40, '011100', 'Ingenier�a Hidr�ulica', 'Ingeniero Hidr�ulico', false, 1);
INSERT INTO public.carreranacional VALUES (41, '011001', 'Organizaci�n de Empresas', 'Ingeniero Industrial Especializaci�n Organizaci�n de Empresas', false, 1);
INSERT INTO public.carreranacional VALUES (42, '072400', 'Educaci�n Especial', 'Licenciado en Educaci�n Especial', false, 7);
INSERT INTO public.carreranacional VALUES (43, '072800', 'Educaci�n Primaria', 'Licenciado en Educaci�n Primaria', false, 7);
INSERT INTO public.carreranacional VALUES (44, '073500', 'Educaci�n Agropecuaria', 'Licenciado en Educaci�n Agropecuaria', false, 7);
INSERT INTO public.carreranacional VALUES (45, '072200', 'Educaci�n Econom�a', 'Licenciado en Educaci�n Econom�a', false, 7);
INSERT INTO public.carreranacional VALUES (46, '073400', 'Educaci�n Mec�nica', 'Licenciado en Educaci�n Mec�nica', false, 7);
INSERT INTO public.carreranacional VALUES (47, '064800', 'Lengua Espa�ola para Estudiantes no Hispanohablantes', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (48, '020302', 'Radioqu�mica', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (49, '011500', 'Dise�o de Comunicaci�n Visual', NULL, false, 1);
INSERT INTO public.carreranacional VALUES (50, '022900', 'Ciencias Farmac�uticas', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (51, '100300', 'Arte Danzario', NULL, false, 9);
INSERT INTO public.carreranacional VALUES (52, '022700', 'Microbiolog�a', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (53, '030200', 'Estomatolog�a', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (54, '063600', 'Lengua Rusa (Con segunda Lengua Extranjera)', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (55, '022801', 'Ciencias Alimentarias', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (56, '041900', 'Ingenier�a en Mecanizaci�n de la Producci�n Agropecuaria', NULL, false, 4);
INSERT INTO public.carreranacional VALUES (57, '030400', 'Tecnolog�a de la salud', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (58, '074200', 'Educaci�n Mecanizaci�n', NULL, false, 7);
INSERT INTO public.carreranacional VALUES (59, '063300', 'Historia del Arte', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (60, '064400', 'Estudios Socioculturales', 'Licenciado en Estudios Socioculturales', false, 6);
INSERT INTO public.carreranacional VALUES (61, '071300', 'Educaci�n Lengua Extranjera (Ingl�s)', 'Licenciado en Educaci�n Especialidad Lengua Extranjera (Ingl�s)', false, 7);
INSERT INTO public.carreranacional VALUES (62, '063400', 'Derecho', 'Licenciado en Derecho', false, 6);
INSERT INTO public.carreranacional VALUES (63, '051900', 'Contabilidad y Finanzas', 'Licenciado en Contabilidad y Finanzas', false, 5);
INSERT INTO public.carreranacional VALUES (64, '064300', 'Psicolog�a', 'Licenciado en Psicolog�a', false, 6);
INSERT INTO public.carreranacional VALUES (65, '063500', 'Comunicaci�n Social', 'Licenciado en Comunicaci�n Social', false, 6);
INSERT INTO public.carreranacional VALUES (66, '023100', 'Meteorolog�a', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (67, '011900', 'Ingenieria Geofisica', NULL, false, 1);
INSERT INTO public.carreranacional VALUES (68, '052000', 'Turismo', NULL, false, 5);
INSERT INTO public.carreranacional VALUES (69, '090300', 'Cultura F�sica', 'Licenciado en Cultura F�sica', false, 8);
INSERT INTO public.carreranacional VALUES (70, '074100', 'Educaci�n Logopedia', 'Licenciado en Educaci�n Logopedia', false, 7);
INSERT INTO public.carreranacional VALUES (71, '071400', 'Educaci�n Construcci�n', 'Licenciado en Educaci�n Construcci�n', false, 7);
INSERT INTO public.carreranacional VALUES (72, '073300', 'Educaci�n El�ctrica', 'Licenciado en Educaci�n El�ctrica', false, 7);
INSERT INTO public.carreranacional VALUES (73, '073600', 'Educaci�n Pedagog�a-Psicolog�a', 'Licenciado en Educaci�n Pedagog�a-Psicolog�a', false, 7);
INSERT INTO public.carreranacional VALUES (74, '074300', 'Educaci�n Instructor de Arte', 'Licenciado en Instructor de Arte', false, 7);
INSERT INTO public.carreranacional VALUES (75, '074400', 'Educaci�n Matem�tica-F�sica', 'Licenciado en Educaci�n Matem�tica -F�sica', false, 7);
INSERT INTO public.carreranacional VALUES (76, '074500', 'Educaci�n Biolog�a-Qu�mica', 'Licenciado en Educaci�n Biolog�a -Geograf�a', false, 7);
INSERT INTO public.carreranacional VALUES (77, '074600', 'Educaci�n Biolog�a-Geograf�a', 'Licenciado en Educaci�n Biolog�a -Qu�mica', false, 7);
INSERT INTO public.carreranacional VALUES (78, '074900', 'Educaci�n Espa�ol-Literatura', 'Licenciado en Educaci�n Espa�ol-Literatura', false, 7);
INSERT INTO public.carreranacional VALUES (79, '010100', 'Ingenier�a Geol�gica', NULL, false, 1);
INSERT INTO public.carreranacional VALUES (80, '010300', 'Ingenier�a en Metalurgia y Materiales', 'Ingeniero Metal�rgico', false, 1);
INSERT INTO public.carreranacional VALUES (81, '010600', 'Ingenier�a Autom�tica', 'Ingeniero en Autom�tica', false, 1);
INSERT INTO public.carreranacional VALUES (82, '011200', 'Ingenier�a Civil', 'Ingeniero Civil', false, 1);
INSERT INTO public.carreranacional VALUES (83, '011300', 'Arquitectura y Urbanismo', 'Arquitecto', false, 1);
INSERT INTO public.carreranacional VALUES (84, '011800', 'Ingenier�a en Ciencias Inform�ticas', NULL, false, 1);
INSERT INTO public.carreranacional VALUES (85, '022500', 'Bioqu�mica y Biolog�a Molecular', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (86, '023003', 'Ingenier�a en Tecnolog�as Nucleares y Energ�ticas', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (87, '063200', 'Filosof�a Marxista Leninista', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (88, '071100', 'Educaci�n Preescolar', 'Licenciado en Educaci�n Preescolar', false, 7);
INSERT INTO public.carreranacional VALUES (89, '030401', 'Bioan�lisis Cl�nico', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (90, '030402', 'Imagenolog�a y Radiof�sica M�dica', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (91, '030403', 'Optometr�a y �ptica', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (92, '030404', 'Rehabilitaci�n en Salud', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (93, '030405', 'Nutrici�n', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (94, '030406', 'Higiene y Epidemiolog�a', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (95, '030407', 'Logofonoaudiolog�a', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (96, '030408', 'Sistema de Informaci�n en Salud', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (97, '063601', 'Lengua Rusa (Preparatoria)', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (98, '063801', 'Lengua Alemana (Preparatoria)', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (99, '063901', 'Lengua Francesa (Preparatoria)', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (100, '064102', 'Ciencias de la Informaci�n', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (101, '070300', 'Educaci�n Lengua Inglesa', 'Licenciado en Educaci�n Lengua Extranjera (Ingl�s)', false, 7);
INSERT INTO public.carreranacional VALUES (102, '074700', 'Educaci�n Laboral-Inform�tica', 'Licenciado en Educaci�n Espa�ol-Literatura', false, 7);
INSERT INTO public.carreranacional VALUES (103, '074800', 'Educaci�n Qu�mica Industrial', 'Licenciado en Educaci�n Educaci�n Laboral-Inform�tica', false, 7);
INSERT INTO public.carreranacional VALUES (104, '074901', 'Educaci�n Inform�tica', NULL, false, 7);
INSERT INTO public.carreranacional VALUES (105, '041800', 'Ingenier�a en Procesos Agroindustriales', 'Ingeniero en Procesos Agroindustriales', false, 4);
INSERT INTO public.carreranacional VALUES (106, '070200', 'Educaci�n Marxismo Leninismo e Historia', 'Licenciado en Educaci�n Marxismo Leninismo e Historia', false, 7);
INSERT INTO public.carreranacional VALUES (107, '041700', 'Medicina Veterinaria y Zootecnia', 'Doctor en Medicina Veterinaria y Zootecnia', false, 4);
INSERT INTO public.carreranacional VALUES (108, '075400', 'Educaci�n Qu�mica', NULL, false, 7);
INSERT INTO public.carreranacional VALUES (109, '075700', 'Educaci�n Laboral', NULL, false, 7);
INSERT INTO public.carreranacional VALUES (110, '075500', 'Educaci�n Geograf�a', NULL, false, 7);
INSERT INTO public.carreranacional VALUES (111, '075300', 'Educaci�n Biolog�a', NULL, false, 7);
INSERT INTO public.carreranacional VALUES (112, '075100', 'Educaci�n Matem�tica', NULL, false, 7);
INSERT INTO public.carreranacional VALUES (113, '075200', 'Educaci�n en F�sica', NULL, false, 7);
INSERT INTO public.carreranacional VALUES (114, '075000', 'Educaci�n Art�stica', 'Licenciado en Educaci�n Art�stica', false, 7);
INSERT INTO public.carreranacional VALUES (115, '070301', 'Educaci�n Lengua Extranjera: Ingl�s para profesores de Educaci�n Superior', NULL, false, 7);
INSERT INTO public.carreranacional VALUES (116, '064401', 'Gesti�n Sociocultural para el Desarrollo', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (117, '041702', 'Medicina Veterinaria', NULL, false, 4);
INSERT INTO public.carreranacional VALUES (118, '001501', 'T�cnico Superior en Administraci�n de Redes y Seguridad Inform�tica (ARSI)', '', false, 1);
INSERT INTO public.carreranacional VALUES (119, '001533', 'T�cnico Superior en Agua y Saneamiento', '', false, 1);
INSERT INTO public.carreranacional VALUES (120, '001534', 'T�cnico Superior en Electromec�nica de equipos ferroviarios', '', false, 1);
INSERT INTO public.carreranacional VALUES (121, '001535', 'T�cnico Superior en Geolog�a', '', false, 1);
INSERT INTO public.carreranacional VALUES (122, '001537', 'T�cnico Superior en Gesti�n de la infraestructura ferroviaria', '', false, 1);
INSERT INTO public.carreranacional VALUES (123, '001540', 'T�cnico Superior en Montaje Industrial', '', false, 1);
INSERT INTO public.carreranacional VALUES (124, '001541', 'T�cnico Superior en Riego y Drenaje', '', false, 1);
INSERT INTO public.carreranacional VALUES (125, '001542', 'T�cnico Superior en Transporte Automotor', '', false, 1);
INSERT INTO public.carreranacional VALUES (126, '005529', 'T�cnico Superior en Comercio Sostenible', '', false, 5);
INSERT INTO public.carreranacional VALUES (127, '005530', 'T�cnico Superior en Log�stica', '', false, 5);
INSERT INTO public.carreranacional VALUES (128, '005531', 'T�cnico Superior en Asistencia Tur�stica', '', false, 5);
INSERT INTO public.carreranacional VALUES (129, '005539', 'T�cnico Superior en Mantenimiento para el Turismo', '', false, 5);
INSERT INTO public.carreranacional VALUES (130, '005544', 'T�cnico Superior en Auditor�a', '', false, 5);
INSERT INTO public.carreranacional VALUES (131, '006536', 'T�cnico Superior en Gesti�n de la Administraci�n P�blica', '', false, 6);
INSERT INTO public.carreranacional VALUES (132, '006538', 'T�cnico Superior en Interpretaci�n de la Lengua de Se�as Cubanas', '', false, 6);
INSERT INTO public.carreranacional VALUES (133, '006543', 'T�cnico Superior en Gesti�n del Desarrollo Local Sostenible', '', false, 6);
INSERT INTO public.carreranacional VALUES (134, '007502', 'Profesor de Matem�tica para Secundaria B�sica', '', false, 7);
INSERT INTO public.carreranacional VALUES (135, '007503', 'Profesor de F�sica para Secundaria B�sica', '', false, 7);
INSERT INTO public.carreranacional VALUES (136, '007504', 'Profesor de Qu�mica para Secundaria B�sica', '', false, 7);
INSERT INTO public.carreranacional VALUES (137, '007505', 'Profesor de Biolog�a para Secundaria B�sica', '', false, 7);
INSERT INTO public.carreranacional VALUES (138, '007506', 'Profesor de Geograf�a para Secundaria B�sica', '', false, 7);
INSERT INTO public.carreranacional VALUES (139, '007507', 'Profesor de Educaci�n Laboral para Secundaria B�sica', '', false, 7);
INSERT INTO public.carreranacional VALUES (140, '007508', 'Profesor de Espa�ol para Secundaria B�sica', '', false, 7);
INSERT INTO public.carreranacional VALUES (141, '007509', 'Profesor de Historia y Educaci�n Moral y Ciudadana para Secundaria B�sica', '', false, 7);
INSERT INTO public.carreranacional VALUES (142, '007527', 'Profesor de Educaci�n Art�stica para Secundaria B�sica', '', false, 7);
INSERT INTO public.carreranacional VALUES (143, '009528', 'T�cnico Superior en Entrenamiento Deportivo', '', false, 8);
INSERT INTO public.carreranacional VALUES (144, '011802', 'Ingenier�a en Ciberseguridad', NULL, false, 1);
INSERT INTO public.carreranacional VALUES (145, '022701', 'Microbiolog�a y Virolog�a', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (146, '023004', 'F�sica Nuclear', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (147, '063201', 'Filosof�a', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (148, '100700', 'Artes Visuales', NULL, false, 9);
INSERT INTO public.carreranacional VALUES (149, '100800', 'Arte de los Medios de Comunicaci�n Visual', NULL, false, 9);
INSERT INTO public.carreranacional VALUES (150, '001546', 'T�cnico Superior Profesor de El�ctrica', '', false, 1);
INSERT INTO public.carreranacional VALUES (151, '001547', 'T�cnico Superior Profesor de Mec�nica', '', false, 1);
INSERT INTO public.carreranacional VALUES (152, '001548', 'T�cnico Superior Profesor de Mecanizaci�n', '', false, 1);
INSERT INTO public.carreranacional VALUES (153, '004550', 'T�cnico Superior Profesor de Agropecuaria', '', false, 4);
INSERT INTO public.carreranacional VALUES (154, '005551', 'T�cnico Superior Profesor de Econom�a', '', false, 5);
INSERT INTO public.carreranacional VALUES (155, '001549', 'T�cnico Superior Profesor de Construcci�n', '', false, 1);
INSERT INTO public.carreranacional VALUES (156, '006552', 'T�cnico Superior Profesor de Interpretaci�n de se�as Cubanas', '', false, 6);
INSERT INTO public.carreranacional VALUES (157, '008553', 'Ingeniero en Equipos Radioelectr�nicos de Aviaci�n', '', false, 12);
INSERT INTO public.carreranacional VALUES (158, '008554', 'Ingeniero en Medios T�cnicos de aseguramiento de Aer�dromos', '', false, 12);
INSERT INTO public.carreranacional VALUES (159, '008855', 'Ingeniero en Telecomunicaciones CACSA', '', false, 12);
INSERT INTO public.carreranacional VALUES (160, '005556', 'T�cnico Superior en Administraci�n Tributaria', '', false, 5);
INSERT INTO public.carreranacional VALUES (161, '001557', 'T�cnico Superior de Tecnolog�a para el Montaje Industrial', '', false, 1);
INSERT INTO public.carreranacional VALUES (162, '005558', 'T�cnico Superior en Servicios T�cnicos a Instalaciones Tur�sticas', '', false, 5);
INSERT INTO public.carreranacional VALUES (163, '001559', 'T�cnico Superior en Explotaci�n y Mantenimiento del Transporte Automotor', '', false, 1);
INSERT INTO public.carreranacional VALUES (164, '008560', 'Ingeniero en radioelectr�nico en equipos electro autom�ticos de aviaci�n', '', false, 12);
INSERT INTO public.carreranacional VALUES (165, '008561', 'T�cnico Superior en Radioelectr�nico en equipos electro autom�ticos de aviaci�n', '', false, 12);
INSERT INTO public.carreranacional VALUES (166, '008562', 'T�cnico Superior Mec�nico Aeron�utico en Motores y Estructuras', '', false, 12);
INSERT INTO public.carreranacional VALUES (167, '008563', 'T�cnico Superior Mec�nico en Aseguramiento T�cnico en Equipos Especiales', '', false, 12);
INSERT INTO public.carreranacional VALUES (168, '003524', 'T�cnico Superior en Trabajo Social', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (169, '004551', 'T�cnico Superior Agroindustrial en Tabaco', NULL, false, 4);
INSERT INTO public.carreranacional VALUES (170, '006532', 'T�cnico Superior en Asistencia Jur�dica', '', false, 6);
INSERT INTO public.carreranacional VALUES (171, '005548', 'T�cnico Superior en Riesgo de Impago en el Turismo', NULL, false, 5);
INSERT INTO public.carreranacional VALUES (172, '002549', 'T�cnico Superior en Procesos Qu�micos', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (173, '001558', 'T�cnico Superior en Metrolog�a', NULL, false, 1);
INSERT INTO public.carreranacional VALUES (174, '012100', 'Ingenier�a en Transporte', NULL, false, 1);
INSERT INTO public.carreranacional VALUES (175, '022001', 'Ciencia de Datos', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (176, '030409', 'Servicios Estomatol�gicos', NULL, false, 3);
INSERT INTO public.carreranacional VALUES (177, '002555', 'T�cnico Superior en Gesti�n de Residuos S�lidos Urbanos e Industriales', NULL, false, 2);
INSERT INTO public.carreranacional VALUES (178, '004554', 'T�cnico Superior en Gesti�n de la Innovaci�n Agraria', NULL, false, 4);
INSERT INTO public.carreranacional VALUES (179, '006556', 'T�cnico Superior en Gesti�n y Desarrollo Cooperativo Agropecuario', NULL, false, 6);
INSERT INTO public.carreranacional VALUES (180, '005552', 'T�cnico Superior en Comercio Agropecuario', NULL, false, 5);


--
-- TOC entry 3620 (class 0 OID 33592)
-- Dependencies: 224
-- Data for Name: color_piel; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.color_piel VALUES (1, 'Blanco', false);
INSERT INTO public.color_piel VALUES (2, 'Negro', false);
INSERT INTO public.color_piel VALUES (3, 'Mestizo (Mulato)', false);
INSERT INTO public.color_piel VALUES (4, 'Amarillo', false);


--
-- TOC entry 3622 (class 0 OID 33596)
-- Dependencies: 226
-- Data for Name: cum; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cum VALUES ('', '', '', '', '', '', '', '', '', '', false, '', -1);
INSERT INTO public.cum VALUES ('06815.0211', 'CUM Nueva Paz', 'Docente - Investigativa', 'CUM Nueva Paz', 'OLPP calle 12 esq 15', '', '', 'Neysi Molina Borges', 'Lic. Olga Lidia Rodr�guez S�nchez', 'Continuidad de Estudios', false, '223.0.06815', 92);
INSERT INTO public.cum VALUES ('06815.0216', 'CUM Quivic�n', 'Docente - Investigativa', 'CUM Quivic�n', '', '', '', '', '', 'Continuidad de Estudios', false, '223.0.06815', 97);
INSERT INTO public.cum VALUES ('06815.0209', 'CUM Santa Cruz', 'Docente - Investigativa', 'CUM Santa Cruz del Norte', 'Escuela MINAZ Carretera CAI Camilo Cienfuegos', '', '', 'Lic. Adriana Sosa Gonz�lez', 'Lic. Miguel Ram�n Garc�a Guzm�n', 'Continuidad de Estudios', false, '223.0.06815', 90);
INSERT INTO public.cum VALUES ('06815.0207', 'CUM San Jos�', 'Docente - Investigativa', 'CUM San Jos� de las Lajas', '', '', '', 'Aleiny Ojito Mart�nez', 'Lic. Edaida Rivero Palenzuela', 'Continuidad de Estudios', false, '223.0.06815', 88);
INSERT INTO public.cum VALUES ('0221', 'CUM Bejucal', 'Docente - Investigativa', 'CUM Bejucal', 'Calle 4 # 1706 e/ 17 y 19', '', '', '', '', 'Continuidad de Estudios', false, '223.0.06815', 87);
INSERT INTO public.cum VALUES ('06815.0215', 'CUM Bataban�', 'Docente - Investigativa', 'CUM Bataban�', '', '', '', 'Georgi Licea P�rez', 'MsC. Marl�n Tabares Mesa', 'Continuidad de Estudios', false, '223.0.06815', 96);
INSERT INTO public.cum VALUES ('06815.0212', 'CUM San Nicol�s', 'Docente - Investigativa', 'CUM San Nicol�s de Bari', 'Ave 63 #4825 %48 y 52', '', '', '', 'MsC. Artemio Mart�nez Rosa', 'Continuidad de Estudios', false, '223.0.06815', 93);
INSERT INTO public.cum VALUES ('06815.0213', 'CUM Guines', 'Docente - Investigativa', 'CUM Guines', 'Ave 91 e/ 74 y 76', '', '', '', 'Elizabeth P�rez Mateu', 'Continuidad de Estudios', false, '223.0.06815', 94);
INSERT INTO public.cum VALUES ('06815.0214', 'CUM Melena ', 'Docente - Investigativa', 'CUM Melena del Sur', '', '', '', 'Lic. Syliany Salda�a Fumero', '', 'Continuidad de Estudios', false, '223.0.06815', 95);
INSERT INTO public.cum VALUES ('06815.0210', 'CUM Madruga', 'Docente - Investigativa', 'CUM Madruga', 'Ave 33 e/ Calle 34 y Calle 36, #3404', '', '', 'Jacqueline Arteaga Barrueta', 'MsC. Alideylis M�ndez S�nchez', 'Continuidad de Estudios', false, '223.0.06815', 91);
INSERT INTO public.cum VALUES ('0225', 'CUM Jaruco', 'Docente - Investigativa', 'CUM Jaruco', '', '', '', 'Tatiana Rodr�guez Salazar', '', 'Continuidad de Estudios', false, '223.0.06815', 89);


--
-- TOC entry 3623 (class 0 OID 33601)
-- Dependencies: 227
-- Data for Name: cum_authorities; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3624 (class 0 OID 33604)
-- Dependencies: 228
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3625 (class 0 OID 33607)
-- Dependencies: 229
-- Data for Name: disciplina; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.disciplina VALUES ('GYM', 'Gerencia y Marketing', false);
INSERT INTO public.disciplina VALUES ('C', 'Calidad', false);
INSERT INTO public.disciplina VALUES ('IDM', 'Ingenier�a de M�todos', false);
INSERT INTO public.disciplina VALUES ('FYM', 'Fisiolog�a y Metabolismo', false);
INSERT INTO public.disciplina VALUES ('IA', 'Ingenier�a Agr�cola', false);
INSERT INTO public.disciplina VALUES ('PDP', 'Procesos de Producci�n Agropecuaria', false);
INSERT INTO public.disciplina VALUES ('PID', 'Proyecto Interador de Ingenieria Industrial', false);
INSERT INTO public.disciplina VALUES ('EDL', 'Estudios de Lengua Espa�ola', false);
INSERT INTO public.disciplina VALUES ('PPL', 'Preparaci�n para la Defensa', false);
INSERT INTO public.disciplina VALUES ('PA', 'Producci�n Agr�cola', false);
INSERT INTO public.disciplina VALUES ('CN', 'Cuentas Nacionales', false);
INSERT INTO public.disciplina VALUES ('IAL', 'Introducci�n a la Computaci�n', false);
INSERT INTO public.disciplina VALUES ('A', 'Auditoria', false);
INSERT INTO public.disciplina VALUES ('IYS', 'Informaci�n y Sociedad', false);
INSERT INTO public.disciplina VALUES ('B', 'Biolog�a', false);
INSERT INTO public.disciplina VALUES ('DDL', 'Direcci�n de la Cultura F�sica', false);
INSERT INTO public.disciplina VALUES ('CE', 'Ciencias Empresariles', false);
INSERT INTO public.disciplina VALUES ('SDI', 'Sistemas de Ingemier�a Agr�cola', false);
INSERT INTO public.disciplina VALUES ('IS', 'Intervenci�n Sociocultural', false);
INSERT INTO public.disciplina VALUES ('PDD', 'Psicolog�a del Desarrollo', false);
INSERT INTO public.disciplina VALUES ('EF', 'Educaci�n F�sica', false);
INSERT INTO public.disciplina VALUES ('G', 'Geograf�a', false);
INSERT INTO public.disciplina VALUES ('IE', 'Idioma Extranjero', false);
INSERT INTO public.disciplina VALUES ('F', 'F�sica', false);
INSERT INTO public.disciplina VALUES ('SYT', 'Sistemas y Tecnolog�as de la Informaci�n para el Contador', false);
INSERT INTO public.disciplina VALUES ('TE', 'Temas Especiales', false);
INSERT INTO public.disciplina VALUES ('DC', 'Derecho Civil', false);
INSERT INTO public.disciplina VALUES ('EFG', 'Educaci�n F�sica General', false);
INSERT INTO public.disciplina VALUES ('Q', 'Qu�mica', false);
INSERT INTO public.disciplina VALUES ('PL', 'Procesos Laborales', false);
INSERT INTO public.disciplina VALUES ('CB', 'Ciencias Biol�gicas  ', false);
INSERT INTO public.disciplina VALUES ('RA', 'Reproducci�n Animal', false);
INSERT INTO public.disciplina VALUES ('I', 'Introductorio', false);
INSERT INTO public.disciplina VALUES ('TS', 'Trabajo Social', false);
INSERT INTO public.disciplina VALUES ('D', 'Dibujo', false);
INSERT INTO public.disciplina VALUES ('M', 'Matem�tica', false);
INSERT INTO public.disciplina VALUES ('SE', 'Seminarios Especiales', false);
INSERT INTO public.disciplina VALUES ('CYS', 'Comunicaci�n y Sociedad', false);
INSERT INTO public.disciplina VALUES ('PB', 'Procesos B�sicos', false);
INSERT INTO public.disciplina VALUES ('TYM', 'Teor�a y Metodolog�a Social', false);
INSERT INTO public.disciplina VALUES ('TDD', 'Trabajo de Diploma', false);
INSERT INTO public.disciplina VALUES ('QG', 'Qu�mica General', false);
INSERT INTO public.disciplina VALUES ('TYS', 'Teor�as y Sistemas', false);
INSERT INTO public.disciplina VALUES ('ASD', 'Aspectos Socioculturales del Territorio', false);
INSERT INTO public.disciplina VALUES ('GVY', 'Gerencia Veterinaria y Zoot�cnica', false);
INSERT INTO public.disciplina VALUES ('CP', 'Ciencias Penales', false);
INSERT INTO public.disciplina VALUES ('TDP', 'T�cnica de Programaci�n de Computadoras', false);
INSERT INTO public.disciplina VALUES ('TEI', 'Teor�a e Investigaci�n en Comunicaci�n', false);
INSERT INTO public.disciplina VALUES ('EDP', 'Explotaci�n del Parque de la Maquinaria', false);
INSERT INTO public.disciplina VALUES ('CDS', 'Ciencias del Suelo', false);
INSERT INTO public.disciplina VALUES ('HYC', 'Historia y Cultura', false);
INSERT INTO public.disciplina VALUES ('TDL', 'Tecnolog�a de los Materiales', false);
INSERT INTO public.disciplina VALUES ('ML', 'Marxismo Leninismo', false);
INSERT INTO public.disciplina VALUES ('O', 'Optativa', false);
INSERT INTO public.disciplina VALUES ('LE', 'Lengua Extranjera', false);
INSERT INTO public.disciplina VALUES ('IYG', 'Ingenier�a y gesti�n de Software', false);
INSERT INTO public.disciplina VALUES ('MG', 'Matem�tica General', false);
INSERT INTO public.disciplina VALUES ('AYL', 'Arte y Literatura', false);
INSERT INTO public.disciplina VALUES ('FTY', 'Fundamentos Te�ricos y Pr�cticos del Desempe�o Jur�dico', false);
INSERT INTO public.disciplina VALUES ('GDP', 'Gesti�n de Procesos Industriales', false);
INSERT INTO public.disciplina VALUES ('NA', 'Nutrici�n Animal', false);
INSERT INTO public.disciplina VALUES ('P', 'Psicopedagog�a', false);
INSERT INTO public.disciplina VALUES ('DI', 'Disciplina Integradora', false);
INSERT INTO public.disciplina VALUES ('BYC', 'Bibliotecolog�a y Ciencias de la Informaci�n', false);
INSERT INTO public.disciplina VALUES ('SI', 'Seguridad Inform�tica', false);
INSERT INTO public.disciplina VALUES ('FDL', 'Fundamentos de la Ingenier�a Industrial', false);
INSERT INTO public.disciplina VALUES ('DCY', 'Derecho Civil y Familia', false);
INSERT INTO public.disciplina VALUES ('II', 'Inform�tica Industrial', false);
INSERT INTO public.disciplina VALUES ('MA', 'Matem�tica Aplicada', false);
INSERT INTO public.disciplina VALUES ('MB', 'MorfoBiomec�nica', false);
INSERT INTO public.disciplina VALUES ('H', 'Historia', false);
INSERT INTO public.disciplina VALUES ('MP', 'Medicina Preventiva', false);
INSERT INTO public.disciplina VALUES ('EYA', 'Econom�a y Administraci�n Agr�cola', false);
INSERT INTO public.disciplina VALUES ('TSY', 'Teor�as Sociol�gicas y Pol�ticas Sociol�gicas Especiales', false);
INSERT INTO public.disciplina VALUES ('PP', 'Pr�ctica Profesional', false);
INSERT INTO public.disciplina VALUES ('GDL', 'Gesti�n de las Organizaciones', false);
INSERT INTO public.disciplina VALUES ('ZG', 'Zootecnia General', false);
INSERT INTO public.disciplina VALUES ('TC', 'Tronco Com�n', false);
INSERT INTO public.disciplina VALUES ('PT', 'Procesos Tecnol�gicos', false);
INSERT INTO public.disciplina VALUES ('MDL', 'Metodolog�a de la Investigaci�n Social', false);
INSERT INTO public.disciplina VALUES ('CA', 'Ciencias Agropecuarias', false);
INSERT INTO public.disciplina VALUES ('CSY', 'Ciencias Sociales y Human�sticas', false);
INSERT INTO public.disciplina VALUES ('TDI', 'Tecnolog�as de Informaci�n', false);
INSERT INTO public.disciplina VALUES ('SDY', 'Sistemas Digitales y Aseguramiento B�sico de Programas', false);
INSERT INTO public.disciplina VALUES ('FTD', 'Fundamentos Te�ricos del Estado y el Derecho', false);
INSERT INTO public.disciplina VALUES ('CI', 'Curso Introductorio', false);
INSERT INTO public.disciplina VALUES ('SDP', 'Sistema de Producci�n (ca�a) ', false);
INSERT INTO public.disciplina VALUES ('DDA', 'Derecho de Administraci�n y de la Empresa', false);
INSERT INTO public.disciplina VALUES ('RF', 'Recreaci�n F�sica', false);
INSERT INTO public.disciplina VALUES ('DIJ', 'Disciplina Integradora:Ejercicio Jur�dico Profesional', false);
INSERT INTO public.disciplina VALUES ('PC', 'Psicolog�a Cl�nica', false);
INSERT INTO public.disciplina VALUES ('PEC', 'Procesos Econ�micos Contables', false);
INSERT INTO public.disciplina VALUES ('MYR', 'Mantenimiento y Reparaci�n', false);
INSERT INTO public.disciplina VALUES ('SYP', 'Salud y Producci�n Animal', false);
INSERT INTO public.disciplina VALUES ('DT', 'Dibujo T�cnico', false);
INSERT INTO public.disciplina VALUES ('L', 'Log�stica', false);
INSERT INTO public.disciplina VALUES ('TFY', 'Teor�a Filos�fica y Sociopol�tica', false);
INSERT INTO public.disciplina VALUES ('SV', 'Sanidad Vegetal', false);
INSERT INTO public.disciplina VALUES ('SP', 'Salud P�blica  Veterinaria', false);
INSERT INTO public.disciplina VALUES ('AO', 'Asignaturas Optativas', false);
INSERT INTO public.disciplina VALUES ('CFT', 'Cultura F�sica Terape�tica y Profil�ctica', false);
INSERT INTO public.disciplina VALUES ('ODL', 'Organizaci�n de la Informaci�n', false);
INSERT INTO public.disciplina VALUES ('PCY', 'Patrimonio Cultural y Tur�stico', false);
INSERT INTO public.disciplina VALUES ('E', 'Electrificaci�n', false);
INSERT INTO public.disciplina VALUES ('TDC', 'Trabajos de Cursos', false);
INSERT INTO public.disciplina VALUES ('PYD', 'Planificaci�n y Desarrollo', false);
INSERT INTO public.disciplina VALUES ('PDC', 'Procesos de Capacitaci�n', false);
INSERT INTO public.disciplina VALUES ('LI', 'Lengua Inglesa', false);
INSERT INTO public.disciplina VALUES ('ADS', 'Administraci�n de Sistemas de Ingener�a', false);
INSERT INTO public.disciplina VALUES ('EI', 'Economia Internacional', false);
INSERT INTO public.disciplina VALUES ('EA', 'Energ�a Agr�cola', false);
INSERT INTO public.disciplina VALUES ('AYG', 'Administraci�n y Gesti�n de Empresas', false);
INSERT INTO public.disciplina VALUES ('IDS', 'Infraestructura de sistemas inform�ticos', false);
INSERT INTO public.disciplina VALUES ('DP', 'Diagn�stico Paracl�nico', false);
INSERT INTO public.disciplina VALUES ('CO', 'Comunicaci�n Organizacional', false);
INSERT INTO public.disciplina VALUES ('TYA', 'Tractores y Autom�viles', false);
INSERT INTO public.disciplina VALUES ('EDT', 'Estudio del Trabajo', false);
INSERT INTO public.disciplina VALUES ('CDL', 'Ciencias de la Ingenier�a', false);
INSERT INTO public.disciplina VALUES ('FHD', 'Fundamentos Hist�ricos del Estado y del Derecho', false);
INSERT INTO public.disciplina VALUES ('TYPD', 'Teor�a y Pr�ctica del Deporte', false);
INSERT INTO public.disciplina VALUES ('RYD', 'Riego y Drenaje', false);
INSERT INTO public.disciplina VALUES ('AOY', 'Asignaturas Optativas y Electivas', false);
INSERT INTO public.disciplina VALUES ('EP', 'Econom�a Pol�tica', false);
INSERT INTO public.disciplina VALUES ('Z', 'Zootecnia', false);
INSERT INTO public.disciplina VALUES ('MAA', 'Matem�tica Aplicada a la Toma de Decisiones', false);
INSERT INTO public.disciplina VALUES ('PPD', 'Problemas Pr�cticos de Ingenier�a Industrial', false);
INSERT INTO public.disciplina VALUES ('MDS', 'Manejo de suelo y agua', false);
INSERT INTO public.disciplina VALUES ('GE', 'Gesti�n Ecxon�mica', false);
INSERT INTO public.disciplina VALUES ('PTA', 'Procesos Tecnol�gicos Agropecuarios', false);
INSERT INTO public.disciplina VALUES ('HDC', 'Historia de Cuba', false);
INSERT INTO public.disciplina VALUES ('FG', 'Fitotecnia General', false);
INSERT INTO public.disciplina VALUES ('trfgertrr', 'Procesos de Informaci�n', false);
INSERT INTO public.disciplina VALUES ('123er54', 'F�sica General', false);
INSERT INTO public.disciplina VALUES ('15', 'Derecho de la Empresa', false);
INSERT INTO public.disciplina VALUES ('ingfact56', 'Ingenier�a del Factor Humano', false);
INSERT INTO public.disciplina VALUES ('pedg380', 'Pedagog�a y Did�ctica de la Educaci�n Superior', false);
INSERT INTO public.disciplina VALUES ('GDO', 'Gesti�n de Organizaciones', false);
INSERT INTO public.disciplina VALUES ('7859', 'Historia y Pensamiento Cultural', false);
INSERT INTO public.disciplina VALUES ('0589', 'Promoci�n Sociocultural', false);
INSERT INTO public.disciplina VALUES ('0165', 'Curriculo Propio', false);
INSERT INTO public.disciplina VALUES ('06914', 'Cultura Cubana', false);
INSERT INTO public.disciplina VALUES ('02341', 'Investigaci�n Sociocultural', false);
INSERT INTO public.disciplina VALUES ('20156', 'Psicologia', false);
INSERT INTO public.disciplina VALUES ('5620', 'Fundamentos de Periodismo', false);
INSERT INTO public.disciplina VALUES ('9531', 'Est�tica', false);
INSERT INTO public.disciplina VALUES ('885958', 'Metodolog�a e la Investigaci�n Sociol�gica', false);
INSERT INTO public.disciplina VALUES ('8965', 'Aspectos Metodol�gicos Instrumentales', false);
INSERT INTO public.disciplina VALUES ('8956', 'Psicolog�a Social', false);
INSERT INTO public.disciplina VALUES ('56942', 'Psicolog�a de la Salud', false);
INSERT INTO public.disciplina VALUES ('3265', 'Psicolog�a Organizacional I', false);
INSERT INTO public.disciplina VALUES ('9653', 'Psicolog�a Laboral y de las Organizaciones', false);
INSERT INTO public.disciplina VALUES ('965', 'Psicolog�a Educativa', false);
INSERT INTO public.disciplina VALUES ('1027', 'M�rxismo - Leninnismo e Ideario Martiano', false);
INSERT INTO public.disciplina VALUES ('1031', 'Servicios T�cnicos Agropecuarios', false);
INSERT INTO public.disciplina VALUES ('1033', 'Did�ctica de las Especialidades de la ETP', false);
INSERT INTO public.disciplina VALUES ('1034', 'Ciencias Biol�gicas', false);
INSERT INTO public.disciplina VALUES ('9998', 'Reflexi�n y debate', false);
INSERT INTO public.disciplina VALUES ('1039', 'Curr�culo Propio', false);
INSERT INTO public.disciplina VALUES ('52365', 'Metodolog�a de la Investigaci�n Sociol�gica', false);
INSERT INTO public.disciplina VALUES ('25656', 'Teor�as Sociol�gicas y Pol�ticas Sociales Especiales', false);
INSERT INTO public.disciplina VALUES ('3356', 'CULMINACI�N DE ESTUDIO', false);
INSERT INTO public.disciplina VALUES ('1041', 'Formaci�n Pedag�gica General', false);
INSERT INTO public.disciplina VALUES ('6235', 'Psicolog�a General', false);
INSERT INTO public.disciplina VALUES ('1', 'MARXISMO - LENINISMO', false);
INSERT INTO public.disciplina VALUES ('3', 'PR�CTICA DE LA LENGUA INGLESA', false);
INSERT INTO public.disciplina VALUES ('5', 'INFORM�TICA EDUCATIVA', false);
INSERT INTO public.disciplina VALUES ('32', 'EDUCACI�N ART�STICA', false);
INSERT INTO public.disciplina VALUES ('67', 'ESTUDIOS LING��STICOS', false);
INSERT INTO public.disciplina VALUES ('87', 'ESTUDIOS LITERARIOS', false);
INSERT INTO public.disciplina VALUES ('80', 'ANATOM�A Y FISIOLOG�A HUMANAS', false);
INSERT INTO public.disciplina VALUES ('86', 'LOGOPEDIA', false);
INSERT INTO public.disciplina VALUES ('54', 'DID�CTICAS PARTICULARES', false);
INSERT INTO public.disciplina VALUES ('65', 'FORMACI�N LABORAL - INVESTIGATIVA', false);
INSERT INTO public.disciplina VALUES ('458', 'FUNDAMENTOS FILOS�FICOS Y SOCIOL�GICOS DE LA EDUCACI�N', false);
INSERT INTO public.disciplina VALUES ('333', 'FUNDAMENTOS FISIOL�GICOS Y PSICOL�GICOS DE LA EDUCACI�N', false);
INSERT INTO public.disciplina VALUES ('331', 'FUNDAMENTOS PEDAG�GICOS DE LA EDUCACI�N', false);
INSERT INTO public.disciplina VALUES ('332', 'ORIENTACI�N EN EL CONTEXTO EDUCATIVO', false);
INSERT INTO public.disciplina VALUES ('334', 'DID�CTICA Y CURR�CULO', false);
INSERT INTO public.disciplina VALUES ('335', 'METODOLOG�A DE LA INVESTIGACI�N', false);
INSERT INTO public.disciplina VALUES ('337', 'FORMACI�N  LABORAL INVESTIGATIVA', false);
INSERT INTO public.disciplina VALUES ('354', 'MARXISMO - LENINISMO E IDEARIO MARTIANO', false);
INSERT INTO public.disciplina VALUES ('258', 'HISTORIA DE CUBA', false);
INSERT INTO public.disciplina VALUES ('147', 'PR�CTICA INTEGRAL DE LA LENGUA ESPA�OLA', false);
INSERT INTO public.disciplina VALUES ('3836', 'Sociolog�a', false);
INSERT INTO public.disciplina VALUES ('9895', 'Psicolog�a', false);
INSERT INTO public.disciplina VALUES ('9887', 'Propiedad intelectual', false);
INSERT INTO public.disciplina VALUES ('9668', 'Propiedad Intelectual', false);
INSERT INTO public.disciplina VALUES ('9878', 'pedagog�a', false);
INSERT INTO public.disciplina VALUES ('1452', 'Pr�ctica Integral de la Lengua Inglesa ', false);
INSERT INTO public.disciplina VALUES ('365', 'ESTUDIOS LINGUISTICOS DEL ESPA�OL ', false);
INSERT INTO public.disciplina VALUES ('1235', 'ESTUDIOS LINGUISTICOS DEL INGL�S', false);
INSERT INTO public.disciplina VALUES ('7893', 'DIDACTICA DE LAS LENGUAS EXTRANJERAS ', false);
INSERT INTO public.disciplina VALUES ('3641', 'HISTORIA DE LA CULTURA DE LOS PUEBLOS DE HABLA INGLESA', false);
INSERT INTO public.disciplina VALUES ('2541', 'FORMACI�N LABORAL INVESTIGATIVA', false);
INSERT INTO public.disciplina VALUES ('362', 'ESTUDIOS DE LA NATURALEZA', false);
INSERT INTO public.disciplina VALUES ('030', 'Asignatura propia', false);
INSERT INTO public.disciplina VALUES ('201030', 'Espa�ol y Literatura', false);
INSERT INTO public.disciplina VALUES ('2001', 'Introducci�n a la Metodolog�a de la investigaci�n educativa', false);
INSERT INTO public.disciplina VALUES ('25413', 'DID�CTICA DE LA LENGUA ESPA�OLA Y LA LITERATURA', false);
INSERT INTO public.disciplina VALUES ('3602', 'LENGUAJE Y COMUNICACI�N', false);
INSERT INTO public.disciplina VALUES ('6565', 'Promoci�n Cultural', false);
INSERT INTO public.disciplina VALUES ('65487', 'Historia y Apreciaci�n de las Artes', false);
INSERT INTO public.disciplina VALUES ('65875', 'Teor�a y Metodolog�a de la Educaci�n Art�stica', false);
INSERT INTO public.disciplina VALUES ('89547', 'Talleres de Perfeccionamiento Art�stico', false);
INSERT INTO public.disciplina VALUES ('65689', 'Lengua y Literatura', false);
INSERT INTO public.disciplina VALUES ('87549', 'Requerimientos Metodol�gicos', false);
INSERT INTO public.disciplina VALUES ('65989', 'Talleres para la culminaci�n de estudios', false);
INSERT INTO public.disciplina VALUES ('123456', 'Investigaci�n y M�todos de An�lisis en la Cultura F�sica', false);
INSERT INTO public.disciplina VALUES ('DESC', 'Desconocida', false);
INSERT INTO public.disciplina VALUES ('saddsa', '�tica e Ideario martiano', false);
INSERT INTO public.disciplina VALUES ('ss55', 'Reflexi�n y Debate', false);
INSERT INTO public.disciplina VALUES ('asdww', 'Comunicaci�n Educativa', false);
INSERT INTO public.disciplina VALUES ('asdasd', 'PANORAMA DEL ARTE Y LA LITERATURA', false);
INSERT INTO public.disciplina VALUES ('xzcva', 'HISTORIA DE LA FILOSOF�A', false);
INSERT INTO public.disciplina VALUES ('xccx', 'DID�CTICA DE LAS CIENCIAS SOCIALES', false);
INSERT INTO public.disciplina VALUES ('14s4s4s', 'HISTORIA UNIVERSAL', false);
INSERT INTO public.disciplina VALUES ('sadsaxx', 'HISTORIA DE AM�RICA', false);
INSERT INTO public.disciplina VALUES ('asdcxz', '�TICA E IDEARIO MARTIANOS', false);
INSERT INTO public.disciplina VALUES ('4567342', 'Procesos Tecnol�gicos Industriales', false);
INSERT INTO public.disciplina VALUES ('56', 'FUNDAMENTOS DE LA MATEM�TICA ESCOLAR', false);
INSERT INTO public.disciplina VALUES ('45', 'FUNDAMENTOS DE LA F�SICA ESCOLAR', false);
INSERT INTO public.disciplina VALUES ('58', 'DID�CTICA DE LA F�SICA', false);
INSERT INTO public.disciplina VALUES ('546', 'AN�LISIS MATEM�TICO', false);
INSERT INTO public.disciplina VALUES ('587', '�LGEBRA', false);
INSERT INTO public.disciplina VALUES ('412', 'F�SICA GENERAL', false);
INSERT INTO public.disciplina VALUES ('231432', 'Culminaci�n de Estudios', false);
INSERT INTO public.disciplina VALUES ('231433', 'Culminaci�n de Estudio', false);
INSERT INTO public.disciplina VALUES ('500', 'Gerencia de Informaci�n', false);
INSERT INTO public.disciplina VALUES ('InfEd', 'Inform�tica Educativa', false);
INSERT INTO public.disciplina VALUES ('PILI', 'Pr�ctica Integral de la Lengua Inglesa', false);
INSERT INTO public.disciplina VALUES ('San Ag', 'Sanidad Agropecuaria', false);
INSERT INTO public.disciplina VALUES ('Prod An', 'Producci�n Animal', false);
INSERT INTO public.disciplina VALUES ('05961', 'Formaci�n Pedag�gica', false);
INSERT INTO public.disciplina VALUES ('112266448', 'EJERCICIO DE CULMINACI�N DE ESTUDIO', false);
INSERT INTO public.disciplina VALUES ('1144557', 'Fundamentos biol�gicos del ejercicio f�sico', false);
INSERT INTO public.disciplina VALUES ('123412', 'M�todos de an�lisis e investigaci�n de la Cultura F�sica', false);
INSERT INTO public.disciplina VALUES ('110036', 'Recreaci�n', false);
INSERT INTO public.disciplina VALUES ('10324', 'Fundamentos Biol�gicos de la Actividad F�sica', false);
INSERT INTO public.disciplina VALUES ('222aa', 'FUNDAMENTOS METODOL�GICOS PARA LA ENSE�ANZA ', false);
INSERT INTO public.disciplina VALUES ('asd12', 'FUNDAMENTOS SIENT�FICOS DE LAS DISCIPLINAS DEL AREA ', false);
INSERT INTO public.disciplina VALUES ('dsadasdas', 'FUNDAMENTOS CIENT�FICOS DE LAS DISCIPLINAS DEL AREA ', false);
INSERT INTO public.disciplina VALUES ('kjcnbjhvduqjdm', 'Elementos de Inform�tica', false);
INSERT INTO public.disciplina VALUES ('hcbhafdd6534hji', 'Lenguaje y T�cnica de Programaci�n', false);
INSERT INTO public.disciplina VALUES ('jnstfwe53oaqks0', 'Pr�ctica Integral de la Lengua Espa�ola', false);
INSERT INTO public.disciplina VALUES ('jsnuyfgiwju', 'Formaci�n Laboral Investigativa', false);
INSERT INTO public.disciplina VALUES ('jbdncuyugtsd4321guywg', 'Sistemas de Aplicaciones', false);
INSERT INTO public.disciplina VALUES ('2028', 'Procesos Agroindustriales', false);
INSERT INTO public.disciplina VALUES ('3021', 'Procesos Tecnol�gicos Agroindustriales', false);
INSERT INTO public.disciplina VALUES ('Ingreso', 'Requirimientos de Ingreso', false);
INSERT INTO public.disciplina VALUES ('sad211234', 'ESPA�OL B�SICO', false);
INSERT INTO public.disciplina VALUES ('213sda', 'PREPARACI�N PARA LA DEFENSA', false);
INSERT INTO public.disciplina VALUES ('sad21ss', 'MATEM�TICA B�SICA', false);
INSERT INTO public.disciplina VALUES ('SD2D2', 'Introducci�n al estudio de la Biolog�a', false);
INSERT INTO public.disciplina VALUES ('22231', 'Historia B�sica', false);
INSERT INTO public.disciplina VALUES ('l�jsadlkh', 'Estudios  lingu�sticos y  literarios', false);
INSERT INTO public.disciplina VALUES ('sadn2lnl2', 'Formaci�n Investigativa Laboral', false);
INSERT INTO public.disciplina VALUES ('sdd222', 'ESTUDIOS LINGUISTICOS Y LITERARIOS', false);
INSERT INTO public.disciplina VALUES ('68477', 'Principal Integradora', false);
INSERT INTO public.disciplina VALUES ('95656', 'Metodolog�a Social', false);
INSERT INTO public.disciplina VALUES ('32155', 'Requisitos de Ingreso', false);
INSERT INTO public.disciplina VALUES ('sackj', 'Mec�nica', false);
INSERT INTO public.disciplina VALUES ('asdlkjh', 'Metodolog�a de la Investigaci�n Educativa', false);
INSERT INTO public.disciplina VALUES ('jasdklasd', 'Salud y Sexualidad ', false);
INSERT INTO public.disciplina VALUES ('cvzx31', 'Asignatura Requisito', false);
INSERT INTO public.disciplina VALUES ('222222', 'DIRECCI�N DEL PROCESO DE ENSE�ANZA-APRENDIZAJE DE LA BIOLOG�A', false);
INSERT INTO public.disciplina VALUES ('2201200', 'BIOLOG�A MOLECULAR Y CELULAR ', false);
INSERT INTO public.disciplina VALUES ('88888', 'EDUCACI�N', false);
INSERT INTO public.disciplina VALUES ('22222', 'ORIENTACI�N EDUCATIVA', false);
INSERT INTO public.disciplina VALUES ('22', 'DID�CTICA DE LA QU�MICA', false);
INSERT INTO public.disciplina VALUES ('3333', 'Qu�mica Org�nica', false);
INSERT INTO public.disciplina VALUES ('555555', 'DPI FLI para la Ense�anza de la Geograf�a', false);
INSERT INTO public.disciplina VALUES ('0140', 'Cartograf�a y Sistema de Informaci�n Geogr�fica', false);
INSERT INTO public.disciplina VALUES ('123', 'Geograf�a F�sica', false);
INSERT INTO public.disciplina VALUES ('234', 'Geograf�a Econ�mica y Social', false);
INSERT INTO public.disciplina VALUES ('12567', 'Pr�ctica de Campo', false);
INSERT INTO public.disciplina VALUES ('xzcxz', 'ALGEBRA', false);
INSERT INTO public.disciplina VALUES ('xzcqew', 'FUNDAMENTOS B�SICOS DE LA MATEM�TICA ', false);
INSERT INTO public.disciplina VALUES ('xzcasd', 'Optativa I: La Educaci�n Integral para la sexualidad', false);
INSERT INTO public.disciplina VALUES ('02802-1', 'Auditor�a', false);
INSERT INTO public.disciplina VALUES ('04802-2', 'Costos', false);
INSERT INTO public.disciplina VALUES ('7488', 'AN�LISIS Y CR�TICA LITERARIA', false);
INSERT INTO public.disciplina VALUES ('547', 'EXPRESIONES ART�STICAS', false);
INSERT INTO public.disciplina VALUES ('asdsa', 'EDUCACU�N F�SICA', false);
INSERT INTO public.disciplina VALUES ('321', 'Fundamentos te�ricos y did�cticos de Nociones Elementales de la Matem�tica', false);
INSERT INTO public.disciplina VALUES ('23', 'MICROBIOLOG�A', false);
INSERT INTO public.disciplina VALUES ('sc sa', 'Bot�nica', false);
INSERT INTO public.disciplina VALUES ('322332', 'Gen�tica Ecol�gica', false);
INSERT INTO public.disciplina VALUES ('7890', 'Disciplina Principal Integradora', false);
INSERT INTO public.disciplina VALUES ('ccc', 'Qu�mica F�sica', false);
INSERT INTO public.disciplina VALUES ('2223', 'ZOOLOG�A GENERAL', false);
INSERT INTO public.disciplina VALUES ('vv', 'ASIGNATURAS B�SICAS', false);
INSERT INTO public.disciplina VALUES ('55555', 'Formaci�n laboral Investigativa en la Cultura F�sica', false);
INSERT INTO public.disciplina VALUES ('02267', 'Pr�ctica laboral Investigativa en la Cultura F�sica', false);
INSERT INTO public.disciplina VALUES ('5847', 'FORMACI�N CIUDADANA', false);
INSERT INTO public.disciplina VALUES ('kikiki', 'ASIGNATURAS DE REQUISITO', false);
INSERT INTO public.disciplina VALUES ('5678', 'Formaci�n Laboral Investigativa en la ense�anza de la F�sica', false);
INSERT INTO public.disciplina VALUES ('30980938104', 'Gesti�n Sociocultural', false);
INSERT INTO public.disciplina VALUES ('Des1', 'DESARROLLO Y POL�TICAS SOCIALES', false);
INSERT INTO public.disciplina VALUES ('met1', 'METODOLOGIA SOCIAL', false);
INSERT INTO public.disciplina VALUES ('hist1', 'HISTORIA CULTURAL Y PENSAMIENTO SOCIAL ', false);
INSERT INTO public.disciplina VALUES ('ECO', 'Econom�a Empresarial', false);
INSERT INTO public.disciplina VALUES ('INFR', 'Infraestructuras de Sistemas Inform�ticos', false);
INSERT INTO public.disciplina VALUES ('INTC', 'Inteligencia Computacional', false);
INSERT INTO public.disciplina VALUES ('ECE', 'Econom�a Empresarial ', false);
INSERT INTO public.disciplina VALUES ('INTC2', 'Inteligencia Computacional ', false);
INSERT INTO public.disciplina VALUES ('ASB', 'Asignaturas B�sicas', false);
INSERT INTO public.disciplina VALUES ('MFGA', 'Morfolog�a', false);
INSERT INTO public.disciplina VALUES ('SMA', 'SMA', false);
INSERT INTO public.disciplina VALUES ('oooo', 'Historia de Cuba B�sica', false);
INSERT INTO public.disciplina VALUES ('223a', 'Did�ctica de la Educaci�n Superior', false);
INSERT INTO public.disciplina VALUES ('IG', 'Ingl�s', false);
INSERT INTO public.disciplina VALUES ('DESARROLLO Y POL�TICAS SOCIALES', 'Desarrollo y Pol�ticas Sociales', false);
INSERT INTO public.disciplina VALUES ('PRO.Agro', 'PRODUCCI�N AGROPECUARIA', false);
INSERT INTO public.disciplina VALUES ('ZOO', 'ZOOTECNIA', false);
INSERT INTO public.disciplina VALUES ('2019-1', 'Teor�a Filos�fica y Sociopol�tica Marxista Leninista', false);
INSERT INTO public.disciplina VALUES ('2019-2', 'Teor�a Econ�mica Marxista Leninista', false);
INSERT INTO public.disciplina VALUES ('2019-3', 'M�todos Econ�mico - Matem�ticos', false);
INSERT INTO public.disciplina VALUES ('PLCF', 'Pr�ctica Laboral Contable y Financiera', false);
INSERT INTO public.disciplina VALUES ('2019-4', 'Contabilidad', false);
INSERT INTO public.disciplina VALUES ('2019-5', 'Administraci�n', false);
INSERT INTO public.disciplina VALUES ('2019-8', 'Sistemas', false);
INSERT INTO public.disciplina VALUES ('2019-9', 'Finanzas', false);
INSERT INTO public.disciplina VALUES ('2019-10', 'Econom�a Internacional', false);
INSERT INTO public.disciplina VALUES (',mcpo9', 'Administraci�n de Sistemas de Ingenier�a', false);
INSERT INTO public.disciplina VALUES ('jhvx453fc', 'Explotaci�n de la Maquinaria Agr�cola', false);
INSERT INTO public.disciplina VALUES ('bxndjeur', 'Sistemas de Ingenier�a Agr�cola', false);
INSERT INTO public.disciplina VALUES ('GSPD-ML', 'Marxismo-Leninismo', false);
INSERT INTO public.disciplina VALUES ('GSPD-EL', 'Estudios de la lengua espa�ola', false);
INSERT INTO public.disciplina VALUES ('GSPD-C', 'Computaci�n', false);
INSERT INTO public.disciplina VALUES ('GSPD-DPS', 'Desarrollo y pol�ticas sociales', false);
INSERT INTO public.disciplina VALUES ('GSPD-HCPS', 'Historia Cultural y Pensamiento Social', false);
INSERT INTO public.disciplina VALUES ('GSPD-PD', 'Preparaci�n para la defensa', false);
INSERT INTO public.disciplina VALUES ('GSD-CEI', 'Cumplimiento Exigencias de Ingreso ', false);
INSERT INTO public.disciplina VALUES ('RPMA', 'Reproducci�n y mejora animal', false);
INSERT INTO public.disciplina VALUES ('Derecho(E)LE', 'Idioma Ingl�s', false);
INSERT INTO public.disciplina VALUES ('345', 'Fundamentos Hist�ricos del Estado y el Derecho', false);
INSERT INTO public.disciplina VALUES ('Derecho E FGED', 'Fundamentos Generales del Estado y el Derecho', false);
INSERT INTO public.disciplina VALUES ('1314-1', 'Teor�a Filos�fica y Sociopol�tica Marxista - Leninista', false);
INSERT INTO public.disciplina VALUES ('970', 'Derecho Civil y de Familia', false);
INSERT INTO public.disciplina VALUES ('GSPD HCPS', 'HIstoria Cultural y Pensamiento Social', false);
INSERT INTO public.disciplina VALUES ('DPS GSPD', 'Desarollo y Pol�ticas Sociales', false);
INSERT INTO public.disciplina VALUES ('10601-2', 'Matem�tica Superior', false);
INSERT INTO public.disciplina VALUES ('098021', 'Inform�tica Empresarial', false);
INSERT INTO public.disciplina VALUES ('PII', 'Proyecto de Ingenier�a Industrial', false);
INSERT INTO public.disciplina VALUES ('10601', 'Estad�stica e Investigaci�n de Operaciones', false);
INSERT INTO public.disciplina VALUES ('10602', 'Econom�a y Direcci�n de Procesos', false);
INSERT INTO public.disciplina VALUES ('32155-1', 'Requisitos de Ingreso y Permanencia', false);
INSERT INTO public.disciplina VALUES ('23123', 'Diagn�stico Patol�gico', false);
INSERT INTO public.disciplina VALUES ('Gestion socioec y administ', 'GESTI�N SOCIOECON�MICA Y ADMINISTRATIVA', false);
INSERT INTO public.disciplina VALUES ('Der', 'Derecho', false);
INSERT INTO public.disciplina VALUES ('10601-3', 'MATEM�TICA', false);
INSERT INTO public.disciplina VALUES ('26802-4', 'Sistemas y Tecnolog�a de la Informaci�n para el Contador', false);
INSERT INTO public.disciplina VALUES ('090909', 'Agua y Saneamiento', false);
INSERT INTO public.disciplina VALUES ('98745', 'Disciplina Derecho Penal y Criminolog�a', false);
INSERT INTO public.disciplina VALUES ('3456', 'Teor�a Marxista Leninista', false);
INSERT INTO public.disciplina VALUES ('DTS', 'Disciplina Principal de Trabajo Social ', false);


--
-- TOC entry 3626 (class 0 OID 33610)
-- Dependencies: 230
-- Data for Name: disciplina_planestudio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3627 (class 0 OID 33615)
-- Dependencies: 231
-- Data for Name: especialidad; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.especialidad VALUES (1, 'Ciencias T�cnicas', false);
INSERT INTO public.especialidad VALUES (2, 'Ciencias Naturales y Matem�ticas', false);
INSERT INTO public.especialidad VALUES (3, 'Ciencias M�dicas', false);
INSERT INTO public.especialidad VALUES (4, 'Ciencias Agropecuarias', false);
INSERT INTO public.especialidad VALUES (5, 'Ciencias Econ�micas', false);
INSERT INTO public.especialidad VALUES (6, 'Ciencias Sociales y Human�sticas', false);
INSERT INTO public.especialidad VALUES (7, 'Ciencias Pedag�gicas', false);
INSERT INTO public.especialidad VALUES (8, 'Cultura F�sica y Deportes', false);
INSERT INTO public.especialidad VALUES (9, 'Arte', false);
INSERT INTO public.especialidad VALUES (10, 'Estudios en el extranjero', false);
INSERT INTO public.especialidad VALUES (11, 'Facultad Preparatoria', false);
INSERT INTO public.especialidad VALUES (12, 'Ciencias Militares', false);


--
-- TOC entry 3629 (class 0 OID 33619)
-- Dependencies: 233
-- Data for Name: especialidad_militar; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.especialidad_militar VALUES (1, 'Artiller�a', false);
INSERT INTO public.especialidad_militar VALUES (2, 'Inteligencia', false);
INSERT INTO public.especialidad_militar VALUES (3, 'Ninguna', false);
INSERT INTO public.especialidad_militar VALUES (4, 'Otra', false);
INSERT INTO public.especialidad_militar VALUES (5, 'Zapadores', false);


--
-- TOC entry 3631 (class 0 OID 33623)
-- Dependencies: 235
-- Data for Name: estado_civil; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.estado_civil VALUES (1, 'Casado (a)', false);
INSERT INTO public.estado_civil VALUES (2, 'Soltero (a)', false);
INSERT INTO public.estado_civil VALUES (3, 'Viudo (a)', false);
INSERT INTO public.estado_civil VALUES (4, 'Divorciado (a)', false);
INSERT INTO public.estado_civil VALUES (5, 'Separado (a)', false);
INSERT INTO public.estado_civil VALUES (6, 'Unido (a)', false);


--
-- TOC entry 3633 (class 0 OID 33627)
-- Dependencies: 237
-- Data for Name: estado_estudiante; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.estado_estudiante VALUES (1, 'Activo', false);
INSERT INTO public.estado_estudiante VALUES (2, 'Nueva Matr�cula', false);
INSERT INTO public.estado_estudiante VALUES (3, 'Baja', false);
INSERT INTO public.estado_estudiante VALUES (4, 'Egresado', false);
INSERT INTO public.estado_estudiante VALUES (5, 'Pr�rroga de Tesis', false);
INSERT INTO public.estado_estudiante VALUES (6, 'Matricula Pasiva', false);


--
-- TOC entry 3635 (class 0 OID 33631)
-- Dependencies: 239
-- Data for Name: estudiante; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3636 (class 0 OID 33637)
-- Dependencies: 240
-- Data for Name: examen; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.examen VALUES (1, 'Convocatoria 1', false);
INSERT INTO public.examen VALUES (2, 'Convocatoria 2', false);
INSERT INTO public.examen VALUES (3, 'Convocatoria 3', false);


--
-- TOC entry 3638 (class 0 OID 33641)
-- Dependencies: 242
-- Data for Name: examen_matricula_facultad_cum_carrera_estudiante_asignatura; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3639 (class 0 OID 33644)
-- Dependencies: 243
-- Data for Name: facultad; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.facultad VALUES ('111', 'Ciencias Pedag�gicas', '', 'DrC. Orlianis Farradas L�pez', '', 'Carretera Tapaste y Autopista Nacional km 23 1/2', false, 88, '223.0.06815');
INSERT INTO public.facultad VALUES ('802', 'Ciencias Econ�micas', 'Gabriela Ramos Reyes', '', '', 'Carretera Tapaste y Autopista Nacional km 23 1/2', false, 88, '223.0.06815');
INSERT INTO public.facultad VALUES ('7118e3c1:11d012c1880:-7214', 'Preparatoria', 'Lic. Yamaily Cepero Atencio', '', '', 'Carretera Tapaste y Autopista Nacional km 23 1/2', false, 88, '223.0.06815');
INSERT INTO public.facultad VALUES ('602', 'Agronom�a', 'Miriam Gladys Ofarrill Ferrer', '', '', 'Carretera Tapaste y Autopista Nacional km 23 1/2', false, 88, '223.0.06815');
INSERT INTO public.facultad VALUES ('120', 'Ciencias T�cnicas', '', '', '', 'Carretera Tapaste y Autopista Nacional km 23 1/2', false, 88, '223.0.06815');
INSERT INTO public.facultad VALUES ('-3eb40587:11d6e697e6e:-566d', 'Cultura F�sica', '', '', '', 'Carretera Tapaste y Autopista Nacional km 23 1/2', false, 88, '223.0.06815');
INSERT INTO public.facultad VALUES ('314', 'Ciencias Sociales y Human�sticas', 'M�nica Alvarez Gonz�lez', '', '', 'Carretera Tapaste y Autopista Nacional km 23 1/2', false, 88, '223.0.06815');
INSERT INTO public.facultad VALUES ('603', 'Medicina Veterinaria y Zootecnia', 'Andrea Susana Laguardia Garc�a', '', '', 'Carretera Tapaste y Autopista Nacional km 23 1/2', false, 88, '223.0.06815');


--
-- TOC entry 3640 (class 0 OID 33647)
-- Dependencies: 244
-- Data for Name: facultad_authorities; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3641 (class 0 OID 33650)
-- Dependencies: 245
-- Data for Name: facultad_cum; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.facultad_cum VALUES ('', '111', false);
INSERT INTO public.facultad_cum VALUES ('', '802', false);
INSERT INTO public.facultad_cum VALUES ('', '7118e3c1:11d012c1880:-7214', false);
INSERT INTO public.facultad_cum VALUES ('', '602', false);
INSERT INTO public.facultad_cum VALUES ('', '120', false);
INSERT INTO public.facultad_cum VALUES ('', '-3eb40587:11d6e697e6e:-566d', false);
INSERT INTO public.facultad_cum VALUES ('', '314', false);
INSERT INTO public.facultad_cum VALUES ('', '603', false);


--
-- TOC entry 3642 (class 0 OID 33653)
-- Dependencies: 246
-- Data for Name: facultad_cum_carrera; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3643 (class 0 OID 33656)
-- Dependencies: 247
-- Data for Name: facultad_cum_carrera_estudiante; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3644 (class 0 OID 33659)
-- Dependencies: 248
-- Data for Name: facultad_cum_carrera_estudiante_asignatura; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3645 (class 0 OID 33662)
-- Dependencies: 249
-- Data for Name: fuente_ingreso; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.fuente_ingreso VALUES (1, 'Curso por Encuentro Libre', false);
INSERT INTO public.fuente_ingreso VALUES (2, 'Graduado universitario con segunda carrera', false);
INSERT INTO public.fuente_ingreso VALUES (3, 'Extranjeros', false);
INSERT INTO public.fuente_ingreso VALUES (4, 'Orden 18', false);
INSERT INTO public.fuente_ingreso VALUES (5, 'Cadetes MININT', false);
INSERT INTO public.fuente_ingreso VALUES (6, 'Discapacitados', false);
INSERT INTO public.fuente_ingreso VALUES (7, 'Concurso', false);
INSERT INTO public.fuente_ingreso VALUES (8, 'Cadetes MINFAR', false);
INSERT INTO public.fuente_ingreso VALUES (9, 'Escuelas de Iniciaci�n Deportiva (EIDE)', false);
INSERT INTO public.fuente_ingreso VALUES (10, 'Ganadores de Eventos', false);
INSERT INTO public.fuente_ingreso VALUES (11, 'Servicio Militar Voluntario Femenino', false);
INSERT INTO public.fuente_ingreso VALUES (12, 'Curso por Encuentro', false);
INSERT INTO public.fuente_ingreso VALUES (13, 'Ense�anza T�cnico Provincial', false);
INSERT INTO public.fuente_ingreso VALUES (14, 'Academias Deportivas de Alto Rendimiento', false);
INSERT INTO public.fuente_ingreso VALUES (15, 'Institutos Preuniversitarios', false);
INSERT INTO public.fuente_ingreso VALUES (16, 'Escuela de formaci�n de maestros', false);


--
-- TOC entry 3647 (class 0 OID 33666)
-- Dependencies: 251
-- Data for Name: grado_militar; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.grado_militar VALUES (1, 'Soldado', false);
INSERT INTO public.grado_militar VALUES (2, 'Sargento', false);
INSERT INTO public.grado_militar VALUES (3, 'Suboficial', false);
INSERT INTO public.grado_militar VALUES (4, 'Ninguno', false);
INSERT INTO public.grado_militar VALUES (5, 'Otro', false);


--
-- TOC entry 3649 (class 0 OID 33670)
-- Dependencies: 253
-- Data for Name: huerfano; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.huerfano VALUES (1, 'Madre', false);
INSERT INTO public.huerfano VALUES (2, 'Padre', false);
INSERT INTO public.huerfano VALUES (3, 'Ambos', false);
INSERT INTO public.huerfano VALUES (4, 'Ninguno', false);


--
-- TOC entry 3651 (class 0 OID 33674)
-- Dependencies: 255
-- Data for Name: matricula; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3652 (class 0 OID 33677)
-- Dependencies: 256
-- Data for Name: matricula_facultad_cum_carrera_estudiante_asignatura; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3654 (class 0 OID 33681)
-- Dependencies: 258
-- Data for Name: minusvalia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.minusvalia VALUES (1, 'D�bil auditivo', false);
INSERT INTO public.minusvalia VALUES (2, 'Deficiente permanente del habla o mudo', false);
INSERT INTO public.minusvalia VALUES (3, 'D�bil visual', false);
INSERT INTO public.minusvalia VALUES (4, 'Sordo', false);
INSERT INTO public.minusvalia VALUES (5, 'F�sico Motor', false);
INSERT INTO public.minusvalia VALUES (6, 'Enfermo mental cr�nico', false);
INSERT INTO public.minusvalia VALUES (7, 'Retraso mental', false);
INSERT INTO public.minusvalia VALUES (8, 'Ausencia de extremidades inferiores', false);
INSERT INTO public.minusvalia VALUES (9, 'Invalidez de extremidades inferiores', false);
INSERT INTO public.minusvalia VALUES (10, 'Ausencia de extremidades superiores', false);
INSERT INTO public.minusvalia VALUES (11, 'Invalidez de extremidades superiores', false);
INSERT INTO public.minusvalia VALUES (12, 'Ciego', false);


--
-- TOC entry 3655 (class 0 OID 33684)
-- Dependencies: 259
-- Data for Name: minusvalia_estudiante; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3657 (class 0 OID 33688)
-- Dependencies: 261
-- Data for Name: motivo_baja; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.motivo_baja VALUES (1, 'Definitiva', 2, false);
INSERT INTO public.motivo_baja VALUES (2, 'Deserci�n', 2, false);
INSERT INTO public.motivo_baja VALUES (3, 'Voluntaria', 2, false);
INSERT INTO public.motivo_baja VALUES (4, 'Inasistencia', 1, false);
INSERT INTO public.motivo_baja VALUES (5, 'Insuficiencia Docente', 1, false);
INSERT INTO public.motivo_baja VALUES (6, 'Licencia de Matr�cula', 1, false);
INSERT INTO public.motivo_baja VALUES (7, 'P�rdida de Requisitos', 1, false);
INSERT INTO public.motivo_baja VALUES (8, 'Sanci�n Disciplinaria', 1, false);
INSERT INTO public.motivo_baja VALUES (9, 'Traslado', 1, false);


--
-- TOC entry 3659 (class 0 OID 33692)
-- Dependencies: 263
-- Data for Name: municipio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.municipio VALUES (-1, -1, '', false);
INSERT INTO public.municipio VALUES (1, 5, 'CIENAGA DE ZAPATA', false);
INSERT INTO public.municipio VALUES (2, 5, 'JAGUEY GRANDE', false);
INSERT INTO public.municipio VALUES (3, 5, 'CALIMETE', false);
INSERT INTO public.municipio VALUES (4, 5, 'LOS ARABOS', false);
INSERT INTO public.municipio VALUES (5, 6, 'CORRALILLO', false);
INSERT INTO public.municipio VALUES (6, 6, 'QUEMADO DE GUINES', false);
INSERT INTO public.municipio VALUES (7, 6, 'SAGUA LA GRANDE', false);
INSERT INTO public.municipio VALUES (8, 6, 'ENCRUCIJADA', false);
INSERT INTO public.municipio VALUES (9, 6, 'CAMAJUANI', false);
INSERT INTO public.municipio VALUES (10, 6, 'CAIBARIEN', false);
INSERT INTO public.municipio VALUES (11, 6, 'REMEDIOS', false);
INSERT INTO public.municipio VALUES (12, 3, 'SANDINO', false);
INSERT INTO public.municipio VALUES (13, 3, 'MANTUA', false);
INSERT INTO public.municipio VALUES (14, 3, 'MINAS DE MATAHAMBRE', false);
INSERT INTO public.municipio VALUES (15, 3, 'LA PALMA', false);
INSERT INTO public.municipio VALUES (16, 3, 'LOS PALACIOS', false);
INSERT INTO public.municipio VALUES (17, 3, 'CONSOLACION DEL SUR', false);
INSERT INTO public.municipio VALUES (18, 3, 'PINAR DEL RIO', false);
INSERT INTO public.municipio VALUES (19, 6, 'PLACETAS', false);
INSERT INTO public.municipio VALUES (20, 6, 'SANTA CLARA', false);
INSERT INTO public.municipio VALUES (21, 6, 'CIFUENTES', false);
INSERT INTO public.municipio VALUES (22, 6, 'SANTO DOMINGO', false);
INSERT INTO public.municipio VALUES (23, 6, 'RANCHUELO', false);
INSERT INTO public.municipio VALUES (24, 6, 'MANICARAGUA', false);
INSERT INTO public.municipio VALUES (25, 8, 'YAGUAJAY', false);
INSERT INTO public.municipio VALUES (26, 8, 'JATIBONICO', false);
INSERT INTO public.municipio VALUES (27, 8, 'TAGUASCO', false);
INSERT INTO public.municipio VALUES (28, 8, 'CABAIGUAN', false);
INSERT INTO public.municipio VALUES (29, 8, 'FOMENTO', false);
INSERT INTO public.municipio VALUES (30, 8, 'TRINIDAD', false);
INSERT INTO public.municipio VALUES (31, 8, 'SANCTI SPIRITUS', false);
INSERT INTO public.municipio VALUES (32, 8, 'LA SIERPE', false);
INSERT INTO public.municipio VALUES (33, 13, 'RIO CAUTO', false);
INSERT INTO public.municipio VALUES (34, 13, 'CAUTO CRISTO', false);
INSERT INTO public.municipio VALUES (35, 13, 'JIGUANI', false);
INSERT INTO public.municipio VALUES (36, 13, 'BAYAMO', false);
INSERT INTO public.municipio VALUES (37, 13, 'YARA', false);
INSERT INTO public.municipio VALUES (38, 13, 'MANZANILLO', false);
INSERT INTO public.municipio VALUES (39, 13, 'CAMPECHUELA', false);
INSERT INTO public.municipio VALUES (40, 13, 'MEDIA LUNA', false);
INSERT INTO public.municipio VALUES (41, 13, 'NIQUERO', false);
INSERT INTO public.municipio VALUES (42, 13, 'PILON', false);
INSERT INTO public.municipio VALUES (43, 13, 'BARTOLOME MASO', false);
INSERT INTO public.municipio VALUES (44, 13, 'BUEY ARRIBA', false);
INSERT INTO public.municipio VALUES (45, 13, 'GUISA', false);
INSERT INTO public.municipio VALUES (46, 14, 'CONTRAMAESTRE', false);
INSERT INTO public.municipio VALUES (47, 14, 'MELLA', false);
INSERT INTO public.municipio VALUES (48, 14, 'SAN LUIS', false);
INSERT INTO public.municipio VALUES (49, 14, 'SEGUNDO FRENTE', false);
INSERT INTO public.municipio VALUES (50, 14, 'SONGO - LA MAYA', false);
INSERT INTO public.municipio VALUES (51, 14, 'SANTIAGO DE CUBA', false);
INSERT INTO public.municipio VALUES (52, 14, 'PALMA SORIANO', false);
INSERT INTO public.municipio VALUES (53, 14, 'TERCER FRENTE', false);
INSERT INTO public.municipio VALUES (54, 14, 'GUAMA', false);
INSERT INTO public.municipio VALUES (55, 15, 'EL SALVADOR', false);
INSERT INTO public.municipio VALUES (56, 15, 'YATERAS', false);
INSERT INTO public.municipio VALUES (57, 15, 'BARACOA', false);
INSERT INTO public.municipio VALUES (58, 15, 'MAISI', false);
INSERT INTO public.municipio VALUES (59, 15, 'GUANTANAMO', false);
INSERT INTO public.municipio VALUES (60, 3, 'SAN JUAN Y MARTINEZ', false);
INSERT INTO public.municipio VALUES (61, 3, 'GUANE', false);
INSERT INTO public.municipio VALUES (62, 1, 'BAHIA HONDA', false);
INSERT INTO public.municipio VALUES (63, 1, 'MARIEL', false);
INSERT INTO public.municipio VALUES (64, 1, 'GUANAJAY', false);
INSERT INTO public.municipio VALUES (65, 1, 'CAIMITO', false);
INSERT INTO public.municipio VALUES (66, 1, 'BAUTA', false);
INSERT INTO public.municipio VALUES (67, 1, 'GUIRA DE MELENA', false);
INSERT INTO public.municipio VALUES (68, 1, 'ALQUIZAR', false);
INSERT INTO public.municipio VALUES (69, 1, 'ARTEMISA', false);
INSERT INTO public.municipio VALUES (70, 1, 'CANDELARIA', false);
INSERT INTO public.municipio VALUES (71, 1, 'SAN CRISTOBAL', false);
INSERT INTO public.municipio VALUES (72, 4, 'PLAYA', false);
INSERT INTO public.municipio VALUES (73, 4, 'PLAZA DE LA REVOLUCION', false);
INSERT INTO public.municipio VALUES (74, 4, 'CENTRO HABANA', false);
INSERT INTO public.municipio VALUES (75, 4, 'LA HABANA VIEJA', false);
INSERT INTO public.municipio VALUES (76, 4, 'REGLA', false);
INSERT INTO public.municipio VALUES (77, 4, 'LA HABANA DEL ESTE', false);
INSERT INTO public.municipio VALUES (78, 4, 'GUANABACOA', false);
INSERT INTO public.municipio VALUES (79, 4, 'SAN MIGUEL DEL PADRON', false);
INSERT INTO public.municipio VALUES (80, 4, 'DIEZ DE OCTUBRE', false);
INSERT INTO public.municipio VALUES (81, 4, 'CERRO', false);
INSERT INTO public.municipio VALUES (82, 4, 'MARIANAO', false);
INSERT INTO public.municipio VALUES (83, 4, 'LA LISA', false);
INSERT INTO public.municipio VALUES (84, 4, 'BOYEROS', false);
INSERT INTO public.municipio VALUES (85, 4, 'ARROYO NARANJO', false);
INSERT INTO public.municipio VALUES (86, 4, 'COTORRO', false);
INSERT INTO public.municipio VALUES (87, 2, 'BEJUCAL', false);
INSERT INTO public.municipio VALUES (88, 2, 'SAN JOSE DE LAS LAJAS', false);
INSERT INTO public.municipio VALUES (89, 2, 'JARUCO', false);
INSERT INTO public.municipio VALUES (90, 2, 'SANTA CRUZ DEL NORTE', false);
INSERT INTO public.municipio VALUES (91, 2, 'MADRUGA', false);
INSERT INTO public.municipio VALUES (92, 2, 'NUEVA PAZ', false);
INSERT INTO public.municipio VALUES (93, 2, 'SAN NICOLAS', false);
INSERT INTO public.municipio VALUES (94, 2, 'GUINES', false);
INSERT INTO public.municipio VALUES (95, 2, 'MELENA DEL SUR', false);
INSERT INTO public.municipio VALUES (96, 2, 'BATABANO', false);
INSERT INTO public.municipio VALUES (97, 2, 'QUIVICAN', false);
INSERT INTO public.municipio VALUES (98, 9, 'CHAMBAS', false);
INSERT INTO public.municipio VALUES (99, 9, 'MORON', false);
INSERT INTO public.municipio VALUES (100, 9, 'BOLIVIA', false);
INSERT INTO public.municipio VALUES (101, 9, 'PRIMERO DE ENERO', false);
INSERT INTO public.municipio VALUES (102, 9, 'CIRO REDONDO', false);
INSERT INTO public.municipio VALUES (103, 9, 'FLORENCIA', false);
INSERT INTO public.municipio VALUES (104, 9, 'MAJAGUA', false);
INSERT INTO public.municipio VALUES (105, 9, 'CIEGO DE AVILA', false);
INSERT INTO public.municipio VALUES (106, 9, 'VENEZUELA', false);
INSERT INTO public.municipio VALUES (107, 9, 'BARAGUA', false);
INSERT INTO public.municipio VALUES (108, 10, 'CARLOS MANUEL DE CESPEDES', false);
INSERT INTO public.municipio VALUES (109, 10, 'ESMERALDA', false);
INSERT INTO public.municipio VALUES (110, 10, 'SIERRA DE CUBITAS', false);
INSERT INTO public.municipio VALUES (111, 10, 'MINAS', false);
INSERT INTO public.municipio VALUES (112, 10, 'NUEVITAS', false);
INSERT INTO public.municipio VALUES (113, 10, 'GUAIMARO', false);
INSERT INTO public.municipio VALUES (114, 10, 'SIBANICU', false);
INSERT INTO public.municipio VALUES (115, 10, 'CAMAGUEY', false);
INSERT INTO public.municipio VALUES (116, 10, 'FLORIDA', false);
INSERT INTO public.municipio VALUES (117, 10, 'VERTIENTES', false);
INSERT INTO public.municipio VALUES (118, 10, 'JIMAGUAYU', false);
INSERT INTO public.municipio VALUES (119, 10, 'NAJASA', false);
INSERT INTO public.municipio VALUES (120, 10, 'SANTA CRUZ DEL SUR', false);
INSERT INTO public.municipio VALUES (121, 11, 'MANATI', false);
INSERT INTO public.municipio VALUES (122, 11, 'PUERTO PADRE', false);
INSERT INTO public.municipio VALUES (123, 11, 'JESUS MENENDEZ', false);
INSERT INTO public.municipio VALUES (124, 11, 'MAJIBACOA', false);
INSERT INTO public.municipio VALUES (125, 11, 'LAS TUNAS', false);
INSERT INTO public.municipio VALUES (126, 11, 'JOBABO', false);
INSERT INTO public.municipio VALUES (127, 11, 'COLOMBIA', false);
INSERT INTO public.municipio VALUES (128, 11, 'AMANCIO', false);
INSERT INTO public.municipio VALUES (129, 12, 'GIBARA', false);
INSERT INTO public.municipio VALUES (130, 12, 'RAFAEL FREYRE', false);
INSERT INTO public.municipio VALUES (131, 12, 'BANES', false);
INSERT INTO public.municipio VALUES (132, 12, 'ANTILLA', false);
INSERT INTO public.municipio VALUES (133, 12, 'BAGUANOS', false);
INSERT INTO public.municipio VALUES (134, 12, 'HOLGUIN', false);
INSERT INTO public.municipio VALUES (135, 12, 'CALIXTO GARCIA', false);
INSERT INTO public.municipio VALUES (136, 12, 'CACOCUM', false);
INSERT INTO public.municipio VALUES (137, 12, 'URBANO NORIS', false);
INSERT INTO public.municipio VALUES (138, 12, 'CUETO', false);
INSERT INTO public.municipio VALUES (139, 12, 'MAYARI', false);
INSERT INTO public.municipio VALUES (140, 12, 'FRANK PAIS', false);
INSERT INTO public.municipio VALUES (141, 12, 'SAGUA DE TANAMO', false);
INSERT INTO public.municipio VALUES (142, 12, 'MOA', false);
INSERT INTO public.municipio VALUES (143, 5, 'MATANZAS', false);
INSERT INTO public.municipio VALUES (144, 5, 'CARDENAS', false);
INSERT INTO public.municipio VALUES (145, 5, 'MARTI', false);
INSERT INTO public.municipio VALUES (146, 5, 'COLON', false);
INSERT INTO public.municipio VALUES (147, 5, 'PERICO', false);
INSERT INTO public.municipio VALUES (148, 5, 'JOVELLANOS', false);
INSERT INTO public.municipio VALUES (149, 5, 'PEDRO BETANCOURT', false);
INSERT INTO public.municipio VALUES (150, 5, 'LIMONAR', false);
INSERT INTO public.municipio VALUES (151, 5, 'UNION DE REYES', false);
INSERT INTO public.municipio VALUES (152, 7, 'RODAS', false);
INSERT INTO public.municipio VALUES (153, 7, 'PALMIRA', false);
INSERT INTO public.municipio VALUES (154, 7, 'LAJAS', false);
INSERT INTO public.municipio VALUES (155, 7, 'CRUCES', false);
INSERT INTO public.municipio VALUES (156, 7, 'CUMANAYAGUA', false);
INSERT INTO public.municipio VALUES (157, 7, 'ABREUS', false);
INSERT INTO public.municipio VALUES (158, 15, 'MANUEL TAMES', false);
INSERT INTO public.municipio VALUES (159, 15, 'IMIAS', false);
INSERT INTO public.municipio VALUES (160, 15, 'SAN ANTONIO DEL SUR', false);
INSERT INTO public.municipio VALUES (161, 15, 'CAIMANERA', false);
INSERT INTO public.municipio VALUES (162, 15, 'NICETO PEREZ', false);
INSERT INTO public.municipio VALUES (163, 16, 'ISLA DE LA JUVENTUD', false);
INSERT INTO public.municipio VALUES (164, 3, 'VI�ALES', false);
INSERT INTO public.municipio VALUES (165, 1, 'SAN ANTONIO DE LOS BA�OS', false);
INSERT INTO public.municipio VALUES (166, 7, 'AGUADA DE PASAJEROS', false);
INSERT INTO public.municipio VALUES (167, 3, 'SAN LUIS', false);
INSERT INTO public.municipio VALUES (168, 7, 'CIENFUEGOS', false);


--
-- TOC entry 3661 (class 0 OID 33696)
-- Dependencies: 265
-- Data for Name: nivel_escolar; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.nivel_escolar VALUES (1, 'Primaria', false);
INSERT INTO public.nivel_escolar VALUES (2, 'Media', false);
INSERT INTO public.nivel_escolar VALUES (3, 'Media Superior', false);
INSERT INTO public.nivel_escolar VALUES (4, 'Superior', false);
INSERT INTO public.nivel_escolar VALUES (5, 'Desconocido', false);


--
-- TOC entry 3663 (class 0 OID 33700)
-- Dependencies: 267
-- Data for Name: ocupacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ocupacion VALUES (1, 'Obrero', false);
INSERT INTO public.ocupacion VALUES (2, 'T�cnico', false);
INSERT INTO public.ocupacion VALUES (3, 'Dirigente', false);
INSERT INTO public.ocupacion VALUES (4, 'Profesional', false);
INSERT INTO public.ocupacion VALUES (5, 'Ama de casa', false);
INSERT INTO public.ocupacion VALUES (6, 'Campesino', false);
INSERT INTO public.ocupacion VALUES (7, 'Cuentapropista', false);
INSERT INTO public.ocupacion VALUES (8, 'Jubilado', false);
INSERT INTO public.ocupacion VALUES (9, 'Servicios', false);
INSERT INTO public.ocupacion VALUES (10, 'Administrativo', false);
INSERT INTO public.ocupacion VALUES (11, 'Desconocido', false);
INSERT INTO public.ocupacion VALUES (12, 'Otra ocupaci�n', false);
INSERT INTO public.ocupacion VALUES (13, 'Turismo', false);


--
-- TOC entry 3665 (class 0 OID 33704)
-- Dependencies: 269
-- Data for Name: ong; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3666 (class 0 OID 33707)
-- Dependencies: 270
-- Data for Name: ong_estudiante; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3668 (class 0 OID 33711)
-- Dependencies: 272
-- Data for Name: organismo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.organismo VALUES (1, 'INSTITUTO NACIONAL DE RESERVAS ESTATALES (INRE)', false);
INSERT INTO public.organismo VALUES (2, 'MINISTERIO DE AUDITORIA Y CONTROL (MAC)', false);
INSERT INTO public.organismo VALUES (3, 'CIMEX', false);
INSERT INTO public.organismo VALUES (4, 'Ministerio de la Industria Alimentaria', false);
INSERT INTO public.organismo VALUES (5, 'Poder Popular de Artemisa', false);
INSERT INTO public.organismo VALUES (6, 'Poder Popular de Mayabeque', false);
INSERT INTO public.organismo VALUES (7, 'No se subordina a un Organismo', false);
INSERT INTO public.organismo VALUES (8, 'Consejo de Ministros', false);
INSERT INTO public.organismo VALUES (9, 'Oficina del Historiador de Ciudad Habana', false);
INSERT INTO public.organismo VALUES (10, 'Ministerio de la Industria Ligera', false);
INSERT INTO public.organismo VALUES (11, 'Ministerio de la Agricultura', false);
INSERT INTO public.organismo VALUES (12, 'Ministerio del Transporte', false);
INSERT INTO public.organismo VALUES (13, 'Instituto de la Aeronautica Civil de Cuba', false);
INSERT INTO public.organismo VALUES (14, 'Ministerio de la Informatica y las Comunicaciones', false);
INSERT INTO public.organismo VALUES (15, 'Ministerio del  Comercio Interior', false);
INSERT INTO public.organismo VALUES (16, 'Ministerio de  Cultura', false);
INSERT INTO public.organismo VALUES (17, 'Ministerio de Salud Publica', false);
INSERT INTO public.organismo VALUES (18, 'Banco Nacional de Cuba', false);
INSERT INTO public.organismo VALUES (19, 'Banco Popular de Ahorro', false);
INSERT INTO public.organismo VALUES (20, 'Ministerio de Finanzas y Precios', false);
INSERT INTO public.organismo VALUES (21, 'Ministerio del Turismo', false);
INSERT INTO public.organismo VALUES (22, 'Banco Central de Cuba', false);
INSERT INTO public.organismo VALUES (23, 'Banco Exterior de Cuba', false);
INSERT INTO public.organismo VALUES (24, 'Ministerio de Trabajo y Seguridad Social', false);
INSERT INTO public.organismo VALUES (25, 'Ministerio de Justicia', false);
INSERT INTO public.organismo VALUES (26, 'Ministerio de Relaciones Exteriores', false);
INSERT INTO public.organismo VALUES (27, 'Tribunal Supremo Popular', false);
INSERT INTO public.organismo VALUES (28, 'Ministerio de las Fuerzas Armadas Revolucionarias', false);
INSERT INTO public.organismo VALUES (29, 'Ministerio del Interior', false);
INSERT INTO public.organismo VALUES (30, 'Consejo de Estado', false);
INSERT INTO public.organismo VALUES (31, 'Comite Ejecutivo del Consejo de Ministros', false);
INSERT INTO public.organismo VALUES (32, 'Poder Popular de la Habana.', false);
INSERT INTO public.organismo VALUES (33, 'Poder Popular de Matanzas.', false);
INSERT INTO public.organismo VALUES (34, 'Poder Popular de Villa Clara.', false);
INSERT INTO public.organismo VALUES (35, 'Poder Popular de Cienfuegos.', false);
INSERT INTO public.organismo VALUES (36, 'Poder Popular de Sancti Spiritus.', false);
INSERT INTO public.organismo VALUES (37, 'Poder Popular de Ciego de Avila.', false);
INSERT INTO public.organismo VALUES (38, 'Poder Popular de Camaguey.', false);
INSERT INTO public.organismo VALUES (39, 'Poder Popular de Las Tunas.', false);
INSERT INTO public.organismo VALUES (40, 'Poder Popular de Holguin.', false);
INSERT INTO public.organismo VALUES (41, 'Poder Popular de Granma.', false);
INSERT INTO public.organismo VALUES (42, 'Poder Popular de Santiago de Cuba.', false);
INSERT INTO public.organismo VALUES (43, 'Poder Popular de Gantanamo.', false);
INSERT INTO public.organismo VALUES (44, 'Poder Popular de Isla de la Juventud.', false);
INSERT INTO public.organismo VALUES (45, 'Partido Comunista de Cuba', false);
INSERT INTO public.organismo VALUES (46, 'Central de Trabajadores de Cuba', false);
INSERT INTO public.organismo VALUES (47, 'Organismo Desconocido', false);


--
-- TOC entry 3670 (class 0 OID 33715)
-- Dependencies: 274
-- Data for Name: organizacion_politica; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.organizacion_politica VALUES (1, 'PCC', false);
INSERT INTO public.organizacion_politica VALUES (2, 'UJC', false);
INSERT INTO public.organizacion_politica VALUES (3, 'Doble Militancia', false);
INSERT INTO public.organizacion_politica VALUES (4, 'Ninguna', false);


--
-- TOC entry 3672 (class 0 OID 33719)
-- Dependencies: 276
-- Data for Name: organizacion_popular; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.organizacion_popular VALUES (1, 'CDR', false);
INSERT INTO public.organizacion_popular VALUES (2, 'FMC', false);


--
-- TOC entry 3673 (class 0 OID 33722)
-- Dependencies: 277
-- Data for Name: organizacion_popular_estudiante; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3675 (class 0 OID 33726)
-- Dependencies: 279
-- Data for Name: pais; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pais VALUES (1, 'Cuba', false);
INSERT INTO public.pais VALUES (-1, '', false);
INSERT INTO public.pais VALUES (2, 'Egipto', false);
INSERT INTO public.pais VALUES (3, 'Croacia', false);
INSERT INTO public.pais VALUES (4, 'T�nez', false);
INSERT INTO public.pais VALUES (5, 'Bolivia', false);
INSERT INTO public.pais VALUES (6, 'Noruega', false);
INSERT INTO public.pais VALUES (7, 'Arabia Saudita', false);
INSERT INTO public.pais VALUES (8, 'Timor Leste', false);
INSERT INTO public.pais VALUES (9, 'Sri Lanka', false);
INSERT INTO public.pais VALUES (10, 'Servia', false);
INSERT INTO public.pais VALUES (11, 'Gibraltar', false);
INSERT INTO public.pais VALUES (12, 'Turkmenist�n', false);
INSERT INTO public.pais VALUES (13, 'Argentina', false);
INSERT INTO public.pais VALUES (14, 'Espa�a', false);
INSERT INTO public.pais VALUES (15, 'Burkina Faso', false);
INSERT INTO public.pais VALUES (16, 'Guernesey', false);
INSERT INTO public.pais VALUES (17, 'Rep�blica de Corea', false);
INSERT INTO public.pais VALUES (18, 'Zimbabwe', false);
INSERT INTO public.pais VALUES (19, 'Islas Marshall', false);
INSERT INTO public.pais VALUES (20, 'Islas V�rgenes de los Estados Unidos', false);
INSERT INTO public.pais VALUES (21, 'Rep�blica Bolivariana de Venezuela', false);
INSERT INTO public.pais VALUES (22, 'Francia', false);
INSERT INTO public.pais VALUES (23, 'Mongolia', false);
INSERT INTO public.pais VALUES (24, 'Mozambique', false);
INSERT INTO public.pais VALUES (25, 'Chad', false);
INSERT INTO public.pais VALUES (26, 'Pakist�n', false);
INSERT INTO public.pais VALUES (27, 'Fiji', false);
INSERT INTO public.pais VALUES (28, 'Guadalupe', false);
INSERT INTO public.pais VALUES (29, 'Filipinas', false);
INSERT INTO public.pais VALUES (30, 'Armenia', false);
INSERT INTO public.pais VALUES (31, 'Sierra Leona', false);
INSERT INTO public.pais VALUES (32, 'Belar�s', false);
INSERT INTO public.pais VALUES (33, 'Rep�blica Unida de Tanzania', false);
INSERT INTO public.pais VALUES (34, 'Islas �land', false);
INSERT INTO public.pais VALUES (35, 'Ir�n', false);
INSERT INTO public.pais VALUES (36, 'Lesotho', false);
INSERT INTO public.pais VALUES (37, 'Islas V�rgenes Brit�nicas', false);
INSERT INTO public.pais VALUES (38, 'Iraq', false);
INSERT INTO public.pais VALUES (39, 'Montenegro', false);
INSERT INTO public.pais VALUES (40, 'Gab�n', false);
INSERT INTO public.pais VALUES (41, 'Chipre', false);
INSERT INTO public.pais VALUES (42, 'Marruecos', false);
INSERT INTO public.pais VALUES (43, 'Qatar', false);
INSERT INTO public.pais VALUES (44, 'China', false);
INSERT INTO public.pais VALUES (45, 'Andorra', false);
INSERT INTO public.pais VALUES (46, 'Jap�n', false);
INSERT INTO public.pais VALUES (47, 'Anguila', false);
INSERT INTO public.pais VALUES (48, 'Saint Kitts y Nevis', false);
INSERT INTO public.pais VALUES (49, 'Bhut�n', false);
INSERT INTO public.pais VALUES (50, 'Bahrein', false);
INSERT INTO public.pais VALUES (51, 'Kazajst�n', false);
INSERT INTO public.pais VALUES (52, 'El Salvador', false);
INSERT INTO public.pais VALUES (53, 'Territorio Palestino Ocupado', false);
INSERT INTO public.pais VALUES (54, 'Granada', false);
INSERT INTO public.pais VALUES (55, 'Tailandia', false);
INSERT INTO public.pais VALUES (56, 'Grecia', false);
INSERT INTO public.pais VALUES (57, 'Islas Norfolk', false);
INSERT INTO public.pais VALUES (58, 'Islas Feroe', false);
INSERT INTO public.pais VALUES (59, 'C�te D�Ivoire', false);
INSERT INTO public.pais VALUES (60, 'Nueva Caledonia', false);
INSERT INTO public.pais VALUES (61, 'Suiza', false);
INSERT INTO public.pais VALUES (62, 'Gambia', false);
INSERT INTO public.pais VALUES (63, 'Eslovaquia', false);
INSERT INTO public.pais VALUES (64, 'Guyana', false);
INSERT INTO public.pais VALUES (65, 'Somalia', false);
INSERT INTO public.pais VALUES (66, 'Austria', false);
INSERT INTO public.pais VALUES (67, 'Puerto Rico', false);
INSERT INTO public.pais VALUES (68, 'Singapur', false);
INSERT INTO public.pais VALUES (69, 'Bermuda', false);
INSERT INTO public.pais VALUES (70, 'Rep�blica Centro Africana', false);
INSERT INTO public.pais VALUES (71, 'Comores', false);
INSERT INTO public.pais VALUES (72, 'Kiribati', false);
INSERT INTO public.pais VALUES (73, 'Nauru', false);
INSERT INTO public.pais VALUES (74, 'Nueva Zelanda', false);
INSERT INTO public.pais VALUES (75, 'Rep�blica Dominicana', false);
INSERT INTO public.pais VALUES (76, 'Irlanda', false);
INSERT INTO public.pais VALUES (77, 'L�bano', false);
INSERT INTO public.pais VALUES (78, 'Aruba', false);
INSERT INTO public.pais VALUES (79, 'Liberia', false);
INSERT INTO public.pais VALUES (80, 'Federaci�n Rusa', false);
INSERT INTO public.pais VALUES (81, 'Reino Unido', false);
INSERT INTO public.pais VALUES (82, 'Hungr�a', false);
INSERT INTO public.pais VALUES (83, 'Dinamarca', false);
INSERT INTO public.pais VALUES (84, 'Paraguay', false);
INSERT INTO public.pais VALUES (85, 'Malasia', false);
INSERT INTO public.pais VALUES (86, 'Estonia', false);
INSERT INTO public.pais VALUES (87, 'Islas Wallis y Futuna', false);
INSERT INTO public.pais VALUES (88, 'Santa Sede', false);
INSERT INTO public.pais VALUES (89, 'Portugal', false);
INSERT INTO public.pais VALUES (90, 'Santo Tom� y Pr�ncipe', false);
INSERT INTO public.pais VALUES (91, 'Honduras', false);
INSERT INTO public.pais VALUES (92, 'Jordania', false);
INSERT INTO public.pais VALUES (93, 'Montserrat', false);
INSERT INTO public.pais VALUES (94, 'Pitcairn', false);
INSERT INTO public.pais VALUES (95, 'Finlandia', false);
INSERT INTO public.pais VALUES (96, 'Macedonia', false);
INSERT INTO public.pais VALUES (97, 'Rwanda', false);
INSERT INTO public.pais VALUES (98, 'Djibouti', false);
INSERT INTO public.pais VALUES (99, 'Suriname', false);
INSERT INTO public.pais VALUES (100, 'M�xico', false);
INSERT INTO public.pais VALUES (101, 'Nigeria', false);
INSERT INTO public.pais VALUES (102, 'Pa�ses Bajos', false);
INSERT INTO public.pais VALUES (103, 'Hait�', false);
INSERT INTO public.pais VALUES (104, 'Rep�blica Democr�tica del Congo', false);
INSERT INTO public.pais VALUES (105, 'Malta', false);
INSERT INTO public.pais VALUES (106, 'Polonia', false);
INSERT INTO public.pais VALUES (107, 'Senegal', false);
INSERT INTO public.pais VALUES (108, 'Yemen', false);
INSERT INTO public.pais VALUES (109, 'Jamaica', false);
INSERT INTO public.pais VALUES (110, 'Guatemala', false);
INSERT INTO public.pais VALUES (111, 'Angola', false);
INSERT INTO public.pais VALUES (112, 'Namibia', false);
INSERT INTO public.pais VALUES (113, 'Cabo Verde', false);
INSERT INTO public.pais VALUES (114, 'Brasil', false);
INSERT INTO public.pais VALUES (115, 'Costa Rica', false);
INSERT INTO public.pais VALUES (116, 'Isla de Man', false);
INSERT INTO public.pais VALUES (117, 'Saint Pierre y Miquelon', false);
INSERT INTO public.pais VALUES (118, 'Swazilandia', false);
INSERT INTO public.pais VALUES (119, 'Togo', false);
INSERT INTO public.pais VALUES (120, 'Kirguizist�n', false);
INSERT INTO public.pais VALUES (121, 'Nepal', false);
INSERT INTO public.pais VALUES (122, 'Mauricio', false);
INSERT INTO public.pais VALUES (123, 'Luxemburgo', false);
INSERT INTO public.pais VALUES (124, 'Rumania', false);
INSERT INTO public.pais VALUES (125, 'Kenya', false);
INSERT INTO public.pais VALUES (126, 'Bangladesh', false);
INSERT INTO public.pais VALUES (127, 'Dominica', false);
INSERT INTO public.pais VALUES (128, 'Tokelau', false);
INSERT INTO public.pais VALUES (129, 'Ucrania', false);
INSERT INTO public.pais VALUES (130, 'Letonia', false);
INSERT INTO public.pais VALUES (131, 'Tuvalu', false);
INSERT INTO public.pais VALUES (132, 'Bosnia y Herzegovina', false);
INSERT INTO public.pais VALUES (133, 'Turqu�a', false);
INSERT INTO public.pais VALUES (134, 'Afganist�n', false);
INSERT INTO public.pais VALUES (135, 'Samoa Americana', false);
INSERT INTO public.pais VALUES (136, 'Congo', false);
INSERT INTO public.pais VALUES (137, 'Indonesia', false);
INSERT INTO public.pais VALUES (138, 'Ecuador', false);
INSERT INTO public.pais VALUES (139, 'Islas Turcas y Caicos', false);
INSERT INTO public.pais VALUES (140, 'Israel', false);
INSERT INTO public.pais VALUES (141, 'B�lgica', false);
INSERT INTO public.pais VALUES (142, 'Groenlandia', false);
INSERT INTO public.pais VALUES (143, 'Alemania', false);
INSERT INTO public.pais VALUES (144, 'Palau', false);
INSERT INTO public.pais VALUES (145, 'Ghana', false);
INSERT INTO public.pais VALUES (146, 'Barbados', false);
INSERT INTO public.pais VALUES (147, 'Lituania', false);
INSERT INTO public.pais VALUES (148, 'Myanmar', false);
INSERT INTO public.pais VALUES (149, 'Burundi', false);
INSERT INTO public.pais VALUES (150, 'Islas Svalbard y Jan Mayen', false);
INSERT INTO public.pais VALUES (151, 'Panam�', false);
INSERT INTO public.pais VALUES (152, 'Micronesia', false);
INSERT INTO public.pais VALUES (153, 'Eritrea', false);
INSERT INTO public.pais VALUES (154, 'Jersey', false);
INSERT INTO public.pais VALUES (155, 'Benin', false);
INSERT INTO public.pais VALUES (156, 'Malawi', false);
INSERT INTO public.pais VALUES (157, 'Estados Unidos', false);
INSERT INTO public.pais VALUES (158, 'Azerbaiy�n', false);
INSERT INTO public.pais VALUES (159, 'Georgia', false);
INSERT INTO public.pais VALUES (160, 'Uzbekist�n', false);
INSERT INTO public.pais VALUES (161, 'India', false);
INSERT INTO public.pais VALUES (162, 'Islas Caim�n', false);
INSERT INTO public.pais VALUES (163, 'Madagascar', false);
INSERT INTO public.pais VALUES (164, 'Santa Elena', false);
INSERT INTO public.pais VALUES (165, 'Uruguay', false);
INSERT INTO public.pais VALUES (166, 'Santa Luc�a', false);
INSERT INTO public.pais VALUES (167, 'Sud�frica', false);
INSERT INTO public.pais VALUES (168, 'Bahamas', false);
INSERT INTO public.pais VALUES (169, 'Hong Kong (regi�n administrativa especial de China)', false);
INSERT INTO public.pais VALUES (170, 'Guinea-Bissau', false);
INSERT INTO public.pais VALUES (171, 'Seychelles', false);
INSERT INTO public.pais VALUES (172, 'Vanuatu', false);
INSERT INTO public.pais VALUES (173, 'Islas Marianas Septentrionales', false);
INSERT INTO public.pais VALUES (174, 'Argelia', false);
INSERT INTO public.pais VALUES (175, 'Provincia China de Taiw�n', false);
INSERT INTO public.pais VALUES (176, 'Guinea Ecuatorial', false);
INSERT INTO public.pais VALUES (177, 'Kuwait', false);
INSERT INTO public.pais VALUES (178, 'Colombia', false);
INSERT INTO public.pais VALUES (179, 'Papua Nueva Guinea', false);
INSERT INTO public.pais VALUES (180, 'Camer�n', false);
INSERT INTO public.pais VALUES (181, 'Trinidad y Tobago', false);
INSERT INTO public.pais VALUES (182, 'Islas Malvinas', false);
INSERT INTO public.pais VALUES (183, 'Sud�n', false);
INSERT INTO public.pais VALUES (184, 'Liechtenstein', false);
INSERT INTO public.pais VALUES (185, 'Guayana Francesa', false);
INSERT INTO public.pais VALUES (186, 'Brunei Darussalam', false);
INSERT INTO public.pais VALUES (187, 'Reuni�n', false);
INSERT INTO public.pais VALUES (188, 'Sahara Occidental', false);
INSERT INTO public.pais VALUES (189, 'Guinea', false);
INSERT INTO public.pais VALUES (190, 'Zambia', false);
INSERT INTO public.pais VALUES (191, 'M�naco', false);
INSERT INTO public.pais VALUES (192, 'Botswana', false);
INSERT INTO public.pais VALUES (193, 'Rep�blica de Moldavia', false);
INSERT INTO public.pais VALUES (194, 'Nicaragua', false);
INSERT INTO public.pais VALUES (195, 'Rep�blica Checa', false);
INSERT INTO public.pais VALUES (196, 'Samoa', false);
INSERT INTO public.pais VALUES (197, 'Macao', false);
INSERT INTO public.pais VALUES (198, 'Tayikist�n', false);
INSERT INTO public.pais VALUES (199, 'Maldivas', false);
INSERT INTO public.pais VALUES (200, 'Rep�blica Democr�tica Popular Lao', false);
INSERT INTO public.pais VALUES (201, 'Emiratos Arabes Unidos', false);
INSERT INTO public.pais VALUES (202, 'Martinica', false);
INSERT INTO public.pais VALUES (203, 'N�ger', false);
INSERT INTO public.pais VALUES (204, 'Rep�blica Arabe Siria', false);
INSERT INTO public.pais VALUES (205, 'Etiop�a', false);
INSERT INTO public.pais VALUES (206, 'Islandia', false);
INSERT INTO public.pais VALUES (207, 'Canad�', false);
INSERT INTO public.pais VALUES (208, 'Mayotte', false);
INSERT INTO public.pais VALUES (209, 'Polinesia Francesa', false);
INSERT INTO public.pais VALUES (210, 'San Vicente y las Granadinas', false);
INSERT INTO public.pais VALUES (211, 'Suecia', false);
INSERT INTO public.pais VALUES (212, 'Islas Salom�n', false);
INSERT INTO public.pais VALUES (213, 'Mauritania', false);
INSERT INTO public.pais VALUES (214, 'Albania', false);
INSERT INTO public.pais VALUES (215, 'Islas Cook', false);
INSERT INTO public.pais VALUES (216, 'Italia', false);
INSERT INTO public.pais VALUES (217, 'Rep�blica Popular Democr�tica de Corea', false);
INSERT INTO public.pais VALUES (218, 'Jamahiriya Arabe de Libia', false);
INSERT INTO public.pais VALUES (219, 'Uganda', false);
INSERT INTO public.pais VALUES (220, 'Om�n', false);
INSERT INTO public.pais VALUES (221, 'Antigua y Barbuda', false);
INSERT INTO public.pais VALUES (222, 'Chile', false);
INSERT INTO public.pais VALUES (223, 'Belice', false);
INSERT INTO public.pais VALUES (224, 'Mal�', false);
INSERT INTO public.pais VALUES (225, 'Australia', false);
INSERT INTO public.pais VALUES (226, 'Guam', false);
INSERT INTO public.pais VALUES (227, 'Camboya', false);
INSERT INTO public.pais VALUES (228, 'Viet Nam', false);
INSERT INTO public.pais VALUES (229, 'Niue', false);
INSERT INTO public.pais VALUES (230, 'Tonga', false);
INSERT INTO public.pais VALUES (231, 'San Marino', false);
INSERT INTO public.pais VALUES (232, 'Per�', false);
INSERT INTO public.pais VALUES (233, 'Eslovenia', false);
INSERT INTO public.pais VALUES (234, 'Bulgaria', false);
INSERT INTO public.pais VALUES (235, 'No Especificado', false);
INSERT INTO public.pais VALUES (236, 'Curazao', false);
INSERT INTO public.pais VALUES (237, 'Sint Marteen (parte Holandesa)', false);
INSERT INTO public.pais VALUES (238, 'Donaire, Saint Eustatius y Saba', false);
INSERT INTO public.pais VALUES (239, 'Saint-Bartolom�', false);


--
-- TOC entry 3677 (class 0 OID 33730)
-- Dependencies: 281
-- Data for Name: planestudio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3679 (class 0 OID 33736)
-- Dependencies: 283
-- Data for Name: procedencia_escolar; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.procedencia_escolar VALUES (1, 'Preuniversitario', false);
INSERT INTO public.procedencia_escolar VALUES (2, 'Ense�anza T�cnica y Profesional', false);
INSERT INTO public.procedencia_escolar VALUES (3, 'FOC', false);
INSERT INTO public.procedencia_escolar VALUES (4, 'Estudios en el extranjero', false);
INSERT INTO public.procedencia_escolar VALUES (5, 'Curso de Superaci�n Integral para J�venes', false);
INSERT INTO public.procedencia_escolar VALUES (6, 'Nivelaci�n Orden 18', false);
INSERT INTO public.procedencia_escolar VALUES (7, 'Escuela Emergente', false);


--
-- TOC entry 3681 (class 0 OID 33740)
-- Dependencies: 285
-- Data for Name: provincia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.provincia VALUES (-1, -1, '', false);
INSERT INTO public.provincia VALUES (1, 1, 'Artemisa', false);
INSERT INTO public.provincia VALUES (2, 1, 'Mayabeque', false);
INSERT INTO public.provincia VALUES (3, 1, 'Pinar del R�o', false);
INSERT INTO public.provincia VALUES (4, 1, 'La Habana', false);
INSERT INTO public.provincia VALUES (5, 1, 'Matanzas', false);
INSERT INTO public.provincia VALUES (6, 1, 'Villa Clara', false);
INSERT INTO public.provincia VALUES (7, 1, 'Cienfuegos', false);
INSERT INTO public.provincia VALUES (8, 1, 'Santis Sp�ritus', false);
INSERT INTO public.provincia VALUES (9, 1, 'Ciego de Avila', false);
INSERT INTO public.provincia VALUES (10, 1, 'Camag�ey', false);
INSERT INTO public.provincia VALUES (11, 1, 'Las Tunas', false);
INSERT INTO public.provincia VALUES (12, 1, 'Holgu�n', false);
INSERT INTO public.provincia VALUES (13, 1, 'Granma', false);
INSERT INTO public.provincia VALUES (14, 1, 'Santiago de Cuba', false);
INSERT INTO public.provincia VALUES (15, 1, 'Guant�namo', false);
INSERT INTO public.provincia VALUES (16, 1, 'Isla de la Juventud', false);


--
-- TOC entry 3683 (class 0 OID 33744)
-- Dependencies: 287
-- Data for Name: sexo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sexo VALUES (1, 'Femenino', false);
INSERT INTO public.sexo VALUES (2, 'Masculino', false);


--
-- TOC entry 3685 (class 0 OID 33748)
-- Dependencies: 289
-- Data for Name: sindicato; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sindicato VALUES (1, 'Sindicato Nacional de los Trabajadores Civiles de la Defensa', false);
INSERT INTO public.sindicato VALUES (2, 'Sindicato Nacional de los Trabajadores del Comercio y la Gastronom�a', false);
INSERT INTO public.sindicato VALUES (3, 'Sindicato Nacional de los Trabajadores de las Comunicaciones', false);
INSERT INTO public.sindicato VALUES (4, 'Sindicato Nacional de los Trabajadores de la Construcci�n', false);
INSERT INTO public.sindicato VALUES (5, 'Sindicato Nacional de los Trabajadores de la Cultura', false);
INSERT INTO public.sindicato VALUES (6, 'Sindicato Nacional de los Trabajadores de la Educaci�n y el Deporte', false);
INSERT INTO public.sindicato VALUES (7, 'Sindicato Nacional de los Trabajadores de la Industria Alimenticia', false);
INSERT INTO public.sindicato VALUES (8, 'Sindicato Nacional de los Trabajadores de la Industria Ligera', false);
INSERT INTO public.sindicato VALUES (9, 'Sindicato Nacional de los Trabajadores de la Industria Qu�mico - Minero - Energ�tica', false);
INSERT INTO public.sindicato VALUES (10, 'Sindicato Nacional de los Trabajadores de la Marina Mercante, Puertos y Pesca', false);
INSERT INTO public.sindicato VALUES (11, 'Sindicato Nacional de los Trabajadores Metal�rgicos y Electr�nicos', false);
INSERT INTO public.sindicato VALUES (12, 'Sindicato Nacional de los Trabajadores de la Salud', false);
INSERT INTO public.sindicato VALUES (13, 'Sindicato Nacional de los Trabajadores Tabacaleros', false);
INSERT INTO public.sindicato VALUES (14, 'Sindicato Nacional de los Trabajadores del Transporte', false);
INSERT INTO public.sindicato VALUES (15, 'Sindicato Nacional de los Trabajadores del Turismo', false);
INSERT INTO public.sindicato VALUES (16, 'Sindicato Nacional de los Trabajadores de las Ciencias', false);
INSERT INTO public.sindicato VALUES (17, 'Sindicato Nacional de los Trabajadores Azucareros', false);
INSERT INTO public.sindicato VALUES (18, 'Sindicato Nacional de los Trabajadores Agropecuarios y Forestales', false);
INSERT INTO public.sindicato VALUES (19, 'Sindicato Nacional de los Trabajadores de la Administraci�n P�blica', false);
INSERT INTO public.sindicato VALUES (20, 'Desconocido', false);


--
-- TOC entry 3687 (class 0 OID 33752)
-- Dependencies: 291
-- Data for Name: tipo_asignatura; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_asignatura VALUES (1, 'B�sica', false);
INSERT INTO public.tipo_asignatura VALUES (2, 'Obligatoria', false);
INSERT INTO public.tipo_asignatura VALUES (3, 'Optativa', false);
INSERT INTO public.tipo_asignatura VALUES (4, 'Electiva', false);


--
-- TOC entry 3689 (class 0 OID 33756)
-- Dependencies: 293
-- Data for Name: tipo_baja; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_baja VALUES (1, 'Temporal', false);
INSERT INTO public.tipo_baja VALUES (2, 'Permanente', false);


--
-- TOC entry 3691 (class 0 OID 33760)
-- Dependencies: 295
-- Data for Name: tipo_evaluacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tipo_evaluacion VALUES (1, 'Examen Final', false);
INSERT INTO public.tipo_evaluacion VALUES (2, 'Trabajo de Curso', false);
INSERT INTO public.tipo_evaluacion VALUES (3, 'Desempe�o', false);


--
-- TOC entry 3693 (class 0 OID 33764)
-- Dependencies: 297
-- Data for Name: tipo_militar; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3695 (class 0 OID 33768)
-- Dependencies: 299
-- Data for Name: tipoplanestudio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3696 (class 0 OID 33771)
-- Dependencies: 300
-- Data for Name: tutor; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3698 (class 0 OID 33775)
-- Dependencies: 302
-- Data for Name: universidad; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.universidad VALUES ('', '', '', '', '', '', '', '', '', '', false, -1);
INSERT INTO public.universidad VALUES ('223.0.06815', 'UNIVERSIDAD AGRARIA DE LA HABANA "FRUCTUOSO RODR�GUEZ P�REZ"', 'UNAH', 'Docente - investigaci�n', '', 'Ministerio de Educaci�n Superior', '', '', '', 'Carretera de Tapaste y 8 V�as ', false, 88);


--
-- TOC entry 3699 (class 0 OID 33780)
-- Dependencies: 303
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users VALUES ('ead', '12345678912', 'Educacion a Distancia', 'Eduacion a Distancia', 'Eduacion a Distancia', 'ead@ead.cu', '$2y$10$ETXivXQTH7.morXKR56TB.7s4ym8932uLWoiO1VJUVjGBr5dyPMM.', true, 'Usuario autogenerado');


--
-- TOC entry 3739 (class 0 OID 0)
-- Dependencies: 216
-- Name: asignatura_asignatura_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.asignatura_asignatura_id_seq', 1, false);


--
-- TOC entry 3740 (class 0 OID 0)
-- Dependencies: 221
-- Name: carrera_idcarrera_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.carrera_idcarrera_seq', 1, false);


--
-- TOC entry 3741 (class 0 OID 0)
-- Dependencies: 223
-- Name: carreranacional_idcarreranacional_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.carreranacional_idcarreranacional_seq', 180, true);


--
-- TOC entry 3742 (class 0 OID 0)
-- Dependencies: 225
-- Name: color_piel_color_piel_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.color_piel_color_piel_id_seq', 4, true);


--
-- TOC entry 3743 (class 0 OID 0)
-- Dependencies: 232
-- Name: especialidad_idespecialidad_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.especialidad_idespecialidad_seq', 12, true);


--
-- TOC entry 3744 (class 0 OID 0)
-- Dependencies: 234
-- Name: especialidad_militar_especialidad_militar_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.especialidad_militar_especialidad_militar_id_seq', 5, true);


--
-- TOC entry 3745 (class 0 OID 0)
-- Dependencies: 236
-- Name: estado_civil_estado_civil_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estado_civil_estado_civil_id_seq', 6, true);


--
-- TOC entry 3746 (class 0 OID 0)
-- Dependencies: 238
-- Name: estado_estudiante_estado_estuciante_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estado_estudiante_estado_estuciante_id_seq', 6, true);


--
-- TOC entry 3747 (class 0 OID 0)
-- Dependencies: 241
-- Name: examen_examen_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.examen_examen_id_seq', 3, true);


--
-- TOC entry 3748 (class 0 OID 0)
-- Dependencies: 250
-- Name: fuente_ingreso_fuente_ingreso_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fuente_ingreso_fuente_ingreso_id_seq', 16, true);


--
-- TOC entry 3749 (class 0 OID 0)
-- Dependencies: 252
-- Name: grado_militar_grado_militar_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.grado_militar_grado_militar_id_seq', 5, true);


--
-- TOC entry 3750 (class 0 OID 0)
-- Dependencies: 254
-- Name: huerfano_huerfano_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.huerfano_huerfano_id_seq', 4, true);


--
-- TOC entry 3751 (class 0 OID 0)
-- Dependencies: 257
-- Name: matricula_matricula_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.matricula_matricula_id_seq', 1, false);


--
-- TOC entry 3752 (class 0 OID 0)
-- Dependencies: 260
-- Name: minusvalia_minusvalia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.minusvalia_minusvalia_id_seq', 12, true);


--
-- TOC entry 3753 (class 0 OID 0)
-- Dependencies: 262
-- Name: motivo_baja_id_motivo_baja_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.motivo_baja_id_motivo_baja_seq', 9, true);


--
-- TOC entry 3754 (class 0 OID 0)
-- Dependencies: 264
-- Name: municipio_idmunicipio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.municipio_idmunicipio_seq', 168, true);


--
-- TOC entry 3755 (class 0 OID 0)
-- Dependencies: 266
-- Name: nivel_escolar_nivel_escolar_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.nivel_escolar_nivel_escolar_id_seq', 5, true);


--
-- TOC entry 3756 (class 0 OID 0)
-- Dependencies: 268
-- Name: ocupacion_ocupacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ocupacion_ocupacion_id_seq', 13, true);


--
-- TOC entry 3757 (class 0 OID 0)
-- Dependencies: 271
-- Name: ong_ong_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ong_ong_id_seq', 1, false);


--
-- TOC entry 3758 (class 0 OID 0)
-- Dependencies: 273
-- Name: organismo_idorganismo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organismo_idorganismo_seq', 47, true);


--
-- TOC entry 3759 (class 0 OID 0)
-- Dependencies: 275
-- Name: organizacion_politica_organizacion_politica_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organizacion_politica_organizacion_politica_id_seq', 4, true);


--
-- TOC entry 3760 (class 0 OID 0)
-- Dependencies: 278
-- Name: organizacion_popular_organizacion_popular_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organizacion_popular_organizacion_popular_id_seq', 2, true);


--
-- TOC entry 3761 (class 0 OID 0)
-- Dependencies: 280
-- Name: pais_idpais_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pais_idpais_seq', 239, true);


--
-- TOC entry 3762 (class 0 OID 0)
-- Dependencies: 282
-- Name: planestudio_idplanestudio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.planestudio_idplanestudio_seq', 1, false);


--
-- TOC entry 3763 (class 0 OID 0)
-- Dependencies: 284
-- Name: procedencia_escolar_procedencia_escolar_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.procedencia_escolar_procedencia_escolar_id_seq', 7, true);


--
-- TOC entry 3764 (class 0 OID 0)
-- Dependencies: 286
-- Name: provincia_idprovincia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.provincia_idprovincia_seq', 16, true);


--
-- TOC entry 3765 (class 0 OID 0)
-- Dependencies: 288
-- Name: sexo_sexo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sexo_sexo_id_seq', 2, true);


--
-- TOC entry 3766 (class 0 OID 0)
-- Dependencies: 290
-- Name: sindicato_idsindicato_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sindicato_idsindicato_seq', 20, true);


--
-- TOC entry 3767 (class 0 OID 0)
-- Dependencies: 292
-- Name: tipo_asignatura_tipo_asignatura_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_asignatura_tipo_asignatura_id_seq', 4, true);


--
-- TOC entry 3768 (class 0 OID 0)
-- Dependencies: 294
-- Name: tipo_baja_id_tipo_baja_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_baja_id_tipo_baja_seq', 2, true);


--
-- TOC entry 3769 (class 0 OID 0)
-- Dependencies: 296
-- Name: tipo_evaluacion_tipo_evaluacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_evaluacion_tipo_evaluacion_id_seq', 3, true);


--
-- TOC entry 3770 (class 0 OID 0)
-- Dependencies: 298
-- Name: tipo_militar_id_tipo_militar_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_militar_id_tipo_militar_seq', 1, false);


--
-- TOC entry 3771 (class 0 OID 0)
-- Dependencies: 301
-- Name: tutor_tutor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tutor_tutor_id_seq', 1, false);


--
-- TOC entry 3705 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2023-08-22 13:08:47

--
-- PostgreSQL database dump complete
--

