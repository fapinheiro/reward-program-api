FROM openjdk:8u191-jre-alpine3.8

LABEL maintainerEmail="filipe.alves.pinheiro@gmail.com"

LABEL maintainerName="Filipe Pinheiro"

ENV PROJECT_NAME mobile-number-api

ENV JAR_FILE_NAME mobile-number-api-1.0.0.jar

ENV ENVIRONMENT dev

# Moving jar file
WORKDIR /opt

RUN mkdir $PROJECT_NAME

COPY ./target/$JAR_FILE_NAME ./$PROJECT_NAME

# Configurin exposition and entry point
EXPOSE 8080

CMD java -Dspring.profiles.active=$ENVIRONMENT -jar /opt/$PROJECT_NAME/$JAR_FILE_NAME

