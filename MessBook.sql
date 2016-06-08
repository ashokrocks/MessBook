-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.6.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `Name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('gaurav','raceison10'),('Sheetal','Raceison10'),('Neha','qwerty'),('Rashmi','rashmin'),('Sandeep','qwerty');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintable`
--

DROP TABLE IF EXISTS `maintable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maintable` (
  `stdid` varchar(15) NOT NULL,
  `std_name` varchar(45) NOT NULL,
  `std_branch` varchar(45) NOT NULL,
  `Year` int(11) NOT NULL DEFAULT '0',
  `hostelname` varchar(45) NOT NULL,
  `DueFees` varchar(50) NOT NULL DEFAULT '0',
  `PaidFees` float NOT NULL DEFAULT '0' COMMENT '#This is the main t',
  PRIMARY KEY (`stdid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintable`
--

LOCK TABLES `maintable` WRITE;
/*!40000 ALTER TABLE `maintable` DISABLE KEYS */;
INSERT INTO `maintable` VALUES ('1111','qwerty','CE',0,'CVR','10000',5252),('123','devesh','IT',0,'JLN','54909',55454),('1234','Gaurav Sharma','IT',0,'JLN','55454',2000),('12345','Devesh Dubey','IT',0,'JLN','55454',2000),('123456','Anshu Saket','IT',0,'JLN','55454',2000),('1245','xyz','CE',0,'RNT','326',2500),('1258','Gaurav','IT',0,'CVR','8499.8',8999.8),('1321','sheetal','EE',0,'KC','5000',4567),('159','lkjhgfd','EE',0,'RNT','0',326),('21212','xyz zyx','ME',0,'JLN','55454',5555),('21277','Naman Shrivastava','EE',0,'JLN','0',78912200),('21475','Akash shakya','ECE',0,'JLN','55454',8000),('21531','Sheetal Singh','EE',0,'KC','5475157',5484950),('21532','dfdggnm','CSE',0,'JLN','55454',1800),('21533','qwerty','CE',0,'JLN','-55454',55454),('21544','umesh','ECE',0,'JLN','55454',0),('23445','trishna','IT',0,'KC','5000',4567),('343567889','sfdgjk','IT',0,'JLN','55454',0),('3442','ankita','PCE',0,'IG','2000',6626),('44','Spiderman','IT',0,'JLN','0',454),('453','dfgfhj','ME',0,'JLN','0',0),('454','fghj','ME',0,'RNT','326',555),('456','LOL','ECE',0,'RNT','326',3000),('45678','esdgfhjk','PCE',0,'RNT','326',0),('54321','qwerty','PCE',0,'KC','5000',0),('567','anshu','IT',0,'JLN','55454',0),('652','ghj','CE',0,'CVR','0',500),('789','jkhjghgf','CE',0,'RNT','326',6469);
/*!40000 ALTER TABLE `maintable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactiontable`
--

DROP TABLE IF EXISTS `transactiontable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactiontable` (
  `TransactionId` varchar(45) NOT NULL,
  `std_id` varchar(45) NOT NULL,
  `Amount_Transacted` float NOT NULL,
  `Date` varchar(50) NOT NULL,
  UNIQUE KEY `Enrollment Number_UNIQUE` (`std_id`),
  CONSTRAINT `Enrollment` FOREIGN KEY (`std_id`) REFERENCES `maintable` (`stdid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactiontable`
--

LOCK TABLES `transactiontable` WRITE;
/*!40000 ALTER TABLE `transactiontable` DISABLE KEYS */;
INSERT INTO `transactiontable` VALUES ('-20151106','1111',5252,'05-2015'),('10-2015123','123',55454,'10-2015'),('05-20151234','1234',2000,'05-2015'),('05-201512345','12345',2000,'05-2015'),('-2015123451','123456',2000,'05-2015'),('05-20151245','1245',2500,'05-2015'),('10-20151258','1258',8999.8,'10-2015'),('-20151318','1321',4567,'03-2015'),('11-2015159','159',326,'11-2015'),('-201521207','21212',5555,'05-2015'),('-201521266','21277',78912200,'11-2015'),('05-201521475','21475',8000,'05-2015'),('11-201521531','21531',5484950,'11-2015'),('05-201521532','21532',1800,'05-2015'),('09-201521533','21533',55454,'09-2015'),('03-201521544','21544',0,'03-2015'),('03-201523445','23445',4567,'03-2015'),('03-20153442','3442',6626,'03-2015'),('-201534','44',454,'10-2015'),('-2015443','454',555,'11-2015'),('11-2015456','456',3000,'11-2015'),('-201554311','54321',0,'10-2015'),('-2015641','652',500,'11-2015'),('-2015779','789',6469,'10-2015');
/*!40000 ALTER TABLE `transactiontable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-08 17:59:24
