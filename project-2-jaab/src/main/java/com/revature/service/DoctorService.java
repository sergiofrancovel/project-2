package com.revature.service;

import com.revature.dao.DoctorRepository;
import com.revature.dto.DoctorDTO;
import com.revature.model.Doctor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public void setDoctorRepository(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorDTO getDoctorById(Integer id){
        Doctor doctor = doctorRepository.getById(id);
        return copyToDTO(doctor);
    }

    public DoctorDTO getDoctorByName(String firstName, String lastName){
        Doctor doctor = doctorRepository.getDoctorByFirstNameAndLastName(firstName, lastName);
        return copyToDTO(doctor);
    }

    public Set<DoctorDTO> getAllDoctors(){
        Set<Doctor> doctors = doctorRepository.getAllDoctors();

        return doctors.stream()
                .map(this::copyToDTO)
                .collect(Collectors.toSet());
    }

    private DoctorDTO copyToDTO(Doctor doctor){
        DoctorDTO doctorDTO = new DoctorDTO();
        BeanUtils.copyProperties(doctor, doctorDTO);
        return doctorDTO;
    }
}
