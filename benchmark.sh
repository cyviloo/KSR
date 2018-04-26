#!/bin/bash

datadir="data"
xmlDataFiles=(`ls $datadir/reuters*.xml`)

stoplist=("$datadir/stoplist.txt" "null")
etiquettes=(reuters_places reuters_topics)
trainingPctSize=60
similarity=(binary levenshtein ngram)
nNumber=(2 3 4)
minAcceptableSimilarity=("0.7" "0.8" "0.9")
stemming=(no_stemmer porter_stemmer)
metric=(euclidean taxi chebyshev)
kNumber=(3 7 11)
numbers_cleaning="true"

bindir=bin
runCmd="java -cp $bindir app.Main"

$runCmd ${xmlDataFiles[0]} "null" ${etiquettes[0]} $trainingPctSize ${similarity[0]} ${nNumber[0]} ${minAcceptableSimilarity[0]} ${stemming[1]} ${metric[0]} ${kNumber[0]} $numbers_cleaning

