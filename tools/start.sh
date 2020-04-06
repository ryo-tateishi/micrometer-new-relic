#!/bin/sh

# TODO: パスをなんとかする
java \
  -javaagent:/Users/ryo-tateishi/develop/git/micrometer-new-relic/newrelic/newrelic.jar \
  -Dnewrelic.config.file=/Users/ryo-tateishi/develop/git/micrometer-new-relic/newrelic/newrelic.yml \
  -jar /Users/ryo-tateishi/develop/git/micrometer-new-relic/target/demo-0.0.1-SNAPSHOT.jar \
  --logging.file.name=/Users/ryo-tateishi/develop/git/micrometer-new-relic/target/logs/demo.log \
  --logging.level.biz.kaipoke.boot=DEBUG \
  --server.port=8081 \
  --server.forward-headers-strategy=native
