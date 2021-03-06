--create database if not exists `bookstore`;
--use `bookstore`;
use `finweb`;
drop table if exists `book`;
create table if not exists `book`(
	bid integer not null auto_increment,
	bname varchar(20) not null,
	price integer default 0,
	ct timestamp default current_timestamp,
	primary key (bid)
);

drop table if exists `stock`;
create table if not exists `stock`(
	sid integer not null auto_increment,
	bid integer not null,
	amount integer default 0,
	primary key (sid),
	foreign key (bid) references `book`(bid)
);

drop table if exists `wallet`;
create table if not exists `wallet`(
	wid integer not null auto_increment,
	wname varchar(20) not null,
	money integer default 0,
	primary key (wid)
);

	