/*
 Navicat Premium Data Transfer

 Source Server         : docker-mysql
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : smartUniversitySystem

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 06/07/2018 15:45:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动生成UUID',
  `au_name` varchar(50) NOT NULL COMMENT '权限名，比如老师、学生、商户',
  `au_details` varchar(200) DEFAULT NULL COMMENT '权限职能描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of authority
-- ----------------------------
BEGIN;
INSERT INTO `authority` VALUES (1, 'ROLE_MERCHANT', '添加、查看、删除、查询商品信息');
INSERT INTO `authority` VALUES (2, 'ROLE_CUSTOMER', '查看、查询商品信息');
INSERT INTO `authority` VALUES (3, 'ROLE_ADMIN', '添加、查看、删除、查询商户、商品和顾客信息');
INSERT INTO `authority` VALUES (4, 'ROLE_TEST', '测试权限');
INSERT INTO `authority` VALUES (5, 'GUIST', '访问');
COMMIT;

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '一卡通ID',
  `card_vid` varchar(32) NOT NULL COMMENT '一卡通编号',
  `sq_id` varchar(12) NOT NULL COMMENT '申请ID',
  `auth_id` int(11) NOT NULL COMMENT '一卡通权限',
  `application_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` varchar(1) NOT NULL DEFAULT '0' COMMENT '状态 0不可用1可用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `card_sq_id` (`sq_id`) USING BTREE,
  KEY `card_ibfk_1` (`auth_id`),
  CONSTRAINT `card_ibfk_1` FOREIGN KEY (`auth_id`) REFERENCES `authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='一卡通表';

-- ----------------------------
-- Records of card
-- ----------------------------
BEGIN;
INSERT INTO `card` VALUES (51, 'ca1689d58e7d48c2bab8656d53f91818', '530857742798', 2, '2018-07-06 15:24:13', '0');
COMMIT;

-- ----------------------------
-- Table structure for email_info
-- ----------------------------
DROP TABLE IF EXISTS `email_info`;
CREATE TABLE `email_info` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `code` varchar(20) DEFAULT NULL COMMENT '邮箱验证码',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='邮箱验证码表';

-- ----------------------------
-- Records of email_info
-- ----------------------------
BEGIN;
INSERT INTO `email_info` VALUES (1, '623344053@qq.com', '9D0CG6', '2018-07-02 17:36:38');
INSERT INTO `email_info` VALUES (7, 'ritchie@qq.com', '2B50F9', '2018-07-03 08:09:42');
INSERT INTO `email_info` VALUES (8, '1169532212@qq.com', '630DC4', '2018-07-04 00:05:18');
INSERT INTO `email_info` VALUES (9, '1348390540@qq.com', 'A0GCD5', '2018-07-04 13:22:25');
INSERT INTO `email_info` VALUES (10, 'smallraccoons@outlook.com', 'D92A58', '2018-07-04 13:46:58');
COMMIT;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `g_name` varchar(100) NOT NULL COMMENT '商品名',
  `g_price` varchar(10) NOT NULL,
  `g_quantity` int(11) NOT NULL,
  `g_ly` varchar(100) NOT NULL COMMENT '商品来源',
  `g_vid` varchar(100) NOT NULL COMMENT '条形码',
  `g_img` varchar(100) DEFAULT NULL COMMENT '商品图片',
  `g_simg` varchar(100) DEFAULT NULL COMMENT '商品缩略图',
  `g_label` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `g_details` varchar(500) DEFAULT NULL COMMENT '商品详情',
  `g_state` char(2) NOT NULL COMMENT '商品状态 1上架 0下架 -1缺货',
  `s_i_uuid` varchar(32) NOT NULL COMMENT '商家表ID',
  PRIMARY KEY (`id`),
  KEY `goods_ibfk_1` (`s_i_uuid`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`s_i_uuid`) REFERENCES `shop_info` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of goods
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES (9, '草莓', '3', 11, '市场', 'xxxxxxxxx', 'red', 'red', '红色的草莓', '红色的', '1', '1');
INSERT INTO `goods` VALUES (10, '苹果', '2', 4, '超市', '2', 'yello', 'yello', '黄色的苹果', '黄色的', '0', '2');
INSERT INTO `goods` VALUES (11, '蓝莓', '5', 106, '农场', '沈挺爱撸管', 'blue', 'blue', '蓝莓', '蓝色的', '0', '3');
INSERT INTO `goods` VALUES (12, '蓝莓', '5', 6, '农场', '3', 'blue', 'blue', '蓝莓', '蓝色的', '0', '3');
INSERT INTO `goods` VALUES (13, '面条', '7.5', 5, '工厂', '4', '无色', '无色', '干脆面', '干脆面', '0', '4');
INSERT INTO `goods` VALUES (14, '面条', '7.5', 5, '工厂', '4', '无色', '无色', '干脆面', '干脆面', '0', '4');
INSERT INTO `goods` VALUES (22, '乒乓球', '12', 12, '工厂', '7', 'yellow', 'yellow', '乒乓球', '乒乓球', '1', '4');
INSERT INTO `goods` VALUES (23, '网球', '12', 12, '工厂', '8', 'yellow', 'yellow', '网球拍', '网去拍', '1', '4');
INSERT INTO `goods` VALUES (24, '网球', '12', 12, '工厂', '8', 'yellow', 'yellow', '网球拍', '网去拍', '1', '4');
INSERT INTO `goods` VALUES (25, '网球', '12', 12, '工厂', '8', 'yellow', 'yellow', '网球拍', '网去拍', '1', '4');
INSERT INTO `goods` VALUES (26, '排球', '14', 13, '工厂', '9', 'white', 'white', '排球', '排球', '1', '4');
INSERT INTO `goods` VALUES (27, '南京工业大学', '200000000', 2, '江苏南京', 'fxfsdxdfsdf', 'http://ohkd1whwt.bkt.clouddn.com/1530784971059', 'http://ohkd1whwt.bkt.clouddn.com/1530784971059', '南京工业大学', '四非大学', '1', '1');
INSERT INTO `goods` VALUES (28, 'poorHouse', '2000000', 1, 'njtech', '2312321413', 'http://ohkd1whwt.bkt.clouddn.com/1530802157642', 'http://ohkd1whwt.bkt.clouddn.com/1530802157642', 'poorhouse', 'For Honor is a video game developed and published by Ubisoft for Microsoft Windows, PlayStation 4, and Xbox One. The game allows players to play the roles of historical forms of soldiers and warriors, including knights, samurai, and vikings within a medieval setting, controlled using a third-person perspective.\r\nAnnounced at Electronic Entertainment Expo 2015, the game was developed primarily by Ubisoft\'s studio', '0', '1');
INSERT INTO `goods` VALUES (29, '干锅包菜', '20', 125, '江苏农村', 'xfsdfsffsdfdsfdsfsdfds', 'http://ohkd1whwt.bkt.clouddn.com/1530802866817', 'http://ohkd1whwt.bkt.clouddn.com/1530802866817', '很好吃', 'All heroes are unique and have their own weapons, skills, and fighting styles.[4] There are 12 pieces of downloadable content. Players fight against their opponents with their class-specific melee weapons. When players perform certain actions, such as killing multiple enemies consecutively, they gain Feats, which are additional perks. These perks allow players to gain additional points and strengths, call in a barrage of arrows or a catapult attack, or heal themselves.[5]', '0', '4');
INSERT INTO `goods` VALUES (30, '新垣结衣', '999999999', 0, '日本原产', 'beautiful girl', 'http://ohkd1whwt.bkt.clouddn.com/1530859532066', 'http://ohkd1whwt.bkt.clouddn.com/1530859532066', '日本美女,哈哈哈哈哈哈', '2001年，参加《nicola》模特比赛并获得最优秀奖。2005年，因出演现代剧《涩谷15》而作为演员出道。2006年，参演校园剧《我的老大，我的英雄》；同年，她还出版了个人首本写真集《水漾青春》。2007年，她从日出高校毕业后开始专注于演艺发展，并发表个人首张音乐专辑《天空》；同年，新垣结衣还主演了爱情片《恋空》，而她也凭借该片获得了多个电影新人奖项 [2-3]  。2010年，主演爱情片《花水木》', '-1', '1');
COMMIT;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_uuid` varchar(32) NOT NULL COMMENT '用户ID',
  `g_id` int(11) NOT NULL COMMENT '商品ID',
  `num` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `order_ibfk_1` (`g_id`),
  KEY `order_ibfk_2` (`user_uuid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`g_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`user_uuid`) REFERENCES `user` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of orders
-- ----------------------------
BEGIN;
INSERT INTO `orders` VALUES (21, '09ef1507453340e688c0aee8038effdf', 27, 1);
INSERT INTO `orders` VALUES (22, '09ef1507453340e688c0aee8038effdf', 27, 1);
INSERT INTO `orders` VALUES (23, '3446c151af764ad8bcb3f5a2d930cd6c', 27, 1);
INSERT INTO `orders` VALUES (24, '3446c151af764ad8bcb3f5a2d930cd6c', 27, 1);
INSERT INTO `orders` VALUES (25, '3446c151af764ad8bcb3f5a2d930cd6c', 26, 3);
INSERT INTO `orders` VALUES (26, '3446c151af764ad8bcb3f5a2d930cd6c', 26, 1);
INSERT INTO `orders` VALUES (27, '3446c151af764ad8bcb3f5a2d930cd6c', 26, 1);
INSERT INTO `orders` VALUES (28, '3446c151af764ad8bcb3f5a2d930cd6c', 26, 1);
INSERT INTO `orders` VALUES (29, '3446c151af764ad8bcb3f5a2d930cd6c', 26, 0);
INSERT INTO `orders` VALUES (30, '3446c151af764ad8bcb3f5a2d930cd6c', 27, 1);
INSERT INTO `orders` VALUES (31, '3446c151af764ad8bcb3f5a2d930cd6c', 30, 1);
INSERT INTO `orders` VALUES (32, '3446c151af764ad8bcb3f5a2d930cd6c', 27, 1);
INSERT INTO `orders` VALUES (33, '3446c151af764ad8bcb3f5a2d930cd6c', 27, 20);
COMMIT;

-- ----------------------------
-- Table structure for shop_info
-- ----------------------------
DROP TABLE IF EXISTS `shop_info`;
CREATE TABLE `shop_info` (
  `uuid` varchar(32) NOT NULL COMMENT 'uuID',
  `shop_name` varchar(16) NOT NULL COMMENT '商家名',
  `shop_addr` varchar(50) NOT NULL COMMENT '商家地址',
  `state` char(1) NOT NULL COMMENT '状态 是否可用 0不可用 1可用',
  `user_uuid` varchar(32) NOT NULL,
  PRIMARY KEY (`uuid`),
  KEY `shop_ibfk_1` (`user_uuid`) USING BTREE,
  CONSTRAINT `shop_ibfk_1` FOREIGN KEY (`user_uuid`) REFERENCES `user` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家表';

-- ----------------------------
-- Records of shop_info
-- ----------------------------
BEGIN;
INSERT INTO `shop_info` VALUES ('1', 'ATLUS', 'Japan', '1', '09ef1507453340e688c0aee8038effdf');
INSERT INTO `shop_info` VALUES ('2', 'Ubisoft', 'France', '1', 'afca81b91ceb493b969223ace6332627');
INSERT INTO `shop_info` VALUES ('3', 'NaughtyDog', 'American', '1', 'afca81b91ceb493b969223ace6332627');
INSERT INTO `shop_info` VALUES ('4', 'CAPCOM', 'Japan', '1', 'afca81b91ceb493b969223ace6332627');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uuid` varchar(32) NOT NULL COMMENT '自动生成UUID',
  `id` varchar(12) NOT NULL COMMENT '学号/教师账号 系统生成',
  `password` varchar(100) NOT NULL COMMENT '密码，手机登录无需密码',
  `state` char(1) NOT NULL DEFAULT '1' COMMENT '是否可用，0不可用，1可用',
  `type` char(1) NOT NULL COMMENT '0邮箱，1手机，2帐号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `auth_id` int(11) NOT NULL COMMENT '用户权限',
  `shop_type` char(1) NOT NULL COMMENT '0普通用户 1商家',
  `last_password_reset_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `email` (`email`) USING BTREE,
  UNIQUE KEY `account` (`account`) USING BTREE,
  KEY `user_ibfk_1` (`auth_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`auth_id`) REFERENCES `authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('09ef1507453340e688c0aee8038effdf', '530812346508', '$2a$10$ibNHU3xDMlKyga5CmyZY/e4uclh6SlosVxmIMBEuGwKr65wJzJd06', '1', '2', NULL, NULL, 'merchant', 1, '0', '2018-07-05 17:40:02');
INSERT INTO `user` VALUES ('1433f0c55fa3418ebab0eda250ef17cc', '530710049083', '$2a$10$DKyl0nEPwaFvp8IJdykMwOvymoPfmCpFHtSx.sX.euQ8Gf4zak/sG', '1', '2', NULL, NULL, 'awqw', 2, '0', '2018-07-04 13:14:20');
INSERT INTO `user` VALUES ('3446c151af764ad8bcb3f5a2d930cd6c', '530857742798', '$2a$10$vIUOufEgAXOzXZMmeqcHN.FIwLePnxqQISO25rVFCD6Q37gVD/Rey', '1', '2', NULL, NULL, 'user', 2, '0', '2018-07-06 06:15:42');
INSERT INTO `user` VALUES ('905869556414484f833e2b99f92d486c', '530710832297', '$2a$10$LpjjOCu4YLWJFIvHcy0AIuKSe9jGmeF34GvPCUzzBu2TYWtjql1Ea', '1', '2', NULL, NULL, '121312123', 2, '0', '2018-07-04 13:27:24');
INSERT INTO `user` VALUES ('98495d88a67c47adb0cc0756b9d26943', '530712939801', '$2a$10$tqHJtV1zpnULUiSIWoks/OKjZGFPHWjXw88LEspCjs.OOdmivmH0m', '1', '2', NULL, NULL, 'fafsfdsfdsfds', 2, '1', '2018-07-04 14:02:34');
INSERT INTO `user` VALUES ('afca81b91ceb493b969223ace6332627', '530633947926', '$2a$10$oLfuSekQ2eh6Y3z8io6QqOVdvZvAJp5GbhINEnl2/Joai1YnBHpvu', '1', '0', '1169532212@qq.com', NULL, 'admin', 3, '1', '2018-07-03 16:05:56');
INSERT INTO `user` VALUES ('b4d9f976431946efa780293c4e584d29', '530712084667', '$2a$10$9hSEoCG6/WghGgeahc3w5u0O0E.7u0thpzH1XTAja1n9HbuW5cea.', '1', '0', 'smallraccoons@outlook.com', NULL, NULL, 1, '1', '2018-07-04 13:48:18');
INSERT INTO `user` VALUES ('e136b4ecf2cd479783fdd92a50ae5b74', '530710757077', '$2a$10$5XMFazDNrDzBt/ab2ZkPO.MA3YUr55bTfTIdc57zP4Qpz4zbVfKw6', '1', '2', NULL, NULL, '12133', 2, '0', '2018-07-04 13:26:09');
INSERT INTO `user` VALUES ('ed177faa235d464689cc4ac77a4ed439', '530710612021', '$2a$10$jkKCQLCh23G1luln69emKO5ANb85inNn.aohcYYogrvI9woQ.Bo6y', '1', '0', '1348390540@qq.com', NULL, NULL, 2, '0', '2018-07-04 13:23:43');
INSERT INTO `user` VALUES ('f129063c0b704fd9a72661bb147bb243', '530709708344', '$2a$10$tQhlMmnG3OgT.N3Cc9HZG.L7AJFpKIdEtJ1VRBLKGQb7JVR5YMECO', '1', '2', NULL, NULL, 'aa', 2, '0', '2018-07-04 13:08:39');
INSERT INTO `user` VALUES ('f4faca5e3ab44ce8a0f4592dd214fd06', '530709789498', '$2a$10$oWuwmIEXnLT0.Etu6qWW3OW45PmP1/KrItGm4pV6vb8/mjw3iaHPC', '1', '2', NULL, NULL, 'abc', 2, '0', '2018-07-04 13:10:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
