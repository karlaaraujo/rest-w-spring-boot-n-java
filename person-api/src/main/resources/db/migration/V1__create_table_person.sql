CREATE TABLE IF NOT EXISTS `person` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `address` varchar(255) DEFAULT NULL,
                          `first_name` varchar(255) NOT NULL,
                          `last_name` varchar(255) NOT NULL,
                          `gender` varchar(255) DEFAULT NULL,
                          `birthday` date DEFAULT NULL,
                          PRIMARY KEY (`id`)
);