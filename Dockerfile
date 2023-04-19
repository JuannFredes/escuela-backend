FROM amazoncorretto:11-alpine-jdk
MAINTAINER JUANFRDS
COPY target/escuela-0.0.1-SNAPSHOT.jar SpringBoot-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/SpringBoot-0.0.1-SNAPSHOT.jar"]
