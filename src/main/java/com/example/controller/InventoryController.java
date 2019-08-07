package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.InventoryItemService;

@Controller
public class InventoryController {

	@Autowired
    private InventoryItemService inventoryItemService;
	
    @RequestMapping(value = "/home", method = { RequestMethod.GET, RequestMethod.POST })
    public String viewInventoryList(ModelMap model) {
    	model.put("inventoryList", inventoryItemService.getInventoryList());
        return "home";
    }
    
    @RequestMapping(value = "/add-to-cart-view", method = RequestMethod.GET)
    public String viewAddToCart(ModelMap model, @RequestParam("productId") int id) {
    	model.put("inventoryItem", inventoryItemService.getInventoryItem(id));
        return "add-to-cart";
    }
    
    @RequestMapping(value = "/admin/edit-stock", method = RequestMethod.GET)
    public String editStock(ModelMap model, @RequestParam("inventoryItemId") int id) {
    	model.put("inventoryItem", inventoryItemService.getInventoryItem(id));
        return "edit-stock";
    }
    
    @RequestMapping(value = "/admin/delete-stock", method = RequestMethod.GET)
    public String deleteStock(ModelMap model, @RequestParam("inventoryItemId") int id) {
    	inventoryItemService.deleteInventoryItem(id);
        return "redirect:/admin/home";
    }
    
    @RequestMapping(value = "/admin/update-stock", method = RequestMethod.POST)
    public String update(ModelMap model, @RequestParam("inventoryItemId") int id, 
    		@RequestParam("quantity") int quantity) {
    	inventoryItemService.updateInventoryItemQuantity(id, quantity);
        return "redirect:/admin/home";
    }
	
}
