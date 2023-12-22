package com.solvd.onlineshop.bin;

import java.util.Date;

public class Reviews {
    private  int id;
    private  int user_Id;
    private  int product_Id;
    private int rating;
    private String comment;
    private Date review_Date;

    public Reviews(int id, int user_Id, int product_Id, int rating, String comment, Date review_Date) {
        this.id = id;
        this.user_Id = user_Id;
        this.product_Id = product_Id;
        this.rating = rating;
        this.comment = comment;
        this.review_Date = review_Date;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setProduct_Id(int product_Id) {
        this.product_Id = product_Id;
    }

    public int getProduct_Id() {
        return product_Id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setReview_Date(Date review_Date) {
        this.review_Date = review_Date;
    }

    public Date getReview_Date() {
        return review_Date;
    }
}
