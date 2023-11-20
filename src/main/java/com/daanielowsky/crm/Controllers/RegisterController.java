package com.daanielowsky.crm.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @GetMapping("/customer")
    public String customerRegistration(){
        return "customer-registration";
    }
}
