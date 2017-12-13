package com.example.carikado.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "role_name", unique = true, nullable = false)
    private String name;

    public Role() {

    }

    public Role(String name) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Role) {
            Role role = (Role) obj;

            if (role.getId() != 0)
                return id == role.getId();
            else
                return name.equals(role.getName());
        } else
            return false;
    }
}
