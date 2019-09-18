/*
Navicat MySQL Data Transfer

Source Server         : 我的服务器
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : imooc-demo

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2019-09-09 11:06:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `createdtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '2019-07-26 20:51:07', '$2a$10$DxeACIPFA7.okzzdNjADfOBj1ceucJYqdCwIsPis5FvqtBBfI1.5K', 'admin', null);
INSERT INTO `admin` VALUES ('2', '2019-07-27 21:41:37', '$2a$10$DxeACIPFA7.okzzdNjADfOBj1ceucJYqdCwIsPis5FvqtBBfI1.5K', '13052389239', null);
INSERT INTO `admin` VALUES ('3', '2019-08-07 23:07:54', '$2a$10$DxeACIPFA7.okzzdNjADfOBj1ceucJYqdCwIsPis5FvqtBBfI1.5K', '无影', null);

-- ----------------------------
-- Table structure for `oauth_client_details`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('imooc', null, 'imoocsecret', 'all', 'authorization_code,password,refresh_token', null, null, '3600', '2592000', null, null);
INSERT INTO `oauth_client_details` VALUES ('webApp', null, '$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq', 'all', 'authorization_code,password,refresh_token,client_credentials', null, null, '3600', '2592000', null, null);

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `createdtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `imooc_fkey2j6iw5mn2cdwytqs0143emd` (`parent_id`),
  CONSTRAINT `fks2byvqo0b2enh3rltln5mmvyl` FOREIGN KEY (`parent_id`) REFERENCES `resource` (`id`),
  CONSTRAINT `imooc_fkey2j6iw5mn2cdwytqs0143emd` FOREIGN KEY (`parent_id`) REFERENCES `resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', null, null, null, '根节点', '0', null, null, null);
INSERT INTO `resource` VALUES ('2', null, 'home', null, '首页', '0', 'MENU', '1', null);
INSERT INTO `resource` VALUES ('3', null, 'desktop', null, '平台管理', '0', 'MENU', '1', null);
INSERT INTO `resource` VALUES ('4', null, '', 'roleManage', '角色管理', '0', 'MENU', '3', null);
INSERT INTO `resource` VALUES ('5', null, '', 'adminManage', '管理员管理', '0', 'MENU', '3', null);

-- ----------------------------
-- Table structure for `resource_urls`
-- ----------------------------
DROP TABLE IF EXISTS `resource_urls`;
CREATE TABLE `resource_urls` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource_id` bigint(20) NOT NULL,
  `urls` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `imooc_fkj9xg86j2p4jq3qj58169enymo` (`resource_id`),
  CONSTRAINT `fkg4sbo4qdxrp91dfigfopeb278` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`),
  CONSTRAINT `imooc_fkj9xg86j2p4jq3qj58169enymo` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource_urls
-- ----------------------------
INSERT INTO `resource_urls` VALUES ('1', '4', '/role/**');
INSERT INTO `resource_urls` VALUES ('2', '4', 'roleManage');
INSERT INTO `resource_urls` VALUES ('3', '5', '/admin/**');
INSERT INTO `resource_urls` VALUES ('4', '5', 'adminManage');
INSERT INTO `resource_urls` VALUES ('5', '4', '/user/**');
INSERT INTO `resource_urls` VALUES ('6', '4', '/order/**');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `createdtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', null, '超级管理员', null);

-- ----------------------------
-- Table structure for `roleadmin`
-- ----------------------------
DROP TABLE IF EXISTS `roleadmin`;
CREATE TABLE `roleadmin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `admin_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `createdtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `imooc_fkj4mekxtg56g8rvu13g6dr8cam` (`admin_id`),
  KEY `imooc_fkrxoj9tvi9ywf4hiwx79uaxhbx` (`role_id`),
  CONSTRAINT `fk3bux5qew4gyhbwycamegfdyke` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fk903bl4c1bj3dw8rfvltmua7fo` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`),
  CONSTRAINT `imooc_fkj4mekxtg56g8rvu13g6dr8cam` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`),
  CONSTRAINT `imooc_fkrxoj9tvi9ywf4hiwx79uaxhbx` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleadmin
-- ----------------------------
INSERT INTO `roleadmin` VALUES ('1', null, '1', '1', null);
INSERT INTO `roleadmin` VALUES ('2', null, '2', '1', null);
INSERT INTO `roleadmin` VALUES ('3', null, '3', '1', null);

-- ----------------------------
-- Table structure for `roleresource`
-- ----------------------------
DROP TABLE IF EXISTS `roleresource`;
CREATE TABLE `roleresource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `createdtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `imooc_fkrw1r0fny7ul3jlnlhnpq0r4s5` (`resource_id`),
  KEY `imooc_fkonivxnl9xt881w2t9d2lkd8w9` (`role_id`),
  CONSTRAINT `fka84rthu3qordgyqmb83i9xchd` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fkbd03bw2wygledjtfgcvs671s6` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`),
  CONSTRAINT `imooc_fkonivxnl9xt881w2t9d2lkd8w9` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `imooc_fkrw1r0fny7ul3jlnlhnpq0r4s5` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleresource
-- ----------------------------
INSERT INTO `roleresource` VALUES ('1', '2019-07-27 23:03:26', '2', '1', null);
INSERT INTO `roleresource` VALUES ('2', '2019-07-27 23:03:18', '3', '1', null);
INSERT INTO `roleresource` VALUES ('3', '2019-07-27 23:03:33', '4', '1', null);
INSERT INTO `roleresource` VALUES ('5', '2019-07-27 23:03:45', '5', '1', null);

-- ----------------------------
-- Table structure for `t_UserConnection`
-- ----------------------------
DROP TABLE IF EXISTS `t_UserConnection`;
CREATE TABLE `t_UserConnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL DEFAULT '',
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(512) NOT NULL,
  `secret` varchar(512) DEFAULT NULL,
  `refreshToken` varchar(512) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_UserConnection
-- ----------------------------
INSERT INTO `t_UserConnection` VALUES ('无影', 'weixin', 'od4PTw4pV1I3BMKxlXB7cipJUjvc', '1', '无影', null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJSZcno3GEdDOiaDL0Mvu6t7OOEcuReSUqCzV5spr3ltlXMFEictWGod0FsMmUtkAbDajCCCqHmum7Q/132', '24_lKBb7clQiniFTnHsMtUtAH4k_R_zJ4FlZvcP5V3zFkJqZXbb2fdz3c6V14Odu8E17gfF2ofP3Xgf7C6gdngV5NTb5SYID2eGIoGIJdhX0yI', null, '24_V50AGfQ9f2Oux1WAwgX_B829X7gHZSm1qRRsDdAjotTo-QEoe9Z6d8JfXrSdfW5Ii3fJ4RoB2QfP23t6c_6-G1DufVEMILMQXbAX5JcbVQM', '1565266749924');
