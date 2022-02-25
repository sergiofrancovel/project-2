package com.revature.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDetails {
    private int doctor_id;
    private int patient_id;
    private String schedule;
    @JsonFormat(pattern ="dd/mm/yyyy")
    private Date appointmentTime;
}
