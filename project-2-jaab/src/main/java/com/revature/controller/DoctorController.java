package com.revature.controller;

import com.revature.dto.DoctorDTO;
import com.revature.dto.PatientDTO;
import com.revature.model.Doctor;
import com.revature.model.User;
import com.revature.service.DoctorService;
import com.revature.service.PatientService;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorController {

    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @Autowired
    public DoctorController(UserService userService, DoctorService doctorService, PatientService patientService) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("/newDoctor")
    public String newDoctorForm(Model model){
        User user = new User();
        Doctor doctor = new Doctor();
        model.addAttribute("user", user);
        model.addAttribute("doctor", doctor);
        return "doctor/new_doctor";
    }

    @PostMapping("/newDoctor")
    public String createNewDoctor(@ModelAttribute("user") User user, @ModelAttribute("doctor") Doctor doctor){
        userService.createDoctor(user, doctor);
        return "register_success";
    }

    @GetMapping("/doctor/{doctorId}")
    public String loadDoctorHome(Model model, @PathVariable Integer doctorId){
        DoctorDTO doctorDTO = doctorService.getDoctorById(doctorId);
        model.addAttribute("doctorDTO", doctorDTO);
        return "doctor/doctor_home";
    }

    @GetMapping("/doctor/{doctorId}/patientSearch")
    public String loadPatientSearchForm(Model model, @PathVariable Integer doctorId){
        DoctorDTO doctorDTO = doctorService.getDoctorById(doctorId);
        PatientDTO patientDTO = new PatientDTO();
        model.addAttribute("doctorDTO", doctorDTO);
        model.addAttribute("patientDTO", patientDTO);
        return "doctor/patient_search";
    }

    @PostMapping(value = "/doctor/{doctorId}/patientSearch")
    public String getPatientData(Model model, @ModelAttribute("patientDTO") PatientDTO patientDTO,
                                 @PathVariable Integer doctorId) {
        DoctorDTO doctorDTO = doctorService.getDoctorById(doctorId);
        PatientDTO patientData = patientService.getPatientByName(patientDTO.getFirstName(), patientDTO.getLastName());
        model.addAttribute("doctorDTO", doctorDTO);
        model.addAttribute("patientData", patientData);
        return "doctor/patient_search_result";
    }

}
