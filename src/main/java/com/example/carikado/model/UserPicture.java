package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_picture")
public class UserPicture implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_picture_id", nullable = false)
    private int id;

    @Column(name = "user_picture_name", nullable = false)
    private String name;

    @Column(name = "user_picture_type", nullable = false)
    private String type;

    @Column(name = "user_picture_size", nullable = false)
    private int size;

    @Column(name = "user_picture_url", nullable = false)
    private String url;

    public UserPicture() {

    }

    public UserPicture(String name, String type, int size, String url) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.url = url;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
