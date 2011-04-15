DROP TABLE IF EXISTS `Keyword`;
CREATE TABLE `Keyword` (
  `canon` varchar(50) NOT NULL,
  `synonym` varchar(50) NOT NULL,
  UNIQUE (synonym),
  PRIMARY KEY (`canon`, `synonym`)
) ENGINE=InnoDB;
