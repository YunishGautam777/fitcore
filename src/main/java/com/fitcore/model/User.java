package com.fitcore.model;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private String role;     // ADMIN, MEMBER
    private String status;   // PENDING, ACTIVE, INACTIVE, SUSPENDED
    private Timestamp createdAt;

    public int getUserId() { return userId; }
    public void setUserId(int v) { this.userId = v; }

    public String getUsername() { return username; }
    public void setUsername(String v) { this.username = v; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String v) { this.passwordHash = v; }

    public String getRole() { return role; }
    public void setRole(String v) { this.role = v; }

    public String getStatus() { return status; }
    public void setStatus(String v) { this.status = v; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp v) { this.createdAt = v; }
}
