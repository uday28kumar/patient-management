package org.neev.patientservice.service;

import io.grpc.ManagedChannel;
import org.neev.billing.grpc.BillingRequest;
import org.neev.billing.grpc.BillingResponse;
import org.neev.billing.grpc.BillingServiceGrpc;
import org.springframework.stereotype.Service;

/**
 * gRPC client for communicating with the Billing Service. This client provides methods to create billing records
 * by making gRPC calls to the Billing Service. It uses a blocking stub to perform synchronous calls to the service.
 */
@Service
public class BillingGrpcClient {
    private final BillingServiceGrpc.BillingServiceBlockingStub stub;

    public BillingGrpcClient(ManagedChannel channel){
        this.stub = BillingServiceGrpc.newBlockingStub(channel);
    }

    /**
     * Creates a billing record by sending a gRPC request to the Billing Service. This method constructs a BillingRequest
     * with the provided id and name, and then calls the createBilling method on the stub to get a BillingResponse.
     *
     * @param id   the unique identifier for the billing record
     * @param name the name associated with the billing record
     * @return a BillingResponse containing the status of the billing creation operation
     */
    public BillingResponse createBilling(String id, String name){
        BillingRequest request = BillingRequest.newBuilder()
                .setId(id)
                .setName(name)
                .build();

        return stub.createBilling(request);
    }
}
