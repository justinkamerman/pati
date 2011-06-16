Truncate Indexx;
         
Truncate DocumentProcessed;
        
Update Indexerdb.timer set LatestTime = 0 where ID in(1,2);
