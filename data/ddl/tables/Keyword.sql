DROP TABLE IF EXISTS `Keyword`;
CREATE TABLE `Keyword` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `canon` varchar(50) NOT NULL,
  `synonyms` varchar(255) NOT NULL,
  UNIQUE (`canon`),
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB;
