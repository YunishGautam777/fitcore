package com.fitcore.dao;

import com.fitcore.model.WorkoutSession;
import com.fitcore.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO {

    private static final String BASE_SELECT =
            "SELECT s.*, t.name AS trainer_name FROM WorkoutSessions s " +
            "LEFT JOIN Trainers t ON s.trainer_id = t.trainer_id ";

    public List<WorkoutSession> findAll() throws SQLException {
        List<WorkoutSession> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(BASE_SELECT + "ORDER BY s.schedule")) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public WorkoutSession findById(int id) throws SQLException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(BASE_SELECT + "WHERE s.session_id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? map(rs) : null; }
        }
    }

    public WorkoutSession findById(int id, Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(BASE_SELECT + "WHERE s.session_id=? FOR UPDATE")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? map(rs) : null; }
        }
    }

    public List<WorkoutSession> search(String keyword) throws SQLException {
        List<WorkoutSession> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     BASE_SELECT + "WHERE s.name LIKE ? OR s.type LIKE ? OR t.name LIKE ? ORDER BY s.schedule")) {
            String like = "%" + keyword + "%";
            ps.setString(1, like); ps.setString(2, like); ps.setString(3, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public boolean insert(WorkoutSession s) throws SQLException {
        String sql = "INSERT INTO WorkoutSessions(name, trainer_id, schedule, capacity, type) VALUES(?,?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            if (s.getTrainerId() == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, s.getTrainerId());
            ps.setTimestamp(3, s.getSchedule());
            ps.setInt(4, s.getCapacity());
            ps.setString(5, s.getType());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM WorkoutSessions WHERE session_id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean incrementEnrolled(int sessionId, Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "UPDATE WorkoutSessions SET enrolled = enrolled + 1 WHERE session_id=?")) {
            ps.setInt(1, sessionId);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean book(int memberId, int sessionId, Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "INSERT INTO SessionBookings(session_id, member_id) VALUES(?,?)")) {
            ps.setInt(1, sessionId);
            ps.setInt(2, memberId);
            return ps.executeUpdate() > 0;
        }
    }

    public List<WorkoutSession> findBookedByMember(int memberId) throws SQLException {
        List<WorkoutSession> list = new ArrayList<>();
        String sql = BASE_SELECT +
                "JOIN SessionBookings b ON b.session_id = s.session_id WHERE b.member_id = ? ORDER BY s.schedule";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    private WorkoutSession map(ResultSet rs) throws SQLException {
        WorkoutSession s = new WorkoutSession();
        s.setSessionId(rs.getInt("session_id"));
        s.setName(rs.getString("name"));
        int tid = rs.getInt("trainer_id");
        if (!rs.wasNull()) s.setTrainerId(tid);
        s.setTrainerName(rs.getString("trainer_name"));
        s.setSchedule(rs.getTimestamp("schedule"));
        s.setCapacity(rs.getInt("capacity"));
        s.setEnrolled(rs.getInt("enrolled"));
        s.setType(rs.getString("type"));
        return s;
    }
}
