package com.fitcore.dao;

import com.fitcore.model.MembershipPlan;
import com.fitcore.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDAO {

    public List<MembershipPlan> findAll() throws SQLException {
        List<MembershipPlan> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM MembershipPlans ORDER BY price")) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public MembershipPlan findById(int id) throws SQLException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM MembershipPlans WHERE plan_id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? map(rs) : null; }
        }
    }

    public MembershipPlan findById(int id, Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM MembershipPlans WHERE plan_id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? map(rs) : null; }
        }
    }

    private MembershipPlan map(ResultSet rs) throws SQLException {
        MembershipPlan p = new MembershipPlan();
        p.setPlanId(rs.getInt("plan_id"));
        p.setPlanName(rs.getString("plan_name"));
        p.setDuration(rs.getInt("duration"));
        p.setPrice(rs.getBigDecimal("price"));
        p.setFeatures(rs.getString("features"));
        return p;
    }
}
