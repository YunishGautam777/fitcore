package com.fitcore.dao;

import com.fitcore.model.Member;
import com.fitcore.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public int insert(Member m, Connection con) throws SQLException {
        String sql = "INSERT INTO Members(user_id, name, dob, contact, email, gender, address, " +
                "membership_type, fitness_goal, join_date, status) VALUES (?,?,?,?,?,?,?,?,?,CURRENT_DATE,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (m.getUserId() == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, m.getUserId());
            ps.setString(2, m.getName());
            ps.setDate(3, m.getDob());
            ps.setString(4, m.getContact());
            ps.setString(5, m.getEmail());
            ps.setString(6, m.getGender());
            ps.setString(7, m.getAddress());
            ps.setString(8, m.getMembershipType());
            ps.setString(9, m.getFitnessGoal());
            ps.setString(10, m.getStatus() == null ? "ACTIVE" : m.getStatus());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    public Member findById(int memberId) throws SQLException {
        String sql = "SELECT * FROM Members WHERE member_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public Member findByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM Members WHERE user_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public List<Member> findAll() throws SQLException {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM Members ORDER BY join_date DESC";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public List<Member> search(String keyword) throws SQLException {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM Members WHERE name LIKE ? OR email LIKE ? OR contact LIKE ? ORDER BY name";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            String like = "%" + keyword + "%";
            ps.setString(1, like); ps.setString(2, like); ps.setString(3, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public boolean update(Member m) throws SQLException {
        String sql = "UPDATE Members SET name=?, contact=?, email=?, address=?, membership_type=?, " +
                "fitness_goal=?, status=? WHERE member_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getName());
            ps.setString(2, m.getContact());
            ps.setString(3, m.getEmail());
            ps.setString(4, m.getAddress());
            ps.setString(5, m.getMembershipType());
            ps.setString(6, m.getFitnessGoal());
            ps.setString(7, m.getStatus());
            ps.setInt(8, m.getMemberId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int memberId) throws SQLException {
        String sql = "DELETE FROM Members WHERE member_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updatePlan(int memberId, int planId, Date expiry, Connection con) throws SQLException {
        String sql = "UPDATE Members SET plan_id = ?, plan_expiry = ? WHERE member_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, planId);
            ps.setDate(2, expiry);
            ps.setInt(3, memberId);
            return ps.executeUpdate() > 0;
        }
    }

    public int countByStatus(String status) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Members WHERE status = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) { rs.next(); return rs.getInt(1); }
        }
    }

    private Member map(ResultSet rs) throws SQLException {
        Member m = new Member();
        m.setMemberId(rs.getInt("member_id"));
        int uid = rs.getInt("user_id");
        if (!rs.wasNull()) m.setUserId(uid);
        m.setName(rs.getString("name"));
        m.setDob(rs.getDate("dob"));
        m.setContact(rs.getString("contact"));
        m.setEmail(rs.getString("email"));
        m.setGender(rs.getString("gender"));
        m.setAddress(rs.getString("address"));
        m.setMembershipType(rs.getString("membership_type"));
        int pid = rs.getInt("plan_id");
        if (!rs.wasNull()) m.setPlanId(pid);
        m.setPlanExpiry(rs.getDate("plan_expiry"));
        m.setFitnessGoal(rs.getString("fitness_goal"));
        m.setJoinDate(rs.getDate("join_date"));
        m.setStatus(rs.getString("status"));
        return m;
    }
}
