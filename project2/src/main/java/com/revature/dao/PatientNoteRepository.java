package com.revature.dao;

import com.revature.model.Med_records;
import com.revature.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PatientNoteRepository extends JpaRepository<Med_records, Integer> {
 @Query(value = "select id, date_of_note ,notes,recording_doctor, patient from med_records where patient = ?1", nativeQuery = true)
    List<Med_records> findPatientById(int id);

    @Query(value = "select id, notes,recording_doctor, patient  \n" +
            "from med_records where recording_doctor = ?1", nativeQuery = true)
    List<Med_records> findByRecordingDoctor(int id);

    @Query("FROM Patient p where p.firstName = :firstName and  p.lastName = :lastName")
    Patient getPatientByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Modifying
    @Query("update Patient  p set p.email = :email where p.id = :patientId")
    void updateEmail(@Param("patientId") Integer patientId, @Param("email") String email);

}
