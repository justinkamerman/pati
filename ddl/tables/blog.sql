DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `BlogContent` longtext,
  `Author` varchar(200) DEFAULT NULL,
  `Title` varchar(300) DEFAULT NULL,
  `Link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=latin1
