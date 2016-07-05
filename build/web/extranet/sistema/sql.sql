create database empresa1;
use empresa1;

create table sobre(
id_sobre      int primary key auto_increment,
titulo_sobre        varchar(100),
descricao_sobre     text,
data_cadastro date,
status        enum('on','off')
);

create table servico(
id_servico            int primary key auto_increment,
titulo       varchar(100),
descricao     text,
data_cadastro         date,
status                enum('on','off')
);

create table cargo(
id_cargo            int primary key auto_increment,
titulo              varchar(100) unique,
descricao           text,
data_cadastro       date,
status              enum('on','off')
);
create table funcionario(
id_funcionario      int primary key auto_increment,
nome                varchar(100),
sobre_nome          varchar(100),
email               varchar(100),
tel                 varchar(15),
data_cadastro       date,
status              enum('on','off'),
id_cargo_fk         int,
foreign key (id_cargo_fk) references cargo(id_cargo)
);


create table trabalho(
id_trabalho            int primary key auto_increment,
titulo_trabalho        varchar(100),
descricao_trabalho     text,
data_cadastro         date,
status                enum('on','off')
);