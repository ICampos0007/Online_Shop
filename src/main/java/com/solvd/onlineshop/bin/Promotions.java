package com.solvd.onlineshop.bin;

import java.sql.Timestamp;

import java.util.Date;

public class Promotions {
    private int id;
    private  String promotion_Name;
    private Timestamp start_Date;
    private Timestamp end_Date;
    private  int product_Id;

    public Promotions(int id, String promotion_Name, Timestamp start_Date, Timestamp end_Date, int product_Id) {
        this.id = id;
        this.promotion_Name = promotion_Name;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
        this.product_Id = product_Id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPromotion_Name(String promotion_Name) {
        this.promotion_Name = promotion_Name;
    }

    public String getPromotion_Name() {
        return promotion_Name;
    }

    public void setStart_Date(Timestamp start_Date) {
        this.start_Date = start_Date;
    }

    public Timestamp getStart_Date() {
        return start_Date;
    }

    public void setEnd_Date(Timestamp end_Date) {
        this.end_Date = end_Date;
    }

    public Timestamp getEnd_Date() {
        return end_Date;
    }

    public void setProduct_Id(int product_Id) {
        this.product_Id = product_Id;
    }

    public int getProduct_Id() {
        return product_Id;
    }
}
