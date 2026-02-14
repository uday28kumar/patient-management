# -------- Build stage --------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

# Copy root pom
COPY pom.xml .

# Copy service poms
COPY analytics-service/pom.xml analytics-service/pom.xml
COPY billing-service/pom.xml billing-service/pom.xml
COPY patient-service/pom.xml patient-service/pom.xml

# Copy contracts aggregator + contracts poms
COPY contracts/pom.xml contracts/pom.xml
COPY contracts/grpc-contract/pom.xml contracts/grpc-contract/pom.xml
COPY contracts/event-contract/pom.xml contracts/event-contract/pom.xml
COPY contracts/util-contract/pom.xml contracts/util-contract/pom.xml

RUN mvn -B -pl analytics-service -am dependency:go-offline

COPY analytics-service analytics-service
COPY billing-service billing-service
COPY patient-service patient-service
COPY contracts contracts

RUN mvn -B -pl analytics-service -am clean package -DskipTests

# -------- Runtime stage --------
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /build/analytics-service/target/*.jar app.jar

EXPOSE 4002

ENTRYPOINT ["java","-jar","app.jar"]
