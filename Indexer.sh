#!/bin/sh
# 
# $Id$ 
# 
# $LastChangedDate$ 
# 
# $LastChangedBy$ 
# 

CLASSPATH=.:dist/pati.jar
for J in thirdparty/*.jar; do
    CLASSPATH=$CLASSPATH:$J
done

java -cp $CLASSPATH -Djava.util.logging.config.file=logging.properties Indexer $*