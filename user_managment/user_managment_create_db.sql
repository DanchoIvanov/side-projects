CREATE SCHEMA IF NOT EXISTS `user_management`;

USE `user_management`;

CREATE TABLE IF NOT EXISTS `users` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `birthdate` DATE NOT NULL,
  `phone_number` VARCHAR(20) NOT NULL,
  `email` varchar(100) NOT NULL);