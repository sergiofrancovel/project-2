package com.example.email.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientNoteDTO {
    private int id;
    private String doctor;
    private String email;
    private String patient;
    private String notes;
}

