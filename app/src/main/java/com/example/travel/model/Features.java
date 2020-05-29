package com.example.travel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Features {

    @SerializedName("safe")
    @Expose
    private String safe;
    @SerializedName("restaurant")
    @Expose
    private String restaurant;
    @SerializedName("conference room")
    @Expose
    private String conferenceRoom;
    @SerializedName("swimming pool")
    @Expose
    private String swimmingPool;
    @SerializedName("bar")
    @Expose
    private String bar;
    @SerializedName("cctv")
    @Expose
    private String cctv;
    @SerializedName("massage")
    @Expose
    private String massage;
    @SerializedName("indoor games")
    @Expose
    private String indoorGames;
    @SerializedName("room service")
    @Expose
    private String roomService;
    @SerializedName("gym")
    @Expose
    private String gym;
    @SerializedName("lawn")
    @Expose
    private String lawn;
    @SerializedName("wi-fi")
    @Expose
    private String wiFi;
    @SerializedName("laundry sevice")
    @Expose
    private String laundrySevice;
    @SerializedName("parking")
    @Expose
    private String parking;

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getConferenceRoom() {
        return conferenceRoom;
    }

    public void setConferenceRoom(String conferenceRoom) {
        this.conferenceRoom = conferenceRoom;
    }

    public String getSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(String swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public String getCctv() {
        return cctv;
    }

    public void setCctv(String cctv) {
        this.cctv = cctv;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getIndoorGames() {
        return indoorGames;
    }

    public void setIndoorGames(String indoorGames) {
        this.indoorGames = indoorGames;
    }

    public String getRoomService() {
        return roomService;
    }

    public void setRoomService(String roomService) {
        this.roomService = roomService;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public String getLawn() {
        return lawn;
    }

    public void setLawn(String lawn) {
        this.lawn = lawn;
    }

    public String getWiFi() {
        return wiFi;
    }

    public void setWiFi(String wiFi) {
        this.wiFi = wiFi;
    }

    public String getLaundrySevice() {
        return laundrySevice;
    }

    public void setLaundrySevice(String laundrySevice) {
        this.laundrySevice = laundrySevice;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

}