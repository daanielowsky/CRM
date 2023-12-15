package com.daanielowsky.crm.Services;

import com.daanielowsky.crm.DTO.CustomerDTO;
import com.daanielowsky.crm.Entities.Activity;
import com.daanielowsky.crm.Entities.Customer;
import com.daanielowsky.crm.Repositories.ActivityRepository;
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

    private ActivityRepository activityRepository;

    public CustomerService(CustomerRepository customerRepository, ActivityRepository activityRepository) {
        this.customerRepository = customerRepository;
        this.activityRepository = activityRepository;
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
            Object o = sFields.get(source);
            if(o == null){
                continue;
            }
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

    public void deleteCustomer(Long id){
        Optional<Customer> customerById = customerRepository.getCustomerById(id);
        Customer customer = customerById.orElse(null);
        List<Activity> activities = customer.getActivities();
        activities.stream().forEach(activity -> activityRepository.delete(activity));
        customerRepository.delete(customer);
    }

    public void editingCustomer(Customer customer) {
        Optional<Customer> customerById = customerRepository.getCustomerById(customer.getId());
        Customer customer1 = customerById.orElse(null);
        customer1.setName(customer.getName());
        customer1.setSurname(customer.getSurname());
        customer1.setEmail(customer.getEmail());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customer1.setNote(customer.getNote());
        customer1.setPostCode(customer.getPostCode());
        customer1.setCity(customer.getCity());
        log.info("Success of editing customer.");
        customerRepository.save(customer1);
    }
}
