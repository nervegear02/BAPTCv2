package com.example.baptcv2.Database;

public class Crops {
    private String userName, crop_name, crop_volume, date_planted, date_harvested, isShip;
    private boolean expandable;

    public Crops() {
    }

    public Crops(String userName, String crop_name, String crop_volume, String date_planted, String date_harvested, boolean expandable) {
        this.userName = userName;
        this.crop_name = crop_name;
        this.crop_volume = crop_volume;
        this.date_planted = date_planted;
        this.date_harvested = date_harvested;
        this.expandable = expandable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public String getCrop_name() {
        return crop_name;
    }

    public void setCrop_name(String crop_name) {
        this.crop_name = crop_name;
    }

    public String getCrop_volume() {
        return crop_volume;
    }

    public void setCrop_volume(String crop_volume) {
        this.crop_volume = crop_volume;
    }

    public String getDate_planted() {
        return date_planted;
    }

    public void setDate_planted(String date_planted) {
        this.date_planted = date_planted;
    }

    public String getDate_harvested() {
        return date_harvested;
    }

    public void setDate_harvested(String date_harvested) {
        this.date_harvested = date_harvested;
    }

    @Override
    public String toString() {
        return "Crops{" +
                "crop_name='" + crop_name + '\'' +
                ", crop_volume='" + crop_volume + '\'' +
                ", date_planted='" + date_planted + '\'' +
                ", date_harvested='" + date_harvested + '\'' +
                '}';
    }
}
