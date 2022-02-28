package com.revature.service;

import com.revature.dao.DoctorRepository;
import com.revature.dao.PatientRepository;
import com.revature.dao.UserRepository;
import com.revature.model.Patient;
import com.revature.model.Role;
import com.revature.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;

    @Value("${server.port}")
    int port;

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

    public void createPatient(Patient patient){
        User user = new User();
        patientRepository.save(patient);
        BeanUtils.copyProperties(patient, user);
        user.setRole(Role.PATIENT);
        userRepository.save(user);
    }
}
