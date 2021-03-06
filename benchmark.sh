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
freshFile="experiment.log"
runCmd="java -cp $bindir app.Main"


for xmlFile in ${xmlDataFiles[@]}; do
  for list in ${stoplist[@]}; do
   for etiq in ${etiquettes[@]}; do
#      for sim in ${similarity[@]}; do
#        for minSim in ${minAcceptableSimilarity[@]}; do
          for stem in ${stemming[@]}; do
            for mtr in ${metric[@]}; do
              for k in ${kNumber[@]}; do
                      blist=`basename $list`
     	              $runCmd $xmlFile $list $etiq $trainingPctSize binary ${nNumber[1]} 1 $stem $mtr $k $numbers_cleaning && mv $freshFile "test_${xmlFile}_${blist}_${etiq}_trainPct${trainingPctSize}_binary_${nNumber[1]}_minSim${minSim}_${stem}_${mtr}_k${k}_${numbers_cleaning}.log"
              done
            done
          done
#        done
#      done
    done
  done
done
