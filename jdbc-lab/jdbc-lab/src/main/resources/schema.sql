/*this script will be executed when the server starts up*/
create table course(
	id bigint not null,
	name varchar(255) not null,
	author varchar(255) not null,
	primary key (id)
);