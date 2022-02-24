package com.revature.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notes {
    private int doctor_id;
    private int patient_id;
    private String notes;


}
