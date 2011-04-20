DROP TABLE IF EXISTS `Indexx`;
CREATE TABLE  `Indexx` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `KeywordId` int(10) unsigned NOT NULL,
  `DocumentId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;