package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "gift_info_age")
public class GiftInfoAge implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "gift_info_age_id")
    private int id;

    @Column(name = "gift_info_age_from")
    private String from;

    @Column(name = "gift_info_age_to")
    private String to;

    public GiftInfoAge() {

    }

    public GiftInfoAge(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
