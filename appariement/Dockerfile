FROM openjdk:17-jdk-slim
ARG JAR_FILE=/target/appariement-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} appariement.jar
ENTRYPOINT java -jar /appariement.jar