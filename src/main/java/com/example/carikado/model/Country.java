package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country")
public class Country implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "country_id", nullable = false)
    private Integer id;

    @Column(name = "country_name", nullable = false)
    private String name;

    @Transient
    private List<Province> provinces = new ArrayList<>();

    public Country() {

    }

    public Country(String name) {
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

    public void addProvince(Province province) {
        provinces.add(province);
        province.setCountry(this);
    }

    public void removeProvince(Province province) {
        provinces.remove(province);
        province.setCountry(null);
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }
}
