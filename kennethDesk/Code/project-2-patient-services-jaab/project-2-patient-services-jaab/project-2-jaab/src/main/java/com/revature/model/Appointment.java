package com.revature.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "requesting_doctor")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_for")
    private Patient patient;

    @Column(name = "schedule", nullable = false)
    private String schedule;

    @JsonFormat(pattern ="dd/mm/yyyy")
    @Column(name = "time_of_appointment")
    private Date appointmentTime;

}