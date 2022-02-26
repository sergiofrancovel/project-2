package com.revature.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.revature.model.Doctor;
import com.revature.model.Patient;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
