package com.revature.customPaint.daos;

import com.revature.customPaint.models.Inventory;
import com.revature.customPaint.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InventoryDAO implements CrudDAO<Inventory>{
    @Override
    public void save(Inventory obj) {
        Connection con = DatabaseConnection.getCon();

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO stores (product_id, store_id, quantity) VALUES (?, ?, ?)");
            ps.setString(2, obj.getProductId());
            ps.setString(3, obj.getStoreId());
            ps.setInt(4, obj.getQuantity());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }


    @Override
    public void update(Inventory obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Inventory getById(String id) {
        return null;
    }

    @Override
    public List<Inventory> getAll() {
        return null;
    }
}
