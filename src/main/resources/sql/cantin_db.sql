/*
 Navicat Premium Dump SQL

 Source Server         : sa
 Source Server Type    : MySQL
 Source Server Version : 80039 (8.0.39)
 Source Host           : localhost:3306
 Source Schema         : cantin_db

 Target Server Type    : MySQL
 Target Server Version : 80039 (8.0.39)
 File Encoding         : 65001

 Date: 22/08/2025 18:54:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish`  (
  `dish_id` int NOT NULL AUTO_INCREMENT COMMENT '菜品ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品名称',
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类（主食/荤菜/素菜等）',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '菜品描述',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜品图片URL',
  `recommend` int NULL DEFAULT 0 COMMENT '是否推荐（0:否 1:是）',
  `stock_status` int NULL DEFAULT 1 COMMENT '库存状态（0:缺货 1:正常）',
  `restaurant_id` int NULL DEFAULT 1 COMMENT '所属餐厅ID',
  PRIMARY KEY (`dish_id`) USING BTREE,
  INDEX `idx_category`(`category` ASC) USING BTREE,
  INDEX `idx_recommend`(`recommend` ASC) USING BTREE,
  INDEX `idx_stock_status`(`stock_status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '菜品信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES (1, '宫保鸡丁', '主食', 28.00, '经典川菜，鸡肉鲜嫩，花生香脆，口感丰富', 'https://picsum.photos/id/292/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (2, '麻婆豆腐', '主食', 18.00, '四川传统名菜，豆腐嫩滑，麻辣鲜香', 'https://picsum.photos/id/293/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (3, '糖醋里脊', '主食', 32.00, '外酥内嫩，酸甜可口，开胃下饭', 'https://picsum.photos/id/294/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (4, '红烧肉', '主食', 38.00, '肥而不腻，入口即化，经典美味', 'https://picsum.photos/id/295/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (5, '清蒸鲈鱼', '荤菜', 68.00, '新鲜鲈鱼清蒸，肉质鲜美，营养丰富', 'https://picsum.photos/id/296/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (6, '白切鸡', '荤菜', 45.00, '广东名菜，鸡肉嫩滑，配以姜葱酱料', 'https://picsum.photos/id/297/300/200', 0, 1, 1);
INSERT INTO `dish` VALUES (7, '蒜蓉粉丝蒸扇贝', '荤菜', 58.00, '扇贝鲜美，粉丝入味，蒜香浓郁', 'https://picsum.photos/id/298/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (8, '椒盐虾', '荤菜', 52.00, '虾肉鲜嫩，椒盐香脆，下酒佳品', 'https://picsum.photos/id/299/300/200', 0, 1, 1);
INSERT INTO `dish` VALUES (9, '上汤娃娃菜', '素菜', 16.00, '娃娃菜嫩绿，上汤鲜美，清淡爽口', 'https://picsum.photos/id/300/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (10, '蒜蓉西兰花', '素菜', 14.00, '西兰花翠绿，蒜香浓郁，营养健康', 'https://picsum.photos/id/301/300/200', 0, 1, 1);
INSERT INTO `dish` VALUES (11, '干煸四季豆', '素菜', 18.00, '四季豆干煸，香辣可口，下饭神器', 'https://picsum.photos/id/302/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (12, '醋溜土豆丝', '素菜', 12.00, '土豆丝爽脆，醋香开胃，经典小菜', 'https://picsum.photos/id/303/300/200', 0, 1, 1);
INSERT INTO `dish` VALUES (13, '紫菜蛋花汤', '汤品', 8.00, '紫菜鲜美，蛋花嫩滑，营养丰富', 'https://picsum.photos/id/304/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (14, '番茄蛋汤', '汤品', 10.00, '番茄酸甜，蛋花嫩滑，开胃暖身', 'https://picsum.photos/id/305/300/200', 0, 1, 1);
INSERT INTO `dish` VALUES (15, '冬瓜排骨汤', '汤品', 25.00, '冬瓜清甜，排骨鲜美，滋补养生', 'https://picsum.photos/id/306/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (16, '玉米排骨汤', '汤品', 28.00, '玉米香甜，排骨鲜美，营养美味', 'https://picsum.photos/id/307/300/200', 0, 1, 1);
INSERT INTO `dish` VALUES (17, '口水鸡', '凉菜', 35.00, '鸡肉嫩滑，麻辣鲜香，开胃爽口', 'https://picsum.photos/id/308/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (18, '凉拌黄瓜', '凉菜', 8.00, '黄瓜爽脆，蒜香浓郁，清爽开胃', 'https://picsum.photos/id/309/300/200', 0, 1, 1);
INSERT INTO `dish` VALUES (19, '凉拌木耳', '凉菜', 12.00, '木耳爽脆，酸辣可口，营养丰富', 'https://picsum.photos/id/310/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (20, '凉拌海带丝', '凉菜', 10.00, '海带丝爽滑，酸辣开胃，健康美味', 'https://picsum.photos/id/311/300/200', 0, 1, 1);
INSERT INTO `dish` VALUES (21, '红豆沙汤圆', '甜品', 15.00, '汤圆软糯，红豆香甜，温暖甜蜜', 'https://picsum.photos/id/312/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (22, '银耳莲子羹', '甜品', 18.00, '银耳滑嫩，莲子香甜，滋补养颜', 'https://picsum.photos/id/313/300/200', 0, 1, 1);
INSERT INTO `dish` VALUES (23, '芒果布丁', '甜品', 12.00, '布丁滑嫩，芒果香甜，清新爽口', 'https://picsum.photos/id/314/300/200', 1, 1, 1);
INSERT INTO `dish` VALUES (24, '双皮奶', '甜品', 16.00, '奶香浓郁，口感细腻，经典甜品', 'https://picsum.photos/id/315/300/200', 0, 1, 1);

-- ----------------------------
-- Table structure for order_dish
-- ----------------------------
DROP TABLE IF EXISTS `order_dish`;
CREATE TABLE `order_dish`  (
  `order_dish_id` int NOT NULL AUTO_INCREMENT COMMENT '订单菜品ID',
  `order_id` int NOT NULL COMMENT '订单ID',
  `dish_id` int NOT NULL COMMENT '菜品ID',
  `quantity` int NULL DEFAULT 1 COMMENT '数量',
  `unit_price` decimal(10, 2) NOT NULL COMMENT '单价',
  `subtotal` decimal(10, 2) NOT NULL COMMENT '小计',
  PRIMARY KEY (`order_dish_id`) USING BTREE,
  INDEX `idx_order_id`(`order_id` ASC) USING BTREE,
  INDEX `idx_dish_id`(`dish_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单菜品关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_dish
-- ----------------------------

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `table_id` int NULL DEFAULT NULL COMMENT '桌位ID',
  `order_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `reservation_time` datetime NULL DEFAULT NULL COMMENT '预订时间',
  `people_count` int NULL DEFAULT 1 COMMENT '用餐人数',
  `total_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '订单总金额',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'pending' COMMENT '订单状态（pending/confirmed/completed/cancelled）',
  `remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '备注信息',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (1, 1, 1, '2025-08-17 17:53:30', '2025-08-17 17:53:31', 1, 0.00, 'cancelled', '');
INSERT INTO `order_info` VALUES (2, 1, 2, '2025-08-17 17:53:41', '2025-08-17 17:53:42', 1, 0.00, 'cancelled', '');
INSERT INTO `order_info` VALUES (3, 1, 3, '2025-08-17 17:53:46', '2025-08-17 17:53:47', 1, 0.00, 'cancelled', '');
INSERT INTO `order_info` VALUES (4, 1, 4, '2025-08-17 19:20:51', '2025-08-17 19:20:52', 1, 0.00, 'cancelled', '');
INSERT INTO `order_info` VALUES (5, 1, 6, '2025-08-17 19:21:45', '2025-08-17 19:21:46', 1, 0.00, 'cancelled', '');
INSERT INTO `order_info` VALUES (6, 1, 3, '2025-08-17 20:00:00', '2025-08-17 20:00:00', 1, 0.00, 'pending', '');
INSERT INTO `order_info` VALUES (7, 1, 1, '2025-08-18 09:13:01', '2025-08-18 09:13:02', 1, 0.00, 'cancelled', '');
INSERT INTO `order_info` VALUES (8, 1, 2, '2025-08-22 18:18:46', '2025-08-22 18:18:47', 1, 0.00, 'pending', '');

-- ----------------------------
-- Table structure for restaurant_info
-- ----------------------------
DROP TABLE IF EXISTS `restaurant_info`;
CREATE TABLE `restaurant_info`  (
  `restaurant_id` int NOT NULL AUTO_INCREMENT COMMENT '餐厅ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '餐厅名称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '餐厅地址',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '餐厅描述',
  `business_hours` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '营业时间',
  `rating` decimal(2, 1) NULL DEFAULT 0.0 COMMENT '评分',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '餐厅图片URL',
  `status` int NULL DEFAULT 1 COMMENT '状态（0:关闭 1:营业）',
  PRIMARY KEY (`restaurant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '餐厅信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of restaurant_info
-- ----------------------------
INSERT INTO `restaurant_info` VALUES (1, '王中王餐厅', '北京市朝阳区建国路88号', '010-12345678', '王中王餐厅创立于1998年，是一家集传统与创新于一体的高档中餐厅。我们致力于为顾客提供最优质的食材和最精湛的烹饪技艺，让每一位顾客都能品尝到正宗的中华美食。', '10:00 - 22:00', 4.5, 'https://picsum.photos/id/431/600/400', 1);

-- ----------------------------
-- Table structure for table_info
-- ----------------------------
DROP TABLE IF EXISTS `table_info`;
CREATE TABLE `table_info`  (
  `table_id` int NOT NULL AUTO_INCREMENT COMMENT '桌位ID',
  `table_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '桌位号',
  `capacity` int NULL DEFAULT 4 COMMENT '容纳人数',
  `table_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'standard' COMMENT '桌位类型（window/standard/private）',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'available' COMMENT '状态（available/reserved/occupied）',
  `restaurant_id` int NULL DEFAULT 1 COMMENT '所属餐厅ID',
  PRIMARY KEY (`table_id`) USING BTREE,
  UNIQUE INDEX `uk_table_number`(`table_number` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '桌位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of table_info
-- ----------------------------
INSERT INTO `table_info` VALUES (1, 'A1', 2, 'window', 'available', 1);
INSERT INTO `table_info` VALUES (2, 'A2', 4, 'window', 'reserved', 1);
INSERT INTO `table_info` VALUES (3, 'A3', 6, 'window', 'reserved', 1);
INSERT INTO `table_info` VALUES (4, 'B1', 2, 'standard', 'available', 1);
INSERT INTO `table_info` VALUES (5, 'B2', 4, 'standard', 'available', 1);
INSERT INTO `table_info` VALUES (6, 'B3', 8, 'standard', 'available', 1);
INSERT INTO `table_info` VALUES (7, 'C1', 4, 'private', 'available', 1);
INSERT INTO `table_info` VALUES (8, 'C2', 6, 'private', 'available', 1);
INSERT INTO `table_info` VALUES (9, 'C3', 10, 'private', 'available', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号（注册账号）',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '加密存储的密码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像URL',
  `register_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `status` int NULL DEFAULT 1 COMMENT '用户状态（0:禁用 1:正常 2:管理员）',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_email`(`email` ASC) USING BTREE,
  UNIQUE INDEX `uk_phone`(`phone` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'tyq', '123', '123@123', '123', '11', '1970-01-01 08:00:00', 1);
INSERT INTO `user` VALUES (2, 'ttt', '132', '1234@1234', '1234', '11', '2025-08-17 19:32:48', 2);

SET FOREIGN_KEY_CHECKS = 1;
