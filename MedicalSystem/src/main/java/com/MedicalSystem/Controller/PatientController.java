package com.MedicalSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MedicalSystem.Model.Patient;
import com.MedicalSystem.Repository.AppointmentRepository;
import com.MedicalSystem.Repository.PatientRepository;
import com.MedicalSystem.Service.PatientService;
import com.MedicalSystem.Model.Appointment;
import com.MedicalSystem.Utils.LoggedInUserHolder;

//import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping
    public String getAllPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }

    @GetMapping("/add")
    public String addPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "add-patient";
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String editPatientForm(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "edit-patient";
    }

    @PostMapping("/edit")
    public String editPatient(@ModelAttribute Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patients";
    }
    
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
    	Patient user = patientRepository.findByEmailAndPassword(email,password);
        if (user != null) {
            LoggedInUserHolder.setLoggedInUser(user);
            model.addAttribute("user",user);
            List<Appointment> appointments = appointmentRepository.findByEmail(email);
            model.addAttribute("appointments", appointments);
            return "dashbord";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }
    
    @PostMapping("/logout")
    public String logout() {
        LoggedInUserHolder.setLoggedInUser(null);
        return "redirect:/";
    }
    
}