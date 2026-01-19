# To run PatientServiceApp
docker-compose down -v
docker builder prune -af
docker-compose build --no-cache patient-service
docker-compose up -d

