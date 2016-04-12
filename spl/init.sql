drop table if exists branches;
create table branches(
	id		integer			auto_increment primary key,
	name	varchar(20)		not null
);

drop table if exists departments;
create table departments(
	id		integer			auto_increment primary key,
	name	varchar(20)		not null
);

drop table if exists users;
create table users(
	id				integer			auto_increment primary key,
	login_id		varchar(20)		not null,
	password		varchar(255)	not null,
	name			varchar(10)		not null,
	branch_id		integer			not null,
	department_id	integer			not null,
	is_locked		integer			default 0
);

drop table if exists posts;
create table posts(
	id			integer			auto_increment primary key,
	title		varchar(50)		not null,
	text		text			not null,
	category	varchar(10)		not null,
	user_id		integer			not null,
	created_date timestamp		,
	updated_date timestamp		
);

drop table if exists comments;
create table comments(
	id			integer			auto_increment primary key,
	text		text			not null,
	user_id		integer			not null,
	post_id		integer			,
	created_date timestamp		,
	updated_date timestamp		
);

insert into branches (name) values ('�{��');
insert into branches (name) values ('�����x�X');
insert into branches (name) values ('����x�X');
insert into branches (name) values ('�k�C���x�X');

insert into departments (name) values ('�Ǘ���');
insert into departments (name) values ('�����l���S����');
insert into departments (name) values ('���Ǘ��S����');
insert into departments (name) values ('�x�X��');
insert into departments (name) values ('�Ј�');

insert into users (login_id,password,name,branch_id,department_id) 
			values ('admin','admin','�f���Ǘ���',1,1);
