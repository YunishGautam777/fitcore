package com.fitcore.model;

public class Trainer {
    private int trainerId;
    private String name;
    private String specialization;
    private int experience;
    private String contact;
    private String assignedShift;

    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int v) { this.trainerId = v; }
    public String getName() { return name; }
    public void setName(String v) { this.name = v; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String v) { this.specialization = v; }
    public int getExperience() { return experience; }
    public void setExperience(int v) { this.experience = v; }
    public String getContact() { return contact; }
    public void setContact(String v) { this.contact = v; }
    public String getAssignedShift() { return assignedShift; }
    public void setAssignedShift(String v) { this.assignedShift = v; }
}
