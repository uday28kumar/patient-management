package org.neev.apigateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the API Gateway. This service acts as a single entry point for all client requests,
 * routing them to the appropriate backend services (Patient Service, Appointment Service, Billing Service, etc.)
 * and handling cross-cutting concerns like authentication, logging, and rate limiting.
 */
@SpringBootApplication
public class ApiGatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApp.class, args);
    }
}
