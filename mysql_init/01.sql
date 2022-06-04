CREATE DATABASE  IF NOT EXISTS `creative-database`CHARACTER SET cp1250 COLLATE cp1250_polish_ci ;
USE `creative-database`;

CREATE TABLE  IF NOT EXISTS `trips` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `place` varchar(255) DEFAULT NULL,
  `date_start` varchar(255) DEFAULT NULL,
  `date_end` varchar(255) DEFAULT NULL,
  `days` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=cp1250;

CREATE TABLE  IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=cp1250;

CREATE TABLE  IF NOT EXISTS `user_trip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `trip_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=cp1250;

CREATE TABLE  IF NOT EXISTS `tokens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  `created_at` varchar(255) DEFAULT NULL,
  `expires_at` varchar(255) DEFAULT NULL,
  `confirmed_at` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=cp1250;

CREATE TABLE  IF NOT EXISTS `transports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transport_name` varchar(255) DEFAULT NULL,
  `transport_start` varchar(255) DEFAULT NULL,
  `transport_start_place` varchar(255) DEFAULT NULL,
  `transport_end` varchar(255) DEFAULT NULL,
  `transport_end_place` int(11) DEFAULT NULL,
  `transport_order` int(11) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `trip_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=cp1250;

CREATE TABLE  IF NOT EXISTS `arrangements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `note` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `tripId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=cp1250;

CREATE TABLE  IF NOT EXISTS `images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `data` longblob NOT NULL,
  `trip_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=cp1250;
