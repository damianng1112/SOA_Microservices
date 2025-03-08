create table if not exists student(
	id bigint not null,
	name varchar(255) not null,
	birthDate Date not null,
	course_id bigint not null,
	primary key (id)
);