package com.solvd.onlineshop.bin;

public class ProductHasCategories {
    private int id;
    private int product_Id;
    private  int category_Id;

    public ProductHasCategories(int id, int  product_Id,  int category_Id) {
        this.id = id;
        this.product_Id = product_Id;
        this.category_Id = category_Id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setProduct_Id(int product_Id) {
        this.product_Id = product_Id;
    }

    public int getProduct_Id() {
        return product_Id;
    }

    public void setCategory_Id(int category_Id) {
        this.category_Id = category_Id;
    }

    public int getCategory_Id() {
        return category_Id;
    }
}
