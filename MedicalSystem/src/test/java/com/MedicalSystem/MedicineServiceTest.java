package com.MedicalSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.MedicalSystem.Model.Medicine;
import com.MedicalSystem.Repository.MedicineRepository;
import com.MedicalSystem.Service.MedicineService;

public class MedicineServiceTest {
	 @Mock
	    private MedicineRepository medicineRepository;

	    @InjectMocks
	    private MedicineService medicineService;

	    MedicineServiceTest() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void saveMedicine() {
	        Medicine medicine = new Medicine();
	        medicine.setName("Paracetamol");
	        when(medicineRepository.save(medicine)).thenReturn(medicine);

	        Medicine savedMedicine = medicineService.saveMedicine(medicine);
	        assertEquals("Paracetamol", savedMedicine.getName());
	    }

	    @Test
	    void getAllMedicines() {
	        Medicine medicine1 = new Medicine();
	        medicine1.setName("Paracetamol");
	        Medicine medicine2 = new Medicine();
	        medicine2.setName("Aspirin");

	        when(medicineRepository.findAll()).thenReturn(Arrays.asList(medicine1, medicine2));

	        List<Medicine> medicines = medicineService.getAllMedicines();
	        assertEquals(2, medicines.size());
	    }

	    @Test
	    void getMedicineById() {
	        Medicine medicine = new Medicine();
	        medicine.setName("Paracetamol");
	        when(medicineRepository.findById(1L)).thenReturn(Optional.of(medicine));

	        Medicine foundMedicine = medicineService.getMedicineById(1L);
	        assertEquals("Paracetamol", foundMedicine.getName());
	    }
}
