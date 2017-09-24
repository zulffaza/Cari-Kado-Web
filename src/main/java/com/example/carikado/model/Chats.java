package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "chats")
public class Chats implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "chats_id")
    private int id;

    @Column(name = "chats_user_name")
    private String userName;

    @Column(name = "chats_user_email")
    private String userEmail;

    @Column(name = "chats_created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "chats")
    private List<ChatLines> chatLineses = new ArrayList<>();

    public Chats() {

    }

    public Chats(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addChatLines(ChatLines chatLines) {
        chatLineses.add(chatLines);
        chatLines.setChats(this);
    }

    public void removeChatLines(ChatLines chatLines) {
        chatLineses.remove(chatLines);
        chatLines.setChats(null);
    }

    public List<ChatLines> getChatLineses() {
        return chatLineses;
    }
}
