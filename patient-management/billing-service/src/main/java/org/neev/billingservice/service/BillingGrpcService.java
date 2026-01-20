package org.neev.billingservice.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.neev.billing.grpc.BillingRequest;
import org.neev.billing.grpc.BillingResponse;
import org.neev.billing.grpc.BillingServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

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
