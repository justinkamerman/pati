#!usr/bin/python
#
# Used to get the system time and to send commands to the terminal which launched 
# this program.
#
import time,os

# Used to clear the system screen.
os.system( 'clear' )



numberOfLoop = 80

print '**********************************'
print '*    STARTING INDEXING CYCLE     *'
print '**********************************\n'


# List of command to execute often
command0 = 'bin/Indexer.sh'

# System Times
start = time.time()

current = time.time()
loop=0;
os.chdir( 'workspace/' )
while( loop < numberOfLoop ):
        

        os.system( command0 )
        current = time.time()
        elapsed = current - start
        print ' \n time for loop ' + str(loop) + ' is ' + str(elapsed) + '\n'
        loop = loop + 1

