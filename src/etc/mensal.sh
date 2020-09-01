#!/bin/sh

export APP_DB_USR=
export APP_DB_PWD=
export APP_JWT_KEY=

java -jar /opt/mensal/rest/target/mensal-rest-1.0.0-SNAPSHOT-runner.jar

