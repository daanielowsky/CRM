package com.daanielowsky.crm.Services;

import com.daanielowsky.crm.DTO.CustomerDTO;
import com.daanielowsky.crm.DTO.EmployeeDTO;
import com.daanielowsky.crm.Entities.Activity;
import com.daanielowsky.crm.Entities.Customer;
import com.daanielowsky.crm.Entities.Employee;
import com.daanielowsky.crm.Enums.Status;
import com.daanielowsky.crm.Repositories.CustomerRepository;
import com.daanielowsky.crm.Repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RegistrationService {

    private CustomerRepository customerRepository;
    private ActivityService activityService;

    private EmployeeRepository employeeRepository;

    public RegistrationService(CustomerRepository customerRepository, ActivityService activityService, EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.activityService = activityService;
        this.employeeRepository = employeeRepository;
    }

    public void registeringCustomer(CustomerDTO customerDTO){

        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer.setStatus(Status.NEW);
        customerRepository.save(customer);

        Activity activity = new Activity();
        activity.setMessage("Customer has been created.");
        activityService.createActivity(customer, activity);

//        log.info("Created new customer with ID: " + customer.getId() +
//                "\nImię: " + customer.getName() +
//                "\nNazwisko: " + customer.getSurname() +
//                "\nEmail: " + customer.getEmail() +
//                "\nNumer Telefonu: " + customer.getPhoneNumber() +
//                "\nKod Pocztowy: " + customer.getPostCode() +
//                "\nMiejscowość: " + customer.getCity() +
//                "\nNotatka: " + customer.getNote());
//
    }

    public void registeringEmployee (EmployeeDTO employeeDTO){

        ModelMapper modelMapper = new ModelMapper();

        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        employeeRepository.save(employee);

    }
}
