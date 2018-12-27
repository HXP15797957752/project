/*
Navicat MySQL Data Transfer

Source Server         : jdbc
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : votedb

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-12-27 17:15:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_option`
-- ----------------------------
DROP TABLE IF EXISTS `t_option`;
CREATE TABLE `t_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `idx` int(11) DEFAULT NULL,
  `subjectId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `subjectId` (`subjectId`),
  CONSTRAINT `t_option_ibfk_1` FOREIGN KEY (`subjectId`) REFERENCES `t_subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_option
-- ----------------------------
INSERT INTO `t_option` VALUES ('1', '喜欢', '1', '1');
INSERT INTO `t_option` VALUES ('2', '不喜欢', '2', '1');
INSERT INTO `t_option` VALUES ('3', '篮球', '1', '2');
INSERT INTO `t_option` VALUES ('4', '网球', '2', '2');
INSERT INTO `t_option` VALUES ('5', '乒乓球', '3', '2');

-- ----------------------------
-- Table structure for `t_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subjectId` bigint(20) NOT NULL,
  `optionId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `subjectId` (`subjectId`),
  KEY `optionId` (`optionId`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_record_ibfk_1` FOREIGN KEY (`subjectId`) REFERENCES `t_subject` (`id`),
  CONSTRAINT `t_record_ibfk_2` FOREIGN KEY (`optionId`) REFERENCES `t_option` (`id`),
  CONSTRAINT `t_record_ibfk_3` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES ('1', '1', '1', '2');
INSERT INTO `t_record` VALUES ('2', '2', '3', '2');
INSERT INTO `t_record` VALUES ('3', '2', '4', '2');
INSERT INTO `t_record` VALUES ('4', '2', '5', '2');

-- ----------------------------
-- Table structure for `t_subject`
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `starttime` bigint(20) DEFAULT NULL,
  `endtime` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_subject_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_subject
-- ----------------------------
INSERT INTO `t_subject` VALUES ('1', '你喜欢下雪吗？', '1', '1545807987518', '1545894387518', '2');
INSERT INTO `t_subject` VALUES ('2', '你喜欢什么？', '2', '1545808134961', '1545894534961', '2');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `online` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('2', 'hxp', 'e10adc3949ba59abbe56e057f20f883e', null, '1');
