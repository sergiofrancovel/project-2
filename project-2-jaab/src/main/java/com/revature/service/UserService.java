package com.revature.service;

import com.revature.dao.DoctorRepository;
import com.revature.dao.PatientRepository;
import com.revature.dao.UserRepository;
import com.revature.model.Doctor;
import com.revature.model.Patient;
import com.revature.model.Role;
import com.revature.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Autowired
    public void setDoctorRepository(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    /**
     * Creates a new patient and adds them to the user and patient databases
     * @param user - The user to be added
     * @param patient - The patient to be added
     */
    public void createPatient(User user, Patient patient){
        user.setRole(Role.PATIENT);
        userRepository.save(user);
        BeanUtils.copyProperties(user, patient);
        patientRepository.save(patient);
    }

    /**
     * Creates a new doctor and adds them to the user and doctor databases
     * @param user - The user to be added
     * @param doctor - The doctor to be added
     */
    public void createDoctor(User user, Doctor doctor){
        user.setRole(Role.PHYSICIAN);
        userRepository.save(user);
        BeanUtils.copyProperties(user, doctor);
        doctorRepository.save(doctor);

    }
}
