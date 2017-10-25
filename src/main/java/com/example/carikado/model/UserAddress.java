package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_address")
public class UserAddress implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_address_id", nullable = false)
    private int id;

    @Column(name = "user_address_street", nullable = false)
    private String street;

    @Column(name = "user_address_hamlet")
    private int hamlet;

    @Column(name = "user_address_neighbourhood")
    private int neighbourhood;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @ManyToOne
    @JoinColumn(name = "sub_district_id", nullable = false)
    private SubDistrict subDistrict;

    public UserAddress() {

    }

    public UserAddress(String street, int hamlet, int neighbourhood) {
        this.street = street;
        this.hamlet = hamlet;
        this.neighbourhood = neighbourhood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHamlet() {
        return hamlet;
    }

    public void setHamlet(int hamlet) {
        this.hamlet = hamlet;
    }

    public int getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(int neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public SubDistrict getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(SubDistrict subDistrict) {
        this.subDistrict = subDistrict;
    }
}
