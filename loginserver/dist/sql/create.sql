CREATE TABLE IF NOT EXISTS `accounts` (
  `id` BIGINT AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `last_server_id` TINYINT UNSIGNED,
  `access_level` TINYINT NOT NULL DEFAULT 1,
  `last_ip` VARCHAR(32),
  `last_time_access` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UNIQUE INDEX(`name`),
  PRIMARY KEY(`id`)
);

CREATE TABLE IF NOT EXISTS `account_otps` (
  `id` BIGINT AUTO_INCREMENT,
  `account_id` BIGINT NOT NULL,
  `name` VARCHAR(32),
  `code` VARCHAR(32) NOT NULL, -- TODO: Calculate correct size
  PRIMARY KEY(`id`),
  FOREIGN KEY(`account_id`) REFERENCES `accounts`(`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `account_bans` (
  `id` BIGINT AUTO_INCREMENT,
  `account_id` BIGINT NOT NULL,
  `active` BOOLEAN NOT NULL DEFAULT TRUE,
  `started_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `expires_at` TIMESTAMP NOT NULL,
  `reason` TEXT,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`account_id`) REFERENCES `accounts`(`id`) ON DELETE CASCADE
);