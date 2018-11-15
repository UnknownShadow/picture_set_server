/*
 Navicat MySQL Data Transfer

 Source Server         : 188.131.176.201
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 188.131.176.201:3306
 Source Schema         : second_hand_mall

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 31/10/2018 18:06:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity_module
-- ----------------------------
DROP TABLE IF EXISTS `activity_module`;
CREATE TABLE `activity_module`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '模块描述',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '展示图片',
  `type` int(1) NOT NULL DEFAULT 1 COMMENT '模块展示类型1：需要展示商品内容；2：只需展示文字和图片',
  `status` int(2) NOT NULL DEFAULT 0 COMMENT '0：不展示；1：展示',
  `sort` int(11) NOT NULL COMMENT '排序',
  `icon` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_module
-- ----------------------------
INSERT INTO `activity_module` VALUES (1, '新品速递', 'in季新品抢先购', 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914175607.png', 1, 1, 2, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/%E6%AD%A3%E5%9C%A8%E6%8B%8D%E5%8D%96.png', '2018-09-14 22:58:09', NULL);
INSERT INTO `activity_module` VALUES (2, '好物热卖', '每周TOP榜', 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914175841.png', 1, 1, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/%E9%A2%84%E5%B1%95.png', '2018-09-14 23:57:30', NULL);
INSERT INTO `activity_module` VALUES (3, '专题', '名家作品', 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914184016.png', 2, 1, 3, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/%E5%B7%B2%E7%BB%93%E6%8B%8D.png', '2018-09-15 00:00:17', NULL);

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `zip_code` int(11) NULL DEFAULT NULL,
  `province` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `city` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `area` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `street` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '详细地址',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (58, 1, '坡子街', '13508169494', 0, '北京市', '北京市', '东城区', '', '退货', '2018-10-26 17:35:42', NULL);
INSERT INTO `address` VALUES (59, 1, '考虑考虑', '13055588555', 0, '湖南省', '长沙市', '芙蓉区', '', '看看', '2018-10-26 17:36:29', '2018-10-26 17:37:15');

-- ----------------------------
-- Table structure for bidding_records
-- ----------------------------
DROP TABLE IF EXISTS `bidding_records`;
CREATE TABLE `bidding_records`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL DEFAULT 0 COMMENT '商品关联ID',
  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '竞拍人信息ID',
  `bidding_price` int(11) NOT NULL DEFAULT 0 COMMENT '竞拍价格（单位：分）',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bidding_records
-- ----------------------------
INSERT INTO `bidding_records` VALUES (1, 1, 1, 1500, '2018-09-29 22:48:48', NULL);
INSERT INTO `bidding_records` VALUES (2, 1, 55, 1000, '2018-09-12 22:49:34', NULL);
INSERT INTO `bidding_records` VALUES (8, 1, 1, 2500, '2018-10-07 23:10:13', NULL);
INSERT INTO `bidding_records` VALUES (17, 1, 55, 3500, '2018-10-07 23:15:49', NULL);
INSERT INTO `bidding_records` VALUES (18, 1, 1, 4500, '2018-10-07 23:41:11', NULL);

-- ----------------------------
-- Table structure for bidding_timeliness
-- ----------------------------
DROP TABLE IF EXISTS `bidding_timeliness`;
CREATE TABLE `bidding_timeliness`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `img` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bidding_timeliness
-- ----------------------------
INSERT INTO `bidding_timeliness` VALUES (1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914175607.png', '正在热拍', '2018-09-28 15:35:56', '2018-10-31 15:35:59', '2018-09-29 21:35:22');
INSERT INTO `bidding_timeliness` VALUES (2, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914175841.png', '预展', '2018-10-01 15:36:40', '2018-10-07 15:36:45', '2018-09-29 21:36:21');

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 122 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES (120, 1, 5, '2018-10-31 00:27:23');

-- ----------------------------
-- Table structure for help
-- ----------------------------
DROP TABLE IF EXISTS `help`;
CREATE TABLE `help`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of help
-- ----------------------------
INSERT INTO `help` VALUES (1, '同步竞拍', '能够在线上参与线下竞拍的拍卖方式。竞拍过程与拍卖会现场同步', '2018-10-24 17:57:21');
INSERT INTO `help` VALUES (2, '起拍价', '拍品开拍时的定价', '2018-10-24 18:07:17');
INSERT INTO `help` VALUES (3, '同步', '似懂非懂', '2018-10-25 10:21:14');

-- ----------------------------
-- Table structure for module_association
-- ----------------------------
DROP TABLE IF EXISTS `module_association`;
CREATE TABLE `module_association`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `activity_module_id` int(11) NOT NULL DEFAULT 0 COMMENT '模块ID',
  `product_id` int(11) NOT NULL DEFAULT 0 COMMENT '商品ID',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of module_association
-- ----------------------------
INSERT INTO `module_association` VALUES (1, 1, 1, '2018-10-08 16:51:03');
INSERT INTO `module_association` VALUES (2, 1, 2, '2018-10-08 16:51:26');
INSERT INTO `module_association` VALUES (3, 2, 1, '2018-10-08 17:08:19');
INSERT INTO `module_association` VALUES (4, 2, 2, '2018-10-08 17:08:59');
INSERT INTO `module_association` VALUES (5, 1, 3, '2018-10-25 11:25:03');
INSERT INTO `module_association` VALUES (6, 1, 4, '2018-10-25 11:25:19');
INSERT INTO `module_association` VALUES (7, 1, 5, '2018-10-25 11:25:20');
INSERT INTO `module_association` VALUES (8, 2, 3, '2018-10-25 11:25:28');
INSERT INTO `module_association` VALUES (9, 3, 1, '2018-10-30 14:35:01');
INSERT INTO `module_association` VALUES (10, 3, 2, '2018-10-30 14:35:06');
INSERT INTO `module_association` VALUES (11, 3, 3, '2018-10-30 14:35:11');

-- ----------------------------
-- Table structure for order_extra
-- ----------------------------
DROP TABLE IF EXISTS `order_extra`;
CREATE TABLE `order_extra`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订单关联ID',
  `product_id` int(11) NOT NULL COMMENT '商品关联ID',
  `sku_index` int(1) NOT NULL DEFAULT 0 COMMENT '商品SKU索引',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` int(11) NOT NULL COMMENT '单个商品的价格',
  `num` int(5) NOT NULL DEFAULT 1 COMMENT '购买的数量',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_extra
-- ----------------------------
INSERT INTO `order_extra` VALUES (9, 6, 1, 0, '女裙', 10000, 1, '2018-10-09 17:21:45');
INSERT INTO `order_extra` VALUES (10, 6, 2, 1, '单鞋', 51000, 2, '2018-10-09 17:21:45');
INSERT INTO `order_extra` VALUES (11, 7, 1, 0, '女裙', 100000, 10, '2018-10-09 20:38:56');
INSERT INTO `order_extra` VALUES (12, 7, 2, 0, '单鞋', 600000, 60, '2018-10-09 20:38:56');
INSERT INTO `order_extra` VALUES (13, 8, 1, 1, '女裙', 29800, 1, '2018-10-09 21:01:25');
INSERT INTO `order_extra` VALUES (14, 8, 2, 0, '单鞋', 0, 0, '2018-10-09 21:01:25');
INSERT INTO `order_extra` VALUES (15, 9, 1, 1, '女裙', 29800, 1, '2018-10-09 21:01:29');
INSERT INTO `order_extra` VALUES (16, 9, 2, 0, '单鞋', 0, 0, '2018-10-09 21:01:29');
INSERT INTO `order_extra` VALUES (17, 10, 1, 1, '女裙', 29800, 1, '2018-10-09 21:01:34');
INSERT INTO `order_extra` VALUES (18, 10, 2, 0, '单鞋', 0, 0, '2018-10-09 21:01:34');
INSERT INTO `order_extra` VALUES (19, 11, 1, 1, '女裙', 29800, 1, '2018-10-09 21:01:37');
INSERT INTO `order_extra` VALUES (20, 11, 2, 0, '单鞋', 0, 0, '2018-10-09 21:01:37');
INSERT INTO `order_extra` VALUES (21, 12, 1, 1, '女裙', 298000, 10, '2018-10-09 21:01:53');
INSERT INTO `order_extra` VALUES (22, 12, 2, 0, '单鞋', 70000, 7, '2018-10-09 21:01:53');
INSERT INTO `order_extra` VALUES (23, 13, 1, 1, '女裙', 327800, 11, '2018-10-09 21:01:57');
INSERT INTO `order_extra` VALUES (24, 13, 2, 0, '单鞋', 70000, 7, '2018-10-09 21:01:57');
INSERT INTO `order_extra` VALUES (25, 14, 1, 1, '女裙', 298000, 10, '2018-10-09 21:07:32');
INSERT INTO `order_extra` VALUES (26, 14, 2, 0, '单鞋', 70000, 7, '2018-10-09 21:07:32');
INSERT INTO `order_extra` VALUES (27, 15, 1, 0, '女裙', 10000, 1, '2018-10-29 10:51:34');
INSERT INTO `order_extra` VALUES (28, 16, 1, 0, '女裙', 10000, 1, '2018-10-29 11:00:02');
INSERT INTO `order_extra` VALUES (29, 17, 1, 0, '女裙', 10000, 1, '2018-10-29 11:01:07');
INSERT INTO `order_extra` VALUES (30, 18, 1, 0, '女裙', 10000, 1, '2018-10-29 11:01:47');
INSERT INTO `order_extra` VALUES (31, 19, 1, 0, '女裙', 10000, 1, '2018-10-29 11:03:17');
INSERT INTO `order_extra` VALUES (32, 20, 1, 0, '女裙', 10000, 1, '2018-10-29 11:04:23');
INSERT INTO `order_extra` VALUES (33, 21, 1, 0, '女裙', 10000, 1, '2018-10-29 11:04:55');
INSERT INTO `order_extra` VALUES (34, 22, 1, 0, '女裙', 10000, 1, '2018-10-29 11:07:03');
INSERT INTO `order_extra` VALUES (35, 23, 1, 0, '女裙', 10000, 1, '2018-10-29 11:08:09');
INSERT INTO `order_extra` VALUES (36, 24, 1, 0, '女裙', 10000, 1, '2018-10-29 11:11:27');
INSERT INTO `order_extra` VALUES (37, 25, 1, 0, '女裙', 10000, 1, '2018-10-29 11:12:55');
INSERT INTO `order_extra` VALUES (38, 26, 1, 0, '女裙', 10000, 1, '2018-10-29 11:14:37');
INSERT INTO `order_extra` VALUES (39, 27, 1, 0, '女裙', 10000, 1, '2018-10-29 11:22:21');
INSERT INTO `order_extra` VALUES (40, 28, 1, 0, '女裙', 10000, 1, '2018-10-29 11:30:43');
INSERT INTO `order_extra` VALUES (41, 29, 1, 0, '女裙', 10000, 1, '2018-10-29 11:37:23');
INSERT INTO `order_extra` VALUES (42, 30, 1, 0, '女裙', 10000, 1, '2018-10-29 11:43:06');
INSERT INTO `order_extra` VALUES (43, 31, 1, 0, '女裙', 99880, 1, '2018-10-31 17:00:02');
INSERT INTO `order_extra` VALUES (44, 32, 1, 0, '女裙', 99880, 1, '2018-10-31 17:52:03');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `price` int(11) NOT NULL DEFAULT 0 COMMENT '支付金额（单位：分）',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '-3逻辑删除,-2已退款,-1已取消,0-待支付,1-已支付',
  `delivery_status` int(1) NOT NULL DEFAULT 0 COMMENT '发货状态；0:待支付,1:待发货,2:待收货,3:已收货',
  `out_trade_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '支付平台订单号',
  `address_id` int(11) NOT NULL COMMENT '下单地址',
  `hide_status` int(1) NOT NULL DEFAULT 1 COMMENT '0：隐藏订单；1：显示订单，',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (6, 1, 61000, 0, 0, '8084201810091121413685', 2, 1, '2018-10-09 17:21:45', NULL);
INSERT INTO `orders` VALUES (7, 1, 700000, 1, 1, '4701201810091438536786', 99, 1, '2018-10-09 20:38:56', NULL);
INSERT INTO `orders` VALUES (8, 1, 29800, 0, 0, '7311201810091501211916', 99, 1, '2018-10-09 21:01:25', NULL);
INSERT INTO `orders` VALUES (9, 1, 29800, 0, 0, '2502201810091501254896', 99, 1, '2018-10-09 21:01:29', NULL);
INSERT INTO `orders` VALUES (10, 1, 29800, 0, 0, '8084201810091501302567', 99, 1, '2018-10-09 21:01:34', NULL);
INSERT INTO `orders` VALUES (11, 1, 29800, 0, 0, '9301201810091501333232', 99, 1, '2018-10-09 21:01:37', NULL);
INSERT INTO `orders` VALUES (12, 1, 368000, 0, 0, '5409201810091501494348', 99, 1, '2018-10-09 21:01:53', NULL);
INSERT INTO `orders` VALUES (13, 1, 397800, 0, 0, '8725201810091501543013', 99, 1, '2018-10-09 21:01:57', NULL);
INSERT INTO `orders` VALUES (14, 1, 368000, 0, 0, '3291201810091507285147', 99, 1, '2018-10-09 21:07:32', NULL);
INSERT INTO `orders` VALUES (15, 1, 10000, 0, 0, '1814201810291051394660', 58, 1, '2018-10-29 10:51:34', NULL);
INSERT INTO `orders` VALUES (16, 1, 10000, 0, 0, '1518201810291100071834', 58, 1, '2018-10-29 11:00:02', NULL);
INSERT INTO `orders` VALUES (17, 1, 10000, 0, 0, '6167201810291101125453', 58, 1, '2018-10-29 11:01:07', NULL);
INSERT INTO `orders` VALUES (18, 1, 10000, 0, 0, '6053201810291101527759', 58, 1, '2018-10-29 11:01:48', NULL);
INSERT INTO `orders` VALUES (19, 1, 10000, 0, 0, '7617201810291103224579', 58, 1, '2018-10-29 11:03:17', NULL);
INSERT INTO `orders` VALUES (20, 1, 10000, 0, 0, '4720201810291104282352', 58, 1, '2018-10-29 11:04:23', NULL);
INSERT INTO `orders` VALUES (21, 1, 10000, 0, 0, '7579201810291105006379', 58, 1, '2018-10-29 11:04:56', NULL);
INSERT INTO `orders` VALUES (22, 1, 10000, 0, 0, '3372201810291107085263', 58, 1, '2018-10-29 11:07:04', NULL);
INSERT INTO `orders` VALUES (23, 1, 10000, 0, 0, '3575201810291108142729', 58, 1, '2018-10-29 11:08:09', NULL);
INSERT INTO `orders` VALUES (24, 1, 10000, 0, 0, '1287201810291111277358', 58, 1, '2018-10-29 11:11:27', NULL);
INSERT INTO `orders` VALUES (25, 1, 10000, 0, 0, '4359201810291112558840', 58, 1, '2018-10-29 11:12:55', NULL);
INSERT INTO `orders` VALUES (26, 1, 10000, 0, 0, '9873201810291114374390', 58, 1, '2018-10-29 11:14:37', NULL);
INSERT INTO `orders` VALUES (27, 1, 10000, 0, 0, '7910201810291122218311', 58, 1, '2018-10-29 11:22:21', NULL);
INSERT INTO `orders` VALUES (28, 1, 10000, 0, 0, '5812201810291130439948', 58, 1, '2018-10-29 11:30:43', NULL);
INSERT INTO `orders` VALUES (29, 1, 10000, 0, 0, '2317201810291137235668', 58, 1, '2018-10-29 11:37:23', NULL);
INSERT INTO `orders` VALUES (30, 1, 10000, 0, 0, '1185201810291143063271', 58, 1, '2018-10-29 11:43:06', NULL);
INSERT INTO `orders` VALUES (31, 1, 99880, 0, 0, '2275201810311700028983', 58, 1, '2018-10-31 17:00:03', NULL);
INSERT INTO `orders` VALUES (32, 1, 99880, 0, 0, '2558201810311752021792', 58, 1, '2018-10-31 17:52:03', NULL);

-- ----------------------------
-- Table structure for page_carousels
-- ----------------------------
DROP TABLE IF EXISTS `page_carousels`;
CREATE TABLE `page_carousels`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT 1 COMMENT '类型1：同步拍；2：悦商城',
  `product_id` int(11) NOT NULL DEFAULT 0 COMMENT '跳转到商品ID',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片路径',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of page_carousels
-- ----------------------------
INSERT INTO `page_carousels` VALUES (1, 1, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-09-15 00:35:08');
INSERT INTO `page_carousels` VALUES (2, 1, 2, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914175301.png', '2018-09-15 00:37:16');
INSERT INTO `page_carousels` VALUES (3, 1, 3, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914175607.png', '2018-09-15 00:37:19');
INSERT INTO `page_carousels` VALUES (4, 2, 4, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914184016.png', '2018-09-15 00:39:01');
INSERT INTO `page_carousels` VALUES (5, 2, 5, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914175607.png', '2018-10-08 00:47:13');

-- ----------------------------
-- Table structure for product_resources
-- ----------------------------
DROP TABLE IF EXISTS `product_resources`;
CREATE TABLE `product_resources`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL DEFAULT 0,
  `type` int(2) NOT NULL DEFAULT 1 COMMENT '1：商品轮播资源；2：商品详情资源；3：商品介绍资源，',
  `res_type` int(1) NOT NULL DEFAULT 1 COMMENT '资源类型，默认1：图片；2：视频；3：音频',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资源地址',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_resources
-- ----------------------------
INSERT INTO `product_resources` VALUES (1, 1, 1, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-09-15 23:57:17');
INSERT INTO `product_resources` VALUES (2, 1, 1, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807131147241799.jpg', '2018-09-15 23:58:37');
INSERT INTO `product_resources` VALUES (3, 1, 1, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-10-07 21:02:59');
INSERT INTO `product_resources` VALUES (4, 1, 2, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-10-07 21:03:10');
INSERT INTO `product_resources` VALUES (5, 1, 2, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807221610273682.jpg', '2018-10-23 14:16:39');
INSERT INTO `product_resources` VALUES (6, 1, 2, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807170948577660.jpg', '2018-10-23 14:20:10');
INSERT INTO `product_resources` VALUES (7, 2, 1, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-09-15 23:57:17');
INSERT INTO `product_resources` VALUES (8, 2, 1, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807131147241799.jpg', '2018-09-15 23:58:37');
INSERT INTO `product_resources` VALUES (9, 2, 1, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-10-07 21:02:59');
INSERT INTO `product_resources` VALUES (10, 2, 2, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-10-07 21:03:10');
INSERT INTO `product_resources` VALUES (11, 2, 2, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807221610273682.jpg', '2018-10-23 14:16:39');
INSERT INTO `product_resources` VALUES (12, 2, 2, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807170948577660.jpg', '2018-10-23 14:20:10');
INSERT INTO `product_resources` VALUES (13, 11, 1, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-09-15 23:57:17');
INSERT INTO `product_resources` VALUES (14, 11, 1, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807131147241799.jpg', '2018-09-15 23:58:37');
INSERT INTO `product_resources` VALUES (15, 11, 1, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-10-07 21:02:59');
INSERT INTO `product_resources` VALUES (16, 11, 2, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-10-07 21:03:10');
INSERT INTO `product_resources` VALUES (17, 11, 2, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807221610273682.jpg', '2018-10-23 14:16:39');
INSERT INTO `product_resources` VALUES (18, 11, 2, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807170948577660.jpg', '2018-10-23 14:20:10');
INSERT INTO `product_resources` VALUES (19, 8, 1, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-09-15 23:57:17');
INSERT INTO `product_resources` VALUES (20, 8, 1, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807131147241799.jpg', '2018-09-15 23:58:37');
INSERT INTO `product_resources` VALUES (21, 8, 1, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-10-07 21:02:59');
INSERT INTO `product_resources` VALUES (22, 8, 2, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-10-07 21:03:10');
INSERT INTO `product_resources` VALUES (23, 8, 2, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807221610273682.jpg', '2018-10-23 14:16:39');
INSERT INTO `product_resources` VALUES (24, 8, 2, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807170948577660.jpg', '2018-10-23 14:20:10');
INSERT INTO `product_resources` VALUES (25, 18, 1, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-09-15 23:57:17');
INSERT INTO `product_resources` VALUES (26, 18, 1, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807131147241799.jpg', '2018-09-15 23:58:37');
INSERT INTO `product_resources` VALUES (27, 18, 1, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-10-07 21:02:59');
INSERT INTO `product_resources` VALUES (28, 18, 2, 1, 'https://test-1255594100.cos.ap-guangzhou.myqcloud.com/second_hand_mall/20180914183656.png', '2018-10-07 21:03:10');
INSERT INTO `product_resources` VALUES (29, 18, 2, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807221610273682.jpg', '2018-10-23 14:16:39');
INSERT INTO `product_resources` VALUES (30, 18, 2, 1, 'http://test-1255594100.file.myqcloud.com/client-img/201807170948577660.jpg', '2018-10-23 14:20:10');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
  `head_img` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品展示图',
  `price` int(11) NOT NULL DEFAULT 9999 COMMENT '起拍价格或购买价格',
  `price_increase` int(11) NOT NULL DEFAULT 100 COMMENT '加价幅度',
  `attribute` json NOT NULL COMMENT 'SKU属性',
  `description` json NOT NULL COMMENT '展示文字，如：7天无理由退货等',
  `type` int(1) NOT NULL DEFAULT 0 COMMENT '0：隐藏商品，不展示；1：购买商品；2：竞拍商品',
  `bidding_timeliness_id` int(11) NOT NULL DEFAULT 0 COMMENT '竞拍的时效性主键ID；type等于1时，该值为0',
  `collection_num` int(11) NOT NULL DEFAULT 0 COMMENT '该商品的总收藏数量',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (1, '女裙', 'http://test-1255594100.file.myqcloud.com/client-img/201807121405353847.jpg', 99800, 1000, '[{\"name\": \"普通\", \"index\": 0, \"price\": 99880, \"stock\": 93}, {\"name\": \"无损\", \"index\": 1, \"price\": 29800, \"stock\": 5}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 1, 0, '2018-10-14 21:16:01', NULL);
INSERT INTO `products` VALUES (2, '单鞋', 'http://test-1255594100.file.myqcloud.com/client-img/201807121452494196.jpg', 1000, 1000, '[{\"name\": \"普通\", \"index\": 0, \"price\": 1000, \"stock\": 13}, {\"name\": \"无损\", \"index\": 1, \"price\": 25500, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 2, 2, '2018-10-14 21:16:01', NULL);
INSERT INTO `products` VALUES (3, '连衣裙', 'http://test-1255594100.file.myqcloud.com/client-img/201807121441157054.jpg', 69800, 1000, '[{\"name\": \"普通\", \"index\": 0, \"price\": 10000, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 2, 1, '2018-10-14 21:16:01', NULL);
INSERT INTO `products` VALUES (4, '智能手表', 'http://test-1255594100.file.myqcloud.com/client-img/201807121347521135.jpg', 98500, 100, '[{\"name\": \"普通\", \"index\": 0, \"price\": 98500, \"stock\": 20}, {\"name\": \"无损\", \"index\": 1, \"price\": 16600, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 1, 2, '2018-10-20 11:56:48', NULL);
INSERT INTO `products` VALUES (5, '新的双肩包', 'http://test-1255594100.file.myqcloud.com/client-img/201807121522434902.jpg', 4500, 100, '[{\"name\": \"普通\", \"index\": 0, \"price\": 4500, \"stock\": 20}, {\"name\": \"无损\", \"index\": 1, \"price\": 16600, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 1, 1, '2018-10-20 13:59:48', NULL);
INSERT INTO `products` VALUES (6, '裙子', 'http://test-1255594100.file.myqcloud.com/client-img/201807121502339213.jpg', 1200, 100, '[{\"name\": \"普通\", \"index\": 0, \"price\": 10000, \"stock\": 20}, {\"name\": \"无损\", \"index\": 1, \"price\": 16600, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 1, 0, '2018-10-20 14:04:55', NULL);
INSERT INTO `products` VALUES (7, '新手提包', 'http://test-1255594100.file.myqcloud.com/client-img/201807121532480229.jpg', 35600, 100, '[{\"name\": \"普通\", \"index\": 0, \"price\": 10000, \"stock\": 20}, {\"name\": \"无损\", \"index\": 1, \"price\": 16600, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 1, 0, '2018-10-20 14:06:51', NULL);
INSERT INTO `products` VALUES (8, '运动鞋', 'http://test-1255594100.file.myqcloud.com/client-img/201807121509411230.jpg', 4200, 100, '[{\"name\": \"普通\", \"index\": 0, \"price\": 10000, \"stock\": 20}, {\"name\": \"无损\", \"index\": 1, \"price\": 16600, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 2, 0, '2018-10-20 14:09:49', NULL);
INSERT INTO `products` VALUES (11, '女裙', 'http://test-1255594100.file.myqcloud.com/client-img/201807121405353847.jpg', 99800, 1000, '[{\"name\": \"普通\", \"index\": 0, \"price\": 10000, \"stock\": 1}, {\"name\": \"无损\", \"index\": 1, \"price\": 29800, \"stock\": 5}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 1, 0, '2018-10-14 21:16:01', NULL);
INSERT INTO `products` VALUES (12, '单鞋', 'http://test-1255594100.file.myqcloud.com/client-img/201807121452494196.jpg', 1000, 1000, '[{\"name\": \"普通\", \"index\": 0, \"price\": 10000, \"stock\": 13}, {\"name\": \"无损\", \"index\": 1, \"price\": 25500, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 2, 3, '2018-10-14 21:16:01', NULL);
INSERT INTO `products` VALUES (13, '连衣裙', 'http://test-1255594100.file.myqcloud.com/client-img/201807121441157054.jpg', 69800, 1000, '[{\"name\": \"普通\", \"index\": 0, \"price\": 10000, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 2, 1, '2018-10-14 21:16:01', NULL);
INSERT INTO `products` VALUES (14, '智能手表', 'http://test-1255594100.file.myqcloud.com/client-img/201807121347521135.jpg', 98500, 100, '[{\"name\": \"普通\", \"index\": 0, \"price\": 10000, \"stock\": 20}, {\"name\": \"无损\", \"index\": 1, \"price\": 16600, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 1, 3, '2018-10-20 11:56:48', NULL);
INSERT INTO `products` VALUES (15, '新的双肩包', 'http://test-1255594100.file.myqcloud.com/client-img/201807121522434902.jpg', 4500, 100, '[{\"name\": \"普通\", \"index\": 0, \"price\": 10000, \"stock\": 20}, {\"name\": \"无损\", \"index\": 1, \"price\": 16600, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 1, 1, '2018-10-20 13:59:48', NULL);
INSERT INTO `products` VALUES (16, '裙子', 'http://test-1255594100.file.myqcloud.com/client-img/201807121502339213.jpg', 1200, 100, '[{\"name\": \"普通\", \"index\": 0, \"price\": 10000, \"stock\": 20}, {\"name\": \"无损\", \"index\": 1, \"price\": 16600, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 1, 1, '2018-10-20 14:04:55', NULL);
INSERT INTO `products` VALUES (17, '新手提包', 'http://test-1255594100.file.myqcloud.com/client-img/201807121532480229.jpg', 35600, 100, '[{\"name\": \"普通\", \"index\": 0, \"price\": 10000, \"stock\": 20}, {\"name\": \"无损\", \"index\": 1, \"price\": 16600, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 1, 0, '2018-10-20 14:06:51', NULL);
INSERT INTO `products` VALUES (18, '运动鞋', 'http://test-1255594100.file.myqcloud.com/client-img/201807121509411230.jpg', 4200, 100, '[{\"name\": \"普通\", \"index\": 0, \"price\": 10000, \"stock\": 20}, {\"name\": \"无损\", \"index\": 1, \"price\": 16600, \"stock\": 20}]', '[{\"img\": \"http://test-1255594100.file.myqcloud.com/client-video/201807301728003862.png\", \"content\": \"7天无理由退货\"}]', 0, 2, 0, '2018-10-20 14:09:49', NULL);

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户关联ID',
  `product_id` int(11) NOT NULL COMMENT '商品关联ID',
  `attr` json NULL COMMENT 'sku属性值',
  `num` int(11) NOT NULL DEFAULT 1 COMMENT '加入购物车的数量',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES (40, 1, 3, '{\"name\": \"普通\", \"index\": 0, \"price\": 10000, \"stock\": 20}', 1, '2018-10-30 17:07:32', NULL);
INSERT INTO `shopping_cart` VALUES (42, 1, 5, '{\"name\": \"无损\", \"index\": 1, \"price\": 16600, \"stock\": 20}', 1, '2018-10-30 17:36:04', NULL);
INSERT INTO `shopping_cart` VALUES (50, 1, 1, '{\"name\": \"普通\", \"index\": 0, \"price\": 99880, \"stock\": 95}', 1, '2018-10-31 16:46:28', NULL);
INSERT INTO `shopping_cart` VALUES (51, 1, 1, '{\"name\": \"普通\", \"index\": 0, \"price\": 99880, \"stock\": 95}', 4, '2018-10-31 16:47:48', NULL);
INSERT INTO `shopping_cart` VALUES (52, 1, 1, '{\"name\": \"普通\", \"index\": 0, \"price\": 99880, \"stock\": 94}', 1, '2018-10-31 17:16:23', NULL);
INSERT INTO `shopping_cart` VALUES (53, 1, 4, '{\"name\": \"无损\", \"index\": 1, \"price\": 16600, \"stock\": 20}', 1, '2018-10-31 17:34:22', NULL);
INSERT INTO `shopping_cart` VALUES (54, 1, 2, '{\"name\": \"无损\", \"index\": 1, \"price\": 25500, \"stock\": 20}', 2, '2018-10-31 17:54:24', NULL);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `openid` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '小程序唯一标识',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `gender` int(1) NOT NULL DEFAULT 0 COMMENT '用户的性别，1：男性，2：女性，0：未知',
  `city` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户所在城市',
  `province` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户所在省份',
  `country` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户所在国家',
  `address_id` int(11) NOT NULL DEFAULT 0 COMMENT '默认收货地址id',
  `money` int(11) NOT NULL DEFAULT 0 COMMENT '用户充值金额（单位：分）',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'o210E5uku1i-JTK6ouTeWYBXxrAM', 'nick', 'www.baidu.com', 1, NULL, NULL, NULL, 59, 10, '2018-09-15 20:49:53', NULL);
INSERT INTO `users` VALUES (55, 'fwe5f5we51w53rlsdfosdfleijv', '98k', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIQ0SUKrWP6yGzMuickr2iajnBZ1kXvj8SfKa2yJFEQqd9bySguMIO77c4WOxDeoYuwBGia9S8JMFzHw/132', 1, 'China', NULL, NULL, 0, 0, '2018-10-07 17:17:46', NULL);
INSERT INTO `users` VALUES (56, 'oiHmA4sVttFwlQLyUIdQv69zwqoA', '⁯', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIa8YWUhXwU9EQ2PsRhIjicVEWaHI4xK9fFArf1rBibN2iaVJ17M1ibefS0JZ2IOMhPExuRuEoTh7YCmQ/132', 1, '', '', '阿尔巴尼亚', 0, 0, '2018-10-15 12:36:57', NULL);

SET FOREIGN_KEY_CHECKS = 1;
