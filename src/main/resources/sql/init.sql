-- 餐厅管理系统数据库初始化脚本

-- 创建数据库（如果不存在）
-- CREATE DATABASE IF NOT EXISTS cantin_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- USE cantin_db;

-- 用户表
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号（注册账号）',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `password` varchar(255) DEFAULT NULL COMMENT '加密存储的密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `status` int DEFAULT '1' COMMENT '用户状态（0:禁用 1:正常）',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_email` (`email`),
  UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户表';

-- 餐厅信息表
CREATE TABLE IF NOT EXISTS `restaurant_info` (
  `restaurant_id` int NOT NULL AUTO_INCREMENT COMMENT '餐厅ID',
  `name` varchar(100) NOT NULL COMMENT '餐厅名称',
  `address` varchar(255) DEFAULT NULL COMMENT '餐厅地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `description` text COMMENT '餐厅描述',
  `business_hours` varchar(100) DEFAULT NULL COMMENT '营业时间',
  `rating` decimal(2,1) DEFAULT '0.0' COMMENT '评分',
  `image_url` varchar(255) DEFAULT NULL COMMENT '餐厅图片URL',
  `status` int DEFAULT '1' COMMENT '状态（0:关闭 1:营业）',
  PRIMARY KEY (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='餐厅信息表';

-- 菜品信息表
CREATE TABLE IF NOT EXISTS `dish` (
  `dish_id` int NOT NULL AUTO_INCREMENT COMMENT '菜品ID',
  `name` varchar(100) NOT NULL COMMENT '菜品名称',
  `category` varchar(50) DEFAULT NULL COMMENT '分类（主食/荤菜/素菜等）',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `description` text COMMENT '菜品描述',
  `image_url` varchar(255) DEFAULT NULL COMMENT '菜品图片URL',
  `recommend` int DEFAULT '0' COMMENT '是否推荐（0:否 1:是）',
  `stock_status` int DEFAULT '1' COMMENT '库存状态（0:缺货 1:正常）',
  `restaurant_id` int DEFAULT '1' COMMENT '所属餐厅ID',
  PRIMARY KEY (`dish_id`),
  KEY `idx_category` (`category`),
  KEY `idx_recommend` (`recommend`),
  KEY `idx_stock_status` (`stock_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜品信息表';

-- 桌位信息表
CREATE TABLE IF NOT EXISTS `table_info` (
  `table_id` int NOT NULL AUTO_INCREMENT COMMENT '桌位ID',
  `table_number` varchar(20) NOT NULL COMMENT '桌位号',
  `capacity` int DEFAULT '4' COMMENT '容纳人数',
  `table_type` varchar(20) DEFAULT 'standard' COMMENT '桌位类型（window/standard/private）',
  `status` varchar(20) DEFAULT 'available' COMMENT '状态（available/reserved/occupied）',
  `restaurant_id` int DEFAULT '1' COMMENT '所属餐厅ID',
  PRIMARY KEY (`table_id`),
  UNIQUE KEY `uk_table_number` (`table_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='桌位信息表';

-- 订单信息表
CREATE TABLE IF NOT EXISTS `order_info` (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `table_id` int DEFAULT NULL COMMENT '桌位ID',
  `order_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `reservation_time` datetime DEFAULT NULL COMMENT '预订时间',
  `people_count` int DEFAULT '1' COMMENT '用餐人数',
  `total_amount` decimal(10,2) DEFAULT '0.00' COMMENT '订单总金额',
  `status` varchar(20) DEFAULT 'pending' COMMENT '订单状态（pending/confirmed/completed/cancelled）',
  `remarks` text COMMENT '备注信息',
  PRIMARY KEY (`order_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单信息表';

-- 订单菜品关联表
CREATE TABLE IF NOT EXISTS `order_dish` (
  `order_dish_id` int NOT NULL AUTO_INCREMENT COMMENT '订单菜品ID',
  `order_id` int NOT NULL COMMENT '订单ID',
  `dish_id` int NOT NULL COMMENT '菜品ID',
  `quantity` int DEFAULT '1' COMMENT '数量',
  `unit_price` decimal(10,2) NOT NULL COMMENT '单价',
  `subtotal` decimal(10,2) NOT NULL COMMENT '小计',
  PRIMARY KEY (`order_dish_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_dish_id` (`dish_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单菜品关联表';

-- 插入初始数据

-- 插入餐厅信息
INSERT INTO `restaurant_info` (`restaurant_id`, `name`, `address`, `phone`, `description`, `business_hours`, `rating`, `image_url`, `status`) VALUES
(1, '王中王餐厅', '北京市朝阳区建国路88号', '010-12345678', '王中王餐厅创立于1998年，是一家集传统与创新于一体的高档中餐厅。我们致力于为顾客提供最优质的食材和最精湛的烹饪技艺，让每一位顾客都能品尝到正宗的中华美食。', '10:00 - 22:00', 4.5, 'https://picsum.photos/id/431/600/400', 1);

-- 插入桌位信息
INSERT INTO `table_info` (`table_id`, `table_number`, `capacity`, `table_type`, `status`, `restaurant_id`) VALUES
(1, 'A1', 2, 'window', 'available', 1),
(2, 'A2', 4, 'window', 'available', 1),
(3, 'A3', 6, 'window', 'available', 1),
(4, 'B1', 2, 'standard', 'available', 1),
(5, 'B2', 4, 'standard', 'available', 1),
(6, 'B3', 8, 'standard', 'available', 1),
(7, 'C1', 4, 'private', 'available', 1),
(8, 'C2', 6, 'private', 'available', 1),
(9, 'C3', 10, 'private', 'available', 1);

-- 菜品数据将通过Java代码的DataInitializer自动插入

