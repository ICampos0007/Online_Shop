package com.solvd.onlineshop.bin;

public class CartItems {
    private int id;
    private int cart_Id;
    private int product_Id;
    private  int quantity;

    public CartItems(int id, int cart_Id, int product_Id, int quantity) {
        this.id = id;
        this.cart_Id = cart_Id;
        this.product_Id = product_Id;
        this.quantity = quantity;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCart_Id(int cart_Id) {
        this.cart_Id = cart_Id;
    }

    public int getCart_Id() {
        return cart_Id;
    }

    public void setProduct_Id(int product_Id) {
        this.product_Id = product_Id;
    }

    public int getProduct_Id() {
        return product_Id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
