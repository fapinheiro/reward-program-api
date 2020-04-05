-- TABLES
create table if not exists users ( 
   user_id int primary key,
   login varchar(100) not null, 
   password varchar(100) not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   active char(1) not null,
);

create table if not exists parameters ( 
   param_id int primary key, 
   indication_expiration int not null,
   score_expiration int not null, 
   request_expiration int not null, 
   creation_at timestamp not null,
   updated_at timestamp null
);

create table if not exists districts (
    district_id int primary key, 
    district_code varchar(10) not null,
    description varchar(100) not null,
    creation_at timestamp not null,
    updated_at timestamp null,
);

create table if not exists counties (
    county_id int primary key, 
    county_code int not null,
    district_id int not null,
    description varchar(100) not null,
    creation_at timestamp not null,
    updated_at timestamp null,
    constraint fk_district_counties foreign key (district_id) references districts(district_id)
);

create table if not exists postal_codes (
    postal_code_id int primary key,
    county_id int not null,
    postal_code varchar(10) not null,
    locale varchar(100) not null,
    creation_at timestamp not null,
    updated_at timestamp null,
    constraint fk_counties_postal_codes foreign key (county_id) references counties(county_id)
);


create table if not exists address ( 
   address_id int primary key, 
   postal_code_id int not null,
   email varchar(100) not null, 
   locale_number varchar(10) not null,
   additional_info varchar(100) not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   constraint fk_postal_codes_address foreign key (postal_code_id) references postal_codes(postal_code_id)
);

create table if not exists clients ( 
   client_id int primary key, 
   address_id int not null,
   email varchar(100) not null, 
   password varchar(100) not null,
   name varchar(100) not null,
   birth_date timestamp not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   active char(1),
   constraint fk_address_clients foreign key (address_id) references address(address_id)
);

create table if not exists indications ( 
   indication_id int primary key, 
   client_id int not null,
   email varchar(100) not null, 
   name varchar(100) not null,
   phone varchar(20) not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   constraint fk_clients_indications foreign key (client_id) references clients(client_id)
);

-- create table if not exists scores ( 
--    cod_score int primary key, 
--    good_type int not null,
--    credit_min int not null, 
--    credit_max int not null, 
--    inst_min int not null, 
--    inst_max int not null, 
--    score int not null,
--    creation_at timestamp not null,
--    updated_at timestamp null
-- );

-- SEQUENCES
create sequence if not exists seq_users start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_parameters start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_clients start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_indications start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_addresses start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_requests start with 1 increment by 50 maxvalue 99999999;

-- CONSTRAINTS
alter table users add constraint users_login_unique unique(login);
alter table clients add constraint clients_email_unique unique(email);
alter table counties add constraint counties_district_unique unique(county_id, district_id);
alter table indications add constraint indications_email_unique unique(email);