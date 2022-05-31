package com.revature.customPaint.services;

import com.revature.customPaint.daos.HistoryDAO;
import com.revature.customPaint.daos.ProductDAO;
import com.revature.customPaint.models.History;
import com.revature.customPaint.models.Product;

import java.util.List;

public class HistoryService {
    private final HistoryDAO historyDAO;

    public HistoryService(HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    public void register(History history) {
        historyDAO.save(history);
    }

    public List<History> getAllHistories() {
        return historyDAO.getAll();
    }
}
