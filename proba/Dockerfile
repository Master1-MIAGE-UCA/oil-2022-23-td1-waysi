FROM openjdk:17-jdk-slim
ARG JAR_FILE=/target/proba-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} proba.jar
ENTRYPOINT java -jar /proba.jar