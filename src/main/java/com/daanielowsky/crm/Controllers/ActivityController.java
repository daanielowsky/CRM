package com.daanielowsky.crm.Controllers;


import com.daanielowsky.crm.Entities.Activity;
import com.daanielowsky.crm.Entities.Customer;
import com.daanielowsky.crm.Repositories.CustomerRepository;
import com.daanielowsky.crm.Services.ActivityService;
import com.daanielowsky.crm.Services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/customers/activities")
public class ActivityController {

    private ActivityService activityService;
    private CustomerService customerService;
    private CustomerRepository customerRepository;

    public ActivityController(ActivityService activityService, CustomerService customerService, CustomerRepository customerRepository) {
        this.activityService = activityService;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @GetMapping()
    public String listofAllActivities(){
        return "activities-list";
    }


    @GetMapping("/add/{id}")
    public String addActivityToCustomer(Model model){
        model.addAttribute("activity", new Activity());
        return "add-activity";
    }

    @PostMapping("/add/{id}")
    public String creatingActivity(@PathVariable Long id, @ModelAttribute ("activity") Activity activity){

        activityService.createActivity(id, activity);


        return "redirect:/customers";
    }

    @GetMapping("{id}")
    public String getAllActivitiesConnectedWithCustomer(@PathVariable("id") Long id, Model model){
        List<Activity> listOfActivitiesConnectedWithUser = activityService.getListOfActivitiesConnectedWithUser(id);
        model.addAttribute("activities", listOfActivitiesConnectedWithUser);
        return "activities-of-customer";
    }

    @GetMapping("/edit/{id}")
    public String editFormForActivity(@PathVariable("id") Long id, Model model){

        Activity activity = activityService.getActivityForEdit(id);

        model.addAttribute("activity", activity);

        return "edit-activity";
    }

    @PostMapping("/edit/{id}")
    public String editingActivity(@PathVariable("id") Long id, @ModelAttribute Activity activity){
        activityService.editActivity(id, activity);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteActivity(@PathVariable Long id){
        activityService.deleteActivity(id);
        return "redirect:/customers";
    }


}
