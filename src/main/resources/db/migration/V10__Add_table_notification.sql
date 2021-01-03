CREATE TABLE notification  (
     `id` int(0) NOT NULL AUTO_INCREMENT,
     `receiver_id` int(0) NOT NULL COMMENT '发送者id',
     `sender_id` int(0) NOT NULL COMMENT '接收者id',
     `target_id` int(0) NOT NULL COMMENT '载体id',
     `outer_id` int(0) NOT NULL COMMENT '外部id',
     `sender_name` varchar(255) NOT NULL  COMMENT '发送者名称',
     `target_title` varchar(255) NOT NULL  COMMENT '载体标题',
     `type` int(0) NOT NULL COMMENT '通知类型 1.问题评价 2.评价评价',
     `create_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP,
     `update_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
     `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '已读状态 0.未读 1.已读',
     PRIMARY KEY (`id`)
);