package com.example.email.services;

import com.example.email.dto.EmailAppointmentDTO;
import com.example.email.dto.EmailNoteDTO;
import com.example.email.dto.EmailPrescriptionDTO;
import lombok.Getter;

@Getter
public class EmailTemplates {

    private String newAppointment;
    private String patientNote;
    private String prescription;


    public void setNewAppointment(EmailAppointmentDTO emailAppointmentDTO) {
        this.newAppointment = "Hello, " + emailAppointmentDTO.getPatientfn() + "!\n\nThis email confirms your appointment on " + emailAppointmentDTO.getSchedule() +
                " made at the time of " + emailAppointmentDTO.getAppointmentTime() + " with doctor " + emailAppointmentDTO.getDoctorfn() + " " +
                emailAppointmentDTO.getDoctorln() +
    "\nIf you'd like to make changes to your appointment, " +
                "reach back to your doctor or feel free to reply to this email\n\nHave a great day!";
    }

    public void createPatientNote(EmailNoteDTO noteDTO){
        this.patientNote = "Hello, " + noteDTO.getPatient() + "\n\nThis email is in regard with your recent apointment with Dr. " +
                noteDTO.getDoctor() + "\n\nDr. " + noteDTO.getDoctor() + " had the following notes about your recent appointment: " +
                "\n\t- " +noteDTO.getNotes() + "\n\n If you have any questions about your appointment, please refer to your doctor " +
                "with your patient id, " + noteDTO.getId()+ "\n\nHave a great day!";
    }

    public void createPrescription(EmailPrescriptionDTO noteDTO){
        this.prescription = "Hello, " + noteDTO.getPatient() + "\n\nYour doctor " + noteDTO.getDoctor() +
        " has prescribed you with new medication."
        + "To view the details of your medication, sign in to the Hopital website with your username" +
        " and password.\n\nHere is the current status of your prescription: "+ noteDTO.getStatus()+
        "\n\nIf you have any questions about your medication of the status of your medication, "+
        "please contact your pharmacist at "+ noteDTO.getPharmacist_email()+"\n\nHave a great day!";
    }

}
