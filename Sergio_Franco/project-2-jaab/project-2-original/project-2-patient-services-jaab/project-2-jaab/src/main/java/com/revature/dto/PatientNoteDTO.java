package com.revature.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientNoteDTO {
    private int id;

    private String doctor;


    private String patient;


    private Date dateCreated;


    private String notes;
}
