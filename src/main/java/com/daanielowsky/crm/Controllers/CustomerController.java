package com.daanielowsky.crm.Controllers;

import com.daanielowsky.crm.DTO.CustomerDTO;
import com.daanielowsky.crm.Entities.Customer;
import com.daanielowsky.crm.Repositories.CustomerRepository;
import com.daanielowsky.crm.Services.CustomerService;
import com.daanielowsky.crm.Services.RegistrationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/customers")
@Controller
@Slf4j
public class CustomerController {

    private CustomerService customerService;
    private RegistrationService registrationService;
    private CustomerRepository customerRepository;

    public CustomerController(CustomerService customerService, RegistrationService registrationService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.registrationService = registrationService;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String listOfAllCustomers(Model model){
        model.addAttribute("customers", customerService.getAllCustomersAsList());
        return "customers";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) throws IllegalAccessException{
        Optional<Customer> customerById = customerRepository.getCustomerById(id);
        Customer customer = customerById.orElse(null);
        model.addAttribute("customer", customer);
        return "customer-edit";
    }

    @PostMapping("/edit/{id}")
    public String editingCustomer(@PathVariable Long id, @ModelAttribute ("customer") Customer customer, BindingResult result) throws IllegalAccessException{
        if (result.hasErrors()){
            log.warn("There are " + result.getErrorCount() + " errors in registration form. Forwarding back to edit panel.");
            return "customer-edit";
//            TODO dodać walidacje
        }
        customerService.editingCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id){

        customerService.deleteCustomer(id);
        return "redirect:/customers";

    }
}
