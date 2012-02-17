
parallel = read.table ("graph.data")
colnames(parallel) = c ("nodes", "ideal", "small", "big")
parafilt <- parallel[complete.cases(parallel),]
attach(parallel)

png("parallelization.png")

plot (ideal ~ nodes, data=parallel, type="p", pch=1, xlab="number of indexer nodes", ylab="throughput (documents/second)")
points (small ~ nodes, pch=2)
points (big ~ nodes, pch=3)
lines ( lowess (nodes, ideal))
lines ( lowess (nodes, small))
lines ( lowess (parafilt$nodes, parafilt$big))

legend ("topleft", legend=c("ideal", "100,000", "800,000"), pch=c(1,2,3))

dev.off()

