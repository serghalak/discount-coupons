
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: db
-- ------------------------------------------------------
-- Server version	8.0.22

use db;

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
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,NULL,'Travel'),(2,NULL,'Food'),(3,NULL,'Beauty'),(4,NULL,'Health'),(5,NULL,'Sport'),(7,NULL,'Training'),(8,NULL,'Other');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Szczecin',2),(2,'Warszawa',2),(3,'Bialystok',2),(4,'Poznan',2),(5,'Kiev',1),(6,'Odessa',1),(7,'Lviv',1),(8,'Vinnitsa',1),(9,'Kharkiv',1),(10,'Dresden',7),(11,'Dresden',8),(12,'Dresden',8),(13,'Tashkent',6),(14,'Brest',4),(15,'Gomel',4),(16,'Grodno',4),(17,'Minsk',4),(18,'Vitebsk',4);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Ukraine'),(2,'Poland'),(3,'Lithuania'),(4,'Belarus'),(5,'Russia'),(6,'Uzbekistan'),(7,'Germany'),(8,'United States'),(9,'Georgia');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'2021-08-10','2021-08-25','Discount 15%','sport@maill.com',_binary '\0',50,'Sneakers','+122-33-333-33',1500.00,12,1,'NEW'),(2,'2021-05-12','2021-08-04','Discount 10%','home@maill.com',_binary '\0',1000,'Blanket','+155-55-555-55',1584.00,0,1,'NEW');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `event_location`
--

LOCK TABLES `event_location` WRITE;
/*!40000 ALTER TABLE `event_location` DISABLE KEYS */;
INSERT INTO `event_location` VALUES (1,12),(2,13);
/*!40000 ALTER TABLE `event_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `event_product`
--

LOCK TABLES `event_product` WRITE;
/*!40000 ALTER TABLE `event_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,0,0,'15','Yaroslaviv',1),(2,0,0,'27B','Avenue Science',1),(3,0,0,'16','Zaliznyaka',1),(4,0,0,'8','Elisavetinskaya',1),(5,0,0,'103','Bratslav',1),(6,0,0,'15','Moscow Street',4),(7,0,0,'27B','Lenin Avenue',4),(8,0,0,'1','street Sanatorna',4),(9,0,0,'3','Akadkmika Kuprevycha',4),(10,0,0,'156','Prytytskoho',4),(11,0,0,'16','Communist',4),(12,0,0,'226Рђ','Kulparkivska',1),(13,0,0,'1Рђ','Sports Square',1);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Stylish childrens sneakers, are made of the \n							 combined materials. Textiles inside. Wide rubber \n							sole for maximum comfort. Decorated with company logos.','https://megasport.ua/ua/product/md-valiant_cn8558-400/?gclid=Cj0KCQjwweyFBhDvARIsAA67M71V4J5-JQ12P3i9znMwWum1PKOvIj56OQja5ACeIACqIz6CZn5UYswaAkpEEALw_wcB','Sneakers NIKE MD VALIANT - 127661',5,1),(2,'Something about that','https://www.zarahome.com/ua','BLANKET WITH PRINT \"DREAMS\"',8,2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `saved_event`
--

LOCK TABLES `saved_event` WRITE;
/*!40000 ALTER TABLE `saved_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `saved_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'boris@gmail.com','Boris',_binary '','ChackNoris','$2y$08$n4kBbalGu4spd84Lp05FweRWwNdAaRnLGY1wCDP5e4IFRsgy5b5pi','admin',3,'MODERATOR'),(2,'oleg@gmail.com','Oleg',_binary '','Voron','$2y$08$n4kBbalGu4spd84Lp05FweRWwNdAaRnLGY1wCDP5e4IFRsgy5b5pi','user#1',3,'USER'),(3,'igor@gmail.com','Igor',_binary '','Polevoi','$2y$08$n4kBbalGu4spd84Lp05FweRWwNdAaRnLGY1wCDP5e4IFRsgy5b5pi','user#2',3,'USER'),(4,'svitlana@gmail.com','Svitlana',_binary '','Kukushkina','$2y$08$n4kBbalGu4spd84Lp05FweRWwNdAaRnLGY1wCDP5e4IFRsgy5b5pi','moderator',3,'ADMIN');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_order`
--

LOCK TABLES `user_order` WRITE;
/*!40000 ALTER TABLE `user_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
INSERT INTO `vendor` VALUES (1,'all for sports','sport@maill.com','Megasport','+122-33-333-33'),(2,'all for home','home@maill.com','Zara Home','+155-55-555-55');
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sandbox_test'
--

--
-- Dumping routines for database 'sandbox_test'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-10 20:41:07
