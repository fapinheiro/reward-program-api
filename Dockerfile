FROM openjdk:8u191-jre-alpine3.8

LABEL maintainerName="Filipe Pinheiro"

LABEL maintainerEmail="filipe.alves.pinheiro@gmail.com"

ENV ENVIRONMENT dev

# Moving jar file
RUN mkdir app
WORKDIR /app

# Copy jar file
COPY target/*.jar .

# Rename jar file
COPY scripts/*.sh .
RUN chmod +x rename-jar.sh
RUN ./rename-jar.sh

## Start webapp
CMD java -Dspring.profiles.active=$ENVIRONMENT -jar webapp.jar

