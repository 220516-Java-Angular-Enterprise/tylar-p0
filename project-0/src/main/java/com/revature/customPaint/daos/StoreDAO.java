package com.revature.customPaint.daos;

import com.revature.customPaint.models.Store;
import com.revature.customPaint.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO implements CrudDAO<Store> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Store obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO stores (id, name, city, state) VALUES (?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getCity());
            ps.setString(4, obj.getState());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(Store obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Store getById(String id) {
        return null;
    }

    @Override
    public List<Store> getAll() {
        List<Store> stores = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stores");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                stores.add(new Store(rs.getString("id"), rs.getString("name"), rs.getString("city"), rs.getString("state")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return stores;
    }


}