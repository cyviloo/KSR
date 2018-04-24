#!/bin/bash

sgm_files_directory="$1"
data_dir="data"

function process { egrep '^<REUTERS|^</REUTERS|^<PLACES|^<TOPIC|<BODY>|^[^<]' "$1" | grep -v '</UNKNOWN>' | sed 's/.*<BODY>/<BODY>/' | sed 's/.*<\/BODY>.*/<\/BODY>/' | sed 's/<D>//g' | sed 's/<\/D>//g' | sed 's/\(<REUTERS\).*\(OLDID.*\)/\1 \2/' | grep -v '^&' | grep -v '^\*' | sed 's/<PLACES><\/PLACES>/<PLACES>x<\/PLACES>/' | sed 's/<TOPICS><\/TOPICS>/<TOPICS>x<\/TOPICS>/' | sed 's/^.&.*//' | sed -r 's/\&#[0-9]+\;//g' ; }

sgm_files=(`ls ${sgm_files_directory}*.sgm`)
counter=1

for FILE in ${sgm_files[@]}; do
	filename="$data_dir/reuters${counter}.xml"
	counter=$(($counter+1))
	echo '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>' > $filename
	echo '<DOCUMENT>' >> $filename
	process "$FILE" >> $filename
	echo '</DOCUMENT>' >> $filename
done

