# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.6.23)
# Database: stadtradb
# Generation Time: 2015-03-18 13:39:49 +0000
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



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
  `classification` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_student_no` (`student_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
