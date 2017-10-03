package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "district")
public class District implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "district_id")
    private int id;

    @Column(name = "district_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "district")
    private List<SubDistrict> subDistricts = new ArrayList<>();

    public District() {

    }

    public District(String name) {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void addSubDistrict(SubDistrict subDistrict) {
        subDistricts.add(subDistrict);
        subDistrict.setDistrict(this);
    }

    public void removeSubDistrict(SubDistrict subDistrict) {
        subDistricts.remove(subDistrict);
        subDistrict.setDistrict(null);
    }

    public List<SubDistrict> getSubDistricts() {
        return subDistricts;
    }
}