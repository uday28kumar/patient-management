package org.neev.analyticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Main application class for the Analytics Service. This service listens to patient-related events from Kafka
 * and processes them for analytics purposes.
 */
@SpringBootApplication
@EnableKafka
public class AnalyticsServiceApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(AnalyticsServiceApp.class, args);
    }
}
