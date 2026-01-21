# -------- Build stage --------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

# Copy root pom
COPY pom.xml .

# Copy service poms
COPY billing-service/pom.xml billing-service/pom.xml
COPY patient-service/pom.xml patient-service/pom.xml

# Copy contracts aggregator + contracts poms
COPY contracts/pom.xml contracts/pom.xml
COPY contracts/grpc-contract/pom.xml contracts/grpc-contract/pom.xml
COPY contracts/event-contract/pom.xml contracts/event-contract/pom.xml

RUN mvn -B -pl billing-service -am dependency:go-offline

COPY billing-service billing-service
COPY patient-service patient-service
COPY contracts contracts

RUN mvn -B -pl billing-service -am clean package -DskipTests

# -------- Runtime stage --------
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /build/billing-service/target/*.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","app.jar"]
