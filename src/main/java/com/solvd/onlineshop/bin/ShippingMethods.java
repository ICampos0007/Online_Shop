package com.solvd.onlineshop.bin;

public class ShippingMethods {
    private  int id;

    private  String shipping_Method_Name;

    private  double shipping_Cost;

    private  int order_Id;

    public ShippingMethods(int id, String shipping_Method_Name, double shipping_Cost, int order_Id) {
        this.id = id;
        this.shipping_Method_Name = shipping_Method_Name;
        this.shipping_Cost = shipping_Cost;
        this.order_Id = order_Id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setShipping_Method_Name(String shipping_Method_Name) {
        this.shipping_Method_Name = shipping_Method_Name;
    }

    public String getShipping_Method_Name() {
        return shipping_Method_Name;
    }

    public void setShipping_Cost(double shipping_Cost) {
        this.shipping_Cost = shipping_Cost;
    }

    public double getShipping_Cost() {
        return shipping_Cost;
    }

    public void setOrder_Id(int order_Id) {
        this.order_Id = order_Id;
    }

    public int getOrder_Id() {
        return order_Id;
    }
}
