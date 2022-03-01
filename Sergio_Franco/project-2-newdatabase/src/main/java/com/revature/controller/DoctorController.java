package com.revature.controller;


import com.revature.dto.*;
import com.revature.model.Appointment;
import com.revature.model.Doctor;
import com.revature.model.Patient;
import com.revature.model.Prescription;
import com.revature.service.DoctorService;
import com.revature.utils.DoctorDetails;
import com.revature.utils.Notes;
import com.revature.utils.PatientResponse;
import com.revature.utils.Patientid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.print.Doc;
import java.util.Optional;

@RestController
@RequestMapping("/hospital")
public class DoctorController {

    @Autowired
    DoctorService doctorService;
    @Autowired
    RestTemplate restTemplate;


    @PostMapping(value = "/addDoctor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addDoctor(@RequestBody DoctorDTO em) {
        try {
            doctorService.saveDoctor(em);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully created");
        } catch (Exception e) {

            e.getMessage();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("email already exist");
        }


    }
    @PostMapping(value = "/doctorNote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity doctorNote(@RequestBody Notes notes) throws Exception {
        Patient patient = doctorService.medicalRecords(notes);
        Doctor doctor = doctorService.getDoctorById(notes.getDoctor_id());
        EmailNoteDTO noteDTO = new EmailNoteDTO(patient.getId(),patient.getEmail(),doctor.getLastName(), patient.getFirstName(), notes.getNotes());
        try {
            doctorService.medicalRecords(notes);
            restTemplate.postForEntity("http://localhost:8081/email/newpatientnote", noteDTO, null);
            return ResponseEntity.status(HttpStatus.OK).body("note submitted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("doctors id or patient id is incorrect");
        }
    }

    @PostMapping(value ="/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
            public ResponseEntity doctorAppointment(@RequestBody DoctorDetails d){
        Appointment appointment = doctorService.doctorAppointment(d);
        Doctor doctor = doctorService.getDoctorById(d.getDoctor_id());
        Optional<Patient> p = doctorService.findPatientbyId(d.getPatient_id());
        EmailAppointmentDTO appointmentDTO = new EmailAppointmentDTO(p.get().getEmail(), doctor.getFirstName(),
                doctor.getLastName(), p.get().getFirstName(), p.
                get().getLastName(), appointment.getSchedule(), appointment.getAppointmentTime());
        if(appointment.getDoctor()!=0){
            restTemplate.postForEntity("http://localhost:8081/email/newappointment", appointmentDTO, null);
            return ResponseEntity.status(HttpStatus.OK).body("Appointment is sent");
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("doctors id or patient id is incorrect");
        }
    }

    @GetMapping(value = "/patientRecords", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userAccount(@RequestBody Patientid em) {


        try {
            PatientResponse response = doctorService.accessPatientRecords(em);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(Exception e){
             e.getMessage();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid patient id");
        }


    }
    @PostMapping(value = "/prescription", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity prescription(@RequestBody PrescriptionDTO dto) throws Exception {
        Prescription prep = doctorService.prescription(dto);
        Optional<Patient> p = doctorService.findPatientbyId(dto.getPatient_id());
        Doctor d = doctorService.getDoctorById(dto.getDoctor_id());
        EmailPrescriptionDTO emailPrescriptionDTO = new EmailPrescriptionDTO(d.getLastName(), p.get().getFirstName(),
                prep.getMedicineName(), prep.getDosage(), prep.getStatus(), prep.getPharmacistEmail(),
                p.get().getEmail());
        if (prep.getDoctor() == dto.getDoctor_id() && prep.getPatient() ==dto.getPatient_id()){

            restTemplate.postForEntity("http://localhost:8081/email/newprescription", emailPrescriptionDTO, null);
            return ResponseEntity.status(HttpStatus.OK).body("prescription is sent");
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid credentials");
        }
    }


}
