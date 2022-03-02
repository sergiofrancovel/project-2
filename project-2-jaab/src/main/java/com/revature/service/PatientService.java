package com.revature.service;

import com.revature.dao.PatientRepository;
import com.revature.dto.DoctorDTO;
import com.revature.dto.PatientDTO;
import com.revature.model.Email;
import com.revature.model.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Set<PatientDTO> getAllPatients(){
        Set<Patient> patients = patientRepository.getAllPatients();

        return patients.stream()
                .map(this::copyToDTO)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves patient data based on their first and last name
     * @param firstName - The patient's first name
     * @return - PatientDTO
     */
    public PatientDTO getPatientByName(String firstName, String lastName){
        PatientDTO patientDTO = new PatientDTO();
        Patient patient = patientRepository.getPatientByFirstNameAndLastName(firstName, lastName);
        BeanUtils.copyProperties(patient, patientDTO);
        return patientDTO;
    }

    /**
     * Retrieves patient data based on their id
     * @param id - The id of the patient
     * @return - PatientDTO
     */
    public PatientDTO getPatientById(Integer id){
        PatientDTO patientDTO = new PatientDTO();
        Patient patient = patientRepository.getById(id);
        BeanUtils.copyProperties(patient, patientDTO);
        return patientDTO;
    }

    /**
     * Allows a patient to update their email
     * @param email - The new email
     */
    public PatientDTO updateEmail(Integer id, String email){
        PatientDTO patientDTO = new PatientDTO();
        Patient patient = patientRepository.getById(id);
        patientRepository.updateEmail(patient.getId(), email);
        BeanUtils.copyProperties(patient, patientDTO);
        return patientDTO;
    }

    public void contactPhysician(PatientDTO patientDTO, DoctorDTO doctorDTO){
        Email email = new Email();
        email.setSender(patientDTO.getEmail());
        email.setRecipient(doctorDTO.getEmail());
    }

    private PatientDTO copyToDTO(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        BeanUtils.copyProperties(patient, patientDTO);
        return patientDTO;
    }
}
