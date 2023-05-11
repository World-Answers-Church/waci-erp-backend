FROM maven:3.5-jdk-8-alpine as builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM adoptopenjdk/openjdk11-openj9
COPY --from=builder /usr/src/app/target/*.jar application.jar
ENV EXPOSED_CONTAINER_PORT 80
EXPOSE $EXPOSED_CONTAINER_PORT:$EXPOSED_CONTAINER_PORT
ENTRYPOINT ["java", "-jar", "application.jar"]