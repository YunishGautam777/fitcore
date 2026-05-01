package com.fitcore.dao;

import com.fitcore.model.DietPlan;
import com.fitcore.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DietPlanDAO {

    public List<DietPlan> findByMember(int memberId) throws SQLException {
        List<DietPlan> list = new ArrayList<>();
        String sql = "SELECT d.*, t.name AS trainer_name FROM DietPlans d " +
                "LEFT JOIN Trainers t ON d.trainer_id = t.trainer_id " +
                "WHERE d.member_id=? ORDER BY d.assigned_date DESC";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DietPlan d = map(rs);
                    d.setTrainerName(rs.getString("trainer_name"));
                    list.add(d);
                }
            }
        }
        return list;
    }

    public boolean insert(DietPlan d) throws SQLException {
        String sql = "INSERT INTO DietPlans(member_id, trainer_id, calories, meal_details, assigned_date) VALUES(?,?,?,?,CURRENT_DATE)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, d.getMemberId());
            if (d.getTrainerId() == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, d.getTrainerId());
            ps.setInt(3, d.getCalories());
            ps.setString(4, d.getMealDetails());
            return ps.executeUpdate() > 0;
        }
    }

    private DietPlan map(ResultSet rs) throws SQLException {
        DietPlan d = new DietPlan();
        d.setDietId(rs.getInt("diet_id"));
        d.setMemberId(rs.getInt("member_id"));
        int tid = rs.getInt("trainer_id");
        if (!rs.wasNull()) d.setTrainerId(tid);
        d.setCalories(rs.getInt("calories"));
        d.setMealDetails(rs.getString("meal_details"));
        d.setAssignedDate(rs.getDate("assigned_date"));
        return d;
    }
}
