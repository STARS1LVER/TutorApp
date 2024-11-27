FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/tutorApp-0.0.1-SNAPSHOT.jar app/tutorApp-0.0.1-SNAPSHOT.jar

EXPOSE 3000

LABEL maintainer="stas1lver"

CMD ["java", "-jar", "app/tutorApp-0.0.1-SNAPSHOT.jar"]
