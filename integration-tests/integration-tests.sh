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

echo ""
echo '==> Trying to get "IT" msg via gwSocket -> pipeline -> monitor'

java -jar $(getJarPath pipeline) &
java -jar $(getJarPath gwSocket) &
java -jar $(getJarPath monitor) > $MONITOR_LOG &

for i in {1..5}; do
  sleep 1
  if echo "IT" | nc -v 127.0.0.1 10001; then break; fi
  if [ $i == 5 ]; then echo "FAILED: can't connect to gwSocket"; cleanup; exit 3; fi
done

for i in {1..3}; do
  if grep -q IT $MONITOR_LOG; then echo "SUCCESS"; cleanup; exit 0; fi
  sleep 1
done

echo "FAILED. Logs of $MONITOR_LOG:"
cat $MONITOR_LOG
cleanup
exit 2
