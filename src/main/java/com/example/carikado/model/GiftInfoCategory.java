package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gift_info_category")
public class GiftInfoCategory implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "gift_info_category_id", nullable = false)
    private Integer id;

    @Column(name = "gift_info_category_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "giftInfoCategories")
    private List<GiftInfo> giftInfos = new ArrayList<>();

    public GiftInfoCategory() {

    }

    public GiftInfoCategory(String name) {
        this.name = name;
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

    public void addGiftInfo(GiftInfo giftInfo) {
        giftInfos.add(giftInfo);
    }

    public void removeGiftInfo(GiftInfo giftInfo) {
        giftInfos.remove(giftInfo);
    }

    public List<GiftInfo> getGiftInfos() {
        return giftInfos;
    }
}
