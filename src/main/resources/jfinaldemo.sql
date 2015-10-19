/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50534
Source Host           : localhost:3306
Source Database       : jfinaldemo

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2015-10-12 14:38:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_res
-- ----------------------------
DROP TABLE IF EXISTS `system_res`;
CREATE TABLE `system_res` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_res
-- ----------------------------
INSERT INTO `system_res` VALUES ('1', 'hello', null, 'admin:hello');
INSERT INTO `system_res` VALUES ('2', 'test', null, 'admin:test');
INSERT INTO `system_res` VALUES ('3', 'guest', null, 'guest:index');
INSERT INTO `system_res` VALUES ('4', 'super', null, '*');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1', 'admin', '管理员', '1');
INSERT INTO `system_role` VALUES ('2', 'guest', '游客', '1');

-- ----------------------------
-- Table structure for system_role_res
-- ----------------------------
DROP TABLE IF EXISTS `system_role_res`;
CREATE TABLE `system_role_res` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `res_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role_res
-- ----------------------------
INSERT INTO `system_role_res` VALUES ('1', '1', '1');
INSERT INTO `system_role_res` VALUES ('2', '1', '2');
INSERT INTO `system_role_res` VALUES ('3', '1', '3');
INSERT INTO `system_role_res` VALUES ('4', '2', '3');
INSERT INTO `system_role_res` VALUES ('5', '2', '1');
INSERT INTO `system_role_res` VALUES ('6', '1', '4');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `is_locked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', 'tanlsh', 'tanlsh', '', '1', '0');
INSERT INTO `system_user` VALUES ('2', 'zhangsan', 'zhangsan', null, '1', '0');

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
INSERT INTO `system_user_role` VALUES ('1', '1', '1');
INSERT INTO `system_user_role` VALUES ('2', '2', '2');
