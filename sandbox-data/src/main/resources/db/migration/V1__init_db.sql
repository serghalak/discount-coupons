create table category (
    id integer not null auto_increment,
    description varchar(255),
    name varchar(255),
    primary key (id)) engine=InnoDB;

create table city (
    id integer not null auto_increment,
    name varchar(255),
    country_id integer,
    primary key (id)) engine=InnoDB;

create table country (
    id integer not null auto_increment,
    name varchar(255),
    primary key (id)) engine=InnoDB;


create table event (
    id integer not null auto_increment,
    count integer,
    date_begin date,
    date_end date,
    description varchar(255),
    email varchar(255),
    limitation integer,
    name varchar(255),
    phone_number varchar(255),
    price decimal(19,2),
    status_id integer, primary key (id)) engine=InnoDB;

create table event_location (
    event_id integer not null,
    location_id integer not null,
    primary key (event_id, location_id)) engine=InnoDB;

create table event_product (
    event_id integer not null,
    product_id integer not null,
    primary key (event_id, product_id)) engine=InnoDB;

create table feedback (
    event_id integer not null,
    user_id integer not null,
    primary key (event_id, user_id)) engine=InnoDB;

create table location (
    id integer not null auto_increment,
    longitude decimal(19,2),
    lotitude decimal(19,2),
    number varchar(255),
    street varchar(255),
    city_id integer,
    primary key (id)) engine=InnoDB;

create table product (
    id integer not null auto_increment,
    description varchar(255),
    link varchar(255),
    name varchar(255),
    category_id integer,
    vendor_id integer,
    primary key (id)) engine=InnoDB;

create table role (
    id integer not null auto_increment,
    name varchar(255),
    primary key (id)) engine=InnoDB;

create table saved_event (
    event_id integer not null,
    user_id integer not null,
    primary key (event_id, user_id)) engine=InnoDB;

create table status (
    id integer not null auto_increment,
    name varchar(255),
    primary key (id)) engine=InnoDB;

create table user (
    id integer not null auto_increment,
    email varchar(255),
    first_name varchar(255),
    enable bit,
    last_name varchar(255),
    password varchar(255),
    username varchar(255),
    location_id integer,
    role_id integer,
    primary key (id)) engine=InnoDB;

create table user_order (
    event_id integer not null,
    user_id integer not null,
    primary key (event_id, user_id)) engine=InnoDB;

create table vendor (
    id integer not null auto_increment,
    description varchar(255),
    name varchar(255),
    phone_number varchar(255),
    primary key (id)) engine=InnoDB;

alter table city add constraint FK_city_country_id foreign key (country_id) references country (id);

alter table event add constraint FK_event_status_id foreign key (status_id) references status (id);

alter table event_location add constraint FK_event_location_id foreign key (location_id) references location (id);

alter table event_location add constraint FK_event_event_id foreign key (event_id) references event (id);

alter table event_product add constraint FK_event_product_id foreign key (product_id) references product (id);

alter table event_product add constraint FK_event_product_event_id foreign key (event_id) references event (id);

alter table feedback add constraint FK_feedback_user_id foreign key (user_id) references user (id);

alter table feedback add constraint FK_feedback_event_id foreign key (event_id) references event (id);

alter table location add constraint FK_lacation_city_id foreign key (city_id) references city (id);

alter table product add constraint FK_product_category_id foreign key (category_id) references category (id);

alter table product add constraint FK_product_vendor_id foreign key (vendor_id) references vendor (id);

alter table saved_event add constraint FK_saved_event_user_id foreign key (user_id) references user (id);

alter table saved_event add constraint FK_saved_event_event_id foreign key (event_id) references event (id);

alter table user add constraint FK_user_location_id foreign key (location_id) references location (id);

alter table user add constraint FK_user_role_id foreign key (role_id) references role (id);

alter table user_order add constraint FK_user_order_user_id foreign key (user_id) references user (id);

alter table user_order add constraint FK_user_order_event_id foreign key (event_id) references event (id);