CREATE TABLE users(
    id_user bigint primary key auto_increment,
    login varchar(100) not null unique,
    password varchar(100) not null,
    partner_id bigint not null,
    status varchar(50) not null,

    foreign key(partner_id) references partners(id_partner)
);
