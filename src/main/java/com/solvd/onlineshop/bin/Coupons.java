package com.solvd.onlineshop.bin;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;
@XmlRootElement(name = "Coupons")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coupons {
    @XmlAttribute(name = "id")
    private  int id;
    private String codes;

    private double discount;

    private Date expiration_Date;

    private  int user_Id;
    public Coupons() {
    }

    public Coupons(int id, String codes, double discount, Date expiration_Date, int user_Id) {
        this.id = id;
        this.codes = codes;
        this.discount = discount;
        this.expiration_Date = expiration_Date;
        this.user_Id = user_Id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCodes(String coupon_Code) {
        this.codes = codes;
    }

    public String getCodes() {
        return codes;
    }

    public void setDiscount(double discount_Percentage) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setExpiration_Date(Date expiration_Date) {
        this.expiration_Date = expiration_Date;
    }

    public Date getExpiration_Date() {
        return expiration_Date;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public int getUser_Id() {
        return user_Id;
    }

}
