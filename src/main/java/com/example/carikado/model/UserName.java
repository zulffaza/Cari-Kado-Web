package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_name")
public class UserName implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_name_id")
    private int id;

    @Column(name = "user_first_name")
    private String firstName;

    @Column(name = "user_middle_name", nullable = true)
    private String middleName;

    @Column(name = "user_last_name")
    private String lastName;

    public UserName() {

    }

    public UserName(String firstName, String lastName) {
        this(firstName, null, lastName);
    }

    public UserName(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
