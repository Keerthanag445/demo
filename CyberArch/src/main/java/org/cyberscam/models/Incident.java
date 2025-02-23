package org.cyberscam.models;

public class Incident {
    private int IncidentId;
    private int UserId;
    private String Title;
    private String Description;
    private int CategoryId;
    private String EvidencePath;
    private String Status;

    public Incident() {}

    public Incident(int UserId, String Title, String Description, int CategoryId, String EvidencePath) {
        this.UserId = UserId;
        this.Title = Title;
        this.Description = Description;
        this.CategoryId = CategoryId;
        this.EvidencePath = EvidencePath;
        this.Status = "Pending";
    }

    public int getIncidentId() {
        return IncidentId;
    }

    public void setIncidentId(int IncidentId) {
        this.IncidentId = IncidentId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }
    
    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int CategoryId) {
        this.CategoryId = CategoryId;
    }

    public String getEvidencePath() {
        return EvidencePath;
    }

    public void setEvidencePath(String EvidencePath) {
        this.EvidencePath = EvidencePath;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
