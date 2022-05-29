package com.revature.customPaint.services;

import com.revature.customPaint.daos.InventoryDAO;
import com.revature.customPaint.daos.ProductDAO;
import com.revature.customPaint.models.Inventory;
import com.revature.customPaint.models.Product;

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
}
