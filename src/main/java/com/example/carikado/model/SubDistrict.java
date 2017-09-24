package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sub_district")
public class SubDistrict implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "sub_district_id")
    private int id;

    @Column(name = "sub_district_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    public SubDistrict() {

    }

    public SubDistrict(String name) {
        this.name = name;
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
