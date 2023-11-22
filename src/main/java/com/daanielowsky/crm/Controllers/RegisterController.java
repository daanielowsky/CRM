package com.daanielowsky.crm.Controllers;

import com.daanielowsky.crm.DTO.CustomerDTO;
import com.daanielowsky.crm.Services.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String registeringCustomer(@ModelAttribute("customer") CustomerDTO customerDTO) throws IllegalAccessException{

        registrationService.registeringCustomer(registrationService.fieldsFromDtoToCustomerEntity(customerDTO));


        return "homepage";
    }
}
