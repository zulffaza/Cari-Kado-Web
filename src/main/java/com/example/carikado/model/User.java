package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_email", unique = true)
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_phone")
    private String phone;

    @Column(name = "user_created_at")
    private Date createdAt;

    @Column(name = "user_status")
    private UserStatus status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne
    @JoinColumn(name = "user_name_id")
    private UserName userName;

    @OneToOne
    @JoinColumn(name = "user_address_id")
    private UserAddress userAddress;

    @OneToMany(mappedBy = "user")
    private List<GiftInfo> giftInfos = new ArrayList<>();

    public User() {

    }

    public User(String email, String password, String phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserName getUserName() {
        return userName;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public void addGiftInfo(GiftInfo giftInfo) {
        giftInfos.add(giftInfo);
        giftInfo.setUser(this);
    }

    public void removeGiftInfo(GiftInfo giftInfo) {
        giftInfos.remove(giftInfo);
    }

    public List<GiftInfo> getGiftInfos() {
        return giftInfos;
    }

    public static String base64Encoder(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

    public static String base64Decoder(String s) {
        return new String(Base64.getDecoder().decode(s));
    }
}
