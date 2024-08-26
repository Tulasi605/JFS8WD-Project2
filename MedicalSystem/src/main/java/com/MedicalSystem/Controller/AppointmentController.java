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

import com.MedicalSystem.Model.Appointment;
import com.MedicalSystem.Model.Doctor;
import com.MedicalSystem.Model.Medicine;
import com.MedicalSystem.Model.Patient;
import com.MedicalSystem.Service.AppointmentService;
import com.MedicalSystem.Service.DoctorService;
import com.MedicalSystem.Service.MedicineService;
import com.MedicalSystem.Service.PatientService;

//import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String getAllAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments";
    }

    @GetMapping("/add")
    public String addAppointmentForm(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        List<Medicine> medicines = medicineService.getAllMedicines();
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("patients", patients);
        model.addAttribute("medicines", medicines);
        model.addAttribute("doctors", doctors);
        model.addAttribute("appointment", new Appointment());
        return "add-appointment";
    }

    @PostMapping("/add")
    public String addAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "dashbord";
    }

    @GetMapping("/edit/{id}")
    public String editAppointmentForm(@PathVariable Long id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        List<Patient> patients = patientService.getAllPatients();
        List<Medicine> medicines = medicineService.getAllMedicines();
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("appointment", appointment);
        model.addAttribute("patients", patients);
        model.addAttribute("medicines", medicines);
        model.addAttribute("doctors", doctors);
        return "edit-appointment";
    }

    @PostMapping("/edit")
    public String editAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/appointments";
    }
}
