package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "gift_info")
public class GiftInfo implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "gift_info_id", nullable = false)
    private Integer id;

    @Column(name = "gift_info_title", nullable = false)
    private String title;

    @Column(name = "gift_info_description", nullable = false)
    private String description;

    @Column(name = "gift_info_essence", nullable = false)
    private String essence;

    @Column(name = "gift_info_created_at", nullable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gift_info_age_id", nullable = false)
    private GiftInfoAge giftInfoAge;

    @ManyToMany
    @JoinTable(
            name = "gift_info_categories",
            joinColumns = @JoinColumn(name = "gift_info_id"),
            inverseJoinColumns = @JoinColumn(name = "gift_info_category_id")
    )
    private List<GiftInfoCategory> giftInfoCategories = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gift_info_budget_id", nullable = false)
    private GiftInfoBudget giftInfoBudget;

    public GiftInfo() {

    }

    public GiftInfo(String title, String description, String essence) {
        this.title = title;
        this.description = description;
        this.essence = essence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEssence() {
        return essence;
    }

    public void setEssence(String essence) {
        this.essence = essence;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GiftInfoAge getGiftInfoAge() {
        return giftInfoAge;
    }

    public void setGiftInfoAge(GiftInfoAge giftInfoAge) {
        this.giftInfoAge = giftInfoAge;
    }

    public void addGiftInfoCategory(GiftInfoCategory giftInfoCategory) {
        giftInfoCategories.add(giftInfoCategory);
    }

    public void removeGiftInfoCategory(GiftInfoCategory giftInfoCategory) {
        giftInfoCategories.remove(giftInfoCategory);
    }

    public List<GiftInfoCategory> getGiftInfoCategories() {
        return giftInfoCategories;
    }

    public GiftInfoBudget getGiftInfoBudget() {
        return giftInfoBudget;
    }

    public void setGiftInfoBudget(GiftInfoBudget giftInfoBudget) {
        this.giftInfoBudget = giftInfoBudget;
    }
}
