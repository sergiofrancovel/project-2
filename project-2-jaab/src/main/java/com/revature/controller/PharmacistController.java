package com.revature.controller;

import com.revature.dto.DoctorDTO;
import com.revature.dto.PatientDTO;
import com.revature.model.Pharmacist;
import com.revature.model.Prescription;
import com.revature.model.Status;
import com.revature.model.User;
import com.revature.service.DoctorService;
import com.revature.service.PatientService;
import com.revature.service.PrescriptionService;
import com.revature.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class PharmacistController {

    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final PrescriptionService prescriptionService;

    @Autowired
    public PharmacistController(UserService userService, DoctorService doctorService, PatientService patientService,
                                PrescriptionService prescriptionService) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/newPharmacist")
    public String newPharmacistForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "pharmacist/new_pharmacist";
    }

    @PostMapping("/newPharmacist")
    public String createNewPharmacist(@ModelAttribute("user") User user, Pharmacist pharmacist){
        userService.createPharmacist(user, pharmacist);
        return "register_success";
    }

    @GetMapping("/pharmacy")
    public String prescriptionRequestsList(Model model){
        Set<Prescription> unapprovedPrescriptions = prescriptionService.getAllUncheckedPrescriptions();
        model.addAttribute("unapprovedPrescriptions", unapprovedPrescriptions);
        return "pharmacist/pharmacist_home";
    }

    @GetMapping("/pharmacy/approveRequests/{prescriptionId}")
    public String loadPrescriptionRequest(Model model, @PathVariable Integer prescriptionId){
        Prescription prescription = prescriptionService.getPrescriptionById(prescriptionId);
        DoctorDTO doctorDTO = doctorService.getDoctorById(prescription.getDoctorId());
        PatientDTO patientDTO = patientService.getPatientById(prescription.getPatientId());
        model.addAttribute("prescription", prescription);
        model.addAttribute("doctorDTO", doctorDTO);
        model.addAttribute("patientDTO", patientDTO);
        return "pharmacist/prescription_approval";
    }

    @PatchMapping("/pharmacy/approveRequests/{prescriptionId}")
    public String approvePrescriptionRequest(@ModelAttribute("prescription") Prescription prescription,
                                             @PathVariable Integer prescriptionId, Status status){
        Prescription getPrescription = prescriptionService.getPrescriptionById(prescriptionId);
        prescriptionService.updateStatus(getPrescription.getId(), status);
        BeanUtils.copyProperties(getPrescription, prescription);
        return "pharmacist/prescription_approved";
    }
}
