package com.daanielowsky.crm.Controllers;

import com.daanielowsky.crm.DTO.CustomerDTO;
import com.daanielowsky.crm.DTO.EmployeeDTO;
import com.daanielowsky.crm.Entities.Employee;
import com.daanielowsky.crm.Enums.Roles;
import com.daanielowsky.crm.Services.EmployeeService;
import com.daanielowsky.crm.Services.RegistrationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegisterController {

    private RegistrationService registrationService;
    private EmployeeService employeeService;

    public RegisterController(RegistrationService registrationService, EmployeeService employeeService) {
        this.registrationService = registrationService;
        this.employeeService = employeeService;
    }

    @GetMapping("/customer")
    public String customerRegistration(Model model){
        model.addAttribute("customer", new CustomerDTO());
        model.addAttribute("employees", employeeService.getSalesRepresentativeForCustomerRegistration());
        return "customer-registration";
    }

    @PostMapping("/customer")
    public String registeringCustomer(@Valid @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult result) throws IllegalAccessException{
        log.info(customerDTO.toString());
        if (result.hasErrors()){
            log.warn("There are " + result.getErrorCount() + " errors in registration form. Forwarding back to registration form.");
            return "customer-registration";
        }

        registrationService.registeringCustomer(customerDTO);

        return "redirect:/";
    }

    @GetMapping("/employee")
    public String employeeRegistration(Model model){

        model.addAttribute("employee", new EmployeeDTO());
        model.addAttribute("roles", Roles.values());
        return "employee-registration";

    }

    @PostMapping("/employee")
    public String registeringEmployee(@Valid @ModelAttribute EmployeeDTO employeeDTO, BindingResult result, Model model){
        if(result.hasErrors()){
            log.warn("There are " + result.getErrorCount() + " errors in registration form. Forwarding back to registration form.");
            model.addAttribute("employees", employeeService.getSalesRepresentativeForCustomerRegistration());
            return "employee-registration";
        }

        registrationService.registeringEmployee(employeeDTO);
        return "redirect:/";
    }
}
