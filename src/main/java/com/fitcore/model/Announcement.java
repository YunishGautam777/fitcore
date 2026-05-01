package com.fitcore.model;

import java.sql.Timestamp;

public class Announcement {
    private int announcementId;
    private String title;
    private String body;
    private String category;
    private Timestamp postedAt;
    private boolean pinned;

    public int getAnnouncementId() { return announcementId; }
    public void setAnnouncementId(int v) { this.announcementId = v; }
    public String getTitle() { return title; }
    public void setTitle(String v) { this.title = v; }
    public String getBody() { return body; }
    public void setBody(String v) { this.body = v; }
    public String getCategory() { return category; }
    public void setCategory(String v) { this.category = v; }
    public Timestamp getPostedAt() { return postedAt; }
    public void setPostedAt(Timestamp v) { this.postedAt = v; }
    public boolean isPinned() { return pinned; }
    public void setPinned(boolean v) { this.pinned = v; }
}
