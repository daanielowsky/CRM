package com.daanielowsky.crm.Services;

import com.daanielowsky.crm.Entities.Activity;
import com.daanielowsky.crm.Entities.Customer;
import com.daanielowsky.crm.Repositories.ActivityRepository;
import com.daanielowsky.crm.Repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ActivityService {

    private CustomerRepository customerRepository;
    private ActivityRepository activityRepository;

    public ActivityService(CustomerRepository customerRepository, ActivityRepository activityRepository) {
        this.customerRepository = customerRepository;
        this.activityRepository = activityRepository;
    }

    @Transactional
    public void createActivity(Long id, Activity activity){
        Optional<Customer> customerById = customerRepository.getCustomerById(id);
        Customer customer = customerById.orElse(null);
        activity.setCustomer(customer);
        activityRepository.save(activity);
    }

    public List<Activity> getListOfActivitiesConnectedWithUser(Long id) {
        Optional<Customer> customerById = customerRepository.getCustomerById(id);
        Customer customer = customerById.orElse(null);
        return activityRepository.getAllActivitiesConnectedWithCustomer(customer);
    }

    public Activity getActivityForEdit(Long id){
        return activityRepository.getActivityById(id);
    }

    public void editActivity(Activity activity){
        activityRepository.save(activity);
    }
}
