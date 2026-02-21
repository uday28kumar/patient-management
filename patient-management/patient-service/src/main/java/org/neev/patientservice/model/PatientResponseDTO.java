package org.neev.patientservice.model;

/**
 * Data Transfer Object (DTO) for representing patient information in responses. This class contains fields for the patient's ID, name,
 * email, address, and date of birth (DOB). It includes getter and setter methods for each field, allowing for easy access and modification
 * of the patient's information when sending responses to clients.
 */
public class PatientResponseDTO {
    private String id;

    private String name;

    private String email;

    private String address;

    private String dob;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
