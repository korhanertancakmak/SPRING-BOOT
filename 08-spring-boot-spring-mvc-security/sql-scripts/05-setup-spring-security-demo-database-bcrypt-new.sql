USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users` bcrypt password 'fun123'
--

INSERT INTO `users` 
VALUES 
('john','{bcrypt}$2a$10$J0bn7oskh1MiAUCjSlHAzudbspx8Tc1fzw73R.RY3oFcxOuir4NEu',1),
('mary','{bcrypt}$2a$10$AaH0AaU3loWzviBKz.yhmulBsvGQ0cMo4o5fLb.8.b4n3dYV6NGgO',1),
('susan','{bcrypt}$2a$10$WM5VdGryWM.RaOFWQH5Vh.4Cwug4PUvczvJvrySzWBgEZeW4EB19O',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_MANAGER'),
('susan','ROLE_ADMIN');


