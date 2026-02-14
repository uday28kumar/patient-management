package org.neev.patientservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.neev.events.PatientCreatedEvent;
import org.neev.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PatientEventPublisher {
    private static final Logger log = LoggerFactory.getLogger(PatientEventPublisher.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PatientEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPatientCreated(Patient patient) {
        PatientCreatedEvent event = new PatientCreatedEvent(patient.getId().toString(), patient.getName());
        try {
            byte[] data = objectMapper.writeValueAsBytes(event);
            kafkaTemplate.send("patient-events", patient.getId().toString(), data)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            log.error("Failed to publish event for patientId={}", event.patientId());
                        } else {
                            log.info("Event published successfully to topic={} partition={} offset={}",
                                    result.getRecordMetadata().topic(),
                                    result.getRecordMetadata().partition(),
                                    result.getRecordMetadata().offset());
                        }
                    });
        } catch (Exception e) {
            log.error("Failed to publish event for patientId={}", event.patientId(), e);
        }
    }
}
