#!/bin/bash
# Call this from a UNIX terminal that supports bash otherwise the $RANDOM
# keyword will not work and the data produced will be invalid.
# Usage:
#   - ./random\ data\ generator.sh - To generate 1000 random records
#   - ./random\ data\ generator.sh <number> - To generate <number> random records
if [ $# -ge 1 ]; then
    RECORDS=$1
else
    RECORDS=1000
fi

echo "" > data.txt
for i in  $(eval echo "{1..$RECORDS}"); do
    echo "Student$i,$(($RANDOM % 9)).$(($RANDOM % 9))" >> data.txt
done
