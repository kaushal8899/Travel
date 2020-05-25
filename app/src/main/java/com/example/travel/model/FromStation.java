package com.example.travel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class FromStation {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("name")
    @Expose
    private String name;

    FromStation() {
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng2) {
        this.lng = lng2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code2) {
        this.code = code2;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat2) {
        this.lat = lat2;
    }
}
