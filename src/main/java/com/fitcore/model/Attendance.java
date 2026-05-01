package com.fitcore.model;

import java.sql.Date;
import java.sql.Time;

public class Attendance {
    private int attendanceId;
    private int memberId;
    private Date date;
    private Time checkInTime;
    private Time checkOutTime;

    public int getAttendanceId() { return attendanceId; }
    public void setAttendanceId(int v) { this.attendanceId = v; }
    public int getMemberId() { return memberId; }
    public void setMemberId(int v) { this.memberId = v; }
    public Date getDate() { return date; }
    public void setDate(Date v) { this.date = v; }
    public Time getCheckInTime() { return checkInTime; }
    public void setCheckInTime(Time v) { this.checkInTime = v; }
    public Time getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(Time v) { this.checkOutTime = v; }
}
