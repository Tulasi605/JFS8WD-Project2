package com.MedicalSystem;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.MedicalSystem.Controller.DoctorController;
import com.MedicalSystem.Model.Doctor;
import com.MedicalSystem.Service.DoctorService;

@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Test
    void getAllDoctors() throws Exception {
        Doctor doctor1 = new Doctor();
        doctor1.setName("Dr. Smith");
        Doctor doctor2 = new Doctor();
        doctor2.setName("Dr. John");

        when(doctorService.getAllDoctors()).thenReturn(Arrays.asList(doctor1, doctor2));

        mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("doctors"))
                .andExpect(view().name("doctors"));
    }

    @Test
    void addDoctor() throws Exception {
        mockMvc.perform(post("/doctors/add")
                .param("name", "Dr. Smith")
                .param("specialty", "Cardiology"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/doctors"));
    }

    @Test
    void editDoctor() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. Smith");
        doctor.setSpecialty("Cardiology");

        when(doctorService.getDoctorById(1L)).thenReturn(doctor);

        mockMvc.perform(get("/doctors/edit/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("doctor"))
                .andExpect(view().name("edit-doctor"));
    }
}
