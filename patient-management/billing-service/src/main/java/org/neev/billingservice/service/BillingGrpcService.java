package org.neev.billingservice.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.neev.billing.grpc.BillingRequest;
import org.neev.billing.grpc.BillingResponse;
import org.neev.billing.grpc.BillingServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * gRPC service implementation for handling billing-related requests. This service defines the logic for processing
 * billing operations such as creating bills, retrieving billing information, and managing payment records.
 */
@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    /**
     * Handles incoming billing creation requests. This method processes the billing information provided in the request
     * and generates a response indicating the success or failure of the operation.
     *
     * @param request          the billing request containing necessary information for creating a bill
     * @param responseObserver the stream observer used to send back the billing response to the client
     */
    @Override
    public void createBilling(BillingRequest request, StreamObserver<BillingResponse> responseObserver) {
        log.info("Request = {}", request.toString());
        BillingResponse response = BillingResponse.newBuilder()
                .setStatus("OK")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
