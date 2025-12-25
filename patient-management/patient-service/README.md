# To run PatientServiceApp
- docker compose down -v
- mvn clean package -DskipTests
- docker compose up --build
