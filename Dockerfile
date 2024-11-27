FROM openjdk:23-slim

WORKDIR /app

COPY target/ms-spring-security-jwt-0.0.1-SNAPSHOT.jar /app/ms-spring-security-jwt.jar

EXPOSE 3000

CMD ["java", "-jar", "/app/ms-spring-security-jwt.jar"]