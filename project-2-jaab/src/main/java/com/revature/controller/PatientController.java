package com.revature.controller;

import com.revature.dto.PatientDTO;
import com.revature.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/doctors/patientInfo/{firstName}/{lastName}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> getPatientByName(@PathVariable String firstName, @PathVariable String lastName) {
        PatientDTO patientDTO = patientService.getPatientByName(firstName, lastName);
        return ResponseEntity.ok(patientDTO);
    }

    @PatchMapping(value = "/patients/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> updateEmail(@PathVariable Integer patientId, @RequestBody PatientDTO patientDTO){
        patientDTO = patientService.updateEmail(patientId, patientDTO.getEmail());
        return ResponseEntity.ok(patientDTO);
    }
}
