-- WARNING: This schema is for context only and is not meant to be run.
-- Table order and constraints may not be valid for execution.

CREATE TABLE public.catalogo_eei (
                                     id_catalogo_eei bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
                                     nombre_cientifico character varying NOT NULL UNIQUE,
                                     nombre_comun character varying,
                                     reino character varying,
                                     familia character varying,
                                     normativa_ref text,
                                     fecha_actualizacion timestamp with time zone,
                                     CONSTRAINT catalogo_eei_pkey PRIMARY KEY (id_catalogo_eei)
);
CREATE TABLE public.comentario (
                                   id_comentario bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
                                   id_observacion bigint NOT NULL,
                                   id_usuario bigint NOT NULL,
                                   contenido text NOT NULL,
                                   creado_en timestamp with time zone NOT NULL DEFAULT now(),
                                   editado_en timestamp with time zone,
                                   CONSTRAINT comentario_pkey PRIMARY KEY (id_comentario),
                                   CONSTRAINT comentario_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario),
                                   CONSTRAINT comentario_id_observacion_fkey FOREIGN KEY (id_observacion) REFERENCES public.observacion(id_observacion)
);
CREATE TABLE public.especie (
                                id_especie bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
                                nombre_cientifico character varying NOT NULL UNIQUE,
                                nombre_comun character varying,
                                familia character varying,
                                descripcion text,
                                id_catalogo_eei bigint,
                                CONSTRAINT especie_pkey PRIMARY KEY (id_especie),
                                CONSTRAINT especie_id_catalogo_eei_fkey FOREIGN KEY (id_catalogo_eei) REFERENCES public.catalogo_eei(id_catalogo_eei)
);
CREATE TABLE public.imagen (
                               id_imagen bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
                               id_observacion bigint NOT NULL,
                               url_archivo text NOT NULL,
                               titulo character varying,
                               creado_en timestamp without time zone NOT NULL DEFAULT now(),
                               CONSTRAINT imagen_pkey PRIMARY KEY (id_imagen),
                               CONSTRAINT imagen_id_observacion_fkey FOREIGN KEY (id_observacion) REFERENCES public.observacion(id_observacion)
);
CREATE TABLE public.observacion (
                                    id_observacion bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
                                    id_usuario bigint NOT NULL,
                                    id_tipo_observacion smallint NOT NULL,
                                    id_especie bigint,
                                    titulo character varying,
                                    descripcion text,
                                    fecha_observacion timestamp with time zone NOT NULL DEFAULT now(),
                                    estado character varying NOT NULL DEFAULT 'ABIERTA'::character varying,
                                    nombre_tradicional character varying,
                                    latitud numeric NOT NULL,
                                    longitud numeric NOT NULL,
                                    direccion_txt text,
                                    nombre_zona character varying,
                                    estado_identificacion character varying,
                                    fuente_identificacion character varying,
                                    confianza_ia numeric,
                                    creado_en timestamp with time zone NOT NULL DEFAULT now(),
                                    actualizado_en timestamp with time zone NOT NULL DEFAULT now(),
                                    CONSTRAINT observacion_pkey PRIMARY KEY (id_observacion),
                                    CONSTRAINT observacion_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario),
                                    CONSTRAINT observacion_id_tipo_observacion_fkey FOREIGN KEY (id_tipo_observacion) REFERENCES public.tipo_observacion(id_tipo_observacion),
                                    CONSTRAINT observacion_id_especie_fkey FOREIGN KEY (id_especie) REFERENCES public.especie(id_especie)
);
CREATE TABLE public.observacion_like (
                                         id_like bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
                                         id_observacion bigint NOT NULL,
                                         id_usuario bigint NOT NULL,
                                         creado_en timestamp with time zone NOT NULL DEFAULT now(),
                                         CONSTRAINT observacion_like_pkey PRIMARY KEY (id_like),
                                         CONSTRAINT observacion_like_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario),
                                         CONSTRAINT observacion_like_id_observacion_fkey FOREIGN KEY (id_observacion) REFERENCES public.observacion(id_observacion)
);
CREATE TABLE public.tipo_observacion (
                                         id_tipo_observacion smallint GENERATED ALWAYS AS IDENTITY NOT NULL,
                                         nombre character varying NOT NULL UNIQUE,
                                         CONSTRAINT tipo_observacion_pkey PRIMARY KEY (id_tipo_observacion)
);
CREATE TABLE public.usuario (
                                id_usuario bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
                                nombre character varying NOT NULL,
                                apellidos character varying,
                                nick character varying NOT NULL UNIQUE,
                                email character varying UNIQUE,
                                password_hash character varying NOT NULL,
                                avatar_url text,
                                rol character varying NOT NULL DEFAULT 'USER'::character varying,
                                activo boolean NOT NULL DEFAULT true,
                                fecha_registro timestamp with time zone NOT NULL DEFAULT now(),
                                ultimo_acceso timestamp with time zone,
                                CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario)
);