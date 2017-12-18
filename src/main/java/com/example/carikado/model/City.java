package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
public class City implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "city_id", nullable = false)
    private Integer id;

    @Column(name = "city_name", nullable = false)
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "province_id")
    private Province province;

    @Transient
    private List<District> districts = new ArrayList<>();

    public City() {

    }

    public City(String name) {
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void addDistrict(District district) {
        districts.add(district);
        district.setCity(this);
    }

    public void removeDistrict(District district) {
        districts.remove(district);
        district.setCity(null);
    }

    public List<District> getDistricts() {
        return districts;
    }
}
