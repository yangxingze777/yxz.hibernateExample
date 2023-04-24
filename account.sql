CREATE TABLE `account` (
	`uid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`first_name` TINYTEXT NOT NULL COLLATE 'utf8mb4_general_ci',
	`last_name` TINYTEXT NOT NULL COLLATE 'utf8mb4_general_ci',
	`email` TINYTEXT NOT NULL COLLATE 'utf8mb4_general_ci',
	`password` TINYTEXT NOT NULL COLLATE 'utf8mb4_general_ci',
	`mobile_number` INT(10) UNSIGNED NOT NULL DEFAULT '0',
	PRIMARY KEY (`uid`) USING BTREE,
	UNIQUE INDEX `email` (`email`) USING HASH
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;
