#!/bin/bash
set -e

until nc -z db 3306; do
    echo "$(date) - waiting for database to start..."
    sleep 1
done

JAVA_OPTS="${JAVA_OPTS} \
-server \
-Djava.net.preferIPv4Stack=true \
  -XX:+AggressiveOpts             \
  -XX:+UseParNewGC                \
  -XX:+UseConcMarkSweepGC         \
  -XX:+CMSParallelRemarkEnabled   \
  -XX:+CMSClassUnloadingEnabled   \
  -XX:ReservedCodeCacheSize=128m  \
  -XX:MaxPermSize=1024m           \
  -XX:SurvivorRatio=128           \
  -XX:MaxTenuringThreshold=0      \
  -Xss8M                          \
  -Xms512M                        \
  -Xmx2G"

if [ "$1" = 'service' ]; then
    exec java -ea ${JAVA_OPTS} -jar /app/bin/service.jar
else 
    exec "$@"
fi
