package com.daanielowsky.crm.Controllers;

import com.daanielowsky.crm.DTO.CustomerDTO;
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

    @GetMapping("/customer")
    public String customerRegistration(Model model){
        model.addAttribute("customer", new CustomerDTO());
        return "customer-registration";
    }

    @PostMapping("/customer")
    public String registeringCustomer(@ModelAttribute("customer") CustomerDTO customerDTO){
        log.info("Created new Customer!" +
                "\nName: " + customerDTO.getName() +
                "\nNazwisko: " + customerDTO.getSurname() +
                "\nMiasto: " + customerDTO.getCity());
        return "homepage";
    }
}
