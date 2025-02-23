package org.cyberscam.models;

public class Information {
    private int infoId;
    private int categoryId;
    private String title;
    private String content;
    private String sourceLink;
    private String postedBy;
    private String postedAt;

    public Information() {}

    public Information(int categoryId, String title, String content, String sourceLink, String postedBy, String postedAt) {
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.sourceLink = sourceLink;
        this.postedBy = postedBy;
        this.postedAt = postedAt;
    }

    // Getters and Setters
    public int getInfoId() {
        return infoId;
    }
    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getSourceLink() {
        return sourceLink;
    }
    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }
    public String getPostedBy() {
        return postedBy;
    }
    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
    public String getPostedAt() {
        return postedAt;
    }
    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }
}

