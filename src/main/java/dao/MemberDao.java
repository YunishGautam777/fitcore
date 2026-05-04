package dao;

import java.sql.*;
import java.util.*;
import util.DBUtil;
import model.Member;

/**
 * Data Access Object for Member entities.
 * Handles all database operations related to members.
 */
public class MemberDao {

    /**
     * Adds a new member to the database.
     * @param m The Member object to add
     * @throws SQLException if a database error occurs
     */
    public void addMember(Member m) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO members (name, email, phone, plan) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, m.getName());
            stmt.setString(2, m.getEmail());
            stmt.setString(3, m.getPhone());
            stmt.setString(4, m.getPlan());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    /**
     * Retrieves all members from the database.
     * @return List of all Member objects
     * @throws SQLException if a database error occurs
     */
    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM members";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Member m = new Member();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setEmail(rs.getString("email"));
                m.setPhone(rs.getString("phone"));
                m.setPlan(rs.getString("plan"));
                members.add(m);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return members;
    }

    /**
     * Updates an existing member in the database.
     * @param m The Member object with updated information
     * @throws SQLException if a database error occurs
     */
    public void updateMember(Member m) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE members SET name=?, email=?, phone=?, plan=? WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, m.getName());
            stmt.setString(2, m.getEmail());
            stmt.setString(3, m.getPhone());
            stmt.setString(4, m.getPlan());
            stmt.setInt(5, m.getId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    /**
     * Deletes a member from the database by ID.
     * @param id The ID of the member to delete
     * @throws SQLException if a database error occurs
     */
    public void deleteMember(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM members WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    /**
     * Searches for members by name or email.
     * @param keyword The search keyword
     * @return List of Member objects matching the search
     * @throws SQLException if a database error occurs
     */
    public List<Member> searchMembers(String keyword) throws SQLException {
        List<Member> members = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM members WHERE name LIKE ? OR email LIKE ?";
            stmt = conn.prepareStatement(sql);
            String searchKeyword = "%" + keyword + "%";
            stmt.setString(1, searchKeyword);
            stmt.setString(2, searchKeyword);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Member m = new Member();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setEmail(rs.getString("email"));
                m.setPhone(rs.getString("phone"));
                m.setPlan(rs.getString("plan"));
                members.add(m);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return members;
    }
}
