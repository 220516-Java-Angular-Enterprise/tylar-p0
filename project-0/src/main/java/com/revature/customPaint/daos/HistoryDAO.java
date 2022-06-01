package com.revature.customPaint.daos;

import com.revature.customPaint.models.History;
import com.revature.customPaint.models.Inventory;
import com.revature.customPaint.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO implements CrudDAO<History>{
    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(History obj) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO history (id, user_id, order_date, total_price) VALUES (?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUserId());
            ps.setString(3, obj.getOrderDate());
            ps.setDouble(4, obj.getTotalPrice());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(History obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public History getById(String id) {
        return null;
    }

    @Override
    public List<History> getAll() {
        List<History> histories = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM history");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                histories.add(new History(rs.getString("id"), rs.getString("user_id"), rs.getString("order_date"), rs.getDouble("total_price")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when tyring to get data from to the database.");
        }

        return histories;
    }
}
