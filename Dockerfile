FROM amazoncorretto:11-alpine-jdk
MAINTAINER JUANFRDS
COPY . .
RUN apk update && apk upgrade
RUN apk add maven
RUN mvn clean package
EXPOSE 8080
ENTRYPOINT ["/bin/sh", "-c", "mvn liquibase:update -Pprod && java -jar -Dspring.profiles.active=prod /target/escuela-0.0.1-SNAPSHOT.jar"]