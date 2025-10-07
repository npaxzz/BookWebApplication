package com.bookweb.model;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewerName; // ชื่อผู้รีวิว
    private int rating;          // คะแนน
    private String comment;      // ความคิดเห็น

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;           // Item ที่รีวิว

    public Review() {}

    // ----- Getter/Setter -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReviewerName() { return reviewerName; }
    public void setReviewerName(String reviewerName) { this.reviewerName = reviewerName; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
}
