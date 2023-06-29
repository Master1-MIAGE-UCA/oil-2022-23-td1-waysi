FROM openjdk:17-jdk-slim
ARG JAR_FILE=/target/joueur-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} joueur.jar
ENTRYPOINT java -jar /joueur.jar $SERVER_IP