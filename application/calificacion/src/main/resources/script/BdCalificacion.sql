SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

CREATE SCHEMA "calificacionSch";

ALTER SCHEMA "calificacionSch" OWNER TO postgres;

COMMENT ON SCHEMA "calificacionSch" IS 'Esquema principal de la base de datos Calificacion';

CREATE SEQUENCE "calificacionSch"."CALIFICACION_SEQ"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
ALTER TABLE "calificacionSch"."CALIFICACION_SEQ" OWNER TO postgres;

SET default_tablespace = "pg_default";

SET default_table_access_method = heap;

CREATE TABLE "calificacionSch"."CALIFICACION" (
    "ID_CALIFICACION" smallint DEFAULT nextval('"calificacionSch"."CALIFICACION_SEQ"'::regclass) NOT NULL,
    "ID_PROVEEDOR" character varying(200),
    "CALIFICACION" smallint,
    "ESTADO_CALIFICACION" boolean
);

ALTER TABLE "calificacionSch"."CALIFICACION" OWNER TO postgres;
--
CREATE SEQUENCE "calificacionSch"."HISTORIAL_CALIFICACION_SEQ"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;
	
CREATE TABLE "calificacionSch"."HISTORIAL_CALIFICACION" (
    "ID" smallint DEFAULT nextval('"calificacionSch"."HISTORIAL_CALIFICACION_SEQ"'::regclass) NOT NULL,
    "ID_CLIENTE" character varying(200),
	"ID_PROVEEDOR" character varying(200), 
	"CALIFICACION" smallint,
	"FECHA_CALIFICACION" date,
	"ESTADO_CALIFICACION" boolean
);

ALTER TABLE "calificacionSch"."HISTORIAL_CALIFICACION" OWNER TO postgres;

COMMENT ON TABLE "calificacionSch"."HISTORIAL_CALIFICACION" IS 'Historial de calificaciones de un proveedor';
	
ALTER TABLE ONLY "calificacionSch"."CALIFICACION"
    ADD CONSTRAINT "CALIFICACION_PK" PRIMARY KEY ("ID_CALIFICACION");
	COMMENT ON CONSTRAINT "CALIFICACION_PK" ON "calificacionSch"."CALIFICACION" IS 'Llave primarya Tabla CALIFICACION';
	SET default_tablespace = 'pg_default';
	
ALTER TABLE ONLY "calificacionSch"."HISTORIAL_CALIFICACION"
    ADD CONSTRAINT "ID_HISTORIAL_CALIFICACION_PK" PRIMARY KEY ("ID");
	COMMENT ON CONSTRAINT "ID_HISTORIAL_CALIFICACION_PK" ON "calificacionSch"."HISTORIAL_CALIFICACION" IS 'Llave primaria de un registro en historial Calificación ';
	SET default_tablespace = "pg_default";
	




