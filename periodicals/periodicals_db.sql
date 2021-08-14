-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: periodicals_db
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `user_id` bigint NOT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK7m8ru44m93ukyb61dfxw0apf6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,0.00),(2,817.29),(11,10.00),(12,0.00),(13,0.00),(14,0.00);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (17),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodical`
--

DROP TABLE IF EXISTS `periodical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `periodical` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `topic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f4udydauc4tumpkiadx2qm9e8` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodical`
--

LOCK TABLES `periodical` WRITE;
/*!40000 ALTER TABLE `periodical` DISABLE KEYS */;
INSERT INTO `periodical` VALUES (1,113.00,'Forbes','economics'),(2,170.50,'Vogue','fashion'),(3,120.00,'National Geographic','science'),(4,126.50,'Science ','science'),(5,123.60,'The New York Times','news'),(6,156.60,'Times','news'),(7,185.00,'Рибалка','leisure'),(8,231.00,'Садівник','leisure');
/*!40000 ALTER TABLE `periodical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscription` (
  `periodical_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `last_payment_date_time` datetime DEFAULT NULL,
  `next_payment_date_time` datetime DEFAULT NULL,
  `payment_period_in_days` int NOT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`periodical_id`,`user_id`),
  KEY `FK8l1goo02px4ye49xd7wgogxg6` (`user_id`),
  CONSTRAINT `FK8l1goo02px4ye49xd7wgogxg6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmwyufr71uh9581y2reovt5egp` FOREIGN KEY (`periodical_id`) REFERENCES `periodical` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
INSERT INTO `subscription` VALUES (1,2,'2021-08-11 16:14:57','2021-09-10 16:14:57',30,_binary ''),(1,11,'2021-08-11 15:38:31','2021-09-10 15:38:31',30,_binary ''),(2,2,'2021-08-11 07:01:55','2021-09-10 07:01:55',30,_binary ''),(3,2,'2021-08-11 16:09:15','2021-09-10 16:09:15',30,_binary ''),(4,2,'2021-08-11 12:44:41','2021-09-10 12:44:41',30,_binary ''),(7,11,'2021-08-11 15:38:54','2021-09-10 15:38:54',30,_binary ''),(8,2,'2021-08-11 13:30:49','2021-09-10 13:30:49',30,_binary '');
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_non_expired` tinyint(1) DEFAULT '1',
  `account_non_locked` tinyint(1) DEFAULT '1',
  `authority` int DEFAULT NULL,
  `credentials_non_expired` tinyint(1) DEFAULT '1',
  `email` varchar(255) NOT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `language` varchar(3) DEFAULT 'UA',
  `name` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,1,0,1,'sherlock@221.uk',1,'UA','Sherlock','$2a$10$Dgta0j9EeJmAvqZUdI.ZNOgj8xijzst8SO2f0MVdLnYwz2iCZOvwe','Holmes','sherlock'),(2,1,1,1,1,'john@in.ua',1,'UA','John','$2a$10$k/clw/VcqVvt7NY4CpwjB.2/0gDgOu5JCDytGVxx8tHsn3JCgGeUu','Smith','admin'),(11,1,1,0,1,'asmith@in.ua',1,'UA','Anna','$2a$10$H0j4iMY8Njm4rAL14FUYx.ZXq.AxdAhpw/uriOt89w1ZxmkcWu.4q','Smith','anna'),(12,1,1,0,1,'mary@in.ua',1,'UA','Mary','$2a$10$Hdo542k.SobpkfvYmHH/4.F6RA9fCw6OqphaW6ZyU.yRAaJMf8Mt2','Watson','mary'),(13,1,1,0,1,'joan@ukr.net',1,'UA','Joan','$2a$10$LcYi47M8k1S8yShBbcHG6.zcRaugQ5JpFuYOyvjrTLP7supfaJ0K6','Smith','joan'),(14,1,1,0,1,'mark@in.uk',1,'UA','Mark','$2a$10$3FgQsuRLCpWWjHpoWz4HGe4moTK1OheGg413.VAtyh4E4GrzHiPie','Holmes','mark');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-14 13:20:17
