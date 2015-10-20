/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50534
Source Host           : localhost:3306
Source Database       : jfinaldemo

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2015-10-20 14:11:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_url` varchar(255) DEFAULT NULL,
  `menu_sn` char(2) DEFAULT NULL,
  `menu_type` char(6) DEFAULT NULL,
  `menu_parent_id` int(11) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `creater` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('7', '菜单管理', '/ucenter/menu', '2', '1', '18', '2014-09-11 00:00:00', '1');
INSERT INTO `system_menu` VALUES ('8', '用户管理', '/ucenter/user', '1', '1', '18', '2014-09-11 00:00:00', '1');
INSERT INTO `system_menu` VALUES ('18', 'auth', '--', '1', '1', '0', '2014-12-10 15:26:52', '2');
INSERT INTO `system_menu` VALUES ('23', '权限管理', '/ucenter/role', '3', '1', '18', '2014-12-11 20:32:55', '2');

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
-- Table structure for system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu`;
CREATE TABLE `system_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role_menu
-- ----------------------------
INSERT INTO `system_role_menu` VALUES ('1', '1', '1');
INSERT INTO `system_role_menu` VALUES ('2', '1', '2');
INSERT INTO `system_role_menu` VALUES ('3', '1', '3');
INSERT INTO `system_role_menu` VALUES ('4', '2', '3');
INSERT INTO `system_role_menu` VALUES ('5', '2', '1');
INSERT INTO `system_role_menu` VALUES ('6', '1', '4');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `user_type` varchar(10) DEFAULT NULL,
  `is_locked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', 'tanlsh', 'tanlsh', null, '1', '0');
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

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('2', '小明111');
INSERT INTO `test` VALUES ('7', '小明111');
