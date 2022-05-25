package com.revature.customPaint.services;

import com.revature.customPaint.daos.StoreDAO;
import com.revature.customPaint.daos.StoreDAO;
import com.revature.customPaint.models.Store;

import java.util.List;

public class StoreService {
    private final StoreDAO storeDAO;

    public StoreService(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    public void register(Store store) {
        storeDAO.save(store);
    }

    public List<Store> getAllStores() {
        return storeDAO.getAll();
    }
}