FROM alpine:3.17.3
WORKDIR /opt/app
COPY . .
RUN apk update && apk upgrade
RUN apk add openjdk11
RUN apk add maven
RUN mvn clean package
CMD ["sh", "-c", "mvn liquibase:update -Pprod && java -jar -Dspring.profiles.active=prod /opt/app/target/escuela-0.0.1-SNAPSHOT.jar"]
