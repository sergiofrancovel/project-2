package com.example.email.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailAppointmentDTO {
    private Integer id;
    private String doctorfn;
    private String doctorln;
    private String patientfn;
    private String patientln;
    private String schedule;
    private Long appointmentTime;
}
