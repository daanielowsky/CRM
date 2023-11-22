package com.daanielowsky.crm.Services;

import com.daanielowsky.crm.DTO.CustomerDTO;
import com.daanielowsky.crm.Entities.Customer;
import com.daanielowsky.crm.Repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
@Slf4j
public class RegistrationService {

    private CustomerRepository customerRepository;

    public RegistrationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer fieldsFromDtoToCustomerEntity(CustomerDTO customerDTO) throws IllegalAccessException{

        Customer customer = new Customer();

        Class<?> source = customerDTO.getClass();
        Class<?> destination = customer.getClass();

        Field[] sourceFields = source.getDeclaredFields();
        Field[] destinationFields = destination.getDeclaredFields();

        for (Field sField : sourceFields) {
            sField.setAccessible(true);

            for (Field destField : destinationFields) {
                if (sField.getName().equals(destField.getName()) && sField.getType().equals(destField.getType())){
                    destField.setAccessible(true);
                    destField.set(customer, sField.get(customerDTO));
                    break;
                }
            }
        }

        log.info("Data transferred from DTO file to Customer");

        return customer;
    }


    public void registeringCustomer(Customer customer){

        customerRepository.save(customer);

        log.info("Created new customer with ID: " + customer.getId() +
                "\nImię: " + customer.getName() +
                "\nNazwisko: " + customer.getSurname() +
                "\nEmail: " + customer.getEmail() +
                "\nNumer Telefonu: " + customer.getPhoneNumber() +
                "\nKod Pocztowy: " + customer.getPostCode() +
                "\nMiejscowość: " + customer.getCity());

    }






}
