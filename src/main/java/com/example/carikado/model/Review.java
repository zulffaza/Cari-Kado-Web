package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "review")
public class Review implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "review_id", nullable = false)
    private int id;

    @Column(name = "review_name", nullable = false)
    private String name;

    @Column(name = "review_email", nullable = false)
    private String email;

    @Column(name = "review_comment", nullable = false)
    private String comment;

    @Column(name = "review_rating", nullable = false)
    private int rating;

    @Column(name = "review_created_at", nullable = false)
    private Date createdAt;

    public Review() {

    }

    public Review(String name, String email, String comment, int rating) {
        this.name = name;
        this.email = email;
        this.comment = comment;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
