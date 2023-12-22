package com.solvd.onlineshop.bin;

public class Users {
    private int id;
    private String username;

    private String passw;

    private String email;

    public Users(int id, String username, String passw, String email) {
        this.id = id;
        this.username = username;
        this.passw = passw;
        this.email = email;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String passw) {
        this.passw = passw;
    }

    public String getPassword() {
        return passw;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
