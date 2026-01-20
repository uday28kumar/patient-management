# -------- Build stage --------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

COPY pom.xml .
COPY billing-service/pom.xml billing-service/pom.xml
COPY patient-service/pom.xml patient-service/pom.xml
COPY grpc-contract/pom.xml grpc-contract/pom.xml

RUN mvn -B -pl billing-service -am dependency:go-offline

COPY billing-service billing-service
COPY patient-service patient-service
COPY grpc-contract grpc-contract

RUN mvn -B -pl billing-service -am clean package -DskipTests

# -------- Runtime stage --------
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /build/billing-service/target/*.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","app.jar"]
