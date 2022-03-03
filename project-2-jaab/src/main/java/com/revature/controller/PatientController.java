package com.revature.controller;

import com.revature.dto.DoctorDTO;
import com.revature.dto.PatientDTO;
import com.revature.model.Email;
import com.revature.model.Patient;
import com.revature.model.User;
import com.revature.service.DoctorService;
import com.revature.service.PatientService;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    private final PatientService patientService;
    private final DoctorService doctorService;
    private final UserService userService;

    @Autowired
    public PatientController(PatientService patientService, DoctorService doctorService, UserService userService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.userService = userService;
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

    @GetMapping("/newPatient")
    public String newPatientForm(Model model){
        User user = new User();
        Patient patient = new Patient();
        model.addAttribute("user", user);
        model.addAttribute("patient", patient);
        return "patient/new_patient";
    }

    @PostMapping("/newPatient")
    public String createNewPatient(@ModelAttribute("user") User user, @ModelAttribute("patient") Patient patient){
        userService.createPatient(user, patient);
        return "register_success";
    }

    @GetMapping("/patient")
    public String loadPatientHome(){
        return "patient/patient_home";
    }

    @GetMapping("/patient/{patientId}/contactPhysician")
    public String loadEmailForm(Model model, @PathVariable Integer patientId){
        Email email = new Email();
        PatientDTO patientDTO = patientService.getPatientById(patientId);
        DoctorDTO doctorDTO = new DoctorDTO();
        model.addAttribute("email", email);
        model.addAttribute("doctorDTO", doctorDTO);
        model.addAttribute("patientDTO", patientDTO);
        return "patient/contact_doctor";
    }

    @PostMapping("/patient/{patientId}/contactPhysician")
    public ResponseEntity<Email> sendEmail(@PathVariable Integer patientId,
                                           String firstName, String lastName){
        PatientDTO patientDTO = patientService.getPatientById(patientId);
        DoctorDTO doctorDTO = doctorService.getDoctorByName(firstName, lastName);
        patientService.contactPhysician(patientDTO, doctorDTO);
        return ResponseEntity.ok().build();
    }
}
