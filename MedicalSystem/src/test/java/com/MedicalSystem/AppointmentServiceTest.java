package com.MedicalSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.MedicalSystem.Model.Appointment;
import com.MedicalSystem.Repository.AppointmentRepository;
import com.MedicalSystem.Service.AppointmentService;

public class AppointmentServiceTest {
	 @Mock
	    private AppointmentRepository appointmentRepository;

	    @InjectMocks
	    private AppointmentService appointmentService;

	    AppointmentServiceTest() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void saveAppointment() {
	        Appointment appointment = new Appointment();
	        appointment.setAppointmentTime(LocalDateTime.now());
	        when(appointmentRepository.save(appointment)).thenReturn(appointment);

	        Appointment savedAppointment = appointmentService.saveAppointment(appointment);
	        assertNotNull(savedAppointment.getAppointmentTime());
	    }

	    @Test
	    void getAllAppointments() {
	        Appointment appointment1 = new Appointment();
	        appointment1.setAppointmentTime(LocalDateTime.now());
	        Appointment appointment2 = new Appointment();
	        appointment2.setAppointmentTime(LocalDateTime.now().plusDays(1));

	        when(appointmentRepository.findAll()).thenReturn(Arrays.asList(appointment1, appointment2));

	        List<Appointment> appointments = appointmentService.getAllAppointments();
	        assertEquals(2, appointments.size());
	    }

	    @Test
	    void getAppointmentById() {
	        Appointment appointment = new Appointment();
	        appointment.setAppointmentTime(LocalDateTime.now());
	        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

	        Appointment foundAppointment = appointmentService.getAppointmentById(1L);
	        assertNotNull(foundAppointment.getAppointmentTime());
	    }
}
