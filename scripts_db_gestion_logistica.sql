CREATE DATABASE "authorization-server"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Colombia.1252'
    LC_CTYPE = 'Spanish_Colombia.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
	-- Table: public.oauth2_authorization

-- DROP TABLE IF EXISTS public.oauth2_authorization;

CREATE TABLE IF NOT EXISTS public.oauth2_authorization
(
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    registered_client_id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    principal_name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    authorization_grant_type character varying(100) COLLATE pg_catalog."default" NOT NULL,
    attributes text COLLATE pg_catalog."default",
    state character varying(500) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    authorization_code_value text COLLATE pg_catalog."default",
    authorization_code_issued_at timestamp without time zone,
    authorization_code_expires_at timestamp without time zone,
    authorization_code_metadata text COLLATE pg_catalog."default",
    access_token_value text COLLATE pg_catalog."default",
    access_token_issued_at timestamp without time zone,
    access_token_expires_at timestamp without time zone,
    access_token_metadata text COLLATE pg_catalog."default",
    access_token_type character varying(100) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    access_token_scopes character varying(1000) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    oidc_id_token_value text COLLATE pg_catalog."default",
    oidc_id_token_issued_at timestamp without time zone,
    oidc_id_token_expires_at timestamp without time zone,
    oidc_id_token_metadata text COLLATE pg_catalog."default",
    refresh_token_value text COLLATE pg_catalog."default",
    refresh_token_issued_at timestamp without time zone,
    refresh_token_expires_at timestamp without time zone,
    refresh_token_metadata text COLLATE pg_catalog."default",
    CONSTRAINT oauth2_authorization_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.oauth2_authorization
    OWNER to postgres;
	

	
-- Table: public.oauth2_authorization_consent

-- DROP TABLE IF EXISTS public.oauth2_authorization_consent;

CREATE TABLE IF NOT EXISTS public.oauth2_authorization_consent
(
    registered_client_id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    principal_name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    authorities character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT oauth2_authorization_consent_pkey PRIMARY KEY (registered_client_id, principal_name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.oauth2_authorization_consent
    OWNER to postgres;
	
-- Table: public.oauth2_registered_client

-- DROP TABLE IF EXISTS public.oauth2_registered_client;

CREATE TABLE IF NOT EXISTS public.oauth2_registered_client
(
    id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    client_id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    client_id_issued_at timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    client_secret character varying(200) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    client_secret_expires_at timestamp without time zone,
    client_name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    client_authentication_methods character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    authorization_grant_types character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    redirect_uris character varying(1000) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    scopes character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    client_settings character varying(2000) COLLATE pg_catalog."default" NOT NULL,
    token_settings character varying(2000) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT oauth2_registered_client_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.oauth2_registered_client
    OWNER to postgres;
	
-- Table: public.user_account

-- DROP TABLE IF EXISTS public.user_account;

CREATE TABLE IF NOT EXISTS public.user_account
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_account_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_account
    OWNER to postgres;
	
-- Table: public.user_account_role

-- DROP TABLE IF EXISTS public.user_account_role;

CREATE TABLE IF NOT EXISTS public.user_account_role
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_account_role_pkey PRIMARY KEY (id),
    CONSTRAINT uk_6044sxgwx7e02symm69mjnfth UNIQUE (role)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_account_roles
    OWNER to postgres;
	
-- Table: public.user_account_roles

-- DROP TABLE IF EXISTS public.user_account_roles;

CREATE TABLE IF NOT EXISTS public.user_account_roles
(
    user_account_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    roles_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_account_roles_pkey PRIMARY KEY (user_account_id, roles_id),
    CONSTRAINT fkpacca51k3kkqoqs0nbmyugdt2 FOREIGN KEY (user_account_id)
        REFERENCES public.user_account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkpcym6uv9vnni6jivyk9wu71hs FOREIGN KEY (roles_id)
        REFERENCES public.user_account_role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_account_roles
    OWNER to postgres;
	
CREATE TABLE IF NOT EXISTS public.clientes
(
    id UUID  DEFAULT gen_random_uuid() NOT NULL,
    nombres character varying(255) COLLATE pg_catalog."default" NOT NULL,
	no_identificacion character varying(50) COLLATE pg_catalog."default" NOT NULL,
	no_telefono character varying(10) COLLATE pg_catalog."default" NOT NULL,
	email character varying(100) COLLATE pg_catalog."default" NOT NULL,
	direccion character varying(200) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT clientes_pkey PRIMARY KEY (id)
   
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.clientes
    OWNER to postgres;
COMMIT;

CREATE TABLE IF NOT EXISTS public.tipo_productos
(
    id UUID  DEFAULT gen_random_uuid() NOT NULL ,
    tipo character varying(255) COLLATE pg_catalog."default" NOT NULL,
	
    CONSTRAINT tipo_productos_pkey PRIMARY KEY (id)
   
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tipo_productos
    OWNER to postgres;
COMMIT;
	
CREATE TABLE IF NOT EXISTS public.bodegas
(
    id UUID  DEFAULT gen_random_uuid()  NOT NULL,
    nombre character varying(255) COLLATE pg_catalog."default" NOT NULL,
	ubicacion character varying(255) COLLATE pg_catalog."default" NOT NULL,
	
    CONSTRAINT pk_bodegas PRIMARY KEY (id)
   
)

TABLESPACE pg_default;
ALTER TABLE IF EXISTS public.bodegas
    OWNER to postgres;

COMMIT;
	
CREATE TABLE IF NOT EXISTS public.puertos_maritimos
(
    id UUID  DEFAULT gen_random_uuid() NOT NULL,
    nombre character varying(255) COLLATE pg_catalog."default" NOT NULL,
	ubicacion character varying(255) COLLATE pg_catalog."default" NOT NULL,
	
    CONSTRAINT pk_puertos_maritimos PRIMARY KEY (id)
   
)


TABLESPACE pg_default;
ALTER TABLE IF EXISTS public.puertos_maritimos
    OWNER to postgres;
	COMMIT;
	
CREATE TABLE IF NOT EXISTS public.logistica
(
    id UUID  DEFAULT gen_random_uuid() NOT NULL,
    cantidad_producto INTEGER  NOT NULL,
	cliente_id UUID NOT NULL,
	tipo_productos_id UUID NOT NULL,
	bodegas_id UUID NOT NULL,
	puertos_maritimos_id UUID NOT NULL,
	fecha_registro timestamp without time zone,
	fecha_entrega timestamp without time zone,
	precio_envio numeric(38,0),
	valor_descuento numeric(38,0),
	valor_total numeric(38,0),
	numero_flota character varying(7) COLLATE pg_catalog."default" NOT NULL,
	placa_vehiculo character varying(6) COLLATE pg_catalog."default" NOT NULL,
	numero_guia character varying(10) COLLATE pg_catalog."default" NOT NULL,
	tipo_Logistica character varying(1) COLLATE pg_catalog."default" NOT NULL,
	
    CONSTRAINT pk_logistica PRIMARY KEY (id),
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id)
        REFERENCES public.clientes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_tipos_productos FOREIGN KEY (tipo_productos_id)
        REFERENCES public.tipo_productos (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	
	CONSTRAINT fk_puertos_maritimos FOREIGN KEY (puertos_maritimos_id)
        REFERENCES public.puertos_maritimos (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	CONSTRAINT fk_bodegas FOREIGN KEY (bodegas_id)
        REFERENCES public.bodegas (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.logistica
    OWNER to postgres;
	COMMIT;
		

	

	
	