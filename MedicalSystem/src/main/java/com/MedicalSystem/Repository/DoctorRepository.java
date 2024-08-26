package com.MedicalSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MedicalSystem.Model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}