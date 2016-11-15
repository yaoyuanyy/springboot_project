CREATE TABLE `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL DEFAULT '',
  `user_id` bigint(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
)

CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `mobile` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
)
