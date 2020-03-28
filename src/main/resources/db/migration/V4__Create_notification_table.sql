create table notification
(
	id bigint auto_increment,
	notifier bigint not null comment '通知的人',
	receiver bigint not null comment '被通知的人',
	outerid bigint not null comment '评论或回复',
	type int comment '区分评论或回复',
	gmt_create bigint not null comment '创建时间',
	status int default 0 comment '是否已读',
	notifier_name varchar(100),
	outer_title varchar(256) not null ,
	constraint notification_pk
		primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;