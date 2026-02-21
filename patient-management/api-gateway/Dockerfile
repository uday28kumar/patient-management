# -------- Build stage --------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

# Copy root pom
COPY pom.xml .

# Copy service poms
COPY api-gateway/pom.xml api-gateway/pom.xml
COPY billing-service/pom.xml billing-service/pom.xml
COPY patient-service/pom.xml patient-service/pom.xml
COPY analytics-service/pom.xml analytics-service/pom.xml

# Copy contracts aggregator + contracts poms
COPY contracts/pom.xml contracts/pom.xml
COPY contracts/grpc-contract/pom.xml contracts/grpc-contract/pom.xml
COPY contracts/event-contract/pom.xml contracts/event-contract/pom.xml
COPY contracts/util-contract/pom.xml contracts/util-contract/pom.xml

RUN mvn -B -pl api-gateway -am dependency:go-offline

COPY api-gateway api-gateway
COPY billing-service billing-service
COPY patient-service patient-service
COPY analytics-service analytics-service
COPY contracts contracts

RUN mvn -B -pl api-gateway -am clean package -DskipTests

# -------- Runtime stage --------
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /build/api-gateway/target/*.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","app.jar"]
