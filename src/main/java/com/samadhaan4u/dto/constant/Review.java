package com.samadhaan4u.dto.constant;

/**
 * Created by raghvendra.mishra on 09/06/18.
 */
public enum Review {
    NICKY("This is very wonderful site", "Nicky", "face-256.png"),
    RAM("This is very wonderful site", "Ram", "face-256.png"),
    SHYAM("This is very wonderful site", "Shyam", "face-256.png");

    private final String reviewContent;
    private final String reviewer;
    private final String imagePath;

    Review(String reviewContent, String reviewer, String imagePath) {
        this.reviewContent = reviewContent;
        this.reviewer = reviewer;
        this.imagePath = imagePath;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getImagePath() {
        return imagePath;
    }
}
