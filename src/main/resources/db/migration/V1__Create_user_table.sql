create table user
(
	id bigint auto_increment not null,
	account_id varchar(100),
	name varchar(50),
	token char(36),
	gmt_create bigint,
	gmt_modified bigint,
	bio varchar(256),
	avatar_url varchar(100),
	constraint user_pk
	primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

