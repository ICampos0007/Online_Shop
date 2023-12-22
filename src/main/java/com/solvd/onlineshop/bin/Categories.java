package com.solvd.onlineshop.bin;

public class Categories {
    private  int id;
    private  String category_Name;

    public Categories(int id, String category_Name) {
        this.id = id;
        this.category_Name = category_Name;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCategory_Name(String category_Name) {
        this.category_Name = category_Name;
    }

    public String getCategory_Name() {
        return category_Name;
    }
}
