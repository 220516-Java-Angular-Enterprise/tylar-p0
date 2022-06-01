package com.revature.customPaint.services;

import com.revature.customPaint.daos.StoreDAO;
import com.revature.customPaint.models.Store;
import com.revature.customPaint.util.custom_exceptions.InvalidStoreException;

import java.util.List;

public class StoreService {
    private final StoreDAO storeDAO;

    public StoreService(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    public void register(Store store) {
        storeDAO.save(store);
    }
    public void delete(String id){storeDAO.delete(id); }

    public List<Store> getAllStores() {
        return storeDAO.getAll();
    }

    public boolean isNotDuplicateCity(String city) {
        List<String> cities = storeDAO.getAllCities();
        if (cities.contains(city)) throw new InvalidStoreException("There's already a store in that city :(");
        return true;
    }
}