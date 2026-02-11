package org.neev.events;

import java.io.Serializable;

public record PatientCreatedEvent(
        String patientId,
        String name
) implements Serializable {
}
