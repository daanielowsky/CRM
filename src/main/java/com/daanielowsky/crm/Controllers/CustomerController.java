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
        ModelMapper modelMapper = new ModelMapper();
        CustomerDTO customerDTO = modelMapper.map(customerRepository.getCustomerById(id), CustomerDTO.class);

        model.addAttribute("customer", customerDTO);
        return "customer-edit";
    }

    @PostMapping("/edit/{id}")
    public String editingCustomer(@PathVariable Long id, @Valid @ModelAttribute ("customer") CustomerDTO customer, BindingResult result) throws IllegalAccessException{
        if (result.hasErrors()){
            log.warn("There are " + result.getErrorCount() + " errors in registration form. Forwarding back to edit panel.");
            return "customer-edit";
        }

        Customer customer1 = customerService.dataTransferFromDTOToEntity(customer, id);
        customerRepository.save(customer1);
        log.info(customer1.toString());

        //TODO zrobić to jakoś ładniej


        return "redirect:/customers";
    }
}
