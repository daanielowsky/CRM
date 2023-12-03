package com.daanielowsky.crm.Controllers;

import com.daanielowsky.crm.Services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
