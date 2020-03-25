create table comment
(
	id bigint auto_increment ,
	parent_id bigint not null comment '父类id',
	type int not null comment '父类类型',
	commentator bigint default 0 comment '评论人id',
	gmt_create bigint not null comment '创建时间',
	gmt_modified bigint not null comment '修改时间',
	like_count bigint default 0 comment '点赞数',
	content varchar(1024) comment '评论内容',
	comment_count int default  0 comment '回复数',
	constraint comment_pk
		primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
