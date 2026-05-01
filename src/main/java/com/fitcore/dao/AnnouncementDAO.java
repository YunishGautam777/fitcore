package com.fitcore.dao;

import com.fitcore.model.Announcement;
import com.fitcore.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDAO {

    public List<Announcement> findRecent(int limit) throws SQLException {
        List<Announcement> list = new ArrayList<>();
        String sql = "SELECT * FROM Announcements ORDER BY pinned DESC, posted_at DESC LIMIT ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public boolean insert(Announcement a) throws SQLException {
        String sql = "INSERT INTO Announcements(title, body, category, pinned) VALUES(?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getBody());
            ps.setString(3, a.getCategory());
            ps.setBoolean(4, a.isPinned());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM Announcements WHERE announcement_id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Announcement map(ResultSet rs) throws SQLException {
        Announcement a = new Announcement();
        a.setAnnouncementId(rs.getInt("announcement_id"));
        a.setTitle(rs.getString("title"));
        a.setBody(rs.getString("body"));
        a.setCategory(rs.getString("category"));
        a.setPostedAt(rs.getTimestamp("posted_at"));
        a.setPinned(rs.getBoolean("pinned"));
        return a;
    }
}
