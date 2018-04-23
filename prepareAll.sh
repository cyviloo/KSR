#!/bin/bash

filename="$1"
xmldir="$2"

echo '<DOCUMENT>' > $filename
./prepareXML.sh $xmldir >> $filename
echo '</DOCUMENT>' >> $filename

