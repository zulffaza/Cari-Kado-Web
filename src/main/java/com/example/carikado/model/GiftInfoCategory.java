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

    @Column(name = "gift_info_category_name", nullable = false, unique = true)
    private String name;

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GiftInfoCategory) {
            GiftInfoCategory giftInfoCategory = (GiftInfoCategory) obj;

            return id.equals(giftInfoCategory.getId());
        } else
            return false;
    }
}
