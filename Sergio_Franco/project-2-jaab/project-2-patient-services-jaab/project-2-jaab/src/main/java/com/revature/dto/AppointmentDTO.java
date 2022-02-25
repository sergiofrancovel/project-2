package com.revature.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.revature.model.Doctor;
import com.revature.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Integer id;
    private String schedule;
    private String appointmentTime;
}
