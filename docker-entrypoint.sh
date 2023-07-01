#!/bin/sh
mvn liquibase:update -Pprod mvn liquibase:update -Pprod -Denv.PROD_DB_HOST=$PROD_DB_HOST -Denv.PROD_DB_PORT=$PROD_DB_PORT -Denv.PROD_DB_NAME=$PROD_DB_NAME -Denv.PROD_DB_USERNAME=$PROD_DB_USERNAME -Denv.PROD_DB_PASSWORD=$PROD_DB_PASSWORD
java -jar -Dspring.profiles.active=prod /target/escuela-0.0.1-SNAPSHOT.jar
