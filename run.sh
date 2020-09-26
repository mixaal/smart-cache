#!/bin/bash -xe

DEBUG_OPTS1="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=22100"
DEBUG_OPTS2="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=22101"
nohup java --enable-preview "$DEBUG_OPTS1" -jar target/smart-cache-1.0-SNAPSHOT.jar server config.yml &
nohup java --enable-preview "$DEBUG_OPTS2" -jar target/smart-cache-1.0-SNAPSHOT.jar server config2.yml &
