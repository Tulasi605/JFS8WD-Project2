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

import com.MedicalSystem.Model.Patient;
import com.MedicalSystem.Repository.PatientRepository;
import com.MedicalSystem.Service.PatientService;


public class PatientServiceTest {
	@Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    PatientServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void savePatient() {
        Patient patient = new Patient();
        patient.setFname("John Doe");
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.savePatient(patient);
        assertEquals("John Doe", savedPatient.getFname());
    }

    @Test
    void getAllPatients() {
        Patient patient1 = new Patient();
        patient1.setFname("John Doe");
        Patient patient2 = new Patient();
        patient2.setFname("Jane Doe");

        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        List<Patient> patients = patientService.getAllPatients();
        assertEquals(2, patients.size());
    }

    @Test
    void getPatientById() {
        Patient patient = new Patient();
        patient.setFname("John Doe");
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Patient foundPatient = patientService.getPatientById(1L);
        assertEquals("John Doe", foundPatient.getFname());
    }
}
