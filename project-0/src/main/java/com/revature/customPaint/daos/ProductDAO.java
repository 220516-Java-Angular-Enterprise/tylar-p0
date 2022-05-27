package com.revature.customPaint.daos;

import com.revature.customPaint.models.Product;
import com.revature.customPaint.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductDAO implements CrudDAO<Product> {
    //connect to database
    Connection con = DatabaseConnection.getCon();

    public void save(Product obj) {
        try{
            //prepares SQL statement template
            PreparedStatement ps = con.prepareStatement("INSERT INTO products (id, name, category, description, quantity, cost) VALUES (?, ?, ?, ?, ?, ?) ");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getCategory());
            ps.setString(4, obj.getDescription());
            ps.setInt(5, obj.getQuantity());
            ps.setDouble(6, obj.getCost());
            ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Product obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Product getById(String id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }
}
