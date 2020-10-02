package com.example.baptcv2.Database;

public class Sold {
    private String crop_name, crop_price, crop_volume, date_sales, sold_to;

    public Sold() {
    }

    public Sold(String crop_name, String crop_price, String crop_volume, String date_sales, String sold_to) {
        this.crop_name = crop_name;
        this.crop_price = crop_price;
        this.crop_volume = crop_volume;
        this.date_sales = date_sales;
        this.sold_to = sold_to;
    }

    public String getCrop_name() {
        return crop_name;
    }

    public void setCrop_name(String crop_name) {
        this.crop_name = crop_name;
    }

    public String getCrop_price() {
        return crop_price;
    }

    public void setCrop_price(String crop_price) {
        this.crop_price = crop_price;
    }

    public String getCrop_volume() {
        return crop_volume;
    }

    public void setCrop_volume(String crop_volume) {
        this.crop_volume = crop_volume;
    }

    public String getDate_sales() {
        return date_sales;
    }

    public void setDate_sales(String date_sales) {
        this.date_sales = date_sales;
    }

    public String getSold_to() {
        return sold_to;
    }

    public void setSold_to(String sold_to) {
        this.sold_to = sold_to;
    }
}
