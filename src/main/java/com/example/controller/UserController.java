package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.service.InventoryItemService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
    private InventoryItemService inventoryItemService;
	
    @RequestMapping(value = "/home", method = { RequestMethod.GET, RequestMethod.POST })
    public String viewInventoryList(ModelMap model) {
    	model.put("inventoryItemList", inventoryItemService.getInventoryListForUser());
        return "user-home";
    }
    
}
