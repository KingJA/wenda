CREATE TABLE comment  (
 `id` int(0) NOT NULL AUTO_INCREMENT,
 `parent_id` int(0)  NULL COMMENT '父评论id',
 `type` int(0) NOT NULL COMMENT '评论等级',
 `user_id` int(0) NOT NULL COMMENT '用户id',
 `like_count` int(0)  NULL DEFAULT 0 COMMENT '点赞数',
 `content` varchar(1024) NOT NULL COMMENT '评论内容',
 `create_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP,
 `update_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
 PRIMARY KEY (`id`)
);