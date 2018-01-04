package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "gift_info_budget")
public class GiftInfoBudget implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "gift_info_budget_id", nullable = false)
    private Integer id;

    @Column(name = "gift_info_budget_from", nullable = false)
    private Integer from;

    @Column(name = "gift_info_budget_to", nullable = false)
    private Integer to;

    public GiftInfoBudget() {

    }

    public GiftInfoBudget(Integer from, Integer to) {
        this.from = from;
        this.to = to;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }
}
