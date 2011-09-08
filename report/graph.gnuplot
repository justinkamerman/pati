# ahostatemachine
set terminal png size 1200,800
set output "parallelization.png"
set grid
set xlabel "number of indexer nodes"
set xtics 0,1
set ylabel "throughput (documents/second)"
set xrange [0:5]
set yrange [0:300]
set style data linespoints
set pointsize 3
plot "graph.data" using 1:2 index 0 title "ideal", \
    "graph.data" using 1:3 index 0 title "100,000 documents", \
    "graph.data" using 1:4 index 0 title "800,000 documents"
