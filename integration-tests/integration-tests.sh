#!/bin/bash

WORKDIR=$(dirname $0)
MONITOR_LOG=$WORKDIR/monitor.log

function getJarPath
{
  NAME=$1
  find $WORKDIR/.. -name ${NAME}*.jar
}

function cleanup
{
  kill $(jobs -p)
  [ -f $MONITOR_LOG ] && rm -f $MONITOR_LOG
}

echo "Trying to get "IT" msg via gwSocket -> pipeline -> monitor"

java -jar $(getJarPath pipeline) &
java -jar $(getJarPath gwSocket) &
java -jar $(getJarPath monitor) > $MONITOR_LOG &
sleep 1

echo "IT" | nc -v 127.0.0.1 10001
for i in {1..3}; do
  if grep -q IT $MONITOR_LOG; then echo "SUCCESS"; cleanup; exit 0; fi
done

echo "FAILED"
cleanup
exit 2
