-- TABLES
create table if not exists users ( 
   cod_user int primary key auto_increment, 
   login varchar(100) not null, 
   password varchar(100) not null,
   creation_at timestamp not null,
   updated_at timestamp null,
);

create table if not exists distritos (
    cod_distrito int primary key, 
    nome_distrito varchar(100) not null,
    creation_at timestamp not null,
    updated_at timestamp null,
);

create table if not exists concelhos (
    cod_distrito int not null,
    cod_concelho int not null,
    nome_concelho varchar(100) not null,
    creation_at timestamp not null,
    updated_at timestamp null,
    constraint fk_distrito_concelhos foreign key (cod_distrito) references distritos(cod_distrito),
    constraint pk_concelhos primary key (cod_distrito, cod_concelho)
);

create table if not exists codigos_postais (
    cod_codigo_postal int primary key,
    cod_distrito int not null,
    cod_concelho int not null,
    codigo_postal varchar(10) not null,
    localidade varchar(100) not null,
    creation_at timestamp not null,
    updated_at timestamp null,
    constraint fk_concelhos_codigos_postais foreign key (cod_distrito, cod_concelho) references concelhos(cod_distrito, cod_concelho)
);

create table if not exists clients ( 
   cod_cliente int primary key, 
   email varchar(100) not null, 
   password varchar(100) not null,
   name varchar(100) not null,
   nif int not null,
   cod_codigo_postal int not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   foreign key (cod_codigo_postal) references codigos_postais(cod_codigo_postal)
);

-- SEQUENCES
create sequence if not exists seq_users start with 1 increment by 1 maxvalue 99999999;
create sequence if not exists seq_codigos_postais start with 1 increment by 1 maxvalue 99999999;
create sequence if not exists seq_clients start with 1 increment by 1 maxvalue 99999999;

-- CONSTRAINTS
alter table users add constraint login_unique unique(login);
alter table clients add constraint email_unique unique(email);