jre: 1.7.0_65
IDE: eclipse
DB: MySQL 5.6.21

Database create:
CREATE DATABASE `chatroom` DEFAULT CHARACTER SET utf8;
CREATE TABLE `chatroom_user` (
  `id` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
