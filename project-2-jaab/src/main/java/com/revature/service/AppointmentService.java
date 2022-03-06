package com.revature.service;

import com.revature.dao.AppointmentRepository;
import com.revature.dao.DoctorRepository;
import com.revature.dao.PatientRepository;
import com.revature.model.Appointment;
import com.revature.model.Doctor;
import com.revature.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    @Autowired
    public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Autowired
    public void setDoctorRepository(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void createNewAppointment(Appointment appointment, Integer doctorId, String firstName, String lastName){
        Doctor doctor = doctorRepository.getById(doctorId);
        Patient patient = patientRepository.getPatientByFirstNameAndLastName(firstName, lastName);

        appointment.setDoctorId(doctor.getId());
        appointment.setPatientId(patient.getId());

        appointmentRepository.save(appointment);
    }
}
