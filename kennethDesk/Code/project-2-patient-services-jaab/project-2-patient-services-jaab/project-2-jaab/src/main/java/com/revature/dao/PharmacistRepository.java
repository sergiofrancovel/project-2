package com.revature.dao;

import com.revature.model.Doctor;
import com.revature.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Integer> {
    Pharmacist findByEmail(String email);
}