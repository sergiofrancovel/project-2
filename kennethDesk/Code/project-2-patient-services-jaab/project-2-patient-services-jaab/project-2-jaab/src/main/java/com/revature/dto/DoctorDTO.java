package com.revature.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String specialty;
}
