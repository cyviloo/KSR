#!/bin/bash

java=`which java`
javac=`which javac`
bindir="bin"
srcdir="src"

rm -rf $bindir
mkdir $bindir

find $srcdir -name *.java | xargs $javac -d $bindir

$java -cp $bindir test.Main

rm -rf $bindir

