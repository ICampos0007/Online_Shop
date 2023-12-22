package com.solvd.onlineshop.bin;

public class OrderDetails {
    private int id;
    private int order_Id;

    private int product_Id;

    private int quantity;

    private double subtotal;
    public OrderDetails(int id, int order_Id, int product_Id, int quantity, double subtotal) {
        this.id = id;
        this.order_Id = order_Id;
        this.product_Id = product_Id;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOrder_Id(int order_Id) {
        this.order_Id = order_Id;
    }

    public int getOrder_Id() {
        return order_Id;
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

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getSubtotal() {
        return subtotal;
    }
}

