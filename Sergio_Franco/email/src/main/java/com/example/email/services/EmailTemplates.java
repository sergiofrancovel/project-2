package com.example.email.services;

import com.example.email.dto.EmailAppointmentDTO;
import com.example.email.dto.EmailNoteDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public class EmailTemplates {

    private String newAppointment;
    private String patientNote;


    public void setNewAppointment(EmailAppointmentDTO emailAppointmentDTO) {
        this.newAppointment = "Hello, " + emailAppointmentDTO.getPatientfn() + "!\n\nThis email confirms your appointment on " + emailAppointmentDTO.getSchedule() +
                " made at the time of " + emailAppointmentDTO.getAppointmentTime() + " with doctor " + emailAppointmentDTO.getDoctorfn() + " " +
                emailAppointmentDTO.getDoctorln() +
    "\nIf you'd like to make changes to your appointment, " +
                "reach back to your doctor or feel free to reply to this email";
    }

    public void createPatientNote(EmailNoteDTO noteDTO){
        this.patientNote = "Hello, " + noteDTO.getPatient() + "\n\nThis email is in regard with your recent apointment with Dr. " +
                noteDTO.getDoctor() + "\n\nDr. " + noteDTO.getDoctor() + " had the following notes about your recent appointment: " +
                "\n\t- " +noteDTO.getNotes() + "\n\n If you have any questions about your appointment, please refer to your doctor " +
                "with your appointment id, " + noteDTO.getId();
    }

}
