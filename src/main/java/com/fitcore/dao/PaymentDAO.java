package com.fitcore.dao;

import com.fitcore.model.Payment;
import com.fitcore.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    public int insert(Payment p, Connection con) throws SQLException {
        String sql = "INSERT INTO Payments(member_id, plan_id, amount, date, status, payment_method) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, p.getMemberId());
            if (p.getPlanId() == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, p.getPlanId());
            ps.setBigDecimal(3, p.getAmount());
            ps.setDate(4, p.getDate());
            ps.setString(5, p.getStatus());
            ps.setString(6, p.getPaymentMethod());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    public List<Payment> findByMember(int memberId) throws SQLException {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM Payments WHERE member_id=? ORDER BY date DESC";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public List<Payment> findAllWithMemberName() throws SQLException {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT p.*, m.name AS member_name FROM Payments p " +
                "JOIN Members m ON p.member_id = m.member_id ORDER BY p.date DESC LIMIT 200";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Payment p = map(rs);
                p.setMemberName(rs.getString("member_name"));
                list.add(p);
            }
        }
        return list;
    }

    public boolean updateStatus(int paymentId, String status) throws SQLException {
        String sql = "UPDATE Payments SET status=? WHERE payment_id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, paymentId);
            return ps.executeUpdate() > 0;
        }
    }

    public java.math.BigDecimal monthlyRevenue() throws SQLException {
        String sql = "SELECT COALESCE(SUM(amount),0) FROM Payments WHERE status='PAID' AND " +
                "MONTH(date)=MONTH(CURRENT_DATE) AND YEAR(date)=YEAR(CURRENT_DATE)";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            rs.next(); return rs.getBigDecimal(1);
        }
    }

    private Payment map(ResultSet rs) throws SQLException {
        Payment p = new Payment();
        p.setPaymentId(rs.getInt("payment_id"));
        p.setMemberId(rs.getInt("member_id"));
        int pid = rs.getInt("plan_id");
        if (!rs.wasNull()) p.setPlanId(pid);
        p.setAmount(rs.getBigDecimal("amount"));
        p.setDate(rs.getDate("date"));
        p.setStatus(rs.getString("status"));
        p.setPaymentMethod(rs.getString("payment_method"));
        return p;
    }
}
