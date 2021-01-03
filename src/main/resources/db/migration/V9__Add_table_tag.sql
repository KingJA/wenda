CREATE TABLE tag  (
     `id` int(0) NOT NULL AUTO_INCREMENT,
     `name` varchar(255) NOT NULL COMMENT '标签名',
     `fid` int(0) COMMENT '父级id',
     `type` int(0) NOT NULL COMMENT '标签等级 1：一级， 2：二级 ',
     `create_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP,
     `update_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
     PRIMARY KEY (`id`)
);