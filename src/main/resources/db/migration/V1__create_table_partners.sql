CREATE TABLE partners(
    id_partner bigint primary key auto_increment,
    tax_number varchar(15) not null unique,
    name varchar(150) not null,
    email varchar(100) not null unique,
    client_key varchar(100) not null unique
);
