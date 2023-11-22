package com.daanielowsky.crm.Services;

import com.daanielowsky.crm.DTO.CustomerDTO;
import com.daanielowsky.crm.Entities.Customer;
import com.daanielowsky.crm.Repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomersAsList(){
        return customerRepository.getAllBy();
    }

    public Customer gettingCustomerForEdit(Long id){
        Optional<Customer> optionalCustomer = customerRepository.getCustomerById(id);
        Customer customer = optionalCustomer.orElse(null);
        if (customer.equals(null)){
            return null;
        }
        return customer;

        //TODO zmienić z customer na customerDTO

    }
}
