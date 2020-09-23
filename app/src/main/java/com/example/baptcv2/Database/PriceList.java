package com.example.baptcv2.Database;

public class PriceList {
    int price;
    String name;

    public PriceList() {
    }

    public PriceList(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
