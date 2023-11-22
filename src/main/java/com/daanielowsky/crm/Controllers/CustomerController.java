package com.daanielowsky.crm.Controllers;

import com.daanielowsky.crm.Entities.Customer;
import com.daanielowsky.crm.Services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/customers")
@Controller
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String listOfAllCustomers(Model model){
        model.addAttribute("customers", customerService.getAllCustomersAsList());
        return "customers";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model){

        model.addAttribute("customer", customerService.gettingCustomerForEdit(id));
        return "customer-edit";

    }
}
