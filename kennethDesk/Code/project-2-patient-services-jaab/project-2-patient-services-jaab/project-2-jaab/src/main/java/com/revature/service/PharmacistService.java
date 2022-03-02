package com.revature.service;

import com.revature.dao.*;
import com.revature.dto.PharmacistDTO;
import com.revature.model.Pharmacist;
import com.revature.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PharmacistService {
    private final Logger logger = LoggerFactory.getLogger(PharmacistService.class);
    @Autowired
    PharmacistRepository pharmacistRepository;

    public Pharmacist createPharmacist(PharmacistDTO email) throws Exception{
        Pharmacist newPharm = new Pharmacist();
        Pharmacist checkEmail = pharmacistRepository.findByEmail(email.getEmail());
        if (checkEmail != null){
            logger.info("email already exist");
            throw new Exception();
        } else{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(email.getPassword());

            newPharm.setId(email.getId());
            newPharm.setEmail(email.getEmail());
            newPharm.setFirstName(email.getFirstName());
            newPharm.setLastName(email.getLastName());
            newPharm.setPassword(encodedPassword);
            pharmacistRepository.save(newPharm);
            logger.info("Pharmacist was added successfully");
        }
        return newPharm;
        }
    }

