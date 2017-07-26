CREATE DATABASE first_wicket
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

----------------------------------------------------------------------------------------

-- Table: public.logins

-- DROP TABLE public.logins;

CREATE TABLE public.logins
(
    id integer NOT NULL,
    name character varying(100) COLLATE pg_catalog."default",
    pass character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT logins_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.logins
    OWNER to postgres;


----------------------------------------------------------------------------------------

insert into logins
select 1, 'admin', 'admin'
union all
select 2, 'user', 'user'
union all
select 3, 'user2', 'user2'