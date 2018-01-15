package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "gift_info_picture")
public class GiftInfoPicture implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "gift_info_picture_id", nullable = false)
    private Integer id;

    @Column(name = "gift_info_picture_name", nullable = false)
    private String name;

    @Column(name = "gift_info_picture_type", nullable = false)
    private String type;

    @Column(name = "gift_info_picture_size", nullable = false)
    private Integer size;

    @Column(name = "gift_info_picture_url", nullable = false)
    private String url;

    public GiftInfoPicture() {

    }

    public GiftInfoPicture(String name, String type, Integer size, String url) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.url = url;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
