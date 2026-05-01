package com.fitcore.model;

import java.sql.Timestamp;

public class WorkoutSession {
    private int sessionId;
    private String name;
    private Integer trainerId;
    private String trainerName;     // joined display field
    private Timestamp schedule;
    private int capacity;
    private int enrolled;
    private String type;

    public int getSessionId() { return sessionId; }
    public void setSessionId(int v) { this.sessionId = v; }
    public String getName() { return name; }
    public void setName(String v) { this.name = v; }
    public Integer getTrainerId() { return trainerId; }
    public void setTrainerId(Integer v) { this.trainerId = v; }
    public String getTrainerName() { return trainerName; }
    public void setTrainerName(String v) { this.trainerName = v; }
    public Timestamp getSchedule() { return schedule; }
    public void setSchedule(Timestamp v) { this.schedule = v; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int v) { this.capacity = v; }
    public int getEnrolled() { return enrolled; }
    public void setEnrolled(int v) { this.enrolled = v; }
    public String getType() { return type; }
    public void setType(String v) { this.type = v; }
    public boolean isFull() { return enrolled >= capacity; }
}
