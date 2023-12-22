package com.solvd.onlineshop.bin;

public class Carts {
    private int id;
    private int user_Id;

    public Carts(int id, int user_Id) {
        this.id = id;
        this.user_Id = user_Id;
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
}
