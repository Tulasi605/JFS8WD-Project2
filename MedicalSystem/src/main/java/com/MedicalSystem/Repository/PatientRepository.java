package com.MedicalSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MedicalSystem.Model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	Patient findByEmailAndPassword(String email, String password);

	Patient findByEmail(String email);   
}
