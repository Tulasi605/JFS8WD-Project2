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

import com.MedicalSystem.Model.Medicine;
import com.MedicalSystem.Service.MedicineService;

//import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/medicines")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public String getAllMedicines(Model model) {
        List<Medicine> medicines = medicineService.getAllMedicines();
        model.addAttribute("medicines", medicines);
        return "medicines";
    }

    @GetMapping("/add")
    public String addMedicineForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "add-medicine";
    }

    @PostMapping("/add")
    public String addMedicine(@ModelAttribute Medicine medicine) {
        medicineService.saveMedicine(medicine);
        return "redirect:/medicines";
    }

    @GetMapping("/edit/{id}")
    public String editMedicineForm(@PathVariable Long id, Model model) {
        Medicine medicine = medicineService.getMedicineById(id);
        model.addAttribute("medicine", medicine);
        return "edit-medicine";
    }

    @PostMapping("/edit")
    public String editMedicine(@ModelAttribute Medicine medicine) {
        medicineService.saveMedicine(medicine);
        return "redirect:/medicines";
    }
}
