package com.example.travel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Flight {

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("src code")
    @Expose
    private String srcCode;
    @SerializedName("dst code")
    @Expose
    private String dstCode;
    @SerializedName("takeoff time")
    @Expose
    private String takeoffTime;
    @SerializedName("landing time")
    @Expose
    private String landingTime;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("nonstop")
    @Expose
    private String nonstop;
    @SerializedName("airline")
    @Expose
    private String airline;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("connecting")
    @Expose
    private String connecting;

    /**
     * No args constructor for use in serialization
     *
     */
    public Flight() {
    }

    /**
     *
     * @param duration
     * @param nonstop
     * @param price
     * @param landingTime
     * @param destination
     * @param takeoffTime
     * @param srcCode
     * @param source
     * @param airline
     * @param connecting
     * @param dstCode
     */
    public Flight(String source, String destination, String srcCode, String dstCode, String takeoffTime, String landingTime, Integer duration, String nonstop, String airline, String price, String connecting) {
        super();
        this.source = source;
        this.destination = destination;
        this.srcCode = srcCode;
        this.dstCode = dstCode;
        this.takeoffTime = takeoffTime;
        this.landingTime = landingTime;
        this.duration = duration;
        this.nonstop = nonstop;
        this.airline = airline;
        this.price = price;
        this.connecting = connecting;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Flight withSource(String source) {
        this.source = source;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Flight withDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public String getSrcCode() {
        return srcCode;
    }

    public void setSrcCode(String srcCode) {
        this.srcCode = srcCode;
    }

    public Flight withSrcCode(String srcCode) {
        this.srcCode = srcCode;
        return this;
    }

    public String getDstCode() {
        return dstCode;
    }

    public void setDstCode(String dstCode) {
        this.dstCode = dstCode;
    }

    public Flight withDstCode(String dstCode) {
        this.dstCode = dstCode;
        return this;
    }

    public String getTakeoffTime() {
        return takeoffTime;
    }

    public void setTakeoffTime(String takeoffTime) {
        this.takeoffTime = takeoffTime;
    }

    public Flight withTakeoffTime(String takeoffTime) {
        this.takeoffTime = takeoffTime;
        return this;
    }

    public String getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(String landingTime) {
        this.landingTime = landingTime;
    }

    public Flight withLandingTime(String landingTime) {
        this.landingTime = landingTime;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Flight withDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getNonstop() {
        return nonstop;
    }

    public void setNonstop(String nonstop) {
        this.nonstop = nonstop;
    }

    public Flight withNonstop(String nonstop) {
        this.nonstop = nonstop;
        return this;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Flight withAirline(String airline) {
        this.airline = airline;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Flight withPrice(String price) {
        this.price = price;
        return this;
    }

    public String getConnecting() {
        return connecting;
    }

    public void setConnecting(String connecting) {
        this.connecting = connecting;
    }

    public Flight withConnecting(String connecting) {
        this.connecting = connecting;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("source", source).append("destination", destination).append("srcCode", srcCode).append("dstCode", dstCode).append("takeoffTime", takeoffTime).append("landingTime", landingTime).append("duration", duration).append("nonstop", nonstop).append("airline", airline).append("price", price).append("connecting", connecting).toString();
    }

}