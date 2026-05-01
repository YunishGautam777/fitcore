package com.fitcore.dao;

import com.fitcore.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactDAO {
    public boolean insert(String name, String email, String subject, String message) throws SQLException {
        String sql = "INSERT INTO ContactMessages(name, email, subject, message) VALUES(?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, subject);
            ps.setString(4, message);
            return ps.executeUpdate() > 0;
        }
    }
}
