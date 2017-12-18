package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "user_email", unique = true, nullable = false)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_phone", nullable = false)
    private String phone;

    @Column(name = "user_created_at", nullable = false)
    private Date createdAt;

    @Column(name = "user_status", nullable = false)
    private UserStatus status;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_name_id", nullable = false)
    private UserName userName;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_address_id", nullable = false)
    private UserAddress userAddress;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_picture_id")
    private UserPicture userPicture;

    @OneToMany(mappedBy = "user")
    private List<GiftInfo> giftInfos = new ArrayList<>();

    public User() {

    }

    public User(String email, String password, String phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public UserPicture getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(UserPicture userPicture) {
        this.userPicture = userPicture;
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

    public static String passwordEncoder(String password) throws NoSuchAlgorithmException {
        StringBuilder stringBuffer = new StringBuilder();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());

        byte[] digest = messageDigest.digest();
        for (byte digestByte : digest)
            stringBuffer.append(Integer.toString((digestByte & 0xff) + 0x100, 16).substring(1));

        return stringBuffer.toString();
    }
}
