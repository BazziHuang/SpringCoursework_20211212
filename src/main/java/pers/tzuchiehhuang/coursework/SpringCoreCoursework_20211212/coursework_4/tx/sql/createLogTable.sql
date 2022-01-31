drop table if exists order_log;
create table if not exists order_log(
	lid integer not null auto_increment,
	wid integer not null, --foreign key
	wname varchar(20) not null,
	bid integer not null, --foreign key
	bname varchar(20) not null,
	quantity integer not null,
	total integer not null,
	ct timestamp default current_timestamp,
	success boolean not null,
	primary key (lid),
	foreign key (wid) references wallet(wid),
	foreign key (bid) references book(bid)
);
	