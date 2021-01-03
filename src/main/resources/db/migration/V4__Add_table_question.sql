CREATE TABLE question  (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `title` varchar(255) NULL,
 `description` text NULL,
 `creator` int(11) NULL,
 `comment_count` int(11) NULL DEFAULT 0,
 `view_count` int(11) NULL DEFAULT 0,
 `like_count` int(11) NULL DEFAULT 0,
 `tag` varchar(255) NULL,
 `gmt_create` bigint(11) NULL,
 `gmt_modified` bigint(11) NULL,
 PRIMARY KEY (`id`)
);