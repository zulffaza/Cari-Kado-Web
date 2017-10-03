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

    @Column(name = "sub_district_postal_code")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    public SubDistrict() {

    }

    public SubDistrict(String name, String postalCode) {
        this.name = name;
        this.postalCode = postalCode;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
