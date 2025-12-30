-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: lyc
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `category_id` bigint NOT NULL COMMENT '二级分类ID',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `stock` int DEFAULT '0' COMMENT '库存数量',
  `description` text COLLATE utf8mb4_unicode_ci COMMENT '商品描述',
  `image_url` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品图片URL',
  `status` tinyint DEFAULT '1' COMMENT '状态：1-上架，0-下架',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `product_category_level2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'iPhone 15 Pro',1,7999.00,50,'最新款iPhone 15 Pro，性能强劲','http://example.com/iphone15.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(2,'华为Mate60',1,6999.00,30,'华为最新旗舰手机','http://example.com/mate60.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(3,'MacBook Pro 14寸',2,15999.00,20,'苹果MacBook Pro，专业级笔记本','http://example.com/macbook.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(4,'戴尔XPS 13',2,8999.00,25,'戴尔高端轻薄笔记本','http://example.com/dellxps.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(5,'iPad Air',3,4399.00,40,'苹果iPad Air平板电脑','http://example.com/ipadair.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(6,'小米平板6',3,2499.00,35,'小米最新平板电脑','http://example.com/mipad6.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(7,'男士休闲衬衫',4,199.00,100,'舒适透气的男士休闲衬衫','http://example.com/mensshirt.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(8,'女士连衣裙',5,299.00,80,'时尚优雅的女士连衣裙','http://example.com/womendress.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(9,'运动跑鞋',6,599.00,60,'专业运动跑鞋，舒适透气','http://example.com/runningshoes.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(10,'时尚背包',7,199.00,70,'时尚潮流背包，大容量','http://example.com/backpack.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(11,'不粘锅',8,129.00,90,'优质不粘锅，健康烹饪','http://example.com/nonstickpan.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(12,'四件套床品',9,399.00,55,'舒适四件套，亲肤面料','http://example.com/bedding.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(13,'洗衣液',10,29.90,200,'高效去污洗衣液','http://example.com/detergent.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(14,'薯片大礼包',11,29.90,150,'多种口味薯片大礼包','http://example.com/chips.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(15,'可口可乐',12,3.50,300,'经典可口可乐','http://example.com/cocacola.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(16,'青岛啤酒',13,5.00,250,'经典青岛啤酒','http://example.com/tsingtao.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(17,'帐篷',14,299.00,25,'户外露营帐篷','http://example.com/tent.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(18,'哑铃套装',15,199.00,40,'家庭健身哑铃套装','http://example.com/dumbbell.jpg',1,'2025-12-29 22:32:55','2025-12-29 22:32:55');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category_level1`
--

DROP TABLE IF EXISTS `product_category_level1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category_level1` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称',
  `description` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分类描述',
  `sort_order` int DEFAULT '0' COMMENT '排序',
  `status` tinyint DEFAULT '1' COMMENT '状态：1-启用，0-禁用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品一级分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category_level1`
--

LOCK TABLES `product_category_level1` WRITE;
/*!40000 ALTER TABLE `product_category_level1` DISABLE KEYS */;
INSERT INTO `product_category_level1` VALUES (1,'电子产品','各类电子产品',1,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(2,'服装鞋包','服装、鞋类、箱包等',2,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(3,'家居用品','家居、生活用品',3,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(4,'食品饮料','各类食品和饮料',4,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(5,'运动户外','运动器材、户外用品',5,1,'2025-12-29 22:32:55','2025-12-29 22:32:55');
/*!40000 ALTER TABLE `product_category_level1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category_level2`
--

DROP TABLE IF EXISTS `product_category_level2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category_level2` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称',
  `parent_id` bigint NOT NULL COMMENT '父分类ID（关联一级分类）',
  `description` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分类描述',
  `sort_order` int DEFAULT '0' COMMENT '排序',
  `status` tinyint DEFAULT '1' COMMENT '状态：1-启用，0-禁用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  CONSTRAINT `fk_category_level2_parent` FOREIGN KEY (`parent_id`) REFERENCES `product_category_level1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品二级分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category_level2`
--

LOCK TABLES `product_category_level2` WRITE;
/*!40000 ALTER TABLE `product_category_level2` DISABLE KEYS */;
INSERT INTO `product_category_level2` VALUES (1,'手机',1,'各类手机',1,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(2,'电脑',1,'台式机、笔记本电脑',2,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(3,'平板',1,'各类平板电脑',3,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(4,'男装',2,'男士服装',1,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(5,'女装',2,'女士服装',2,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(6,'鞋类',2,'各类鞋子',3,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(7,'箱包',2,'各类箱包',4,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(8,'厨房用品',3,'厨房用具',1,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(9,'卧室用品',3,'卧室家具用品',2,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(10,'清洁用品',3,'清洁用品',3,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(11,'零食',4,'各类零食',1,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(12,'饮料',4,'各类饮料',2,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(13,'酒类',4,'各类酒品',3,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(14,'户外装备',5,'户外运动装备',1,1,'2025-12-29 22:32:55','2025-12-29 22:32:55'),(15,'健身器材',5,'健身器材',2,1,'2025-12-29 22:32:55','2025-12-29 22:32:55');
/*!40000 ALTER TABLE `product_category_level2` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-29 22:52:26
