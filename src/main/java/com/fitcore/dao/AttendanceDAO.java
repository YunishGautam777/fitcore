package com.fitcore.dao;

import com.fitcore.model.Attendance;
import com.fitcore.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    public List<Attendance> findByMember(int memberId) throws SQLException {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM Attendance WHERE member_id=? ORDER BY date DESC LIMIT 60";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public int countMonthlyVisits(int memberId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Attendance WHERE member_id=? AND " +
                "MONTH(date)=MONTH(CURRENT_DATE) AND YEAR(date)=YEAR(CURRENT_DATE)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            try (ResultSet rs = ps.executeQuery()) { rs.next(); return rs.getInt(1); }
        }
    }

    public boolean checkIn(int memberId) throws SQLException {
        String sql = "INSERT INTO Attendance(member_id, date, check_in_time) VALUES(?, CURRENT_DATE, CURRENT_TIME)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            return ps.executeUpdate() > 0;
        }
    }

    private Attendance map(ResultSet rs) throws SQLException {
        Attendance a = new Attendance();
        a.setAttendanceId(rs.getInt("attendance_id"));
        a.setMemberId(rs.getInt("member_id"));
        a.setDate(rs.getDate("date"));
        a.setCheckInTime(rs.getTime("check_in_time"));
        a.setCheckOutTime(rs.getTime("check_out_time"));
        return a;
    }
}
