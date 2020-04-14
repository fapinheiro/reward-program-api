-- users
-- password: 123
insert into users ( user_id, login, password, creation_at, active) values (seq_users.nextval,'admin@gmail.com','$2a$10$wB0XIhjN9IvPh9cNWNHSHeGv67rbLizId7Lz4lcSjzIJoOQgjnC4a',current_timestamp, 1);

-- parameters
insert into parameters (param_id,indication_expiration,score_expiration,request_expiration,creation_at) values (seq_parameters.nextval, 30, 60, 90, current_timestamp);

-- districts
insert into districts (district_id,district_code, description, creation_at) values (1,11,'Lisboa             ',current_timestamp);
insert into districts (district_id,district_code, description, creation_at) values (2,15,'Setúbal            ',current_timestamp);

-- counties
insert into counties (county_id, county_code, district_id, description, creation_at) values (1,6,1,'Lisboa                                  ',current_timestamp);
insert into counties (county_id, county_code, district_id, description, creation_at) values (2,3,2 ,'Almada                                  ',current_timestamp);

-- postal codes
insert into postal_codes (postal_code_id, county_id, locale, postal_code, creation_at) values (1,1,'Rua Câmara de Almada','2825832', current_timestamp);
insert into postal_codes (postal_code_id, county_id, locale, postal_code, creation_at) values (2,2,'Avenida Bulhão Pato','2825846', current_timestamp);

-- roles
insert into roles (role_id, description) values (1, 'ROLE_ADMIN');
insert into roles (role_id, description) values (2, 'ROLE_USER');
insert into roles (role_id, description) values (3, 'ROLE_CLIENT');
insert into roles (role_id, description) values (4, 'ROLE_PARTNER');

-- admin_roles
insert into users_roles (user_id, role_id) values (1, 1);