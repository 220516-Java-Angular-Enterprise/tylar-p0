package com.revature.customPaint.daos;

import com.revature.customPaint.models.Inventory;
import com.revature.customPaint.models.Product;
import com.revature.customPaint.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        List<Inventory> inventories = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inventory");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                inventories.add(new Inventory(rs.getString("store_id"), rs.getString("product_id"), rs.getInt("quantity")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return inventories;
    }

    public List<String> getAllStoreIds(){
        List<String> ids = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT store_id FROM inventory");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // String username = rs.getString("username");
                // usernames.add(username);

                ids.add(rs.getString("store_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return ids;
    }

    public void updateQuantity(String storeId, int quantity){
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE inventory SET quantity = ? WHERE store_id = ?");
            ps.setInt(1, quantity);
            ps.setString(2, storeId);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("An error occurred when tyring to update your database.");
        }
    }

}
