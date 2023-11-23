package com.daanielowsky.crm.Services;

import com.daanielowsky.crm.DTO.CustomerDTO;
import com.daanielowsky.crm.Entities.Customer;
import com.daanielowsky.crm.Repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomersAsList(){
        return customerRepository.getAllBy();
    }


    @Transactional
    public Customer dataTransferFromDTOToEntity(CustomerDTO source, Long id) throws IllegalAccessException{
        Optional<Customer> optionalCustomer = customerRepository.getCustomerById(id);
        Customer destination = optionalCustomer.orElse(null);

        log.info(source.toString());


        Class<?> sourceClass = source.getClass();
        Class<?> destinationClass = destination.getClass();

        Field[] sourceFields = sourceClass.getDeclaredFields();
        Field[] destinationFields = destinationClass.getDeclaredFields();

        for (Field sFields : sourceFields){
            sFields.setAccessible(true);
            for (Field d : destinationFields){
                if(sFields.getName().equals(d.getName()) && sFields.getType().equals(d.getType())){
                    d.setAccessible(true);
                    d.set(destination, sFields.get(source));
                    break;
                }
            }
        }

        return destination;
    }
}
