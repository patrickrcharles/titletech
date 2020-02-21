CREATE DATABASE  IF NOT EXISTS `title-ownership`;
USE `title-ownership`;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parcelid` int(11) DEFAULT 0,
  `first_name` varchar(45) DEFAULT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `date_purchased` varchar(45) DEFAULT '00/00/0000',
  `date_sold`  varchar(45) DEFAULT '00/00/0000',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `parcel`;

CREATE TABLE `parcel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(45)  DEFAULT NULL,
  `city` varchar(45)  DEFAULT NULL,
  `state` varchar(45)  DEFAULT NULL,
  `zip_code`  varchar(45) DEFAULT NULL,
  `current_ownerid` int(11) DEFAULT 0,
  `previous_ownerid` int(11)  DEFAULT 0,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `parcel_document`;

CREATE TABLE `parcel_document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parcelid` int(11) DEFAULT NULL,
  `date_purchased` varchar(45) DEFAULT NULL,
  `date_sold` varchar(45) DEFAULT NULL,
  `current_ownerid` int(11) DEFAULT NULL,
  `previous_ownerid` int(11) DEFAULT NULL,
  `current_owner` varchar(45) DEFAULT NULL,
  `previous_owner` varchar(45) DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `previous_owner`;

CREATE TABLE `previous_owner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parcelid` int(11) DEFAULT NULL,
  `personid` int(11) DEFAULT NULL,
  `parcel_documentid` int(11) DEFAULT NULL,
  `parcel_ownershipid` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `parcel_ownership`;

CREATE TABLE `parcel_ownership` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `current_ownerid` int(11) DEFAULT NULL,
  `previous_ownerid` int(11) DEFAULT NULL,
  `parcelid` int(11) DEFAULT NULL,
  `parcel_documentid` int(11) DEFAULT NULL,
  `date_purchased` varchar(45) DEFAULT NULL,
  `date_sold` varchar(45) DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
