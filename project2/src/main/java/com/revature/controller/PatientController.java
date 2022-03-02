package com.revature.controller;

import com.revature.dto.PatientDTO;
import com.revature.model.Patient;
import com.revature.model.User;
import com.revature.service.PatientService;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class PatientController {

    private final PatientService patientService;
    private UserService userService;

    //autowired changed for front end user addition
    @Autowired
    public PatientController(PatientService patientService, UserService userService) {
        this.patientService = patientService;
        this.userService = userService;
    }


    @GetMapping(value = "/doctors/patientInfo/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> getPatientByid(String firstName, String lastName) throws URISyntaxException {

        PatientDTO patientDTO = patientService.getPatientByName(firstName, lastName);
        Integer patientId = patientDTO.getId();

        return ResponseEntity.created(new URI("http://localhost:8880/docors/patientInfo/" + patientId)).build();
    }

    @PatchMapping(value = "/patients/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> updateEmail(@PathVariable Integer patientId, String email){

        patientService.updateEmail(patientId, email);

        return ResponseEntity.ok().build();
    }

    // start of Joseph code
    @GetMapping(value = "/doctors/patientInfo/{firstName}/{lastName}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> getPatientByName(@PathVariable String firstName, @PathVariable String lastName) {
        PatientDTO patientDTO = patientService.getPatientByName(firstName, lastName);
        return ResponseEntity.ok(patientDTO);
    }

//    @PatchMapping(value = "/patients/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<PatientDTO> updateEmailByid(@PathVariable Integer patientId, @RequestBody PatientDTO patientDTO){
//        patientService.updateEmail(patientId, patientDTO.getEmail());
//        return ResponseEntity.ok(patientDTO);
//    }
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

}
