CREATE TABLE IF NOT EXISTS `character_variables` (
  `charId` int(10) UNSIGNED NOT NULL,
  `var`    varchar(255)     NOT NULL,
  `val`    VARBINARY(255)   NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;