-- Database: biblioteca

-- DROP DATABASE biblioteca;

CREATE DATABASE biblioteca
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'C'
    LC_CTYPE = 'C'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

ALTER DATABASE biblioteca
    SET "DateStyle" TO 'ISO, DMY';
    
-- Table: public.alunos

-- DROP TABLE public.alunos;

CREATE TABLE public.alunos
(
    codmatricula bigint NOT NULL,
    nome character varying(150) NOT NULL,
    endereco character varying(150),
    situacao character varying(50),
    CONSTRAINT alunos_pkey PRIMARY KEY (codmatricula)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.alunos
    OWNER to postgres;


-- Table: public.biblioteca

-- DROP TABLE public.biblioteca;

CREATE TABLE public.biblioteca
(
    codbib bigint NOT NULL DEFAULT nextval('biblioteca_codbib_seq'::regclass),
    nome character varying(90) NOT NULL,
    endereco character varying(150),
    CONSTRAINT biblioteca_pkey PRIMARY KEY (codbib)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.biblioteca
    OWNER to postgres;
    
-- Table: public.categoria

-- DROP TABLE public.categoria;

CREATE TABLE public.categoria
(
    codcategoria bigint NOT NULL DEFAULT nextval('categoria_codcategoria_seq'::regclass),
    descricao character varying(90) NOT NULL,
    CONSTRAINT categoria_pkey PRIMARY KEY (codcategoria)
)
WITH (
    OIDS = FALSE
);

-- Table: public.livros

-- DROP TABLE public.livros;

CREATE TABLE public.livros
(
    codlivro bigint NOT NULL DEFAULT nextval('livros_codlivro_seq'::regclass),
    titulo character varying(150) NOT NULL,
    editora character varying(150),
    valor double precision,
    codcategoria bigint NOT NULL,
    codbib bigint NOT NULL,
    situacao character varying(90),
    CONSTRAINT livros_pkey PRIMARY KEY (codlivro),
    CONSTRAINT livros_codbib FOREIGN KEY (codbib)
        REFERENCES public.biblioteca (codbib) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT livros_codcategoria FOREIGN KEY (codcategoria)
        REFERENCES public.categoria (codcategoria) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.livros
    OWNER to postgres;

ALTER TABLE public.categoria
    OWNER to postgres;
    
-- Table: public.empresta

-- DROP TABLE public.empresta;

CREATE TABLE public.empresta
(
    codmatricula bigint NOT NULL,
    codlivro bigint NOT NULL,
    dtretirada date NOT NULL,
    dtprevisao date,
    dtentrega date,
    CONSTRAINT empresta_pkey PRIMARY KEY (codlivro, codmatricula, dtretirada),
    CONSTRAINT empresta_codlivro FOREIGN KEY (codlivro)
        REFERENCES public.livros (codlivro) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT empresta_codmatricula FOREIGN KEY (codmatricula)
        REFERENCES public.alunos (codmatricula) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.empresta
    OWNER to postgres;

-- Index: fki_empresta_codlivro

-- DROP INDEX public.fki_empresta_codlivro;

CREATE INDEX fki_empresta_codlivro
    ON public.empresta USING btree
    (codlivro)
;

-- Index: fki_empresta_codmatricula

-- DROP INDEX public.fki_empresta_codmatricula;

CREATE INDEX fki_empresta_codmatricula
    ON public.empresta USING btree
    (codmatricula)
;

-- Table: public.funcionario

-- DROP TABLE public.funcionario;

CREATE TABLE public.funcionario
(
    codfunc bigint NOT NULL DEFAULT nextval('funcionario_codfunc_seq'::regclass),
    nome character varying(90) NOT NULL,
    endereco character varying(150),
    telefone character varying(20),
    salario double precision,
    codbib bigint NOT NULL,
    CONSTRAINT funcionario_pkey PRIMARY KEY (codfunc),
    CONSTRAINT funcionario_codbib FOREIGN KEY (codbib)
        REFERENCES public.biblioteca (codbib) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.funcionario
    OWNER to postgres;

-- Index: fki_funcionario_codbib

-- DROP INDEX public.fki_funcionario_codbib;

CREATE INDEX fki_funcionario_codbib
    ON public.funcionario USING btree
    (codbib)
;

-- Index: fki_livros_codbib

-- DROP INDEX public.fki_livros_codbib;

CREATE INDEX fki_livros_codbib
    ON public.livros USING btree
    (codbib)
;

-- Index: fki_livros_codcategoria

-- DROP INDEX public.fki_livros_codcategoria;

CREATE INDEX fki_livros_codcategoria
    ON public.livros USING btree
    (codcategoria)
;





CREATE SEQUENCE biblioteca_codbib_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE SEQUENCE categoria_codcategoria_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE SEQUENCE funcionario_codfunc_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE SEQUENCE livros_codlivro_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;



-- SELECT
--     e1.Nome,
--     e2.Nome as nome_amigo, 
--     s.Valor as salario_amigo
-- FROM
--     Estudantes e1
-- LEFT JOIN
--     Amigos a
--     ON a.ID = e1.ID
-- LEFT JOIN
--     Estudante e2
--     ON a.Amigo_ID = e2.id
-- LEFT JOIN
--     Salarios s
--     ON s.ID = e2.id
-- ORDER BY
--     s.Valor DESC

--     