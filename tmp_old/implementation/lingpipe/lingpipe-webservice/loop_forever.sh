#!/bin/sh

echo "PORT (argument 1): "$1
MAVEN_OPTS=-Xmx3072m

# Loop forever (A cronjob is more suitable, this is for developing)
while :
do
mvn -Djetty.port=$1 jetty:run
done # Start over
