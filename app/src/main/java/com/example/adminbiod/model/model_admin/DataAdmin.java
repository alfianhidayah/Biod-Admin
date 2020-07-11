package com.example.adminbiod.model.model_admin;

public class DataAdmin {
    private String username, password;
    private Integer id;

    public DataAdmin(String username, String password, Integer id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }
}
