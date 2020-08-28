FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /app
COPY api/target/api-1.0-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "api-1.0-SNAPSHOT.jar"]
