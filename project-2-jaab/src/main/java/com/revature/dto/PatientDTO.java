package com.revature.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String primaryDoctor;
    private Long phoneNumber;
    private String bloodType;
}
