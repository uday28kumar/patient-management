# -------- Build stage --------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

# 1️⃣ Copy parent pom
COPY pom.xml .

# 2️⃣ Copy ALL module poms (even placeholders!)
COPY patient-service/pom.xml patient-service/pom.xml
COPY billing-service/pom.xml billing-service/pom.xml

# 3️⃣ Download dependencies (reactor is now valid)
RUN mvn -B -pl patient-service -am dependency:go-offline

# 4️⃣ Copy actual source code
COPY patient-service patient-service
COPY billing-service billing-service

# 5️⃣ Build only patient-service
RUN mvn -B -pl patient-service -am clean package -DskipTests

# -------- Runtime stage --------
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /build/patient-service/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
