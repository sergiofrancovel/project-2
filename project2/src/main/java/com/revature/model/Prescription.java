package com.revature.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "prescription")
public class Prescription {
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

    @Column(name = "medicine_name", nullable = false)
    private String medicineName;

    @Column(name = "cc", nullable = false)
    private Float dosage;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}