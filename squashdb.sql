CREATE DATABASE  IF NOT EXISTS `squashdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `squashdb`;
-- MySQL dump 10.13  Distrib 8.0.33, for macos13 (arm64)
--
-- Host: localhost    Database: squashdb
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `matches`
--

DROP TABLE IF EXISTS `matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matches` (
  `id` int NOT NULL AUTO_INCREMENT,
  `player1_email` varchar(256) NOT NULL,
  `player2_email` varchar(256) NOT NULL,
  `place_id` int NOT NULL,
  `score_p1` int NOT NULL,
  `score_p2` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `player1_email_fk_idx` (`player1_email`),
  KEY `player2_email_fk_idx` (`player2_email`),
  KEY `place_fk_idx` (`place_id`),
  CONSTRAINT `place_fk` FOREIGN KEY (`place_id`) REFERENCES `squash_places` (`id`),
  CONSTRAINT `player1_email_fk` FOREIGN KEY (`player1_email`) REFERENCES `players` (`email`),
  CONSTRAINT `player2_email_fk` FOREIGN KEY (`player2_email`) REFERENCES `players` (`email`),
  CONSTRAINT `players_not_the_same` CHECK ((`player1_email` <> `player2_email`))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matches`
--

LOCK TABLES `matches` WRITE;
/*!40000 ALTER TABLE `matches` DISABLE KEYS */;
INSERT INTO `matches` VALUES (1,'david.remetei@gmail.com','jozzy123@freemail.hu',98,13,12,'2023-03-02'),(2,'david.remetei@gmail.com','zsoltszilardkiss04@gmail.com',24,25,0,'2023-07-23'),(3,'zsoltszilardkiss04@gmail.com','jozzy123@freemail.hu',11,14,14,'2023-05-12');
/*!40000 ALTER TABLE `matches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players` (
  `username` varchar(256) NOT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `role_id` int NOT NULL,
  `activated` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `roleid_fk_idx` (`role_id`),
  CONSTRAINT `roleid_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES ('admin','admin@admin.com','admin',1,1),('DemoDávid','david.remetei@gmail.com','somegeneratedpassword',2,1),('DemoJózsef','jozzy123@freemail.hu','jozzypass',2,1),('DemoSzilárd','zsoltszilardkiss04@gmail.com','jelszo123',2,1);
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'PLAYER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `squash_places`
--

DROP TABLE IF EXISTS `squash_places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `squash_places` (
  `id` int NOT NULL AUTO_INCREMENT,
  `place_name` varchar(256) NOT NULL,
  `address` varchar(256) NOT NULL,
  `rent_fee_per_hour_huf` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `squash_places`
--

LOCK TABLES `squash_places` WRITE;
/*!40000 ALTER TABLE `squash_places` DISABLE KEYS */;
INSERT INTO `squash_places` VALUES (11,'Wonderful Squash','Nagykőrös',1500),(24,'Health & Sport','Szeged',2230),(98,'Magic Movement','Budapest',5000);
/*!40000 ALTER TABLE `squash_places` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18 19:47:43
