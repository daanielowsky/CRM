package com.daanielowsky.crm.Controllers;

import com.daanielowsky.crm.Services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping
    public String showListOfItems(Model model){
        model.addAttribute("items", itemService.getListOfItems());
        return "items-list";
    }
}
