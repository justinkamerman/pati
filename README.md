Parallel Text Indexer (PATI)
============================

An Aho-Corasick indexer was implemented using Java and MySQL
relational database. The Aho-Corasick string matching algorithm is a
simple and efficient text scanning algorithm. The algorithm constructs
a finite state machine to scan for a given set of keywords. It is, in
effect, a reduced grammar regular expression parser. State machine
time is proportional to the sum of the length of the keyword set, and
the number of state transitions required to scan a document is
independent of the number of the size of the keyword set.

The source includes database DDL and maintenance scripts, as well as
operational scripts ofr maintianing the index.  Upon startup, the
indexer reads a list of index keywords and synonyms from the database
and constructs an Aho-Corasick state machine. The indexer then enters
a loop in which it uses the state machine to scan batches of documents
retrieved from the database. The document batch size is varied
manually to match the physical capabilities of the host. A possible
enhancement to our implementation could include an adaptive loading
component. The output of the state machine is used to augment a global
index maintained in the database.  Decoupled design allows multiple
indexer instances can be deployed in parallel, using the database to
synchronize their access to the corpus and index. The individual
indexers do not interact directly with one another, making for a
simple deployment and operation model.



[![Deployment Model](https://github.com/justinkamerman/pati/raw/master/images/deploymentmodel.png)](https://github.com/justinkamerman/pati/raw/master/images/deploymentmodel.png)