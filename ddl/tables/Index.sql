DROP TABLE IF EXISTS `Index`;
CREATE TABLE  `Index` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `KeywordId` int(10) unsigned NOT NULL,
  `DocumentId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;