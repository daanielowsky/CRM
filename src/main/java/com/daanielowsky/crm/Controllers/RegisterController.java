package com.daanielowsky.crm.Controllers;

import com.daanielowsky.crm.DTO.CustomerDTO;
import com.daanielowsky.crm.Services.RegistrationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegisterController {

    private RegistrationService registrationService;

    public RegisterController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/customer")
    public String customerRegistration(Model model){
        model.addAttribute("customer", new CustomerDTO());
        return "customer-registration";
    }

    @PostMapping("/customer")
    public String registeringCustomer(@Valid @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult result) throws IllegalAccessException{

        if (result.hasErrors()){
            log.warn("There are " + result.getErrorCount() + " errors in registration form. Forwarding back to registration form.");
            return "customer-registration";
        }

        registrationService.registeringCustomer(registrationService.fieldsFromDtoToCustomerEntity(customerDTO));

        return "redirect:/";
    }
}
