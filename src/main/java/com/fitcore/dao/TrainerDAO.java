package com.fitcore.dao;

import com.fitcore.model.Trainer;
import com.fitcore.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {

    public List<Trainer> findAll() throws SQLException {
        List<Trainer> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Trainers ORDER BY name")) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public Trainer findById(int id) throws SQLException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Trainers WHERE trainer_id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? map(rs) : null; }
        }
    }

    public List<Trainer> searchBySpecialization(String spec) throws SQLException {
        List<Trainer> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM Trainers WHERE specialization LIKE ? OR name LIKE ? ORDER BY name")) {
            String like = "%" + spec + "%";
            ps.setString(1, like); ps.setString(2, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public boolean insert(Trainer t) throws SQLException {
        String sql = "INSERT INTO Trainers(name, specialization, experience, contact, assigned_shift) VALUES(?,?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getName());
            ps.setString(2, t.getSpecialization());
            ps.setInt(3, t.getExperience());
            ps.setString(4, t.getContact());
            ps.setString(5, t.getAssignedShift());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(Trainer t) throws SQLException {
        String sql = "UPDATE Trainers SET name=?, specialization=?, experience=?, contact=?, assigned_shift=? WHERE trainer_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getName());
            ps.setString(2, t.getSpecialization());
            ps.setInt(3, t.getExperience());
            ps.setString(4, t.getContact());
            ps.setString(5, t.getAssignedShift());
            ps.setInt(6, t.getTrainerId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM Trainers WHERE trainer_id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Trainer map(ResultSet rs) throws SQLException {
        Trainer t = new Trainer();
        t.setTrainerId(rs.getInt("trainer_id"));
        t.setName(rs.getString("name"));
        t.setSpecialization(rs.getString("specialization"));
        t.setExperience(rs.getInt("experience"));
        t.setContact(rs.getString("contact"));
        t.setAssignedShift(rs.getString("assigned_shift"));
        return t;
    }
}
