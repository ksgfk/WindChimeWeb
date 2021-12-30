/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : wind_chime

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 30/12/2021 15:48:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemId` int(11) NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `discount` int(11) NOT NULL,
  `special_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_active` int(1) NOT NULL,
  PRIMARY KEY (`id`, `itemId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for intertwined_fate
-- ----------------------------
DROP TABLE IF EXISTS `intertwined_fate`;
CREATE TABLE `intertwined_fate`  (
  `id` int(11) NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `count` int(11) NOT NULL,
  `extra_count` int(255) NOT NULL,
  `discount` int(255) NOT NULL,
  `uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `position` enum('head','earring','necklace','bracelet','ring') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `uriName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for item_position_minor
-- ----------------------------
DROP TABLE IF EXISTS `item_position_minor`;
CREATE TABLE `item_position_minor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` enum('head','earring','necklace','bracelet','ring') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `minor` int(11) NULL DEFAULT NULL,
  `minor_weight` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for item_position_primory
-- ----------------------------
DROP TABLE IF EXISTS `item_position_primory`;
CREATE TABLE `item_position_primory`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` enum('head','earring','necklace','bracelet','ring') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `primary` int(255) NULL DEFAULT NULL,
  `primary_weight` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for item_property
-- ----------------------------
DROP TABLE IF EXISTS `item_property`;
CREATE TABLE `item_property`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `percentage` int(255) NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `increase` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Password` binary(32) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `QQ` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `WorkCount` int(255) NULL DEFAULT NULL,
  `HeartCount` int(255) NULL DEFAULT NULL,
  `Heart` int(255) NULL DEFAULT NULL,
  `Courage` int(255) NULL DEFAULT NULL,
  `Cautious` int(255) NULL DEFAULT NULL,
  `Discipline` int(255) NULL DEFAULT NULL,
  `Justice` int(255) NULL DEFAULT NULL,
  `MoonCard` int(255) NULL DEFAULT NULL,
  `Money` int(255) NULL DEFAULT NULL,
  `SavePebox` int(255) NULL DEFAULT NULL,
  `LatestLogin` datetime(6) NULL DEFAULT NULL,
  `lastRefreshShop` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 256 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_fate
-- ----------------------------
DROP TABLE IF EXISTS `user_fate`;
CREATE TABLE `user_fate`  (
  `userId` int(11) NOT NULL,
  `fate_count` int(255) NOT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_has_card
-- ----------------------------
DROP TABLE IF EXISTS `user_has_card`;
CREATE TABLE `user_has_card`  (
  `userId` int(11) NOT NULL,
  `cardId` int(11) NOT NULL,
  PRIMARY KEY (`userId`, `cardId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_has_item
-- ----------------------------
DROP TABLE IF EXISTS `user_has_item`;
CREATE TABLE `user_has_item`  (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `itemId` int(11) NOT NULL,
  `rare` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `primary` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `minor` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`userId`, `itemId`, `id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_shop_item
-- ----------------------------
DROP TABLE IF EXISTS `user_shop_item`;
CREATE TABLE `user_shop_item`  (
  `userId` int(11) NOT NULL,
  `goodsId` int(11) NOT NULL,
  `rare` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `has_buy` int(1) NOT NULL,
  PRIMARY KEY (`userId`, `goodsId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_use_card
-- ----------------------------
DROP TABLE IF EXISTS `user_use_card`;
CREATE TABLE `user_use_card`  (
  `userId` int(11) NOT NULL,
  `slot1` int(255) NULL DEFAULT NULL,
  `slot2` int(255) NULL DEFAULT NULL,
  `slot3` int(255) NULL DEFAULT NULL,
  `slot4` int(255) NULL DEFAULT NULL,
  `slot5` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
