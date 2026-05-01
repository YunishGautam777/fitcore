package com.fitcore.model;

import java.math.BigDecimal;

public class MembershipPlan {
    private int planId;
    private String planName;
    private int duration;          // days
    private BigDecimal price;
    private String features;

    public int getPlanId() { return planId; }
    public void setPlanId(int v) { this.planId = v; }
    public String getPlanName() { return planName; }
    public void setPlanName(String v) { this.planName = v; }
    public int getDuration() { return duration; }
    public void setDuration(int v) { this.duration = v; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal v) { this.price = v; }
    public String getFeatures() { return features; }
    public void setFeatures(String v) { this.features = v; }
}
