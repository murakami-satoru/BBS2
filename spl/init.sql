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
	created_date timestamp		,
	updated_date timestamp
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

insert into branches (name) values ('本社');
insert into branches (name) values ('東京支店');
insert into branches (name) values ('沖縄支店');
insert into branches (name) values ('北海道支店');

insert into departments (name) values ('掲示板管理');
insert into departments (name) values ('情報管理');
insert into departments (name) values ('総務人事');
insert into departments (name) values ('支店長');
insert into departments (name) values ('社員');

insert into users (login_id,password,name,branch_id,department_id)
			values ('admin','admin','掲示板管理者',1,1);
insert into users (login_id,password,name,branch_id,department_id)
			values ('soumu','soumu','総務人事',1,3);

commit;