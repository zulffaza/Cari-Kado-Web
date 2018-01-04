package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "gift_picture")
public class GiftPicture implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "gift_picture_id", nullable = false)
    private Integer id;

    @Column(name = "gift_picture_url", nullable = false)
    private String url;

    public GiftPicture() {

    }

    public GiftPicture(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
