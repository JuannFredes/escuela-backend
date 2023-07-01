#!/bin/sh
mvn liquibase:update -Pprod
java -jar -Dspring.profiles.active=prod /target/escuela-0.0.1-SNAPSHOT.jar
