-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: smartsearch
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `deletemessage`
--

DROP TABLE IF EXISTS `deletemessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deletemessage` (
  `isDeleteMessage` int NOT NULL AUTO_INCREMENT,
  `deleteContent` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`isDeleteMessage`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deletemessage`
--

LOCK TABLES `deletemessage` WRITE;
/*!40000 ALTER TABLE `deletemessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `deletemessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editmessage`
--

DROP TABLE IF EXISTS `editmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editmessage` (
  `idEditMessage` int NOT NULL AUTO_INCREMENT,
  `EditContent` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`idEditMessage`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editmessage`
--

LOCK TABLES `editmessage` WRITE;
/*!40000 ALTER TABLE `editmessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `editmessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logs` (
  `idLogs` int NOT NULL AUTO_INCREMENT,
  `idMessage` int DEFAULT NULL,
  `dataCreate` time DEFAULT NULL,
  `dataEditing` time DEFAULT NULL,
  `dateDeliting` time DEFAULT NULL,
  PRIMARY KEY (`idLogs`),
  KEY `logs_message_FK` (`idMessage`),
  CONSTRAINT `logs_message_FK` FOREIGN KEY (`idMessage`) REFERENCES `message` (`idMessage`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mentions`
--

DROP TABLE IF EXISTS `mentions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mentions` (
  `idMentions` int NOT NULL AUTO_INCREMENT,
  `idMessage` int DEFAULT NULL,
  `idUserMentions` int DEFAULT NULL,
  PRIMARY KEY (`idMentions`),
  KEY `mentions_users_FK` (`idUserMentions`),
  KEY `mentions_message_FK` (`idMessage`),
  CONSTRAINT `mentions_message_FK` FOREIGN KEY (`idMessage`) REFERENCES `message` (`idMessage`),
  CONSTRAINT `mentions_users_FK` FOREIGN KEY (`idUserMentions`) REFERENCES `users` (`isUsers`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mentions`
--

LOCK TABLES `mentions` WRITE;
/*!40000 ALTER TABLE `mentions` DISABLE KEYS */;
/*!40000 ALTER TABLE `mentions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `idMessage` int NOT NULL AUTO_INCREMENT,
  `idRoom` int DEFAULT NULL,
  `idUser` int DEFAULT NULL,
  `content` varchar(800) DEFAULT NULL,
  `urlFile` varchar(400) DEFAULT NULL,
  `isEditing` tinyint(1) DEFAULT NULL,
  `isDeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idMessage`),
  KEY `message_room_FK` (`idRoom`),
  KEY `message_users_FK` (`idUser`),
  CONSTRAINT `message_room_FK` FOREIGN KEY (`idRoom`) REFERENCES `room` (`idRoom`),
  CONSTRAINT `message_users_FK` FOREIGN KEY (`idUser`) REFERENCES `users` (`isUsers`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (2,1,1,'Сообщение2','ссылка класс блин',1,0),(3,2,1,'пупуруп','сылка',0,0),(4,2,1,'второе сообщение',NULL,0,0),(5,2,2,'третье',NULL,NULL,NULL),(6,2,1,'ffff','',0,0),(7,2,1,'ssss','',0,0),(8,2,1,'scscscs','',0,0),(9,2,1,'dddd','',0,0),(10,2,1,'new','',0,0),(11,2,1,'Разраб уснул я нейросеть','',0,0),(12,2,1,'привет','',0,0);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `idRole` int NOT NULL AUTO_INCREMENT,
  `nameRole` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `idRoom` int NOT NULL AUTO_INCREMENT,
  `nameRoom` varchar(100) DEFAULT NULL,
  `DateCreateRoom` date DEFAULT NULL,
  PRIMARY KEY (`idRoom`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'Тим спирит','2025-02-28'),(2,'Дримтип','2020-09-01'),(3,'bubububl','2025-03-01'),(4,'lel','2025-03-01'),(5,'Эльбрус','2025-03-01'),(6,'пес','2025-03-01'),(7,'Абоба','2025-03-01'),(8,'fffffff','2025-03-01');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timeworks`
--

DROP TABLE IF EXISTS `timeworks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timeworks` (
  `idTimeWorks` int NOT NULL AUTO_INCREMENT,
  `idUser` int DEFAULT NULL,
  `startWorks` time DEFAULT NULL,
  `stopWorkTime` time DEFAULT NULL,
  PRIMARY KEY (`idTimeWorks`),
  KEY `timeworks_users_FK` (`idUser`),
  CONSTRAINT `timeworks_users_FK` FOREIGN KEY (`idUser`) REFERENCES `users` (`isUsers`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timeworks`
--

LOCK TABLES `timeworks` WRITE;
/*!40000 ALTER TABLE `timeworks` DISABLE KEYS */;
/*!40000 ALTER TABLE `timeworks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userroom`
--

DROP TABLE IF EXISTS `userroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userroom` (
  `idUserRole` int NOT NULL AUTO_INCREMENT,
  `idUser` int DEFAULT NULL,
  `idRole` int DEFAULT NULL,
  `idRoom` int DEFAULT NULL,
  PRIMARY KEY (`idUserRole`),
  KEY `userroom_users_FK` (`idUser`),
  KEY `userroom_room_FK` (`idRoom`),
  KEY `userroom_role_FK` (`idRole`),
  CONSTRAINT `userroom_role_FK` FOREIGN KEY (`idRole`) REFERENCES `role` (`idRole`),
  CONSTRAINT `userroom_room_FK` FOREIGN KEY (`idRoom`) REFERENCES `room` (`idRoom`),
  CONSTRAINT `userroom_users_FK` FOREIGN KEY (`idUser`) REFERENCES `users` (`isUsers`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userroom`
--

LOCK TABLES `userroom` WRITE;
/*!40000 ALTER TABLE `userroom` DISABLE KEYS */;
INSERT INTO `userroom` VALUES (2,1,2,2),(3,1,2,1),(4,1,1,3),(5,1,1,4),(6,6,1,5),(7,6,1,6),(8,1,2,6),(9,2,2,6),(10,1,1,7),(11,1,2,7),(12,2,2,7),(13,6,2,7),(14,1,1,8),(15,1,2,8),(16,2,2,8),(17,3,2,8);
/*!40000 ALTER TABLE `userroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `isUsers` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) DEFAULT NULL,
  `userTelegramId` varchar(100) DEFAULT NULL,
  `idWorkRole` int DEFAULT NULL,
  PRIMARY KEY (`isUsers`),
  KEY `users_workrole_FK` (`idWorkRole`),
  CONSTRAINT `users_workrole_FK` FOREIGN KEY (`idWorkRole`) REFERENCES `workrole` (`idWorkRole`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'nanana','na',1),(2,'Lolka','Lolkaid',2),(3,'ысысысы','ысысысыid',3),(4,'ысысысыаа','ысысысыааid',1),(5,'машулька','машулькаid',2),(6,'мишутка','мишуткаid',3),(7,'dsdsds','dsdsdsid',1),(8,'ddfddc','ddfddcid',2),(9,'yy','yyid',3),(10,'смешарик','смешарикid',1),(11,'милка','милкаid',2),(12,'Lalka','Lalkaid',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workrole`
--

DROP TABLE IF EXISTS `workrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workrole` (
  `idWorkRole` int NOT NULL AUTO_INCREMENT,
  `nameWorkRole` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idWorkRole`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workrole`
--

LOCK TABLES `workrole` WRITE;
/*!40000 ALTER TABLE `workrole` DISABLE KEYS */;
INSERT INTO `workrole` VALUES (1,'Разработчик'),(2,'Заказчик'),(3,'Тимлид');
/*!40000 ALTER TABLE `workrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'smartsearch'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-01 19:20:46
