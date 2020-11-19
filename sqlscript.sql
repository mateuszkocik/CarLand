DROP DATABASE IF EXISTS `carland`;

CREATE DATABASE  IF NOT EXISTS `carland`;
USE `carland`;


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `name` varchar(50) NOT NULL,
  `telephone_number` varchar(9) NOT NULL,
  `city` varchar(50) NOT NULL,
  `street` varchar(50) NOT NULL,
  `postal_code` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `user` (username,password,name,telephone_number,city,street,postal_code)
VALUES 
('mateusz4577@o2.pl','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Mateusz','123456789','Krakow','Falata','12-123');



DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `role` (name)
VALUES 
('ROLE_USER'),('ROLE_ADMIN');



DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users_roles` (user_id,role_id)
VALUES
(1,1),
(1,2);

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
	`name` varchar(32) NOT NULL,
    
    PRIMARY KEY (`name`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `make` (
	`name` varchar(32) NOT NULL,
    
    PRIMARY KEY (`name`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `model` (
	`name` varchar(32) NOT NULL,
    `make` varchar(32) NOT NULL,
    
    PRIMARY KEY (`name`, `make`),
    
    CONSTRAINT `FK_MODEL_MAKE` FOREIGN KEY (`make`) 
	REFERENCES `make` (`name`) 
	ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `advert`;

CREATE TABLE `advert` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `user` int(11) NOT NULL,
    `title` varchar(32) NOT NULL,
    `type` varchar(32) NOT NULL,
    `make` varchar(32) NOT NULL,
    `model` varchar(32) NOT NULL,
    `price` int unsigned NOT NULL,
    `year` year NOT NULL,
    `mileage` int unsigned NOT NULL,
    `fuel_type` varchar(32) NOT NULL,
    `description` varchar(256),
    `expiration_date` date,
    `state` varchar(16) NOT NULL,
    
	PRIMARY KEY(`id`),
    
    CONSTRAINT `FK_USER` FOREIGN KEY (`user`) 
	REFERENCES `user` (`id`) 
	ON DELETE NO ACTION ON UPDATE NO ACTION,
    
    CONSTRAINT `FK_TYPE` FOREIGN KEY (`type`) 
	REFERENCES `type` (`name`) 
	ON DELETE NO ACTION ON UPDATE NO ACTION,
    
    CONSTRAINT `FK_MAKE` FOREIGN KEY (`make`) 
	REFERENCES `make` (`name`) 
	ON DELETE NO ACTION ON UPDATE NO ACTION,
    
    CONSTRAINT `FK_MODEL` FOREIGN KEY (`model`) 
	REFERENCES `model` (`name`) 
	ON DELETE NO ACTION ON UPDATE NO ACTION
    
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`advert` int(11) NOT NULL,
	`name` varchar(32) NOT NULL,
    
    PRIMARY KEY (`id`),
    
    CONSTRAINT `FK_ADVERT` FOREIGN KEY (`advert`) 
	REFERENCES `advert` (`id`) 
	ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO `type` VALUES
('Hatchback'),
('Sedan'),
('MPV'),
('SUV'),
('Crossover'),
('Coupe'),
('Convertible');

INSERT INTO `make` VALUES
('Toyota'),
('Mercedes-Benz'),
('BMW'),
('Porsche');

INSERT INTO `model` VALUES
('i8','BMW'),
('X6','BMW'),
('i3','BMW'),
('E36','BMW'),
('Corolla','Toyota'),
('Celica','Toyota'),
('Prius','Toyota'),
('Verso','Toyota'),
('CL','Mercedes-Benz'),
('GLS','Mercedes-Benz'),
('Vario','Mercedes-Benz'),
('Sprinter','Mercedes-Benz'),
('911','Porsche'),
('Macan','Porsche'),
('Cayenne','Porsche'),
('Boxter','Porsche');

INSERT INTO `advert` values
(1,1,'Fastest car you ever seen','Convertible','Porsche','Cayenne',1231,1999,11,'Diesel','a','2020-05-20','ACTIVE'),
(2,1,'My second car','Coupe','Mercedes-Benz','CL',2500,1999,1999,'Electric','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vulputate quam in leo imperdiet tincidunt non non risus. Mauris','2020-05-25','PENDING'),
(3,1,'Super car','Coupe','Mercedes-Benz','GLS',1231321,2000,12312321,'Diesel','dsadsadsadsa','2020-05-26','PENDING'),
(5,1,'My wifes car','Convertible','Porsche','911',12121,2001,1222,'Diesel','asssssssssss','2020-05-26','PENDING'),
(6,1,'Car','Crossover','Mercedes-Benz','GLS',111111,2003,1231,'Electric','aaasss','2020-05-27','ACTIVE');

INSERT INTO `image` values
(1,1,'1.jpg'),
(2,1,'2.jpg'),
(3,2,'3.jpg'),
(4,6,'4.jpg'),
(5,3,'5.jpg'),
(6,5,'6.jpg');


