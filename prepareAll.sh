#!/bin/bash

filename="$1"
xmldir="$2"

echo '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>' > $filename
echo '<DOCUMENT>' >> $filename
./prepareXML.sh $xmldir >> $filename
echo '</DOCUMENT>' >> $filename

