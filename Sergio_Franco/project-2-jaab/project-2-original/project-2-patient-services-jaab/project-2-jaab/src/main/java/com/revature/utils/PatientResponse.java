package com.revature.utils;

import com.revature.dto.PatientNoteDTO;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class PatientResponse {
    @NonNull
    private String email;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private List<PatientNoteDTO> patientNotes;
}
