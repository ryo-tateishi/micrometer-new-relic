#!/bin/sh

# TODO: パスをなんとかする
java \
  -jar /Users/ryo-tateishi/develop/git/micrometer-new-relic/target/demo-0.0.1-SNAPSHOT.jar \
  --management.metrics.export.newrelic.account-id="${NEWRELIC_ACCOUNT_ID}" \
  --management.metrics.export.newrelic.api-key="${NEWRELIC_INSIGHT_API_KEY}" \
  --logging.file.name=/Users/ryo-tateishi/develop/git/micrometer-new-relic/target/logs/demo.log \
  --logging.level.biz.kaipoke.boot=DEBUG \
  --server.port=8081 \
  --server.forward-headers-strategy=native
