#!/bin/bash -xe
java -version 2>&1 | grep version.*14 || {
  echo "Java 14 required"
  exit 1
}
mvn clean package
