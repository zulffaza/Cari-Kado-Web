package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "province")
public class Province implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "province_id")
    private int id;

    @Column(name = "province_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "province")
    private List<City> cities = new ArrayList<>();

    public Province() {

    }

    public Province(String name) {
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void addCity(City city) {
        cities.add(city);
        city.setProvince(this);
    }

    public void removeCity(City city) {
        cities.remove(city);
        city.setProvince(null);
    }

    public List<City> getCities() {
        return cities;
    }
}
