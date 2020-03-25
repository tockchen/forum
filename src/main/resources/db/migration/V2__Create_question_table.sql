create table question
(
	id bigint auto_increment not null comment '序号',
	title varchar(50) comment '名称',
	description text comment '问题',
	gmt_create bigint comment '创建时间',
	gmt_modified bigint comment '修改时间',
	creator bigint comment '创建人id',
	comment_count int default 0 comment '评论数',
	view_count int default 0 comment '阅读数',
	like_count int default 0 comment '点赞数',
	tag varchar(256) comment '标签',
	constraint question_pk
		primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;