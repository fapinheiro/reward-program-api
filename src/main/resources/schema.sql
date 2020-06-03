-- TABLES
create table if not exists users ( 
   user_id int primary key,
   login varchar(100) not null, 
   password varchar(100) not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   active char(1) not null
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


create table if not exists addresses ( 
   address_id int primary key, 
   postal_code_id int not null,
   locale_info varchar(100) not null,
   additional_info varchar(100) null,
   creation_at timestamp not null,
   updated_at timestamp null,
   constraint fk_postal_codes_address foreign key (postal_code_id) references postal_codes(postal_code_id)
);

create table if not exists accounts ( 
   account_id int primary key, 
   score_balance int not null,
   creation_at timestamp not null,
   updated_at timestamp null,
);

create table if not exists clients ( 
   client_id int primary key, 
   account_id int not null,
   address_id int not null,
   email varchar(100) not null, 
   password varchar(100) not null,
   name varchar(100) not null,
   birth_date timestamp not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   active char(1),
   constraint fk_clients_address foreign key (address_id) references addresses(address_id),
   constraint fk_clients_accounts foreign key (account_id) references accounts(account_id)
);

create table if not exists requests ( 
   request_id int primary key, 
   client_id int not null,
   status integer not null, 
   cupom varchar(200) not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   valid_until timestamp not null,
   constraint fk_client_request foreign key (client_id) references clients(client_id)
);

create table if not exists categories ( 
   category_id int primary key, 
   description varchar(100) not null,
   creation_at timestamp not null,
   updated_at timestamp null,
);

create table if not exists partners ( 
   partner_id int primary key, 
   account_id int not null,
   name varchar(45) not null,
   active char(1) not null,
   primary_color varchar(45) null,
   secondary_color varchar(45) null,
   logo_url varchar(255) not null,
   email varchar(255) not null,
   password varchar(255) not null,
   created_at timestamp not null,
   updated_at timestamp null,
   constraint fk_partners_accounts foreign key (account_id) references accounts(account_id)
);

create table if not exists items ( 
   item_id int primary key, 
   category_id int not null,
   partner_id int not null,
   name varchar(100) not null, 
   description varchar(200) not null,
   url varchar(255) not null,
   required_score int not null,
   discount int not null,
   win_score int not null,
   valid_until timestamp not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   constraint fk_items_categories foreign key (category_id) references categories(category_id),
   constraint fk_items_partners foreign key (partner_id) references partners(partner_id)
);

create table if not exists virtual_items ( 
   item_id int primary key, 
   url_site varchar(255) not null,
   constraint fk_virtual_items foreign key (item_id) references items(item_id)
);

create table if not exists physical_items ( 
   item_id int primary key, 
   address_id int not null,
   physical_name varchar(255) not null,
   constraint fk_physical_address foreign key (address_id) references addresses(address_id),
   constraint fk_physical_items foreign key (item_id) references items(item_id)
);

create table if not exists request_items ( 
   item_id int not null, 
   request_id int not null,
   required_score int not null,
   discount int not null,
   win_score int not null,
   constraint pk_request_items primary key (item_id, request_id),
   constraint fk_request_items_item foreign key (item_id) references items(item_id),
   constraint fk_request_items_request foreign key (request_id) references requests(request_id)
);

create table if not exists indications ( 
   indication_id int primary key, 
   client_id int not null,
   status integer not null,
   email varchar(100) not null, 
   name varchar(100) not null,
   phone varchar(20) not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   constraint fk_clients_indications foreign key (client_id) references clients(client_id)
);

create table if not exists identifications ( 
   identification_id int primary key, 
   client_id int not null,
   ident_type integer not null,
   ident_code varchar(100) not null, 
   emission_date timestamp not null,
   valid_date timestamp not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   constraint fk_clients_identifications foreign key (client_id) references clients(client_id)
);

create table if not exists contacts ( 
   contact_id int primary key, 
   client_id int not null,
   contact_type integer not null,
   contact varchar(200) not null, 
   validated_at timestamp null,
   creation_at timestamp not null,
   updated_at timestamp null,
   constraint fk_clients_contacts foreign key (client_id) references clients(client_id)
);

create table if not exists roles ( 
   role_id int primary key, 
   description varchar(200) not null
);

create table if not exists clients_roles ( 
   client_id int not null,
   role_id int not null, 
   constraint pk_clients_roles primary key (client_id, role_id),
   constraint fk_clients_roles_client foreign key (client_id) references clients(client_id),
   constraint fk_clients_roles_role foreign key (role_id) references roles(role_id)
);

create table if not exists transactions ( 
   transaction_id int primary key, 
   account_id int not null, 
   status int not null,
   amount int not null,
   transaction_type int not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   constraint fk_accounts_transactions foreign key (account_id) references accounts(account_id)
);

create table if not exists partners_roles ( 
   partner_id int not null,
   role_id int not null, 
   constraint pk_partners_roles primary key (partner_id, role_id),
   constraint fk_partners_roles_partner foreign key (partner_id) references partners(partner_id),
   constraint fk_partners_roles_role foreign key (role_id) references roles(role_id)
);

create table if not exists users_roles ( 
   user_id int not null,
   role_id int not null, 
   constraint pk_users_roles primary key (user_id, role_id),
   constraint fk_users_roles_user foreign key (user_id) references users(user_id),
   constraint fk_users_roles_role foreign key (role_id) references roles(role_id)
);

create table if not exists clients ( 
   client_id int primary key, 
   account_id int not null,
   address_id int not null,
   email varchar(100) not null, 
   password varchar(100) not null,
   name varchar(100) not null,
   birth_date timestamp not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   active char(1),
   constraint fk_clients_address foreign key (address_id) references address(address_id),
   constraint fk_clients_accounts foreign key (account_id) references accounts(account_id)
);

-- SEQUENCES
create sequence if not exists seq_users start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_parameters start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_clients start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_indications start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_addresses start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_requests start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_accounts start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_categories start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_contacts start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_identifications start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_items start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_partners start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_transactions start with 1 increment by 50 maxvalue 99999999;



-- CONSTRAINTS
alter table users add constraint users_login_unique unique(login);
alter table clients add constraint clients_email_unique unique(email);
alter table counties add constraint counties_district_unique unique(county_id, district_id);
alter table indications add constraint indications_email_unique unique(email);