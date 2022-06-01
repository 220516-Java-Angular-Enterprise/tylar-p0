package com.revature.customPaint.services;

import com.revature.customPaint.daos.StoreDAO;
import junit.framework.TestCase;

public class StoreServiceTest extends TestCase {
    StoreService storeService = new StoreService(new StoreDAO());

    public void testIsNotDuplicateCity() {
        String notDuplicateCity = "atlanta";
        String notDuplicateCity2 = "Orlando";

        boolean isTrue = storeService.isNotDuplicateCity(notDuplicateCity);
        boolean isTrue2 = storeService.isNotDuplicateCity(notDuplicateCity2);

        assertTrue(isTrue);
        assertTrue(isTrue2);
    }
}