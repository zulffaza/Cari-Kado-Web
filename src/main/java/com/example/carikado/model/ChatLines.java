package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chat_lines")
public class ChatLines implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "chat_lines_id", nullable = false)
    private int id;

    @Column(name = "chat_lines_text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "chats_id", nullable = false)
    private Chats chats;

    public ChatLines() {

    }

    public ChatLines(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Chats getChats() {
        return chats;
    }

    public void setChats(Chats chats) {
        this.chats = chats;
    }
}
