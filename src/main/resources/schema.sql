-- create sequence
-- create sequence if not exists seq_subscriber start with 1 increment by 1 maxvalue 99999999;
create sequence if not exists seq_user start with 1 increment by 1 maxvalue 99999999;

-- create table
-- create table if not exists subscriber ( 
--    id int auto_increment, 
--    msisdn varchar(164) not null, 
--    customer_id_owner int not null, 
--    customer_id_user int not null,
--    service_type enum('MOBILE_PREPAID', 'MOBILE_POSTPAID') not null,
--    service_start_date bigint not null,
--    creation_at timestamp not null
-- );

create table if not exists users ( 
   id int auto_increment, 
   login varchar(300) not null, 
   password varchar(300) not null, 
   creation_at timestamp not null
);

-- adding constraint for msisdn
-- alter table subscriber add constraint msisdn_unique unique(msisdn);
alter table users add constraint username_unique unique(login);