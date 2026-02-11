package org.neev.billingservice.kafka;

import org.neev.events.PatientCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PatientEventListener {

    private static final Logger log = LoggerFactory.getLogger(PatientEventListener.class);

    @KafkaListener(topics = "patient-events", groupId = "billing-service-group")
    public void onPatientCreated(PatientCreatedEvent event) {
        log.info("Received patient created event: {}", event);
    }
}
