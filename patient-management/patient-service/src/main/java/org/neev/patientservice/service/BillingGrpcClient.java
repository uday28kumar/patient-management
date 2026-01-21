package org.neev.patientservice.service;

import io.grpc.ManagedChannel;
import org.neev.billing.grpc.BillingRequest;
import org.neev.billing.grpc.BillingResponse;
import org.neev.billing.grpc.BillingServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class BillingGrpcClient {
    private final BillingServiceGrpc.BillingServiceBlockingStub stub;

    public BillingGrpcClient(ManagedChannel channel){
        this.stub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBilling(String id, String name){
        BillingRequest request = BillingRequest.newBuilder()
                .setId(id)
                .setName(name)
                .build();

        return stub.createBilling(request);
    }
}
