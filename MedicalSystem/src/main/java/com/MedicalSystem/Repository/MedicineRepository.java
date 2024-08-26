package com.MedicalSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MedicalSystem.Model.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
