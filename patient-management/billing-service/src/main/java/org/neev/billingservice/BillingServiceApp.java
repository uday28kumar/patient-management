package org.neev.billingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Billing Service. This service handles all billing-related operations, including
 * creating bills, retrieving billing information, and managing payment records. It also listens to relevant events
 * from Kafka to keep the billing data up-to-date.
 */
@SpringBootApplication
public class BillingServiceApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(BillingServiceApp.class, args);
    }
}
