package com.fitcore.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Payment {
    private int paymentId;
    private int memberId;
    private Integer planId;
    private BigDecimal amount;
    private Date date;
    private String status;          // PENDING, PAID, OVERDUE, REFUNDED
    private String paymentMethod;   // CASH, CARD, ONLINE
    private String memberName;      // joined display field

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int v) { this.paymentId = v; }
    public int getMemberId() { return memberId; }
    public void setMemberId(int v) { this.memberId = v; }
    public Integer getPlanId() { return planId; }
    public void setPlanId(Integer v) { this.planId = v; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal v) { this.amount = v; }
    public Date getDate() { return date; }
    public void setDate(Date v) { this.date = v; }
    public String getStatus() { return status; }
    public void setStatus(String v) { this.status = v; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String v) { this.paymentMethod = v; }
    public String getMemberName() { return memberName; }
    public void setMemberName(String v) { this.memberName = v; }
}
