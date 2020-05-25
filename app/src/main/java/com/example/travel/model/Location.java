package com.example.travel.model;

import java.io.Serializable;
import java.util.Arrays;

public class Location implements Serializable {
    String airport;
    String cityState;
    int day;
    String hasFlight;
    String hasRail;
    String location;
    String near_airport;
    String near_station;
    int night;
    String[] season;
    String[] season_end;
    String[] season_start;
    String station;

    public Location() {
    }

    public String getHasRail() {
        return this.hasRail;
    }

    public void setHasRail(String hasRail2) {
        this.hasRail = hasRail2;
    }

    public String getHasFlight() {
        return this.hasFlight;
    }

    public void setHasFlight(String hasFlight2) {
        this.hasFlight = hasFlight2;
    }

    public Location(String[] season2, String location2, String cityState2, String station2, String airport2, String near_station2, String near_airport2, String[] season_start2, String[] season_end2, String hasRail2, String hasFlight2, int day2, int night2) {
        this.season = season2;
        this.location = location2;
        this.cityState = cityState2;
        this.station = station2;
        this.airport = airport2;
        this.near_station = near_station2;
        this.near_airport = near_airport2;
        this.season_start = season_start2;
        this.season_end = season_end2;
        this.hasRail = hasRail2;
        this.hasFlight = hasFlight2;
        this.day = day2;
        this.night = night2;
    }

    public String[] getSeason() {
        return this.season;
    }

    public void setSeason(String[] season2) {
        this.season = season2;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location2) {
        this.location = location2;
    }

    public String getCityState() {
        return this.cityState;
    }

    public void setCityState(String cityState2) {
        this.cityState = cityState2;
    }

    public String getStation() {
        return this.station;
    }

    public void setStation(String station2) {
        this.station = station2;
    }

    public String getAirport() {
        return this.airport;
    }

    public void setAirport(String airport2) {
        this.airport = airport2;
    }

    public String getNear_station() {
        return this.near_station;
    }

    public void setNear_station(String near_station2) {
        this.near_station = near_station2;
    }

    public String getNear_airport() {
        return this.near_airport;
    }

    public void setNear_airport(String near_airport2) {
        this.near_airport = near_airport2;
    }

    public String[] getSeason_start() {
        return this.season_start;
    }

    public void setSeason_start(String[] season_start2) {
        this.season_start = season_start2;
    }

    public String[] getSeason_end() {
        return this.season_end;
    }

    public void setSeason_end(String[] season_end2) {
        this.season_end = season_end2;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day2) {
        this.day = day2;
    }

    public int getNight() {
        return this.night;
    }

    public void setNight(int night2) {
        this.night = night2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Location{season=");
        sb.append(Arrays.toString(this.season));
        sb.append(", location='");
        sb.append(this.location);
        sb.append('\'');
        sb.append(", cityState='");
        sb.append(this.cityState);
        sb.append('\'');
        sb.append(", station='");
        sb.append(this.station);
        sb.append('\'');
        sb.append(", airport='");
        sb.append(this.airport);
        sb.append('\'');
        sb.append(", near_station='");
        sb.append(this.near_station);
        sb.append('\'');
        sb.append(", near_airport='");
        sb.append(this.near_airport);
        sb.append('\'');
        sb.append(", season_start=");
        sb.append(Arrays.toString(this.season_start));
        sb.append(", season_end=");
        sb.append(Arrays.toString(this.season_end));
        sb.append(", hasRail=");
        sb.append(this.hasRail);
        sb.append(", hasFlight=");
        sb.append(this.hasFlight);
        sb.append(", day=");
        sb.append(this.day);
        sb.append(", night=");
        sb.append(this.night);
        sb.append('}');
        return sb.toString();
    }
}
