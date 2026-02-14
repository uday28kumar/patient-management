# Patient Management

## Overview
A Spring Boot service (Java, Maven) that manages patient records and persists data to an SQL database. Designed to run locally or inside containers.

## Tech stack
- Java 17+
- Spring Boot
- Maven
- Relational database (PostgreSQL / MySQL)
- Optional: Docker & Docker Compose

## Prerequisites
- Java JDK installed
- Maven installed
- Docker and Docker Compose (for containerized runs)
- Database credentials available

## Build & run locally
Use Maven to build and run the application in development mode:
```bash
    mvn clean package
    mvn spring-boot:run
```

## Run with Docker
Build the Docker image:
```bash
    # Stop and remove containers and volumes
    docker-compose down -v
    
    # (optional) remove unused images and build cache
    docker system prune -af
    
    # Rebuild images and start in background
    docker-compose build --no-cache
    docker-compose up -d
```
   