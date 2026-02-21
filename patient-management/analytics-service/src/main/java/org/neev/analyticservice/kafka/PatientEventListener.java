package org.neev.analyticservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neev.events.PatientCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * Listens to patient-related events from Kafka and processes them for analytics purposes.
 */
@Service
public class PatientEventListener {
    private static final Logger log = LoggerFactory.getLogger(PatientEventListener.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Handles incoming patient creation events from the "patient-events" topic.
     *
     * @param payload the raw message payload received from Kafka
     */
    @KafkaListener(topics = "patient-events", groupId = "analytics-service-group")
    public void onPatientCreated(byte[] payload) {
        try {
            String json = new String(payload, StandardCharsets.UTF_8);

            PatientCreatedEvent event = objectMapper.readValue(json, PatientCreatedEvent.class);

            log.info("Received PatientCreatedEvent for patientId={}", event.patientId());

        } catch (Exception e) {
            log.warn("Invalid or unexpected message received. Ignoring. Size={} bytes", payload.length);
        }
    }
}
