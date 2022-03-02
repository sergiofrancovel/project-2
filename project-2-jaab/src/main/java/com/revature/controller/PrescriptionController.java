package com.revature.controller;

import com.revature.dto.DoctorDTO;
import com.revature.dto.PatientDTO;
import com.revature.model.Prescription;
import com.revature.service.DoctorService;
import com.revature.service.PatientService;
import com.revature.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService, DoctorService doctorService,
                                  PatientService patientService) {
        this.prescriptionService = prescriptionService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("/pharmacy")
    public String prescriptionRequestsList(Model model){
        Set<Prescription> unapprovedPrescriptions = prescriptionService.getAllUncheckedPrescriptions();
        model.addAttribute("unapprovedPrescriptions", unapprovedPrescriptions);
        return "pharmacist/pharmacist_home";
    }

    @GetMapping("/doctor/{doctorId}/pharmacyRequest")
    public String newPharmacyRequestForm(Model model, @PathVariable Integer doctorId){
        Prescription prescription = new Prescription();
        DoctorDTO doctorDTO = doctorService.getDoctorById(doctorId);
        PatientDTO patientDTO = new PatientDTO();
        model.addAttribute("doctorDTO", doctorDTO);
        model.addAttribute("patientDTO", patientDTO);
        model.addAttribute("prescription", prescription);
        return "doctor/pharmacy_request";
    }

    @PostMapping("/doctor/{doctorId}/pharmacyRequest")
    public String pharmacyRequest(@PathVariable Integer doctorId,
                                  @ModelAttribute("prescription") Prescription prescription,
                                  @ModelAttribute("patientDTO") PatientDTO patientDTO){
        prescriptionService.prescriptionRequest(prescription, doctorId, patientDTO.getFirstName(),
                patientDTO.getLastName());
        return "prescription_success";
    }
}
