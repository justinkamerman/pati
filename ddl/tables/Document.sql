DROP TABLE IF EXISTS `DocumentProcessed`;
CREATE TABLE `DocumentProcessed` (
  `DocumentId` int(10) unsigned NOT NULL,
  `Processed` boolean NOT NULL
) ENGINE=InnoDB 

DROP VIEW IF EXISTS `Document`;
CREATE VIEW `Document` ( Id, Content, Author, Title, Link, Processed ) AS 
       SELECT blog.ID, 
              blog.BlogContent, 
              blog.Author,
              blog.Title,
              blog.Link,
              DocumentProcessed.Processed
       FROM blog LEFT JOIN DocumentProcessed ON blog.ID = DocumentProcessed.DocumentId;