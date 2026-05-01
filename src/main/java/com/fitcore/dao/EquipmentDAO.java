package com.fitcore.dao;

import com.fitcore.model.Equipment;
import com.fitcore.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO {

    public List<Equipment> findAll() throws SQLException {
        List<Equipment> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Equipment ORDER BY name")) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public boolean insert(Equipment e) throws SQLException {
        String sql = "INSERT INTO Equipment(name, category, purchase_date, `condition`, quantity) VALUES(?,?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setString(2, e.getCategory());
            ps.setDate(3, e.getPurchaseDate());
            ps.setString(4, e.getCondition());
            ps.setInt(5, e.getQuantity());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(Equipment e) throws SQLException {
        String sql = "UPDATE Equipment SET name=?, category=?, `condition`=?, quantity=? WHERE equipment_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setString(2, e.getCategory());
            ps.setString(3, e.getCondition());
            ps.setInt(4, e.getQuantity());
            ps.setInt(5, e.getEquipmentId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM Equipment WHERE equipment_id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean addMaintenanceLog(int equipmentId, Date date, String note, java.math.BigDecimal cost) throws SQLException {
        String sql = "INSERT INTO MaintenanceLogs(equipment_id, log_date, note, cost) VALUES(?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, equipmentId);
            ps.setDate(2, date);
            ps.setString(3, note);
            ps.setBigDecimal(4, cost);
            return ps.executeUpdate() > 0;
        }
    }

    private Equipment map(ResultSet rs) throws SQLException {
        Equipment e = new Equipment();
        e.setEquipmentId(rs.getInt("equipment_id"));
        e.setName(rs.getString("name"));
        e.setCategory(rs.getString("category"));
        e.setPurchaseDate(rs.getDate("purchase_date"));
        e.setCondition(rs.getString("condition"));
        e.setQuantity(rs.getInt("quantity"));
        return e;
    }
}
