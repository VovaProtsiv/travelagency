FROM maven:3.8.1-jdk-11-slim AS build
RUN mkdir -p /target
WORKDIR /target
COPY pom.xml /target
COPY src /target/src
RUN mvn -f pom.xml package spring-boot:repackage

FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]