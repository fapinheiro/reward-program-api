-- TABLES
create table if not exists distritos (
    cod_distrito int primary key, 
    nome_distrito varchar(100) not null,
    creation_at timestamp not null,
    updated_at timestamp null,
);

create table if not exists concelhos (
    id_concelho int primary key, 
    cod_concelho int not null,
    cod_distrito int not null,
    nome_concelho varchar(100) not null,
    creation_at timestamp not null,
    updated_at timestamp null,
    constraint fk_distrito_concelhos foreign key (cod_distrito) references distritos(cod_distrito)
);

create table if not exists codigos_postais (
    cod_codigo_postal int primary key,
    id_concelho int not null,
    codigo_postal varchar(10) not null,
    localidade varchar(100) not null,
    creation_at timestamp not null,
    updated_at timestamp null,
    constraint fk_concelhos_codigos_postais foreign key (id_concelho) references concelhos(id_concelho)
);

create table if not exists clients ( 
   cod_cliente int primary key, 
   email varchar(100) not null, 
   password varchar(100) not null,
   name varchar(100) not null,
   phone varchar(20) not null,
   nif varchar(10) not null,
   cod_codigo_postal int not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   constraint fk_codigos_postais_clients foreign key (cod_codigo_postal) references codigos_postais(cod_codigo_postal)
);

create table if not exists users ( 
   cod_user int primary key auto_increment,
   cod_cliente int null, 
   login varchar(100) not null, 
   password varchar(100) not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   constraint fk_users_clientes foreign key (cod_cliente) references clients(cod_cliente)
);

create table if not exists indications ( 
   cod_indication int primary key, 
   cod_cliente int not null,
   email varchar(100) not null, 
   name varchar(100) not null,
   phone varchar(20) not null,
   status enum('CREATED', 'SENT', 'RESENT', 'ACCEPTED', 'CONVERTED', 'EXPIRED', 'CANCELED') not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   constraint fk_clients_indications foreign key (cod_cliente) references clients(cod_cliente)
);

create table if not exists parameters ( 
   cod_param int primary key, 
   indication_expiration int not null,
   score_expiration int not null, 
   creation_at timestamp not null,
   updated_at timestamp null
);

-- SEQUENCES
create sequence if not exists seq_users start with 1 increment by 1 maxvalue 99999999;
create sequence if not exists seq_codigos_postais start with 1 increment by 1 maxvalue 99999999;
create sequence if not exists seq_clients start with 1 increment by 1 maxvalue 99999999;
create sequence if not exists seq_concelho start with 1 increment by 1 maxvalue 99999999;
create sequence if not exists seq_indications start with 1 increment by 1 maxvalue 99999999;
create sequence if not exists seq_parameters start with 1 increment by 1 maxvalue 99999999;

-- CONSTRAINTS
alter table users add constraint login_unique unique(login);
alter table clients add constraint clients_email_unique unique(email);
alter table concelhos add constraint concelho_distrito_unique unique(cod_concelho, cod_distrito);
alter table indications add constraint indications_email_unique unique(email);