package com.revature.customPaint.daos;

import com.revature.customPaint.models.Inventory;
import com.revature.customPaint.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InventoryDAO implements CrudDAO<Inventory>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Inventory obj) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO inventory (store_id, product_id, quantity) VALUES (?, ?, ?)");
            ps.setString(1, obj.getStoreId());
            ps.setString(2, obj.getProductId());
            ps.setInt(3, obj.getQuantity());
            ps.executeUpdate();
        }catch(SQLException e){
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
