package org.neev.analyticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * AnalyticsServiceApp
 *
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
