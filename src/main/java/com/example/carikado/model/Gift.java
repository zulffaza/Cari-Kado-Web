package com.example.carikado.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gift")
public class Gift implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "gift_id", nullable = false)
    private Integer id;

    @Column(name = "gift_name", nullable = false)
    private String name;

    @Column(name = "gift_description", nullable = false)
    @Type(type = "text")
    private String description;

    @Column(name = "gift_link", nullable = false)
    @Type(type = "text")
    private String link;

    @Column(name = "gift_gender", nullable = false)
    private String gender;

    @Column(name = "gift_age_from", nullable = false)
    private Integer ageFrom;

    @Column(name = "gift_age_to", nullable = false)
    private Integer ageTo;

    @Column(name = "gift_price", nullable = false)
    private Integer price;

    @Column(name = "gift_rating", nullable = false)
    private Integer rating;

    @Column(name = "gift_store", nullable = false)
    private String store;

    @OneToMany
    @JoinColumn(name = "gift_id", nullable = false)
    private List<GiftPicture> giftPictures = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "gift_categories",
            joinColumns = @JoinColumn(name = "gift_id"),
            inverseJoinColumns = @JoinColumn(name = "gift_info_category_id")
    )
    private List<GiftInfoCategory> giftInfoCategories = new ArrayList<>();

    public Gift() {

    }

    public Gift(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(Integer ageFrom) {
        this.ageFrom = ageFrom;
    }

    public Integer getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(Integer ageTo) {
        this.ageTo = ageTo;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void addGiftPicture(GiftPicture giftPicture) {
        giftPictures.add(giftPicture);
    }

    public void removeGiftPicture(GiftPicture giftPicture) {
        giftPictures.remove(giftPicture);
    }

    public List<GiftPicture> getGiftPictures() {
        return giftPictures;
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
}
