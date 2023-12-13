package com.daanielowsky.crm.Controllers;

import com.daanielowsky.crm.Entities.Employee;
import com.daanielowsky.crm.Enums.Roles;
import com.daanielowsky.crm.Services.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getListOfEmployees(Model model){
        model.addAttribute("employees", employeeService.getListOfEmployees());
        return "list-of-employees";
    }

    @GetMapping("/edit/{id}")
    public String editPanelForEmployee(@PathVariable Long id, Model model){
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("roles", Roles.values());
        return "employee-edit";
    }

    @PostMapping("/edit/{id}")
    public String editingGivenEmployee(@Valid @ModelAttribute Employee employee, BindingResult result){

        if (result.hasErrors()) {
            return "employee-edit";
        }
        employeeService.editEmployee(employee);
        return "redirect:/employees";

    }
}
