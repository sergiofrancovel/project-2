package com.revature.dto;

import com.revature.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrescriptionDTO {
    private Integer id;
    private String medicineName;
    private Float dosage;
    private Status status;
}
