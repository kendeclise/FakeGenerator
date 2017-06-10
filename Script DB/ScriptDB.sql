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


create table apellidos
(
	id int identity(1,1) not null primary key,
	apellido varchar(255) not null unique
)
go

create table nombres_hombre
(
	id int identity(1,1) not null primary key,
	nombre varchar(255) not null unique
)
go

create table nombres_mujer
(
	id int identity(1,1) not null primary key,
	nombre varchar(255) not null unique
)
go

create table direcciones
(
	id int identity(1,1) not null primary key,
	descr varchar(255) not null 
)
go

create table telefonos
(
	id	int identity(1,1) not null primary key,
	descr varchar(255) not null
)
go


