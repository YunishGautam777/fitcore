package com.fitcore.model;

import java.sql.Date;

public class Equipment {
    private int equipmentId;
    private String name;
    private String category;
    private Date purchaseDate;
    private String condition;
    private int quantity;

    public int getEquipmentId() { return equipmentId; }
    public void setEquipmentId(int v) { this.equipmentId = v; }
    public String getName() { return name; }
    public void setName(String v) { this.name = v; }
    public String getCategory() { return category; }
    public void setCategory(String v) { this.category = v; }
    public Date getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(Date v) { this.purchaseDate = v; }
    public String getCondition() { return condition; }
    public void setCondition(String v) { this.condition = v; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int v) { this.quantity = v; }
}
