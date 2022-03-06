package com.example.email.controllers;

import com.example.email.dto.EmailAppointmentDTO;
import com.example.email.dto.EmailNoteDTO;
import com.example.email.dto.EmailPrescriptionDTO;
import com.example.email.services.EmailTemplates;
import com.example.email.services.EmailServiceIntImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailServiceIntImpl service;

    @PostMapping(value = "newappointment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewAppointment(@RequestBody EmailAppointmentDTO emailAppointmentDTO){
        EmailTemplates emailTemplates = new EmailTemplates();
        emailTemplates.setNewAppointment(emailAppointmentDTO);
        service.sendSimpleMessageForOrder(emailAppointmentDTO.getPatientEmail(), emailTemplates.getNewAppointment(),
                "Your new appointment has been scheduled");
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "newpatientnote", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewDoctorNote(@RequestBody EmailNoteDTO noteDTO){
        EmailTemplates emailTemplates = new EmailTemplates();
        emailTemplates.createPatientNote(noteDTO);
        service.sendSimpleMessageForOrder(noteDTO.getEmail(), emailTemplates.getPatientNote(),
                "Your patient notes have been updated");
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "newprescription", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewPrescription(@RequestBody EmailPrescriptionDTO noteDTO){
        EmailTemplates emailTemplates = new EmailTemplates();
        emailTemplates.createPrescription(noteDTO);
        service.sendSimpleMessageForOrder(noteDTO.getPatientEmail(), emailTemplates.getPrescription(),
                "Your patient notes have been updated");
        return ResponseEntity.noContent().build();
    }
}
