package com.neev.patient_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PatientRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "name can't exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Date of birth is required")
    private String dob;

    public @NotBlank(message = "Name is required") @Size(max = 100, message = "name can't exceed 100 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") @Size(max = 100, message = "name can't exceed 100 characters") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Address is required") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address is required") String address) {
        this.address = address;
    }

    public @NotNull(message = "Date of birth is required") String getDob() {
        return dob;
    }

    public void setDob(@NotNull(message = "Date of birth is required") String dob) {
        this.dob = dob;
    }
}
