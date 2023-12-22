package com.solvd.onlineshop.bin;

import java.util.Date;

public class Orders {
    private int id;

    private int user_Id;

    private Date order_date;

    private double total_price;

    public Orders(int id, int user_Id, Date order_date,double total_price) {
        this.id = id;
        this.user_Id = user_Id;
        this.order_date = order_date;
        this.total_price = total_price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUser_id(int user_id) {
        this.user_Id = user_id;
    }

    public int getUser_id() {
        return user_Id;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public double getTotal_price() {
        return total_price;
    }
}
