package com.solvd.onlineshop.bin;

public class Addresses {
    private int id;

    private String address_Line_1;

    private String address_Line_2;

    private String city;

    private String state;

    private int postal_Code;

    private int user_Id;

    public Addresses(int id, String address_Line_1, String address_Line_2, String city, String state, int postal_Code, int user_Id) {
        this.id = id;
        this.address_Line_1 = address_Line_1;
        this.address_Line_2 = address_Line_2;
        this.city = city;
        this.state = state;
        this.postal_Code = postal_Code;
        this.user_Id = user_Id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAddress_line_1(String address_line_1) {
        this.address_Line_1 = address_line_1;
    }

    public String getAddress_line_1() {
        return address_Line_1;
    }

    public void setAddress_line_2(String address_line_2) {
        this.address_Line_2 = address_line_2;
    }

    public String getAddress_line_2() {
        return address_Line_2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setPostal_code(int postal_code) {
        this.postal_Code = postal_code;
    }

    public int getPostal_code() {
        return postal_Code;
    }

    public void setUser_id(int user_id) {
        this.user_Id = user_id;
    }

    public int getUser_id() {
        return user_Id;
    }
}
