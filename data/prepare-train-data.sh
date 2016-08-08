#!/bin/sh
cat rus_wikipedia_2010_10K-sentences.txt | sed '/^[0-9]*0 *.*/G'  | sed 's/^[0-9]* *\(.*\)/\1/g' > ru-train-sent.data
#cat rus_wikipedia_2010_10K-sentences.txt | sed '/^[0-9]*0 *.*/G'  

