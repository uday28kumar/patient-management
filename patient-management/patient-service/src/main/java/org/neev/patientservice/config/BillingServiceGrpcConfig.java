package org.neev.patientservice.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BillingServiceGrpcConfig {
    @Value("${billing.grpc.host}")
    private String host;

    @Value("${billing.grpc.port}")
    private int port;

    @Bean
    ManagedChannel billingServiceChannel(){
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
    }
}
