package com.revature.dto;

import com.revature.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailPrescriptionDTO {
    private String doctor;
    private String patient;
    private String medicineName;
    private Float dosage;
    private Status status;
    private String pharmacist_email;
    private String patientEmail;
}
