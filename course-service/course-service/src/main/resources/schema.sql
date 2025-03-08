create table if not exists course(
	course_id bigint not null,
	`name` varchar(255) not null,
	code varchar(255) not null,
	primary key (course_id)
);