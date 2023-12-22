package com.solvd.onlineshop.bin;

public class Wishlists {
    private int id;
    private int user_Id;
    private int product_Id;

    public Wishlists(int id, int user_Id, int product_Id) {
        this.id = id;
        this.user_Id = user_Id;
        this.product_Id = product_Id;
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
}
