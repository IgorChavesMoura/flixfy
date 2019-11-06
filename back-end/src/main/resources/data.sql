-- Adminer 4.7.1 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

USE `flixfy`;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `episode`;
CREATE TABLE `episode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `duration` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `video_content_id` bigint(20) DEFAULT NULL,
  `video_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbbmmuiev4kanyxd9b36agiymw` (`video_content_id`),
  KEY `FKohtd4jap8e55ivh86kov8hsnm` (`video_id`),
  CONSTRAINT `FKbbmmuiev4kanyxd9b36agiymw` FOREIGN KEY (`video_content_id`) REFERENCES `video_content` (`id`),
  CONSTRAINT `FKohtd4jap8e55ivh86kov8hsnm` FOREIGN KEY (`video_id`) REFERENCES `video_content` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `format`;
CREATE TABLE `format` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `relation_video_category`;
CREATE TABLE `relation_video_category` (
  `id_video` bigint(20) NOT NULL,
  `id_category` bigint(20) NOT NULL,
  KEY `FKoj5cbx7elwje7y4bxcfj7765v` (`id_category`),
  KEY `FK3wnukqdl0owwp5pj7uuf6on22` (`id_video`),
  CONSTRAINT `FK3wnukqdl0owwp5pj7uuf6on22` FOREIGN KEY (`id_video`) REFERENCES `video_content` (`id`),
  CONSTRAINT `FKoj5cbx7elwje7y4bxcfj7765v` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `relation_video_category` (`id_video`, `id_category`) VALUES
(1,	5),
(2,	5),
(6,	5),
(10,	5),
(10,	7),
(14,	5);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `video_content`;
CREATE TABLE `video_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `duration` int(11) DEFAULT NULL,
  `picture_url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `format_id` bigint(20) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg30oj68cu2evgtuw2a3y10en1` (`format_id`),
  KEY `FKsjulrp3yfv549lasliync52v8` (`owner_id`),
  CONSTRAINT `FKg30oj68cu2evgtuw2a3y10en1` FOREIGN KEY (`format_id`) REFERENCES `format` (`id`),
  CONSTRAINT `FKsjulrp3yfv549lasliync52v8` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- 2019-11-06 00:47:13