-- Base de datos - Generador de datos falsos

if db_id('fakeGeneratorDB') is not null
	begin
		use master
		drop database fakeGeneratorDB
	end
go

create database fakeGeneratorDB
go

use fakeGeneratorDB
go


create table departamentos
(
	id	int	primary key,
	nombre varchar(255) not null
)
go


create table provincias
(
	id int primary key,
	nombre varchar(255) not null,
	id_depa	int	not null references departamentos
)
go

create table distritos
(
	id int primary key,
	nombre varchar(255) not null,
	id_prov int not null references provincias
)
go