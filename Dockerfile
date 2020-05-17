FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /app
COPY target/letterme-1.0-SNAPSHOT.jar /app/
