package com.revature.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "primary_doctor")
    private Doctor doctor;

    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;

    @Column(name = "blood_type", nullable = false)
    private String bloodType;

    @Column(name = "patient_notes")
    private String notes;

    @Column(name = "role", nullable = false)
    private Role role;
}