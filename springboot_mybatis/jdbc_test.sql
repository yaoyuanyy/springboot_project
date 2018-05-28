/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : jdbc_test

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 28/05/2018 19:26:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `teacherId` bigint(20) unsigned NOT NULL DEFAULT '0',
  `schoolName` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (1, 11, 'a');
INSERT INTO `student` VALUES (2, 22, 'ccc');
INSERT INTO `student` VALUES (3, 33, 'c');
INSERT INTO `student` VALUES (4, 44, 'd');
INSERT INTO `student` VALUES (5, 55, 'e');
INSERT INTO `student` VALUES (6, 66, 'f');
INSERT INTO `student` VALUES (7, 77, 'g');
INSERT INTO `student` VALUES (8, 11, 'h');
INSERT INTO `student` VALUES (9, 11, 'i');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `score` int(4) NOT NULL DEFAULT '0' COMMENT '分数',
  `studentId` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_studentId` (`studentId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'y', '13000000000', 0, 1);
INSERT INTO `user` VALUES (2, 'l', '13800008888', 5, 2);
INSERT INTO `user` VALUES (3, 's', '111', 0, 3);
INSERT INTO `user` VALUES (4, 's', '111', 0, 3);
INSERT INTO `user` VALUES (5, 's', '112', 0, 4);
INSERT INTO `user` VALUES (7, 's', '113', 0, 5);
INSERT INTO `user` VALUES (8, 's', '113', 0, 60);
INSERT INTO `user` VALUES (9, 's', '114', 0, 7);
INSERT INTO `user` VALUES (10, 's', '115', 0, 8);
INSERT INTO `user` VALUES (11, 'skyler', '', 0, 0);
INSERT INTO `user` VALUES (12, '<script>alert(11)</script>', '', 0, 0);
INSERT INTO `user` VALUES (13, '<script>alert(11)</script>', '', 0, 0);
INSERT INTO `user` VALUES (14, '<script>alert(11)</script>', '', 0, 0);
INSERT INTO `user` VALUES (15, '<script>alert(11)</script>', '', 0, 0);
INSERT INTO `user` VALUES (16, '<script>alert(11)</script>', '', 0, 0);
INSERT INTO `user` VALUES (17, '<script>alert(11)</script>', '', 0, 0);
INSERT INTO `user` VALUES (18, '<script>alert(11)</script>', '', 0, 0);
INSERT INTO `user` VALUES (19, '<script>alert(11)</script>', '', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `useable_balance` decimal(16,8) NOT NULL DEFAULT '0.00000000',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_account_transfer
-- ----------------------------
DROP TABLE IF EXISTS `user_account_transfer`;
CREATE TABLE `user_account_transfer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `amount` decimal(16,8) NOT NULL DEFAULT '0.00000000',
  `transfer_uuid` varchar(200) NOT NULL DEFAULT '',
  `retry_times` tinyint(4) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_uuid` (`transfer_uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
