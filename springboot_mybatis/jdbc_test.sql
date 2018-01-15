/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost
 Source Database       : jdbc_test

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 01/15/2018 15:31:16 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `teacherId` bigint(20) unsigned NOT NULL DEFAULT '0',
  `schoolName` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `student`
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES ('1', '11', 'a'), ('2', '22', 'ccc'), ('3', '33', 'c'), ('4', '44', 'd'), ('5', '55', 'e'), ('6', '66', 'f'), ('7', '77', 'g'), ('8', '11', 'h'), ('9', '11', 'i');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `score` int(4) NOT NULL DEFAULT '0' COMMENT '分数',
  `studentId` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_studentId` (`studentId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'y', '13000000000', '0', '1'), ('2', 'l', '13800008888', '5', '2'), ('3', 's', '111', '0', '3'), ('4', 's', '111', '0', '3'), ('5', 's', '112', '0', '4'), ('7', 's', '113', '0', '5'), ('8', 's', '113', '0', '60'), ('9', 's', '114', '0', '7'), ('10', 's', '115', '0', '8');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
