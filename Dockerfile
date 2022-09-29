FROM openjdk:17.0-jdk-oracle
ARG JAR_FILE=build/libs/growth-backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=prod"]