#!usr/bin/python
#
# Used to get the system time and to send commands to the terminal which launched 
# this program.
import time,os

# Used to clear the system screen.
os.system( 'clear' )

print '**********************************'
print '*    STARTING INDEXING CYCLE     *'
print '**********************************\n'


#       List of command to execute often
command0 = 'bin/indexer.sh'

#  System Times
call = os.popen( "mysql --host=localhost  --user=xxx --password=xxx indexerdb --execute=\"select count(*) from timer where ID=2 AND LatestTime=0\" --skip-column-names" )       
time = call.readline()
call.close()
time = int( time )
os.chdir( 'workspace/' )


while( time==1 ):
        os.system( command0 )
        call = os.popen( "mysql --host=localhost  --user=xxx --password=xxx indexerdb --execute=\"select count(*) from timer where ID=2 AND LatestTime=0\" --skip-column-names" )       
        time = call.readline()
        call.close()
        time = int( time )

