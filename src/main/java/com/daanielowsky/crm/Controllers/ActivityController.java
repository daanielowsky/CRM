package com.daanielowsky.crm.Controllers;


import com.daanielowsky.crm.Entities.Activity;
import com.daanielowsky.crm.Services.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/customers/activities")
public class ActivityController {

    private ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
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

    


}
