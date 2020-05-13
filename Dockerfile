FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
MAINTAINER Pranay Singhal
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/hibernate-events-example.jar /app/
ENTRYPOINT ["java", "-jar", "hibernate-events-example.jar"]