package com.daanielowsky.crm.Repositories;


import com.daanielowsky.crm.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> getAllBy();

    Optional<Customer> getCustomerById(Long id);

}
