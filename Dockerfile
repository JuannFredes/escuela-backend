FROM maven:3.9.0 AS maven
WORKDIR /build
COPY . .
RUN mvn clean package


FROM amazoncorretto:11.0.18-alpine3.17
WORKDIR /opt/app
COPY --from=maven /build/target/escuela-0.0.1-SNAPSHOT.jar escuela.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "escuela.jar"]