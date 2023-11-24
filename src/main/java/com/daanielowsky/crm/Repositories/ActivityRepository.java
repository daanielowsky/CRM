package com.daanielowsky.crm.Repositories;

import com.daanielowsky.crm.Entities.Activity;
import com.daanielowsky.crm.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {


    @Query("SELECT a FROM Activity a WHERE a.customer =:customer")
    List<Activity> getAllActivitiesConnectedWithCustomer(@Param("customer")Customer customer);
}
