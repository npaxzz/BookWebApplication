package com.bookweb.model;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewerName;
    private int rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;  // <-- แก้ตรงนี้

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // ถ้าต้องการเชื่อมกับผู้ใช้

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

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    
}
