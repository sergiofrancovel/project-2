package com.revature.dao;

import com.revature.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

    @Query("FROM Prescription p where p.status = 'PENDING'")
    Set<Prescription> getAllUnCheckedPrescriptions();
}