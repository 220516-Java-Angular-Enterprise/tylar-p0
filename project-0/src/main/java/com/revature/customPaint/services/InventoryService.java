package com.revature.customPaint.services;

import com.revature.customPaint.daos.InventoryDAO;
import com.revature.customPaint.models.Inventory;

import java.util.List;

public class InventoryService {
    private final InventoryDAO inventoryDAO;

    public InventoryService(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public void register(Inventory inventory) {
        inventoryDAO.save(inventory);
    }

    public List<Inventory> getAllInventory() {
        return inventoryDAO.getAll();
    }
    public boolean inventoryExists(String storeId){
        List<String> inventories = inventoryDAO.getAllStoreIds();

        return inventories.contains(storeId);
    }

    public void update(String storeId, String productId, int quantity){
        inventoryDAO.updateQuantity(storeId, productId, quantity);
    }
}
