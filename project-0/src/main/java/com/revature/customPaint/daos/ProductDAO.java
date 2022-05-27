package com.revature.customPaint.daos;

import com.revature.customPaint.models.Product;
import com.revature.customPaint.models.Store;
import com.revature.customPaint.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements CrudDAO<Product> {
    //connect to database
    Connection con = DatabaseConnection.getCon();

    public void save(Product obj) {
        try{
            //prepares SQL statement template
            PreparedStatement ps = con.prepareStatement("INSERT INTO products (id, name, category, description, cost) VALUES (?, ?, ?, ?, ?) ");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getCategory());
            ps.setString(4, obj.getDescription());
            ps.setDouble(5, obj.getCost());
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
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                products.add(new Product(rs.getString("id"), rs.getString("name"), rs.getString("category"), rs.getString("description"), rs.getDouble("cost")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return products;
    }
}
