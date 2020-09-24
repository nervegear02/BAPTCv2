package com.example.baptcv2.Database;

public class ShipDB {
    private String crop_name, crop_price, crop_volume, date_ship, destination;

    public ShipDB() {
    }

    public ShipDB(String crop_name, String crop_price, String crop_volume, String date_ship, String destination) {
        this.crop_name = crop_name;
        this.crop_price = crop_price;
        this.crop_volume = crop_volume;
        this.date_ship = date_ship;
        this.destination = destination;
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

    public String getDate_ship() {
        return date_ship;
    }

    public void setDate_ship(String date_ship) {
        this.date_ship = date_ship;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
