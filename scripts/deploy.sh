#!/usr/bin/env bash

mvn clean package

echo 'Copy files'


scp -P 2280 target/masterhaus-1.0-SNAPSHOT.jar root@212.109.223.169:/home/zanoza/

    echo 'restart server'

ssh -p 2280 root@212.109.223.169 << EOF

pgrep java | xargs kill -9
nohup java -jar /home/zanoza/masterhaus-1.0-SNAPSHOT.jar > log.txt &

EOF

E:\IdeaProg2\masterhaus

echo 'By'


