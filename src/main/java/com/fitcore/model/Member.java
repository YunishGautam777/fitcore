package com.fitcore.model;

import java.sql.Date;

public class Member {
    private int memberId;
    private Integer userId;
    private String name;
    private Date dob;
    private String contact;
    private String email;
    private String gender;
    private String address;
    private String membershipType;
    private Integer planId;
    private Date planExpiry;
    private String fitnessGoal;
    private Date joinDate;
    private String status;

    public int getMemberId() { return memberId; }
    public void setMemberId(int v) { this.memberId = v; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer v) { this.userId = v; }
    public String getName() { return name; }
    public void setName(String v) { this.name = v; }
    public Date getDob() { return dob; }
    public void setDob(Date v) { this.dob = v; }
    public String getContact() { return contact; }
    public void setContact(String v) { this.contact = v; }
    public String getEmail() { return email; }
    public void setEmail(String v) { this.email = v; }
    public String getGender() { return gender; }
    public void setGender(String v) { this.gender = v; }
    public String getAddress() { return address; }
    public void setAddress(String v) { this.address = v; }
    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String v) { this.membershipType = v; }
    public Integer getPlanId() { return planId; }
    public void setPlanId(Integer v) { this.planId = v; }
    public Date getPlanExpiry() { return planExpiry; }
    public void setPlanExpiry(Date v) { this.planExpiry = v; }
    public String getFitnessGoal() { return fitnessGoal; }
    public void setFitnessGoal(String v) { this.fitnessGoal = v; }
    public Date getJoinDate() { return joinDate; }
    public void setJoinDate(Date v) { this.joinDate = v; }
    public String getStatus() { return status; }
    public void setStatus(String v) { this.status = v; }
}
