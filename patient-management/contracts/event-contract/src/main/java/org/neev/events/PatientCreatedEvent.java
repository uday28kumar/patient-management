package org.neev.events;

import java.io.Serializable;

/**
 * Event representing the creation of a new patient. This event is published to Kafka when a new patient is created
 * in the Patient Service, allowing other services (like Analytics Service) to react to this event and update their
 * data accordingly.
 */
public record PatientCreatedEvent(
        String patientId,
        String name
) implements Serializable {
}
