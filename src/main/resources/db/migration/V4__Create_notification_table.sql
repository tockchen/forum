create table notification
(
	id bigint auto_increment,
	notifier bigint not null,
	receiver bigint not null,
	outerId bigint not null,
	type int,
	gmt_create bigint not null,
	status int default 0,
	notifier_name varchar(100) not null,
	outer_title varchar(256) not null,
	constraint notification_pk
		primary key (id)
);

comment on column notification.notifier is '通知的人';

comment on column notification.receiver is '被通知的人';

comment on column notification.outerId is '评论或回复';

comment on column notification.type is '区分评论或回复';

comment on column notification.gmt_create is '创建时间';

comment on column notification.status is '是否已读';

