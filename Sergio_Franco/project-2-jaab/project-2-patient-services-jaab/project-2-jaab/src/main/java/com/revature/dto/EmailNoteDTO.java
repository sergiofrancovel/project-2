package com.revature.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailNoteDTO {
    private int id;
    private String email;
    private String doctor;
    private String patient;
    private String notes;
}
