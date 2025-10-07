package com.bookweb.dto;

public class ReviewDTO {
    private Long id;
    private String reviewerName;
    private int rating;
    private String comment;

    public ReviewDTO(Long id, String reviewerName, int rating, String comment) {
        this.id = id;
        this.reviewerName = reviewerName;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReviewerName() { return reviewerName; }
    public void setReviewerName(String reviewerName) { this.reviewerName = reviewerName; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
