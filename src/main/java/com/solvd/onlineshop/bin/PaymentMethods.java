package com.solvd.onlineshop.bin;

public class PaymentMethods {
    private int id;

    private int user_Id;

    private String card_Number;

    private int expiration_Date;

    private int cvv;

    public PaymentMethods(int id, int user_Id, String card_Number, int expiration_Date, int cvv) {
         this.id = id;
         this.user_Id = user_Id;
         this.card_Number = card_Number;
         this.expiration_Date = expiration_Date;
         this.cvv = cvv;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUser_id(int user_id) {
        this.user_Id = user_id;
    }

    public int getUser_id() {
        return user_Id;
    }

    public void setCard_Number(String card_Number) {
        this.card_Number = card_Number;
    }

    public String getCard_Number() {
        return card_Number;
    }

    public void setExpiration_Date(int expiration_Date) {
        this.expiration_Date = expiration_Date;
    }

    public int getExpiration_Date() {
        return expiration_Date;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getCvv() {
        return cvv;
    }
}
