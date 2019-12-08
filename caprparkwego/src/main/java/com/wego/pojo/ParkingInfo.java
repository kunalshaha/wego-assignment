package com.wego.pojo;

public class ParkingInfo {

    String carparkName;
    String carparkAddress;
    private double latitude;
    private double longitude;

    public ParkingInfo(String carparkName, String carparkAddress, double latitude, double longitude) {
        this.carparkName = carparkName;
        this.carparkAddress = carparkAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getCarparkName() {
        return carparkName;
    }

    public void setCarparkName(String carparkName) {
        this.carparkName = carparkName;
    }

    public String getCarparkAddress() {
        return carparkAddress;
    }

    public void setCarparkAddress(String carparkAddress) {
        this.carparkAddress = carparkAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "ParkingInfo{" +
                "carparkName='" + carparkName + '\'' +
                ", carparkAddress='" + carparkAddress + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
