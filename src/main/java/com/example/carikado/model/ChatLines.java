package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chat_lines")
public class ChatLines implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "chat_lines_id", nullable = false)
    private Integer id;

    @Column(name = "chat_lines_text", nullable = false)
    private String text;

    public ChatLines() {

    }

    public ChatLines(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
