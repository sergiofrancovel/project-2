package com.revature.controller;


import com.revature.dto.*;
import com.revature.model.Appointment;
import com.revature.model.Doctor;
import com.revature.model.Patient;
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
import java.util.Date;
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
    public ResponseEntity doctorNote(@RequestBody Notes notes)  {
        Patient patient = doctorService.medicalRecords(notes);
        Doctor doctor = doctorService.getDoctorById(notes.getDoctor_id());
        EmailNoteDTO noteDTO = new EmailNoteDTO(patient.getId(),patient.getEmail(),doctor.getLastName(), patient.getFirstName(), notes.getNotes());
            if(patient!=null) {
                restTemplate.postForEntity("http://localhost:8081/email/newpatientnote", noteDTO, null);
                return ResponseEntity.status(HttpStatus.OK).body("note submitted successfully");
            }else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("doctors id or patient id is incorrect");
            }
    }

    @PostMapping(value ="/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
            public ResponseEntity doctorAppointment(@RequestBody DoctorDetails d){
        Appointment appointment = doctorService.doctorAppointment(d);
        Optional<Patient> p = doctorService.findPatientbyId(d.getPatient_id());
        EmailAppointmentDTO appointmentDTO = new EmailAppointmentDTO(p.get().getEmail(),appointment.getDoctor().getFirstName(),
                appointment.getDoctor().getLastName(), appointment.getPatient().getFirstName(), appointment.
                getPatient().getLastName(), appointment.getSchedule(), appointment.getAppointmentTime());
        System.out.println(appointmentDTO);
        if(appointment.getDoctor() !=null && appointment.getPatient()!=null){
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


}
