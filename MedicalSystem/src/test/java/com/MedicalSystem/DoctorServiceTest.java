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

import com.MedicalSystem.Model.Doctor;
import com.MedicalSystem.Repository.DoctorRepository;
import com.MedicalSystem.Service.DoctorService;

public class DoctorServiceTest {
	@Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    DoctorServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveDoctor() {
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        Doctor savedDoctor = doctorService.saveDoctor(doctor);
        assertEquals("Dr. Smith", savedDoctor.getName());
    }

    @Test
    void getAllDoctors() {
        Doctor doctor1 = new Doctor();
        doctor1.setName("Dr. Smith");
        Doctor doctor2 = new Doctor();
        doctor2.setName("Dr. John");

        when(doctorRepository.findAll()).thenReturn(Arrays.asList(doctor1, doctor2));

        List<Doctor> doctors = doctorService.getAllDoctors();
        assertEquals(2, doctors.size());
    }

    @Test
    void getDoctorById() {
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        Doctor foundDoctor = doctorService.getDoctorById(1L);
        assertEquals("Dr. Smith", foundDoctor.getName());
    }
}
