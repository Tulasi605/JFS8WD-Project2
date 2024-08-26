package com.MedicalSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.MedicalSystem.Model.Patient;
import com.MedicalSystem.Service.AppointmentService;
import com.MedicalSystem.Service.DoctorService;
import com.MedicalSystem.Service.PatientService;
import com.MedicalSystem.Utils.LoggedInUserHolder;

@Controller
public class HomeController {
	@Autowired
    private PatientService patientService;
	
	@Autowired
    private DoctorService doctorService;
	
	@Autowired
    private AppointmentService appointmentService;

	@GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("totalP", patientService.getPTotalCount());
        model.addAttribute("totalA", appointmentService.getATotalCount());
        model.addAttribute("totalD", doctorService.getDTotalCount());
        Patient loggedInUser = LoggedInUserHolder.getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        return "home";
    }
	
	
	
}
