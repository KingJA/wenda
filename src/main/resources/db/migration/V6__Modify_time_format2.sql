ALTER TABLE question
    MODIFY COLUMN `gmt_create` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
    MODIFY COLUMN `gmt_modified` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0);

ALTER TABLE `user`
    MODIFY COLUMN `gmt_create` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ,
    MODIFY COLUMN `gmt_modified` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0);