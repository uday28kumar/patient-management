package org.neev.patientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Patient Service. This service is responsible for managing patient information,
 * including creating, retrieving, updating, and deleting patient records. It also listens to relevant events
 * from Kafka to keep the patient data up-to-date.
 */
@SpringBootApplication
public class PatientServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApp.class, args);
	}

}
