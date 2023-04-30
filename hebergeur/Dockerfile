FROM openjdk:17-jdk-slim
ARG JAR_FILE=/target/hebergeur-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} hebergeur.jar
ENTRYPOINT java -jar /hebergeur.jar