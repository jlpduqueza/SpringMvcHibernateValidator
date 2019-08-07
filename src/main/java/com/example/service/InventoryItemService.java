package com.example.service;

import java.util.List;

import com.example.domain.InventoryItem;

public interface InventoryItemService {
	
    public List <InventoryItem> getInventoryList();
    public List <InventoryItem> getInventoryListForUser();
    public void saveInventoryItem(InventoryItem inventoryItem);
    public void updateInventoryItemQuantity(int id, int quantity);
    public InventoryItem getInventoryItem(int id);
    public InventoryItem getInventoryItemByProductId(int id);
    public void deleteInventoryItem(int id);
	
}
