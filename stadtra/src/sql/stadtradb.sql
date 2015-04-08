# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.6.23)
# Database: stadtradb
# Generation Time: 2015-04-08 04:47:30 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table t_announcement
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_announcement`;

CREATE TABLE `t_announcement` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL,
  `body` varchar(300) DEFAULT NULL,
  `file` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

LOCK TABLES `t_announcement` WRITE;
/*!40000 ALTER TABLE `t_announcement` DISABLE KEYS */;

INSERT INTO `t_announcement` (`id`, `title`, `body`, `file`)
VALUES
	(1,'Boo	Announcement','Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.','Praesent blandit lacinia erat.'),
	(2,'Sign\nUp	Sheet','Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.','Praesent lectus.'),
	(4,'SignUp\nSheet','Sed ante. Vivamus tortor. Duis mattis egestas metus.\n\nAenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.','Donec semper sapien a libero.');

/*!40000 ALTER TABLE `t_announcement` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_message`;

CREATE TABLE `t_message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table t_notification
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_notification`;

CREATE TABLE `t_notification` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table t_request
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_request`;

CREATE TABLE `t_request` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table t_student
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_student`;

CREATE TABLE `t_student` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `student_no` varchar(10) NOT NULL DEFAULT '',
  `f_name` varchar(30) NOT NULL DEFAULT '',
  `m_name` varchar(30) DEFAULT '',
  `l_name` varchar(30) NOT NULL DEFAULT '',
  `birth_date` date DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `classification` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_student_no` (`student_no`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

LOCK TABLES `t_student` WRITE;
/*!40000 ALTER TABLE `t_student` DISABLE KEYS */;

INSERT INTO `t_student` (`id`, `student_no`, `f_name`, `m_name`, `l_name`, `birth_date`, `sex`, `email`, `classification`)
VALUES
	(1,'2008-36881','David','Coleman','Jackson',NULL,'M','djackson0@nba.com','Undergraduate'),
	(2,'2007-81179','Susan','Payne','Rivera',NULL,'F','srivera1@slideshare.net','Undergraduate'),
	(3,'2009-97380','Chris','Murphy','Jenkins',NULL,'M','cjenkins2@de.vu','Graduate'),
	(4,'2016-80552','Robin','Meyer','Woods',NULL,'F','rwoods3@cpanel.net','Graduate'),
	(5,'2008-80952','Paula','Cook','Wright',NULL,'F','pwright4@google.es','Graduate'),
	(6,'2019-72410','Kevin','Ryan','Myers',NULL,'M','kmyers5@nymag.com','Graduate'),
	(7,'2019-56800','Joan','Tucker','Collins',NULL,'F','jcollins6@photobucket.com','Undergraduate'),
	(8,'2018-70114','Katherine','Patterson','Chapman',NULL,'F','kchapman7@dion.ne.jp','Undergraduate'),
	(9,'2019-37084','Douglas','Harper','Ward',NULL,'M','dward8@pcworld.com','Undergraduate'),
	(10,'2008-14797','Peter','Alvarez','Morgan',NULL,'M','pmorgan9@harvard.edu','Undergraduate'),
	(11,'2018-37927','Edward','Lawrence','Alvarez',NULL,'M','ealvareza@cnn.com','Graduate'),
	(12,'2019-90175','Teresa','Johnston','Brooks',NULL,'F','tbrooksb@go.com','Undergraduate'),
	(13,'2019-75214','Mark','Larson','Johnston',NULL,'M','mjohnstonc@washington.edu','Undergraduate'),
	(14,'2018-36605','Lori','Elliott','Stevens',NULL,'F','lstevensd@marketwatch.com','Graduate'),
	(15,'2019-73007','Bruce','Morales','Hicks',NULL,'M','bhickse@wikimedia.org','Undergraduate'),
	(16,'2008-09675','Evelyn','Murphy','Rose',NULL,'F','erosef@unblog.fr','Graduate'),
	(17,'2019-16998','Virginia','Stevens','Murphy',NULL,'F','vmurphyg@addthis.com','Undergraduate'),
	(18,'2017-62401','Christine','Hansen','Bennett',NULL,'F','cbennetth@blogspot.com','Undergraduate'),
	(19,'2001-77321','Kathryn','Johnson','Kelly',NULL,'F','kkellyi@fda.gov','Graduate'),
	(20,'2018-07643','Clarence','Medina','Jones',NULL,'M','cjonesj@list-manage.com','Graduate'),
	(21,'2008-50374','Bonnie','Harper','Richardson',NULL,'F','brichardsonk@opensource.org','Undergraduate'),
	(22,'2019-59380','Anne','Scott','Coleman',NULL,'F','acolemanl@joomla.org','Graduate'),
	(23,'2018-78193','Sharon','Oliver','Harper',NULL,'F','sharperm@comsenz.com','Graduate'),
	(24,'2018-88448','Nicholas','Tucker','Andrews',NULL,'M','nandrewsn@tripadvisor.com','Undergraduate'),
	(25,'2009-68211','Tammy','Gilbert','Lopez',NULL,'F','tlopezo@globo.com','Undergraduate'),
	(26,'2018-61287','Barbara','Rodriguez','Jordan',NULL,'F','bjordanp@examiner.com','Undergraduate'),
	(27,'2009-93404','Jack','Simmons','Wheeler',NULL,'M','jwheelerq@hc360.com','Undergraduate'),
	(28,'2018-97680','Helen','Phillips','Perry',NULL,'F','hperryr@tuttocitta.it','Undergraduate'),
	(29,'2008-12745','Ernest','Boyd','Smith',NULL,'M','esmiths@last.fm','Undergraduate'),
	(30,'2015-40010','Paula','James','Miller',NULL,'F','pmillert@imdb.com','Graduate');

/*!40000 ALTER TABLE `t_student` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_teacher
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_teacher`;

CREATE TABLE `t_teacher` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `employee_no` varchar(15) NOT NULL DEFAULT '',
  `f_name` varchar(30) NOT NULL DEFAULT '',
  `m_name` varchar(30) DEFAULT '',
  `l_name` varchar(30) NOT NULL DEFAULT '',
  `birth_date` date DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_employee_no` (`employee_no`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

LOCK TABLES `t_teacher` WRITE;
/*!40000 ALTER TABLE `t_teacher` DISABLE KEYS */;

INSERT INTO `t_teacher` (`id`, `employee_no`, `f_name`, `m_name`, `l_name`, `birth_date`, `sex`, `email`)
VALUES
	(1,'0701171','William','Castillo','Berry',NULL,'M','wberry0@netscape.com'),
	(2,'3290575','Ann','Sullivan','Ross',NULL,'F','aross1@upenn.edu'),
	(3,'5753160','Harry','Fuller','Bishop',NULL,'M','hbishop2@hao123.com'),
	(4,'6612765','Jeremy','Woods','Ford',NULL,'M','jford3@bbc.co.uk'),
	(5,'6569641','Eugene','Larson','Hudson',NULL,'M','ehudson4@163.com'),
	(6,'1877952','Rose','Hill','Cunningham',NULL,'F','rcunningham5@google.com.hk'),
	(7,'1117411','Jesse','Duncan','Pierce',NULL,'M','jpierce6@canalblog.com'),
	(8,'5954199','Brian','Hansen','Rivera',NULL,'M','brivera7@plala.or.jp'),
	(9,'4735844','Randy','Cole','Lynch',NULL,'M','rlynch8@ning.com'),
	(10,'8899521','Robin','Romero','Fields',NULL,'F','rfields9@joomla.org'),
	(11,'5423756','Jimmy','Rivera','Hunter',NULL,'M','jhuntera@earthlink.net'),
	(12,'3727307','Ruth','Lawson','Reynolds',NULL,'F','rreynoldsb@cocolog-nifty.com'),
	(13,'7678898','Ruby','Collins','Kennedy',NULL,'F','rkennedyc@upenn.edu'),
	(14,'1394319','Henry','Diaz','Johnston',NULL,'M','hjohnstond@godaddy.com'),
	(15,'7494507','Christopher','Morgan','Graham',NULL,'M','cgrahame@drupal.org'),
	(16,'9750390','Catherine','Jones','Gonzales',NULL,'F','cgonzalesf@upenn.edu'),
	(17,'0407856','Eugene','Campbell','Turner',NULL,'M','eturnerg@goodreads.com'),
	(18,'3861459','Jonathan','Diaz','Franklin',NULL,'M','jfranklinh@weibo.com'),
	(19,'0279974','Daniel','Matthews','Hall',NULL,'M','dhalli@webs.com'),
	(20,'5097211','Craig','Hunter','Meyer',NULL,'M','cmeyerj@php.net'),
	(21,'6792382','Joan','Kelly','Schmidt',NULL,'F','jschmidtk@typepad.com'),
	(22,'7837011','Philip','Reid','Washington',NULL,'M','pwashingtonl@wunderground.com'),
	(23,'3951964','Henry','Johnston','Price',NULL,'M','hpricem@wiley.com'),
	(24,'7572916','Joseph','Morgan','Garrett',NULL,'M','jgarrettn@shinystat.com'),
	(25,'6598890','John','Hansen','Patterson',NULL,'M','jpattersono@omniture.com'),
	(26,'2635232','Sara','Warren','Mitchell',NULL,'F','smitchellp@eventbrite.com'),
	(27,'2031505','Julia','Boyd','Miller',NULL,'F','jmillerq@pbs.org'),
	(28,'1443698','Ralph','Johnson','Cruz',NULL,'M','rcruzr@simplemachines.org'),
	(29,'5149105','Carl','Perry','Kennedy',NULL,'M','ckennedys@histats.com'),
	(30,'6548472','Mark','Marshall','Thomas',NULL,'M','mthomast@google.nl');

/*!40000 ALTER TABLE `t_teacher` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_teacher_student
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_teacher_student`;

CREATE TABLE `t_teacher_student` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `student_id` int(11) unsigned DEFAULT NULL,
  `student_no` varchar(10) DEFAULT NULL,
  `teacher_id` int(11) unsigned DEFAULT NULL,
  `employee_no` varchar(15) DEFAULT NULL,
  `date_approved` date DEFAULT NULL,
  `is_still_effective` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_student_adviser` (`student_no`,`employee_no`,`date_approved`),
  KEY `fk_student_id` (`student_id`),
  KEY `fk_teacher_id` (`teacher_id`),
  CONSTRAINT `fk_student_id` FOREIGN KEY (`student_id`) REFERENCES `t_student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `t_teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table t_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `login_id` varchar(15) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `f_name` varchar(30) DEFAULT NULL,
  `m_name` varchar(30) DEFAULT NULL,
  `l_name` varchar(30) DEFAULT NULL,
  `role` varchar(15) DEFAULT NULL,
  `reference_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_login` (`login_id`,`password`),
  UNIQUE KEY `idx_user` (`role`,`reference_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;

INSERT INTO `t_user` (`id`, `login_id`, `password`, `f_name`, `m_name`, `l_name`, `role`, `reference_id`)
VALUES
	(1,'0701171','123456','William','Castillo','Berry','teacher',1),
	(2,'3290575','123456','Ann','Sullivan','Ross','teacher',2),
	(3,'5753160','123456','Harry','Fuller','Bishop','teacher',3),
	(4,'6612765','123456','Jeremy','Woods','Ford','teacher',4),
	(5,'6569641','123456','Eugene','Larson','Hudson','teacher',5),
	(6,'1877952','123456','Rose','Hill','Cunningham','teacher',6),
	(7,'1117411','123456','Jesse','Duncan','Pierce','teacher',7),
	(8,'5954199','123456','Brian','Hansen','Rivera','teacher',8),
	(9,'4735844','123456','Randy','Cole','Lynch','teacher',9),
	(10,'8899521','123456','Robin','Romero','Fields','teacher',10),
	(11,'5423756','123456','Jimmy','Rivera','Hunter','teacher',11),
	(12,'3727307','123456','Ruth','Lawson','Reynolds','teacher',12),
	(13,'7678898','123456','Ruby','Collins','Kennedy','teacher',13),
	(14,'1394319','123456','Henry','Diaz','Johnston','teacher',14),
	(15,'7494507','123456','Christopher','Morgan','Graham','teacher',15),
	(16,'9750390','123456','Catherine','Jones','Gonzales','teacher',16),
	(17,'0407856','123456','Eugene','Campbell','Turner','teacher',17),
	(18,'3861459','123456','Jonathan','Diaz','Franklin','teacher',18),
	(19,'0279974','123456','Daniel','Matthews','Hall','teacher',19),
	(20,'5097211','123456','Craig','Hunter','Meyer','teacher',20),
	(21,'6792382','123456','Joan','Kelly','Schmidt','teacher',21),
	(22,'7837011','123456','Philip','Reid','Washington','teacher',22),
	(23,'3951964','123456','Henry','Johnston','Price','teacher',23),
	(24,'7572916','123456','Joseph','Morgan','Garrett','teacher',24),
	(25,'6598890','123456','John','Hansen','Patterson','teacher',25),
	(26,'2635232','123456','Sara','Warren','Mitchell','teacher',26),
	(27,'2031505','123456','Julia','Boyd','Miller','teacher',27),
	(28,'1443698','123456','Ralph','Johnson','Cruz','teacher',28),
	(29,'5149105','123456','Carl','Perry','Kennedy','teacher',29),
	(30,'6548472','123456','Mark','Marshall','Thomas','teacher',30),
	(31,'2008-36881','123456','David','Coleman','Jackson','student',1),
	(32,'2007-81179','123456','Susan','Payne','Rivera','student',2),
	(33,'2009-97380','123456','Chris','Murphy','Jenkins','student',3),
	(34,'2016-80552','123456','Robin','Meyer','Woods','student',4),
	(35,'2008-80952','123456','Paula','Cook','Wright','student',5),
	(36,'2019-72410','123456','Kevin','Ryan','Myers','student',6),
	(37,'2019-56800','123456','Joan','Tucker','Collins','student',7),
	(38,'2018-70114','123456','Katherine','Patterson','Chapman','student',8),
	(39,'2019-37084','123456','Douglas','Harper','Ward','student',9),
	(40,'2008-14797','123456','Peter','Alvarez','Morgan','student',10),
	(41,'2018-37927','123456','Edward','Lawrence','Alvarez','student',11),
	(42,'2019-90175','123456','Teresa','Johnston','Brooks','student',12),
	(43,'2019-75214','123456','Mark','Larson','Johnston','student',13),
	(44,'2018-36605','123456','Lori','Elliott','Stevens','student',14),
	(45,'2019-73007','123456','Bruce','Morales','Hicks','student',15),
	(46,'2008-09675','123456','Evelyn','Murphy','Rose','student',16),
	(47,'2019-16998','123456','Virginia','Stevens','Murphy','student',17),
	(48,'2017-62401','123456','Christine','Hansen','Bennett','student',18),
	(49,'2001-77321','123456','Kathryn','Johnson','Kelly','student',19),
	(50,'2018-07643','123456','Clarence','Medina','Jones','student',20),
	(51,'2008-50374','123456','Bonnie','Harper','Richardson','student',21),
	(52,'2019-59380','123456','Anne','Scott','Coleman','student',22),
	(53,'2018-78193','123456','Sharon','Oliver','Harper','student',23),
	(54,'2018-88448','123456','Nicholas','Tucker','Andrews','student',24),
	(55,'2009-68211','123456','Tammy','Gilbert','Lopez','student',25),
	(56,'2018-61287','123456','Barbara','Rodriguez','Jordan','student',26),
	(57,'2009-93404','123456','Jack','Simmons','Wheeler','student',27),
	(58,'2018-97680','123456','Helen','Phillips','Perry','student',28),
	(59,'2008-12745','123456','Ernest','Boyd','Smith','student',29),
	(60,'2015-40010','123456','Paula','James','Miller','student',30);

/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
