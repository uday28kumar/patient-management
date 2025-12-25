FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app

COPY target/patient-service-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]
