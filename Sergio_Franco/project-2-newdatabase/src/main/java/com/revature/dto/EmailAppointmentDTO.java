package com.revature.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailAppointmentDTO {
    private String patientEmail;
    private String doctorfn;
    private String doctorln;
    private String patientfn;
    private String patientln;
    private String schedule;
    private Date appointmentTime;
}
