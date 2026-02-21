package org.neev.patientservice.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up gRPC communication with the Billing Service. This class creates a ManagedChannel
 * bean that can be injected into other components (like controllers or services) to make gRPC calls to the Billing Service.
 */
@Configuration
public class BillingServiceGrpcConfig {
    @Value("${billing.grpc.host}")
    private String host;

    @Value("${billing.grpc.port}")
    private int port;

    /**
     * Creates a ManagedChannel bean for communicating with the Billing Service via gRPC.
     * The channel is configured to connect to the host and port specified in the application properties.
     *
     * @return a ManagedChannel for gRPC communication with the Billing Service
     */
    @Bean
    ManagedChannel billingServiceChannel(){
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
    }
}
