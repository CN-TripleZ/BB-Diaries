drop database if exists bb_diaries;
create database bb_diaries;
use bb_diaries; 
create table t_user (
	id int primary key auto_increment,
	password varchar(64),
	name varchar(64),
	email varchar(256)
);

create table t_photo(
  id int primary key auto_increment,
  uploadTime TIMESTAMP,
  userId int,
  description varchar(2000),
  path varchar(256)
);

insert into t_user(id,name,password,email) values(-1,'admin','abc1234','admin@admin.com');