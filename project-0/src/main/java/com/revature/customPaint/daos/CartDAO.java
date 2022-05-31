package com.revature.customPaint.daos;

import com.revature.customPaint.models.Cart;
import com.revature.customPaint.util.database.DatabaseConnection;

import javax.naming.ldap.PagedResultsControl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CartDAO implements CrudDAO<Cart>{

    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Cart obj) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO cart (id, product_id, product_name, product_price, product_quantity) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getProductId());
            ps.setString(3, obj.getProductName());
            ps.setDouble(4, obj.getProductPrice());
            ps.setInt(5, obj.getProductQuantity());
            ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }

    }

    @Override
    public void update(Cart obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Cart getById(String id) {
        return null;
    }

    @Override
    public List<Cart> getAll() {
        return null;
    }
}
