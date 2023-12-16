package com.soyhenry.moneywiseAPI.model;
import java.util.List;
import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    private String name;
    private String email;
    private String pass;
    private List<Entry> entries;

    public User() {
    }

    // create user constructor
    public User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    ;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}

