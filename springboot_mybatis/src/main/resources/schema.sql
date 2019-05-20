drop table if exists user;

CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `score` int(4) NOT NULL DEFAULT '0' COMMENT '分数',
  `studentId` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_studentId` (`studentId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `user`(`id`, `name`, `mobile`, `score`, `studentId`) VALUES (1, 'y', '13000000000', 0, 1);
INSERT INTO `user`(`id`, `name`, `mobile`, `score`, `studentId`) VALUES (2, 'l', '13800008888', 5, 2);
INSERT INTO `user`(`id`, `name`, `mobile`, `score`, `studentId`) VALUES (3, 's', '111', 0, 3);
INSERT INTO `user`(`id`, `name`, `mobile`, `score`, `studentId`) VALUES (4, 's', '111', 0, 33);
INSERT INTO `user`(`id`, `name`, `mobile`, `score`, `studentId`) VALUES (5, 's', '112', 0, 4);
INSERT INTO `user`(`id`, `name`, `mobile`, `score`, `studentId`) VALUES (7, 's', '113', 0, 5);
INSERT INTO `user`(`id`, `name`, `mobile`, `score`, `studentId`) VALUES (8, 's', '113', 0, 60);
INSERT INTO `user`(`id`, `name`, `mobile`, `score`, `studentId`) VALUES (9, 's', '114', 0, 7);
INSERT INTO `user`(`id`, `name`, `mobile`, `score`, `studentId`) VALUES (10, 's', '115', 0, 8);
