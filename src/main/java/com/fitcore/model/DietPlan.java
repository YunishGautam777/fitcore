package com.fitcore.model;

import java.sql.Date;

public class DietPlan {
    private int dietId;
    private int memberId;
    private Integer trainerId;
    private int calories;
    private String mealDetails;
    private Date assignedDate;
    private String trainerName;

    public int getDietId() { return dietId; }
    public void setDietId(int v) { this.dietId = v; }
    public int getMemberId() { return memberId; }
    public void setMemberId(int v) { this.memberId = v; }
    public Integer getTrainerId() { return trainerId; }
    public void setTrainerId(Integer v) { this.trainerId = v; }
    public int getCalories() { return calories; }
    public void setCalories(int v) { this.calories = v; }
    public String getMealDetails() { return mealDetails; }
    public void setMealDetails(String v) { this.mealDetails = v; }
    public Date getAssignedDate() { return assignedDate; }
    public void setAssignedDate(Date v) { this.assignedDate = v; }
    public String getTrainerName() { return trainerName; }
    public void setTrainerName(String v) { this.trainerName = v; }
}
